package com.nbfc.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.lowagie.text.DocumentException;
import com.nbfc.bean.GeneralReport;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.helper.PropertyLoader;
import com.nbfc.model.State;
import com.nbfc.service.ExposureWiseGuaranteeDataService;
import com.nbfc.service.NPAService;
import com.nbfc.service.StateService;
import com.nbfc.service.UserActivityService;
import com.nbfc.validation.EmployeeValidator;

@Controller
public class ExposureWiseGuaranteeDataController {
	
	@Autowired
	UserActivityService userActivityService;
	
	@Autowired
	ExposureWiseGuaranteeDataService exposureWiseGuaranteeDataService;
	@Autowired
	EmployeeValidator validator;
	
	@Autowired
	StateService stateService;
	
	@Autowired
	NPAService npaService;
	String memberId=null;
	String userId = null;
	public static final int BUFFER_SIZE = 4096;
	
	List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
	
	static Logger log = Logger.getLogger(NBFCController.class.getName());
	                         
	@RequestMapping(value = "/ExposureWiseGuaranteeDetail", method = RequestMethod.GET)
	public ModelAndView cgpanDetailReport(
			@ModelAttribute("command") GeneralReport bean,
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
		return new ModelAndView("ExposureWiseGuaranteepageinput", model1);
		// return null;
	}
	
	@RequestMapping(value = "/searchExposureData", method = RequestMethod.POST)
	public ModelAndView searchExposureData(
			@ModelAttribute("command") GeneralReport bean,
			BindingResult result, HttpSession session, Model model)
			throws JRException, ParseException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		String Role = (String) session.getAttribute("uRole");
		// nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);

		validator.exposureDetailsValidate(bean, result);
		if (result.hasErrors()) {
			if (Role.equals("CMAKER")) {
				// userId = "ADMIN";
				model1.put("guaranteelist", userActivityService.getActivity(
						"CGTMSEMAKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity(
						"CGTMSEMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity(
						"CGTMSEMAKER", "Receipt_Payments"));

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
				model1.put("CList",
						userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
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
				model1.put("CList",
						userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("NBFCMAKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
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
				model1.put("CList",
						userActivityService.getReport("NBFCCHECKER", "Claim_Lodgement"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("NBFCCHECKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "nbfcCheckerHome");

			}
			return new ModelAndView("ExposureWiseGuaranteepageinput", model1);
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
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSEMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSECHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("NBFCCHECKER", "Claim_Lodgement"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCCHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcCheckerHome");

		}
		String toDateF = bean.getToDate();
		String fromDateF = bean.getFromDate();
		session.setAttribute("FDate", fromDateF);
		session.setAttribute("TDate", toDateF);
		//session.setAttribute("AppStatus", bean.getAppStatus());

		Date toDate = new SimpleDateFormat("dd/MM/yyyy")
				.parse(bean.getToDate());
		Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(bean
				.getFromDate());
		rows = exposureWiseGuaranteeDataService.getExposureGuaranteeData(userId,Role, toDate,
				fromDate);
		
		if (!rows.isEmpty()) {
			model1.put("rows", rows);
			session.setAttribute("exposuredata1", rows);
		} else {
			model1.put("noDataFound", "NO Data Found !!");
		}

		return new ModelAndView("ExposureWiseGuaranteepageinput", model1);
		// return null;
	}
	
	//method for getting perticular mli exposure data .....added by ajeet 12-02-2020
	@RequestMapping(value = "/getMliExposureDataById", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView getMliExposureDataById(
			@ModelAttribute("command") GeneralReport bean,
			BindingResult result, HttpSession session, Model model,HttpServletRequest request)
			throws JRException, ParseException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		List<Map<String, Object>> rowsChild = new ArrayList<Map<String, Object>>();

		String Role = (String) session.getAttribute("uRole");
		String EXPOSURE_ID=request.getParameter("EXPOSURE_ID");
		session.setAttribute("EXPOSURE_ID", EXPOSURE_ID);

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
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSEMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSECHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("NBFCCHECKER", "Claim_Lodgement"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCCHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcCheckerHome");

		}
	
		String toDateF=(String) session.getAttribute("TDate");
		String fromDateF=(String) session.getAttribute("FDate");
	
		Date toDate = new SimpleDateFormat("dd/MM/yyyy")
				.parse(toDateF);
		Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(fromDateF);
		if(EXPOSURE_ID != null){
		rowsChild = exposureWiseGuaranteeDataService.getMliExposureDataByMLIName(userId,EXPOSURE_ID,Role, toDate,
				fromDate);
		
		if (!rowsChild.isEmpty()) {
			model1.put("rowsChild", rowsChild);
			session.setAttribute("exposuredata2", rowsChild);
		} else {
			model1.put("noDataFound", "NO Data Found !!");
		}
		}else{
			rows = exposureWiseGuaranteeDataService.getExposureGuaranteeData(userId,Role, toDate,
					fromDate);
			
			if (!rows.isEmpty()) {
				model1.put("rows", rows);
			} else {
				model1.put("noDataFound", "NO Data Found !!");
			}
		}

		return new ModelAndView("ExposureWiseGuaranteepageinput", model1);
		// return null;
	}

	//method for getting perticular portfolio data by folioName .....added by ajeet 13-02-2020
	@RequestMapping(value = "/getDataByPortfolioName", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView getDataByPortfolioName(
			@ModelAttribute("command") GeneralReport bean,
			BindingResult result, HttpSession session, Model model,HttpServletRequest request)
			throws JRException, ParseException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		List<Map<String, Object>> rowsChild1 = new ArrayList<Map<String, Object>>();

		String Role = (String) session.getAttribute("uRole");
		String portfolioName=request.getParameter("PORTFOLIO_NAME");
		

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
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSEMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSECHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("NBFCCHECKER", "Claim_Lodgement"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCCHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcCheckerHome");

		}
	
		String toDateF=(String) session.getAttribute("TDate");
		String fromDateF=(String) session.getAttribute("FDate");
		
	     
		
		Date toDate = new SimpleDateFormat("dd/MM/yyyy")
				.parse(toDateF);
		Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(fromDateF);
		
	if(portfolioName !=null){
		session.setAttribute("portfolioName", portfolioName);
			rowsChild1 = exposureWiseGuaranteeDataService.getMliExposureDataBYPortfoioName(userId,portfolioName,Role, toDate,
					fromDate);
	}else{
		String sessionPortfolioName=(String) session.getAttribute("portfolioName");
		rowsChild1 = exposureWiseGuaranteeDataService.getMliExposureDataBYPortfoioName(userId,sessionPortfolioName,Role, toDate,
				fromDate);
	}
		
		if (!rowsChild1.isEmpty()) {
			model1.put("rowsChild1", rowsChild1);
			//session.setAttribute("exposuredata3", rowsChild1);
		} else {
			model1.put("noDataFound", "NO Data Found !!");
		}
		return new ModelAndView("ExposureWiseGuaranteepageinput", model1);
	}
	
	@RequestMapping(value = "/getDataByFileID", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView getDataByFileID(
			@ModelAttribute("command") GeneralReport bean,
			BindingResult result, HttpSession session, Model model,HttpServletRequest request)
			throws JRException, ParseException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		List<Map<String, Object>> rowsChild4 = new ArrayList<Map<String, Object>>();

		String Role = (String) session.getAttribute("uRole");
		String fileId=request.getParameter("file_Id");
		String download=request.getParameter("fileIdCount");


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
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSEMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSECHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("NBFCCHECKER", "Claim_Lodgement"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCCHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcCheckerHome");

		}
	
		String toDateF=(String) session.getAttribute("TDate");
		String fromDateF=(String) session.getAttribute("FDate");
	
		Date toDate = new SimpleDateFormat("dd/MM/yyyy")
				.parse(toDateF);
		Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(fromDateF);
		
		rowsChild4 = exposureWiseGuaranteeDataService.getMliExposureDataBYFileId(userId,fileId,Role, toDate,
				fromDate);
		
		if (!rowsChild4.isEmpty()) {
			model1.put("rowsChild4", rowsChild4);
			//session.setAttribute("exposuredata4", rowsChild4);
		} else {
			model1.put("noDataFound", "NO Data Found !!");
		}
		if((download !=null)&&(download.equalsIgnoreCase("excelFileIdCount"))){
			session.setAttribute("excelDataFileCount", rowsChild4);
			return new ModelAndView("redirect:/exposureReportDownload.html?value=filecount");
		}
		return new ModelAndView("ExposureWiseGuaranteepageinput", model1);
	}
	
	//method for getting perticular portfolio data by folioName .....added by ajeet 13-02-2020
	@RequestMapping(value = "/backfromfolionamedata", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView backfromfolionamedata(
			@ModelAttribute("command") GeneralReport bean,
			BindingResult result, HttpSession session, Model model,HttpServletRequest request)
			throws JRException, ParseException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		
		List<Map<String, Object>> rowsChild = new ArrayList<Map<String, Object>>();

		String Role = (String) session.getAttribute("uRole");
		//String portfolioName=request.getParameter("PORTFOLIO_NAME");
		

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
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSEMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSECHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("NBFCCHECKER", "Claim_Lodgement"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCCHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcCheckerHome");

		}
	
		String toDateF=(String) session.getAttribute("TDate");
		String fromDateF=(String) session.getAttribute("FDate");
		String EXPOSURE_ID=(String) session.getAttribute("EXPOSURE_ID");
		Date toDate = new SimpleDateFormat("dd/MM/yyyy")
				.parse(toDateF);
		Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(fromDateF);
		
		rowsChild = exposureWiseGuaranteeDataService.getMliExposureDataByMLIName(userId,EXPOSURE_ID,Role, toDate,
				fromDate);
		
		if (!rowsChild.isEmpty()) {
			model1.put("rowsChild", rowsChild);
		} else {
			model1.put("noDataFound", "NO Data Found !!");
		}
		return new ModelAndView("ExposureWiseGuaranteepageinput", model1);
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
/*
	@RequestMapping(value = "/getdownloadExcelByPortfolioName", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView getdownloadExcelByPortfolioName(
			@ModelAttribute("command") GeneralReport bean,
			BindingResult result, HttpSession session, Model model,HttpServletRequest request)
			throws JRException, ParseException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		List<Map<String, Object>> rowsChild1 = new ArrayList<Map<String, Object>>();

		String Role = (String) session.getAttribute("uRole");
		String portfolioName=request.getParameter("PORTFOLIO_NAME");
		String portfolioCount=request.getParameter("portfolioCount");
		
		
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
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSEMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSECHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList",
					userActivityService.getReport("NBFCCHECKER", "Claim_Lodgement"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCCHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcCheckerHome");

		}
	
		String toDateF=(String) session.getAttribute("TDate");
		String fromDateF=(String) session.getAttribute("FDate");
		
	     
		
		Date toDate = new SimpleDateFormat("dd/MM/yyyy")
				.parse(toDateF);
		Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(fromDateF);
		
			rowsChild1 = exposureWiseGuaranteeDataService.getExcelDataBYPortfoioName(userId,portfolioName,Role, toDate,
					fromDate);
	
		if (!rowsChild1.isEmpty()) {
			model1.put("rowsChild1", rowsChild1);
			//session.setAttribute("exposuredata3", rowsChild1);
		} else {
			model1.put("noDataFound", "NO Data Found !!");
		}
	
		if((portfolioCount !=null)&&(portfolioCount.equalsIgnoreCase("excelportfolioCount"))){
			session.setAttribute("excelDataPortfolioCount", rowsChild1);
			return new ModelAndView("redirect:/exposureReportDownload.html?value=portfolioCount");
		}
		return new ModelAndView("ExposureWiseGuaranteepageinput", model1);
	}*/
	
	@RequestMapping(value = "/exposureReportDownload", method = {
			RequestMethod.GET, RequestMethod.POST })
		public ModelAndView GETuploadedFileData(
			@ModelAttribute("command") GeneralReport bean,
			BindingResult result, String fileId, HttpSession session,
			Model model,HttpServletResponse response, HttpServletRequest request) throws JRException, ParseException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		String Role = (String) session.getAttribute("uRole");
		String value=request.getParameter("portfolioCount");
		

		try {

			OutputStream os = response.getOutputStream();

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
			String strDate = sdf.format(cal.getTime());

			String toDateF=(String) session.getAttribute("TDate");
			String fromDateF=(String) session.getAttribute("FDate");
			Date toDate = new SimpleDateFormat("dd/MM/yyyy")
			.parse(toDateF);
	Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(fromDateF);
	

			String contextPath1 = request.getSession(false).getServletContext().getRealPath("");
			String contextPath = PropertyLoader.changeToOSpath(contextPath1);

			System.out.println("contextPath1 :" + contextPath1);
			System.out.println("contextPath :" + contextPath);

			ArrayList<List> FILEDATAReport = new ArrayList<List>();
			if(value.equalsIgnoreCase("portfolioCount")){
				//list = (List<Map<String, Object>>) session.getAttribute("excelDataFileCount");
				FILEDATAReport = exposureWiseGuaranteeDataService.getGETuploadedFileData(userId,fileId,Role,toDate,fromDate);
				session.setAttribute("file_data", FILEDATAReport);
			}
			else if(value.equalsIgnoreCase("excelFileIdCount")){
				//list = (List<Map<String, Object>>) session.getAttribute("excelDataPortfolioCount");
				FILEDATAReport = exposureWiseGuaranteeDataService.getExposureDataBYFileId(userId, fileId, Role, toDate, fromDate);
				session.setAttribute("file_data", FILEDATAReport);
			}
		
			 ArrayList HeaderArrLst = (ArrayList) FILEDATAReport.get(0);
			
			int NoColumn = HeaderArrLst.size();

			System.out.println("NoColumn:" + NoColumn);

			byte[] b = generateEXL(FILEDATAReport,NoColumn,contextPath);	
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

			//Working now
			public byte[] generateEXL(ArrayList<List> fILEDATAReport,int No_Column, String contextPath) throws DocumentException,
			IOException {
						System.out.println("---generateEXL()---");
						StringBuffer strbuff = new StringBuffer();
						//System.out.println("ParamDataList:" + ParamDataList);
						ArrayList<String> rowDataLst = new ArrayList<String>();
						ArrayList<String> HeaderLst = (ArrayList) fILEDATAReport.get(0);
						ArrayList<ArrayList> RecordWiseLst = (ArrayList) fILEDATAReport.get(1);
						
						HSSFWorkbook workbook = new HSSFWorkbook();
						HSSFSheet sheet = workbook.createSheet("Data1");
						
						DataFormat format = workbook.createDataFormat();
						HSSFCellStyle style = workbook.createCellStyle();
						style.setDataFormat(format.getFormat("#,##0.00"));
						
						// #### Header List Wrinting
						Row row = sheet.createRow(0);	
						int hdcolnum = 0;
						for (String headerdata : HeaderLst) {
							Cell cell = row.createCell(hdcolnum);
							cell.setCellValue(headerdata);
							hdcolnum++;
						}
						
							int rownum = 1;
								for (ArrayList<String> RecordWiseLstObj : RecordWiseLst) {
										int colnum = 0;
										row = sheet.createRow(rownum);			
										for (String SingleRecordDataObj : RecordWiseLstObj) {
											Cell cell = row.createCell(colnum);
													cell.setCellValue(SingleRecordDataObj);
													colnum++;			
													rowDataLst.add(SingleRecordDataObj);
											
										}
										rownum++;
										System.out.println("rownum==="+rownum);
							
								}
					
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
						String strDate = sdf.format(cal.getTime());
						try {			
							FileOutputStream out = new FileOutputStream(new File(contextPath+ "\\Download\\DataCSVFile" + strDate + ".xls"));
							workbook.write(out);
							out.close();
							//System.out.println("Excel written successfully..");
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}		
						FileInputStream fis = new FileInputStream(contextPath+ "\\Download\\DataCSVFile" + strDate + ".xls");
						//System.out.println("9");
						byte b[];
						int x = fis.available();
						b = new byte[x];
						// System.out.println(" b size"+b.length);
						fis.read(b);		
						return b;
		}
}
