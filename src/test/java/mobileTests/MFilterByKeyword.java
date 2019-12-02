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
import mobilePageObjects.MFilterResults_Page;
import mobilePageObjects.MLogin_Page;
import mobilePageObjects.MSearch;

public class MFilterByKeyword  extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	
	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(MFilterByKeyword.class);
	
	static String className = MFilterByKeyword.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";

	@Test(groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mFilterKeyword (String searchkeyword, String keyword) throws IOException, InterruptedException
	{
		AppiumDriver<?> driver = getAppiumDriver();
		MLogin_Page mloginpg = new MLogin_Page();
		mloginpg.allowDeviceLocationAccess(driver);
		MSearch msearch = new MSearch();
		msearch.searchhomes(driver, searchkeyword);
	    MFilterResults_Page mfilterresultspg = new MFilterResults_Page();
	    mfilterresultspg.clickFilterOption(driver);
	    mfilterresultspg.clickFilterByKeyword(driver, keyword); 
	    mfilterresultspg.clickApplyFilter(driver);
	    Thread.sleep(4000);
		String diditfilter = mfilterresultspg.verifyFilterByKeyword(driver, keyword);

		try{
			AssertJUnit.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by keyword correctly.", e.getMessage());
			errorname = "didntfilterybykeywordcorrectly";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			Assert.fail();
		}


		   softAssert.assertAll();
	}


}
