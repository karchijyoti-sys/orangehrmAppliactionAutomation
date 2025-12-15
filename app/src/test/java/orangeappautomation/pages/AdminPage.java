package orangeappautomation.pages;

import static orangeappautomation.Wrapper.wrapper.clickelement;
import static orangeappautomation.Wrapper.wrapper.menuitemselection;
import static orangeappautomation.Wrapper.wrapper.selevtEmployeeName;
import static orangeappautomation.Wrapper.wrapper.settext;
import static orangeappautomation.Wrapper.wrapper.waitforElementToBeVisible;
import static orangeappautomation.Wrapper.wrapper.waitforlistofElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import orangeappautomation.Wrapper.wrapper;

public class AdminPage {
    WebDriver driver;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//label[text()='Username']/../following-sibling::div//input")
    private WebElement usenametextbox;
    @FindBy(xpath = "//label[text()='Employee Name']/../following-sibling::div//input")
    private WebElement employeenametextbox;
    @FindBy(xpath = "//div//button[@type='submit']")
    private WebElement searchbtn;
    By usernamelist = By.xpath("//div[@class='oxd-table-card']//div[@role='row']//div[2]/div");

    // @FindAll({@FindBy(xpath
    // ="//div[@class='oxd-table-card']//div[@role='row']//div[3]/div")})
    // private List<WebElement> userrolelist;
    By userrolelist = By.xpath("//div[@class='oxd-table-card']//div[@role='row']//div[3]/div");
    By employeelist = By.xpath("//div[@class='oxd-table-card']//div[@role='row']//div[4]/div");
    By statuslist=By.xpath("//div[@class='oxd-table-card']//div[@role='row']//div[5]/div");
    @FindBy(xpath = "//label[contains(text(),'User Role')]/../following-sibling::div/div")
    private WebElement userroledropdown;
    @FindBy(xpath = "//div[@role='listbox']")
    private WebElement opt;
    @FindBy(xpath = "//label[contains(text(),'Status')]/../following-sibling::div/div")
    private WebElement statusdropdown;

    public void enterusername(String username) {
        settext(driver, usenametextbox, username);
    }

    public void clickSearchButton() {
        clickelement(driver, searchbtn);
    }

    public void searchforuser(String user, String role, String employeename, String status) {
        menuitemselection(driver, "Admin");
        if (user != null && !user.isEmpty()) {
            waitforElementToBeVisible(driver, usenametextbox, 20);
            enterusername(user);

        }
        if (role != null && !role.isEmpty()) {
            waitforElementToBeVisible(driver, userroledropdown, 10);

            wrapper.clcikondropdown(driver, userroledropdown, role);
        }
        if (employeename != null && !employeename.isEmpty()) {
            waitforElementToBeVisible(driver, employeenametextbox, 20);
            selevtEmployeeName(driver, employeenametextbox, employeename);

        }
        if (status != null && !status.isEmpty()) {

            waitforElementToBeVisible(driver, statusdropdown, 10);

            wrapper.clcikondropdown(driver, statusdropdown, status);
        }
        waitforElementToBeVisible(driver, searchbtn, 20);
        clickSearchButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public boolean vlaidateforindivdualuser(String searchtext) {
        return wrapper.vlaidateforindivdualfilter(driver, usernamelist, searchtext);
    }

    public boolean vlaidateforindivdualrole(String searchtext) {
        return wrapper.vlaidateforindivdualfilter(driver, userrolelist, searchtext);
    }

    public boolean vlaidateforindivdualemployee(String searchtext) {
        return wrapper.vlaidateforindivdualfilter(driver, employeelist, searchtext);
    }
    public boolean vlaidateforuserSatus(String searchtext) {
        return wrapper.vlaidateforindivdualfilter(driver, statuslist, searchtext);
    }
}
