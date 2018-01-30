package org.arpit.java2blog.model;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class MyInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = -8604352909121459025L;
	private List<AuditTrail> auditTrailList = new ArrayList<AuditTrail>();   
	
	AuditTrail auditTrail = null;
	
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		
		// Get the table name from entity
		Class<?> entityClass = entity.getClass();
		Table tableAnnotation = entityClass.getAnnotation(Table.class);            

		for (int i = 0; i < currentState.length; i++) {              

			// Check whether column value is updated
			if(previousState[i] != null && currentState[i] != null 
					&& !(previousState[i].equals(currentState[i]))){
				
				auditTrail = new AuditTrail();               
				auditTrail.setTableName(tableAnnotation.name()); // Set updated table name

				// Set updated column name
				Column col = null;
				try {                                                  
					String filedName = propertyNames[i];
					Character firstChar =  filedName.charAt(0);
					firstChar = Character.toUpperCase(firstChar);
					String filedNameSubStr = filedName.substring(1);
					String methodName = "get"+ firstChar+filedNameSubStr;                       
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
				if(previousState[i] != null){
					auditTrail.setOldValue(previousState[i] == null  ? null : previousState[i].toString());    
				}               
				
				// Set new value
				if(currentState[i] != null){
					auditTrail.setNewValue(currentState[i] == null ? null: currentState[i].toString());
				}    
				
				// Set database operation
				auditTrail.setOperation("Update");               
				
				// Set row primary key value
				auditTrail.setColumnIdentifier(id.toString());  
		
				auditTrailList.add(auditTrail);
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