package com.nbfc.controller;

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
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.BorrowerDetailsBean;
import com.nbfc.bean.DANAllocationBean;
import com.nbfc.bean.DanGenerateRpNumberForCheckerApprovalBean;
import com.nbfc.bean.UserBean;
import com.nbfc.model.DANAllocatioListEntity;
import com.nbfc.model.DANAllocation;
import com.nbfc.model.DANAllocationFee;
import com.nbfc.model.DanAllocationForASFNbfcMakerUsingVWModel;
import com.nbfc.model.DanAllocationForNbfcMakerUsingVWModel;
import com.nbfc.model.DanCorrespondigDataASFModel;
import com.nbfc.model.DanGenerateRpNumberForPaymentCheckerModel;
import com.nbfc.model.DanPaymentInitiateASFModel;
import com.nbfc.model.DanPaymentInitiateModel;
import com.nbfc.model.MLICGFeeDetails;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIName;
import com.nbfc.model.MliMakerEntity;
import com.nbfc.model.PortfolioNumInfo;
import com.nbfc.model.User;
import com.nbfc.service.DANAllocationService;
import com.nbfc.service.DanGenerateRpNumberForPaymentCheckerService;
import com.nbfc.service.PortfolioInfoService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class DANAllocationForASFController {

	@Autowired
	UserActivityService userActivityService;
	@Autowired
	DANAllocationService danAllocationService;
	@Autowired
	UserService employeeService;
	@Autowired
	PortfolioInfoService portfolioInfoService;
	@Autowired
	DanGenerateRpNumberForPaymentCheckerService danGenerateRpNumberForPaymentCheckerService;
	Long totalAmount=0L;
	private List<PortfolioNumInfo> appRefNum = new ArrayList<PortfolioNumInfo>();
	static Logger log = Logger.getLogger(NBFCController.class.getName());
	List<DANAllocationBean> danDetailList = new ArrayList<DANAllocationBean>();
	List<BorrowerDetailsBean> borrowerDetailList = new ArrayList<BorrowerDetailsBean>();
	MLIDetails mliDetails;
	MLIName mliName;
	String value;
	List<String> portFiloNameList = new ArrayList<String>();
	List<Float> portFiloRateList;
	List<String> fileNameList;
	List<String> portFoliNameList = new ArrayList<String>();
	List<String> danIDList = new ArrayList<String>();
	List<String> fileIdList = new ArrayList<String>();
	List<DANAllocation> danlist= new ArrayList<DANAllocation>();
	List<DanAllocationForASFNbfcMakerUsingVWModel> allocationList= new ArrayList<DanAllocationForASFNbfcMakerUsingVWModel>();
	List<DanGenerateRpNumberForPaymentCheckerModel> listPaymentInitiation = new ArrayList<DanGenerateRpNumberForPaymentCheckerModel>();
	List<MLICGFeeDetails> borworDetailsList = new ArrayList<MLICGFeeDetails>();
	Map<String, String> borDetailsList;
	static double totalOfAmount=0;

		
	@RequestMapping(value = "danAllocationASF", method = RequestMethod.GET)
	public ModelAndView danAllocationASF(
			@ModelAttribute("command") DANAllocationBean loginBean,
			BindingResult result, Model model, HttpSession session)
			throws ClassCastException {
		log.info("adminHome***************************************************************************************************************"
				+ session.getAttribute("userId"));
		danDetailList.clear();

		String userId = (String) session.getAttribute("userId");
		mliDetails = employeeService.getBNKId(userId);
		String mliId=mliDetails.getMem_bnk_id()+mliDetails.getMem_zne_id()+mliDetails.getMem_brn_id();
		List<DANAllocationBean>list=createDanAllocationIntoBean(danAllocationService.getDataForDanAllocationASF(mliId));
		
    	Map<String, Object> modelAct = new HashMap<String, Object>();
		if(list.size()>0){
			modelAct.put("danDetailList",	list);
		}else{
			modelAct.put("danDetailList","");
		}
		//Added by say 6 FEb-------------------------------------------------------------
		modelAct.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		modelAct.put("guaranteelist",
				userActivityService.getActivity("NBFCMAKER", "Registration"));
		modelAct.put("applicationList",
				userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		modelAct.put("RPaymentList",
				userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		modelAct.put("actName", userActivityService.getActivityName("NBFCMAKER",
				"danAllocation"));// Added by Say 31 Jan19
		modelAct.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));
		modelAct.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));
		modelAct.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		modelAct.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//		modelAct.put("actList",
//				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		modelAct.put("homePage", "nbfcMakerHome");
		return new ModelAndView("danAllocationPageASF", modelAct);
	}

	
	
	private List<DANAllocationBean> createDanAllocationIntoBean(
			List<DanAllocationForASFNbfcMakerUsingVWModel> dataForDanAllocation) {
		// TODO Auto-generated method stub
		List<DANAllocationBean>list=new ArrayList<DANAllocationBean>();
		DANAllocationBean db=null;
		totalOfAmount=0;
		if(!dataForDanAllocation.isEmpty()){
			for(DanAllocationForASFNbfcMakerUsingVWModel danAllocate:dataForDanAllocation){
				db=new DANAllocationBean();
				db.setPortfoliName(danAllocate.getPortfolioName());
				db.setFileName(danAllocate.getFileId());
				db.setPortfolioRate(danAllocate.getPortfolioRate());
				//db.setTotalFee(BigDecimal.valueOf((Double.parseDouble(danAllocate.getAmount()))).toBigDecimal().toPlainString());
				db.setTotalFee(danAllocate.getAmount());
				db.setDAN_ID(danAllocate.getDanId());
				db.setMliId(danAllocate.getMliId());
				totalOfAmount=totalOfAmount+Double.parseDouble(danAllocate.getAmount());
				list.add(db);
			}
		}
		
		return list;
	}
	
	@RequestMapping(value = "submitForConfirmASF", method = RequestMethod.GET)
	public ModelAndView submitForConfirmASF(
			@ModelAttribute("command") DANAllocationBean loginBean,
			BindingResult result, Model model, HttpSession session) {
		log.info("adminHome***************************************************************************************************************"
				+ session.getAttribute("userId"));
		borrowerDetailList.clear();
		Map<String, Object> modelAct = new HashMap<String, Object>();
		allocationList=new ArrayList<DanAllocationForASFNbfcMakerUsingVWModel>();
		List<String> data=loginBean.getChcktbl();
		for(String dt:data){
			String []arr=dt.split("@");
			setDataToBean(arr);

		}
		/*if(!allocationList.isEmpty()){
			danAllocationService.getRpNumberData(allocationList);
		}*/
		List<DANAllocationBean> danDetailList=null;
		if(!allocationList.isEmpty()){
			 danDetailList=createDanAllocationIntoBeanASF(allocationList);

		}
		
		modelAct.put("totalOfAmount", BigDecimal.valueOf(totalOfAmount).toBigDecimal().toPlainString());
		modelAct.put("danDetailList", danDetailList);
		//Added by say 6 FEb-------------------------------------------------------------
		modelAct.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		modelAct.put("guaranteelist",
				userActivityService.getActivity("NBFCMAKER", "Registration"));
		modelAct.put("applicationList",
				userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		modelAct.put("RPaymentList",
				userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		modelAct.put("actName", userActivityService.getActivityName("NBFCMAKER","danAllocation"));// Added by Say 31 Jan19
		modelAct.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));
		modelAct.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));
		modelAct.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		modelAct.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
		modelAct.put("homePage", "nbfcMakerHome");
		return new ModelAndView("submitForConfirmPageASF", modelAct);
	}

	private List<DANAllocationBean> createDisbuseCaseBean(
			List<MLICGFeeDetails> mliCGFeeDetails,String portfolioRate) {
		// TODO Auto-generated method stub
		List<DANAllocationBean>list=new ArrayList<DANAllocationBean>();
		DANAllocationBean danAllocationBean=null;
		totalOfAmount=0;
		if(!mliCGFeeDetails.isEmpty()){
			for(MLICGFeeDetails danAllocate:mliCGFeeDetails){
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	               
                //to convert Date to String, use format method of SimpleDateFormat class.
                
				danAllocationBean=new DANAllocationBean();
				
				danAllocationBean.setPortfoliName(danAllocate.getPortfolio_Name());
				danAllocationBean.setBorwerName(danAllocate.getMSE_NAME());
			//	danAllocationBean.setSanctionAmount(String.valueOf(danAllocate.getSANCTIONED_AMOUNT()));
				danAllocationBean.setSanctionAmount(String.valueOf(danAllocate.getOUTSTANDING_AMOUNT()));

				danAllocationBean.setLoneAccountNo(danAllocate.getLOAN_ACCOUNT_NO());
				if(danAllocate.getSNCTION_DATE()!=null){
					String sanctionDate = dateFormat.format(danAllocate.getSNCTION_DATE());
					danAllocationBean.setSanctionDate(sanctionDate);
				}
				if(danAllocate.getFIRST_DISBURSEMENT_DATE()!=null){
					String disDate = dateFormat.format(danAllocate.getFIRST_DISBURSEMENT_DATE());
					danAllocationBean.setDisbursermentDate(disDate);
				}
				float intrRate=Float.valueOf(portfolioRate);
				double calculatedFee=(danAllocate.getOUTSTANDING_AMOUNT()*intrRate)/100;
				
				danAllocationBean.setAmount(BigDecimal.valueOf(calculatedFee).toBigDecimal().toPlainString());
				danAllocationBean.setRateOfInterest(String.valueOf(portfolioRate));
				
				list.add(danAllocationBean);
			}
		}
		
		return list;
	}
	private void setDataToBean(String[] arr) {
		// TODO Auto-generated method stub
		
		if(arr.length>0){
			DanAllocationForASFNbfcMakerUsingVWModel db=new DanAllocationForASFNbfcMakerUsingVWModel();
			db.setDanId(arr[0]);
			db.setPortfolioRate(arr[1]);
			db.setAmount(arr[2]);
			db.setPortfolioName(arr[3]);
			db.setFileId(arr[4]);
			db.setMliId(arr[5]);
			allocationList.add(db);
		}
	}
	private List<DANAllocationBean> createDanAllocationIntoBeanASF(
			List<DanAllocationForASFNbfcMakerUsingVWModel> dataForDanAllocation) {
		// TODO Auto-generated method stub
		List<DANAllocationBean>list=new ArrayList<DANAllocationBean>();
		DANAllocationBean db=null;
		totalOfAmount=0;
		if(!dataForDanAllocation.isEmpty()){
			for(DanAllocationForASFNbfcMakerUsingVWModel danAllocate:dataForDanAllocation){
				db=new DANAllocationBean();
				db.setPortfoliName(danAllocate.getPortfolioName());
				db.setFileName(danAllocate.getFileId());
				db.setPortfolioRate(danAllocate.getPortfolioRate());
				//db.setTotalFee(BigDecimal.valueOf((Double.parseDouble(danAllocate.getAmount()))).toBigDecimal().toPlainString());
				db.setTotalFee(danAllocate.getAmount());
				db.setDAN_ID(danAllocate.getDanId());
				db.setMliId(danAllocate.getMliId());
				totalOfAmount=totalOfAmount+Double.parseDouble(danAllocate.getAmount());
				list.add(db);
			}
		}
		
		return list;
	}

	@RequestMapping(value = "/danPaymentInitiationASF", method = RequestMethod.GET)
	public ModelAndView getDanPaymentInitiationASF(
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
			list = preparePaymentInitiationASF(danAllocationService
					.getApprovedDanDataForPaymentASF(mliId));
		}
		if (list.size() > 0 || !list.isEmpty()) {
			model.put("dataList", list);

		} else {
			log.info("Throwing exception in DanGenerateRpNumberForPaymentCheckerController class");
			model.put("dataList", "");

			// throw new CustomExceptionHandler("No DAN approved data");
		}
		model.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));
		//added by say 6 feb-----------------------
		model.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("NBFCMAKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("nbfcMakerHome",
				"danPaymentInitiationASF"));// Added by Say 31 Jan19
		model.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));
		model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------
//		model.put("actList",
//				userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
		model.put("homePage", "nbfcMakerHome");
		log.info("Exit in getDanPaymentInitiation() method in DanGenerateRpNumberForPaymentCheckerController class");

		return new ModelAndView("DanPaymentInitiationCheckerASF", model);
	}





//	@RequestMapping(value = "/danPaymentInitiationCheckerApproval", method = RequestMethod.POST)
//	public ModelAndView getDanApprovalSuccessPage(
//			@ModelAttribute("command") DanGenerateRpNumberForCheckerApprovalBean bean,
//			BindingResult result, String message) {
//		log.info("Enter in getDanApprovalSuccessPage() method in DanGenerateRpNumberForPaymentCheckerController class");
//
//		Map<String, Object> model = new HashMap<String, Object>();
//		List<String> list1 = bean.getChcktbl();
//		int count = danGenerateRpNumberForPaymentCheckerService.getDanRpApproval(list1);
//
//		model.put("message", message);
//		
//		//added by say 6 feb-----------------------
//		model.put("adminlist",
//				userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
//		model.put("guaranteelist",
//				userActivityService.getActivity("NBFCCHECKER", "Registration"));
//		model.put("applicationList",
//				userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
//		model.put("RPaymentList",
//				userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
//		model.put("actName", userActivityService.getActivityName("NBFCCHECKER",
//				"danPaymentInitiation"));// Added by Say 31 Jan19
//		model.put("repList",
//				userActivityService.getReport("NBFCCHECKER", "User_Report"));
//		model.put("GMaintainlist", userActivityService.getActivity(
//				"NBFCCHECKER", "Guarantee_Maintenance"));
////end---------------------------------------------------------------------------------------
////		model.put("actList",
////				userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
//		model.put("homePage", "nbfcCheckerHome");
//		if (count > 0) {
//			model.put("message", "Successfully Approved");
//
//			return new ModelAndView("DanCheckerApprovalSuccess", model);
//
//		}
//		log.info("Exit in getDanApprovalSuccessPage() method in DanGenerateRpNumberForPaymentCheckerController class");
//
//		return new ModelAndView(
//				"redirect:/danGenerateRpNumberForPaymentChecker.html");
//
//	}
	
	private List<DanGenerateRpNumberForCheckerApprovalBean> preparePaymentInitiationASF(
			List<DanPaymentInitiateASFModel> approvedDanDataForPaymentASF) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		List<DanGenerateRpNumberForCheckerApprovalBean> list = new ArrayList<DanGenerateRpNumberForCheckerApprovalBean>();
		if (approvedDanDataForPaymentASF.size() > 0) {
			DanGenerateRpNumberForCheckerApprovalBean dgbean = null;
			for (DanPaymentInitiateASFModel dm : approvedDanDataForPaymentASF) {
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



	@RequestMapping(value = "/danPaymentInitiationSuccessPageASF", method = RequestMethod.POST)
	public ModelAndView getDanPaymentInitiationSuccessPageASF(
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
					.getPaymentInitiatedDataASF(listPaymentInitiation, mliId));

			if (list.size() > 0) {
				model.put("initiatedDataList", list);
			} else {
				throw new CustomExceptionHandler(
						"Payment initiated data no available");
			}

		}

		//added by say 6 feb-----------------------
		model.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("NBFCMAKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("NBFCMAKER",
				"danPaymentInitiationASF"));// Added by Say 31 Jan19
		model.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));
		model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
		model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
//end---------------------------------------------------------------------------------------
		model.put("homePage", "nbfcMakerHome");
		if (count > 0) {
			return new ModelAndView("DanPaymentCheckerSuccessASF", model);

		}
		log.info("Exit from getDanPaymentInitiationSuccessPage() method in DanGenerateRpNumberForPaymentCheckerController class");

		return new ModelAndView("redirect:/danPaymentInitiationASF.html", model);
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




	private List<DanGenerateRpNumberForCheckerApprovalBean> preparePaymentInitiatedData(
			List<DanPaymentInitiateASFModel> paymentInitiatedData) {
		List<DanGenerateRpNumberForCheckerApprovalBean> list = new ArrayList<DanGenerateRpNumberForCheckerApprovalBean>();
		if (paymentInitiatedData.size() > 0) {
			DanGenerateRpNumberForCheckerApprovalBean dgbean = null;
			for (DanPaymentInitiateASFModel dm : paymentInitiatedData) {
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
	
}
