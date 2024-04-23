package Smart_Irrigation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;

public class Based_class1
{
	protected static WebDriver driver;
@BeforeSuite	
public void launchBrowser()
{
		driver = new ChromeDriver();
		driver.get("https://192.168.0.4:4202/basepath/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebElement advanced = driver.findElement(By.xpath("//button[@id='details-button']"));
		WebElement link = driver.findElement(By.xpath("//div[@id='details']/p[2]/a"));
		advanced.click();
		link.click();	
}
	//@AfterSuite
	public void teardown()
	{
		driver.close();
	}
}
