package org.arpit.java2blog.model;

import java.io.Serializable;

import javax.persistence.Table;

import org.arpit.java2blog.serviceImpl.DemoRuleServiceImpl;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class MyInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = -8604352909121459025L;
	AuditTrail auditTrail = null;

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] newValues, Object[] oldValues,
			String[] columnNames, Type[] types) {

		// Get the table name from entity
		Class<?> entityClass = entity.getClass();
		Table tableAnnotation = entityClass.getAnnotation(Table.class);            

		for (int i = 0; i < newValues.length; i++) {              

			// Check whether column value is updated
			if(oldValues[i] != null && newValues[i] != null && !(oldValues[i].equals(newValues[i]))){

				auditTrail = new AuditTrail();               
				
				auditTrail.setOperation("Update");  // Set database operation         
				auditTrail.setTableName(tableAnnotation.name()); // Set updated table name
				auditTrail.setFieldName(columnNames[i]);  // Set updated column name      

				// Set old value
				if(oldValues[i] != null){
					auditTrail.setOldValue(oldValues[i] == null  ? null : oldValues[i].toString());    
				}               

				// Set new value
				if(newValues[i] != null){
					auditTrail.setNewValue(newValues[i] == null ? null: newValues[i].toString());
				}    

				//TODO: save object in database
				System.out.println("\nOld value: " + auditTrail.getOldValue()
				+ "\nNew Value: " + auditTrail.getNewValue()
				+ "\nField Name: " + auditTrail.getFieldName()
				+ "\nTable Name: " + auditTrail.getTableName());
				
				DemoRuleServiceImpl.setAuditTrail(auditTrail);
			}           
		}      

		return false;
	}   

	// This method is called when object gets created.
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof RuleSetup ) {
			System.out.println("Rule Setup - Create Operation" );
			return true; 
		} else if (entity instanceof OrderLine ) {
			System.out.println("OrderLine - Create Operation" );
			return true; 
		}

		return false;
	}

}