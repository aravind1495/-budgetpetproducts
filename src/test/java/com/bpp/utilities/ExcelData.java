package com.bpp.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelData {
	
	@DataProvider(name = "credentials", indices = {0,1,2,3})
	public String[][] readData() {
		
		String[][] data = null;
		
		try {
			
			FileInputStream file = new FileInputStream
					(new File("C:\\Users\\pkkumar\\Desktop\\Aravind\\Test Data\\budgetpetproducts.xlsx"));
			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(1);
			
			int rowLength = sheet.getPhysicalNumberOfRows();
			
			data = new String[rowLength-1][2];
			
			for(int i=1; i<rowLength; i++) {
				data[i-1][0] = sheet.getRow(i).getCell(0).getStringCellValue(); //getting username
				data[i-1][1] = sheet.getRow(i).getCell(1).getStringCellValue();	 //getting password
			}
			
			workbook.close();
			file.close();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;	
	}
	
	public static void writeData(int index, String result, String output) {
		
		try {
			
			File file = new File("C:\\Users\\pkkumar\\Desktop\\Aravind\\Test Data\\budgetpetproducts.xlsx");
			FileInputStream fileIn = new FileInputStream(file);
			
			XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
			XSSFSheet sheet = workbook.getSheetAt(1);
			
			sheet.getRow(index).createCell(2).setCellValue(result);
			sheet.getRow(index).createCell(3).setCellValue(output);
			
			FileOutputStream fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
			
			workbook.close();
			fileIn.close();
			fileOut.close();
			
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
