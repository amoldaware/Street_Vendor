package com.nbfc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nbfc.bean.JsonResponse2;
import com.nbfc.bean.SanctionDetailBean;
import com.nbfc.helper.NBFCHelper;
import com.nbfc.helper.NBFCValidator;
import com.nbfc.model.SanctionDetailsExposureLimitModel;
import com.nbfc.service.LoginService;
import com.nbfc.service.PortfolioGridPaneService;
import com.nbfc.service.SanctionDetailService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.nbfc.model.MLIBankIdDetails;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIExposureIDDetails;
import com.nbfc.model.MLIExposureId;
import com.nbfc.model.MLIName;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.PortfolioMSTModel;
import com.nbfc.model.SanctionDetailsChild;
import com.nbfc.model.SanctionDetailsSave;
import com.nbfc.model.UserPerivilegeDetails;
import com.raistudies.domain.CustomExceptionHandler;
import com.vaannila.validator.SanctionDetailsValidator;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class SanctionDetailController {
	
	@Autowired
	UserService employeeService;
	@Autowired
	private SanctionDetailService sanctionDetailService;
	@Autowired
	LoginService lofinService;
	
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	PortfolioGridPaneService portfolioGridPaneService;
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;
	ModelAndView modelView;
	SanctionDetailsSave sanctionDetailsSaveObj=new SanctionDetailsSave();
	SanctionDetailsValidator sanctionDetailsValidator = new SanctionDetailsValidator();
    String portFiloName=null;
	private List<SanctionDetailBean> userList = new ArrayList<SanctionDetailBean>();
	NBFCValidator nbfcValidator = new NBFCValidator();
	static Logger log = Logger.getLogger(PortfolioGridController.class.getName());
	Map<String, String> mapLongNameObj1 = new HashMap<String, String>();
	Map<String, String> mapShowFinalcialYearObj = new HashMap<String, String>();
	MLIDetails mliDetails;
	MLIName mliName ;
	String exposureValidityDate=null;
	MLIExposureId mliExposureId;
	MLIExposureIDDetails mliExposureIDDetails;
	ArrayList arrayListObj1 = new ArrayList();
	ArrayList arrayListObj2 = new ArrayList();
	ArrayList arrayListObj3 = new ArrayList();
	ArrayList arrayListObj4 = new ArrayList();

	// This method will call for GET Request with name of url of jsp
	// sanctionDetailsRM
	@RequestMapping(value = "sanctionDetailsPageRM", method = RequestMethod.GET)
	public ModelAndView showSantionDetailsInputForm(
			@ModelAttribute("command") SanctionDetailBean sanctionDetailBean,
			BindingResult result,HttpSession session) {
		System.out.println("SanctionDetailsShowInputForm inside the SanctionDetailController called$$$$$$==="+sanctionDetailBean.getDateOfSanction());
		
		System.out.println("********************************************First");
		Map<String, Object> modelAct = new HashMap<String, Object>();	
		String userId=(String)session.getAttribute("userId");
		 if(userId!=null){
			userPri=lofinService.getUserPrivlageDtl(userId, "A");
			userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
			if(userPrvMst.getPrvCreatedModifiedBy().equals("CMAKER")){
				//added by say 6 feb-----------------------
				modelAct.put("adminlist",
						userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
				modelAct.put("guaranteelist",
						userActivityService.getActivity("CGTMSEMAKER", "Registration"));
				modelAct.put("applicationList",
						userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
				modelAct.put("RPaymentList",
						userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		//end---------------------------------------------------------------------------------------	
				
			
//				modelAct.put("actList",
//						userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
				modelAct.put("homePage","cgtmseMakerHome");
				modelView = new ModelAndView("sanctionDetails",modelAct); 
		 }else{
			 modelView = new ModelAndView("redirect:/SVLogin.html");
			// return new ModelAndView("redirect:/SVLogin.html"); 
		 }
		 }else{
			 modelView = new ModelAndView("redirect:/SVLogin.html");
		 }
		return modelView;
	}
	@RequestMapping(value = "/portfolioGridBack", method = RequestMethod.POST)
	public ModelAndView getPortfolioDetails(
			@ModelAttribute(value = "command") com.nbfc.bean.PortfolioGridPanBean portfolioGridPanBean,
			BindingResult result, String userId)
			 {
		log.info("Enter from getPortfolioDetails() method in PortfolioGridController class ");

		Map<String, Object> model=null;
		try {
			model = new HashMap<String, Object>();
			model.put("portfolioList",portfolioGridPaneService.getPortfolioData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("gettting exception in getPortfolioDetails() method in PortfolioGridController class "+e);

		}
	//	model.put("mlisList",prepareMLIListofBean(mliDetailsService.getMLIAllDetails()));
		/*model.put("actList",
				userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
		model.put("homePage", "cgtmseCheckerHome");*/
		//added by say 6 feb-----------------------
		model.put("adminlist",
				userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model.put("CBMFList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------	
		
		
//		modelAct.put("actList", userActivityService.getActivity(
//				"NBFCMAKER", "User_Activity"));

		model.put("homePage", "cgtmseMakerHome");
		return new ModelAndView("portFolioGrid",model);
	}
	// Get the LongName Inside the LongName dropdown box in page on load

	@ModelAttribute("lstLongName")
	public List<Object[]>retriveRecord() {
		System.out.println("HI===");
		List<Object[]>listLongNameObj = (List<Object[]>) sanctionDetailService.getLongName();
		System.out.println("mapLongNameObj==" + listLongNameObj);
		return listLongNameObj;
	}

	// Get Finalcial Year in Dropdown
	@ModelAttribute("finalcial_year")
	public Map<String, String> showFinalcial_year() {
		NBFCHelper nbfcHelper =new NBFCHelper();
		/*System.out.println("HI===");

		long millis = System.currentTimeMillis();
		System.out.println("millis===" + millis);
		java.sql.Date date3 = new java.sql.Date(millis);
		System.out.println("====date3===" + date3);

		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String s = formatter.format(date3);
		System.out.println("s===" + s);
		String sArr[] = s.split("-");
		System.out.println("HI==" + sArr[0]);
		String y = sArr[0];
		Integer current_Year = Integer.parseInt(y);

		System.out.println("Current Year==" + current_Year);

		String current_Year_String = String.valueOf(current_Year);

		Integer back_Year = current_Year - 1;

		System.out.println("Back Year==" + back_Year);

		String back_Year_String = String.valueOf(back_Year);

		String backYearCon = back_Year_String + "-" + current_Year_String;
		System.out.println("backYearCon==" + backYearCon);

		// System.out.println("FY-"+ back_Year+ "-"+current_Year);

		Integer next_Year = current_Year + 1;
		String next_Year_String = String.valueOf(next_Year);
		String next_YearCon = current_Year + "-" + next_Year_String;
		System.out.println("next_YearCon==" + next_YearCon);*/
		// System.out.println("FY-"+ current_Year+ "-"+next_Year);

		// Integer last_year=next_Year+1;
		// String last_Year_String=String.valueOf(last_year);
		// String last_YearCon=next_Year+ "-"+last_Year_String;
		// System.out.println("last_YearCon=="+last_YearCon);
		// System.out.println("FY-"+ next_Year+ "-"+last_year);

		//mapShowFinalcialYearObj.put(backYearCon, backYearCon);
		mapShowFinalcialYearObj.put(nbfcHelper.getCurrentYear(), nbfcHelper.getCurrentYear());
		// mapShowFinalcialYearObj.put(last_YearCon, last_YearCon);

		return mapShowFinalcialYearObj;

	}

	// Get short name on change of long name
	@RequestMapping(value = "/AddUser3", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse2 addUser(
			@ModelAttribute(value = "sanctionDetails") SanctionDetailBean sanctionDetailBean,
			BindingResult result, HttpSession session) {

		Double expLimit = (double) 0;
		String toDate = null;
		Double expId = (double) 0;
		System.out.println("###########inside addUser method of SanctionDetailsController class##########################");
		String longName = sanctionDetailBean.getLong_name();
		
		//String userId=(String)session.getAttribute("userId");
		MLIBankIdDetails MLIBankIdDetails=employeeService.getMLIId(longName);
		NBFCHelper nbfcHelper = new NBFCHelper();
		String fyBasedOnStartAndEndDate=nbfcHelper.getCurrentYear();
		mliExposureId = employeeService.getExposureId(MLIBankIdDetails.getBank_id(),fyBasedOnStartAndEndDate);
		Integer exp_value = null;
		JsonResponse2 res = new JsonResponse2();
		SanctionDetailBean s1 = new SanctionDetailBean();
		SanctionDetailsExposureLimitModel sanctionDetailsExposureLimitModelObj = new SanctionDetailsExposureLimitModel();
		sanctionDetailsExposureLimitModelObj.getMemBnkId();

		
		arrayListObj1 = sanctionDetailService
				.getShortNameOnChangeOfLongName(sanctionDetailBean);
		Iterator itr = arrayListObj1.iterator();
		

		String sN = (String) itr.next();
		session.setAttribute("SnameKey", sN);
		String memBankId = (String) itr.next();
	

		sanctionDetailsExposureLimitModelObj.setMemBnkId(memBankId);
		
		List<SanctionDetailsExposureLimitModel> listObj = sanctionDetailService
				.getExposureLimit(sanctionDetailsExposureLimitModelObj);

		Iterator itr2 = listObj.iterator();

	

		while (itr2.hasNext()) {
			SanctionDetailsExposureLimitModel sanctionDetailsExposureLimitModelObj1 = (SanctionDetailsExposureLimitModel) itr2
					.next();
			sanctionDetailsExposureLimitModelObj1.getExposureLimit();
			sanctionDetailsExposureLimitModelObj1.getToDate();
			expLimit = sanctionDetailsExposureLimitModelObj1.getExposureLimit();
			
			toDate = sanctionDetailsExposureLimitModelObj1.getToDate();
			expId = sanctionDetailsExposureLimitModelObj1.getExposureId();
			sanctionDetailsSaveObj.setExposure_limit(sanctionDetailsExposureLimitModelObj1.getExposureLimit());
			sanctionDetailsSaveObj.setExposure_id(sanctionDetailsExposureLimitModelObj1.getExposureId());
			
		}

		String expLimit1 = String.valueOf(expLimit);
		String expId1 = String.valueOf(expId);

		session.setAttribute("eLKey", expLimit1);
		session.setAttribute("expIdKey", expId1);

		
		//mliExposureId = employeeService.getExposureId(mliDetails.getMem_bnk_id());
		//mliExposureIDDetails = employeeService.getExposureIdDetails(mliDetails.getMem_bnk_id());

		long UEXPLimit = sanctionDetailService
				.getUtilisiedExposureLimit(mliExposureId.getEXPOSURE_ID());
		s1.setShort_name(sN);
		// s1.setExposure_limit(expLimit);
		s1.setExposure_limit(expLimit);
		s1.setMax_portfolio_size((double) UEXPLimit);
		String date = toDate.substring(0, 10);
		String dateSplit[] = date.split("-");
		
		// String
		// formatedSanctionDate=dateSplit[1]+"/"+dateSplit[2]+"/"+dateSplit[0];
		String formatedSanctionDate = dateSplit[2] + "/" + dateSplit[1] + "/"
				+ dateSplit[0];
		exposureValidityDate=formatedSanctionDate;
		s1.setDateOfSanction(formatedSanctionDate);
		userList.clear();
		userList.add(s1);

		res.setStatus("SUCCESS");

		res.setResult(userList);
		return res;
	}

	// Save Functionality

	@RequestMapping(value = "sanctionDetailsRM", method = RequestMethod.POST   )
	public ModelAndView save(@ModelAttribute("command") SanctionDetailsSave sanctionDetailsSave,BindingResult result, HttpServletRequest request,HttpSession session, Model model) throws ParseException {
		Date date = new Date();
		SanctionDetailBean sanctionDetailBeanObj1=new SanctionDetailBean();
		Integer showPortfolioNo = 0;
		boolean error = true;
		String sN = (String) session.getAttribute("SnameKey");
		//sanctionDetailsValidator.validate(sanctionDetailsSave, result);
		if (result.hasErrors()) {
			modelView= new ModelAndView("sanctionDetails");
		} else {
			Integer NoOfCountOfLongName = sanctionDetailService
					.getGeneratedPortfolioCount();
			Integer generatePortfolioNo = 10000000;

			// Integer portfolioNum=sanctionDetailService.getPortFolioNumber();
			if (NoOfCountOfLongName == 0) {
				NoOfCountOfLongName = 1;
				generatePortfolioNo = 0 + generatePortfolioNo;
			} else {
				NoOfCountOfLongName = NoOfCountOfLongName + 1;
				generatePortfolioNo = NoOfCountOfLongName + generatePortfolioNo;
			}

			

			// sanctionDetailsValidator.validate(sanctionDetailsSave,result);
			portFiloName=sanctionDetailsSave.getShort_name()+"/"+sanctionDetailsSave.getFinancial_year()+"/"+nbfcValidator.getQuarterNUmber(date)+"/"+nbfcValidator.lastDigit(generatePortfolioNo);
			
			int portfolioNum1 = generatePortfolioNo;
			sanctionDetailsSave.setPortfolio_no(generatePortfolioNo);
		
			Double ep=sanctionDetailsSaveObj.getExposure_limit();
			Double expID=sanctionDetailsSaveObj.getExposure_id();
			sanctionDetailsSave.setExposure_limit(ep);
			sanctionDetailsSave.setExposure_id(expID);

			SanctionDetailsChild sanctionDetailsChildObj = new SanctionDetailsChild();
			int portfolioLife = sanctionDetailsSave.getPortfolio_life();
			int portfolioYearCal = portfolioLife / 12;
			Float p = sanctionDetailsSave.getPay_out_cap();
			sanctionDetailsChildObj.setPayOutCap(p);
			sanctionDetailsSave.setPay_out_cap(p);

			// Storing Data in request object.
			request.setAttribute("sName", sanctionDetailsSave.getShort_name());
			
			String lN = sanctionDetailsSave.getLong_name();
			String portfolioQuater = sN;
			//sanctionDetailsSave.setLong_name(portfolioQuater);

			// Get sumOfMaxPorfolio

			Double eLimit = sanctionDetailsSave.getExposure_limit();
		
			Double sumOfMaxPorfolio = (double) sanctionDetailService.getSumOfMaxPortfolio(expID);
			if(sumOfMaxPorfolio==null){
				sumOfMaxPorfolio=(double) 0;	
			}
			
			Double maxPortfolio_size = sanctionDetailsSave.getMax_portfolio_size();
			if(maxPortfolio_size==null){
				maxPortfolio_size=(double) 0;
			}
			double total = sumOfMaxPorfolio + maxPortfolio_size;

			if (total > eLimit) {
						error = false;
			} else {
				sanctionDetailsSave.setLong_name(lN);
				//convert date to string
				sanctionDetailsSave.setDateOfSanction(exposureValidityDate);
				sanctionDetailsSave.setPortFolioName(portFiloName);
				sanctionDetailService.addSanctionDetails(pripairedModel(sanctionDetailsSave));// Parent
																				
				for (int i = 1; i < 5; i++) {
					
					String quater1 = sanctionDetailsSave.getFinancial_year()
							+ "Q" + i;
					
					sanctionDetailsChildObj
							.setPortfolio_No(generatePortfolioNo);
					String quater2 = portfolioQuater + quater1;
					sanctionDetailsChildObj.setPortfolioQuarter(quater2);
					sanctionDetailsChildObj.setPayOutCap(p);
					sanctionDetailsChildObj.setFileUploadType("GF");
					//sanctionDetailsChildObj.setPortFilioName(portFiloName);

					// Generated subportfoliioSerialNumber
					sanctionDetailsChildObj.setSubPortfolioSerialNo(i);

					sanctionDetailService.addSanctionDetails1(sanctionDetailsChildObj);
				}
				for (int i = 1; i < portfolioYearCal; i++) {				
					
					String quater = sanctionDetailsSave.getFinancial_year()+ "_Y_" + i;
					String finalciallYearwithMliName=portfolioQuater+quater;
					sanctionDetailsChildObj.setPortfolio_No(generatePortfolioNo);
					sanctionDetailsChildObj.setPortfolioQuarter(finalciallYearwithMliName);
					sanctionDetailsChildObj.setPayOutCap(p);
					sanctionDetailsChildObj.setFileUploadType("ASF");
					//sanctionDetailsChildObj.setPortFilioName(portFiloName);
					
					
					int j=i;
					int fixvalue = j+4;
					sanctionDetailsChildObj.setSubPortfolioSerialNo(fixvalue);
					sanctionDetailService.addSanctionDetails1(sanctionDetailsChildObj);
				}
				showPortfolioNo = generatePortfolioNo;
				model.addAttribute("SuccessMsg",
						"Portfolio created successfully with PortfolioId:");
				model.addAttribute("showPortfolioNoKey", showPortfolioNo);
			}
		}
		model.addAttribute("shortName", sanctionDetailsSave.getShort_name());

		Map<String, Object> modelAct = new HashMap<String, Object>();	
		String userId=(String)session.getAttribute("userId");
		 if(userId!=null){
			userPri=lofinService.getUserPrivlageDtl(userId, "A");
			userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
			if(userPrvMst.getPrvCreatedModifiedBy().equals("CMAKER")){
				//added by say 6 feb-----------------------
				modelAct.put("adminlist",
						userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
				modelAct.put("guaranteelist",
						userActivityService.getActivity("CGTMSEMAKER", "Registration"));
				modelAct.put("applicationList",
						userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
				modelAct.put("RPaymentList",
						userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
				modelAct.put("CBMFList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Bank_Mandate"));
		//end---------------------------------------------------------------------------------------	
				
				
//				modelAct.put("actList", userActivityService.getActivity(
//						"NBFCMAKER", "User_Activity"));
				modelAct.put("homePage","cgtmseMakerHome");
				modelView = new ModelAndView("sanctionDetails",modelAct); 
		 }else{
			 modelView = new ModelAndView("redirect:/SVLogin.html");
			// return new ModelAndView("redirect:/SVLogin.html"); 
		 }
		 }else{
			 modelView = new ModelAndView("redirect:/SVLogin.html");
		 }

		return modelView;
	}

	// To handle date format
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	public  PortfolioMSTModel  pripairedModel(SanctionDetailsSave sanctionDetailsSave){
		
		PortfolioMSTModel portfolioMSTModel = new PortfolioMSTModel();
		portfolioMSTModel.setCreadted_by(sanctionDetailsSave.getCreadted_by());
		try {
			portfolioMSTModel.setDateOfSanction(new SimpleDateFormat("dd/MM/yyyy").parse(sanctionDetailsSave.getDateOfSanction()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		portfolioMSTModel.setExposure_id(sanctionDetailsSave.getExposure_id());
		//portfolioMSTModel.setExposure_limit(sanctionDetailsSave.getExposure_limit());
		portfolioMSTModel.setFinancial_year(sanctionDetailsSave.getFinancial_year());
		portfolioMSTModel.setGurantee_fee(sanctionDetailsSave.getGurantee_fee());
		portfolioMSTModel.setInsertedon(sanctionDetailsSave.getInsertedon());
		portfolioMSTModel.setItems(sanctionDetailsSave.getItems());
		portfolioMSTModel.setMax_portfolio_size(sanctionDetailsSave.getMax_portfolio_size());
		portfolioMSTModel.setPay_out_cap(sanctionDetailsSave.getPay_out_cap());
		portfolioMSTModel.setPortfolio_amount(sanctionDetailsSave.getPortfolio_amount());
		portfolioMSTModel.setPortfolio_life(sanctionDetailsSave.getPortfolio_life());
		portfolioMSTModel.setPortfolio_no(sanctionDetailsSave.getPortfolio_no());
		try {
			portfolioMSTModel.setPortfolio_start_date(new SimpleDateFormat("dd/MM/yyyy").parse(sanctionDetailsSave.getPortfolio_start_date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		portfolioMSTModel.setPortfolio_status(sanctionDetailsSave.getPortfolio_status());
		portfolioMSTModel.setPortFolioName(sanctionDetailsSave.getPortFolioName());
		portfolioMSTModel.setTotal_upload_count(sanctionDetailsSave.getTotal_upload_count());
		
		
		return portfolioMSTModel;
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
