package com.nbfc.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.nbfc.bean.BankMandateFormBo;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.Login;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.BankMandateFormService;
import com.nbfc.service.LoginService;
import com.nbfc.service.NBFCUserReportService;
import com.nbfc.service.UserActivityService;
import com.nbfc.utility.ApprovalStatus;

@Controller
public class BankMandateFormController {
	final static Logger logger = Logger.getLogger(BankMandateFormController.class.getName());
	@Autowired
	BankMandateFormService bankMandateFormService;
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
	String msg=null;
	@RequestMapping(value = "/bankMandateForm", method = RequestMethod.GET)
	public ModelAndView getDetailBankMandateCgtmseUser(Model modeluserRole, HttpSession session)
			throws BusinessException {
		Map<String, Object> model = new HashMap<String, Object>();
		UserPerivilegeDetails userPri;
		NBFCPrivilegeMaster userPrvMst;
		try {
			userId = (String) session.getAttribute("userId");
			String Role = (String) session.getAttribute("uRole");
			mliName = (String) session.getAttribute("mliName");
			if (userId != null) {
				session.setAttribute("bankMandateUser",userId);
				userDetails = lofinService.userDetails(userId);
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				model = menuUtilsController.prepareMenu(session, Role, userId);
				if (model!=null) {
					model.put("homePage", "nbfcCheckerHome");
					modeluserRole.addAttribute("MliName", mliName);
					modeluserRole.addAttribute("user_id",
							userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID());
					modeluserRole.addAttribute("loginId", userDetails.getUsr_id());
					return new ModelAndView("BankMandateFormCgtmse", model);
				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}

	@RequestMapping(value = "/bankMandateFormMli", method = RequestMethod.GET)
	public ModelAndView getBankMandateFormMliDetail(Model modeluserRole, HttpSession session) throws BusinessException {
		Map<String, Object> model = new HashMap<String, Object>();
		UserPerivilegeDetails userPri;
		NBFCPrivilegeMaster userPrvMst;
		/* BankMandateFormBo bo=null; */
		try {
			userId = (String) session.getAttribute("userId");
			mliName = (String) session.getAttribute("mliName");
			String Role = (String) session.getAttribute("uRole");
			model = menuUtilsController.prepareMenu(session, Role, userId);
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				session.setAttribute("bankMandateUser",userId);			

					/*model.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
					model.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
					model.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
					model.put("applicationList",
							userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
					model.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
					model.put("actName", userActivityService.getActivityName("NBFCMAKER", "mliMaker"));
					model.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
					model.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
					model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
					model.put("claimUpload", userActivityService.getActivity("NBFCMAKER", "Claim_Upload"));
					model.put("bankMandateFormMli",
							userActivityService.getActivity("NBFCMAKER", "bank_Mandate_Form_Mli"));

					*/
					model.put("homePage", "nbfcMakerHome");
					modeluserRole.addAttribute("MliName", mliName);
					modeluserRole.addAttribute("user_id",
							userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID());
					modeluserRole.addAttribute("loginId", userDetails.getUsr_id());
					/* modeluserRole.addAttribute("status",bo.getApproveStatus()); */
					return new ModelAndView("BankMandateFormMli", model);
				 
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/SVLogin.html");
		
	}
	
	/*
	 * @RequestMapping(value = "/bankMandateFormCgtmscApproval", method =
	 * RequestMethod.GET) public ModelAndView
	 * getDetailBankMandateCgtmseApproval(Model modeluserRole, HttpSession session)
	 * throws BusinessException { Map<String, Object> model = new HashMap<String,
	 * Object>(); UserPerivilegeDetails userPri; NBFCPrivilegeMaster userPrvMst; try
	 * { userId = (String) session.getAttribute("userId"); mliName = (String)
	 * session.getAttribute("mliName"); if (userId != null) {
	 * session.setAttribute("bankMandateUser",userId); userDetails =
	 * lofinService.userDetails(userId); userPri =
	 * lofinService.getUserPrivlageDtl(userId, "A"); userPrvMst =
	 * lofinService.getPrivlageMstDtl(userPri.getPrv_id());
	 * 
	 * if (userPrvMst.getPrvCreatedModifiedBy().equals("CMAKER")||userPrvMst.
	 * getPrvCreatedModifiedBy().equals("CCHECKER")) {
	 * 
	 * model.put("repList", userActivityService.getReport("NBFCMAKER",
	 * "User_Report")); model.put("adminlist",
	 * userActivityService.getActivity("NBFCMAKER", "System_Admin"));
	 * model.put("guaranteelist", userActivityService.getActivity("NBFCMAKER",
	 * "Guarantee_Maintenance")); model.put("applicationList",
	 * userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
	 * model.put("RPaymentList", userActivityService.getActivity("NBFCMAKER",
	 * "Receipt_Payments")); model.put("actName",
	 * userActivityService.getActivityName("NBFCMAKER", "mliMaker"));
	 * model.put("repList", userActivityService.getReport("NBFCMAKER",
	 * "User_Report")); model.put("GMaintainlist",
	 * userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
	 * model.put("CList", userActivityService.getActivity("NBFCMAKER",
	 * "Claim_Lodgement"));
	 * model.put("claimUploadCGTMSE",userActivityService.getActivity(
	 * "CGTMSECHECKER", "claim_upload_cgtmse"));
	 * model.put("bankMandateFormCgtmscApproval",
	 * userActivityService.getActivity("CGTMSECHECKER",
	 * "bankMandateForm_Cgtmsc_Approval"));
	 * 
	 * model.put("homePage", "nbfcMakerHome"); modeluserRole.addAttribute("MliName",
	 * mliName); modeluserRole.addAttribute("user_id", userDetails.getMEM_BNK_ID() +
	 * userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID());
	 * modeluserRole.addAttribute("loginId", userDetails.getUsr_id()); return new
	 * ModelAndView("BankMandateFormCgtmseApproval", model); } else { return new
	 * ModelAndView("redirect:/SVLogin.html"); } } } catch (Exception e) {
	 * logger.error(e.getMessage()); } return new
	 * ModelAndView("redirect:/SVLogin.html"); }
	 */
	
	

	@RequestMapping(value = "/bankMandateFormData", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<BankMandateFormBo> bankMandateFormCgtmseDetails(HttpSession session)
			throws BusinessException {
		BankMandateFormBo bo = null;
		try {

			if (userDetails != null) {
				List<BankMandateFormBo> bnkList = bankMandateFormService.findBankMandateForm(
						userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID(),
						userDetails.getUsr_id());
				if (bnkList != null && !bnkList.isEmpty()) {
					bo = bnkList.get(0);
				} else {
					bo = new BankMandateFormBo();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new BankMandateFormBo(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(bo, HttpStatus.OK);
	}

	@RequestMapping(value = "/bankMandateFormMakerSave", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> saveBankMandateMli(@RequestParam("uploadfile") MultipartFile uploadfile,
			@RequestParam("objField") String objString,HttpSession session) throws BusinessException {
		boolean isSuccess = false;
		boolean isBnkExist = false;
		String msg="",userId1="";
		try {
			
			//Added by Sarita on 07032022 [START]
			userId1 = (String) session.getAttribute("userId");
			System.out.println("User ID IS ::" + userId1);
			//Added by Sarita on 07032022 [END]
			if (uploadfile.isEmpty()) {
				return new ResponseEntity<>("Please select file.", HttpStatus.BAD_REQUEST);
			}
			if (uploadfile.getOriginalFilename().contains("..")) {
				return new ResponseEntity<>(
						"Sorry! Filename contains invalid path sequence " + uploadfile.getOriginalFilename(),
						HttpStatus.BAD_REQUEST);
			}
			Gson gson = new Gson();
			BankMandateFormBo bo = gson.fromJson(objString, BankMandateFormBo.class);
			if (bo != null) 
			{
				isBnkExist = bankMandateFormService.checkBankMandateExist(bo.getMilId(),userId1);
				System.out.println("Value of isBnkExist is ::" + isBnkExist);
				if(isBnkExist == true){
					return new ResponseEntity<>("Bank Mandate Already Exists!!", HttpStatus.BAD_REQUEST);
				}
				else {
					bo.setFileData(uploadfile.getBytes());
					bo.setApproveStatus(ApprovalStatus.SUBMITTED_TO_MLI_CHECHER.getValue());
					isSuccess = bankMandateFormService.saveBankMandateFormDataMli(bo);
					if (isSuccess) {
						if(bo.getBankId()!=null && !bo.getBankId().isEmpty() ) {
							msg="Record updated successfully.";
						}else {
							msg="Your Data Successfully Saved.";
						}
						return new ResponseEntity<>(msg, HttpStatus.OK);
					}
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<>("Something Went Wrong.", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getbankMandateFormDataMli", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<BankMandateFormBo> getBankMandateMli() throws BusinessException {
		BankMandateFormBo bo = null;
		try {

			if (userDetails != null) {
				bo = bankMandateFormService.getBankMandateDataStatusWise(
						userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID(),
						"Approved");

			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new BankMandateFormBo(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(bo, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/download-mandate-form​", method =
	 * RequestMethod.GET) // @ResponseBody public void
	 * downloadMandateFileData(@RequestParam String mliId) throws BusinessException
	 * { BankMandateFormBo bankMadateData = null; try { bankMadateData =
	 * bankMandateFormService.findDataByUsrId(mliId, userId); if (bankMadateData !=
	 * null) {
	 * 
	 * byte[] fileData = bankMadateData.getFileData(); Path source =
	 * Paths.get(bankMadateData.getUploadDoc()); String contentType =
	 * Files.probeContentType(source);
	 * 
	 * 
	 * response.setContentType(contentType);
	 * response.setHeader("Content-Disposition", "attachment; filename=\"" +
	 * viewUpload.getFileName() + "\""); OutputStream os =
	 * response.getOutputStream(); os.write(fileData); os.close();
	 * 
	 * 
	 * }
	 * 
	 * } catch (Exception e) {
	 * 
	 * } }
	 */

	@RequestMapping(value = "/saveBankMandateMliChecker", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> savebankMandateFormMliCheckerData(
			@RequestParam("objField") String objString, HttpSession session) throws BusinessException {
		BankMandateFormBo bo = null;
		boolean isSuccess = false;
		try {
			Gson gson = new Gson();
			bo = gson.fromJson(objString, BankMandateFormBo.class);

			if (bo != null) {
				if(bo.getApproveStatus().equalsIgnoreCase("Approve")) {
					bo.setApproveStatus(ApprovalStatus.SUBMITTED_TO_CGTMSE.getValue());
					msg="Bank Mandate forwarded to CGTMSE for Approval.";
				}else if(bo.getApproveStatus().equalsIgnoreCase("Return")) {
					bo.setApproveStatus(ApprovalStatus.REJECTED_BY_CHECKER.getValue());
					msg="Returned to MLI Maker.";
					
				}
				isSuccess = bankMandateFormService.saveBankMandateFormDataMliChecker(bo);
				if (isSuccess) {
					return new ResponseEntity<>(msg, HttpStatus.OK);
				}

			}
		} catch (Exception e) {
			logger.error(e.getMessage());

		}
		return new ResponseEntity<>("Something Went Wrong.", HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/bank_MandateFormData_MliStatus", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<BankMandateFormBo> getbankMandateFormDataAfter(HttpSession session)
			throws BusinessException {
		BankMandateFormBo bo = null;
		try {

			if (userDetails != null) {
				bo=bankMandateFormService.findBankMandateFormStatus(userDetails.getMEM_BNK_ID
						() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID(), userId);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new BankMandateFormBo(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(bo, HttpStatus.OK);
	}
}
