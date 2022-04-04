package com.nbfc.controller;

import java.io.IOException;
import java.sql.Blob;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.ClaimLodgementBean;
import com.nbfc.model.ClaimLodgeBlobModel;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.model.documentModel;
import com.nbfc.service.ClaimLodgementService;
import com.nbfc.service.LoginService;
import com.nbfc.service.NPAService;
import com.nbfc.service.UserActivityService;
import com.nbfc.validator.DataValidation;
@Controller
public class ClaimLodgementController {
	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	NBFCPrivilegeMaster userPrvMst;
	ModelAndView modelView;
	String userId=null;
	UserPerivilegeDetails userPri;
	String loginUserMemId=null;
	@Autowired
	ClaimLodgementService claimLodgementService;
	@Autowired
	NPAService npaService;
	String claimRefNoValue;
	DataValidation dataValidation = new DataValidation();
	@RequestMapping(value = "displayClaimLodgementForm", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView showClaimLodgementInputForm(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj) {
	try{
		
		Map<String, Object> model = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		List<ClaimLodgementBean> claimLodgList = null;
		loginUserMemId = npaService.getMemberId(userId);
       	System.out.println(loginUserMemId);
			 userId=(String)session.getAttribute("userId");
			 if(userId!=null){
					userPri=lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
					if(userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")){
						Map<String, Object> mapObj = new HashMap<String, Object>();
						List<ClaimLodgementBean> claimLodgementBeanObjList=claimLodgementService.getClaimReturnedRecordsByNBFCChecker(loginUserMemId);
						List<ClaimLodgementBean> claimLodgementBeanObjList1=claimLodgementService.getClaimReturnedRecordsByNBFCCheckerCGS(loginUserMemId);
						if (!claimLodgementBeanObjList.isEmpty()) {
							mapObj.put("claimReturnedRecordsList", claimLodgementBeanObjList);
						} else {
							mapObj.put("noDataFound", "NO Data Found !!");
						}
						if (!claimLodgementBeanObjList1.isEmpty()) {
							mapObj.put("claimReturnedRecordsList1", claimLodgementBeanObjList1);
						} else {
							mapObj.put("noDataFound", "NO Data Found !!");
						}
						mapObj.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
						mapObj.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
						mapObj.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
						mapObj.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
						mapObj.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
						mapObj.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
						mapObj.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
						mapObj.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
						mapObj.put("actNameHome", "CGTMSE");
						mapObj.put("homePage", "nbfcMakerHome");
						mapObj.put("memberId", loginUserMemId);
						modelView = new ModelAndView("displayClaimLodgementInputForm", mapObj);
						return modelView;
					}else{
						modelView = new ModelAndView("redirect:/SVLogin.html");
					}
				 }else{
					 modelView = new ModelAndView("redirect:/SVLogin.html");
				 }
			 
			 if(userId!=null){
					userPri=lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
					if(userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")){
						Map<String, Object> mapObj1 = new HashMap<String, Object>();
						mapObj1.put("adminlist", userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
						mapObj1.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
						mapObj1.put("applicationList", userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
						mapObj1.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
						mapObj1.put("GMaintainlist", userActivityService.getActivity("CGTMSEMAKER", "Guarantee_Maintenance"));
						mapObj1.put("ClaimList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
						mapObj1.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));
						mapObj1.put("actNameHome", "CGTMSE");
						
						mapObj1.put("homePage", "nbfcMakerHome");
						mapObj1.put("memberId", loginUserMemId);
						
						modelView = new ModelAndView("displayClaimLodgementInputForm", mapObj1);
						return modelView;
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
	@RequestMapping(value = "claimLodgementInputForm", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView claimLodgementInputForm(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj) {
	try{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> mapObj5 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		 userId=(String)session.getAttribute("userId");
		 if(userId!=null){
				userPri=lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
		loginUserMemId = npaService.getMemberId(userId);
		String cgpan = claimLodgementBean.getCgpan();
		System.out.println("-----------"+cgpan);
		claimLodgementBean=claimLodgementService.checkDuplicateCGPANForClaim(cgpan,userId);
		if(claimLodgementBean.getDupCnt() ==0){
		claimLodgementBean=claimLodgementService.getCGPANForClaim(cgpan,userId);
		        if(claimLodgementBean.getCnt() ==0){
			mapObj5.put("message", "Lock-In period not completed.");
			ClaimLodgementBean claimLodgListObj=claimLodgementService.getMliBorrowerNpaDtlsBeforClaimLodgement(cgpan);
			ClaimLodgementBean claimLodgListObj1=claimLodgementService.getSaveClaimLodgmentData(cgpan);
			ClaimLodgementBean claimLodgListObj2=claimLodgementService.getSaveClaimLodgementCheckListData(cgpan);
			
		//	List<ClaimLodgementBean> claimLodgList=claimLodgementService.getSaveClaimLodgementReturnReasonData(claimLodgListObj2.getClaimRefNo());

						mapObj5.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
						mapObj5.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
						mapObj5.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
						mapObj5.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
						mapObj5.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
						mapObj5.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
						mapObj5.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
						mapObj5.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
						mapObj5.put("actNameHome", "CGTMSE");
//						model.put("reductionReason2",
//								dataValidation.getNPAResion());
						mapObj5.put("homePage", "nbfcMakerHome");
						mapObj5.put("memberId", loginUserMemId);
						mapObj5.put("formData", claimLodgListObj);
						mapObj5.put("formData1", claimLodgListObj1);
						mapObj5.put("formData2", claimLodgListObj2);
					//	mapObj5.put("formData3", claimLodgList);
						modelView = new ModelAndView("displayClaimLodgementInputForm", mapObj5);
						return modelView;
					
		}
		}else if(claimLodgementBean.getDupCnt()==1){
			mapObj5.put("message", " Claim Already Lodged.");
			//ClaimLodgementBean claimLodgListObj=claimLodgementService.getMliBorrowerNpaDtlsBeforClaimLodgement(cgpan);
			//ClaimLodgementBean claimLodgListObj1=claimLodgementService.getSaveClaimLodgmentData(cgpan);
			//ClaimLodgementBean claimLodgListObj2=claimLodgementService.getSaveClaimLodgementCheckListData(cgpan);

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
						mapObj5.put("memberId", loginUserMemId);
						
						//mapObj5.put("formData", claimLodgListObj);
						//mapObj5.put("formData1", claimLodgListObj1);
						//mapObj5.put("formData2", claimLodgListObj2);
						modelView = new ModelAndView("displayClaimLodgementInputForm", mapObj5);
						return modelView;
			
		}
			ClaimLodgementBean claimLodgListObj=claimLodgementService.getMliBorrowerNpaDtlsBeforClaimLodgement(cgpan);
			ClaimLodgementBean claimLodgListObj1=claimLodgementService.getSaveClaimLodgmentData(cgpan);
			ClaimLodgementBean claimLodgListObj2=claimLodgementService.getSaveClaimLodgementCheckListData(cgpan);
		//	List<ClaimLodgementBean> claimLodgList=claimLodgementService.getSaveClaimLodgementReturnReasonData(claimLodgListObj2.getClaimRefNo());
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
						mapObj5.put("memberId", loginUserMemId);
//						model.put("reductionReason2",
//								dataValidation.getNPAResion());
						mapObj5.put("formData", claimLodgListObj);
						mapObj5.put("formData1", claimLodgListObj1);
						mapObj5.put("formData2", claimLodgListObj2);
					//	mapObj5.put("formData3", claimLodgList);
						modelView = new ModelAndView("claimLodgementInputForm", mapObj5);
						return modelView;	
			
			
		
			 
		 }else{
				modelView = new ModelAndView("redirect:/SVLogin.html");
			}
			return modelView;
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return modelView;
	}
	
	@RequestMapping(value = "saveClaimLodgmentDtls", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView saveClaimLodgmentData(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj,@RequestParam("file") MultipartFile file) {
	try{
		
		String claimRefNO=null;
		int claimLodgementCheckListDataSave=0;
		System.out.println("saveClaimLodgmentData method called as part of ClaimLodgementController controller=== and cgPAN=="+claimLodgementBean.getCgpan());
		String cgpanValue=claimLodgementBean.getCgpan();
		Map<String, Object> mapObj5 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		String loginUserId=userId;
		loginUserMemId = npaService.getMemberId(userId);
		String memberId=loginUserMemId;
		System.out.println(loginUserMemId);
		System.out.println("File:" + file.getName());
		System.out.println("ContentType:" + file.getContentType());
		ClaimLodgeBlobModel claimLodgeBlobModelObj=new ClaimLodgeBlobModel();
		claimRefNO=claimLodgementService.saveClaimLodgmentData(claimLodgementBean,memberId,loginUserId);
		System.out.println("Claim Lodg data  save successfully claimRefNO=="+claimRefNO);
		
		if(claimRefNO!=null){
			claimLodgementCheckListDataSave=claimLodgementService.saveClaimLodgementCheckListData(claimLodgementBean,memberId,loginUserId);
			System.out.println("ClaimCheckListData save successfully claimLodgementCheckListDataSave=="+claimLodgementCheckListDataSave);
		}
		
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
			Date date = new Date();
			System.out.println(dateFormat.format(date)); 
			Blob blob = Hibernate.createBlob(file.getInputStream());
			claimLodgeBlobModelObj.setLEGAL_DOCUMENT(blob);
			claimLodgeBlobModelObj.setCLAIM_REF_NO(claimRefNO);
			claimLodgeBlobModelObj.setCGPAN(cgpanValue);
			claimLodgeBlobModelObj.setNBFC_MAKER_ID(loginUserId);
			claimLodgeBlobModelObj.setNBFC_MAKER_DATE(date);
			claimLodgeBlobModelObj.setINSERTEDON(date);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Now Executing saveBlobDocument method to save Blob Data==");
			if(claimRefNO!=null && claimLodgementCheckListDataSave==1){
				claimLodgementService.saveBlobDocument(claimLodgeBlobModelObj,claimRefNO,claimLodgementCheckListDataSave);
				System.out.println("Claim Lodg, CheckList and Blod data save successfully==");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		userId=(String)session.getAttribute("userId");
		if(userId!=null){
			userPri=lofinService.getUserPrivlageDtl(userId, "A");
			userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				if(userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")){
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
					mapObj5.put("memberId", loginUserMemId);
					mapObj5.put("message", "Claim Successfully lodged.ClaimRefNo is  "+claimRefNO);
					modelView = new ModelAndView("claimLodgementsuccessForm", mapObj5);
					return modelView;
				}else{
					modelView = new ModelAndView("redirect:/SVLogin.html");
				}
		 }else{
			 modelView = new ModelAndView("redirect:/SVLogin.html");
		 }
 
		if(userId!=null){
			userPri=lofinService.getUserPrivlageDtl(userId, "A");
			userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
			if(userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")){
				Map<String, Object> mapObj1 = new HashMap<String, Object>();
				mapObj1.put("adminlist", userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
				mapObj1.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
				mapObj1.put("applicationList", userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
				mapObj1.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
				mapObj1.put("GMaintainlist", userActivityService.getActivity("CGTMSEMAKER", "Guarantee_Maintenance"));
				mapObj1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
				mapObj1.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));
				mapObj1.put("actNameHome", "CGTMSE");
				mapObj1.put("homePage", "nbfcMakerHome");
				mapObj1.put("memberId", loginUserMemId);
				modelView = new ModelAndView("claimLodgementInputForm", mapObj1);
				return modelView;
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
	@RequestMapping(value = "claimLodgementCertificate", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView viewClaimLodgementCertificate(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj) {
	try{
		System.out.println("viewClaimLodgementCertificate method called as part of ClaimLodgementController controller==");
		
		Map<String, Object> mapObj6 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		loginUserMemId = npaService.getMemberId(userId);
		System.out.println(loginUserMemId);
		
		 userId=(String)session.getAttribute("userId");
		 if(userId!=null){
				userPri=lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				if(userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")){
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
					mapObj6.put("memberId", loginUserMemId);
					modelView = new ModelAndView("viewClaimLodgementCertificate", mapObj6);
					return modelView;
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
	
	@RequestMapping(value = "claimLodgementCheckList", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView viewClaimLodgementCheckList(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj) {
	try{
		System.out.println("viewClaimLodgementCheckList method called as part of ClaimLodgementController controller==");
		Map<String, Object> mapObj6 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		loginUserMemId = npaService.getMemberId(userId);
		System.out.println(loginUserMemId);
		
		userId=(String)session.getAttribute("userId");
		if(userId!=null){
				userPri=lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				if(userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")){
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
					mapObj6.put("memberId", loginUserMemId);
					modelView = new ModelAndView("viewClaimLodgementCheckList", mapObj6);
					return modelView;
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
	
	
	@RequestMapping(value = "saveClaimLodgementCheckList", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView saveClaimLodgementCheckListData(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj) {
	try{
		System.out.println("saveClaimLodgementCheckListData method called as part of ClaimLodgementController controller=== and cgPAN=="+claimLodgementBean.getCgpan());
		
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> mapObj5 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		loginUserMemId = npaService.getMemberId(userId);
		System.out.println(loginUserMemId);
		
		int claimLodgementCheckListDataSave=claimLodgementService.saveClaimLodgementCheckListData(claimLodgementBean,loginUserMemId, userId);
		System.out.println("claimLodgementCheckListDataSave=="+claimLodgementCheckListDataSave);
		
		
			 userId=(String)session.getAttribute("userId");
			 if(userId!=null){
					userPri=lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
					if(userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")){
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
						mapObj5.put("memberId", loginUserMemId);
						modelView = new ModelAndView("viewClaimLodgementCheckList", mapObj5);
						return modelView;
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
	@RequestMapping(value = "claimLodgedDataForModification", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getclaimLodgedDataForModification(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> mapObj5 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		userId=(String)session.getAttribute("userId");
		 if(userId!=null){
			userPri=lofinService.getUserPrivlageDtl(userId, "A");
			userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
			loginUserMemId = npaService.getMemberId(userId);
			String cgpan = claimLodgementBean.getCgpan();
			//String cgpan="CGN2019000000025";
			System.out.println("-----cgpan 222222222222------"+cgpan);
			ClaimLodgementBean claimLodgListObj=claimLodgementService.getMliBorrowerNpaDtlsBeforClaimLodgement(cgpan);
			ClaimLodgementBean claimLodgListObj1=claimLodgementService.getSaveClaimLodgmentData(cgpan);
			claimRefNoValue=claimLodgListObj1.getClaimRefNo();
			
			ClaimLodgementBean claimLodgListObj2=claimLodgementService.getSaveClaimLodgementCheckListData(cgpan);
			List<ClaimLodgementBean> claimLodgList=claimLodgementService.getSaveClaimLodgementReturnReasonData(claimRefNoValue);
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
			mapObj5.put("memberId", loginUserMemId);
			mapObj5.put("formData", claimLodgListObj);
			mapObj5.put("formData1", claimLodgListObj1);
			mapObj5.put("formData2", claimLodgListObj2);
			mapObj5.put("formData3", claimLodgList);
			mapObj5.put("cgpan", cgpan);
			modelView = new ModelAndView("claimLodgedModificationFormForMLIMaker", mapObj5);
		}
		return modelView;
	}	
	
	//editClaimDataReturnByNBFCMake
	@RequestMapping(value = "editClaimDataReturnByNBFCMaker", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView editClaimDataReturnByNBFCMake(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session,@RequestParam("file") MultipartFile file) {
		Map<String, Object> mapObj5 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		userId=(String)session.getAttribute("userId");
		String MSG ="";
		if(userId!=null){
			userPri=lofinService.getUserPrivlageDtl(userId, "A");
			userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
			loginUserMemId = npaService.getMemberId(userId);
			String memberId=loginUserMemId;
			String loginUserId=userId;
			String claimRefNo = claimRefNoValue;
			int updatedCheckList=0;
			System.out.println("ClaimRefNoValue==="+claimRefNo);
			String cgpan = claimLodgementBean.getCgpan();
			String claimRefNO1=claimLodgementService.updateClaimLodgmentData(claimLodgementBean,claimRefNo, userId);
			System.out.println("Claim Lodg Data Updated Successfully==="+claimRefNO1);
			
			System.out.println("File:" + file.getName());
			System.out.println("ContentType:" + file.getContentType());
			ClaimLodgeBlobModel claimLodgeBlobModelObj=new ClaimLodgeBlobModel();
			try {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
				Date date = new Date();
				System.out.println(dateFormat.format(date)); 
				Blob blob = Hibernate.createBlob(file.getInputStream());
				claimLodgeBlobModelObj.setLEGAL_DOCUMENT(blob);
				claimLodgeBlobModelObj.setCLAIM_REF_NO(claimRefNo);
				claimLodgeBlobModelObj.setCGPAN(cgpan);
				claimLodgeBlobModelObj.setNBFC_MAKER_ID(loginUserId);
				claimLodgeBlobModelObj.setNBFC_MAKER_DATE(date);
				claimLodgeBlobModelObj.setINSERTEDON(date);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(claimRefNo!=null){
				updatedCheckList=claimLodgementService.updateClaimLodgementCheckListData(claimLodgementBean,claimRefNo);
				System.out.println("Claim CheckList Data Updated Successfully==="+updatedCheckList);
			}
			try {
				if(updatedCheckList==1){
					claimLodgementService.saveBlobDocument1(claimLodgeBlobModelObj,claimRefNO1,updatedCheckList);
					System.out.println("Claim Blob Data Updated Successfully===");
					MSG = "Claim Successfully updated. ClaimRefNo is :"+claimRefNo;
				}
			} catch(Exception e) {
				e.printStackTrace();
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
			mapObj5.put("memberId", loginUserMemId);
			mapObj5.put("message", MSG);
			modelView = new ModelAndView("claimLodgementsuccessForm", mapObj5);
		}
		return modelView;
	
	}	
}

