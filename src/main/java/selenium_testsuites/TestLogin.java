package selenium_testsuites;

import org.testng.Assert;
import org.testng.annotations.Test;


public class TestLogin extends TestcaseCommon {
	
    @Test
    public void LoginToMainMenu_Test01 () {
    	//Login
    	login("testqa2", "Voda1234");
    	
    	//Check landing page
    	Assert.assertEquals(driver.getCurrentUrl(), "https://www.vodafone.com.mt/myVodafone-postpaid");
    	
    	//Check pending account balance
    	String[] balance = getAccountBallance();
    	Assert.assertTrue(balance[0].contains("My Pending Balance is :"));
    	Assert.assertTrue(balance[1].contains("€"));
    	Assert.assertTrue(Float.valueOf(balance[1].substring(1)) instanceof Float);
    }

}

 