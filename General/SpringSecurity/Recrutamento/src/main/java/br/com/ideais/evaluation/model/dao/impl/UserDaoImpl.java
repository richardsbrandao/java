package br.com.ideais.evaluation.model.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ideais.evaluation.model.dao.AbstractDao;
import br.com.ideais.evaluation.model.dao.UserDao;
import br.com.ideais.evaluation.model.entity.User;

@Transactional(propagation=Propagation.REQUIRED)
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public User findByName(String name) {
		Criteria criteria = session().createCriteria(User.class);
		criteria.add(Restrictions.eq("name", name));
		return criteriaGetSingleResult(criteria) != null ? (User) criteriaGetSingleResult(criteria) : null;
	}


}
