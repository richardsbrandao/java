package com.estudos.ejbtest.ejb_test;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
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
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
