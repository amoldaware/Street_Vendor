package com.nbfc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nbfc.bean.ASFGenerationDetailBean;
import com.nbfc.bean.MLIDEtailsBean;
import com.nbfc.bean.NBFCASFUpdationBean;
import com.nbfc.bean.NbfcAppropriationBean;
import com.nbfc.helper.FileExportHelper;
import com.nbfc.service.ASFGenerationBulkUploadService;
import com.nbfc.service.NBFCASFUpdationService;
import com.nbfc.service.UserActivityService;
import com.nbfc.validator.DataValidation;
import com.nbfc.validator.UserValidator;

@Controller
public class ASFGenerationBulkUploadCheckerController extends HttpServlet {

	@Autowired
	NBFCASFUpdationService nbfcASFUpdationService;
	@Autowired
	UserActivityService userActivityService;
	@Value("${downloadFileDirPath}")
	private String downloadFileDirPath;
	@Value("${UpdatedOutstandingAmtDetailsFile}")
	private String downloadFileName;
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	ASFGenerationBulkUploadService ASFGenerationService;
	public static final int BUFFER_SIZE = 4096;
	String userId;

	@RequestMapping(value = "/asfDetailsChecker", method = RequestMethod.GET)
	public ModelAndView asfDetailsChecker(
			@ModelAttribute("command") NBFCASFUpdationBean  nbfcASFUpdationBean,
			BindingResult result, Model modelMsg, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		userId = (String) session.getAttribute("userId");
        List<NBFCASFUpdationBean> list = nbfcASFUpdationService.getASFDetailsByPortfolio(userId);
		if (!list.isEmpty()) {
			model.put("danDetailList", list);
		} else {
			model.put("noDataFound", "NO Data Found !!");
		}
		model.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("NBFCMAKER", "Registration"));
		model.put("applicationList", userActivityService.getActivity(
				"NBFCMAKER", "Application_Processing"));
		model.put("RPaymentList", userActivityService.getActivity("NBFCMAKER",
				"Receipt_Payments"));
		model.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));
		// model1.put("actNameHome",
		// userActivityService.getActivityName("NBFCMAKER",
		// "cgpanDetailReport"));// Added by Say 31 Jan19
		model.put("CList",
				userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));
		model.put("CBMFList",userActivityService.getReport("NBFCMAKER", "Claim_Bank_Mandate"));
		model.put("homePage", "nbfcMakerHome");

		return new ModelAndView("ASFBulkUploadCheckerDetails", model);
	}
	
	@RequestMapping(value = "/submitForApprove", method = RequestMethod.GET)
	public ModelAndView submitForApprove(
			@ModelAttribute("command") NBFCASFUpdationBean  nbfcASFUpdationBean,
			BindingResult result, Model modelMsg, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		userId = (String) session.getAttribute("userId");
		List<String> checkedData= nbfcASFUpdationBean.getChcktbl();
	    System.out.println("chkbox----------"+checkedData);
	//String approvedCount=request.getParameter("approveCount");
	
	  int flag=nbfcASFUpdationService.approveASFData(checkedData,userId);
	//if(flag>0){
		model.put("message", "Succefully Approved records ");
	//}
		model.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("NBFCMAKER", "Registration"));
		model.put("applicationList", userActivityService.getActivity(
				"NBFCMAKER", "Application_Processing"));
		model.put("RPaymentList", userActivityService.getActivity("NBFCMAKER",
				"Receipt_Payments"));
		model.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));
		model.put("CList",
				userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));
		model.put("CBMFList",userActivityService.getReport("NBFCMAKER", "Claim_Bank_Mandate"));
		// model1.put("actNameHome",
		// userActivityService.getActivityName("NBFCMAKER",
		// "cgpanDetailReport"));// Added by Say 31 Jan19
		model.put("homePage", "nbfcMakerHome");

		return new ModelAndView("ASFBulkUploadCheckerDetails", model);
	}
	
	
	

	// For DownLoadExcel updated outstanding File

			@RequestMapping(value = "/downloadOutstandingUpdateExcel", method = RequestMethod.GET)
			public ModelAndView ModifyOutstandingAmountDownLoad(
					@ModelAttribute("command") ASFGenerationDetailBean bean, BindingResult result,
					HttpServletRequest request, HttpServletResponse response,
					HttpSession session, String fileid) throws IOException {
				try {

					String filePath = downloadFileDirPath + File.separator
							+ downloadFileName;

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
					
					String userId = (String) session.getAttribute("userId");
					//model.put("expoList",prepareExpotureLimitListofBean(cgtmscCreateExposureLimitMakerService.getAllExposureLimitDetails()));
					List<ASFGenerationDetailBean> list = nbfcASFUpdationService.getAllUpdatedOSAmtDetails(userId,fileid);
					
//					CGTMSEExposureMasterMLIName	listobj1=(CGTMSEExposureMasterMLIName)list;
//					CGTMSEExposureMasterMLIName	listobj2=(CGTMSEExposureMasterMLIName)list;
					// Writing and Downlaoding Excel File

					XSSFWorkbook hwb = new XSSFWorkbook();
					XSSFSheet sheet = hwb.createSheet("ExposureDetailsdownloadFileName");

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
					sheet.createFreezePane(0, 1); // Freeze 1st Row
													// sheet.createFreezePane(int
													// colSplit, int rowSplit, int
													// leftmostColumn, int topRow)

					// Creating First rows for excel heading
					XSSFRow rowhead = sheet.createRow((short) 0);
					
					XSSFCell cell0 = rowhead.createCell(0);
					cell0.setCellStyle(style);
					cell0.setCellValue("FILE_ID");// Done 5
					
					
					XSSFCell cell1 = rowhead.createCell(1);
					cell1.setCellStyle(style);
					cell1.setCellValue("CGPAN");// Done 1
					
				
					
					XSSFCell cell2 = rowhead.createCell(2);
					cell2.setCellStyle(style);
					cell2.setCellValue("LOAN_ACCOUNT_NO");// Done 3
					
					XSSFCell cell3 = rowhead.createCell(3);
					cell3.setCellStyle(style);
					cell3.setCellValue("ITPAN");// Done 4

					XSSFCell cell4 = rowhead.createCell(4);
					cell4.setCellStyle(style);
					cell4.setCellValue("BORROWER_NAME");// Done 4

					XSSFCell cell5 = rowhead.createCell(5);
					cell5.setCellStyle(style);
					cell5.setCellValue("GUARANTEE_AMOUNT");// Done 5
					
					XSSFCell cell6 = rowhead.createCell(6);
					cell6.setCellStyle(style);
					cell6.setCellValue("OUTSTANDING_AMOUNT");// Done 5
					
					
					XSSFCell cell7 = rowhead.createCell(7);
					cell7.setCellStyle(style);
					cell7.setCellValue("OUTSTANDING_DATE");
							
					
					
					
					int index = 1;
					int sno = 0;
					String SanctionDate="";
					String StartDate="";
					String EndDate="";
					if (list != null && !list.isEmpty()) {					
						Iterator<ASFGenerationDetailBean> itr2 = list.iterator();
						while (itr2.hasNext()) {
							ASFGenerationDetailBean obj1 = (ASFGenerationDetailBean) itr2.next();
							sno++;
							XSSFRow row = sheet.createRow((short) index);
							
							row.createCell((short) 0).setCellValue(fileid);// Done
						
							row.createCell((short) 1).setCellValue(obj1.getCGPAN());// Done
																					// 1
							row.createCell((short) 2).setCellValue(obj1.getLoadAccountNo());// Done
																					// 2
							
							row.createCell((short) 4).setCellValue(obj1.getMse_name());// Done
							
							row.createCell((short) 3).setCellValue(obj1.getItpan());// Done
																					// 3
							row.createCell((short) 5).setCellValue(obj1.getPrevoutstandingAmt());// Done
								
							row.createCell((short) 6).setCellValue(obj1.getOutstanding_amount());// Done
							
							row.createCell((short) 7).setCellValue(obj1.getOutstanding_date());// Done
							
						
							

							index++;
					}
					}
					FileOutputStream fileOut = new FileOutputStream(filePath);
					hwb.write(fileOut);
					fileOut.close();

					ModelAndView model = new ModelAndView("ASFBulkUploadDetails");

					model.addObject(
							"excelFileDownLoadSuccessfully",
							"File DownLoaded Successfully in this location F:/ExcelReports/nbfcExposuredetails.xls.");

					File downloadFile = new File(filePath);
				//	log.info("downloadFile =" + downloadFile);
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
				//	log.info("Exception == " + e);
					System.out.println("Exception == " + e);
				}
				return null;
			}
	
}
