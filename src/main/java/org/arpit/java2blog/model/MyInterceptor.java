package org.arpit.java2blog.model;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Table;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class MyInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = -8604352909121459025L;
	AuditTrail auditTrail = null;

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] newValues, Object[] oldValues,
			String[] propertyNames, Type[] types) {

		// Get the table name from entity
		Class<?> entityClass = entity.getClass();
		Table tableAnnotation = entityClass.getAnnotation(Table.class);            

		for (int i = 0; i < newValues.length; i++) {              

			// Check whether column value is updated
			if(oldValues[i] != null && newValues[i] != null 
					&& !(oldValues[i].equals(newValues[i]))){

				auditTrail = new AuditTrail();               
				auditTrail.setTableName(tableAnnotation.name()); // Set updated table name

				// Set updated column name
				Column col = null;
				try {                                                  
					String fieldName = propertyNames[i];
					auditTrail.setFieldName(fieldName);       
					
					Character firstChar =  fieldName.charAt(0);
					firstChar = Character.toUpperCase(firstChar);
					String fieldNameSubStr = fieldName.substring(1);
					String methodName = "get"+ firstChar + fieldNameSubStr;                       
					Class[] parameterTypes = new Class[]{};                           
					Method ueMethod = entityClass.getDeclaredMethod(methodName, parameterTypes);
					col = ueMethod.getAnnotation(Column.class);                         
				} catch (Exception e) {
					e.printStackTrace();
				}

				if(col != null){
					auditTrail.setColumnIdentifier(col.name());       
				}               

				// Set old value
				if(oldValues[i] != null){
					auditTrail.setOldValue(oldValues[i] == null  ? null : oldValues[i].toString());    
				}               

				// Set new value
				if(newValues[i] != null){
					auditTrail.setNewValue(newValues[i] == null ? null: newValues[i].toString());
				}    

				// Set database operation
				auditTrail.setOperation("Update");               

				// Set row primary key value
				auditTrail.setColumnIdentifier(id.toString());  

				//TODO: save object in database
				
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