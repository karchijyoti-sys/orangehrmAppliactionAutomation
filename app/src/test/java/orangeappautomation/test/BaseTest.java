package orangeappautomation.test;

import java.lang.reflect.Method;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import static orangeappautomation.uitility.ExtentTestManager.*;
import static orangeappautomation.uitility.ScreenshotUtil.getScreenshotPath;

import static orangeappautomation.Wrapper.wrapper.*;
import orangeappautomation.uitility.DriverSingleton;

public class BaseTest {
    protected static WebDriver driver;
    final String URL="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"; 
    @BeforeSuite(alwaysRun = true)
    public void setup(){
       
       driver=DriverSingleton.getDriver("chrome");
       driver.manage().window().maximize();   
        driver.get(URL); 
        logger("Lauched url"+URL);
        driver.manage().window().maximize();     
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
    }
//    @BeforeMethod(alwaysRun = true)
//    public void runBeforeEcahTestMethod(Method method)
//    {
//        logger("current test method is "+ method.getName());
//         startTest(method.getName(), "Test case "+method.getName()+" started");
        
//    }

   @AfterMethod(alwaysRun = true)
    public void runAfterEachTestMethod(ITestResult res)
    {
        if(res.getStatus()==ITestResult.SUCCESS)
        {
            logpass("Test case passed");
        }
        else if(res.getStatus()==ITestResult.FAILURE)
        {
           
            logfail(res.getThrowable().getMessage(),getScreenshotPath(driver, res.getName(),"FailureScreenshots"));
        }
        else if(res.getStatus()==ITestResult.SKIP)
        {
            logskip("Test case skipped");
        }
         endTest();
        
    }
   
  

    @AfterSuite(alwaysRun = true)
    public void browserteardoun()
    {
        DriverSingleton.teardownDriver();
        flushReport();
       
    }


    
}
