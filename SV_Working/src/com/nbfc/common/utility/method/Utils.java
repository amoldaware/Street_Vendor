package com.nbfc.common.utility.method;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Utils {

	/**
	 * 
	 */

	public static <T> void writeToExcel(String fileName, String sheetName, String[] optionalColumns, List<T> data,
			HttpServletResponse response) {
		Workbook workbook = null;
		try {
			System.out.println("fileName:"+fileName+"\tsheetName"+sheetName+"\t optionalColumns"+optionalColumns+"\t data"+data);
			
			if (data != null && !data.isEmpty()) {
				if (fileName != null && !fileName.isEmpty()) 
				{
					workbook = new HSSFWorkbook();
					Sheet sheet = workbook.createSheet(sheetName);
					Font headerFont = workbook.createFont();
					headerFont.setFontHeightInPoints((short) 12);
					headerFont.setColor(IndexedColors.RED.getIndex());
					CellStyle headerCellStyle = workbook.createCellStyle();
					headerCellStyle.setFont(headerFont);

					List<String> fieldNames = getColumnsNamesForClass(data.get(0).getClass());
					System.out.println("FieldNames ::: "+fieldNames);
					
					int rowCount = 0;
					int columnCount = 0;
					Row row = sheet.createRow(rowCount++);

					if (optionalColumns != null && optionalColumns.length > 0) {
						for (String fieldName : optionalColumns) {
							Cell cell = row.createCell(columnCount++);
							cell.setCellValue(firstLetterCapitalize(fieldName));
							cell.setCellStyle(headerCellStyle);
						}
					} else {
						for (String fieldName : fieldNames) {
							Cell cell = row.createCell(columnCount++);
							cell.setCellValue(firstLetterCapitalize(fieldName));
							cell.setCellStyle(headerCellStyle);
						}
					}

					Class<? extends Object> classz = data.get(0).getClass();
					for (T t : data) {
						row = sheet.createRow(rowCount++);
						columnCount = 0;
						for (String fieldName : fieldNames) {
							Cell cell = row.createCell(columnCount);
							Method method = null;
							try {
								method = classz.getMethod("get" + firstLetterCapitalize(fieldName));
							} catch (NoSuchMethodException nme) {
								method = classz.getMethod("get" + fieldName);
							}
							Object value = method.invoke(t, (Object[]) null);
							if (value != null) {
								if (value instanceof String) {
									cell.setCellValue((String) value);
								} else if (value instanceof Long) {
									cell.setCellValue((Long) value);
								} else if (value instanceof Integer) {
									cell.setCellValue((Integer) value);
								} else if (value instanceof Double) {
									cell.setCellValue((Double) value);
								} else if (value instanceof Float) {
									cell.setCellValue((Float) value);
								}
							}
							columnCount++;
						}
					}
					for (int i = 0; i < fieldNames.size(); i++) {
						sheet.autoSizeColumn(i);
					}
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					try {
						workbook.write(bos);
					} finally {
						bos.close();
					}
					byte[] bytes = bos.toByteArray();

					response.setContentType("application/vnd.ms-excel");
					response.addHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");
					OutputStream os = response.getOutputStream();
					os.write(bytes);
					os.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static List<String> getColumnsNamesForClass(Class<?> clazz) throws Exception {
		List<String> fieldNames = new ArrayList<String>();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {

			if (fields[i].getName().equalsIgnoreCase("serialVersionUID")) {
				continue;

			}
			fieldNames.add(fields[i].getName());

		}
		return fieldNames;
	}

	private static String firstLetterCapitalize(String s) {
		if (s.length() == 0)
			return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	//Added by Sarita 07122021
		public static <T> void writeToExcelForDisburseData(String fileName, String sheetName, String[] optionalColumns, List<T> data,
				HttpServletResponse response) 
		{
			Workbook workbook = null;
			try {
				System.out.println("fileName::"+fileName+"\t sheetName::"+sheetName+"\t optionalColumns::"+optionalColumns+"\t data ::"+data);
				if (data != null && !data.isEmpty()) {
					if (fileName != null && !fileName.isEmpty()) 
					{

						workbook = new HSSFWorkbook();
						Sheet sheet = workbook.createSheet(sheetName);
						Font headerFont = workbook.createFont();
						headerFont.setFontHeightInPoints((short) 12);
						headerFont.setColor(IndexedColors.RED.getIndex());
						CellStyle headerCellStyle = workbook.createCellStyle();
						headerCellStyle.setFont(headerFont);
         
						//List<String> fieldNames = new ArrayList<>();
						List<String> fieldNames = getColumnsNamesForClass(data.get(0).getClass());
						System.out.println("FieldNames ::: "+fieldNames);
						//System.out.println(com.nbfc.model.ApplicationDetailsBean().getPersonalDetails().getApplicant_Name());
						fieldNames.add("lenderName");
						fieldNames.add("applicationNo");
						fieldNames.add("loanAccountNumber");
						fieldNames.add("PersonalDetails().getApplicant_Name");
						fieldNames.add("disbursedAmount");
						fieldNames.add("disbursedDt");
						fieldNames.add("sanctionedAmount");
						fieldNames.add("sanctionedDt");
						fieldNames.add("status");
						fieldNames.add("loanTerm");
						
						if(fieldNames.contains("com.nbfc.model.ApplicationDetailsBean.getPersonalDetails().getApplicant_Name()")){
							fieldNames.remove(3);
							fieldNames.add(3,"com.nbfc.model.ApplicationDetailsBean().getPersonalDetails().getApplicant_Name()");
						}
			
						int rowCount = 0;
						int columnCount = 0;
						Row row = sheet.createRow(rowCount++);
						if (optionalColumns != null && optionalColumns.length > 0)
						{
							for (String fieldName : optionalColumns) 
							{
								Cell cell = row.createCell(columnCount++);
								cell.setCellValue(firstLetterCapitalize(fieldName));
								cell.setCellStyle(headerCellStyle);
							}
						} else {
							for (String fieldName : fieldNames) 
							{
								Cell cell = row.createCell(columnCount++);
								cell.setCellValue(firstLetterCapitalize(fieldName));
								cell.setCellStyle(headerCellStyle);
							}
						}
						Class<? extends Object> classz = data.get(0).getClass();
						for (T t : data) {
							row = sheet.createRow(rowCount++);
							columnCount = 0;
							for (String fieldName : fieldNames) 
							{
								Cell cell = row.createCell(columnCount);
								Method method = null;
								try {
									method = classz.getMethod("get" + firstLetterCapitalize(fieldName));
								} catch (NoSuchMethodException nme) {
									method = classz.getMethod("get" + fieldName);
								}
								Object value = method.invoke(t, (Object[]) null);
								if (value != null) {
									if (value instanceof String) {
										cell.setCellValue((String) value);
									} else if (value instanceof Long) {
										cell.setCellValue((Long) value);
									} else if (value instanceof Integer) {
										cell.setCellValue((Integer) value);
									} else if (value instanceof Double) {
										cell.setCellValue((Double) value);
									} else if (value instanceof Float) {
										cell.setCellValue((Float) value);
									}
								}
								columnCount++;
							}
						}
						for (int i = 0; i < fieldNames.size(); i++) {
							sheet.autoSizeColumn(i);
						}
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						try {
							workbook.write(bos);
						} 
						catch(Exception e){
							e.printStackTrace();
						}
						finally {
							bos.close();
						}
						byte[] bytes = bos.toByteArray();

						response.setContentType("application/vnd.ms-excel");
						response.addHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");
						OutputStream os = response.getOutputStream();
						os.write(bytes);
						os.close();
						System.out.println("File Downloaded!!!");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
