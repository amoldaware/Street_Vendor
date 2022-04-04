package com.nbfc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.CGPANDetailsReportBean;
import com.nbfc.bean.DanGenerateRpNumberForCheckerApprovalBean;
import com.nbfc.bean.LoginBean;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.OtpDetailsModel;
import com.nbfc.model.ProformaInvoiceDetails;
import com.nbfc.model.ProformaInvoiceDetailsASF;
import com.nbfc.model.TaxInvoiceDetails;
import com.nbfc.model.TaxInvoiceDetailsASF;
import com.nbfc.service.NBFCInvoiceService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class JasperReportASFController {
	@Autowired
	NBFCInvoiceService nbfcInvoiceService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	UserService employeeService;
	TaxInvoiceDetails taxDetailsObj1 = new TaxInvoiceDetails();
	TaxInvoiceDetailsASF taxDetailsObj2 = new TaxInvoiceDetailsASF();
	ProformaInvoiceDetailsASF proformaDetailsObj1 = new ProformaInvoiceDetailsASF();
	MLIDetails mliDetails;

	@RequestMapping(value = "/taxInvoiceReportASF", method = RequestMethod.GET)
	public ModelAndView taxInvoiceReportASF(
			@ModelAttribute("command") CGPANDetailsReportBean bean,
			HttpServletResponse response, HttpServletRequest request,
			HttpSession session) throws JRException {
		System.out.println("hiiiiiiiiiiiii");
		String Role = (String) session.getAttribute("uRole");
		String userId = (String) session.getAttribute("userId");
		Map<String, Object> model1 = new HashMap<String, Object>();

		mliDetails = employeeService.getBNKId(userId);
		String mliId = mliDetails.getMem_bnk_id() + mliDetails.getMem_zne_id()
				+ mliDetails.getMem_brn_id();

		List<CGPANDetailsReportBean> list;
		if (mliId != null) {
			list = prepareTaxInvoiceDataListofBean(nbfcInvoiceService
					.getTaxInvoiceDataASF(mliId, session));

		} else {
			throw new CustomExceptionHandler("MLI name is null");
		}
		if (list.size() > 0 || !list.isEmpty()) {
			model1.put("dataList", list);

		} else {
			model1.put("dataList", "");

			// throw new CustomExceptionHandler("No data found");
		}

		if (Role.equals("CMAKER")) {
			model1.put("guaranteelist", userActivityService.getActivity(
					"CGTMSEMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"CGTMSEMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"CGTMSEMAKER", "Receipt_Payments"));
			model1.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSEMAKER", "Guarantee_Maintenance"));
			model1.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSEMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "cgtmseMakerHome");

		} else if (Role.equals("CCHECKER")) {
			model1.put("adminlist", userActivityService.getActivity(
					"CGTMSECHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"CGTMSECHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"CGTMSECHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"CGTMSECHECKER", "Receipt_Payments"));
			model1.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSECHECKER", "Guarantee_Maintenance"));
			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
			model1.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSECHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "cgtmseCheckerHome");
			// return null;
		} else if (Role.equals("NMAKER")) {
			// added by say 6 feb-----------------------
			model1.put("adminlist", userActivityService.getActivity(
					"NBFCMAKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"NBFCMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"NBFCMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"NBFCMAKER", "Receipt_Payments"));
			model1.put("repList",
					userActivityService.getReport("NBFCMAKER", "User_Report"));
			model1.put("GMaintainlist", userActivityService.getActivity(
					"NBFCMAKER", "Guarantee_Maintenance"));
			model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			model1.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcMakerHome");

		} else if (Role.equals("NCHECKER")) {
			model1.put("adminlist", userActivityService.getActivity(
					"NBFCCHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"NBFCCHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"NBFCCHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"NBFCCHECKER", "Receipt_Payments"));
			model1.put("repList",
					userActivityService.getReport("NBFCCHECKER", "User_Report"));
			model1.put("GMaintainlist", userActivityService.getActivity(
					"NBFCCHECKER", "Guarantee_Maintenance"));
			model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
			model1.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCCHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcCheckerHome");

		}
		return new ModelAndView("TaxInvoiceGenerationASF", model1);

	}

	@RequestMapping(value = "/proformaInvoiceReportASF", method = RequestMethod.GET)
	public ModelAndView proformaInvoiceReportASF(
			@ModelAttribute("command") CGPANDetailsReportBean bean,
			HttpServletResponse response, HttpServletRequest request,
			HttpSession session) throws JRException {
		System.out.println("hiiiiiiiiiiiii");
		String Role = (String) session.getAttribute("uRole");
		String userId = (String) session.getAttribute("userId");
		Map<String, Object> model1 = new HashMap<String, Object>();

		mliDetails = employeeService.getBNKId(userId);
		String mliId = mliDetails.getMem_bnk_id() + mliDetails.getMem_zne_id()
				+ mliDetails.getMem_brn_id();

		List<CGPANDetailsReportBean> list;
		if (mliId != null) {
			list = prepareTaxInvoiceDataListofBean(nbfcInvoiceService
					.proformaInvoiceDataASF(mliId, session));

		} else {
			throw new CustomExceptionHandler("MLI name is null");
		}
		if (list.size() > 0 || !list.isEmpty()) {
			model1.put("dataList", list);

		} else {
			model1.put("dataList", "");

			// throw new CustomExceptionHandler("No data found");
		}

		if (Role.equals("CMAKER")) {
			model1.put("guaranteelist", userActivityService.getActivity(
					"CGTMSEMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"CGTMSEMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"CGTMSEMAKER", "Receipt_Payments"));
			model1.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSEMAKER", "Guarantee_Maintenance"));
			model1.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSEMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "cgtmseMakerHome");

		} else if (Role.equals("CCHECKER")) {
			model1.put("adminlist", userActivityService.getActivity(
					"CGTMSECHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"CGTMSECHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"CGTMSECHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"CGTMSECHECKER", "Receipt_Payments"));
			model1.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSECHECKER", "Guarantee_Maintenance"));
			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSECHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "cgtmseCheckerHome");
			// return null;
		} else if (Role.equals("NMAKER")) {
			// added by say 6 feb-----------------------
			model1.put("adminlist", userActivityService.getActivity(
					"NBFCMAKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"NBFCMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"NBFCMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"NBFCMAKER", "Receipt_Payments"));
			model1.put("GMaintainlist", userActivityService.getActivity(
					"NBFCMAKER", "Guarantee_Maintenance"));
			model1.put("repList",
					userActivityService.getReport("NBFCMAKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			model1.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcMakerHome");

		} else if (Role.equals("NCHECKER")) {
			model1.put("adminlist", userActivityService.getActivity(
					"NBFCCHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"NBFCCHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"NBFCCHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"NBFCCHECKER", "Receipt_Payments"));
			model1.put("repList",
					userActivityService.getReport("NBFCCHECKER", "User_Report"));
			model1.put("GMaintainlist", userActivityService.getActivity(
					"NBFCCHECKER", "Guarantee_Maintenance"));
			model1.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCCHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcCheckerHome");

		}
		return new ModelAndView("ProformaInvoiceGenerationASF", model1);

	}

	private List<CGPANDetailsReportBean> prepareTaxInvoiceDataListofBean(
			List<Object> taxInvoiceData) {
		// TODO Auto-generated method stub
		List<CGPANDetailsReportBean> list = new ArrayList<CGPANDetailsReportBean>();
		Iterator<Object> itr1 = taxInvoiceData.iterator();
		while (itr1.hasNext()) {
			CGPANDetailsReportBean bean = new CGPANDetailsReportBean();
			Object[] obj1 = (Object[]) itr1.next();
	 	    String danId = (String) obj1[0];
			//String portfolioName = (String) obj1[1];
			String bankName = (String) obj1[1];
			/*Date Datetime = (Date) obj1[2];
			
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String Datetime1="";
			if(Datetime !=null){
				
				Datetime1 = df.format(Datetime);
			}*/
		
			bean.setDanId(danId);
			bean.setBankname(bankName);
		//	bean.setPortfolioName(portfolioName);
			list.add(bean);

		}

		return list;
	}

	@RequestMapping(value = "/callJasperReportOfProformaASF", method = RequestMethod.GET)
	public ModelAndView callJasperReportOfProformaASF(
			@ModelAttribute("command") CGPANDetailsReportBean bean,
			ModelAndView modelAndView, @RequestParam("danId") String danId,
			Model modelMsg, ModelMap model, HttpServletResponse response,
			HttpServletRequest request,HttpSession session) throws JRException {
		System.out.println("Test" + danId);
		long GeneratedproformaNo = (long) 1000;
		// CGPANDetailsReportBean bean=null;
		String userId = (String) session.getAttribute("userId");
		Integer proformainvoiceCount = nbfcInvoiceService
				.getProformaInvoiceIdCountASF(danId);
		if (proformainvoiceCount == 0) {
			List<Object> list = nbfcInvoiceService.getTaxReportDetailsASF(danId,userId);
			Iterator<Object> itr1 = list.iterator();
			while (itr1.hasNext()) {
				Object[] obj1 = (Object[]) itr1.next();
				String danID = (String) obj1[0];
				Double igstAmt = ((BigDecimal) obj1[1]).doubleValue();
				Double cgstAmt = ((BigDecimal) obj1[2]).doubleValue();
				Double sgstAmt = ((BigDecimal) obj1[3]).doubleValue();
				Double oustandingAmt = ((BigDecimal) obj1[4]).doubleValue();
				Date appropriationDate = (Date) obj1[5];
				System.out.println("----date--------" + appropriationDate);
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String appropriationDate1="";
				String appDate="";
				if(appropriationDate !=null){
					
					 appropriationDate1 = df.format(appropriationDate);
				}
				Float guranteeFee = ((BigDecimal) obj1[6]).floatValue();
			 	Date danGenDate = (Date) obj1[7];
				String danGenDate1 = df.format(danGenDate);
				String stateCode = (String) obj1[8];
				String stateName = (String) obj1[9];
				Date date1 = (Date) obj1[10];
				appDate = df.format(date1);
				bean.setAppsubmittedDate(appDate);
				Integer maxProformaInvoiceIdCount = nbfcInvoiceService
						.getMaxProformaInvoiceIdCountASF();

				if (maxProformaInvoiceIdCount == 0) {
					GeneratedproformaNo = (long) 000;
				} else {
					GeneratedproformaNo = maxProformaInvoiceIdCount;
				}

				System.out.println("id----------" + GeneratedproformaNo);
				bean.setTaxInvoiceId(GeneratedproformaNo);
				bean.setDciGuaranteeeSDate(danGenDate1);
				bean.setDanId(danID);
				bean.setOutstandingAmount1(oustandingAmt);
				
				bean.setDciAppropriationDate(appropriationDate1);
				bean.setIgstAmt(igstAmt);
				bean.setCgstAmt(cgstAmt);
				bean.setSgstAmt(sgstAmt);
				bean.setGuaranteeFee(guranteeFee);
				bean.setState(stateName);
			}
			nbfcInvoiceService.callProformaInvoiceReportASF(danId, response, request,
					bean);
			 ProformaInvoiceDetailsASF proformaObj1  = prepareModelForProformaDetails(bean,danId);
			nbfcInvoiceService.insertProformaInvoiceDetailsASF(proformaObj1);

		} else {
			List<Object> list = nbfcInvoiceService.getTaxReportDetailsASF(danId,userId);
			Iterator<Object> itr1 = list.iterator();
			while (itr1.hasNext()) {
				Object[] obj1 = (Object[]) itr1.next();
				String danID = (String) obj1[0];
				Double igstAmt = ((BigDecimal) obj1[1]).doubleValue();
				Double cgstAmt = ((BigDecimal) obj1[2]).doubleValue();
				Double sgstAmt = ((BigDecimal) obj1[3]).doubleValue();
				Double oustandingAmt = ((BigDecimal) obj1[4]).doubleValue();
				Date appropriationDate = (Date) obj1[5];
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String appropriationDate1="";
				String appDate="";
				if(appropriationDate !=null){
			         appropriationDate1 = df.format(appropriationDate);
				}
				Float guranteeFee = ((BigDecimal) obj1[6]).floatValue();
				
				Date danGenDate = (Date) obj1[7];
				String danGenDate1 = df.format(danGenDate);
				String stateCode = (String) obj1[8];
				String stateName = (String) obj1[9];
				Date date1 = (Date) obj1[10];
				appDate = df.format(date1);
				bean.setAppsubmittedDate(appDate);
				String ProformaInvoiceId = nbfcInvoiceService.getProformaInvoiceNoASF(danId);
				System.out.println("ProformaInvoiceId-------------" + ProformaInvoiceId);
				bean.setTaxInvoiceId(Long.parseLong(ProformaInvoiceId));
				bean.setDanId(danId);
				bean.setOutstandingAmount1(oustandingAmt);
				bean.setDciGuaranteeeSDate(danGenDate1);
				//bean.setAppsubmittedDate((String) obj1[10]);
				bean.setDciAppropriationDate(appropriationDate1);
				bean.setIgstAmt(igstAmt);
				bean.setCgstAmt(cgstAmt);
				bean.setSgstAmt(sgstAmt);
				bean.setGuaranteeFee(guranteeFee);
				bean.setState(stateName);

			}
			nbfcInvoiceService.callProformaInvoiceReportASF(danId, response, request,
					bean);
			// TaxInvoiceDetails taxDetailsObj =
			// prepareModelForDetails(bean,dan_id);
			// nbfcInvoiceService.insertTaxInvoiceDetails(taxDetailsObj);

		}

		return new ModelAndView("redirect:/TaxInvoicesdata.html");
	}

	private ProformaInvoiceDetailsASF prepareModelForProformaDetails(
			CGPANDetailsReportBean bean, String portfolioName) {
		proformaDetailsObj1.setPROFORMA_INV_ID(bean.getTaxInvoiceId());
		proformaDetailsObj1.setDAN_ID(bean.getDanId());
		proformaDetailsObj1.setPORTFOLIO_NAME(portfolioName);
		proformaDetailsObj1.setDAN_TYPE("CF");
		proformaDetailsObj1.setPROFORMA_INVOICE_GEN_DT(null);
		return proformaDetailsObj1;
	}

	@RequestMapping(value = "/callJasperReportOfTaxASF", method = RequestMethod.GET)
	public ModelAndView callJasperReportOfTaxASF(
			@ModelAttribute("command") CGPANDetailsReportBean bean,
			ModelAndView modelAndView, @RequestParam("danId") String danId,
			Model modelMsg, ModelMap model, HttpServletResponse response,
			HttpServletRequest request,HttpSession session) throws JRException {
		System.out.println("Test" + danId);
		long GeneratedTaxNo = (long) 1000;
		// CGPANDetailsReportBean bean=null;
		String userId = (String) session.getAttribute("userId");
		Integer TaxinvoiceCount = nbfcInvoiceService
				.getTaxInvoiceIdCountASF(danId);
		if (TaxinvoiceCount == 0) {
			List<Object> list = nbfcInvoiceService.getTaxReportDetailsASF(danId,userId);
			Iterator<Object> itr1 = list.iterator();
			while (itr1.hasNext()) {
				Object[] obj1 = (Object[]) itr1.next();
				String danID = (String) obj1[0];
				Double igstAmt = ((BigDecimal) obj1[1]).doubleValue();
				Double cgstAmt = ((BigDecimal) obj1[2]).doubleValue();
				Double sgstAmt = ((BigDecimal) obj1[3]).doubleValue();
				Double oustandingAmt = ((BigDecimal) obj1[4]).doubleValue();
				Date appropriationDate = (Date) obj1[5];
				System.out.println("----date--------" + appropriationDate);
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String appropriationDate1="";
				String appDate="";
				if(appropriationDate !=null){
					
					 appropriationDate1 = df.format(appropriationDate);
				}
				Float guranteeFee = ((BigDecimal) obj1[6]).floatValue();
			 	Date danGenDate = (Date) obj1[7];
				String danGenDate1 = df.format(danGenDate);
				String stateCode = (String) obj1[8];
				String stateName = (String) obj1[9];
				Date date1 = (Date) obj1[10];
				appDate = df.format(date1);
				bean.setAppsubmittedDate(appDate);
//				Date danGenDate = (Date) obj1[7];
//				String danGenDate1 = df.format(danGenDate);
				Integer maxTaxInvoiceIdCount = nbfcInvoiceService
						.getMaxTaxInvoiceIdCountASF();
				
				

				if (maxTaxInvoiceIdCount == 0) {
					GeneratedTaxNo = (long) 1000;
				} else {
					GeneratedTaxNo = maxTaxInvoiceIdCount;
				}
				
				System.out.println("id----------" + GeneratedTaxNo);
				bean.setTaxInvoiceId(GeneratedTaxNo);
				bean.setDanId(danID);
				bean.setOutstandingAmount1(oustandingAmt);
			//	bean.setDciGuaranteeeSDate(danGenDate1);
				bean.setDciAppropriationDate(appropriationDate1);
				bean.setIgstAmt(igstAmt);
				bean.setCgstAmt(cgstAmt);
				bean.setSgstAmt(sgstAmt);
				bean.setGuaranteeFee(guranteeFee);
				bean.setState(stateName);
			}
			nbfcInvoiceService.callTaxInvoiceReportASF(danId, response, request,
					bean);
			TaxInvoiceDetailsASF taxDetailsObj = prepareModelForDetails(bean,
					danId);
			nbfcInvoiceService.insertTaxInvoiceDetailsASF(taxDetailsObj);

		} else {
			List<Object> list = nbfcInvoiceService.getTaxReportDetailsASF(danId,userId);
			Iterator<Object> itr1 = list.iterator();
			while (itr1.hasNext()) {
				Object[] obj1 = (Object[]) itr1.next();
				String danID = (String) obj1[0];
				Double igstAmt = ((BigDecimal) obj1[1]).doubleValue();
				Double cgstAmt = ((BigDecimal) obj1[2]).doubleValue();
				Double sgstAmt = ((BigDecimal) obj1[3]).doubleValue();
				Double oustandingAmt = ((BigDecimal) obj1[4]).doubleValue();
				Date appropriationDate = (Date) obj1[5];
				System.out.println("----date--------" + appropriationDate);
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String appropriationDate1="";
				String appDate="";
				if(appropriationDate !=null){
					
					 appropriationDate1 = df.format(appropriationDate);
				}
				Float guranteeFee = ((BigDecimal) obj1[6]).floatValue();
			 	Date danGenDate = (Date) obj1[7];
				String danGenDate1 = df.format(danGenDate);
				String stateCode = (String) obj1[8];
				String stateName = (String) obj1[9];
				Date date1 = (Date) obj1[10];
				appDate = df.format(date1);
				bean.setAppsubmittedDate(appDate);
				String TaxInvoiceId = nbfcInvoiceService.getTaxInvoiceNoASF(danId);
			
				System.out.println("TaxInvoiceId-------------" + TaxInvoiceId);
				bean.setTaxInvoiceId(Long.parseLong(TaxInvoiceId));
				bean.setDanId(danId);
				bean.setOutstandingAmount1(oustandingAmt);
				bean.setDciAppropriationDate(appropriationDate1);
				bean.setIgstAmt(igstAmt);
				bean.setCgstAmt(cgstAmt);
				bean.setSgstAmt(sgstAmt);
				bean.setGuaranteeFee(guranteeFee);
				bean.setState(stateName);
			}
			nbfcInvoiceService.callTaxInvoiceReportASF(danId, response, request,
					bean);
			// TaxInvoiceDetails taxDetailsObj =
			// prepareModelForDetails(bean,dan_id);
			// nbfcInvoiceService.insertTaxInvoiceDetails(taxDetailsObj);

		}

		return new ModelAndView("redirect:/TaxInvoicesdata.html");
	}

	private TaxInvoiceDetailsASF prepareModelForDetails(
			CGPANDetailsReportBean bean, String PORTFOLIONAME) {
		taxDetailsObj2.setTAX_INV_ID(bean.getTaxInvoiceId());
		taxDetailsObj2.setPORTFOLIO_NAME(null);
		taxDetailsObj2.setDAN_ID(bean.getDanId());
		taxDetailsObj2.setTAX_INVOICE_GEN_DT(null);
		taxDetailsObj2.setDAN_TYPE("AF");
		return taxDetailsObj2;
	}
	

}
