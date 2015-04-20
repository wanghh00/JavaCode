package org.myapp.playground;

import java.util.Date;

import com.jayway.jsonpath.JsonPath;

public class TestJsonPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String json = "{\"date_as_long\" : 1411455611975}";

		Date date = JsonPath.parse(json).read("$['date_as_long']", Date.class);
		System.out.println(date);

	}

}
