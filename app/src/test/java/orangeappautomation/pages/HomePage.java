package orangeappautomation.pages;

import static orangeappautomation.Wrapper.wrapper.isdisplayed;
import static orangeappautomation.Wrapper.wrapper.waitforElementToBeVisible;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import orangeappautomation.Wrapper.wrapper;

public class HomePage {
    protected WebDriver driver;
    public HomePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath="//a[@class='oxd-brand']")
    private WebElement logo;
    By locator=By.xpath("//a[@class='oxd-brand']");
    public  boolean logovisiblity()
    {
      //  waitforElementToBeVisible(driver,logo,20);
       boolean status= isdisplayed(driver,locator );
       return status;
    }
    
}
