package orangeappautomation.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import orangeappautomation.pages.AdminPage;
import orangeappautomation.pages.LoginPage;

public class Admintest extends BaseTest {
    AdminPage adminpage;
    LoginPage loginpage;

    @Test(enabled = false)
    public void adminsearchByUsername() {
        loginpage = new LoginPage(driver);
        loginpage.validuservalidation("Admin", "admin123");
        adminpage = new AdminPage(driver);
        adminpage.searchforuser("Admin", "", "", "");
        Assert.assertTrue(adminpage.vlaidateforindivdualuser("Admin"), "User not found");

    }

    @Test(enabled = false)
    public void searchByuserrole() {
        loginpage = new LoginPage(driver);
        loginpage.validuservalidation("Admin", "admin123");
        adminpage = new AdminPage(driver);
        adminpage.searchforuser("", "ESS", "", "");
        Assert.assertTrue(adminpage.vlaidateforindivdualrole("ESS"), "User role not found");
    }

    @Test(enabled = false)
    public void searchByEmployeeName() {
        loginpage = new LoginPage(driver);
        loginpage.validuservalidation("Admin", "admin123");
        adminpage = new AdminPage(driver);
        adminpage.searchforuser("", "", "Lilyan Hayden Ryan", "");
        Assert.assertTrue(adminpage.vlaidateforindivdualemployee("Lilyan Ryan"), "User role not found");
    }

    @Test(enabled = false)
    public void searchBystatus() {
        loginpage = new LoginPage(driver);
        loginpage.validuservalidation("Admin", "admin123");
        adminpage = new AdminPage(driver);
        adminpage.searchforuser("", "", "", "Enabled");
        Assert.assertTrue(adminpage.vlaidateforuserSatus("Enabled"), "User role not found");
    }

    @Test
    public void allfilter() {
        loginpage = new LoginPage(driver);
        loginpage.validuservalidation("Admin", "admin123");
        adminpage = new AdminPage(driver);
        adminpage.searchforuser("Jammie1632", "ESS", "Lilyan Hayden Ryan", "Enabled");
        Assert.assertTrue(adminpage.vlaidateforindivdualuser("Jammie1632"), "User not found");
        Assert.assertTrue(adminpage.vlaidateforindivdualrole("ESS"), "User role not found");
        Assert.assertTrue(adminpage.vlaidateforindivdualemployee("Lilyan Ryan"), "User role not found");
        Assert.assertTrue(adminpage.vlaidateforuserSatus("Enabled"), "User role not found");
    }

}
