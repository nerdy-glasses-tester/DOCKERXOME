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

public class MFilterByBedBath extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	
	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(MFilterByBedBath.class);
	
	
	static String className = MFilterByBedBath.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";

	@Test(groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mFilterBed (String searchkeyword, String beds) throws IOException, InterruptedException
	{
		AppiumDriver<?> driver = getAppiumDriver();
		
		MLogin_Page mloginpg = new MLogin_Page();
		mloginpg.allowDeviceLocationAccess(driver);
		MSearch msearch = new MSearch();
		msearch.searchhomes(driver, searchkeyword);
	    MFilterResults_Page mfilterresultspg = new MFilterResults_Page();
	    mfilterresultspg.clickFilterOption(driver);
	    mfilterresultspg.clickFilterByBed(driver, beds);
	    mfilterresultspg.clickApplyFilter(driver);
		String diditfilter = mfilterresultspg.verifyFilterByBeds(driver, beds);

		try{
			AssertJUnit.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by beds correctly.", e.getMessage());
			errorname = "didntfilterybybedscorrectly";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			Assert.fail();
		}


		   softAssert.assertAll();
	}

	
	@Test(groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mFilterBath (String searchkeyword, String baths) throws IOException, InterruptedException
	{
		AppiumDriver<?> driver = getAppiumDriver();
		MLogin_Page mloginpg = new MLogin_Page();
		mloginpg.allowDeviceLocationAccess(driver);
		MSearch msearch = new MSearch();
		msearch.searchhomes(driver, searchkeyword);
	    MFilterResults_Page mfilterresultspg = new MFilterResults_Page();
	    mfilterresultspg.clickFilterOption(driver);
	    mfilterresultspg.clickFilterByBath(driver, baths);
	    mfilterresultspg.clickApplyFilter(driver);
	    String diditfilter = mfilterresultspg.verifyFilterByBaths(driver, baths);

		try{
			AssertJUnit.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by baths correctly.", e.getMessage());
			errorname = "didntfilterybybathscorrectly";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			Assert.fail();
		}


		   softAssert.assertAll();
	}


}
