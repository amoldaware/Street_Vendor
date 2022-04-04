package com.nbfc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.nbfc.bean.StateBean;
import com.nbfc.bean.TaxDetailsBean;
import com.nbfc.helper.ExcelValidator;
import com.nbfc.helper.NBFCValidator;
import com.nbfc.model.District;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIExposureId;
import com.nbfc.model.MLIName;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.PortfolioBatchApp;
import com.nbfc.model.PortfolioNumInfo;
import com.nbfc.model.PortfolioNumber;
import com.nbfc.model.PortfolioNumberMaster;
import com.nbfc.model.State;
import com.nbfc.model.TaxDetailMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.DistrictService;
import com.nbfc.service.LoginService;
import com.nbfc.service.MLICheckerUpdateService;
import com.nbfc.service.MLIValidatorService;
import com.nbfc.service.NBFCInvoiceService;
import com.nbfc.service.PortfolioApprovalService;
import com.nbfc.service.PortfolioBatchService;
import com.nbfc.service.PortfolioInfoService;
import com.nbfc.service.StateService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;
import com.nbfc.validation.EmployeeValidator;

@Controller
public class NBFCTaxInvoiceController {

	@Autowired
	UserService employeeService;
	@Autowired
	NBFCInvoiceService nbfcInvoiceService;
	@Autowired
	PortfolioInfoService portfolioInfoService;
	@Autowired
	StateService stateService;
	@Autowired
	PortfolioBatchService portfolioBatchService;
	@Autowired
	DistrictService districtService;
	@Autowired
	EmployeeValidator validator;
	@Autowired
	PortfolioApprovalService portfolioApprovalService;
	@Autowired
	MLIValidatorService mliValidatorService;
	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	MLICheckerUpdateService mliCheckerUpdateService;

	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;
	/*
	 * private Pattern pattern; private Matcher matcher;
	 */
	private List<PortfolioNumInfo> appRefNum = new ArrayList<PortfolioNumInfo>();
	private List<PortfolioNumber> quarterNumber = new ArrayList<PortfolioNumber>();
	static Logger log = Logger.getLogger(NBFCController.class.getName());
	static int count;
	static int countPortfolioNUmber;
	
	@RequestMapping(value = "/TaxInvoicesdata", method = RequestMethod.GET)
	public ModelAndView TaxInvoicesdata(
			@ModelAttribute("command") TaxDetailsBean Bean,
			BindingResult result, HttpServletRequest request,
			HttpSession session, Model modelMsg) throws Exception {
		System.out.println("state" + request.getParameter("state"));
		Map<String, Object> model = new HashMap<String, Object>();

		// List<State> stateList = stateService.listStates();
		log.info("Tax In voice Generation****************************************************************************************************************************************");

		model.put("stateList",
				prepareStateListofBean(stateService.listStates("CCA")));
		String userId = (String) session.getAttribute("userId");
		if (userId.equals("CGTMSE ADMIN")) {
			return new ModelAndView("TaxInvoiceReport", model);
		} else {
			if (userId != null) {
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst = lofinService
						.getPrivlageMstDtl(userPri.getPrv_id());
				if (userPrvMst.getPrvCreatedModifiedBy().equals("ADMIN")) {
					model.put("actList", userActivityService.getActivity(
							"ADMIN", "User_Activity"));
					model.put(
							"employees",
							prepareListofBeanofTaxData(
									nbfcInvoiceService.getTaxInvoiceDeails(),
									employeeService));
					model.put("homePage", "adminHome");

					return new ModelAndView("TaxInvoiceReport", model);
				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}

	private List<TaxDetailsBean> prepareListofBeanofTaxData(
			List<TaxDetailMaster> taxInvoiceDeails, UserService employeeService2) {
		List<TaxDetailsBean> beans = null;
		if (taxInvoiceDeails != null && !taxInvoiceDeails.isEmpty()) {
			beans = new ArrayList<TaxDetailsBean>();
			TaxDetailsBean bean = null;
			for (TaxDetailMaster employee : taxInvoiceDeails) {
				bean = new TaxDetailsBean();
				try {
					bean.setMem_id(employee.getMem_id());
					bean.setT_id(employee.getT_id());
					bean.setNbfc_name(employee.getNbfc_name());
					bean.setPortfoliono(employee.getPortfoliono());
					bean.setSanction_amt(employee.getSanction_amt());
					bean.setApprove_amt(employee.getApprove_amt());

					beans.add(bean);
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		}
		return beans;
	}
	
	private List<StateBean> prepareStateListofBean(List<State> employees) {
		List<StateBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<StateBean>();
			StateBean bean = null;
			for (State employee : employees) {
				bean = new StateBean();
				bean.setSte_code(employee.getSte_code());
				bean.setSte_name(employee.getSte_name());
				beans.add(bean);
			}
		}
		return beans;
	}
}
