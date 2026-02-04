package orangeappautomation.Wrapper;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class wrapper {
    public static void settext(WebDriver driver, WebElement element, String text) {
        waitforElementToBeVisible(driver, element, 10);
        element.clear();
        element.sendKeys(text);
    }

    public static void clickelement(WebDriver driver, WebElement element) {
        elementtobeclickable(driver, element, 10);
        element.click();
    }

    public static void elementtobeclickable(WebDriver driver, WebElement element, int timeinseconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeinseconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitforElementToBeVisible(WebDriver driver, WebElement element, int timeinseconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeinseconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitforElementToBeVisible(WebDriver driver, By locator, int timeinseconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeinseconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static boolean isdisplayed(WebDriver driver,By ele)
    { boolean status;
        try{
             waitforElementToBeVisible(driver,ele,20);
            WebElement elemnt=driver.findElement(ele);
            status= elemnt.isDisplayed();
        }catch(Exception e){
            status=false;
        }
        return status;
    }
   public static String getText(WebDriver driver,WebElement ele)
   {
    return ele.getText();
   }
    public static void menuitemselection(WebDriver driver, String menuitem) {
        By menuitems = By.xpath(
                "//ul[@class='oxd-main-menu']//li//span[contains(@class,'oxd-main-menu-item--name') and normalize-space()='"
                        + menuitem + "']");
        WebElement menu = waitforElementToBeVisible(driver, menuitems, 20);
        clickelement(driver, menu);

    }

    public static List<WebElement> waitforlistofElements(WebDriver driver, By ele, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ele));
    }

    public static List<WebElement> visiblityiflistofele(WebDriver driver, WebElement ele, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfAllElements(ele));
    }

    public static boolean verifyRecordsbasedonsearchtext(WebDriver driver, List<WebElement> element,
            String searchtext) {

        if (element.isEmpty()) {
            return false;
        }
        for (WebElement e : element) {
            String text = e.getText();
            if (!text.equalsIgnoreCase(searchtext)) {
                return false;
            }
        }

        return true;
    }

    public static void clcikondropdown(WebDriver driver, WebElement dropdown, String optiontext) {
        clickelement(driver, dropdown);
        By locator = By.xpath("//div[@role='listbox']");
        waitforElementToBeVisible(driver, locator, 0);
        WebElement ele = driver.findElement(locator);
        List<WebElement> optionslocator = ele
                .findElements(By.xpath(".//*[contains(normalize-space(text()),'" + optiontext + "')]"));

        for (WebElement option : optionslocator) {
            String text = option.getText();
            if (text.equalsIgnoreCase(optiontext)) {
                clickelement(driver, option);
                break;
            }
        }
    }

    public static void selevtEmployeeName(WebDriver driver, WebElement element, String optiontext) {
        Actions actions = new Actions(driver);
        element.click();
        element.clear();

        actions.sendKeys(optiontext).perform();

        actions.pause(Duration.ofMillis(700)).perform();
        By locator = By.xpath("//div[@role='listbox']");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        waitforElementToBeVisible(driver, locator, 30);
        actions.sendKeys(Keys.ARROW_DOWN).perform();
        actions.sendKeys(Keys.ENTER).perform();

    }

    public static boolean vlaidateforindivdualfilter(WebDriver driver, By element,
            String searchtext) {
        List<WebElement> visiblelist = waitforlistofElements(driver, element, 20);
        if (visiblelist.size() == 0)
            return false;
        return wrapper.verifyRecordsbasedonsearchtext(driver, visiblelist, searchtext);

    }

   public static String logger(String log)
   {
        long timeStamp=System.currentTimeMillis();
        String logmsg=String.format("Info :: %s : %s",timeStamp,log);
        return logmsg;

   }

    public static void searchuser(WebDriver driver, String username, WebElement usernameele, String role,
            WebElement userrole, String employeename, WebElement employeenametextbox, String status,
            WebElement statusdropdown, WebElement searchbtn) {

        if (username != null && !username.isEmpty()) {
            waitforElementToBeVisible(driver, usernameele, 20);
            settext(driver, usernameele, username);

        }
        if (role != null && !role.isEmpty()) {
            waitforElementToBeVisible(driver, userrole, 10);

            wrapper.clcikondropdown(driver, userrole, role);
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
        clickelement(driver, searchbtn);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
