package org.myapp.playground;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebElement;

public class TestSelenium {
	
	public static String getOuterHTML(WebDriver driver, WebElement elem) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].outerHTML;", elem);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "/c:/Tools/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();
		
		driver.get("http://www.ebay.com/feed/1114?FEED_LAYOUT_GRID=1");
		
		WebElement node = driver.findElement(By.cssSelector("div.tile"));
		String html = getOuterHTML(driver, node);
		System.out.println(html);
		
		Element elem = Jsoup.parse(html);
		Elements lstElem = elem.getElementsByTag("html");
		Element eee = null;
		if (lstElem.size() == 0) {
			
		} else {
			eee = elem.select("body > *").get(0);
		}
		System.out.println(lstElem.size());
		//elem.select("div").
		//String ttt = elem.getElementsByAttribute("class");
		//System.out.println(elem);
		
		
//		WebElement elem = node.findElement(By.cssSelector("h2"));
//		for (WebElement one : driver.findElements(By.cssSelector("#content span.thumb"))) {
//			RemoteWebElement rmnode = (RemoteWebElement) one;
//			System.out.println(rmnode + " " + rmnode.getId());
//		}
//		
//		String html = driver.getPageSource();
//		
//		
//		System.out.println(elem);
//		
//		System.out.println(driver.getTitle());
//		
//		driver.quit();
		
	}

}
