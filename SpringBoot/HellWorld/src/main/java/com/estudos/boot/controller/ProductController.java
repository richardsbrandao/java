package com.estudos.boot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.richard.errors.NotFoundException;


@RestController
@RequestMapping(value="/product")
public class ProductController {

	@RequestMapping(value="/{productId}", method=RequestMethod.GET)
    public String getUser(@PathVariable String productId) throws NotFoundException  {
		if ( productId.equals("0") ) {
			throw new NotFoundException();
		}
		
        return "{'id': "+productId+"}";
    }
	
}
