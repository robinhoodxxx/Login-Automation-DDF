package com.utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDataReader {

    public static Object[][] readExcelData(String filename,String sheetname) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        Workbook workbook = new XSSFWorkbook(fis);  // For .xlsx files
        // For .xls files, use: Workbook workbook = new HSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetname); // Assuming data is in the first sheet
        if(!sheetname.equalsIgnoreCase(sheet.getSheetName()) || sheet==null) {
        	System.out.println("you entered "+sheetname+" name is not matched with any sheetname in the "+filename);
        	return null;
        }
      //  System.out.println(sheetname+" "+sheet.getSheetName());
        int rows = sheet.getLastRowNum() ;
        int cols = sheet.getRow(0).getLastCellNum();
        //System.out.println(rows+" "+cols);

        Object[][] data = new Object[rows-1][cols];

        for (int i = 1; i < rows; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < cols; j++) {
                Cell cell = row.getCell(j);
                data[i-1][j] = getCellValue(cell);
                //System.out.println(getCellValue(cell));
            }
        }

        fis.close();
        workbook.close();

        return data;
    }

    private static Object getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case BLANK:
                return "";
            default:
                return cell.toString();
        }
    }

    
}
