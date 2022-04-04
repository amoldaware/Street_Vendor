package com.nbfc.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import com.ibm.icu.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
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
import com.nbfc.model.CGTMSEMakerForBatchApprovalUploadedBatchFile;
import com.nbfc.service.CGTMSEMakerForBatchApprovalUploadedBatchFileService;
import com.nbfc.service.UserActivityService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class CGTMSEMakerForBatchApprovalUploadedBatchFileCCRDetailsController {
	@Autowired
	private CGTMSEMakerForBatchApprovalUploadedBatchFileService cgtmseMakerForBatchApprovalUploadedBatchFileService;
	@Autowired
	UserActivityService userActivityService;
	static Logger log = Logger.getLogger(NBFCController.class.getName());
	                       
	@RequestMapping(value="cgtmseMakerForBatchApprovalUploadedBatchFileCCRDetailsRM" ,method = RequestMethod.GET)
	public ModelAndView showcgtMakerForBatchApprovalUploadedBatchFileInputForm(@ModelAttribute("command") CGTMSEMakerForBatchApprovalUploadedBatchFile cgtmseMakerForBatchApprovalUploadedBatchFile, 
			BindingResult result,HttpServletRequest request) {
		try{
			String fileId = null;
			String fileId1=null;
			String fileName=null;
			Integer quaterId = null;
			String shortName = null;
			Long noOfFile = null;
			String status = null;
			Long portfolioNo = null;
			String uploadedDate = null;
			String sanctionAmount=null;
			String fileN=null;
			Long noOfRecord=(long) 0;
			String quaterName=null;
			List<String> listObj1=new ArrayList<String>();
			List<Object> list1 = new ArrayList<Object>();
			Map<String, Object> modelAct = new HashMap<String, Object>();
			modelAct.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
			modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			modelAct.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
			 modelAct.put("repList",
						userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			modelAct.put("actName", userActivityService.getActivityName("CGTMSEMAKER","cgtmseMakerForBacthApprovalRM"));// Added by Say 31 Jan19
			 modelAct.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSEMAKER", "Guarantee_Maintenance"));
			modelAct.put("homePage", "cgtmseCheckerHome");
			ModelAndView model = new ModelAndView("cgtmseMakerForBatchApprovalUploadedBatchFileCCRDetails",modelAct);
			String NCAstatus="CCR";
			cgtmseMakerForBatchApprovalUploadedBatchFile.setStatus(NCAstatus);
			CGTMSEMakerForBatchApprovalUploadedBatchFile cgtmseMakerForBatchApprovalUploadedBatchFileObj2 = null;
			CGTMSEMakerForBatchApprovalUploadedBatchFile cgtmseMakerForBatchApprovalUploadedBatchFileObj3 = null;
			CGTMSEMakerForBatchApprovalUploadedBatchFile obj2=new CGTMSEMakerForBatchApprovalUploadedBatchFile();
			List<Object> listObj = cgtmseMakerForBatchApprovalUploadedBatchFileService.getUploadedBatchFileCCRDetails(cgtmseMakerForBatchApprovalUploadedBatchFile);
			if(listObj.size()!=0){
				Iterator<Object> itr1= listObj.iterator();
				while(itr1.hasNext()){
					Object[] obj1 = (Object[]) itr1.next();
					fileId=(String) obj1[0];
					fileN=fileId;
					quaterId=(Integer) obj1[1];
					shortName=(String) obj1[2];
					noOfFile=(Long) obj1[3];
					status=(String) obj1[4];
					portfolioNo= (Long) obj1[5];
					sanctionAmount= (String) obj1[6];
					noOfRecord=(Long) obj1[7];
					quaterName=(String) obj1[8];
					java.sql.Date NbfcUploadedDate=   (Date) obj1[9];
					SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");	
					String reportDate = df.format(NbfcUploadedDate);
					String nbfcExcelUploadtedDate=reportDate.substring(0, 10);
					String splitnbfcExcelUploadtedDate[] =nbfcExcelUploadtedDate.split("/");
					String formatedNbfcExcelUploadtedDate=splitnbfcExcelUploadtedDate[1]+"/"+splitnbfcExcelUploadtedDate[0]+"/"+splitnbfcExcelUploadtedDate[2];
					String fileNameSplit[]=fileId.split("/");
					String fileName1=fileNameSplit[7];
					fileName=fileName1.substring(0, 13);
					fileId1=fileName;
					CGTMSEMakerBatchUploadDetails cgtmseMakerBatchUploadDetailsObj=new CGTMSEMakerBatchUploadDetails();
					cgtmseMakerBatchUploadDetailsObj.setFilePath(fileId1);
					cgtmseMakerBatchUploadDetailsObj.setSubPortfolioNo(quaterId);;
					cgtmseMakerBatchUploadDetailsObj.setShortName(shortName);
					cgtmseMakerBatchUploadDetailsObj.setNoOfFile(noOfFile);
					cgtmseMakerBatchUploadDetailsObj.setStatus(status);
					cgtmseMakerBatchUploadDetailsObj.setPortfolioNo(portfolioNo);
					cgtmseMakerBatchUploadDetailsObj.setSanctionAmount(sanctionAmount);
					cgtmseMakerBatchUploadDetailsObj.setFileName(fileN);
					cgtmseMakerBatchUploadDetailsObj.setNoOfRecords(noOfRecord);
					cgtmseMakerBatchUploadDetailsObj.setPortfolioQuarter(quaterName);
					cgtmseMakerBatchUploadDetailsObj.setNbfcUploadedDate(formatedNbfcExcelUploadtedDate);
					list1.add(cgtmseMakerBatchUploadDetailsObj);
				}
		 		model.addObject("lists", list1);
				return model;
			}else{
				model.addObject("recordNotExitKey1", "Data Not Exits.");
				return model;
			}
	}catch(Exception e){
		System.out.println("Exception=="+e);
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