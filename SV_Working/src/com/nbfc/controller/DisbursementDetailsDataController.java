package com.nbfc.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.nbfc.common.utility.method.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nbfc.bean.FileExcelDataBO;
import com.nbfc.bean.LodgeFreshClaimBO;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.ApplicationDetailsBean;
import com.nbfc.model.Login;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.DisbursementDetailsService;
import com.nbfc.service.LoginService;
import com.nbfc.service.NBFCUserReportService;
import com.nbfc.service.RecoveryUploadService;
import com.nbfc.service.UserActivityService;

@Controller
public class DisbursementDetailsDataController 
{
	
final static Logger logger = Logger.getLogger(DisbursementDetailsController.class.getName());
	
	@Autowired
	DisbursementDetailsService disbursementDetailsService;
	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	NBFCUserReportService nbfcUserReportService;
	@Autowired
	RecoveryUploadService recoveryUploadService;
	@Autowired
	MenuUtilsController menuUtilsController;
	@Value("${disbursementDetailFilePath}")
	private String downloadFileDirPath;
	@Autowired
	private SessionFactory sessionFactory;
	
	String userId = null;
	Login userDetails = null;
	HttpSession session1 = null;
	NBFCPrivilegeMaster userPrvMst=null;
	List<ApplicationDetailsBean> appListData = null;
	
	@RequestMapping(value = "/disbDetailsData", method = RequestMethod.GET)
	public ModelAndView disbursementDetailsData(Model modeluserRole, HttpSession session) throws BusinessException 
	{
		Map<String, Object> model = new HashMap<String, Object>();
		UserPerivilegeDetails userPri;
		try {
			userId = (String) session.getAttribute("userId");
			String Role = (String) session.getAttribute("uRole");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				session.setAttribute("userRoleClaimPayment", userPrvMst.getPrvCreatedModifiedBy());
				session.setAttribute("userNameId",
						userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID());
				model = menuUtilsController.prepareMenu(session, Role, userId);
				if (model!=null) {
					if(Role.equals("CCHECKER")){
						model.put("homePage", "cgtmseCheckerHome");
					}
					if(Role.equals("CMAKER")){
						model.put("homePage", "cgtmseMakerHome");
					}
					
					if(Role.equals("NCHECKER")){
							model.put("homePage", "nbfcCheckerHome");
						}
						if(Role.equals("NMAKER")){
							model.put("homePage", "nbfcMakerHome");
						}
					return new ModelAndView("disbDetailsData", model);
				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}
	
	
	@RequestMapping(value = "/uploadDisburseFile", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<List<ApplicationDetailsBean>> uploadExcelFile(@RequestParam("uploadfile") MultipartFile uploadfile,
			HttpSession session,Login userDetails,HttpServletResponse response) throws BusinessException {
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		Workbook wb = null;
		Sheet sheet = null;
		boolean isSuccess = false;
		String extensionType = null;
		String userId = null;
		Map<Integer, List<Object>> uploadDataMap = new HashMap<>();
		try {
			userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
			}
			
			if (uploadfile.isEmpty()) {
				//return new ResponseEntity<>("Please select file.", HttpStatus.BAD_REQUEST);
			}
			if (uploadfile.getOriginalFilename().contains("..")) {
				/*return new ResponseEntity<>(
						"Sorry! Filename contains invalid path sequence " + uploadfile.getOriginalFilename(),
						HttpStatus.BAD_REQUEST);*/
			}
			if (uploadfile.getOriginalFilename().lastIndexOf(".") != -1
					&& uploadfile.getOriginalFilename().lastIndexOf(".") != 0) {
				extensionType = uploadfile.getOriginalFilename()
						.substring(uploadfile.getOriginalFilename().lastIndexOf(".") + 1);
			}

			String[] docType = { "xls", "xlsx" };
			List<String> validExtensionList = Arrays.asList(docType);
			if (!validExtensionList.contains(extensionType)) {
				//return new ResponseEntity<>("Only xls or xlsx file type allowed...!", HttpStatus.BAD_REQUEST);
			}

			if (extensionType.equalsIgnoreCase("xlsx")) {
				wb = new XSSFWorkbook(uploadfile.getInputStream());

			} else if (extensionType.equalsIgnoreCase("xls")) {
				wb = new HSSFWorkbook(uploadfile.getInputStream());

			}

			sheet = wb.getSheetAt(0);

			int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
			System.out.println("physicalNumberOfRows :::" + physicalNumberOfRows);
			
			if (physicalNumberOfRows == 0) {
				//return new ResponseEntity<>("Prescribe template is empty...!.", HttpStatus.BAD_REQUEST);
			}

			for (int i = 0; i < physicalNumberOfRows; i++) {
				List<Object> genericUploadList = new ArrayList<>();
				Row row = sheet.getRow(i);

				if (i == 0) {
					int numberOfCells = row.getPhysicalNumberOfCells();
					System.out.println("Number of Cells :::" +numberOfCells);
					if (numberOfCells != 1) {
						/*return new ResponseEntity<>("Prescribe template is not used while uploading these records.",
								HttpStatus.BAD_REQUEST);*/
					}
					continue;
				}

				Iterator<Cell> iterator = row.iterator();
				while (iterator.hasNext()) {

					Cell cell = iterator.next();

					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_STRING:
						genericUploadList.add(cell.getStringCellValue());
						break;

					case Cell.CELL_TYPE_NUMERIC:

						if (DateUtil.isCellDateFormatted(cell)) {
							genericUploadList.add(cell.getDateCellValue());
						} else {
							genericUploadList.add(cell.getNumericCellValue());
						}
						break;

					case Cell.CELL_TYPE_BLANK:
						genericUploadList.add("");
						break;
					}
				}
				uploadDataMap.put(Integer.valueOf(i), genericUploadList);
			}
			List<FileExcelDataBO> uploadDataProcessList = new ArrayList<>();
			Set<Integer> keySet = uploadDataMap.keySet();

			if (keySet == null || keySet.isEmpty()) {
				//return new ResponseEntity<>("Prescribe template is empty...!.", HttpStatus.BAD_REQUEST);
			}
			for (Integer key : keySet) {
				List<Object> list = uploadDataMap.get(key);
				FileExcelDataBO bo = new FileExcelDataBO();
				int index = -1;
				for (Object obj : list) {
					bo.setUserId(userDetails.getUsr_id());
					bo.setMemberBnkId(
							userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_BRN_ID());
					
					if (obj instanceof String) {
						index++;
						if (index == 0) {
							bo.setPmsNumber((String) obj);
							continue;
						}				
					}
					if (obj instanceof Double) {
						index++;
						double valueDouble = (Double) obj;
						if (index == 0) {
							bo.setPmsNumber(Long.toString((long) valueDouble));
							continue;
						}
					}
					if (obj instanceof Date) {
						index++;
						if (index == 0) {
							bo.setPmsNumber(sdf1.format((Date) obj));
							continue;
						}
					}
				}
				uploadDataProcessList.add(bo);
			}
			appListData = disbursementDetailsService.getDisburseData(uploadDataProcessList);
		} 
			catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(appListData, HttpStatus.BAD_REQUEST);
		} finally {
			try {
				if (wb != null) {
					wb.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return new ResponseEntity<>(appListData, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/download-pms-data", method = RequestMethod.GET)
	public void exportDisbursementData(HttpServletResponse response,HttpSession session)
			throws BusinessException 
	{
		String fileName = "DisbursementData";
		try
		{
			System.out.println("List is :::" + appListData);
			XSSFWorkbook hwb = new XSSFWorkbook();
			XSSFSheet sheet = hwb.createSheet("DownLoadedFileForDisbursementDetails");
			CellStyle style = hwb.createCellStyle();
			Font font = hwb.createFont();
			font.setFontHeightInPoints((short) 11);
			font.setFontName(HSSFFont.FONT_ARIAL);
			font.setBoldweight(HSSFFont.COLOR_NORMAL);
			font.setBold(true);
			font.setColor(HSSFColor.DARK_BLUE.index);
			style.setFont(font);
			style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			sheet.createFreezePane(0, 1); 
			XSSFRow rowhead = sheet.createRow((short)0);
			
			XSSFCell cell0 = rowhead.createCell(0);
			cell0.setCellStyle(style);
			cell0.setCellValue("SR.No.");//Done 1

			XSSFCell cell1 = rowhead.createCell(1);
			cell1.setCellStyle(style);
			cell1.setCellValue("LENDER NAME");//Done 3
			    
			XSSFCell cell2 = rowhead.createCell(2);
			cell2.setCellStyle(style);
			cell2.setCellValue("APPLICATION NO");//Done 4
			    
			XSSFCell cell3 = rowhead.createCell(3);
			cell3.setCellStyle(style);
			cell3.setCellValue("LOAN ACCOUNT NO");//Done 5
			    
			XSSFCell cell4 = rowhead.createCell(4);
			cell4.setCellStyle(style);
			cell4.setCellValue("APPLICANT NAME");//Done 6
			    
			XSSFCell cell5 = rowhead.createCell(5);
			cell5.setCellStyle(style);
			cell5.setCellValue("DISBURSED AMOUNT");//Done 7
			
			XSSFCell cell6 = rowhead.createCell(6);
			cell6.setCellStyle(style);
			cell6.setCellValue("DISBURSED DATE");//Done 7
	        
		     XSSFCell cell7 = rowhead.createCell(7);
		     cell7.setCellStyle(style);
		     cell7.setCellValue("SANCTION AMOUNT");//Done 9
		        
		     XSSFCell cell8 = rowhead.createCell(8);
		     cell8.setCellStyle(style);
		     cell8.setCellValue("SANCTION DATE");//Done 10
		     
		     XSSFCell cell9 = rowhead.createCell(9);
		     cell9.setCellStyle(style);
		     cell9.setCellValue("STATUS");
		     
		     XSSFCell cell10 = rowhead.createCell(10);
		     cell10.setCellStyle(style);
		     cell10.setCellValue("LOAN TERM");
		     
		     int index = 1;
				int sno = 1;
				String name = "";
				Iterator<ApplicationDetailsBean> itr2 = appListData.iterator();
				while (itr2.hasNext()) {
					ApplicationDetailsBean obj1 = (ApplicationDetailsBean) itr2.next();
					XSSFRow row = sheet.createRow((short) index);
					row.createCell((short) 0).setCellValue(sno);//Done 0
					row.createCell((short) 1).setCellValue(obj1.getLenderName());//Done 1
					row.createCell((short) 2).setCellValue(obj1.getApplicationNo());//Done 1
				    row.createCell((short) 3).setCellValue(obj1.getLoanAccountNumber());//Done 2
					row.createCell((short) 4).setCellValue(obj1.getPersonalDetails().getApplicant_Name());//Done 3
					row.createCell((short) 5).setCellValue(obj1.getDisbursedAmount());//Done 10
					row.createCell((short) 6).setCellValue(obj1.getDisbursedDt());//Done 11
					row.createCell((short) 7).setCellValue(obj1.getSanctionedAmount());//Done 22
					row.createCell((short) 8).setCellValue(obj1.getSanctionedDt());//Done 22
					row.createCell((short) 9).setCellValue(obj1.getStatus());//Done 22
					row.createCell((short) 10).setCellValue(obj1.getLoanTerm());//Done 22
					index++;
					sno++;	
				}
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				try {
					hwb.write(bos);
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
		catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
