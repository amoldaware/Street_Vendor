package com.nbfc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.nbfc.bean.LoginBean;
import com.nbfc.helper.CGTMSECheckerBatchUploadsPendingForApprovalHelper;
import com.nbfc.model.CGTMSECheckerBatchUploadsPendingForApproval;
import com.nbfc.service.CGTMSECheckerBatchUploadsPendingForApprovalService;
import com.nbfc.service.UserActivityService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class CGTMSECheckerBatchUploadsPendingForApprovalController {

	@Autowired
	private CGTMSECheckerBatchUploadsPendingForApprovalService cgtmseCheckerBatchUploadsPendingForApprovalService;
	@Autowired
	UserActivityService userActivityService;
	CGTMSECheckerBatchUploadsPendingForApprovalHelper cgtmseCheckerBatchUploadsPendingForApprovalHelperObj = null;
	static Logger log = Logger.getLogger(NBFCController.class.getName());
	String fullFileName = null;
	Integer quaterNumber = 0;
	String shortName = null;
	Long noOfFiles = (long) 0;
	String status = null;
	Integer portfolioNumber = 0;
	String totalSanctionAmt = null;
	String fileName = null;
	String fileNameSplit1 = null;
	String fileId = null;
	String uploadedDate = null;
	String uploadedDate1 = null;
	Long totalNoOfRecordsUploadedInExcelFile = (long) 0;
	String quaterName = null;

	@RequestMapping(value = "cgtmseCheckerBatchUploadsPendingForApprovalRM", method = RequestMethod.GET)
	public ModelAndView showcgtmseCheckerBatchUploadsPendingForApprovalInputForm(@ModelAttribute("command") CGTMSECheckerBatchUploadsPendingForApproval cgtmseCheckerBatchUploadsPendingForApproval,BindingResult result) {
		try {
			String CMAStatus = "CMA";
			cgtmseCheckerBatchUploadsPendingForApproval.setSTATUS(CMAStatus);
			Map<String, Object> modelAct = new HashMap<String, Object>();
			modelAct.put("adminlist",userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
			modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSECHECKER", "Registration"));
			modelAct.put("applicationList",userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
			modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
			modelAct.put("actName", userActivityService.getActivityName("CGTMSECHECKER","cgtmseCheckerForBacthApprovalRM"));
			modelAct.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			modelAct.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSECHECKER", "Guarantee_Maintenance"));
			modelAct.put("CList",
					userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
			modelAct.put("CBMFList",userActivityService.getReport("CGTMSECHECKER", "Claim_Bank_Mandate"));
		 	modelAct.put("homePage","cgtmseCheckerHome");
			ModelAndView model = new ModelAndView("cgtmseCheckerBatchUploadsPendingForApproval",modelAct);
			CGTMSECheckerBatchUploadsPendingForApproval cgtmseCheckerBatchUploadsPendingForApprovalObj = null;
			List<CGTMSECheckerBatchUploadsPendingForApprovalHelper> list1=cgtmseCheckerBatchUploadsPendingForApprovalService.getUploadedData("CMA","");
			if (list1 != null) {
				model.addObject("lists", list1);
				return model;
			}else {
				model.addObject("recordNotExitKey2", "Data Not Exits.");
				return model;
			}
		} catch (Exception e) {
			System.out.println("Exception ==" + e);
		}
		return null;
	}
	@ExceptionHandler(CustomExceptionHandler.class)
	public ModelAndView handleCustomException(CustomExceptionHandler ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		
		ModelAndView model = new ModelAndView("customException",model1);
		model.addObject("customException", ex.getMessage());
		return model;
	}
	@RequestMapping(value="cgtmseCheckerApprovalBack", method = RequestMethod.POST)
	public ModelAndView cgtmsePendingbatchapprovalHome(@ModelAttribute("command") LoginBean loginBean,BindingResult result, Model model, HttpSession session) {
		return new ModelAndView("redirect:/cgtmseCheckerBatchUploadsPendingForApprovalRM.html");
	}
		
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		
		ModelAndView model = new ModelAndView("exception",model1);
		model.addObject("exception", ex.getCause());
		return model;
	}

	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithException(ArithmeticException ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		
		ModelAndView model = new ModelAndView("exception",model1);
		model.addObject("exception", ex.getMessage());
		return model;
	}

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullException(NullPointerException ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		
		ModelAndView model = new ModelAndView("exception",model1);
		model.addObject("exception", "Data is null");
		return model;
	}
}
