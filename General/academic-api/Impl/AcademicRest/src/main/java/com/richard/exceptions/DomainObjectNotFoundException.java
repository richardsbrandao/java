package com.richard.exceptions;

import java.io.Serializable;

import org.hibernate.ObjectNotFoundException;

@SuppressWarnings("serial")
public class DomainObjectNotFoundException extends ObjectNotFoundException {

	public DomainObjectNotFoundException(Serializable identifier, String clazz) {
		super(identifier, clazz);
	}

}
