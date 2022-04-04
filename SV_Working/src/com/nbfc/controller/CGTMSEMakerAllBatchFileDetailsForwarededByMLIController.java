package com.nbfc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.nbfc.model.CGTMSEMakerAllBatchFileDetailsForwarededByMLI;
import com.nbfc.service.CGTMSEMakerAllBatchFileDetailsForwarededByMLIService;
import com.nbfc.service.UserActivityService;
import com.raistudies.domain.CustomExceptionHandler;


@Controller
public class CGTMSEMakerAllBatchFileDetailsForwarededByMLIController {
	List<Integer> portFolioNoObj=new ArrayList<Integer>();
	@Autowired
	CGTMSEMakerAllBatchFileDetailsForwarededByMLIService cgtmseMakerAllBatchFileDetailsForwarededByMLIService;
	@Autowired
	UserActivityService userActivityService;
	static Logger log = Logger.getLogger(NBFCController.class.getName());
		
	public CGTMSEMakerAllBatchFileDetailsForwarededByMLIController(){
	}
	
	//This method will call for GET Request with name of url of jsp sanctionDetailsRM
 	@RequestMapping(value="cgtmseMakerAllBatchFileDetailsForwarededByMLIRM" ,method = RequestMethod.GET)
	public ModelAndView showcgtmseMakerAllBatchFileDetailsForwarededByMLIInputForm(@ModelAttribute("command") CGTMSEMakerAllBatchFileDetailsForwarededByMLI cgtmseMakerAllBatchFileDetailsForwarededByMLI,BindingResult result,HttpServletRequest request,@RequestParam("uploadedBatchId") Integer uploadedBatchId,@RequestParam("uploadedRecordOfBatch") Integer uploadedRecordOfBatch,@RequestParam("status") String status) {
	 	String status1=status;
	 	cgtmseMakerAllBatchFileDetailsForwarededByMLI.setSubPortfolioNo(uploadedBatchId);
	 	cgtmseMakerAllBatchFileDetailsForwarededByMLI.setSTATUS(status1);
	 	Map<String, Object> modelAct = new HashMap<String, Object>();
		modelAct.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		modelAct.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		modelAct.put("repList",userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		modelAct.put("GMaintainlist", userActivityService.getActivity("CGTMSEMAKER", "Guarantee_Maintenance"));
		modelAct.put("actName", userActivityService.getActivityName("CGTMSEMAKER","cgtmseMakerForBacthApprovalRM"));
		modelAct.put("homePage", "cgtmseMakerHome");
	 	ModelAndView model = new ModelAndView("cgtmseMakerAllBatchFileDetailsForwarededByMLI",modelAct);
 		List<CGTMSEMakerAllBatchFileDetailsForwarededByMLI> list1 = new ArrayList<CGTMSEMakerAllBatchFileDetailsForwarededByMLI>();
	 	CGTMSEMakerAllBatchFileDetailsForwarededByMLI cgtmseMakerAllBatchFileDetailsForwarededByMLIObj = null;
		List<CGTMSEMakerAllBatchFileDetailsForwarededByMLI> listObj = cgtmseMakerAllBatchFileDetailsForwarededByMLIService.getIndividualBatchDetails(cgtmseMakerAllBatchFileDetailsForwarededByMLI);
		if(listObj.size()!=0){
			Iterator<CGTMSEMakerAllBatchFileDetailsForwarededByMLI> itr1= listObj.iterator();
			while(itr1.hasNext()){
				CGTMSEMakerAllBatchFileDetailsForwarededByMLI cgtmseMakerAllBatchFileDetailsForwarededByMLIobj=(CGTMSEMakerAllBatchFileDetailsForwarededByMLI)itr1.next();
				Date dateofUpload=cgtmseMakerAllBatchFileDetailsForwarededByMLIobj.getDATEOFUPLOAD();
				Integer PortfolioNumber=cgtmseMakerAllBatchFileDetailsForwarededByMLIobj.getPORTFOLIONO();
				String sanctionAmt=cgtmseMakerAllBatchFileDetailsForwarededByMLIobj.getTOTALSANCTIONEDAMOUNT();
				String status2=cgtmseMakerAllBatchFileDetailsForwarededByMLIobj.getSTATUS();
				cgtmseMakerAllBatchFileDetailsForwarededByMLIObj =new CGTMSEMakerAllBatchFileDetailsForwarededByMLI();
				cgtmseMakerAllBatchFileDetailsForwarededByMLIObj.setDATEOFUPLOAD(dateofUpload);
				cgtmseMakerAllBatchFileDetailsForwarededByMLIObj.setPORTFOLIONO(PortfolioNumber);;
				cgtmseMakerAllBatchFileDetailsForwarededByMLIObj.setTOTALSANCTIONEDAMOUNT(sanctionAmt);
				cgtmseMakerAllBatchFileDetailsForwarededByMLIObj.setSTATUS(status2);
				list1.add(cgtmseMakerAllBatchFileDetailsForwarededByMLIObj);
			}
			model.addObject("lists", list1);
			model.addObject("uploadedBatchIdKey", uploadedBatchId);
			model.addObject("uploadedRecordOfBatchKey", uploadedRecordOfBatch);
			return model;
		}else{
			model.addObject("recordNotExitKey", "Record Not Exit In DB.!");
			return model;
		}
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
