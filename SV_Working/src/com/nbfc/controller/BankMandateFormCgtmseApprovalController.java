package com.nbfc.controller;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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

import com.nbfc.bean.BankMandateFormBo;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.Login;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserActivity;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.BankMandateFormService;
import com.nbfc.service.LoginService;
import com.nbfc.service.NBFCUserReportService;
import com.nbfc.service.UserActivityService;
import com.nbfc.utility.ApprovalStatus;

@Controller
public class BankMandateFormCgtmseApprovalController {
	final static Logger logger = Logger.getLogger(BankMandateFormCgtmseApprovalController.class.getName());
	@Autowired
	BankMandateFormService bankMandateService;
	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	NBFCUserReportService nbfcUserReportService;
	@Autowired
	MenuUtilsController menuUtilsController;

	String userId = null;
	Login userDetails = null;
	String mliName = null;

	@RequestMapping(value = "/bankMandateFormCgtmscApproval", method = RequestMethod.GET)
	public ModelAndView getDetailBankMandateCgtmseApproval(Model modeluserRole, HttpSession session)
			throws BusinessException {
		Map<String, Object> model = new HashMap<String, Object>();
		UserPerivilegeDetails userPri;
		NBFCPrivilegeMaster userPrvMst;
		try {
			userId = (String) session.getAttribute("userId");
			mliName = (String) session.getAttribute("mliName");
			String Role = (String) session.getAttribute("uRole");
			if (userId != null) {
				session.setAttribute("bankMandateUser", userId);
				userDetails = lofinService.userDetails(userId);
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				model = menuUtilsController.prepareMenu(session, Role, userId);
				if (model!=null) {
					if(Role.equals("CCHECKER")){
						model.put("homePage", "cgtmseCheckerHome");
					}
					if(Role.equals("CMAKER")){
						model.put("homePage", "cgtmseMakerHome");
					}
					modeluserRole.addAttribute("MliName", mliName);
					modeluserRole.addAttribute("user_id",
							userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID());
					modeluserRole.addAttribute("loginId", userDetails.getUsr_id());
					return new ModelAndView("BankMandateFormCgtmseApproval", model);
				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}

	@RequestMapping(value = "/getAllCgtmseApproval", method = RequestMethod.GET)
	//public @ResponseBody ResponseEntity<List<BankMandateFormBo>> getAllCGTMSE() throws BusinessException {
	public @ResponseBody ResponseEntity<List<BankMandateFormBo>> getAllCGTMSE(@RequestParam String apprvStatus) throws BusinessException {
		List<BankMandateFormBo> allCGTMSE = null;
		try {
			System.out.println("Approval Status is ::::" + apprvStatus);
			allCGTMSE = bankMandateService.getAllCgtmseApprovalByUserId("",apprvStatus);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<BankMandateFormBo>(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(allCGTMSE, HttpStatus.OK);
	}

	@RequestMapping(value = "/update-cgtmse-mli-approval", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> updateCgtmseMliApproval(@RequestBody BankMandateFormBo bo)
			throws BusinessException {
		boolean isSuccess = false;
		try {
			if (bo != null) {
				if (bo.getApproveStatus().equalsIgnoreCase("Approve")) {
					bo.setApproveStatus(ApprovalStatus.APPROVED.getValue());
				} else if (bo.getApproveStatus().equalsIgnoreCase("Return")) {
					bo.setApproveStatus(ApprovalStatus.REJECTED.getValue());
				}
				bo.setLoginId(userId);
			}
			isSuccess = bankMandateService.updateCgtmseApproal(bo);
			if (isSuccess) {
				return new ResponseEntity<>("Record updated successfully.", HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<>("Something Went Wrong.", HttpStatus.BAD_REQUEST);
	}

	// added
	@RequestMapping(value = "/download-cgtmse-mli-approval", method = RequestMethod.GET)
	@ResponseBody
	public void downloadMandateFileBankApprovalData(@RequestParam String mliId, HttpServletResponse response,
			HttpSession session) throws BusinessException {
		try {
			BankMandateFormBo bankMadateData = bankMandateService.downloadCgtmseApproal(mliId);
			if (bankMadateData != null) {
				byte[] fileData = bankMadateData.getFileData();
				Path source = Paths.get(bankMadateData.getFileName());
				String contentType = Files.probeContentType(source);

				response.setContentType("application/msword");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + bankMadateData.getFileName() + "\"");
				OutputStream os = response.getOutputStream();
				os.write(fileData);
				os.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
