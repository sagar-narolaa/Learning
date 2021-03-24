package com.sagar.service;

import java.util.List;

import com.sagar.entity.Product;
import com.sagar.model.ProductSearchResponse;
import com.sagar.model.ProductSearchResponseWrapper;
import com.sagar.entity.Catagory;

public interface ProductService {
    List<ProductSearchResponse> getAllproducts();

    Product saveproduct(Product product);

    Product updateproduct(Product product);

    boolean deleteproduct(int id);

	//List<Product> productsWithPageAndSort(Integer pageIndex, Integer sizeOfPage, String sortingOrder, String[] strings);

	//List<Product> findByAnytg(String searchKeyword);

	void saveMultiproducts(List<Product> products);

	Product getProduct(int id);
}
