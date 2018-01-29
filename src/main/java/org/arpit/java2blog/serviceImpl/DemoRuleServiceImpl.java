package org.arpit.java2blog.serviceImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.arpit.java2blog.dao.DemoRuleDao;
import org.arpit.java2blog.model.Account;
import org.arpit.java2blog.model.OrderLine;
import org.arpit.java2blog.model.OrderMap;
import org.arpit.java2blog.model.RuleMap;
import org.arpit.java2blog.model.RuleSetup;
import org.arpit.java2blog.model.form.DemoForm;
import org.arpit.java2blog.revListner.CustomAgendaEventListener;
import org.arpit.java2blog.service.DemoRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.technorage.demo.drools.monitoring.TrackingAgendaEventListener;
import com.technorage.demo.drools.monitoring.TrackingWorkingMemoryEventListener;
import com.technorage.demo.drools.spring.DefaultKieSessionBean;
import com.technorage.demo.drools.spring.KieContainerBean;
import com.technorage.demo.drools.spring.KieServicesBean;
import com.technorage.demo.drools.spring.KieSessionBean;

@Service
@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode=ScopedProxyMode.INTERFACES)
public class DemoRuleServiceImpl<T> implements DemoRuleService<T>, Serializable {

	private static final long serialVersionUID = -8700062519048895244L;
	public KieSessionBean kieSession;
	private TrackingAgendaEventListener agendaEventListener;
	private TrackingWorkingMemoryEventListener workingMemoryEventListener;
	private RuleMap ruleMap;
	private OrderMap orderMap;

	@Autowired
	DemoRuleDao demoRuleDao;

	@Autowired
	public DemoRuleServiceImpl(
			@Qualifier("demoKieContainer") KieContainerBean kieContainer,@Qualifier("demoKieServices") KieServicesBean kieServices) {

		kieSession = new DefaultKieSessionBean(kieServices, kieContainer);

		agendaEventListener = new TrackingAgendaEventListener();
		workingMemoryEventListener = new TrackingWorkingMemoryEventListener();

		kieSession.addEventListener(agendaEventListener);
		kieSession.addEventListener(workingMemoryEventListener);
		kieSession.addEventListener( new CustomAgendaEventListener() ); //audit

		ruleMap = new RuleMap();
		orderMap = new OrderMap();

	}

	@Override
	public void addRule(DemoForm demoForm) {
		RuleSetup ruleSetup = null;
		Account account = null;
		List<Account> accountList = null;

		for (Entry<Integer, Map<String, String>> entry : addRuleSetupValues().entrySet()) { 
			ruleSetup = new RuleSetup();
			accountList = new ArrayList<Account>();
			ruleSetup.setRuleNumber(entry.getKey());

			for (Map.Entry<String,String> pair : entry.getValue().entrySet()) { 
				account = new Account();
				account.setColumnName(pair.getKey());
				account.setColumnValue(pair.getValue());

				accountList.add(account);

			}

			ruleSetup.setAccount(accountList);
			demoRuleDao.addRuleSetUp(ruleSetup);
		}

	}

	private Map<Integer, Map<String, String>> addRuleSetupValues() {
		Map<Integer, Map<String, String>> tempMap = new HashMap<Integer, Map<String, String>>();
		Map<String, String> columnValuePair = new HashMap<String, String>();

		columnValuePair.put("accountType", "51");
		columnValuePair.put("ISBN", "111");
		columnValuePair.put("familyCode", "HB");
		tempMap.put(1, columnValuePair);

		columnValuePair = new HashMap<String, String>();
		columnValuePair.put("accountType", "52");
		columnValuePair.put("ISBN", "222");
		tempMap.put(2, columnValuePair);

		columnValuePair = new HashMap<String, String>();
		columnValuePair.put("accountNumber", "123");
		columnValuePair.put("familyCode", "HB");
		tempMap.put(3, columnValuePair);

		ruleMap.setRuleMapObj(tempMap);
		return tempMap;

	}

	@Override
	public void addOrder(DemoForm demoForm) {
		OrderLine orderLine = null;
		Account account = null;
		List<Account> accountList = null;

		for (Entry<Integer, Map<String, String>> entry : addOrderLineValues().entrySet()) { 
			orderLine = new OrderLine();
			accountList = new ArrayList<Account>();
			orderLine.setOrderLineNumber(entry.getKey());

			for (Map.Entry<String,String> pair : entry.getValue().entrySet()) { 
				account = new Account();
				account.setColumnName(pair.getKey());
				account.setColumnValue(pair.getValue());

				accountList.add(account);

			}

			orderLine.setAccount(accountList);
			demoRuleDao.addOrderLineSetUp(orderLine);
		}

	}

	private Map<Integer, Map<String, String>> addOrderLineValues() {
		Map<Integer, Map<String, String>> tempMap = new HashMap<Integer, Map<String, String>>();
		Map<String, String> columnValuePair = new HashMap<String, String>();

		columnValuePair.put("accountNumber", "123");
		columnValuePair.put("accountType", "51");
		columnValuePair.put("ISBN", "111");

		tempMap.put(1, columnValuePair);

		orderMap.setOrderMapObj(tempMap);
		return tempMap;
	}

	@Override
	public String generateOffer(DemoForm demoForm, Model model) {
		loadKieSession();

		return ("index");
	}

	@Override
	public List<RuleSetup> getRuleSetupList() {
		// TODO Auto-generated method stub
		return demoRuleDao.getAllRuleSetup();
	}

	@Override
	public List<OrderLine> getOrderSetupList() {
		return demoRuleDao.getAllOrderLineSetup();
	}

	private void loadKieSession() {
		List<OrderLine> orderLineList = getOrderSetupList();
		
		//load kieSession again for case when server is restarted
		addRuleSetupValues();
		addOrderLineValues();
		
		kieSession.insert(ruleMap);
		kieSession.insert(orderMap);

		for(RuleSetup ruleSetup : getRuleSetupList()) {
			kieSession.insert(ruleSetup);
		}

		for(OrderLine orderLine : orderLineList) {
			kieSession.insert(orderLine);
		}

		kieSession.fireAllRules();
		
		for(OrderLine orderLine : orderLineList) {
			for(RuleSetup rules : orderLine.getRulesQualified()) {
				System.out.println("RuleQualified_" + rules.getRuleNumber() 
					+ " for OrderLine_" + orderLine.getOrderLineNumber());	  
			}
		}
		
	}

}


