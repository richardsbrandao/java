package br.com.ideais.evaluation.model.service;

import java.util.List;

import br.com.ideais.evaluation.model.dao.PermissionDao;
import br.com.ideais.evaluation.model.entity.Candidate;
import br.com.ideais.evaluation.model.entity.Permission;

public class PermissionService {
	
	private PermissionDao permissionDao;
	
	public void save(Permission permission){
		permissionDao.save(permission);
	}

	public void update(Permission permission) {
		permissionDao.save(permission);
	}

	public void delete(Permission permission) {
		permissionDao.save(permission);
	}
	
	public Permission findByCandidate(Candidate candidate) {
		return permissionDao.findByCandidate(candidate);
	}
	
	public List<Permission> listAll() {
		return permissionDao.listAll();
	}
	
	public PermissionDao getPermissionDao() {
		return permissionDao;
	}
	
	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}
}
