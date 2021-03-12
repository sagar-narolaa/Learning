package com.sagar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagar.model.ProductSearchRequest;
import com.sagar.model.ProductSearchResponseWrapper;
import com.sagar.serviceimpl.ProductSearchService;

@RestController
@RequestMapping("/catagories")
public class ProductSearchController {
	
	@Autowired
	private ProductSearchService service;
	
	@PostMapping("/_searchBook")
	public ProductSearchResponseWrapper search(@RequestBody(required = false) ProductSearchRequest requestJSON) {
		if(requestJSON!=null) {			
			return service.getResponse(requestJSON);
		}
		
		return service.getResponseIfJSONBlank();
		
	}

}
