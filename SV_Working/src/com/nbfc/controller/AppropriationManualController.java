package com.nbfc.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nbfc.bean.AppropriationManualBO;
import com.nbfc.bean.ClaimPaymentBO;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.Login;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserActivity;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.AppropriationManualService;
import com.nbfc.service.ClaimPaymentService;
import com.nbfc.service.LoginService;
import com.nbfc.service.UserActivityService;

@Controller
public class AppropriationManualController {

	final static Logger logger = Logger.getLogger(AppropriationManualController.class.getName());

	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	AppropriationManualService appropriationManualService;
	@Autowired
	ClaimPaymentService claimPaymentService;
	@Autowired
	MenuUtilsController menuUtilsController;

	String userId = null;
	Login userDetails = null;

	/* umesh code */
	HttpSession session1 = null;
	List<ClaimPaymentBO> claimPaymentList = null;

	@RequestMapping(value = "/appropriation-Manual", method = RequestMethod.GET)
	public ModelAndView appropriationManual(Model modeluserRole, HttpSession session) throws BusinessException {
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
				session.setAttribute("userNameId",
						userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID());
				model = menuUtilsController.prepareMenu(session, Role, userId);
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
					return new ModelAndView("appropriationManual", model);
				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}
	
	
	@RequestMapping(value = "/mli-list-manual", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<ClaimPaymentBO>> getMliList() throws BusinessException {
		List<ClaimPaymentBO> mliList = null;
		try {
			String mliId=userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID();
			mliList = claimPaymentService.getMliList(mliId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(mliList, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(mliList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search-appropriation", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<List<AppropriationManualBO>> getAll(@RequestBody AppropriationManualBO bo)
			throws BusinessException {
		List<AppropriationManualBO> all = null;
		try {
			all = appropriationManualService.getAll(bo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(all, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(all, HttpStatus.OK);
	}

	@RequestMapping(value = "/save-appropriation", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> saveUpdate(@RequestParam("obj") String objString)
			throws BusinessException {
		boolean isSuccess = false;
		String msg = "Your record successfully saved!";
		try {
			Type type = new TypeToken<ArrayList<AppropriationManualBO>>() {
			}.getType();
			ArrayList<AppropriationManualBO> listBO = new Gson().fromJson(objString, type);
			if (listBO != null && !listBO.isEmpty()) {
				isSuccess = appropriationManualService.save(listBO);
				if (isSuccess) {
					return new ResponseEntity<>(msg, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<>("Something Went Wrong.", HttpStatus.BAD_REQUEST);
	}

}
