package TimeBased_TwoValves_at_same_Time;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Smart_Irrigation.Irrigation_Screen;

public class Z13_Z14_Valves_TimeBased extends Irrigation_Screen
{
@Test(dependsOnMethods = { "Irrigatiomodulescrren" })	
public void Z13_Z14_Valves_At_a_Time() throws AWTException
{
	WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
	WebElement Smart = driver.findElement(By.xpath("//ul[@class='justify-content-start nav nav-tabs']/li[2]"));
	Smart.click();
	Select s = new Select(driver.findElement(By.xpath("(//select[@name='Farm'])[2]")));
			s.selectByVisibleText("Farm12");
			
			Select se = new Select(driver.findElement(By.xpath("(//select[@name='Farm'])[3]")));
			se.selectByVisibleText("Time Based Schedules");
			Actions a = new Actions(driver);
			
			
			WebElement EnterstartTime = driver.findElement(By.xpath("//input[@name='OpenAt']"));
			
			a.moveToElement(EnterstartTime).click().build().perform();
			Robot r = new Robot();
			int mins = 3;
			r.keyPress(KeyEvent.VK_RIGHT);
			r.keyRelease(KeyEvent.VK_RIGHT);
			
			// set the time
			for(int i = 0 ; i<mins; i++)
			{    
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
			}
			r.keyRelease(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			
			WebElement Duration = driver.findElement(By.xpath("(//div[@class='input-group'])[7]/input"));
			Duration.click();
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			String value1 = (String) js1.executeScript("var timeValue = arguments[0].value; var timeParts = timeValue.split(':'); var hours = parseInt(timeParts[0]); var minutes = timeParts[1]; var ampm = hours >= 12 ? 'PM' : 'AM'; hours = hours % 12 || 12; return hours + ':' + minutes + ' ' + ampm;", driver.findElement(By.xpath("(//div[@class='input-group'])[6]/input")));
			System.out.println("Valve Start is : "+ value1);
			String IrrigationTime = "6";
			Duration.sendKeys(IrrigationTime);
			WebElement Zone13 = driver.findElement(By.xpath("(//label[@name='Zone'])[13]"));
			Zone13.click();
			WebElement Zone14 = driver.findElement(By.xpath("(//label[@name='Zone'])[14]"));
			Zone14.click();
			WebElement StartButton = null;
			try {
			WebElement start = driver.findElement(By.xpath("//button[text()=' Start ']"));
			
			StartButton = wait.until(ExpectedConditions.elementToBeClickable(start));
			}
			catch(Exception e)
			{
				WebElement start = driver.findElement(By.xpath("//button[text()=' Start ']"));
    			
    			StartButton = wait.until(ExpectedConditions.elementToBeClickable(start))	;
			}
			
			if(StartButton.isEnabled()) 
			{
				StartButton.click();
				
			}
			else
			{
				System.out.println("Start button is Disabled");
			}   
			try {
			WebElement torsture13 = driver.findElement(By.xpath("//div[text()=' Z13 schedule created ']"));
			
		      wait.until(ExpectedConditions.visibilityOf(torsture13));
		       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Z13 schedule created ']")));
		      // WebElement torsture12 = driver.findElement(By.xpath("//div[text()=' Z12 schedule created ']"));
   			
 		   //   wait.until(ExpectedConditions.visibilityOf(torsture12));
 		       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Z14 schedule created ']")));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally {
			  WebElement close = driver.findElement(By.xpath("//button[@type='button']/span"));
			 WebElement xmark = wait.until(ExpectedConditions.visibilityOf(close));
			  xmark.click();
			}
			  	
}
}
