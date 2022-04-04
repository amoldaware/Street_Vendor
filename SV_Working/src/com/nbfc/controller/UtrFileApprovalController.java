package com.nbfc.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nbfc.bean.ClaimPaymentBO;
import com.nbfc.bean.LodgeFreshClaimBO;
import com.nbfc.bean.LodgeFreshUrtBO;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.Login;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserActivity;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.LoginService;
import com.nbfc.service.NBFCUserReportService;
import com.nbfc.service.UrtUploadService;
import com.nbfc.service.UserActivityService;

@Controller
public class UtrFileApprovalController {
final static Logger logger = Logger.getLogger(UtrFileApprovalController.class.getName());
	
	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	NBFCUserReportService nbfcUserReportService;
	@Autowired
	UrtUploadService utrUploadService;
	@Autowired
	MenuUtilsController menuUtilsController;
	
	
	String userId = null;
	Login userDetails = null;

	/* vivek code */
	HttpSession session1 = null;
	NBFCPrivilegeMaster userPrvMst=null;
	String msg=null;
	List<LodgeFreshUrtBO> utrApprocalList = null;
	@RequestMapping(value = "/utrFileApproval", method = RequestMethod.GET)
	public ModelAndView urtFileUploadApproval(Model modeluserRole, HttpSession session) throws BusinessException {
		Map<String, Object> model = new HashMap<String, Object>();
		UserPerivilegeDetails userPri;

		try {
			session1 = session;
			userId = (String) session.getAttribute("userId");
			String Role = (String) session.getAttribute("uRole");
			
			if (userId != null) {
				
				userDetails = lofinService.userDetails(userId);
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				session.setAttribute("userRoleClaimPayment", userPrvMst.getPrvCreatedModifiedBy());
				model = menuUtilsController.prepareMenu(session, Role, userId);
				if (model!=null){
				/*if (userPrvMst.getPrvCreatedModifiedBy().equals("CMAKER")
						|| userPrvMst.getPrvCreatedModifiedBy().equals("CCHECKER")) {

					model.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
					model.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
					model.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
					model.put("applicationList",
							userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
					model.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
					model.put("actName", userActivityService.getActivityName("NBFCMAKER", "mliMaker"));
					model.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
					model.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
					model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
					if (userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")
							|| userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")) {
						model.put("claimUpload", userActivityService.getActivity("NBFCMAKER", "Claim_Upload"));
						model.put("bankMandateFormMli",
								userActivityService.getActivity("NBFCMAKER", "bank_Mandate_Form_Mli"));

					}

					model.put("claimUploadCGTMSE",
							userActivityService.getActivity("CGTMSECHECKER", "claim_upload_cgtmse"));
					model.put("bankMandateFormCgtmscApproval",
							userActivityService.getActivity("CGTMSECHECKER", "bankMandateForm_Cgtmsc_Approval"));
					
					
					List<UserActivity> urtMakerUploadList = null;
					List<UserActivity> claimPaymentList = null;
					List<UserActivity> recoveryReportUploadList = null;
					if (userPrvMst.getPrvCreatedModifiedBy().equals("CMAKER")
							|| userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")) {
						claimPaymentList = userActivityService.getActivity("CGTMSEMAKER", "claim_Payment_Initition");
						urtMakerUploadList = userActivityService.getActivity("CGTMSEMAKER","urt_upload_cgtmse");
						recoveryReportUploadList=userActivityService.getActivity("NBFCMAKER", "recovery_upload");
					} else if (userPrvMst.getPrvCreatedModifiedBy().equals("CCHECKER")
							|| userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")) {
						claimPaymentList = userActivityService.getActivity("CGTMSECHECKER", "claim_Payment_Approval");
						urtMakerUploadList = userActivityService.getActivity("CGTMSECHECKER","urt_upload_cgtmse_Approval");
						recoveryReportUploadList=userActivityService.getActivity("NCHECKER", "recovery_upload_approval");
						
					}
					
					urtMakerUploadList.addAll(claimPaymentList);
					urtMakerUploadList.addAll(recoveryReportUploadList);
					model.put("urtMakerUpload", urtMakerUploadList);


					model.put("homePage", "nbfcMakerHome");*/
					if(Role.equals("CCHECKER")){
						model.put("homePage", "cgtmseCheckerHome");
					}
					return new ModelAndView("utrFileApproval", model);
				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}
	
	
	@RequestMapping(value = "/getUploadUtrApproval", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<LodgeFreshUrtBO>> getAllUtrApprovalData()
			throws BusinessException {
		
		try {
			utrApprocalList = utrUploadService.getAllUtrApproval(userId,userPrvMst.getPrvCreatedModifiedBy());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(utrApprocalList, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(utrApprocalList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save-cgtmse-checker-approval", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> saveCGTMSECheckerApprovalByStatus(@RequestParam("obj") String objString)
			throws BusinessException {
		boolean isSuccess = false;
		String msg = "";
		try {
			Type type = new TypeToken<ArrayList<LodgeFreshUrtBO>>() {
			}.getType();
			ArrayList<LodgeFreshUrtBO> listBO = new Gson().fromJson(objString, type);

			if (listBO != null && !listBO.isEmpty()) {
				if (listBO.get(0).getApprovalAction().equalsIgnoreCase("APPROVE")) {
					msg = "File approved...!";
				} else if (listBO.get(0).getApprovalAction().equalsIgnoreCase("RETURN")) {
					msg = "File return...!";
					
				}
				isSuccess = utrUploadService.saveCheckerApproval(listBO);
				if (isSuccess) {
					return new ResponseEntity<>(msg, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<>("Something Went Wrong.", HttpStatus.BAD_REQUEST);
	}
	

}
