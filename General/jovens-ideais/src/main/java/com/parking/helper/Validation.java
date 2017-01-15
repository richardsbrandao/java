package com.parking.helper;

import java.util.regex.Pattern;

public class Validation {
	
	public static boolean cpf(String cpf) {
		String cpfNumbers = cpf.replaceAll("-|\\.", "");
		if(cpfNumbers.length() != 11) {
			return false;
		}
		if( !cpfNumbers.matches("[0-9]+") ) {
			return false;
		}
		if( cpfNumbers.equals("00000000000") || cpfNumbers.equals("11111111111") || cpfNumbers.equals("22222222222") ||
			cpfNumbers.equals("33333333333") || cpfNumbers.equals("44444444444") || cpfNumbers.equals("55555555555") ||
			cpfNumbers.equals("66666666666") || cpfNumbers.equals("77777777777") || cpfNumbers.equals("88888888888") ||
			cpfNumbers.equals("99999999999")) {
			return false;
		}

		Integer firstAux = 0;
		for(int i = 10, index = 0; i >= 2; i--, index++) {
			firstAux += Integer.parseInt(cpfNumbers.substring(index, index+1)) * i;
		}
		Integer firstDigit = firstAux%11 >= 2 ? 11-Math.round(firstAux%11) : 0;

		Integer secondAux = 0;
		for(int i = 11, index = 0; i >= 2; i--, index++) {
			secondAux += Integer.parseInt(cpfNumbers.substring(index, index+1)) * i;
		}
		Integer secondDigit = secondAux%11 >= 2 ? 11-Math.round(secondAux%11) : 0;
		
		return firstDigit == Integer.parseInt(cpfNumbers.substring(9, 10)) &&
				secondDigit == Integer.parseInt(cpfNumbers.substring(10, 11));
	}

	public static boolean plate(String plate) {
		Pattern pattern = Pattern.compile("^[a-zA-Z]{3,3}-\\d{4,4}$");
		return pattern.matcher(plate).find();
	}
	
	public static boolean telephone(String telephone) {
		Pattern pattern = Pattern.compile("^\\d{4,4}-?\\d{4,4}$");
		return pattern.matcher(telephone).find();
	}
	
	public static boolean isNumber(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
