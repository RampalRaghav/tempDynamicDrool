package org.arpit.java2blog.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.arpit.java2blog.dao.DemoRuleDao;
import org.arpit.java2blog.model.OrderLine;
import org.arpit.java2blog.model.RuleSetup;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;;

@Repository
public class DemoRuleDaoImpl implements DemoRuleDao {

	@Autowired
	private SessionFactory sessionFactory;
	@PersistenceContext
	private EntityManager entityManager;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Override
	@Transactional
	public void addRuleSetUp(RuleSetup ruleSetUp) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(ruleSetUp);
	}
	@Override
	@Transactional
	public List<RuleSetup> getAllRuleSetup() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from RuleSetup");
		return query.list();
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
		
		/*Session session = this.sessionFactory
				.withOptions()
				.interceptor(new MyInterceptor())
				.openSession();*/
		
		session.save(orderLine);

	}

}
