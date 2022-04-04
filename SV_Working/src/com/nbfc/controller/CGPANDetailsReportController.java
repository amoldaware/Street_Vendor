package com.nbfc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.CGPANDetailsReportBean;
import com.nbfc.bean.ClaimLodgementBean;
import com.nbfc.bean.DanAllForCgtmseCheckerBean;
import com.nbfc.model.BankDetails;
import com.nbfc.model.CGTMSECheckerForBatchApprovalAndRejectionSumbission;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETExposureDetails;
import com.nbfc.model.CGTMSEExposureMasterMLIName;
import com.nbfc.model.FileUploadModel;
import com.nbfc.model.PortFolioDetailsInParentTBL;
import com.nbfc.service.CGPANDetailservice;
import com.nbfc.service.UserActivityService;
import com.nbfc.validation.EmployeeValidator;


@Controller
public class CGPANDetailsReportController {
	@Autowired
	CGPANDetailservice CgpanDetailservice;
	@Autowired
	EmployeeValidator validator;
	@Autowired
	UserActivityService userActivityService;
	
	@RequestMapping(value = "/cgpanDetailReport", method = RequestMethod.GET)
	public ModelAndView cgpanDetailReport(@ModelAttribute("command") CGPANDetailsReportBean bean,BindingResult result,HttpSession session,Model model) throws JRException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		String Role=(String) session.getAttribute("uRole");
	  //nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);
		if(Role.equals("CMAKER")){
			model1.put("guaranteelist", userActivityService.getActivity(
					"CGTMSEMAKER", "Registration"));
			model1.put("applicationList", userActivityService
					.getActivity("CGTMSEMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"CGTMSEMAKER", "Receipt_Payments"));
			model1.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSEMAKER", "Guarantee_Maintenance"));
			model1.put("repList", userActivityService.getReport(
					"CGTMSEMAKER", "User_Report"));
//			model1.put("actNameHome", userActivityService.getActivityName("CGTMSEMAKER",
//					"cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "cgtmseMakerHome");
			
		}else if(Role.equals("CCHECKER")){
			model1.put("adminlist", userActivityService.getActivity(
					"CGTMSECHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"CGTMSECHECKER", "Registration"));
			model1.put("applicationList", userActivityService
					.getActivity("CGTMSECHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"CGTMSECHECKER", "Receipt_Payments"));
			model1.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSECHECKER", "Guarantee_Maintenance"));
			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
			model1.put("CList",
					userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
//			model1.put("actNameHome", userActivityService.getActivityName("CGTMSECHECKER",
//					"cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "cgtmseCheckerHome");
			//return null;
		}else if(Role.equals("NMAKER")){
			// added by say 6 feb-----------------------
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
//			model1.put("actNameHome", userActivityService.getActivityName("NBFCMAKER",
//					"cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcMakerHome");

		}else if(Role.equals("NCHECKER")){
			model1.put("adminlist", userActivityService.getActivity(
					"NBFCCHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"NBFCCHECKER", "Registration"));
			model1.put("applicationList", userActivityService
					.getActivity("NBFCCHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"NBFCCHECKER", "Receipt_Payments"));
			model1.put("repList", userActivityService.getReport(
					"NBFCCHECKER", "User_Report"));
			model1.put("GMaintainlist", userActivityService.getActivity(
					"NBFCCHECKER", "Guarantee_Maintenance"));
			model1.put("CList",
					userActivityService.getReport("NBFCCHECKER", "Claim_Lodgement"));
//			model1.put("actNameHome", userActivityService.getActivityName("NBFCCHECKER",
//					"cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcCheckerHome");
			
		}
		List<CGPANDetailsReportBean> cgpanlist =new ArrayList<CGPANDetailsReportBean>();
		model1.put("cgpanlist", "cgpanlist");
		return new ModelAndView("cgpanDetails",model1);
		//return null;
	}
	
	@RequestMapping(value = "/cgpanDetailsData", method = RequestMethod.POST)
	public ModelAndView cgpanDetailsData(@ModelAttribute("command") CGPANDetailsReportBean bean,BindingResult result,Model model,String Cgpan,HttpSession session) throws JRException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		String Role=(String) session.getAttribute("uRole");
		validator.Cgpanvalidate(bean, result);
		if (result.hasErrors()) {
			if(Role.equals("CMAKER")){
				model1.put("guaranteelist", userActivityService.getActivity(
						"CGTMSEMAKER", "Registration"));
				model1.put("applicationList", userActivityService
						.getActivity("CGTMSEMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity(
						"CGTMSEMAKER", "Receipt_Payments"));
				model1.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSEMAKER", "Guarantee_Maintenance"));
				model1.put("repList", userActivityService.getReport(
						"CGTMSEMAKER", "User_Report"));
				
//				model1.put("actNameHome", userActivityService.getActivityName("CGTMSEMAKER",
//						"cgpanDetailReport"));// Added by Say 31 Jan19
				
				model1.put("homePage", "cgtmseMakerHome");
				
			}else if(Role.equals("CCHECKER")){
				model1.put("adminlist", userActivityService.getActivity(
						"CGTMSECHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity(
						"CGTMSECHECKER", "Registration"));
				model1.put("applicationList", userActivityService
						.getActivity("CGTMSECHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity(
						"CGTMSECHECKER", "Receipt_Payments"));
				model1.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSECHECKER", "Guarantee_Maintenance"));
				model1.put("repList", userActivityService.getReport(
						"CGTMSECHECKER", "User_Report"));
				model1.put("CList",
						userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
//				model1.put("actNameHome", userActivityService.getActivityName("CGTMSECHECKER",
//						"cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "cgtmseCheckerHome");
				//return null;
			}else if(Role.equals("NMAKER")){
				// added by say 6 feb-----------------------
				model1.put("adminlist", userActivityService.getActivity(
						"NBFCMAKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity(
						"NBFCMAKER", "Registration"));
				model1.put("applicationList", userActivityService
						.getActivity("NBFCMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity(
						"NBFCMAKER", "Receipt_Payments"));
				model1.put("GMaintainlist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
				model1.put("CList",
						userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));
//				model1.put("actNameHome", userActivityService.getActivityName("NBFCMAKER",
//						"cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("repList",
						userActivityService.getReport("NBFCMAKER", "User_Report"));
				model1.put("homePage", "nbfcMakerHome");

			}else if(Role.equals("NCHECKER")){
				model1.put("adminlist", userActivityService.getActivity(
						"NBFCCHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity(
						"NBFCCHECKER", "Registration"));
				model1.put("applicationList", userActivityService
						.getActivity("NBFCCHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity(
						"NBFCCHECKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport(
						"NBFCCHECKER", "User_Report"));
				model1.put("GMaintainlist", userActivityService.getActivity(
						"NBFCCHECKER", "Guarantee_Maintenance"));
				model1.put("CList",
						userActivityService.getReport("NBFCCHECKER", "Claim_Lodgement"));
//				 model1.put("actNameHome", userActivityService.getActivityName("NBFCCHECKER",
//						"cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "nbfcCheckerHome");
				
			}	
		return new ModelAndView("cgpanDetails",model1);	
		}
	
		List<CGPANDetailsReportBean> cgpanlist=CgpanDetailservice.getCgpanDetailsReport(Cgpan);
	
		model1.put("cgpenList", cgpanlist);
	if(Role.equals("CMAKER")){
		model1.put("guaranteelist", userActivityService.getActivity(
				"CGTMSEMAKER", "Registration"));
		model1.put("applicationList", userActivityService
				.getActivity("CGTMSEMAKER", "Application_Processing"));
		model1.put("RPaymentList", userActivityService.getActivity(
				"CGTMSEMAKER", "Receipt_Payments"));
		model1.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		model1.put("repList", userActivityService.getReport(
				"CGTMSEMAKER", "User_Report"));
//		model1.put("actNameHome", userActivityService.getActivityName("CGTMSEMAKER",
//				"cgpanDetailReport"));// Added by Say 31 Jan19
		model1.put("homePage", "cgtmseMakerHome");
		
	}else if(Role.equals("CCHECKER")){
		model1.put("adminlist", userActivityService.getActivity(
				"CGTMSECHECKER", "System_Admin"));
		model1.put("guaranteelist", userActivityService.getActivity(
				"CGTMSECHECKER", "Registration"));
		model1.put("applicationList", userActivityService
				.getActivity("CGTMSECHECKER", "Application_Processing"));
		model1.put("RPaymentList", userActivityService.getActivity(
				"CGTMSECHECKER", "Receipt_Payments"));
		model1.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		model1.put("repList", userActivityService.getReport(
				"CGTMSECHECKER", "User_Report"));
		model1.put("CList",
				userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
//		model1.put("actNameHome", userActivityService.getActivityName("CGTMSECHECKER",
//				"cgpanDetailReport"));// Added by Say 31 Jan19
		model1.put("homePage", "cgtmseCheckerHome");
		//return null;
	}else if(Role.equals("NMAKER")){
		// added by say 6 feb-----------------------
		model1.put("adminlist", userActivityService.getActivity(
				"NBFCMAKER", "System_Admin"));
		model1.put("guaranteelist", userActivityService.getActivity(
				"NBFCMAKER", "Registration"));
		model1.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));
		model1.put("applicationList", userActivityService
				.getActivity("NBFCMAKER", "Application_Processing"));
		model1.put("RPaymentList", userActivityService.getActivity(
				"NBFCMAKER", "Receipt_Payments"));
		model1.put("CList",
				userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));
//		model1.put("actNameHome", userActivityService.getActivityName("NBFCMAKER",
//				"cgpanDetailReport"));// Added by Say 31 Jan19
		model1.put("homePage", "nbfcMakerHome");

	}else if(Role.equals("NCHECKER")){
		model1.put("adminlist", userActivityService.getActivity(
				"NBFCCHECKER", "System_Admin"));
		model1.put("guaranteelist", userActivityService.getActivity(
				"NBFCCHECKER", "Registration"));
		model1.put("applicationList", userActivityService
				.getActivity("NBFCCHECKER", "Application_Processing"));
		model1.put("RPaymentList", userActivityService.getActivity(
				"NBFCCHECKER", "Receipt_Payments"));
		model1.put("repList", userActivityService.getReport(
				"NBFCCHECKER", "User_Report"));
		model1.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model1.put("CList",
				userActivityService.getReport("NBFCCHECKER", "Claim_Lodgement"));
//		model1.put("actNameHome", userActivityService.getActivityName("NBFCCHECKER",
//				"cgpanDetailReport"));// Added by Say 31 Jan19
		model1.put("homePage", "nbfcCheckerHome");
		
	}
	return new ModelAndView("cgpanDetails",model1);
}
}