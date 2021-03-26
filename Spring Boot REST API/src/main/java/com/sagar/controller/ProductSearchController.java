/*
 * package com.sagar.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Qualifier; import
 * org.springframework.web.bind.annotation.CrossOrigin; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.sagar.model.ProductSearchRequest; import
 * com.sagar.model.ProductSearchResponseWrapper; import
 * com.sagar.serviceimpl.ProductSearchService;
 * 
 * @RestController
 * 
 * @RequestMapping("/products") public class ProductSearchController {
 * 
 * @Autowired
 * 
 * @Qualifier("serviceWithMapCache") private ProductSearchService service;
 * 
 * @PostMapping("/_searchProduct") public ProductSearchResponseWrapper
 * search(@RequestBody(required = false) ProductSearchRequest requestJSON) {
 * 
 * if (requestJSON == null) { return service.getResponse(new
 * ProductSearchRequest()); } else { return service.getResponse(requestJSON); }
 * 
 * 
 * }
 * 
 * }
 */