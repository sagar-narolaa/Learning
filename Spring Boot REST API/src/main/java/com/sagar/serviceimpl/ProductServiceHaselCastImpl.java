/*
 * package com.sagar.serviceimpl;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.cache.CacheManager; import
 * org.springframework.cache.annotation.CacheConfig; import
 * org.springframework.cache.annotation.CacheEvict; import
 * org.springframework.cache.annotation.Cacheable; import
 * org.springframework.stereotype.Service;
 * 
 * import com.sagar.entity.Product; import
 * com.sagar.model.ProductSearchResponse;
 * 
 * @Service("hazelCast") public class ProductServiceHaselCastImpl extends
 * AbstractProductService {
 * 
 * public ProductServiceHaselCastImpl() { }
 * 
 * @Override
 * 
 * @Cacheable public Product getProduct(int id) { return super.getProduct(id); }
 * 
 * @Override public void saveMultiproducts(List<Product> products) {
 * super.saveMultiproducts(products); }
 * 
 * @Cacheable(value = "hazelcast") public List<ProductSearchResponse>
 * getAllproducts() { return super.getAllproducts(); }
 * 
 * // @CachePut(value = "productsCache", key = "#product.id")
 * 
 * @CacheEvict public Product saveproduct(Product product) { return
 * super.saveproduct(product); }
 * 
 * @CacheEvict // @CachePut(value = "productsCache", key = "#product.id") public
 * Product updateproduct(Product product) {
 * 
 * Cache cache = cm.getCache("productsCache"); cache.evict(product.getId());
 * cache.put(product.getId(), product);
 * 
 * 
 * return super.updateproduct(product); }
 * 
 * @CacheEvict public boolean deleteproduct(int id) { try {
 * super.deleteproduct(id); return true; } catch (Exception e) { return false; }
 * }
 * 
 * }
 */