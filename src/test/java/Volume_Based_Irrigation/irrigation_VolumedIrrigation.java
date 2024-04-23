package Volume_Based_Irrigation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Smart_Irrigation.Irrigation_Screen;

public class irrigation_VolumedIrrigation extends Irrigation_Screen
{
	//protected static WebDriver driver;
	@Test(priority = 3 ,dependsOnMethods = { "Irrigatiomodulescrren" })
public void Setup_VolumeBased()
{
WebElement Smart_Schedule = driver.findElement(By.xpath("(//li[@class='nav-item ng-star-inserted'])[2]/a"));
Smart_Schedule.click();
}
	@Test(priority = 4 ,dependsOnMethods = { "Setup_VolumeBased" })
	public void SelectFarm()
	{
		WebElement Farm = driver.findElement(By.xpath("(//select[@name='Farm'])[2]"));
		Select se = new Select(Farm);
		se.selectByVisibleText("Farm12");
	}
	@Test(priority = 5, dependsOnMethods = { "SelectFarm" })
	public void ScheduleType()
	{
		WebElement Schedule_Type = driver.findElement(By.xpath("(//select[@name='Farm'])[3]"));
		Select sq = new Select(Schedule_Type);
		sq.selectByVisibleText("Volume Based Schedules");
		
	}
	@Test(priority = 6 , dependsOnMethods = { "ScheduleType" })
	public void Start_Time() throws AWTException
	{
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement EnterStartTime = driver.findElement(By.xpath("//input[@placeholder='Enter the time']"));
		WebElement statTime = wait.until(ExpectedConditions.elementToBeClickable(EnterStartTime));
		statTime.click();
		Robot re = new Robot();
		re.keyPress(KeyEvent.VK_RIGHT);
		re.keyRelease(KeyEvent.VK_RIGHT);
		for(int i =0; i<2 ; i++)
		{
			re.keyPress(KeyEvent.VK_DOWN);
			re.keyRelease(KeyEvent.VK_DOWN);
			
		}
		re.keyPress(KeyEvent.VK_ENTER);
		re.keyRelease(KeyEvent.VK_ENTER);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String Script = "var timeValue = arguments[0].value;" + 
		                "var timeParts = timeValue.split(':');" +
				        "var hours = parseInt(timeParts[0]);" +
		                "var minutes = timeParts[1];" +
				        "var amPm = hours >=12 ? 'PM' : 'AM';"+
		                "hours = hours % 12 || 12; " +
				        "return hours + ':' + minutes + ' ' + amPm;";
		String formatedTime = (String) jse.executeScript(Script, statTime);
		System.out.println("Valve Start Time Is :"+ formatedTime);
	}
	@Test(priority = 7 , dependsOnMethods = { "Start_Time" })
	public void Enter_Volume()
	{
		String Liters = "250";
		WebElement Volume = driver.findElement(By.xpath("//input[@name='VlmCloseAfter']"));
		Volume.sendKeys(Liters);
		System.out.println("Set Volume of Water :" + Volume.getAttribute("value"));
	}
	
}
