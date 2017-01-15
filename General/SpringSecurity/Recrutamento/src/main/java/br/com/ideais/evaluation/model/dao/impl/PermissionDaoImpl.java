package br.com.ideais.evaluation.model.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ideais.evaluation.model.dao.AbstractDao;
import br.com.ideais.evaluation.model.dao.PermissionDao;
import br.com.ideais.evaluation.model.entity.Candidate;
import br.com.ideais.evaluation.model.entity.Permission;

@Transactional(propagation=Propagation.REQUIRED)
public class PermissionDaoImpl extends AbstractDao<Permission> implements PermissionDao {

	protected PermissionDaoImpl() {
		super(Permission.class);
	}

	@Override
	public List<Permission> listAll() {
		return super.listAll();
	}
	@Override
	public void save(Permission permission) {
		saveEntity(permission);
	}

	@Override
	public Permission findByCandidate(Candidate candidate) {
		Criteria criteria = session().createCriteria(Permission.class);
		criteria.add(Restrictions.eq("active", true));
		criteria.add(Restrictions.eq("candidate", candidate));
//		criteria.add(Restrictions.le("end", new Date()));
		return (Permission) criteria.uniqueResult();
	}

}
