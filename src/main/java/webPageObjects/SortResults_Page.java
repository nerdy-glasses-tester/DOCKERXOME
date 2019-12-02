package webPageObjects;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.MiscMethods;


public class SortResults_Page {
	        
	        //****************************************//
			//***                                  ***//
			//*** Created by Angela Tong Apr 2018  ***//
			//***                                  ***//
			//****************************************//
			final static Logger log = LogManager.getLogger(SortResults_Page.class);
			
			private By sortbyclicktoexpanddrpdown = By.cssSelector("div.search-results-sorter>div.btn-group.bootstrap-select>button.btn.dropdown-toggle.btn-default>span.filter-option.pull-left");
			private By dropdownarea = By.cssSelector("div.btn-group.bootstrap-select.open>div.dropdown-menu.open");
			private By sortbydropdownmostrecent = By.cssSelector("div.search-results-sorter>div.btn-group.bootstrap-select.open>div.dropdown-menu.open>ul.dropdown-menu.inner>li[data-original-index='0']>a>span.text");
			private By sortbydropdownpricedesc = By.cssSelector("div.search-results-sorter>div.btn-group.bootstrap-select.open>div.dropdown-menu.open>ul.dropdown-menu.inner>li[data-original-index='1']>a>span.text");
			private By sortbydropdownpriceasc = By.cssSelector("div.search-results-sorter>div.btn-group.bootstrap-select.open>div.dropdown-menu.open>ul.dropdown-menu.inner>li[data-original-index='2']>a>span.text");
			private By sortbydropdownbedsdesc = By.cssSelector("div.search-results-sorter>div.btn-group.bootstrap-select.open>div.dropdown-menu.open>ul.dropdown-menu.inner>li[data-original-index='3']>a>span.text");
			private By sortbydropdpwnbedsasc = By.cssSelector("div.search-results-sorter>div.btn-group.bootstrap-select.open>div.dropdown-menu.open>ul.dropdown-menu.inner>li[data-original-index='4']>a>span.text");
			private By sortbydropdownbathsdesc = By.cssSelector("div.search-results-sorter>div.btn-group.bootstrap-select.open>div.dropdown-menu.open>ul.dropdown-menu.inner>li[data-original-index='5']>a>span.text");
			private By sortbydropdownbathsasc = By.cssSelector("div.search-results-sorter>div.btn-group.bootstrap-select.open>div.dropdown-menu.open>ul.dropdown-menu.inner>li[data-original-index='6']>a>span.text");
			private By propertypriceresults = By.cssSelector("div.price>span[itemprop='price']");
			private By searchresultslistbeds = By.xpath("//span[contains(text(), 'bd')]/preceding-sibling::span");
			private By searchresultslistbaths = By.xpath("//span[contains(text(), 'ba')]/preceding-sibling::span");
			private By property1newicon = By.cssSelector("span.ribbon-new.ribbon");  
			private String diditsort ="";
			
			 public String sortResultsByMostRecent (RemoteWebDriver webdriver) throws InterruptedException
			    {
				 
			    	WebDriverWait wait = new WebDriverWait (webdriver, 60);
			    	WebElement clicksortby = wait.until(ExpectedConditions.elementToBeClickable(sortbyclicktoexpanddrpdown));
			    	clicksortby.click();

			    	Thread.sleep(4000); //Must wait
			    		
			    	WebElement dropdownareabox = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownarea));
			    	Actions action = new Actions(webdriver);
			    	action.moveToElement(dropdownareabox).perform();
		            action.sendKeys(Keys.ARROW_DOWN);
		            action.sendKeys(Keys.ARROW_DOWN);
			    	WebElement clicksortbyprice = wait.until(ExpectedConditions.elementToBeClickable(sortbydropdownpricedesc));
			        action.moveToElement(clicksortbyprice).click().perform();
			        Thread.sleep(4000); //Must wait
		    		    
			        clicksortby.click();
					Thread.sleep(4000); //Must wait
			    	action.moveToElement(dropdownareabox).perform();
		            action.sendKeys(Keys.ARROW_UP);
			    	WebElement clicksortbymostrecent = wait.until(ExpectedConditions.elementToBeClickable(sortbydropdownmostrecent));
			        action.moveToElement(clicksortbymostrecent).click().perform();
			        
			        Thread.sleep(4000); //Must wait
	        
		            List <WebElement> resultswithnewicon = webdriver.findElements(property1newicon);

		            String newicon = resultswithnewicon.get(0).getText();
  
		            log.info("Newicon says: "+newicon+"\n");
		            
		            if (newicon.equalsIgnoreCase("NEW"))
		            {
		            	    log.info("It has sorted by most recent order.");
		            	    diditsort = "yes";
		            }
		            else
		            {
		            	    log.error("It failed to sort in most recent order.");
		            	    diditsort = "no";
		            }
		            

		            return diditsort;
		    }
			
			 public String sortResultsByDescPrice (RemoteWebDriver webdriver) throws InterruptedException
			    {
				 
			    	WebDriverWait wait = new WebDriverWait (webdriver, 60);
			    	WebElement clicksortby = wait.until(ExpectedConditions.elementToBeClickable(sortbyclicktoexpanddrpdown));
			    	clicksortby.click();
 
			    	Thread.sleep(4000); //Must wait
			    		
			    	WebElement dropdownareabox = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownarea));
			    	Actions action = new Actions(webdriver);
			    	action.moveToElement(dropdownareabox).perform();
		            action.sendKeys(Keys.ARROW_DOWN);
		            action.sendKeys(Keys.ARROW_DOWN);
			    	WebElement clicksortbyprice = wait.until(ExpectedConditions.elementToBeClickable(sortbydropdownpricedesc));
			    	action.moveToElement(clicksortbyprice).click().perform();
			    	//((JavascriptExecutor)webdriver).executeScript("arguments[0].click();", clicksortbyprice); 
			        
			        Thread.sleep(4000); //Must wait 
		    		
			        List <WebElement> propertypriceresultslist = webdriver.findElements(propertypriceresults); 
			        
		            WebElement property1_price = propertypriceresultslist.get(0);
		            WebElement property2_price = propertypriceresultslist.get(1);
		            WebElement property3_price = propertypriceresultslist.get(2);
		            WebElement property4_price = propertypriceresultslist.get(3);
		            WebElement property5_price = propertypriceresultslist.get(4);
		            
		            String priceone = property1_price.getText(); 
		            priceone=priceone.replaceAll(",", "");
		            String price1 = priceone.substring(1);
		            int prop1price = Integer.parseInt(price1);
		            
		            String pricetwo = property2_price.getText();
		            pricetwo=pricetwo.replaceAll(",", "");
		            String price2 = pricetwo.substring(1);
		            int prop2price = Integer.parseInt(price2);

		            String pricethree = property3_price.getText();
		            pricethree=pricethree.replaceAll(",", "");
		            String price3 = pricethree.substring(1);
		            int prop3price = Integer.parseInt(price3);

		            String pricefour = property4_price.getText();
		            pricefour=pricefour.replaceAll(",", "");
		            String price4 = pricefour.substring(1);
		            int prop4price = Integer.parseInt(price4);

		            String pricefive = property5_price.getText();
		            pricefive=pricefive.replaceAll(",", "");
		            String price5 = pricefive.substring(1);
		            int prop5price = Integer.parseInt(price5);
 
		            String original = "["+price1+" "+price2+" "+price3+" "+price4+" "+price5+ "]";
		            
		            ArrayList<Integer> desclist = new ArrayList<Integer> ();
		            desclist.add(prop1price);
		            desclist.add(prop2price);
		            desclist.add(prop3price);
		            desclist.add(prop4price);
		            desclist.add(prop5price);
		            
		            MiscMethods.sortDescending(desclist);
		            String sorteddesc = MiscMethods.returnsStringforIntegerArrayList(desclist);
		            
		            log.info("Original order is: "+original+"\n");
		            log.info("Sorted descending order is: "+sorteddesc+"\n");
		            
		            if (original.equalsIgnoreCase(sorteddesc))
		            {
		            	    log.info("It has sorted in descending order.");
		            	    diditsort = "yes";
		            }
		            else
		            {
		            	    log.error("It failed to sort in descending order.");
		            	    diditsort = "no";
		            }
		            

		            return diditsort;
		    }
			 
			 public String sortResultsByAscPrice (RemoteWebDriver webdriver) throws InterruptedException
			 {
				    WebDriverWait wait = new WebDriverWait (webdriver, 60);
			    	WebElement clicksortby = wait.until(ExpectedConditions.elementToBeClickable(sortbyclicktoexpanddrpdown));
			    	clicksortby.click();
			    		
			    	Thread.sleep(4000); //Must wait
			    		
			    	WebElement dropdownareabox = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownarea));
			    	Actions action = new Actions(webdriver);		    	
			    	action.moveToElement(dropdownareabox).perform();
		            action.sendKeys(Keys.ARROW_DOWN);
		            action.sendKeys(Keys.ARROW_DOWN);
			    	WebElement clicksortbyprice = wait.until(ExpectedConditions.elementToBeClickable(sortbydropdownpriceasc));
			    	action.moveToElement(clicksortbyprice).click().perform();
	            		
			        Thread.sleep(4000); //Must wait
		    			        
	            	List <WebElement> propertypriceresultslist = webdriver.findElements(propertypriceresults); 
				        
		            WebElement property1_price = propertypriceresultslist.get(0);
		            WebElement property2_price = propertypriceresultslist.get(1);
		            WebElement property3_price = propertypriceresultslist.get(2);
		            WebElement property4_price = propertypriceresultslist.get(3);
		            WebElement property5_price = propertypriceresultslist.get(4);
	            	
		            String priceone = property1_price.getText(); 
		            priceone=priceone.replaceAll(",", "");
		            String price1 = priceone.substring(1);
		            int prop1price = Integer.parseInt(price1);
		            
		            String pricetwo = property2_price.getText();
		            pricetwo=pricetwo.replaceAll(",", "");
		            String price2 = pricetwo.substring(1);
		            int prop2price = Integer.parseInt(price2);
		            
		            String pricethree = property3_price.getText();
		            pricethree=pricethree.replaceAll(",", "");
		            String price3 = pricethree.substring(1);
		            int prop3price = Integer.parseInt(price3);
		            
		            String pricefour = property4_price.getText();
		            pricefour=pricefour.replaceAll(",", "");
		            String price4 = pricefour.substring(1);
		            int prop4price = Integer.parseInt(price4);
		            
		            String pricefive = property5_price.getText();
		            pricefive=pricefive.replaceAll(",", "");
		            String price5 = pricefive.substring(1);
		            int prop5price = Integer.parseInt(price5);
  
			        String original = "["+ price1+" "+price2+" "+price3+" "+price4+" "+price5+"]";
		            
		            ArrayList<Integer> asclist = new ArrayList<Integer> ();
		            asclist.add(prop1price);
		            asclist.add(prop2price);
		            asclist.add(prop3price);
		            asclist.add(prop4price);
		            asclist.add(prop5price);
		            
		            MiscMethods.sortAscending(asclist);
		            String sortedasc = MiscMethods.returnsStringforIntegerArrayList(asclist);
		            
		            
		            log.info("Original order is: "+original+"\n");
		            log.info("Sorted ascending order is: "+sortedasc+"\n");
		            
		            if (original.equalsIgnoreCase(sortedasc))
		            {
		            	    log.info("It has sorted in ascending order.");
		            	    diditsort = "yes";
		            }
		            else
		            {
		            	    log.error("It failed to sort in ascending order.");
		            	    diditsort = "no";
		            }
	           
	            return diditsort;
		    }
			 
			 
			 public String sortResultsByDescBeds (RemoteWebDriver webdriver) throws InterruptedException
			 {
				 
	    		WebDriverWait wait = new WebDriverWait (webdriver, 60);
	    		WebElement clicksortby = wait.until(ExpectedConditions.elementToBeClickable(sortbyclicktoexpanddrpdown));
	    		clicksortby.click();

	    		Thread.sleep(4000); //Must wait for this time to load, cant use implicit wait, because it will find the element, but the element is not in the state i want it at yet
	    		
	    		WebElement dropdownareabox = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownarea));
	    		Actions action = new Actions(webdriver);
	    	    action.moveToElement(dropdownareabox).perform();
                action.sendKeys(Keys.ARROW_DOWN);
                action.sendKeys(Keys.ARROW_DOWN);
	    		WebElement clicksortbybeds= wait.until(ExpectedConditions.elementToBeClickable(sortbydropdownbedsdesc));
	            action.moveToElement(clicksortbybeds).click().perform();
	        
	           Thread.sleep(4000); //Must wait for this time to load, cant use implicit wait, because it will find the element, but the element is not in the state i want it at yet
    		
	           List <WebElement> searchresultsbedslist = webdriver.findElements(searchresultslistbeds);
	           ArrayList<Integer> desclist = new ArrayList<Integer> ();
	           
	           String property1_bed  = searchresultsbedslist.get(0).getText();
	           int prop1bed = Integer.parseInt(property1_bed);
	           desclist.add(prop1bed);
	           
	           String property2_bed  = searchresultsbedslist.get(1).getText();
	           int prop2bed = Integer.parseInt(property2_bed);
	           desclist.add(prop2bed);
	           
	           String property3_bed  = searchresultsbedslist.get(2).getText();
	           int prop3bed = Integer.parseInt(property3_bed);
	           desclist.add(prop3bed);
	           
	           String property4_bed  = searchresultsbedslist.get(3).getText();
	           int prop4bed = Integer.parseInt(property4_bed);
	           desclist.add(prop4bed);
	           
	           String property5_bed  = searchresultsbedslist.get(4).getText();
	           int prop5bed = Integer.parseInt(property5_bed);
	           desclist.add(prop5bed);

	           String property6_bed  = searchresultsbedslist.get(5).getText();
	           int prop6bed = Integer.parseInt(property6_bed);
	           desclist.add(prop6bed);
	           
	           String property7_bed  = searchresultsbedslist.get(6).getText();
	           int prop7bed = Integer.parseInt(property7_bed);
	           desclist.add(prop7bed);
	           
	           String property8_bed  = searchresultsbedslist.get(7).getText();
	           int prop8bed = Integer.parseInt(property8_bed);
	           desclist.add(prop8bed);
	           
	           String property9_bed  = searchresultsbedslist.get(8).getText();
	           int prop9bed = Integer.parseInt(property9_bed);
	           desclist.add(prop9bed);
        		
	           String property10_bed  = searchresultsbedslist.get(9).getText();
	           int prop10bed = Integer.parseInt(property10_bed);
	           desclist.add(prop10bed);

			   String original = "["+prop1bed+" "+prop2bed+" "+prop3bed+" "+prop4bed+" "+prop5bed+" "+prop6bed+" "+prop7bed+" "+prop8bed+" "+prop9bed+" "+prop10bed+"]";

				         
		       MiscMethods.sortDescending(desclist);
		       String sorteddesc = MiscMethods.returnsStringforIntegerArrayList(desclist);
		            
		            
		       log.info("Original order is: "+original+"\n");
		       log.info("Sorted descending order is: "+sorteddesc+"\n");
		            
	            if (original.equalsIgnoreCase(sorteddesc))
	            {
	            	    log.info("It has sorted in descending order.");
	            	    diditsort = "yes";
	            }
	            else
	            {
	            	    log.error("It failed to sort in descending order.");
	            	    diditsort = "no";
	            }
		            

		       return diditsort;
		    }
			 
			 public String sortResultsByAscBeds (RemoteWebDriver webdriver) throws InterruptedException
			 {
				   WebDriverWait wait = new WebDriverWait (webdriver, 60);
			       WebElement clicksortby = wait.until(ExpectedConditions.elementToBeClickable(sortbyclicktoexpanddrpdown));
			       clicksortby.click();
			    		
			       Thread.sleep(4000); //Must wait for this time to load, cant use implicit wait, because it will find the element, but the element is not in the state i want it at yet
			    		
			       WebElement dropdownareabox = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownarea));
			       Actions action = new Actions(webdriver);
			       action.moveToElement(dropdownareabox).perform();
		           action.sendKeys(Keys.ARROW_DOWN);
		           action.sendKeys(Keys.ARROW_DOWN);
		           WebElement clicksortbybeds= wait.until(ExpectedConditions.elementToBeClickable(sortbydropdpwnbedsasc));
		           action.moveToElement(clicksortbybeds).click().perform();
		           
		           Thread.sleep(4000); //Must wait for this time to load, cant use implicit wait, because it will find the element, but the element is not in the state i want it at yet
		    			        

	 	           List <WebElement> searchresultsbedslist = webdriver.findElements(searchresultslistbeds);
		           ArrayList<Integer> asclist = new ArrayList<Integer> ();
		           
		           String property1_bed  = searchresultsbedslist.get(0).getText();
		           int prop1bed = Integer.parseInt(property1_bed);
		           asclist.add(prop1bed);
		           
		           String property2_bed  = searchresultsbedslist.get(1).getText();
		           int prop2bed = Integer.parseInt(property2_bed);
		           asclist.add(prop2bed);
		           
		           String property3_bed  = searchresultsbedslist.get(2).getText();
		           int prop3bed = Integer.parseInt(property3_bed);
		           asclist.add(prop3bed);
		           
		           String property4_bed  = searchresultsbedslist.get(3).getText();
		           int prop4bed = Integer.parseInt(property4_bed);
		           asclist.add(prop4bed);
		           
		           String property5_bed  = searchresultsbedslist.get(4).getText();
		           int prop5bed = Integer.parseInt(property5_bed);
		           asclist.add(prop5bed);

		           String property6_bed  = searchresultsbedslist.get(5).getText();
		           int prop6bed = Integer.parseInt(property6_bed);
		           asclist.add(prop6bed);
		           
		           String property7_bed  = searchresultsbedslist.get(6).getText();
		           int prop7bed = Integer.parseInt(property7_bed);
		           asclist.add(prop7bed);
		           
		           String property8_bed  = searchresultsbedslist.get(7).getText();
		           int prop8bed = Integer.parseInt(property8_bed);
		           asclist.add(prop8bed);
		           
		           String property9_bed  = searchresultsbedslist.get(8).getText();
		           int prop9bed = Integer.parseInt(property9_bed);
		           asclist.add(prop9bed);
	        		
		           String property10_bed  = searchresultsbedslist.get(9).getText();
		           int prop10bed = Integer.parseInt(property10_bed);
		           asclist.add(prop10bed);

		           String original = "["+prop1bed+" "+prop2bed+" "+prop3bed+" "+prop4bed+" "+prop5bed+" "+prop6bed+" "+prop7bed+" "+prop8bed+" "+prop9bed+" "+prop10bed+"]";

			            
		            MiscMethods.sortAscending(asclist);
		            String sortedasc = MiscMethods.returnsStringforIntegerArrayList(asclist);
		            
		            
		            log.info("Original order is: "+original+"\n");
		            log.info("Sorted ascending order is: "+sortedasc+"\n");
		            
		            if (original.equalsIgnoreCase(sortedasc))
		            {
		            	    log.info("It has sorted in ascending order.");
		            	    diditsort = "yes";
		            }
		            else
		            {
		            	    log.error("It failed to sort in ascending order.");
		            	    diditsort = "no";
		            }
	           
	            return diditsort;
		    }
			 
			public String sortResultsByDescBaths (RemoteWebDriver webdriver) throws InterruptedException
			{
				 
			      WebDriverWait wait = new WebDriverWait (webdriver, 60);
			      WebElement clicksortby = wait.until(ExpectedConditions.elementToBeClickable(sortbyclicktoexpanddrpdown));
			      clicksortby.click();

			      Thread.sleep(4000); //Must wait for this time to load, cant use implicit wait, because it will find the element, but the element is not in the state i want it at yet
			    		
			      WebElement dropdownareabox = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownarea));
			      Actions action = new Actions(webdriver);
			      action.moveToElement(dropdownareabox).perform();
		          action.sendKeys(Keys.ARROW_DOWN);
		          action.sendKeys(Keys.ARROW_DOWN);
			      WebElement clicksortbybaths= wait.until(ExpectedConditions.elementToBeClickable(sortbydropdownbathsdesc));
			      action.moveToElement(clicksortbybaths).click().perform();
			        
			      Thread.sleep(4000); //Must wait for this time to load, cant use implicit wait, because it will find the element, but the element is not in the state i want it at yet

			      List <WebElement> searchresultsbathslist = webdriver.findElements(searchresultslistbaths);
		          ArrayList<Integer> desclist = new ArrayList<Integer> ();
		          
		          String bath1 = searchresultsbathslist.get(0).getText();
		          int prop1bath = Integer.parseInt(bath1);
		          desclist.add(prop1bath);
	            
		          String bath2 = searchresultsbathslist.get(1).getText();
		          int prop2bath = Integer.parseInt(bath2);
		          desclist.add(prop2bath);
		          
		          String bath3 = searchresultsbathslist.get(2).getText();
		          int prop3bath = Integer.parseInt(bath3);
		          desclist.add(prop3bath);
		          
		          String bath4 = searchresultsbathslist.get(3).getText();
		          int prop4bath = Integer.parseInt(bath4);
		          desclist.add(prop4bath);
		          
		          String bath5 = searchresultsbathslist.get(4).getText();
		          int prop5bath = Integer.parseInt(bath5);
		          desclist.add(prop5bath);
		          
		          String bath6 = searchresultsbathslist.get(5).getText();
		          int prop6bath = Integer.parseInt(bath6);
		          desclist.add(prop6bath);
		          
		          String bath7 = searchresultsbathslist.get(6).getText();
		          int prop7bath = Integer.parseInt(bath7);
		          desclist.add(prop7bath);
		          
		          String bath8 = searchresultsbathslist.get(7).getText();
		          int prop8bath = Integer.parseInt(bath8);
		          desclist.add(prop8bath);
		          
		          String bath9 = searchresultsbathslist.get(8).getText();
		          int prop9bath = Integer.parseInt(bath9);
		          desclist.add(prop9bath);
		          
		          String bath10 = searchresultsbathslist.get(9).getText();
		          int prop10bath = Integer.parseInt(bath10);
		          desclist.add(prop10bath);
		          
			      String original = "["+bath1+" "+bath2+" "+bath3+" "+bath4+" "+bath5+" "+bath6+" "+bath7+" "+bath8+" "+bath9+" "+bath10+"]";

			            
		          MiscMethods.sortDescending(desclist);
		          String sorteddesc = MiscMethods.returnsStringforIntegerArrayList(desclist);
		            
		            
		         log.info("Original order is: "+original+"\n");
		         log.info("Sorted descending order is: "+sorteddesc+"\n");
		            
		            if (original.equalsIgnoreCase(sorteddesc))
		            {
		            	    log.info("It has sorted in descending order.");
		            	    diditsort = "yes";
		            }
		            else
		            {
		            	    log.error("It failed to sort in descending order.");
		            	    diditsort = "no";
		            }
		            

		            return diditsort;
		    }
			 
			 public String sortResultsByAscBaths (RemoteWebDriver webdriver) throws InterruptedException
			 {
				  WebDriverWait wait = new WebDriverWait (webdriver, 60);
			      WebElement clicksortby = wait.until(ExpectedConditions.elementToBeClickable(sortbyclicktoexpanddrpdown));
			      clicksortby.click();
			    		
			      Thread.sleep(4000); //Must wait for this time to load, cant use implicit wait, because it will find the element, but the element is not in the state i want it at yet
			    		
			      WebElement dropdownareabox = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownarea));
			      Actions action = new Actions(webdriver);
			      action.moveToElement(dropdownareabox).perform();
		          action.sendKeys(Keys.ARROW_DOWN);
		          action.sendKeys(Keys.ARROW_DOWN);
		          WebElement clicksortbybaths= wait.until(ExpectedConditions.elementToBeClickable(sortbydropdownbathsasc));
		    
		          action.moveToElement(clicksortbybaths).click().perform();
		           
		          Thread.sleep(4000); //Must wait for this time to load, cant use implicit wait, because it will find the element, but the element is not in the state i want it at yet
		    		
			     	            	
			      List <WebElement> searchresultsbathslist = webdriver.findElements(searchresultslistbaths);
		          ArrayList<Integer> asclist = new ArrayList<Integer> ();
		          
		          String bath1 = searchresultsbathslist.get(0).getText();
		          int prop1bath = Integer.parseInt(bath1);
		          asclist.add(prop1bath);
	            
		          String bath2 = searchresultsbathslist.get(1).getText();
		          int prop2bath = Integer.parseInt(bath2);
		          asclist.add(prop2bath);
		          
		          String bath3 = searchresultsbathslist.get(2).getText();
		          int prop3bath = Integer.parseInt(bath3);
		          asclist.add(prop3bath);
		          
		          String bath4 = searchresultsbathslist.get(3).getText();
		          int prop4bath = Integer.parseInt(bath4);
		          asclist.add(prop4bath);
		          
		          String bath5 = searchresultsbathslist.get(4).getText();
		          int prop5bath = Integer.parseInt(bath5);
		          asclist.add(prop5bath);
		          
		          String bath6 = searchresultsbathslist.get(5).getText();
		          int prop6bath = Integer.parseInt(bath6);
		          asclist.add(prop6bath);
		          
		          String bath7 = searchresultsbathslist.get(6).getText();
		          int prop7bath = Integer.parseInt(bath7);
		          asclist.add(prop7bath);
		          
		          String bath8 = searchresultsbathslist.get(7).getText();
		          int prop8bath = Integer.parseInt(bath8);
		          asclist.add(prop8bath);
		          
		          String bath9 = searchresultsbathslist.get(8).getText();
		          int prop9bath = Integer.parseInt(bath9);
		          asclist.add(prop9bath);
		          
		          String bath10 = searchresultsbathslist.get(9).getText();
		          int prop10bath = Integer.parseInt(bath10);
		          asclist.add(prop10bath);
		          
			      String original = "["+bath1+" "+bath2+" "+bath3+" "+bath4+" "+bath5+" "+bath6+" "+bath7+" "+bath8+" "+bath9+" "+bath10+"]";
	            
		          MiscMethods.sortAscending(asclist);
		          String sortedasc = MiscMethods.returnsStringforIntegerArrayList(asclist);
		            
		            
		          log.info("Original order is: "+original+"\n");
		          log.info("Sorted ascending order is: "+sortedasc+"\n");
		            
		            if (original.equalsIgnoreCase(sortedasc))
		            {
		            	    log.info("It has sorted in ascending order.");
		            	    diditsort = "yes";
		            }
		            else
		            {
		            	    log.error("It failed to sort in ascending order.");
		            	    diditsort = "no";
		            }
	           
	            return diditsort;
		    }
		   
}
