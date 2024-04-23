package Smart_Irrigation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Click_on_Zone12_During_Time_Based_Irrigation extends Z12_Valve_Select
{
	@Test(priority = 4 , dependsOnMethods = { "SelectZ12_valve_TimeBased" })
    public void click_Zone12_During_SingleValve_Irrigation() throws AWTException, InterruptedException
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
		WebElement z12icon = driver.findElement(By.xpath("(//div[@class='thumbnail'])[12]/img"));
		z12icon.click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,700);");
		
	//here waiting for response
			try {
				Thread.sleep(150000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		WebElement deliverdLitersElement = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span"));
		String liters = deliverdLitersElement.getText();
		System.out.println(liters);
	Robot r = new Robot();
	r.keyPress(KeyEvent.VK_PAGE_UP);
	r.keyRelease(KeyEvent.VK_PAGE_UP);
	//WebElement DuringIrrigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@src='../../assets/new_assets/images/addfarmanimations.gif']")));
	WebElement DuringIrrigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@class='ng-star-inserted'])[12]")));
	r.keyPress(KeyEvent.VK_PAGE_DOWN);
	r.keyRelease(KeyEvent.VK_PAGE_DOWN);
	int minutestoRun = 10;
	long endTime = System.currentTimeMillis() + minutestoRun * 60 * 1000;//src="../../assets/new_assets/images/addfarmanimations.gif"
	int Seconds = 0;
	WebElement deliverdLitersElement1 = null;

	while (System.currentTimeMillis() < endTime || DuringIrrigation.getAttribute("class").contains("../../assets/new_assets/images/addfarmanimations.gif")) {
	    try {
	        // Scroll to the element
	        jse.executeScript("window.scrollTo(0, 700);");

	        // Locate the deliverdLitersElement
	        deliverdLitersElement1 = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span"));

	        // Wait for DuringIrrigation element and reassign
	        WebDriverWait wait1 = new WebDriverWait(driver , Duration.ofSeconds(25));
	        DuringIrrigation = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@class='ng-star-inserted'])[12]")));

	        // Try to get and print the delivery liters
	        try {
	            WebElement ls = wait.until(ExpectedConditions.visibilityOf(deliverdLitersElement1));
	            String deliveryliters = ls.getText();
	            System.out.println("Delivered liters at " + Seconds + " Seconds: " + deliveryliters);
	        } catch (StaleElementReferenceException e) {
	            WebElement ls = wait.until(ExpectedConditions.visibilityOf(deliverdLitersElement1));
	            String deliveryliters = ls.getText();
	            System.out.println("Delivered liters at " + Seconds + " Seconds: " + deliveryliters);
	        }

	        // Rest of your code for the loop
	        Thread.sleep(1000);
	        Seconds++;

	        if (Seconds % 25 == 0) {
	            // Click the zone icon every 10 seconds
	            jse.executeScript("window.scrollTo(0, 0);");
	            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
	            WebElement duringIrrigationElement = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@class='ng-star-inserted'])[12]")));
	            duringIrrigationElement.click();
	            System.out.println("Clicked on zone 12 icon at " + Seconds + " Seconds");
	        }
	    } catch (StaleElementReferenceException e) {
	        // Handle stale element reference exception
	        System.out.println("Stale element reference exception occurred. Retrying...");
	    }
	}



	// After the time is over, you can continue printing the "delivery liters" value
	boolean litersZero = false;

	while (true) {
	    deliverdLitersElement1 = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span"));
	    String deliveryliters = deliverdLitersElement1.getText();
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
	    }
	

    }
}


    


