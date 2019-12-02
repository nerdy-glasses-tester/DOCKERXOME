package webTests;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.TestBase;
import common.ScreenshotURL;
import webPageObjects.Login_Page;
import webPageObjects.Search;

public class Login_and_Search extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	
	//Login and search by city, browse to the 12th house and click on pic, then verify address on details page and scroll through pics in details page

	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(Login_and_Search.class);
	
	
	static String className = Login_and_Search.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";

	@Parameters({"browser", "platform"})	
	@Test(groups= {"smoke", "regression"}, dataProvider = "getData") 
	public void loginSearch (String login, String password, String searchkeyword) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		Login_Page loginpg = new Login_Page();
		loginpg.clickSignIn(driver);
		String signedinusername = loginpg.login(driver, login, password);
		
		try{
			Assert.assertEquals(signedinusername, "AUTOMATION TESTER");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't login as AUTOMATION TESTER.", e.getMessage());
			errorname = "didntloginasAUTOMATIONTESTER";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
		
		Search search = new Search();
		boolean match = search.loginsearchfor6thhouseinresults(driver, searchkeyword);
	
		
		try{
			Assert.assertEquals(match,  true);
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't scroll down the list of search results.", e.getMessage());
			errorname = "didntscrollsearchresults";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
		
		   softAssert.assertAll();
	}

}
