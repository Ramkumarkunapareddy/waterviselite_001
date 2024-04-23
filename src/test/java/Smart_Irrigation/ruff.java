package Smart_Irrigation;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Arrays;
//import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
@Test
public class ruff extends Based_class1
{
	protected static WebDriver driver;
	@Test(priority =1)
	public void launchBrowser()
	{
			driver = new ChromeDriver();
			driver.get("https://192.168.0.4:4202/basepath/login");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			WebElement advanced = driver.findElement(By.xpath("//button[@id='details-button']"));
			WebElement link = driver.findElement(By.xpath("//div[@id='details']/p[2]/a"));
			advanced.click();
			link.click();	
			
	}
	@Test(dependsOnMethods = "launchBrowser" ,priority = 2)
	public void login()
	{
		
		WebElement UserName1 = driver.findElement(By.xpath("//input[@name='UserName']"));
		WebElement Pwd1 = driver.findElement(By.xpath("//input[@name='Password']"));
		WebElement sigin1 = driver.findElement(By.xpath("//button[text()='Sign In']"));
		UserName1.sendKeys("Rtu_7");
		Pwd1.sendKeys("Rtu7@123");
		sigin1.click();
		WebElement UserIcon = driver.findElement(By.xpath("//div[@id='navbarSupportedContent']/app-navigation/ul[2]/li/a"));
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		WebElement userprofile = wait.until(ExpectedConditions.visibilityOf(UserIcon));
		assertTrue(userprofile.isDisplayed() , "Userprofile is not displayed");
		WebElement torsture1 = driver.findElement(By.xpath("//div[text()=' Please assign valves to the selected zone ']"));
		WebDriverWait w = new WebDriverWait(driver , Duration.ofSeconds(25));
		w.until(ExpectedConditions.invisibilityOf(torsture1));
		//driver.close();
	}
	@Test(dependsOnMethods = "login" ,priority = 3)
	 public void Irrigationscrren() throws InterruptedException, AWTException 
	    {
		System.out.println("driver"+driver);
	    	WebElement Irrigation_Screen = driver.findElement(By.xpath("(//li[@class='sidebar-item ng-star-inserted'])[7]"));
	    	Irrigation_Screen.click();
	    	try
	    	{
	    		
		    List<WebElement>carddetails = driver.findElements(By.xpath(".//div[@class='card-bx stacked card']/div/div/div/div/h2"));
		    		for(WebElement refname : carddetails)
		    		{
		    			System.out.println(refname.getText());
		    		}
		    		
		    }
			  
	    	catch(Exception e)
	    	{
	    		
	    	System.out.println("no card is there");
	    		
	    	}
	    }
	@Test(dependsOnMethods = "Irrigationscrren" , priority = 4)
	public void MultipleValves() throws AWTException
	{
	    
	    	//WebElement GotoIrrigation_Operations = driver.findElement(By.xpath("//img[@class='mb-3 currency-icon']"));
	    	WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(30));
	    	WebElement GotoIrrigation_Operations = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='mb-3 currency-icon']")));
	    	GotoIrrigation_Operations.click();
	    	GotoIrrigation_Operations.click();
	    	WebElement Smart = driver.findElement(By.xpath("//ul[@class='justify-content-start nav nav-tabs']/li[2]"));
	    	Smart.click();
	    	Select s = new Select(driver.findElement(By.xpath("(//select[@name='Farm'])[2]")));
	    			s.selectByVisibleText("Farm12");
	    			
	    			Select se = new Select(driver.findElement(By.xpath("(//select[@name='Farm'])[3]")));
	    			se.selectByVisibleText("Time Based Schedules");
	    			Actions a = new Actions(driver);
	    			
	    			
	    			WebElement EnterstartTime = driver.findElement(By.xpath("//input[@name='OpenAt']"));
	    			WebElement t = wait.until(ExpectedConditions.visibilityOf(EnterstartTime));
	    			
	    			a.moveToElement(t).click().build().perform();
	    			Robot r = new Robot();
	    			//set time mins grether than current time
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
	    			//set time during mins
	    			String IrrigationTime = "10";
	    			Duration.sendKeys(IrrigationTime);
	    			//WebElement Zone11 = driver.findElement(By.xpath("(//label[@name='Zone'])[11]"));
	    			//Zone11.click();
	    			//WebElement Zone12 = driver.findElement(By.xpath("(//label[@name='Zone'])[12]"));
	    			//Zone12.click();
	    			WebElement Zone14 = driver.findElement(By.xpath("(//label[@name='Zone'])[14]"));
	    			Zone14.click();
	    			 
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
	    			//WebElement torsture11 = driver.findElement(By.xpath("//div[text()=' Z11 schedule created ']"));
	    			
	    		      //wait.until(ExpectedConditions.visibilityOf(torsture11));
	    		     //  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Z11 schedule created ']")));
	    		       
	    		    //   WebElement torsture12 = driver.findElement(By.xpath("//div[text()=' Z12 schedule created ']"));
		    			
		    		 //     wait.until(ExpectedConditions.visibilityOf(torsture12));
		    		      // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Z12 schedule created ']")));
	    		       
		    		       WebElement torsture14 = driver.findElement(By.xpath("//div[text()=' Z14 schedule created ']"));
			    			
			    		      wait.until(ExpectedConditions.visibilityOf(torsture14));
			    		       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Z14 schedule created ']")));
	    			 try {
				    		Thread.sleep(30000);      
		    		       }
		    		       catch(Exception e)
		    		       {
				    		WebElement close = driver.findElement(By.xpath("//button[@class='close']/span"));
			    		
			    			  a.moveToElement(close).click(close).build().perform();
		    		       }
		    			finally
		    			{
		    				WebElement close = driver.findElement(By.xpath("//button[@class='close']/span"));
				    		
			    			  a.moveToElement(close).click(close).build().perform();
		    		       
		    			}
	}
	
	            //  @Test(dependsOnMethods = "MultipleValves" , priority =5)
	    			public void AfterMultipleValves_IrrigationScreen()
	    			{
	    				
	    		      
	    		       
	            	  WebElement Irrigation_Screen = driver.findElement(By.xpath("(//li[@class='sidebar-item ng-star-inserted'])[7]"));
	      	    	Irrigation_Screen.click();
	      	    	try
	      	    	{
	      	    		
	      		    List<WebElement>carddetails = driver.findElements(By.xpath(".//div[@class='card-bx stacked card']/div/div/div/div/h2"));
	      		    		for(WebElement refname : carddetails)
	      		    		{
	      		    			System.out.println(refname.getText());
	      		    		}
	      		    		
	      		    }
	      			  
	      	    	catch(Exception e)
	      	    	{
	      	    		
	      	    	System.out.println("no card is there");
	      	    		
	      	    	}
	    }
	    		    	
	    		 @Test(dependsOnMethods = "MultipleValves" ,priority = 5)
	    		  public void DuringIrrigation() throws InterruptedException, AWTException
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
	    			WebElement z14icon = driver.findElement(By.xpath("(//div[@class='thumbnail'])[14]/img"));
	    			z14icon.click();
	    			JavascriptExecutor jse = (JavascriptExecutor)driver;
	    			jse.executeScript("window.scrollBy(0,700);");
	    			
	    		//here waiting for response
	    				Thread.sleep(150000);
	    			
	    			
	    			WebElement deliverdLitersElement = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span"));
	    			String liters = deliverdLitersElement.getText();
	    			System.out.println(liters);
	    		Robot r = new Robot();
	    		r.keyPress(KeyEvent.VK_PAGE_UP);
	    		r.keyRelease(KeyEvent.VK_PAGE_UP);
	    		//WebElement DuringIrrigation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@src='../../assets/new_assets/images/addfarmanimations.gif']")));
	    		WebElement DuringIrrigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@class='ng-star-inserted'])[14]")));
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
	    		        DuringIrrigation = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@class='ng-star-inserted'])[14]")));

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

	    		        if (Seconds % 10 == 0) {
	    		            // Click the zone icon every 10 seconds
	    		            jse.executeScript("window.scrollTo(0, 0);");
	    		            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
	    		            WebElement duringIrrigationElement = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@class='ng-star-inserted'])[14]")));
	    		            duringIrrigationElement.click();
	    		            System.out.println("Clicked on zone icon at " + Seconds + " Seconds");
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

	    		
	    		  @Test(priority =6 , dependsOnMethods = "AfterMultipleValves_IrrigationScreen")
	    		  public void Multiple_ValvesIrrigation() throws InterruptedException
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
	    			  WebElement Zone11 = driver.findElement(By.xpath("(//img[@class='ng-star-inserted'])[11]"));
	    			  WebElement zone12 = driver.findElement(By.xpath("(//img[@class='ng-star-inserted'])[12]"));
	    			  WebElement zone14 = driver.findElement(By.xpath("(//img[@class='ng-star-inserted'])[14]"));
	    			  List<WebElement>ValvesMoniter = Arrays.asList(Zone11 , zone12 , zone14);
	    			  int DurationTime = 10;
	    			  long endingTime = System.currentTimeMillis() + DurationTime * 60 * 1000;
	    			  int Seconds = 0;
	    			  while(System.currentTimeMillis() < endingTime)
	    			  {
	    				  for(WebElement valve :ValvesMoniter )
	    				  {
	    					 valve.click();
	    					 JavascriptExecutor jse = (JavascriptExecutor)driver;
	    		    		jse.executeScript("window.scrollBy(0,700);");
	    		    		Thread.sleep(2000);
	    		    		WebElement deliverdLitersElement = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span"));
	    		    		String liters = deliverdLitersElement.getText();
	    		    		System.out.println("valve :"+valve.getAttribute("id")+",Delivery liters at "+Seconds + "Secomds :"+liters);
	    		    		
	    				  }
	    				  Thread.sleep(1000);
	    				  Seconds++;
	    			  }
	    			  
	    			  
	    			  
	    		  }
	    
	    		  
	    		    	//@Test(priority = 5)
	    		    	public void AfterIrrigation()
	    		    	{
	    		    		WebElement Irrigation_Screen = driver.findElement(By.xpath("(//li[@class='sidebar-item ng-star-inserted'])[7]"));
	    			    	Irrigation_Screen.click();
	    			    	WebElement AfterIrrigation_Status = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between'])[3]/h2"));
	    			    	String status = AfterIrrigation_Status.getText();
	    			    	System.out.println(status);
	    		    	}
	    	}
	    
	
	
	    

/* while (System.currentTimeMillis() < endTime || DuringIrrigation.getAttribute("class").contains("../../assets/new_assets/images/addfarmanimations.gif"))
		    		{
		    			
	    		        jse.executeScript("window.scrollTo(0, 700);");
		    		    deliverdLitersElement = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between mb-2 align-items-center'])[3]/div[2]/h5/span"));
		    		    DuringIrrigation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@class='ng-star-inserted'])[11]")));
		    		    try
		    		    {
		    		    	 WebElement ls = wait.until(ExpectedConditions.visibilityOf(deliverdLitersElement));
				    		    String deliveryliters = ls.getText();
				    		    System.out.println("Delivered liters at " + Seconds + " Seconds: " + deliveryliters);
		    		    }
		    		    catch(StaleElementReferenceException e)
		    		    {
		    		    	 WebElement ls = wait.until(ExpectedConditions.visibilityOf(deliverdLitersElement));
				    		    String deliveryliters = ls.getText();
				    		    System.out.println("Delivered liters at " + Seconds + " Seconds: " + deliveryliters);
		    		    }
		    		   
	                   
	                       
		    		    Thread.sleep(1000);
		    		    Seconds++;
		    		    if (Seconds % 10 == 0) {
		                    // Click the zone icon every 10 seconds
		    		    	
		    		        jse.executeScript("window.scrollTo(0, 0);");
		    		        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
		    		        WebElement duringIrrigationElement = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@class='ng-star-inserted'])[11]")));
		    		        duringIrrigationElement.click();
		                    System.out.println("Clicked on zone icon at " + Seconds + " Seconds");
		                }
		    		}  */
