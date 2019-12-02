package mobilePageObjects;

import java.util.List;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.MiscMethods;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class MFilterResults_Page {
	    //****************************************//
		//***                                  ***//
		//*** Created by Angela Tong Apr 2018  ***//
		//***                                  ***//
		//****************************************//
		final static Logger log = LogManager.getLogger(MFilterResults_Page.class);

		private By filterbtn = By.id("com.xome.android:id/filter_menu_item_btn");
		private By activestatus = By.id("com.xome.android:id/active_status_tv");
		private By pendingstatus = By.id("com.xome.android:id/pending_status_tv");
		private By soldstatus = By.id("com.xome.android:id/sold_status_tv");
		private By bedsdrpdown = By.id("com.xome.android:id/beds_spinner");
		private By bedselection = By.id("android:id/text1");
		private By bathsdrpdown = By.id("com.xome.android:id/bath_spinner");
		private By bathselection = By.id("android:id/text1");
		private By sqftmin = By.id("com.xome.android:id/filter_sqr_foot_min");
		private By sqftmax = By.id("com.xome.android:id/filter_sqr_foot_max");
		private By sqftselection = By.id("android:id/text1");
		private By yrmin = By.id("com.xome.android:id/filter_year_built_min");
		private By yrmax = By.id("com.xome.android:id/filter_year_built_max");
		private By yrselection = By.id("android:id/text1");
		private By propertysinglefamily = By.id("com.xome.android:id/single_family_type_tv");
		private By propertytownhome = By.id("com.xome.android:id/townhome_type_tv");
		private By propertycondo = By.id("com.xome.android:id/condo_type_tv");
		private By keywordfield = By.id("com.xome.android:id/keyword_search_edit_text");
		private By applybtn = By.id("com.xome.android:id/apply");
		
		private By pendingtag = By.xpath(".//android.widget.TextView[contains(@resource-id, 'com.xome.android:id/listing_summary_status') and @text='PENDING']");
		private By    soldtag = By.xpath(".//android.widget.TextView[contains(@resource-id, 'com.xome.android:id/listing_summary_status') and @text='SOLD']");
		private By forsaletag = By.xpath(".//android.widget.TextView[contains(@resource-id, 'com.xome.android:id/listing_summary_status') and @text='FOR SALE']");
		private By propertybed = By.id("com.xome.android:id/listing_summary_bedrooms_textView");
		private By propertybath = By.id("com.xome.android:id/listing_summary_bathrooms_textView");
		private By propertysqft = By.id("com.xome.android:id/listing_summary_square_feet_textView");
		private By propertypic = By.id("com.xome.android:id/listing_summary_imageViewPager");
		private By propertydescription = By.id("com.xome.android:id/description");
		//private By propertydetailsmorebutton = MobileBy.AccessibilityId("com.xome.android:id/ldTvMoreButton");
		private By propertydetailsmorebutton = By.xpath("//android.widget.Button[@text='MORE' and @index='5']");
		private By propertyyearheader8thslot = By.xpath(".//android.widget.LinearLayout[@index='8']/android.widget.TextView[@index='0' and @text='Year Built:']");
		private By propertyyearheader7thslot = By.xpath(".//android.widget.LinearLayout[@index='7']/android.widget.TextView[@index='0' and @text='Year Built:']");
		private By 	    propertyyear8thslot = By.xpath(".//android.widget.LinearLayout[@index='8']/android.widget.TextView[@index='1']");
		private By 		propertyyear7thslot = By.xpath(".//android.widget.LinearLayout[@index='7']/android.widget.TextView[@index='1']");
		private By 			   propertytype = By.xpath(".//android.widget.LinearLayout[@index='2']/android.widget.TextView[contains(@resource-id, 'row_value') and @index='1']");
		private By scrollnextproperty = By.id("com.xome.android:id/menu_filter_tv");
		
		private String diditfilter = "";
		
		public void clickFilterOption (AppiumDriver<?> driver) throws InterruptedException 
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			WebElement filterbtnelement = wait.until(ExpectedConditions.elementToBeClickable(filterbtn));
			filterbtnelement.click();
			Thread.sleep(4000); //Must wait for this time to load
		}
		
		public void uncheckAllStatus (AppiumDriver<?> driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			WebElement activestatuselement = wait.until(ExpectedConditions.elementToBeClickable(activestatus));
			WebElement pendingstatuselement = wait.until(ExpectedConditions.elementToBeClickable(activestatus));
			WebElement soldstatuselement = wait.until(ExpectedConditions.elementToBeClickable(activestatus));
			
			Boolean activeselected = activestatuselement.isSelected();
			Boolean pendingselected = pendingstatuselement.isSelected();
			Boolean soldselected = soldstatuselement.isSelected();
			
			if (activeselected.equals(true))
			{
				activestatuselement.click();
				log.info("Active status is unchecked.");
			}
			else
			{
				log.info("Active status is unchecked.");
			}
			
			
			if (pendingselected.equals(true))
			{
				pendingstatuselement.click();
				log.info("Pending status is unchecked.");
			}
			else 
			{
				log.info("Pending status is unchecked.");
			}
			
			if (soldselected.equals(true))
			{
				soldstatuselement.click();
				log.info("Sold status is unchecked.");
			}
			else
			{
				log.info("Sold status is unchecked.");
			}
			
			Thread.sleep(4000);
		}
		
		public void checkPendingStatus (AppiumDriver<?> driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			WebElement pendingstatuselement = wait.until(ExpectedConditions.elementToBeClickable(pendingstatus));
			pendingstatuselement.click();
			log.info("Pending status is checked.");
			Thread.sleep(4000);
		}
		
		public void checkSoldStatus (AppiumDriver<?> driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			WebElement soldstatuselement = wait.until(ExpectedConditions.elementToBeClickable(soldstatus));
			soldstatuselement.click();
			log.info("Sold status is checked.");
			Thread.sleep(4000);
		}
		
		
		public void checkForSaleStatus (AppiumDriver<?> driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			WebElement activestatuselement = wait.until(ExpectedConditions.elementToBeClickable(activestatus));
			activestatuselement.click();
			log.info("Active status is checked.");
			Thread.sleep(4000);
		}
		
		public void clickFilterByBed (AppiumDriver<?> driver, String beds) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			WebElement bedsdrpdownelement = wait.until(ExpectedConditions.elementToBeClickable(bedsdrpdown));
			bedsdrpdownelement.click();
			Thread.sleep(4000);
			
			int numofbeds = Integer.parseInt(beds);
			List <MobileElement> bedslist = (List<MobileElement>) driver.findElements(bedselection);
			WebElement numofbedsselection = bedslist.get(numofbeds);
			numofbedsselection.click();
			Thread.sleep(4000);
		}
		
		public void clickFilterByBath (AppiumDriver<?> driver, String baths) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			WebElement bathsdrpdownelement = wait.until(ExpectedConditions.elementToBeClickable(bathsdrpdown));
			bathsdrpdownelement.click();
			Thread.sleep(4000);
			
			int numofbaths = Integer.parseInt(baths);
			List <MobileElement> bathslist = (List<MobileElement>) driver.findElements(bathselection);
			WebElement numofbathsselection = bathslist.get(numofbaths);
			numofbathsselection.click();
			Thread.sleep(4000);
		}
		
		public void clickFilterByMinSqFt (AppiumDriver<?> driver, String minsqft) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			WebElement sqftminelement = wait.until(ExpectedConditions.elementToBeClickable(sqftmin));
			sqftminelement.click();
			Thread.sleep(4000);
			
			List <MobileElement> sqftselectionlist = (List<MobileElement>) driver.findElements(sqftselection);
			
			
			switch (minsqft)
			{
				case "250": 
					WebElement sqft1 = sqftselectionlist.get(1);
					sqft1.click();
					break;
				case "500":
					WebElement sqft2 = sqftselectionlist.get(2);
					sqft2.click();
					break;
				case "750":
					WebElement sqft3 = sqftselectionlist.get(3);
					sqft3.click();
					break;
				case "1000":
					WebElement sqft4 = sqftselectionlist.get(4);
					sqft4.click();
					break;
				case "1250":
					WebElement sqft5 = sqftselectionlist.get(5);
					sqft5.click();
					break;
				case "1500":
					WebElement sqft6 = sqftselectionlist.get(6);
					sqft6.click();
					break;
				case "1750":
					WebElement sqft7 = sqftselectionlist.get(7);
					sqft7.click();
					break;
				case "2000":
					WebElement sqft8 = sqftselectionlist.get(8);
					sqft8.click();
					break;
				case "2250":
					WebElement sqft9 = sqftselectionlist.get(9);
					sqft9.click();
					break;
				case "2500":
					WebElement sqft10 = sqftselectionlist.get(10);
					sqft10.click();
					break;
				case "2750":
					WebElement sqft11 = sqftselectionlist.get(11);
					sqft11.click();
					break;
				case "3000":
					WebElement sqft12 = sqftselectionlist.get(12);
					sqft12.click();
					break;
			}
			
			Thread.sleep(4000);	

		}
		
		
		public void clickFilterByMaxSqFt (AppiumDriver<?> driver, String maxsqft) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			WebElement sqftmaxelement = wait.until(ExpectedConditions.elementToBeClickable(sqftmax));
			sqftmaxelement.click();
			Thread.sleep(4000);	
			
			List <MobileElement> sqftselectionlist = (List<MobileElement>) driver.findElements(sqftselection);
			
			
			switch (maxsqft)
			{
				case "250": 
					WebElement sqft1 = sqftselectionlist.get(1);
					sqft1.click();
					break;
				case "500":
					WebElement sqft2 = sqftselectionlist.get(2);
					sqft2.click();
					break;
				case "750":
					WebElement sqft3 = sqftselectionlist.get(3);
					sqft3.click();
					break;
				case "1000":
					WebElement sqft4 = sqftselectionlist.get(4);
					sqft4.click();
					break;
				case "1250":
					WebElement sqft5 = sqftselectionlist.get(5);
					sqft5.click();
					break;
				case "1500":
					WebElement sqft6 = sqftselectionlist.get(6);
					sqft6.click();
					break;
				case "1750":
					WebElement sqft7 = sqftselectionlist.get(7);
					sqft7.click();
					break;
				case "2000":
					WebElement sqft8 = sqftselectionlist.get(8);
					sqft8.click();
					break;
				case "2250":
					WebElement sqft9 = sqftselectionlist.get(9);
					sqft9.click();
					break;
				case "2500":
					WebElement sqft10 = sqftselectionlist.get(10);
					sqft10.click();
					break;
				case "2750":
					WebElement sqft11 = sqftselectionlist.get(11);
					sqft11.click();
					break;
				case "3000":
					WebElement sqft12 = sqftselectionlist.get(12);
					sqft12.click();
					break;
			}
			
			Thread.sleep(4000);	

		}
		
		public void clickFilterByKeyword (AppiumDriver<?> driver, String keyword) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			MiscMethods.swipeDown(driver);
			WebElement keywordelement = wait.until(ExpectedConditions.elementToBeClickable(keywordfield));
			keywordelement.click();
			keywordelement.clear();
			keywordelement.sendKeys(keyword);
			Thread.sleep(4000);	
		}	
		
		public void clickFilterByMinYear (AppiumDriver<?> driver, String minyear) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			WebElement yrminelement = wait.until(ExpectedConditions.elementToBeClickable(yrmin));
			yrminelement.click();
			Thread.sleep(4000);
			
			List <MobileElement> yrselectionlist = (List<MobileElement>) driver.findElements(yrselection);
			
			switch (minyear)
			{
				case "2017":
					WebElement yrselectionelement1 = yrselectionlist.get(1);
					yrselectionelement1.click();
					break;
				case "2016":
					WebElement yrselectionelement2 = yrselectionlist.get(2);
					yrselectionelement2.click();
					break;
				case "2015":
					WebElement yrselectionelement3 = yrselectionlist.get(3);
					yrselectionelement3.click();
					break;
				case "2014":
					WebElement yrselectionelement4 = yrselectionlist.get(4);
					yrselectionelement4.click();
					break;
				case "2005":
					WebElement yrselectionelement5 = yrselectionlist.get(5);
					yrselectionelement5.click();
					break;
				case "2000":
					WebElement yrselectionelement6 = yrselectionlist.get(6);
					yrselectionelement6.click();
					break;
				case "1995":
					WebElement yrselectionelement7 = yrselectionlist.get(7);
					yrselectionelement7.click();
					break;
				case "1990":
					WebElement yrselectionelement8 = yrselectionlist.get(8);
					yrselectionelement8.click();
					break;
				case "1980":
					WebElement yrselectionelement9 = yrselectionlist.get(9);
					yrselectionelement9.click();
					break;
				case "1970":
					WebElement yrselectionelement10 = yrselectionlist.get(10);
					yrselectionelement10.click();
					break;
				case "1960":
					WebElement yrselectionelement11 = yrselectionlist.get(11);
					yrselectionelement11.click();
					break;
				case "1950":
					WebElement yrselectionelement12 = yrselectionlist.get(12);
					yrselectionelement12.click();
					break;
				case "1940":
					WebElement yrselectionelement13 = yrselectionlist.get(13);
					yrselectionelement13.click();
					break;
				case "1920":
					WebElement yrselectionelement14 = yrselectionlist.get(14);
					yrselectionelement14.click();
					break;
				case "1900":
					WebElement yrselectionelement15 = yrselectionlist.get(15);
					yrselectionelement15.click();
					break;
			}
			
			Thread.sleep(4000);
			
		}
		
		
		public void clickFilterByMaxYear (AppiumDriver<?> driver, String maxyear) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			WebElement yrmaxelement = wait.until(ExpectedConditions.elementToBeClickable(yrmax));
			yrmaxelement.click();
			Thread.sleep(4000);
			
			List <MobileElement> yrselectionlist = (List<MobileElement>) driver.findElements(yrselection);
			
			switch (maxyear)
			{
				case "2017":
					WebElement yrselectionelement1 = yrselectionlist.get(1);
					yrselectionelement1.click();
					break;
				case "2016":
					WebElement yrselectionelement2 = yrselectionlist.get(2);
					yrselectionelement2.click();
					break;
				case "2015":
					WebElement yrselectionelement3 = yrselectionlist.get(3);
					yrselectionelement3.click();
					break;
				case "2014":
					WebElement yrselectionelement4 = yrselectionlist.get(4);
					yrselectionelement4.click();
					break;
				case "2005":
					WebElement yrselectionelement5 = yrselectionlist.get(5);
					yrselectionelement5.click();
					break;
				case "2000":
					WebElement yrselectionelement6 = yrselectionlist.get(6);
					yrselectionelement6.click();
					break;
				case "1995":
					WebElement yrselectionelement7 = yrselectionlist.get(7);
					yrselectionelement7.click();
					break;
				case "1990":
					WebElement yrselectionelement8 = yrselectionlist.get(8);
					yrselectionelement8.click();
					break;
				case "1980":
					WebElement yrselectionelement9 = yrselectionlist.get(9);
					yrselectionelement9.click();
					break;
				case "1970":
					WebElement yrselectionelement10 = yrselectionlist.get(10);
					yrselectionelement10.click();
					break;
				case "1960":
					WebElement yrselectionelement11 = yrselectionlist.get(11);
					yrselectionelement11.click();
					break;
				case "1950":
					WebElement yrselectionelement12 = yrselectionlist.get(12);
					yrselectionelement12.click();
					break;
				case "1940":
					WebElement yrselectionelement13 = yrselectionlist.get(13);
					yrselectionelement13.click();
					break;
				case "1920":
					WebElement yrselectionelement14 = yrselectionlist.get(14);
					yrselectionelement14.click();
					break;
				case "1900":
					WebElement yrselectionelement15 = yrselectionlist.get(15);
					yrselectionelement15.click();
					break;
			}
			
			Thread.sleep(4000);
			
		}
		
		public String verifyFilterByYear (AppiumDriver<?> driver, String minyear, String maxyear) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			
			List <MobileElement> propertypiclist = (List<MobileElement>) driver.findElements(propertypic);
			WebElement property1pic = propertypiclist.get(0);
			property1pic.click();
			Thread.sleep(4000);
			
			int min_year = Integer.parseInt(minyear);
			int max_year = Integer.parseInt(maxyear);
			int year1;
			int year2;
			int year3;
			
	        Dimension size = driver.manage().window().getSize();
	        int starty = (int) (size.height * 0.4);
	        int endy = (int) (size.height * 0.2);
	        int startx = size.width / 2;
	        new TouchAction(driver).press(PointOption.point(startx, starty)).waitAction().moveTo(PointOption.point(startx, endy)).release().perform();
	        Thread.sleep(4000);
			
			List <MobileElement> property1yearheader8thslotlist = (List<MobileElement>) driver.findElements(propertyyearheader8thslot);
			int property1yearheader8thslotsize = property1yearheader8thslotlist.size();
			
			if (property1yearheader8thslotsize > 0)
			{
				WebElement property1year8thslotelement = wait.until(ExpectedConditions.presenceOfElementLocated(propertyyear8thslot));
				String year8text1 = property1year8thslotelement.getText();
				year1 = Integer.parseInt(year8text1);
			}
			else
			{
				WebElement property1year7thslotelement = wait.until(ExpectedConditions.presenceOfElementLocated(propertyyear7thslot));
				String year7text1 = property1year7thslotelement.getText();
				year1 = Integer.parseInt(year7text1);
			}
		
			int yreighth;
			int yrseventh;
			Boolean year;
			Boolean yearmatch1 = (year1 >=min_year) && (year1 <=max_year);
			Boolean yearmatch2 = false;
			Boolean yearmatch3 = false;
				
			log.info("yearmatch1 is "+yearmatch1);
			
			for (int i=0; i<2; i++)
			{
				WebElement scrollnext = wait.until(ExpectedConditions.elementToBeClickable(scrollnextproperty));
				scrollnext.click();
				Thread.sleep(4000);
				
				new TouchAction(driver).press(PointOption.point(startx, starty)).waitAction().moveTo(PointOption.point(startx, endy)).release().perform();
		        Thread.sleep(4000);
		        
				List <MobileElement> propertyyearheader8thslotlist = (List<MobileElement>) driver.findElements(propertyyearheader8thslot);
				int propertyyearheader8thslotsize = propertyyearheader8thslotlist.size();
				
				if (propertyyearheader8thslotsize > 0)
				{
					WebElement propertyyear8thslotelement = wait.until(ExpectedConditions.presenceOfElementLocated(propertyyear8thslot));
					String year8text = propertyyear8thslotelement.getText();
					yreighth = Integer.parseInt(year8text);
					year = (yreighth >= min_year) && (yreighth <= max_year);
				}
				else
				{
					WebElement propertyyear7thslotelement = wait.until(ExpectedConditions.presenceOfElementLocated(propertyyear7thslot));
					String year7text = propertyyear7thslotelement.getText();
					yrseventh = Integer.parseInt(year7text);
					year = (yrseventh >= min_year) && (yrseventh <= max_year);
				}
				

				
				if (i==0)
				{
					yearmatch2 = year;
					log.info("yearmatch2 is "+yearmatch2);
				}
				else if (i==1)
				{
					yearmatch3 = year;
					log.info("yearmatch3 is "+yearmatch3);
				}
			}
			
			
			if (yearmatch1.equals(true) && yearmatch2.equals(true) && yearmatch3.equals(true))
			{
				diditfilter="yes";
				log.info("It filter by min year "+minyear+" and max year "+maxyear+" successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filter by min year "+minyear+" and max year "+maxyear+" unsuccessfully.");
			}
			
			return diditfilter;
			
		}
			
		public String verifyFilterByKeyword (AppiumDriver<?> driver, String keyword) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			
			List <MobileElement> propertypiclist = (List<MobileElement>) driver.findElements(propertypic);
			WebElement property1pic = propertypiclist.get(0);
			property1pic.click();
			Thread.sleep(4000);
			
			List <MobileElement> propertydetailsmorebuttonlist = (List<MobileElement>) driver.findElements(propertydetailsmorebutton);
			if(propertydetailsmorebuttonlist.size()>0)
			{
				propertydetailsmorebuttonlist.get(0).click();
			}
			
			WebElement propdescriptionelement1 = wait.until(ExpectedConditions.elementToBeClickable(propertydescription));
			String propdescriptiontext1 = propdescriptionelement1.getText();
			Boolean propkeywordmatch1 = Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(propdescriptiontext1).find();
			Boolean propkeywordmatch2 = false;
			Boolean propkeywordmatch3 = false;
			
			for (int i=0; i<2; i++)
			{
				WebElement scrollnext = wait.until(ExpectedConditions.elementToBeClickable(scrollnextproperty));
				scrollnext.click();
				Thread.sleep(4000);
				
				List <MobileElement> propertydetailsmorebuttonlist2 = (List<MobileElement>) driver.findElements(propertydetailsmorebutton);
				if(propertydetailsmorebuttonlist2.size()>0)
				{
					propertydetailsmorebuttonlist2.get(0).click();
				}
				
				WebElement propdescriptionelement = wait.until(ExpectedConditions.elementToBeClickable(propertydescription));
				String propdescriptiontext = propdescriptionelement.getText();
				Boolean propkeywordmatch = Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(propdescriptiontext).find();
				
				if (i==0)
				{
					propkeywordmatch2 = propkeywordmatch;
				}
				else if (i==1)
				{
					propkeywordmatch3 = propkeywordmatch;
				}

			}
			
			
			if (propkeywordmatch1.equals(true) && propkeywordmatch2.equals(true) && propkeywordmatch3.equals(true))
			{
				diditfilter="yes";
				log.info("It filter by keyword "+keyword+" successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filter by keyword "+keyword+" unsuccessfully.");
			}
			
			return diditfilter;
		}
		
		public String verifyFilterBySqFt (AppiumDriver<?> driver, String minsqft, String maxsqft) throws InterruptedException
		{
		    List <MobileElement> sqftlist = (List<MobileElement>) driver.findElements(propertysqft);
		    WebElement prop1sqftelement = sqftlist.get(0);
		    String prop1sqfttext = prop1sqftelement.getText();
		    prop1sqfttext = prop1sqfttext.replaceAll(",", "");
		    int prop1sqft = Integer.parseInt(prop1sqfttext);
		    
		    WebElement prop2sqftelement = sqftlist.get(0);
		    String prop2sqfttext = prop2sqftelement.getText();
		    prop2sqfttext = prop2sqfttext.replaceAll(",", "");
		    int prop2sqft = Integer.parseInt(prop2sqfttext);
		    
		    WebElement prop3sqftelement = sqftlist.get(0);
		    String prop3sqfttext = prop3sqftelement.getText();
		    prop3sqfttext = prop3sqfttext.replaceAll(",", "");
		    int prop3sqft = Integer.parseInt(prop3sqfttext);
			
		    int min_sqft = Integer.parseInt(minsqft);
		    int max_sqft = Integer.parseInt(maxsqft);
		    
		    if ((prop1sqft >= min_sqft && prop1sqft <= max_sqft) && (prop2sqft >= min_sqft && prop2sqft <= max_sqft)  && (prop3sqft >= min_sqft && prop3sqft <= max_sqft) )
		    {
		    		diditfilter="yes";
		    		log.info("It filter by min square feet  "+minsqft+" and max square feet "+maxsqft+" successfully.");
		    }
		    else
		    {
	    			diditfilter="no";
		    		log.error("It filter by min square feet "+minsqft+" and max square feet "+maxsqft+" unsuccessfully.");
		    }
		    
			return diditfilter;
		}
		
		public String verifyFilterByBeds (AppiumDriver<?> driver, String beds) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			int numofbeds = Integer.parseInt(beds);
			
			List <MobileElement> bedlist = (List<MobileElement>) driver.findElements(propertybed);
			WebElement prop1bedelement = bedlist.get(0);
			String prop1bedtext = prop1bedelement.getText();
			int prop1bed = Integer.parseInt(prop1bedtext);
			
			WebElement prop2bedelement = bedlist.get(1);
			String prop2bedtext = prop2bedelement.getText();
			int prop2bed = Integer.parseInt(prop2bedtext);
		
			WebElement prop3bedelement = bedlist.get(2);
			String prop3bedtext = prop3bedelement.getText();
			int prop3bed = Integer.parseInt(prop3bedtext);
			
			if(prop1bed>=numofbeds && prop2bed>=numofbeds && prop3bed>=numofbeds)
			{
				diditfilter="yes";
				log.info("It filtered by "+numofbeds+" beds successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filtered by "+numofbeds+" beds unsuccessfully.");
			}
			
			return diditfilter;
		}
		
		public String verifyFilterByBaths (AppiumDriver<?> driver, String baths) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			int numofbaths = Integer.parseInt(baths);
			
			List <MobileElement> bathlist = (List<MobileElement>) driver.findElements(propertybath);
			WebElement prop1bathelement = bathlist.get(0);
			String prop1bathtext = prop1bathelement.getText();
			int prop1bath = Integer.parseInt(prop1bathtext);
			
			WebElement prop2bathelement = bathlist.get(1);
			String prop2bathtext = prop2bathelement.getText();
			int prop2bath = Integer.parseInt(prop2bathtext);
		
			WebElement prop3bathelement = bathlist.get(2);
			String prop3bathtext = prop3bathelement.getText();
			int prop3bath = Integer.parseInt(prop3bathtext);
			
			if(prop1bath>=numofbaths && prop2bath>=numofbaths && prop3bath>=numofbaths)
			{
				diditfilter="yes";
				log.info("It filtered by "+numofbaths+" baths successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filtered by "+numofbaths+" baths unsuccessfully.");
			}
			
			return diditfilter;
		}
		

		public String verifyPendingStatus (AppiumDriver<?> driver) throws InterruptedException
		{
			Thread.sleep(3000);
			List <MobileElement> pendinglist = (List<MobileElement>) driver.findElements(pendingtag);
			
			WebElement prop1pendingelement = pendinglist.get(0);
			String prop1pending = prop1pendingelement.getText();
			
			WebElement prop2pendingelement = pendinglist.get(0);
			String prop2pending = prop2pendingelement.getText();
			
			WebElement prop3pendingelement = pendinglist.get(0);
			String prop3pending = prop3pendingelement.getText();
			
			if (prop1pending.equalsIgnoreCase("PENDING") && prop2pending.equalsIgnoreCase("PENDING") && prop3pending.equalsIgnoreCase("PENDING"))
			{
				diditfilter="yes";
				log.info("It filtered by pending status successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filtered by pending status unsuccessfully.");
			}
			
			return diditfilter;
		}
		
		public String verifySoldStatus (AppiumDriver<?> driver) throws InterruptedException
		{
			Thread.sleep(3000);
			List <MobileElement> soldlist = (List<MobileElement>) driver.findElements(soldtag);
			
			WebElement prop1soldelement = soldlist.get(0);
			String prop1sold = prop1soldelement.getText();
			
			WebElement prop2soldelement = soldlist.get(0);
			String prop2sold = prop2soldelement.getText();
			
			WebElement prop3soldelement = soldlist.get(0);
			String prop3sold = prop3soldelement.getText();
			
			if (prop1sold.equalsIgnoreCase("SOLD") && prop2sold.equalsIgnoreCase("SOLD") && prop3sold.equalsIgnoreCase("SOLD"))
			{
				diditfilter="yes";
				log.info("It filtered by sold status successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filtered by sold status unsuccessfully.");
			}
			
			return diditfilter;
		}
		
		public String verifyForSaleStatus (AppiumDriver<?> driver) throws InterruptedException
		{
			Thread.sleep(3000);
			
			List <MobileElement> activelist = (List<MobileElement>) driver.findElements(forsaletag);
			
			WebElement prop1activeelement = activelist.get(0);
			String prop1active = prop1activeelement.getText();
			
			WebElement prop2activeelement = activelist.get(0);
			String prop2active = prop2activeelement.getText();
			
			WebElement prop3activeelement = activelist.get(0);
			String prop3active = prop3activeelement.getText();
			
			if (prop1active.equalsIgnoreCase("FOR SALE") && prop2active.equalsIgnoreCase("FOR SALE") && prop3active.equalsIgnoreCase("FOR SALE"))
			{
				diditfilter="yes";
				log.info("It filtered by for sale active status successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filtered by for sale active status unsuccessfully.");
			}
			
			return diditfilter;
		}
		
		public void clickSingleFamily (AppiumDriver<?> driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			WebElement propertysinglefamilyelement = wait.until(ExpectedConditions.elementToBeClickable(propertysinglefamily));
			propertysinglefamilyelement.click();
		    Thread.sleep(4000);
		}
		
		public void clickTownHome (AppiumDriver<?> driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			WebElement propertytownhomeelement = wait.until(ExpectedConditions.elementToBeClickable(propertytownhome));
			propertytownhomeelement.click();
		    Thread.sleep(4000);
		}
		
		public void clickCondo (AppiumDriver<?> driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			WebElement propertycondoelement = wait.until(ExpectedConditions.elementToBeClickable(propertycondo));
			propertycondoelement.click();
		    Thread.sleep(4000);
		}
		
		public String verifyFilterBySingleFamily (AppiumDriver<?> driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);

			List <MobileElement> propertypiclist = (List<MobileElement>) driver.findElements(propertypic);
			WebElement property1pic = propertypiclist.get(0);
			property1pic.click();
			Thread.sleep(6000);
			
			WebElement property1typeelement = wait.until(ExpectedConditions.visibilityOfElementLocated(propertytype));
			String property1typetext = property1typeelement.getText();
			log.info(property1typetext);
			
			Boolean propertytypematch1 = Pattern.compile(Pattern.quote("Single Family"), Pattern.CASE_INSENSITIVE).matcher(property1typetext).find();
			Boolean propertytypematch2 = false;
			Boolean propertytypematch3 = false;
			
			for (int i=0; i<2; i++)
			{
				WebElement scrollnext = wait.until(ExpectedConditions.elementToBeClickable(scrollnextproperty));
				scrollnext.click();
				Thread.sleep(6000);
				
				WebElement propertytypeelement = wait.until(ExpectedConditions.visibilityOfElementLocated(propertytype));
				String propertytypetext = propertytypeelement.getText();
				log.info(propertytypetext);
				
				Boolean propertytypematch = Pattern.compile(Pattern.quote("Single Family"), Pattern.CASE_INSENSITIVE).matcher(propertytypetext).find();
	
				if (i==0)
				{
					propertytypematch2 = propertytypematch;
				}
				else if (i==1)
				{
					propertytypematch3 = propertytypematch;
				}

			}
			
			
			if (propertytypematch1.equals(true) && propertytypematch2.equals(true) && propertytypematch3.equals(true))
			{
				diditfilter="yes";
				log.info("It filter by Single Family successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filter by Single Family unsuccessfully.");
			}
			
			return diditfilter;

		}
		
		
		public String verifyFilterByTownHome (AppiumDriver<?> driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);

			List <MobileElement> propertypiclist = (List<MobileElement>) driver.findElements(propertypic);
			WebElement property1pic = propertypiclist.get(0);
			property1pic.click();
			Thread.sleep(6000);
			
			WebElement property1typeelement = wait.until(ExpectedConditions.visibilityOfElementLocated(propertytype));
			String property1typetext = property1typeelement.getText();
			log.info(property1typetext);

			Boolean propertytypematch1 = Pattern.compile(Pattern.quote("Townhouse"), Pattern.CASE_INSENSITIVE).matcher(property1typetext).find();
			Boolean propertytypematch2 = false;
			Boolean propertytypematch3 = false;
			
			
			for (int i=0; i<2; i++)
			{
				WebElement scrollnext = wait.until(ExpectedConditions.elementToBeClickable(scrollnextproperty));
				scrollnext.click();
				Thread.sleep(6000);
				
				WebElement propertytypeelement = wait.until(ExpectedConditions.visibilityOfElementLocated(propertytype));
				String propertytypetext = propertytypeelement.getText();
				log.info(propertytypetext);
				
				Boolean propertytypematch = Pattern.compile(Pattern.quote("Townhouse"), Pattern.CASE_INSENSITIVE).matcher(propertytypetext).find();
	
				if (i==0)
				{
					propertytypematch2 = propertytypematch;
				}
				else if (i==1)
				{
					propertytypematch3 = propertytypematch;
				}

			}
			
			
			if (propertytypematch1.equals(true) && propertytypematch2.equals(true) && propertytypematch3.equals(true))
			{
				diditfilter="yes";
				log.info("It filter by Townhouse successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filter by Townhouse unsuccessfully.");
			}
			
			return diditfilter;

		}
		
		
		public String verifyFilterByCondo (AppiumDriver<?> driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);

			List <MobileElement> propertypiclist = (List<MobileElement>) driver.findElements(propertypic);
			WebElement property1pic = propertypiclist.get(0);
			property1pic.click();
			Thread.sleep(6000);
			
			WebElement property1typeelement = wait.until(ExpectedConditions.visibilityOfElementLocated(propertytype));
			String property1typetext = property1typeelement.getText();
			log.info(property1typetext);
			
			Boolean propertytypematch1 = Pattern.compile(Pattern.quote("Condo"), Pattern.CASE_INSENSITIVE).matcher(property1typetext).find();
			Boolean propertytypematch2 = false;
			Boolean propertytypematch3 = false;
			
			for (int i=0; i<2; i++)
			{
				WebElement scrollnext = wait.until(ExpectedConditions.elementToBeClickable(scrollnextproperty));
				scrollnext.click();
				Thread.sleep(6000);
				
				WebElement propertytypeelement = wait.until(ExpectedConditions.visibilityOfElementLocated(propertytype));
				String propertytypetext = propertytypeelement.getText();
				log.info(propertytypetext);
				
				Boolean propertytypematch = Pattern.compile(Pattern.quote("Condo"), Pattern.CASE_INSENSITIVE).matcher(propertytypetext).find();
	
				if (i==0)
				{
					propertytypematch2 = propertytypematch;
				}
				else if (i==1)
				{
					propertytypematch3 = propertytypematch;
				}

			}
			
			
			if (propertytypematch1.equals(true) && propertytypematch2.equals(true) && propertytypematch3.equals(true))
			{
				diditfilter="yes";
				log.info("It filter by condo successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filter by condo unsuccessfully.");
			}
			
			return diditfilter;

		}
		
		
		public void clickApplyFilter (AppiumDriver<?> driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, 60);
			WebElement applybtnelement = wait.until(ExpectedConditions.elementToBeClickable(applybtn));
			applybtnelement.click();
			
			Thread.sleep(4000); //Must wait for this time to load
		}
		
		
		public void firstOpenAppFilterBySingleFamilyTownHomeCondo (AppiumDriver<?> driver) throws InterruptedException
		{	
            MFilterResults_Page mfrp = new MFilterResults_Page();
            mfrp.clickFilterOption(driver);
            mfrp.clickSingleFamily(driver);
            mfrp.clickTownHome(driver);
            mfrp.clickCondo(driver);
            mfrp.clickApplyFilter(driver);

		}
}
