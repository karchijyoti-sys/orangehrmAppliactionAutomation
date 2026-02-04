package orangeappautomation.uitility;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
   private static final String ROOT_DIR = System.getProperty("user.dir")+File.separator+"screenshots";
    private ScreenshotUtil() {
    }

    public static String getScreenshotPath(WebDriver driver,String testMethodName,String status) {
       
        String timestamp=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        if(driver==null)
        {
            return null;
        }
        String directory=ROOT_DIR+File.separator+status;
        File dir=new File(directory);
        if(!dir.exists())
        {
            dir.mkdirs();
        }
        //String filePath = System.getProperty("user.dir") + "/screenshots/" + testMethodName + ".png";
        
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File src=screenshot.getScreenshotAs(OutputType.FILE);
        String filePath=directory+File.separator+testMethodName+"_"+timestamp+"_"+status+".png";
        File dest=new File(filePath);
        try{
            FileUtils.copyFile(src, dest);
        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

       return filePath;

    }
}
