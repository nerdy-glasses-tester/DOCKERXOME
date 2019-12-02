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

public class Login_NegativeTests extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//

	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(Login_NegativeTests.class);
	
	
	static String className = Login_NegativeTests.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";

	@Parameters({"browser", "platform"})
	@Test(groups="P3", dataProvider = "getData")
	public void loginBlankFields (String login, String password) throws IOException, InterruptedException
	{
	   RemoteWebDriver driver = getDriver();
	   Login_Page loginpg = new Login_Page();
	   loginpg.clickSignIn(driver);
	   String emailisrequired1 = loginpg.signinBlankFields(driver);
	   
		try
		{
			   Assert.assertEquals(emailisrequired1, "true");
		}
		catch(AssertionError e)
		{ 
			log.error("Didn't get required error message tip when signin with blank fields.", e.getMessage());
			errorname = "norequirederrmsgsigninwithblankfields";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
	 
	   
	   softAssert.assertAll();
	   
	}

	
	@Parameters({"browser", "platform"})
	@Test(groups="regression", dataProvider = "getData")
	public void loginWrongPwd (String login, String password) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		Login_Page loginpg = new Login_Page();
		loginpg.clickSignIn(driver);
	    String wrongpwdtextresults = loginpg.signinWrongPwd(driver, login, password);
	    String wrongpwdtext = "Oops, the e-mail or password doesn't match.";
	 
		try
		{
			log.info(wrongpwdtextresults+" is displayed. We are looking for text: "+wrongpwdtext);
			Assert.assertEquals(wrongpwdtextresults, wrongpwdtext);
		}
		catch(AssertionError e)
		{ 
			log.error("Signin with wrong password text has issues.", e.getMessage());
			errorname = "signinwithwrongpwdissue";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
	
		   softAssert.assertAll();
	}

	
}
