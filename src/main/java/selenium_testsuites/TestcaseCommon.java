package selenium_testsuites;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public class TestcaseCommon {
    public WebDriver driver;
    private static final String URL = "https://www.vodafone.com.mt/home";
    private static final String DRIVER_PATH = "lib\\chromedriver.exe";
    private static final int TIMEOUT = 20000;
    
    @BeforeClass
    public void setupTestsuite (){
    	System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        //Create a new ChromeDriver
        driver = new ChromeDriver();
    }
    
    @BeforeMethod
    public void setupTest (){
        driver.navigate().to(URL);
        driver.manage().window().maximize();
    }	    
    
    @AfterMethod
    public void teardownTest (){
        //Close browser and end the session
        driver.quit();
    }
    
    public void sleep(int miliseconds) {
    	try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void login(String username, String password) {
    	String selector = ".homepage-login .fm-data input";
    	waitElementPresence(selector);
        List<WebElement> elem = driver.findElements(By.cssSelector(selector));
        elem.get(0).sendKeys(username);
        elem.get(1).sendKeys(password);
    	       
    	
    	WebElement btnElement = waitElementPresence(".fm-submit");
    	btnElement.click();
    }             
    
    public String[] getAccountBallance() {
    	WebElement elem = waitElementPresence(".prepaidLandingInflate p");
    	WebElement elemCurrency = waitElementPresence(".prepaidLandingInflate p span");
    	return new String[]{ elem.getText(), elemCurrency.getText() };
    }
   
    public WebElement waitElementPresence(String selector) {
    	WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
    	
    	return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
    }
}
