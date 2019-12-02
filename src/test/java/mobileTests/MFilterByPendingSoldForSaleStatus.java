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


public class MFilterByPendingSoldForSaleStatus extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	
	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(MFilterByPendingSoldForSaleStatus.class);
	
	
	static String className = MFilterByPendingSoldForSaleStatus.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";

	@Test(groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mFilterPending (String searchkeyword) throws IOException, InterruptedException
	{
		AppiumDriver<?> driver = getAppiumDriver();
		MLogin_Page mloginpg = new MLogin_Page();
		mloginpg.allowDeviceLocationAccess(driver);
		MSearch msearch = new MSearch();
		msearch.searchhomes(driver, searchkeyword);
	    MFilterResults_Page mfilterresultspg = new MFilterResults_Page();
	    mfilterresultspg.clickFilterOption(driver);
	    mfilterresultspg.uncheckAllStatus(driver);
	    mfilterresultspg.checkPendingStatus(driver);
	    mfilterresultspg.clickApplyFilter(driver);
		String diditfilter = mfilterresultspg.verifyPendingStatus(driver);

		try{
			AssertJUnit.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by pending sales correctly.", e.getMessage());
			errorname = "didntfilterybypendingcorrectly";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			Assert.fail();
		}


		   softAssert.assertAll();
	}

	@Test(groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mFilterSold (String searchkeyword) throws IOException, InterruptedException
	{
		AppiumDriver<?> driver = getAppiumDriver();
		MLogin_Page mloginpg = new MLogin_Page();
		mloginpg.allowDeviceLocationAccess(driver);
		MSearch msearch = new MSearch();
		msearch.searchhomes(driver, searchkeyword);
	    MFilterResults_Page mfilterresultspg = new MFilterResults_Page();
	    mfilterresultspg.clickFilterOption(driver);
	    mfilterresultspg.uncheckAllStatus(driver);
	    mfilterresultspg.checkSoldStatus(driver);
	    mfilterresultspg.clickApplyFilter(driver);
		String diditfilter = mfilterresultspg.verifySoldStatus(driver);

		try{
			AssertJUnit.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by sold correctly.", e.getMessage());
			errorname = "didntfilterybysoldcorrectly";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			Assert.fail();
		}


		   softAssert.assertAll();
	}

	@Test(groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mFilterForSale (String searchkeyword) throws IOException, InterruptedException
	{
		AppiumDriver<?> driver = getAppiumDriver();
		MLogin_Page mloginpg = new MLogin_Page();
		mloginpg.allowDeviceLocationAccess(driver);
		MSearch msearch = new MSearch();
		msearch.searchhomes(driver, searchkeyword);
	    MFilterResults_Page mfilterresultspg = new MFilterResults_Page();
	    mfilterresultspg.clickFilterOption(driver);
	    mfilterresultspg.uncheckAllStatus(driver);
	    mfilterresultspg.checkForSaleStatus(driver);
	    mfilterresultspg.clickApplyFilter(driver);
		String diditfilter = mfilterresultspg.verifyForSaleStatus(driver);
		
		try{
			AssertJUnit.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by for sale correctly.", e.getMessage());
			errorname = "didntfilterybyforsalecorrectly";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			Assert.fail();
		}


		   softAssert.assertAll();
	}
	
}
