package com.nbfc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nbfc.bean.ClaimPaymentBO;
import com.nbfc.bean.ClaimPaymentExcelBO;
import com.nbfc.bean.RecoveryReportBO;
import com.nbfc.bean.RecoveryReportExcelBO;
import com.nbfc.common.utility.method.Utils;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.Login;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserActivity;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.ClaimPaymentService;
import com.nbfc.service.RecoveryReportService;
import com.nbfc.service.ClaimReportServiceImpl;
import com.nbfc.service.LoginService;
import com.nbfc.service.UserActivityService;

@Controller
public class RecoveryReportController {
	final static Logger logger = Logger.getLogger(RecoveryReportController.class.getName());

	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	ClaimPaymentService claimPaymentService;
	@Autowired
	MenuUtilsController menuUtilsController;
	@Autowired
	private RecoveryReportService recoveryReportService;

	String userId = null;
	Login userDetails = null;

	@RequestMapping(value = "/claim-recovery-report", method = RequestMethod.GET)
	public ModelAndView claimUploadCgtmse(Model modeluserRole, HttpSession session) throws BusinessException {
		Map<String, Object> model = new HashMap<String, Object>();
		UserPerivilegeDetails userPri;
		NBFCPrivilegeMaster userPrvMst;
		try {
			userId = (String) session.getAttribute("userId");
			String Role = (String) session.getAttribute("uRole");
			if (userId != null) {

				userDetails = lofinService.userDetails(userId);
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				session.setAttribute("userRoleClaimPayment", userPrvMst.getPrvCreatedModifiedBy());
				model  = menuUtilsController.prepareMenu(session, Role, userId);
				if (model!=null) {
					if(Role.equals("CCHECKER")){
						model.put("homePage", "cgtmseCheckerHome");
					}
					if(Role.equals("CMAKER")){
						model.put("homePage", "cgtmseMakerHome");
					}
					
					if(Role.equals("NCHECKER")){
							model.put("homePage", "nbfcCheckerHome");
						}
						if(Role.equals("NMAKER")){
							model.put("homePage", "nbfcMakerHome");
						}
					return new ModelAndView("claimRecoveryReport", model);
				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}

	@RequestMapping(value = "/mli-list-report", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<RecoveryReportBO>> getMliList() throws BusinessException {
		List<RecoveryReportBO> mliList = null;
		try {
			mliList = recoveryReportService.getMliList(
					userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(mliList, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(mliList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search-recovery-report", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<RecoveryReportBO>> getAll(@RequestParam String dataBo)
			throws BusinessException {
		List<RecoveryReportBO> all = null;

		try {
			String[] split = dataBo.split(",");
			RecoveryReportBO bo = new RecoveryReportBO();
			bo.setMliId(split[0]);
			bo.setPmsNumber(split[1]);
			bo.setMisMatch(split[2]);
			bo.setFromDt(split[3]);
			bo.setToDate(split[4]);
			bo.setUserRole(split[5]);
			bo.setLoanAccount(split[6]);

			all = recoveryReportService.getAll(bo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new ArrayList<RecoveryReportBO>(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(all, HttpStatus.OK);
	}

	@RequestMapping(value = "/download-claim-recovery", method = RequestMethod.GET)
	public void downloadClaimPaymentApproval(@RequestParam String dataBo, HttpServletResponse response)
			throws BusinessException {
		List<RecoveryReportExcelBO> allExelData = null;
		try {
			String optionalColumns[] = { "SR.No.", "MLI ID", "MLI NAME", "RP NUMBER", "UPLOADED DATE", "TOTAL RECORD",
					"STATUS", "REMARK", "VIRTUAL ACCOUNT NUMBER", "APPROVAL DATE", "UPLOADED BY", "APPROVED BY",
					"UTR NUMBER", "APPROPRIATION DATE", "FTP RESPONSE", "MISMATCH AMOUNT","RECOVERY AMOUNT" };

			String[] split = dataBo.split(",");
			RecoveryReportBO bo = new RecoveryReportBO();
			bo.setMliId(split[0]);
			bo.setPmsNumber(split[1] == "N" ? "" : split[1]);
			bo.setMisMatch(split[2]);
			bo.setFromDt(split[3]);
			bo.setToDate(split[4]);
			bo.setUserRole(split[5]);
			bo.setLoanAccount(split[6] == "N" ? "" : split[6]);

			allExelData = recoveryReportService.getExportToExcelData(bo);
			Utils.writeToExcel("ClaimRecoveryReport", "RecoveryReport", optionalColumns, allExelData, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
