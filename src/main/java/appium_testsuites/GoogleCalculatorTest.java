package appium_testsuites;

import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;


public class GoogleCalculatorTest {
	AppiumDriver<WebElement> driver;
	
    @BeforeTest
    public void setup() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", "Android");
        cap.setCapability("deviceName", "emulator-5554");
        cap.setCapability("appPackage", "com.android.calculator2");;
        cap.setCapability("appActivity", "com.android.calculator2.Calculator");
        cap.setCapability("appWaitPackage", "com.android.calculator2");
        cap.setCapability("appWaitActivity", "com.android.calculator2.Calculator");
        cap.setCapability("clearSystemFiles", "true");
        cap.setCapability("adbExecTimeout", "60000");
        driver = new AndroidDriver<WebElement>(new URL("http://localhost:4723/wd/hub"), cap);
	}

	@Test
	public void addition_test01() {
		//Input 1, +, 1, =
		findElementByXPATH("//android.widget.LinearLayout[3]/android.widget.Button[1]");
		findElementByID("plus");
		findElementByXPATH("//android.widget.LinearLayout[3]/android.widget.Button[1]");
		findElementByID("equals");
		
		//Check result is 2
		Assert.assertEquals("2", getDisplayedValue());
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
    public void sleep(int miliseconds) {
    	try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
	
    public void findElementByID(String elemId) {
    	WebElement elem = driver.findElementByAccessibilityId(elemId);
    	elem.click();
    } 	

    public void findElementByXPATH(String elemId) {
    	WebElement elem = driver.findElementByXPath(elemId);
    	elem.click();
    } 	    
    
    public String getDisplayedValue() {
    	WebElement elem = driver.findElementByXPath("//android.widget.ViewSwitcher/android.widget.EditText");
    	return elem.getText();
    }   
}
