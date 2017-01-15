package com.richard.test;

import com.richard.domain.Institution;

public class DomainUtils {

	public static Institution getValidInstitution() {
		Institution institution = new Institution();
		institution.setName("Cta");
		return institution;
	}

}
