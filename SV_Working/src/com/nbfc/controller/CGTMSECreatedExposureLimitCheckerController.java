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
import com.nbfc.bean.CGTMSEExposureMasterBean;
import com.nbfc.bean.CGTMSECreatedExposureLimitCheckerBean;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETExposureDetails;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETMemberInfoDetails;
import com.nbfc.model.CGTMSEExposureMasterMLIName;
import com.nbfc.model.CGTMSERejectExposureMasterCheckerDetailsData;
import com.nbfc.model.CGTMSESaveExposureMasterCheckerDetailsData;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.CGTMSECreatedExposureLimitCheckerService;
import com.nbfc.service.LoginService;
import com.nbfc.service.UserActivityService;
import com.raistudies.domain.CustomExceptionHandler;
import com.vaannila.validator.CGTMSEExposureMasterCheckerValidator;

@Controller
public class CGTMSECreatedExposureLimitCheckerController {
	@Autowired
	LoginService lofinService;
	@Autowired
	MenuUtilsController menuUtilsController;
	@Autowired
	UserActivityService userActivityService;
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;
	ModelAndView modelView;
	CGTMSEExposureMasterCheckerValidator cgtmseExposureMasterCheckerValidator = new CGTMSEExposureMasterCheckerValidator();
	String userId = null;
	String memBnkId = null;
	String memBrnId = null;
	String memZneId = null;
	Long eLimit = (long) 0;

	@Autowired
	private CGTMSECreatedExposureLimitCheckerService cgtmseCreatedExposureLimitCheckerService;
	private List<CGTMSECreatedExposureLimitCheckerBean> userList = new ArrayList<CGTMSECreatedExposureLimitCheckerBean>();

	@RequestMapping(value = "showCGTMSECreatedExposureLimitCheckerInputForm", method = RequestMethod.GET)
	public ModelAndView showCGTMSECreatedExposureLimitCheckerInputForm(@ModelAttribute("command") CGTMSECreatedExposureLimitCheckerBean cgtmseCreatedExposureLimitCheckerBean,BindingResult result, HttpSession session, Model modelObj) {
		try {
			userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				if (userPrvMst.getPrvCreatedModifiedBy().equals("CCHECKER")) {
					// No of Pending records in page onload
					List<Object[]> listObj1 = cgtmseCreatedExposureLimitCheckerService.getExposureLimitDetailsPendingForApproval();
					int countNoOfExposureMasterPendingForApprovalDetails = listObj1.size();

					// No of Approved records in page onload
					List<Object[]> listObj6 = cgtmseCreatedExposureLimitCheckerService.getExposureLimitApprovedDetails();
					int countNoOfExposureMasterApprovalDetailsByChecker = listObj6.size();

					// No of Rejected records in page onload
					List<Object[]> listObj10 = cgtmseCreatedExposureLimitCheckerService.getExposureLimitRejectedDetails();
					int countNoOfExposureMasterRejectionDetailsRejectedByChecker = listObj10.size();

					modelObj.addAttribute("countNoOfExposureMasterPendingForApprovalDetailsKey",countNoOfExposureMasterPendingForApprovalDetails);
					modelObj.addAttribute("countNoOfExposureMasterApprovalDetailsByCheckerKey",countNoOfExposureMasterApprovalDetailsByChecker);
					modelObj.addAttribute("countNoOfExposureMasterRejectionDetailsRejectedByCheckerKey",countNoOfExposureMasterRejectionDetailsRejectedByChecker);
					Map<String, Object> modelAct = new HashMap<String, Object>();
					modelAct.put("adminlist",userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
					modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSECHECKER", "Registration"));
					modelAct.put("applicationList",userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
					modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
					modelAct.put("actName", userActivityService.getActivityName("CGTMSECHECKER","ExposureListingChecker"));
					modelAct.put("repList",
							userActivityService.getReport("CGTMSECHECKER", "User_Report"));
					modelAct.put("GMaintainlist", userActivityService.getActivity("CGTMSECHECKER", "Guarantee_Maintenance"));
					modelAct.put("homePage", "cgtmseCheckerHome");
					modelAct.put("CList",
							userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
					modelAct.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
					modelView = new ModelAndView("cgtmseCreatedExposureLimitByChecker", modelAct);
				} else {
					modelView = new ModelAndView("redirect:/SVLogin.html");
				}
			} else {
				modelView = new ModelAndView("redirect:/SVLogin.html");
			}
			return modelView;
		} catch (Exception e) {
			System.out.println("Exception ==" + e);
		}
		return modelView;
	}

	// Get MLI Long in MLI DropDown in Page Onload
	@ModelAttribute("mliLongName")
	public List<Object[]> getMliLongName() {
		List<Object[]> listObjLongName = cgtmseCreatedExposureLimitCheckerService.getMliLongNameInDropDown();
		return listObjLongName;
	}

	@RequestMapping(value = "/getMliCheckerShortName1", method = RequestMethod.GET)
	public ModelAndView getMliShortName(@ModelAttribute(value = "command") CGTMSECreatedExposureLimitCheckerBean cgtmseCreatedExposureLimitCheckerBean,BindingResult result, HttpSession session){
		String mliLName=cgtmseCreatedExposureLimitCheckerBean.getMliLongName();
		ArrayList arrayListObj2 = new ArrayList();
		arrayListObj2 = cgtmseCreatedExposureLimitCheckerService.getMliShortNameOnChangeOfMliLongName(cgtmseCreatedExposureLimitCheckerBean);
		Map<String, Object> model = new HashMap<String, Object>();
		Iterator itr = arrayListObj2.iterator();
		CGTMSECreatedExposureLimitCheckerBean s2 = new CGTMSECreatedExposureLimitCheckerBean();
		String shortName = (String) itr.next();
		memBnkId = (String) itr.next();
		memBrnId = (String) itr.next();
		memZneId = (String) itr.next();
		ArrayList<String> arrayListObj3 = new ArrayList();
		CGTMSEExposureMasterCheckerGETMemberInfoDetails cgtmseExposureMasterCheckerGETMemberInfoDetailsObj = new CGTMSEExposureMasterCheckerGETMemberInfoDetails();
		cgtmseExposureMasterCheckerGETMemberInfoDetailsObj.setMliShortName(shortName);

		CGTMSEExposureMasterCheckerGETExposureDetails cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj = new CGTMSEExposureMasterCheckerGETExposureDetails();
		cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj.setMemBnkId(memBnkId);
		cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj.setMemBrnId(memBrnId);
		cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj.setMemZneId(memZneId);

		CGTMSEExposureMasterCheckerGETExposureDetails cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2 = cgtmseCreatedExposureLimitCheckerService.getExposureLimitInfo(cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj);
		eLimit = cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getExposureLimit();
		Date esDate = cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getExposureSanctionDate();
		Date fDate = cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getFromDate();
		Date tDate = cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getToDate();

		//Convert date into String
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		String exposureSanctionDate = formatter.format(esDate);
		String fromDate = formatter.format(fDate);
		String toDate = formatter.format(tDate);
		Float guranteeFee=cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getGurantee_fee();
		Long payOutCap=cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getPay_out_cap();
		//Get Member Info Details
		s2.setMliLongName(mliLName);
		s2.setMliShortName(shortName);
		s2.setMliExposureLimit(eLimit);
		s2.setMliDateOfSanctionOfExposure(exposureSanctionDate);
		s2.setFromDate(fromDate);
		s2.setToDate(toDate);
		s2.setGurantee_fee(cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getGurantee_fee());
		s2.setPay_out_cap(cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getPay_out_cap());
		s2.setGuranteeCoverage(cgtmseExposureMasterCheckerGETMemberInfoDetailsObj2.getGuranteeCoverage());
		userList.clear();
		userList.add(s2);
		model.put("CheckerApprovalList", userList);
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		model.put("adminlist",userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
		model.put("guaranteelist",userActivityService.getActivity("CGTMSECHECKER", "Registration"));
		model.put("applicationList",userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
		model.put("RPaymentList",userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("CGTMSECHECKER","ExposureListingChecker"));// Added by Say 31 Jan19
		model.put("repList",
				userActivityService.getReport("CGTMSECHECKER", "User_Report"));
		model.put("CList",
				userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		model.put("homePage", "cgtmseCheckerHome");
		return new ModelAndView("cgtmseCreatedExposureLimitByChecker", model);
	}

	// Apporve Exposure Limit
	@RequestMapping(value = "/saveExposureMasterCheckerDetails", params = "action1", method = RequestMethod.POST)
	public ModelAndView approveCreatedExposureLimitByChecker(@ModelAttribute("command") CGTMSECreatedExposureLimitCheckerBean cgtmseCreatedExposureLimitCheckerBean,BindingResult result, HttpSession session, Model modelObj,Model model) throws ParseException {
		Map<String, Object> modelAct = new HashMap<String, Object>();
		userId = (String) session.getAttribute("userId");
		String checkerStatus = "CEMCA";
		String checkerId = null;
		// Get Checker Date means sysdate
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
		String checkerSysDate = sdf.format(cal.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date checkerDate = null;
		try {
			checkerDate = formatter.parse(checkerSysDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String mliLongName = cgtmseCreatedExposureLimitCheckerBean.getMliLongName();
		CGTMSEExposureMasterCheckerGETMemberInfoDetails cgtmseExposureMasterGetDetailsOfMemberInfoDtls = new CGTMSEExposureMasterCheckerGETMemberInfoDetails();
		cgtmseExposureMasterGetDetailsOfMemberInfoDtls.setMliLongName(mliLongName);
		
		List<CGTMSEExposureMasterCheckerGETMemberInfoDetails> obj3=cgtmseCreatedExposureLimitCheckerService.getDetailsOfMemberInfo(cgtmseExposureMasterGetDetailsOfMemberInfoDtls);
		Iterator<CGTMSEExposureMasterCheckerGETMemberInfoDetails> itr=obj3.iterator();
		while(itr.hasNext()){
			CGTMSEExposureMasterCheckerGETMemberInfoDetails obj4=(CGTMSEExposureMasterCheckerGETMemberInfoDetails)itr.next();
			memBnkId=obj4.getMemBnkId();
			memBrnId=obj4.getMemBrnId();
			memZneId=obj4.getMemZneId();
		}
		
		CGTMSESaveExposureMasterCheckerDetailsData cgtmseExposureMasterCheckerDetailsData = new CGTMSESaveExposureMasterCheckerDetailsData();
		cgtmseExposureMasterCheckerDetailsData.setMemBnkId(memBnkId);
		cgtmseExposureMasterCheckerDetailsData.setMemBrnId(memBrnId);
		cgtmseExposureMasterCheckerDetailsData.setMemZneId(memZneId);
		List<CGTMSESaveExposureMasterCheckerDetailsData> obj4=cgtmseCreatedExposureLimitCheckerService.getDetailsOfExposer(cgtmseExposureMasterCheckerDetailsData);
		Iterator<CGTMSESaveExposureMasterCheckerDetailsData> itr2=obj4.iterator();
		
		CGTMSESaveExposureMasterCheckerDetailsData obj5=(CGTMSESaveExposureMasterCheckerDetailsData)itr2.next();
		String exposureStatus=obj5.getCheckerStatus();
		
		CGTMSESaveExposureMasterCheckerDetailsData cgtmseSaveExposureMasterCheckerDetailsDataObj = new CGTMSESaveExposureMasterCheckerDetailsData();
		List<CGTMSEExposureMasterCheckerGETMemberInfoDetails> obj = cgtmseCreatedExposureLimitCheckerService.getDetailsOfMemberInfo(cgtmseExposureMasterGetDetailsOfMemberInfoDtls);
		Iterator<CGTMSEExposureMasterCheckerGETMemberInfoDetails> itr_1 = obj.iterator();
		while (itr_1.hasNext()) {
			CGTMSEExposureMasterCheckerGETMemberInfoDetails ob = (CGTMSEExposureMasterCheckerGETMemberInfoDetails) itr_1.next();
			if (exposureStatus.equals("CEMMA") || exposureStatus.equals("CEMME")||exposureStatus.equals("CEMCA") || exposureStatus.equals("CEMCR")) {
			//if (mliLongName.equals(ob.getMliLongName())&& ob.getStatus().equals("CCA") && (exposureStatus.equals("CEMMA") || exposureStatus.equals("CEMME")) && !exposureStatus.equals("CEMCA") && !exposureStatus.equals("CEMCR")) {
				cgtmseSaveExposureMasterCheckerDetailsDataObj.setCheckerId(userId);
				cgtmseSaveExposureMasterCheckerDetailsDataObj.setCheckerDate(checkerDate);
				cgtmseSaveExposureMasterCheckerDetailsDataObj.setCheckerStatus(checkerStatus);
				cgtmseSaveExposureMasterCheckerDetailsDataObj.setMemBnkId(memBnkId);
				cgtmseSaveExposureMasterCheckerDetailsDataObj.setMemBrnId(memBrnId);
				cgtmseSaveExposureMasterCheckerDetailsDataObj.setMemZneId(memZneId);
				int approveRecord = cgtmseCreatedExposureLimitCheckerService.approveCreatedExposureLimit(cgtmseSaveExposureMasterCheckerDetailsDataObj);
				System.out.println("Record approveRecord from Checker==="+approveRecord);
				model.addAttribute("checkerApproveKey", "Successfully approved.");
				System.out.println("Approve successfully.==" + approveRecord);
			}
		}
		modelAct.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		modelAct.put("adminlist",userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
		modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSECHECKER", "Registration"));
		modelAct.put("applicationList",userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
		modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
		modelAct.put("actName", userActivityService.getActivityName("CGTMSECHECKER","ExposureListingChecker"));// Added by Say 31 Jan19
		modelAct.put("repList",
				userActivityService.getReport("CGTMSECHECKER", "User_Report"));
		modelAct.put("CList",
				userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
		modelAct.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		modelAct.put("homePage", "cgtmseCheckerHome");
		return new ModelAndView("cgtmseCreatedExposureLimitByChecker", modelAct);
	}

	// Reejction
	@RequestMapping(value = "/saveExposureMasterCheckerDetails", params = "action3", method = RequestMethod.POST)
	public ModelAndView rejectCreatedExposureLimitByChecker(@ModelAttribute("command") CGTMSECreatedExposureLimitCheckerBean cgtmseCreatedExposureLimitCheckerBean,BindingResult result, HttpSession session, Model modelObj,Model model) throws ParseException {
		String rejectStatus = "CEMCR";
		String checkerStatusDescription = "Rejected";
		userId = (String) session.getAttribute("userId");
		// Get Checker Date means sysdate
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
		String checkerSysDate = sdf.format(cal.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date checkerDate = null;
		try {
			checkerDate = formatter.parse(checkerSysDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String Remark = cgtmseCreatedExposureLimitCheckerBean.getRemarks();
		String mliLongName = cgtmseCreatedExposureLimitCheckerBean.getMliLongName();
		CGTMSERejectExposureMasterCheckerDetailsData cgtmseRejectExposureMasterCheckerDetailsDataObj = new CGTMSERejectExposureMasterCheckerDetailsData();
		CGTMSEExposureMasterCheckerGETMemberInfoDetails cgtmseExposureMasterGetDetailsOfMemberInfoDtls = new CGTMSEExposureMasterCheckerGETMemberInfoDetails();
		cgtmseExposureMasterGetDetailsOfMemberInfoDtls.setMliLongName(mliLongName);
		List<CGTMSEExposureMasterCheckerGETMemberInfoDetails> obj3=cgtmseCreatedExposureLimitCheckerService.getDetailsOfMemberInfo(cgtmseExposureMasterGetDetailsOfMemberInfoDtls);
		Iterator<CGTMSEExposureMasterCheckerGETMemberInfoDetails> itr=obj3.iterator();
		while(itr.hasNext()){
			CGTMSEExposureMasterCheckerGETMemberInfoDetails obj4=(CGTMSEExposureMasterCheckerGETMemberInfoDetails)itr.next();
			memBnkId=obj4.getMemBnkId();
			memBrnId=obj4.getMemBrnId();
			memZneId=obj4.getMemZneId();
		}
		
		CGTMSESaveExposureMasterCheckerDetailsData cgtmseExposureMasterCheckerDetailsData = new CGTMSESaveExposureMasterCheckerDetailsData();
		cgtmseExposureMasterCheckerDetailsData.setMemBnkId(memBnkId);
		cgtmseExposureMasterCheckerDetailsData.setMemBrnId(memBrnId);
		cgtmseExposureMasterCheckerDetailsData.setMemZneId(memZneId);
		List<CGTMSESaveExposureMasterCheckerDetailsData> obj4=cgtmseCreatedExposureLimitCheckerService.getDetailsOfExposer(cgtmseExposureMasterCheckerDetailsData);
		Iterator<CGTMSESaveExposureMasterCheckerDetailsData> itr2=obj4.iterator();
		CGTMSESaveExposureMasterCheckerDetailsData obj5=(CGTMSESaveExposureMasterCheckerDetailsData)itr2.next();
		String exposureStatus=obj5.getCheckerStatus();
		List<CGTMSEExposureMasterCheckerGETMemberInfoDetails> obj = cgtmseCreatedExposureLimitCheckerService.getDetailsOfMemberInfo(cgtmseExposureMasterGetDetailsOfMemberInfoDtls);
		Iterator<CGTMSEExposureMasterCheckerGETMemberInfoDetails> itr_1 = obj.iterator();
		while (itr_1.hasNext()) {
			CGTMSEExposureMasterCheckerGETMemberInfoDetails ob = (CGTMSEExposureMasterCheckerGETMemberInfoDetails) itr_1.next();
			//if (mliLongName.equals(ob.getMliLongName()) && ob.getStatus().equals("CCA") && (exposureStatus.equals("CEMMA") || exposureStatus.equals("CEMME"))  && !exposureStatus.equals("CEMCA") && !exposureStatus.equals("CEMCR"))
			if (exposureStatus.equals("CCA")||exposureStatus.equals("CEMMA") || exposureStatus.equals("CEMME")  ||exposureStatus.equals("CEMCA") ||exposureStatus.equals("CEMCR")){
				cgtmseRejectExposureMasterCheckerDetailsDataObj.setCheckerId(userId);
				cgtmseRejectExposureMasterCheckerDetailsDataObj.setCheckerDate(checkerDate);
				cgtmseRejectExposureMasterCheckerDetailsDataObj.setCheckerStatus(rejectStatus);
				cgtmseRejectExposureMasterCheckerDetailsDataObj.setCheckerStatusDescription(checkerStatusDescription);
				cgtmseRejectExposureMasterCheckerDetailsDataObj.setMemBnkId(memBnkId);
				cgtmseRejectExposureMasterCheckerDetailsDataObj.setRemarks(Remark);
				cgtmseRejectExposureMasterCheckerDetailsDataObj.setMemBrnId(memBrnId);
				cgtmseRejectExposureMasterCheckerDetailsDataObj.setMemZneId(memZneId);
				int rejectedRecord = cgtmseCreatedExposureLimitCheckerService.rejectCreatedExposureLimit(cgtmseRejectExposureMasterCheckerDetailsDataObj);
				model.addAttribute("checkerRejectKey", "Return Sucessfully.");
			}
		}
		
		Map<String, Object> modelAct = new HashMap<String, Object>();
		modelAct.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		modelAct.put("adminlist",userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
		modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSECHECKER", "Registration"));
		modelAct.put("applicationList",userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
		modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
		modelAct.put("actName", userActivityService.getActivityName("CGTMSECHECKER","ExposureListingChecker"));
		modelAct.put("repList",
				userActivityService.getReport("CGTMSECHECKER", "User_Report"));
		modelAct.put("homePage", "cgtmseCheckerHome");
		modelAct.put("CList",
				userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
		modelAct.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		return new ModelAndView("cgtmseCreatedExposureLimitByChecker",modelAct);
	}
	
	// Get Exposure Master Details PendingForApproval whose status is CEMMA
	@RequestMapping(value = "createdExposureLimitDetailsPendingForApprovalByChecker2", method = RequestMethod.GET)
	public ModelAndView getCreatedExposureLimitDetailsPendingForApproval(@ModelAttribute("command") CGTMSECreatedExposureLimitCheckerBean cgtmseCreatedExposureLimitCheckerBean,BindingResult result, HttpSession session, Model modelObj) {
		List<Object[]> listObj = cgtmseCreatedExposureLimitCheckerService.getExposureLimitDetailsPendingForApproval();
		List<Object> list1 = new ArrayList<Object>();
		Iterator<Object[]> itr1 = listObj.iterator();
		while (itr1.hasNext()) {
			Object[] obj1 = (Object[]) itr1.next();
			CGTMSECreatedExposureLimitCheckerBean cgtmseExposureMasterCheckerBeanObj1 = new CGTMSECreatedExposureLimitCheckerBean();
			cgtmseExposureMasterCheckerBeanObj1.setMliLongName((String) obj1[0]);
			cgtmseExposureMasterCheckerBeanObj1.setMliShortName((String) obj1[1]);
			cgtmseExposureMasterCheckerBeanObj1.setMliExposureLimit((Long) obj1[2]);
			// Convert Date to String, use format method of SimpleDateFormat
			java.util.Date dateOfSanctionOfExposure = (Date) obj1[3];
			java.util.Date mliValidityOfExposureLimitStartDate = (Date) obj1[4];
			java.util.Date mliValidityOfExposureLimitEndDate = (Date) obj1[5];
			String statusDescription = (String) obj1[6];
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String dateOfSanctionOfExposure1 = dateFormat.format(dateOfSanctionOfExposure);
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
			String mliValidityOfExposureLimitStartDate1 = dateFormat2.format(mliValidityOfExposureLimitStartDate);
			String mliValidityOfExposureLimitEndDate1 = dateFormat2.format(mliValidityOfExposureLimitEndDate);
			cgtmseExposureMasterCheckerBeanObj1.setMliDateOfSanctionOfExposure(dateOfSanctionOfExposure1);
			cgtmseExposureMasterCheckerBeanObj1.setMliValidityOfExposureLimitStartDate(mliValidityOfExposureLimitStartDate1);
			cgtmseExposureMasterCheckerBeanObj1.setMliValidityOfExposureLimitEndDate(mliValidityOfExposureLimitEndDate1);
			cgtmseExposureMasterCheckerBeanObj1.setStatusDescription(statusDescription);
			list1.add(cgtmseExposureMasterCheckerBeanObj1);
		}
		ModelAndView modelView = new ModelAndView("cgtmseCreatedExposureLimitDetailsPendingForApprovalFromChecker");
		modelView.addObject("lists", list1);
		return modelView;
	}

	// Get Exposure Master Approve Details whose status is CEMCA
	@RequestMapping(value = "createdExposureLimitDetailsApprovedByChecker2", method = RequestMethod.GET)
	public ModelAndView createdExposureLimitDetailsApprovedByChecker(@ModelAttribute("command") CGTMSECreatedExposureLimitCheckerBean cgtmseCreatedExposureLimitCheckerBean,BindingResult result, HttpSession session, Model modelObj) {
		List<Object[]> listObj5 = cgtmseCreatedExposureLimitCheckerService.getExposureLimitApprovedDetails();
		List<Object> list5 = new ArrayList<Object>();
		Iterator<Object[]> itr1 = listObj5.iterator();// cgtmseExposureMasterCheckerBean
		while (itr1.hasNext()) {
			Object[] obj5 = (Object[]) itr1.next();
			CGTMSECreatedExposureLimitCheckerBean cgtmseExposureMasterCheckerBeanObj1 = new CGTMSECreatedExposureLimitCheckerBean();
			cgtmseExposureMasterCheckerBeanObj1.setMliLongName((String) obj5[0]);
			cgtmseExposureMasterCheckerBeanObj1.setMliShortName((String) obj5[1]);
			cgtmseExposureMasterCheckerBeanObj1.setMliExposureLimit((Long) obj5[2]);
			//Convert Date to String, use format method of SimpleDateFormat
			java.util.Date dateOfSanctionOfExposure = (Date) obj5[3];
			java.util.Date mliValidityOfExposureLimitStartDate = (Date) obj5[4];
			java.util.Date mliValidityOfExposureLimitEndDate = (Date) obj5[5];

			String statusDescription = (String) obj5[6];
			String checkerId = (String) obj5[7];
			java.util.Date checkerApproveDate = (Date) obj5[8];
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String dateOfSanctionOfExposure1 = dateFormat.format(dateOfSanctionOfExposure);
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
			String mliValidityOfExposureLimitStartDate1 = dateFormat2.format(mliValidityOfExposureLimitStartDate);
			String mliValidityOfExposureLimitEndDate1 = dateFormat2.format(mliValidityOfExposureLimitEndDate);
			cgtmseExposureMasterCheckerBeanObj1.setMliDateOfSanctionOfExposure(dateOfSanctionOfExposure1);
			cgtmseExposureMasterCheckerBeanObj1.setMliValidityOfExposureLimitStartDate(mliValidityOfExposureLimitStartDate1);
			cgtmseExposureMasterCheckerBeanObj1.setMliValidityOfExposureLimitEndDate(mliValidityOfExposureLimitEndDate1);
			cgtmseExposureMasterCheckerBeanObj1.setStatusDescription(statusDescription);
			cgtmseExposureMasterCheckerBeanObj1.setCheckerId(checkerId);
			SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd/MM/yyyy");
			String checkerAppDate = dateFormat3.format(checkerApproveDate);
			cgtmseExposureMasterCheckerBeanObj1.setCheckerDate(checkerAppDate);
			list5.add(cgtmseExposureMasterCheckerBeanObj1);
		}
		ModelAndView modelView = new ModelAndView("cgtmseCreatedExposureLimitDetailsApprovedByChecker");
		modelView.addObject("list5key", list5);
		return modelView;
	}

	// Get Exposure Master Rejected By Checker whose status is CEMCA
	@RequestMapping(value = "createdExposureLimitDetailsRejectedByChecker2")
	public ModelAndView cgtmseCreatedExposureLimitDetailsApprovedByChecker(@ModelAttribute("command") CGTMSECreatedExposureLimitCheckerBean cgtmseCreatedExposureLimitCheckerBean,BindingResult result, HttpSession session, Model modelObj) {
		List<Object[]> listObj7 = cgtmseCreatedExposureLimitCheckerService.getExposureLimitRejectedDetails();
		List<Object> list7 = new ArrayList<Object>();
		Iterator<Object[]> itr7 = listObj7.iterator();
		while (itr7.hasNext()) {
			Object[] obj7 = (Object[]) itr7.next();
			CGTMSEExposureMasterBean cgtmseExposureMasterBeanObj1 = new CGTMSEExposureMasterBean();
			cgtmseExposureMasterBeanObj1.setMliLongName((String) obj7[0]);
			cgtmseExposureMasterBeanObj1.setMliShortName((String) obj7[1]);
			cgtmseExposureMasterBeanObj1.setMliExposureLimit((Long) obj7[2]);
			//Convert Date to String, use format method of SimpleDateFormat
			java.util.Date dateOfSanctionOfExposure = (Date) obj7[3];
			java.util.Date mliValidityOfExposureLimitStartDate = (Date) obj7[4];
			java.util.Date mliValidityOfExposureLimitEndDate = (Date) obj7[5];

			String statusDescription = (String) obj7[6];
			String checkerId = (String) obj7[7];
			java.util.Date checkerRejectedDate = (Date) obj7[8];
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String dateOfSanctionOfExposure1 = dateFormat.format(dateOfSanctionOfExposure);
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
			String mliValidityOfExposureLimitStartDate1 = dateFormat2.format(mliValidityOfExposureLimitStartDate);
			String mliValidityOfExposureLimitEndDate1 = dateFormat2.format(mliValidityOfExposureLimitEndDate);
			cgtmseExposureMasterBeanObj1.setMliDateOfSanctionOfExposure(dateOfSanctionOfExposure1);
			cgtmseExposureMasterBeanObj1.setMliValidityOfExposureLimitStartDate(mliValidityOfExposureLimitStartDate1);
			cgtmseExposureMasterBeanObj1.setMliValidityOfExposureLimitEndDate(mliValidityOfExposureLimitEndDate1);
			cgtmseExposureMasterBeanObj1.setStatusDescription(statusDescription);
			cgtmseExposureMasterBeanObj1.setCheckerId(checkerId);
			SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd/MM/yyyy");
			String checkerRejDate = dateFormat3.format(checkerRejectedDate);
			cgtmseExposureMasterBeanObj1.setCheckerDate(checkerRejDate);
			list7.add(cgtmseExposureMasterBeanObj1);
		}
		ModelAndView modelView = new ModelAndView("cgtmseCreatredExposureLimitDetailsRejectedByChecker");
		modelView.addObject("list7key", list7);
		return modelView;
	}
	
	@RequestMapping(value = "/ExposureListingChecker", method = RequestMethod.GET)
	public ModelAndView getExpoLimitDetails(@ModelAttribute("command") CGTMSEExposureMasterBean cgtmseExposureMasterBeanObj1,BindingResult result, Model modelMsg, HttpSession session){
		String userId = (String) session.getAttribute("userId");
		String Role = (String) session.getAttribute("uRole");
		Map<String, Object> model = new HashMap<String, Object>();
		model = menuUtilsController.prepareMenu(session, Role, userId);
		/*model.put("expoCheckerList",prepareExpotureLimitListofBean(cgtmseCreatedExposureLimitCheckerService.getAllExposureLimitDetails()));
		model.put("adminlist",userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
		model.put("guaranteelist",userActivityService.getActivity("CGTMSECHECKER", "Registration"));
		model.put("applicationList",userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
		model.put("RPaymentList",userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("CGTMSECHECKER","ExposureListingChecker"));
		model.put("repList",
				userActivityService.getReport("CGTMSECHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		model.put("CList",
				userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		*/
		model.put("homePage","cgtmseCheckerHome");
		return new ModelAndView("ExposureListingChecker",model);
		
	}
	
	@RequestMapping(value = "/saveExposureMasterCheckerDetails",params="action2", method = RequestMethod.POST)
	public ModelAndView getExpoLimitDetailsForBack(@ModelAttribute("command") CGTMSEExposureMasterBean cgtmseExposureMasterBeanObj1,BindingResult result, Model modelMsg, HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("expoCheckerList",prepareExpotureLimitListofBean(cgtmseCreatedExposureLimitCheckerService.getAllExposureLimitDetails()));
		model.put("adminlist",userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
		model.put("guaranteelist",userActivityService.getActivity("CGTMSECHECKER", "Registration"));
		model.put("applicationList",userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
		model.put("RPaymentList",userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("CGTMSECHECKER","ExposureListingChecker"));// Added by Say 31 Jan19
		model.put("repList",
				userActivityService.getReport("CGTMSECHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		model.put("CList",
				userActivityService.getReport("CGTMSECHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		model.put("homePage","cgtmseCheckerHome");
		return new ModelAndView("ExposureListingChecker",model);
	}
	
	private List<Object> prepareExpotureLimitListofBean(List<Object[]>list){
		List<Object> beans = null;
		if (list != null && !list.isEmpty()) {
			beans = new ArrayList<Object>();
			CGTMSEExposureMasterBean bean = null;
			for (Object expoVar[] : list){
				CGTMSEExposureMasterCheckerGETExposureDetails member2=(CGTMSEExposureMasterCheckerGETExposureDetails) expoVar[0];
				CGTMSEExposureMasterMLIName member1=(CGTMSEExposureMasterMLIName) expoVar[1];
				java.util.Date dateOfSanctionOfExposure= (Date) member2.getExposureSanctionDate();
				java.util.Date mliValidityOfExposureLimitStartDate=(Date) member2.getFromDate();
				java.util.Date mliValidityOfExposureLimitEndDate=(Date) member2.getToDate();
				
				String SanctionDate="";
				String StartDate="";
				String EndDate="";
				
				SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
				SanctionDate=dateFormat2.format(dateOfSanctionOfExposure);
				StartDate=dateFormat2.format(mliValidityOfExposureLimitStartDate);
				EndDate=dateFormat2.format(mliValidityOfExposureLimitEndDate);
			
				bean = new CGTMSEExposureMasterBean();
				bean.setMliLongName(member1.getMliLongName());
				bean.setMliShortName(member1.getMliShortName());
				bean.setMliExposureLimit(member2.getExposureLimit());
				bean.setMliDateOfSanctionOfExposure(SanctionDate);						
				bean.setStatus(member2.getStatus());
				bean.setStatusDescription(member2.getStatusDescription());
				bean.setMliValidityOfExposureLimitStartDate(StartDate);
				bean.setMliValidityOfExposureLimitEndDate(EndDate);
				bean.setGurantee_fee(member2.getGurantee_fee());
				bean.setPay_out_cap(member2.getPay_out_cap());
				bean.setGuranteeCoverage(member2.getGuranteeCoverage());
				beans.add(bean);
			}
		}
		return beans;
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
