package TimeBased_TwoValves_at_same_Time;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Z13_Z14_DeliveryLiters_TimeBased extends Z13_Z14_Valves_TimeBased
{
	@Test(dependsOnMethods = { "Z13_Z14_Valves_At_a_Time" } )
    public void Deliverd_Liters_Zone13_Zone14_SameTime()
    {
		WebElement DashBoard = driver.findElement(By.xpath("(//li[@class='ng-star-inserted'])[1]"));
		DashBoard.click();
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Please assign valves to the selected zone ']")));
		WebElement cursor = driver.findElement(By.xpath("//span[@class='carousel-control-next-icon']"));
		for(int i=0;i<3;i++)
		{
			cursor.click();
		}
		WebElement z13icon = driver.findElement(By.xpath("(//div[@class='thumbnail'])[13]/img"));
		z13icon.click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,700);");
		
		//Thread.sleep(120000);
		
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
        boolean zone14 = false; // Start with Zone 13
        while(endTime > System.currentTimeMillis() )
        {
        	String ZonePath = zone14 ? "(//img[@class='ng-star-inserted'])[14]" : "(//img[@class='ng-star-inserted'])[13]" ;
        	WebElement ZoneIcon = null;
        	try {
        	ZoneIcon = driver.findElement(By.xpath(ZonePath));
        	ZoneIcon.click();
        	}
        	catch(StaleElementReferenceException e)
        	{
        		System.out.println("stale Element Exception REtrying");
        		continue;
        	}
        	//print the delivery liters on every seconds for 10 seconds
        	
        	for(int i=0 ; i<10 ; i++)
        	{
        		
        	try {
        		jse.executeScript("window.scrollBy(0,700);");
        		 WebElement deliveredLitersElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span")));
                 String deliveryLiters = deliveredLitersElement.getText();
                 System.out.println("DeliverdLIters At zone " + (zone14 ? "Zone 14" : " Zone 13") + " " + seconds + "Seconds "  + deliveryLiters );
        	}
        	catch(StaleElementReferenceException e)
        	{
        		System.out.println("Stale Element Retrying");
        		continue;
        	}
        	Thread.sleep(1000);
        	seconds++;
        }
        	jse.executeScript("window.scrollBy(0,0);");
        	 // Switch the Zones
        	zone14 = !zone14;
        }
	 }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        
	
	
	
    
}
}
