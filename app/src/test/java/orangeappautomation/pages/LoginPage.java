package orangeappautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static orangeappautomation.Wrapper.wrapper.*;

public class LoginPage {
    static WebDriver  driver;
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
    
    private static By errormsginvalidcre=By.xpath("//div[@role=\"alert\"]//p");
    private static By blankusernameelement=By.xpath("//label[text()='Username']/parent::div[contains(@class, 'wrapper')]/following-sibling::span");
    private static By blankpassword=By.xpath("//label[text()='Password']/parent::div[contains(@class, 'wrapper')]/following-sibling::span");

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
     
    public static boolean InvalidCredErrormsgvisiblity()
    {
       return  isdisplayed(driver, errormsginvalidcre);
    }

    public static String InvalidCredErrormsg()
    {
       return driver.findElement(errormsginvalidcre).getText().trim();
    }
    
    public static boolean BlankUsernamevisibility()
    {
        return  isdisplayed(driver, blankusernameelement); 
    }

    public static String errorMsgBlankUsername()
    {
          return driver.findElement(blankusernameelement).getText().trim();
    }
    public static boolean Blankpasswordvisibility()
    {
        return  isdisplayed(driver, blankpassword); 
    }

    public static String errorMsgBlankPassword()
    {
            return driver.findElement(blankpassword).getText().trim();
    }

}
