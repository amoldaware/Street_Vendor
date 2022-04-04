package com.nbfc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nbfc.bean.JsonResponse2;
import com.nbfc.bean.MLIDEtailsBean;
import com.nbfc.bean.StateBean;
import com.nbfc.bean.UserBean;
import com.nbfc.bean.UserRoleBean;
import com.nbfc.exception.NBFCException;
import com.nbfc.model.BulkUploadTracker;
import com.nbfc.model.CGTMSEMakerRejectionAndSumbission;
import com.nbfc.model.District;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIInfo;
import com.nbfc.model.MLIMainEditDetails;
import com.nbfc.model.MLIName;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.PortfolioNumber;
import com.nbfc.model.State;
import com.nbfc.model.UseRoleModel;
import com.nbfc.model.User;
import com.nbfc.model.UserInfoModel;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.model.UserRolePrivelage;
import com.nbfc.service.LoginService;
import com.nbfc.service.MLIDetailsService;
import com.nbfc.service.StateService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;
import com.nbfc.validation.EmployeeValidator;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class UserRoleController {
	@Autowired
	LoginService lofinService;
	@Autowired
	StateService stateService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	MLIDetailsService mliDetailsService;
	@Autowired
	private UserService employeeService;
	@Autowired
	EmployeeValidator validator;
	@Autowired
	MenuUtilsController menuUtilsController;

	MLIName mliNameDetails;
	UseRoleModel UserInfo;
	@Value("${downloadFileDirPath}")
	private String downloadFileDirPath;
	@Value("${UserRoledownloadFileName}")
	private String downloadFileName;
	static Logger log = Logger.getLogger(NBFCController.class.getName());

	public static final int BUFFER_SIZE = 4096;

	List<MLIDetails> mliDetailList;

	List<String> userNameList = new ArrayList<String>();
	List<String> MliNameList = new ArrayList<String>();
	List<String> userNamePrvList = new ArrayList<String>();
	List toReturn;

	User userDetails;

	List<UserRolePrivelage> userRolePrivelage;
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;

	@RequestMapping(value = "/newRolePage", method = RequestMethod.GET)
	public ModelAndView newMLIRegistration(
			@ModelAttribute("command") UserRoleBean mliRegistrationBean,
			HttpServletRequest request, HttpSession session, Model modelMsg)
			throws NBFCException {

		Map<String, Object> model = new HashMap<String, Object>();
		// model.put("stateList", "");
		// model.put("reatingAGNList", "");
		String userId = (String) session.getAttribute("userId");

		if (userId.equals("CGTMSE ADMIN")) {

		} else {
			if (userId != null) {
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst = lofinService
						.getPrivlageMstDtl(userPri.getPrv_id());
				if (userPrvMst.getPrvCreatedModifiedBy().equals("ADMIN")) {

					model.put(
							"employees",
							prepareListofBean(
									employeeService.getUserPrivlageDetails(),
									employeeService));
					// Added by say 6 Feb
					model.put("adminlist", userActivityService.getActivity(
							"ADMIN", "System_Admin"));
					// modelAct.put("actList", userActivityService.getActivity(
					// "ADMIN", "User_Activity"));//commented by Say 6 Feb
					model.put("actName", userActivityService.getActivityName(
							"ADMIN", "newRolePage"));// Added by say 25june19
					model.put("homePage", "adminHome");
					model.put("mlisList",
							prepareMLIListofBean(mliDetailsService
									.getApprovedMLIDetails("CCA")));
					return new ModelAndView("newRolePage", model);
				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		}

		// Added by say 6 Feb
		model.put("adminlist",
				userActivityService.getActivity("ADMIN", "System_Admin"));
		// modelAct.put("actList", userActivityService.getActivity(
		// "ADMIN", "User_Activity"));//commented by Say 6 Feb
		model.put("homePage", "adminHome");
		return new ModelAndView("newRolePage", model);

	}

	@RequestMapping(value = "/saveNewMLIRoles", params = "submit", method = RequestMethod.POST)
	public ModelAndView saveNewMLIRoleDetails(
			@ModelAttribute("command") UserRoleBean mliRegistrationBean,
			BindingResult result, HttpServletRequest request,
			HttpSession session, Model modelMsg) throws Exception {
		System.out.println("submit");
		String mliId = null;
		/*
		 * 
		 * Map<String, Object> model = new HashMap<String, Object>(); //
		 * model.put("stateList", ""); // model.put("reatingAGNList", "");
		 * String userId = (String) session.getAttribute("userId"); if
		 * (userId.equals("CGTMSE ADMIN")) {
		 * 
		 * } else { if (userId != null) { userPri =
		 * lofinService.getUserPrivlageDtl(userId, "A"); userPrvMst =
		 * lofinService .getPrivlageMstDtl(userPri.getPrv_id()); if (userPrvMst
		 * .getPrvCreatedModifiedBy().equals("ADMIN")) {
		 * 
		 * } else { return new ModelAndView("redirect:/SVLogin.html"); } } }
		 * userActivityService .saveUserRoles(prepareMLIRoleModel
		 * (mliRegistrationBean,userActivityService)); model.put( "employees",
		 * prepareListofBean(employeeService .getUserPrivlageDetails(),
		 * employeeService)); model.put("actList",
		 * userActivityService.getActivity("ADMIN", "User_Activity"));
		 * model.put("homePage", "adminHome"); model.put("mlisList",
		 * prepareMLIListofBean(mliDetailsService
		 * .getApprovedMLIDetails("CCA"))); modelMsg.addAttribute("message",
		 * "Role Successfully Assigned"); return new ModelAndView("newRolePage",
		 * model);
		 */

		Map<String, Object> model = new HashMap<String, Object>();
		// model.put("stateList", "");
		// model.put("reatingAGNList", "");
		String userId = (String) session.getAttribute("userId");
		String Mliname = mliRegistrationBean.getMliName();
		String Userrole = mliRegistrationBean.getRoleName();
		String userName = mliRegistrationBean.getUserName();
		// -------Added by say 22-11-018--------------------------------
		validator.mliCreateRoleValidator(mliRegistrationBean, result);
		if (result.hasErrors()) {
			// log.info("Error in field*******************************************************************************************************************");
			System.out
					.println("Error in field-----------------------------------");
			model.put(
					"employees",
					prepareListofBean(employeeService.getUserPrivlageDetails(),
							employeeService));
			// Added by say 6 Feb
			model.put("adminlist",
					userActivityService.getActivity("ADMIN", "System_Admin"));
			// modelAct.put("actList", userActivityService.getActivity(
			// "ADMIN", "User_Activity"));//commented by Say 6 Feb
			model.put("actName",
					userActivityService.getActivityName("ADMIN", "newRolePage"));// Added
																					// by
																					// say
																					// 25june19
			model.put("homePage", "adminHome");
			model.put("mlisList", prepareMLIListofBean(mliDetailsService
					.getApprovedMLIDetails("CCA")));
			return new ModelAndView("newRolePage", model);

		}
		// ended------------------------------------------------------------------------------------------------
		if (!Mliname.isEmpty()) {
			mliNameDetails = userActivityService.getBankID(Mliname);
		}
		if (mliNameDetails != null) {

			mliId = mliNameDetails.getBnkId() + mliNameDetails.getZneID()
					+ mliNameDetails.getBrnName();

			int count = userActivityService.checkExistingUserRole(mliId,
					Userrole, userName);

			if (userId.equals("CGTMSE ADMIN")) {

			} else {
				if (userId != null) {
					userPri = lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst = lofinService.getPrivlageMstDtl(userPri
							.getPrv_id());
					if (userPrvMst.getPrvCreatedModifiedBy().equals("ADMIN")) {

					} else {
						return new ModelAndView("redirect:/SVLogin.html");
					}
				}
			}

			if (count == 0) {
				userActivityService.saveUserRoles(prepareMLIRoleModel(
						mliRegistrationBean, userActivityService));
				modelMsg.addAttribute("message", "Role Successfully Assigned");
			} else if (count == 1) {
				modelMsg.addAttribute("Errormessage",
						"This userType is Already assigned for " + Mliname
								+ " ");

			} else if (count == 2) {

				if (Userrole.equalsIgnoreCase("Maker")) {
					modelMsg.addAttribute("Errormessage",
							"This user is Already assigned with Checker ");
				} else {
					modelMsg.addAttribute("Errormessage",
							"This user is Already assigned with Maker ");
				}

			}

		}

		/*
		 * else if (count == 4) { modelMsg.addAttribute("message",
		 * "This user is Already assigned with Maker ");
		 * 
		 * }else if (count == 5) { modelMsg.addAttribute("message",
		 * "This user is Already assigned with Checker ");
		 * 
		 * }else if(count==6){ modelMsg.addAttribute("message",
		 * "This userType is Already assigned"); }
		 */

		/*
		 * if(Userrole.equals("CK")){ int countOfChecker=0;
		 * if(mliNameDetails!=null){
		 * countOfChecker=userActivityService.verifychkCnt
		 * (mliNameDetails.getBnkId()); } System.out.println("count of checker"
		 * +countOfChecker); if(countOfChecker<1){
		 * userActivityService.saveUserRoles
		 * (prepareMLIRoleModel(mliRegistrationBean,userActivityService));
		 * modelMsg.addAttribute("message", "Role Successfully Assigned");
		 * }else{ modelMsg.addAttribute("message",
		 * "Checker Is Already Exists For " +Mliname+" "); } }else
		 * if(Userrole.equals("MK")){ int countOfMaker=0;
		 * if(mliNameDetails!=null){
		 * countOfMaker=userActivityService.verifymkCnt
		 * (mliNameDetails.getBnkId());
		 * 
		 * } //int
		 * countOfMaker=userActivityService.verifymkCnt(mliNameDetails.getBnkId
		 * ()); System.out.println("count of checker" +countOfMaker);
		 * if(countOfMaker<1){
		 * userActivityService.saveUserRoles(prepareMLIRoleModel
		 * (mliRegistrationBean,userActivityService));
		 * modelMsg.addAttribute("message", "Role Successfully Assigned");
		 * }else{ modelMsg.addAttribute("message", "Maker Is Already Exists For"
		 * +Mliname+" "); } }
		 */
		model.put(
				"employees",
				prepareListofBean(employeeService.getUserPrivlageDetails(),
						employeeService));
		// Added by say 6 Feb
		model.put("adminlist",
				userActivityService.getActivity("ADMIN", "System_Admin"));
		// modelAct.put("actList", userActivityService.getActivity(
		// "ADMIN", "User_Activity"));//commented by Say 6 Feb
		model.put("actName",
				userActivityService.getActivityName("ADMIN", "newRolePage"));// Added
																				// by
																				// say
																				// 25june19
		model.put("homePage", "adminHome");
		model.put("mlisList", prepareMLIListofBean(mliDetailsService
				.getApprovedMLIDetails("CCA")));
		return new ModelAndView("newRolePage", model);

	}

	// -------Added by say 22-11-018--------------------------------
	@RequestMapping(value = "/Reset", method = RequestMethod.POST)
	public ModelAndView saveNewMLIRoleResetDetails(
			@ModelAttribute("command") UserRoleBean mliRegistrationBean,
			HttpServletRequest request, HttpSession session, Model modelMsg)
			throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		String Mliname = mliRegistrationBean.getMliName();
		if (!Mliname.isEmpty()) {
			mliNameDetails = userActivityService.getBankID(Mliname);
		}
		if (userId.equals("CGTMSE ADMIN")) {
		} else {
			if (userId != null) {
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst = lofinService
						.getPrivlageMstDtl(userPri.getPrv_id());
				if (userPrvMst.getPrvCreatedModifiedBy().equals("ADMIN")) {
				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		}
		model.put(
				"employees",
				prepareListofBean(employeeService.getUserPrivlageDetails(),
						employeeService));
		// Added by say 6 Feb
		model.put("adminlist",
				userActivityService.getActivity("ADMIN", "System_Admin"));
		// modelAct.put("actList", userActivityService.getActivity(
		// "ADMIN", "User_Activity"));//commented by Say 6 Feb
		model.put("homePage", "adminHome");
		model.put("actName",
				userActivityService.getActivityName("ADMIN", "newRolePage"));// Added
																				// by
																				// say
																				// 25june19
		model.put("mlisList", prepareMLIListofBean(mliDetailsService
				.getApprovedMLIDetails("CCA")));

		return new ModelAndView("redirect:/newRolePage.html");

	}

	// //end-----------------------------------------------------------------------------------
	// -------Added by say 22-11-018--------------------------------
	@RequestMapping(value = "/updateUserRoleDetails", params = "exit", method = RequestMethod.POST)
	public ModelAndView exitDetails(

	@ModelAttribute("command") UserBean mliRegistrationBean,
			BindingResult result, HttpSession session, Model modelReg)
			throws ParseException {
		Map<String, Object> model = new HashMap<String, Object>();
		// model.put("stateList", "");
		// // model.put("reatingAGNList", "");
		String userId = (String) session.getAttribute("userId");
		System.out.println("updateUserRoleDetails controller exit param");
		// Added by say 6 Feb
		model.put("adminlist",
				userActivityService.getActivity("ADMIN", "System_Admin"));
		// modelAct.put("actList", userActivityService.getActivity(
		// "ADMIN", "User_Activity"));//commented by Say 6 Feb
		model.put("actName",
				userActivityService.getActivityName("ADMIN", "newRolePage"));// Added
																				// by
																				// say
																				// 25june19
		model.put("homePage", "adminHome");
		model.put("mlisList", prepareMLIListofBean(mliDetailsService
				.getApprovedMLIDetails("CCA")));
		return new ModelAndView("redirect:/add.html");
		// } else {
		// return new ModelAndView("redirect:/SVLogin.html");
		// }
		//
	}

	// Added by Say
	// 13/2/2019------------------------------------------------------------------------
	@RequestMapping(value = "/updateUserRoleDetails", method = RequestMethod.POST)
	public ModelAndView editUserDetails(

	@ModelAttribute("command") UserBean mliRegistrationBean,
			BindingResult result, HttpSession session, Model modelMsg)
			throws ParseException {

		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("updateUserRoleDetails UserRoleController");
		validator.validate(mliRegistrationBean, result);

		if (result.hasErrors()) {
			log.info("Error in field*******************************************************************************************************************");
			String utype = mliRegistrationBean.getUserType();

			List<String> UserTypelist = new ArrayList<String>();
		
			UserTypelist.add(0, "Maker");
			UserTypelist.add(1, "Checker");
			model.put("UserTypelist1", UserTypelist);
			model.put("guaranteelist",
					userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			model.put("applicationList",
					userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			model.put("RPaymentList",
					userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		
			model.put("actName", userActivityService.getActivityName("CGTMSEMAKER","add"));// Added by Say 31 Jan19
			model.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));	
			model.put("homePage", "cgtmseMakerHome");

			return new ModelAndView("newRoleEditPage", model);
		}
		// int countOfEmailid=employeeService.userEmailIDCount(Email);

		String Email = mliRegistrationBean.getEmail();
		String RoleName = mliRegistrationBean.getUserType();
		String userName = mliRegistrationBean.getUserID();
		String mliName = mliRegistrationBean.getMliName();
		MLIInfo mliInfo = stateService.userInfo(mliName);
		String mliId = mliInfo.getMEM_BNK_ID() + mliInfo.getMEM_ZNE_ID()
				+ mliInfo.getMEM_BRN_ID();
		int count = 0;
				//userActivityService.checkExistingUserRoleforModify(mliId,
				//RoleName, userName);
		if (count == 1) {
			// added by say 6 feb-----------------------
			model.put("guaranteelist",
					userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			model.put("applicationList",
					userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			model.put("RPaymentList",
					userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		
			model.put("actName", userActivityService.getActivityName("CGTMSEMAKER","add"));// Added by Say 31 Jan19
			model.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));	
			model.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSEMAKER", "Guarantee_Maintenance"));
			model.put("homePage", "cgtmseMakerHome");
			modelMsg.addAttribute("Errormessage",
					"This userType is Already assigned for " + mliName + " ");
			return new ModelAndView("newRoleEditPage", model);
		} else {
			String userId = mliRegistrationBean.getUserID();
			UserInfoModel userInfo = prepareEntityForUserInfoDetails(mliRegistrationBean);

			employeeService.updateUserInfo(userInfo);
			int prvId = 0 ;
			if(RoleName.equalsIgnoreCase("Checker")){
				prvId = 15;
			}
			else if (RoleName.equalsIgnoreCase("Maker")){
				prvId = 14;
			}
			employeeService.updateUserPrivlige(userId,prvId);
			//model.put("mliDetail", userInfo);
			modelMsg.addAttribute("message", "User Details Successfully updated...");
			
			// added by say 6 feb-----------------------
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
			model.put("actName", userActivityService.getActivityName("CGTMSEMAKER","add"));// Added by Say 31 Jan19

			model.put("homePage", "cgtmseMakerHome");
			return new ModelAndView("newRoleEditPage", model);
		}
		// String userId = (String) session.getAttribute("userId");
		
		// }
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

	private Object prepareListofBeanUser(List<User> employees,
			UserService userService) {
		List<UserBean> beans = null;
		int count = 0;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<UserBean>();
			UserBean bean = null;
			for (User employee : employees) {
				bean = new UserBean();
				try {

					bean.setUserID(employee.getUsr_id());
					System.out.println("BNK ID count--" + count + " "
							+ employee.getUsr_id());
					bean.setfName(employee.getfName());
					System.out.println("Fname count--" + count + " "
							+ employee.getfName());
					bean.setMiddalName(employee.getmName());
					bean.setlName(employee.getlName());
					System.out.println("Email count--" + count + " "
							+ employee.getEmail());
					bean.setEmail(employee.getEmail());
					bean.setPhoneNumber(employee.getPhoneNumber());
					bean.setMobileNumber(employee.getMobileNumber());
					bean.setPhoneCode(employee.getPhone_code());
					bean.setUserType(employee.getUserType());
					MLIName MLIName = userService.getMLIName(employee
							.getMem_bnk_id());
					bean.setMliName(MLIName.getMliLName());
					System.out.println("BNK ID" + employee.getMem_bnk_id());
					beans.add(bean);
					count++;
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		}
		return beans;
	}

	private UserInfoModel prepareEntityForUserInfoDetails(UserBean userBean) {
		UserInfoModel ubean = new UserInfoModel();

		ubean.setUsr_id(userBean.getUserID());
		ubean.setfName(userBean.getfName());
		ubean.setEmail(userBean.getEmail());
		ubean.setlName(userBean.getlName());
		ubean.setUserType(userBean.getUserType());
		ubean.setPhone_code(userBean.getPhoneCode());
		ubean.setPhoneNumber(userBean.getPhoneNumber());
		ubean.setMobileNumber(userBean.getMobileNumber());
		ubean.setHint_question(userBean.getHint_question());
		ubean.setHint_ans(userBean.getHint_ans());
		ubean.setIsActive(userBean.getIsActive());
		return ubean;
	}

	// end--------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/getUserDetailsForEdit", method = RequestMethod.GET)
	public ModelAndView getPortfolioDetails(
			@ModelAttribute(value = "command") UserBean mliRegistrationBean,
			BindingResult result, String userId,HttpSession session) throws NullPointerException,
			Exception {
		System.out.println("User Id  " + userId);
		String Role = (String) session.getAttribute("uRole");
		List<String> UserTypelist = new ArrayList<String>();
		UserTypelist.add(0, "Maker");
		UserTypelist.add(1, "Checker");

		Map<String, Object> model = new HashMap<String, Object>();
		model = menuUtilsController.prepareMenu(session, Role, userId);
		model.put(
				"mliDetail",
				prepareBeanForEditDetails(
						employeeService.getUserPrivlageDetailsForEdit(userId),
						employeeService));
		model.put("UserTypelist1", UserTypelist);

		// Added by say 6 Feb
		/*model.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));	
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		model.put("actName", userActivityService.getActivityName("CGTMSEMAKER","add"));// Added by Say 31 Jan19

		model.put("homePage", "cgtmseMakerHome");*/
		return new ModelAndView("newRoleEditPage", model);
	}

	// @RequestMapping(value = "/updateUserRoleDetails", method =
	// RequestMethod.GET)
	// public ModelAndView editUserDetails(
	// @ModelAttribute(value = "command") UserBean mliRegistrationBean,
	// BindingResult result, String userId) throws NullPointerException,
	// Exception {
	// System.out.println("User Id  " + userId);
	// employeeService.updateUserPrivlage(prepareEntityForUserRoleDetails(
	// employeeService
	// .getUserPrivlageDetailsForEdit(mliRegistrationBean
	// .getUserID()), mliRegistrationBean));
	// System.out.println("Update User-----------------------------------");
	//
	// prepareEntityForUserRoleDetails(
	// employeeService.getUserPrivlageDetailsForEdit(mliRegistrationBean
	// .getUserID()), mliRegistrationBean);
	// employeeService.getUserPrivlageDetailsForEdit(mliRegistrationBean
	// .getUserID());
	//
	// Map<String, Object> model = new HashMap<String, Object>();
	// model.put(
	// "mliDetail",
	// prepareBeanForEditDetails(
	// employeeService.getUserPrivlageDetailsForEdit(userId),
	// employeeService));
	// //Added by say 6 Feb
	// model.put("adminlist",
	// userActivityService.getActivity("ADMIN", "System_Admin"));
	// // modelAct.put("actList", userActivityService.getActivity(
	// // "ADMIN", "User_Activity"));//commented by Say 6 Feb
	// model.put("actName",
	// userActivityService.getActivityName("ADMIN", "newRolePage"));// Added
	// // by
	// // say
	// // 25june19
	// model.put("homePage", "adminHome");
	// return new ModelAndView("newRoleEditPage", model);
	// }

	@RequestMapping(value = "/getUserName", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse2 getQuarterNumber(
			@ModelAttribute(value = "mliName") PortfolioNumber portfolioNumInfo,
			BindingResult result, String mliName) throws ParseException {
		JsonResponse2 res = new JsonResponse2();
		System.out.println("mliName " + mliName);
		userNameList.clear();
		userNamePrvList.clear();
		mliNameDetails = userActivityService.getBankID(mliName);
		System.out.println("mliNameDetails : " + mliNameDetails);
		mliDetailList = userActivityService.getUserName(mliNameDetails
				.getBnkId());
		System.out.println("mliDetailList " + mliDetailList);

		for (MLIDetails lis : mliDetailList) {
			if (lis.getUser_id() != null) {

				userNameList.add(lis.getUser_id());
				System.out.println(lis.getUser_id());
			}
		}
		System.out.println("userNameList  " + userNameList);

		/*
		 * userRolePrivelage = userActivityService.getUserPrvDetails(); for
		 * (UserRolePrivelage rolePrivelage : userRolePrivelage) { if
		 * (rolePrivelage.getUser_id() != null) {
		 * userNamePrvList.add(rolePrivelage.getUser_id());
		 * 
		 * } }
		 */
		List toReturn = new ArrayList(userNameList);
		// toReturn.removeAll(userNamePrvList);
		// System.out.println(toReturn);
		res.setStatus("SUCCESS");
		res.setResult(toReturn);
		return res;
	}

	// ----------Added by Say 25jan19-------------------------------------
	@RequestMapping(value = "/getMliName", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse2 getMliName(
			@ModelAttribute(value = "mliName") PortfolioNumber portfolioNumInfo,
			BindingResult result, String val) throws ParseException {
		JsonResponse2 res = new JsonResponse2();
		System.out.println("val " + val);
		MliNameList.clear();
		// userNamePrvList.clear();
		List<MLIRegistration> mliList;
		mliList = userActivityService.getMliLongName(val);
		System.out.println("mliList " + mliList.size());

		for (MLIRegistration lis : mliList) {
			if (lis.getLongName() != null) {

				MliNameList.add(lis.getLongName());
				System.out.println(lis.getLongName());
			}
		}
		System.out.println("MliNameList  " + MliNameList);

		/*
		 * userRolePrivelage = userActivityService.getUserPrvDetails(); for
		 * (UserRolePrivelage rolePrivelage : userRolePrivelage) { if
		 * (rolePrivelage.getUser_id() != null) {
		 * userNamePrvList.add(rolePrivelage.getUser_id());
		 * 
		 * } }
		 */
		List toReturn = new ArrayList(MliNameList);
		// toReturn.removeAll(userNamePrvList);
		// System.out.println(toReturn);
		res.setStatus("SUCCESS");
		res.setResult(toReturn);
		return res;
	}

	private List<MLIDEtailsBean> prepareMLIListofBean(
			List<MLIMainEditDetails> employees) {
		List<MLIDEtailsBean> beans = null;
		MLIDEtailsBean bean = null;
		beans = new ArrayList<MLIDEtailsBean>();
		if (employees != null && !employees.isEmpty()) {

			for (MLIMainEditDetails employee : employees) {
				bean = new MLIDEtailsBean();
				bean.setMliLongName(employee.getLongName());
				bean.setShortName(employee.getShortName());
				bean.setCompanyAddress(employee.getCompanyAddress());
				bean.setCity(employee.getCity());
				bean.setCompanyPAN(employee.getCompanyPAN());
				bean.setStatus(employee.getStatus());
				beans.add(bean);
			}
		}
		// added by say 17 jan 2019
		else {
			bean = new MLIDEtailsBean();
			bean.setMliLongName("");
			bean.setShortName("");
			bean.setCompanyAddress("");
			bean.setCity("");
			bean.setCompanyPAN("");
			bean.setStatus("");
			beans.add(bean);
		}
		// end-----------------------------
		return beans;
	}

	private UserRolePrivelage prepareMLIRoleModel(UserRoleBean userRoleBean,
			UserActivityService userActivityService) throws Exception {

		UserRolePrivelage userRolePrivelage = new UserRolePrivelage();

		userRolePrivelage.setUser_id(userRoleBean.getUserName());
		userRolePrivelage.setUpr_flag("A");
		if (userRoleBean.getRoleName().equals("Checker")) {
			userRolePrivelage.setPrv_id(15);
		} else if (userRoleBean.getRoleName().equals("Maker")) {
			userRolePrivelage.setPrv_id(14);
		}

		if ((userRoleBean.getRoleName() != "" && userRoleBean.getUserName() != "")
				|| (userRoleBean.getRoleName() != null && userRoleBean
						.getUserName() != null)) {
			userActivityService.setUserType(userRoleBean.getRoleName(),
					userRoleBean.getUserName());
		}
		if (userActivityService.getMaxNumber() == 0) {

			userRolePrivelage.setS_no(1);

		} else {

			userRolePrivelage.setS_no(userActivityService.getMaxNumber() + 1);
		}

		return userRolePrivelage;
	}

	private List<UserRoleBean> prepareListofBean(
			List<UserPerivilegeDetails> employees, UserService userService) {
		List<UserRoleBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<UserRoleBean>();
			UserRoleBean bean = null;
			for (UserPerivilegeDetails employee : employees) {
				bean = new UserRoleBean();
				try {
					userDetails = userService.getUserDetails(employee
							.getUser_id());
					System.out.println("userDetails.getUsr_id():"
							+ userDetails.getUsr_id());
					bean.setUserID(userDetails.getUsr_id());
					bean.setfName(userDetails.getfName());
					bean.setMiddalName(userDetails.getmName());
					bean.setlName(userDetails.getlName());
					bean.setEmail(userDetails.getEmail());
					bean.setPhoneNumber(userDetails.getPhoneNumber());
					bean.setMobileNumber(userDetails.getMobileNumber());
					bean.setUserType(userDetails.getUserType());
					if (employee.getUpr_flag().equals("A")) {
						bean.setStatus("Active");
					} else {

						bean.setStatus("Deactive");
					}

					MLIName MLIName = userService.getMLIName(userDetails
							.getMem_bnk_id());
					bean.setMliName(MLIName.getMliLName());
					System.out.println("BNK ID" + userDetails.getMem_bnk_id());
					beans.add(bean);
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		}
		return beans;
	}

	private UserBean prepareBeanForEditDetails(UserPerivilegeDetails employees,
			UserService userService) {
		UserBean bean = new UserBean();

		try {
			userDetails = userService.getUserDetails(employees.getUser_id());
			bean.setUserID(userDetails.getUsr_id());
			bean.setfName(userDetails.getfName());
			bean.setMiddalName(userDetails.getmName());
			bean.setlName(userDetails.getlName());
			bean.setEmail(userDetails.getEmail());
			bean.setPhoneNumber(userDetails.getPhoneNumber());
			bean.setPhoneCode(userDetails.getPhone_code());
			bean.setMobileNumber(userDetails.getMobileNumber());
			//bean.setHint_question(userDetails.getHint_question());
			//bean.setHint_ans(userDetails.getHint_ans());
			bean.setIsActive(userDetails.getIsActive());
			
			bean.setUserType(userDetails.getUserType());
			if (employees.getUpr_flag().equals("A")) {
				bean.setStatus("Active");
			} else {

				bean.setStatus("Deactive");
			}

			MLIName MLIName = userService.getMLIName(userDetails
					.getMem_bnk_id());
			bean.setMliName(MLIName.getMliLName());
			System.out.println("BNK ID" + userDetails.getMem_bnk_id());

		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return bean;
	}

	private UserPerivilegeDetails prepareEntityForUserRoleDetails(
			UserPerivilegeDetails employees, UserBean userBean) {
		UserPerivilegeDetails bean = new UserPerivilegeDetails();

		bean.setPrv_id(employees.getPrv_id());
		if (userBean.getStatus().equals("Active")) {
			bean.setUpr_flag("A");
		} else {
			bean.setUpr_flag("B");
		}

		bean.setUser_id(employees.getUser_id());
		bean.setsNo(employees.getsNo());

		return bean;
	}

	// For DownLoadExcel File

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/userRoleCreationdownLoad", method = RequestMethod.GET)
	public ModelAndView userRoleCreationdownLoad(
			@ModelAttribute("command") User userDetails, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException {
		try {
			downloadFileDirPath = request.getRealPath("/")+"/Download";
			String filePath = downloadFileDirPath + File.separator
					+ downloadFileName;
			System.out.println("Request Path 1:"+downloadFileDirPath);
			File file = new File(downloadFileDirPath);
			boolean isCreated = file.mkdir();

			if (isCreated) {
				File file1 = new File(filePath);
				boolean isExists = file1.exists();
				if (isExists) {

				} else {
					file1.createNewFile();
				}

			}

			List<User> list = employeeService.listEmployeess();

			// List<UserPerivilegeDetails>
			// list=employeeService.getUserPrivlageDetails();
			log.info("list size==" + list.size());
			log.info("list Data==" + list);

			// Writing and Downlaoding Excel File

			XSSFWorkbook hwb = new XSSFWorkbook();
			XSSFSheet sheet = hwb.createSheet("UserRoleDownLoadedFile");

			// Making bold and color to excel column heading
			CellStyle style = hwb.createCellStyle();
			Font font = hwb.createFont();
			font.setFontHeightInPoints((short) 11);
			font.setFontName(HSSFFont.FONT_ARIAL);
			font.setBoldweight(HSSFFont.COLOR_NORMAL);
			font.setBold(true);
			font.setColor(HSSFColor.DARK_BLUE.index);
			style.setFont(font);
			style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			sheet.createFreezePane(0, 1); // Freeze 1st Row
											// sheet.createFreezePane(int
											// colSplit, int rowSplit, int
											// leftmostColumn, int topRow)

			// Creating First rows for excel heading
			XSSFRow rowhead = sheet.createRow((short) 0);

			XSSFCell cell0 = rowhead.createCell(0);
			cell0.setCellStyle(style);
			cell0.setCellValue("First Name");// Done 1

			/*
			 * XSSFCell cell1 = rowhead.createCell(1);
			 * cell1.setCellStyle(style);
			 * cell1.setCellValue("Business Product");//Done 2
			 */
			XSSFCell cell1 = rowhead.createCell(1);
			cell1.setCellStyle(style);
			cell1.setCellValue("Middle Name");// Done 3

			XSSFCell cell2 = rowhead.createCell(2);
			cell2.setCellStyle(style);
			cell2.setCellValue("Last Name");// Done 4

			XSSFCell cell3 = rowhead.createCell(3);
			cell3.setCellStyle(style);
			cell3.setCellValue("User ID");// Done 5

			XSSFCell cell4 = rowhead.createCell(4);
			cell4.setCellStyle(style);
			cell4.setCellValue("MLI Name");// Done 6

			XSSFCell cell5 = rowhead.createCell(5);
			cell5.setCellStyle(style);
			cell5.setCellValue("Mobile No");// Done 7

			/*
			 * XSSFCell cell7 = rowhead.createCell(7);
			 * cell7.setCellStyle(style);
			 * cell7.setCellValue("Whether Disbursed/Not");//Done 8
			 */
			XSSFCell cell6 = rowhead.createCell(6);
			cell6.setCellStyle(style);
			cell6.setCellValue("Phone No");// Done 9

			XSSFCell cell7 = rowhead.createCell(7);
			cell7.setCellStyle(style);
			cell7.setCellValue("Email ID");// Done 10

			XSSFCell cell8 = rowhead.createCell(8);
			cell8.setCellStyle(style);
			cell8.setCellValue("User Type");
			//
			// XSSFCell cell9 = rowhead.createCell(9);
			// cell9.setCellStyle(style);
			// cell9.setCellValue("Status");
			//

			int index = 1;
			int sno = 0;
			Iterator<User> itr2 = list.iterator();
			while (itr2.hasNext()) {
				User obj1 = (User) itr2.next();
				sno++;
				XSSFRow row = sheet.createRow((short) index);
				// System.out.println("usetid-----" +obj1.getUser_id());
				// userDetails =
				// employeeService.getUserDetails(obj1.getUser_id());
				// System.out.println("id-----" +userDetails.getMem_bnk_id());
				MLIName MLIName = employeeService.getMLIName(obj1
						.getMem_bnk_id());

				row.createCell((short) 0).setCellValue(obj1.getfName());// Done
																		// 1
				row.createCell((short) 1).setCellValue(obj1.getmName());// Done
																		// 2
				row.createCell((short) 2).setCellValue(obj1.getlName());// Done
																		// 3
				row.createCell((short) 3).setCellValue(obj1.getUsr_id());// Done
																			// 4
				row.createCell((short) 4).setCellValue(MLIName.getMliLName());// Done
																				// 5
				row.createCell((short) 5).setCellValue(obj1.getMobileNumber());// Done
																				// 6
				row.createCell((short) 6).setCellValue(obj1.getPhoneNumber());// Done
																				// 7
				// row.createCell((short)
				// 7).setCellValue(obj1.getDisbursmentStatus());//Done 8
				row.createCell((short) 7).setCellValue(obj1.getEmail());// Done
																		// 9
				row.createCell((short) 8).setCellValue(obj1.getUserType());// Done
																			// 10
				// if (obj1.getUpr_flag().equals("A")) {
				// row.createCell((short) 9).setCellValue("Active");//Done 11
				// } else {
				// row.createCell((short) 9).setCellValue("Deactive");//Done 11
				//
				// }

				//

				index++;
			}
			FileOutputStream fileOut = new FileOutputStream(filePath);
			hwb.write(fileOut);
			fileOut.close();

			ModelAndView model = new ModelAndView("newRolePage");

			model.addObject(
					"excelFileDownLoadSuccessfully",
					"File DownLoaded Successfully in this location F:/ExcelReports/nbfcUserRoleExcelFile.xls.");

			File downloadFile = new File(filePath);
			log.info("downloadFile =" + downloadFile);
			FileInputStream inputStream = new FileInputStream(downloadFile);

			response.setContentLength((int) downloadFile.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					downloadFile.getName());
			response.setHeader(headerKey, headerValue);

			// get output stream of the response
			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
			return model;
		} catch (Exception e) {
			log.info("Exception == " + e);
			System.out.println("Exception == " + e);
		}
		return null;
	}

	@ExceptionHandler(CustomExceptionHandler.class)
	public ModelAndView handleCustomException(CustomExceptionHandler ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		// Added by say 6 Feb
		map.put("adminlist",
				userActivityService.getActivity("ADMIN", "System_Admin"));
		// modelAct.put("actList", userActivityService.getActivity(
		// "ADMIN", "User_Activity"));//commented by Say 6 Feb
		map.put("homePage", "adminHome");
		ModelAndView model = new ModelAndView("customException", map);
		model.addObject("customException", ex.getMessage());
		return model;

	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		// Added by say 6 Feb
		map.put("adminlist",
				userActivityService.getActivity("ADMIN", "System_Admin"));
		// modelAct.put("actList", userActivityService.getActivity(
		// "ADMIN", "User_Activity"));//commented by Say 6 Feb
		map.put("homePage", "adminHome");
		ModelAndView model = new ModelAndView("exception", map);
		model.addObject("exception", ex.getMessage());
		return model;

	}

	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithException(ArithmeticException ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		// Added by say 6 Feb
		map.put("adminlist",
				userActivityService.getActivity("ADMIN", "System_Admin"));
		// modelAct.put("actList", userActivityService.getActivity(
		// "ADMIN", "User_Activity"));//commented by Say 6 Feb
		map.put("homePage", "adminHome");
		ModelAndView model = new ModelAndView("exception", map);
		model.addObject("exception", ex.getMessage());
		return model;

	}

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullException(NullPointerException ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		// Added by say 6 Feb
		map.put("adminlist",
				userActivityService.getActivity("ADMIN", "System_Admin"));
		// modelAct.put("actList", userActivityService.getActivity(
		// "ADMIN", "User_Activity"));//commented by Say 6 Feb
		map.put("homePage", "adminHome");
		ModelAndView model = new ModelAndView("exception", map);
		model.addObject("exception", ex.getMessage());
		return model;

	}
	@RequestMapping(value = "/getBulkUploadTrack", method = RequestMethod.GET)
	public ModelAndView getBulkUploadTrack(
			@ModelAttribute("command") BulkUploadTracker tracker, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException {
		Map<String, Object> model = new HashMap<String, Object>();

		String userId = (String) session.getAttribute("userId");
		String Role = (String) session.getAttribute("uRole");
		if (userId != null) {
			userPri = lofinService.getUserPrivlageDtl(userId, "A");
			userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
			model = menuUtilsController.prepareMenu(session, Role, userId);
			if (model!=null) {
				model.put("bulklist",employeeService.getBulkUploadTrack(userId));
				/*model.put("repList", userActivityService.getReport("NBFCMAKER",	"User_Report"));
				// added by say 6 feb-----------------------
				model.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				model.put("guaranteelist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
				model.put("applicationList", userActivityService.getActivity(
						"NBFCMAKER", "Application_Processing"));
				model.put("RPaymentList", userActivityService.getActivity(
						"NBFCMAKER", "Receipt_Payments"));
				model.put("actName", userActivityService.getActivityName(
						"NBFCMAKER", "mliMaker"));// Added by say 25june19
				model.put("repList",
						userActivityService.getReport("NBFCMAKER", "User_Report"));
				model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
				model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));*/
				model.put("message", "Batch File Should be .xls");
				// end---------------------------------------------------------------------------------------
				// modelAct.put("actList",
				// userActivityService.getActivity("NBFCMAKER",
				// "User_Activity"));

				//model.put("homePage", "nbfcMakerHome");
				return new ModelAndView("bulkUploadPage",model);
	//	return null;
			}
			else
			{
				return new ModelAndView("redirect:/SVLogin.html");
			}
		}
		return new ModelAndView("redirect:/SVLogin.html");
			

	}

}
