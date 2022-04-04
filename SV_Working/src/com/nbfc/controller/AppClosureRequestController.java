package com.nbfc.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
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
import javax.validation.Valid;

import net.sf.jasperreports.engine.JRException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.ApplicationStatusDetailsBean;
import com.nbfc.bean.CGPANDetailsReportBean;
import com.nbfc.bean.CGTMSEExposureMasterBean;
import com.nbfc.bean.ClaimLodgementBean;
import com.nbfc.bean.MLIRegBean;
import com.nbfc.bean.NPADetailsBean;
//import com.nbfc.bean.NpaUpgradationBean;
import com.nbfc.exception.NBFCException;
import com.nbfc.helper.NBFCValidator;
import com.nbfc.helper.PropertyLoader;
import com.nbfc.model.BankDetails;
import com.nbfc.model.District;
import com.nbfc.model.MLIName;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.ApplicationStatusService;
import com.nbfc.service.CGTMSECreateExposureLimitMakerService;
import com.nbfc.service.ClosureRequestService;
import com.nbfc.service.DistrictService;
import com.nbfc.service.LoginService;
import com.nbfc.service.MLIDetailsService;
import com.nbfc.service.MLIRegService;
import com.nbfc.service.NPAService;
import com.nbfc.service.SanctionDetailService;
import com.nbfc.service.StateService;
import com.nbfc.service.UserActivityService;
import com.nbfc.validation.EmployeeValidator;
import com.nbfc.validator.DataValidation;

@Controller
public class AppClosureRequestController {

	@Autowired
	LoginService lofinService;
	@Autowired
	ApplicationStatusService applicationStatusService;

	@Autowired
	EmployeeValidator validator;

	static Logger log = Logger.getLogger(NBFCController.class.getName());
	List<BankDetails> bankDetails = new ArrayList<BankDetails>();
	List<Integer> bankId = new ArrayList<Integer>();
	List<Integer> bankBranchId = new ArrayList<Integer>();
	List<Integer> bankZonId = new ArrayList<Integer>();
	String bankBRID = null;
	String bankBRIDTest = null;
	//String mliID = null;

	
	@Autowired
	UserActivityService userActivityService;
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;
	ModelAndView modelView;
	String userId = null;
	public static final int BUFFER_SIZE = 4096;
	
	@Autowired
	ClosureRequestService closureRequestService;
	@Autowired
	NPAService npaService;
	@Autowired
	private CGTMSECreateExposureLimitMakerService cgtmseExposureMasterMakerService;
	@Autowired
	CGTMSECreateExposureLimitMakerService cgtmscCreateExposureLimitMakerService;
	ModelAndView modelAndViewObj;
	private List<CGTMSEExposureMasterBean> userList = new ArrayList<CGTMSEExposureMasterBean>();
	String portFiloName = null;
	NBFCValidator nbfcValidator = new NBFCValidator();
	@Value("${downloadFileDirPath}")
	private String downloadFileDirPath;
	@Value("${ExposureDetailsdownloadFileName}")
	private String downloadFileName;
	String memberId=null;
	MLIName mem_id = null;
	List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();

	@RequestMapping(value = "/AppClosureRequest", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView AppClosureRequest(
			@ModelAttribute("command") ApplicationStatusDetailsBean bean,
			BindingResult result, HttpSession session)
			throws JRException {
			Map<String, Object> model = new HashMap<String, Object>();
			String userId = (String) session.getAttribute("userId");
			List<ApplicationStatusDetailsBean> appClosureList = null;
			if(userId==null){
				return new ModelAndView("redirect:/SVLogin.html");
			}
			memberId = npaService.getMemberId(userId);
	       	System.out.println(memberId);
	       	appClosureList = closureRequestService.getAppClosureEditDetails(userId,"NCR");
			 if (appClosureList != null) {
					model.put("dataListReturn", appClosureList);
				} else {
					//log.info("Throwing exception due to data not found in NPACheckerController class");
					model.put("dataListReturn", "");
				}
			//Added by say 6 FEb-------------------------------------------------------------
			 model.put("adminlist",
						userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			 model.put("guaranteelist",
						userActivityService.getActivity("NBFCMAKER", "Registration"));
			 model.put("applicationList",
						userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			 model.put("RPaymentList",
						userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
						"NPADetails"));// Added by Say 31 Jan19
			 model.put("repList",
						userActivityService.getReport("NBFCMAKER", "User_Report"));
			 model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
			 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
//				modelAct.put("actList",
//						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			 model.put("MEMBER_ID", memberId);
			 model.put("homePage", "nbfcMakerHome");
			return new ModelAndView("AppClosureRequestInput", model);
		}
	
	@RequestMapping(value = "/AppClosureRequestDetails", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView AppClosureRequestDetails(
			@ModelAttribute("command") @Valid ApplicationStatusDetailsBean Bean,
			BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("saveAppClosureDetails==");
		//validator.npaValidation(Bean, result);
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		ApplicationStatusDetailsBean appClosureDetailsBean;
		List<ApplicationStatusDetailsBean> appClosureList = null;
		List<ApplicationStatusDetailsBean> list= new ArrayList<ApplicationStatusDetailsBean>();
		//DataValidation dataValidation = new DataValidation();
		
		String cgpan=Bean.getCGPAN();
		String memberId=Bean.getMEMBER_ID();
		//String memberId = npaService.getMemberId(userId);
		System.out.println("bankId "+bankId+" ,cgpan  "+cgpan);
		//validator.npaValidation(Bean, result);
		 list=closureRequestService.getCgpanStatus(cgpan,userId);//getNPADetailsForUpgradation
		 if(list.size() < 0 || list.isEmpty()){
				
				//	model.put("dataList", list);
					model.put("message", "CGPAN has not live !!");
					//Added by say 6 FEb-------------------------------------------------------------
					 model.put("adminlist",
								userActivityService.getActivity("NBFCMAKER", "System_Admin"));
					 model.put("guaranteelist",
								userActivityService.getActivity("NBFCMAKER", "Registration"));
					 model.put("applicationList",
								userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
					 model.put("RPaymentList",
								userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
					 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
								"NPADetails"));// Added by Say 31 Jan19
					 model.put("repList",
								userActivityService.getReport("NBFCMAKER", "User_Report"));
					 model.put("GMaintainlist", userActivityService.getActivity(
								"NBFCMAKER", "Guarantee_Maintenance"));
					 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
					 model.put("homePage", "nbfcMakerHome");
					model.put("MEMBER_ID", memberId);
					return new ModelAndView("AppClosureRequestInput", model);
				 }
		 
		 list=closureRequestService.getClosureDetailsForEdit(cgpan,userId);//getNPADetailsForUpgradation
		 if(list.size() > 0 ){
				
				//	model.put("dataList", list);
					model.put("message", "Closure request already exist !!");
					//Added by say 6 FEb-------------------------------------------------------------
					 model.put("adminlist",
								userActivityService.getActivity("NBFCMAKER", "System_Admin"));
					 model.put("guaranteelist",
								userActivityService.getActivity("NBFCMAKER", "Registration"));
					 model.put("applicationList",
								userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
					 model.put("RPaymentList",
								userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
					 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
								"NPADetails"));// Added by Say 31 Jan19
					 model.put("repList",
								userActivityService.getReport("NBFCMAKER", "User_Report"));
					 model.put("GMaintainlist", userActivityService.getActivity(
								"NBFCMAKER", "Guarantee_Maintenance"));
					 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
					 model.put("homePage", "nbfcMakerHome");
					model.put("MEMBER_ID", memberId);
					return new ModelAndView("AppClosureRequestInput", model);
				 }
		appClosureDetailsBean=closureRequestService.getAppClosureDetails(cgpan);
		
		 Bean.setBankName(appClosureDetailsBean.getBankName());
		 
		 Bean.setMEMBER_ID(appClosureDetailsBean.getMEMBER_ID());
		 Bean.setMSE_NAME(appClosureDetailsBean.getMSE_NAME());
		 Bean.setCGPAN(appClosureDetailsBean.getCGPAN());
		 
		
		 	
		/*
		 * model.put("npaCreditRisk",dataValidation.getNPACreditRisk());
		 * model.put("npaResion", dataValidation.getNPAResion());
		 */
//			model.put("actList",
//					userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			//Added by say 6 FEb-------------------------------------------------------------
			 model.put("adminlist",
						userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			 model.put("guaranteelist",
						userActivityService.getActivity("NBFCMAKER", "Registration"));
			 model.put("applicationList",
						userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			 model.put("RPaymentList",
						userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
						"NPADetails"));// Added by Say 31 Jan19
			 model.put("repList",
						userActivityService.getReport("NBFCMAKER", "User_Report"));
			 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			 model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
//				modelAct.put("actList",
//						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			 model.put("MEMBER_ID", memberId);
			 model.put("homePage", "nbfcMakerHome");
			return new ModelAndView("AppClosureRequestDetails", model);

	}
	
	@RequestMapping(value = "/SaveAppClosureDetails", method = { RequestMethod.GET,RequestMethod.POST})
	public ModelAndView SaveAppClosureDetails(
			@ModelAttribute("command")ApplicationStatusDetailsBean Bean,
			BindingResult result, HttpSession session) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		validator.appClosureRequestValidation(Bean, result);
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		ApplicationStatusDetailsBean appDetailsBean;
		DataValidation dataValidation = new DataValidation();
		System.out.println("Bean"+Bean);
		String cgpan=Bean.getCGPAN();
		String memberId=Bean.getMEMBER_ID();
		//String memberId = npaService.getMemberId(userId);
		System.out.println("bankId "+bankId+" ,cgpan  "+cgpan);
		validator.appClosureRequestValidation(Bean, result);
		/*try {
			validator.npaSaveForDate(Bean, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		if (result.hasErrors()) {
			//Added by say 6 FEb-------------------------------------------------------------
			 model.put("adminlist",
						userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			 model.put("guaranteelist",
						userActivityService.getActivity("NBFCMAKER", "Registration"));
			 model.put("applicationList",
						userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			 model.put("RPaymentList",
						userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
						"NPADetails"));// Added by Say 31 Jan19
			 model.put("repList",
						userActivityService.getReport("NBFCMAKER", "User_Report"));
			 model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
			 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
//				modelAct.put("actList",
//						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			 model.put("homePage", "nbfcMakerHome");
			model.put("memberId", memberId);
		return new ModelAndView("AppClosureRequestInput", model);
		}
		String closureDt=Bean.getAppClosureDate();
		System.out.println("NPA Date=="+closureDt);
		
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date appClosureDt=sdf.parse(closureDt);
		System.out.println("appClosureDt ==appClosureDt==="+appClosureDt);
		/*String msg=dataValidation.appClosureDateValidator(appClosureDt);
		System.out.println("Msg==="+msg);
		if (msg!=null) {
			System.out.println("Msg######==="+msg);
			model.put("message", msg);
			//Added by say 6 FEb-------------------------------------------------------------
			 model.put("adminlist",
						userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			 model.put("guaranteelist",
						userActivityService.getActivity("NBFCMAKER", "Registration"));
			 model.put("applicationList",
						userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			 model.put("RPaymentList",
						userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
						"NPADetails"));// Added by Say 31 Jan19
			 model.put("repList",
						userActivityService.getReport("NBFCMAKER", "User_Report"));
			 model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
			 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
//				modelAct.put("actList",
//						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			 model.put("homePage", "nbfcMakerHome");
			model.put("memberId", memberId);
			return new ModelAndView("AppClosureRequestDetails", model);
		}
	*/
		int val=closureRequestService.saveAppClosureDetails(Bean,userId);
		
		if(val==0){
			//Added by say 6 FEb-------------------------------------------------------------
			 model.put("adminlist",
						userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			 model.put("guaranteelist",
						userActivityService.getActivity("NBFCMAKER", "Registration"));
			 model.put("applicationList",
						userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			 model.put("RPaymentList",
						userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
						"NPADetails"));// Added by Say 31 Jan19
			 model.put("repList",
						userActivityService.getReport("NBFCMAKER", "User_Report"));
			 model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
			 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
//				modelAct.put("actList",
//						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			 model.put("homePage", "nbfcMakerHome");
			model.put("memberId", memberId);
			model.put("message", "Closure Request successfully lodged: "+cgpan);
			return new ModelAndView("AppClosureRequestInput", model);
			
		}
		System.out.println(val);
		
		 model.put("adminlist",
					userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		 model.put("guaranteelist",
					userActivityService.getActivity("NBFCMAKER", "Registration"));
		 model.put("applicationList",
					userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		 model.put("RPaymentList",
					userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
					"NPADetails"));// Added by Say 31 Jan19
		 model.put("repList",
					userActivityService.getReport("NBFCMAKER", "User_Report"));
		 model.put("GMaintainlist", userActivityService.getActivity(
					"NBFCMAKER", "Guarantee_Maintenance"));
		 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
//			modelAct.put("actList",
//					userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		 model.put("memberId", memberId);
		 model.put("homePage", "nbfcMakerHome");
		return new ModelAndView("AppClosureRequestInput", model);
	}
	
	@RequestMapping(value = "/AppClosureRequestApproval", method = RequestMethod.GET)
	public ModelAndView AppClosureRequestApproval(
			@ModelAttribute("command") ApplicationStatusDetailsBean Bean,
			BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		List<ApplicationStatusDetailsBean> appClosureList = null;
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		}
		memberId = npaService.getMemberId(userId);
		appClosureList = closureRequestService.getAppClosureDetailsForApproval(userId,"NMA");
		if (appClosureList != null) {
			model.put("dataList", appClosureList);
		}
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		//added by say 6 feb-----------------------
		model.put("adminlist",
				userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("NBFCCHECKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("NBFCCHECKER",
				"danGenerateRpNumberForPaymentChecker"));// Added by Say 31 Jan19
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		 model.put("homePage", "nbfcCheckerHome");
		 model.put("memberId", memberId);
	
		return new ModelAndView("AppClosureRequestApproval", model);

	}
	
	@RequestMapping(value = "/AppClosureRequestUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView AppClosureRequestUpdate(
			@ModelAttribute("command")ApplicationStatusDetailsBean Bean,
			BindingResult result, HttpSession session,String Cgpan) {
		Map<String, Object> model = new HashMap<String, Object>();
		//validator.npaValidation(Bean, result);
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		List<ApplicationStatusDetailsBean> list= new ArrayList<ApplicationStatusDetailsBean>();
		ApplicationStatusDetailsBean appClosureDetailsBean;
		ApplicationStatusDetailsBean appClosureDetailBean;
		DataValidation dataValidation = new DataValidation();
		System.out.println("Bean"+Bean);
		String bankId=Bean.getMEMBER_ID();
		appClosureDetailsBean=closureRequestService.getAppClosureDetails(Cgpan,userId); //getNPAUpgradationDetails
		//npaDetailBean=npaService.getModifyNPADetails(Cgpan,userId);		
		
		//model.put("npaDetailsBean",	npaDetailsBean);
		//Added by say 6 FEb-------------------------------------------------------------
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		//added by say 6 feb-----------------------
		model.put("adminlist",
				userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("NBFCCHECKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("NBFCCHECKER",
				"danGenerateRpNumberForPaymentChecker"));// Added by Say 31 Jan19
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		 model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		 model.put("homePage", "nbfcCheckerHome");
	
		 model.put("memberId", memberId);
		 
		 Bean.setBankName(appClosureDetailsBean.getBankName());
		 Bean.setMEMBER_ID(appClosureDetailsBean.getMEMBER_ID());
		 Bean.setMSE_NAME(appClosureDetailsBean.getMSE_NAME());
		 Bean.setCGPAN(appClosureDetailsBean.getCGPAN());
		 Bean.setAppClosureDate(appClosureDetailsBean.getAppClosureDate());
		 Bean.setAppClosureRemarks(appClosureDetailsBean.getAppClosureRemarks());
	 
		return new ModelAndView("AppClosureRequestApproveDetails", model);
	}
	
	@RequestMapping(value = "/SaveAppClosureReject", method = RequestMethod.POST)
	public ModelAndView SaveAppClosureReject(
			@ModelAttribute("command")ApplicationStatusDetailsBean Bean,
			BindingResult result, HttpSession session) throws Exception {
		
		Map<String, Object> model = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		String memberId=(String) session.getAttribute("memberId");
		System.out.println("memberId=="+memberId);
		
		int flag;
			
	
		
		List<ApplicationStatusDetailsBean> npaList;
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		}
		memberId = npaService.getMemberId(userId);
		//flag = npaService.updateNPAApproveReject(checCgpan, userId, "NCR","NM",ReturnRemark);
		flag=closureRequestService.SaveAppClosureReject(Bean,userId);//SaveNPAUpgradationReject

		if (flag == 0) {
			npaList = closureRequestService.getAppClosureDetailsForApproval(userId,"NMA");
			//if (npaList.size() > 0 || !npaList.isEmpty()) {
				if (npaList!=null) {
					
				model.put("dataList", npaList);
				
			} else {
				//log.info("Throwing exception due to data not found in NPACheckerController class");
				model.put("dataList", "");
			}
				model.put("message", "Record Succefully Returned");
		}

		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		//added by say 6 feb-----------------------
		model.put("adminlist",
				userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("NBFCCHECKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("NBFCCHECKER",
				"danGenerateRpNumberForPaymentChecker"));// Added by Say 31 Jan19
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		 model.put("homePage", "nbfcCheckerHome");
		model.put("memberId", memberId);
		//return new ModelAndView("npaApprovalRejection", model);
		return new ModelAndView("AppClosureRequestApproval", model);
	}
	
	@RequestMapping(value = "/EditAppClosureDetails", method = { RequestMethod.GET,RequestMethod.POST})
	public ModelAndView EditAppClosureDetails(
			@ModelAttribute("command")ApplicationStatusDetailsBean Bean,
			BindingResult result, HttpSession session,String Cgpan) throws ParseException {
		Map<String, Object> model = new HashMap<String, Object>();
		//validator.npaValidation(Bean, result);
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		List<ApplicationStatusDetailsBean> list= new ArrayList<ApplicationStatusDetailsBean>();
		ApplicationStatusDetailsBean appClosureDetailsBean;
		//NpaUpgradationBean npaDetailBean;
		DataValidation dataValidation = new DataValidation();
		System.out.println("Bean"+Bean);
		System.out.println("CGPAN=="+Cgpan);
		String bankId=Bean.getMEMBER_ID();
		
		appClosureDetailsBean=closureRequestService.getEditAppClosureDetails(Cgpan,userId); //getNPAUpgradationDetails
		
		//npaDetailBean=npaService.getModifyNPADetails(Cgpan,userId);		
		
		//model.put("npaDetailsBean",	npaDetailsBean);
		//Added by say 6 FEb-------------------------------------------------------------
		 model.put("adminlist",
					userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		 model.put("guaranteelist",
					userActivityService.getActivity("NBFCMAKER", "Registration"));
		 model.put("applicationList",
					userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		 model.put("RPaymentList",
					userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
					"NPADetails"));// Added by Say 31 Jan19
		 model.put("repList",
					userActivityService.getReport("NBFCMAKER", "User_Report"));
		 model.put("GMaintainlist", userActivityService.getActivity(
					"NBFCMAKER", "Guarantee_Maintenance"));
		 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
//			modelAct.put("actList",
//					userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		 model.put("homePage", "nbfcMakerHome");
		model.put("memberId", memberId);
		 
		 Bean.setBankName(appClosureDetailsBean.getBankName());
		 Bean.setMEMBER_ID(appClosureDetailsBean.getMEMBER_ID());
		 Bean.setMSE_NAME(appClosureDetailsBean.getMSE_NAME());
		 Bean.setCGPAN(appClosureDetailsBean.getCGPAN());
		 Bean.setAppClosureDate(appClosureDetailsBean.getAppClosureDate());
		 Bean.setAppClosureRemarks(appClosureDetailsBean.getAppClosureRemarks());
		 
		
		 
		return new ModelAndView("EditAppClosureDetails", model);
	}
	
	@RequestMapping(value = "/SaveAppClosureEdit", method = RequestMethod.POST)
	public ModelAndView SaveAppClosureEdit(
			@ModelAttribute("command")ApplicationStatusDetailsBean Bean,
			BindingResult result, HttpSession session,String Cgpan) throws ParseException {
		Map<String, Object> model = new HashMap<String, Object>();
		//validator.npaValidation(Bean, result);
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		List<ApplicationStatusDetailsBean> list= new ArrayList<ApplicationStatusDetailsBean>();
		ApplicationStatusDetailsBean appClosureDetailsBean;
		//NpaUpgradationBean npaDetailBean;
		DataValidation dataValidation = new DataValidation();
		System.out.println("Bean"+Bean);
		String bankId=Bean.getMEMBER_ID();
		String appClosureDt=Bean.getAppClosureDate();
		System.out.println("NPA Date=="+appClosureDt);
		
		
		 int val=closureRequestService.SaveAppClosureEdit(Bean,userId);
			
			if(val==0){
				//Added by say 6 FEb-------------------------------------------------------------
				 model.put("adminlist",
							userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				 model.put("guaranteelist",
							userActivityService.getActivity("NBFCMAKER", "Registration"));
				 model.put("applicationList",
							userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
				 model.put("RPaymentList",
							userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
				 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
							"NPADetails"));// Added by Say 31 Jan19
				 model.put("repList",
							userActivityService.getReport("NBFCMAKER", "User_Report"));
				 model.put("GMaintainlist", userActivityService.getActivity(
							"NBFCMAKER", "Guarantee_Maintenance"));
				 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
//					modelAct.put("actList",
//							userActivityService.getActivity("NBFCMAKER", "User_Activity"));
				 model.put("homePage", "nbfcMakerHome");
				model.put("memberId", memberId);
				model.put("message", "Closure Request successfully updated : ");
				return new ModelAndView("AppClosureRequestInput", model);
				
			}
		 
		return new ModelAndView("EditAppClosureDetails", model);
	}
	
	@RequestMapping(value = "/SaveAppClosureApprove", method = RequestMethod.POST)
	public ModelAndView SaveAppClosureApprove(
			@ModelAttribute("command")ApplicationStatusDetailsBean Bean,
			BindingResult result, HttpSession session) throws Exception {
		
		
		Map<String, Object> model = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		String memberId=(String) session.getAttribute("memberId");
		System.out.println("memberId=="+memberId);
		
		int flag;
		
		List<ApplicationStatusDetailsBean> npaList;
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		}
		memberId = npaService.getMemberId(userId);
		//flag = npaService.updateNPAApproveReject(checCgpan, userId, "NCR","NM",ReturnRemark);
		flag=closureRequestService.SaveAppClosureApprove(Bean,userId);//SaveNPAUpgradationReject

		if (flag == 0) {
			npaList = closureRequestService.getAppClosureDetailsForApproval(userId,"NMA");
			//if (npaList.size() > 0 || !npaList.isEmpty()) {
				if (npaList!=null) {
					
				model.put("dataList", npaList);
				
			} else {
				//log.info("Throwing exception due to data not found in NPACheckerController class");
				model.put("dataList", "");
			}
				model.put("message", "Record Succefully Approved");
		}

		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		//added by say 6 feb-----------------------
		model.put("adminlist",
				userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("NBFCCHECKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("NBFCCHECKER",
				"danGenerateRpNumberForPaymentChecker"));// Added by Say 31 Jan19
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		 model.put("homePage", "nbfcCheckerHome");
		model.put("memberId", memberId);
		//return new ModelAndView("npaApprovalRejection", model);
		return new ModelAndView("AppClosureRequestApproval", model);
		
	}
	
	//cLOSURE REQUEST REPORT
	
	
	@RequestMapping(value = "/AppClosureRequestReport", method = RequestMethod.GET)
	public ModelAndView AppClosureRequestReport(@ModelAttribute("command") ClaimLodgementBean bean, BindingResult result,
			HttpSession session, Model model) throws JRException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		String Role = (String) session.getAttribute("uRole");
		userId = (String) session.getAttribute("userId");
		String loginUserMemId = npaService.getMemberId(userId);
		// nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);
		if (Role.equals("CMAKER")) {
			model1.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
			model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
			model1.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));

			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSEMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("memberId", loginUserMemId);
			model1.put("homePage", "cgtmseMakerHome");

		} else if (Role.equals("CCHECKER")) {
			model1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
			model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSECHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("memberId", loginUserMemId);
			model1.put("homePage", "cgtmseCheckerHome");
			// return null;
		}
		return new ModelAndView("AppClosureRequestReportInput", model1);
		// return null;
	}

	//Get the MLI Long Name in Drop Down on Page on Load
			@ModelAttribute("mliLongName")
			public Map<String, String> getMlilongName() {
				Map<String, String> mapMliLongNameObj = new HashMap<String, String>();
				mapMliLongNameObj = cgtmseExposureMasterMakerService.getMliLongNameInDropDown();
				return   mapMliLongNameObj;
			}
	
	@RequestMapping(value = "/ClosureReportData", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView ClosureReportData(@ModelAttribute("command") ClaimLodgementBean bean, BindingResult result,
			HttpSession session, Model model)
			throws JRException, ParseException {

		System.out.println("Data=======");

		Map<String, Object> model1 = new HashMap<String, Object>();
		List<Map<String, Object>> ClaimDetailsDataReport = new ArrayList<Map<String, Object>>();

		
		  session.setAttribute("ClaimDetailsDataReport",null);
		 session.removeAttribute("ClaimDetailsDataReport");
		 
		String Role = (String) session.getAttribute("uRole");

		String userId = (String) session.getAttribute("userId");
		memberId = npaService.getMemberId(userId);
	//	String claimStatus="AP";
		//System.out.println("claimStatus:" + claimStatus);
		
		String mliLongName=bean.getMliLongName();
		
		System.out.println("mliLongName==194==="+mliLongName);

		// Date fromDate=fromDate1.to;

		// Date fromDate=new SimpleDateFormat("dd/MM/yyyy").parse(fromDate1);
		// Date toDate=new SimpleDateFormat("dd/MM/yyyy").parse(toDate1);
		// System.out.println("From Date="+fromDate);
		// System.out.println("To Date="+toDate);

		validator.ClaimDetailsReportValidate(bean, result);
		if (result.hasErrors()) {
			if (Role.equals("CMAKER")) {
				// userId = "ADMIN";
				model1.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
				model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
				model1.put("repList", userActivityService.getReport("CGTMSEMAKER", "User_Report"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("CGTMSEMAKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "cgtmseMakerHome");

			} else if (Role.equals("CCHECKER")) {
				// userId = "ADMIN";
				model1.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
				model1.put("applicationList",
						userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
				model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
				model1.put("repList", userActivityService.getReport("CGTMSECHECKER", "User_Report"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("CGTMSECHECKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "cgtmseCheckerHome");
				// return null;
			
			return new ModelAndView("AppClosureRequestReportInput", model1);
		}
		}
		
		/*
		 * String toDateF = bean.getToDate(); String fromDateF = bean.getFromDate();
		 * session.setAttribute("FDate", fromDateF); session.setAttribute("TDate",
		 * toDateF);
		 */

		// Date fromDate=fromDate1;

		Date toDate = new SimpleDateFormat("dd/MM/yyyy").parse(bean.getToDate());
		Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(bean.getFromDate());

		// Date toDate1=bean.getToDate();
		// Date fromDate1=bean.getFromDate();
		if (Role.equals("CCHECKER") || Role.equals("CMAKER")) {
			//String mliname = bean.getMliName();
			mem_id = userActivityService.getBankID(mliLongName);
			//mem_id = userActivityService.getBankID(mliLongName);
			//mem_id = userActivityService.getBankID(mliname);
			String Mem_Id = mem_id.getBnkId() + mem_id.getZneID() + mem_id.getBrnName();
			System.out.println("Mem_Id==="+Mem_Id);
			System.out.println("From Date=###" + fromDate);
			System.out.println("To Date=###" + toDate);
			ClaimDetailsDataReport = closureRequestService.getAppClosureRequestReport(userId, toDate, fromDate, Mem_Id, Role
					);
		} else {
			System.out.println("Into Calling==");
			System.out.println("From Date=" + fromDate);
			System.out.println("To Date=" + toDate);
			ClaimDetailsDataReport = closureRequestService.getAppClosureRequestReport(userId, toDate, fromDate, memberId,
					Role );
		}

		// nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);
		if (!ClaimDetailsDataReport.isEmpty()) {
			model1.put("ClaimDetailsDataReport", ClaimDetailsDataReport);
			session.setAttribute("ClaimDetailsDataReport", ClaimDetailsDataReport);
		} else {
			model1.put("message", "NO Data Found !!");
		}
		return new ModelAndView("AppClosureRequestReportData", model1);
	
	}
	
	 
	
	@RequestMapping(value = "/AppClosureReportDownload", method = RequestMethod.GET)
	public ModelAndView AppClosureReportDownload(@ModelAttribute("command") ClaimLodgementBean bean,
			BindingResult result, HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {

		try {

			OutputStream os = response.getOutputStream();

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
			String strDate = sdf.format(cal.getTime());

			// System.out.println("ExportToFile Calling..");

			String contextPath1 = request.getSession(false).getServletContext().getRealPath("");
			String contextPath = PropertyLoader.changeToOSpath(contextPath1);

			System.out.println("contextPath1 :" + contextPath1);
			System.out.println("contextPath :" + contextPath);

			// HttpSession sess = request.getSession(false);
			// String fileType = request.getParameter("fileType");
			// String FlowLevel = request.getParameter("FlowLevel");

			// System.out.println("@@@@@@@@@@@@FlowLevel :" + FlowLevel);
			// ArrayList ClmDataList =
			// (ArrayList)sess.getAttribute("ClaimSettledDatalist");
			// ArrayList ClmDataList = (ArrayList) sess.getAttribute(FlowLevel);

			List<Map<String, Object>> list = (List<Map<String, Object>>) session.getAttribute("ClaimDetailsDataReport");
			// System.out.println("@@@@@@@@@@@@ClmDataList:" + ClmDataList);
			// ArrayList HeaderArrLst = (ArrayList) ClmDataList.get(0);
			ArrayList<Object> HeaderArrLst = new ArrayList<Object>();

			String key = null;
			for (Map<String, Object> ClmDataList : list) {
				for (Map.Entry<String, Object> entry : ClmDataList.entrySet()) {
					// rowhead = sheet.createRow((short) 0);
					key = entry.getKey();
					// Object value = entry.getValue();
					System.out.println("ClmDataList key==" + key);
					HeaderArrLst.add(key);
				}
				break;
			}
			// System.out.println("@@@@@@@@@@@@HeaderArrLst:" + HeaderArrLst);
			int NoColumn = HeaderArrLst.size();

			System.out.println("NoColumn:" + NoColumn);

			// if (fileType.equals("CSVType")) {
			byte[] b = accountHistoryGenerateCSV(list, NoColumn, contextPath);

			if (response != null)
				response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=ExcelData" + strDate + ".csv");
			os.write(b);
			os.flush();

		} catch (Exception e) {
			log.info("Exception == " + e);
			System.out.println("Exception == " + e);
		}
		return null;
	}

	public byte[] accountHistoryGenerateCSV(List<Map<String, Object>> list, int No_Column, String contextPath)
			throws IOException {

		System.out.println("---generateCSV()---");
		StringBuffer strbuff = new StringBuffer();
		// System.out.println("ParamDataList:" + ParamDataList);
		// System.out.println("contextPath :" + contextPath);
		// ArrayList<String> rowDataLst = new ArrayList<String>();
		// ArrayList<String> HeaderLst = (ArrayList) list.get(0);
		// ArrayList<ArrayList> RecordWiseLst = (ArrayList) list.get(1);

		// List<Map<String, Object>> rowDataLst= new ArrayList<Map<String, Object>>();
		ArrayList<Object> rowDataLst = new ArrayList<Object>();

		// List<Map<String, Object>> HeaderLst= new ArrayList<Map<String, Object>>();
		String key = null;
		for (Map<String, Object> HeaderLst : list) {
			for (Map.Entry<String, Object> entry : HeaderLst.entrySet()) {
				// rowhead = sheet.createRow((short) 0);
				key = entry.getKey();
				// Object value = entry.getValue();
				System.out.println("ClmDataList key==" + key);
				rowDataLst.add(key);
			}
			// rowDataLst.add(key);
			System.out.println("HeaderLst" + HeaderLst);
			break;
		}

		// List<Map<String, Object>> RecordWiseLst= new ArrayList<Map<String,
		// Object>>();
		System.out.println("rowDataLst" + rowDataLst);

		// System.out.println("HeaderLst" + HeaderLst);
		// System.out.println("RecordWiseLst" + RecordWiseLst);
		// #### Header List

		// System.out.println("Loop--headerdata:" + headerdata);

		// System.out.println("rowDataLst:" + rowDataLst);
		// #### Header List

		// #### Data List

		for (Map<String, Object> RecordWiseLst : list) {
			for (Map.Entry<String, Object> entry : RecordWiseLst.entrySet()) {
				// rowhead = sheet.createRow((short) 0);
				// key = entry.getKey();
				Object value = entry.getValue();
				System.out.println("ClmDataList key==" + value);
				rowDataLst.add(value);

			}
			// rowDataLst.add(RecordWiseLst);
			System.out.println("RecordWiseLst" + RecordWiseLst);
		}
		System.out.println("rowDataLst" + rowDataLst);
		// System.out.println("rowDataLst::" + rowDataLst);
		// #### Data List

		ArrayList<Object> FinalrowDatalist = new ArrayList<Object>();
		// System.out.println("1");
		int y = 0;
		System.out.println("2==" + No_Column);
		for (int n = 0; n < rowDataLst.size(); n++) {

			if (n % No_Column == 0 && n != 0) {
				FinalrowDatalist.add(rowDataLst.get(n));
				FinalrowDatalist.add(n + y, "\n");
				System.out.println("2n value inside if:" + n);
				System.out.println("n:" + n);
				y++;
			} else {
				System.out.println("2n inside else:" + n);
				if (null != rowDataLst.get(n)) {
					// if (( rowDataLst.get(n)).contains(",")) {
					if (((Object) rowDataLst.get(n)).equals(",")) {
						rowDataLst.set(n, ((String) rowDataLst.get(n)).replace(",", ";"));
					}
				}
				FinalrowDatalist.add(rowDataLst.get(n));
			}
			System.out.println("rowDataLst.get " + rowDataLst.get(n) + "    " + n % 3);
		}
		// System.out.println("rowDataLst :"+rowDataLst.toString().replace("\n,","\n"));
		// String tempStr = rowDataLst.toString().replace("\n,", "\n");
		// System.out.println("3");

		String tempStr = FinalrowDatalist.toString().replace("\n,", "\n").replace(" ,", ",").replace(", ", ",");
		// String tempStr = FinalrowDatalist.toString().replace("\n,", "\n");

		// System.out.println("4");
		// strbuff.append(ParamDataList.toString().substring(2,
		// ParamDataList.toString().length() - 2).replace("endrow,", "\n"));
		strbuff.append(tempStr.substring(1, tempStr.length() - 1));
		// System.out.println("strbuff :"+strbuff);
		/// System.out.println("5");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String strDate = sdf.format(cal.getTime());
		BufferedWriter output = null;
		OutputStream outStrm;
		// File genfile = new File("D:\\GenerateFiles\\SampleFile" + strDate+
		// ".csv");
		File genfile = new File(contextPath + "\\Download\\DataCSVFile" + strDate + ".csv");

		// System.out.println("6");
		output = new BufferedWriter(new FileWriter(genfile));
		output.write(strbuff.toString());
		// System.out.println("7");
		output.flush();
		output.close();
		// System.out.println("8");

		// ##
		// FileInputStream fis = new
		// FileInputStream("D:\\GenerateFiles\\SampleFile" + strDate+ ".csv");
		FileInputStream fis = new FileInputStream(contextPath + "\\Download\\DataCSVFile" + strDate + ".csv");

		// System.out.println("9");
		byte b[];
		int x = fis.available();
		b = new byte[x];
		// System.out.println(" b size"+b.length);

		fis.read(b);
		// ##
		return b;
		// genfile.setReadOnly();
	}
}
