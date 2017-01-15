package com.estudos.ejb.core.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Sms {

	@Id
	private String id;
	@Column(name="numberFrom")
	private String from;
	@Column(name="numberTo")
	private String to;
	@Column(name="textMessage")
	private String text;
	private String date;
	
	public Sms() {
		this.id = UUID.randomUUID().toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{ ")
			.append("\"from\": \""+ from +"\", ")
			.append("\"to\": \""+ to +"\", ")
			.append("\"text\": \""+ text +"\", ")
			.append("\"date\": \""+ date +"\"");
		return sb.toString();
	}
	
}
