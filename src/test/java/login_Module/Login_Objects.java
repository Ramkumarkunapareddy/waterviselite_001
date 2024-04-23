package login_Module;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Objects
{
	protected WebDriver driver;
public Login_Objects(WebDriver driver)
{
	this.driver = driver;
	PageFactory.initElements(driver, this);
}
@FindBy(xpath = "//div[@class='row no-padding']/div/div/div/span/img")
WebElement logincon;

public WebElement Logicon()
{
	return logincon;
}

@FindBy(xpath = "//input[@name='UserName']")
WebElement Username;
public WebElement Uname()
{
	return Username;
}
@FindBy(xpath = "//input[@name='Password']")
WebElement Password;
public WebElement pwd()
{
	return Password;
}

@FindBy(xpath = "//button[text()='Sign In']")
WebElement SignIN;
public WebElement Sigin()
{
	return SignIN;
}

@FindBy(xpath = "(//div[@class='form-group text-center'])[2]/p")
WebElement DoyouhaveAccount;
public WebElement Do_tou_haveAccountTag()
{
return DoyouhaveAccount;
}

@FindBy(xpath = "//div[@id='navbarSupportedContent']/app-navigation/ul[2]/li/a")
WebElement UserIcon;
public WebElement UIcon()
{
	return UserIcon;
}

@FindBy(xpath = "//div[text()=' Invalid Username or Password ! ']")
WebElement Invalidmsg_toasture;
public WebElement Toastmsg()
{
	return Invalidmsg_toasture;
}

@FindBy(xpath = "//div[text()=' Please assign valves to the selected zone ']")
WebElement NoValvesToastur;
public WebElement ValveToast()
{
	return NoValvesToastur;
}

@FindBy(xpath = "//a[@class='dropdown-toggle nav-link text-muted waves-effect waves-dark pro-pic img-line-height']")
WebElement Userr_icons;
public WebElement Icons()
{
	return  Userr_icons;
}
}
