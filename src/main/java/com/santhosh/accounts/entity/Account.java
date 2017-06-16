package com.santhosh.accounts.entity;
// Generated Oct 1, 2016 4:48:28 PM by Hibernate Tools 5.2.0.Beta1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Account generated by hbm2java
 */
@Entity
@Table(name = "account", catalog = "accountmanagement")

public class Account implements java.io.Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer accountId;
	private AccountHolder accountHolder;
	private String name;
	private String username;
	private String password;
	private String url;
	private String comments;
	private Date createdTime;
	private Date updatedTime;

	public Account() {
	}

	public Account(AccountHolder accountHolder, String name, String username, String password) {
		this.accountHolder = accountHolder;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public Account(AccountHolder accountHolder, String name, String username, String password, String url,
			String comments, Date createdTime, Date updatedTime) {
		this.accountHolder = accountHolder;
		this.name = name;
		this.username = username;
		this.password = password;
		this.url = url;
		this.comments = comments;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "account_id", unique = true, nullable = false)
	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_holder_id", nullable = false)
	public AccountHolder getAccountHolder() {
		return this.accountHolder;
	}

	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}

	@Column(name = "name", nullable = false, length = 500)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "username", nullable = false, length = 500)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 500)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "url", length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "Comments", length = 500)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time", length = 19)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_time", length = 19)
	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

}