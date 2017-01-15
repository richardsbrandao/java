package br.com.ideais.evaluation.model.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import br.com.ideais.evaluation.helpers.Level;
import br.com.ideais.evaluation.model.dao.AbstractDao;
import br.com.ideais.evaluation.model.dao.EvaluationDao;
import br.com.ideais.evaluation.model.entity.Evaluation;

public class EvaluationDaoImpl extends AbstractDao<Evaluation> implements EvaluationDao {

	public EvaluationDaoImpl() {
		super(Evaluation.class);
	}

	@Override
	public void save(Evaluation evaluation) {
		saveEntity(evaluation);
	}

	@Override
	public List<Evaluation> findByDatesAndLevel(Date beginning, Date end, Level level) {
		
		String sql = "from Evaluation where endDate between :beginning and :end";
		
		if(level != null) {
			sql += " and candidate in (select id from Candidate where level = :level)";
		}
		
		Query query = session().createQuery(sql);
		query.setParameter("beginning", beginning);
		query.setParameter("end", end);
		
		if(level != null) {
			query.setParameter("level", level).list();
		}
		
		return query.list();
		
	}

	@Override
	public void update(Evaluation evaluation) {
		saveEntity(evaluation);
	}

}
