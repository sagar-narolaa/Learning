/*
 * package com.sagar.cache;
 * 
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.data.redis.connection.RedisStandaloneConfiguration;
 * import
 * org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
 * import org.springframework.data.redis.core.RedisTemplate;
 * 
 * import com.sagar.entity.Product;
 * 
 * @Configuration public class RedisConfiguration {
 * 
 * @Bean JedisConnectionFactory jedisConnectionFactory() {
 * RedisStandaloneConfiguration redisStandaloneConfiguration = new
 * RedisStandaloneConfiguration("localhost", 6379); return new
 * JedisConnectionFactory(redisStandaloneConfiguration); }
 * 
 * @Bean RedisTemplate<Integer, Product> redisTemplate() {
 * RedisTemplate<Integer, Product> redisTemplate = new RedisTemplate<>();
 * redisTemplate.setConnectionFactory(jedisConnectionFactory()); return
 * redisTemplate; } }
 */