package com.nbfc.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.CGTMSECreatedExposureLimitCheckerBean;
import com.nbfc.bean.CGTMSEModifiedExposureAuditMasterBean;
import com.nbfc.bean.CGTMSEModifiedExposureLimitCheckerBean;
import com.nbfc.bean.JsonResponse4;
import com.nbfc.common.utility.method.NBFCCommonUtility;
import com.nbfc.model.CGTMSEAuditExposureLimit;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETExposureDetails;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETMemberInfoDetails;
import com.nbfc.model.CGTMSEExposureMasterDetails;
import com.nbfc.model.CGTMSEMemberInfoDetails;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.CGTMSECreatedExposureLimitCheckerService;
import com.nbfc.service.CGTMSEModifiedExposureLimitCheckerService;
import com.nbfc.service.LoginService;
import com.nbfc.service.UserActivityService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class CGTMSEModifiedExposureLimitCheckerController {
	@Autowired
	private CGTMSECreatedExposureLimitCheckerService cgtmseCreatedExposureLimitCheckerService;
	private List<CGTMSEModifiedExposureAuditMasterBean> ModifyUserList = new ArrayList<CGTMSEModifiedExposureAuditMasterBean>();
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;
	ModelAndView modelView;
	String userId=null;
	String memBnkId=null;
	String memBrnId=null;
	String memZneId=null;
	Long oldExposureLimit=(long) 0;
	Date sanDate;
	Date esDate;
	Date fDate;
	Date tDate;
	String exposureSanctionDate;
	String fromDate ;
	String toDate ;
	Long eLimit=(long) 0;
	Long auditExposureLimit=(long) 0;
	String staus=null;
	String statusDescription=null;
	
	@Autowired
	private CGTMSEModifiedExposureLimitCheckerService cgtmseModifiedExposureLimitCheckerService;
	
	private List<CGTMSEModifiedExposureLimitCheckerBean> userList = new ArrayList<CGTMSEModifiedExposureLimitCheckerBean>();
	CGTMSEModifiedExposureLimitCheckerBean cgtmseModifiedExposureLimitCheckerBeanObj1=new CGTMSEModifiedExposureLimitCheckerBean();
	@RequestMapping(value = "showModifiedExposureLimitApprovalAndRejectionCheckerInputForm", method = RequestMethod.GET)
	public ModelAndView showModifiedExposureLimitApprovalAndRejectionCheckerInputForm(@ModelAttribute("command") CGTMSEModifiedExposureLimitCheckerBean cgtmseModifiedExposureLimitCheckerBean,BindingResult result,HttpSession session,Model modelObj) {
		try{
			userId=(String)session.getAttribute("userId");
			 if(userId!=null){
				userPri=lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				if(userPrvMst.getPrvCreatedModifiedBy().equals("CCHECKER")){
					modelView = new ModelAndView("cgtmseModifiedExposureLimitApprovalAndRejectionByChecker"); 
				}else{
					modelView = new ModelAndView("redirect:/SVLogin.html");
				}
			 }else{
				 modelView = new ModelAndView("redirect:/SVLogin.html");
			 }
			return modelView;
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return modelView;
	}
	
	//Get MLI Long in MLI DropDown in Page Onload
	@ModelAttribute("getMliModifiedExposureLimitCheckerLongName")
	public Map<String, String> getMliLongName() {
		Map<String, String> mapMliLongNameObj = new HashMap<String, String>();
		mapMliLongNameObj = cgtmseModifiedExposureLimitCheckerService.getMliLongNameInDropDown();
		return   mapMliLongNameObj;
	}
			
	//Get MLI Short Name on change of LongName Drop Down
	@RequestMapping(value = "/getMliModifiedExposureLimitCheckerShortName", method = RequestMethod.POST)
	public @ResponseBody JsonResponse4 getMliShortName(@ModelAttribute(value = "mliLName") CGTMSEModifiedExposureLimitCheckerBean cgtmseModifiedExposureLimitCheckerBeanBeanObj,
			BindingResult result,String mliLName, HttpSession session) {
			ArrayList arrayListObj2 = new ArrayList();
			cgtmseModifiedExposureLimitCheckerBeanBeanObj.setMliLongName(mliLName);
			arrayListObj2 = cgtmseModifiedExposureLimitCheckerService.getMliShortNameOnChangeOfMliLongName(cgtmseModifiedExposureLimitCheckerBeanBeanObj);
			Iterator itr = arrayListObj2.iterator();
			JsonResponse4 res = new JsonResponse4();
			CGTMSEModifiedExposureLimitCheckerBean s4 = new CGTMSEModifiedExposureLimitCheckerBean();
			String shortName = (String) itr.next();
			memBnkId=(String) itr.next();
			memBrnId=(String) itr.next();
			memZneId=(String) itr.next();
			//Aanand Store data in cgtmseExposureMasterMakerEditBeanObj1 object for use at the time of modify exposure
			cgtmseModifiedExposureLimitCheckerBeanObj1.setMemBnkId(memBnkId);
			cgtmseModifiedExposureLimitCheckerBeanObj1.setMemBrnId(memBrnId);
			cgtmseModifiedExposureLimitCheckerBeanObj1.setMemZneId(memZneId);
			cgtmseModifiedExposureLimitCheckerBeanObj1.setMakerId(userId);
			//SysDate or Maker Date
			Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
		    String sysDate = sdf.format(cal.getTime());
		    //Convert String into Date and Store into DB as Date data type
		  	SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
		    String sysCurrentDate = sysDate;
		    Date makerDate = null;
		    try {
		    	makerDate = formatter1.parse(sysCurrentDate);
		  	} catch (ParseException e) {
		  		e.printStackTrace();
		  	}
		  		
		    cgtmseModifiedExposureLimitCheckerBeanObj1.setMakerDate(makerDate);
			staus="CEMMA";
			statusDescription="Pending for approval";
			cgtmseModifiedExposureLimitCheckerBeanObj1.setStatus(staus);		
			cgtmseModifiedExposureLimitCheckerBeanObj1.setStatusDescription(statusDescription);
			
			ArrayList<String> arrayListObj3=new ArrayList();
			CGTMSEMemberInfoDetails cgtmseMemberInfoDetailsObj=new CGTMSEMemberInfoDetails();
			cgtmseMemberInfoDetailsObj.setMliShortName(shortName);
			CGTMSEExposureMasterDetails cgtmseExposureMasterDetailsObj=new CGTMSEExposureMasterDetails();
			cgtmseExposureMasterDetailsObj.setMemBnkId(memBnkId);
			cgtmseExposureMasterDetailsObj.setMemBrnId(memBrnId);
			cgtmseExposureMasterDetailsObj.setMemZneId(memZneId);
			
			CGTMSEMemberInfoDetails  cgtmseMemberInfoDetailsObj1=  cgtmseModifiedExposureLimitCheckerService.getMliMemberInfo(cgtmseMemberInfoDetailsObj);
			CGTMSEExposureMasterDetails cgtmseExposureMasterDetailsObj2=  cgtmseModifiedExposureLimitCheckerService.getMliExposureLimitInfo(cgtmseExposureMasterDetailsObj);
			sanDate=cgtmseExposureMasterDetailsObj2.getExposureSanctionDate();
			eLimit=cgtmseExposureMasterDetailsObj2.getExposureLimit();
			esDate=cgtmseExposureMasterDetailsObj2.getExposureSanctionDate();
			fDate=cgtmseExposureMasterDetailsObj2.getFromDate();
			tDate=cgtmseExposureMasterDetailsObj2.getToDate();
			//Convert date into String
			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			exposureSanctionDate = formatter.format(esDate);
			fromDate = formatter.format(fDate);
			toDate = formatter.format(tDate);
			//Conver Date into String
			oldExposureLimit=eLimit;
			cgtmseModifiedExposureLimitCheckerBeanObj1.setFromDate(fromDate);
			cgtmseModifiedExposureLimitCheckerBeanObj1.setToDate(toDate);
			cgtmseModifiedExposureLimitCheckerBeanObj1.setExposureSanctionDate(exposureSanctionDate);
			//Get Member Info Details
			s4.setMliShortName(shortName);
			s4.setMliExposureLimit(eLimit);
			s4.setMliDateOfSanctionOfExposure(exposureSanctionDate);
			s4.setFromDate(fromDate);
			s4.setToDate(toDate);
			s4.setGurantee_fee(cgtmseExposureMasterDetailsObj2.getGurantee_fee());
			s4.setPay_out_cap(cgtmseExposureMasterDetailsObj2.getPay_out_cap());
			s4.setGuranteeCoverage(cgtmseExposureMasterDetailsObj2.getGuranteeCoverage());
			//private String audGuranteeCoverage;
			userList.clear();
			userList.add(s4);
			res.setStatus("SUCCESS");
			res.setResult(userList);
			return res;
	}
			
	@RequestMapping(value = "/getMliCheckerShortName2", method = RequestMethod.GET)
	public ModelAndView getMliShortName(@ModelAttribute(value = "command") CGTMSECreatedExposureLimitCheckerBean cgtmseCreatedExposureLimitCheckerBean,BindingResult result, HttpSession session)	{
		String mliLName=cgtmseCreatedExposureLimitCheckerBean.getMliLongName();
		ArrayList arrayListObj2 = new ArrayList();
		arrayListObj2 = cgtmseCreatedExposureLimitCheckerService.getMliShortNameOnChangeOfMliLongName(cgtmseCreatedExposureLimitCheckerBean);
		Map<String, Object> model = new HashMap<String, Object>();
		Iterator itr = arrayListObj2.iterator();
		JsonResponse4 res = new JsonResponse4();
		CGTMSEModifiedExposureAuditMasterBean AuditExposureDetail = new CGTMSEModifiedExposureAuditMasterBean();
		String shortName = (String) itr.next();
		memBnkId = (String) itr.next();
		memBrnId = (String) itr.next();
		memZneId = (String) itr.next();
		/*ArrayList<String> arrayListObj3 = new ArrayList();*/
		CGTMSEExposureMasterCheckerGETMemberInfoDetails cgtmseExposureMasterCheckerGETMemberInfoDetailsObj = new CGTMSEExposureMasterCheckerGETMemberInfoDetails();
		cgtmseExposureMasterCheckerGETMemberInfoDetailsObj.setMliShortName(shortName);
		CGTMSEExposureMasterCheckerGETExposureDetails cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj = new CGTMSEExposureMasterCheckerGETExposureDetails();
		cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj.setMemBnkId(memBnkId);
		cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj.setMemBrnId(memBrnId);
		cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj.setMemZneId(memZneId);
		CGTMSEExposureMasterCheckerGETExposureDetails cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2 = cgtmseModifiedExposureLimitCheckerService.getExposureLimitInfo(cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj);
		/*-----Getting Data From Exposure Limit Table-----*/
		eLimit = cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getExposureLimit();
		Date esDate = cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getExposureSanctionDate();
		Date fDate = cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getFromDate();
		Date tDate = cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getToDate();
		String Remark = cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getRemarks();
		//Convert date into String
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String exposureSanctionDate = formatter.format(esDate);
		String fromDate = formatter.format(fDate);
		String toDate = formatter.format(tDate);
		/*-----Getting Data From Audit Exposure Limit-----*/
		CGTMSEAuditExposureLimit cgtmseAuditExposureLimitObj = new CGTMSEAuditExposureLimit();
		cgtmseAuditExposureLimitObj.setMemBnkId(memBnkId);
		cgtmseAuditExposureLimitObj.setMemBrnId(memBrnId);
		cgtmseAuditExposureLimitObj.setMemZneId(memZneId);
		CGTMSEAuditExposureLimit cgtmseAuditExposureLimitObj2 = cgtmseModifiedExposureLimitCheckerService.getAllExposureAuditLimitDetails(cgtmseAuditExposureLimitObj);
		auditExposureLimit = cgtmseAuditExposureLimitObj2.getExposureLimit();
		String auditRemark = cgtmseAuditExposureLimitObj2.getRemarks();
		NBFCCommonUtility obj4=new NBFCCommonUtility();
		AuditExposureDetail.setLongName(mliLName);
		AuditExposureDetail.setAuditLongName(mliLName);
		AuditExposureDetail.setShortName(shortName);
		AuditExposureDetail.setAuditShortName(shortName);
		AuditExposureDetail.setExposureLimit(eLimit);
		AuditExposureDetail.setAuditExposureLimit(auditExposureLimit);
		AuditExposureDetail.setDateOfSanctionOfExposure(exposureSanctionDate);
		String aesD=cgtmseAuditExposureLimitObj2.getExposureSanctionDate();
		String[] auditESDSplit=aesD.split("/");
		AuditExposureDetail.setAuditDateOfSanctionOfExposure(auditESDSplit[1]+"/"+auditESDSplit[0]+auditESDSplit[2]);
		AuditExposureDetail.setFromDate(fromDate);
		String afd=cgtmseAuditExposureLimitObj2.getFromDate();
		String[] auditFDSplit=afd.split("/");
		AuditExposureDetail.setAuditFromDate(auditFDSplit[1]+"/"+auditFDSplit[0]+"/"+auditFDSplit[2]);
		AuditExposureDetail.setToDate(toDate);
		String atd=cgtmseAuditExposureLimitObj2.getToDate();
		String[] auditTDSplit=atd.split("/");
		AuditExposureDetail.setAuditToDate(auditTDSplit[1]+"/"+auditTDSplit[0]+"/"+auditTDSplit[2]);
		AuditExposureDetail.setRemarks(Remark);
		AuditExposureDetail.setAuditRemarks(auditRemark);
		AuditExposureDetail.setPay_out_cap(cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getPay_out_cap());
		AuditExposureDetail.setAudPayOutCap(cgtmseAuditExposureLimitObj2.getPay_out_cap());
		AuditExposureDetail.setGurantee_fee(cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getGurantee_fee());
		AuditExposureDetail.setAudGuranteeFee(cgtmseAuditExposureLimitObj2.getGurantee_fee());
		AuditExposureDetail.setGuranteeCoverage(cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getGuranteeCoverage());
		AuditExposureDetail.setAudGuranteeCoverage(cgtmseAuditExposureLimitObj2.getGuranteeCoverage());
		
		ModifyUserList.clear();
		ModifyUserList.add(AuditExposureDetail);
		model.put("CheckerUserList", ModifyUserList);
		model.put("adminlist",userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
		model.put("guaranteelist",userActivityService.getActivity("CGTMSECHECKER", "Registration"));
		model.put("applicationList",userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
		model.put("RPaymentList",userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("CGTMSECHECKER","danForAllCgtmseChecker"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		 model.put("repList",
					userActivityService.getReport("CGTMSECHECKER", "User_Report"));
		 model.put("CList",
					userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
		 model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		model.put("homePage","cgtmseCheckerHome");
		return new ModelAndView("cgtmseModifiedExposureLimitApprovalAndRejectionByChecker", model);
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
