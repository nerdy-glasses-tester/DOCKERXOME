package mobileTests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.TestBase;
import common.ScreenshotURL;
import io.appium.java_client.AppiumDriver;
import mobilePageObjects.MLogin_Page;
import mobilePageObjects.MSearch;


public class MLoginandSearch extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	
	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(MLoginandSearch.class);
	
	
	static String className = MLoginandSearch.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";

	@Test(groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mobileLoginAndSearch (String login, String password, String searchkeyword) throws IOException, InterruptedException
	{
		AppiumDriver<?> driver = getAppiumDriver();
		MLogin_Page mloginpg = new MLogin_Page();
		mloginpg.allowDeviceLocationAccess(driver);
		mloginpg.mobileLogin(driver, login, password, searchkeyword);
		MSearch msearch = new MSearch();
		msearch.clickSearchtoDismissLeftMenu(driver);
		msearch.searchhomes(driver, searchkeyword);
		String diditfilter = msearch.verifySearchResults(driver, searchkeyword);

		try{
			AssertJUnit.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't search correctly.", e.getMessage());
			errorname = "didntsearchcorrectly";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			Assert.fail();
		}


		   softAssert.assertAll();
	}

}
