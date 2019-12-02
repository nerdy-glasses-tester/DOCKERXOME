package base;

import org.testng.annotations.AfterMethod;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import common.ExcelMethods;
import common.MiscMethods;
import common.ReadProperties;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;



public class TestBase {

	//****************************************//
	//***                                  ***//
	//*** Created by Angela Tong APR 2018  ***//
	//***                                  ***//
	//****************************************//
	
	//Run mvn dependency:copy-dependencies -DoutputDirectory=lib to extract all the maven dependencies/jars into the lib folder
	//If using for windows, please change paths to windows paths.
	
        @SuppressWarnings("rawtypes")
	    protected ThreadLocal<RemoteWebDriver> driverThread = new ThreadLocal<>();
	    @SuppressWarnings("rawtypes")
		protected  ThreadLocal<AppiumDriver> mdriverThread = new ThreadLocal<>();
	    
		public static final String basePath = System.getProperty("user.dir");
	    
	    public static String folderpath = basePath+"/screencaptures";
	    
	    
	    public String androidORios = "";
	    public String runonrealdevice = "";
		public String methodname = "";
	    public String whichNodeURL = "";
	    //public String host = "localhost"; //run from local mac use this
	    public String host = "hub"; //run from docker image and container then use this from docker-compose.yaml services hub
		public String completeURL= "http://"+host+":4444/wd/hub";
	    public String mobile1URL = "http://0.0.0.0:xxxx/wd/hub";
	    public String mobile2URL = "http://0.0.0.0:xxxx/wd/hub";
	    
		public String firstDeviceName="ecbfd827b282a9dc";
		public String secondDeviceName="HT4CJJT00982";
	    
		
		DesiredCapabilities capabilities = new DesiredCapabilities();

        @SuppressWarnings({ "rawtypes"})
		@BeforeMethod (alwaysRun=true)
		//Use before method instead of before class or before test so each method/test will open in new browser; 
		//This was tested and found before method was the only one that works.
	    @Parameters({"browser", "platform", "deviceID"})
		public void setDriver(@Optional ("") String browser, @Optional ("") String platform, @Optional ("") String deviceID, ITestContext ctx) throws Exception{
	         
        	ReadProperties.retrieveGlobalProperties();
        	
			
			String testName = ctx.getCurrentXmlTest().getName();
			
			if(System.getProperty("HUB_HOST") !=null) {
				host=System.getProperty("HUB_HOST");
			}

			
			if (ReadProperties.globalProp.getProperty("webautomation").contains("yes") && ReadProperties.globalProp.getProperty("mobileautomation").contains("no"))
			{
				if(browser.contains("firefox") && platform.contains("WINDOWS"))
				{
					
					System.setProperty("webdriver.gecko.driver", basePath+ReadProperties.globalProp.getProperty("pcgeckodriver"));
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					firefoxOptions.setCapability("name", testName);
					firefoxOptions.setCapability("platform", platform);
					firefoxOptions.setCapability("browserName", browser);
					firefoxOptions.setCapability("newCommandTimeout", 5000);
					firefoxOptions.setCapability(FirefoxDriver.MARIONETTE, true);
					firefoxOptions.setCapability("takesScreenshot", true);
					//firefoxOptions.setBinary("C:/Program Files/Mozilla Firefox/firefox.exe"); //use when running on actual non DOCKER computers
					
					try
					{
						driverThread.set(new RemoteWebDriver(new URL(completeURL), firefoxOptions));
					} catch (MalformedURLException e) {
						e.printStackTrace();
				    }
					
					
				}
				else if(browser.contains("firefox") && platform.contains("LINUX"))
				{

					System.setProperty("webdriver.gecko.driver", basePath+ReadProperties.globalProp.getProperty("geckodriver"));
					FirefoxOptions macfirefoxOptions = new FirefoxOptions();
					macfirefoxOptions.setCapability("name", testName);
					macfirefoxOptions.setCapability("platform", platform);
					macfirefoxOptions.setCapability("browserName", browser);
					macfirefoxOptions.setCapability("newCommandTimeout", 5000);
					macfirefoxOptions.setCapability(FirefoxDriver.MARIONETTE, true);
					macfirefoxOptions.setCapability("takesScreenshot", true);
					//macfirefoxOptions.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox-bin"); //use when running on actual non DOCKER computers
					
					
					try
					{
						driverThread.set(new RemoteWebDriver(new URL(completeURL), macfirefoxOptions));
					} catch (MalformedURLException e) {
						e.printStackTrace();
				    }
					
				}
				else if (browser.contains("chrome") && platform.contains("WINDOWS"))
				{
					System.setProperty("webdriver.chrome.driver", basePath+ReadProperties.globalProp.getProperty("pcchromedriver"));
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.setCapability("name", testName);
					chromeOptions.setCapability("platform", platform);
					chromeOptions.setCapability("browserName", browser);
					chromeOptions.setCapability("newCommandTimeout", 5000);
					chromeOptions.setCapability("takesScreenshot", true);
					
					
					try
					{
						driverThread.set(new RemoteWebDriver(new URL(completeURL), chromeOptions));
					} catch (MalformedURLException e) {
						e.printStackTrace();
				    }
					
				    
				}
				else if (browser.contains("chrome") && platform.contains("LINUX"))
				{
					System.setProperty("webdriver.chrome.driver", basePath+ReadProperties.globalProp.getProperty("chromedriver"));
					ChromeOptions macchromeOptions = new ChromeOptions();
					macchromeOptions.setCapability("name", testName);
					macchromeOptions.setCapability("platform", platform);
					macchromeOptions.setCapability("browserName", browser);
					macchromeOptions.setCapability("newCommandTimeout", 5000);
					macchromeOptions.setCapability("takesScreenshot", true);
					
					try
					{
						driverThread.set(new RemoteWebDriver(new URL(completeURL), macchromeOptions));
					} catch (MalformedURLException e) {
						e.printStackTrace();
				    }
					
					
				}
				else if (browser.contains("safari") && platform.contains("MAC"))
				{
					SafariOptions safariOptions = new SafariOptions();
					safariOptions.setCapability("name", testName);
					safariOptions.setCapability("platform", platform);
					safariOptions.setCapability("browserName", browser);
					safariOptions.setCapability("newCommandTimeout", 5000);
					safariOptions.setCapability("takesScreenshot", true);
					
					try
					{
						driverThread.set(new RemoteWebDriver(new URL(completeURL), safariOptions));
					} catch (MalformedURLException e) {
						e.printStackTrace();
				    }
					
					
				}
				//edge or MicrosoftEdge try for name of browser
				else if (browser.contains("MicrosoftEdge") && platform.contains("WINDOWS"))
				{
					System.setProperty("webdriver.edge.driver", basePath+ReadProperties.globalProp.getProperty("edgedriver"));
					EdgeOptions edgeOptions = new EdgeOptions();
					edgeOptions.setCapability("name", testName);
					edgeOptions.setCapability("platform", platform);
					edgeOptions.setCapability("browserName", browser);
					edgeOptions.setCapability("ensureCleanSession", true);
					edgeOptions.setCapability("ignoreZoomSetting", true);
					edgeOptions.setCapability("ignoreProtectedModeSettings", true);
					edgeOptions.setCapability("ignore-certificate-error", true);
					edgeOptions.setCapability("acceptSslCerts", "true");
					edgeOptions.setCapability("newCommandTimeout", 5000);
					edgeOptions.setCapability("takesScreenshot", true);
					
					try
					{
						driverThread.set(new RemoteWebDriver(new URL(completeURL), edgeOptions));
					} catch (MalformedURLException e) {
						e.printStackTrace();
				    }
					
				}
				//try iexplorer or internet explorer for browser name
				else if(browser.contains("iexplorer") && platform.contains("WINDOWS"))
				{
					System.setProperty("webdriver.ie.driver", basePath+ReadProperties.globalProp.getProperty("iedriver"));
					InternetExplorerOptions iexplorerOptions = new InternetExplorerOptions();
					iexplorerOptions.setCapability("name", testName);
					iexplorerOptions.setCapability("platform", platform);
					iexplorerOptions.setCapability("browserName", browser);
					iexplorerOptions.setCapability("ensureCleanSession", true);
					iexplorerOptions.setCapability("ignoreZoomSetting", true);
					iexplorerOptions.setCapability("ignoreProtectedModeSettings", true);
					iexplorerOptions.setCapability("ignore-certificate-error", true);
					iexplorerOptions.setCapability("capabilityType.ACCEPT_SSL_CERTS", true);
					iexplorerOptions.setCapability("newCommandTimeout", 5000);
					iexplorerOptions.setCapability("takesScreenshot", true);
					
					try
					{
						driverThread.set(new RemoteWebDriver(new URL(completeURL), iexplorerOptions));
					} catch (MalformedURLException e) {
						e.printStackTrace();
				    }
					
					
				}

				
				
				
				androidORios="NA";
				driverThread.get().manage().deleteAllCookies();
			    String weburl = ReadProperties.globalProp.getProperty("weburl");
			    driverThread.get().get(weburl);
			    Thread.sleep(1000);
			    driverThread.get().manage().window().maximize(); 
			    Thread.sleep(1000);


			}
			else if (ReadProperties.globalProp.getProperty("webautomation").contains("no") && ReadProperties.globalProp.getProperty("mobileautomation").contains("yes"))
			{

					if (deviceID.equalsIgnoreCase(firstDeviceName) && ReadProperties.globalProp.getProperty("mobiledevice").contains("Android")&& ReadProperties.globalProp.getProperty("runonrealdevice").contains("yes"))
					{
						String appfile=ReadProperties.globalProp.getProperty("simulatormacandroidappfile");
						
						MiscMethods.exec(firstDeviceName, "uninstall io.appium.uiautomator2.server");
						MiscMethods.exec(firstDeviceName, "uninstall io.appium.uiautomator2.server.test");
						MiscMethods.exec(firstDeviceName, "uninstall io.appium.settings");
						Thread.sleep(30000);
						
						//Remove "Unlock" and "Appium settings" apps from Android device
                        //Appium installs those apps automatically. (In my case those apps were not compatible after updating Android from version 6 to version 7.
			            
	
						
						capabilities.setCapability("deviceName",firstDeviceName);
						capabilities.setCapability("udid", firstDeviceName);
						capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "GalaxyTabA2017");
						capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
						capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.0");
						capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");//need this or else can't locate some elements
						capabilities.setCapability("wdaLocalPort", 8200);
						capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100000); 
						//must put the apk in here and remove app package and app activity or app wont load in appium mobile grid
						capabilities.setCapability(MobileCapabilityType.APP, appfile);

						
						if (ReadProperties.globalProp.getProperty("reset").contains("fullReset")) { // uninstall and install client
				            System.out.println("Driver DO FULL-RESET");
				            capabilities.setCapability("fullReset", true);
				            capabilities.setCapability("noReset", false);
				        } else if (ReadProperties.globalProp.getProperty("reset").contains("fastReset")) { // clears cache and settings without reinstall
				            System.out.println("Driver DO FAST-RESET");
				            capabilities.setCapability("fullReset", false);
				            capabilities.setCapability("noReset", false);
				        } else { // just start client
				            System.out.println("Driver DO NORMAL start"); 
				            capabilities.setCapability("fullReset", false);
				            capabilities.setCapability("noReset", true);
				        }
						
						
						
						//If the application under test is supposed to be already installed on the device (noReset=true) then at least appActivity and appPackage options are required to be set, since no package manifest is available in such case.
						//run this at start of test to cleanup
						//adb -s ecbfd827b282a9dc uninstall io.appium.uiautomator2.server
						//adb -s ecbfd827b282a9dc uninstall io.appium.uiautomator2.server.test
						//adb -s ecbfd827b282a9dc uninstall io.appium.settings

						//adb -s HT4CJJT00982 uninstall io.appium.uiautomator2.server
						//adb -s HT4CJJT00982 uninstall io.appium.uiautomator2.server.test
						//adb -s HT4CJJT00982 uninstall io.appium.settings
						
						capabilities.setCapability("–session-override",true);
						//capabilities.setCapability("appPackage", "com.xome.android");
						//capabilities.setCapability("appActivity", "com.xome.android.feature.mapsearch.view.MapActivity2");
						//capabilities.setCapability("appPackage", "com.xome.android");
						//capabilities.setCapability("appActivity", "com.xome.android/.ui.map.MapActivity2");
						
						//adb shell
						//dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'
						//first open app see this different apppackage com.google.android.packageinstaller/com.android.packageinstaller.permission.ui.GrantPermissionsActivity
						//com.xome.android/.ui.map.MapActivity2
						
						runonrealdevice = "yes";
						androidORios="Android";
						whichNodeURL=mobile1URL;	
						mdriverThread.set(new AndroidDriver <MobileElement> (new URL(whichNodeURL), capabilities));
					}
					else if (deviceID.equalsIgnoreCase(secondDeviceName) && ReadProperties.globalProp.getProperty("mobiledevice").contains("Android")&& ReadProperties.globalProp.getProperty("runonrealdevice").contains("yes"))
					{
						String appfile=ReadProperties.globalProp.getProperty("simulatormacandroidappfile");
						
						MiscMethods.exec(secondDeviceName, "uninstall io.appium.uiautomator2.server");
						MiscMethods.exec(secondDeviceName, "uninstall io.appium.uiautomator2.server.test");
						MiscMethods.exec(secondDeviceName, "uninstall io.appium.settings");
						
						Thread.sleep(20000);
						
						//Remove "Unlock" and "Appium settings" apps from Android device
                        //Appium installs those apps automatically. (In my case those apps were not compatible after updating Android from version 6 to version 7.


						
						capabilities.setCapability("deviceName",secondDeviceName);
						capabilities.setCapability("udid", secondDeviceName);
						capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus");
						capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
						capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");
						capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");//need this or else can't locate some elements
						capabilities.setCapability("wdaLocalPort", 8201);
						capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100000); 
						//must put the apk in here and remove app package and app activit or app wont load in appium mobile grid
						capabilities.setCapability(MobileCapabilityType.APP, appfile);


						if (ReadProperties.globalProp.getProperty("reset").contains("fullReset")) { // uninstall and install client
				            System.out.println("Driver DO FULL-RESET");
				            capabilities.setCapability("fullReset", true);
				            capabilities.setCapability("noReset", false);
				        } else if (ReadProperties.globalProp.getProperty("reset").contains("fastReset")) { // clears cache and settings without reinstall
				            System.out.println("Driver DO FAST-RESET");
				            capabilities.setCapability("fullReset", false);
				            capabilities.setCapability("noReset", false);
				        } else { // just start client
				            System.out.println("Driver DO NORMAL start"); 
				            capabilities.setCapability("fullReset", false);
				            capabilities.setCapability("noReset", true);
				        }
	
			            
						//If the application under test is supposed to be already installed on the device (noReset=true) then at least appActivity and appPackage options are required to be set, since no package manifest is available in such case.
						//run this at start of test to cleanup
						//adb -s ecbfd827b282a9dc uninstall io.appium.uiautomator2.server
						//adb -s ecbfd827b282a9dc uninstall io.appium.uiautomator2.server.test
						//adb -s ecbfd827b282a9dc uninstall io.appium.settings

						//adb -s HT4CJJT00982 uninstall io.appium.uiautomator2.server
						//adb -s HT4CJJT00982 uninstall io.appium.uiautomator2.server.test
						//adb -s HT4CJJT00982 uninstall io.appium.settings
						
						capabilities.setCapability("–session-override",true);
						//capabilities.setCapability("appPackage", "com.xome.android");
						//capabilities.setCapability("appActivity", "com.xome.android.feature.mapsearch.view.MapActivity2");
						//capabilities.setCapability("appPackage", "com.xome.android");
						//capabilities.setCapability("appActivity", "com.xome.android/.ui.map.MapActivity2");
						
						//adb shell
						//dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'
						//first open app see this different apppackage com.google.android.packageinstaller/com.android.packageinstaller.permission.ui.GrantPermissionsActivity
						//com.xome.android/.ui.map.MapActivity2
						
						runonrealdevice = "yes";
						androidORios="Android";
						whichNodeURL=mobile2URL;	
						mdriverThread.set(new AndroidDriver <MobileElement> (new URL(whichNodeURL), capabilities));

					}
					else if (ReadProperties.globalProp.getProperty("mobiledevice").contains("iOS")&& ReadProperties.globalProp.getProperty("runonrealdevice").contains("yes"))
					{
     
			            File appDir = new File(ReadProperties.globalProp.getProperty("iosappfile")); //this ipa was done with automatic signing
			            File app = new File(appDir, "TestApp.ipa");
			            capabilities.setCapability("app", app.getAbsolutePath());
						capabilities.setCapability("platformName", "iOS");     
						capabilities.setCapability("platformVersion", "10.3.3");     
						capabilities.setCapability("deviceName", "iPhone 5");     
						capabilities.setCapability("udid", "8319807bbbc1d04c9bbc0634e14d28aca946b536");
						capabilities.setCapability("automationName", "Appium");
						capabilities.setCapability("appiumVersion","7.0.0");
						capabilities.setCapability("agentPath", "/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/WebDriverAgent.xcodeproj"); //must use this or won't work
						capabilities.setCapability("bootstrapPath", "/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent"); //must use this or won't work
						capabilities.setCapability("xcodeOrgid", "Angela Tong");
						capabilities.setCapability("xcodeSigningId", "iPhone Developer");
			            capabilities.setCapability("fullReset", false);
			            capabilities.setCapability("noReset", true);
						capabilities.setCapability("newCommandTimeout", 120);    

						
						runonrealdevice = "yes";
						androidORios="iOS";
						whichNodeURL=completeURL;	
						mdriverThread.set(new IOSDriver <MobileElement> (new URL(whichNodeURL), capabilities));

					}
					else if (ReadProperties.globalProp.getProperty("simulator").contains("Android")&& ReadProperties.globalProp.getProperty("runonrealdevice").contains("no"))
					{
						//Tested work for Android simulator
						//Mac
			            File appDir = new File(ReadProperties.globalProp.getProperty("simulatormacandroidappfile"));
					    //Windows
			            //File appDir = new File(ReadProperties.globalProp.getProperty("simulatorpcandroidappfile"));
			            File app = new File(appDir, "base.apk");
			            capabilities.setCapability("app", app.getAbsolutePath());
						capabilities.setCapability("platformName", "Android");
						capabilities.setCapability("platformVersion", "6.0");     
						capabilities.setCapability("deviceName", "Nexus 4");  
						//capabilities.setCapability("session-override", true); //set this in appium desktop settings
						capabilities.setCapability("newCommandTimeout", 120);
						 // Launches the below android virtual device and waits for 120 seconds for AVD to be ready
						capabilities.setCapability("avd", "Nexus_4_AVD"); //need underscore for spaces in name for appium inspector
						capabilities.setCapability("avdReadyTimeout", 120000);
						capabilities.setCapability("appPackage", "com.xome.android");
						capabilities.setCapability("appActivity", "com.xome.android.ui.map.MapActivity2");
						
						androidORios="Android";
						whichNodeURL=completeURL;	
						mdriverThread.set(new AndroidDriver <MobileElement> (new URL(whichNodeURL), capabilities));
			
						
					}
					else if (ReadProperties.globalProp.getProperty("simulator").contains("iOS")&& ReadProperties.globalProp.getProperty("runonrealdevice").contains("no"))
					{
     
			            File appDir = new File(ReadProperties.globalProp.getProperty("simulatormaciosappfile"));
			            File app = new File(appDir, "TestApp.app");
						capabilities.setCapability("platformName", "iOS");
						capabilities.setCapability("platformVersion", "11.0");   
						capabilities.setCapability("deviceName", "iPhone Simulator"); 
			            capabilities.setCapability("app", app.getAbsolutePath());
						capabilities.setCapability("newCommandTimeout", 120);
						
						androidORios="iOS";
						whichNodeURL=completeURL;	
						mdriverThread.set(new IOSDriver <MobileElement> (new URL(whichNodeURL), capabilities));

					}
				   
					

			}
			
				
	    }


        protected RemoteWebDriver getDriver() {
            return driverThread.get();
          }    
        	
        @SuppressWarnings("rawtypes")
    	protected AppiumDriver getAppiumDriver() {
            return mdriverThread.get();
          }   

        
    	@DataProvider(parallel=true, name = "getData")
    	public Object[][] getData(Method method) throws Exception {
    		String testName= method.getName();
    		Object[][] arrayObject = null;
    			arrayObject = ExcelMethods.getDataFromExcelTestData(testName);

    		return (arrayObject);
    	}
        
    	@DataProvider(parallel=true, name = "getMobileData")
    	public Object[][] getMobileData(Method method) throws Exception {
    		String testName= method.getName();
    		Object[][] arrayObject = null;
    			arrayObject = ExcelMethods.getMobileDataFromExcelTestData(testName);

    		return (arrayObject);
    	}
    	
    	
    	@AfterMethod(alwaysRun=true) 
    	public void OnFailure(ITestResult testResult) throws IOException { 
    		if (testResult.getStatus() == ITestResult.FAILURE) 
    		{ 
    			System.out.println(testResult.getStatus()); 
    		} 
    	} 


		@AfterMethod(alwaysRun=true)
        //Use after method instead of after class or after test so each method/test will open in new browser; 
      	//This was tested and found after method was the only one that works.  	
        public void tearDown() {
       

    	    if(androidORios == "NA")
    	    {
    	    		getDriver().quit();
    	    }
    	    else if (androidORios == "Android")
    	    {
    	    		getAppiumDriver().quit();
    	    }
    	    else if (androidORios == "iOS")
    	    {
	    		getAppiumDriver().quit();
    	    }
 
        }


}
