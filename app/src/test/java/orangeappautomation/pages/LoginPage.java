package orangeappautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static orangeappautomation.Wrapper.wrapper.*;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
       this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(name="username")
    private WebElement username;
    @FindBy(name="password")
    private WebElement password;
    @FindBy(xpath="//button[contains(@class,'login')]")
    private WebElement loginButton;

    public void enterUsername(String user){
       settext(driver,username,user);
    }

    public void enterpassword(String pass){
         settext(driver,password,pass);
    }

    public void clickLoginButton(){
        clickelement(driver,loginButton);
    }

    public void validuservalidation(String user,String pass){
        waitforElementToBeVisible(driver,username,20);
        waitforElementToBeVisible(driver, loginButton, 10);
        enterUsername(user);
        enterpassword(pass);
        clickLoginButton();
    }


}
