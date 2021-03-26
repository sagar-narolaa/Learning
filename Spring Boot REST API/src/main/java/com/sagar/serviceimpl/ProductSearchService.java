/*
 * package com.sagar.serviceimpl;
 * 
 * import java.util.List; import java.util.stream.Collectors;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.cache.annotation.Cacheable; import
 * org.springframework.stereotype.Service;
 * 
 * import com.sagar.Utils.ObjectConverter; import
 * com.sagar.dao.ProductDaoCriteria; import com.sagar.entity.Product; import
 * com.sagar.model.ProductSearchRequest; import
 * com.sagar.model.ProductSearchResponse; import
 * com.sagar.model.ProductSearchResponseWrapper;
 * 
 * @Service public class ProductSearchService {
 * 
 * @Autowired ProductDaoCriteria productDao;
 * 
 * @Cacheable(value = "productsCache") public ProductSearchResponseWrapper
 * getResponse(ProductSearchRequest request) {
 * 
 * List<Product> products =
 * productDao.getproductsWithSearch(request.getSortBy(), request.getSortOrder(),
 * request.getRecordsPerPage(), request.getSearch(), request.getCatagoryId(),
 * request.getPageIndex());
 * 
 * return new ProductSearchResponseWrapper(ObjectConverter.
 * entitiesToModel(products));
 * 
 * }
 * 
 * 
 * protected List<ProductSearchResponse> entitiesToModel(List<Product> products)
 * { return products.stream().map(product -> { ProductSearchResponse response =
 * new ProductSearchResponse(); response.setProductId(product.getId());
 * response.setProductName(product.getName());
 * response.setProductDescription(product.getDescription());
 * response.setCategoryId(product.getCatagory().getId());
 * response.setCategoryName(product.getCatagory().getName()); return response;
 * }).collect(Collectors.toList()); }
 * 
 * 
 * 
 * private void performValidations(ProductSearchRequest request) throws
 * ValidationException { if (request == null) { throw new
 * ValidationException("Hey..Fill the data"); } if (request.getCatagoryId() < 0)
 * { throw new ValidationException("id cannot be less than 0"); } }
 * 
 * 
 * }
 */