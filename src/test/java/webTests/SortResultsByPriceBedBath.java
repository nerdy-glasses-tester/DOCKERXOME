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
import webPageObjects.Search;
import webPageObjects.SortResults_Page;

public class SortResultsByPriceBedBath extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	
	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(SortResultsByPriceBedBath.class);
	
	
	static String className = SortResultsByPriceBedBath.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";
	
	
	@Parameters({"browser", "platform"})
	@Test(groups= {"smoke", "regression"}, dataProvider = "getData") 
	public void sortResultsByMostRecent (String searchkeyword) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		Search search = new Search();
		search.searchByCity(driver, searchkeyword);
	
		SortResults_Page sortresultspg = new SortResults_Page();
		String diditsortmostrecent = sortresultspg.sortResultsByMostRecent(driver);
		
		try{
			Assert.assertEquals(diditsortmostrecent, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't sort by most recent order.", e.getMessage());
			errorname = "didntsortmostrecent";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
	
		   softAssert.assertAll();
	}
	
	@Parameters({"browser", "platform"})
	@Test(groups= {"smoke", "regression"}, dataProvider = "getData") 
	public void sortResultsByPrice (String searchkeyword) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		Search search = new Search();
		search.searchByCity(driver, searchkeyword);
	
		SortResults_Page sortresultspg = new SortResults_Page();
		String diditsortdesc = sortresultspg.sortResultsByDescPrice(driver);
		
		try{
			Assert.assertEquals(diditsortdesc, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't sort price by descending order.", e.getMessage());
			errorname = "didntsortpricedesc";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
		

		String diditsortasc = sortresultspg.sortResultsByAscPrice(driver);
		
		try{
			Assert.assertEquals(diditsortasc, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't sort price by ascending order.", e.getMessage());
			errorname = "didntsortpriceasc";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}

		
		   softAssert.assertAll();
	}
	
	@Parameters({"browser", "platform"})
	@Test(groups= {"smoke", "regression"}, dataProvider = "getData") 
	public void sortResultsByBeds (String searchkeyword) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		Search search = new Search();
		search.searchByCity(driver, searchkeyword);
	
		SortResults_Page sortresultspg = new SortResults_Page();
		String diditsortdesc = sortresultspg.sortResultsByDescBeds(driver);
		
		try{
			Assert.assertEquals(diditsortdesc, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't sort beds by descending order.", e.getMessage());
			errorname = "didntsortbeddesc";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
		

		String diditsortasc = sortresultspg.sortResultsByAscBeds(driver);
		
		try{
			Assert.assertEquals(diditsortasc, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't sort beds by ascending order.", e.getMessage());
			errorname = "didntsortbedsasc";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}

		
		   softAssert.assertAll();
	}
	
	
	

	@Parameters({"browser", "platform"})
	@Test(groups= {"smoke", "regression"}, dataProvider = "getData") 
	public void sortResultsByBaths (String searchkeyword) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		Search search = new Search();
		search.searchByCity(driver, searchkeyword);
	
		SortResults_Page sortresultspg = new SortResults_Page();
		String diditsortdesc = sortresultspg.sortResultsByDescBaths(driver);
		
		try{
			Assert.assertEquals(diditsortdesc, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't sort baths by descending order.", e.getMessage());
			errorname = "didntsortbathdesc";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
		

		String diditsortasc = sortresultspg.sortResultsByAscBaths(driver);
		
		try{
			Assert.assertEquals(diditsortasc, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't sort baths by ascending order.", e.getMessage());
			errorname = "didntsortbathsasc";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}

		
		   softAssert.assertAll();
	}
	

}
