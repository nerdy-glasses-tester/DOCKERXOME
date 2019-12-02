package webPageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Gmail {
	
	//Selenium 3.11 doesn't work with actions click for these gmail methods below. I had to revert back to Selenium 3.6.
	//Tested using latest firefox browser 59.0.2 with the same code in both test runs.
	
	        //****************************************//
			//***                                  ***//
			//*** Created by Angela Tong Apr 2018  ***//
			//***                                  ***//
			//****************************************//
			final static Logger log = LogManager.getLogger(Gmail.class);
			
			private By loginemail = By.cssSelector("div.Xb9hP>input.whsOnd.zHQkBf");
			private By nextbtn = By.xpath(".//span[@class='RveJvd snByac' and contains(text(),'Next')]");
			private By pwd = By.cssSelector("div#password>div.aCsJod.oJeWuf>div.aXBtI.Wic03c>div.Xb9hP>input.whsOnd.zHQkBf");
			private By pwdnextbtn = By.xpath(".//span[@class='RveJvd snByac' and contains(text(), 'Next')]");
			
			private By searchemailfield = By.cssSelector("input.gb_Qe");
			private By searchemailbutton = By.cssSelector("button.gb_0e.gb_1e>svg");
			private By emailfrom = By.cssSelector("div.yW>span.bA4>span.zF");
			private By emailtitle = By.cssSelector("div.y6>span.bog>span.bqe");
			//private By thankyoutext1 = By.xpath(".//sup[1]/parent::td[1]");//Thank you for registering for
			private By thankyoutext1 = By.cssSelector("td[class*='message_header2']"); //Thank you for registering for
		    //private By emailtext = By.xpath(".//strong/parent::td"); //Your username is:
			private By emailtext = By.cssSelector("td[class*='message_header3']"); //Your username is:

			/*** Doesn't work
		    private By toptooltipbar = By.cssSelector("div[gh='tm']");
		    private By deletetoptooltip= By.cssSelector("div.asa>div.ar9.T-I-J3.J-J5-Ji");
		    ***/
			private By threedotsmenu = By.cssSelector("div.T-I.J-J5-Ji.T-I-Js-Gs.aap.T-I-awG.T-I-ax7.L3[aria-label='More']");
			private By threedotsopenmenudrpdown = By.cssSelector("div.b7.J-M");
			private By deletethismsglink = By.cssSelector("div.cj[act='11']>img.dS.J-N-JX");
			
		    private By expandmorebtn = By.cssSelector("span.CJ");
		    private By trashlink = By.cssSelector("a.J-Ke.n0[aria-label='Trash']");
		 
		    private By emptytrashnow = By.cssSelector("span.x2");
		    private By emptytrashnowok = By.cssSelector("button.J-at1-auR.J-at1-atl");
		    
		    
		    public  String [] checkRegisterEmail (RemoteWebDriver webdriver, String email, String gmailpwd, String gmailsearch) throws InterruptedException 
		    {
		    	webdriver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		    	WebDriverWait wait = new WebDriverWait (webdriver, 90);
		    	WebElement login = wait.until(ExpectedConditions.elementToBeClickable(loginemail));
		        login.clear();
		        login.sendKeys(email);
	    		WebElement next1 = wait.until(ExpectedConditions.elementToBeClickable(nextbtn));
			    next1.click();
			    
			    WebElement password = wait.until(ExpectedConditions.elementToBeClickable(pwd));
			    password.clear();
			    password.sendKeys(gmailpwd);
			    WebElement next2 = wait.until(ExpectedConditions.elementToBeClickable(pwdnextbtn));
			    next2.click();
			    
			    log.info("Wait 60secs before searching for email.");
			    Thread.sleep(60000);
			    WebElement search_field = wait.until(ExpectedConditions.elementToBeClickable(searchemailfield));
			    WebElement searchbtn = wait.until(ExpectedConditions.elementToBeClickable(searchemailbutton));
			    search_field.click();
			    search_field.clear();
			    search_field.sendKeys(gmailsearch);
			    searchbtn.click();
			    
			    
			    //Wait up to 3 minutes for registration email to come into gmail inbox.
			    
			    WebDriverWait wait2 = new WebDriverWait (webdriver, 180);
			    WebElement email_from = wait2.until(ExpectedConditions.presenceOfElementLocated(emailfrom));
			    String xomename = email_from.getAttribute("innerHTML");
			    
			    WebElement email_title = wait2.until(ExpectedConditions.elementToBeClickable(emailtitle));
			    String welcometoxome = email_title.getText();
			    
			    String [] emailarray = new String [4];
			    emailarray[0] = xomename;
			    emailarray[1]= welcometoxome;
			    
			    email_title.click();
			    
			    Thread.sleep(5000);//Need some wait here or intermittently causes false failures when text doesn't fully load.
			    
			    WebElement thankyou1;
			    WebElement yourusernameis;
			    String thanks = "";
			    String yourusername = "";
			    try
			    {
			    		thankyou1 = wait.until(ExpectedConditions.presenceOfElementLocated(thankyoutext1));
			    		thanks = thankyou1.getText();
			    }
			    catch (Exception e)
			    {
			    		log.error("Can't get thank you for registering text in email.");
			    }
			    
			    try
			    {
				    yourusernameis = wait.until(ExpectedConditions.presenceOfElementLocated(emailtext));
				    yourusername = yourusernameis.getText();
			    }
			    catch (Exception e)
			    {
			    		log.error("Can't get username text in email.");
			    }

	    
			    emailarray[2] = thanks;
			    emailarray[3] = yourusername;
			    
			    return emailarray;
			    
		    }
		    

		    public void deleteGmail (RemoteWebDriver webdriver) throws InterruptedException 
		    {

	    			//((JavascriptExecutor) webdriver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	    			//log.info("Scroll up to height of window via javascript.");

	    			
	    			WebDriverWait wait = new WebDriverWait (webdriver, 60);
	    			Actions action = new Actions(webdriver);
			    action.sendKeys(Keys.ARROW_UP);
			    action.sendKeys(Keys.ARROW_UP);
			    action.sendKeys(Keys.ARROW_UP);
			    action.sendKeys(Keys.ARROW_UP);
			    action.sendKeys(Keys.ARROW_UP);
			    action.sendKeys(Keys.ARROW_UP);
			    action.sendKeys(Keys.ARROW_UP);
			    action.sendKeys(Keys.ARROW_UP).click().perform();
    			    Thread.sleep(1000);
    			
    			    log.info("Scroll up to height of window.");
    			
	    			try {
		    			WebElement threedots = wait.until(ExpectedConditions.elementToBeClickable(threedotsmenu));
		    			threedots.click();
		    			Thread.sleep(3000);
	    				log.info("Found three dots menu.");
	    			}
	    			catch (Exception e)
	    			{
	    				log.error("Can't find three dots menu.");
	    			}

	    			try
	    			{
	    				WebElement threedotsopenmenudrpdownoptions = wait.until(ExpectedConditions.presenceOfElementLocated(threedotsopenmenudrpdown));
				    action.moveToElement(threedotsopenmenudrpdownoptions).perform();
				    try
				    {
					    WebElement deletethismsglnk = wait.until(ExpectedConditions.elementToBeClickable(deletethismsglink));
					    action.moveToElement(deletethismsglnk).click().build().perform();
					    log.info("Clicked delete this message link without arrowing down.");
				    }
				    catch (Exception e)
				    {
					    action.sendKeys(Keys.ARROW_DOWN);
					    action.sendKeys(Keys.ARROW_DOWN);
					    action.sendKeys(Keys.ARROW_DOWN);
					    action.sendKeys(Keys.ARROW_DOWN);
					    action.sendKeys(Keys.ARROW_DOWN);
					    action.sendKeys(Keys.ARROW_DOWN).click().build().perform();
					    log.info("Clicked delete this message link by arrowing down.");
				    }
					    
				    Thread.sleep(1000);
				    log.info("Click delete option from three dots dropdown menu.");
	    			}
	    			catch (Exception e)
	    			{
					log.error("Can't click delete option from three dots dropdown menu.");
	    			}
	    			

	    			try
	    			{
	    				WebElement expandmorebtnsidebar = wait.until(ExpectedConditions.elementToBeClickable(expandmorebtn));
	    				((JavascriptExecutor) webdriver).executeScript("arguments[0].click();", expandmorebtnsidebar);
	    				Thread.sleep(1000);
	    				log.info("Found side bar expand more button link.");
	    			}
	    			catch (Exception e)
	    			{
	    				log.error("Can't find side bar expand more button link.");
	    			}

	    			try {
	    				
    				    action.sendKeys(Keys.ARROW_DOWN).build().perform();				    
    				    action.sendKeys(Keys.ARROW_DOWN).build().perform();


			    	    WebElement trashlnk = wait.until(ExpectedConditions.elementToBeClickable(trashlink));
			    	    ((JavascriptExecutor) webdriver).executeScript("arguments[0].click();", trashlnk);
	    			    Thread.sleep(1000);
	    				log.info("Scrolled down and found trash link after expanding more button on the sidebar.");
	    			}
	    			catch (Exception e)
	    			{
	    				log.error("Can't scroll down to find trash link after expanding more button on the sidebar.");
	    			}
	    			

	    			try
	    			{
			    	    WebElement emptytrashnowlink = wait.until(ExpectedConditions.elementToBeClickable(emptytrashnow));
			    	    action.moveToElement(emptytrashnowlink).click().build().perform();
	    				log.info("Click empty trash now link.");
	    			}
	    			catch (Exception e)
	    			{
	    				log.error("Can't find empty trash now link.");
	    			}
	    			
	    			try
	    			{
			    	    WebElement emptytrashnowokbtn = wait.until(ExpectedConditions.elementToBeClickable(emptytrashnowok));
			    	    emptytrashnowokbtn.click();
	    				log.info("Click empty trash now ok button.");
	    			}
	    			catch (Exception e)
	    			{
	    				log.error("Can't find empty trash now ok button popup.");
	    			}

		    	    
		 
		    }
		    
}
