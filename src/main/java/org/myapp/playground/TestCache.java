package org.myapp.playground;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class TestCache {

	public static void ttt() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("aaa", 123);
		map.put("bbb", "ccc");
		
		String ttt = mapper.writeValueAsString(map);
		System.out.println(ttt);
		
	}

	public static void main(String[] args) throws JsonProcessingException {
		Cache<String, String> cache = CacheBuilder.newBuilder().build(); 
		
		ttt();
	}

}
