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

public class SearchforSpecificProperty extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//

	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(Login_and_Search.class);
	
	
	static String className = Login_and_Search.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";

	@Parameters({"browser", "platform"})	
	@Test(groups={"smoke", "regression"}, dataProvider = "getData")
	public void searchSpecificProperty (String address) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		Search search = new Search();
		String searchspecificaddress = search.searchandVerifyAddressSearched(driver, address);
		
		try{
			Assert.assertEquals(searchspecificaddress, address);
		} 
		catch(AssertionError e)
		{ 
			log.error("Details page doesn't show search specific address.", e.getMessage());
			errorname = "detailspgdidntshowsearchspecificaddress";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
		
		   softAssert.assertAll();
	}
}
