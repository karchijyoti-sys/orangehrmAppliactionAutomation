package orangeappautomation.test;

import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.Test;

import orangeappautomation.pages.LoginPage;

public class loginTestcases extends BaseTest{
    LoginPage loginpage;
    @Test(enabled=false)
    public void vlaidLogin()
    {
        loginpage=new LoginPage(driver);
        loginpage.validuservalidation("Admin", "admin123");
        String expectedTitle="OrangeHRM";
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle,"Title does not match");
    }
}
