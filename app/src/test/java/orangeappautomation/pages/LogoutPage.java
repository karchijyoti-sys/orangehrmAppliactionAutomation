package orangeappautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static orangeappautomation.Wrapper.wrapper.*;

public class LogoutPage {
    WebDriver driver;
   public LogoutPage(WebDriver driver){
      this.driver=driver;
      PageFactory.initElements(driver,this);  
    }

    @FindBy(xpath="//a[contains(text(),'Logout')]")
    private WebElement logoutlink;
   @FindBy(xpath="//span[contains(@class,'userdropdown')]")
   private WebElement userdropdown;
    public void clicklogoutlink(){
       clickelement(driver, userdropdown);
        clickelement(driver,logoutlink);
    }
}
