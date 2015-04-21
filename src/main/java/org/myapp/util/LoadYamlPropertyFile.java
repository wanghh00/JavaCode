package org.myapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;

public class LoadYamlPropertyFile {
	public String retPropertyFileNio(String filename, String encodeName) {
		String path = getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
		Path file = Paths.get(path + filename);
		String data = null;
		try {
			if (encodeName == null) encodeName = "UTF8";
			data = new String(Files.readAllBytes(file), encodeName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static String getPropertyFileString(String filename, String encodeName) {
		InputStream in = LoadYamlPropertyFile.class.getClassLoader().getResourceAsStream(filename);
		String data = null;
		try {
			if (encodeName == null) encodeName = "UTF8";
			data = IOUtils.toString(in, encodeName);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);
		}
		return data;
	}

	public String retPropertyFileIOUtils(String filename, String encodeName) {
		InputStream in = getClass().getClassLoader().getResourceAsStream(filename);
		String data = null;
		try {
			if (encodeName == null) encodeName = "UTF8";
			data = IOUtils.toString(in, encodeName);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);
		}
		return data;
	}

	public String retPropertyFile(String filename, String encodeName) {
		InputStream in = getClass().getClassLoader().getResourceAsStream(filename);
		BufferedReader reader = null;
		String line = null;
		StringBuilder total = new StringBuilder();

		try {
			if (encodeName == null) encodeName = "UTF8";
			reader = new BufferedReader(new InputStreamReader(in, encodeName));
			while ((line = reader.readLine()) != null) {
				total.append(line);
				total.append(System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// IOUtils.closeQuietly(reader);
			try {
				if (reader != null) reader.close();
			} catch (IOException e) {}
		}
		return total.toString();
	}

	public static String joinStringList_old(List<String> list, String delimiter) {
		StringBuilder sb = new StringBuilder();
		for (String str : list) {
			if (delimiter == null) delimiter = "";
			sb.append(str + delimiter);
		}
		return sb.toString();
	}

	public static String joinStringList(List<String> list, String delimiter) {
		StringBuilder sb = new StringBuilder();
		Iterator<String> iter = list.iterator();

		if (iter.hasNext()) sb.append(iter.next());
		while (iter.hasNext()) {
			if (delimiter == null) delimiter = "";
			sb.append(delimiter + iter.next());
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	protected void travelMap(Map<String, Object> map, int level, Vector<String> vctName, Map<String, String> mpRet) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			vctName.set(level, entry.getKey());
			if (entry.getValue() instanceof Map) {
				travelMap((Map<String, Object>) entry.getValue(), level + 1, vctName, mpRet);
				continue;
			}
			mpRet.put(joinStringList(vctName, "!") + "!", (String) entry.getValue());
		}
	}

	public void genFromMap(Map<String, Object> map) {
		Vector<String> vctName = new Vector<String>(Arrays.asList("", "", ""));
		Map<String, String> mpRet = new HashMap<String, String>();
		int level = 0;

		travelMap(map, level, vctName, mpRet);
		for (Map.Entry<String, String> entry : mpRet.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	@SuppressWarnings("unchecked")
	public void loadYaml() {
		String filename = "org/myapp/playground/properties.yaml";

		Yaml yaml = new Yaml();
		Map<String, Object> mpYaml = (Map<String, Object>) yaml.load(retPropertyFile(filename, null));
		System.out.println(mpYaml);

		genFromMap(mpYaml);

	}

	public static List<String> splitString(String str, String delimiter) {
		String[] array = str.split(delimiter);
		return Arrays.asList(array);
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// InputStream in =
		// this.getClass().getResourceAsStream("/com/ebay/playground/TodayLactors.yaml");

		// LoadYamlPropertyFile ly = new LoadYamlPropertyFile();

		// ly.loadYaml();

		String filename = "org/myapp/playground/properties.yaml";
		String text = getPropertyFileString(filename, null);
		System.out.println(text);
		
		Yaml yaml = new Yaml();
		Map<String, Object> mpYaml = (Map<String, Object>) yaml.load(getPropertyFileString(filename, null));
		JSONObject obj = new JSONObject(mpYaml);
		System.out.println(obj);
		
		JsonObject newObj = new JsonParser().parse(obj.toString()).getAsJsonObject();
		System.out.println(newObj);
		
		System.out.println(JsonPath.read(obj.toString(), "$.AAA[1]"));

		// List<String> list = splitString("AA,BB,CC", ",");
		// System.out.println(list.getClass().getName());
		//
		// for (String str : splitString("AA,BB,CC", ",")) {
		// System.out.println(str);
		// }

		// Vector<String> vctName = new
		// Vector<String>(Arrays.asList("a","b","c"));
		// System.out.println(joinStringListIterator(vctName,"!"));
	}
}
