package com.sagar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sagar.entity.Product;
import com.sagar.model.ProductSearchResponseWrapper;
import com.sagar.service.ProductService;

@RestController
@RequestMapping({ "/products" })

public class ProductsController {
	@Autowired
	private ProductService productService;

	@GetMapping
	public ProductSearchResponseWrapper getAllProducts() {
		return productService.getAllproducts();
	}

	@PostMapping
	public ProductSearchResponseWrapper saveProduct(@RequestBody Product product) {
		productService.saveproduct(product);
		return getAllProducts();
	}

	@PostMapping({ "/saveMulti" })
	public ProductSearchResponseWrapper saveMultipleProducts(@RequestBody List<Product> products) {
		productService.saveMultiproducts(products);
		return getAllProducts();
	}

	@PutMapping
	public ProductSearchResponseWrapper update(@RequestBody Product product) {
		productService.updateproduct(product);
		return getAllProducts();
	}

	@DeleteMapping
	public boolean delete(@RequestParam("id") int id) {
		return productService.deleteproduct(id);

	}

//    @GetMapping({ "/list" })
//	public List<Product> getAllProducts1(@RequestParam(required = false) Integer pageIndex,@RequestParam(required = false) Integer sizeOfPage,@RequestParam(required = false) String sortingOrder, @RequestParam(required = false) String sortBy) {
//		if (sortingOrder == null) {
//			return getAllProducts();
//		}
//		return productService.ProductsWithPageAndSort(pageIndex,sizeOfPage, sortingOrder, new String[] { sortBy });
//	}

//    @GetMapping({ "/find/{searchKeyword}" })
//	public List<Product> findByAny(@PathVariable String searchKeyword) {
//		return productService.findByAnytg(searchKeyword);
//	}
}