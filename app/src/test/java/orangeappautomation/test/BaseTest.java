package orangeappautomation.test;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import orangeappautomation.uitility.DriverSingleton;

public class BaseTest {
    protected WebDriver driver;
    @BeforeMethod
    public void setup(){
       DriverSingleton.intializedriver("chrome");
       driver=DriverSingleton.getDriver();
       driver.manage().window().maximize();   
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");      
        
    }

    @AfterMethod
    public void browserteardoun()
    {
        DriverSingleton.teardownDriver();
    }

}
