package br.com.ideais.evaluation.model.dao;

import java.util.Date;
import java.util.List;

import br.com.ideais.evaluation.helpers.Level;
import br.com.ideais.evaluation.model.entity.Evaluation;

public interface EvaluationDao {

	public void save(Evaluation evaluation);

	public List<Evaluation> findByDatesAndLevel(Date beginning, Date end, Level level);

	public void update(Evaluation evaluation);

}
