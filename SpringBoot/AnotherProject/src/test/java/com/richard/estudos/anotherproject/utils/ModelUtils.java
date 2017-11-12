package com.richard.estudos.anotherproject.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.richard.estudos.anotherproject.models.Category;
import com.richard.estudos.anotherproject.models.Report;
import com.richard.estudos.anotherproject.models.User;

public class ModelUtils {

	public static List<Category> categories(Long number) {
		List<Category> categories = new ArrayList<Category>();
		for(Long i = 0L; i < number; i++) {
			categories.add(category(i, "Name"));
		}
		return categories;
	}

	public static Category category(Long id, String name) {
		Category category = new Category(id);
		category.setName(name);
		return category ;
	}

	public static User user(Long id, String name, String bornDate) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setBornDate(LocalDate.parse(bornDate));
		user.setCategory(category(1L, "Name"));
		return user;
	}

	public static List<User> users(Long number) {
		List<User> users = new ArrayList<User>();
		for(Long i = 0L; i < number; i++) {
			users.add(user(i, "Name", "2010-11-12"));
		}
		return users;
	}

	public static Report report() {
		Report report = new Report();
		report.setId(1L);
		report.setExternalId(UUID.randomUUID());
		report.setRequester("Richard");
		report.setContent("[]");
		return report;
	}
}
