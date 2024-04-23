package Smart_Irrigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class AfterIrrigation_Statuscard extends DeliveryLiters_TimeBased
{
@Test(priority = 5)
public void z11_afterIrrigationStatuscard()
{
	WebElement Irrigation_Screen = driver.findElement(By.xpath("(//li[@class='sidebar-item ng-star-inserted'])[7]"));
	Irrigation_Screen.click();
	WebElement AfterIrrigation_Status = driver.findElement(By.xpath("(//div[@class='d-flex justify-content-between'])[3]/h2"));
	String status = AfterIrrigation_Status.getText();
	System.out.println(status);
}
}
