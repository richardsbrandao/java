package com.estudos.ejbtest.ejb_test;

import javax.ejb.Stateless;

@Stateless
public class CalculatorImpl implements CalculatorLocal {

	public int sum(int add1, int add2) {
		return add1+add2;
	}

	public int multiply(int mul1, int mul2) {
		return mul1*mul2;
	}

}
