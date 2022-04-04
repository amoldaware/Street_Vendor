package com.nbfc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.service.NPAService;
import com.nbfc.service.UserActivityService;

@Controller
public class NPACheckerController {
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	NPAService npaService;
	String memberId = null;
	// List<NPADetailsBean> npaList = new ArrayList<NPADetailsBean>();
	static Logger log = Logger.getLogger(NPACheckerController.class.getName());

	@RequestMapping(value = "/NPAApproval", method = RequestMethod.GET)
	public ModelAndView npaApproval(
			@ModelAttribute("command") NPADetailsBean Bean,
			BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		List<NPADetailsBean> npaList = null;
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		}
		memberId = npaService.getMemberId(userId);
		npaList = npaService.getNPADetailsForApproval(userId,"NMA");
		if (npaList != null) {
			model.put("dataList", npaList);
		} else {
			log.info("Throwing exception due to data not found in NPACheckerController class");
			model.put("dataList", "");
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
		model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
		 model.put("homePage", "nbfcCheckerHome");
		 model.put("memberId", memberId);
	
		return new ModelAndView("npaApprovalRejection", model);

	}

	@RequestMapping(value = "/NPAApproved", method = RequestMethod.POST)
	public ModelAndView npaApproved(
			@ModelAttribute("command") NPADetailsBean Bean,
			BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		String ReturnRemark="";
		int flag;
		List<String> checkedCgpan = Bean.getChcktbl();
		// String checCgpan = checkedCgpan.toString().replace("[",
		// "'").replace("]", "'").replace(", ", "','");
		String checCgpan = checkedCgpan.toString().replace("[", "")
				.replace("]", "").replace(", ", ",");

		System.out.println(checCgpan);
		List<NPADetailsBean> npaList;
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		}
		memberId = npaService.getMemberId(userId);
		flag = npaService.updateNPAApproveReject(checCgpan, userId, "NCA","NM",ReturnRemark);

		if (flag == 0) {
			npaList = npaService.getNPADetailsForApproval(userId,"NMA");
			// if (npaList.size() > 0 || !npaList.isEmpty()) {
			if (npaList != null) {

				model.put("dataList", npaList);
				
			} else {
				log.info("Throwing exception due to data not found in NPACheckerController class");
				model.put("dataList", "");
			}
			model.put("message", "Record Succefully Approved");
		}
		
		 model.put("adminlist",
					userActivityService.getActivity("NBFCHECKER", "System_Admin"));
		 model.put("guaranteelist",
					userActivityService.getActivity("NBFCHECKER", "Registration"));
		 model.put("applicationList",
					userActivityService.getActivity("NBFCHECKER", "Application_Processing"));
		 model.put("RPaymentList",
					userActivityService.getActivity("NBFCHECKER", "Receipt_Payments"));
		 model.put("actName", userActivityService.getActivityName("NBFCHECKER",
					"NPAApproval"));// Added by Say 31 Jan19
		 model.put("repList",
					userActivityService.getReport("NBFCHECKER", "User_Report"));
		 model.put("GMaintainlist", userActivityService.getActivity(
					"NBFCHECKER", "Guarantee_Maintenance"));
			model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
			model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
//			modelAct.put("actList",
//					userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		 model.put("homePage", "nbfcCheckerHome");
	
		model.put("memberId", memberId);
		return new ModelAndView("npaApprovalRejection", model);

	}

	@RequestMapping(value = "/NPAReject", method = RequestMethod.POST)
	public ModelAndView npaReject(
			@ModelAttribute("command") NPADetailsBean Bean,
			BindingResult result, HttpSession session, Model messageTag) {
		Map<String, Object> model = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		int flag;
		List<String> checkedCgpan = Bean.getChcktbl();
		String ReturnRemark = Bean.getRemarks();
		
		String checCgpan = checkedCgpan.toString().replace("[", "")
				.replace("]", "").replace(", ", ",");

		System.out.println(checCgpan);
	
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		}
		memberId = npaService.getMemberId(userId);
		flag = npaService.updateNPAApproveReject(checCgpan, userId, "NCR","NM",ReturnRemark);

		/*if (flag == 0) {
			List<NPADetailsBean> npaList=null;
			npaList = npaService.getNPADetailsForApproval(userId,"NMA");
			//if (npaList.size() > 0 || !npaList.isEmpty()) {
				if (npaList!=null) {
			    model.put("remarks", "");
			    model.put("mliRemarks", "");
				model.put("dataList", npaList);
				
			} else {
				log.info("Throwing exception due to data not found in NPACheckerController class");
				model.put("dataList", "");
			}
		model.put("message", "Record Succefully Returned");
		}*/

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
		model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
		 model.put("homePage", "nbfcCheckerHome");
		model.put("memberId", memberId);
		messageTag.addAttribute("message", "Record Succefully Returned");
		//return new ModelAndView("npaApprovalRejection", model);
		return new ModelAndView("redirect:/NPAApproval.html",model);
	}

	@RequestMapping(value = "/npaDetail", method = RequestMethod.GET)
	public ModelAndView npaDetailForCGTMEMaker(
			@ModelAttribute("command") NPADetailsBean Bean,
			BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		List<NPADetailsBean> npaList = null;
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		}
		// memberId = npaService.getMemberId(userId);
		//npaList = npaService.getNPADetailsForCGTMSE("NCA", "", "", "", "");
		npaList=npaService.getNPADetailsForApproval(null,"NCA");
		if (npaList != null) {
			model.put("dataList", npaList);
		} else {
			log.info("Throwing exception due to data not found in NPACheckerController class");
			model.put("dataList", "");
		}
		model.put("adminlist",
				userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("CGTMSECHECKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("CGTMSECHECKER",
				"NPADetails"));// Added by Say 31 Jan19
		model.put("repList", userActivityService.getReport(
				"CGTMSECHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		model.put("homePage", "cgtmseCheckerHome");
		model.put("memberId", memberId);
		model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		return new ModelAndView("npaDetailsForCGTMSE", model);

	}
	
//	@RequestMapping(value = "/NPAReturn", method = RequestMethod.POST)
//	public ModelAndView NPAReturn(
//			@ModelAttribute("command") NPADetailsBean Bean,
//			BindingResult result, HttpSession session) {
//		Map<String, Object> model = new HashMap<String, Object>();
//		String userId = (String) session.getAttribute("userId");
//		int flag;
//		List<String> checkedCgpan = Bean.getChcktbl();
//		// String checCgpan = checkedCgpan.toString().replace("[",
//		// "'").replace("]", "'").replace(", ", "','");
//		String checCgpan = checkedCgpan.toString().replace("[", "")
//				.replace("]", "").replace(", ", ",");
//
//		System.out.println(checCgpan);
//		List<NPADetailsBean> npaList;
//		if (userId == null) {
//			return new ModelAndView("redirect:/SVLogin.html");
//		}
//		memberId = npaService.getMemberId(userId);
//		flag = npaService.updateNPAApproveReject(checCgpan, userId, "NMA","CM");
//
//		if (flag == 0) {
//			npaList = npaService.getNPADetailsForApproval(userId,"NCA");
//			//if (npaList.size() > 0 || !npaList.isEmpty()) {
//				if (npaList!=null) {
//					
//				model.put("dataList", npaList);
//				
//			} else {
//				log.info("Throwing exception due to data not found in NPACheckerController class");
//				model.put("dataList", "");
//			}
//				model.put("message", "Record Succefully Return to NBFC.");
//		}
//
//		model.put("adminlist",
//				userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
//		model.put("guaranteelist",
//				userActivityService.getActivity("CGTMSECHECKER", "Registration"));
//		model.put("applicationList",
//				userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
//		model.put("RPaymentList",
//				userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
//		model.put("actName", userActivityService.getActivityName("CGTMSECHECKER",
//				"NPAApproval"));// Added by Say 31 Jan19
//		model.put("repList", userActivityService.getReport(
//				"CGTMSECHECKER", "User_Report"));
//		model.put("GMaintainlist", userActivityService.getActivity(
//				"CGTMSECHECKER", "Guarantee_Maintenance"));
//		model.put("homePage", "cgtmseCheckerHome");
//		model.put("memberId", memberId);
//		return new ModelAndView("npaApprovalRejection", model);
//
//	}
	
	@RequestMapping(value = "/npaClaimDetail", method = RequestMethod.GET)
	public ModelAndView npaClaimDetailForCGTMEMaker(
			@ModelAttribute("command") NPADetailsBean Bean,
			BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		List<NPADetailsBean> npaList = null;
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		}
		// memberId = npaService.getMemberId(userId);
		//npaList = npaService.getNPADetailsForCGTMSE("NCA", "", "", "", "");
		npaList=npaService.getNPADetailsForApproval(null,"NCA");
		if (npaList != null) {
			model.put("dataList", npaList);
		} else {
			log.info("Throwing exception due to data not found in NPACheckerController class");
			model.put("dataList", "");
		}
		model.put("adminlist",
				userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("CGTMSEMAKER",
				"NPAApproval"));// Added by Say 31 Jan19
		model.put("repList", userActivityService.getReport(
				"CGTMSEMAKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		model.put("homePage", "cgtmseMakerHome");
		model.put("memberId", memberId);
		return new ModelAndView("npaClaimDetailsForCGTMSE", model);

	}

}
