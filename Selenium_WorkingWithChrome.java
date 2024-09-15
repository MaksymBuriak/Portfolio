package seleniumScripts;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WorkingWithChrome {
	
 	ChromeDriver driver;
 	
 	String url = "http://demo.guru99.com/v4";
		
	public void invokeBrowser() {
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver/chromedriver");
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--no-sandbox");
		driver = new ChromeDriver(chromeOptions);
		
		driver.manage().window().maximize();
		
		driver.get(url);
	}
	
	public void getTitle() {
		String titleOfThePage = driver.getTitle();
		
		System.out.println("Title Of The Page - "+ titleOfThePage);
	}
	
	public void closeBrowser() {
		//driver.close();
		
		driver.quit();
	}
	
	public static void main(String[] args) {
		
		WorkingWithChrome wc = new WorkingWithChrome();
		wc.invokeBrowser();
		
		wc.getTitle();
		
		wc.closeBrowser();
	}
}
