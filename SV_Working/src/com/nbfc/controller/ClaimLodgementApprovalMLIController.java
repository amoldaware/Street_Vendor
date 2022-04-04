package com.nbfc.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*import oracle.net.aso.s;*/

import org.apache.commons.io.IOUtils;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.ASFGenerationDetailBean;
import com.nbfc.bean.ApplicationStatusDetailsBean;
import com.nbfc.bean.CGPANDetailsReportBean;
import com.nbfc.bean.ClaimApprovalBean;
import com.nbfc.bean.ClaimLodgementBean;
import com.nbfc.bean.DANAllocationBean;
//import com.nbfc.form.claimDetailForm;
import com.nbfc.model.CGTMSEMakerAllBatchFileDetailsForwarededByMLI;
import com.nbfc.model.ClaimDetailsModel;
import com.nbfc.model.ClaimReturnReasonsModel;
import com.nbfc.model.DanAllocationForNbfcMakerUsingVWModel;
import com.nbfc.model.MLIName;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.model.documentModel;
import com.nbfc.service.ApplicationStatusService;
import com.nbfc.service.CGPANDetailservice;
import com.nbfc.service.ClaimLodgementService;
import com.nbfc.service.LoginService;
import com.nbfc.service.NPAService;
import com.nbfc.service.UserActivityService;
@Controller
public class ClaimLodgementApprovalMLIController {
	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	NBFCPrivilegeMaster userPrvMst;
	ModelAndView modelView;
	String userId=null;
	UserPerivilegeDetails userPri;
	String loginUserMemId=null;
	MLIName mem_id=null;
	@Autowired
	ClaimLodgementService claimLodgementService;
	@Autowired
	NPAService npaService;
	@Autowired
	UserActivityService UserService;
	@Autowired
	ApplicationStatusService applicationStatusService;
	@Autowired
	CGPANDetailservice CgpanDetailservice;
	
	
	
	@RequestMapping(value = "claimLoadgementApprovalMLI", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView claimLoadgementApprovalMLI(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj) {
	try{
		
		Map<String, Object> mapObj1 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		List<ClaimLodgementBean> claimLodgList = null;
		///loginUserMemId = claimLodgementService.getLoginUserMemId(userId);
		loginUserMemId = npaService.getMemberId(userId);
	
		//List<MLIRegistration> obj =  UserService.getMliLongName(loginUserMemId);
    
		 ClaimLodgementBean list2 = null;
		String Claim_ref_no="";
			 userId=(String)session.getAttribute("userId");
			 List<ClaimLodgementBean> list = claimLodgementService.getClaimLoadgmentDetails(loginUserMemId);
			 List<ClaimLodgementBean> list1 = claimLodgementService.getClaimLoadgmentDetailsCGS(loginUserMemId);
		/*	 for (ClaimLodgementBean bean: list) {
				   System.out.println(bean.getClaimRefNo());
				   Claim_ref_no=bean.getClaimRefNo();
				  list2 = claimLodgementService.getClaimChecklistRecommndation(Claim_ref_no);
				}
			*/
				if (!list.isEmpty()) {
					mapObj1.put("danDetailList", list);
				} else {
					mapObj1.put("noDataFound", "NO Data Found !!");
				}
				if (!list1.isEmpty()) {
					mapObj1.put("danDetailList1", list1);
				} else {
					mapObj1.put("noDataFound", "NO Data Found !!");
				}
			
			 if(userId!=null){
					userPri=lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				//	if(userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")){
						
						mapObj1.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
						mapObj1.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
						mapObj1.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
						mapObj1.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
						mapObj1.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
						mapObj1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
						mapObj1.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
						mapObj1.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
						mapObj1.put("actNameHome", "CGTMSE");
						mapObj1.put("homePage", "nbfcMakerHome");
						mapObj1.put("memberId", loginUserMemId);
						mapObj1.put("MLIID", loginUserMemId);
						mapObj1.put("Danlist", list2);
				
						modelView = new ModelAndView("displayClaimLodgementApprovalList", mapObj1);
						return modelView;
					//}else{
					//	modelView = new ModelAndView("redirect:/SVLogin.html");
					//}
				 }else{
					 modelView = new ModelAndView("redirect:/SVLogin.html");
				 }
			
			return modelView;
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return modelView;
	}
	@RequestMapping(value = "getclaimLoadgeForm", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getclaimLoadgeForm(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj,@RequestParam(value = "claimRefNo")String claimRefNo) {
	try{
		System.out.println("claimLodgementInputForm method called as part of ClaimLodgementController controller=== and claimRefNo=="+claimRefNo);
		
	
		Map<String, Object> mapObj1 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		//List<ClaimLodgementBean> claimLodgList = null;
		///loginUserMemId = claimLodgementService.getLoginUserMemId(userId);
		loginUserMemId = npaService.getMemberId(userId);
		ClaimLodgementBean claimLodgListObj=claimLodgementService.getClaimRefNoDetails(claimRefNo);
		System.out.println(loginUserMemId);
		claimLodgListObj.setClaimRefNo(claimRefNo);
		
			 userId=(String)session.getAttribute("userId");
			 if(userId!=null){
					userPri=lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
					//if(userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")){
						mapObj1.put("adminlist", userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
						mapObj1.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
						mapObj1.put("applicationList", userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
						mapObj1.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
						mapObj1.put("GMaintainlist", userActivityService.getActivity("CGTMSEMAKER", "Guarantee_Maintenance"));
						mapObj1.put("ClaimList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement1"));
						mapObj1.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));
						mapObj1.put("actNameHome", "CGTMSE");
						mapObj1.put("homePage", "nbfcMakerHome");
					//	mapObj1.put("memberId", loginUserMemId);
						mapObj1.put("formData", claimLodgListObj);
						
						modelView = new ModelAndView("claimLodgementDetailForm", mapObj1);
						return modelView;
					//}else{
						//modelView = new ModelAndView("redirect:/SVLogin.html");
					//}
				 }else{
					 modelView = new ModelAndView("redirect:/SVLogin.html");
				 }
			
			return modelView;
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return modelView;
	}
	//method for Claim Approve Or Reject By Checker
	@RequestMapping(value = "mliApproveOrRejectClaim", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView mliCheckerApproveOrRejectClaim(@ModelAttribute("ClaimLodgementBean") ClaimLodgementBean claimbean,BindingResult result, HttpSession session, Model modelObj,HttpServletRequest req,@RequestParam(value = "mli")String mliID){
		System.out.println("Inside MLI CHeckar Approve Or Reject Claim Method");
		String Status="";
		String claimRemarks="";
 		String claimRefNo="";
		 String Final_settelement_Amt = "";
		 String Claim_eligibility_Amt = "";
		String msg="Claim Updated successfully";
		String hidden=claimbean.getClaimStatusRemarkHidden();
		System.out.println("hidden--------------"+hidden);
		Map<String, Object> mapObj1 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		String userRole = (String) session.getAttribute("uRole");
		System.out.println(claimbean.getAcceptReturn());
		System.out.println(claimbean.getClaimRefNo());
          String par=req.getParameter("AcceptReturn");
          System.out.println("yyyyy0------"+par);
		String status=claimbean.getAcceptReturn();
		claimRefNo=claimbean.getClaimRefNo();
		List<String> List4 =null;
		List<String> List5 =null;
	    Final_settelement_Amt=claimbean.getFirstInstallClaimStr();
	    Claim_eligibility_Amt=claimbean.getClaimAmountStr();
	    String unit=claimbean.getUnitName();
		claimRemarks=claimbean.getRemarks();
		System.out.println("Return remarks=="+claimRemarks);
	//	System.out.println("final_amt=="+Final_settelement_Amt);
		
		System.out.println(claimRemarks);
		ClaimApprovalBean bean;
		List<String> List2 = new ArrayList<String>(Arrays.asList(status.split(",")));
		List<String> List1 = new ArrayList<String>(Arrays.asList(claimRefNo.split(",")));
		List<String> List3 = new ArrayList<String>(Arrays.asList(claimRemarks.split(",")));
		if(Final_settelement_Amt!=null){
		List4 = new ArrayList<String>(Arrays.asList(Final_settelement_Amt.split(",")));
		
		}
		if(Claim_eligibility_Amt!=null){
			List5 = new ArrayList<String>(Arrays.asList(Claim_eligibility_Amt.split(",")));
			
			}
			
		Map<String, Object> claimStatusMapObj = new HashMap<String, Object>();
		
		
			
		for(int i=0;i<List1.size();i++){
			bean=new ClaimApprovalBean();	
			if(!List1.isEmpty()){
				  List1.get(i); 
				  bean.setMLI_STATUS(List2.get(i));
				  
			  }	else{
				  bean.setMLI_STATUS("");  
			  }
			
		  if(!List2.isEmpty()){
			  List2.get(i); 
			  
			  bean.setMLI_STATUS(List2.get(i));
			//  claimLodgementService.validateClaimAccptReturn(userId, claimStatusMapObj,userRole); 
		  }	else{
			  bean.setMLI_STATUS("");  
		  }
		  if(!List3.isEmpty()){	
			  	if(List3.get(i).length()>0){
				  	if(!List3.get(i).equals("")||!List3.get(i).equals(null)){
					  bean.setMLI_REMARK(List3.get(i));  
			  		}else{
							bean.setMLI_REMARK("Enter Remarks");  
					}
			  	}
		  }
		  if(userRole.equals("CCHECKER")){
		  if(!List4.isEmpty()){
			  List4.get(i); 
			  
			  bean.setFINAL_SETTELEMENT_AMT(List4.get(i));
			//  claimLodgementService.validateClaimAccptReturn(userId, claimStatusMapObj,userRole); 
		  }	else{
			  bean.setFINAL_SETTELEMENT_AMT("0.0");  
		  }
		  if(!List5.isEmpty()){
			  List5.get(i); 
			  
			  bean.setACTUAL_CLAIM_AMT(List5.get(i));
			//  claimLodgementService.validateClaimAccptReturn(userId, claimStatusMapObj,userRole); 
		  }	else{
			  bean.setACTUAL_CLAIM_AMT("0.0");  
		  }
		  }
		  
		  
		  
			claimStatusMapObj.put(List1.get(i), bean);
		}
		
		
		ClaimLodgementBean Claimbean=claimLodgementService.updateStatusClaimLodgeApprovedReturn(userId, claimStatusMapObj,userRole);
		
		String MSG=Claimbean.getMsg();
		String RMSG=Claimbean.getRMsg();
		loginUserMemId = npaService.getMemberId(userId);
		
		 if(userId!=null){
				userPri=lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				if(userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")){
					mapObj1.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
					mapObj1.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
					mapObj1.put("applicationList", userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
					mapObj1.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
					mapObj1.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));
					mapObj1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
					mapObj1.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
					mapObj1.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
					mapObj1.put("actNameHome", "CGTMSE");
					mapObj1.put("homePage", "nbfcMakerHome");
					mapObj1.put("memberId", loginUserMemId);
					mapObj1.put("messageR", RMSG);
			        mapObj1.put("message", MSG);
			        modelView = new ModelAndView("successclaimLoadgementApprovalMLI",mapObj1);
				//	modelView = new ModelAndView("displayClaimLodgementApprovalList", mapObj1);
					return modelView;
				}else if(userPrvMst.getPrvCreatedModifiedBy().equals("CCHECKER")){
				//	 List<ClaimLodgementBean> list = claimLodgementService.getClaimLoadgmentDetailsForApproval(mliID);
					
					//mapObj1.put("danDetailList", list);
					mapObj1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
					mapObj1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
					mapObj1.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
					mapObj1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
					mapObj1.put("GMaintainlist", userActivityService.getActivity("CGTMSECHECKER", "Guarantee_Maintenance"));
					mapObj1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
					mapObj1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
					mapObj1.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
					mapObj1.put("actNameHome", "CGTMSE");
					mapObj1.put("homePage", "nbfcCheckerHome");
					mapObj1.put("memberId", loginUserMemId);
				    mapObj1.put("messageR", RMSG);
				    mapObj1.put("message", MSG);
			        mapObj1.put("MLIID", loginUserMemId);
					modelView = new ModelAndView("successclaimLoadgementApprovalMLI", mapObj1);
			     //	modelView = new ModelAndView("redirect:/claimLoadgementApprovalCGTMSEMLIWise.html",mapObj1);
					return modelView;
				}
			 }else{
				 modelView = new ModelAndView("redirect:/SVLogin.html");
			 }
		
		return modelView;
		
	}
	@RequestMapping(value = "claimLoadgementApprovalCGTMSE", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView claimLoadgementApprovalCGTMSE(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj,@RequestParam(value = "MLIName")String MLI,@RequestParam(value = "CNT")String CNT) {
	try{
		
		Map<String, Object> mapObj1 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		List<ClaimLodgementBean> claimLodgList = null;
		///loginUserMemId = claimLodgementService.getLoginUserMemId(userId);
		loginUserMemId = npaService.getMemberId(userId);
		mem_id = UserService.getBankID(MLI);
		String MLIID= mem_id.getBnkId() + mem_id.getZneID()
				+ mem_id.getBrnName();
       	System.out.println(CNT);
		
		
			 userId=(String)session.getAttribute("userId");
			
			 List<ClaimLodgementBean> list = claimLodgementService.getClaimLoadgmentDetailsForApproval(MLIID,CNT);
				if (!list.isEmpty()) {
					mapObj1.put("danDetailList", list);
				} else {
					mapObj1.put("noDataFound", "NO Data Found !!");
				}
			
			 if(userId!=null){
					userPri=lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
					if(userPrvMst.getPrvCreatedModifiedBy().equals("CCHECKER")){
						
						mapObj1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
						mapObj1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
						mapObj1.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
						mapObj1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
						mapObj1.put("GMaintainlist", userActivityService.getActivity("CGTMSECHECKER", "Guarantee_Maintenance"));
						mapObj1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
						mapObj1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
						mapObj1.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
						mapObj1.put("actNameHome", "CGTMSE");
						mapObj1.put("homePage", "nbfcMakerHome");
						mapObj1.put("memberId", loginUserMemId);
						mapObj1.put("MLIID", MLIID);
						mapObj1.put("CNT", CNT);
						
						modelView = new ModelAndView("displayClaimLodgementApprovalListCGS1", mapObj1);
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
	@RequestMapping(value = "claimLoadgementApprovalCGTMSEMLIWise", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView claimLoadgementApprovalCGTMSEMLIWise(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj) {
	try{
		
		Map<String, Object> mapObj1 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		List<ClaimLodgementBean> claimLodgList = null;
		///loginUserMemId = claimLodgementService.getLoginUserMemId(userId);
		loginUserMemId = npaService.getMemberId(userId);
		
       	System.out.println(loginUserMemId);
		
		
			 userId=(String)session.getAttribute("userId");
			 List<ClaimLodgementBean> list = claimLodgementService.claimLoadgementApprovalCGTMSEMLIWise(userId);
				if (!list.isEmpty()) {
					mapObj1.put("danDetailList", list);
				} else {
					mapObj1.put("noDataFound", "NO Data Found !!");
				}
			
			 if(userId!=null){
					userPri=lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
					if(userPrvMst.getPrvCreatedModifiedBy().equals("CCHECKER")){
						
						mapObj1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
						mapObj1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
						mapObj1.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
						mapObj1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
						mapObj1.put("GMaintainlist", userActivityService.getActivity("CGTMSECHECKER", "Guarantee_Maintenance"));
						mapObj1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
						mapObj1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
						mapObj1.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
						mapObj1.put("actNameHome", "CGTMSE");
						mapObj1.put("homePage", "nbfcMakerHome");
						mapObj1.put("memberId", loginUserMemId);
						
						modelView = new ModelAndView("displayClaimLodgementApprovalListCGS", mapObj1);
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
	@RequestMapping(value = "getChecklistDeclaration", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getChecklistDeclaration(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj,@RequestParam(value = "claimRefNo")String claimRefNo) {
	try{
		
		Map<String, Object> mapObj1 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		List<ClaimLodgementBean> claimLodgList = null;
		///loginUserMemId = claimLodgementService.getLoginUserMemId(userId);
		loginUserMemId = npaService.getMemberId(userId);
		//System.out.println(MLIID);
       //	System.out.println(unitName);
       //	System.out.println(osAmt);
      // 	System.out.println(claimRefNo);
		
		
			 userId=(String)session.getAttribute("userId");
			 ClaimLodgementBean claimLodgListObj = claimLodgementService.getClaimChecklistDetails(claimRefNo);
			 
			
			 if(userId!=null){
					userPri=lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
					//if(userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")){
						
						mapObj1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
						mapObj1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
						mapObj1.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
						mapObj1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
						mapObj1.put("GMaintainlist", userActivityService.getActivity("CGTMSECHECKER", "Guarantee_Maintenance"));
						mapObj1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
						mapObj1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
						mapObj1.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
						mapObj1.put("actNameHome", "CGTMSE");
						mapObj1.put("homePage", "nbfcMakerHome");
						mapObj1.put("memberId", loginUserMemId);
						//mapObj1.put("MLIID", MLIID);
						mapObj1.put("danDetailList", claimLodgListObj);
						
						modelView = new ModelAndView("claimLodgementCheckList", mapObj1);
						return modelView;
					//}else{
					//	modelView = new ModelAndView("redirect:/SVLogin.html");
					//}
				 }else{
					 modelView = new ModelAndView("redirect:/SVLogin.html");
				 }
			
			return modelView;
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return modelView;
	}
	@RequestMapping(value = "getChecklistDeclRecommand", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getChecklistDeclRecommand(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj,@RequestParam(value = "claimRefNo")String claimRefNo) {
	try{
		
		Map<String, Object> mapObj1 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		List<ClaimLodgementBean> claimLodgList = null;
		///loginUserMemId = claimLodgementService.getLoginUserMemId(userId);
		loginUserMemId = npaService.getMemberId(userId);
		//System.out.println(MLIID);
       //	System.out.println(unitName);
       //	System.out.println(osAmt);
      // 	System.out.println(claimRefNo);
		
		
			 userId=(String)session.getAttribute("userId");
			 ClaimLodgementBean claimLodgListObj = claimLodgementService.getClaimChecklistRecommndation(claimRefNo);
			 
			
			 if(userId!=null){
					userPri=lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
					//if(userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")){
						
						mapObj1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
						mapObj1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
						mapObj1.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
						mapObj1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
						mapObj1.put("GMaintainlist", userActivityService.getActivity("CGTMSECHECKER", "Guarantee_Maintenance"));
						mapObj1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
						mapObj1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
						mapObj1.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
						mapObj1.put("actNameHome", "CGTMSE");
						mapObj1.put("homePage", "nbfcMakerHome");
						mapObj1.put("memberId", loginUserMemId);
						//mapObj1.put("MLIID", MLIID);
						mapObj1.put("danDetailList", claimLodgListObj);
						
						modelView = new ModelAndView("claimLodgCheckListRecomd", mapObj1);
						return modelView;
					//}else{
					//	modelView = new ModelAndView("redirect:/SVLogin.html");
					//}
				 }else{
					 modelView = new ModelAndView("redirect:/SVLogin.html");
				 }
			
			return modelView;
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return modelView;
	}
	@RequestMapping(value = "DownloadBlobDoc", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView DownloadBlobDoc(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj,@RequestParam(value = "claimRefNo")String claimRefNo,HttpServletResponse response) {
	try{
		
		Map<String, Object> mapObj1 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		List<ClaimLodgementBean> claimLodgList = null;
		loginUserMemId = npaService.getMemberId(userId);
       	System.out.println(claimRefNo);
			
       	documentModel doc=claimLodgementService.DownloadBlobDoc(claimRefNo);
		//documentModel doc = documentDao.getDoc(documentId);
		
			    response.setContentType("application/pdf");
		        response.setContentLength(doc.getLEGAL_DOCUMENT().length);
		        response.setHeader("Content-Disposition","attachment; filename=\"" + doc.getCLAIM_REF_NO() +".pdf\"");
		  
		        FileCopyUtils.copy(doc.getLEGAL_DOCUMENT(), response.getOutputStream());
			
			
		/*	response.setHeader("Content-Disposition", "inline;filename=\""+"Download_" +doc.getCLAIM_REF_NO()+".pdf"+ "\"");
			
			OutputStream out = response.getOutputStream();
        	response.setContentType(".pdf");
			IOUtils.copy(doc.getLEGAL_DOCUMENT().getBinaryStream(), out);
			out.flush();
			out.close();*/
		
		
		
		
		//return null;	 
	
		//	return null;
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return null;
	}
	
	@RequestMapping(value = "getRecommandationCGS", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getRecommandationCGS(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj,@RequestParam(value = "claimRefNo")String claimRefNo,@RequestParam(value = "MLIID")String MLIID,@RequestParam(value = "CNT")String CNT ) {
	try{
		
		Map<String, Object> mapObj1 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		List<ClaimLodgementBean> claimLodgList = null;
		///loginUserMemId = claimLodgementService.getLoginUserMemId(userId);
		loginUserMemId = npaService.getMemberId(userId);
		String mem_bnk_id=MLIID.substring(0, 4);
		
		MLIName MLIName = claimLodgementService.getMLIName(mem_bnk_id);
		//claimLodgementBean.setMliName(MLIName.getMliLName());
		System.out.println(MLIName.getMliLName());
       	System.out.println(CNT);
       //	System.out.println(osAmt);
      // 	System.out.println(claimRefNo);
		
		
			 userId=(String)session.getAttribute("userId");
			 ClaimLodgementBean claimLodgListObj = claimLodgementService.getRecommandationCGS(claimRefNo);
			 
			
			 if(userId!=null){
					userPri=lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
					//if(userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")){
						
						mapObj1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
						mapObj1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
						mapObj1.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
						mapObj1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
						mapObj1.put("GMaintainlist", userActivityService.getActivity("CGTMSECHECKER", "Guarantee_Maintenance"));
						mapObj1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
						mapObj1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
						mapObj1.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
						mapObj1.put("actNameHome", "CGTMSE");
						mapObj1.put("homePage", "nbfcMakerHome");
						mapObj1.put("memberId", loginUserMemId);
						mapObj1.put("MLIID", MLIName.getMliLName());
						mapObj1.put("CNT", CNT);
						mapObj1.put("danDetailList", claimLodgListObj);
						
						modelView = new ModelAndView("claimRecommendationCGS", mapObj1);
						return modelView;
					//}else{
					//	modelView = new ModelAndView("redirect:/SVLogin.html");
					//}
				 }else{
					 modelView = new ModelAndView("redirect:/SVLogin.html");
				 }
			
			return modelView;
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return modelView;
	}
	@RequestMapping(value = "getChecklistDeclarationCGS", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getChecklistDeclarationCGS(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj,@RequestParam(value = "claimRefNo")String claimRefNo,@RequestParam(value = "MLIID")String MLIID,@RequestParam(value = "CNT")String CNT,@RequestParam(value = "AMOUNT")String AMOUNT) {
	try{
		
		Map<String, Object> mapObj1 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		List<ClaimLodgementBean> claimLodgList = null;
		///loginUserMemId = claimLodgementService.getLoginUserMemId(userId);
		loginUserMemId = npaService.getMemberId(userId);
       String mem_bnk_id=MLIID.substring(0, 4);
		
		MLIName MLIName = claimLodgementService.getMLIName(mem_bnk_id);
		//claimLodgementBean.setMliName(MLIName.getMliLName());
		System.out.println(MLIName.getMliLName());
       	System.out.println(CNT);
		//System.out.println(MLIID);
       //	System.out.println(unitName);
       //	System.out.println(osAmt);
      // 	System.out.println(claimRefNo);
		
		
			 userId=(String)session.getAttribute("userId");
			  ClaimLodgementBean claimLodgListObj = claimLodgementService.getClaimChecklistDetails(claimRefNo);
              ClaimLodgementBean claimLodgListObj1=claimLodgementService.getClaimRefNoDetails(claimRefNo);
              ClaimLodgementBean claimLodgListObj2=claimLodgementService.getClaimCheckerDetails(claimRefNo);
			 if(userId!=null){
					userPri=lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
					//if(userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")){
						
						mapObj1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
						mapObj1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
						mapObj1.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
						mapObj1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
						mapObj1.put("GMaintainlist", userActivityService.getActivity("CGTMSECHECKER", "Guarantee_Maintenance"));
						mapObj1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
						mapObj1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
						mapObj1.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
						mapObj1.put("actNameHome", "CGTMSE");
						mapObj1.put("homePage", "nbfcMakerHome");
						mapObj1.put("memberId", loginUserMemId);
						mapObj1.put("MLIID", MLIName.getMliLName());
						mapObj1.put("CNT", CNT);
						mapObj1.put("AMOUNT", AMOUNT);
						mapObj1.put("danDetailList", claimLodgListObj);
						mapObj1.put("claimDetailList", claimLodgListObj1);
						mapObj1.put("claimDetailList1", claimLodgListObj2);
						
						modelView = new ModelAndView("claimLodgementCheckListCGS", mapObj1);
						return modelView;
					//}else{
					//	modelView = new ModelAndView("redirect:/SVLogin.html");
					//}
				 }else{
					 modelView = new ModelAndView("redirect:/SVLogin.html");
				 }
			
			return modelView;
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return modelView;
	}
	@RequestMapping(value = "getclaimLoadgeFormCGS", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getclaimLoadgeFormCGS(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj,@RequestParam(value = "claimRefNo")String claimRefNo,@RequestParam(value = "MLIID")String MLIID,@RequestParam(value = "CNT")String CNT) {
	try{
		System.out.println("claimLodgementInputForm method called as part of ClaimLodgementController controller=== and claimRefNo=="+claimRefNo);
		ApplicationStatusDetailsBean appBean;
	
		Map<String, Object> mapObj1 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		//List<ClaimLodgementBean> claimLodgList = null;
		///loginUserMemId = claimLodgementService.getLoginUserMemId(userId);
		loginUserMemId = npaService.getMemberId(userId);
      String mem_bnk_id=MLIID.substring(0, 4);
		
		MLIName MLIName = claimLodgementService.getMLIName(mem_bnk_id);
		//claimLodgementBean.setMliName(MLIName.getMliLName());
		System.out.println(MLIName.getMliLName());
       	System.out.println(CNT);
		ClaimLodgementBean claimLodgListObj=claimLodgementService.getClaimRefNoDetails(claimRefNo);
		String loanAccNo=claimLodgListObj.getLoanAccNo();
		String CGPAN=claimLodgListObj.getCgpan();
		appBean = applicationStatusService.getapplicationDetails(loanAccNo);
		
		//List<CGPANDetailsReportBean> cgpanlist =new ArrayList<CGPANDetailsReportBean>();
		List<CGPANDetailsReportBean> cgpanlist=CgpanDetailservice.getCgpanDetailsReport(CGPAN);
		/*Iterator<Object> itr1= list.iterator();
		while(itr1.hasNext()){
			String appDate="";
			CGPANDetailsReportBean bean1 = new CGPANDetailsReportBean();
			Object[] obj1 = (Object[]) itr1.next();
			
			String Cgpan=(String) obj1[0];
			String memid=(String) obj1[1];
			String mse_name=(String) obj1[2];
			String danid=(String) obj1[3];
			Double gurAmt = ((BigDecimal) obj1[4]).doubleValue();
			Double osAmt = ((BigDecimal) obj1[5]).doubleValue();
			Double Dan_amt = ((BigDecimal) obj1[6]).doubleValue();
			double gurAmt=Double.valueOf((Double)obj1[4]);
			double osAmt=Double.valueOf((Double)obj1[5]);
			double Dan_amt=Double.valueOf((Double)obj1[6]);
			String payid=(String) obj1[7];
		    Date appropriationDate=   (Date) obj1[8];
			String approp_by=(String) obj1[9];
			Date guaranteStart_date=   (Date) obj1[10];
			String tenureinmonths=(String) obj1[11];
			int tenure=Integer.parseInt(tenureinmonths);
		
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date appropriationDate=   (Date) obj1[11];
            if(appropriationDate!=null){
            		
    			String appropriationDate1 = df.format(appropriationDate);
    			System.out.println("appropriationDate1==="+appropriationDate1);
    			String splitD[]=appropriationDate1.split("/");
    			System.out.println("----------dateaaa"+splitD[0]+"/"+splitD[1]+"/"+splitD[2]);
    			 appDate=splitD[0]+"/"+splitD[1]+"/"+splitD[2];
				
			}
			
		   //---------------------------Expiry date formula-------------------------------------
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(guaranteStart_date);
	        cal.add(Calendar.MONTH, tenure);
	        String insertdate = df.format(cal.getTime());
	        ///--------------------------CrystalizationDate formula----------------------------------------
	       
	        Calendar calendar = new GregorianCalendar();
	    	calendar.setTime(guaranteStart_date);
	    	int factor = 0;
	    	int month = calendar.get(Calendar.MONTH);
	    	if (month == Calendar.JANUARY
	    		|| month == Calendar.APRIL
	    		|| month == Calendar.JULY
	    		|| month == Calendar.OCTOBER) {
	    		factor = 2;
	    	} else if (
	    		month == Calendar.FEBRUARY
	    		|| month == Calendar.MAY
	    		|| month == Calendar.AUGUST
	    		|| month == Calendar.NOVEMBER) {
	    		factor = 1;
	    		} else {
	    			factor = 0;
	    	}
	    	calendar.add(Calendar.MONTH, factor);
	    	calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
	        String CrystalizationDate = df.format(calendar.getTime());
	        //-------------------------------------------------------------------------------------
			bean1.setDisplayCgpen(CGPAN);
			bean1.setDanId(danid);
			bean1.setPayId(payid);
			bean1.setMliId(memid);
			bean1.setOutstandingAmt(Math.round(osAmt));
			bean1.setDciAppropriationDate(appDate);
			bean1.setAppropriation_by(approp_by);
			bean1.setSsiName(mse_name);
			bean1.setGuaranteeAmt(gurAmt);
			bean1.setExpiryDate(insertdate);
			bean1.setCrystalizationDate(CrystalizationDate);
			bean1.setDan_Amt(Dan_amt);
			
			
	    cgpanlist.add(bean1);
	
}*/
	
		System.out.println(loginUserMemId);
		claimLodgListObj.setClaimRefNo(claimRefNo);
		
			 userId=(String)session.getAttribute("userId");
			 if(userId!=null){
					userPri=lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
					//if(userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")){
						mapObj1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
						mapObj1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
						mapObj1.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
						mapObj1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
						mapObj1.put("GMaintainlist", userActivityService.getActivity("CGTMSECHECKER", "Guarantee_Maintenance"));
						mapObj1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
						mapObj1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
						mapObj1.put("actNameHome", "CGTMSE");
						mapObj1.put("homePage", "nbfcMakerHome");
						mapObj1.put("MLIID", MLIName.getMliLName());
						mapObj1.put("CNT", CNT);
						mapObj1.put("formData", claimLodgListObj);
						mapObj1.put("applicationDetails", appBean);
						mapObj1.put("cgpenList", cgpanlist);
						modelView = new ModelAndView("claimLodgementDetailFormCGS", mapObj1);
						return modelView;
					//}else{
						//modelView = new ModelAndView("redirect:/SVLogin.html");
					//}
				 }else{
					 modelView = new ModelAndView("redirect:/SVLogin.html");
				 }
			
			return modelView;
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return modelView;
	}
	@RequestMapping(value = "getReturnreasons", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getReturnreasons(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj,@RequestParam(value = "claimRefNo")String claimRefNo,@RequestParam(value = "MLIID")String MLIID,@RequestParam(value = "CNT")String CNT) {
	try{
		System.out.println("claimLodgementInputForm method called as part of ClaimLodgementController controller=== and claimRefNo=="+claimRefNo);
		
	
		Map<String, Object> mapObj1 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		//List<ClaimLodgementBean> claimLodgList = null;
		///loginUserMemId = claimLodgementService.getLoginUserMemId(userId);
		loginUserMemId = npaService.getMemberId(userId);
        String mem_bnk_id=MLIID.substring(0, 4);
	    session.setAttribute("claimRefNo", claimRefNo);
		MLIName MLIName = claimLodgementService.getMLIName(mem_bnk_id);
		//claimLodgementBean.setMliName(MLIName.getMliLName());
		System.out.println(MLIName.getMliLName());
       	System.out.println(CNT);
       session.setAttribute("MLINameID", MLIName.getMliLName());
       session.setAttribute("CNT12", CNT);
        List<ClaimLodgementBean> list =claimLodgementService.getClaimReturnReasons();
		System.out.println(loginUserMemId);
//		claimLodgListObj.setClaimRefNo(claimRefNo);
		
			 userId=(String)session.getAttribute("userId");
			 if(userId!=null){
					userPri=lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
					//if(userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")){
						mapObj1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
						mapObj1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
						mapObj1.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
						mapObj1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
						mapObj1.put("GMaintainlist", userActivityService.getActivity("CGTMSECHECKER", "Guarantee_Maintenance"));
						mapObj1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
						mapObj1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
						mapObj1.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
						mapObj1.put("actNameHome", "CGTMSE");
						mapObj1.put("homePage", "nbfcMakerHome");
						mapObj1.put("MLIID", MLIName.getMliLName());
						mapObj1.put("CNT", CNT);
						mapObj1.put("claimRefNo", claimRefNo);
						
						mapObj1.put("danDetailList", list);
						
						modelView = new ModelAndView("ClaimReturnReasons", mapObj1);
						return modelView;
					//}else{
						//modelView = new ModelAndView("redirect:/SVLogin.html");
					//}
				 }else{
					 modelView = new ModelAndView("redirect:/SVLogin.html");
				 }
			
			return modelView;
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return modelView;
	}
	@RequestMapping(value = "submitForClaimReturnResons", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView submitForClaimReturnResons(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj) {
	try{
		 String mli=  (String) session.getAttribute("MLINameID");
		 String cnt=(String) session.getAttribute("CNT12");
		
		ClaimReturnReasonsModel claimobj=new ClaimReturnReasonsModel();
		ClaimLodgementBean bean =new ClaimLodgementBean ();
		Map<String, Object> mapObj1 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		List<ClaimLodgementBean> claimLodgList =null;
		///loginUserMemId = claimLodgementService.getLoginUserMemId(userId);
		loginUserMemId = npaService.getMemberId(userId);
		String returnRemark=claimLodgementBean.getReturnReasonsRemarks();
		
		String []arr={};
    	List<String> data=claimLodgementBean.getChcktbl();
    	claimLodgList = new ArrayList<ClaimLodgementBean>();
    	if(data != null){
    		for(String dt:data){
    			arr=dt.split("@");
    		
    		 	 bean = new ClaimLodgementBean();
    				bean.setRemarkID(arr[1]);
    				bean.setClaimRefNo(arr[0]);
    				bean.setUserName(userId);
    				claimLodgList.add(bean);
    		}	
    	}
		
//		if(arr.length>0){
//		  
//			//claimLodgList.add(bean);
//			
//		}
       String count =claimLodgementService.submitForClaimReturnResons(claimLodgList,returnRemark);
       List<ClaimLodgementBean> list =claimLodgementService.getClaimReturnReasons();
		System.out.println(loginUserMemId);
//		claimLodgListObj.setClaimRefNo(claimRefNo);
		
			 userId=(String)session.getAttribute("userId");
			 if(userId!=null){
					userPri=lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
					//if(userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")){
						mapObj1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
						mapObj1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
						mapObj1.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
						mapObj1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
						mapObj1.put("GMaintainlist", userActivityService.getActivity("CGTMSECHECKER", "Guarantee_Maintenance"));
						mapObj1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
						mapObj1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
						mapObj1.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
						mapObj1.put("actNameHome", "CGTMSE");
						mapObj1.put("homePage", "nbfcMakerHome");
						mapObj1.put("MLIID", mli);
						mapObj1.put("CNT", cnt);
						mapObj1.put("danDetailList", list);
						mapObj1.put("msg", "Return Reasons Submitted.");
						modelView = new ModelAndView("ClaimReturnReasons", mapObj1);
						return modelView;
					//}else{
						//modelView = new ModelAndView("redirect:/SVLogin.html");
					//}
				 }else{
					 modelView = new ModelAndView("redirect:/SVLogin.html");
				 }
			
			return modelView;
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return modelView;
	}
	@RequestMapping(value = "submitReturnResonsRemark", method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView submitReturnResonsRemark(@ModelAttribute("command") ClaimLodgementBean claimLodgementBean,BindingResult result, HttpSession session, Model modelObj) {
	try{

		ClaimReturnReasonsModel claimobj=new ClaimReturnReasonsModel();
		ClaimLodgementBean bean =new ClaimLodgementBean ();
		Map<String, Object> mapObj1 = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		List<ClaimLodgementBean> claimLodgList =null;
		///loginUserMemId = claimLodgementService.getLoginUserMemId(userId);
		loginUserMemId = npaService.getMemberId(userId);
		String claimrefno=(String) session.getAttribute("claimRefNo");
		String returnRemark=claimLodgementBean.getReturnReasonsRemarks();
		 String mli=  (String) session.getAttribute("MLINameID");
		 String cnt=(String) session.getAttribute("CNT12");
		
       int count =claimLodgementService.updateClaimReturnResonsRemark(claimrefno,returnRemark);
       List<ClaimLodgementBean> list =claimLodgementService.getClaimReturnReasons();
		System.out.println(loginUserMemId);
//		claimLodgListObj.setClaimRefNo(claimRefNo);
		
			 userId=(String)session.getAttribute("userId");
			 if(userId!=null){
					userPri=lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
					//if(userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")){
						mapObj1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
						mapObj1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
						mapObj1.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
						mapObj1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
						mapObj1.put("GMaintainlist", userActivityService.getActivity("CGTMSECHECKER", "Guarantee_Maintenance"));
						mapObj1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
						mapObj1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
						mapObj1.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
						mapObj1.put("actNameHome", "CGTMSE");
						mapObj1.put("homePage", "nbfcMakerHome");
						mapObj1.put("MLIID", mli);
						mapObj1.put("CNT", cnt);
						mapObj1.put("danDetailList", list);
						mapObj1.put("msg", "Return Remark Submitted.");
						modelView = new ModelAndView("ClaimReturnReasons", mapObj1);
						return modelView;
					//}else{
						//modelView = new ModelAndView("redirect:/SVLogin.html");
					//}
				 }else{
					 modelView = new ModelAndView("redirect:/SVLogin.html");
				 }
			
			return modelView;
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return modelView;
	}
	
	
}



