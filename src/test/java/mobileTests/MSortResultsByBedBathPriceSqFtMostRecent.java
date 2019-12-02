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
import mobilePageObjects.MSortResults_Page;


public class MSortResultsByBedBathPriceSqFtMostRecent extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	
	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(MSortResultsByBedBathPriceSqFtMostRecent.class);
	
	
	static String className = MSortResultsByBedBathPriceSqFtMostRecent.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";

	@Test(priority=1, groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mSortByBed (String searchkeyword) throws IOException, InterruptedException
	{
		AppiumDriver<?> driver = getAppiumDriver();
		Thread.sleep(4000);
		MLogin_Page mloginpg = new MLogin_Page();
		mloginpg.allowDeviceLocationAccess(driver);
		MSearch msearch = new MSearch();
		msearch.searchhomes(driver, searchkeyword);
		MFilterResults_Page mfilterresultspg = new MFilterResults_Page();
		mfilterresultspg.firstOpenAppFilterBySingleFamilyTownHomeCondo(driver);
		Thread.sleep(4000);
		MSortResults_Page msortresultspg = new MSortResults_Page();
		String diditsort = msortresultspg.sortAndVerifyByBeds(driver);

		try{
			AssertJUnit.assertEquals(diditsort, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't sort by beds correctly.", e.getMessage());
			errorname = "didntsortybybedscorrectly";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			Assert.fail();
		}


		   softAssert.assertAll();
	}
	


	@Test(priority=2, groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mSortByBath (String searchkeyword) throws IOException, InterruptedException
	{
		AppiumDriver<?> driver = getAppiumDriver();
		Thread.sleep(4000);
		MLogin_Page mloginpg = new MLogin_Page();
		mloginpg.allowDeviceLocationAccess(driver);
		MSearch msearch = new MSearch();
		msearch.searchhomes(driver, searchkeyword);
		MFilterResults_Page mfilterresultspg = new MFilterResults_Page();
		mfilterresultspg.firstOpenAppFilterBySingleFamilyTownHomeCondo(driver);
		Thread.sleep(4000);
		MSortResults_Page msortresultspg = new MSortResults_Page();
		String diditsort = msortresultspg.sortAndVerifyByBaths(driver);


		try{
			AssertJUnit.assertEquals(diditsort, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't sort by baths correctly.", e.getMessage());
			errorname = "didntsortbybathscorrectly";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			Assert.fail();
		}


		   softAssert.assertAll();
	}
	
	
	@Test(priority=3, groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mSortByLowtoHighPrice (String searchkeyword) throws IOException, InterruptedException
	{
		AppiumDriver<?> driver = getAppiumDriver();
		Thread.sleep(4000);
		MLogin_Page mloginpg = new MLogin_Page();
		mloginpg.allowDeviceLocationAccess(driver);
		MSearch msearch = new MSearch();
		msearch.searchhomes(driver, searchkeyword);
		MFilterResults_Page mfilterresultspg = new MFilterResults_Page();
		mfilterresultspg.firstOpenAppFilterBySingleFamilyTownHomeCondo(driver);
		Thread.sleep(4000);
		MSortResults_Page msortresultspg = new MSortResults_Page();
		msortresultspg.sortByLowtoHighPrice(driver);
		String diditsort = msortresultspg.verifySortByLowtoHighPrice(driver);

		try{
			AssertJUnit.assertEquals(diditsort, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't sort by low to high price correctly.", e.getMessage());
			errorname = "didntsortbylowtohighpricecorrectly";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			Assert.fail();
		}


		   softAssert.assertAll();
	}


	
	@Test(priority=4, groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mSortByHightoLowPrice (String searchkeyword) throws IOException, InterruptedException
	{
		AppiumDriver<?> driver = getAppiumDriver();
		Thread.sleep(4000);
		MLogin_Page mloginpg = new MLogin_Page();
		mloginpg.allowDeviceLocationAccess(driver);
		MSearch msearch = new MSearch();
		msearch.searchhomes(driver, searchkeyword);
		MFilterResults_Page mfilterresultspg = new MFilterResults_Page();
		mfilterresultspg.firstOpenAppFilterBySingleFamilyTownHomeCondo(driver);
		Thread.sleep(4000);
		MSortResults_Page msortresultspg = new MSortResults_Page();
		msortresultspg.sortByHightoLowPrice(driver);
		String diditsort = msortresultspg.verifySortByHightoLowPrice(driver);

		try{
			AssertJUnit.assertEquals(diditsort, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't sort by high to low price correctly.", e.getMessage());
			errorname = "didntsortbyhightolowpricecorrectly";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			Assert.fail();
		}


		   softAssert.assertAll();
	}


	@Test(priority=5, groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mSortBySqFt (String searchkeyword) throws IOException, InterruptedException
	{
		AppiumDriver<?> driver = getAppiumDriver();
		Thread.sleep(4000);
		MLogin_Page mloginpg = new MLogin_Page();
		mloginpg.allowDeviceLocationAccess(driver);
		MSearch msearch = new MSearch();
		msearch.searchhomes(driver, searchkeyword);
		MFilterResults_Page mfilterresultspg = new MFilterResults_Page();
		mfilterresultspg.firstOpenAppFilterBySingleFamilyTownHomeCondo(driver);
		Thread.sleep(4000);
		MSortResults_Page msortresultspg = new MSortResults_Page();
		msortresultspg.sortBySqFt(driver);
		String diditsort = msortresultspg.verifySortBySquareFeet(driver);

		try{
			AssertJUnit.assertEquals(diditsort, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't sort by square feet correctly.", e.getMessage());
			errorname = "didntsortbysqftcorrectly";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			Assert.fail();
		}


		   softAssert.assertAll();
	}
	

	
	@Test(priority=6, groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mSortByMostRecent (String searchkeyword) throws IOException, InterruptedException
	{
		AppiumDriver<?> driver = getAppiumDriver();
		Thread.sleep(4000);
		MLogin_Page mloginpg = new MLogin_Page();
		mloginpg.allowDeviceLocationAccess(driver);
		MSearch msearch = new MSearch();
		msearch.searchhomes(driver, searchkeyword);
		MFilterResults_Page mfilterresultspg = new MFilterResults_Page();
		mfilterresultspg.firstOpenAppFilterBySingleFamilyTownHomeCondo(driver);
		Thread.sleep(4000);
		MSortResults_Page msortresultspg = new MSortResults_Page();
		msortresultspg.sortByHightoLowPrice(driver);
		Thread.sleep(4000);
		msortresultspg.sortByMostRecent(driver);
		String diditsort = msortresultspg.verifySortByMostRecent(driver);

		try{
			AssertJUnit.assertEquals(diditsort, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't sort by most recent correctly.", e.getMessage());
			errorname = "didntsortbymostrecentcorrectly";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			Assert.fail();
		}


		   softAssert.assertAll();
	}


}
