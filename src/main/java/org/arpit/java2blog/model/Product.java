/**
 * 
 */
package org.arpit.java2blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.hibernate.envers.Audited;


@Entity
@Audited
@Proxy(lazy=false)

@Table(name="Product")
public class Product {

	@Id
	@Column(name="prd_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;   

	@Column(name="columnName") 
	private String columnName;

	@Column(name="columnValue")
	private String columnValue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
