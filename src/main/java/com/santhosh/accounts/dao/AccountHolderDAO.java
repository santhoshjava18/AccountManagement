package com.santhosh.accounts.dao;

import java.util.List;

import com.santhosh.accounts.entity.AccountHolder;

public interface AccountHolderDAO {
	AccountHolder findById(int id);
	public List<AccountHolder> getAllAccountHolders();
}
