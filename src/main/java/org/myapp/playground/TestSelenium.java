package org.myapp.playground;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebElement;

public class TestSelenium {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "/c:/Tools/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();
		
		driver.get("http://www.ebay.com");
		
		WebElement node = driver.findElement(By.cssSelector("div.heros-heading"));
		
		WebElement elem = node.findElement(By.cssSelector("h2"));
		for (WebElement one : driver.findElements(By.cssSelector("#content span.thumb"))) {
			RemoteWebElement rmnode = (RemoteWebElement) one;
			System.out.println(rmnode + " " + rmnode.getId());
		}
		
		String html = driver.getPageSource();
		
		
		System.out.println(elem);
		
		System.out.println(driver.getTitle());
		
		driver.quit();
		
	}

}
