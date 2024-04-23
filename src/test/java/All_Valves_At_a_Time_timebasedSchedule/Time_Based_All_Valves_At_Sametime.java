package All_Valves_At_a_Time_timebasedSchedule;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Smart_Irrigation.Login_Rtu7;

public class Time_Based_All_Valves_At_Sametime extends Login_Rtu7
{
	@Test(dependsOnMethods = "login")
public void deliveryLiters_TimeBased_Allvalves_SameTime() throws InterruptedException
{
		WebElement DashBoard = driver.findElement(By.xpath("(//li[@class='ng-star-inserted'])[1]"));
		DashBoard.click();
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
	//	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Please assign valves to the selected zone ']")));
		WebElement cursor = driver.findElement(By.xpath("//span[@class='carousel-control-next-icon']"));
		for(int i=0;i<2;i++)
		{
			cursor.click();
		}
		WebElement z11icon = driver.findElement(By.xpath("(//div[@class='thumbnail'])[11]/img"));
		z11icon.click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,700);");
		
	
			//Thread.sleep(150000);
		WebElement DuringIrrigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='thumbnail'])[11]/img")));
			/*jse.executeScript("window.scrollTo(0, 0);");
			int minutestoRun = 10;
		  
			

			 try {
		         // Your login code here (if needed)

		         // Define the end time for your monitoring (e.g., 30 minutes from now)
		         long endTime = System.currentTimeMillis() + (minutestoRun * 60 * 1000); // 30 minutes in milliseconds
		         int seconds = 0;
		         boolean zone12 = false; // Start with Zone 11

		         while (System.currentTimeMillis() < endTime || DuringIrrigation.getAttribute("class").contains("../../assets/new_assets/images/addfarmanimations.gif")) {
		             // Click on the current zone
		        	 
		             String zoneXpath = zone12 ? "(//img[@class='ng-star-inserted'])[12]" : "(//img[@class='ng-star-inserted'])[11]";
		             WebElement zoneIcon = null;
		              
		             try
		             {
		            	 zoneIcon = driver.findElement(By.xpath(zoneXpath));
		            	 zoneIcon.click();
		             }
		             catch(StaleElementReferenceException e)
		             {
		            	 System.out.println("Stale element Exception retrying");
		            	 continue;
		             }
		            

		             // Print the delivery liters every second for 10 seconds
		             for (int i = 0; i < 10; i++) 
		             {
		               try { // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		            	   jse.executeScript("window.scrollTo(0, 700);");
		                 WebElement deliveredLitersElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span")));
		                 String deliveryLiters = deliveredLitersElement.getText();
		                 System.out.println("Delivered liters at Zone " + (zone12 ? "12" : "11") + " " + seconds + " Seconds: " + deliveryLiters);
		                 //jse.executeScript("window.scrollTo(0, 0);");
		                 
		               }
		               catch(StaleElementReferenceException e)
		               {
		            	   System.out.println("Stale element Exception retrying");
		            	   continue;
		               }
		              
		                 // Sleep for 1 second
		                 Thread.sleep(1000);
		               
		               
		                 seconds++;
		               
		             }
		             jse.executeScript("window.scrollTo(0, 0);");

		             // Switch zones
		             zone12 = !zone12;
		         }
		     } catch (Exception e) {
		         e.printStackTrace();
		     }*/
	WebElement deliverdLitersElement = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span"));
		String liters = deliverdLitersElement.getText();
		System.out.println(liters);
		//WebElement DuringIrrigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='thumbnail'])[12]/img")));
		jse.executeScript("window.scrollTo(0, 0)");
		int minutestoRun = 10;
		WebElement prevCursor = driver.findElement(By.xpath("//span[@class='carousel-control-prev-icon']"));
		long endIrrigationTime = System.currentTimeMillis() + (minutestoRun * 60 * 1000);
		int Seconds = 0;
		//int maxSecondsPerzone = 25;
		/*boolean zone11Active = true;
		boolean zone13Active = true;
		while (System.currentTimeMillis() <= endIrrigationTime) {
		    try {
		        if (zone11Active) {
		            WebElement Zone11 = driver.findElement(By.xpath("(//div[@class='thumbnail'])[11]/img"));
		            Zone11.click();
		        }
		        else
		        {
		            WebElement Zone12 = driver.findElement(By.xpath("(//div[@class='thumbnail'])[12]/img"));
		            Zone12.click();
		        }
		        if(zone13Active)
		        {
		        	WebElement Zone13 = driver.findElement(By.xpath("(//div[@class='thumbnail'])[13]/img"));
		            Zone13.click();
		        }
		        else
		        {
		        	WebElement Zone14 = driver.findElement(By.xpath("(//div[@class='thumbnail'])[12]/img"));
		            Zone14.click();
		        }
		    } catch (StaleElementReferenceException e) {
		        System.out.println("StaleElementException");
		        continue;
		    }

		    for (int i = 0; i < 20  ; i++) {
		        try {
		        	if(zone11Active)
		        	{
		            jse.executeScript("window.scrollTo(0, 700);");
		            WebElement deliveredLitersElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span")));
		            String deliveryLiters = deliveredLitersElement.getText();
		            System.out.println("Delivered liters at Zone " + (zone11Active ? "11" : "12") + " " + Seconds + " Seconds: " + deliveryLiters);
		           
		            jse.executeScript("window.scrollTo(0, 0)");
		        	}
		        	else if(zone13Active)
		        	{
		        		jse.executeScript("window.scrollTo(0, 700);");
			            WebElement deliveredLitersElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span")));
			            String deliveryLiters = deliveredLitersElement.getText();
			            System.out.println("Delivered liters at Zone " + (zone13Active ? "13" : "14") + " " + Seconds + " Seconds: " + deliveryLiters);
			           
			            jse.executeScript("window.scrollTo(0, 0)");
		        	}
		            
		        } catch (Exception e) {
		            System.out.println("Stale element Exception retrying");
		            continue;
		        }
		    
		        Thread.sleep(1000);
		        Seconds++;
		    }
		        if (zone11Active) {
		            cursor.click();
		        } else {
		            prevCursor.click();
		        }
		      //  jse.executeScript("window.scrollTo(0, 0)");
		        zone11Active = !zone11Active;
		    }*/
		/* try {
	         // Your login code here (if needed)

	         // Define the end time for your monitoring (e.g., 30 minutes from now)
	         long endTime = System.currentTimeMillis() + (minutestoRun * 60 * 1000); // 30 minutes in milliseconds
	         int seconds = 0;
	         boolean zone12Active = false; 
	         boolean zone14Active = false;// Start with Zone 11

	         while (System.currentTimeMillis() < endTime || DuringIrrigation.getAttribute("class").contains("../../assets/new_assets/images/addfarmanimations.gif")) {
	             // Click on the current zone
	        	 
	        	 String zoneXpath = zone12Active ? "(//img[@class='ng-star-inserted'])[12]" : "(//img[@class='ng-star-inserted'])[11]";
	        	 WebElement zoneIcon = null;
	        	 zoneXpath = zone14Active ? "(//img[@class='ng-star-inserted'])[14]" : "(//img[@class='ng-star-inserted'])[13]";

	              
	             try
	             {
	            	 zoneIcon = driver.findElement(By.xpath(zoneXpath));
	            	 zoneIcon.click();
	             }
	             catch(StaleElementReferenceException e)
	             {
	            	 System.out.println("Stale element Exception retrying");
	            	 continue;
	             }
	             
	            

	             // Print the delivery liters every second for 10 seconds
	             for (int i = 0; i < 10; i++) 
	             {
	               try { // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            	   jse.executeScript("window.scrollTo(0, 700);");
	                 WebElement deliveredLitersElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span")));
	                 String deliveryLiters = deliveredLitersElement.getText();
	                 if(zone12Active)
	                 {
	                 System.out.println("Delivered liters at Zone " + (zone12Active ? "12" : "11") + " " + seconds + " Seconds: " + deliveryLiters);
	                 //jse.executeScript("window.scrollTo(0, 0);");
	                 }
	                 else
	                 {
	                	 System.out.println("Delivered liters at Zone " + (zone14Active ? "14" : "13") + " " + seconds + " Seconds: " + deliveryLiters); 
	                 }
	                 
	               }
	               catch(StaleElementReferenceException e)
	               {
	            	   System.out.println("Stale element Exception retrying");
	            	   continue;
	               }
	              
	                 // Sleep for 1 second
	                 Thread.sleep(1000);
	               
	               
	                 seconds++;
	               
	             }
	             jse.executeScript("window.scrollTo(0, 0);");

	             if (zone12Active) {
			            cursor.click();
			        } else {
			            prevCursor.click();
			        }
			      //  jse.executeScript("window.scrollTo(0, 0)");
			        zone12Active = !zone12Active;
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }*/
		 try {
			    // Define the end time for your monitoring (e.g., 30 minutes from now)
			    long endTime = System.currentTimeMillis() + (minutestoRun * 60 * 1000); // 10 minutes in milliseconds
			    int seconds = 0;
			    
			    boolean zone12Active = false;
			    boolean zone14Active = false;

			    while (System.currentTimeMillis() < endTime) {
			        // Click on the current zone
			        String zoneXpath = zone12Active ? "(//img[@class='ng-star-inserted'])[12]" : "(//img[@class='ng-star-inserted'])[11]";
			        WebElement zoneIcon = driver.findElement(By.xpath(zoneXpath));
			        zoneIcon.click();
			        
			        // Print the delivery liters
			        String zoneName = zone12Active ? "Zone 12" : "Zone 11";
			        WebElement deliveredLitersElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span")));
			        String deliveryLiters = deliveredLitersElement.getText();
			        System.out.println("Delivered liters at " + zoneName + " " + seconds + " Seconds: " + deliveryLiters);

			        // Sleep for 1 second
			        Thread.sleep(1000);
			        seconds++;

			        // Switch to the next zone
			        if (zone12Active) {
			            cursor.click(); // Assuming 'cursor' is used to navigate to the next zone
			        } else {
			            prevCursor.click(); // Assuming 'prevCursor' is used to navigate to the previous zone
			        }
			        
			        zone12Active = !zone12Active;
			        
			        // Check if the specified time has elapsed
			        if (System.currentTimeMillis() >= endTime) {
			            break;
			        }
			        
			        // Repeat the process for zone 14
			        zoneXpath = zone14Active ? "(//img[@class='ng-star-inserted'])[14]" : "(//img[@class='ng-star-inserted'])[13]";
			        zoneIcon = driver.findElement(By.xpath(zoneXpath));
			        zoneIcon.click();
			        
			        zoneName = zone14Active ? "Zone 14" : "Zone 13";
			        deliveredLitersElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span")));
			        deliveryLiters = deliveredLitersElement.getText();
			        System.out.println("Delivered liters at " + zoneName + " " + seconds + " Seconds: " + deliveryLiters);

			        Thread.sleep(1000);
			        seconds++;

			        if (zone14Active) {
			            cursor.click();
			        } else {
			            prevCursor.click();
			        }
			        
			        zone14Active = !zone14Active;
			    }
			} catch (Exception e) {
			    e.printStackTrace();
			}

}
}
