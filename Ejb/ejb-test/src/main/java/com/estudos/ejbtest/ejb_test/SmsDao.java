package com.estudos.ejbtest.ejb_test;

import javax.ejb.Local;

@Local
public interface SmsDao {

	public void save(Sms sms);
	
}
