package orangeappautomation.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import orangeappautomation.pages.AdminPage;
import orangeappautomation.pages.LoginPage;
import orangeappautomation.pages.LogoutPage;

public class Admintest extends BaseTest {
    AdminPage adminpage;
    LoginPage loginpage;
    LogoutPage logoutpage;

    @BeforeClass
    public void setupmethod() {
        loginpage = new LoginPage(driver);
          adminpage = new AdminPage(driver);
          
        loginpage.validuservalidation("Admin", "admin123");
        adminpage.selectmenu("Admin");
      
        
    }
    @Test(priority=1)
    public void adminsearchByUsername() {
       
        adminpage.searchforuser("Admin", "", "", "");
        Assert.assertTrue(adminpage.vlaidateforindivdualuser("Admin"), "User not found");
      
    }

    @Test(priority = 2)
    public void searchByuserrole() {
       
        adminpage.searchforuser("", "ESS", "", "");
        Assert.assertTrue(adminpage.vlaidateforindivdualrole("ESS"), "User role not found");
        
    }

    @Test(enabled = false)
    public void searchByEmployeeName() {
        loginpage = new LoginPage(driver);
        loginpage.validuservalidation("Admin", "admin123");
        adminpage = new AdminPage(driver);
        adminpage.selectmenu("Admin");
        adminpage.searchforuser("", "", "Letha  Auer", "");
        Assert.assertTrue(adminpage.vlaidateforindivdualemployee("Letha  Auer"), "User role not found");
    }

    @Test(enabled = false)
    public void searchBystatus() {
        loginpage = new LoginPage(driver);
        loginpage.validuservalidation("Admin", "admin123");
        adminpage = new AdminPage(driver);
        adminpage.selectmenu("Admin");
        adminpage.searchforuser("", "", "", "Enabled");
        Assert.assertTrue(adminpage.vlaidateforuserSatus("Enabled"), "User role not found");
    }

    @Test(priority = 3)
    public void allfilter() {
     
        adminpage.selectmenu("Admin");
        adminpage.searchforuser("lethaauer893", "ESS", "Letha  Auer", "Enabled");
        Assert.assertTrue(adminpage.vlaidateforindivdualuser("lethaauer893"), "User not found");
        Assert.assertTrue(adminpage.vlaidateforindivdualrole("ESS"), "User role not found");
        Assert.assertTrue(adminpage.vlaidateforindivdualemployee("Letha Auer"), "User role not found");
        Assert.assertTrue(adminpage.vlaidateforuserSatus("Enabled"), "User role not found");
    }
    @Test(enabled=false)
    public void serachstatusbyDisabled() {
        loginpage = new LoginPage(driver);
        loginpage.validuservalidation("Admin", "admin123");
        adminpage = new AdminPage(driver);
        adminpage.selectmenu("Admin");
        adminpage.searchforuser("", "", "", "Disabled");
        Assert.assertTrue(adminpage.vlaidateforuserSatus("Disabled"), "User role not found");
    }

    @Test(enabled=false)
    public void verifyreset()
    {
          loginpage = new LoginPage(driver);
        loginpage.validuservalidation("Admin", "admin123");
        adminpage = new AdminPage(driver);
        adminpage.selectmenu("Admin");
        adminpage.searchforuser("Jammie1632", "ESS", "Lilyan Hayden Ryan", "Enabled");
    }
   

}
