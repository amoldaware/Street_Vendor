package com.nbfc.controller;

import java.math.BigDecimal;
import java.text.ParseException;
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

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.CGPANDetailsReportBean;
import com.nbfc.bean.DanGenerateRpNumberForCheckerApprovalBean;
import com.nbfc.bean.LoginBean;
import com.nbfc.helper.NBFCHelper;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.OtpDetailsModel;
import com.nbfc.model.ProformaInvoiceDetails;
import com.nbfc.model.TaxInvoiceDetails;
import com.nbfc.service.NBFCInvoiceService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class JasperReportController {
	@Autowired
	NBFCInvoiceService nbfcInvoiceService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	UserService employeeService;
	TaxInvoiceDetails taxDetailsObj1 = new TaxInvoiceDetails();
	ProformaInvoiceDetails proformaDetailsObj1 = new ProformaInvoiceDetails();
	MLIDetails mliDetails;

	@RequestMapping(value = "/taxInvoiceReport", method = RequestMethod.GET)
	public ModelAndView taxInvoiceReport(
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
					.getTaxInvoiceData(mliId, session));

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
			model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
			model1.put("repList",
					userActivityService.getReport("NBFCCHECKER", "User_Report"));
			model1.put("GMaintainlist", userActivityService.getActivity(
					"NBFCCHECKER", "Guarantee_Maintenance"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCCHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcCheckerHome");

		}
		return new ModelAndView("TaxInvoiceGeneration", model1);

	}

	@RequestMapping(value = "/proformaInvoiceReport", method = RequestMethod.GET)
	public ModelAndView proformaInvoiceReport(
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
					.proformaInvoiceData(mliId, session));

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
			model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
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
			model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			model1.put("repList",
					userActivityService.getReport("NBFCMAKER", "User_Report"));
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
			model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
			model1.put("repList",
					userActivityService.getReport("NBFCCHECKER", "User_Report"));
			model1.put("GMaintainlist", userActivityService.getActivity(
					"NBFCCHECKER", "Guarantee_Maintenance"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("NBFCCHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("homePage", "nbfcCheckerHome");

		}
		return new ModelAndView("ProformaInvoiceGeneration", model1);

	}

	private List<CGPANDetailsReportBean> prepareTaxInvoiceDataListofBean(
			List<Object> taxInvoiceData) {
		// TODO Auto-generated method stub
		List<CGPANDetailsReportBean> list = new ArrayList<CGPANDetailsReportBean>();
		Iterator<Object> itr1 = taxInvoiceData.iterator();
		while (itr1.hasNext()) {
			CGPANDetailsReportBean bean = new CGPANDetailsReportBean();
			Object[] obj1 = (Object[]) itr1.next();
		    String danId = (String) obj1[1];
			String portfolioName = (String) obj1[0];
			String bankName = (String) obj1[1];
			String MLIUSER = (String) obj1[2];
			/*Date Datetime = (Date) obj1[2];
			
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String Datetime1="";
			if(Datetime !=null){
				
				Datetime1 = df.format(Datetime);
			}
			bean.setDate(Datetime1);*/
			bean.setDanId(danId);
			bean.setBankname(bankName);
			bean.setPortfolioName(portfolioName);
			bean.setUserId(MLIUSER);
			list.add(bean);

		}

		return list;
	}

	
	@RequestMapping(value = "/callJasperReportOfProforma", method = RequestMethod.GET)
	public ModelAndView callJasperReportOfProforma(
			@ModelAttribute("command") CGPANDetailsReportBean bean,
			ModelAndView modelAndView, @RequestParam("portfolioName") String portfolioName,@RequestParam("userId") String user,
			Model modelMsg, ModelMap model, HttpServletResponse response,
			HttpServletRequest request,HttpSession session) throws JRException {
			System.out.println("portfolioName ====" + portfolioName);
			long GeneratedproformaNo = (long) 0000;
			NBFCHelper nbfcHelper = new NBFCHelper();
			String userId = (String) session.getAttribute("userId");
			Integer proformainvoiceCount = nbfcInvoiceService.getProformaInvoiceIdCount(portfolioName);
			if (proformainvoiceCount == 0) {
				List<Object> list = nbfcInvoiceService.getTaxReportDetails(portfolioName,user);
				Iterator<Object> itr1 = list.iterator();
				while (itr1.hasNext()) {
					Object[] obj1 = (Object[]) itr1.next();
				
				/*	StringBuilder sb = new StringBuilder(fyBasedOnStartAndEndDate);
					sb.delete(5, 7);
					String fy = sb.toString()*/

					Float guranteeFee = ((BigDecimal) obj1[20]).floatValue();
					Integer maxProformaInvoiceIdCount = nbfcInvoiceService.getMaxProformaInvoiceIdCount();
					System.out.println("maxProformaInvoiceIdCount==="+maxProformaInvoiceIdCount);
					if (maxProformaInvoiceIdCount == 0) {
						GeneratedproformaNo = (long) 0000;
					} else {
						GeneratedproformaNo = maxProformaInvoiceIdCount;
					}
	
					System.out.println("GeneratedproformaNo----------" + GeneratedproformaNo);
					bean.setTaxInvoiceId(GeneratedproformaNo);
					bean.setOutstandingAmount1(((BigDecimal) obj1[9]).doubleValue());
					bean.setIgstAmt(((BigDecimal) obj1[15]).doubleValue());
					bean.setCgstAmt(((BigDecimal) obj1[17]).doubleValue());
					bean.setSgstAmt(((BigDecimal) obj1[19]).doubleValue());
					bean.setDci_base_amt(((BigDecimal) obj1[13]).doubleValue());
					bean.setDci_total_amt(((BigDecimal) obj1[12]).doubleValue());
					
					bean.setGuaranteeFee(guranteeFee);
					bean.setAppsubmittedDate((String) obj1[11]);
					bean.setState((String) obj1[5]);
					
					bean.setUserId(user);
				}
				nbfcInvoiceService.callProformaInvoiceReport(portfolioName, response, request,bean);
				ProformaInvoiceDetails proformaObj1  = prepareModelForProformaDetails(bean,portfolioName);
				nbfcInvoiceService.insertProformaInvoiceDetails(proformaObj1);
	
			} else {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
				Date currentDate = new Date();
				Integer maxProformaInvoiceIdCount = nbfcInvoiceService.getMaxProformaInvoiceIdCount();
				List<Object> list = nbfcInvoiceService.getTaxReportDetails(portfolioName,user);
				Iterator<Object> itr1 = list.iterator();
				while (itr1.hasNext()) {
					Object[] obj1 = (Object[]) itr1.next();
					Double oustandingAmt = ((BigDecimal) obj1[9]).doubleValue();
					Float guranteeFee = ((BigDecimal) obj1[20]).floatValue();
					System.out.println("guranteeFee-------------" + guranteeFee);
					String ProformaInvoiceId = nbfcInvoiceService.getProformaInvoiceNo(portfolioName);
 					System.out.println("ProformaInvoiceId-------------" + ProformaInvoiceId);
					bean.setTaxInvoiceId(Long.parseLong(ProformaInvoiceId));
					bean.setOutstandingAmount1(oustandingAmt);
					bean.setIgstAmt(((BigDecimal) obj1[15]).doubleValue());
					bean.setCgstAmt(((BigDecimal) obj1[17]).doubleValue());
					bean.setSgstAmt(((BigDecimal) obj1[19]).doubleValue());
					bean.setAppsubmittedDate((String) obj1[11]);
					bean.setState((String) obj1[5]);
					bean.setGuaranteeFee(guranteeFee);
					bean.setUserId(user);
					bean.setDci_base_amt(((BigDecimal) obj1[13]).doubleValue());
					bean.setDci_total_amt(((BigDecimal) obj1[12]).doubleValue());
				}
				nbfcInvoiceService.callProformaInvoiceReport(portfolioName, response, request,bean);
				
	
			}
	
			return new ModelAndView("redirect:/TaxInvoicesdata.html");
		}
	
		private ProformaInvoiceDetails prepareModelForProformaDetails(
				CGPANDetailsReportBean bean, String portfolioName) {
			proformaDetailsObj1.setPROFORMA_INV_ID(bean.getTaxInvoiceId());
			proformaDetailsObj1.setDAN_ID(bean.getDanId());
			proformaDetailsObj1.setPORTFOLIO_NAME(portfolioName);
			proformaDetailsObj1.setDAN_TYPE("CF");
			proformaDetailsObj1.setPROFORMA_INVOICE_GEN_DT(null);
		//	proformaDetailsObj1.set(null);
			return proformaDetailsObj1;
		}
	
		
		
		@RequestMapping(value = "/callJasperReportOfTax", method = RequestMethod.GET)
		public ModelAndView callJasperReportOfTax(@ModelAttribute("command") CGPANDetailsReportBean bean,ModelAndView modelAndView, @RequestParam("portfolioName") String portfolioName,@RequestParam("userId") String user,Model modelMsg, ModelMap model, HttpServletResponse response,HttpServletRequest request,HttpSession session) throws JRException {
				System.out.println("Test" + user);
			//	long GeneratedTaxNo = (long) 0000000;
				String userId = (String) session.getAttribute("userId");
				NBFCHelper nbfcHelper = new NBFCHelper();
				String fyBasedOnStartAndEndDate = nbfcHelper.getCurrentYear();
			//	Integer TaxinvoiceCount = nbfcInvoiceService.getTaxInvoiceIdCount(portfolioName);
		//	if (TaxinvoiceCount == 0) {
				List<Object> list = nbfcInvoiceService.getTaxReportDetails(portfolioName,user);
				Iterator<Object> itr1 = list.iterator();
				while (itr1.hasNext()) {
					Object[] obj1 = (Object[]) itr1.next();
					Float guranteeFee = ((BigDecimal) obj1[20]).floatValue();
					String taxInvoiceId = nbfcInvoiceService.getTaxInvoiceNo(portfolioName);
					
					/*Integer maxTaxInvoiceIdCount = nbfcInvoiceService.getMaxTaxInvoiceIdCount();
					if (maxTaxInvoiceIdCount == 0) {
						GeneratedTaxNo = (long) 0000000;
					} else {
						GeneratedTaxNo = maxTaxInvoiceIdCount;
					}
					System.out.println("id----------" + GeneratedTaxNo)*/;
					bean.setTaxid(taxInvoiceId);
					bean.setOutstandingAmount1(((BigDecimal) obj1[9]).doubleValue());
					bean.setIgstAmt(((BigDecimal) obj1[15]).doubleValue());
					bean.setCgstAmt(((BigDecimal) obj1[17]).doubleValue());
					bean.setSgstAmt(((BigDecimal) obj1[19]).doubleValue());
					bean.setGuaranteeFee(guranteeFee);
					bean.setAppsubmittedDate((String) obj1[11]);
					bean.setUserId(user);
					bean.setfYYear(fyBasedOnStartAndEndDate);
					bean.setState((String) obj1[5]);
					bean.setDci_base_amt(((BigDecimal) obj1[13]).doubleValue());
					bean.setDci_total_amt(((BigDecimal) obj1[12]).doubleValue());
				}
				nbfcInvoiceService.callTaxInvoiceReport(portfolioName, response, request,bean);
				/*TaxInvoiceDetails taxDetailsObj = prepareModelForDetails(bean,portfolioName);
				nbfcInvoiceService.insertTaxInvoiceDetails(taxDetailsObj);*/
	
			/*} else {
				List<Object> list = nbfcInvoiceService.getTaxReportDetails(portfolioName,user);
				Iterator<Object> itr1 = list.iterator();
				while (itr1.hasNext()) {
					Object[] obj1 = (Object[]) itr1.next();
					Double oustandingAmt = ((BigDecimal) obj1[9]).doubleValue();
					Float guranteeFee = ((BigDecimal) obj1[20]).floatValue();
					System.out.println("guranteeFee-------------" + guranteeFee);
					String taxInvoiceId = nbfcInvoiceService.getTaxInvoiceNo(portfolioName);
 					System.out.println("ProformaInvoiceId-------------" + taxInvoiceId);
					bean.setTaxInvoiceId(Long.parseLong(taxInvoiceId));
					bean.setOutstandingAmount1(oustandingAmt);
					bean.setIgstAmt(((BigDecimal) obj1[15]).doubleValue());
					bean.setCgstAmt(((BigDecimal) obj1[17]).doubleValue());
					bean.setSgstAmt(((BigDecimal) obj1[19]).doubleValue());
					bean.setAppsubmittedDate((String) obj1[11]);
					bean.setState((String) obj1[5]);
					bean.setGuaranteeFee(guranteeFee);
					bean.setUserId(user);
					bean.setfYYear(fyBasedOnStartAndEndDate);
					bean.setDci_base_amt(((BigDecimal) obj1[13]).doubleValue());
					bean.setDci_total_amt(((BigDecimal) obj1[12]).doubleValue());
				}
				nbfcInvoiceService.callTaxInvoiceReport(portfolioName, response, request,
						bean);
				
	
			}
	*/
			return new ModelAndView("redirect:/TaxInvoicesdata.html");
		}
	
		
		
	
		private TaxInvoiceDetails prepareModelForDetails(
				CGPANDetailsReportBean bean, String PORTFOLIONAME) {
			taxDetailsObj1.setTAX_INV_ID(bean.getTaxInvoiceId());
			taxDetailsObj1.setPORTFOLIO_NAME(PORTFOLIONAME);
			//taxDetailsObj1.setDAN_ID("NBFCDANID00000");
			taxDetailsObj1.setTAX_INVOICE_GEN_DT(null);
			taxDetailsObj1.setFY_YEAR(bean.getfYYear());
			taxDetailsObj1.setDAN_TYPE("CF");
			
			return taxDetailsObj1;
		}
}
