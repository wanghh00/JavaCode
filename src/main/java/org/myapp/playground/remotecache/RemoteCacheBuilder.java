package org.myapp.playground.remotecache;

import com.google.common.cache.CacheBuilder;

public class RemoteCacheBuilder<K, V> {
	String host;
	int port;
	DumpObj<V> dumpValue;
	LoadObj<V> loadValue;
	DumpObj<K> dumpKey;

	// CacheBuilder builder;
	RemoteCacheBuilder() {
	}

	public static RemoteCacheBuilder<Object, Object> newBuilder() {
		return new RemoteCacheBuilder<Object, Object>();
	}
	
	public RemoteCacheBuilder<K, V> host(String host) {
		this.host = host;
		return this;
	}
	
	public RemoteCacheBuilder<K, V> port(int port) {
		this.port = port;
		return this;
	}
	
	public String getHost() {
		return host;		
	}
	
	public int getPort() {
		return port;
	}
	
	public RemoteCacheBuilder<K, V> dumpKey(DumpObj<K> dumpKey) {
		this.dumpKey = dumpKey;
		return this;
	}
	
	public DumpObj<K> getDumpKey() {
		return dumpKey;
	}
	
	public RemoteCacheBuilder<K, V> dumpValue(DumpObj<V> dumpValue) {
		this.dumpValue = dumpValue;
		return this;
	}
	
	public DumpObj<V> getDumpValue() {
		return dumpValue;
	}
	
	public RemoteCacheBuilder<K, V> loadValue(LoadObj<V> loadValue) {
		this.loadValue = loadValue;
		return this;
	}
	
	public LoadObj<V> getLoadValue() {
		return loadValue;
	}

}
