package com.santhosh.accounts.service;

import java.util.List;

import com.santhosh.accounts.entity.AccountHolder;
import com.santhosh.accounts.model.AccountVO;

public interface AccountsService {
     List<AccountVO> listAllAccounts();
     void addAccount(AccountVO accountVO);
     AccountVO findById(int id);
     void updateAccount(AccountVO accountVO);
     void deleteAccountById(int id);
     public boolean isAccountExists(AccountVO accountVO);
     public List<AccountHolder> listAllAccountHolders();
}
