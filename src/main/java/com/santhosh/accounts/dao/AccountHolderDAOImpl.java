package com.santhosh.accounts.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.santhosh.accounts.entity.AccountHolder;
import com.santhosh.accounts.util.Log;

@Repository
public class AccountHolderDAOImpl implements AccountHolderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Log Logger logger;
	
	@Override
	public AccountHolder findById(int id) {
		AccountHolder accountHolder = (AccountHolder)this.sessionFactory.getCurrentSession().load(AccountHolder.class, id);
		return accountHolder;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AccountHolder> getAllAccountHolders() {
		logger.info("executing get all accounts holders.");
		return this.sessionFactory.getCurrentSession().createQuery("from AccountHolder").list();
	}

}
