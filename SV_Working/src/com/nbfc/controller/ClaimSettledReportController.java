package com.nbfc.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.nbfc.bean.ApplicationStatusDetailsBean;
import com.nbfc.bean.ClaimLodgementBean;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.bean.StateBean;
import com.nbfc.helper.PropertyLoader;
import com.nbfc.model.MLIName;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.State;
import com.nbfc.model.User;
import com.nbfc.service.CGTMSECreateExposureLimitMakerService;
import com.nbfc.service.ClaimLodgementService;
import com.nbfc.service.NPAService;
import com.nbfc.service.StateService;
import com.nbfc.service.UserActivityService;
import com.nbfc.validation.EmployeeValidator;


@Controller
public class ClaimSettledReportController {
	
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	ClaimLodgementService claimLodgementService;
	@Autowired
	NPAService npaService;
	@Autowired
	StateService stateService;
	@Autowired
	EmployeeValidator validator;
	@Value("${downloadFileDirPath}")
	private String downloadFileDirPath;
	@Value("${ClaimSettledReportFile}")
	private String downloadFileName;
	@Autowired
	private CGTMSECreateExposureLimitMakerService cgtmseExposureMasterMakerService;
	public static final int BUFFER_SIZE = 4096;
	String memberId=null;
	String userId = null;
	MLIName mem_id=null;
	@Autowired
	static Logger log = Logger.getLogger(NBFCController.class.getName());
	
	@RequestMapping(value = "/ClaimSettledReport", method = RequestMethod.GET)
	public ModelAndView ClaimSettledReport(
			@ModelAttribute("command") ClaimLodgementBean bean,
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
			
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSEMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("memberId", loginUserMemId);
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
			model1.put("memberId", loginUserMemId);
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
			model1.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
			model1.put("memberId", loginUserMemId);
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
			model1.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
			model1.put("memberId", loginUserMemId);
			model1.put("homePage", "nbfcCheckerHome");

		}
		return new ModelAndView("ClaimSettledReportInput", model1);
		// return null;
	}
	
	@RequestMapping(value = "/ClaimSettledReportData", method = RequestMethod.POST)
	public ModelAndView claimSettledReport(
			@ModelAttribute("command") ClaimLodgementBean bean,
			BindingResult result, HttpSession session, Model model)
			throws JRException, ParseException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		List<ClaimLodgementBean> ClaimSettledDataReport=null;
		
		session.setAttribute("ClaimSettledDataReport",null);
		session.removeAttribute("ClaimSettledDataReport");
		
		//String mliLongName=(String) session.getAttribute("mliLongName");
		
		String Role = (String) session.getAttribute("uRole");
		
		String userId = (String) session.getAttribute("userId");
		memberId = npaService.getMemberId(userId);
		
		String mliLongName=bean.getMliLongName();
		
		System.out.println("mliLongName=="+mliLongName);
		
		validator.ClaimSettledReportValidate(bean, result);
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
				model1.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
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
				model1.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
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
				model1.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("NBFCCHECKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "nbfcCheckerHome");

			}
			return new ModelAndView("ClaimSettledReportInput", model1);
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
			model1.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
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
			model1.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
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
			model1.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
			model1.put("homePage", "nbfcCheckerHome");

		}
		
		/*String toDateF = bean.getToDate();
		String fromDateF = bean.getFromDate();
		session.setAttribute("FDate", fromDateF);
		session.setAttribute("TDate", toDateF);*/
		

		Date toDate = new SimpleDateFormat("dd/MM/yyyy").parse(bean.getToDate());
		Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(bean.getFromDate());
		if(Role.equals("CCHECKER") || Role.equals("CMAKER")){
			List<Map<String, Object>> ClaimSettledDataReport1 = new ArrayList<Map<String, Object>>();
			if(mliLongName.equals("NONE"))
			{
				ClaimSettledDataReport1=claimLodgementService.getClaimSettledReportAll(userId, toDate, fromDate,Role);
				if (!ClaimSettledDataReport1.isEmpty()) {
					model1.put("ClaimSettledDataReport1", ClaimSettledDataReport1);
					session.setAttribute("ClaimSettledDataReport1", ClaimSettledDataReport1);
				} else {
					model1.put("message", "NO Data Found !!");
				}
				return new ModelAndView("ClaimSettledReportDataAll", model1);
			}
			else
			{
			mem_id = userActivityService.getBankID(mliLongName);
			String	Mem_Id= mem_id.getBnkId() + mem_id.getZneID()
				+ mem_id.getBrnName();
			/*if(Mem_Id.equals(null)||Mem_Id.equals(""))
					{
			ClaimSettledDataReport=claimLodgementService.getClaimSettledReport(userId, toDate, fromDate, Mem_Id,mliLongName,Role);
					}*/
			//String mliname=bean.getMliName();
			//System.out.println("mliname=="+mliname);
			
			ClaimSettledDataReport=claimLodgementService.getClaimSettledReport(userId, toDate, fromDate, Mem_Id,mliLongName,Role);
			}
			
			/*
			 * //String mliname=bean.getMliName();
			 * //System.out.println("mliname=="+mliname); mem_id =
			 * userActivityService.getBankID(mliLongName); String Mem_Id= mem_id.getBnkId()
			 * + mem_id.getZneID() + mem_id.getBrnName();
			 * ClaimSettledDataReport=claimLodgementService.getClaimSettledReport(userId,
			 * toDate, fromDate, Mem_Id,mliLongName,Role);
			 */
		}else{
			
			ClaimSettledDataReport=claimLodgementService.getClaimSettledReport(userId, toDate, fromDate, memberId,mliLongName,Role);
		}
	
		// nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);
		if (!ClaimSettledDataReport.isEmpty()) {
			model1.put("ClaimSettledDataReport", ClaimSettledDataReport);
			session.setAttribute("ClaimSettledDataReport", ClaimSettledDataReport);
		} else {
			model1.put("message", "NO Data Found !!");
		}
		return new ModelAndView("ClaimSettledReportData", model1);
	}
	
	@RequestMapping(value = "/ClaimSettledReportDownloadAll", method = RequestMethod.GET)
	public ModelAndView ClaimSettledReportDownloadAll(@ModelAttribute("command") ClaimLodgementBean bean,
			BindingResult result, HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {

		try {

			OutputStream os = response.getOutputStream();

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
			String strDate = sdf.format(cal.getTime());

			// System.out.println("ExportToFile Calling..");

			String contextPath1 = request.getSession(false).getServletContext().getRealPath("");
			String contextPath = PropertyLoader.changeToOSpath(contextPath1);

			System.out.println("contextPath1 :" + contextPath1);
			System.out.println("contextPath :" + contextPath);

			// HttpSession sess = request.getSession(false);
			// String fileType = request.getParameter("fileType");
			// String FlowLevel = request.getParameter("FlowLevel");

			// System.out.println("@@@@@@@@@@@@FlowLevel :" + FlowLevel);
			// ArrayList ClmDataList =
			// (ArrayList)sess.getAttribute("ClaimSettledDatalist");
			// ArrayList ClmDataList = (ArrayList) sess.getAttribute(FlowLevel);

			List<Map<String, Object>> list = (List<Map<String, Object>>) session.getAttribute("ClaimSettledDataReport1");
			// System.out.println("@@@@@@@@@@@@ClmDataList:" + ClmDataList);
			// ArrayList HeaderArrLst = (ArrayList) ClmDataList.get(0);
			ArrayList<Object> HeaderArrLst = new ArrayList<Object>();

			String key = null;
			for (Map<String, Object> ClmDataList : list) {
				for (Map.Entry<String, Object> entry : ClmDataList.entrySet()) {
					// rowhead = sheet.createRow((short) 0);
					key = entry.getKey();
					// Object value = entry.getValue();
					System.out.println("ClmDataList key==" + key);
					HeaderArrLst.add(key);
				}
				break;
			}
			// System.out.println("@@@@@@@@@@@@HeaderArrLst:" + HeaderArrLst);
			int NoColumn = HeaderArrLst.size();

			System.out.println("NoColumn:" + NoColumn);

			// if (fileType.equals("CSVType")) {
			byte[] b = claimSettledAllReportGenerateCSV(list, NoColumn, contextPath);

			if (response != null)
				response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=ExcelData" + strDate + ".csv");
			os.write(b);
			os.flush();

		} catch (Exception e) {
			log.info("Exception == " + e);
			System.out.println("Exception == " + e);
		}
		return null;
	}

	public byte[] claimSettledAllReportGenerateCSV(List<Map<String, Object>> list, int No_Column, String contextPath)
			throws IOException {

		System.out.println("---generateCSV()---");
		StringBuffer strbuff = new StringBuffer();
		// System.out.println("ParamDataList:" + ParamDataList);
		// System.out.println("contextPath :" + contextPath);
		// ArrayList<String> rowDataLst = new ArrayList<String>();
		// ArrayList<String> HeaderLst = (ArrayList) list.get(0);
		// ArrayList<ArrayList> RecordWiseLst = (ArrayList) list.get(1);

		// List<Map<String, Object>> rowDataLst= new ArrayList<Map<String, Object>>();
		ArrayList<Object> rowDataLst = new ArrayList<Object>();

		// List<Map<String, Object>> HeaderLst= new ArrayList<Map<String, Object>>();
		String key = null;
		for (Map<String, Object> HeaderLst : list) {
			for (Map.Entry<String, Object> entry : HeaderLst.entrySet()) {
				// rowhead = sheet.createRow((short) 0);
				key = entry.getKey();
				// Object value = entry.getValue();
				System.out.println("ClmDataList key==" + key);
				rowDataLst.add(key);
			}
			// rowDataLst.add(key);
			System.out.println("HeaderLst" + HeaderLst);
			break;
		}

		// List<Map<String, Object>> RecordWiseLst= new ArrayList<Map<String,
		// Object>>();
		System.out.println("rowDataLst" + rowDataLst);

		// System.out.println("HeaderLst" + HeaderLst);
		// System.out.println("RecordWiseLst" + RecordWiseLst);
		// #### Header List

		// System.out.println("Loop--headerdata:" + headerdata);

		// System.out.println("rowDataLst:" + rowDataLst);
		// #### Header List

		// #### Data List

		for (Map<String, Object> RecordWiseLst : list) {
			for (Map.Entry<String, Object> entry : RecordWiseLst.entrySet()) {
				// rowhead = sheet.createRow((short) 0);
				// key = entry.getKey();
				Object value = entry.getValue();
				System.out.println("ClmDataList key==" + value);
				rowDataLst.add(value);

			}
			// rowDataLst.add(RecordWiseLst);
			System.out.println("RecordWiseLst" + RecordWiseLst);
		}
		System.out.println("rowDataLst" + rowDataLst);
		// System.out.println("rowDataLst::" + rowDataLst);
		// #### Data List

		ArrayList<Object> FinalrowDatalist = new ArrayList<Object>();
		// System.out.println("1");
		int y = 0;
		System.out.println("2==" + No_Column);
		for (int n = 0; n < rowDataLst.size(); n++) {

			if (n % No_Column == 0 && n != 0) {
				FinalrowDatalist.add(rowDataLst.get(n));
				FinalrowDatalist.add(n + y, "\n");
				System.out.println("2n value inside if:" + n);
				System.out.println("n:" + n);
				y++;
			} else {
				System.out.println("2n inside else:" + n);
				if (null != rowDataLst.get(n)) {
					// if (( rowDataLst.get(n)).contains(",")) {
					if (((Object) rowDataLst.get(n)).equals(",")) {
						rowDataLst.set(n, ((String) rowDataLst.get(n)).replace(",", ";"));
					}
				}
				FinalrowDatalist.add(rowDataLst.get(n));
			}
			System.out.println("rowDataLst.get " + rowDataLst.get(n) + "    " + n % 3);
		}
		// System.out.println("rowDataLst :"+rowDataLst.toString().replace("\n,","\n"));
		// String tempStr = rowDataLst.toString().replace("\n,", "\n");
		// System.out.println("3");

		String tempStr = FinalrowDatalist.toString().replace("\n,", "\n").replace(" ,", ",").replace(", ", ",");
		// String tempStr = FinalrowDatalist.toString().replace("\n,", "\n");

		// System.out.println("4");
		// strbuff.append(ParamDataList.toString().substring(2,
		// ParamDataList.toString().length() - 2).replace("endrow,", "\n"));
		strbuff.append(tempStr.substring(1, tempStr.length() - 1));
		// System.out.println("strbuff :"+strbuff);
		/// System.out.println("5");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String strDate = sdf.format(cal.getTime());
		BufferedWriter output = null;
		OutputStream outStrm;
		// File genfile = new File("D:\\GenerateFiles\\SampleFile" + strDate+
		// ".csv");
		File genfile = new File(contextPath + "\\Download\\DataCSVFile" + strDate + ".csv");

		// System.out.println("6");
		output = new BufferedWriter(new FileWriter(genfile));
		output.write(strbuff.toString());
		// System.out.println("7");
		output.flush();
		output.close();
		// System.out.println("8");

		// ##
		// FileInputStream fis = new
		// FileInputStream("D:\\GenerateFiles\\SampleFile" + strDate+ ".csv");
		FileInputStream fis = new FileInputStream(contextPath + "\\Download\\DataCSVFile" + strDate + ".csv");

		// System.out.println("9");
		byte b[];
		int x = fis.available();
		b = new byte[x];
		// System.out.println(" b size"+b.length);

		fis.read(b);
		// ##
		return b;
		// genfile.setReadOnly();
	}

	//Get the MLI Long Name in Drop Down on Page on Load
		@ModelAttribute("mliLongName")
		public Map<String, String> getMlilongName() {
			Map<String, String> mapMliLongNameObj = new HashMap<String, String>();
			mapMliLongNameObj = cgtmseExposureMasterMakerService.getMliLongNameInDropDown();
			return   mapMliLongNameObj;
		}
		
		@RequestMapping(value = "/ClaimSettledReportDownload", method = RequestMethod.GET)
		public ModelAndView claimSettledReportExcel(
				@ModelAttribute("command") ClaimLodgementBean bean, BindingResult result,
				HttpServletRequest request, HttpServletResponse response,
				HttpSession session) throws IOException {
			try {

				String filePath = downloadFileDirPath + File.separator
						+ downloadFileName;
				System.out.println("File Path==="+filePath);
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
				List<ClaimLodgementBean> list = (List<ClaimLodgementBean>) session.getAttribute("ClaimSettledDataReport");
				String Role=(String) session.getAttribute("uRole");
				//List<MLIRegistration> list = mliDetailsService.getMLIAllDetails();

				// List<UserPerivilegeDetails>
				// list=employeeService.getUserPrivlageDetails();
				log.info("list size==" + list.size());
				log.info("list Data==" + list);

				// Writing and Downlaoding Excel File
				System.out.println("Create Sheet=");
				XSSFWorkbook hwb = new XSSFWorkbook();
				XSSFSheet sheet = hwb.createSheet("ClaimSettledReportExcelFile");

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
	            int ronnn=0;
				XSSFCell cell0 = rowhead.createCell(ronnn);
				cell0.setCellStyle(style);
				cell0.setCellValue("Bank Name");// Done 1
				ronnn++;

				XSSFCell cell1 = rowhead.createCell(ronnn);
				cell1.setCellStyle(style);
				cell1.setCellValue("Member ID");// Done 3
				ronnn++;
				
				XSSFCell cell2 = rowhead.createCell(ronnn);
				cell2.setCellStyle(style);
				cell2.setCellValue("Unit Name");// Done 4
				ronnn++;
				
				XSSFCell cell3 = rowhead.createCell(ronnn);
				cell3.setCellStyle(style);
				cell3.setCellValue("CGPAN Number");// Done 5
				ronnn++;
				
				if(Role.equals("CMAKER") || Role.equals("CCHECKER")){
					XSSFCell cell112 = rowhead.createCell(ronnn);
					cell112.setCellStyle(style);
					cell112.setCellValue("Msc-It-Pan");// Done 4
					ronnn++;
					
					XSSFCell cell113 = rowhead.createCell(ronnn);
					cell113.setCellStyle(style);
					cell113.setCellValue("CHip-promoter-It-pan");// Done 5
					ronnn++;
					
				}
				
				
				XSSFCell cell4 = rowhead.createCell(ronnn);
				cell4.setCellStyle(style);
				cell4.setCellValue("Approved Amt");// Done 6
				ronnn++;
				
				XSSFCell cell5 = rowhead.createCell(ronnn);
				cell5.setCellStyle(style);
				cell5.setCellValue("Latest O/S guaranteed Amount");// Done 7
				ronnn++;
				
				XSSFCell cell6 = rowhead.createCell(ronnn);
				cell6.setCellStyle(style);
				cell6.setCellValue("O/S Amt as on NPA");// Done 7
				ronnn++;
				
				XSSFCell cell7 = rowhead.createCell(ronnn);
				cell7.setCellStyle(style);
				cell7.setCellValue("(Repayment/ Recovery) after  NPA");// Done 7
				ronnn++;
				
				XSSFCell cell8 = rowhead.createCell(ronnn);
				cell8.setCellStyle(style);
				cell8.setCellValue("Subsidy amount");// 
				ronnn++;
				
				XSSFCell cell9 = rowhead.createCell(ronnn);
				cell9.setCellStyle(style);
				cell9.setCellValue("Latest O/S Amt as on ASF payment");// 
				ronnn++;
				
				XSSFCell cell10 = rowhead.createCell(ronnn);
				cell10.setCellStyle(style);
				cell10.setCellValue("O/S as on lodgment of claim");// 
				ronnn++;
				
				XSSFCell cell11 = rowhead.createCell(ronnn);
				cell11.setCellStyle(style);
				cell11.setCellValue("Eligible Amt");// 
				ronnn++;
				
				XSSFCell cell12 = rowhead.createCell(ronnn);
				cell12.setCellStyle(style);
				cell12.setCellValue("Payable as First Installment");// 
				ronnn++;
				
				XSSFCell cell13 = rowhead.createCell(ronnn);
				cell13.setCellStyle(style);
				cell13.setCellValue("CLAIM APPROVED DATE");// 
				ronnn++;
				
				XSSFCell cell14 = rowhead.createCell(ronnn);
				cell14.setCellStyle(style);
				cell14.setCellValue("Loan Account No");// 
			
				int index = 1;
				int sno = 0;
				Iterator<ClaimLodgementBean> itr2 = list.iterator();
				while (itr2.hasNext()) {
					ClaimLodgementBean obj1 = (ClaimLodgementBean) itr2.next();
					sno++;
					XSSFRow row = sheet.createRow((short) index);
					//
	                int row1=0;
					row.createCell((short) row1).setCellValue(obj1.getBankName());// Done
					row1++;															// 1
					row.createCell((short) row1).setCellValue(obj1.getMemberId());// Done
					row1++;																// 2
					row.createCell((short) row1).setCellValue(obj1.getBorrowerName());// Done
					row1++;
					row.createCell((short) row1).setCellValue(obj1.getCgpan());// Done
					row1++;
					if(Role.equals("CMAKER") || Role.equals("CCHECKER")){
						row.createCell((short) row1).setCellValue(obj1.getMscItPan()==null ? "":obj1.getMscItPan());// Done
						row1++;
						row.createCell((short) row1).setCellValue(obj1.getChipProItPan()==null ?"" :obj1.getChipProItPan());// Done
						row1++;
					}
					
					row.createCell((short) row1).setCellValue(obj1.getGuarantee_Amt());// Done
					row1++;
					row.createCell((short) row1).setCellValue(obj1.getLatestOsAmt());// Done
					row1++;
					row.createCell((short) row1).setCellValue(obj1.getNpaEligibleAmt());// Done
					row1++;
					row.createCell((short) row1).setCellValue(obj1.getRecovery());// Done
					row1++;
					row.createCell((short) row1).setCellValue(obj1.getSubsidyAmt());// Done
					row1++;
					row.createCell((short) row1).setCellValue(obj1.getTotalOsAmt());// Done
					row1++;
					row.createCell((short) row1).setCellValue(obj1.getOsAmtClaim());// Done
					row1++;
					row.createCell((short) row1).setCellValue(obj1.getEligableAmtClaim());// Done
					row1++;
					row.createCell((short) row1).setCellValue(obj1.getFirstInstallClaim());// Done
					row1++;															// 4
				    row.createCell((short) row1).setCellValue(obj1.getCgtCheckerDate());// Done
				    row1++;
					row.createCell((short) row1).setCellValue(obj1.getLoanAccountNo());// Done
					
					
					index++;
				}
				FileOutputStream fileOut = new FileOutputStream(filePath);
				hwb.write(fileOut);
				fileOut.close();

				ModelAndView model = new ModelAndView("newRolePage");

				model.addObject(
						"excelFileDownLoadSuccessfully",
						"File DownLoaded Successfully in this location F:/ExcelReports/nbfcClaimSettledExcel.xls.");

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
				System.out.println("DDDDD==");
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
