package orangeappautomation.uitility;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;



public class ExtentReportManager {
 //  private static ExtentReportManager instance=null;
   private static ExtentReports report;
   static final String  reportpath=System.getProperty("user.dir")+File.separator+"Extentreports"+File.separator+"QAExtentReport.html";
   private ExtentReportManager()
   {
    
   }

   public static synchronized ExtentReports getReport()
   {
         if(report==null)
         {
            report=new ExtentReports(reportpath, true);
            report.addSystemInfo("Host Name", "Localhost");
         }
            return report;
   }
   
   

   

    
}
