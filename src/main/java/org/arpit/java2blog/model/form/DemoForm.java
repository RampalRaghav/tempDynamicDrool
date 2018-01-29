package org.arpit.java2blog.model.form;

import java.util.HashMap;
import java.util.Map;

public class DemoForm {

    private Integer orderLineNumber;
    private Integer ruleNumber;
	private String columnName;
	private String columnValue;
	Map<String, String> columnValuePair = new HashMap<String, String>();
	
	public Integer getOrderLineNumber() {
		return orderLineNumber;
	}
	public void setOrderLineNumber(Integer orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}
	public Integer getRuleNumber() {
		return ruleNumber;
	}
	public void setRuleNumber(Integer ruleNumber) {
		this.ruleNumber = ruleNumber;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnValue() {
		return columnValue;
	}
	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}
	public Map<String, String> getColumnValuePair() {
		return columnValuePair;
	}
	public void setColumnValuePair(Map<String, String> columnValuePair) {
		this.columnValuePair = columnValuePair;
	}


}
