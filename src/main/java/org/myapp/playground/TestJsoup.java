package org.myapp.playground;

import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestJsoup {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map parseUrl() throws Exception {
		// String strUrl = "http://www.ebay.de/sch/i.html?_nkw=oil";
		//String strUrl = "http://www.ebay.de/sch/i.html?_nkw=EEK%20A%20LED";
		String strUrl = "http://www.ebay.co.uk/sch/i.html?_nkw=iphone";

		URL url = new URL(strUrl);
		Document doc = Jsoup.parse(url, 5000);
		// System.out.println(elem);

		Map ret = new HashMap();
		List list = new ArrayList();
		for (Element elem : doc.select(".sresult")) {
			Map obj = new HashMap();
			obj.put("id", elem.attr("listingid"));
			obj.put("url", elem.select("a.img").attr("href"));
			obj.put("price", elem.select(".lvprice").text());
			obj.put("ppu", elem.select(".lvprice .ppu").text());
			obj.put("lvsubtitle", elem.select(".lvsubtitle").text());
			
			Elements lstE = elem.select(".timeleft");
			System.out.println("HEHE: " + lstE);
			

			// if (obj.get("ppu").equals("") == false)
			System.out.println(obj);
			list.add(obj);
		}
		ret.put("ret", list);

		/*
		 * List list = new ArrayList(); list.add("AAA"); list.add(123);
		 * 
		 * Map obj = new LinkedHashMap(); obj.put("name", "foo"); obj.put("num",
		 * new Integer(100)); obj.put("balance", new Double(1000.21));
		 * obj.put("is_vip", new Boolean(true)); obj.put("nickname", null);
		 * obj.put("list", list);
		 * 
		 * StringWriter out = new StringWriter(); JSONValue.writeJSONString(obj,
		 * out); String jsonText = out.toString(); System.out.print(jsonText);
		 */
		return ret;
	}
	
	public static void handleUrl() throws Exception {
		String strUrl = "http://www.ebay.de/sch/i.html?_nkw=EEK%20A%20LED";

		URL url = new URL(strUrl);
		System.out.println(url.getRef());
		System.out.println(url.getPath());
		System.out.println(url.getProtocol());
		System.out.println(url.getHost());
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//handleUrl();
		parseUrl();
		
		/*
		String url = "http://www.ebay.com/itm/Apple-iPhone-4s-8GB-16GB-32GB-Black-White-Factory-Unlocked-Smartphone-/331489884310";
		String[] arStr = url.split("/");
		System.out.println(arStr[arStr.length-1]);
		*/	

	}

}
