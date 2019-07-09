package AutoHeal;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

public class EcelUtility {
	
	public static void storingData(XSSFWorkbook wb, XSSFSheet sheet, String sheetName, ArrayList<String> xpathList, ArrayList<String> xpathNameList, String excelName) {
		
		try {
		XSSFRow titleRow = sheet.createRow(0);
		titleRow.createCell(0).setCellValue("PageTitle");
		titleRow.createCell(1).setCellValue(sheetName);
		XSSFRow r = sheet.createRow(1);
		XSSFCell c = r.createCell(1);
		XSSFCell n = r.createCell(0);
		XSSFCell stat = r.createCell(2);
		XSSFCell newX = r.createCell(3);
		CellStyle style = wb.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.FINE_DOTS);
		style.setAlignment(HorizontalAlignment.CENTER);
		Font font = wb.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(font);
		
		n.setCellValue("Element Names");
		n.setCellStyle(style);
		c.setCellValue("Captured Xpaths");
		c.setCellStyle(style);
		stat.setCellValue("Change Status");
		stat.setCellStyle(style);
		newX.setCellValue("New XPath");
		newX.setCellStyle(style);
		for(int i=1; i<=xpathList.size(); i++) {
			
			XSSFRow row = sheet.getRow(i+1);
			
			if(row == null) {
				row = sheet.createRow(i+1);
			}
			
			XSSFCell cellEleName = row.createCell(0);
			XSSFCell cellXpath = row.createCell(1);
			cellXpath.setCellValue(xpathList.get(i-1));
			cellEleName.setCellValue(xpathNameList.get(i-1));
	}
		
		FileOutputStream fileOut;
		
			fileOut = new FileOutputStream(excelName);
		
		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


}
	
	public static CellStyle setColour(XSSFWorkbook wb, String colour) {
		CellStyle style = wb.createCellStyle();
		if(colour.equalsIgnoreCase("GREEN")) {
			style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		} else style.setFillBackgroundColor(IndexedColors.RED.getIndex());		
		style.setFillPattern(FillPatternType.FINE_DOTS);
		style.setAlignment(HorizontalAlignment.CENTER);
		Font font = wb.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(font);
		return style;
	}
	
}
