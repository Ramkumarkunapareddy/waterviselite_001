package login_Module;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Based_class
{
	public static String screenShotSubFolderName;
protected static WebDriver driver;
public static ExtentReports er;
public static ExtentTest extentTest;

@BeforeSuite
public void setup()
{
	ExtentSparkReporter esr_All = new ExtentSparkReporter("LoginTest.html");
	esr_All.config().setReportName("All Tests Report");

	
	er = new ExtentReports();
	er.attachReporter(esr_All);
	
	er.setSystemInfo("OS", System.getProperty("os.name"));
	er.setSystemInfo("Java Version", System.getProperty("java.version"));
	
}

@Parameters("browserName")
@BeforeTest
public void SetAllStiff(ITestContext context , @Optional("Chrome") String browserName)
{
	extentTest = er.createTest("Test");
	System.out.println("Browser Name is :"+browserName);
	if(browserName.equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
	}
	else if(browserName.equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
	}
	else if(browserName.equalsIgnoreCase("edge"))
	{
		driver = new EdgeDriver();
	}
	driver.manage().window().maximize();
	Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
	String Device = cap.getBrowserName()+ " "+ cap.getBrowserVersion().subSequence(0, cap.getBrowserVersion().indexOf("."));
	String Author = context.getCurrentXmlTest().getParameter("author");
	extentTest = er.createTest(context.getName());
	extentTest.assignDevice(Device);
	extentTest.assignAuthor(Author);
}

@BeforeMethod
public void launchbrowser()
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
@AfterMethod
public void close(Method m , ITestResult result) throws IOException
{
	if(result.getStatus() == ITestResult.FAILURE)
	{
		String screenshotPath = null;
		screenshotPath = CaptureScreenshot(result.getTestContext().getName()+"_"+result.getMethod().getMethodName()+".jpg");
		extentTest.addScreenCaptureFromPath(screenshotPath);
		extentTest.fail(result.getThrowable());
		
	}
	else if(result.getStatus() == ITestResult.SUCCESS)
	{
		extentTest.pass(m.getName() + "is passed");
		
	}
	
	driver.close();
	
}

@AfterTest
public void qiut()
{
	//driver.close();
}


@AfterSuite
public void GenerateExtentReports() throws IOException
{
	er.flush();
	Desktop.getDesktop().browse(new File("LoginTest.html").toURI());
	
}


public String CaptureScreenshot(String FileName) throws IOException
{
	if(screenShotSubFolderName ==null)
	{
		LocalDateTime myobj = LocalDateTime.now();
		DateTimeFormatter myfarmatobj = DateTimeFormatter.ofPattern("ddMMyyyHHmmss");
		screenShotSubFolderName = myobj.format(myfarmatobj);
	}
	TakesScreenshot ts = (TakesScreenshot)driver;
	File sourceFile = ts.getScreenshotAs(OutputType.FILE);
	File destfile = new File("./screemshots/"+screenShotSubFolderName+"/"+FileName);
	FileUtils.copyFile(sourceFile, destfile);
	System.out.println("Screen shot saves succesfully");
	
	return destfile.getAbsolutePath();
}

public String getDataFromExcel(String Sheet1 , int rowNo , int CellNo) throws IOException
{
	File ExcelFile  = new File("./src/test/resources/Loginpage.xlsx");
	FileInputStream fis = new FileInputStream(ExcelFile);
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	XSSFSheet sheet = wb.createSheet(Sheet1);
	DataFormatter df = new DataFormatter();
	String Data = df.formatCellValue(sheet.getRow(rowNo).getCell(CellNo));
	wb.close();
	return Data;
}
}

