package com.nbfc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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

import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.helper.CGTMSEMakerBatchUploadDetails;
import com.nbfc.model.CGTMSEMakerForBatchApprovalUploadedBatchFile;
import com.nbfc.model.CGTMSEMakerRejectionAndSumbission;
import com.nbfc.model.CGTMSEMakerRejectionAndSumbissionSave;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.CGTMSEMakerForBatchApprovalGetStatusService;
import com.nbfc.service.CGTMSEMakerForBatchApprovalUploadedBatchFileService;
import com.nbfc.service.CGTMSEMakerRejectionAndSumbissionService;
import com.nbfc.service.LoginService;
import com.nbfc.service.UserActivityService;
import com.raistudies.domain.CustomExceptionHandler;

import java.sql.Date;

@Controller
public class CGTMSEMakerRejectionAndSumbissionController {
	// For UAT
	// public static final String FILE_NAME = "E:/DownLoadNBFCMakerFile.xlsx";
	@Autowired
	private CGTMSEMakerForBatchApprovalUploadedBatchFileService cgtmseMakerForBatchApprovalUploadedBatchFileService;

	@Autowired
	private CGTMSEMakerForBatchApprovalGetStatusService cgtmseMakerForBatchApprovalGetStatusService;
	Long rejectedNoOfFileByCGTMSE = (long) 0;
	@Autowired
	LoginService lofinService;

	ModelAndView model;
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;
	// public static final String FILE_NAME = "F:/DownLoadNBFCMakerFile.xlsx";
	// public static final String FILE_NAME =
	// "G:/NBFC_Template/DownLoadNBFCMakerFile.xlsx";
	@Value("${downloadFileDirPath}")
	private String downloadFileDirPath;
	@Value("${downloadFileName}")
	private String downloadFileName;
	static Logger log = Logger.getLogger(NBFCController.class.getName());

	public static final int BUFFER_SIZE = 4096;
	CGTMSEMakerRejectionAndSumbission cgtmseMakerRejectionAndSumbissionObj2 = new CGTMSEMakerRejectionAndSumbission();
	CGTMSEMakerRejectionAndSumbissionSave CGTMSEMakerRejectionAndSumbissionSaveObj1 = new CGTMSEMakerRejectionAndSumbissionSave();

	String fileName;
	String portfolioNoKey;
	String fileIdKey;
	String quaterIdKey;
	String shortNameKey;
	Integer noOfFileKey;
	String sanctionAmountKey;
	String fullFileName1;
	String status = "NCA";
	String dateOfUpload;
	String cgtmseRjectionID = null;
	Integer noOfRecordsOfExcelFile1 = 0;

	String paramID1 = null;
	String fileId1 = null;
	String quaterId1 = null;
	String shortName1 = null;
	String sanctionAmount1 = null;
	String fullFileName2 = null;
	String dateOfUpload1 = null;
	Integer noOfRecordsOfExcelFile2 = 0;
	String portfolioNoFieldValue = null;

	/**
	 * Path of the file to be downloaded, relative to application's directory
	 */

	@Autowired
	private CGTMSEMakerRejectionAndSumbissionService cgtmseMakerRejectionAndSumbissionService;
	@Autowired
	UserActivityService userActivityService;

	// This method will call for GET Request with name of url of jsp
	@RequestMapping(value = "cgtmseMakerRejectionAndSumbissionRM", method = RequestMethod.GET)
	public ModelAndView showRejectionBatchApprovalInputForm(
			@ModelAttribute("command") CGTMSEMakerRejectionAndSumbission cgtmseMakerRejectionAndSumbission,
			BindingResult result,
			HttpServletRequest request,
			@RequestParam("paramID") String paramID,
			@RequestParam("fileId") String fileId,
			@RequestParam("quaterId") String quaterId,
			@RequestParam("shortName") String shortName,
			@RequestParam("sanctionAmount") String sanctionAmount,
			@RequestParam("outstandingAmount") String outstandingAmount,
			@RequestParam("fullFileName") String fullFileName,
			@RequestParam("uploadedDate") String uploadedDate,
			@RequestParam("noOfRecordsOfExcelFile") Integer noOfRecordsOfExcelFile,
			HttpSession session) {
		cgtmseRjectionID = (String) session.getAttribute("loginUserId");
		fileName = fullFileName;
		portfolioNoKey = paramID;
		fileIdKey = fileId;
		quaterIdKey = quaterId;
		shortNameKey = shortName;
		sanctionAmountKey = sanctionAmount;
		fullFileName1 = fullFileName;
		status = "NCA";
		dateOfUpload = uploadedDate;
		String portfolioID = paramID;
		paramID1 = paramID;
		portfolioNoFieldValue = paramID1;
		paramID1 = paramID;
		fileId1 = fileId;
		quaterId1 = quaterId;
		shortName1 = shortName;
		sanctionAmount1 = sanctionAmount;
		fullFileName2 = fullFileName;
		dateOfUpload1 = uploadedDate;
		noOfRecordsOfExcelFile2 = noOfRecordsOfExcelFile;
		noOfRecordsOfExcelFile1 = noOfRecordsOfExcelFile;
		cgtmseMakerRejectionAndSumbissionObj2.setFilePath(fullFileName1);
		cgtmseMakerRejectionAndSumbissionObj2.setPortfolioNo(portfolioNoKey);
		cgtmseMakerRejectionAndSumbissionObj2.setStatus(status);
		CGTMSEMakerRejectionAndSumbissionSaveObj1.setFilePath(fullFileName1);
		CGTMSEMakerRejectionAndSumbissionSaveObj1.setPortfolioNo(portfolioNoKey);
		CGTMSEMakerRejectionAndSumbissionSaveObj1.setStatus(status);
		session.setAttribute("paramIDKey", paramID);
		request.setAttribute("paramIDKey1", paramID);
		cgtmseMakerRejectionAndSumbission.setPortfolioNo(paramID);
		String status2 = "NCA";
		CGTMSEMakerRejectionAndSumbission cgtmseMakerRejectionAndSumbissionObj = new CGTMSEMakerRejectionAndSumbission();
		cgtmseMakerRejectionAndSumbissionObj.setStatus(status2);
		Map<String, Object> modelAct = new HashMap<String, Object>();
		modelAct.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		modelAct.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		modelAct.put("actName", userActivityService.getActivityName("CGTMSEMAKER","cgtmseMakerForBacthApprovalRM"));
		modelAct.put("homePage", "cgtmseMakerHome");
		 modelAct.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		ModelAndView model = new ModelAndView("cgtmseMakerRejectionAndSumbission", modelAct);
		model.addObject("portfolioNoKey", paramID);
		model.addObject("fileIdKey", fileId);
		model.addObject("quaterIdKey", quaterId);
		model.addObject("shortNameKey", shortName);
		model.addObject("sanctionAmountKey", sanctionAmount);
		model.addObject("outstandingAmount", outstandingAmount);
		model.addObject("fullFileName1Key", fullFileName1);
		model.addObject("dateOfUploadKey", dateOfUpload);
		model.addObject("noOfRecordsOfExcelFile1Key", noOfRecordsOfExcelFile1);
		model.addObject("lists", paramID);
		return model;
	}

	@RequestMapping(value = "cgtmseMakerForBatchApprovalUploadedBatchFileRMBack", method = RequestMethod.POST)
	public ModelAndView showcgtMakerForBatchApprovalUploadedBatchFileInputForm(
			@ModelAttribute("command") CGTMSEMakerForBatchApprovalUploadedBatchFile cgtmseMakerForBatchApprovalUploadedBatchFile,
			BindingResult result, HttpServletRequest request) {
		try {
			String fileId = null;
			String fileId1 = null;
			String fileName = null;
			Integer quaterId = null;
			String shortName = null;
			Long noOfFile = null;
			String status = null;
			Long portfolioNo = null;
			String uploadedDate = null;
			String sanctionAmount = null;
			String fileN = null;
			Long noOfRecord = (long) 0;
			String quaterName = null;
			List<String> listObj1 = new ArrayList<String>();
			List<Object> list1 = new ArrayList<Object>();
			Map<String, Object> modelAct = new HashMap<String, Object>();
			modelAct.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
			modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			modelAct.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
			modelAct.put("actName", userActivityService.getActivityName("CGTMSEMAKER","cgtmseMakerForBacthApprovalRM"));
			 modelAct.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSEMAKER", "Guarantee_Maintenance"));
			modelAct.put("homePage", "cgtmseMakerHome");
			 modelAct.put("repList",
						userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			ModelAndView model = new ModelAndView("cgtmseMakerForBatchApprovalUploadedBatchFile", modelAct);
			String NCAstatus = "NCA";
			cgtmseMakerForBatchApprovalUploadedBatchFile.setStatus(NCAstatus);
			CGTMSEMakerForBatchApprovalUploadedBatchFile cgtmseMakerForBatchApprovalUploadedBatchFileObj2 = null;
			CGTMSEMakerForBatchApprovalUploadedBatchFile cgtmseMakerForBatchApprovalUploadedBatchFileObj3 = null;
			CGTMSEMakerForBatchApprovalUploadedBatchFile obj2 = new CGTMSEMakerForBatchApprovalUploadedBatchFile();
			List<Object> listObj = cgtmseMakerForBatchApprovalUploadedBatchFileService.getUploadedBatchFileDetails(cgtmseMakerForBatchApprovalUploadedBatchFile);
			if (listObj.size() != 0) {
				Iterator<Object> itr1 = listObj.iterator();
				while (itr1.hasNext()) {
					Object[] obj1 = (Object[]) itr1.next();
					fileId = (String) obj1[0];
					fileN = fileId;
					quaterId = (Integer) obj1[1];
					shortName = (String) obj1[2];
					noOfFile = (Long) obj1[3];
					status = (String) obj1[4];
					portfolioNo = (Long) obj1[5];
					sanctionAmount = (String) obj1[6];
					noOfRecord = (Long) obj1[7];
					quaterName = (String) obj1[8];
					java.sql.Date NbfcUploadedDate = (Date) obj1[9];
					SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					String reportDate = df.format(NbfcUploadedDate);
					String nbfcExcelUploadtedDate = reportDate.substring(0, 10);
					String splitnbfcExcelUploadtedDate[] = nbfcExcelUploadtedDate.split("/");
					String formatedNbfcExcelUploadtedDate = splitnbfcExcelUploadtedDate[1]
							+ "/"
							+ splitnbfcExcelUploadtedDate[0]
							+ "/"
							+ splitnbfcExcelUploadtedDate[2];
					String fileNameSplit[] = fileId.split("/");
					fileId1 = fileId.substring(fileId.lastIndexOf("/") + 1,fileId.lastIndexOf("."));
					CGTMSEMakerBatchUploadDetails cgtmseMakerBatchUploadDetailsObj = new CGTMSEMakerBatchUploadDetails();
					cgtmseMakerBatchUploadDetailsObj.setFilePath(fileId1);
					cgtmseMakerBatchUploadDetailsObj.setSubPortfolioNo(quaterId);
					cgtmseMakerBatchUploadDetailsObj.setShortName(shortName);
					cgtmseMakerBatchUploadDetailsObj.setNoOfFile(noOfFile);
					cgtmseMakerBatchUploadDetailsObj.setStatus(status);
					cgtmseMakerBatchUploadDetailsObj.setPortfolioNo(portfolioNo);
					cgtmseMakerBatchUploadDetailsObj.setSanctionAmount(sanctionAmount);
					cgtmseMakerBatchUploadDetailsObj.setFileName(fileN);
					cgtmseMakerBatchUploadDetailsObj.setNoOfRecords(noOfRecord);
					cgtmseMakerBatchUploadDetailsObj.setPortfolioQuarter(quaterName);
					cgtmseMakerBatchUploadDetailsObj.setNbfcUploadedDate(formatedNbfcExcelUploadtedDate);
					list1.add(cgtmseMakerBatchUploadDetailsObj);
				}
				model.addObject("lists", list1);
				return model;
			} else {
				model.addObject("recordNotExitKey2", "Data Not Exits.");
				return model;
			}
		} catch (Exception e) {
			System.out.println("Exception raised===" + e);
		}
		return model;
	}

	// For DownLoadExcel File

	@RequestMapping(value = "/cgtmseMakerRejectionAndSumbissionRMdownLoad", method = RequestMethod.GET)
	public ModelAndView nbfcMakerFileDownLoad(
			@ModelAttribute("command") CGTMSEMakerRejectionAndSumbission cgtmseMakerRejectionAndSumbission,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		try {
			String filePath = downloadFileDirPath + File.separator+ downloadFileName;
			File file = new File(downloadFileDirPath);
			boolean isCreated = file.mkdir();
			if (isCreated) {
				File file1 = new File(filePath);
				boolean isExists = file1.exists();
				if (isExists) {
				} else {
					file1.createNewFile();
				}
			}
			String fPath = cgtmseMakerRejectionAndSumbissionObj2.getFilePath();
			String status3 = "NCA";
			String portNumner = cgtmseMakerRejectionAndSumbissionObj2.getPortfolioNo();
			Integer QuaterNumber = cgtmseMakerRejectionAndSumbissionObj2.getSubPortfolioNo();
			String status1 = cgtmseMakerRejectionAndSumbissionObj2.getStatus();
			cgtmseMakerRejectionAndSumbission.setFilePath(fileId1);// PortfolioId
			cgtmseMakerRejectionAndSumbission.setStatus(status3);
			List<CGTMSEMakerRejectionAndSumbission> list = cgtmseMakerRejectionAndSumbissionService.getDataForNBFCMakerFileDownLoad(cgtmseMakerRejectionAndSumbission);
			// Writing and Downlaoding Excel File
			XSSFWorkbook hwb = new XSSFWorkbook();
			XSSFSheet sheet = hwb.createSheet("NBFCMakerDownLoadedFile");
			// Making bold and color to excel column heading
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
			// Creating First rows for excel heading
			XSSFRow rowhead = sheet.createRow((short) 0);
			XSSFCell cell0 = rowhead.createCell(0);
			cell0.setCellStyle(style);
			cell0.setCellValue("Unit Name");// Done 1

			XSSFCell cell1 = rowhead.createCell(1);
			cell1.setCellStyle(style);
			cell1.setCellValue("Constitution");// Done 3

			XSSFCell cell2 = rowhead.createCell(2);
			cell2.setCellStyle(style);
			cell2.setCellValue("Loan Type");// Done 4

			XSSFCell cell3 = rowhead.createCell(3);
			cell3.setCellStyle(style);
			cell3.setCellValue("Loan Account No.");// Done 5

			XSSFCell cell4 = rowhead.createCell(4);
			cell4.setCellStyle(style);
			cell4.setCellValue("Sanction Date");// Done 6

			XSSFCell cell5 = rowhead.createCell(5);
			cell5.setCellStyle(style);
			cell5.setCellValue("Sanctioned Amount");// Done 7

			XSSFCell cell6 = rowhead.createCell(6);
			cell6.setCellStyle(style);
			cell6.setCellValue("First Disbursement Date");// Done 9

			XSSFCell cell7 = rowhead.createCell(7);
			cell7.setCellStyle(style);
			cell7.setCellValue("Interest Rate (% p.a)");// Done 10

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
			cell13.setCellValue("Tenor In Months");// Done 11

			XSSFCell cell14 = rowhead.createCell(14);
			cell14.setCellStyle(style);
			cell14.setCellValue("Whether The Assisted Unit Is Micro/Small ?");// Done
																				// 12

			XSSFCell cell15 = rowhead.createCell(15);
			cell15.setCellStyle(style);
			cell15.setCellValue("Unit Address");// Done 13

			XSSFCell cell16 = rowhead.createCell(16);
			cell16.setCellStyle(style);
			cell16.setCellValue("City");// Done 14

			XSSFCell cell17 = rowhead.createCell(17);
			cell17.setCellStyle(style);
			cell17.setCellValue("District");// Done 15

			XSSFCell cell18 = rowhead.createCell(18);
			cell18.setCellStyle(style);
			cell18.setCellValue("Pincode");// Done 16

			XSSFCell cell19 = rowhead.createCell(19);
			cell19.setCellStyle(style);
			cell19.setCellValue("State");// Done 17

			XSSFCell cell20 = rowhead.createCell(20);
			cell20.setCellStyle(style);
			cell20.setCellValue("Unit IT-PAN");// Done 18

			XSSFCell cell21 = rowhead.createCell(21);
			cell21.setCellStyle(style);
			cell21.setCellValue("Udyog Aadhar No.");// Done 19

			XSSFCell cell22 = rowhead.createCell(22);
			cell22.setCellStyle(style);
			cell22.setCellValue("Industry Nature");// Done 21

			XSSFCell cell23 = rowhead.createCell(23);
			cell23.setCellStyle(style);
			cell23.setCellValue("Industry Sector");// Done 22

			XSSFCell cell24 = rowhead.createCell(24);
			cell24.setCellStyle(style);
			cell24.setCellValue("No. Of Employees");// Done 23

			XSSFCell cell25 = rowhead.createCell(25);
			cell25.setCellStyle(style);
			cell25.setCellValue("Projected Sales");// Done 24

			XSSFCell cell26 = rowhead.createCell(26);
			cell26.setCellStyle(style);
			cell26.setCellValue("Projected Exports");// Done 25

			XSSFCell cell27 = rowhead.createCell(27);
			cell27.setCellStyle(style);
			cell27.setCellValue("New/ Existing Unit");// Done 26

			XSSFCell cell28 = rowhead.createCell(28);
			cell28.setCellStyle(style);
			cell28.setCellValue("Customer  Having Any Previous Banking Experience?");// Done
			
			XSSFCell cell29 = rowhead.createCell(29);
			cell29.setCellStyle(style);
			cell29.setCellValue("Chief Promoter First Name"); // Done 29

			XSSFCell cell30 = rowhead.createCell(30);
			cell30.setCellStyle(style);
			cell30.setCellValue("Chief Promoter Middle Name");// Done 30

			XSSFCell cell31 = rowhead.createCell(31);
			cell31.setCellStyle(style);
			cell31.setCellValue("Chief Promoter Last Name");// Done 31

			XSSFCell cell32 = rowhead.createCell(32);
			cell32.setCellStyle(style);
			cell32.setCellValue("Chief Promoter IT-PAN");// Done 32

			XSSFCell cell33 = rowhead.createCell(33);
			cell33.setCellStyle(style);
			cell33.setCellValue("Chief Promoter's Mail Id");// Done 33

			XSSFCell cell34 = rowhead.createCell(34);
			cell34.setCellStyle(style);
			cell34.setCellValue("Chief Promoter's Contact Number");// Done 34

			XSSFCell cell35 = rowhead.createCell(35);
			cell35.setCellStyle(style);
			cell35.setCellValue("Minority Community");// Done 35

			XSSFCell cell36 = rowhead.createCell(36);
			cell36.setCellStyle(style);
			cell36.setCellValue("Handicapped");// Done 36

			XSSFCell cell37 = rowhead.createCell(37);
			cell37.setCellStyle(style);
			cell37.setCellValue("Women");// Done 37

			XSSFCell cell38 = rowhead.createCell(38);
			cell38.setCellStyle(style);
			cell38.setCellValue("Category");// Done 38

			XSSFCell cell39 = rowhead.createCell(39);
			cell39.setCellStyle(style);
			cell39.setCellValue("Aadhar Number");

			int index = 1;
			int sno = 0;
			Iterator<CGTMSEMakerRejectionAndSumbission> itr2 = list.iterator();
			while (itr2.hasNext()) {
				CGTMSEMakerRejectionAndSumbission obj1 = (CGTMSEMakerRejectionAndSumbission) itr2.next();
				sno++;
				XSSFRow row = sheet.createRow((short) index);
				row.createCell((short) 0).setCellValue(obj1.getMseName());// Done
				row.createCell((short) 1).setCellValue(obj1.getConstitution());// Done
				row.createCell((short) 2).setCellValue(obj1.getLoneType());// Done
				row.createCell((short) 3).setCellValue(obj1.getLoanAccountNo());// Done
				row.createCell((short) 4).setCellValue(obj1.getSnctionDate());// Done
				row.createCell((short) 5).setCellValue(obj1.getSanctionedAmount());// Done 6
				row.createCell((short) 6).setCellValue(obj1.getFirstDisbursementDate());// Done 7
				row.createCell((short) 7).setCellValue(obj1.getInterestRate());// Done
				row.createCell((short) 8).setCellValue(obj1.getRETAIL_TRADE());// Done
				row.createCell((short) 9).setCellValue(obj1.getPARTIAL_SECURITY_FLAG());// Done 11
				row.createCell((short) 10).setCellValue(obj1.getGUARANTEE_AMOUNT());// Done 12
				row.createCell((short) 11).setCellValue(obj1.getCOLLETRAL_SECURITY_AMOUNT());// Done 13
				row.createCell((short) 12).setCellValue(obj1.getOUTSTANDING_AMOUNT()); // Done 14
				row.createCell((short) 13).setCellValue(obj1.getTenorInMonth());// Done
				row.createCell((short) 14).setCellValue(obj1.getMicroSmall());// Done
				row.createCell((short) 15).setCellValue(obj1.getMseAddress());// Done
				row.createCell((short) 16).setCellValue(obj1.getCity());// Done
				row.createCell((short) 17).setCellValue(obj1.getDistrict());// Done
				row.createCell((short) 18).setCellValue(obj1.getPincode());// Done
				row.createCell((short) 19).setCellValue(obj1.getState());// Done
				row.createCell((short) 20).setCellValue(obj1.getMseITPAN());// done
				row.createCell((short) 21).setCellValue(obj1.getUdyogAadharNo()); // Done 24
				row.createCell((short) 22).setCellValue(obj1.getIndustryNature());// done 25
				row.createCell((short) 23).setCellValue(obj1.getIndustrySector());// Done 26
				row.createCell((short) 24).setCellValue(obj1.getNoOfEmployees());// Done 27
				row.createCell((short) 25).setCellValue(
						obj1.getProjectedSales());// Done 29
				row.createCell((short) 26).setCellValue(
						obj1.getProjectedExports());// Done 30
				row.createCell((short) 27).setCellValue(
						obj1.getNewExistingUnit());// Done 31
				row.createCell((short) 28).setCellValue(
						obj1.getPreviousbankingExperience());// Done 32
				row.createCell((short) 29).setCellValue(
						obj1.getChiefPromoterFirstName());// Done 33
				row.createCell((short) 30).setCellValue(
						obj1.getChiefPromoterMiddleName());// Done 34
				row.createCell((short) 31).setCellValue(
						obj1.getChiefPromoterLastName());// Done 35
				row.createCell((short) 32).setCellValue(
						obj1.getChiefPromoterITPAN());// Done 36
				row.createCell((short) 33).setCellValue(
						obj1.getChiefPromoterMailId());// Done 37
				row.createCell((short) 34).setCellValue(
						obj1.getChiefPromoterContactNo());// Done 38
				row.createCell((short) 35).setCellValue(
						obj1.getMinorityCommunity());// Done 38
				row.createCell((short) 36).setCellValue(obj1.getHandicapped());// Done
																				// 38
				row.createCell((short) 37).setCellValue(obj1.getWomen());// Done
																			// 38
				row.createCell((short) 38).setCellValue(obj1.getCategory());// Done
																			// 38
				if (obj1.getAADHAR_NUMBER() != null)
					row.createCell((short) 39).setCellValue(
							obj1.getAADHAR_NUMBER());

				// row.createCell((short)
				// 36).setCellValue(obj1.getCategory());//Done 38

				index++;
			}
			FileOutputStream fileOut = new FileOutputStream(filePath);
			hwb.write(fileOut);
			fileOut.close();

			ModelAndView model = new ModelAndView(
					"cgtmseMakerRejectionAndSumbission");
			model.addObject("portfolioNoKey", paramID1);
			model.addObject("fileIdKey", fileId1);
			model.addObject("quaterIdKey", quaterId1);
			model.addObject("shortNameKey", shortName1);
			model.addObject("sanctionAmountKey", sanctionAmount1);
			model.addObject("fullFileName1Key", fullFileName2);
			model.addObject("dateOfUploadKey", dateOfUpload1);
			model.addObject("noOfRecordsOfExcelFile1Key",
					noOfRecordsOfExcelFile2);
			model.addObject(
					"excelFileDownLoadSuccessfully",
					"File DownLoaded Successfully in this location D:/PARMANAND/nbfcMakerDownLoadExcelFile.xls.");
			model.addObject("lists", portfolioNoFieldValue);

			File downloadFile = new File(filePath);
			log.info("downloadFile =" + downloadFile);
			FileInputStream inputStream = new FileInputStream(downloadFile);

			response.setContentLength((int) downloadFile.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					downloadFile.getName());
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
			return model;
		} catch (Exception e) {
			log.info("Exception == " + e);
			System.out.println("Exception == " + e);
		}
		return null;
	}

	// CGTMSE MAKER APPROVAL
	@RequestMapping(value = "/storeCgtmseDataRM", params = "action1", method = RequestMethod.POST)
	public ModelAndView save(
			@ModelAttribute("command") CGTMSEMakerRejectionAndSumbissionSave cgtmseMakerRejectionAndSumbissionSave,
			BindingResult result, HttpServletRequest request,
			HttpSession session, Model model) {
		Map<String, Object> modelAct = new HashMap<String, Object>();
		try {
			log.info("save method call as part of CGTMSEMakerRejectionAndSumbissionController=====");
			String fPath = cgtmseMakerRejectionAndSumbissionObj2.getFilePath();
			String portNumner = cgtmseMakerRejectionAndSumbissionObj2
					.getPortfolioNo();
			Integer QuaterNumber = cgtmseMakerRejectionAndSumbissionObj2
					.getSubPortfolioNo();
			String status1 = cgtmseMakerRejectionAndSumbissionObj2.getStatus();

			String fPath1 = CGTMSEMakerRejectionAndSumbissionSaveObj1
					.getFilePath();
			String portNumner1 = CGTMSEMakerRejectionAndSumbissionSaveObj1
					.getPortfolioNo();
			Integer QuaterNumber1 = CGTMSEMakerRejectionAndSumbissionSaveObj1
					.getSubPortfolioNo();
			String status2 = CGTMSEMakerRejectionAndSumbissionSaveObj1
					.getStatus();


			String Status = "CMA";// Cgtmse Maker approve

			cgtmseMakerRejectionAndSumbissionSave.setFilePath(fPath);
			cgtmseMakerRejectionAndSumbissionSave.setPortfolioNo(portNumner1);
			cgtmseMakerRejectionAndSumbissionSave.setStatus(Status);
			cgtmseMakerRejectionAndSumbissionSave
					.setSubPortfolioNo(QuaterNumber1);
			cgtmseMakerRejectionAndSumbissionSave.setStatus(status2);
			cgtmseMakerRejectionAndSumbissionSave
					.setCgtmseMakerId(cgtmseRjectionID);

			boolean appRefNoList1 = cgtmseMakerRejectionAndSumbissionService
					.storeData(cgtmseMakerRejectionAndSumbissionSave);
			log.info("Record Updated Successfully===" + appRefNoList1);
			modelAct.put("adminlist",
					userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
			modelAct.put("guaranteelist",
					userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			modelAct.put("applicationList",
					userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			modelAct.put("RPaymentList",
					userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
			modelAct.put("actName", userActivityService.getActivityName("CGTMSEMAKER",
					"cgtmseMakerForBacthApprovalRM"));// Added by Say 31 Jan19
			modelAct.put("homePage", "cgtmseMakerHome");
			 modelAct.put("repList",
						userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			 modelAct.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSEMAKER", "Guarantee_Maintenance"));
			model.addAttribute("recordApproveByCGTMSEMaker",
					"Submitted to CGTMSE checker successfully.");
			return new ModelAndView("cgtmseMakerRejectionAndSumbission",
					modelAct);
		} catch (Exception e) {
			System.out.println("Exception ===" + e);
		}
		return null;
	}

	// CGTMSE Maker Rejection
	@RequestMapping(value = "/storeCgtmseDataReject", method = RequestMethod.POST)
	public ModelAndView cgtmseMakerRejection(
			@ModelAttribute("command") CGTMSEMakerRejectionAndSumbissionSave cgtmseMakerRejectionAndSumbissionSaveObj6,
			BindingResult result, HttpServletRequest request,
			HttpSession session, Model model) {
		Map<String, Object> modelAct = new HashMap<String, Object>();
		try {
			String remarks = cgtmseMakerRejectionAndSumbissionSaveObj6
					.getRejection_reason();
			String cgtmseRjectionID1 = cgtmseRjectionID;
			System.out.println("cgtmseRjectionID1===" + cgtmseRjectionID1);
			String Status = "CMR";// Cgtmse Maker Rejected
			CGTMSEMakerRejectionAndSumbissionSave cgtmseMakerRejectionAndSumbissionSaveObj4 = new CGTMSEMakerRejectionAndSumbissionSave();
			cgtmseMakerRejectionAndSumbissionSaveObj4
					.setRejection_reason(remarks);
			cgtmseMakerRejectionAndSumbissionSaveObj4.setFilePath(fileName);
			cgtmseMakerRejectionAndSumbissionSaveObj4.setStatus(Status);
			cgtmseMakerRejectionAndSumbissionSaveObj4
					.setCgtmseMakerId(cgtmseRjectionID);

			cgtmseMakerRejectionAndSumbissionService
					.cgtmseMakerRejected(cgtmseMakerRejectionAndSumbissionSaveObj4);
			model.addAttribute("recordRejectedByCGTMSEMacker",
					"Rejected Successfully.");
			modelAct.put("adminlist",
					userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
			modelAct.put("guaranteelist",
					userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			modelAct.put("applicationList",
					userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			modelAct.put("RPaymentList",
					userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
			 modelAct.put("repList",
						userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			modelAct.put("actName", userActivityService.getActivityName("CGTMSEMAKER",
					"cgtmseMakerForBacthApprovalRM"));// Added by Say 31 Jan19
			 modelAct.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSEMAKER", "Guarantee_Maintenance"));
			modelAct.put("homePage", "cgtmseMakerHome");
			return new ModelAndView("cgtmseMakerRejectionAndSumbission",
					modelAct);
		} catch (Exception e) {
			System.out.println("Exception==" + e);
			log.info("Exception==" + e);
		}
		return null;
	}

	@ExceptionHandler(CustomExceptionHandler.class)
	public ModelAndView handleCustomException(CustomExceptionHandler ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();
		ModelAndView model = new ModelAndView("customException", model1);
		model.addObject("customException", ex.getMessage());
		return model;

	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();
		ModelAndView model = new ModelAndView("exception", model1);
		model.addObject("exception", ex.getCause());
		return model;

	}

	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithException(ArithmeticException ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();
		ModelAndView model = new ModelAndView("exception", model1);
		model.addObject("exception", ex.getMessage());
		return model;

	}

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullException(NullPointerException ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();
		ModelAndView model = new ModelAndView("exception", model1);
		model.addObject("exception", "Data is null");
		return model;

	}

}
