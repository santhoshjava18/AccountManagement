package com.santhosh.accounts.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.santhosh.accounts.entity.Account;
import com.santhosh.accounts.model.AccountVO;

@Component
public class AccountTransformerImpl implements AccountTransformer{
	
	@Override
	public List<AccountVO> getAccountList(List<Account> accountList) {
		List<AccountVO> accountVOList = new ArrayList<AccountVO>();
		accountList.forEach(account->accountVOList.add(getAccountVO(account)));
		/*for (Account account : accountList) {
			accountVOList.add(getAccountVO(account));
		}*/
		return accountVOList;
	}

	public AccountVO getAccountVO(Account account) {
		AccountVO accountVO = new AccountVO();
		accountVO.setId(account.getAccountId());
		accountVO.setName(account.getName());
		accountVO.setUserid(account.getUsername());
		accountVO.setPassword(account.getPassword());
		accountVO.setRemarks(account.getComments());
		accountVO.setUrl(account.getUrl());
		accountVO.setAccountHolder(account.getAccountHolder().getAccountHolderId().toString());
		accountVO.setAccountHolderName(account.getAccountHolder().getAccountHolderName());
		return accountVO;
	}
	
	@Override
	public Account getAccount(AccountVO accountvo) {
		Account account = new Account();
		account.setAccountId(accountvo.getId());
		account.setName(accountvo.getName());
		account.setUsername(accountvo.getUserid());
		account.setPassword(accountvo.getPassword());
		account.setComments(accountvo.getRemarks());
		account.setUrl(accountvo.getUrl());
		return account;
	}
	
}
