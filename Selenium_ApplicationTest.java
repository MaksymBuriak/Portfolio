package guru99Application;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Guru99ApplicationTest {
	
	ChromeDriver driver;
	
	String url = "http://demo.guru99.com/v4";
	
	@Test (priority = -100)
	public void invokeBrowser() {
		
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver/chromedriver");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--no-sandbox");
		driver = new ChromeDriver(chromeOptions);
		
		driver.manage().window().maximize();
		
		driver.get(url);
	}
	
	@Test(priority = 0)
	public void verifyTitleOfThePage() {
		
		String expectedTitle = "Guru99 Bank Home Page";
		
		String actualTitle = driver.getTitle();
		
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test(priority = 100)
	public void verifyLoginToGuru99Application() {
		
		WebElement userId = driver.findElement(By.name("uid"));
		WebElement userPassword = driver.findElement(By.name("password"));
		WebElement loginButton = driver.findElement(By.name("btnLogin"));
		
		userId.sendKeys("mngr589355");
		userPassword.sendKeys("jynEjAz");
		
		loginButton.click();

	}
	
	@Test(priority = 200)
	public void addCustomer() {
		
		WebElement addCustomerLink = driver.findElement(By.linkText("New Customer"));
		addCustomerLink.click();
		
		driver.findElement(By.xpath("//input[@value='f']")).click();
		
		driver.findElement(By.name("name")).sendKeys("Maksym Buriak");
		
		driver.findElement(By.name("dob")).sendKeys("06/21/1989");
		
		driver.findElement(By.name("addr")).sendKeys("Gurgaon");
		
		driver.findElement(By.name("city")).sendKeys("Gurugram");
		
		driver.findElement(By.name("state")).sendKeys("Haryana");
		
		driver.findElement(By.name("pinno")).sendKeys("122001");
		
		driver.findElement(By.name("telephoneno")).sendKeys("97834523576");
		
		driver.findElement(By.name("emailid")).sendKeys("mngr5851232@gmail.com");
		
		driver.findElement(By.name("password")).sendKeys("jynEjAz");
		
		driver.findElement(By.name("sub")).click();
		
	}
	
	@Test(priority = 400)
	public void GetCustomerID() {
		String customerID = driver.findElement( By.xpath("//table[@id='customer']/tbody/tr[4]/td[2]")).getText();
		
		System.out.println("Customer Id - "+ customerID);
	}
}

