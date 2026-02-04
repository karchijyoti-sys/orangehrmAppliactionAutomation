package orangeappautomation.uitility;

import static orangeappautomation.uitility.ExtentReportManager.getReport;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentTestManager {
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static synchronized void startTest(String testname, String description) {
        ExtentTest extenttest = getReport().startTest(testname, description);
        test.set(extenttest);

    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static synchronized void endTest() {
        getReport().endTest(test.get());
        test.remove();
    }

    public static synchronized void flushReport() {
        getReport().flush();
    }

    public static void loginfo(String msg) {
        getTest().log(LogStatus.INFO, msg);
    }

    public static void logpass(String msg) {
        getTest().log(LogStatus.PASS, msg);
    }

    public static void logfail(String msg,String screenshotpath)
    {
        getTest().log(LogStatus.INFO,msg);
        getTest().log(LogStatus.FAIL,getTest().addScreenCapture(screenshotpath));
    }

    public static void logskip(String msg) {
        getTest().log(LogStatus.SKIP, msg);
    }

}
