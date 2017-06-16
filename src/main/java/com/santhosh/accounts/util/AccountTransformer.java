package com.santhosh.accounts.util;

import java.util.List;

import com.santhosh.accounts.entity.Account;
import com.santhosh.accounts.model.AccountVO;

public interface AccountTransformer {
	public List<AccountVO> getAccountList(List<Account> accountList);
	public Account getAccount(AccountVO accountvo);
	public AccountVO getAccountVO(Account account);
}
