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
import com.nbfc.bean.UserBean;
import com.nbfc.model.DANAllocatioListEntity;
import com.nbfc.model.DANAllocation;
import com.nbfc.model.DANAllocationFee;
import com.nbfc.model.DanAllocationForNbfcMakerUsingVWModel;
import com.nbfc.model.MLICGFeeDetails;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIName;
import com.nbfc.model.MliMakerEntity;
import com.nbfc.model.PortfolioNumInfo;
import com.nbfc.model.User;
import com.nbfc.service.DANAllocationService;
import com.nbfc.service.PortfolioInfoService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class DANAllocationController {

	@Autowired
	UserActivityService userActivityService;
	@Autowired
	DANAllocationService danAllocationService;
	@Autowired
	UserService employeeService;
	@Autowired
	PortfolioInfoService portfolioInfoService;
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
	List<DanAllocationForNbfcMakerUsingVWModel> allocationList= new ArrayList<DanAllocationForNbfcMakerUsingVWModel>();

	List<MLICGFeeDetails> borworDetailsList = new ArrayList<MLICGFeeDetails>();
	Map<String, String> borDetailsList;
	static double totalOfAmount=0;

		
	@RequestMapping(value = "danAllocation", method = RequestMethod.GET)
	public ModelAndView adminHome(
			@ModelAttribute("command") DANAllocationBean loginBean,
			BindingResult result, Model model, HttpSession session)
			throws ClassCastException {
		log.info("adminHome***************************************************************************************************************"
				+ session.getAttribute("userId"));
		danDetailList.clear();

		String userId = (String) session.getAttribute("userId");
		mliDetails = employeeService.getBNKId(userId);
		String mliId=mliDetails.getMem_bnk_id()+mliDetails.getMem_zne_id()+mliDetails.getMem_brn_id();
		List<DANAllocationBean>list=createDanAllocationIntoBean(danAllocationService.getDataForDanAllocation(mliId));
		
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
		modelAct.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
		modelAct.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		modelAct.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//		modelAct.put("actList",
//				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		modelAct.put("homePage", "nbfcMakerHome");
		return new ModelAndView("danAllocationPage", modelAct);
	}

	
	
	private List<DANAllocationBean> createDanAllocationIntoBean(
			List<DanAllocationForNbfcMakerUsingVWModel> dataForDanAllocation) {
		// TODO Auto-generated method stub
		List<DANAllocationBean>list=new ArrayList<DANAllocationBean>();
		DANAllocationBean db=null;
		totalOfAmount=0;
		if(!dataForDanAllocation.isEmpty()){
			for(DanAllocationForNbfcMakerUsingVWModel danAllocate:dataForDanAllocation){
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



	@RequestMapping(value = "disbursedborwerDetails", method = RequestMethod.GET)
	public ModelAndView disbursedborwerDetails(
			@ModelAttribute("command") DANAllocationBean danAllocatioBean,
			BindingResult result, Model model,String fileName,String disbursedStatus, HttpSession session) {
		log.info("adminHome***************************************************************************************************************"
				+ session.getAttribute("userId"));
		borrowerDetailList.clear();
		Map<String, Object> modelAct = new HashMap<String, Object>();
		session.setAttribute("fileName", fileName);
		
		/*createDisbuseCaseBean(danAllocationService.getDisbusCaseDetails(fileName),portfolioRate);
		modelAct.put("danDetailList", createDisbuseCaseBean(danAllocationService.getDisbusCaseDetails(fileName),portfolioRate));
		modelAct.put("fileName", fileName);
		modelAct.put("portfolioRate", portfolioRate);*/
		
		modelAct.put(
				"dataList1",
				danAllocationService.getDisburseNonDisburseData(fileName,disbursedStatus));
		modelAct.put(
				"disStatus",disbursedStatus);
		
		//Added by say 6 FEb-------------------------------------------------------------
		modelAct.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		modelAct.put("guaranteelist",
				userActivityService.getActivity("NBFCMAKER", "Registration"));
		modelAct.put("applicationList",
				userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		modelAct.put("RPaymentList",
				userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		modelAct.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));
		modelAct.put("actName", userActivityService.getActivityName("NBFCMAKER",
				"danAllocation"));// Added by Say 31 Jan19
		modelAct.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		modelAct.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));
		modelAct.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
		modelAct.put("homePage", "nbfcMakerHome");
		return new ModelAndView("disbursedborwerDetailsPage", modelAct);
	}

	/*@RequestMapping(value = "nonDisbursedborwerDetails", method = RequestMethod.GET)
	public ModelAndView nonDisbursedborwerDetails(
			@ModelAttribute("command") DANAllocationBean danAllocatioBean,
			BindingResult result, Model model, String fileName,String portfolioRate, HttpSession session) {
		log.info("adminHome***************************************************************************************************************"
				+ session.getAttribute("userId"));
		borrowerDetailList.clear();
		Map<String, Object> modelAct = new HashMap<String, Object>();
				modelAct.put("danDetailList", createDisbuseCaseBean(danAllocationService.getNonDisbusCaseDetails(fileName),portfolioRate));
		modelAct.put("fileName", fileName);
		modelAct.put("portfolioRate", portfolioRate);
		modelAct.put("actList",
				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		modelAct.put("homePage", "nbfcMakerHome");
		return new ModelAndView("nonDisbursedborwerDetailsPage", modelAct);
	}
*/
	@RequestMapping(value = "submitForConfirm", method = RequestMethod.GET)
	public ModelAndView submitForConfirm(
			@ModelAttribute("command") DANAllocationBean loginBean,
			BindingResult result, Model model, HttpSession session) {
		log.info("adminHome***************************************************************************************************************"
				+ session.getAttribute("userId"));
		borrowerDetailList.clear();
		Map<String, Object> modelAct = new HashMap<String, Object>();
		allocationList=new ArrayList<DanAllocationForNbfcMakerUsingVWModel>();
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
			 danDetailList=createDanAllocationIntoBean(allocationList);

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
		modelAct.put("actName", userActivityService.getActivityName("CGTMSECHECKER",
				"danAllocation"));// Added by Say 31 Jan19
		modelAct.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));
		modelAct.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));
		modelAct.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		modelAct.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
		modelAct.put("homePage", "nbfcMakerHome");
		modelAct.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
		return new ModelAndView("submitForConfirmPage", modelAct);
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
			DanAllocationForNbfcMakerUsingVWModel db=new DanAllocationForNbfcMakerUsingVWModel();
			db.setDanId(arr[0]);
			db.setPortfolioRate(arr[1]);
			db.setAmount(arr[2]);
			db.setPortfolioName(arr[3]);
			db.setFileId(arr[4]);
			db.setMliId(arr[5]);
			allocationList.add(db);
		}
	}



	

	private List<DANAllocationBean> prepareDANListOfBean(
			DANAllocationService danAllocationService, List<String> danIDList,
			List<String> portfolioNameList, List<String> fileIDList) {
		List<DANAllocationBean> beans = null;
		 String portFolioNumber=null;
		beans = new ArrayList<DANAllocationBean>();
		DANAllocationBean bean = null;
		for(String valueDel:portfolioNameList){
			portFolioNumber=valueDel;
			
		}
			
		if(danIDList.size()>=1){
		for (int i = 0, j = 0, k = 0; i < danIDList.size()
				&& k < fileIDList.size(); ++i, ++k) {
			try {
			bean = new DANAllocationBean();
			System.out.println(danIDList.get(i));
			System.out.println(portFolioNumber);
			System.out.println(fileIDList.get(k));
			// do something here

			bean.setPortfoliName(portFolioNumber);
			bean.setFileName(fileIDList.get(k));
			bean.setDAN_ID(danIDList.get(i));
			// bean.setPortfolioRate(dAllocation.getINTEREST_RATE());
			DANAllocationFee danAllocationfo = danAllocationService
					.getCGAmoutDetails(danIDList.get(j), "NCDI");
			if(danAllocationfo.getDCI_AMOUNT_RAISED()!=0){
				bean.setTotalFee(BigDecimal.valueOf(danAllocationfo.getDCI_AMOUNT_RAISED()).toBigDecimal().toPlainString());
					
			}else{
				bean.setTotalFee("0.0");
					
			}
			if(danAllocationfo.getDCI_STANDARD_RATE()!=0){
				bean.setPortfolioRate(Double.toString(danAllocationfo.getDCI_STANDARD_RATE()));
					
			}else{
				
				bean.setPortfolioRate("0");
					
			}
			
			beans.add(bean);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

			
		
		}
		}else{

			for (int i = 0, j = 0, k = 0; i < danIDList.size()
					&& j < portfolioNameList.size() && k < fileIDList.size(); ++i, ++j, ++k) {
				try {
				bean = new DANAllocationBean();
				System.out.println(danIDList.get(i));
				System.out.println(portfolioNameList.get(j));
				System.out.println(fileIDList.get(k));
				// do something here

				bean.setPortfoliName(portfolioNameList.get(j));
				bean.setFileName(fileIDList.get(k));
				bean.setDAN_ID(danIDList.get(i));
				// bean.setPortfolioRate(dAllocation.getINTEREST_RATE());
				DANAllocationFee danAllocationfo = danAllocationService
						.getCGAmoutDetails(danIDList.get(j), "NCDI");
				if(danAllocationfo.getDCI_AMOUNT_RAISED()!=0){
					bean.setTotalFee(BigDecimal.valueOf(danAllocationfo.getDCI_AMOUNT_RAISED()).toBigDecimal().toPlainString());
						
				}else{
					bean.setTotalFee("0.0");
						
				}
				if(danAllocationfo.getDCI_STANDARD_RATE()!=0){
					bean.setPortfolioRate(Double.toString(danAllocationfo.getDCI_STANDARD_RATE()));
						
				}else{
					
					bean.setPortfolioRate("0");
						
				}
				
				beans.add(bean);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

				
			
			}
				
			
		}
			return beans;
	}


	private List<DANAllocationBean> prepareDANAllocatedListOfBean(
			DANAllocationService danAllocationService, List<String> danIDList,
			List<String> portfolioNameList,List<String> fileIDList) {
		List<DANAllocationBean> beans = null;
         String portFolioNumber=null;
		beans = new ArrayList<DANAllocationBean>();
		DANAllocationBean bean = null;
	for(String valueDel:portfolioNameList){
		portFolioNumber=valueDel;
		
	}
		
		
		if(danIDList.size()>=1){
		for (int i = 0, j = 0,k=0; i < danIDList.size()
				&& k < fileIDList.size() ; ++i, ++k) {
			try {
			bean = new DANAllocationBean();
			System.out.println(danIDList.get(i));
			System.out.println(portFolioNumber);
			System.out.println(fileIDList.get(k));
			// do something here

			bean.setPortfoliName(portFolioNumber);
			bean.setFileName(fileIDList.get(k));
			//bean.setFileName(fileIDList.get(k));
			bean.setDAN_ID(danIDList.get(i));
			// bean.setPortfolioRate(dAllocation.getINTEREST_RATE());
			DANAllocationFee danAllocationfo = danAllocationService
					.getCGAmoutDetails(danIDList.get(j), "NCDI");
			if(danAllocationfo.getDCI_AMOUNT_RAISED()!=0){
				System.out.println(danAllocationfo.getDCI_AMOUNT_RAISED());
				bean.setTotalFee(BigDecimal.valueOf(danAllocationfo.getDCI_AMOUNT_RAISED()).toBigDecimal().toPlainString());
					
			}else{
				bean.setTotalFee("0.0");
					
			}
			
			totalOfAmount=totalOfAmount+danAllocationfo.getDCI_AMOUNT_RAISED();
			beans.add(bean);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

			
		
		}
		}else{

			for (int i = 0, j = 0,k=0; i < danIDList.size()
					&& j < portfolioNameList.size() && k < fileIDList.size() ; ++i, ++j,++k) {
				try {
				bean = new DANAllocationBean();
				System.out.println(danIDList.get(i));
				System.out.println(portfolioNameList.get(j));
				System.out.println(fileIDList.get(k));
				// do something here

				bean.setPortfoliName(portfolioNameList.get(j));
				bean.setFileName(fileIDList.get(k));
				//bean.setFileName(fileIDList.get(k));
				bean.setDAN_ID(danIDList.get(i));
				// bean.setPortfolioRate(dAllocation.getINTEREST_RATE());
				DANAllocationFee danAllocationfo = danAllocationService
						.getCGAmoutDetails(danIDList.get(j), "NCDI");
				if(danAllocationfo.getDCI_AMOUNT_RAISED()!=0){
					System.out.println(danAllocationfo.getDCI_AMOUNT_RAISED());
					bean.setTotalFee(BigDecimal.valueOf(danAllocationfo.getDCI_AMOUNT_RAISED()).toBigDecimal().toPlainString());
						
				}else{
					bean.setTotalFee("0.0");
						
				}
				
				totalOfAmount=totalOfAmount+danAllocationfo.getDCI_AMOUNT_RAISED();
				beans.add(bean);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

				
			
			}
			
			
		}
			return beans;
	}


	 @ExceptionHandler(CustomExceptionHandler.class)
		public ModelAndView handleCustomException(CustomExceptionHandler ex) {
			Map<String, Object> model1 = new HashMap<String, Object>();		

			 //model1.put("actList",						userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
			// model1.put("homePage","nbfcCheckerHome");
			ModelAndView model = new ModelAndView("customException",model1);
			model.addObject("customException", ex.getMessage());
			return model;

		}

		@ExceptionHandler(Exception.class)
		public ModelAndView handleAllException(Exception ex) {
			Map<String, Object> model1 = new HashMap<String, Object>();		

			// model1.put("actList",						userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
			// model1.put("homePage","nbfcCheckerHome");
			ModelAndView model = new ModelAndView("exception",model1);
			model.addObject("exception", ex.getCause());
			return model;

		}

		@ExceptionHandler(ArithmeticException.class)
		public ModelAndView handleArithException(ArithmeticException ex) {
			Map<String, Object> model1 = new HashMap<String, Object>();		

			 //model1.put("actList",						userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
			// model1.put("homePage","nbfcCheckerHome");
			ModelAndView model = new ModelAndView("exception",model1);
			model.addObject("exception", ex.getMessage());
			return model;

		}

		@ExceptionHandler(NullPointerException.class)
		public ModelAndView handleNullException(NullPointerException ex) {
			Map<String, Object> model1 = new HashMap<String, Object>();		

			// model1.put("actList",						userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
			// model1.put("homePage","nbfcCheckerHome");
			ModelAndView model = new ModelAndView("exception",model1);
			model.addObject("exception", "Data is null");
			return model;

		}

}
