package org.arpit.java2blog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy=false)
@Table(name="Rule_Setup")
public class RuleSetup{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="rule_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;   

	@Column(name="ruleNumber")
	private Integer ruleNumber;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RULE_ACCOUNT", joinColumns = { @JoinColumn(name = "rule_id") }, inverseJoinColumns = { @JoinColumn(name = "account_id") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Account> account = new ArrayList<Account>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RULE_PRODUCT", joinColumns = { @JoinColumn(name = "rule_id") }, inverseJoinColumns = { @JoinColumn(name = "prd_id") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Product> product = new ArrayList<Product>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public Integer getRuleNumber() {
		return ruleNumber;
	}

	public void setRuleNumber(Integer ruleNumber) {
		this.ruleNumber = ruleNumber;
	}

}
