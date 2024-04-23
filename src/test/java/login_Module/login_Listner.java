package login_Module;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class login_Listner extends Based_class implements ITestListener
{

	public void onTestStart(ITestResult result) {
		System.out.println("on Start Start");
		
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("on Test Success");
		
	}

	public void onTestFailure(ITestResult result) {
		try {
			CaptureScreenshot(result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("on Test skipped");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("on Test failed with Success ");
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("on Test failed with Timeout ");
		
	}

	public void onStart(ITestContext context) {
		System.out.println("on stat");
	
	}

	public void onFinish(ITestContext context) {
		System.out.println("on finish");
	}

}
