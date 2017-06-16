package com.santhosh.accounts.configuration;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



public class AccountsInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AccountsMVCConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	 @Override
	    protected Filter[] getServletFilters() {
	    	Filter [] singleton = { new AccountsCORSFilter() };
	    	return singleton;
		}

}
