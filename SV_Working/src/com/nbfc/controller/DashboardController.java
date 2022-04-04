package com.nbfc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nbfc.bean.MLIMakerFileUploadBean;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.LoginService;
import com.nbfc.service.UserActivityService;
@Controller
public class DashboardController {
	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	MenuUtilsController menuUtilsController;
	
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;
	
	@RequestMapping(value = "/dashboardController", method = RequestMethod.GET)
	public ModelAndView uploadCleanup(
			@ModelAttribute("command") MLIMakerFileUploadBean employeeBean,
			BindingResult result, Model modFr, HttpServletRequest request,
			HttpSession session) throws Exception {
		
		Map<String, Object> model = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		String Role = (String) session.getAttribute("uRole");
		if (userId != null) {
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
				return new ModelAndView("dashBoard", model);
			} else {
				return new ModelAndView("redirect:/SVLogin.html");
			}
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}
}
