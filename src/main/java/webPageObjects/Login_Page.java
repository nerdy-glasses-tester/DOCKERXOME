package webPageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_Page {
	
	//****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	final static Logger log = LogManager.getLogger(Login_Page.class);
	
	private By signinlink = By.cssSelector("a.LinkButton.btn.btn-secondary");
	private By emaillogin = By.id("security_loginname");
	private By pwdlogin = By.id("security_password");
	private By submitbtn = By.cssSelector("input.btn.btn-primary");
	private By emailpwddoesntmatchtext = By.xpath(".//*[@id=\"customerLoginForm\"]/div[3]/ul/li[contains(text(), \"Oops, the e-mail or password doesn't match.\")]");  //Oops, the e-mail or password doesn't match.
	private By onemoreloginchancetext = By.cssSelector("div.row-fluid.errorMessageBox.errorMessageBoxServerSide>ul>li"); //You have 1 more login attempt before your account is locked. Please enter your login credentials properly, reset your password by clicking the \"Forgot your password ? \" link or contact Customer Service at 1-844-400-9663 for assistance.
	private By verifysignedin = By.cssSelector("div.user-section.authenticated>div.NavSubmenu.btn-group>div.NavSubmenu-button>span.NavItem.top-level.user-menu>span");
	
	
    public void clickSignIn (RemoteWebDriver webdriver)
    {
    		WebDriverWait wait = new WebDriverWait (webdriver, 60);
    		WebElement signin = wait.until(ExpectedConditions.elementToBeClickable(signinlink));
        signin.click(); 
	    WebElement iframeSwitch = webdriver.findElement(By.id("login-iframe"));
	    webdriver.switchTo().frame(iframeSwitch);
    }

    
    public String signinBlankFields (RemoteWebDriver webdriver)
    {
    		WebDriverWait wait = new WebDriverWait (webdriver, 60);
		WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitbtn));
    		
        submit.click();
        
    		WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(emaillogin));
        String fieldisrequired = email.getAttribute("required");
        
	    log.info("Check email field is required: "+fieldisrequired);

	    return fieldisrequired;
    }
    
   
    public String signinWrongPwd (RemoteWebDriver webdriver, String login, String password) throws InterruptedException
    {
    		WebDriverWait wait = new WebDriverWait (webdriver, 60);
    		WebElement email = wait.until(ExpectedConditions.elementToBeClickable(emaillogin));
    		WebElement pwd = wait.until(ExpectedConditions.elementToBeClickable(pwdlogin));
    		WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitbtn));
        email.click();
    		email.clear();
        email.sendKeys(login);
        
        pwd.click();
        pwd.clear();
        pwd.sendKeys(password);

        submit.click();
        
        Thread.sleep(2000);
	    
    		WebElement wrongpwd_text = wait.until(ExpectedConditions.presenceOfElementLocated(emailpwddoesntmatchtext));
        String wrongemailpwdtext = wrongpwd_text.getText();
        
	    log.info("Wrong password text is : "+wrongemailpwdtext);

	    return wrongemailpwdtext;
    }
    
    public String login (RemoteWebDriver webdriver, String login, String password) throws InterruptedException
    {
    	WebDriverWait wait = new WebDriverWait (webdriver, 60);
    	WebElement email = wait.until(ExpectedConditions.elementToBeClickable(emaillogin));
    	WebElement pwd = wait.until(ExpectedConditions.elementToBeClickable(pwdlogin));
    	WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitbtn));
        email.click();
    	email.clear();
        email.sendKeys(login);
        
        pwd.click();
        pwd.clear();
        pwd.sendKeys(password);

        submit.click();
        
        //Wait a bit for it to signin
        Thread.sleep(30);
        //Have to switch to defaultContent or can't find future elements/objects
        webdriver.switchTo().defaultContent();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(verifysignedin));
        WebElement signedinuser = webdriver.findElement(verifysignedin);
        String signedinusername = signedinuser.getText();
        log.info("Signed in user name is: "+signedinusername);

        return signedinusername;
    }
    
}
