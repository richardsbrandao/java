package br.com.ideais.evaluation.model.dao;

import java.util.List;

import br.com.ideais.evaluation.model.entity.Candidate;
import br.com.ideais.evaluation.model.entity.Permission;

public interface PermissionDao {

	public void save(Permission permission);
	
	public List<Permission> listAll();
	
	public Permission findByCandidate(Candidate candidate);

}
