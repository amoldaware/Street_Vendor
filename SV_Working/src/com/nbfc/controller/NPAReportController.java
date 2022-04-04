package com.nbfc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.ApplicationStatusDetailsBean;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.bean.StateBean;
import com.nbfc.model.MLIName;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.State;
import com.nbfc.model.User;
import com.nbfc.service.CGTMSECreateExposureLimitMakerService;
import com.nbfc.service.NPAService;
import com.nbfc.service.StateService;
import com.nbfc.service.UserActivityService;
import com.nbfc.validation.EmployeeValidator;

/**
 * @author ajeet
 *
 */
@Controller
public class NPAReportController {
	
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	NPAService npaService;
	@Autowired
	EmployeeValidator validator;
	String memberId=null;
	String userId = null;
	@Autowired
	StateService stateService;
	@Value("${downloadFileDirPath}")
	private String downloadFileDirPath;
	@Value("${NPAReportdownloadFileName}")
	private String downloadFileName;
	MLIName mem_id=null;
	public static final int BUFFER_SIZE = 4096;
	@Autowired
	private CGTMSECreateExposureLimitMakerService cgtmseExposureMasterMakerService;
	@Autowired

	static Logger log = Logger.getLogger(NBFCController.class.getName());
	
	//this method call for NPA Report Detail Page
	@RequestMapping(value = "/NPAReport", method = RequestMethod.GET)
	public ModelAndView cgpanDetailReport(
			@ModelAttribute("command") NPADetailsBean bean,
			BindingResult result, HttpSession session, Model model)
			throws JRException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		String Role = (String) session.getAttribute("uRole");
		 userId = (String) session.getAttribute("userId");
		String loginUserMemId = npaService.getMemberId(userId);
		// nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);
		if (Role.equals("CMAKER")) {
			model1.put("guaranteelist", userActivityService.getActivity(
					"CGTMSEMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"CGTMSEMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"CGTMSEMAKER", "Receipt_Payments"));
			model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
			model1.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			model1.put("stateList",
					prepareStateListofBean(stateService.listStates("CCA")));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSEMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("MLIID", loginUserMemId);
			model1.put("homePage", "cgtmseMakerHome");

		} else if (Role.equals("CCHECKER")) {
			model1.put("adminlist", userActivityService.getActivity(
					"CGTMSECHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"CGTMSECHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"CGTMSECHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"CGTMSECHECKER", "Receipt_Payments"));
			model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSECHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("MLIID", loginUserMemId);
			model1.put("homePage", "cgtmseCheckerHome");
			// return null;
		} else if (Role.equals("NMAKER")) {
			// added by say 6 feb-----------------------
			model1.put("adminlist", userActivityService.getActivity(
					"NBFCMAKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"NBFCMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"NBFCMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"NBFCMAKER", "Receipt_Payments"));
			model1.put("repList",
					userActivityService.getReport("NBFCMAKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			model1.put("MLIID", loginUserMemId);
			model1.put("homePage", "nbfcMakerHome");

		} else if (Role.equals("NCHECKER")) {
			model1.put("adminlist", userActivityService.getActivity(
					"NBFCCHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"NBFCCHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"NBFCCHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"NBFCCHECKER", "Receipt_Payments"));
			model1.put("repList",
					userActivityService.getReport("NBFCCHECKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
			model1.put("MLIID", loginUserMemId);
			model1.put("homePage", "nbfcCheckerHome");

		}
		return new ModelAndView("npaReportInputForm", model1);
		// return null;
	}

	private List<NPADetailsBean> prepareStateListofBean(List<State> employees) {
		// TODO Auto-generated method stub
		List<NPADetailsBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<NPADetailsBean>();
			NPADetailsBean bean = null;
			for (State employee : employees) {
				bean = new NPADetailsBean();
				bean.setSte_code(employee.getSte_code());
				bean.setSte_name(employee.getSte_name());
				beans.add(bean);
			}
		}
		return beans;
	}

	//this method call for NPA Report Detail 
	@RequestMapping(value = "/npaReportDetailList", method = RequestMethod.POST)
	public ModelAndView searchAppStatus(
			@ModelAttribute("command") NPADetailsBean bean,
			BindingResult result, HttpSession session, Model model)
			throws JRException, ParseException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		List<NPADetailsBean> NPADetailList=null;
		
		session.setAttribute("NPADetailList",null);
		session.removeAttribute("NPADetailList");
		
		String Role = (String) session.getAttribute("uRole");
		
		String userId = (String) session.getAttribute("userId");
		memberId = npaService.getMemberId(userId);
		
			String mliLongName=bean.getMliLongName();
		
		System.out.println("mliLongName==194==="+mliLongName);
		
		validator.npaReportDetailsValidate(bean, result);
		if (result.hasErrors()) {
			if (Role.equals("CMAKER")) {
				// userId = "ADMIN";
				model1.put("guaranteelist", userActivityService.getActivity(
						"CGTMSEMAKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity(
						"CGTMSEMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity(
						"CGTMSEMAKER", "Receipt_Payments"));
				model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
				model1.put("repList", userActivityService.getReport(
						"CGTMSEMAKER", "User_Report"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("CGTMSEMAKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "cgtmseMakerHome");

			} else if (Role.equals("CCHECKER")) {
				// userId = "ADMIN";
				model1.put("adminlist", userActivityService.getActivity(
						"CGTMSECHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity(
						"CGTMSECHECKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity(
						"CGTMSECHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity(
						"CGTMSECHECKER", "Receipt_Payments"));
				model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
				model1.put("repList", userActivityService.getReport(
						"CGTMSECHECKER", "User_Report"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("CGTMSECHECKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "cgtmseCheckerHome");
				// return null;
			} else if (Role.equals("NMAKER")) {
				// added by say 6 feb-----------------------
				// userId = (String) session.getAttribute("userId");
				model1.put("adminlist", userActivityService.getActivity(
						"NBFCMAKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity(
						"NBFCMAKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity(
						"NBFCMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity(
						"NBFCMAKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport(
						"NBFCMAKER", "User_Report"));
				model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
				model1.put("homePage", "nbfcMakerHome");

			} else if (Role.equals("NCHECKER")) {
				// userId = (String) session.getAttribute("userId");
				model1.put("adminlist", userActivityService.getActivity(
						"NBFCCHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity(
						"NBFCCHECKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity(
						"NBFCCHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity(
						"NBFCCHECKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport(
						"NBFCCHECKER", "User_Report"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("NBFCCHECKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "nbfcCheckerHome");

			}
			return new ModelAndView("npaReportInputForm", model1);
		}

		if (Role.equals("CMAKER")) {
			userId = "ADMIN";
			model1.put("guaranteelist", userActivityService.getActivity(
					"CGTMSEMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"CGTMSEMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"CGTMSEMAKER", "Receipt_Payments"));

			model1.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
			model1.put("homePage", "cgtmseMakerHome");

		} else if (Role.equals("CCHECKER")) {
			userId = "ADMIN";
			model1.put("adminlist", userActivityService.getActivity(
					"CGTMSECHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"CGTMSECHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"CGTMSECHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"CGTMSECHECKER", "Receipt_Payments"));

			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("homePage", "cgtmseCheckerHome");
			// return null;
		} else if (Role.equals("NMAKER")) {
			// added by say 6 feb-----------------------
			userId = (String) session.getAttribute("userId");
			model1.put("adminlist", userActivityService.getActivity(
					"NBFCMAKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"NBFCMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"NBFCMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"NBFCMAKER", "Receipt_Payments"));
			model1.put("repList",
					userActivityService.getReport("NBFCMAKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			model1.put("homePage", "nbfcMakerHome");

		} else if (Role.equals("NCHECKER")) {
			userId = (String) session.getAttribute("userId");
			model1.put("adminlist", userActivityService.getActivity(
					"NBFCCHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"NBFCCHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"NBFCCHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"NBFCCHECKER", "Receipt_Payments"));
			model1.put("repList",
					userActivityService.getReport("NBFCCHECKER", "User_Report"));
			// model1.put("actNameHome",
			model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
			model1.put("homePage", "nbfcCheckerHome");

		}
		
		/*String toDateF = bean.getToDate();
		String fromDateF = bean.getFromDate();
		session.setAttribute("FDate", fromDateF);
		session.setAttribute("TDate", toDateF);*/
		

		Date toDate = new SimpleDateFormat("dd/MM/yyyy")
				.parse(bean.getToDate());
		Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(bean
				.getFromDate());
		if(Role.equals("CCHECKER") || Role.equals("CMAKER")){
			//String mliname=bean.getMliName();
			//mem_id = userActivityService.getBankID(mliname);
		//	String Mem_Id= mem_id.getBnkId() + mem_id.getZneID()
			//		+ mem_id.getBrnName();
			mem_id = userActivityService.getBankID(mliLongName);
			//mem_id = userActivityService.getBankID(mliLongName);
			//mem_id = userActivityService.getBankID(mliname);
			String Mem_Id = mem_id.getBnkId() + mem_id.getZneID() + mem_id.getBrnName();
			NPADetailList=npaService.getNPAReportDetail(userId, toDate, fromDate, Mem_Id,Role);
		}else{
			
			NPADetailList=npaService.getNPAReportDetail(userId, toDate, fromDate, memberId,Role);
		}
	
      
		
		
		// nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);
		if (!NPADetailList.isEmpty()) {
			model1.put("NPADetailList", NPADetailList);
			session.setAttribute("NPADetailList", NPADetailList);
		} else {
			model1.put("message", "NO Data Found !!");
		}
		return new ModelAndView("npaReportListDetails", model1);
	}

	//Get the MLI Long Name in Drop Down on Page on Load
	@ModelAttribute("mliLongName")
	public Map<String, String> getMlilongName() {
		Map<String, String> mapMliLongNameObj = new HashMap<String, String>();
		mapMliLongNameObj = cgtmseExposureMasterMakerService.getMliLongNameInDropDown();
		return   mapMliLongNameObj;
	}
	// For DownLoadExcel File Added by ajeet

		@RequestMapping(value = "/NPAReportDetailDownload", method = RequestMethod.GET)
		public ModelAndView npaReportdownLoad(
				@ModelAttribute("command") NPADetailsBean bean, BindingResult result,
				HttpServletRequest request, HttpServletResponse response,
				HttpSession session) throws IOException {
			try {

				String filePath = downloadFileDirPath + File.separator
						+ downloadFileName;
				System.out.println(filePath);
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
				List<NPADetailsBean> list = (List<NPADetailsBean>) session.getAttribute("NPADetailList");
				//List<MLIRegistration> list = mliDetailsService.getMLIAllDetails();

				// List<UserPerivilegeDetails>
				// list=employeeService.getUserPrivlageDetails();
				log.info("list size==" + list.size());
				log.info("list Data==" + list);

				// Writing and Downlaoding Excel File

				XSSFWorkbook hwb = new XSSFWorkbook();
				XSSFSheet sheet = hwb.createSheet("NPAReportDownLoadedFile");

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
				cell0.setCellValue("Bank Name");// Done 1

				XSSFCell cell1 = rowhead.createCell(1);
				cell1.setCellStyle(style);
				cell1.setCellValue("Member ID");// Done 3

				XSSFCell cell2 = rowhead.createCell(2);
				cell2.setCellStyle(style);
				cell2.setCellValue("Unit Name");// Done 4

				XSSFCell cell3 = rowhead.createCell(3);
				cell3.setCellStyle(style);
				cell3.setCellValue("Loan Account Number");// Done 5

				XSSFCell cell4 = rowhead.createCell(4);
				cell4.setCellStyle(style);
				cell4.setCellValue("CGPAN Number");// Done 6

				XSSFCell cell5 = rowhead.createCell(5);
				cell5.setCellStyle(style);
				cell5.setCellValue("Status");// Done 7

				XSSFCell cell6 = rowhead.createCell(6);
				cell6.setCellStyle(style);
				cell6.setCellValue("Expiry Date");// Done 7
				
				XSSFCell cell7 = rowhead.createCell(7);
				cell7.setCellStyle(style);
				cell7.setCellValue("Date On which Account was Classfied As NPA");// Done 7
				
				XSSFCell cell8 = rowhead.createCell(8);
				cell8.setCellStyle(style);
				cell8.setCellValue("Reasons For Account Turning NPA");// 
				
				XSSFCell cell9 = rowhead.createCell(9);
				cell9.setCellStyle(style);
				cell9.setCellValue("Total Guaranteed Amount");// 
				
				XSSFCell cell10 = rowhead.createCell(10);
				cell10.setCellStyle(style);
				cell10.setCellValue("Latest Outstanding Amount");// 
				
				XSSFCell cell11 = rowhead.createCell(11);
				cell11.setCellStyle(style);
				cell11.setCellValue("Total Outstanding Amount As on Date of NPA");// 
			
				int index = 1;
				int sno = 0;
				Iterator<NPADetailsBean> itr2 = list.iterator();
				while (itr2.hasNext()) {
					NPADetailsBean obj1 = (NPADetailsBean) itr2.next();
					sno++;
					XSSFRow row = sheet.createRow((short) index);
					//

					row.createCell((short) 0).setCellValue(obj1.getBankName());// Done
																				// 1
					row.createCell((short) 1).setCellValue(obj1.getMLIID());// Done
																					// 2
					row.createCell((short) 2).setCellValue(obj1.getBorrowerName());// Done
					
					row.createCell((short) 3).setCellValue(obj1.getLoanAccountNo());// Done
																					// 3
					row.createCell((short) 4).setCellValue(obj1.getCGPAN());// Done
					
					row.createCell((short) 5).setCellValue(obj1.getStatus());// Done
																				// 4
					row.createCell((short) 6).setCellValue(obj1.getExpiryDate());// Done
																					// 5
					row.createCell((short) 7).setCellValue(obj1.getNpaDt());// Done
					
					row.createCell((short) 8).setCellValue(obj1.getNpaReason());// Done

					row.createCell((short) 9).setCellValue(obj1.getTotalGuaranteeAmt());// Done

					row.createCell((short) 10).setCellValue(obj1.getLatestOsAmt());// Done
					
					row.createCell((short) 11).setCellValue(obj1.getClaimEligibityAmt());// Done
					
					index++;
				}
				FileOutputStream fileOut = new FileOutputStream(filePath);
				hwb.write(fileOut);
				fileOut.close();

				ModelAndView model = new ModelAndView("newRolePage");

				model.addObject(
						"excelFileDownLoadSuccessfully",
						"File DownLoaded Successfully in this location F:/ExcelReports/nbfcNPAReportExcelFile.xls.");

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

}
