package com.nbfc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;
import com.nbfc.bean.LoginBean;
import com.nbfc.model.CGTMSECheckerForBatchApprovalGetStatus;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.CGTMSECheckerForBatchApprovalService;
import com.nbfc.service.LoginService;
import com.nbfc.service.MLIValidatorService;
import com.nbfc.service.UserActivityService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class CGTMSECheckerForBatchApprovalController {
	
	@Autowired
	private CGTMSECheckerForBatchApprovalService cgtmseCheckerForBatchApprovalService;
	@Autowired
	MLIValidatorService mliValidatorService;
    @Autowired
    LoginService lofinService;
    @Autowired
  	UserActivityService userActivityService;
    
    UserPerivilegeDetails userPri;
    NBFCPrivilegeMaster userPrvMst;
    ModelAndView model;
    static Logger log = Logger.getLogger(NBFCController.class.getName());
	@RequestMapping(value="cgtmseCheckerForBacthApprovalRM" ,method = RequestMethod.GET)
	public ModelAndView showcgtmseCheckerForBacthApprovalInputForm(@ModelAttribute("command") CGTMSECheckerForBatchApprovalGetStatus cgtmseCheckerForBatchApprovalGetStatus, 
			BindingResult result,HttpServletRequest request,HttpSession session) {
		String statusCMA=null;
		Long noOfUploadedExcelFile=(long) 0;
		Integer counterNOfUploadedExcelFile=0;
		Long noOfUploadedExcelFileCount=(long) 0;
		int statusCMACount=0;
		Map<String, Object> modelAct = new HashMap<String, Object>();
	 	String userId=(String)session.getAttribute("userId");
		if(userId!=null){
			userPri=lofinService.getUserPrivlageDtl(userId, "A");
			userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
			if(userPrvMst.getPrvCreatedModifiedBy().equals("CCHECKER")){
				modelAct.put("adminlist",userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
				modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSECHECKER", "Registration"));
				modelAct.put("applicationList",userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
				modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
				modelAct.put("actName", userActivityService.getActivityName("CGTMSECHECKER","cgtmseCheckerForBacthApprovalRM"));
				modelAct.put("repList",
						userActivityService.getReport("CGTMSEMAKER", "User_Report"));
				modelAct.put("homePage","cgtmseCheckerHome");
				modelAct.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSECHECKER", "Guarantee_Maintenance"));
				modelAct.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
				
				model = new ModelAndView("cgtmseCheckerForBatchApproval",modelAct); 
		 }else{
			 	model = new ModelAndView("redirect:/SVLogin.html");
		 }
		 }else{
			 	model = new ModelAndView("redirect:/SVLogin.html");
		 }
 		List<Object> list1 = new ArrayList<Object>();
 		String statusCMA1="CMA";
 		cgtmseCheckerForBatchApprovalGetStatus.setStatus(statusCMA1);
 		int counter=0;
 		Long noOfFiles=(long) 0;
		List<Object> listObj = cgtmseCheckerForBatchApprovalService.getCMAStatusCount(cgtmseCheckerForBatchApprovalGetStatus);
		if(listObj.size()!=0){
			Iterator<Object> itr1= listObj.iterator();
			while(itr1.hasNext()){
				Object[] obj1 = (Object[]) itr1.next();
				noOfFiles=(Long) obj1[1];
				noOfUploadedExcelFile=(Long) obj1[3];
				counterNOfUploadedExcelFile++;
			}
			model.addObject("statusCMACountKey", counterNOfUploadedExcelFile);
			model.addObject("statusCMAKey", statusCMA1);
			return model;
		}else{
			int noOfFiles1=0;
			int counterNOfUploadedExcelFile1=0;
			model.addObject("recordNotExitKey3", "Record Not Exits In DB !");
			model.addObject("statusCMACountKey", counterNOfUploadedExcelFile);
			return model;
		}
	}
	@RequestMapping(value="cgtmseCheckerHomeBack1", method = RequestMethod.POST)
	public ModelAndView cgtmsePendingbatchapprovalHome(@ModelAttribute("command") LoginBean loginBean,BindingResult result, Model model, HttpSession session) {
		 Map<String, Object> modelAct = new HashMap<String, Object>();
		modelAct.put("adminlist",userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
		modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSECHECKER", "Registration"));
		modelAct.put("applicationList",userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
		modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
		modelAct.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		modelAct.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		modelAct.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		modelAct.put("homePage","cgtmseCheckerHome");
		return new ModelAndView("successCGTMSECHECKERPage",modelAct);
	}
		
	@RequestMapping(value="cgtmseCheckerHomeBack", method = RequestMethod.POST)
	public ModelAndView cgtmseCheckerHome(@ModelAttribute("command") LoginBean loginBean,BindingResult result, Model model, HttpSession session) {
		Map<String, Object> modelAct = new HashMap<String, Object>();
		modelAct.put("adminlist",userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
		modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSECHECKER", "Registration"));
		modelAct.put("applicationList",userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
		modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
		modelAct.put("repList",userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		modelAct.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		modelAct.put("homePage", "cgtmseCheckerHome");
		modelAct.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		return new ModelAndView("successCGTMSECHECKERPage", modelAct);
	}
	@ExceptionHandler(CustomExceptionHandler.class)
	public ModelAndView handleCustomException(CustomExceptionHandler ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		
		ModelAndView model = new ModelAndView("customException",model1);
		model.addObject("customException", ex.getMessage());
		return model;
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
