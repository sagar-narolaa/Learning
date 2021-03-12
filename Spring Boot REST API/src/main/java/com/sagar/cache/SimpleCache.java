package com.sagar.cache;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SimpleCache<K, V> {

	void put(K key, V value);// create update

	Optional<V> get(K key);// read

	List<V> getAll(); // read all

	void remove(K key);// delete
	
	void saveAll(Map<K,V> map);	
	
	void removeAll();

}
