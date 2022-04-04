package com.nbfc.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.math.BigDecimal;
import com.nbfc.bean.DanCorrespondingDataListBeanCheckerBean;
import com.nbfc.bean.DanGenerateRpNumberForCheckerApprovalBean;
import com.nbfc.model.DanCorrespondigDataModel;
import com.nbfc.model.DanCorressDataToRPNumberCheckerModel;
import com.nbfc.model.DanGenerateRpNumberForPaymentCheckerModel;
import com.nbfc.model.DanPaymentInitiateModel;
import com.nbfc.model.DisburseNonDisburseModel;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIName;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.DanGenerateRpNumberForPaymentCheckerService;
import com.nbfc.service.NBFCUserReportService;
import com.nbfc.service.PortfolioInfoService;
import com.nbfc.service.UserService;
import com.nbfc.service.LoginService;
import com.nbfc.service.MLIRegService;
import com.nbfc.service.UserActivityService;
import com.nbfc.validation.EmployeeValidator;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class DanGenerateRpNumberForPaymentCheckerController {

	// ---------------------------------------------------------
	@Autowired
	DanGenerateRpNumberForPaymentCheckerService danGenerateRpNumberForPaymentCheckerService;
	@Autowired
	LoginService lofinService;

	@Autowired
	MLIRegService mliRegService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	EmployeeValidator validator;
	@Autowired
	UserService employeeService;
	List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;
	MLIDetails mliDetails;
	MLIName mliName;
	List<String> danList = new ArrayList<String>();
	@Autowired
	PortfolioInfoService portfolioInfoService;
	@Autowired
	NBFCUserReportService nbfcUserReportService;
	List<DanGenerateRpNumberForPaymentCheckerModel> listPaymentInitiation = new ArrayList<DanGenerateRpNumberForPaymentCheckerModel>();

	static Logger log = Logger
			.getLogger(DanGenerateRpNumberForPaymentCheckerController.class
					.getName());

	@RequestMapping(value = "/danGenerateRpNumberForPaymentChecker", method = RequestMethod.GET)
	public ModelAndView getDanGenerateData(
			@ModelAttribute("command") DanGenerateRpNumberForCheckerApprovalBean bean,
			BindingResult result, Model modelMsg, HttpSession session) {
		log.info("Enter in getDanGenerateData() method in DanGenerateRpNumberForPaymentCheckerController class");
		Map<String, Object> model = new HashMap<String, Object>();

		List<String> portFiloNameList = new ArrayList<String>();
		Map<String, String> borDetailsList;
		String userId = (String) session.getAttribute("userId");
		mliDetails = employeeService.getBNKId(userId);
		String mliId = mliDetails.getMem_bnk_id() + mliDetails.getMem_zne_id()
				+ mliDetails.getMem_brn_id();

		List<DanGenerateRpNumberForCheckerApprovalBean> list;
		if (mliId != null) {
			list = prepareDanGenerateDataListofBean(danGenerateRpNumberForPaymentCheckerService
					.getApprovedDanData(mliId));

		} else {
			throw new CustomExceptionHandler("MLI name is null");
		}

		if (list.size() > 0 || !list.isEmpty()) {
			model.put("dataList", list);

		} else {
			log.info("Throwing exception due to data not found in DanGenerateRpNumberForPaymentCheckerController class");
			model.put("dataList", "");

			// throw new CustomExceptionHandler("No data found");
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
//end---------------------------------------------------------------------------------------
//		model.put("actList",
//				userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
		model.put("homePage", "nbfcCheckerHome");
		log.info("Exit in getDanGenerateData() method in DanGenerateRpNumberForPaymentCheckerController class");

		return new ModelAndView("DanGeneratedRpNumberMLIChecker", model);

	}

	@RequestMapping(value = "/danPaymentInitiation", method = RequestMethod.GET)
	public ModelAndView getDanPaymentInitiation(
			@ModelAttribute("command") DanGenerateRpNumberForCheckerApprovalBean bean,
			BindingResult result, Model modelMsg, HttpSession session) {
		log.info("Exit in getDanPaymentInitiation() method in DanGenerateRpNumberForPaymentCheckerController class");

		List<String> portFiloNameList = new ArrayList<String>();
		Map<String, String> borDetailsList;
		List<DanGenerateRpNumberForCheckerApprovalBean> list = null;
		Map<String, Object> model = new HashMap<String, Object>();

		String userId = (String) session.getAttribute("userId");
		mliDetails = employeeService.getBNKId(userId);
		String mliId = mliDetails.getMem_bnk_id() + mliDetails.getMem_zne_id()
				+ mliDetails.getMem_brn_id();

		if (mliId != null) {
			list = preparePaymentInitiation(danGenerateRpNumberForPaymentCheckerService
					.getApprovedDanDataForPayment(mliId));
		}
		if (list.size() > 0 || !list.isEmpty()) {
			model.put("dataList", list);

		} else {
			log.info("Throwing exception in DanGenerateRpNumberForPaymentCheckerController class");
			model.put("dataList", "");

			// throw new CustomExceptionHandler("No DAN approved data");
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
				"danPaymentInitiation"));// Added by Say 31 Jan19
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------
//		model.put("actList",
//				userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
		model.put("homePage", "nbfcCheckerHome");
		log.info("Exit in getDanPaymentInitiation() method in DanGenerateRpNumberForPaymentCheckerController class");

		return new ModelAndView("DanPaymentInitiationChecker", model);
	}

	@RequestMapping(value = "/danPaymentInitiationCheckerApproval", method = RequestMethod.POST)
	public ModelAndView getDanApprovalSuccessPage(
			@ModelAttribute("command") DanGenerateRpNumberForCheckerApprovalBean bean,
			BindingResult result, String message) {
		log.info("Enter in getDanApprovalSuccessPage() method in DanGenerateRpNumberForPaymentCheckerController class");

		Map<String, Object> model = new HashMap<String, Object>();
		List<String> list1 = bean.getChcktbl();
		int count = danGenerateRpNumberForPaymentCheckerService
				.getDanRpApproval(list1);

		model.put("message", message);
		
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
				"danPaymentInitiation"));// Added by Say 31 Jan19
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------
//		model.put("actList",
//				userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
		model.put("homePage", "nbfcCheckerHome");
		if (count > 0) {
			model.put("message", "Successfully Approved");

			return new ModelAndView("DanCheckerApprovalSuccess", model);

		}
		log.info("Exit in getDanApprovalSuccessPage() method in DanGenerateRpNumberForPaymentCheckerController class");

		return new ModelAndView(
				"redirect:/danGenerateRpNumberForPaymentChecker.html");

	}

	@RequestMapping(value = "/danRPNumberCheckerRejection", method = RequestMethod.POST)
	public ModelAndView getDanApprovalRejectionPage(
			@ModelAttribute("command") DanGenerateRpNumberForCheckerApprovalBean bean,
			BindingResult result, String message) {
		log.info("Enter in getDanApprovalRejectionPage() method in DanGenerateRpNumberForPaymentCheckerController class");

		Map<String, Object> model = new HashMap<String, Object>();
		List<String> list1 = bean.getChcktbl();
		int count = danGenerateRpNumberForPaymentCheckerService
				.getDanRpRejection(list1, bean.getRemark());
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
//end---------------------------------------------------------------------------------------
//		model.put("actList",
//				userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
		model.put("homePage", "nbfcCheckerHome");
		if (count > 0) {
			log.info("Data is rejected in getDanApprovalRejectionPage() method in DanGenerateRpNumberForPaymentCheckerController class");

			model.put("message", "Successfully Rejected");
			return new ModelAndView("DanCheckerApprovalSuccess", model);
		}
		log.info("Exit in getDanApprovalRejectionPage() method in DanGenerateRpNumberForPaymentCheckerController class");

		return new ModelAndView(
				"redirect:/danGenerateRpNumberForPaymentChecker.html");

	}

	@RequestMapping(value = "/danPaymentInitiationCheckerRejection", method = RequestMethod.POST)
	public ModelAndView getDanApprovalRejection(
			@ModelAttribute("command") DanGenerateRpNumberForCheckerApprovalBean bean,
			BindingResult result, String message) {
		log.info("Enter in getDanApprovalRejectionPage() method in DanGenerateRpNumberForPaymentCheckerController class");

		listPaymentInitiation = new ArrayList<DanGenerateRpNumberForPaymentCheckerModel>();

		int count = 0;
		List<String> checkedData = bean.getChcktbl2();
		List<DanGenerateRpNumberForPaymentCheckerModel> initiatedData = null;
		if (!checkedData.isEmpty()) {
			for (String str : checkedData) {
				String array1[] = str.split("/");
				if (array1.length > 0) {
					createPaymentInitiation(array1);
				}
			}
		}

		Map<String, Object> model = new HashMap<String, Object>();
		count = danGenerateRpNumberForPaymentCheckerService
				.getDanCheckerApprovalRejection(listPaymentInitiation,
						bean.getRemark());
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
				"danPaymentInitiation"));// Added by Say 31 Jan19
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------
//		model.put("actList",
//				userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
		model.put("homePage", "nbfcCheckerHome");
		if (count > 0) {
			log.info("Data is rejected in getDanApprovalRejectionPage() method in DanGenerateRpNumberForPaymentCheckerController class");

			model.put("message", "Successfully Rejected");
			return new ModelAndView("DanCheckerApprovalSuccess", model);
		}
		log.info("Exit in getDanApprovalRejectionPage() method in DanGenerateRpNumberForPaymentCheckerController class");

		return new ModelAndView("redirect:/danPaymentInitiation.html");

	}

	@RequestMapping(value = "/danPaymentInitiationSuccessPage", method = RequestMethod.POST)
	public ModelAndView getDanPaymentInitiationSuccessPage(
			@ModelAttribute("command") DanGenerateRpNumberForCheckerApprovalBean bean,
			BindingResult result, String message, HttpSession session) {
		log.info("Enter in getDanPaymentInitiationSuccessPage() method in DanGenerateRpNumberForPaymentCheckerController class");
		int count = 0;
		listPaymentInitiation = new ArrayList<DanGenerateRpNumberForPaymentCheckerModel>();

		List<String> checkedData = bean.getChcktbl2();
		List<DanGenerateRpNumberForPaymentCheckerModel> initiatedData = new ArrayList<DanGenerateRpNumberForPaymentCheckerModel>();
		if (!checkedData.isEmpty()) {
			for (String str : checkedData) {
				String array1[] = str.split("/");
				if (array1.length > 0) {
					createPaymentInitiation(array1);
				}
			}
		}
		if (!listPaymentInitiation.isEmpty()) {
			count = danGenerateRpNumberForPaymentCheckerService
					.saveDataForPaymentInitiation(listPaymentInitiation);
		}
		Map<String, Object> model = new HashMap<String, Object>();

		String userId = (String) session.getAttribute("userId");
		mliDetails = employeeService.getBNKId(userId);
		String mliId = mliDetails.getMem_bnk_id() + mliDetails.getMem_zne_id()
				+ mliDetails.getMem_brn_id();
		List<DanGenerateRpNumberForCheckerApprovalBean> list = null;

		if (count > 0) {
			list = preparePaymentInitiatedData(danGenerateRpNumberForPaymentCheckerService
					.getPaymentInitiatedData(listPaymentInitiation, mliId));

			if (list.size() > 0) {
				model.put("initiatedDataList", list);
			} else {
				throw new CustomExceptionHandler(
						"Payment initiated data no available");
			}

		}

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
				"danPaymentInitiation"));// Added by Say 31 Jan19
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------
		model.put("homePage", "nbfcCheckerHome");
		if (count > 0) {
			return new ModelAndView("DanPaymentCheckerSuccess", model);

		}
		log.info("Exit from getDanPaymentInitiationSuccessPage() method in DanGenerateRpNumberForPaymentCheckerController class");

		return new ModelAndView("redirect:/danPaymentInitiation.html", model);
	}

	private List<DanGenerateRpNumberForCheckerApprovalBean> preparePaymentInitiatedData(
			List<DanPaymentInitiateModel> paymentInitiatedData) {
		List<DanGenerateRpNumberForCheckerApprovalBean> list = new ArrayList<DanGenerateRpNumberForCheckerApprovalBean>();
		if (paymentInitiatedData.size() > 0) {
			DanGenerateRpNumberForCheckerApprovalBean dgbean = null;
			for (DanPaymentInitiateModel dm : paymentInitiatedData) {
				dgbean = new DanGenerateRpNumberForCheckerApprovalBean();
				dgbean.setRpNumber(dm.getRpNumber());
				dgbean.setVirtualAccountNumber(dm.getVirtualAccountNumber());
				dgbean.setAmount(BigDecimal
						.valueOf(Double.valueOf(dm.getAmount())).toBigDecimal()
						.toPlainString());
				dgbean.setIfscCode("CORP0000633");

				dgbean.setBeneficiaryAcccount("CGTMSE");

				dgbean.setBranch("FCS Center Bangalore");

				String status = "";
				if (dm.getStatus().equals("I")) {
					status = "Initiated for Payment";
				}
				dgbean.setStatus(status);
				list.add(dgbean);
			}
		}

		return list;
	}

	private void createPaymentInitiation(String[] array1) {
		List<DanGenerateRpNumberForPaymentCheckerModel> list = new ArrayList<DanGenerateRpNumberForPaymentCheckerModel>();
		DanGenerateRpNumberForPaymentCheckerModel dataModel = new DanGenerateRpNumberForPaymentCheckerModel();
		dataModel.setRpNumber(array1[0]);
		// dataModel.setAmount(Long.parseLong(array1[1]));
		dataModel.setAmount(Double.parseDouble(array1[1]));
		dataModel.setVirtualAccountNumber(array1[2]);
		// dataModel.setDanId(array1[3]);

		listPaymentInitiation.add(dataModel);
		// TODO Auto-generated method stub

	}

	@RequestMapping(value = "/danCorrespondingData", method = RequestMethod.GET)
	public ModelAndView getDanCorrespondingData(
			@ModelAttribute(value = "command") DanCorrespondingDataListBeanCheckerBean bean,
			BindingResult result, String rpNumber, String danId,
			HttpSession session) {
		log.info("Enter from getDanCorrespondingData() method in DanGenerateRpNumberForPaymentCheckerController class");

		Map<String, Object> model = new HashMap<String, Object>();
		List<DanCorrespondingDataListBeanCheckerBean> list;

		// if(!rpNumber.isEmpty()&&!danId.isEmpty()){
		if (!rpNumber.isEmpty()) {

			session.setAttribute("rpNumber", rpNumber);
			session.setAttribute("danId", danId);
			list = prepareDanGenerateDataListCorresponingData(danGenerateRpNumberForPaymentCheckerService
					.getDanCorrespondingData(rpNumber, danId));
		} else {
			throw new CustomExceptionHandler("RP Number is null");
		}

		if (list.size() > 0) {
			model.put("dataList1", list);
		} else {
			model.put("dataList1", "");
		}

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
//end---------------------------------------------------------------------------------------
		model.put("homePage", "nbfcCheckerHome");
		log.info("Exit from getDanCorrespondingData() method in DanGenerateRpNumberForPaymentCheckerController class");

		return new ModelAndView("DanGeneratedDataCorrespondingList", model);
	}

	@RequestMapping(value = "/disburseNonDisburseData", method = RequestMethod.GET)
	public ModelAndView getDisburseNonDisburseData(
			@ModelAttribute(value = "command") DanCorrespondingDataListBeanCheckerBean bean,
			BindingResult result, String file_Id, String danId,
			String disbursedStatus, HttpSession session) {
		log.info("Enter from getDisburseNonDisburseData() method in DanGenerateRpNumberForPaymentCheckerController class");

		Map<String, Object> model = new HashMap<String, Object>();
		if (!file_Id.isEmpty() && !disbursedStatus.isEmpty()) {
			session.setAttribute("file_Id", file_Id);
			session.setAttribute("danId", danId);

			model.put("dataList1",
					danGenerateRpNumberForPaymentCheckerService
							.getDisburseNonDisburseData(file_Id, danId,
									disbursedStatus));

			// prepareDisburseNonDisburseList(rpNumber));
		}
		model.put("disStatus", disbursedStatus);
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
//end---------------------------------------------------------------------------------------
		model.put("homePage", "nbfcCheckerHome");
		log.info("Exit from getDisburseNonDisburseData() method in DanGenerateRpNumberForPaymentCheckerController class");

		return new ModelAndView("DisburseNonDisburseDataDanList", model);

	}

	/*
	 * private List<DanCorrespondingDataListBeanCheckerBean>
	 * prepareDisburseNonDisburseList
	 * (List<DanCorrespondingDataListBeanCheckerBean>
	 * DanCorrespondingDataListBeanCheckerBean,String disStatus) {
	 * 
	 * 
	 * 
	 * 
	 * 
	 * return DanCorrespondingDataListBeanCheckerBean; }
	 */

	private List<DanCorrespondingDataListBeanCheckerBean> prepareDanGenerateDataListCorresponingData(
			List<DanCorressDataToRPNumberCheckerModel> modelList) {
		// TODO Auto-generated method stub
		List<DanCorrespondingDataListBeanCheckerBean> list = new ArrayList<DanCorrespondingDataListBeanCheckerBean>();
		if (modelList.size() > 0) {
			DanCorrespondingDataListBeanCheckerBean dgbean = null;
			for (DanCorressDataToRPNumberCheckerModel dm : modelList) {
				dgbean = new DanCorrespondingDataListBeanCheckerBean();
				dgbean.setPortfolioName(dm.getPortfolioName());
				dgbean.setFileName(dm.getFili_Id());
				dgbean.setTotalFee(dm.getTotalFee());
				// dgbean.setDanId(dm.getDanId());
				// dgbean.setDanId(dm.getDanId());

				list.add(dgbean);
			}
		}

		return list;
	}

	private List<DanGenerateRpNumberForCheckerApprovalBean> prepareDanGenerateDataListofBean(
			List<DanCorrespondigDataModel> modelData) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		// List<DanGenerateRpNumberForPaymentCheckerModel> modelData=new
		// ArrayList<DanGenerateRpNumberForPaymentCheckerModel>();
		/*
		 * List<String>danId1=new ArrayList<String>();
		 * 
		 * for(DanCorrespondigDataModel dg:danId){
		 * 
		 * danList.add(dg.getDanId());
		 * 
		 * } Set pSet = new HashSet(danList); ArrayList uniquePList = new
		 * ArrayList(pSet);
		 */

		// if(!uniquePList.isEmpty()){
		// modelData=danGenerateRpNumberForPaymentCheckerService.getApprovedDanData(uniquePList);

		// }

		List<DanGenerateRpNumberForCheckerApprovalBean> list = new ArrayList<DanGenerateRpNumberForCheckerApprovalBean>();
		if (modelData.size() > 0) {
			DanGenerateRpNumberForCheckerApprovalBean dgbean = null;
			for (DanCorrespondigDataModel dm : modelData) {
				dgbean = new DanGenerateRpNumberForCheckerApprovalBean();
				dgbean.setRpNumber(dm.getRpNumber());
				// dgbean.setAmount(Long.parseLong(dm.getTotalFee()));
				dgbean.setAmount(BigDecimal
						.valueOf(Double.parseDouble(dm.getTotalFee()))
						.toBigDecimal().toPlainString());
				// dgbean.setDanId(dm.getDanId());
				if (dm.getDate() != null) {
					dgbean.setDate(df.format(dm.getDate()));
				} else {
					dgbean.setDate("");
					// throw new
					// CustomExceptionHandler("Generate Date is null");
				}
				String status = "";
				if (dm.getStatus().equals("DNMA")) {
					status = "Pending for Approval";
				}
				dgbean.setStatus(status);
				list.add(dgbean);
			}
		}

		return list;
	}

	private List<DanGenerateRpNumberForCheckerApprovalBean> preparePaymentInitiation(
			List<DanPaymentInitiateModel> danGenetateDataForAppoval) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		List<DanGenerateRpNumberForCheckerApprovalBean> list = new ArrayList<DanGenerateRpNumberForCheckerApprovalBean>();
		if (danGenetateDataForAppoval.size() > 0) {
			DanGenerateRpNumberForCheckerApprovalBean dgbean = null;
			for (DanPaymentInitiateModel dm : danGenetateDataForAppoval) {
				dgbean = new DanGenerateRpNumberForCheckerApprovalBean();
				dgbean.setRpNumber(dm.getRpNumber());
				dgbean.setVirtualAccountNumber(dm.getVirtualAccountNumber());
				dgbean.setAmount(BigDecimal
						.valueOf(Double.valueOf(dm.getAmount())).toBigDecimal()
						.toPlainString());
				// dgbean.setDate(df.format(dm.getDate()));
				// dgbean.setDanId(dm.getDanId());
				if (dm.getDate() != null) {
					dgbean.setDate(df.format(dm.getDate()));
				} else {
					dgbean.setDate("");
					// throw new
					// CustomExceptionHandler("Generate Date is null");
				}
				// sdgbean.setDate(df.format(dm.getDate()));
				String status = "";
				if (dm.getStatus().equals("N")) {
					status = "Pending for Payment Initiation";
				}
				dgbean.setStatus(status);
				list.add(dgbean);
			}
		}

		return list;
	}

	@ExceptionHandler(CustomExceptionHandler.class)
	public ModelAndView handleCustomException(CustomExceptionHandler ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();

		//added by say 6 feb-----------------------
		model1.put("adminlist",
				userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
		model1.put("guaranteelist",
				userActivityService.getActivity("NBFCCHECKER", "Registration"));
		model1.put("applicationList",
				userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
		model1.put("RPaymentList",
				userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
		model1.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model1.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		model1.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------
		model1.put("homePage", "Home");
		ModelAndView model = new ModelAndView("customException", model1);
		model.addObject("customException", ex.getMessage());
		return model;

	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();

		//added by say 6 feb-----------------------
		model1.put("adminlist",
				userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
		model1.put("guaranteelist",
				userActivityService.getActivity("NBFCCHECKER", "Registration"));
		model1.put("applicationList",
				userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
		model1.put("RPaymentList",
				userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
		model1.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model1.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		model1.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------
		model1.put("homePage", "nbfcCheckerHome");
		ModelAndView model = new ModelAndView("exception", model1);
		model.addObject("exception", ex);
		return model;

	}

	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithException(ArithmeticException ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();

		//added by say 6 feb-----------------------
		model1.put("adminlist",
				userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
		model1.put("guaranteelist",
				userActivityService.getActivity("NBFCCHECKER", "Registration"));
		model1.put("applicationList",
				userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
		model1.put("RPaymentList",
				userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));

		model1.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model1.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		model1.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------
		model1.put("homePage", "nbfcCheckerHome");
		ModelAndView model = new ModelAndView("exception", model1);
		model.addObject("exception", ex);
		return model;

	}

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullException(NullPointerException ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();

		//added by say 6 feb-----------------------
		model1.put("adminlist",
				userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
		model1.put("guaranteelist",
				userActivityService.getActivity("NBFCCHECKER", "Registration"));
		model1.put("applicationList",
				userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
		model1.put("RPaymentList",
				userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
		model1.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model1.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		model1.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------
		model1.put("homePage", "nbfcCheckerHome");
		ModelAndView model = new ModelAndView("exception", model1);
		model.addObject("exception", "Data is null");
		return model;

	}

}
