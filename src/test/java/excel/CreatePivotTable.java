package excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

public class CreatePivotTable {
	static String filepath =System.getProperty("user.dir")+"//target//"+"ExcelDatassu.xlsx";
	static String filepath1 =System.getProperty("user.dir")+"//target//"+"empty.xlsx";

	
  


	    public static void main(String[] args) {
	        try {
	        	
	            FileInputStream file = new FileInputStream(new File(filepath));
	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	            XSSFSheet sheet = workbook.getSheetAt(0); // assuming the pivot table data is in the first sheet

	            // Define the data range for the pivot table
	            AreaReference source = new AreaReference("A1:P10", workbook.getSpreadsheetVersion());

	            // Create the pivot table
	            XSSFPivotTable pivotTable = sheet.createPivotTable(source, new CellReference("G11"), sheet);

	            // Define the pivot table layout
	            pivotTable.addRowLabel(7); // First column as row label
	            pivotTable.addColumnLabel(DataConsolidateFunction.COUNT,1); // Third column as sum value1

	            // Refresh the pivot table
	           // pivotTable.refresh();
                
	            // Write the output to the same file
	           File fileout= new File(filepath1);
	           
	            FileOutputStream out = new FileOutputStream(fileout);
	            
	            workbook.write(out);
	            out.close();
	            workbook.close();
	            file.close();

	            System.out.println("Pivot table created and refreshed successfully.");

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}


