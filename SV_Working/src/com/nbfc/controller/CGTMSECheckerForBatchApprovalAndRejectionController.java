package com.nbfc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.nbfc.model.CGTMSECheckerForBatchApprovalAndRejectionSumbission;
import com.nbfc.model.CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave;
import com.nbfc.service.CGTMSECheckerForBatchApprovalAndRejectionService;
import com.nbfc.service.UserActivityService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class CGTMSECheckerForBatchApprovalAndRejectionController {

	// For UAT
	// public static final String FILE_NAME = "E:/DownLoadNBFCCheckerFile.xlsx";
	@Autowired
	UserActivityService userActivityService;
	//public static final String FILE_NAME = "F:/DownLoadNBFCCheckerFile.xlsx";
	@Value("${downloadFileDirPath}")
	private String downloadFileDirPath;
	@Value("${downloadFileName}")
	private String downloadFileName;
	public static final int BUFFER_SIZE = 4096;
	static Logger log = Logger.getLogger(NBFCController.class.getName());

	Integer portfolioNo;
	String dateOfUpload;
	String mLishortName;
	String fullFileName1;
	Integer noOfFile;
	Integer quaterNumber;
	String cgtmseCheckerId = null;
	Object[][] datatypes;

	String noOfTimeClickOnFileDownLoad = null;
	String generatedExelFileName = null;
	String uploadedDate1 = null;
	String shortName1 = null;
	Integer portfolioID1 = 0;
	String quaterName1 = null;
	String excelFileId1 = null;
	String totNoOfRecordsUploadedInExcelFile1 = null;
	String totalSanctionAmt1 = null;
	String excelFileFullName1 = null;

	@Autowired
	private CGTMSECheckerForBatchApprovalAndRejectionService cgtmseCheckerForBatchApprovalAndRejectionService;
	CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave heckerForBatchApprovalAndRejectionSumbissionSave = new CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave();
	
	@RequestMapping(value = "cgtmseCheckerForBatchApprovalAndRejectionRM", method = RequestMethod.GET)
	public ModelAndView showcgtmseCheckerForBacthApprovalInputForm(
			@ModelAttribute("command") CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave,
			BindingResult result,
			HttpSession session,
			@RequestParam("uploadedDate") String uploadedDate,
			@RequestParam("shortName") String shortName,
			@RequestParam("portfolioID") Integer portfolioID,
			@RequestParam("quaterName") String quaterName,
			@RequestParam("excelFileId") String excelFileId,
			@RequestParam("totNoOfRecordsUploadedInExcelFile") String totNoOfRecordsUploadedInExcelFile,
			@RequestParam("totalSanctionAmt") String totalSanctionAmt,
			@RequestParam("outstandingAmount") String outstandingAmount,
			@RequestParam("excelFileFullName") String excelFileFullName) {
		try {
			cgtmseCheckerId = (String) session.getAttribute("userId");
			uploadedDate1 = uploadedDate;
			shortName1 = shortName;
			portfolioID1 = portfolioID;
			quaterName1 = quaterName;
			excelFileId1 = excelFileId;
			totNoOfRecordsUploadedInExcelFile1 = totNoOfRecordsUploadedInExcelFile;
			totalSanctionAmt1 = totalSanctionAmt;
			excelFileFullName1 = excelFileFullName;
			portfolioNo = portfolioID;
			fullFileName1 = excelFileFullName;
			Map<String, Object> modelAct = new HashMap<String, Object>();
			modelAct.put("adminlist",userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
			modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSECHECKER", "Registration"));
			modelAct.put("applicationList",userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
			modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
			modelAct.put("actName", userActivityService.getActivityName("CGTMSECHECKER","cgtmseCheckerForBacthApprovalRM"));
			modelAct.put("repList",
					userActivityService.getReport("CGTMSECHECKER", "User_Report"));
			modelAct.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSECHECKER", "Guarantee_Maintenance"));
			modelAct.put("CList",
					userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
			modelAct.put("homePage", "cgtmseCheckerHome");
			ModelAndView model = new ModelAndView("cgtmseCheckerForBatchApprovalAndRejection",modelAct);
			session.setAttribute("portfolioIDKey", portfolioID);
			heckerForBatchApprovalAndRejectionSumbissionSave.setFilePath(excelFileId);
			model.addObject("uploadedDateKey", uploadedDate);
			model.addObject("shortNameKey", shortName);
			model.addObject("portfolioIDKey1", portfolioID);
			model.addObject("quaterNameKey", quaterName);
			model.addObject("excelFileIdKey", excelFileId);
			model.addObject("totNoOfRecordsUploadedInExcelFileKey",totNoOfRecordsUploadedInExcelFile);
			model.addObject("totalSanctionAmtKey", totalSanctionAmt);
			model.addObject("outstandingAmount", outstandingAmount);
			model.addObject("excelFileFullNameKey", excelFileId1);
			model.addObject("lists", portfolioID);
			return model;
		} catch (Exception e) {
			System.out.println("Exception ===" + e);
		}
		return null;
	}

	@RequestMapping(value = "cgtmseCheckerRejectionAndSumbissionRMdownLoad", method = RequestMethod.GET)
	public ModelAndView nbfcCheckerFileDownLoad(
			@ModelAttribute("command") CGTMSECheckerForBatchApprovalAndRejectionSumbission cgtmseCheckerForBatchApprovalAndRejectionSumbission,BindingResult result,
			@RequestParam("portfolioID") String portfolioID,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws IOException {
		try {
			String filePath=downloadFileDirPath+File.separator+downloadFileName;
			File file = new File(downloadFileDirPath);
			boolean isCreated=file.mkdir();
			if(isCreated){
				File file1 = new File(filePath);
				boolean isExists=file1.exists();
				if(isExists){
				}else{
					file1.createNewFile();
				}
			}
			String statusCMA = "CMA";
			cgtmseCheckerForBatchApprovalAndRejectionSumbission.setFilePath(excelFileId1);
			//cgtmseCheckerForBatchApprovalAndRejectionSumbission.setFilePath(fullFileName1);
			cgtmseCheckerForBatchApprovalAndRejectionSumbission.setStatus(statusCMA);
			List<CGTMSECheckerForBatchApprovalAndRejectionSumbission> list = cgtmseCheckerForBatchApprovalAndRejectionService.getDataForNBFCCheckerFileDownLoad(cgtmseCheckerForBatchApprovalAndRejectionSumbission);
			XSSFWorkbook hwb = new XSSFWorkbook();
			XSSFSheet sheet = hwb.createSheet("DownLoadedFileForNBFCChecker");
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
			cell0.setCellValue("Unit Name");//Done 1

			XSSFCell cell1 = rowhead.createCell(1);
			cell1.setCellStyle(style);
			cell1.setCellValue("Constitution");//Done 3
			    
			XSSFCell cell2 = rowhead.createCell(2);
			cell2.setCellStyle(style);
			cell2.setCellValue("Loan Type");//Done 4
			    
			XSSFCell cell3 = rowhead.createCell(3);
			cell3.setCellStyle(style);
			cell3.setCellValue("Loan Account No.");//Done 5
			    
			XSSFCell cell4 = rowhead.createCell(4);
			cell4.setCellStyle(style);
			cell4.setCellValue("Sanction Date");//Done 6
			    
			XSSFCell cell5 = rowhead.createCell(5);
			cell5.setCellStyle(style);
			cell5.setCellValue("Sanctioned Amount");//Done 7
	        
		     XSSFCell cell6 = rowhead.createCell(6);
		     cell6.setCellStyle(style);
		     cell6.setCellValue("First Disbursement Date");//Done 9
		        
		     XSSFCell cell7 = rowhead.createCell(7);
		     cell7.setCellStyle(style);
		     cell7.setCellValue("Interest Rate (% p.a)");//Done 10
		     
		     XSSFCell cell8 = rowhead.createCell(8);
		     cell8.setCellStyle(style);
		     cell8.setCellValue("Retail Trade");
		     
		     XSSFCell cell9 = rowhead.createCell(9);
		     cell9.setCellStyle(style);
		     cell9.setCellValue("Partially Secured By Collatral");
		     
		     XSSFCell cell10 = rowhead.createCell(10);
		     cell10.setCellStyle(style);
		     cell10.setCellValue("Guarantee amount");
		     
		     XSSFCell cell11 = rowhead.createCell(11);
		     cell11.setCellStyle(style);
		     cell11.setCellValue("Collatral Security Amount");
		     
		     XSSFCell cell12 = rowhead.createCell(12);
		     cell12.setCellStyle(style);
		     cell12.setCellValue("Outstanding Amount");
		     
		     XSSFCell cell13 = rowhead.createCell(13);
		     cell13.setCellStyle(style);
		     cell13.setCellValue("Tenor In Months");//Done 11
		      
		     XSSFCell cell14 = rowhead.createCell(14);
		     cell14.setCellStyle(style);
		     cell14.setCellValue("Whether The Assisted Unit Is Micro/Small ?");//Done 12
		        
		     XSSFCell cell15 = rowhead.createCell(15);
		     cell15.setCellStyle(style);
		     cell15.setCellValue("Unit Address");//Done 13
		        
		     XSSFCell cell16 = rowhead.createCell(16);
		     cell16.setCellStyle(style);
		     cell16.setCellValue("City");//Done 14
		        
		     XSSFCell cell17 = rowhead.createCell(17);
		     cell17.setCellStyle(style);
		     cell17.setCellValue("District");//Done 15
		        
		     XSSFCell cell18 = rowhead.createCell(18);
		     cell18.setCellStyle(style);
		     cell18.setCellValue("Pincode");//Done 16
		        
		     XSSFCell cell19 = rowhead.createCell(19);
		     cell19.setCellStyle(style);
		     cell19.setCellValue("State");//Done 17
		        
		     XSSFCell cell20 = rowhead.createCell(20);
		     cell20.setCellStyle(style);
		     cell20.setCellValue("Unit IT-PAN");//Done 18
		        
		     XSSFCell cell21 = rowhead.createCell(21);
		     cell21.setCellStyle(style);
		     cell21.setCellValue("Udyog Aadhar No.");//Done 19

		     XSSFCell cell22 = rowhead.createCell(22);
		     cell22.setCellStyle(style);
		     cell22.setCellValue("Industry Nature");//Done 21
		        
		     XSSFCell cell23 = rowhead.createCell(23);
		     cell23.setCellStyle(style);
		     cell23.setCellValue("Industry Sector");//Done 22
		        
		     XSSFCell cell24 = rowhead.createCell(24);
		     cell24.setCellStyle(style);
		     cell24.setCellValue("No. Of Employees");//Done 23
		        
		     XSSFCell cell25 = rowhead.createCell(25);
		     cell25.setCellStyle(style);
		     cell25.setCellValue("Projected Sales");//Done 24
		        
		     XSSFCell cell26 = rowhead.createCell(26);
		     cell26.setCellStyle(style);
		     cell26.setCellValue("Projected Exports");//Done 25
		        
		     XSSFCell cell27 = rowhead.createCell(27);
		     cell27.setCellStyle(style);
		     cell27.setCellValue("New/ Existing Unit");//Done 26
		        
		     XSSFCell cell28 = rowhead.createCell(28);
		     cell28.setCellStyle(style);
		     cell28.setCellValue("Customer  Having Any Previous Banking Experience?");//Done 27
		        
		     XSSFCell cell29= rowhead.createCell(29);
		     cell29.setCellStyle(style);
		     cell29.setCellValue("Chief Promoter First Name"); //Done 29
		        
		     XSSFCell cell30 = rowhead.createCell(30);
		     cell30.setCellStyle(style);
		     cell30.setCellValue("Chief Promoter Middle Name");//Done 30
		        
		     XSSFCell cell31 = rowhead.createCell(31);
		     cell31.setCellStyle(style);
		     cell31.setCellValue("Chief Promoter Last Name");//Done 31
		        
		     XSSFCell cell32 = rowhead.createCell(32);
		     cell32.setCellStyle(style);
		     cell32.setCellValue("Chief Promoter IT-PAN");//Done 32

		     XSSFCell cell33 = rowhead.createCell(33);
		     cell33.setCellStyle(style);
		     cell33.setCellValue("Chief Promoter's Mail Id");//Done 33
		        
		     XSSFCell cell34 = rowhead.createCell(34);
		     cell34.setCellStyle(style);
		     cell34.setCellValue("Chief Promoter's Contact Number");//Done 34
		        
		     XSSFCell cell35 = rowhead.createCell(35);
		     cell35.setCellStyle(style);
		     cell35.setCellValue("Minority Community");//Done 35
		        
		     XSSFCell cell36 = rowhead.createCell(36);
		     cell36.setCellStyle(style);
		     cell36.setCellValue("Handicapped");//Done 36
		        
		     XSSFCell cell37 = rowhead.createCell(37);
		     cell37.setCellStyle(style);
		     cell37.setCellValue("Women");//Done 37
		        
		     XSSFCell cell38 = rowhead.createCell(38);
		     cell38.setCellStyle(style);
		     cell38.setCellValue("Category");//Done 38
		     
		     XSSFCell cell39 = rowhead.createCell(39);
		     cell39.setCellStyle(style);
		     cell39.setCellValue("Aadhar Number");

			int index = 1;
			int sno = 0;
			String name = "";
			Iterator<CGTMSECheckerForBatchApprovalAndRejectionSumbission> itr2 = list.iterator();
			while (itr2.hasNext()) {
				CGTMSECheckerForBatchApprovalAndRejectionSumbission obj1 = (CGTMSECheckerForBatchApprovalAndRejectionSumbission) itr2.next();
				sno++;
				XSSFRow row = sheet.createRow((short) index);
				row.createCell((short) 0).setCellValue(obj1.getMseName());//Done 1
		        row.createCell((short) 1).setCellValue(obj1.getConstitution());//Done 2
		        row.createCell((short) 2).setCellValue(obj1.getLoneType());//Done 3
		        row.createCell((short) 3).setCellValue(obj1.getLoanAccountNo());//Done 4
		        row.createCell((short) 4).setCellValue(obj1.getSnctionDate());//Done 5
		        row.createCell((short) 5).setCellValue(obj1.getSanctionedAmount());//Done 6
		        row.createCell((short) 6).setCellValue(obj1.getFirstDisbursementDate());//Done 7
		        row.createCell((short) 7).setCellValue(obj1.getInterestRate());//Done 9
		        row.createCell((short) 8).setCellValue(obj1.getRETAIL_TRADE());//Done 10
		        row.createCell((short) 9).setCellValue(obj1.getPARTIAL_SECURITY_FLAG());//Done 11
		        row.createCell((short) 10).setCellValue(obj1.getGUARANTEE_AMOUNT());//Done 12
		        row.createCell((short) 11).setCellValue(obj1.getCOLLETRAL_SECURITY_AMOUNT());//Done 13
		        row.createCell((short) 12).setCellValue(obj1.getOUTSTANDING_AMOUNT()); //Done 14
		        row.createCell((short) 13).setCellValue(obj1.getTenorInMonth());//Done 15
		        row.createCell((short) 14).setCellValue(obj1.getMicroSmall());//Done 16
		        row.createCell((short) 15).setCellValue(obj1.getMseAddress());//Done 17
		        row.createCell((short) 16).setCellValue(obj1.getCity());//Done 18
		        row.createCell((short) 17).setCellValue(obj1.getDistrict());//Done 19
		        row.createCell((short) 18).setCellValue(obj1.getPincode());//Done 21
		        row.createCell((short) 19).setCellValue(obj1.getState());//Done 22
		        row.createCell((short) 20).setCellValue(obj1.getMseITPAN());//done 23
		        row.createCell((short) 21).setCellValue(obj1.getUdyogAadharNo()); //Done 24
		        row.createCell((short) 22).setCellValue(obj1.getIndustryNature());//done 25
		        row.createCell((short) 23).setCellValue(obj1.getIndustrySector());//Done 26
		        row.createCell((short) 24).setCellValue(obj1.getNoOfEmployees());//Done 27
		        row.createCell((short) 25).setCellValue(obj1.getProjectedSales());//Done 29
		        row.createCell((short) 26).setCellValue(obj1.getProjectedExports());//Done 30
		        row.createCell((short) 27).setCellValue(obj1.getNewExistingUnit());//Done 31
		        row.createCell((short) 28).setCellValue(obj1.getPreviousbankingExperience());//Done 32
		        row.createCell((short) 29).setCellValue(obj1.getChiefPromoterFirstName());//Done 33
		        row.createCell((short) 30).setCellValue(obj1.getChiefPromoterMiddleName());//Done 34
		        row.createCell((short) 31).setCellValue(obj1.getChiefPromoterLastName());//Done 35
		        row.createCell((short) 32).setCellValue(obj1.getChiefPromoterITPAN());//Done 36
		        row.createCell((short) 33).setCellValue(obj1.getChiefPromoterMailId());//Done 37
		        row.createCell((short) 34).setCellValue(obj1.getChiefPromoterContactNo());//Done 38
		        row.createCell((short) 35).setCellValue(obj1.getMinorityCommunity());//Done 38
		        row.createCell((short) 36).setCellValue(obj1.getHandicapped());//Done 38
		        row.createCell((short) 37).setCellValue(obj1.getWomen());//Done 38
		        row.createCell((short) 38).setCellValue(obj1.getCategory());//Done 38
		        if(obj1.getAADHAR_NUMBER()!=null)
			        row.createCell((short) 39).setCellValue(obj1.getAADHAR_NUMBER());
					index++;
				}

			FileOutputStream fileOut = new FileOutputStream(filePath);
			hwb.write(fileOut);
			fileOut.close();
			Map<String, Object> modelAct = new HashMap<String, Object>();
			modelAct.put("adminlist",userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
			modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSECHECKER", "Registration"));
			modelAct.put("applicationList",userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
			modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
			modelAct.put("actName", userActivityService.getActivityName("CGTMSECHECKER","cgtmseCheckerForBacthApprovalRM"));
			modelAct.put("homePage", "cgtmseCheckerHome");
			modelAct.put("CList",
					userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
			modelAct.put("CBMFList",userActivityService.getReport("CGTMSECHECKER", "Claim_Bank_Mandate"));
			ModelAndView model1 = new ModelAndView("cgtmseCheckerForBatchApprovalAndRejection",modelAct);
			model1.addObject("uploadedDateKey", uploadedDate1);
			model1.addObject("shortNameKey", shortName1);
			model1.addObject("portfolioIDKey", portfolioID1);
			model1.addObject("quaterNameKey", quaterName1);
			model1.addObject("excelFileIdKey", excelFileId1);
			model1.addObject("totNoOfRecordsUploadedInExcelFileKey",totNoOfRecordsUploadedInExcelFile1);
			model1.addObject("totalSanctionAmtKey", totalSanctionAmt1);
			model1.addObject("excelFileFullNameKey", excelFileFullName1);
			model1.addObject("cgtmseCheckerexcelFileDonloadedSuccessfully","File Downloaded Successfully in theis location D:/PARMANAND/nbfcCheckerDownLoadExcelFile.xls");
			File downloadFile = new File(filePath);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			response.setContentLength((int) downloadFile.length());
			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",downloadFile.getName());
			response.setHeader(headerKey, headerValue);
			// get output stream of the response
			OutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			// write bytes read from the input stream into the output stream
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
			return model1;
		} catch (Exception e) {
			System.out.println("Exception == " + e);
		}
		return null;
	}
	@RequestMapping(value = "/storeCgtmseCheckerDataRM", params = "action1", method = RequestMethod.POST)
	public ModelAndView checkerApprove(@ModelAttribute("command") CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave,
			BindingResult result, HttpServletRequest request,
			HttpSession session, Model model) {
		Map<String, Object> modelAct = new HashMap<String, Object>();
		try {
			String Status = "CCA";// Cgtmse Checker approve
			String fileName=heckerForBatchApprovalAndRejectionSumbissionSave.getFilePath();
			String userId = (String) session.getAttribute("userId");
			cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.setStatus(Status);
			cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.setFilePath(fullFileName1);
			cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.setCgtmseCheckerId(cgtmseCheckerId);
			cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.setPortfolioNo(portfolioNo);
			cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.setFilePath(fileName);
			modelAct.put("adminlist",userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
			modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSECHECKER", "Registration"));
			modelAct.put("applicationList",userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
			modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
			modelAct.put("actName", userActivityService.getActivityName("CGTMSECHECKER","cgtmseCheckerForBacthApprovalRM"));
			modelAct.put("repList",
					userActivityService.getReport("CGTMSECHECKER", "User_Report"));
			modelAct.put("homePage", "cgtmseCheckerHome");
			modelAct.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSECHECKER", "Guarantee_Maintenance"));
			modelAct.put("CList",
					userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
			modelAct.put("CBMFList",userActivityService.getReport("CGTMSECHECKER", "Claim_Bank_Mandate"));
			cgtmseCheckerForBatchApprovalAndRejectionService.checkerApprove(cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave,userId);
			model.addAttribute("recordApprovedByCGTMSEChecker","Approved Successfully.");
			model.addAttribute("success","true");
			return new ModelAndView("cgtmseCheckerForBatchApprovalAndRejection",modelAct);
		} catch (Exception e) {
			System.out.println("Exception == " + e);
		}
		return new ModelAndView("cgtmseCheckerForBatchApprovalAndRejection",modelAct);
	}

	@RequestMapping(value = "/storeCgtmseCheckerDataRM",params = "action2",method = RequestMethod.POST)
	public ModelAndView checkerRejection(
			@ModelAttribute("command") CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave,
			BindingResult result, HttpServletRequest request,
			HttpSession session, Model model) {
		try {
			String Status = "CCR";// Cgtmse Checker approve
			cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.setStatus(Status);
			cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.setFilePath(excelFileId1);
			cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.setCgtmseCheckerId(cgtmseCheckerId);
			cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.setPortfolioNo(portfolioNo);
			cgtmseCheckerForBatchApprovalAndRejectionService.checkerRejected(cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave);
			Map<String, Object> modelAct = new HashMap<String, Object>();
			modelAct.put("adminlist",userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
			modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSECHECKER", "Registration"));
			modelAct.put("applicationList",userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
			modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
			modelAct.put("actName", userActivityService.getActivityName("CGTMSECHECKER","cgtmseCheckerForBacthApprovalRM"));
			modelAct.put("repList",
					userActivityService.getReport("CGTMSECHECKER", "User_Report"));
			modelAct.put("homePage", "cgtmseCheckerHome");
			modelAct.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSECHECKER", "Guarantee_Maintenance"));
			modelAct.put("CList",
					userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
			modelAct.put("CBMFList",userActivityService.getReport("CGTMSECHECKER", "Claim_Bank_Mandate"));
			model.addAttribute("recordRejectedByCGTMSEChecker","Rejected Successfully.");
			model.addAttribute("Reject","true");
			return new ModelAndView("cgtmseCheckerForBatchApprovalAndRejection",modelAct);
		} catch (Exception e) {
			System.out.println("Exception == " + e);
		}
		return null;
	}
	 @ExceptionHandler(CustomExceptionHandler.class)
		public ModelAndView handleCustomException(CustomExceptionHandler ex) {
			Map<String, Object> model1 = new HashMap<String, Object>();		
			ModelAndView model = new ModelAndView("customException",model1);
			model.addObject("customException", ex.getMessage());
			return model;
		}

		@ExceptionHandler(Exception.class)
		public ModelAndView handleAllException(Exception ex) {
			Map<String, Object> model1 = new HashMap<String, Object>();		
			ModelAndView model = new ModelAndView("exception",model1);
			model.addObject("exception", ex.getCause());
			return model;
		}

		@ExceptionHandler(ArithmeticException.class)
		public ModelAndView handleArithException(ArithmeticException ex) {
			Map<String, Object> model1 = new HashMap<String, Object>();		
			ModelAndView model = new ModelAndView("exception",model1);
			model.addObject("exception", ex.getMessage());
			return model;
		}

		@ExceptionHandler(NullPointerException.class)
		public ModelAndView handleNullException(NullPointerException ex) {
			Map<String, Object> model1 = new HashMap<String, Object>();		
			ModelAndView model = new ModelAndView("exception",model1);
			model.addObject("exception", "Data is null");
			return model;
		}

}
