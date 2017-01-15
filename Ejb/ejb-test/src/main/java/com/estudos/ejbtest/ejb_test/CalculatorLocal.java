package com.estudos.ejbtest.ejb_test;

import javax.ejb.Local;

@Local
public interface CalculatorLocal {
	
	public int sum(int add1, int add2);
	
	public int multiply(int mul1, int mul2);
}