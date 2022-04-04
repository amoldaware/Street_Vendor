package com.nbfc.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.nbfc.model.CGTMSEMakerForBatchApprovalGetStatus;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.CGTMSEMakerForBatchApprovalGetStatusService;
import com.nbfc.service.LoginService;
import com.nbfc.service.UserActivityService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class CGTMSEMakerForBatchApprovalController {
		@Autowired
		private CGTMSEMakerForBatchApprovalGetStatusService cgtmseMakerForBatchApprovalGetStatusService;
		Long rejectedNoOfFileByCGTMSE=(long) 0;
		@Autowired
		LoginService lofinService;
		@Autowired
		UserActivityService userActivityService;
		ModelAndView model;
		UserPerivilegeDetails userPri;
	    NBFCPrivilegeMaster userPrvMst;
	    
	    static Logger log = Logger.getLogger(NBFCController.class.getName());
	    
		@RequestMapping(value="cgtmseMakerForBacthApprovalRM" ,method = RequestMethod.GET)
		public ModelAndView showcgtmseMakerForBacthApprovalInputForm(@ModelAttribute("command") CGTMSEMakerForBatchApprovalGetStatus cgtmseMakerForBatchApprovalGetStatus,BindingResult result,HttpServletRequest request,HttpSession session) {
			Integer totalNoOfUploadedFile=0;
			try{
				String statusNCA=null;
				int statusNCACount=0;
				Integer subPortfolioDtlNo=0;
		 		String status=null;
		 		Map<String, Object> modelAct = new HashMap<String, Object>();	
		 		//Get Record Based On  NCA status
		 		List<Object> list1 = new ArrayList<Object>();
		 		String status1="NCA";
		 		log.info("Status==="+status1);
		 		cgtmseMakerForBatchApprovalGetStatus.setStatus(status1);
		 		totalNoOfUploadedFile =cgtmseMakerForBatchApprovalGetStatusService.getNCAStatusCountBasedOnNCAStatus(cgtmseMakerForBatchApprovalGetStatus);
		 		String userId=(String)session.getAttribute("userId");
		 		if(userId!=null){
		 			userPri=lofinService.getUserPrivlageDtl(userId, "A");
		 			userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
		 			if(userPrvMst.getPrvCreatedModifiedBy().equals("CMAKER")){
		 				modelAct.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		 				modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		 				modelAct.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		 				modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		 				modelAct.put("actName",userActivityService.getActivityName("CGTMSEMAKER", "cgtmseMakerForBacthApprovalRM"));
		 				 modelAct.put("repList",
		 						userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		 				 modelAct.put("GMaintainlist", userActivityService.getActivity(
		 						"CGTMSEMAKER", "Guarantee_Maintenance"));
		 				modelAct.put("homePage", "cgtmseMakerHome");
	 					model = new ModelAndView("cgtmseMakerForBatchApproval",modelAct); 
		 			 }else{
		 				model = new ModelAndView("redirect:/SVLogin.html");
		 			 }
		 		}else{
		 			 	model = new ModelAndView("redirect:/SVLogin.html");
		 		}
 				model.addObject("countTotalNoOfUploadedFileKey", totalNoOfUploadedFile);
 				model.addObject("statusKey", status1);
 				return model;
		}catch(Exception e){
			e.printStackTrace();
		}
		return model;
	}
		
	@RequestMapping(value="cgtmseMakerHomeBack", method = RequestMethod.POST)
	public ModelAndView cgtmseMakerHome(@ModelAttribute("command")  LoginBean loginBean,BindingResult result,Model model,HttpSession session) {
		Map<String, Object> modelAct = new HashMap<String, Object>();
		modelAct.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		modelAct.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		 modelAct.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		 modelAct.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSEMAKER", "Guarantee_Maintenance"));
		modelAct.put("homePage","cgtmseMakerHome");
		return new ModelAndView("successCGTMSEMAKERPage",modelAct);
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

