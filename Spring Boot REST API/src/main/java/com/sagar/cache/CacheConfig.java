/*
 * package com.sagar.cache;
 * 
 * import net.sf.ehcache.config.CacheConfiguration; import
 * org.springframework.cache.CacheManager; import
 * org.springframework.cache.annotation.CachingConfigurer; import
 * org.springframework.cache.annotation.EnableCaching; import
 * org.springframework.cache.ehcache.EhCacheCacheManager; import
 * org.springframework.cache.interceptor.*; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration;
 * 
 * import java.util.Objects;
 * 
 * @EnableCaching
 * 
 * @Configuration class EHCacheConfiguration implements CachingConfigurer {
 * 
 * @Bean(destroyMethod = "shutdown") public net.sf.ehcache.CacheManager
 * ehCacheManager() { CacheConfiguration cacheConfiguration = new
 * CacheConfiguration(); cacheConfiguration.setName("productsCache");
 * cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
 * cacheConfiguration.setMaxEntriesLocalHeap(1000);
 * 
 * net.sf.ehcache.config.Configuration config = new
 * net.sf.ehcache.config.Configuration(); config.addCache(cacheConfiguration);
 * 
 * return net.sf.ehcache.CacheManager.newInstance(config); }
 * 
 * @Bean
 * 
 * @Override public CacheManager cacheManager() { return new
 * EhCacheCacheManager(ehCacheManager()); }
 * 
 * @Bean
 * 
 * @Override public KeyGenerator keyGenerator() { return new
 * SimpleKeyGenerator(); }
 * 
 * @Bean
 * 
 * @Override public CacheResolver cacheResolver() { return new
 * SimpleCacheResolver(Objects.requireNonNull(cacheManager())); }
 * 
 * @Bean
 * 
 * @Override public CacheErrorHandler errorHandler() { return new
 * SimpleCacheErrorHandler(); } }
 */