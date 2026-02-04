package orangeappautomation.uitility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelReaderUtil {

    public static final String file = System.getProperty("user.dir")  + File.separator + "src"
            + File.separator + "test" + File.separator + "resources" + File.separator + "dataset.xlsx";

    public static List<Map<String, String>> excelreader(String sheetname) throws FileNotFoundException {

        System.out.println(file);
        InputStream fin = new FileInputStream(file);
        Workbook wb;
        // Object[][] data = null;
        List<Map<String, String>> clovalue = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        try {

            wb = new XSSFWorkbook(fin);
            Sheet sheet = wb.getSheet(sheetname);
            int row = sheet.getLastRowNum();

            System.out.println(row + "------");
            Row headerrow = sheet.getRow(0);
            int colcount = headerrow.getLastCellNum();

            for (int i = 1; i <= row; i++) {
                Row eachrow = sheet.getRow(i);
                Map<String, String> map = new HashMap<>();
                if (eachrow == null) {
                    continue;
                }

                for (int j = 0; j < colcount; j++) {
                    String key = headerrow.getCell(j).getStringCellValue().toLowerCase().trim();
                    Cell cell=eachrow.getCell(j,Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    String value =(cell==null) ? "":formatter.formatCellValue(cell);
                    map.put(key, value);
                }

                clovalue.add(map);
                System.out.println(clovalue);

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return clovalue;
    }

    public static List<Map<String, String>> filtered(List<Map<String, String>> data, String coloumn, String value) {

        List<Map<String, String>> filter = new ArrayList<>();
        for (Map<String, String> row : data) {
            if (value.equalsIgnoreCase(row.get(coloumn.trim().toLowerCase()))) {
                filter.add(row);
                System.out.println(row);
            }
            System.out.println("filtered data--------------"+filter);
        }
        return filter;

    }

    public static void writeIntoExcel(String sheetname, String TestcaseId, boolean actResult,String errormsg) throws IOException {
        FileInputStream fin = new FileInputStream(file);
        Workbook wb = new XSSFWorkbook(fin);
        Sheet sheet = wb.getSheet(sheetname);
        int rowcount = sheet.getLastRowNum();
        DataFormatter formatter = new DataFormatter();
       

        Row headerrow = sheet.getRow(0);
        Map<String, Integer> map = new HashMap<>();
        for (Cell cell : headerrow) {
            map.put(cell.getStringCellValue().toLowerCase(), cell.getColumnIndex());
        }
        
        for (int i = 1; i <= rowcount; i++) {
            Row row = sheet.getRow(i);
            if (row == null)
                continue;
            String tcId = formatter.formatCellValue(row.getCell(map.get("testcaseid")));
            if (tcId.equalsIgnoreCase(TestcaseId)) {
                   Cell statusCell = row.getCell(map.get("actualresult"));
                if (statusCell == null) {
                    statusCell = row.createCell(map.get("actualresult"));
                }
                statusCell.setCellValue(actResult ? "PASS" : "FAIL");
                
                
            }

            if(map.containsKey("errormsg")){
              Cell errorcell= row.getCell(map.get("errormsg"));
              if(errorcell==null)
              {
                    errorcell=row.createCell(map.get("errormsg"));
              }
              errorcell.setCellValue(errormsg==null ? "" :errormsg);

            }
            break;
           
        }
        FileOutputStream fos = new FileOutputStream(file);
        wb.write(fos);
        fos.close();

    }

    public static Object[][] toDataProvider(
            List<Map<String, String>> data,
            String... columns) {

        Object[][] result = new Object[data.size()][columns.length];

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < columns.length; j++) {
                result[i][j] = data.get(i).get(columns[j].toLowerCase());
                System.out.println(  result[i][j]);
            }
        }
        return result;
    }

  
}
