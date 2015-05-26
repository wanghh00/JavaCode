package org.myapp.playground.remotecache;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import redis.clients.jedis.Jedis;

//public class RedisCache<K, V> implements RemoteCache<K, V>, ConcurrentMap<K, V> {
public class RedisCache<K, V> implements ConcurrentMap<K, V> {
	DumpObj<? super V> dumpValue;
	LoadObj<? super V> loadValue;
	DumpObj<? super K> dumpKey;
	Jedis jedis;
	
	RedisCache(RemoteCacheBuilder<? super K, ? super V> builder) {
		jedis = new Jedis(builder.getHost(), builder.getPort());
		dumpValue = builder.getDumpValue();
		loadValue = builder.getLoadValue();
		dumpKey = builder.getDumpKey();		
	}
	
	@SuppressWarnings("unchecked")
	private String dumpkey(Object key) {
		return dumpKey.dumpObj((K)key);
	}
	
	private String dumpval(V val) {
		return dumpValue.dumpObj(val);
	}
	
	@SuppressWarnings("unchecked")
	private V loadval(String data) {
		return (V) loadValue.loadObj(data);
	}
		
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Object obj) {
		return loadval(jedis.get(dumpkey(obj)));
	}

	@Override
	public V put(K key, V value) {
		jedis.set(dumpkey(key), dumpval(value));
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V remove(Object key) {
		jedis.del(dumpkey(key));
		return (V) key;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V putIfAbsent(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object key, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean replace(K key, V oldValue, V newValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V replace(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	static class RedisRemoteCache <K, V> implements RemoteCache<K, V> {
		final RedisCache<K, V> redis;
		
		RedisRemoteCache(RemoteCacheBuilder<? super K, ? super V> builder) {
			redis = new RedisCache<K, V>(builder);
		}

		@Override
		public V getIfPresent(Object Key) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public V get(K key) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<K, V> getAllPresent(Iterable<?> keys) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void put(K key, V value) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void putAll(Map<? extends K, ? extends V> m) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void invalidate(Object key) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void invalidateAll(Iterable<?> keys) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public ConcurrentMap<K, V> asMap() {
			return redis;
		}
		
	}
	
}
