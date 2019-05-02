package com.asiainfo.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MyCache<T> {
	private Map<Integer, T> cache = new HashMap<>();
	
	public T getValue(int key) {
		return cache.get(key);
	}
	
	public void addOrUpdateCache(int key, T value) {
		cache.put(key, value);
	}
	
	public void deleteByKey(int key) {
		if(cache.containsKey(key)) {
			cache.remove(key);
		}
	}
	
	public void cleanCache() {
		cache.clear();
	}
}
