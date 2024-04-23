package All_Valves_At_a_Time_timebasedSchedule;

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


public class ALL_VALVES_Time_Based extends Irrigation_Screen
{
	@Test(dependsOnMethods = {"Irrigatiomodulescrren"})
   public void SelectValves() throws AWTException
   {
		WebElement Smart_Schedule = driver.findElement(By.xpath("(//li[@class='nav-item ng-star-inserted'])[2]/a"));
		Smart_Schedule.click();
		WebElement Farm = driver.findElement(By.xpath("(//select[@name='Farm'])[2]"));
		Select se = new Select(Farm);
		se.selectByVisibleText("Farm12");
		WebElement Schedule_Type = driver.findElement(By.xpath("(//select[@name='Farm'])[3]"));
		Select sq = new Select(Schedule_Type);
		sq.selectByVisibleText("Time Based Schedules");
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
		WebElement Duration = driver.findElement(By.xpath("(//div[@class='input-group'])[7]/input"));
		Duration.click();
		String IrrigationTime = "10";
		Duration.sendKeys(IrrigationTime);
		WebElement Zone11 = driver.findElement(By.xpath("(//label[@name='Zone'])[11]"));
		Zone11.click();
		WebElement Zone12 = driver.findElement(By.xpath("(//label[@name='Zone'])[12]"));
		Zone12.click();
		WebElement Zone13 = driver.findElement(By.xpath("(//label[@name='Zone'])[13]"));
		Zone13.click();
		WebElement Zone14 = driver.findElement(By.xpath("(//label[@name='Zone'])[14]"));
		Zone14.click();
		
		 WebElement Start_Button = driver.findElement(By.xpath("//button[text()=' Start ']"));
		WebElement Start = wait.until(ExpectedConditions.elementToBeClickable(Start_Button));
		 if(Start.isEnabled())
		 {
			 Start.click();
		 }
		 else
		 {
			 System.out.println("Start Button is disabled");
		 }
		 try {
		 WebElement torsture11 = driver.findElement(By.xpath("//div[text()=' Z11 schedule created ']"));
			
	      wait.until(ExpectedConditions.visibilityOf(torsture11));
	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Z11 schedule created ']")));
	       WebElement torsture12 = driver.findElement(By.xpath("//div[text()=' Z12 schedule created ']"));
			
		      wait.until(ExpectedConditions.visibilityOf(torsture12));
		       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Z12 schedule created ']")));
		       WebElement torsture13 = driver.findElement(By.xpath("//div[text()=' Z13 schedule created ']"));
				
			      wait.until(ExpectedConditions.visibilityOf(torsture13));
			       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Z13 schedule created ']")));
			       WebElement torsture14 = driver.findElement(By.xpath("//div[text()=' Z14 schedule created ']"));
					
				      wait.until(ExpectedConditions.visibilityOf(torsture14));
				       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Z14 schedule created ']")));
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace(); 
		 }
		 finally
		 {
			  WebElement close = driver.findElement(By.xpath("//button[@type='button']/span"));
 			 WebElement xmark = wait.until(ExpectedConditions.visibilityOf(close));
 			  xmark.click();
 			 // WebElement IrrigationCard = driver.findElement(By.xpath("//div[@class='card-info']/../.."));
		 }
   }
}
