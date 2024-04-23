package Smart_Irrigation;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.Test;

public class Login_Rtu7 extends Based_class1
{
@Test(priority = 1)
public void login() throws InterruptedException
{
	WebElement UserName1 = driver.findElement(By.xpath("//input[@name='UserName']"));
	WebElement Pwd1 = driver.findElement(By.xpath("//input[@name='Password']"));
	WebElement sigin1 = driver.findElement(By.xpath("//button[text()='Sign In']"));
	UserName1.sendKeys("Rtu_7");
	Pwd1.sendKeys("Rtu7@123");
	sigin1.click();
	Thread.sleep(4000);
	WebElement UserIcon = driver.findElement(By.xpath("//div[@id='navbarSupportedContent']/app-navigation/ul[2]/li/a"));
	WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
	try
	{
	WebElement valvestorsture = driver.findElement(By.xpath("//div[text()=' Please assign valves to the selected zone ']"));
	wait.until(ExpectedConditions.visibilityOf(valvestorsture));
	wait.until(ExpectedConditions.invisibilityOf(valvestorsture));
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	WebElement userprofile = wait.until(ExpectedConditions.visibilityOf(UserIcon));
	assertTrue(userprofile.isDisplayed() , "Userprofile is not displayed");
}

public void logout()
{
	WebElement profileIcon = driver.findElement(By.xpath("//div[@id='navbarSupportedContent']/app-navigation/ul[2]"));
	profileIcon.click();
	WebElement LogOut = driver.findElement(By.xpath("//a[@class='dropdown-item']/i"));
	LogOut.click();
}
}