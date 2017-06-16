package com.santhosh.accounts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.santhosh.accounts.entity.AccountHolder;
import com.santhosh.accounts.service.AccountsService;
import com.santhosh.accounts.util.Log;

@RestController
public class AccountHolderController {
	 @Autowired
	 AccountsService accountsService;  
	 
	 @Log 
	 Logger logger;
	 
	 @RequestMapping(value = "/accountholder/", method = RequestMethod.GET)
	    public ResponseEntity<List<AccountHolder>> listAllAccountHolders() {
	    	List<AccountHolder> accountholders = accountsService.listAllAccountHolders();
	    	 if(accountholders.isEmpty())
	             return new ResponseEntity<List<AccountHolder>>(HttpStatus.NO_CONTENT);
	        return new ResponseEntity<List<AccountHolder>>(accountholders, HttpStatus.OK);
	    }
}
