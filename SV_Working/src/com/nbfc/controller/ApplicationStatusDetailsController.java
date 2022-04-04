package com.nbfc.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.DocumentException;
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
import com.nbfc.helper.PropertyLoader;
import com.nbfc.helper.TableDetailBean;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETExposureDetails;
import com.nbfc.model.CGTMSEExposureMasterMLIName;
import com.nbfc.model.MLIName;
import com.nbfc.model.User;
import com.nbfc.model.UserInfo;
import com.nbfc.service.ASFGenerationBulkUploadService;
import com.nbfc.service.UserActivityService;


import com.nbfc.bean.ApplicationStatusDetailsBean;
import com.nbfc.bean.CGPANDetailsReportBean;
import com.nbfc.bean.CGTMSEExposureMasterBean;
import com.nbfc.bean.MLIRegBean;
import com.nbfc.exception.NBFCException;
import com.nbfc.helper.NBFCValidator;
import com.nbfc.model.BankDetails;
import com.nbfc.model.District;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.ApplicationStatusService;
import com.nbfc.service.CGTMSECreateExposureLimitMakerService;
import com.nbfc.service.DistrictService;
import com.nbfc.service.LoginService;
import com.nbfc.service.MLIDetailsService;
import com.nbfc.service.MLIRegService;
import com.nbfc.service.SanctionDetailService;
import com.nbfc.service.StateService;

import com.nbfc.validation.EmployeeValidator;


@Controller
public class ApplicationStatusDetailsController {

	@Autowired
	LoginService lofinService;
	@Autowired
	ApplicationStatusService applicationStatusService;

	@Autowired
	MLIDetailsService mliDetailsService;
	@Autowired
	DistrictService districtService;
	@Autowired
	MLIRegService mliRegService;
	@Autowired
	StateService stateService;
	@Autowired
	EmployeeValidator validator;

	static Logger log = Logger.getLogger(NBFCController.class.getName());
	List<BankDetails> bankDetails = new ArrayList<BankDetails>();
	List<Integer> bankId = new ArrayList<Integer>();
	List<Integer> bankBranchId = new ArrayList<Integer>();
	List<Integer> bankZonId = new ArrayList<Integer>();
	String bankBRID = null;
	String bankBRIDTest = null;
	String mliID = null;

	@Autowired
	private SanctionDetailService sanctionDetailService;

	@Autowired
	UserActivityService userActivityService;
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;
	ModelAndView modelView;
	String userId = null;
	public static final int BUFFER_SIZE = 4096;
	@Autowired
	private CGTMSECreateExposureLimitMakerService cgtmseExposureMasterMakerService;
	@Autowired
	CGTMSECreateExposureLimitMakerService cgtmscCreateExposureLimitMakerService;
	ModelAndView modelAndViewObj;
	private List<CGTMSEExposureMasterBean> userList = new ArrayList<CGTMSEExposureMasterBean>();
	String portFiloName = null;
	NBFCValidator nbfcValidator = new NBFCValidator();
	@Value("${downloadFileDirPath}")
	private String downloadFileDirPath;
	@Value("${ExposureDetailsdownloadFileName}")
	private String downloadFileName;

	List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();

	@RequestMapping(value = "/appstatus", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView cgpanDetailReport(
			@ModelAttribute("command") ApplicationStatusDetailsBean bean,
			BindingResult result, HttpSession session, Model model)
			throws JRException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		String Role = (String) session.getAttribute("uRole");
		// nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);
		if (Role.equals("CMAKER")) {
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

			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "Claim_Bank_Mandate"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSECHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList", userActivityService.getReport("NBFCMAKER",
					"Claim_Lodgement"));
			model1.put("CBMFList", userActivityService.getReport("NBFCMAKER",
					"Claim_Bank_Mandate"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
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
			model1.put("CList", userActivityService.getReport("NBFCCHECKER",
					"Claim_Lodgement"));
			model1.put("CBMFList", userActivityService.getReport("NBFCCHECKER",
					"Claim_Bank_Mandate"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCCHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcCheckerHome");

		}
		return new ModelAndView("appStatus", model1);
		// return null;
	}

	@RequestMapping(value = "/searchappstatus", method = RequestMethod.POST)
	public ModelAndView searchAppStatus(
			@ModelAttribute("command") ApplicationStatusDetailsBean bean,
			BindingResult result, HttpSession session, Model model)
			throws JRException, ParseException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		String Role = (String) session.getAttribute("uRole");
		// nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);

		validator.appDetailsValidate(bean, result);
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
				model1.put("CList", userActivityService.getReport(
						"CGTMSECHECKER", "Claim_Lodgement"));
				model1.put("repList", userActivityService.getReport(
						"CGTMSECHECKER", "User_Report"));
				model1.put("CBMFList", userActivityService.getReport(
						"CGTMSECHECKER", "Claim_Bank_Mandate"));
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
				model1.put("CList", userActivityService.getReport("NBFCMAKER",
						"Claim_Lodgement"));
				model1.put("CBMFList", userActivityService.getActivity(
						"NBFCMAKER", "Claim_Bank_Mandate"));
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
				model1.put("CList", userActivityService.getReport(
						"NBFCCHECKER", "Claim_Lodgement"));
				model1.put("CBMFList", userActivityService.getReport(
						"NBFCCHECKER", "Claim_Bank_Mandate"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("NBFCCHECKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "nbfcCheckerHome");

			}
			return new ModelAndView("appStatus", model1);
		}

		if (Role.equals("CMAKER")) {
			//userId = "ADMIN";
			userId = (String) session.getAttribute("userId");
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
			//userId = "ADMIN";
			userId = (String) session.getAttribute("userId");
			model1.put("adminlist", userActivityService.getActivity(
					"CGTMSECHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"CGTMSECHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"CGTMSECHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"CGTMSECHECKER", "Receipt_Payments"));
			model1.put("CList", userActivityService.getReport("CGTMSECHECKER",
					"Claim_Lodgement"));
			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
			model1.put("CBMFList", userActivityService.getReport(
					"CGTMSECHECKER", "Claim_Bank_Mandate"));
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
			model1.put("CList", userActivityService.getReport("NBFCMAKER",
					"Claim_Lodgement"));
			model1.put("CBMFList", userActivityService.getReport("NBFCMAKER",
					"Claim_Bank_Mandate"));
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
			model1.put("CList", userActivityService.getReport("NBFCCHECKER",
					"Claim_Lodgement"));
			model1.put("CBMFList", userActivityService.getReport("NBFCCHECKER",
					"Claim_Bank_Mandate"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCCHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcCheckerHome");

		}
		String toDateF = bean.getToDate();
		String fromDateF = bean.getFromDate();
		session.setAttribute("FDate", fromDateF);
		session.setAttribute("TDate", toDateF);
		session.setAttribute("AppStatus", bean.getAppStatus());

		Date toDate = new SimpleDateFormat("dd/MM/yyyy")
				.parse(bean.getToDate());
		Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(bean
				.getFromDate());
		rows = applicationStatusService.getApplicationStatus(userId, toDate,
				fromDate, bean.getAppStatus());
		if (!rows.isEmpty()) {
			model1.put("rows", rows);
		} else {
			model1.put("noDataFound", "NO Data Found !!");
		}

		return new ModelAndView("appStatus", model1);
		// return null;
	}

	@RequestMapping(value = "/uploadedFileDataApplicationHistory", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView uploadedFileDataApplicationHistory(
			@ModelAttribute("command") ApplicationStatusDetailsBean bean,
			BindingResult result, String fileId, HttpSession session,
			Model model) throws JRException, ParseException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		String Role = (String) session.getAttribute("uRole");
		// nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);

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
			model1.put("CList", userActivityService.getReport("CGTMSECHECKER",
					"Claim_Lodgement"));
			model1.put("CBMFList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "Claim_Bank_Mandate"));
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
			model1.put("CList", userActivityService.getReport("NBFCMAKER",
					"Claim_Lodgement"));
			model1.put("CBMFList", userActivityService.getReport("NBFCMAKER",
					"Claim_Bank_Mandate"));
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
			model1.put("CList", userActivityService.getReport("NBFCCHECKER",
					"Claim_Lodgement"));
			model1.put("CBMFList", userActivityService.getReport("NBFCCHECKER",
					"Claim_Bank_Mandate"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCCHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcCheckerHome");

		}

		// Date toDate=new
		// SimpleDateFormat("dd/MM/yyyy").parse(bean.getToDate());
		// Date fromDate=new
		// SimpleDateFormat("dd/MM/yyyy").parse(bean.getFromDate());
		rows = applicationStatusService.getFileData(fileId);
		String toDateF = bean.getToDate();
		String fromDateF = bean.getFromDate();
		bean.setToDate(toDateF);
		bean.setFromDate(fromDateF);
		bean.setAppStatus(bean.getAppStatus());
		bean.setFILE_ID(fileId);
		session.setAttribute("FILE_ID", fileId);
		// System.out.println("-----------------"+fileId);
		model1.put("rows", rows);
		return new ModelAndView("appStatus", model1);
		// return null;
	}

	@RequestMapping(value = "/loanAcctNoData", method = RequestMethod.GET)
	public ModelAndView getBorworDetails(
			@ModelAttribute("command") ApplicationStatusDetailsBean bean,
			BindingResult result, String fileId, HttpSession session,
			Model model) throws JRException, ParseException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		String Role = (String) session.getAttribute("uRole");
		ApplicationStatusDetailsBean appBean;
		// nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);

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
			model1.put("CList", userActivityService.getReport("CGTMSECHECKER",
					"Claim_Lodgement"));
			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
			model1.put("CBMFList", userActivityService.getReport(
					"CGTMSECHECKER", "Claim_Bank_Mandate"));
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
			model1.put("CList", userActivityService.getReport("NBFCMAKER",
					"Claim_Lodgement"));
			model1.put("CBMFList", userActivityService.getReport("NBFCMAKER",
					"Claim_Bank_Mandate"));
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
			model1.put("CList", userActivityService.getReport("NBFCCHECKER",
					"Claim_Lodgement"));
			model1.put("CBMFList", userActivityService.getReport("NBFCCHECKER",
					"Claim_Bank_Mandate"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCCHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcCheckerHome");

		}

		// Date toDate=new
		// SimpleDateFormat("dd/MM/yyyy").parse(bean.getToDate());
		// Date fromDate=new
		// SimpleDateFormat("dd/MM/yyyy").parse(bean.getFromDate());
		appBean = applicationStatusService.getapplicationDetails(fileId);
		model1.put("applicationDetails", appBean);
		return new ModelAndView("appStatus", model1);
		// return null;
	}

	@RequestMapping(value = "/GETuploadedFileData", method = {
	RequestMethod.GET, RequestMethod.POST })
public ModelAndView GETuploadedFileData(
	@ModelAttribute("command") ApplicationStatusDetailsBean bean,
	BindingResult result, String fileId, HttpSession session,
	Model model,HttpServletResponse response, HttpServletRequest request) throws JRException, ParseException {
Map<String, Object> model1 = new HashMap<String, Object>();
String Role = (String) session.getAttribute("uRole");
// nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);

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

	ArrayList<List> FILEDATAReport = new ArrayList<List>();
	
	FILEDATAReport = applicationStatusService.getGETuploadedFileData(fileId);
	session.setAttribute("file_data", FILEDATAReport);
	
	
	
	//List<Map<String, Object>> list = (List<Map<String, Object>>) session.getAttribute("file_data");
	// System.out.println("@@@@@@@@@@@@ClmDataList:" + ClmDataList);
	 ArrayList HeaderArrLst = (ArrayList) FILEDATAReport.get(0);
	//ArrayList<Object> HeaderArrLst = new ArrayList<Object>();

	
	
	
	
	
/*	String key = null;
	for (Map<String, Object> ClmDataList : list) {
		for (Map.Entry<String, Object> entry : ClmDataList.entrySet()) {
			// rowhead = sheet.createRow((short) 0);
			key = entry.getKey();
			// Object value = entry.getValue();
			System.out.println("ClmDataList key==" + key);
			HeaderArrLst.add(key);
			
		}
		break;
	}*/
	// System.out.println("@@@@@@@@@@@@HeaderArrLst:" + HeaderArrLst);
	int NoColumn = HeaderArrLst.size();

	System.out.println("NoColumn:" + NoColumn);

	// if (fileType.equals("CSVType")) {
	//byte[] b = accountHistoryGenerateCSV(list, NoColumn, contextPath);
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
				// #### Header List Wrinting
				
				
				// #### Data List Writing
				//Existing below Code is Modified By Parmanand 05-Feb-2020 Start
					int rownum = 1;
						for (ArrayList<String> RecordWiseLstObj : RecordWiseLst) {
								int colnum = 0;
								row = sheet.createRow(rownum);			
								for (String SingleRecordDataObj : RecordWiseLstObj) {
									Cell cell = row.createCell(colnum);
//										if(colnum==5||colnum==6||colnum==7||colnum==8||colnum==9||colnum==10||colnum==11||colnum==12||colnum==13||colnum==20||colnum==22||colnum==23){
//											if(SingleRecordDataObj!=null && SingleRecordDataObj.length()>0 ){
//												//To set the excel sheet cell as from strign to number
//												cell.setCellType(cell.CELL_TYPE_NUMERIC);
//												cell.setCellValue(Double.parseDouble(SingleRecordDataObj));
//												colnum++;			
//												rowDataLst.add(SingleRecordDataObj);
//											}else{
//												double defaultDVal=0.00;
//												cell.setCellValue(defaultDVal);
//												colnum++;			
//												rowDataLst.add(SingleRecordDataObj);
//											}
//										}else{
											//cell.setCellType(cell.CELL_TYPE_STRING);
											cell.setCellValue(SingleRecordDataObj);
											colnum++;			
											rowDataLst.add(SingleRecordDataObj);
									//}
								}
								rownum++;
								System.out.println("rownum==="+rownum);
					
						}
				//Existing above Code is Modified By Parmanand 05-Feb-2020 End
				// #### Data List Writing
				
				
				 
				
				
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
 File genfile = new File("D:\\GenerateFiles\\SampleFile" + strDate+".csv");
//File genfile = new File(contextPath + "\\Download\\DataCSVFile" + strDate + ".csv");

// System.out.println("6");
output = new BufferedWriter(new FileWriter(genfile));
output.write(strbuff.toString());
// System.out.println("7");
output.flush();
output.close();
// System.out.println("8");

// ##
FileInputStream fis = new FileInputStream("D:\\GenerateFiles\\SampleFile" + strDate+ ".csv");
//FileInputStream fis = new FileInputStream(contextPath + "\\Download\\DataCSVFile" + strDate + ".csv");

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
