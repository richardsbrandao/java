package com.richard.estudos.anotherproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.richard.estudos.anotherproject.controllers.requests.CategoryRequest;
import com.richard.estudos.anotherproject.controllers.requests.ReportRequest;
import com.richard.estudos.anotherproject.controllers.responses.CategoriesResponse;
import com.richard.estudos.anotherproject.controllers.responses.CategoryResponse;
import com.richard.estudos.anotherproject.models.Category;
import com.richard.estudos.anotherproject.services.CategoriesService;

@RestController
@RequestMapping(value="/categories")
public class CategoriesController {

	@Autowired
	private CategoriesService categoriesService;

	@GetMapping(value="/")
	public ResponseEntity<CategoriesResponse> list() {
		Iterable<Category> categories = categoriesService.findAll();
		return new ResponseEntity<CategoriesResponse>(new CategoriesResponse(categories), HttpStatus.OK);
	}
	
	@PostMapping(value="/")
	public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
		Category requestModel = request.toModel();
		Category category = categoriesService.save(requestModel);
		return new ResponseEntity<CategoryResponse>(new CategoryResponse(category), HttpStatus.CREATED);
	}
	
	@PutMapping(value="/report")
	public ResponseEntity<Object> report(@RequestBody ReportRequest request) {
		categoriesService.generateReport(request.getExternalId(), request.getRequester());
		return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
	}
}
