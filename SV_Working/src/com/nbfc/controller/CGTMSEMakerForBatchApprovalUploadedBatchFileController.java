package com.nbfc.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.nbfc.helper.CGTMSEMakerBatchUploadDetails;
import com.nbfc.model.CGTMSEMakerForBatchApprovalGetStatus;
import com.nbfc.model.CGTMSEMakerForBatchApprovalUploadedBatchFile;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.CGTMSEMakerForBatchApprovalGetStatusService;
import com.nbfc.service.CGTMSEMakerForBatchApprovalUploadedBatchFileService;
import com.nbfc.service.LoginService;
import com.nbfc.service.UserActivityService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class CGTMSEMakerForBatchApprovalUploadedBatchFileController {
	@Autowired
	private CGTMSEMakerForBatchApprovalUploadedBatchFileService cgtmseMakerForBatchApprovalUploadedBatchFileService;
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
	@RequestMapping(value="cgtmseMakerForBatchApprovalUploadedBatchFileRM" ,method = RequestMethod.GET)
	public ModelAndView showcgtMakerForBatchApprovalUploadedBatchFileInputForm(@ModelAttribute("command") CGTMSEMakerForBatchApprovalUploadedBatchFile cgtmseMakerForBatchApprovalUploadedBatchFile, 
			BindingResult result,HttpServletRequest request) {
		try{
			log.info("showcgtMakerForBatchApprovalUploadedBatchFileInputForm method called as part of  CGTMSEMakerForBatchApprovalUploadedBatchFileController.");
			Map<String, Object> modelAct = new HashMap<String, Object>();
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
			ModelAndView model = new ModelAndView("cgtmseMakerForBatchApprovalUploadedBatchFile",modelAct);
			String NCAstatus="NCA";	
			cgtmseMakerForBatchApprovalUploadedBatchFile.setStatus(NCAstatus);
			List<CGTMSEMakerBatchUploadDetails> list1=cgtmseMakerForBatchApprovalUploadedBatchFileService.getUploadedData("NCA","CCR");
			 if (list1 != null) {
				 model.addObject("lists", list1);
					return model;
				} else {
					model.addObject("recordNotExitKey2", "Data Not Exits.");
					return model;
				}
	}catch(Exception e){
		e.printStackTrace();
		log.info("Exception raised==="+e);
		System.out.println("Exception raised==="+e);
	}
		return null;
	
	}
	
	@RequestMapping(value="cgtmseMakerForBacthApprovalRMBack" ,method = RequestMethod.POST)
	public ModelAndView showcgtmseMakerForBacthApprovalInputForm(@ModelAttribute("command") CGTMSEMakerForBatchApprovalGetStatus cgtmseMakerForBatchApprovalGetStatus,BindingResult result,HttpServletRequest request,HttpSession session) {
 	try{
		String statusNCA=null;
		int statusNCACount=0;
		Integer subPortfolioDtlNo=0;
 		Object totalNoOfUploadedFile=0;
 		String status=null;
 		return model;
	}catch(Exception e){
		log.info("Exception =="+e);
	}
	return model;
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

