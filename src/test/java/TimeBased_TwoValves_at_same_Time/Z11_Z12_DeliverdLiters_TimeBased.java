package TimeBased_TwoValves_at_same_Time;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Smart_Irrigation.Login_Rtu7;

public class Z11_Z12_DeliverdLiters_TimeBased extends Z11_Z12_Valves
{
	@Test(dependsOnMethods = { "Z11_Z12Valves" })
  public void Deliverd_Liters_Z11_Z12() throws AWTException, InterruptedException
  {
		WebElement DashBoard = driver.findElement(By.xpath("(//li[@class='ng-star-inserted'])[1]"));
		DashBoard.click();
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Please assign valves to the selected zone ']")));
		WebElement cursor = driver.findElement(By.xpath("//span[@class='carousel-control-next-icon']"));
		for(int i=0;i<2;i++)
		{
			cursor.click();
		}
		WebElement z11icon = driver.findElement(By.xpath("(//div[@class='thumbnail'])[11]/img"));
		z11icon.click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,700);");
		
		Thread.sleep(120000);
		
		WebElement deliverdLitersElement = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span"));
		String liters = deliverdLitersElement.getText();
		System.out.println(liters);	
	//Robot r = new Robot();
	//r.keyPress(KeyEvent.VK_PAGE_UP);
	//r.keyRelease(KeyEvent.VK_PAGE_UP);
	//WebElement DuringIrrigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@src='../../assets/new_assets/images/addfarmanimations.gif']")));
	WebElement DuringIrrigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='thumbnail'])[11]/img")));
	jse.executeScript("window.scrollTo(0, 0);");
	int minutestoRun = 6;
  
	
	
	
	
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
     }}}
/*
	// Define the end time for your monitoring (e.g., 30 minutes from now)
	long endTime = System.currentTimeMillis() + (minutestoRun * 60 * 1000); // 30 minutes in milliseconds
	int seconds = 0;

	// WebElement to store the delivery liters element
	WebElement deliveredLitersElement = null;

	// Initial zone
	boolean zone12 = true;

	// Loop to monitor delivery liters and switch zones every 10 seconds
	while (System.currentTimeMillis() < endTime || DuringIrrigation.getAttribute("class").contains("../../assets/new_assets/images/addfarmanimations.gif")) 
	{
	    try
	    {
	       
	        jse.executeScript("window.scrollTo(0, 700);");
	        
	     // Alternate between zones every 10 seconds
	     if (seconds % 10 == 0)
	     {
	    	 jse.executeScript("window.scrollTo(0, 0);"); 
	    	 zone12 = !zone12; // Switch zones
	         System.out.println("Switched to Zone ram " + (zone12 ? "12" : "11") + " at " + seconds + " Seconds");
	     }

	     // Locate the delivery liters element for the current zone
	     String zoneXpath = zone12 ? "(//img[@class='ng-star-inserted'])[12]" : "(//img[@class='ng-star-inserted'])[11]";
	     WebElement zoneIcon = driver.findElement(By.xpath(zoneXpath));

	     // Scroll into view if necessary
	     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", zoneIcon);

	     // Use JavaScript to click the zone icon forcefully
	     try 
	     {
	         ((JavascriptExecutor) driver).executeScript("arguments[0].click();", zoneIcon);
	     }
	     catch (WebDriverException e) 
	     {
	         // Handle any exceptions, e.g., ElementClickInterceptedException
	         System.out.println("Failed to click zone icon, trying to scroll <app-navigation> out of the way.");
	         WebElement appNavigation = driver.findElement(By.cssSelector("app-navigation"));
	         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", appNavigation);
	         Thread.sleep(1000); // Wait for a moment after scrolling
	         ((JavascriptExecutor) driver).executeScript("arguments[0].click();", zoneIcon);
	     }

	     // Wait for the deliverdLitersElement
	     WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(25));
	     deliveredLitersElement = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span")));

	     // ... Rest of your code ...
	        // Try to get and print the delivery liters
	        try {
	            WebElement ls = wait.until(ExpectedConditions.visibilityOf(deliveredLitersElement));
	            String deliveryLiters = ls.getText();
	            System.out.println("Delivered liters at Zone " + (zone12 ? "12" : "11") + " " + seconds + " Seconds: " + deliveryLiters);
	        } catch (StaleElementReferenceException e) {
	            WebElement ls = wait.until(ExpectedConditions.visibilityOf(deliveredLitersElement));
	            String deliveryLiters = ls.getText();
	            System.out.println("Delivered liters at Zone " + (zone12 ? "12" : "11") + " " + seconds + " Seconds: " + deliveryLiters);
	        }

	        // Rest of your code for the loop
	        Thread.sleep(1000);
	        seconds++;
	    } catch (StaleElementReferenceException e) {
	        // Handle stale element reference exception
	        System.out.println("Stale element reference exception occurred. Retrying...");
	    }
	}

	/*long endTime = System.currentTimeMillis() + minutestoRun * 60 * 1000;//src="../../assets/new_assets/images/addfarmanimations.gif"
	int Seconds = 0;
	while (System.currentTimeMillis() < endTime || DuringIrrigation.getAttribute("class").contains("../../assets/new_assets/images/addfarmanimations.gif")) {
	    try {
	        // Scroll to the element
	        jse.executeScript("window.scrollTo(0, 700);");

	        // Locate the deliverdLitersElement
	        deliverdLitersElement = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span"));

	        // Wait for DuringIrrigation element and reassign
	        WebDriverWait wait1 = new WebDriverWait(driver , Duration.ofSeconds(25));
	        DuringIrrigation = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@class='ng-star-inserted'])[11]")));
	        DuringIrrigation.click();
	        // Try to get and print the delivery liters
	        try {
	            WebElement ls = wait.until(ExpectedConditions.visibilityOf(deliverdLitersElement));
	            String deliveryliters = ls.getText();
	            System.out.println("Delivered liters at z11 " + Seconds + " Seconds: " + deliveryliters);
	        } catch (StaleElementReferenceException e) {
	            WebElement ls = wait.until(ExpectedConditions.visibilityOf(deliverdLitersElement));
	            String deliveryliters = ls.getText();
	            System.out.println("Delivered liters at z11" + Seconds + " Seconds: " + deliveryliters);
	        }

	        // Rest of your code for the loop
	        Thread.sleep(1000);
	        Seconds++;

	        if (Seconds % 10 == 0) {
	            // Click the zone icon every 10 seconds
	            jse.executeScript("window.scrollTo(0, 0);");
	            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
	            WebElement duringIrrigationElement = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@class='ng-star-inserted'])[12]")));
	            duringIrrigationElement.click();
	            System.out.println("Clicked on zone 12 icon at " + Seconds + " Seconds");
	            try {
	    	        // Scroll to the element
	    	        jse.executeScript("window.scrollTo(0, 700);");

	    	        // Locate the deliverdLitersElement
	    	        deliverdLitersElement = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span"));

	    	        // Wait for DuringIrrigation element and reassign
	    	      //  WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(25));
	    	      //  DuringIrrigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@class='ng-star-inserted'])[11]")));
	    	       // DuringIrrigation.click();
	    	        // Try to get and print the delivery liters
	    	        try {
	    	            WebElement ls = wait.until(ExpectedConditions.visibilityOf(deliverdLitersElement));
	    	            String deliveryliters = ls.getText();
	    	            System.out.println("Delivered liters at z12 " + Seconds + " Seconds: " + deliveryliters);
	    	        } catch (StaleElementReferenceException e) {
	    	            WebElement ls = wait.until(ExpectedConditions.visibilityOf(deliverdLitersElement));
	    	            String deliveryliters = ls.getText();
	    	            System.out.println("Delivered liters at z12" + Seconds + " Seconds: " + deliveryliters);
	    	        }

	    	        // Rest of your code for the loop
	    	        Thread.sleep(1000);
	    	        Seconds++;
	        
	    } catch (StaleElementReferenceException e) {
	        // Handle stale element reference exception
	        System.out.println("Stale element reference exception occurred. Retrying...");
	    }
	}
	    
	


	// After the time is over, you can continue printing the "delivery liters" value
	boolean litersZero = false;

	while (true) {
	    deliverdLitersElement = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span"));
	    String deliveryliters = deliverdLitersElement.getText();
	    System.out.println("Delivered liters (Time is over) at " + Seconds + " Seconds: " + deliveryliters);
	    
	    String numericPart = deliveryliters.split(" ")[0]; // Extracts "502.5" from "502.5 (Liters)"
	    int litersValue = (int) Double.parseDouble(numericPart); // Parse the numeric value
	    
	   // int litersValue = Integer.parseInt(deliveryliters);
	    
	    if (litersValue == 0) {
	        if (!litersZero) {
	            System.out.println("Delivery liters turned to zero");
	            litersZero = true;
	        }
	    } else {
	        litersZero = false; // Reset the flag if liters are not zero
	    }

	    if (System.currentTimeMillis() >= endTime && litersValue == 0) {
	        System.out.println("Time is over and Delivery liters turned to zero");
	        break; // Exit the loop when the time is over and delivery liters are zero
	    }

	    Thread.sleep(1000);
	    Seconds++;
	    }*/
	
  
  

