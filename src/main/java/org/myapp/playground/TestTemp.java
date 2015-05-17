package org.myapp.playground;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestTemp {
	
	public static void testMapTravel() {
		Set<String> stMain = new HashSet<String>();
		Map<String, Set<String>> mpMain = new HashMap<String, Set<String>>();
		
		String[] lstString = {"A", "B", "C", "D", "E", "F", "X", "Y", "1", "2"};
		for (String one : lstString) stMain.add(one);
		
		String [] lstA = {"B", "C"};
		String [] lstB = {"X", "Y"};
		String [] lstX = {"1", "2"};
				
		mpMain.put("A", new HashSet<String>(Arrays.asList(lstA)));
		mpMain.put("B", new HashSet<String>(Arrays.asList(lstB)));
		mpMain.put("X", new HashSet<String>(Arrays.asList(lstX)));
		
		//Set<String> ss = new HashSet<String>(Arrays.asList(sa1));
		
		//map.add("A", new HashSet<String>(Arrays.asList({"B", "C"})));
		Set<String> stStore = new HashSet<String>();
		trav(mpMain, "A", stStore);
		
		//Thread.currentThread().getStackTrace();
		new RuntimeException("haha...").printStackTrace();
		
		System.out.println(mpMain);
		System.out.println(stStore);
	}
	
	public static void trav(Map<String, Set<String>> map, String key, Set<String> stStore) {
		stStore.add(key);
		
		if (map.containsKey(key) == false) return;
				
		for (String one : map.get(key)) {
			if (stStore.contains(one)) continue;
			stStore.add(one);
			trav(map, one, stStore);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testMapTravel();
		System.out.println("Hello!");

	}

}
