package org.arpit.java2blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy=false)
@Table(name="Audit_Trail")
public class AuditTrail {

	@Id
	@Column(name="rule_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;   
	
	@Column(name="tableName")
	private String tableName;
	
	@Column(name="fieldName")
	private String fieldName;
	
	@Column(name="columnIdentifier")
	private String columnIdentifier;
	
	@Column(name="oldValue")
	private String oldValue;
	
	@Column(name="newValue")
	private String newValue;
	
	@Column(name="operation")
	private String operation;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnIdentifier() {
		return columnIdentifier;
	}

	public void setColumnIdentifier(String columnIdentifier) {
		this.columnIdentifier = columnIdentifier;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}
