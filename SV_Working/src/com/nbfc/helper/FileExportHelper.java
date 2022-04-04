package com.nbfc.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

public class FileExportHelper {
	
	 /*	public ActionForward getBulkUploadTemplate(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MessageException, Exception {
		
		    Connection conn = DBConnection.getConnection();
		    String contextPath1 = request.getSession(false).getServletContext().getRealPath("");
			String contextPath = PropertyLoader.changeToOSpath(contextPath1);
			System.out.println("####getBulkUploadTemplate#### 1");
			
			
		    OutputStream os = response.getOutputStream();
		    BulkUpload bulkupload = new BulkUpload();
		    String TableName="interface_upload_10_lac_stag";
			String BulkName="Apps";
		    
		    LinkedHashMap<String, TableDetailBean> headerMap = bulkupload.getTableHeaderData(conn, TableName,BulkName);
		    if (headerMap.size()>1) {
		    	System.out.println("####getBulkUploadTemplate#### 2 ");
				byte[] b = generateTemplateFile(headerMap,contextPath);

				if (response != null){
					response.setContentType("APPLICATION/OCTET-STREAM");
				    response.setHeader("Content-Disposition","attachment; filename=TemplateFile.xls");
				    os.write(b);
				    os.flush();
				    System.out.println("####getBulkUploadTemplate#### 3 ");
				}
			}    
		    System.out.println("####getBulkUploadTemplate#### 4");		
		return null;
	}
	
	/**/
	
	 //##
	public byte[] generateTemplateFile(LinkedHashMap<String, TableDetailBean> TableHeaderHashMap,String contextPath) throws IOException {
				    System.out.println("---generateTemplate()---");
					StringBuffer strbuff = new StringBuffer();
					//System.out.println("ParamDataList:" + ParamDataList);				 
				    HSSFWorkbook workbook = new HSSFWorkbook();
					HSSFSheet sheet = workbook.createSheet("Template");
					// #### Header List Wrinting
					HSSFCellStyle MandatoryCellstyle = workbook.createCellStyle();
					MandatoryCellstyle.setFillForegroundColor(IndexedColors.GOLD.index);
					MandatoryCellstyle.setFillPattern(MandatoryCellstyle.SOLID_FOREGROUND);
					MandatoryCellstyle.setBorderBottom(MandatoryCellstyle.BORDER_THIN);
					MandatoryCellstyle.setBorderTop(MandatoryCellstyle.BORDER_THIN);
					MandatoryCellstyle.setBorderLeft(MandatoryCellstyle.BORDER_THIN);
					MandatoryCellstyle.setBorderRight(MandatoryCellstyle.BORDER_THIN);
					
					
					Row row = sheet.createRow(0);
					int hdcolnum = 0;
					Iterator it = TableHeaderHashMap.entrySet().iterator();
				    while (it.hasNext()) {
				        Map.Entry<String,TableDetailBean> Headerpair = (Map.Entry)it.next();
				        System.out.println(Headerpair.getKey() + " = " + Headerpair.getValue());
				        Cell cell = row.createCell(hdcolnum);
				        TableDetailBean tabledetailbean =  Headerpair.getValue();
				        if(tabledetailbean.getColumnAllowNullFlag().equals("N")){
					         //System.out.println("....Setting sytle");
					         cell.setCellValue(Headerpair.getKey().toString());
					         cell.setCellStyle(MandatoryCellstyle);
				        }else{				        
				        	 cell.setCellValue(Headerpair.getKey().toString());
				        }
						hdcolnum++;
				        //it.remove(); // avoids a ConcurrentModificationException
				    }
				    // #### Header List Wrinting
					
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
					String strDate = sdf.format(cal.getTime());
					try {			
						FileOutputStream out = new FileOutputStream(new File(contextPath+ "\\Download\\TemplateApplicationLodgement.xls"));
						workbook.write(out);
						out.close();
						//System.out.println("Excel written successfully..");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}		
					FileInputStream fis = new FileInputStream(contextPath+ "\\Download\\TemplateApplicationLodgement.xls");
					//System.out.println("9");
					byte b[];
					int x = fis.available();
					b = new byte[x];
					// System.out.println(" b size"+b.length);
					fis.read(b);		
					return b;
}
	
	public byte[] generateEXL(ArrayList<ArrayList> ParamDataList,int No_Column, String contextPath) throws IOException {
		System.out.println("---generateEXL()---");
		
		
		StringBuffer strbuff = new StringBuffer();
		// System.out.println("ParamDataList:" + ParamDataList);
		ArrayList<String> rowDataLst = new ArrayList<String>();
		ArrayList<String> HeaderLst = (ArrayList) ParamDataList.get(0);
		ArrayList<ArrayList> RecordWiseLst = (ArrayList) ParamDataList.get(1);
		
		System.out.println("---generateEXL()---"+HeaderLst);
		System.out.println("---generateEXL()---"+RecordWiseLst);
		
		
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Data1");

		// #### Header List Wrinting
		Row row = sheet.createRow(0);
		int hdcolnum = 0;
		for (String headerdata : HeaderLst) {
			Cell cell = row.createCell(hdcolnum);
			cell.setCellValue(headerdata);
			hdcolnum++;
		}
		// #### Header List Wrinting

		// #### Data List Writing
		int rownum = 1;
		for (ArrayList<String> RecordWiseLstObj : RecordWiseLst) {
			int colnum = 0;
			row = sheet.createRow(rownum);
			for (String SingleRecordDataObj : RecordWiseLstObj) {
				Cell cell = row.createCell(colnum);
				if(null!=SingleRecordDataObj){
					cell.setCellValue(SingleRecordDataObj.trim());
				}else{
					cell.setCellValue(SingleRecordDataObj);
				}
				colnum++;
				rowDataLst.add(SingleRecordDataObj);
			}
			rownum++;
		}
		// #### Data List Writing

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String strDate = sdf.format(cal.getTime());
		try {
			FileOutputStream out = new FileOutputStream(new File(contextPath+"\\Download\\DataXLSFile" + strDate + ".xls"));
			
			workbook.write(out);
			out.close();
			// System.out.println("Excel written successfully..");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileInputStream fis = new FileInputStream(contextPath+ "\\Download\\DataXLSFile" + strDate + ".xls");
		// System.out.println("9");
		byte b[];
		int x = fis.available();
		b = new byte[x];
		// System.out.println(" b size"+b.length);
		fis.read(b);
		return b;
	}

}
