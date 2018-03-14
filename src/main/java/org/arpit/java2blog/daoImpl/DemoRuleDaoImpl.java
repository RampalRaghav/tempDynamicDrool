package org.arpit.java2blog.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.arpit.java2blog.dao.DemoRuleDao;
import org.arpit.java2blog.model.AuditTrail;
import org.arpit.java2blog.model.MyInterceptor;
import org.arpit.java2blog.model.OrderLine;
import org.arpit.java2blog.model.RuleSetup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;;

@Repository
public class DemoRuleDaoImpl implements DemoRuleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	@Transactional
	public void addRuleSetUp(RuleSetup ruleSetUp) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(ruleSetUp);
	}
	
	@Override
	@Transactional
	public List<RuleSetup> getAllRuleSetup() {
		Session session = this.sessionFactory.getCurrentSession();
		List<RuleSetup>  ruleSetupList = session.createQuery("from RuleSetup").list();
		
		return ruleSetupList;
	}

	@Override
	@Transactional
	public List<OrderLine> getAllOrderLineSetup() {
		Session session = this.sessionFactory.getCurrentSession();
		List<OrderLine>  orderLineSetUpList = session.createQuery("from OrderLine").list();
		
		return orderLineSetUpList;
	}
	
	@Override
	@Transactional
	public void addOrderLineSetUp(OrderLine orderLine) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(orderLine);
	}

	@Override
	@Transactional
	public void addAuditTrail() {
		Session session = this.sessionFactory.getCurrentSession();
		MyInterceptor myInterceptor = (MyInterceptor) this.sessionFactory.getSessionFactoryOptions().getInterceptor();
		
		if(myInterceptor!=null && myInterceptor.getAuditTrailList()!=null) {
			for(AuditTrail audit : myInterceptor.getAuditTrailList()) {
				session.saveOrUpdate(audit);
			}
		}
	}
	
}
