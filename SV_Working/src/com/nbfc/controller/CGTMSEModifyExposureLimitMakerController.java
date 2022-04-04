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
import org.springframework.web.servlet.ModelAndView;
import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.CGTMSEExposureMasterMakerModifyExposureLimitBean;
import com.nbfc.model.CGTMSEAuditExposureLimit;
import com.nbfc.model.CGTMSEExposureMasterDetails;
import com.nbfc.model.CGTMSEMemberInfoDetails;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.CGTMSEModifyExposureLimitMakerService;
import com.nbfc.service.LoginService;
import com.nbfc.service.UserActivityService;
import com.raistudies.domain.CustomExceptionHandler;
import com.vaannila.validator.CGTMSEExposureMasterCheckerValidator;
@Controller
public class CGTMSEModifyExposureLimitMakerController {
	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;
	ModelAndView modelView;
	CGTMSEExposureMasterCheckerValidator cgtmseExposureMasterCheckerValidator= new CGTMSEExposureMasterCheckerValidator();
	String userId=null;
	String memBnkId=null;
	String memBrnId=null;
	String memZneId=null;
	Long oldExposureLimit=(long) 0;
	Date sanDate;
	Date esDate;
	Date fDate;
	Date tDate;
	Date ChekrDate;
	String makerStatus;
	String exposureSanctionDate;
	String fromDate ;
	String toDate ;
	Long eLimit=(long) 0;
	
	String staus=null;
	String statusDescription=null;
	String oldExposureSanctionDate=null;
	String oldStartDate=null;
	String oldEndDate=null;
	
	Date modifiedExposureDate1 = null;
	Date modifiedStartDate1 = null;
	Date modifiedEndDate1 = null;
	
	@Autowired
	private CGTMSEModifyExposureLimitMakerService cgtmseExposureMasterMakerModifyExposureLimitService;
	private List<CGTMSEExposureMasterMakerModifyExposureLimitBean> userList = new ArrayList<CGTMSEExposureMasterMakerModifyExposureLimitBean>();
	CGTMSEExposureMasterMakerModifyExposureLimitBean cgtmseExposureMasterMakerModifyExposureLimitBeanObj1=new CGTMSEExposureMasterMakerModifyExposureLimitBean();
	@RequestMapping(value = "modifyExposureLimitByMakerInputForm", method = RequestMethod.GET)
	public ModelAndView modifyExposureLimitByMakerInputForm(@ModelAttribute("command") CGTMSEExposureMasterMakerModifyExposureLimitBean cgtmseExposureMasterMakerModifyExposureLimitBean,BindingResult result,HttpSession session,Model modelObj) {
		try{
			userId=(String)session.getAttribute("userId");
			 if(userId!=null){
				userPri=lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				if(userPrvMst.getPrvCreatedModifiedBy().equals("CMAKER")){
					//No of Pending records
					List<Object[]> listObj=cgtmseExposureMasterMakerModifyExposureLimitService.getNoOfExposureMasterPendingForApproval();
					int countNoOfExposureMasterPendingForApproval=listObj.size();
					//No of approved records
					List<Object[]> listObj3=cgtmseExposureMasterMakerModifyExposureLimitService.getExposureMasterCheckerApprovalDetails();
					int countNoOfExposureMasterApproval =listObj3.size();
					//No of Rejected Records
					List<Object[]> listObj4=cgtmseExposureMasterMakerModifyExposureLimitService.getExposureMasterCheckerRejectionDetails();
					int countNoOfExposureMasterRejected =listObj4.size();
					modelObj.addAttribute("countNoOfExposureMasterPendingForApprovalKey", countNoOfExposureMasterPendingForApproval);
					modelObj.addAttribute("countNoOfExposureMasterApprovalKey", countNoOfExposureMasterApproval);
					modelObj.addAttribute("countNoOfExposureMasterRejectedKey", countNoOfExposureMasterRejected);
					modelView = new ModelAndView("cgtmseModifyExposureLimitByMaker"); 
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
	@ModelAttribute("getMliExposureMasterMakerLongName")
	public List<Object[]> getMliLongName() {
		Map<String, String> mapMliLongNameObj = new HashMap<String, String>();
		List<Object[]> listObjLongName = cgtmseExposureMasterMakerModifyExposureLimitService.getMliLongNameInDropDown();
		return  listObjLongName;
	}	
	
	@RequestMapping(value = "/getMliExposureMasterMakerShortName", method = RequestMethod.GET)
	public ModelAndView getMliShortName(@ModelAttribute(value = "command") CGTMSEExposureMasterMakerModifyExposureLimitBean cgtmseExposureMasterMakerModifyExposureLimitBeanObj,BindingResult result,String mliLName, HttpSession session){
	    Map<String, Object> model = new HashMap<String, Object>();
		String MliLongName=cgtmseExposureMasterMakerModifyExposureLimitBeanObj.getMliLongName();
		ArrayList arrayListObj2 = new ArrayList();
		arrayListObj2 = cgtmseExposureMasterMakerModifyExposureLimitService.getMliShortNameOnChangeOfMliLongName(cgtmseExposureMasterMakerModifyExposureLimitBeanObj);
		Iterator itr = arrayListObj2.iterator();
		String shortName = (String) itr.next();
		
		memBnkId=(String) itr.next();
		memBrnId=(String) itr.next();
		memZneId=(String) itr.next();
		
		CGTMSEMemberInfoDetails cgtmseMemberInfoDetailsObj=new CGTMSEMemberInfoDetails();
		cgtmseMemberInfoDetailsObj.setMliShortName(shortName);
		CGTMSEExposureMasterDetails cgtmseExposureMasterDetailsObj=new CGTMSEExposureMasterDetails();
		cgtmseExposureMasterDetailsObj.setMemBnkId(memBnkId);
		cgtmseExposureMasterDetailsObj.setMemBrnId(memBrnId);
		cgtmseExposureMasterDetailsObj.setMemZneId(memZneId);
		
		CGTMSEExposureMasterDetails cgtmseExposureMasterDetailsObj2 = cgtmseExposureMasterMakerModifyExposureLimitService.getMliExposureLimitInfo(cgtmseExposureMasterDetailsObj);
		eLimit=cgtmseExposureMasterDetailsObj2.getExposureLimit();
		esDate=cgtmseExposureMasterDetailsObj2.getExposureSanctionDate();
		fDate=cgtmseExposureMasterDetailsObj2.getFromDate();
		tDate=cgtmseExposureMasterDetailsObj2.getToDate();
		String Remark=cgtmseExposureMasterDetailsObj2.getRemarks();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    String sanctionDate = dateFormat.format(esDate);
	    String startDate = dateFormat.format(fDate);
	    String endDate = dateFormat.format(tDate);
		
		//Get Member Info Details
		cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setMliLongName(MliLongName);
		cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setMliShortName(shortName);
		cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setMliExposureLimit(eLimit);
		cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setExposureSanctionDate(sanctionDate);
		cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setFromDate(startDate);
		cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setToDate(endDate);
		cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setRemarks(Remark);
		cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setGurantee_fee(cgtmseExposureMasterDetailsObj2.getGurantee_fee());
		cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setPay_out_cap(cgtmseExposureMasterDetailsObj2.getPay_out_cap());
		cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setGuranteeCoverage(cgtmseExposureMasterDetailsObj2.getGuranteeCoverage());
		userList.clear();
		userList.add(cgtmseExposureMasterMakerModifyExposureLimitBeanObj1);
		model.put("modifyList", userList);
		model.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("CGTMSEMAKER","ExposureListingMaker"));// Added by Say 31 Jan19
		model.put("homePage","cgtmseMakerHome");
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		 model.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		return new ModelAndView("cgtmseModifyExposureLimitByMaker", model);
	}

		
		
	//@RequestMapping(value = "modifyExposureLimitByMakerAndSendForApproval", params = "action5", method = RequestMethod.POST)

	//Modify Exposure Limit and send for approval to CGTMSE Exposure Master Checker  CGTMSEExposureMasterMakerModifyExposureLimitBean
	//@RequestMapping(value = "modifyExposureLimitByMakerAndSendForApproval", params = "action5", method = RequestMethod.GET)

	@RequestMapping(value = "modifyExposureLimitByMakerAndSendForApproval", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView modifyExposureLimitByMakerAndSendForApproval(@ModelAttribute(value = "command") CGTMSEExposureMasterMakerModifyExposureLimitBean cgtmseExposureMasterMakerModifyExposureLimitBeanObj2,BindingResult result,String mliLName,HttpSession session,Model modelObj,Model model) throws ParseException{
		String memBnkId=null;
		String memBrnId=null;
		String memZneId=null;
		String shortName="";
		Long generatedExposureId=(long) 10000;
		String status="CEMME";
		Map<String, Object> pagMod = new HashMap<String, Object>();
		//Get sys date exposureMasterBean.getMliDateOfSanctionOfExposure()
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
	    String sysDate = sdf.format(cal.getTime());
	    userId=(String)session.getAttribute("userId");
		//Convert String into Date and Store into DB as Date data type
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
	    String sysCurrentDate = sysDate;
	    Date makerDate = null;
		try {
			makerDate = formatter1.parse(sysCurrentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
	    Integer maxExposureIdCount =0;
		Integer increasedMaxExposureIdCount=maxExposureIdCount+1;
		if(maxExposureIdCount==0){
			generatedExposureId=(long) 10000;
		}else{
			generatedExposureId=generatedExposureId+increasedMaxExposureIdCount;
		}
	       
		String mliLongName=cgtmseExposureMasterMakerModifyExposureLimitBeanObj2.getMliLongName();
		CGTMSEMemberInfoDetails cgtmseExposureMasterGetDetailsOfMemberInfoObj2=new CGTMSEMemberInfoDetails();
		cgtmseExposureMasterGetDetailsOfMemberInfoObj2.setMliLongName(mliLongName);
		List<CGTMSEMemberInfoDetails> obj3=(List<CGTMSEMemberInfoDetails>) cgtmseExposureMasterMakerModifyExposureLimitService.getMliMemberInfo(cgtmseExposureMasterGetDetailsOfMemberInfoObj2);
		Iterator<CGTMSEMemberInfoDetails> itr=obj3.iterator();
		while(itr.hasNext()){
	  		CGTMSEMemberInfoDetails obj4=(CGTMSEMemberInfoDetails)itr.next();
	  		memBnkId=obj4.getMemBnkId();
	  		memBrnId=obj4.getMemBrnId();
	  		memZneId=obj4.getMemZneId(); 
	  		shortName=obj4.getMliShortName();
		}
	    //Convert String into Date and Store into DB as Date data type
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String modifiedExposureDate =cgtmseExposureMasterMakerModifyExposureLimitBeanObj2.getMliDateOfSanctionOfExposure();
		String modifiedStartDate=cgtmseExposureMasterMakerModifyExposureLimitBeanObj2.getMliValidityOfExposureLimitStartDate();
		String modifiedEndDate=cgtmseExposureMasterMakerModifyExposureLimitBeanObj2.getMliValidityOfExposureLimitEndDate();
		Long modifyExposureLimit=cgtmseExposureMasterMakerModifyExposureLimitBeanObj2.getMliExposureLimit();

	    Date sDate = null;
	    Date eDate=null;
	    Date sanctionExposureDate=null;
		try {
			sDate = formatter.parse(modifiedStartDate);
			eDate=formatter.parse(modifiedEndDate);
			sanctionExposureDate=formatter.parse(modifiedExposureDate);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		CGTMSEExposureMasterDetails cgtmseExposureMasterDetailsObj=new CGTMSEExposureMasterDetails();
		cgtmseExposureMasterDetailsObj.setMemBnkId(memBnkId);
		cgtmseExposureMasterDetailsObj.setMemBrnId(memBrnId);
		cgtmseExposureMasterDetailsObj.setMemZneId(memZneId);
		CGTMSEExposureMasterDetails cgtmseExposureMasterDetailsObj2 = cgtmseExposureMasterMakerModifyExposureLimitService.getMliExposureLimitInfo(cgtmseExposureMasterDetailsObj);
		
		eLimit=cgtmseExposureMasterDetailsObj2.getExposureLimit();
		esDate=cgtmseExposureMasterDetailsObj2.getExposureSanctionDate();
		fDate=cgtmseExposureMasterDetailsObj2.getFromDate();
		tDate=cgtmseExposureMasterDetailsObj2.getToDate();
		ChekrDate=cgtmseExposureMasterDetailsObj2.getCheckerDate();
		makerStatus=cgtmseExposureMasterDetailsObj2.getStatus();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    String sanctionDate = dateFormat.format(esDate);
	    String startDate = dateFormat.format(fDate);
	    String endDate = dateFormat.format(tDate);
	    String MakerDate=dateFormat.format(makerDate);
	    String checkerDate=dateFormat.format(ChekrDate);
		
		Long ExposerId=cgtmseExposureMasterDetailsObj2.getExposureId();
		String DbStatus=cgtmseExposureMasterDetailsObj2.getStatus();
		String DbStatusDescription=cgtmseExposureMasterDetailsObj2.getStatusDescription();
		String DbRemarks=cgtmseExposureMasterDetailsObj2.getRemarks();
		String ckeckerId=cgtmseExposureMasterDetailsObj2.getCheckerId();
		
		CGTMSEExposureMasterDetails cgtmseExposureMasterDetailsObj1=new CGTMSEExposureMasterDetails();
		List<CGTMSEMemberInfoDetails> obj=(List<CGTMSEMemberInfoDetails>) cgtmseExposureMasterMakerModifyExposureLimitService.getMliMemberInfo(cgtmseExposureMasterGetDetailsOfMemberInfoObj2);
		Iterator<CGTMSEMemberInfoDetails> itr_1=obj.iterator();
		while(itr_1.hasNext()){    
	  		CGTMSEMemberInfoDetails ob = (CGTMSEMemberInfoDetails)itr_1.next();
			if(makerStatus.equals("CCA") ||(makerStatus.equals("CEMCR") || makerStatus.equals("CEMCA"))){
		  		CGTMSEAuditExposureLimit  cgtmseAuditExposureLimitObj2=new CGTMSEAuditExposureLimit();
		  		cgtmseAuditExposureLimitObj2.setExposureId(ExposerId);
		  		cgtmseAuditExposureLimitObj2.setMemBnkId(memBnkId);
		  		cgtmseAuditExposureLimitObj2.setMemBrnId(memBrnId);
		  		cgtmseAuditExposureLimitObj2.setMemZneId(memZneId);
		  		cgtmseAuditExposureLimitObj2.setExposureLimit(eLimit);
		  		cgtmseAuditExposureLimitObj2.setExposureSanctionDate(sanctionDate);
		  		cgtmseAuditExposureLimitObj2.setFromDate(startDate);
		  		cgtmseAuditExposureLimitObj2.setToDate(endDate);
		  		cgtmseAuditExposureLimitObj2.setCheckerDate(checkerDate);
		  		cgtmseAuditExposureLimitObj2.setCheckerId(ckeckerId);
		  		cgtmseAuditExposureLimitObj2.setMakerId(userId);
		  		cgtmseAuditExposureLimitObj2.setMakerDate(MakerDate);
		  		cgtmseAuditExposureLimitObj2.setStatus(DbStatus);
		  		cgtmseAuditExposureLimitObj2.setModifiedExposureDate(modifiedExposureDate);
		  		cgtmseAuditExposureLimitObj2.setStatusDescription(DbStatusDescription);
		  		cgtmseAuditExposureLimitObj2.setGurantee_fee(cgtmseExposureMasterDetailsObj2.getGurantee_fee());
		  		cgtmseAuditExposureLimitObj2.setPay_out_cap(cgtmseExposureMasterDetailsObj2.getPay_out_cap());
		  		cgtmseAuditExposureLimitObj2.setGuranteeCoverage(cgtmseExposureMasterDetailsObj2.getGuranteeCoverage());
		  		cgtmseAuditExposureLimitObj2.setRemarks(DbRemarks);
				int num1=cgtmseExposureMasterMakerModifyExposureLimitService.saveModifyExposureLimitDataAuditTable(cgtmseAuditExposureLimitObj2);
		  	}
			//if(mliLongName.equals(ob.getMliLongName()) && ob.getStatus().equals("CCA")&& (makerStatus.equals("CEMCR") || makerStatus.equals("CEMCA"))){
			if(makerStatus.equals("CCA") ||(makerStatus.equals("CEMCR") || makerStatus.equals("CEMCA"))){
		  		String statusDescription="Pending for approval";
		  		cgtmseExposureMasterDetailsObj1.setExposureId(ExposerId);	
		  		cgtmseExposureMasterDetailsObj1.setMemBnkId(memBnkId);
		  		cgtmseExposureMasterDetailsObj1.setMemBrnId(memBrnId);
		  		cgtmseExposureMasterDetailsObj1.setMemZneId(memZneId);
		  		cgtmseExposureMasterDetailsObj1.setExposureLimit(modifyExposureLimit);
		  		cgtmseExposureMasterDetailsObj1.setMakerId(userId);
		  		cgtmseExposureMasterDetailsObj1.setMakerDate(makerDate);
		  		cgtmseExposureMasterDetailsObj1.setFromDate(sDate);
		  		cgtmseExposureMasterDetailsObj1.setToDate(eDate);
		  		cgtmseExposureMasterDetailsObj1.setStatus(status);
		  		cgtmseExposureMasterDetailsObj1.setExposureSanctionDate(sanctionExposureDate);
				cgtmseExposureMasterDetailsObj1.setStatusDescription(statusDescription);
				cgtmseExposureMasterDetailsObj1.setGurantee_fee(cgtmseExposureMasterMakerModifyExposureLimitBeanObj2.getGurantee_fee());
				cgtmseExposureMasterDetailsObj1.setPay_out_cap(cgtmseExposureMasterMakerModifyExposureLimitBeanObj2.getPay_out_cap());
				cgtmseExposureMasterDetailsObj1.setGuranteeCoverage(cgtmseExposureMasterMakerModifyExposureLimitBeanObj2.getGuranteeCoverage());
			    int num2=cgtmseExposureMasterMakerModifyExposureLimitService.saveModifyExposureLimitByMakerAndSendForApprovalToChecker(cgtmseExposureMasterDetailsObj1);
			    model.addAttribute("Modifymessage", "Record Modified Sucessfully.");
			    pagMod.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
			    pagMod.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			    pagMod.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			    pagMod.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
			    pagMod.put("actName", userActivityService.getActivityName("CGTMSEMAKER","ExposureListingMaker"));
			    pagMod.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSEMAKER", "Guarantee_Maintenance"));
			    pagMod.put("homePage","cgtmseMakerHome");
			    pagMod.put("repList",
						userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			}else{
				model.addAttribute("modifyerror", "Unable to modify! please try again");	
			}
		}
		ModelAndView modelAndViewObj = new ModelAndView("cgtmseModifyExposureLimitByMaker",pagMod);
		return modelAndViewObj;
		   
	}
	
	//Get Exposure Master Details PendingForApproval whose status is CEMMR modifyExposureLimitByMakerAndSendForApproval
	@RequestMapping(value = "modifiedExposureLimitDetailsByMakerPendingForApprovalFromChecker", method = RequestMethod.GET)
	public ModelAndView getModifiedExposureLimitDetailsByMakerPendingForApprovalFromChecker(@ModelAttribute("command") CGTMSEExposureMasterMakerModifyExposureLimitBean cgtmseExposureMasterMakerModifyExposureLimitBean,BindingResult result,HttpSession session,Model modelObj) {
		List<Object[]> listObj=cgtmseExposureMasterMakerModifyExposureLimitService.getExposureMasterDetailsPendingForApproval();
		List<Object> list1 = new ArrayList<Object>();
		Iterator<Object[]> itr1= listObj.iterator();
		while(itr1.hasNext()){
			Object[] obj1 = (Object[]) itr1.next();
			cgtmseExposureMasterMakerModifyExposureLimitBean.setMliLongName((String) obj1[0]);
			cgtmseExposureMasterMakerModifyExposureLimitBean.setMliShortName((String) obj1[1]);
			cgtmseExposureMasterMakerModifyExposureLimitBean.setMliExposureLimit((Long) obj1[2]);
			// Convert Date to String, use format method of SimpleDateFormat class.
			java.util.Date dateOfSanctionOfExposure= (Date) obj1[3];
			java.util.Date mliValidityOfExposureLimitStartDate=(Date) obj1[4];
			java.util.Date mliValidityOfExposureLimitEndDate=(Date) obj1[5];
			
			String statusDescription=(String) obj1[6];
			String checkerId=(String)obj1[7];
			
			java.util.Date  checkerApproveDate= (Date)obj1[8];
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
	        String dateOfSanctionOfExposure1 = dateFormat2.format(dateOfSanctionOfExposure);
	        String mliValidityOfExposureLimitStartDate1 = dateFormat2.format(mliValidityOfExposureLimitStartDate);
	        String mliValidityOfExposureLimitEndDate1 = dateFormat2.format(mliValidityOfExposureLimitEndDate);
	        String mliValidityOfExposureLimitApprovalDate1 =dateFormat2.format(checkerApproveDate);
	        
	        cgtmseExposureMasterMakerModifyExposureLimitBean.setMliDateOfSanctionOfExposure(dateOfSanctionOfExposure1);
	        cgtmseExposureMasterMakerModifyExposureLimitBean.setMliValidityOfExposureLimitStartDate(mliValidityOfExposureLimitStartDate1);
	        cgtmseExposureMasterMakerModifyExposureLimitBean.setMliValidityOfExposureLimitEndDate(mliValidityOfExposureLimitEndDate1);
	        cgtmseExposureMasterMakerModifyExposureLimitBean.setStatusDescription(statusDescription);
	        cgtmseExposureMasterMakerModifyExposureLimitBean.setCheckerId(checkerId);  
	        cgtmseExposureMasterMakerModifyExposureLimitBean.setCheckerDate(mliValidityOfExposureLimitApprovalDate1);
			list1.add(cgtmseExposureMasterMakerModifyExposureLimitBean);
		}
		ModelAndView modelView=new ModelAndView("cgtmseModifyExposureLimitDetailsByMakerPendingForApprovalByChecker");
		modelView.addObject("lists", list1);
		return modelView;
	}
	
	
	//Get Exposure Master Approve Details  whose status is CEMCA
	@RequestMapping(value = "modifiedExposureLimitDetailsByMakerApprovedByChecker", method = RequestMethod.GET)
	public ModelAndView getModifiedExposureLimitDetailsByMakerApprovedByChecker(@ModelAttribute("command") CGTMSEExposureMasterMakerModifyExposureLimitBean cgtmseExposureMasterMakerModifyExposureLimitBean,BindingResult result,HttpSession session,Model modelObj) {
		List<Object[]> listObj=cgtmseExposureMasterMakerModifyExposureLimitService.getExposureMasterCheckerApprovalDetails();
		List<Object> list2 = new ArrayList<Object>();
		Iterator<Object[]> itr1= listObj.iterator();
		while(itr1.hasNext()){
			Object[] obj1 = (Object[]) itr1.next();
			CGTMSEExposureMasterMakerModifyExposureLimitBean cgtmseExposureMasterMakerModifyExposureLimitBeanObj1=new CGTMSEExposureMasterMakerModifyExposureLimitBean();
			cgtmseExposureMasterMakerModifyExposureLimitBean.setMliLongName((String) obj1[0]);
			cgtmseExposureMasterMakerModifyExposureLimitBean.setMliShortName((String) obj1[1]);
			cgtmseExposureMasterMakerModifyExposureLimitBean.setMliExposureLimit((Long) obj1[2]);
			// Convert Date to String, use format method of SimpleDateFormat class.
			java.util.Date dateOfSanctionOfExposure= (Date) obj1[3];
			java.util.Date mliValidityOfExposureLimitStartDate=(Date) obj1[4];
			java.util.Date mliValidityOfExposureLimitEndDate=(Date) obj1[5];
			String statusDescription=(String) obj1[6];
		    String checkerId=(String) obj1[7];
			java.util.Date  checkerApproveDate= (Date)obj1[8];
			
		    SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
            String dateOfSanctionOfExposure1 = dateFormat2.format(dateOfSanctionOfExposure);
            String mliValidityOfExposureLimitStartDate1 = dateFormat2.format(mliValidityOfExposureLimitStartDate);
            String mliValidityOfExposureLimitEndDate1 = dateFormat2.format(mliValidityOfExposureLimitEndDate);
            String mliValidityOfExposureLimitApproveDate=dateFormat2.format(checkerApproveDate);
            
            cgtmseExposureMasterMakerModifyExposureLimitBean.setMliDateOfSanctionOfExposure(dateOfSanctionOfExposure1);
            cgtmseExposureMasterMakerModifyExposureLimitBean.setMliValidityOfExposureLimitStartDate(mliValidityOfExposureLimitStartDate1);
            cgtmseExposureMasterMakerModifyExposureLimitBean.setMliValidityOfExposureLimitEndDate(mliValidityOfExposureLimitEndDate1);
            cgtmseExposureMasterMakerModifyExposureLimitBean.setStatusDescription(statusDescription);
            cgtmseExposureMasterMakerModifyExposureLimitBean.setCheckerId(checkerId);  
            cgtmseExposureMasterMakerModifyExposureLimitBean.setCheckerDate(mliValidityOfExposureLimitApproveDate);
            list2.add(cgtmseExposureMasterMakerModifyExposureLimitBean);
		}
		ModelAndView modelView=new ModelAndView("cgtmseModifiedExposureLimitDetailsByMakerApprovedByChecker");
		modelView.addObject("list2key", list2);
		return modelView;
	}
	
	
	//Get Exposure Master Rejected By Checker  whose status is CEMCR 
	@RequestMapping(value = "modifiedExposureLimitDetailsByMakerRejectedByChecker", method = RequestMethod.GET)
	public ModelAndView getModifiedExposureLimitDetailsByMakerRejectedByChecker(@ModelAttribute("command") CGTMSEExposureMasterMakerModifyExposureLimitBean cgtmseExposureMasterMakerModifyExposureLimitBean,BindingResult result,HttpSession session,Model modelObj) {
		List<Object[]> listObj4=cgtmseExposureMasterMakerModifyExposureLimitService.getExposureMasterCheckerRejectionDetails();
		List<Object> list4 = new ArrayList<Object>();
		Iterator<Object[]> itr1= listObj4.iterator();
		while(itr1.hasNext()){
			Object[] obj4 = (Object[]) itr1.next();
			CGTMSEExposureMasterMakerModifyExposureLimitBean cgtmseExposureMasterMakerModifyExposureLimitBeanObj1=new CGTMSEExposureMasterMakerModifyExposureLimitBean();
			cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setMliLongName((String) obj4[0]);
			cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setMliShortName((String) obj4[1]);
			cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setMliExposureLimit((Long) obj4[2]);
			// Convert Date to String, use format method of SimpleDateFormat class.
			java.util.Date dateOfSanctionOfExposure= (Date) obj4[3];
			java.util.Date mliValidityOfExposureLimitStartDate=(Date) obj4[4];
			java.util.Date mliValidityOfExposureLimitEndDate=(Date) obj4[5];
			java.util.Date  checkerRejectedDate= (Date) obj4[8];
			String statusDescription=(String) obj4[6];
			String checkerId=(String) obj4[7];
			
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
            String dateOfSanctionOfExposure1 = dateFormat2.format(dateOfSanctionOfExposure);
            String mliValidityOfExposureLimitStartDate1 = dateFormat2.format(mliValidityOfExposureLimitStartDate);
            String mliValidityOfExposureLimitEndDate1 = dateFormat2.format(mliValidityOfExposureLimitEndDate);
            String mliValidityOfExposureLimitRejectedDate = dateFormat2.format(checkerRejectedDate);
            
            cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setMliDateOfSanctionOfExposure(dateOfSanctionOfExposure1);
            cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setMliValidityOfExposureLimitStartDate(mliValidityOfExposureLimitStartDate1);
            cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setMliValidityOfExposureLimitEndDate(mliValidityOfExposureLimitEndDate1);
            cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setCheckerDate(mliValidityOfExposureLimitRejectedDate);
            cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setStatusDescription(statusDescription);
            cgtmseExposureMasterMakerModifyExposureLimitBeanObj1.setCheckerId(checkerId);  
            list4.add(cgtmseExposureMasterMakerModifyExposureLimitBeanObj1);
		}
		ModelAndView modelView=new ModelAndView("cgtmseModifiedExposureLimitDetailsByMakerRejectedByChecker");
		modelView.addObject("list4key", list4);
		return modelView;
	}
	
	@ExceptionHandler(CustomExceptionHandler.class)
	public ModelAndView handleCustomException(CustomExceptionHandler ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		
		model1.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model1.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model1.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model1.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model1.put("homePage","cgtmseMakerHome");
		model1.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		ModelAndView model = new ModelAndView("customException",model1);
		model.addObject("customException", ex.getMessage());
		return model;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		
		model1.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model1.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model1.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model1.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model1.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		model1.put("homePage","cgtmseMakerHome");
		model1.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		ModelAndView model = new ModelAndView("exception",model1);
		model.addObject("exception", ex);
		return model;
	}

	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithException(ArithmeticException ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		
		model1.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model1.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model1.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model1.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model1.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		model1.put("homePage","cgtmseMakerHome");
		model1.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		ModelAndView model = new ModelAndView("exception",model1);
		model.addObject("exception", ex);
		return model;
	}

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullException(NullPointerException ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		
		model1.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model1.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model1.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model1.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model1.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		model1.put("homePage","cgtmseMakerHome");
		model1.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		ModelAndView model = new ModelAndView("exception",model1);
		model.addObject("exception", "Data is null");
		return model;
	}
}
	