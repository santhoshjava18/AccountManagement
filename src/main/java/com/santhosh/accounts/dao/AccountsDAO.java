package com.santhosh.accounts.dao;

import java.util.List;

import com.santhosh.accounts.entity.Account;


public interface AccountsDAO {
	public List<Account> getAllAccounts();
	public void addAccount(Account account);
	Account findById(int id);
    void updateAccount(Account account);
    void deleteAccountById(int id);
}
