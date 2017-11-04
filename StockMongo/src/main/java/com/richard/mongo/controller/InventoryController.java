package com.richard.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.richard.mongo.domain.Inventory;
import com.richard.mongo.service.InventoryService;

@Controller
@RequestMapping(value="/inventory")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<Inventory> create() {
		List<Inventory> subinventories = inventoryService.findAll();
		return subinventories;
	}
	
}
