package com.nbfc.helper;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Saurav Tyagi 2017
 * 
 */

public class ExcelHelpRowbyes {
	public void ExcelReader(String filePath) throws IOException {

		try{
			FileInputStream input = new FileInputStream(filePath);

			XSSFWorkbook wb = new XSSFWorkbook(input);
			XSSFSheet sheet = wb.getSheetAt(0);
			Row row;
			for(int i=1; i<=sheet.getLastRowNum(); i++){
			row = sheet.getRow(i);
		/*	int id = (int) row.getCell(0).getNumericCellValue();
			String name = row.getCell(1).getStringCellValue();
			String address = row.getCell(2).getStringCellValue();
		*/	System.out.println("Import rows "+i);
			}
			//System.out.println("Success import excel to mysql table");
		}catch(IOException ioe){
			System.out.println(ioe);
			}

		
	}}