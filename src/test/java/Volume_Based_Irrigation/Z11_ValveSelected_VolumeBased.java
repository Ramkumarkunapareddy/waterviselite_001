package Volume_Based_Irrigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Z11_ValveSelected_VolumeBased  extends irrigation_VolumedIrrigation
{
	@Test(dependsOnMethods = { "Enter_Volume" })
public void Z11_Valve_Selected_VolumeBased()
{
	WebElement Z11ValveCheckBox = driver.findElement(By.xpath("(//label[@class='ng-untouched ng-pristine ng-valid ng-star-inserted'])[11]/input"));
	Z11ValveCheckBox.click();
}
	
}
