package com.santhosh.accounts.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.santhosh.accounts.entity.Account;
import com.santhosh.accounts.entity.AccountHolder;
import com.santhosh.accounts.util.Log;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.santhosh.accounts")
@EnableTransactionManagement
@PropertySource("classpath:accountmanagement.properties")
@PropertySource("classpath:database.properties")
public class AccountsApplicationContextConfig {
	
	 @Log 
	 Logger logger;
	 
	 private @Value("${encryptpassword}") 
	 String encryptpassword;
	 
	 private @Value("${driverclass}") 
	 String driverclass;
	 private @Value("${url}") 
	 String url;
	 private @Value("${databaseusername}") 
	 String databaseusername;
	 private @Value("${password}") 
	 String password;
	 
	 
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	
	@Bean(name = "standardPBEStringEncryptor")
	public StandardPBEStringEncryptor standardPBEStringEncryptor(){
		StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
		standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
		standardPBEStringEncryptor.setPassword(encryptpassword);
		return standardPBEStringEncryptor;
		
	}
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverclass);
		dataSource.setUrl(url);
		dataSource.setUsername(databaseusername);
		dataSource.setPassword(password);
		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClasses(Account.class);
		sessionBuilder.addAnnotatedClasses(AccountHolder.class);
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
	
}
