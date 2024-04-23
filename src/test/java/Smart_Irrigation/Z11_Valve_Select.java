package Smart_Irrigation;

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

public class Z11_Valve_Select extends Irrigation_Screen {

   @Test(priority = 3 , dependsOnMethods = { "Irrigatiomodulescrren" })
    public void select_Z11TimeBased() throws AWTException
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
    			String IrrigationTime = "10";
    			Duration.sendKeys(IrrigationTime);
    			WebElement Zone11 = driver.findElement(By.xpath("(//label[@name='Zone'])[11]"));
    			Zone11.click();
    			WebElement start = driver.findElement(By.xpath("//button[text()=' Start ']"));
    			
    			WebElement StartButton = wait.until(ExpectedConditions.visibilityOf(start));
    			if(StartButton.isEnabled()) 
    			{
    				StartButton.click();
    				
    			}
    			else
    			{
    				System.out.println("Start button is Disabled");
    			}
    			
    			WebElement torsture = driver.findElement(By.xpath("//div[text()=' Z11 schedule created ']"));
    			
    		      wait.until(ExpectedConditions.visibilityOf(torsture));
    		       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Z11 schedule created ']")));
    			  WebElement close = driver.findElement(By.xpath("//button[@type='button']/span"));
    			 WebElement xmark = wait.until(ExpectedConditions.visibilityOf(close));
    			  xmark.click();
    			  WebElement IrrigationCard = driver.findElement(By.xpath("//div[@class='card-info']/../.."));
    			  
  		    	if(IrrigationCard.isDisplayed())
  		    	{
  		    		System.out.println("card is there");
  		    		WebElement Valveid = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between'])[2]/h2"));
  		    		String valvename = Valveid.getText();
  		    		System.out.println("createdCard is "+ valvename);
  		    	}
  		    	else
  		    	{
  		    		System.out.println("no card is there");
  		    	}
}
}
