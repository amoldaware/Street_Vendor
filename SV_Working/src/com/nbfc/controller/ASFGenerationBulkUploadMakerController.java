package com.nbfc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import com.nbfc.bean.ApplicationStatusDetailsBean;
import com.nbfc.bean.MLIMakerInterfaceUploadedBean;
import com.nbfc.bean.NBFCASFUpdationBean;
import com.nbfc.exception.NBFCException;
import com.nbfc.helper.BulkUpload0;
import com.nbfc.helper.FileExportHelper;
import com.nbfc.helper.TableDetailBean;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETExposureDetails;
import com.nbfc.model.CGTMSEExposureMasterMLIName;
import com.nbfc.model.MLIName;
import com.nbfc.model.User;
import com.nbfc.model.UserInfo;
import com.nbfc.service.ASFGenerationBulkUploadService;
import com.nbfc.service.UserActivityService;

@Controller
public class ASFGenerationBulkUploadMakerController {
	@Autowired
	UserActivityService userActivityService;
	@Value("${downloadFileDirPath}")
	private String downloadFileDirPath;
	@Value("${ModifyOutstandingAmtDetailsFile}")
	private String downloadFileName;
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	ASFGenerationBulkUploadService ASFGenerationService;
	public static final int BUFFER_SIZE = 4096;
	static Logger log = Logger.getLogger(NBFCController.class.getName());
	@RequestMapping(value = "/asfDetails", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView asfDetails(@ModelAttribute("command") ASFGenerationDetailBean bean,BindingResult result,HttpSession session,Model model) throws JRException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		String Role=(String) session.getAttribute("uRole");
		String userId = (String) session.getAttribute("userId");
		  List<ASFGenerationDetailBean> list = ASFGenerationService.getASFDetailsByFileWise(userId);
			if (!list.isEmpty()) {
				model1.put("danDetailList", list);
			} else {
				model1.put("noDataFound", "NO Data Found !!");
			}
			model1.put("adminlist", userActivityService.getActivity(
					"NBFCMAKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"NBFCMAKER", "Registration"));
			model1.put("applicationList", userActivityService
					.getActivity("NBFCMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"NBFCMAKER", "Receipt_Payments"));
			model1.put("repList", userActivityService.getReport(
					"NBFCMAKER", "User_Report"));
			model1.put("GMaintainlist", userActivityService.getActivity(
					"NBFCMAKER", "Guarantee_Maintenance"));
			model1.put("CList",
					userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));
			model1.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
			//model1.put("message", "Batch File Should be .xls");
//			model1.put("actNameHome", userActivityService.getActivityName("NBFCMAKER",
//					"cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcMakerHome");
		return new ModelAndView("ASFBulkUploadDetails",model1);
		//return null;
	}
	//
	@RequestMapping(value = "/asfDetailsError", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView asfDetailsError(@ModelAttribute("command") ASFGenerationDetailBean bean,BindingResult result,HttpSession session,Model model) throws JRException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		String Role=(String) session.getAttribute("uRole");
		String userId = (String) session.getAttribute("userId");
		  List<ASFGenerationDetailBean> list = ASFGenerationService.getASFDetailsByFileWise(userId);
			if (!list.isEmpty()) {
				model1.put("danDetailList", list);
			} else {
				model1.put("noDataFound", "NO Data Found !!");
			}
			model1.put("adminlist", userActivityService.getActivity(
					"NBFCMAKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"NBFCMAKER", "Registration"));
			model1.put("applicationList", userActivityService
					.getActivity("NBFCMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"NBFCMAKER", "Receipt_Payments"));
			model1.put("repList", userActivityService.getReport(
					"NBFCMAKER", "User_Report"));
			model1.put("GMaintainlist", userActivityService.getActivity(
					"NBFCMAKER", "Guarantee_Maintenance"));
			model1.put("message", "Batch File Should be .xls  only");
			model1.put("CList",
					userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));
			model1.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//			model1.put("actNameHome", userActivityService.getActivityName("NBFCMAKER",
//					"cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcMakerHome");
		return new ModelAndView("ASFBulkUploadDetails",model1);
		//return null;
	}
	@RequestMapping(value = "/ASFfileUploaded", method = RequestMethod.POST)
	public ModelAndView ASFfileUploaded(
			@ModelAttribute("command") ASFGenerationDetailBean excelFile,
			BindingResult result, ModelMap modelMap, Model modFr,
			HttpServletRequest request, Model modelMsg, HttpSession session,String fileId) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String fileName = excelFile.getFile().getOriginalFilename();
			if (!fileName.endsWith(".xls")) {
				
				return new ModelAndView("redirect:/asfDetailsError.html");
				
				
			}
			
		UserInfo user=(UserInfo) session.getAttribute("userInfo");
		Session connSess = sessionFactory.openSession();
		Transaction tn = connSess.beginTransaction();
		Connection conn = connSess.connection();
		System.out.println("conn :"+conn);
		//######################
		
		BulkUpload0 BlUpObj = new BulkUpload0();
		String TableName="NBFC_OUTSTANDING_AMT_STAG";		
		String BulkName="OSAmt";
		LinkedHashMap<String, TableDetailBean> headerMap;
		headerMap = BlUpObj.getTableHeaderDataOSAmt(conn,TableName,BulkName);
		Map<String, ArrayList<String>> UploadedStatus = new HashMap<String, ArrayList<String>>();
		UploadedStatus=BlUpObj.CheckExcelData(excelFile.getFile(),headerMap,TableName,conn,user,BulkName);
		
		if(UploadedStatus!=null){	
			
			ArrayList SuccessDataList=(ArrayList)UploadedStatus.get("successRecord");
			ArrayList UnSuccessDataList=(ArrayList)UploadedStatus.get("unsuccessRecord");
			ArrayList allerrors=(ArrayList)UploadedStatus.get("allerror");/**/
			if(SuccessDataList!=null){
			model.put("msg", "DAN Generated for Successfull Records");	
			}
			System.out.println("allerrors:"+allerrors);
			System.out.println("UnSuccessDataList:"+UnSuccessDataList);			
			HttpSession sess = request.getSession(false);
			request.setAttribute("UploadedStatus",UploadedStatus);
			sess.setAttribute("SuccessDataList", SuccessDataList);
			sess.setAttribute("UnSuccessDataList", UnSuccessDataList); 
			sess.setAttribute("Allerrors", allerrors);
		}
	//	request.setAttribute("UploadedStatus", UploadedStatus);
		//System.out.println("Conn:"+BlUpObj.getConnection());/**/
		//#################
		
		
		model.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		model.put("guaranteelist", userActivityService.getActivity("NBFCMAKER",
				"Guarantee_Maintenance"));
		model.put("applicationList", userActivityService.getActivity(
				"NBFCMAKER", "Application_Processing"));
		model.put("RPaymentList", userActivityService.getActivity("NBFCMAKER",
				"Receipt_Payments"));
		model.put("actName",
				userActivityService.getActivityName("NBFCMAKER", "mliMaker"));// Added
		model.put("CList",
				userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));																	// by
																				// say
		model.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));			
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));// 25june19
		model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
		model.put("homePage", "nbfcMakerHome");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ModelAndView("ASFBulkUploadSuccess", model);

	}
	// For DownLoadExcel File

		@RequestMapping(value = "/exportModifyOutstandingAmountDetails", method = RequestMethod.GET)
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
				List<ASFGenerationDetailBean> list = ASFGenerationService.getAllASFDetails(userId,fileid);
				
//				CGTMSEExposureMasterMLIName	listobj1=(CGTMSEExposureMasterMLIName)list;
//				CGTMSEExposureMasterMLIName	listobj2=(CGTMSEExposureMasterMLIName)list;
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
						
						
						
						row.createCell((short) 3).setCellValue(obj1.getItpan());// Done
						
						row.createCell((short) 4).setCellValue(obj1.getMse_name());// Done
																				// 3
						row.createCell((short) 5).setCellValue(obj1.getPrevoutstandingAmt());// Done
																					// 4
						

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
//For Download succesfull and unsuccesfull records file---------------------------------------------------
		@RequestMapping(value = "exportToFileASF", method = RequestMethod.GET)
		public ModelAndView exportToFileASF(
				@ModelAttribute("command") ASFGenerationDetailBean excelFile,
				BindingResult result, ModelMap modelMap, Model modFr,
				HttpServletRequest request,HttpServletResponse response, Model modelMsg, HttpSession session) throws IOException {
			//System.out.println("exportToFile.............START");
			
			//OutputStream os = response.getOutputStream();
			Map<String, Object> model = new HashMap<String, Object>();
	    	Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
			String strDate = sdf.format(cal.getTime());		
	    	//System.out.println("ExportToFile Calling..");
			
	    	//String contextPath1 = request.getSession(false).getServletContext().getRealPath("");
	    	//String contextPath1 = request.getSession(false).getServletContext().getRealPath("");
	    	String contextPath1 = "D://NBFCDownload";
	    	
	    	
	    	
	    	System.out.println("exportToFile@contextPath1 :"+contextPath1);
	        String contextPath = contextPath1.replace('\\','/');
	        System.out.println("exportToFile@contextPath1 :"+contextPath1);
	        //System.out.println("contextPath1 :"+contextPath1);
	        //System.out.println("contextPath :"+contextPath);
	    	HttpSession sess = request.getSession(false);		
	    	String fileType = request.getParameter("fileType");
	    	String FlowLevel = request.getParameter("FlowLevel");    	
	    	//System.out.println("@@@@@@@@@@@@FlowLevel :"+FlowLevel);		
			//ArrayList ClmDataList = (ArrayList)sess.getAttribute("ClaimSettledDatalist");
			
			ArrayList ClmDataList = (ArrayList)sess.getAttribute(FlowLevel);
			
			//System.out.println("@@@@@@@@@@@@ClmDataList:"+ClmDataList);
			ArrayList HeaderArrLst = (ArrayList)ClmDataList.get(0);
			//System.out.println("@@@@@@@@@@@@HeaderArrLst:"+HeaderArrLst);
			int NoColumn = HeaderArrLst.size();		
			//System.out.println("fileType:"+fileType);
			
			
			
			if(fileType.equals("XLSType")){
				System.out.println("####################inside if#####################");
				FileExportHelper fileExportHelper = new FileExportHelper();
				byte[] b = fileExportHelper.generateEXL(ClmDataList,NoColumn,contextPath);			
				if (response != null)
				    response.setContentType("APPLICATION/OCTET-STREAM");
				    response.setHeader("Content-Disposition","attachment; filename=ExcelData"+strDate+".xls");
				    OutputStream os = response.getOutputStream();
		            os.write(b);
		            //os.flush();			
		            os.close();
			}		 /**/
			//System.out.println("exportToFile.............END");
			return null;
	    }

}
