package excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



public class ExcelCopy {

	public static void main(String[] args) {
		
		String path=System.getProperty("user.dir")+"//target//";
		System.out.println(path);
		String sourceFilePath = path+"ExcelDatassu.xlsx";
		String targetFilePath = path+"empty.xlsx";
		
		CopyExcel(sourceFilePath,targetFilePath);
		
	}
    public static void CopyExcel(String sourceFilePath,String targetFilePath) {
       // String sourceFilePath = "path/to/source.xlsx";
      //  String targetFilePath = "path/to/target.xlsx";

        try {
            FileInputStream fis = new FileInputStream(sourceFilePath);
            Workbook sourceWorkbook = new XSSFWorkbook(fis);
            fis.close();

            Workbook targetWorkbook = new XSSFWorkbook();

            for (int i = 0; i < sourceWorkbook.getNumberOfSheets(); i++) {
                Sheet sourceSheet = sourceWorkbook.getSheetAt(i);
                Sheet targetSheet = targetWorkbook.createSheet(sourceSheet.getSheetName());

                copySheet(sourceWorkbook, sourceSheet, targetSheet);
            }

            FileOutputStream fos = new FileOutputStream(targetFilePath);
            targetWorkbook.write(fos);
            fos.close();

            System.out.println("Excel file copied successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copySheet(Workbook sourceWorkbook, Sheet sourceSheet, Sheet targetSheet) {
        for (int rowIndex = 0; rowIndex <= sourceSheet.getLastRowNum(); rowIndex++) {
            Row sourceRow = sourceSheet.getRow(rowIndex);
            Row newRow = targetSheet.createRow(rowIndex);

            if (sourceRow != null) {
                copyRow(sourceWorkbook, sourceRow, newRow);
            }
        }
    }

    private static void copyRow(Workbook sourceWorkbook, Row sourceRow, Row newRow) {
        newRow.setHeight(sourceRow.getHeight());

        for (int columnIndex = 0; columnIndex <= sourceRow.getLastCellNum(); columnIndex++) {
            Cell sourceCell = sourceRow.getCell(columnIndex);
            Cell newCell = newRow.createCell(columnIndex);

            if (sourceCell != null) {
                copyCell(sourceWorkbook, sourceCell, newCell);
            }
        }
    }

    private static void copyCell(Workbook sourceWorkbook, Cell sourceCell, Cell newCell) {
        CellType cellType = sourceCell.getCellType();

        // Copy value and formula
        switch (cellType) {
            case STRING:
                newCell.setCellValue(sourceCell.getRichStringCellValue());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(sourceCell)) {
                    newCell.setCellValue(sourceCell.getDateCellValue());
                } else {
                    newCell.setCellValue(sourceCell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                newCell.setCellValue(sourceCell.getBooleanCellValue());
                break;
            case FORMULA:
                newCell.setCellFormula(sourceCell.getCellFormula());
                break;
            default:
                break;
        }

        // Copy cell style
        CellStyle newCellStyle = newCell.getSheet().getWorkbook().createCellStyle();
        newCellStyle.cloneStyleFrom(sourceCell.getCellStyle());
        newCell.setCellStyle(newCellStyle);

        // Copy hyperlink if exists
        if (sourceCell.getHyperlink() != null) {
            Hyperlink sourceHyperlink = sourceCell.getHyperlink();
            Hyperlink newHyperlink = newCell.getSheet().getWorkbook().getCreationHelper().createHyperlink(sourceHyperlink.getType());
            newHyperlink.setAddress(sourceHyperlink.getAddress());
            newCell.setHyperlink(newHyperlink);
        }
    }

   
    
   

   
}
