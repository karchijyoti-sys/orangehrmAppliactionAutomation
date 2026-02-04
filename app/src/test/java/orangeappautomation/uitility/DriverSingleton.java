package orangeappautomation.uitility;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class DriverSingleton {
    private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
   // private static DriverSingleton instance=new DriverSingleton();

    private DriverSingleton() {
        
    }

    

    public static WebDriver getDriver(String browser)
    {
        if(driver.get()==null)
        {
            switch(browser.toLowerCase())
            {
                case "chrome" :
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver());
                    driver.get().manage().window().maximize();
                    break;
                case "firefox" :
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new ChromeDriver());
                    driver.get().manage().window().maximize();
                    break;
                    default :
                       new  Exception("Browser not found");

            }

        }
        return driver.get();
    }

    public static void teardownDriver()
    {
        if(driver.get()!=null)
        {
            driver.get().quit();
            driver.remove();
        }
    }

}
