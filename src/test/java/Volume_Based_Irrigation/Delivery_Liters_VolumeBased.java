package Volume_Based_Irrigation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Delivery_Liters_VolumeBased extends Volume_Based_Irrigation_Start
{
	@Test(dependsOnMethods = { "ExitIrrigation_Screen" })
	
   public void DashBoRD()
   {
	   WebElement DashBoard = driver.findElement(By.xpath("//span[text()='Dashboard']/.."));
	   DashBoard.click();
	   WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
	   try
	   {
	   WebElement Torst = driver.findElement(By.xpath("//div[text()=' Please assign valves to the selected zone ']"));
	   wait.until(ExpectedConditions.visibilityOf(Torst));
	   wait.until(ExpectedConditions.invisibilityOf(Torst));
	   }
	   catch(Exception e)
	   {
		  e.printStackTrace();
	   }
	   finally
	   {
		   WebElement cursor = driver.findElement(By.xpath("//span[@class='carousel-control-next-icon']"));
			for(int i=0;i<2;i++)
			{
				cursor.click();
			} 
	   }
	   
   }
	@Test(dependsOnMethods = {"DashBoRD"})
   public void Zon11_click() throws InterruptedException
   {
	  
		WebElement z11icon = driver.findElement(By.xpath("(//div[@class='thumbnail'])[11]/img"));
		z11icon.click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,700);");
		Thread.sleep(100000);
   }
	//@Test(dependsOnMethods = {"Zone11_click"})
   public void VolumeBased_DeliveryLiters() throws InterruptedException
   {
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement DuringIrrigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@class='ng-star-inserted'])[12]")));
	   String ExpectedLiters = "300 (Liters)";
	   String DeliveryLiters = "0 (Liters)";
	   while(DeliveryLiters.equals(ExpectedLiters) && DuringIrrigation.getAttribute("class").contains("../../assets/new_assets/images/addfarmanimations.gif"))
	   {
		   try
		   {
			   WebElement DeliveryVolumeLiters = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span"));  
			   String DelryLiters = DeliveryVolumeLiters.getText();
			   DeliveryLiters = DelryLiters.split(" ")[0];
			   Thread.sleep(1000);
		   }
		   catch (StaleElementReferenceException e)
		   {
			   System.out.println("Stale element reference Exception Occured");
		   }
	   }
	   System.out.println("Reached The Expected Volume of " + ExpectedLiters + "liters");
	   
		   
   }
   @Test(dependsOnMethods = {"Zon11_click"})
   public void myTest() throws InterruptedException
   {
	   int TargetLiters = 250;
	   int timeEclpsedSeconds = 0;
	   double CurrentDeliveryLiters = 0;
	   while(true)
	   {
		   WebElement DeliveryVolumeLiters = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span"));    
		   String DelryLiters = DeliveryVolumeLiters.getText();
		   double NumericPart = extractNumericValue(DelryLiters);
		   System.out.println("Time :"+timeEclpsedSeconds + "seconds" + "Delivery Liters "+ NumericPart + "liters");
		   CurrentDeliveryLiters = NumericPart;
		   if(CurrentDeliveryLiters > TargetLiters)
		   {
			   System.out.println("Reached the target delivery liters of " + TargetLiters + " liters.");
               break;
		   }
		   Thread.sleep(1000);
		   timeEclpsedSeconds++;  
	   }
	   boolean deliveryLitersZero = false;
	   while(true)
	   {
		  WebElement DeliveryVolumeLiters = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span"));  
		   String dvLiters = DeliveryVolumeLiters.getText();
		   System.out.println("Delivered liters (target is over) at " + timeEclpsedSeconds + " Seconds: " + dvLiters );
		   
		   String numericPart = dvLiters.split(" ")[0];
		   int litersValue = (int) Double.parseDouble(numericPart);
		   
		   if(litersValue == 0)
		   {
			   if(!deliveryLitersZero)
			   {
				   System.out.println("Delivery liters is Zero");
				   deliveryLitersZero = true;
			   }
			   
		   }
		   else 
		   {
			   deliveryLitersZero = false;  
		   }
		   if(CurrentDeliveryLiters > TargetLiters && litersValue == 0 )
		   {
			   System.out.println("Target is over and Delivery liters is turned to zero");
			   break;
		   }
		   Thread.sleep(1000);
		   timeEclpsedSeconds++;
	   }
   }
   private double extractNumericValue(String text)
   {
	 String numericPart = text.split(" ")[0];
	 return Double.parseDouble(numericPart);
   }
}
