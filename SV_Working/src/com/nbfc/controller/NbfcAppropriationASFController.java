package com.nbfc.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//import com.cgtsi.receiptspayments.ShortExceedsLimitException;
import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.AppropriationBeanForm;
import com.nbfc.bean.DemandAdvice;
import com.nbfc.bean.NbfcAppropriationBean;
import com.nbfc.bean.PaymentDetails;
import com.nbfc.service.UserActivityService;
import com.nbfc.validation.EmployeeValidator;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class NbfcAppropriationASFController {
	@Autowired
	com.nbfc.service.NbfcAppropriationService nbfcAppropriationService;
	static Logger log = Logger.getLogger(NbfcAppropriationASFController.class
			.getName());
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	EmployeeValidator validator;
	
	@RequestMapping(value = "/getAppropriationDataASF", method = RequestMethod.GET)
	public ModelAndView getDataDateWiseASF(@ModelAttribute("command")NbfcAppropriationBean bean,BindingResult result,Model model1,HttpServletRequest request,HttpSession session) throws ParseException {
		log.info("Enter in getDataDateWise() method in NbfcAppropriationController class");
		Map<String, Object> modelAct = new HashMap<String, Object>();
		//added by say 6 feb-----------------------
		modelAct.put("adminlist",
				userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		modelAct.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		modelAct.put("applicationList",
				userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		modelAct.put("RPaymentList",
				userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		modelAct.put("actName", userActivityService.getActivityName(
				"CGTMSEMAKER", "getAppropriationData"));// Added by say 25june19
		modelAct.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
//end---------------------------------------------------------------------------------------	
		modelAct.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		modelAct.put("homePage","cgtmseMakerHome");
//		modelAct.put("actList", userActivityService.getActivity(
//				"CGTMSEMAKER", "User_Activity"));
		return new ModelAndView("dateAppropriationASF",modelAct);

	}
	@RequestMapping(value = "/getAppropriationDataBetweenDatesASF", method = RequestMethod.GET)
	public ModelAndView getAppropriationDataBetweenDatesASF(@ModelAttribute("command")NbfcAppropriationBean bean,BindingResult result) {
		log.info("Enter in getDataDateWise() method in NbfcAppropriationController class");
		//added by say 6 feb-----------------------
		Map<String, Object> modelAct = new HashMap<String, Object>();
		modelAct.put("adminlist",
				userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		modelAct.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		modelAct.put("applicationList",
				userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		modelAct.put("RPaymentList",
				userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		modelAct.put("actName", userActivityService.getActivityName(
				"CGTMSEMAKER", "getAppropriationDataBetweenDates"));// Added by say 25june19
		modelAct.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
//end---------------------------------------------------------------------------------------	
		modelAct.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		modelAct.put("homePage","cgtmseMakerHome");
//		modelAct.put("actList", userActivityService.getActivity(
//				"CGTMSEMAKER", "User_Activity"));
		return new ModelAndView("dateAppropriationBetweenDatesASF",modelAct);

	}
	@RequestMapping(value = "/dateWiseGfBatchApplicaitonASF", method = RequestMethod.POST)
	public ModelAndView getAppropriationDataASF(@ModelAttribute("command")@Valid NbfcAppropriationBean bean,BindingResult result,Model model1,HttpServletRequest request,HttpSession session) throws ParseException {
		log.info("Enter in getAppropriationData() method in NbfcAppropriationController class");
		Map<String, Object> model = new HashMap<String, Object>();
		
		//String sDate1="31/12/1998";
			//	Map<String, String> borDetailsList;
		//String userId = (String) session.getAttribute("userId");
		// mliDetails = employeeService.getBNKId(userId);
		//String mliId = mliDetails.getMem_bnk_id() + mliDetails.getMem_zne_id()
		
		//Date date2=new Date();
		//		+ mliDetails.getMem_brn_id();
		//AppropriationBeanForm beanForm=new AppropriationBeanForm();
		List<NbfcAppropriationBean> list;
		validator.checkDate(bean,result);
		if(result.hasErrors()){
			model.put("adminlist",
					userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
			model.put("guaranteelist",
					userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			model.put("applicationList",
					userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			model.put("RPaymentList",
					userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
			model.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));	
			model.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSEMAKER", "Guarantee_Maintenance"));
			model.put("homePage","cgtmseMakerHome");
			return new ModelAndView("dateAppropriation",model);
		}else{
			String date=request.getParameter("inputName");
			Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date);
			session.setAttribute("date", date1);
		 list = nbfcAppropriationService.getDataForAppropriationASF(date1);
		}
		//if (mliId != null) {
			

		//} else {
		//	throw new CustomExceptionHandler("MLI name is null");
		//}
			//beanForm.setFormData(list);
		if(list !=null ){
		if (list.size() > 0 || !list.isEmpty()) {
			model.put("dataList", list);

		} else {
			log.info("Throwing exception due to data not found in NbfcAppropriationController class");
			model.put("dataList", "");

			// throw new CustomExceptionHandler("Data not Available");
			
			///");
		}
		}else{
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
		model.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));	

		model.put("actName", userActivityService.getActivityName(
				"CGTMSEMAKER", "getAppropriationData"));// Added by say 25june19
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		model.put("homePage","cgtmseMakerHome");
		log.info("Exit in getDanGenerateData() method in NbfcAppropriationController class");

		return new ModelAndView("dateWiseGfBatchApplicaitonASF", model);

	}
	
	@RequestMapping(value = "/betweenTwoDateAppropriationASF", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getDataBetweenTwoDateAppropriationASF(@ModelAttribute("command") NbfcAppropriationBean bean,BindingResult result,Model model1,HttpServletRequest request,HttpSession session) throws ParseException {
		log.info("Enter in getAppropriationData() method in NbfcAppropriationController class");
		Map<String, Object> model = new HashMap<String, Object>();
	

		//session.setAttribute("date", date1);	//	Map<String, String> borDetailsList;
		//String userId = (String) session.getAttribute("userId");
		// mliDetails = employeeService.getBNKId(userId);
		//String mliId = mliDetails.getMem_bnk_id() + mliDetails.getMem_zne_id()
		
		//Date date2=new Date();
		//		+ mliDetails.getMem_brn_id();
		//AppropriationBeanForm beanForm=new AppropriationBeanForm();
		List<NbfcAppropriationBean> list;
		validator.validateTwoDate(bean,result);
		if(result.hasErrors()){
			model.put("adminlist",
					userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
			model.put("guaranteelist",
					userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			model.put("applicationList",
					userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			model.put("RPaymentList",
					userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
			model.put("actName", userActivityService.getActivityName(
					"CGTMSEMAKER", "getAppropriationDataBetweenDates"));// Added by say 25june19
			model.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSEMAKER", "Guarantee_Maintenance"));
	//end---------------------------------------------------------------------------------------	
			model.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			model.put("homePage","cgtmseMakerHome");
			return new ModelAndView("dateAppropriationBetweenDates",model);
		}else{
			String fromDateHidden=request.getParameter("fromDateHidden");
			String toDateHidden=request.getParameter("toDateHidden");

			//String sDate1="31/12/1998";
			Date fromDateHidden1=new SimpleDateFormat("dd/MM/yyyy").parse(fromDateHidden);
			Date toDateHidden1=new SimpleDateFormat("dd/MM/yyyy").parse(toDateHidden);
			list = nbfcAppropriationService.getDataBetweenTwoDateForAppropriationASF(fromDateHidden1,toDateHidden1);
		}
		//if (mliId != null) {
			

		//} else {
		//	throw new CustomExceptionHandler("MLI name is null");
		//}
			//beanForm.setFormData(list);
		if(list !=null )
		{
		if (list.size() > 0 || !list.isEmpty()) {
			model.put("dataList", list);

		} else {
			log.info("Throwing exception due to data not found in NbfcAppropriationController class");
			model.put("dataList", "");

			// throw new CustomExceptionHandler("Data not Available");
			
			///");
		}
		}else{
			
			model.put("dataList", "");
		}
		//added by say 6 feb-----------------------
		model.put("adminlist",
				userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName(
				"CGTMSEMAKER", "getAppropriationDataBetweenDates"));// Added by say 25june19
//end---------------------------------------------------------------------------------------	
		model.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		
//		modelAct.put("actList", userActivityService.getActivity(
//				"CGTMSEMAKER", "User_Activity"));
		//model.put("homePage", "");
		model.put("homePage","cgtmseMakerHome");
		log.info("Exit in getDanGenerateData() method in NbfcAppropriationController class");

		return new ModelAndView("nbfcAppropriationDataBetweenDatesASF", model);

	}
	
	
	@RequestMapping(value = "/saveAppropriationDataASF", method = RequestMethod.POST)
	public ModelAndView saveAppropriationDataASF(@ModelAttribute("command")NbfcAppropriationBean contactForm,BindingResult result,Model model1,HttpServletRequest request,HttpSession session) {
		log.info("Enter in getAppropriationData() method in NbfcAppropriationController class");
		Map<String, Object> model = new HashMap<String, Object>();
		List<String> checkedData= contactForm.getChcktbl();
		String userId = (String) session.getAttribute("userId");
		Date dateOfReconsilation=(Date)session.getAttribute("date");
		String approvedCount=request.getParameter("approveCount");
		
		int flag=nbfcAppropriationService.updateAppropriateStatus(checkedData,dateOfReconsilation,userId);
		//if(flag>0){
			model.put("message", "Succefully Approved records "+approvedCount);
		//}
		
			//added by say 6 feb-----------------------
			model.put("adminlist",
					userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
			model.put("guaranteelist",
					userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			model.put("applicationList",
					userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			model.put("RPaymentList",
					userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
			model.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			
//			model.put("actName", userActivityService.getActivityName(
//					"CGTMSEMAKER", "getAppropriationData"));// Added by say 25june19
	//end---------------------------------------------------------------------------------------	
			
			model.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSEMAKER", "Guarantee_Maintenance"));	
//			modelAct.put("actList", userActivityService.getActivity(
//					"CGTMSEMAKER", "User_Activity"));
			//model.put("homePage", "");
			model.put("homePage","cgtmseMakerHome");
		log.info("Exit in getDanGenerateData() method in NbfcAppropriationController class");

		return new ModelAndView("successAppropriation", model);

	}
	
	@RequestMapping(value = "/getAppropriatePaymentASF", method = RequestMethod.GET)
	public ModelAndView getAppropriatePaymentASF(@ModelAttribute("command")DemandAdvice demandAdvice,String paymentId) {
		log.info("Enter in getAppropriatePayment() method in NbfcAppropriationController class");
		Map<String, Object> model = new HashMap<String, Object>();
		
	
		//Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date);
		//session.setAttribute("date", date1);	//	Map<String, String> borDetailsList;
		
	
		List list = nbfcAppropriationService.getAppropriatePaymentDetailsASF(paymentId);
	
		//	model.put("dataList", list);

	
		List<DemandAdvice> danDetails=(List<DemandAdvice>) list.get(0);
		
		model.put("danDetails", danDetails);
	PaymentDetails paymentDetails=(PaymentDetails) list.get(1);
		
		
		model.put("paymentId", paymentId);
		if(paymentDetails!=null){
			DemandAdvice da=new DemandAdvice();
			da.setModeOfDelivery(paymentDetails.getModeOfDelivery());
			da.setModeOfPayment(paymentDetails.getModeOfPayment());
			da.setInstrumentNo(paymentDetails.getInstrumentNo());
			da.setInstrumentDate(paymentDetails.getInstrumentDate());
			da.setInstrumentAmount(paymentDetails.getInstrumentAmount());
			da.setPayableAt(paymentDetails.getPayableAt());
			da.setDrawnAtBank(paymentDetails.getDrawnAtBank());
			da.setDrawnAtBranch(paymentDetails.getDrawnAtBranch());
			da.setCollectingBank(paymentDetails.getCollectingBank());
			da.setCollectingBankBranch(paymentDetails.getCollectingBankBranch());
			da.setAllocatedAmount(paymentDetails.getAllocatedAmount());
			da.setPaymentDate(paymentDetails.getInstrumentDate());
			da.setCgtsiAccNumber(paymentDetails.getCgtsiAccNumber());
		
			model.put("paymentDetails", da);
		}

		/*if (list.size() > 0 || !list.isEmpty()) {
			model.put("dataList", list);

		} else {
			log.info("Throwing exception due to data not found in NbfcAppropriationController class");
			model.put("dataList", "");

			// throw new CustomExceptionHandler("Data not Available");
			
			///");
		}
*/
		//added by say 6 feb-----------------------
		model.put("adminlist",
				userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
//		model.put("actName", userActivityService.getActivityName(
//				"CGTMSEMAKER", "getAppropriationData"));// Added by say 25june19
//end---------------------------------------------------------------------------------------	
		model.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		
//		modelAct.put("actList", userActivityService.getActivity(
//				"CGTMSEMAKER", "User_Activity"));
		//model.put("homePage", "");
		model.put("homePage","cgtmseMakerHome");
		
		return new ModelAndView("appropriatePaymentASF", model);

	}
	
	@RequestMapping(value = "/updateAppropriateDataASF", method = RequestMethod.POST)
	public ModelAndView saveAppropriatePaymentASF(@ModelAttribute("command")DemandAdvice demandAdvice,String rpno,HttpSession Session) {
		log.info("Enter in getAppropriatePayment() method in NbfcAppropriationController class");
	//	HttpSession Session=null;
	    Date date1=demandAdvice.getRealisationDate();
		String user_id=(String) Session.getAttribute("userId");
		
		Map<String, Object> model = new HashMap<String, Object>();
		List<String> checkedData= demandAdvice.getChcktbl();
	
		List list = nbfcAppropriationService.getAppropriatePaymentDetailsASF(rpno);
		
		List<DemandAdvice> danDetails=(List<DemandAdvice>) list.get(0);
		double appropriatedAmount=0.0d;
		model.put("danDetails", danDetails);
		PaymentDetails paymentDetails=(PaymentDetails) list.get(1);
		model.put("paymentId", rpno);
		if(paymentDetails!=null){
			DemandAdvice da=new DemandAdvice();
			da.setModeOfDelivery(paymentDetails.getModeOfDelivery());
			da.setModeOfPayment(paymentDetails.getModeOfPayment());
			da.setInstrumentNo(paymentDetails.getInstrumentNo());
			da.setInstrumentDate(paymentDetails.getInstrumentDate());
			da.setInstrumentAmount(paymentDetails.getInstrumentAmount());
			da.setPayableAt(paymentDetails.getPayableAt());
			da.setDrawnAtBank(paymentDetails.getDrawnAtBank());
			da.setDrawnAtBranch(paymentDetails.getDrawnAtBranch());
			da.setCollectingBank(paymentDetails.getCollectingBank());
			da.setCollectingBankBranch(paymentDetails.getCollectingBankBranch());
			da.setAllocatedAmount(paymentDetails.getAllocatedAmount());
			da.setPaymentDate(paymentDetails.getInstrumentDate());
			da.setCgtsiAccNumber(paymentDetails.getCgtsiAccNumber());
			model.put("paymentDetails", da);
			appropriatedAmount=paymentDetails.getAllocatedAmount();
		}

		
		
		double receivedAmount=demandAdvice.getReceivedAmount();

		if (receivedAmount < appropriatedAmount) {
			double shortLimit = appropriatedAmount - receivedAmount;
			throw new CustomExceptionHandler(
					(new StringBuilder())
							.append("Received Amount is less than Allocated Amount by Rs.")
							.append(shortLimit).toString());
		}
		if (receivedAmount > appropriatedAmount) {
			double excessLimit = receivedAmount - appropriatedAmount;
			throw new CustomExceptionHandler(
					(new StringBuilder())
							.append("Received Amount is greater than Allocated Amount by Rs.")
							.append(excessLimit).toString());
		} else {
		
			int  status=nbfcAppropriationService.updateApprocationDate(rpno, user_id, date1);
			//double shortOrExcess = nbfcAppropriationService.getAppropriatePaymentDetails.appropriatePayment(
			//		demandAdvice, realisationDetail, request.getSession(false)
				//			.getServletContext().getRealPath(""));
			/*request.setAttribute(
					"message",
					(new StringBuilder())
							.append("Payment Amount Appropriated Successfully.<BR><BR>Total Received Amount : ")
							.append(receivedAmount)
							.append("<BR>Total Appropriated Amount : ")
							.append(appropriatedAmount)
							.append("<BR>Short / Excess : ")
							.append(shortOrExcess).toString());*/
			
			model.put("message","Payment Amount Appropriated Successfully");
			model.put("receivedAmount",receivedAmount);
			model.put("appropriatedAmount",appropriatedAmount);
		//	model.put("shortOrExcess",shortOrExcess);
					
		}
		//added by say 6 feb-----------------------
		model.put("adminlist",
				userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
//		model.put("actName", userActivityService.getActivityName(
//				"CGTMSEMAKER", "getAppropriationData"));// Added by say 25june19
//end---------------------------------------------------------------------------------------	
		model.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));	
		
//		modelAct.put("actList", userActivityService.getActivity(
//				"CGTMSEMAKER", "User_Activity"));
		//model.put("homePage", "");
		model.put("homePage","cgtmseMakerHome");
		log.info("Exit in getAppropriatePayment() method in NbfcAppropriationController class");

		return new ModelAndView("appropriatedAmountSuccesASF", model);

	}
		
	@ExceptionHandler(CustomExceptionHandler.class)
	public ModelAndView handleCustomException(CustomExceptionHandler ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		
		//added by say 6 feb-----------------------
		model1.put("adminlist",
				userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model1.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model1.put("applicationList",
				userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model1.put("RPaymentList",
				userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
//end---------------------------------------------------------------------------------------	
		model1.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		model1.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		
//		modelAct.put("actList", userActivityService.getActivity(
//				"CGTMSEMAKER", "User_Activity"));
		model1.put("homePage","cgtmseMakerHome");
		ModelAndView model = new ModelAndView("customException",model1);
		model.addObject("customException", ex.getMessage());
		return model;

	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		

		//added by say 6 feb-----------------------
		model1.put("adminlist",
				userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model1.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model1.put("applicationList",
				userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model1.put("RPaymentList",
				userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
//end---------------------------------------------------------------------------------------	
		model1.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		
		model1.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
//		modelAct.put("actList", userActivityService.getActivity(
//				"CGTMSEMAKER", "User_Activity"));
		 model1.put("homePage","cgtmseMakerHome");
		ModelAndView model = new ModelAndView("exception",model1);
		model.addObject("exception", ex.getMessage());
		return model;

	}

	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithException(ArithmeticException ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		

		//added by say 6 feb-----------------------
		model1.put("adminlist",
				userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model1.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model1.put("applicationList",
				userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model1.put("RPaymentList",
				userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
//end---------------------------------------------------------------------------------------	
		model1.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		model1.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		
//		modelAct.put("actList", userActivityService.getActivity(
//				"CGTMSEMAKER", "User_Activity"));
		 model1.put("homePage","cgtmseMakerHome");
		ModelAndView model = new ModelAndView("exception",model1);
		model.addObject("exception", ex.getCause());
		return model;

	}

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullException(NullPointerException ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		

		//added by say 6 feb-----------------------
		model1.put("adminlist",
				userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model1.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model1.put("applicationList",
				userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model1.put("RPaymentList",
				userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
//end---------------------------------------------------------------------------------------	
		model1.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		model1.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
//		modelAct.put("actList", userActivityService.getActivity(
//				"CGTMSEMAKER", "User_Activity"));
		 model1.put("homePage","cgtmseMakerHome");
		ModelAndView model = new ModelAndView("exception",model1);
		model.addObject("exception", "Data is null");
		return model;

	}

}
