package com.nbfc.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author Saurav Tyagi 2017
 * 
 */
public class ExcelHelper {

	private List<String> fieldNames = new ArrayList<String>();
	private Workbook workbook = null;
	private String workbookName = "";

	public List<String> getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(List<String> fieldNames) {
		this.fieldNames = fieldNames;
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

	public String getWorkbookName() {
		return workbookName;
	}

	public void setWorkbookName(String workbookName) {
		this.workbookName = workbookName;
	}

	public ExcelHelper(String workbookName) {

		this.workbookName = workbookName;
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		setWorkbook((Workbook) new HSSFWorkbook());
	}

	public void closeWorksheet() {

		FileOutputStream fileOut;
		try {

			fileOut = new FileOutputStream(getWorkbookName());
			getWorkbook().write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	private boolean setupFieldsForClass(Class<?> clazz) throws Exception {

		java.lang.reflect.Field[] fields = clazz.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			fieldNames.add(fields[i].getName());
		}
		return true;
	}

	private Sheet getSheetWithName(String name) {
		Sheet sheet = null;
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (name.compareTo(workbook.getSheetName(i)) == 0) {
				sheet = workbook.getSheetAt(i);
				break;
			}
		}
		return sheet;

	}

	private void initializeForRead() throws IOException, InvalidFormatException{
		InputStream inp= new FileInputStream(getWorkbookName());
		workbook= WorkbookFactory.create(inp);
	}
	@SuppressWarnings({"unchecked","rawtypes"})
	public <T> List<T> readData(String className) throws Exception{
		System.out.println("ReadData..........."+className);
	
		initializeForRead();
		Sheet sheet = getSheetWithName(className);
		
		Class clazz= Class.forName(workbook.getSheetName(0));
		setupFieldsForClass(clazz);
		List<T> result= new ArrayList<T>();
		Row row;
		for(int rowCount=1;rowCount<4;rowCount++){
			
			T one = (T) clazz.newInstance();
			row= sheet.getRow(rowCount);
			int colCount=0;
			result.add(one);
			for(Cell cell : row){
				int type = cell.getCellType();
				String fieldName=fieldNames.get(colCount++);
				Method method= constructMethod(clazz,fieldName);
				if(type==cell.CELL_TYPE_STRING){
					String value=cell.getStringCellValue();
					Object[] values = new Object[1];
					values[0]= value;
					method.invoke(one, values);
				}
				else if(type==cell.CELL_TYPE_NUMERIC){
					Double num = cell.getNumericCellValue();
					Class<?> returnType= getGetterReturnClass(clazz,fieldName);
					if(returnType == int.class || returnType==Integer.class){
						method.invoke(one, num.intValue());
					}
					else if(returnType==double.class||returnType==Double.class){
						method.invoke(one, num);
							
					}else if(returnType==float.class||returnType==Float.class){
						method.invoke(one, num.floatValue());
							
					}else if(returnType==long.class||returnType==Long.class){
						method.invoke(one, num.longValue());
							
					}else if(returnType==Date.class){
						Date date=HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
						method.invoke(one, date);
							
					}
					
				}else if(type==Cell.CELL_TYPE_BOOLEAN){
					boolean num=cell.getBooleanCellValue();
					Object[] values = new Object[1];
					method.invoke(one, values);
				}
					
				}
			}
		return result;
		}
		private Class<?> getGetterReturnClass(Class<?> clazz, String fieldName){
			
			String methodName="get"+capitalize(fieldName);
			String methodsName="is"+capitalize(fieldName);
			Class<?> returnType= null;
			for(Method method : clazz.getMethods()){
				if(method.getName().equals(methodName) || method.getName().equals(methodsName)){
			returnType=method.getReturnType();
			break;
				}
			}
			return returnType;

		}
		@SuppressWarnings({"unchecked","rawtypes"})
		private Method constructMethod(Class clazz,String fieldName) throws SecurityException, NoSuchMethodException{
		Class<?> fieldClass = getGetterReturnClass(clazz, fieldName);
		return clazz.getMethod("set"+capitalize(fieldName),fieldClass);
		}
		public <T> void writeDate(List<T> data) throws Exception{
			try{
				Sheet sheet =getWorkbook().createSheet(data.get(0).getClass().getName());
			setupFieldsForClass(data.get(0).getClass());
			int rowCount=0;
			int columnCount=0;
			Row row= sheet.createRow(rowCount++);
			for(String fieldName : fieldNames){
				
				Cell cel=row.createCell(rowCount++);
				cel.setCellValue(fieldName);
			}
			Class<? extends Object> classz=data.get(0).getClass();
			for(T t:data){
				row = sheet.createRow(rowCount++);
				columnCount=0;
				for(String fieldName : fieldNames){
					Cell cel = row.createCell(columnCount);
					Method method=hasMethod(classz,fieldName)
							? classz.getMethod("get"+capitalize(fieldName))
									: classz.getMethod("is"+ capitalize(fieldName));
							Object value=method.invoke(t,(Object[]) null);
							if(value!=null){
								if(value instanceof String){
									cel.setCellValue((String)value);
								}else if(value instanceof Long){
									cel.setCellValue((Long)value);
								}else if(value instanceof Integer){
									cel.setCellValue((Integer)value);
								}else if(value instanceof Double){
									cel.setCellValue((Double)value);
								}else if(value instanceof Date){
									cel.setCellValue((Date)value);
									CellStyle styleData = workbook.createCellStyle();
									DataFormat dataFormateDate = workbook.createDataFormat();
									styleData.setDataFormat(dataFormateDate.getFormat("m/d/yy"));
								cel.setCellStyle(styleData);
								
								}else if(value instanceof Boolean){
									cel.setCellValue((Boolean)value);
								}
							}
							columnCount++;
				}
			}
			for(int i=0;i<fieldNames.size();i++)
			sheet.autoSizeColumn(i);
			FileOutputStream out = new FileOutputStream(new File(workbookName));
			workbook.write(out);
			out.close();
			((FileInputStream) workbook).close();
			}catch(Exception e){
				
				System.out.println("Error  "+e);
			}
			
					
		}
		@SuppressWarnings({"unchecked","rawtypes","unused"})
		private boolean hasMethod(Class classz, String fieldName){
			try{
				classz.getMethod("get"+ capitalize(fieldName));
				return true;
			}catch(Exception e){
				return false;
			}
			}
		public String capitalize(String string){
			String capital = string.substring(0,1).toUpperCase();
			return capital+string.substring(1);
		}
		}
		