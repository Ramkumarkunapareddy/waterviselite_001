package TimeBased_TwoValves_at_same_Time;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



public class Z12_Z13_DeliveredLiters_TimeBased extends  Z12_Z13_Valves_TimeBased
{
	@Test(dependsOnMethods = {"Z12_Z13_Valves"})
public void DeliverdLiters_Z12_Z13_At_aTime() throws InterruptedException
{
	
		
		WebElement DashBoard = driver.findElement(By.xpath("(//li[@class='ng-star-inserted'])[1]"));
		DashBoard.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Please assign valves to the selected zone ']")));
		WebElement cursor = driver.findElement(By.xpath("//span[@class='carousel-control-next-icon']"));
		WebElement prevCursor = driver.findElement(By.xpath("//span[@class='carousel-control-prev-icon']"));
		for (int i = 0; i < 2; i++) {
		    cursor.click();
		}
		WebElement z12icon = driver.findElement(By.xpath("(//div[@class='thumbnail'])[12]/img"));
		z12icon.click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)");
		Thread.sleep(120000);
		WebElement deliverdLitersElement = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span"));
		String liters = deliverdLitersElement.getText();
		System.out.println(liters);
		//WebElement DuringIrrigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='thumbnail'])[12]/img")));
		jse.executeScript("window.scrollTo(0, 0)");
		int minutestoRun = 6;
		long endIrrigationTime = System.currentTimeMillis() + (minutestoRun * 60 * 1000);
		int Seconds = 0;
		//int maxSecondsPerzone = 25;
		boolean zone12Active = true;
		while (System.currentTimeMillis() <= endIrrigationTime) {
		    try {
		        if (zone12Active) {
		            WebElement Zone12 = driver.findElement(By.xpath("(//div[@class='thumbnail'])[12]/img"));
		            Zone12.click();
		        } else {
		            WebElement Zone13 = driver.findElement(By.xpath("(//div[@class='thumbnail'])[13]/img"));
		            Zone13.click();
		        }
		    } catch (StaleElementReferenceException e) {
		        System.out.println("StaleElementException");
		        continue;
		    }

		    for (int i = 0; i < 20  ; i++) {
		        try {
		            jse.executeScript("window.scrollTo(0, 700);");
		            WebElement deliveredLitersElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span")));
		            String deliveryLiters = deliveredLitersElement.getText();
		            System.out.println("Delivered liters at Zone " + (zone12Active ? "12" : "13") + " " + Seconds + " Seconds: " + deliveryLiters);
		           
		            jse.executeScript("window.scrollTo(0, 0)");
		            
		        } catch (Exception e) {
		            System.out.println("Stale element Exception retrying");
		            continue;
		        }
		    
		        Thread.sleep(1000);
		        Seconds++;
		    }
		        if (zone12Active) {
		            cursor.click();
		        } else {
		            prevCursor.click();
		        }
		      //  jse.executeScript("window.scrollTo(0, 0)");
		        zone12Active = !zone12Active;
		    }
	}		
}
