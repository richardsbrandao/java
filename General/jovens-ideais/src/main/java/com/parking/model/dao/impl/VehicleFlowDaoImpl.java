package com.parking.model.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.parking.model.dao.AbstractDao;
import com.parking.model.dao.VehicleFlowDao;
import com.parking.model.entity.Customer;
import com.parking.model.entity.Flow;

public class VehicleFlowDaoImpl extends AbstractDao<Flow> implements VehicleFlowDao {

	protected VehicleFlowDaoImpl() {
		super(Flow.class);
	}

	@Override
	public Integer getTotalVehicleFlow() {
		Criteria criteria = session().createCriteria(Flow.class);
		criteria.add(Restrictions.isNull("departure"));
		return criteria.list().size();
	}
	
	public Flow findActiveFlow(Customer customer){
		return (Flow) session().createQuery(
				"from Flow flow where flow.vehicle.customer = :customer and flow.departure is null").
				setParameter("customer", customer).uniqueResult();
	}

	@Override
	public void allocate(Flow flow) {
		saveEntity(flow);
	}

	@Override
	public Flow findById(Flow flow) {
		return super.findById(flow.getId());
	}

}
