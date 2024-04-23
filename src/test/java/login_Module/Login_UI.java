package login_Module;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Assert.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({login_Listner.class})
public class Login_UI extends Based_class
{
	@Test(priority = 1)
public void AquasStrideLogo()
{
    Login_Objects LO = new Login_Objects(driver);
	WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
	WebElement logoicon = wait.until(ExpectedConditions.visibilityOf(LO.Logicon()));
	org.openqa.selenium.Point location = logoicon.getLocation();
	assertTrue(location.getX() > 80);
	assertTrue(logoicon.isDisplayed() , "logoicon is not displayed");	
}
	@Test(priority = 2)
	public void Sign_Details()
	{
		Login_Objects LO = new Login_Objects(driver);
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement UserName = wait.until(ExpectedConditions.visibilityOf(LO.Uname()));
		WebElement Pwd = wait.until(ExpectedConditions.visibilityOf(LO.pwd()));
		WebElement sigin = wait.until(ExpectedConditions.visibilityOf(LO.Sigin()));
		WebElement Doyouhaveaccount = wait.until(ExpectedConditions.visibilityOf(LO.Do_tou_haveAccountTag()));
	assertTrue(UserName.isDisplayed() , " Username textbox is not Displayed");
	assertTrue(Pwd.isDisplayed() , " password TextBox is not Displayed");
	assertTrue(sigin.isDisplayed() , " Sign button is not Displayed");
	assertTrue(Doyouhaveaccount.isDisplayed() , "Doyouhave account sign is not displayed");
	}
	@Test(priority = 3)
	public void checkmouseCursorOnUsername()
	{
		Login_Objects LO = new Login_Objects(driver);
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement UsernamemouseCursorCheck = wait.until(ExpectedConditions.visibilityOf(LO.Uname()));
		boolean Usertextbox = UsernamemouseCursorCheck.equals(driver.switchTo().activeElement());
		assertTrue(Usertextbox , "Focus is not on UserTextBox");
	}
	
	@Test(priority = 4)
	public void TabFunctionalitytoLogin() throws Exception
	{
		Login_Objects LO = new Login_Objects(driver);
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement UserName = wait.until(ExpectedConditions.visibilityOf(LO.Uname()));
		UserName.sendKeys("User_rk" , Keys.TAB , "Userrk@123" , Keys.TAB , Keys.TAB , Keys.ENTER);
		WebElement UserIcon = wait.until(ExpectedConditions.visibilityOf(LO.UIcon()));
	    WebElement userprofile = wait.until(ExpectedConditions.visibilityOf(UserIcon));
		//Thread.sleep(4000);
		assertTrue(userprofile.isDisplayed() , "Userprofile is not displayed");
		}
	
	@Test(priority = 5)
	public void loginValidUserName_Validpwd()
	{
		Login_Objects LO = new Login_Objects(driver);
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement UserName = wait.until(ExpectedConditions.visibilityOf(LO.Uname()));
		WebElement Pwd = wait.until(ExpectedConditions.visibilityOf(LO.pwd()));
		WebElement sigin = wait.until(ExpectedConditions.visibilityOf(LO.Sigin()));
		UserName.sendKeys("User_rk");
		Pwd.sendKeys("Userrk@123");
		sigin.click();
		WebElement UserIcon = wait.until(ExpectedConditions.visibilityOf(LO.UIcon()));
		WebElement userprofile = wait.until(ExpectedConditions.visibilityOf(UserIcon));
		assertTrue(userprofile.isDisplayed() , "Userprofile is not displayed");
	}
	
	@Test(priority = 6 )
	public void loginValidUserName_InValidPwd()
	{
		Login_Objects LO = new Login_Objects(driver);
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement UserName = wait.until(ExpectedConditions.visibilityOf(LO.Uname()));
		WebElement Pwd = wait.until(ExpectedConditions.visibilityOf(LO.pwd()));
		WebElement sigin = wait.until(ExpectedConditions.visibilityOf(LO.Sigin()));
		UserName.sendKeys("User_rk");
		Pwd.sendKeys("Userrk12"
				+ "123");
		sigin.click();
		WebElement toastmsg = wait.until(ExpectedConditions.visibilityOf(LO.Toastmsg()));
		WebDriverWait wait1 = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement toastmsgverify = wait1.until(ExpectedConditions.visibilityOf(toastmsg));
		assertTrue(toastmsgverify.isDisplayed() , "Invalid creadites passed");
     }
	
	@Test(priority = 7)
	public void login_InvalidUserName_ValidPwd()
	{
		Login_Objects LO = new Login_Objects(driver);
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement UserName = wait.until(ExpectedConditions.visibilityOf(LO.Uname()));
		WebElement Pwd = wait.until(ExpectedConditions.visibilityOf(LO.pwd()));
		WebElement sigin = wait.until(ExpectedConditions.visibilityOf(LO.Sigin()));
		UserName.sendKeys("Userrk");
		Pwd.sendKeys("Userrk@123");
		sigin.click();
		WebElement toastmsg = wait.until(ExpectedConditions.visibilityOf(LO.Toastmsg()));
		WebElement toastmsgverify = wait.until(ExpectedConditions.visibilityOf(toastmsg));
		assertTrue(toastmsgverify.isDisplayed() , "Invalid creadites passed");
	}
	@Test(priority = 8)
	public void login_InvalidUsername_InvalidPwd()
	{
		Login_Objects LO = new Login_Objects(driver);
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement UserName = wait.until(ExpectedConditions.visibilityOf(LO.Uname()));
		WebElement Pwd = wait.until(ExpectedConditions.visibilityOf(LO.pwd()));
		WebElement sigin = wait.until(ExpectedConditions.visibilityOf(LO.Sigin()));
		UserName.sendKeys("Userrk");
		Pwd.sendKeys("Userrk#123");
		sigin.click();
		WebElement toastmsg2 = wait.until(ExpectedConditions.visibilityOf(LO.Toastmsg()));
		WebElement toastmsgverify = wait.until(ExpectedConditions.visibilityOf(toastmsg2));
		assertTrue(toastmsgverify.isDisplayed() , "Invalid creadites passed");
	}
	@Test(priority = 9)
	public void login_Blankcreadites()
	{
		Login_Objects LO = new Login_Objects(driver);
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement UserName = wait.until(ExpectedConditions.visibilityOf(LO.Uname()));
		WebElement Pwd = wait.until(ExpectedConditions.visibilityOf(LO.pwd()));
		WebElement sigin = wait.until(ExpectedConditions.visibilityOf(LO.Sigin()));
		UserName.sendKeys("      ");
		Pwd.sendKeys("      ");
		sigin.click();
		WebElement toastmsg3 = wait.until(ExpectedConditions.visibilityOf(LO.Toastmsg()));
		WebElement toastmsgverify = wait.until(ExpectedConditions.visibilityOf(toastmsg3));
		assertTrue(toastmsgverify.isDisplayed() , "Invalid creadites passed");
	}
	@Test(priority = 10)
	public void BrowserBack()
	{
		Login_Objects LO = new Login_Objects(driver);
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement UserName = wait.until(ExpectedConditions.visibilityOf(LO.Uname()));
		WebElement Pwd = wait.until(ExpectedConditions.visibilityOf(LO.pwd()));
		WebElement sigin = wait.until(ExpectedConditions.visibilityOf(LO.Sigin()));
		UserName.sendKeys("User_rk");
		Pwd.sendKeys("Userrk@123");
		sigin.click();
		WebElement UserIcon = wait.until(ExpectedConditions.visibilityOf(LO.UIcon()));
		
		WebElement userprofile = wait.until(ExpectedConditions.visibilityOf(UserIcon));
		assertTrue(userprofile.isDisplayed() , "Userprofile is not displayed");
		driver.get("https://www.amazon.in");
		driver.navigate().back();
		WebElement UserIcon1 =  wait.until(ExpectedConditions.visibilityOf(LO.UIcon()));
		WebDriverWait wait5 = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement userprofile1 = wait5.until(ExpectedConditions.visibilityOf(UserIcon1));
		assertTrue(userprofile1.isDisplayed() , "User is logout");
	}
	@Test(priority = 11)
	public void LogoutBrowserback() throws InterruptedException
	{
		Login_Objects LO = new Login_Objects(driver);
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement UserName = wait.until(ExpectedConditions.visibilityOf(LO.Uname()));
		WebElement Pwd = wait.until(ExpectedConditions.visibilityOf(LO.pwd()));
		WebElement sigin = wait.until(ExpectedConditions.visibilityOf(LO.Sigin()));		
		UserName.sendKeys("User_rk");
		Pwd.sendKeys("Userrk@123");
		sigin.click();
		WebDriverWait wait6;
		try {
		wait6 = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement torsturevalves  = wait6.until(ExpectedConditions.visibilityOf(LO.NoValvesToastur));
		wait6.until(ExpectedConditions.visibilityOf(torsturevalves));
		wait6.until(ExpectedConditions.invisibilityOf(torsturevalves));
		}
		catch(Exception e)
		{
			/*wait6 = new WebDriverWait(driver , Duration.ofSeconds(10));
	    driver.findElement(By.xpath("//ul[@class='navbar-nav float-right']/li/a/span")).click();
	    WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-menu user-dd show']/a[2]"));
	    
	    WebElement l = wait6.until(ExpectedConditions.elementToBeClickable(logout));
	    l.click();*/
		}
	    finally
	    {
	    	wait6 = new WebDriverWait(driver , Duration.ofSeconds(10));
		    driver.findElement(By.xpath("//span[@class='class-name']")).click();
		    WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-menu user-dd show']/a[2]"));
		    
		    WebElement l = wait6.until(ExpectedConditions.elementToBeClickable(logout));
		    l.click();
	    }
		
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("window.open();");
	    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
	    
	    driver.get("https://www.amazon.in");
		driver.switchTo().window(tabs.get(0));
		
		By UserIcon2 = By.xpath("//div[@id='navbarSupportedContent']/app-navigation/ul[2]/li/a");
		try {
			wait6.until(ExpectedConditions.invisibilityOfElementLocated(UserIcon2));
		}
		catch(TimeoutException e)
		{
			Assert.assertFalse(true , "Userprofile is Displayed");
		}
	
	}
	@Test(priority = 12)
	public void logincheckafterclosethe_Browser() throws InterruptedException
	{
		 Login_Objects LO = new Login_Objects(driver);
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement UserName = wait.until(ExpectedConditions.visibilityOf(LO.Uname()));
		    WebElement Pwd = wait.until(ExpectedConditions.visibilityOf(LO.pwd()));
		    WebElement sigin = wait.until(ExpectedConditions.visibilityOf(LO.Sigin()));
		    UserName.sendKeys("User_rk");
		    Pwd.sendKeys("Userrk@123");
		    sigin.click();
		    WebElement UserIcon3 = wait.until(ExpectedConditions.visibilityOf(LO.UIcon()));

		    WebElement userprofile3 = wait.until(ExpectedConditions.visibilityOf(UserIcon3));
		    assertTrue(userprofile3.isDisplayed(), "User is not logged in");
		    driver.close(); // Close the browser after login

		    // Re-use existing WebDriver instance managed by TestNG
		    launchbrowser();

		    WebDriverWait wait8 = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement UserIcon4 = wait.until(ExpectedConditions.visibilityOf(LO.Icons()));
		    try {
		        wait8.until(ExpectedConditions.invisibilityOf(UserIcon4));
		    } catch (TimeoutException e) {
		        assertFalse(true, "User is still logged in");
		    }
	}
	@Test(priority = 13)
	public void NoFarms_User()
	{
		Login_Objects LO = new Login_Objects(driver);
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement UserName = wait.until(ExpectedConditions.visibilityOf(LO.Uname()));
		WebElement Pwd = wait.until(ExpectedConditions.visibilityOf(LO.pwd()));
		WebElement sigin = wait.until(ExpectedConditions.visibilityOf(LO.Sigin()));	
		UserName.sendKeys("Vishwa");
		Pwd.sendKeys("Vishwa@123");
		sigin.click();
		WebElement UserIcon4 = driver.findElement(By.xpath("//div[@id='navbarSupportedContent']/app-navigation/ul[2]/li/a"));
		WebDriverWait waits = new WebDriverWait(driver , Duration.ofSeconds(20));
		waits.until(ExpectedConditions.visibilityOf(UserIcon4));
		WebElement Farm = driver.findElement(By.xpath("//h2[text()='Add Farm']"));
		assertTrue(Farm.isDisplayed() , "Farm module is not displayed");
	}
	@Test(priority = 14)
	public void Farmhave_user()
	{
		Login_Objects LO = new Login_Objects(driver);
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement UserName = wait.until(ExpectedConditions.visibilityOf(LO.Uname()));
		WebElement Pwd = wait.until(ExpectedConditions.visibilityOf(LO.pwd()));
		WebElement sigin = wait.until(ExpectedConditions.visibilityOf(LO.Sigin()));	
		UserName.sendKeys("User_rk");
		Pwd.sendKeys("Userrk@123");
		sigin.click();
		WebElement UserIcon4 = driver.findElement(By.xpath("//div[@id='navbarSupportedContent']/app-navigation/ul[2]/li/a"));
		WebDriverWait waits = new WebDriverWait(driver , Duration.ofSeconds(20));
		waits.until(ExpectedConditions.visibilityOf(UserIcon4));
		WebElement Dashboardpresent = driver.findElement(By.xpath("//li[@class='sidebar-item ng-star-inserted active']"));
		assertTrue(Dashboardpresent.isDisplayed() , "After login DashBoard not present");
		
	}
	}
	
	

