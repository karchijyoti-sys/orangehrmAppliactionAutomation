package orangeappautomation.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import orangeappautomation.uitility.ExcelReaderUtil;
import static orangeappautomation.uitility.ExcelReaderUtil.*;
import static orangeappautomation.uitility.ExtentTestManager.loginfo;
import static orangeappautomation.uitility.ExtentTestManager.startTest;
import static orangeappautomation.Wrapper.wrapper.*;

import orangeappautomation.pages.HomePage;
import orangeappautomation.pages.LoginPage;

public class LoginTestcases extends BaseTest {
    LoginPage loginpage;
    HomePage homepage;

    @Test(priority = 1, dataProvider = "login")
    public void TC_01(String TestcaseId, String username, String password, String expectedresult) throws IOException {
        startTest(TestcaseId, "Login validation using Excel-driven data");
        loginpage = new LoginPage(driver);
        homepage = new HomePage(driver);
        boolean status = false;
        String erromsg="";
        logger("Step-1 Enter Username : " + username + " and password" + password);
        loginfo("Step-1 Enter Username : " + username);
        loginfo("Step-1 Enter Password :" + password);
       try{
         loginpage.validuservalidation(username, password);
        switch (expectedresult.toUpperCase()) {
            case "VALID":
                status = homepage.logovisiblity();
                if(!status){
                   erromsg="Homepgae logo is not diaplyed";
                }
                logger("step 2 vlaidate logo is visible " + status);
                loginfo("step 2 vlaidate logo is visible " + status);
                Assert.assertTrue(status, erromsg);
                 
                break;
            case "INVALID":
                String expectederrorMsg = "Invalid credentials";
                 if(loginpage.InvalidCredErrormsgvisiblity()){
                 logger("step 2 vlaidate Error mag is visible " + status);
                loginfo("step 2 vlaidate Errormsg   " + loginpage.InvalidCredErrormsg());
                 erromsg=loginpage.InvalidCredErrormsg();
                status=erromsg.equals(expectederrorMsg);
                 }else{
                    erromsg="Invalid credential error not displayed";
                    status=false;
                 }
               ;
                break;
            case "BLANKUSERNAME":
                String expectedres = "Required";
                if(loginpage.BlankUsernamevisibility()){
                 logger("step 2 vlaidate Error mag is visible " + status);
                loginfo("step 2 vlaidate Errormsg   " + loginpage.errorMsgBlankUsername());
                
                erromsg=loginpage.errorMsgBlankUsername();
                status=erromsg.equals(expectedres);
                }else{
                    erromsg="Invalid msg for blankusername";
                    status=false;
                }
                break;
            case "BLANKPASSWORD":
                String expectedrespwd = "Required";
                if(loginpage.Blankpasswordvisibility()){
                logger("step 2 vlaidate Error mag is visible " + status);
                loginfo("step 2 vlaidate Errormsg   " + loginpage.errorMsgBlankPassword());
                
                erromsg=loginpage.errorMsgBlankPassword();
                status=erromsg.equals(expectedrespwd);
               
                }else{
                    erromsg="Invalid msg for blankpassword";
                    status=false;
                }
                break;
            default:
                 status = false;
                erromsg = "Unknown ExpectedResult: " + expectedresult;

        }
    }catch(AssertionError ae)
    {
        status=false;
      erromsg=ae.getMessage();
      throw ae;
    }catch(Exception e)
    {
        status=false;
        erromsg=e.getMessage();
        throw e;
    }finally{
      ExcelReaderUtil.writeIntoExcel("Login", TestcaseId, status,erromsg);
    }
    }

    @DataProvider(name = "login")
    public static Object[][] dataprovider() throws FileNotFoundException {
        List<Map<String, String>> totalrows = ExcelReaderUtil.excelreader("Login");
        List<Map<String, String>> filetreddata = ExcelReaderUtil.filtered(totalrows, "RunMode", "Y");
        for (Map<String, String> res : filetreddata) {
            System.out.println(res);
        }
        return ExcelReaderUtil.toDataProvider(filetreddata, "TestcaseId", "Username", "Password", "ExpectedResult");

    }
}
