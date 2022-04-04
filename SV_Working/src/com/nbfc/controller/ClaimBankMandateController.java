package com.nbfc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.nbfc.bean.ClaimBankMandateBean;
import com.nbfc.model.ClaimBankMandateBlobModel;
import com.nbfc.model.ClaimBankMandateBlobModelDownLoadPDF;
import com.nbfc.model.MLIInfo;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.ClaimBankMandateService;
import com.nbfc.service.LoginService;
import com.nbfc.service.NPAService;
import com.nbfc.service.UserActivityService;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Hibernate;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.text.DateFormat;
import org.apache.poi.util.IOUtils;







@Controller
public class ClaimBankMandateController {
	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	NBFCPrivilegeMaster userPrvMst;
	ModelAndView modelView;
	String userId=null;
	UserPerivilegeDetails userPri;
	String loginUserMemId=null;
	String memberId=null;
	@Autowired
	ClaimBankMandateService claimBankMandateService;
	@Autowired
	NPAService npaService;
	
	@RequestMapping(value = "ClaimBankMandate", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView showClaimBankMandateForm(@ModelAttribute("command") ClaimBankMandateBean claimBankMandateBean,BindingResult result, HttpSession session) {
	try{
		Map<String, Object> mapObj1 = new HashMap<String, Object>();
		String loginUserId = (String) session.getAttribute("userId");
		String meberId=loginUserId;
		if(loginUserId!=null){
			userPri=lofinService.getUserPrivlageDtl(loginUserId, "A");
			userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
			memberId= npaService.getMemberId(loginUserId);
			if(userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")){
				MLIInfo mliInfo=claimBankMandateService.getMemInfo(memberId);
				System.out.println("MLI Name=="+mliInfo.getLONG_NAME());
				String mliName=mliInfo.getLONG_NAME();
				System.out.println("mliName=="+mliName);
				ClaimBankMandateBean claimBankMandateBeanObj=claimBankMandateService.getSaveBankMandateDetails(memberId);
				if (!claimBankMandateBeanObj.equals(null)||!claimBankMandateBeanObj.equals("")) {
					mapObj1.put("bankmanDateData", claimBankMandateBeanObj);
				}else{
					mapObj1.put("bankmanDateData", claimBankMandateBeanObj);
				}
				mapObj1.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				mapObj1.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
				mapObj1.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
				mapObj1.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
				mapObj1.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
				mapObj1.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
				mapObj1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
				mapObj1.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
				mapObj1.put("actNameHome", "CGTMSE");
				mapObj1.put("homePage", "nbfcMakerHome");
				mapObj1.put("memberId", memberId);
				mapObj1.put("mliName", mliName);
				modelView = new ModelAndView("claimBankManToAddAccount", mapObj1);
				return modelView;
			}else if(userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")){
				Map<String, Object> mapObj2 = new HashMap<String, Object>();
				String memId=memberId;
				ClaimBankMandateBean claimBankMandateBeanObj=claimBankMandateService.getBankMandateDetailsForApprovalorRejection(memId);
				if (!claimBankMandateBeanObj.equals(null)||!claimBankMandateBeanObj.equals("")) {
					mapObj2.put("bankmanDateData", claimBankMandateBeanObj);
					
				} else {
					
					mapObj2.put("recordNotFound", "NO Data Found !!");
				}
				mapObj2.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
				mapObj2.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
				mapObj2.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
				mapObj2.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
				mapObj2.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
				mapObj2.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
				mapObj2.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
				mapObj2.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
				mapObj2.put("actNameHome", "CGTMSE");
				mapObj2.put("homePage", "nbfcMakerHome");
				
				modelView = new ModelAndView("claimBankManToAppAndRej", mapObj2);
				return modelView;
			}else if(userPrvMst.getPrvCreatedModifiedBy().equals("CMAKER")){
				Map<String, Object> mapObj8 = new HashMap<String, Object>();
				String memId=memberId;
				ClaimBankMandateBean claimBankMandateBeanObj=claimBankMandateService.getBankMandateDetailsForApprovalorRejection(memId);
				if (!claimBankMandateBeanObj.equals(null)||!claimBankMandateBeanObj.equals("")) {
					mapObj8.put("bankmanDateData", claimBankMandateBeanObj);
				} else {
					
					mapObj8.put("recordNotFound", "NO Data Found !!");
				}
				mapObj8.put("adminlist", userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
				mapObj8.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
				mapObj8.put("applicationList", userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
				mapObj8.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
				mapObj8.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));
				mapObj8.put("GMaintainlist", userActivityService.getActivity("CGTMSEMAKER", "Guarantee_Maintenance"));
				mapObj8.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
				//mapObj8.put("CBMFList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Bank_Mandate"));
				mapObj8.put("actNameHome", "CGTMSE");
				mapObj8.put("homePage", "nbfcMakerHome");
				mapObj8.put("CBMFList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Bank_Mandate"));
				
				modelView = new ModelAndView("claimBankManToAppAndRejFromCGTMSECC", mapObj8);
				return modelView;
			}else if(userPrvMst.getPrvCreatedModifiedBy().equals("CCHECKER")){
				Map<String, Object> mapObj8 = new HashMap<String, Object>();
				String memId=memberId;
				List<ClaimBankMandateBean> listObj=claimBankMandateService.getBankMandateDetailsForApprovalorRejectionFromCGTMSE();
				if (!listObj.isEmpty()) {
					mapObj8.put("bankmanDateDataList", listObj);
				} else {
					
					mapObj8.put("recordNotFound", "NO Data Found !!");
				}
				
			
				mapObj8.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
				mapObj8.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
				mapObj8.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
				mapObj8.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
				mapObj8.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
				mapObj8.put("GMaintainlist", userActivityService.getActivity("CGTMSECHECKER", "Guarantee_Maintenance"));
				mapObj8.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
				mapObj8.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
				mapObj8.put("actNameHome", "CGTMSE");
				mapObj8.put("homePage", "nbfcMakerHome");
				
				modelView = new ModelAndView("claimBankManToAppAndRejFromCGTMSECC", mapObj8);
				return modelView;
			}
		}else{
			modelView = new ModelAndView("redirect:/SVLogin.html");
		}
		
	}catch(Exception e){
			System.out.println("Exception =="+e);
	}
	
			return modelView;
	
	}
	
	
	

	
	@RequestMapping(value = "saveClaimBankMandateAddedAccountDtlsFromNM", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView saveBankMandateDetails(@ModelAttribute("command") ClaimBankMandateBean claimBankMandateBean,BindingResult result, HttpSession session, Model modelObj,@RequestParam("file") MultipartFile file) {
		ModelAndView modelAndViewObj = null;
		try{
			boolean saveFlagValue=false;
			String displaySuccessMsg="";
			String displayAlreadyExitingMsg="";
			Map<String, Object> mapObj3 = new HashMap<String, Object>();
			String loginUserId = (String) session.getAttribute("userId");
			
			if(loginUserId!=null){
				userPri=lofinService.getUserPrivlageDtl(loginUserId, "A");
				userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				memberId= npaService.getMemberId(loginUserId);
				System.out.println("File:" + file.getName());
				System.out.println("ContentType:" + file.getContentType());
				ClaimBankMandateBlobModel claimBankMandateBlobModelObj=new ClaimBankMandateBlobModel();
				System.out.println("memberId=="+memberId);
				if(userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")){
					saveFlagValue=claimBankMandateService.saveBankMandateDetails(claimBankMandateBean,loginUserId);
					System.out.println("Bank Mandate Details Save inside the NBFC_MEMBER_ACCOUNT_INFO Table=="+saveFlagValue);
					if(saveFlagValue==true){
						try {
							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
							Date date = new Date();
							System.out.println(dateFormat.format(date)); 
							Blob blob = Hibernate.createBlob(file.getInputStream());
							claimBankMandateBlobModelObj.setMEM_ID(claimBankMandateBean.getMemberId());
							claimBankMandateBlobModelObj.setLEGAL_DOCUMENT(blob);
							claimBankMandateBlobModelObj.setMEM_STATUS("NE");
							claimBankMandateBlobModelObj.setNBFC_MK_USER_ID(loginUserId);
							claimBankMandateBlobModelObj.setNBFC_MK_DATE(date);
							claimBankMandateBlobModelObj.setINSERTEDON(date);
							claimBankMandateService.saveBankMandateBlob(claimBankMandateBlobModelObj);
							System.out.println("Bank Mandate Blob Save inside NBFC_MEMBER_ACCOUNT_INFO_BLOB Table=="+saveFlagValue);
							displaySuccessMsg="MANDATE DETAILS  SAVED SUCCESSFULLY. ";
							mapObj3.put("displaySuccessMsg", displaySuccessMsg);
							modelAndViewObj = new ModelAndView("bankMadateSaveSucessFully", mapObj3);
							mapObj3.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
							mapObj3.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
							mapObj3.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
							mapObj3.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
							mapObj3.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
							mapObj3.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
							mapObj3.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
							mapObj3.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
							mapObj3.put("actNameHome", "CGTMSE");
							mapObj3.put("homePage", "nbfcMakerHome");
							return modelAndViewObj;
						
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else{
						displayAlreadyExitingMsg="Record are allready exist.";
						modelAndViewObj = new ModelAndView("bankMadateAlreadyExist", mapObj3);
						mapObj3.put("displayAlreadyExitingMsg", displayAlreadyExitingMsg);
						mapObj3.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
						mapObj3.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
						mapObj3.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
						mapObj3.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
						mapObj3.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
						mapObj3.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
						mapObj3.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
						mapObj3.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
						mapObj3.put("actNameHome", "CGTMSE");
						mapObj3.put("homePage", "nbfcMakerHome");
						return modelAndViewObj;
						
					}
					
				}
			}else{
				modelAndViewObj = new ModelAndView("redirect:/SVLogin.html");
			}
			return modelAndViewObj;
		}catch(Exception e){
				System.out.println("Exception =="+e);
		}
				return modelAndViewObj;
		}
	
	
	@RequestMapping(value = "approveBankMandateByNFCChecker", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView approveBankMandateDtlsByNFCChecker(@ModelAttribute("command") ClaimBankMandateBean claimBankMandateBean,BindingResult result, HttpSession session, Model modelObj) {
		ModelAndView modelAndViewObj = null;
		try{
			String memberId=null;
			Map<String, Object> mapObj4 = new HashMap<String, Object>();
			String loginUserId = (String) session.getAttribute("userId");
			if(loginUserId!=null){
				userPri=lofinService.getUserPrivlageDtl(loginUserId, "A");
				userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				memberId= npaService.getMemberId(loginUserId);
				if(userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")){
					int recordUpdated=claimBankMandateService.approveBankMandateDtlsByNFCChecker(memberId,loginUserId);
					if(recordUpdated==1){
						modelView = new ModelAndView("claimBankManApprovedSucessfullyFromNC", mapObj4);
						mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
						mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
						mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
						mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
						mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
						mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
						mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
						mapObj4.put("actNameHome", "CGTMSE");
						mapObj4.put("homePage", "nbfcMakerHome");
						System.out.println("Record updated");
						//mapObj4.put("recordApproved", "Approved Successfully.");
					}else{
						modelView = new ModelAndView("disClaimBankManAddedAccDtlsToAppAndRejFromNC", mapObj4);
						System.out.println("Record not updated");
						mapObj4.put("recordApproved1", "Allready Approved..");
						modelView = new ModelAndView("claimBankManToAppAndRej", mapObj4);
						mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
						mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
						mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
						mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
						mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
						mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
						mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
						mapObj4.put("actNameHome", "CGTMSE");
						mapObj4.put("homePage", "nbfcMakerHome");
						
					}
					System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111");
					mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
					mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
					mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
					mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
					mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
					mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
					mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
					mapObj4.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
					mapObj4.put("actNameHome", "CGTMSE");
					mapObj4.put("homePage", "nbfcMakerHome");
					
					
					return modelView;
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
	
	@RequestMapping(value = "returnBankMandateByNFCChecker", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView returnBankMandateDtlsByNFCChecker(@ModelAttribute("command") ClaimBankMandateBean claimBankMandateBean,BindingResult result, HttpSession session, Model modelObj) {
		try{
			String memberId=null;
			Map<String, Object> mapObj4 = new HashMap<String, Object>();
			String loginUserId = (String) session.getAttribute("userId");
			if(loginUserId!=null){
				userPri=lofinService.getUserPrivlageDtl(loginUserId, "A");
				userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				memberId= npaService.getMemberId(loginUserId);
				if(userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")){
					int recordUpdated=claimBankMandateService.returnBankMandateDtlsByNFCChecker(memberId,loginUserId,claimBankMandateBean);
					if(recordUpdated==1){
						System.out.println("Record updated");
						mapObj4.put("recordReturn1", "Bank Mandate returned Successfully.");
						modelView = new ModelAndView("bankMandateReturnByNBFCChecker", mapObj4);
						mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
						mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
						mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
						mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
						mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
						mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
						mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
						mapObj4.put("actNameHome", "CGTMSE");
						mapObj4.put("homePage", "nbfcMakerHome");
					}else{
						System.out.println("Record not updated");
						mapObj4.put("recordReturn2", "Bank Mandate returned Allready.");
						modelView = new ModelAndView("claimBankManToAppAndRej", mapObj4);
						mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
						mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
						mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
						mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
						mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
						mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
						mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
						mapObj4.put("actNameHome", "CGTMSE");
						mapObj4.put("homePage", "nbfcMakerHome");
					}
					System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111");
					mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
					mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
					mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
					mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
					mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
					mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
					mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
					mapObj4.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
					mapObj4.put("actNameHome", "CGTMSE");
					mapObj4.put("homePage", "nbfcMakerHome");
					
					return modelView;
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
	
	@RequestMapping(value = "claimBankMandateForUpdation", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView displayClaimBankMandateFormForUpdation(@ModelAttribute("command") ClaimBankMandateBean claimBankMandateBean,BindingResult result, HttpSession session) {
	try{
		Map<String, Object> mapObj6 = new HashMap<String, Object>();
		String loginUserId = (String) session.getAttribute("userId");
		String memberId=null;
		ClaimBankMandateBean claimBankMandateBeanObj1=null;
		if(loginUserId!=null){
			userPri=lofinService.getUserPrivlageDtl(loginUserId, "A");
			userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
			memberId= npaService.getMemberId(loginUserId);
			if(userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")){
				MLIInfo mliInfo=claimBankMandateService.getMemInfo(memberId);
				System.out.println("MLI Name=="+mliInfo.getLONG_NAME());
				String mliName=mliInfo.getLONG_NAME();
				System.out.println("mliName=="+mliName);
				claimBankMandateBeanObj1=claimBankMandateService.getBankMandateDetailsForUpdate(memberId);
				if (!claimBankMandateBeanObj1.equals(null)||!claimBankMandateBeanObj1.equals("")) {
					mapObj6.put("bankmanDateData1", claimBankMandateBeanObj1);
					
				} else {
					mapObj6.put("recordNotFound", "NO Data Found !!");
				
				}
				mapObj6.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				mapObj6.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
				mapObj6.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
				mapObj6.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
				mapObj6.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
				mapObj6.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
				mapObj6.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
				mapObj6.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
				mapObj6.put("actNameHome", "CGTMSE");
				mapObj6.put("homePage", "nbfcMakerHome");
				modelView = new ModelAndView("displayClaimBankMandateFormForUpdate", mapObj6);
				return modelView;
			}
		}else{
			modelView = new ModelAndView("redirect:/SVLogin.html");
		}
	}catch(Exception e){
			System.out.println("Exception =="+e);
	}
	
			return modelView;
	
	}
	
	
	@RequestMapping(value = "updateBankMandateByNbfcMk", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView updateBankMandateDtlsByNbfcMk(@ModelAttribute("command") ClaimBankMandateBean claimBankMandateBean,BindingResult result, HttpSession session, Model modelObj,@RequestParam("file") MultipartFile file) {
		String displayErrorMsg1=null;
		try{
			System.out.println("2222222222222222222222222222222222222222222222222222222222222222");
			String memberId=null;
			Map<String, Object> mapObj5 = new HashMap<String, Object>();
			String loginUserId = (String) session.getAttribute("userId");
			if(loginUserId!=null){
				userPri=lofinService.getUserPrivlageDtl(loginUserId, "A");
				userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				memberId= npaService.getMemberId(loginUserId);
				System.out.println("File:" + file.getName());
				System.out.println("ContentType:" + file.getContentType());
				ClaimBankMandateBlobModel claimBankMandateBlobModelObj2=new ClaimBankMandateBlobModel();
				System.out.println("memberId=="+memberId);
				if(userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")){
					int recordUpdated=claimBankMandateService.updateBankMandateDtlsByNbfcMk(claimBankMandateBean,loginUserId);
					System.out.println("recordUpdated==========="+recordUpdated);
					if(recordUpdated==1){
						try {
							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
							Date date = new Date();
							System.out.println(dateFormat.format(date)); 
							Blob blob = Hibernate.createBlob(file.getInputStream());
							claimBankMandateBlobModelObj2.setMEM_ID(claimBankMandateBean.getMemberId());
							claimBankMandateBlobModelObj2.setLEGAL_DOCUMENT(blob);
							claimBankMandateBlobModelObj2.setMEM_STATUS("NE");
							claimBankMandateBlobModelObj2.setNBFC_MK_USER_ID(loginUserId);
							claimBankMandateBlobModelObj2.setNBFC_MK_DATE(date);
							claimBankMandateBlobModelObj2.setINSERTEDON(date);
							claimBankMandateService.UpdateBankMandateBlob(claimBankMandateBlobModelObj2,memberId);
							System.out.println("Bank Mandate Blob Save inside NBFC_MEMBER_ACCOUNT_INFO_BLOB Table=="+recordUpdated);
							displayErrorMsg1="MANDATE DETAILS  UPDATED SUCCESSFULLY. ";
							mapObj5.put("displayErrorMsg1", "MANDATE DETAILS  UPDATED SUCCESSFULLY.");
							modelView = new ModelAndView("bankMandateUpdatedSuccessfully", mapObj5);
							
						} catch (IOException e) {
							e.printStackTrace();
						}
					}/*else{
						displayErrorMsg1="MANDATE DETAILS ALREADY EXIST and MLI should be allowed to update account details.";
						mapObj5.put("displayErrorMsg2", "MANDATE DETAILS ALREADY EXIST and MLI should be allowed to update account details.");
						modelView = new ModelAndView("displayClaimBankMandateFormForUpdate", mapObj5);
					}*/
					
				}
			}else{
					modelView = new ModelAndView("redirect:/SVLogin.html");
			}
					
					mapObj5.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
					mapObj5.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
					mapObj5.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
					mapObj5.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
					mapObj5.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
					mapObj5.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
					mapObj5.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
					mapObj5.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
					mapObj5.put("actNameHome", "CGTMSE");
					mapObj5.put("homePage", "nbfcMakerHome");
					
					return modelView;

	}catch(Exception e){
		System.out.println("Exception =="+e);
	}

	return modelView;

	}


	
	
	@RequestMapping(value = "downLoadBankMandate", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView downloadClaimBankMandateForm(@ModelAttribute("command") ClaimBankMandateBean claimBankMandateBean,BindingResult result, HttpSession session, Model modelObj,HttpServletResponse response) {
	try{
		String userId = (String) session.getAttribute("userId");
		loginUserMemId = npaService.getMemberId(userId);
		String memberId1=loginUserMemId;
		loginUserMemId = npaService.getMemberId(userId);
		ClaimBankMandateBlobModelDownLoadPDF claimBankMandateBlobModelDownLoadPDFObj=claimBankMandateService.downloadClaimBankMandate(memberId1);
	    response.setContentType("application/pdf");;
        response.setContentLength(claimBankMandateBlobModelDownLoadPDFObj.getLEGAL_DOCUMENT().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + claimBankMandateBlobModelDownLoadPDFObj.getLEGAL_DOCUMENT() +".pdf\"");
        FileCopyUtils.copy( claimBankMandateBlobModelDownLoadPDFObj.getLEGAL_DOCUMENT(), response.getOutputStream());
	
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return null;
	}
	

	@RequestMapping(value = "downLoadBankMandateFormCheckerSide", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView downLoadBankMandateFormCheckerSide(@ModelAttribute("command") ClaimBankMandateBean claimBankMandateBean,BindingResult result, HttpSession session, Model modelObj,HttpServletResponse response,@RequestParam(value = "memberId")String particularMliId) {
	try{
		String userId = (String) session.getAttribute("userId");
		loginUserMemId = npaService.getMemberId(userId);
		String memberId1=loginUserMemId;
		loginUserMemId = npaService.getMemberId(userId);
		ClaimBankMandateBlobModelDownLoadPDF claimBankMandateBlobModelDownLoadPDFObj=claimBankMandateService.downloadClaimBankMandate(particularMliId);
	    response.setContentType("application/pdf");;
        response.setContentLength(claimBankMandateBlobModelDownLoadPDFObj.getLEGAL_DOCUMENT().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + claimBankMandateBlobModelDownLoadPDFObj.getLEGAL_DOCUMENT() +".pdf\"");
        FileCopyUtils.copy( claimBankMandateBlobModelDownLoadPDFObj.getLEGAL_DOCUMENT(), response.getOutputStream());
	
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return null;
	}
	

	
	
	@RequestMapping(value = "getBankMandateDataForApprovalOrRejectionFromCGTMSE", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getBankMandateDataForApprovalOrRejectionFromCGTMSE(@ModelAttribute("command") ClaimBankMandateBean claimBankMandateBean,BindingResult result, HttpSession session, Model modelObj,@RequestParam(value = "memberId")String mliMemeberId) {
		System.out.println("memberIdmemlimberIdmemberIdmemberIdmemberId====="+mliMemeberId);
		
		ModelAndView modelAndViewObj = null;
		try{
			boolean saveFlagValue=false;
			String displaySuccessMsg="";
			String displayAlreadyExitingMsg="";
			Map<String, Object> mapObj3 = new HashMap<String, Object>();
			String loginUserId = (String) session.getAttribute("userId");
			
			if(loginUserId!=null){
				userPri=lofinService.getUserPrivlageDtl(loginUserId, "A");
				userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				memberId= npaService.getMemberId(loginUserId);
				System.out.println("SVLogin CGTMSE memberId=="+memberId);
				if(userPrvMst.getPrvCreatedModifiedBy().equals("CCHECKER")){
					ClaimBankMandateBean claimBankMandateBeanObj=claimBankMandateService.getBankMandateDetailsForApprovalorRejection(mliMemeberId);
					mapObj3.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
					mapObj3.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
					mapObj3.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
					mapObj3.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
					mapObj3.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
					mapObj3.put("GMaintainlist", userActivityService.getActivity("CGTMSECHECKER", "Guarantee_Maintenance"));
					mapObj3.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
					mapObj3.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
					mapObj3.put("actNameHome", "CGTMSE");
					mapObj3.put("homePage", "nbfcMakerHome");
					mapObj3.put("bankmanDateData", claimBankMandateBeanObj);
					modelAndViewObj = new ModelAndView("claimBankMandateDataForApprovalOrRejectionFromCGTMSE", mapObj3);
					return modelAndViewObj;
				}
			}else{
				modelAndViewObj = new ModelAndView("redirect:/SVLogin.html");
			}
			return modelAndViewObj;
		}catch(Exception e){
				System.out.println("Exception =="+e);
		}
				return modelAndViewObj;
		}
	@RequestMapping(value = "approveBankMandateByCGTMSEChecker", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView approveBankMandateDtlsByCGTMSEChecker(@ModelAttribute("command") ClaimBankMandateBean claimBankMandateBean,BindingResult result, HttpSession session, Model modelObj) {
		ModelAndView modelAndViewObj = null;
		try{
			
			String memberId=null;
			Map<String, Object> mapObj4 = new HashMap<String, Object>();
			String loginUserId = (String) session.getAttribute("userId");
			if(loginUserId!=null){
				userPri=lofinService.getUserPrivlageDtl(loginUserId, "A");
				userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				memberId= npaService.getMemberId(loginUserId);
				if(userPrvMst.getPrvCreatedModifiedBy().equals("CCHECKER")){
					String mliMerberId=claimBankMandateBean.getMemberIdHiddenField();
					int recordUpdated=claimBankMandateService.approveBankMandateDtlsByCGTMSEChecker(mliMerberId,loginUserId);
					if(recordUpdated==1){
						System.out.println("Record updated");
						//mapObj4.put("recordApproved", "Approved Successfully.");
						//mapObj4.put("recordApproved", "Approved Successfully.");
						modelView = new ModelAndView("approvedBankMandateByCGTMSECheckerSucessfully", mapObj4);
					}else{
						System.out.println("Record not updated");
						mapObj4.put("recordApproved1", "There is some errror..");
						modelView = new ModelAndView("claimBankMandateDataForApprovalOrRejectionFromCGTMSE", mapObj4);
						mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
						mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
						mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
						mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
						mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
						mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
						mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
						mapObj4.put("actNameHome", "CGTMSE");
						mapObj4.put("homePage", "nbfcMakerHome");
					}
					mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
					mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
					mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
					mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
					mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
					mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
					mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
					mapObj4.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
					mapObj4.put("actNameHome", "CGTMSE");
					mapObj4.put("homePage", "nbfcMakerHome");
					
					
					return modelView;
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
	
	@RequestMapping(value = "returnBankMandateByCGTMSEChecker", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView returnBankMandateDtlsByCGTMSEChecker(@ModelAttribute("command") ClaimBankMandateBean claimBankMandateBean,BindingResult result, HttpSession session, Model modelObj) {
		try{
			
			String memberId=null;
			Map<String, Object> mapObj4 = new HashMap<String, Object>();
			String loginUserId = (String) session.getAttribute("userId");
			if(loginUserId!=null){
				userPri=lofinService.getUserPrivlageDtl(loginUserId, "A");
				userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				memberId= npaService.getMemberId(loginUserId);
				if(userPrvMst.getPrvCreatedModifiedBy().equals("CCHECKER")){
					String mliMerberId=claimBankMandateBean.getMemberIdHiddenField();
					int recordUpdated=claimBankMandateService.returnBankMandateDtlsByCGTMSEChecker(mliMerberId,loginUserId,claimBankMandateBean);
					if(recordUpdated==1){
						System.out.println("Record updated");
						//mapObj4.put("recordReturn1", "Return Successfully");
						modelView = new ModelAndView("returnBankMandateByCGTMSECheckerSucessfully", mapObj4);
						mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
						mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
						mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
						mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
						mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
						mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
						mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
						mapObj4.put("actNameHome", "CGTMSE");
						mapObj4.put("homePage", "nbfcMakerHome");
					}else{
						System.out.println("Record not updated");
						mapObj4.put("recordReturn2", "The is some error");
						modelView = new ModelAndView("claimBankMandateDataForApprovalOrRejectionFromCGTMSE", mapObj4);
						mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
						mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
						mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
						mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
						mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
						mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
						mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
						mapObj4.put("actNameHome", "CGTMSE");
						mapObj4.put("homePage", "nbfcMakerHome");
					}
					mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
					mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
					mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
					mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
					mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
					mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
					mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
					mapObj4.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
					mapObj4.put("actNameHome", "CGTMSE");
					mapObj4.put("homePage", "nbfcMakerHome");
					
					return modelView;
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


	@RequestMapping(value="claimBankMandateFormat")
    public void getLogFile(HttpSession session,HttpServletResponse response) throws Exception {
        try {
        	String fileName="BankMandateFormate.pdf";
            String filePathToBeServed = "C:\\WEB-INF\\JReport\\ireportImage\\";
            File fileToDownload = new File(filePathToBeServed+fileName);
            InputStream inputStream = new FileInputStream(fileToDownload);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename="+fileName); 
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }

    }
	
	@RequestMapping(value = "reportsOfNBFCAccountDetails", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView reportsOfNBFCAccountDetailsByCGTMSEChecker(@ModelAttribute("command") ClaimBankMandateBean claimBankMandateBean,BindingResult result, HttpSession session, Model modelObj) {
		try{
			Map<String, Object> mapObj4 = new HashMap<String, Object>();
			String loginUserId = (String) session.getAttribute("userId");
			if(loginUserId!=null){
				userPri=lofinService.getUserPrivlageDtl(loginUserId, "A");
				userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				memberId= npaService.getMemberId(loginUserId);
				if(userPrvMst.getPrvCreatedModifiedBy().equals("CCHECKER")){
					String mliMerberId=claimBankMandateBean.getMemberIdHiddenField();
					List<ClaimBankMandateBean> claimBankMandateBeanListObj=claimBankMandateService.getReportsOfNBFCAccountDetailsByCGTMSEChecker();
					if (!claimBankMandateBeanListObj.isEmpty()) {
						mapObj4.put("claimBankMandateBeanList", claimBankMandateBeanListObj);
						mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
						mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
						mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
						mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
						mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
						mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
						mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
						mapObj4.put("actNameHome", "CGTMSE");
						mapObj4.put("homePage", "nbfcMakerHome");
					} else {
						
						mapObj4.put("recordNotFound", "NO Data Found !!");
						mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
						mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
						mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
						mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
						mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
						mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
						mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
						mapObj4.put("actNameHome", "CGTMSE");
						mapObj4.put("homePage", "nbfcMakerHome");
					}
					
					mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
					mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
					mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
					mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
					mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
					mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
					mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
					mapObj4.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
					mapObj4.put("actNameHome", "CGTMSE");
					mapObj4.put("homePage", "nbfcMakerHome");
					modelView = new ModelAndView("accountDetailsReport", mapObj4);
					return modelView;
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

	
	@RequestMapping(value = "getBankMandateAccountDtlsReportByCC", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getBankMandateAccountDtlsReportByCGTMSEChecker(@ModelAttribute("command") ClaimBankMandateBean claimBankMandateBean,BindingResult result, HttpSession session, Model modelObj) {
		try{
			Map<String, Object> mapObj4 = new HashMap<String, Object>();
			String loginUserId = (String) session.getAttribute("userId");
			if(loginUserId!=null){
				userPri=lofinService.getUserPrivlageDtl(loginUserId, "A");
				userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				memberId= npaService.getMemberId(loginUserId);
				if(userPrvMst.getPrvCreatedModifiedBy().equals("CCHECKER")){
					String mliMerberId=claimBankMandateBean.getMemberIdHiddenField();
					List<ClaimBankMandateBean> claimBankMandateBeanListObj=claimBankMandateService.getReportsOfNBFCAccountDetailsByCGTMSEChecker();
					if (!claimBankMandateBeanListObj.isEmpty()) {
						mapObj4.put("claimBankMandateBeanList", claimBankMandateBeanListObj);
						mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
						mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
						mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
						mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
						mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
						mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
						mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
						mapObj4.put("actNameHome", "CGTMSE");
						mapObj4.put("homePage", "nbfcMakerHome");
					} else {
						
						mapObj4.put("recordNotFound", "NO Data Found !!");
						mapObj4.put("claimBankMandateBeanList", claimBankMandateBeanListObj);
						mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
						mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
						mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
						mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
						mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
						mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
						mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
						mapObj4.put("actNameHome", "CGTMSE");
						mapObj4.put("homePage", "nbfcMakerHome");
					}
					
					mapObj4.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
					mapObj4.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
					mapObj4.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
					mapObj4.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
					mapObj4.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
					mapObj4.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
					mapObj4.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
					mapObj4.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
					mapObj4.put("actNameHome", "CGTMSE");
					mapObj4.put("homePage", "nbfcMakerHome");
					modelView = new ModelAndView("accountDetailsReport", mapObj4);
					return modelView;
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
	
	@RequestMapping(value = "getUploadedBankMandateForm", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getUploadedBankMandatePDFForm(@ModelAttribute("command") ClaimBankMandateBean claimBankMandateBean,BindingResult result, HttpSession session, Model modelObj,HttpServletResponse response,@RequestParam(value = "memberId")String mliMemeberId) {
	try{
		String userId = (String) session.getAttribute("userId");
		loginUserMemId = npaService.getMemberId(userId);
		String memberId1=loginUserMemId;
		loginUserMemId = npaService.getMemberId(userId);
		ClaimBankMandateBlobModelDownLoadPDF claimBankMandateBlobModelDownLoadPDFObj=claimBankMandateService.downloadClaimBankMandate(mliMemeberId);
	    response.setContentType("application/pdf");;
        response.setContentLength(claimBankMandateBlobModelDownLoadPDFObj.getLEGAL_DOCUMENT().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + claimBankMandateBlobModelDownLoadPDFObj.getLEGAL_DOCUMENT() +".pdf\"");
        FileCopyUtils.copy( claimBankMandateBlobModelDownLoadPDFObj.getLEGAL_DOCUMENT(), response.getOutputStream());
	
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return null;
	}

}