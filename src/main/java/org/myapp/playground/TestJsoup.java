package org.myapp.playground;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class TestJsoup {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map parseUrl() throws Exception {
		// String strUrl = "http://www.ebay.de/sch/i.html?_nkw=oil";
		// String strUrl = "http://www.ebay.de/sch/i.html?_nkw=EEK%20A%20LED";
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

	public static void parseHtml() {
		// String html =
		// "<td class=\"data\">flagSetId=666&amp;bitPosition=14&amp;value=false</td>";
		String html = "<div class=\"data\">flagSetId=666&amp;bitPosition=14&amp;value=false</div>";
		Element elem = Jsoup.parse("");

		List<Node> lstNode = Parser.parseFragment(html, elem, "");

		elem.html(html);
		System.out.println(elem);
		System.out.println(elem.html());
	}

	public static void runCmd() throws IOException {
		Runtime rt = Runtime.getRuntime();
		String[] commands = { "javac" };
		Process proc = rt.exec(commands);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		// read the output from the command
		System.out.println("Here is the standard output of the command:\n");
		String s = null;
		while ((s = stdInput.readLine()) != null) {
			System.out.println(s);
		}

		// read any errors from the attempted command
		System.out.println("Here is the standard error of the command (if any):\n");
		while ((s = stdError.readLine()) != null) {
			System.out.println(s);
		}
	}

	public static void runCmd1() {
		try {
			// Use a ProcessBuilder
			ProcessBuilder pb = new ProcessBuilder("dir");

			Process p = pb.start();
			InputStream is = p.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			int r = p.waitFor(); // Let the process finish.
			if (r == 0) { // No error
				// run cmd2.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// handleUrl();
		// parseUrl();
		// parseHtml();
		runCmd1();

		/*
		 * String url =
		 * "http://www.ebay.com/itm/Apple-iPhone-4s-8GB-16GB-32GB-Black-White-Factory-Unlocked-Smartphone-/331489884310"
		 * ; String[] arStr = url.split("/");
		 * System.out.println(arStr[arStr.length-1]);
		 */

	}

}
