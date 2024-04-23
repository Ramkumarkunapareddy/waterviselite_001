package Smart_Irrigation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.Test;

public class Irrigation_Screen extends Login_Rtu7
{
	@Test(priority = 2 , dependsOnMethods = { "login" })
    public void Irrigatiomodulescrren()
    {
		
	    	WebElement Screen = driver.findElement(By.xpath("//span[text()='Irrigation']/.."));
	    	Screen.click();
	    	try
	    	{
	    	WebElement IrrigationCard = driver.findElement(By.xpath("//div[@class='card-info']/../.."));
	    	if(IrrigationCard.isDisplayed())
	    	{
	    		System.out.println("card is there");
	    		Actions a1 = new Actions(driver);
	    		a1.moveToElement(IrrigationCard);
	    		WebElement DeleteCard = driver.findElement(By.xpath("//div[@class='mr-4 text-white']/span[3]/i"));
	    		a1.moveToElement(DeleteCard).click().click().build().perform();
	    		WebElement Confirmation_Yes = driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[1]"));
	    		Confirmation_Yes.click();
	    		Thread.sleep(4000);
	    		
	    	}
	    	else
	    	{
	    		System.out.println("no card is there");
	    	}
	    	}
	    	catch(Exception e)
	    	{
	    		System.out.println("hello world");
	    	}
	    	//WebElement GotoIrrigation_Operations = driver.findElement(By.xpath("//img[@class='mb-3 currency-icon']"));
	    	WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
	    	WebElement GotoIrrigation_Operations = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='mb-3 currency-icon']")));
	    	GotoIrrigation_Operations.click();
	    	GotoIrrigation_Operations.click();
    	
    }
	
	
}
