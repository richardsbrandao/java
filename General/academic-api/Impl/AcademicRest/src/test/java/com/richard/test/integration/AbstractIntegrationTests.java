package com.richard.test.integration;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.richard.domain.Institution;
import com.richard.test.AbstractTests;

@ContextConfiguration({
	"classpath:spring/applicationContext.xml",
	"classpath:spring/dataTestContext.xml"
})
@Ignore
public class AbstractIntegrationTests extends AbstractTests {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Institution findInstitutionByName(String name) {
		Criteria criteria = session().createCriteria(Institution.class);
		criteria.add(Restrictions.eq("name", name));
		return (Institution) criteria.uniqueResult();
	}

	private Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public String getPrepareScriptSqlFileName() {
		return "database/prepareIntegrationTestsDatabase.sql";
	}
	
}
