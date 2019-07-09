package AutoHeal;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


class TestingClass extends ReadProperties{
	void fetchXpath() {
		String excelName = "C:\\newWorkSpace\\xpathGenerator.xlsx";
		try {
			File excelFile = new File(excelName);
		    FileInputStream fis = new FileInputStream(excelFile);
		    XSSFWorkbook wb = new XSSFWorkbook(fis);
		    XSSFSheet sheet = wb.getSheetAt(0);
		    String cell2ValueMaybeNull = "";
		    String cell1ValueMaybeNull = "";
//		    Map<Integer, String> userXpath = new HashMap<Integer, String>();
//		    Map<Integer, String> generatedXpath = new HashMap<Integer, String>();
		    Row row;
			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				  row = sheet.getRow(rowIndex);
				  if (row != null ) {
				    Cell cell2 = row.getCell(4);
				    Cell cell1 = row.getCell(2);
				    Cell cellNotMatchedXpathColumn = row.getCell(2);
				    if (cell2 != null) {
				      cell2ValueMaybeNull = cell2.getStringCellValue();
//				      generatedXpath.put(rowIndex, cell2ValueMaybeNull); 
				      if(cell2ValueMaybeNull.equalsIgnoreCase("not matched")) {
				    	  String xpath = cellNotMatchedXpathColumn.getStringCellValue();
				    	  super.fetchPropertiesOfXpath(xpath);
				      }
				    }
				 }
			}	
		}catch(Exception e) {
			e.getMessage();
		}
		
		
		
	}
}



public class FetchUnknownXpaths {
	public static void main(String[] args) {
		TestingClass t = new TestingClass();
		t.fetchXpath();
	}
}
