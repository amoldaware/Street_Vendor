package com.nbfc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nbfc.bean.ITPANSearchHistoryBean;
import com.nbfc.bean.JsonResponse2;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.model.District;
import com.nbfc.service.NPAService;
import com.nbfc.service.UserActivityService;
import com.nbfc.validation.EmployeeValidator;
import com.nbfc.validator.DataValidation;

@Controller
public class CGTMSEITPanSearchHistoryController {
	
	
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	NPAService npaService;
	@Autowired
	EmployeeValidator validator;
	String memberId=null;
	
	@RequestMapping(value = "/itpanSearchHistory", method = RequestMethod.GET)
	public ModelAndView itpanSearchHistory(
			@ModelAttribute("command") @Valid ITPANSearchHistoryBean Bean,
			BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		List<NPADetailsBean> npaList = null;
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		memberId = npaService.getMemberId(userId);
       //	System.out.println(memberId);
		npaList = npaService.getNPADetailsForApproval(userId,"NCR");
		 if (npaList != null) {
				model.put("dataListReturn", npaList);
			} else {
				//log.info("Throwing exception due to data not found in NPACheckerController class");
				model.put("dataListReturn", "");
			}

		 
		 model.put("guaranteelist",
					userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		 model.put("applicationList", userActivityService.getActivity(
					"CGTMSEMAKER", "Application_Processing"));
		 model.put("RPaymentList", userActivityService.getActivity(
					"CGTMSEMAKER", "Receipt_Payments"));
		 model.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSEMAKER", "Guarantee_Maintenance"));
			// end---------------------------------------------------------------------------------------

			// modelAct.put("actList",
			// userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
		 model.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		 model.put("actNameHome", "HOME");// Added by Say 31 Jan19
		 model.put("homePage", "cgtmseMakerHome");
		return new ModelAndView("itpanSearchHistory", model);

	}

	@RequestMapping(value = "/itPanSearchHistory", method = RequestMethod.POST)
	public ModelAndView npaDetailsData(
			@ModelAttribute("command") @Valid ITPANSearchHistoryBean Bean,
			BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		//validator.npaValidation(Bean, result);
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		String itPanVal=Bean.getItPan();
		
		
		
		//String bankId=Bean.getMLIID();
		//String memberId = npaService.getMemberId(userId);
		
		validator.itPanSearchValidation(Bean, result);
		if (result.hasErrors()) {
			 model.put("guaranteelist",
						userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			 model.put("applicationList", userActivityService.getActivity(
						"CGTMSEMAKER", "Application_Processing"));
			 model.put("RPaymentList", userActivityService.getActivity(
						"CGTMSEMAKER", "Receipt_Payments"));
			 model.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSEMAKER", "Guarantee_Maintenance"));
				// end---------------------------------------------------------------------------------------

				// modelAct.put("actList",
				// userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
			 model.put("repList",
						userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			 model.put("actNameHome", "HOME");// Added by Say 31 Jan19
			 model.put("homePage", "cgtmseMakerHome");
			 model.put("itPanVal", itPanVal);
			return new ModelAndView("itpanSearchHistory", model);
		}
		//npaDetailsBean=npaService.getCGPANDetails(cgpan,userId);
		List<ITPANSearchHistoryBean> beanMa = new ArrayList<ITPANSearchHistoryBean>();
		beanMa=npaService.getITpanSearchDetails(itPanVal);
		
		if (beanMa != null) {
			model.put("dataListReturn", beanMa);
		} else {
			model.put("dataListReturn", "");
		}
		 model.put("guaranteelist",
					userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		 model.put("applicationList", userActivityService.getActivity(
					"CGTMSEMAKER", "Application_Processing"));
		 model.put("RPaymentList", userActivityService.getActivity(
					"CGTMSEMAKER", "Receipt_Payments"));
		 model.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSEMAKER", "Guarantee_Maintenance"));
			// end---------------------------------------------------------------------------------------

			// modelAct.put("actList",
			// userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
		 model.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		 model.put("actNameHome", "HOME");// Added by Say 31 Jan19
		 model.put("homePage", "cgtmseMakerHome");
		 model.put("itPanVal", itPanVal);
		return new ModelAndView("itpanSearchHistory", model);

	}
	
	@RequestMapping(value = "/searchItpan", method = RequestMethod.GET)
	public ModelAndView searchItpan(
			@ModelAttribute("command") @Valid ITPANSearchHistoryBean Bean,
			BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		List<NPADetailsBean> npaList = null;
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		memberId = npaService.getMemberId(userId);
       //	System.out.println(memberId);
		npaList = npaService.getNPADetailsForApproval(userId,"NCR");
		 if (npaList != null) {
				model.put("dataListReturn", npaList);
			} else {
				//log.info("Throwing exception due to data not found in NPACheckerController class");
				model.put("dataListReturn", "");
			}
		 String Role=(String) session.getAttribute("uRole");
		  //nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);
			if(Role.equals("NCHECKER")){
		 
		 model.put("adminlist",
					userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
		 model.put("guaranteelist",
					userActivityService.getActivity("NBFCCHECKER", "Registration"));
		 model.put("applicationList",
					userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
		 model.put("RPaymentList",
					userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
		 model.put("repList",
					userActivityService.getReport("NBFCCHECKER", "User_Report"));
		 model.put("homePage", "nbfcCheckerHome");
		 model.put("CList",
					userActivityService.getReport("NBFCCHECKER", "Claim_Lodgement"));
		 model.put("CBMFList",userActivityService.getReport("NBFCCHECKER", "Claim_Bank_Mandate"));
		 model.put("GMaintainlist", userActivityService.getActivity(
					"NBFCCHECKERs", "Guarantee_Maintenance"));
			}else if(Role.equals("NMAKER")){
			 model.put("adminlist",
						userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			 model.put("guaranteelist",
						userActivityService.getActivity("NBFCMAKER", "Registration"));
			 model.put("applicationList",
						userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			 model.put("RPaymentList",
						userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			 model.put("repList",
						userActivityService.getReport("NBFCMAKER", "User_Report"));
			 model.put("homePage", "nbfcMakerHome");
			 model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
			 model.put("CList",
						userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));
			 model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
			}
			
		return new ModelAndView("searchItpan", model);

	}
	@RequestMapping(value = "/searchItpanHistory", method = RequestMethod.POST)
	public ModelAndView searchItpanHistory(
			@ModelAttribute("command") @Valid ITPANSearchHistoryBean Bean,
			BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		//validator.npaValidation(Bean, result);
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		String itPanVal=Bean.getItPan();
		String Role=(String) session.getAttribute("uRole");
		
		
		//String bankId=Bean.getMLIID();
		//String memberId = npaService.getMemberId(userId);
		
		validator.itPanSearchValidation(Bean, result);
		if (result.hasErrors()) {
			 
			  //nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);
				if(Role.equals("NCHECKER")){
					 model.put("CList",
								userActivityService.getReport("NBFCCHECKER", "Claim_Lodgement"));
			 model.put("adminlist",
						userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
			 model.put("guaranteelist",
						userActivityService.getActivity("NBFCCHECKER", "Registration"));
			 model.put("applicationList",
						userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
			 model.put("RPaymentList",
						userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
			 model.put("repList",
						userActivityService.getReport("NBFCCHECKER", "User_Report"));
			 model.put("CBMFList",userActivityService.getReport("NBFCCHECKER", "Claim_Bank_Mandate"));
			 model.put("homePage", "nbfcCheckerHome");
			 model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCCHECKERs", "Guarantee_Maintenance"));
				}else if(Role.equals("NMAKER")){
				 model.put("adminlist",
							userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				 model.put("guaranteelist",
							userActivityService.getActivity("NBFCMAKER", "Registration"));
				 model.put("applicationList",
							userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
				 model.put("RPaymentList",
							userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
				 model.put("repList",
							userActivityService.getReport("NBFCMAKER", "User_Report"));
				 model.put("homePage", "nbfcMakerHome");
				 model.put("GMaintainlist", userActivityService.getActivity(
							"NBFCMAKER", "Guarantee_Maintenance"));
				 model.put("CList",
							userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));
				 model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
				}
			return new ModelAndView("searchItpan", model);
		}
		//npaDetailsBean=npaService.getCGPANDetails(cgpan,userId);
		List<ITPANSearchHistoryBean> beanMa = new ArrayList<ITPANSearchHistoryBean>();
		beanMa=npaService.getITpanSearchDetails(itPanVal);
		
		if (beanMa != null) {
			model.put("dataListReturn", beanMa);
		} else {
			model.put("dataListReturn", "");
		}
		if(Role.equals("NCHECKER")){
			 
			 model.put("adminlist",
						userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
			 model.put("guaranteelist",
						userActivityService.getActivity("NBFCCHECKER", "Registration"));
			 model.put("applicationList",
						userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
			 model.put("RPaymentList",
						userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
			 model.put("repList",
						userActivityService.getReport("NBFCCHECKER", "User_Report"));
			 model.put("homePage", "nbfcCheckerHome");
			 model.put("CList",
						userActivityService.getReport("NBFCCHECKER", "Claim_Lodgement"));
			 model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCCHECKERs", "Guarantee_Maintenance"));
			 model.put("CBMFList",userActivityService.getReport("NBFCCHECKER", "Claim_Bank_Mandate"));
				}else if(Role.equals("NMAKER")){
				 model.put("adminlist",
							userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				 model.put("guaranteelist",
							userActivityService.getActivity("NBFCMAKER", "Registration"));
				 model.put("applicationList",
							userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
				 model.put("RPaymentList",
							userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
				 model.put("repList",
							userActivityService.getReport("NBFCMAKER", "User_Report"));
				 model.put("homePage", "nbfcMakerHome");
				 model.put("GMaintainlist", userActivityService.getActivity(
							"NBFCMAKER", "Guarantee_Maintenance"));
				 model.put("CList",
							userActivityService.getReport("NBFCMAKER", "Claim_Lodgement"));
				 model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
				}
		 model.put("itPanVal", itPanVal);
		return new ModelAndView("searchItpan", model);

	}
}
