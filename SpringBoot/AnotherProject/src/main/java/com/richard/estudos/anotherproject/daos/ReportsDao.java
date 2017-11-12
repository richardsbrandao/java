package com.richard.estudos.anotherproject.daos;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.richard.estudos.anotherproject.models.Report;

public interface ReportsDao extends CrudRepository<Report, Long> {

	Report findByExternalIdAndRequester(UUID externalId, String requester);

}
