package Volume_Based_Irrigation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Volume_Based_Irrigation_Start  extends Z11_ValveSelected_VolumeBased
{
	
	@Test(dependsOnMethods = { "Z11_Valve_Selected_VolumeBased" })
   public void Start_VoumeBased_Irrigation()
   {
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
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
   }
	@Test(dependsOnMethods = { "Start_VoumeBased_Irrigation" })
	public void ExitIrrigation_Screen()
	{
		WebElement torsture_z11 = driver.findElement(By.xpath("//div[contains(text(),' schedule created ')]"));
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
	      wait.until(ExpectedConditions.visibilityOf(torsture_z11));
	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),' schedule created ')]")));
	       try {
		  WebElement close = driver.findElement(By.xpath("//span[text()='×']"));
		 WebElement xmark = wait.until(ExpectedConditions.visibilityOf(close));
		  xmark.click();
	}
	       catch(Exception e)
	       {
	    	   WebElement close = driver.findElement(By.xpath("//span[text()='×']"));
	  		 WebElement xmark = wait.until(ExpectedConditions.visibilityOf(close));
	  		  xmark.click();
	       }
}
}
