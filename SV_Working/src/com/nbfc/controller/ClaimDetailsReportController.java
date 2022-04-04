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
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import com.lowagie.text.Cell;
import com.lowagie.text.pdf.codec.Base64.InputStream;
import com.nbfc.bean.ApplicationStatusDetailsBean;
import com.nbfc.bean.ClaimLodgementBean;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.bean.StateBean;
import com.nbfc.helper.PropertyLoader;
import com.nbfc.model.MLIName;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.State;
import com.nbfc.model.User;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.CGTMSECreateExposureLimitMakerService;
import com.nbfc.service.ClaimLodgementService;
import com.nbfc.service.LoginService;
import com.nbfc.service.NPAService;
import com.nbfc.service.StateService;
import com.nbfc.service.UserActivityService;
import com.nbfc.validation.EmployeeValidator;

@Controller
public class ClaimDetailsReportController {

	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	ClaimLodgementService claimLodgementService;
	NBFCPrivilegeMaster userPrvMst;
	@Autowired
	NPAService npaService;
	@Autowired
	StateService stateService;
	@Autowired
	private CGTMSECreateExposureLimitMakerService cgtmseExposureMasterMakerService;
	@Autowired
	EmployeeValidator validator;
	@Value("${downloadFileDirPath}")
	private String downloadFileDirPath;
	@Value("${ClaimDetailsReportFile}")
	private String downloadFileName;
	ModelAndView modelView;
	public static final int BUFFER_SIZE = 4096;
	UserPerivilegeDetails userPri;
	String memberId = null;
	String userId = null;
	MLIName mem_id = null;
	@Autowired
	static Logger log = Logger.getLogger(NBFCController.class.getName());

	List<Map<String, Object>> ClaimDetailsDataReport = new ArrayList<Map<String, Object>>();

	@RequestMapping(value = "/ClaimStatusWiseDetailsReport", method = RequestMethod.GET)
	public ModelAndView ClaimDetailsReport(@ModelAttribute("command") ClaimLodgementBean bean, BindingResult result,
			HttpSession session, Model model) throws JRException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		String Role = (String) session.getAttribute("uRole");
		userId = (String) session.getAttribute("userId");
		String loginUserMemId = npaService.getMemberId(userId);
		// nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);
		if (Role.equals("CMAKER")) {
			model1.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
			model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
			model1.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));

			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSEMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("memberId", loginUserMemId);
			model1.put("homePage", "cgtmseMakerHome");

		} else if (Role.equals("CCHECKER")) {
			model1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
			model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSECHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("memberId", loginUserMemId);
			model1.put("homePage", "cgtmseCheckerHome");
			// return null;
		} else if (Role.equals("NMAKER")) {
			// added by say 6 feb-----------------------
			model1.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			model1.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			model1.put("memberId", loginUserMemId);
			model1.put("homePage", "nbfcMakerHome");

		} else if (Role.equals("NCHECKER")) {
			model1.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
			model1.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
			model1.put("memberId", loginUserMemId);
			model1.put("homePage", "nbfcCheckerHome");

		}
		return new ModelAndView("ClaimDetailsReportInput", model1);
		// return null;
	}

	//Get the MLI Long Name in Drop Down on Page on Load
			@ModelAttribute("mliLongName")
			public Map<String, String> getMlilongName() {
				Map<String, String> mapMliLongNameObj = new HashMap<String, String>();
				mapMliLongNameObj = cgtmseExposureMasterMakerService.getMliLongNameInDropDown();
				return   mapMliLongNameObj;
			}
	@RequestMapping(value = "/ClaimDetailsReportData", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView claimDetailsReportList(@ModelAttribute("command") ClaimLodgementBean bean, BindingResult result,
			HttpSession session, Model model, @RequestParam("claimStatus") String claimStatus)
			throws JRException, ParseException {

		System.out.println("Data=======");

		Map<String, Object> model1 = new HashMap<String, Object>();
		List<Map<String, Object>> ClaimDetailsDataReport = new ArrayList<Map<String, Object>>();

		
		  session.setAttribute("ClaimDetailsDataReport",null);
		 session.removeAttribute("ClaimDetailsDataReport");
		 
		String Role = (String) session.getAttribute("uRole");

		String userId = (String) session.getAttribute("userId");
		memberId = npaService.getMemberId(userId);
		System.out.println("claimStatus:" + claimStatus);
		
		String mliLongName=bean.getMliLongName();
		
		System.out.println("mliLongName==194==="+mliLongName);

		// Date fromDate=fromDate1.to;

		// Date fromDate=new SimpleDateFormat("dd/MM/yyyy").parse(fromDate1);
		// Date toDate=new SimpleDateFormat("dd/MM/yyyy").parse(toDate1);
		// System.out.println("From Date="+fromDate);
		// System.out.println("To Date="+toDate);

		validator.ClaimDetailsReportValidate(bean, result);
		if (result.hasErrors()) {
			if (Role.equals("CMAKER")) {
				// userId = "ADMIN";
				model1.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
				model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
				model1.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("CGTMSEMAKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "cgtmseMakerHome");

			} else if (Role.equals("CCHECKER")) {
				// userId = "ADMIN";
				model1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
				model1.put("applicationList",
						userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
				model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
				model1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("CGTMSECHECKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "cgtmseCheckerHome");
				// return null;
			} else if (Role.equals("NMAKER")) {
				// added by say 6 feb-----------------------
				// userId = (String) session.getAttribute("userId");
				model1.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
				model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
				model1.put("homePage", "nbfcMakerHome");

			} else if (Role.equals("NCHECKER")) {
				// userId = (String) session.getAttribute("userId");
				model1.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("NBFCCHECKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "nbfcCheckerHome");

			}
			return new ModelAndView("ClaimDetailsReportInput", model1);
		}

		if (Role.equals("CMAKER")) {
			userId = "ADMIN";
			model1.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));

			model1.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
			model1.put("homePage", "cgtmseMakerHome");

		} else if (Role.equals("CCHECKER")) {
			userId = "ADMIN";
			model1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));

			model1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("homePage", "cgtmseCheckerHome");
			// return null;
		} else if (Role.equals("NMAKER")) {
			// added by say 6 feb-----------------------
			userId = (String) session.getAttribute("userId");
			model1.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			model1.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			model1.put("homePage", "nbfcMakerHome");

		} else if (Role.equals("NCHECKER")) {
			userId = (String) session.getAttribute("userId");
			model1.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
			model1.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
			// model1.put("actNameHome",
			model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
			model1.put("homePage", "nbfcCheckerHome");

		}

		/*
		 * String toDateF = bean.getToDate(); String fromDateF = bean.getFromDate();
		 * session.setAttribute("FDate", fromDateF); session.setAttribute("TDate",
		 * toDateF);
		 */

		// Date fromDate=fromDate1;

		Date toDate = new SimpleDateFormat("dd/MM/yyyy").parse(bean.getToDate());
		Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(bean.getFromDate());

		// Date toDate1=bean.getToDate();
		// Date fromDate1=bean.getFromDate();
		if (Role.equals("CCHECKER") || Role.equals("CMAKER")) {
			List<Map<String, Object>> ClaimDetailsDataReport1 = new ArrayList<Map<String, Object>>();
			if(mliLongName.equals("NONE"))
			{
				ClaimDetailsDataReport1=claimLodgementService.getClaimDetailsReportAll(userId, toDate, fromDate,Role,claimStatus);
				if (!ClaimDetailsDataReport1.isEmpty()) {
					model1.put("ClaimDetailsDataReport1", ClaimDetailsDataReport1);
					session.setAttribute("ClaimDetailsDataReport1", ClaimDetailsDataReport1);
				} else {
					model1.put("message", "NO Data Found !!");
				}
				return new ModelAndView("ClaimDetailsReportDataAll", model1);
			}
			else
			{
			//String mliname = bean.getMliName();
			mem_id = userActivityService.getBankID(mliLongName);
			//mem_id = userActivityService.getBankID(mliLongName);
			//mem_id = userActivityService.getBankID(mliname);
			String Mem_Id = mem_id.getBnkId() + mem_id.getZneID() + mem_id.getBrnName();
			System.out.println("Mem_Id==="+Mem_Id);
			System.out.println("From Date=###" + fromDate);
			System.out.println("To Date=###" + toDate);
			ClaimDetailsDataReport = claimLodgementService.getClaimDetailsReport(userId, toDate, fromDate, Mem_Id, Role,
					claimStatus);
			}
		} else {
			System.out.println("Into Calling==");
			System.out.println("From Date=" + fromDate);
			System.out.println("To Date=" + toDate);
			ClaimDetailsDataReport = claimLodgementService.getClaimDetailsReport(userId, toDate, fromDate, memberId,
					Role, claimStatus);
		}

		// nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);
		if (!ClaimDetailsDataReport.isEmpty()) {
			model1.put("ClaimDetailsDataReport", ClaimDetailsDataReport);
			session.setAttribute("ClaimDetailsDataReport", ClaimDetailsDataReport);
		} else {
			model1.put("message", "NO Data Found !!");
		}
		return new ModelAndView("ClaimDetailsReportData", model1);
	}

	
	  @RequestMapping(value = "/ClaimDetailsReportDataBack", method ={RequestMethod.GET, RequestMethod.POST}) public ModelAndView
	  claimDetailsReportBackList(@ModelAttribute("command") ClaimLodgementBean bean, BindingResult result,
	  HttpSession session, Model model,@RequestParam("claimStatus") String
	  claimStatus, @RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("mliId") String memberId )
	  throws JRException, ParseException {
	  
	  System.out.println("Data===ClaimDetailsReportDataBack====");
	  
	  System.out.println("Data=======");

		Map<String, Object> model1 = new HashMap<String, Object>();
		List<Map<String, Object>> ClaimDetailsDataReport = new ArrayList<Map<String, Object>>();

		/*
		 * session.setAttribute("ClaimDetailsDataReport",null);
		 * session.removeAttribute("ClaimDetailsDataReport");
		 */
		String Role = (String) session.getAttribute("uRole");

		String userId = (String) session.getAttribute("userId");
		//memberId = npaService.getMemberId(userId);
		System.out.println("claimStatus:" + claimStatus);
		
		
		
		//mliLongName=bean.getMliLongName();
		
		System.out.println("mliLongName==194==="+memberId);

		// Date fromDate=fromDate1.to;

		// Date fromDate=new SimpleDateFormat("dd/MM/yyyy").parse(fromDate1);
		// Date toDate=new SimpleDateFormat("dd/MM/yyyy").parse(toDate1);
		// System.out.println("From Date="+fromDate);
		// System.out.println("To Date="+toDate);

		validator.ClaimDetailsReportValidate(bean, result);
		if (result.hasErrors()) {
			if (Role.equals("CMAKER")) {
				// userId = "ADMIN";
				model1.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
				model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
				model1.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("CGTMSEMAKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "cgtmseMakerHome");

			} else if (Role.equals("CCHECKER")) {
				// userId = "ADMIN";
				model1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
				model1.put("applicationList",
						userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
				model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
				model1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("CGTMSECHECKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "cgtmseCheckerHome");
				// return null;
			} else if (Role.equals("NMAKER")) {
				// added by say 6 feb-----------------------
				// userId = (String) session.getAttribute("userId");
				model1.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
				model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
				model1.put("homePage", "nbfcMakerHome");

			} else if (Role.equals("NCHECKER")) {
				// userId = (String) session.getAttribute("userId");
				model1.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("NBFCCHECKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "nbfcCheckerHome");

			}
			return new ModelAndView("ClaimDetailsReportInput", model1);
		}

		if (Role.equals("CMAKER")) {
			userId = "ADMIN";
			model1.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));

			model1.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
			model1.put("homePage", "cgtmseMakerHome");

		} else if (Role.equals("CCHECKER")) {
			userId = "ADMIN";
			model1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));

			model1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("homePage", "cgtmseCheckerHome");
			// return null;
		} else if (Role.equals("NMAKER")) {
			// added by say 6 feb-----------------------
			userId = (String) session.getAttribute("userId");
			model1.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			model1.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			model1.put("homePage", "nbfcMakerHome");

		} else if (Role.equals("NCHECKER")) {
			userId = (String) session.getAttribute("userId");
			model1.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
			model1.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
			// model1.put("actNameHome",
			model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
			model1.put("homePage", "nbfcCheckerHome");

		}
	  
	/*  String toDateF = bean.getToDate(); String fromDateF = bean.getFromDate();
	  session.setAttribute("FDate", fromDateF); session.setAttribute("TDate",
	  toDateF);
	  
	  //Date fromDate=fromDate1;
	 
	  //toDate = new SimpleDateFormat("dd/MM/yyyy").parse(bean.getToDate());
	  //fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(bean.getFromDate());
	  
	  
	  //Date toDate1=bean.getToDate(); //Date fromDate1=bean.getFromDate();
	  if(Role.equals("CCHECKER") || Role.equals("CMAKER")){ 
		  String
	  mliname=bean.getMliName(); mem_id = userActivityService.getBankID(mliname);
	  String Mem_Id= mem_id.getBnkId() + mem_id.getZneID() + mem_id.getBrnName();
	  
	  System.out.println("From Date=###"+fromDate);
	  System.out.println("To Date=###"+toDate);
	  ClaimDetailsDataReport=claimLodgementService.getClaimDetailsReport(userId,
	  toDate, fromDate, Mem_Id,Role,claimStatus); }else{
	  System.out.println("Into Calling==");
	  System.out.println("From Date="+fromDate);
	  System.out.println("To Date="+toDate);
	  ClaimDetailsDataReport=claimLodgementService.getClaimDetailsReport(userId,
	  toDate, fromDate, memberId,Role,claimStatus); }
	  
	  
	  
	  
	  // nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request); 
	  if
	  (!ClaimDetailsDataReport.isEmpty()) { model1.put("ClaimDetailsDataReport",
	  ClaimDetailsDataReport); session.setAttribute("ClaimDetailsDataReport",
	  ClaimDetailsDataReport); } else { model1.put("message", "NO Data Found !!");
	  } return new ModelAndView("ClaimDetailsReportData", model1); */
		
		 //toDate = new SimpleDateFormat("dd/MM/yyyy").parse(bean.getToDate());
		 //fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(bean.getFromDate());

		// Date toDate1=bean.getToDate();
		// Date fromDate1=bean.getFromDate();
		if (Role.equals("CCHECKER") || Role.equals("CMAKER")) {
			//String mliname = bean.getMliName();
			//////mem_id = userActivityService.getBankID(memberId);
			//mem_id = userActivityService.getBankID(mliLongName);
			//mem_id = userActivityService.getBankID(mliname);
			//////String Mem_Id = mem_id.getBnkId() + mem_id.getZneID() + mem_id.getBrnName();
			System.out.println("Mem_Id==="+memberId);
			System.out.println("From Date=###" + fromDate);
			System.out.println("To Date=###" + toDate);
			ClaimDetailsDataReport = claimLodgementService.getClaimDetailsReport(userId, toDate, fromDate, memberId, Role,
					claimStatus);
		} else {
			System.out.println("Into Calling==");
			System.out.println("From Date=" + fromDate);
			System.out.println("To Date=" + toDate);
			ClaimDetailsDataReport = claimLodgementService.getClaimDetailsReport(userId, toDate, fromDate, memberId,
					Role, claimStatus);
		}

		// nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);
		if (!ClaimDetailsDataReport.isEmpty()) {
			model1.put("ClaimDetailsDataReport", ClaimDetailsDataReport);
			session.setAttribute("ClaimDetailsDataReport", ClaimDetailsDataReport);
		} else {
			model1.put("message", "NO Data Found !!");
		}
		return new ModelAndView("ClaimDetailsReportData", model1);
	  }
	  
	  @RequestMapping(value = "/ClaimDetailsAllReportDataBack", method ={RequestMethod.GET, RequestMethod.POST})
	  public ModelAndView claimDetailsAllReporDatatBack(@ModelAttribute("command") ClaimLodgementBean bean, BindingResult result,
	  HttpSession session, Model model,@RequestParam("claimStatus") String
	  claimStatus, @RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate)
	  throws JRException, ParseException {
	  System.out.println("Data===ClaimDetailsReportDataBack====");
	  System.out.println("Data=======");
		Map<String, Object> model1 = new HashMap<String, Object>();
		List<Map<String, Object>> ClaimDetailsDataReport = new ArrayList<Map<String, Object>>();
		String Role = (String) session.getAttribute("uRole");
		String userId = (String) session.getAttribute("userId");
		System.out.println("claimStatus:" + claimStatus);
		validator.ClaimDetailsReportValidate(bean, result);
		if (result.hasErrors()) {
			if (Role.equals("CMAKER")) {
				model1.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
				model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
				model1.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));
				model1.put("homePage", "cgtmseMakerHome");
			} else if (Role.equals("CCHECKER")) {
				model1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
				model1.put("applicationList",
						userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
				model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
				model1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
				model1.put("homePage", "cgtmseCheckerHome");
			} else if (Role.equals("NMAKER")) {
				model1.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
				model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
				model1.put("homePage", "nbfcMakerHome");
			} else if (Role.equals("NCHECKER")) {
				model1.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
				model1.put("homePage", "nbfcCheckerHome");
			}
			return new ModelAndView("ClaimDetailsReportInput", model1);
		}
		if (Role.equals("CMAKER")) {
			userId = "ADMIN";
			model1.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
			model1.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
			model1.put("homePage", "cgtmseMakerHome");
		} else if (Role.equals("CCHECKER")) {
			userId = "ADMIN";
			model1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
			model1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("homePage", "cgtmseCheckerHome");
		} else if (Role.equals("NMAKER")) {
			userId = (String) session.getAttribute("userId");
			model1.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			model1.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			model1.put("homePage", "nbfcMakerHome");
		} else if (Role.equals("NCHECKER")) {
			userId = (String) session.getAttribute("userId");
			model1.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
			model1.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
			model1.put("homePage", "nbfcCheckerHome");
		}
		if (Role.equals("CCHECKER") || Role.equals("CMAKER")) {
			System.out.println("Mem_Id==="+memberId);
			System.out.println("From Date=###" + fromDate);
			System.out.println("To Date=###" + toDate);
			ClaimDetailsDataReport = claimLodgementService.getClaimDetailsReportAll(userId, toDate, fromDate, Role,
					claimStatus);
		} else {
			System.out.println("Into Calling==");
			System.out.println("From Date=" + fromDate);
			System.out.println("To Date=" + toDate);
			ClaimDetailsDataReport = claimLodgementService.getClaimDetailsReportAll(userId, toDate, fromDate,
					Role, claimStatus);
		}
		if (!ClaimDetailsDataReport.isEmpty()) {
			model1.put("ClaimDetailsDataReport1", ClaimDetailsDataReport);
			session.setAttribute("ClaimDetailsDataReport1", ClaimDetailsDataReport);
		} else {
			model1.put("message", "NO Data Found !!");
		}
		return new ModelAndView("ClaimDetailsReportDataAll", model1);
	  }
	  @RequestMapping(value = "/ClaimDetailsReportAllDownload", method = RequestMethod.GET)
		public ModelAndView ClaimDetailsReportAllDownload(@ModelAttribute("command") ClaimLodgementBean bean,
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

				List<Map<String, Object>> list = (List<Map<String, Object>>) session.getAttribute("ClaimDetailsDataReport1");
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
	 
	@RequestMapping(value = "ClaimDetailForm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView getclaimLoadgeForm(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,
			BindingResult result, HttpSession session, Model modelObj,
			@RequestParam(value = "claimRefNo") String claimRefNo, @RequestParam("claimStatus") String claimStatus,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,@RequestParam("mliId") String memberId) {
		try {
			System.out.println(
					"claimLodgementInputForm method called as part of ClaimLodgementController controller=== and claimRefNo=="
							+ claimRefNo);

			Map<String, Object> model1 = new HashMap<String, Object>();
			String userId = (String) session.getAttribute("userId");

			System.out.println("Claim_status--Claim Form --" + claimStatus);
			// List<ClaimLodgementBean> claimLodgList = null;
			/// loginUserMemId = claimLodgementService.getLoginUserMemId(userId);
			//memberId = npaService.getMemberId(userId);
			String Role = (String) session.getAttribute("uRole");
			ClaimLodgementBean claimLodgListObj = claimLodgementService.getClaimRefNoDetails(claimRefNo);
			//System.out.println(memberId);
			claimLodgListObj.setClaimRefNo(claimRefNo);
			claimLodgListObj.setClaimStatus(claimStatus);
			claimLodgListObj.setFromDate(fromDate);
			claimLodgListObj.setToDate(toDate);
			//claimLodgListObj.setBankName(mliId);
			claimLodgListObj.setMemberId(memberId);
			
			System.out.println("Member_id==="+memberId);

			userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				if (Role.equals("CMAKER")) {
					// userId = "ADMIN";
					model1.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
					model1.put("applicationList",
							userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
					model1.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
					model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
					model1.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));
					// model1.put("actNameHome",
					// userActivityService.getActivityName("CGTMSEMAKER",
					// "cgpanDetailReport"));// Added by Say 31 Jan19
					model1.put("homePage", "cgtmseMakerHome");
					model1.put("formData", claimLodgListObj);

				} else if (Role.equals("CCHECKER")) {
					// userId = "ADMIN";
					model1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
					model1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
					model1.put("applicationList",
							userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
					model1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
					model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
					model1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
					// model1.put("actNameHome",
					// userActivityService.getActivityName("CGTMSECHECKER",
					// "cgpanDetailReport"));// Added by Say 31 Jan19
					model1.put("homePage", "cgtmseCheckerHome");
					model1.put("formData", claimLodgListObj);
					// return null;
				} else if (Role.equals("NMAKER")) {
					// added by say 6 feb-----------------------
					// userId = (String) session.getAttribute("userId");
					model1.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
					model1.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
					model1.put("applicationList",
							userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
					model1.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
					model1.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
					model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
					model1.put("homePage", "nbfcMakerHome");
					model1.put("formData", claimLodgListObj);

				} else if (Role.equals("NCHECKER")) {
					// userId = (String) session.getAttribute("userId");
					model1.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
					model1.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
					model1.put("applicationList",
							userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
					model1.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
					model1.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
					// model1.put("actNameHome",
					// userActivityService.getActivityName("NBFCCHECKER",
					// "cgpanDetailReport"));// Added by Say 31 Jan19
					model1.put("homePage", "nbfcCheckerHome");
					model1.put("formData", claimLodgListObj);

				}
				modelView = new ModelAndView("ClaimDetailForm", model1);
				return modelView;
			}

			if (Role.equals("CMAKER")) {
				userId = "ADMIN";
				model1.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));

				model1.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));
				model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
				model1.put("homePage", "cgtmseMakerHome");
				model1.put("formData", claimLodgListObj);

			} else if (Role.equals("CCHECKER")) {
				userId = "ADMIN";
				model1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
				model1.put("applicationList",
						userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));

				model1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
				model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
				model1.put("homePage", "cgtmseCheckerHome");
				model1.put("formData", claimLodgListObj);
				// return null;
			} else if (Role.equals("NMAKER")) {
				// added by say 6 feb-----------------------
				userId = (String) session.getAttribute("userId");
				model1.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
				model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
				model1.put("homePage", "nbfcMakerHome");
				model1.put("formData", claimLodgListObj);

			} else if (Role.equals("NCHECKER")) {
				userId = (String) session.getAttribute("userId");
				model1.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
				// model1.put("actNameHome",
				model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
				model1.put("homePage", "nbfcCheckerHome");
				model1.put("formData", claimLodgListObj);

			}

			// model1.put("formData", claimLodgListObj);

			modelView = new ModelAndView("ClaimDetailForm", model1);
			return modelView;
			// }else{
			// modelView = new ModelAndView("redirect:/SVLogin.html");
			// }

		} catch (Exception e) {
			System.out.println("Exception ==" + e);
		}
		return modelView;
	}
	@RequestMapping(value = "ClaimDetailFormAll", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView getclaimLoadgeFormAll(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,
			BindingResult result, HttpSession session, Model modelObj,
			@RequestParam(value = "claimRefNo") String claimRefNo, @RequestParam("claimStatus") String claimStatus,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		try {
			System.out.println(
					"claimLodgementInputForm method called as part of ClaimLodgementController controller=== and claimRefNo=="
							+ claimRefNo);
			Map<String, Object> model1 = new HashMap<String, Object>();
			String userId = (String) session.getAttribute("userId");
			System.out.println("Claim_status--Claim Form --" + claimStatus);
			String Role = (String) session.getAttribute("uRole");
			ClaimLodgementBean claimLodgListObj = claimLodgementService.getClaimRefNoDetails(claimRefNo);
			claimLodgListObj.setClaimRefNo(claimRefNo);
			claimLodgListObj.setClaimStatus(claimStatus);
			claimLodgListObj.setFromDate(fromDate);
			claimLodgListObj.setToDate(toDate);
			userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				if (Role.equals("CMAKER")) {
					model1.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
					model1.put("applicationList",
							userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
					model1.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
					model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
					model1.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));
					model1.put("homePage", "cgtmseMakerHome");
					model1.put("formData", claimLodgListObj);
				} else if (Role.equals("CCHECKER")) {
					model1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
					model1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
					model1.put("applicationList",
							userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
					model1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
					model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
					model1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
					model1.put("homePage", "cgtmseCheckerHome");
					model1.put("formData", claimLodgListObj);
				} else if (Role.equals("NMAKER")) {
					model1.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
					model1.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
					model1.put("applicationList",
							userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
					model1.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
					model1.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
					model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
					model1.put("homePage", "nbfcMakerHome");
					model1.put("formData", claimLodgListObj);
				} else if (Role.equals("NCHECKER")) {
					model1.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
					model1.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
					model1.put("applicationList",
							userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
					model1.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
					model1.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
					model1.put("homePage", "nbfcCheckerHome");
					model1.put("formData", claimLodgListObj);
				}
				modelView = new ModelAndView("ClaimAllDetailForm", model1);
				return modelView;
			}
			if (Role.equals("CMAKER")) {
				userId = "ADMIN";
				model1.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));
				model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
				model1.put("homePage", "cgtmseMakerHome");
				model1.put("formData", claimLodgListObj);
			} else if (Role.equals("CCHECKER")) {
				userId = "ADMIN";
				model1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
				model1.put("applicationList",
						userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
				model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
				model1.put("homePage", "cgtmseCheckerHome");
				model1.put("formData", claimLodgListObj);
			} else if (Role.equals("NMAKER")) {
				userId = (String) session.getAttribute("userId");
				model1.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
				model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
				model1.put("homePage", "nbfcMakerHome");
				model1.put("formData", claimLodgListObj);
			} else if (Role.equals("NCHECKER")) {
				userId = (String) session.getAttribute("userId");
				model1.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
				model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
				model1.put("homePage", "nbfcCheckerHome");
				model1.put("formData", claimLodgListObj);
			}
			modelView = new ModelAndView("ClaimAllDetailForm", model1);
			return modelView;
		} catch (Exception e) {
			System.out.println("Exception ==" + e);
		}
		return modelView;
	}

	@RequestMapping(value = "/ClaimDetailsReportDownload", method = RequestMethod.GET)
	public ModelAndView claimDetailsReportExcel(@ModelAttribute("command") ClaimLodgementBean bean,
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

			List<Map<String, Object>> list = (List<Map<String, Object>>) session.getAttribute("ClaimDetailsDataReport");
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
			byte[] b = accountHistoryGenerateCSV(list, NoColumn, contextPath);

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

	public byte[] accountHistoryGenerateCSV(List<Map<String, Object>> list, int No_Column, String contextPath)
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

}
