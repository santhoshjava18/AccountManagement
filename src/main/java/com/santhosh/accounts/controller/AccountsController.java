package com.santhosh.accounts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.santhosh.accounts.model.AccountVO;
import com.santhosh.accounts.service.AccountsService;
import com.santhosh.accounts.util.Log;


@RestController
public class AccountsController {
	
	 @Autowired
	 AccountsService accountsService;  
	 
	 @Log 
	 Logger logger;
	
	 //-------------------Retrieve All Accounts--------------------------------------------------------
    
    @RequestMapping(value = "/account/", method = RequestMethod.GET)
    public ResponseEntity<List<AccountVO>> listAllAccounts() {
    	List<AccountVO> accounts = accountsService.listAllAccounts();
    	 if(accounts.isEmpty()){
             return new ResponseEntity<List<AccountVO>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
         }
        return new ResponseEntity<List<AccountVO>>(accounts, HttpStatus.OK);
    }
    
   
    
    @RequestMapping(value = "/account/", method = RequestMethod.POST)
    public ResponseEntity<Void> createAccount(@RequestBody AccountVO accountVO,    UriComponentsBuilder ucBuilder) {
    	logger.debug("Creating Account " + accountVO.getId());
 
        if (accountVO.getId() != 0 &&  accountsService.isAccountExists(accountVO)) {
            System.out.println("A Account with name " + accountVO.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
    	accountsService.addAccount(accountVO);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/account/{id}").buildAndExpand(accountVO.getName()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
  //------------------- Update a Account --------------------------------------------------------
    
    @RequestMapping(value = "/account/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AccountVO> updateAccount(@PathVariable("id") int id, @RequestBody AccountVO accountVO) {
        System.out.println("Updating Account " + id);
         
        AccountVO curentAccount = accountsService.findById(id);
         
        if (curentAccount==null) {
            System.out.println("Account with id " + id + " not found");
            return new ResponseEntity<AccountVO>(HttpStatus.NOT_FOUND);
        }
 
        curentAccount.setName(accountVO.getName());
        curentAccount.setUserid(accountVO.getUserid());
        curentAccount.setPassword(accountVO.getPassword());
        curentAccount.setUrl(accountVO.getUrl());
        curentAccount.setRemarks(accountVO.getRemarks());
        curentAccount.setAccountHolder(accountVO.getAccountHolder());
        accountsService.updateAccount(curentAccount);
        return new ResponseEntity<AccountVO>(curentAccount, HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a Account --------------------------------------------------------
     
    @RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AccountVO> deleteAccount(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Account with id " + id);
 
        AccountVO user = accountsService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. Account with id " + id + " not found");
            return new ResponseEntity<AccountVO>(HttpStatus.NOT_FOUND);
        }
 
        accountsService.deleteAccountById(id);
        return new ResponseEntity<AccountVO>(HttpStatus.NO_CONTENT);
    }
    
    

}
