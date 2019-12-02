package webPageObjects;

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

public class FilterResults_Page {
	
	//****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	final static Logger log = LogManager.getLogger(FilterResults_Page.class);
	
	private By anypricedropdown = By.id("ddbtn-criteria-pricerange");
	private By minpricefield = By.id("mapsearch-criteria-minprice");
	private By maxpricefield = By.id("mapsearch-criteria-maxprice");
	private By confirmpricedrpdown = By.cssSelector("div#ddbtn-label-pricerange>span.ddbtn-label-arrow.fa.fa-angle-down");
	private By sortbyclicktoexpanddrpdown = By.cssSelector("div.search-results-sorter>div.btn-group.bootstrap-select>button.btn.dropdown-toggle.btn-default>span.filter-option.pull-left");
	private By dropdownarea = By.cssSelector("div.btn-group.bootstrap-select.open>div.dropdown-menu.open");
	private By sortbydropdownpricedesc = By.cssSelector("div.search-results-sorter>div.btn-group.bootstrap-select.open>div.dropdown-menu.open>ul.dropdown-menu.inner>li[data-original-index='1']>a>span.text");
	private By sortbydropdownpriceasc = By.cssSelector("div.search-results-sorter>div.btn-group.bootstrap-select.open>div.dropdown-menu.open>ul.dropdown-menu.inner>li[data-original-index='2']>a>span.text");
	private By searchresultsprice = By.cssSelector("div.price>span[itemprop='price']");
	
	private By anybedsdropdown = By.id("ddbtn-criteria-beds");
    private By selectedbedsdrpdown = By.cssSelector("span.dd-info[data-suff='Beds']");
	private By searchresultsbeds = By.xpath("//span[contains(text(), 'bd')]/preceding-sibling::span");
    
	private By anybathsdropdown = By.id("ddbtn-criteria-baths");
    private By selectedbathsdrpdown = By.cssSelector("button#ddbtn-criteria-baths>div.ddbtn-criteria-label>span");
    private By searchresultsbaths = By.xpath("//span[contains(text(), 'ba')]/preceding-sibling::span");

	private String diditfilter ="";
	
	public void filterByPrice (RemoteWebDriver webdriver, String price1, String price2) throws InterruptedException 
	{
		
		WebDriverWait wait = new WebDriverWait (webdriver, 60);
		WebElement pricedrpdown = wait.until(ExpectedConditions.elementToBeClickable(anypricedropdown));
		pricedrpdown.click();
		
		Actions action = new Actions(webdriver);
		WebElement minprice = wait.until(ExpectedConditions.elementToBeClickable(minpricefield));
		action.moveToElement(minprice).click().perform();
		action.sendKeys(price1).perform();
	
		WebElement maxprice = wait.until(ExpectedConditions.elementToBeClickable(maxpricefield));
		action.moveToElement(maxprice).click().perform();
		action.sendKeys(price2).perform();
		
		WebElement confirmpricedrp = wait.until(ExpectedConditions.elementToBeClickable(confirmpricedrpdown));
		confirmpricedrp.click();

		Thread.sleep(4000); //Must wait
		
    	WebElement clicksortby = wait.until(ExpectedConditions.elementToBeClickable(sortbyclicktoexpanddrpdown));
    	clicksortby.click();

    	Thread.sleep(4000); //Must wait
    		
    	WebElement dropdownareabox = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownarea));
    	action.moveToElement(dropdownareabox).perform();
        action.sendKeys(Keys.ARROW_DOWN);
        action.sendKeys(Keys.ARROW_DOWN);
    	WebElement clicksortbyprice = wait.until(ExpectedConditions.elementToBeClickable(sortbydropdownpricedesc));
    	action.moveToElement(clicksortbyprice).click().perform(); 
        
        Thread.sleep(4000); //Must wait 
		
	}
	
    public String verifyFilterByPrice (RemoteWebDriver webdriver, String price1, String price2) throws InterruptedException 
	{
    	WebDriverWait wait = new WebDriverWait (webdriver, 60);
    	List <WebElement> maxsearchresultspricelist = webdriver.findElements(searchresultsprice);
    	String highestprice = maxsearchresultspricelist.get(0).getText();
	    highestprice=highestprice.replaceAll(",", "");
	    String highest_price = highestprice.substring(1);
	    int prop1pricemax = Integer.parseInt(highest_price);
	    
	    log.info("Filter results max price is "+highestprice+"\n");
	    
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
	    
	    
    	List <WebElement> minsearchresultspricelist = webdriver.findElements(searchresultsprice); 
		String lowestprice = minsearchresultspricelist.get(0).getText(); 
	    lowestprice=lowestprice.replaceAll(",", "");
	    String lowest_price = lowestprice.substring(1);
	    int prop1pricemin = Integer.parseInt(lowest_price);
	    
		log.info("Filter results min price is "+lowestprice+"\n");
		
		int searchminprice = Integer.parseInt(price1.replaceAll(",", ""));
		int searchmaxprice = Integer.parseInt(price2.replaceAll(",", ""));
		
		log.info("Search for price between "+searchminprice+" and "+searchmaxprice +"\n");
	    
		if ((prop1pricemin >= searchminprice) && (prop1pricemax <= searchmaxprice) )
		{
			 log.info("It filtered by price correctly.");
		     diditfilter = "yes";
		}
		else
		{
			 log.error("It filtered by price incorrectly.");
			diditfilter = "no";
		}
		return diditfilter;
	 
    }

	public void filterByBeds (RemoteWebDriver webdriver, String bed) throws InterruptedException 
    {

	    //Have to add 2 to count the bed header and the first bed selection of any
	    String i = bed;
	    int j = Integer.parseInt(i)+2;
		By numberofbeds = By.cssSelector("ul#dd-criteria-beds>li:nth-of-type("+j+")");
		
		WebDriverWait wait = new WebDriverWait(webdriver, 60);
		WebElement beddrpdown = wait.until(ExpectedConditions.elementToBeClickable(anybedsdropdown));
		beddrpdown.click();
		
		Thread.sleep(4000); //Must wait
		
		WebElement beds = wait.until(ExpectedConditions.presenceOfElementLocated(numberofbeds));
		Actions action = new Actions(webdriver);
		action.moveToElement(beds).click().perform();

		Thread.sleep(4000); //Must wait
    }
	
	public String verifyFilterByBeds (RemoteWebDriver webdriver, String bed) throws InterruptedException 
    {
		WebDriverWait wait = new WebDriverWait(webdriver, 60);
		List <WebElement> resultsbedslist = webdriver.findElements(searchresultsbeds);
		
		String numofbeds1 = resultsbedslist.get(0).getText();
		String numofbeds2 = resultsbedslist.get(1).getText();
		String numofbeds3 = resultsbedslist.get(2).getText();
		String numofbeds4 = resultsbedslist.get(3).getText();
		String numofbeds5 = resultsbedslist.get(4).getText();
		
		int beds1 = Integer.parseInt(numofbeds1);
		int beds2 = Integer.parseInt(numofbeds2);
		int beds3 = Integer.parseInt(numofbeds3);
		int beds4 = Integer.parseInt(numofbeds4);
		int beds5 = Integer.parseInt(numofbeds5);
		int originalbed = Integer.parseInt(bed);
		
		WebElement selectedbeds = wait.until(ExpectedConditions.presenceOfElementLocated(selectedbedsdrpdown));
		String selectedbedsdrpdowntext  = selectedbeds.getText(); 
		
		
		if ((selectedbedsdrpdowntext.equalsIgnoreCase("3+ Beds"))&&(beds1 >= originalbed) && (beds2 >= originalbed) && (beds3 >= originalbed) && (beds4 >= originalbed) && (beds5 >= originalbed))
		{
			diditfilter = "yes";
			log.info("It filtered by beds correctly.");
		}
		else
		{
			diditfilter = "no";
			log.error("It filtered by beds incorrectly.");
		}


		
		return diditfilter;
	 
    }
	
	
	public void filterByBaths (RemoteWebDriver webdriver, String bath) throws InterruptedException 
    {
	    //Have to add 2 to count the bath header and the first bath selection of any
		String i = bath;
	    int j = Integer.parseInt(i) + 2;
		By numberofbaths = By.cssSelector("ul#dd-criteria-baths>li:nth-of-type("+j+")"); 
		
		WebDriverWait wait = new WebDriverWait(webdriver, 60);
		WebElement bathdrpdown = wait.until(ExpectedConditions.elementToBeClickable(anybathsdropdown));
		bathdrpdown.click();
		
		Thread.sleep(4000); //Must wait	
		
		WebElement baths = wait.until(ExpectedConditions.presenceOfElementLocated(numberofbaths));
		Actions action = new Actions(webdriver);
		action.moveToElement(baths).click().perform();

		Thread.sleep(4000); //Must wait
    }
	
	public String verifyFilterByBaths (RemoteWebDriver webdriver, String bath) throws InterruptedException 
    {
		WebDriverWait wait = new WebDriverWait(webdriver, 60);
		List <WebElement> resultsbathslist = webdriver.findElements(searchresultsbaths);
		
		String numofbaths1 = resultsbathslist.get(0).getText();
		String numofbaths2 = resultsbathslist.get(1).getText();
		String numofbaths3 = resultsbathslist.get(2).getText();
		String numofbaths4 = resultsbathslist.get(3).getText();
		String numofbaths5 = resultsbathslist.get(4).getText();

		int baths1 = Integer.parseInt(numofbaths1);
		int baths2 = Integer.parseInt(numofbaths2);
		int baths3 = Integer.parseInt(numofbaths3);
		int baths4 = Integer.parseInt(numofbaths4);
		int baths5 = Integer.parseInt(numofbaths5);
		int originalbath = Integer.parseInt(bath);
		
		WebElement selectedbaths = wait.until(ExpectedConditions.presenceOfElementLocated(selectedbathsdrpdown));
		String selectedbathsdrpdowntext  = selectedbaths.getText(); 
		
		
		if ((selectedbathsdrpdowntext.equalsIgnoreCase("3+ Baths"))&&(baths1 >= originalbath) && (baths2 >= originalbath) && (baths3 >= originalbath) && (baths4 >= originalbath) && (baths5 >= originalbath))
		{
			diditfilter = "yes";
			log.info("It filtered by baths correctly.");
		}
		else
		{
			diditfilter = "no";
			log.error("It filtered by baths incorrectly.");
		}


		
		return diditfilter;
	 
	 
    }
    
    
	
}
