package com.santhosh.accounts.service;

import java.util.ArrayList;
import java.util.List;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santhosh.accounts.dao.AccountHolderDAO;
import com.santhosh.accounts.dao.AccountsDAO;
import com.santhosh.accounts.entity.Account;
import com.santhosh.accounts.entity.AccountHolder;
import com.santhosh.accounts.model.AccountVO;
import com.santhosh.accounts.util.AccountTransformer;

@Service("accountsService")
public class AccountsServiceImpl implements AccountsService {
	@Autowired
	AccountsDAO accountsDAO;
	
	@Autowired
	AccountHolderDAO accountHolderDAO;
	
	@Autowired
	AccountTransformer accountTransformer;
	
	
	@Autowired
	StandardPBEStringEncryptor standardPBEStringEncryptor;
	
	@Override
	@Transactional
	public List<AccountVO> listAllAccounts() {
		List<AccountVO> accountVOList = new ArrayList<AccountVO>();
		accountsDAO.getAllAccounts().forEach(account -> {
			accountVOList.add(accountTransformer.getAccountVO(account));
	    
		});
		accountVOList.forEach(accountVo-> accountVo.setPassword(standardPBEStringEncryptor.decrypt(accountVo.getPassword())));
    	return accountVOList;
	}

	@Override
	@Transactional
	public void addAccount(AccountVO accountVO) {
		accountVO.setPassword(standardPBEStringEncryptor.encrypt(accountVO.getPassword()));
		Account account = accountTransformer.getAccount(accountVO);
		AccountHolder accountHolder = new AccountHolder();
		accountHolder.setAccountHolderId(Integer.parseInt(accountVO.getAccountHolder()));
		account.setAccountHolder(accountHolder);
		accountsDAO.addAccount(account);
	}

	@Override
	@Transactional
	public AccountVO findById(int id) {
		return accountTransformer.getAccountVO(accountsDAO.findById(id));
	}

	@Override
	@Transactional
	public void updateAccount(AccountVO accountVO) {
		accountVO.setPassword(standardPBEStringEncryptor.encrypt(accountVO.getPassword()));
		Account account = accountTransformer.getAccount(accountVO);
		AccountHolder accountHolder = new AccountHolder();
		accountHolder.setAccountHolderId(Integer.parseInt(accountVO.getAccountHolder()));
		account.setAccountHolder(accountHolder);
		accountsDAO.updateAccount(account);
		
	}
	
	@Override
	@Transactional
	public boolean isAccountExists(AccountVO accountVO) {
		return (findById(accountVO.getId()) != null);
	}

	

	@Override
	@Transactional
	public void deleteAccountById(int id) {
		accountsDAO.deleteAccountById(id);
		
	}
	
	@Override
	@Transactional
	public List<AccountHolder> listAllAccountHolders() {
		return accountHolderDAO.getAllAccountHolders();
	}
	
	

}
