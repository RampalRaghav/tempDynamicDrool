package org.arpit.java2blog.dao;

import java.util.List;

import org.arpit.java2blog.model.OrderLine;
import org.arpit.java2blog.model.RuleSetup;

public interface DemoRuleDao {
	
	public void addRuleSetUp(RuleSetup ruleSetUp);
	public List<RuleSetup> getAllRuleSetup();
	public List<OrderLine> getAllOrderLineSetup();
	public void addOrderLineSetUp(OrderLine orderLine);

}
