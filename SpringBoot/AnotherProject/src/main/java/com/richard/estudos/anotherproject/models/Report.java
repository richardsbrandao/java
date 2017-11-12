package com.richard.estudos.anotherproject.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Reports")
@Getter @Setter @NoArgsConstructor
public class Report {

	@Id 
	@Column(columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String requester;
	private UUID externalId;
	private String content;

	public Report(UUID externalId, String requester) {
		this.setRequester(requester);
		this.setExternalId(externalId);
	}
}
