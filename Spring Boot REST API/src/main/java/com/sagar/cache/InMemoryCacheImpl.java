package com.sagar.cache;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class InMemoryCacheImpl<K, V> implements SimpleCache<K, V> {


	Map<K, V> map = new ConcurrentHashMap<>();

	@Override
	public void put(K key, V value) {
		map.put(key, value);
	}

	@Override
	public Optional<V> get(K key) {
		return Optional.ofNullable(map.get(key));
	}

	@Override
	public void remove(K key) {
		map.remove(key);
	}

	@Override
	public List<V> getAll() {
		return map.values().stream().collect(Collectors.toList()); 
	}

	@Override
	public void saveAll(Map<K,V> mapp) {
		map.putAll(mapp);		
	}
	

	@Override
	public void removeAll() {
		map.clear();		
	}



}
