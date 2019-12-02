package webPageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Search {

	    //****************************************//
		//***                                  ***//
		//*** Created by Angela Tong Apr 2018  ***//
		//***                                  ***//
		//****************************************//
		final static Logger log = LogManager.getLogger(Search.class);
		
		private By xomelogo = By.className("SiteHeadLogo-image");
		private By homepagesearchfield = By.id("homepage-search-field");
		private By searchautocompletebycity = By.cssSelector("div.autocomplete-entry>div.text>span.location");
		private By searchautocompletebyaddress = By.cssSelector("div.autocomplete-entry>div.text>span.name");
		private By searchbutton = By.cssSelector("button.call-to-action.search-field-button");
		private By searchingmsg = By.cssSelector("div#mapsearch-searching-message[style='top: 163.984px']>div#display-message");
		private By nomoresearchingmsg = By.id("div#mapsearch-searching-message[style='top: 163.984px; opacity: 0; display: none;']>div#display-message");	
		private By searchresults = By.cssSelector("div.mapsearch-singleprop.mapsearch-map-singleprop.mapsearch-two-columns-view.included.slick-already-processed");
		private By addressline2 = By.cssSelector("div.address-line-2.second-field");
		private By searchbyaddressresult_address1= By.cssSelector("h1.address-line-1.first-field.bolded");
		private By searchbyaddressresult_address2= By.cssSelector("div.address-line-2.second-field");
		
	    public void searchByCity (RemoteWebDriver webdriver, String searchkeyword)
	    {
	    	WebDriverWait wait = new WebDriverWait (webdriver, 60);
	    	WebElement searchbuy = wait.until(ExpectedConditions.elementToBeClickable(homepagesearchfield));
	        searchbuy.click(); 
            searchbuy.clear();
            searchbuy.sendKeys(searchkeyword);
            
            WebElement searchbtn = wait.until(ExpectedConditions.elementToBeClickable(searchbutton));
            searchbtn.click();
            
	    }
	    
	    public boolean loginsearchfor6thhouseinresults (RemoteWebDriver webdriver, String searchkeyword)
	    {
	    	Search search = new Search();
	    	search.searchByCity(webdriver, searchkeyword); 
	    		
	    	WebDriverWait wait = new WebDriverWait (webdriver, 60);
	    	List<WebElement> searchr = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) searchresults));
	        searchr.get(5).click();
            WebElement addline2 = wait.until(ExpectedConditions.presenceOfElementLocated(addressline2));
            String addressline2text = addline2.getText();
            
            if(addressline2text.contains(searchkeyword))
            {
            	return true;
            }
            else
            {
            	return false;
            }
            
	    }
	
	    
	    public void searchSpecificAddress (RemoteWebDriver webdriver, String address)
	    {
	    	WebDriverWait wait = new WebDriverWait (webdriver, 60);
	    	WebElement searchbuy = wait.until(ExpectedConditions.elementToBeClickable(homepagesearchfield));
	        searchbuy.click(); 
            searchbuy.clear();
            searchbuy.sendKeys(address);

            WebElement autocomplete = wait.until(ExpectedConditions.elementToBeClickable(searchautocompletebyaddress));
            autocomplete.click();
            
            webdriver.manage().timeouts().implicitlyWait(60,TimeUnit.MILLISECONDS);//Need some time for page to load in order to be able to locate some elements later.
 	       
                
        }
	    
	    public String searchandVerifyAddressSearched(RemoteWebDriver webdriver, String address)
	    {
			Search search = new Search();
			search.searchSpecificAddress(webdriver,address);
			WebDriverWait wait = new WebDriverWait (webdriver, 60);
			WebElement resultaddress1 = wait.until(ExpectedConditions.visibilityOfElementLocated(searchbyaddressresult_address1));
			WebElement resultaddress2 = wait.until(ExpectedConditions.visibilityOfElementLocated(searchbyaddressresult_address2));
			String address1=resultaddress1.getText();
			String address2=resultaddress2.getText();
			String resultaddress=address1+" "+address2;
			log.info("resultaddress is: "+resultaddress);
	    		return resultaddress;
	    	
	    }
	    
}
