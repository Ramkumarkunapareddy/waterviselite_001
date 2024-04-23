package Volume_Based_Irrigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Z12_ValveSelected_VolumeBased extends irrigation_VolumedIrrigation 
{
	@Test(dependsOnMethods = { "Enter_Volume" })
   public void Z12_Valve_Selected_VolumeBased()
   {
		WebElement Z12ValveCheckBox = driver.findElement(By.xpath("(//label[@class='ng-untouched ng-pristine ng-valid ng-star-inserted'])[12]/input"));
		Z12ValveCheckBox.click();
	}
}
