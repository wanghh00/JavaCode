package org.myapp.playground.remotecache;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public interface RemoteCache <K, V> {
	
	V getIfPresent(Object Key);	
	V get(K key);	
	Map<K, V> getAllPresent(Iterable<?> keys);
	
	void put(K key, V value);
	void putAll(Map<? extends K,? extends V> m);
	
	void invalidate(Object key);
	void invalidateAll(Iterable<?> keys);
		
	ConcurrentMap<K, V> asMap();
}
