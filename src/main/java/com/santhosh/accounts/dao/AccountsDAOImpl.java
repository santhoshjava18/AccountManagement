package com.santhosh.accounts.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.santhosh.accounts.entity.Account;
import com.santhosh.accounts.util.Log;

@Repository
public class AccountsDAOImpl implements AccountsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Log Logger logger;

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAllAccounts() {
		logger.info("executing get all accounts .");
		return this.sessionFactory.getCurrentSession().createQuery("from Account").list();
	}
	
	@Override
	public void addAccount(Account account) {
		this.sessionFactory.getCurrentSession().save(account);

	}

	@Override
	public Account findById(int id) {
		return (Account)this.sessionFactory.getCurrentSession().load(Account.class, id);
	}

	@Override
	public void updateAccount(Account account) {
		this.sessionFactory.getCurrentSession().update(account);
		
	}

	@Override
	public void deleteAccountById(int id) {
		Account acc = new Account();
		acc.setAccountId(id);
		this.sessionFactory.getCurrentSession().delete(acc);
		
	}
	
	
	

}
