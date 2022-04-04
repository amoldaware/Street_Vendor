package com.nbfc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


//import org.apache.catalina.connector.Request;
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

import javax.validation.Valid;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.JsonResponse2;
import com.nbfc.bean.LoginBean;
import com.nbfc.bean.MLICheckerBean;
import com.nbfc.bean.MLIMakerApplicationLodgementBean;
import com.nbfc.bean.MLIMakerBean;
import com.nbfc.bean.MLIMakerFileUploadBean;
import com.nbfc.bean.PortfolioBatchBean;
import com.nbfc.bean.PortfolioDetailsBean;
import com.nbfc.bean.PortfolioNumInfoBean;
import com.nbfc.bean.StateBean;
import com.nbfc.bean.TaxDetailsBean;
import com.nbfc.bean.UserBean;
import com.nbfc.bean.DistrictBean;
import com.nbfc.bean.UserRoleBean;
import com.nbfc.helper.ExcelValidator;
import com.nbfc.helper.NBFCHelper;
import com.nbfc.helper.NBFCValidator;
import com.nbfc.model.District;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIExposureId;
import com.nbfc.model.MLIIdDetails;
import com.nbfc.model.MLIInfo;
import com.nbfc.model.MLIMakerApprovalRejection;
import com.nbfc.model.MLIName;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.PortfolioBatchApp;
import com.nbfc.model.PortfolioNumInfo;
import com.nbfc.model.PortfolioNumber;
import com.nbfc.model.PortfolioNumberDetails;
import com.nbfc.model.PortfolioNumberInfo;
import com.nbfc.model.PortfolioNumberMaster;
import com.nbfc.model.TaxDetailMaster;
import com.nbfc.model.User;
import com.nbfc.model.State;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.model.UserRolePrivelage;
import com.nbfc.service.DistrictService;
import com.nbfc.service.LoginService;
import com.nbfc.service.MLICheckerUpdateService;
import com.nbfc.service.MLIValidatorService;
import com.nbfc.service.NBFCInvoiceService;
import com.nbfc.service.NPAService;
import com.nbfc.service.PortfolioApprovalService;
import com.nbfc.service.PortfolioBatchService;
import com.nbfc.service.PortfolioInfoService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;
import com.nbfc.utility.SendMail;
import com.nbfc.service.StateService;
import com.nbfc.validation.EmployeeValidator;
import com.raistudies.domain.CustomExceptionHandler;

/**
 * @author Saurav Tyagi 2017
 * 
 */
@Controller
@Scope("session")
public class NBFCController {

	@Autowired
	UserService employeeService;
	@Autowired
	NBFCInvoiceService nbfcInvoiceService;
	@Autowired
	PortfolioInfoService portfolioInfoService;
	@Autowired
	NPAService npaService;
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
	@Autowired
	MenuUtilsController menuUtilsController;
	//@Value("${downloadFileDirPath}")
	private String downloadFileDirPath;
	@Value("${UserRegistartionDetailsdownloadFileName}")
	private String downloadFileName;
	@Autowired
	ServletContext context;
	public static final int BUFFER_SIZE = 4096;
	List<PortfolioNumberMaster> portfolioList = new ArrayList<PortfolioNumberMaster>();
	List<String> listPort = new ArrayList<String>();
	List<String> masterPortfolioList = new ArrayList<String>();
	String newList;
	User userDetails;
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;
	String toDate = null;
	List<Integer> qNumberlist;
	PortfolioNumInfo portfolioNumberInfo;
	ExcelValidator excelValidator = new ExcelValidator();
	NBFCValidator nbfcValidator = new NBFCValidator();
	private List<District> userList = new ArrayList<District>();
	private List<PortfolioBatchApp> apprefList = new ArrayList<PortfolioBatchApp>();

	Map<List<String>, List<String>> errorList = new LinkedHashMap<List<String>, List<String>>();

	Map<String, String> erroList = new HashMap<String, String>();
	List<String> key = new ArrayList<String>();
	List<String> values = new ArrayList<String>();
	MLIDetails mliDetails;
	MLIName mliName;
	MLIExposureId mliExposureId;
	String value = null;
	String keyVal = null;
	/*
	 * private Pattern pattern; private Matcher matcher;
	 */
	private List<PortfolioNumInfo> appRefNum = new ArrayList<PortfolioNumInfo>();
	private List<PortfolioNumber> quarterNumber = new ArrayList<PortfolioNumber>();
	static Logger log = Logger.getLogger(NBFCController.class.getName());
	static int count;
	static int countPortfolioNUmber;

	// String MOBILE_PATTERN = "[0-9]{10}";
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveEmployee(
			@ModelAttribute("command") UserBean employeeBean,
			BindingResult result, Model modelMsg, HttpSession session)
	
			throws Exception {

		String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
			Map<String, Object> model = new HashMap<String, Object>();
			List<String> UserTypelist = new ArrayList<String>();
			UserTypelist.add(0, "Maker");
			UserTypelist.add(1, "Checker");
			log.info("Save MLI User Details***************************************************************************************************************");
			
			validator.validate(employeeBean, result);
				if (result.hasErrors()) {
				log.info("Error in field*******************************************************************************************************************");
				model.put("stateList",
						prepareStateListofBean(stateService.listStates("CCA")));
				model.put(
						"employees",
						prepareListofBean(employeeService.listEmployeess(),
								employeeService));
				model.put("guaranteelist", userActivityService.getActivity(
						"CGTMSEMAKER", "Registration"));
				model.put("applicationList", userActivityService.getActivity(
						"CGTMSEMAKER", "Application_Processing"));
				model.put("RPaymentList", userActivityService.getActivity(
						"CGTMSEMAKER", "Receipt_Payments"));
				model.put("repList",
						userActivityService.getReport("CGTMSEMAKER", "User_Report"));
				model.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSEMAKER", "Guarantee_Maintenance"));
				model.put("actName", userActivityService.getActivityName(
						"CGTMSEMAKER", "add"));

				model.put("homePage", "cgtmseMakerHome");
				model.put("UserTypelist1", UserTypelist);
				return new ModelAndView("userRegistration", model);
			}

			String mliName = employeeBean.getState();
			String Email = employeeBean.getEmail();
			String RoleName = employeeBean.getUserType();
            if(RoleName.equals("Maker")) {
            	employeeBean.setUserType("Maker");
            	
            	
            }else {
            	employeeBean.setUserType("Checker");
            }
            
			MLIInfo mliInfo = stateService.userInfo(mliName);
			//String userId = (String) session.getAttribute("userId");
			String userName = (String) session.getAttribute("uName");
			
			User employee = prepareModel(employeeBean, mliInfo.getMEM_BNK_ID(),
					mliInfo.getMEM_BRN_ID(), mliInfo.getMEM_ZNE_ID(), userId);
			String mliId = mliInfo.getMEM_BNK_ID() + mliInfo.getMEM_ZNE_ID()
					+ mliInfo.getMEM_BRN_ID();
			int countOfUser = employeeService.userRegisteredCount(mliInfo
					.getMEM_BNK_ID());
			int userLimit=employeeService.userLimit("USER_LIMIT");
			int count = userActivityService.checkExistingUserRole(mliId,
					RoleName, userName);
			String user_id = employee.getUsr_id();
			System.out.println("Generate User ID----------------" + user_id);
			
			if(countOfUser>=userLimit)
			{
				modelMsg.addAttribute("Errormessage",
						"your limit has been exceeded please contact the administrator");
				model.put("stateList",
						prepareStateListofBean(stateService.listStates("CCA")));
				model.put(
						"employees",
						prepareListofBean(employeeService.listEmployeess(),
								employeeService));
				model.put("guaranteelist", userActivityService.getActivity(
						"CGTMSEMAKER", "Registration"));
				model.put("applicationList", userActivityService.getActivity(
						"CGTMSEMAKER", "Application_Processing"));
				model.put("RPaymentList", userActivityService.getActivity(
						"CGTMSEMAKER", "Receipt_Payments"));
				model.put("repList",
						userActivityService.getReport("CGTMSEMAKER", "User_Report"));
				model.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSEMAKER", "Guarantee_Maintenance"));
				model.put("actName", userActivityService.getActivityName(
						"CGTMSEMAKER", "add"));

				model.put("homePage", "cgtmseMakerHome");
				model.put("UserTypelist1", UserTypelist);
				return new ModelAndView("userRegistration", model);

				
			}
			

			/*Commented By Anand 
			if (countOfUser >= 2) {
			 
				modelMsg.addAttribute("Errormessage",
						"MLI User Already Registered For " + mliName + " ");
				model.put("stateList",
						prepareStateListofBean(stateService.listStates("CCA")));

				model.put(
						"employees",
						prepareListofBean(employeeService.listEmployeess(),
								employeeService));
				model.put("guaranteelist", userActivityService.getActivity(
						"CGTMSEMAKER", "Registration"));
				model.put("applicationList", userActivityService.getActivity(
						"CGTMSEMAKER", "Application_Processing"));
				model.put("RPaymentList", userActivityService.getActivity(
						"CGTMSEMAKER", "Receipt_Payments"));
				model.put("repList",
						userActivityService.getReport("CGTMSEMAKER", "User_Report"));
				model.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSEMAKER", "Guarantee_Maintenance"));
		

				model.put("actName", userActivityService.getActivityName(
						"CGTMSEMAKER", "add"));

				model.put("homePage", "cgtmseMakerHome");
				model.put("UserTypelist1", UserTypelist);
				return new ModelAndView("userRegistration", model);
				// ended-----------------------------------------------------------------------------------------------------------------------
			} else if (count == 1) {
			
				model.put("guaranteelist", userActivityService.getActivity(
						"CGTMSEMAKER", "Registration"));
				model.put("applicationList", userActivityService.getActivity(
						"CGTMSEMAKER", "Application_Processing"));
				model.put("RPaymentList", userActivityService.getActivity(
						"CGTMSEMAKER", "Receipt_Payments"));
				model.put("repList",
						userActivityService.getReport("CGTMSEMAKER", "User_Report"));
				model.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSEMAKER", "Guarantee_Maintenance"));
				model.put("actName", userActivityService.getActivityName(
						"CGTMSEMAKER", "add"));

				model.put("homePage", "cgtmseMakerHome");
				model.put("stateList",
						prepareStateListofBean(stateService.listStates("CCA")));

				model.put(
						"employees",
						prepareListofBean(employeeService.listEmployeess(),
								employeeService));
				// 19
				modelMsg.addAttribute("Errormessage",
						"This userType is Already assigned for " + mliName
								+ " ");
				model.put("UserTypelist1", UserTypelist);
				return new ModelAndView("userRegistration", model);

			} else {*/
			
				UserRoleBean mliRegistrationBean = new UserRoleBean();
				employeeService.addEmployee(employee);
				userActivityService.saveUserRoles(prepareMLIRoleModel(employeeBean, userActivityService, user_id));
				String Password = employee.getPassword();
				String subject = "User ID and Password Generation from User creation";
				String mailBody = "<BR>Dear User,<BR>Congrats,Your UserID And Password has been generated from Street Vendor Scheme, corresponding to your User Registration.<br>Please find the below details.<BR>"
						+ "<BR>"
						+ "User Id :"
						+ user_id
						+ "<BR>"
						+ "Password :"
						+ Password
						+ "<BR>Please given Password must not be shared with anybody."
						+ "<BR>" + "You may Reset your password.";
				/*
				userActivityService.sendmail(user_id, Email, Password, subject,
						mailBody);
				*/
				try {
					SendMail.sendEmail(Email, subject, mailBody);
				}catch(Exception e) {
					log.error(e);
				}
				model.put("guaranteelist", userActivityService.getActivity(
						"CGTMSEMAKER", "Registration"));
				model.put("applicationList", userActivityService.getActivity(
						"CGTMSEMAKER", "Application_Processing"));
				model.put("RPaymentList", userActivityService.getActivity(
						"CGTMSEMAKER", "Receipt_Payments"));
				model.put("repList",
						userActivityService.getReport("CGTMSEMAKER", "User_Report"));
				model.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSEMAKER", "Guarantee_Maintenance"));
				model.put("actName", userActivityService.getActivityName(
						"CGTMSEMAKER", "add"));
				model.put("homePage", "cgtmseMakerHome");
				modelMsg.addAttribute("message",
						"MLI User Successfully Registered..");
				model.put("UserTypelist1", UserTypelist);
				model.put("stateList",
						prepareStateListofBean(stateService.listStates("CCA")));
				log.info("MLI Successfully Registerd***********************************************************************************************************");
				return new ModelAndView("redirect:/addUser.html");
				

			}
		


	private List<UserRoleBean> prepareListofprevelegeBean(
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

	private UserRolePrivelage prepareMLIRoleModel(UserBean employeeBean,
			UserActivityService userActivityService2, String user_id) {
		UserRolePrivelage userRolePrivelage = new UserRolePrivelage();

		userRolePrivelage.setUser_id(user_id);
		userRolePrivelage.setUpr_flag("A");
		if (employeeBean.getUserType().equals("Checker")) {
			userRolePrivelage.setPrv_id(15);
		} else if (employeeBean.getUserType().equals("Maker")) {
			userRolePrivelage.setPrv_id(14);
		}

		if ((employeeBean.getUserType() != "" && user_id != "")
				|| (employeeBean.getUserType() != null && user_id != null)) {
			userActivityService
					.setUserType(employeeBean.getUserType(), user_id);
		}
		if (userActivityService.getMaxNumber() == 0) {

			userRolePrivelage.setS_no(1);

		} else {

			userRolePrivelage.setS_no(userActivityService.getMaxNumber() + 1);
		}

		return userRolePrivelage;

	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ModelAndView saveUserEmployee(
			@ModelAttribute("command") UserBean employeeBean,
			BindingResult result, Model modelMsg, HttpSession session)
			throws Exception {
			String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		Map<String, Object> model = new HashMap<String, Object>();
		List<String> UserTypelist = new ArrayList<String>();
		UserTypelist.add(0, "Maker");
		UserTypelist.add(1, "Checker");
		log.info("Save MLI User Details***************************************************************************************************************");
		if (employeeBean.getMobileNumber().isEmpty()
				&& employeeBean.getPhoneNumber().isEmpty()) {
			validator.validate(employeeBean, result);
		} else {
			validator.validateUser(employeeBean, result);
		}
		if (result.hasErrors()) {
			log.info("Error in field*******************************************************************************************************************");
			model.put("stateList",
					prepareStateListofBean(stateService.listStates("CCA")));
			model.put(
					"employees",
					prepareListofBean(employeeService.listEmployeess(),
							employeeService));
			// added by say 6 feb-----------------------
			model.put("guaranteelist", userActivityService.getActivity(
					"CGTMSEMAKER", "Registration"));
			model.put("applicationList", userActivityService.getActivity(
					"CGTMSEMAKER", "Application_Processing"));
			model.put("RPaymentList", userActivityService.getActivity(
					"CGTMSEMAKER", "Receipt_Payments"));
			model.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSEMAKER", "Guarantee_Maintenance"));
			// model.put("actList",
			// userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));

			model.put("actName",
					userActivityService.getActivityName("CGTMSEMAKER", "add"));// Added
																				// by
																				// Say
																				// 31
																				// Jan19

			model.put("homePage", "cgtmseMakerHome");
			return new ModelAndView("userRegistration", model);
		}

		/*
		 * if (!(employeeBean.getMobileNumber() != null &&
		 * employeeBean.getMobileNumber().isEmpty())) { pattern =
		 * Pattern.compile(MOBILE_PATTERN); matcher =
		 * pattern.matcher(employeeBean.getMobileNumber()); if
		 * (!matcher.matches()) { errors.rejectValue("phone", "phone.incorrect",
		 * "Enter a correct phone number"); } }
		 */
		String mliName = employeeBean.getState();
		MLIInfo mliInfo = stateService.userInfo(mliName);
		//String userId = (String) session.getAttribute("userId");
		User employee = prepareModel(employeeBean, mliInfo.getMEM_BNK_ID(),
				mliInfo.getMEM_BRN_ID(), mliInfo.getMEM_ZNE_ID(), userId);
		employeeService.addEmployee(employee);
		modelMsg.addAttribute("message", "MLI User Successfully Registered..");
		model.put("UserTypelist1", UserTypelist);
		model.put("stateList",
				prepareStateListofBean(stateService.listStates("CCA")));
		log.info("MLI Successfully Registerd***********************************************************************************************************");
		return new ModelAndView("redirect:/addUser.html");
	}

	@RequestMapping(value = "/save", params = "reset", method = RequestMethod.POST)
	public ModelAndView ResatAddEmployee(
			@ModelAttribute("command") UserBean employeeBean,
			BindingResult result, HttpServletRequest request, HttpSession session) throws Exception {
		System.out.println("reset the value......................");
		// List<State> stateList = stateService.listStates();
		log.info("Reset MLI Registration Page***********************************************************************************************************");
			String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("stateList",
				prepareStateListofBean(stateService.listStates("CCA")));

		return new ModelAndView("redirect:/add.html");
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addMLIUser(
			@ModelAttribute("command") UserBean employeeBean,
			BindingResult result, HttpServletRequest request, Model modelMsg, HttpSession session)
			throws Exception {
		String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		List<String> UserTypelist = new ArrayList<String>();
		UserTypelist.add(0, "Maker");
		UserTypelist.add(1, "Checker");
		System.out.println("state" + request.getParameter("state"));
		// List<State> stateList = stateService.listStates();

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("stateList",
				prepareStateListofBean(stateService.listStates("CCA")));
		modelMsg.addAttribute("message", "MLI User Successfully Registered..");
		model.put("UserTypelist1", UserTypelist);
		model.put(
				"employees",
				prepareListofBean(employeeService.listEmployeess(),
						employeeService));
		model.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList", userActivityService.getActivity(
				"CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList", userActivityService.getActivity(
				"CGTMSEMAKER", "Receipt_Payments"));
		model.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		// model.put("actList",
		// userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));

		model.put("actName",
				userActivityService.getActivityName("CGTMSEMAKER", "add"));// Added
																			// by
																			// Say
																			// 31
																			// Jan19

		model.put("homePage", "cgtmseMakerHome");
		return new ModelAndView("userRegistration", model);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addEmployee(
			@ModelAttribute("command") UserBean employeeBean,
			BindingResult result, HttpServletRequest request,
			HttpSession session) throws Exception {
			String userId = (String) session.getAttribute("userId");
			String Role = (String) session.getAttribute("uRole");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		List<String> UserTypelist = new ArrayList<String>();
		UserTypelist.add(0, "Maker");
		UserTypelist.add(1, "Checker");
		System.out.println("state" + request.getParameter("state"));
		Map<String, Object> model = new HashMap<String, Object>();
		model = menuUtilsController.prepareMenu(session, Role, userId);
		// List<State> stateList = stateService.listStates();
		log.info("MLI Registration Page*****************************************************************************************************************************************");

		model.put("stateList",
				prepareStateListofBean(stateService.listStates("CCA")));
		model.put("UserTypelist1", UserTypelist);
		//String userId = (String) session.getAttribute("userId");
		if (userId.equals("CGTMSE ADMIN")) {
			return new ModelAndView("userRegistration", model);
		} else {
			if (userId != null) {
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst = lofinService
						.getPrivlageMstDtl(userPri.getPrv_id());
					model.put("actName", userActivityService.getActivityName(
							"CGTMSEMAKER", "add"));// Added by Say 31 Jan19
					model.put(
							"employees",
							prepareListofBean(employeeService.listEmployeess(),
									employeeService));
					if(Role.equals("CCHECKER")){
						model.put("homePage", "cgtmseCheckerHome");
					}
					if(Role.equals("CMAKER")){
						model.put("homePage", "cgtmseMakerHome");
					}
					return new ModelAndView("userRegistration", model);
			}
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}

	@RequestMapping(value = "/nbfcCertificate", method = RequestMethod.GET)
	public ModelAndView nbfcCertificate(
			@ModelAttribute("command") MLIMakerFileUploadBean employeeBean,
			BindingResult result, HttpServletRequest request) throws Exception {

		log.info("NBFC Certificate**********************************************************************************************************************************************");

		Map<String, Object> model = new HashMap<String, Object>();

		return new ModelAndView("nbfcCertificate", model);
	}

	@RequestMapping(value = "/mliUserList", method = RequestMethod.GET)
	public ModelAndView mliUserList(
			@ModelAttribute("command") MLIMakerFileUploadBean employeeBean,
			BindingResult result, HttpServletRequest request,HttpSession session) throws Exception {
			String userId = (String) session.getAttribute("userId");
			String Role = (String) session.getAttribute("uRole");
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		log.info("Show Mli User List*********************************************************************************************************************************************");

		Map<String, Object> model = new HashMap<String, Object>();
		model = menuUtilsController.prepareMenu(session, Role, userId);
		model.put(
				"employees",
				prepareListofBean(employeeService.listEmployeess(),
						employeeService));
		if(Role.equals("CCHECKER")){
			model.put("homePage", "cgtmseCheckerHome");
		}
		if(Role.equals("CMAKER")){
			model.put("homePage", "cgtmseMakerHome");
		}
		return new ModelAndView("mliUserListScreen", model);
	}

	@RequestMapping(value = "/mliChecker", method = RequestMethod.GET)
	public ModelAndView mliMaker(
			@ModelAttribute("command") MLIMakerBean employeeBean,
			BindingResult result, HttpServletRequest request,
			HttpSession session) throws Exception {
			String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		log.info("MLI Checker Screen*********************************************************************************************************************************************");
		listPort.clear();
		masterPortfolioList.clear();
		String status = "NMA";
		NBFCHelper nbfcHelper = new NBFCHelper();
		//String fyBasedOnStartAndEndDate=nbfcHelper.getCurrentYear();
		String fyBasedOnStartAndEndDate="2019-2020";
		List<PortfolioBatchApp> appRefNoList = portfolioBatchService
				.appRefNoList(status);
		System.out.println("appRefNoList :" + appRefNoList);
		Map<String, Object> model = new HashMap<String, Object>();

		//String userId = (String) session.getAttribute("userId");

		if (userId != null) {
			userPri = lofinService.getUserPrivlageDtl(userId, "A");
			userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
			if (userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")) {

				  
				mliDetails = employeeService.getBNKId(userId);
				// mliName
				// =employeeService.getMLIName(mliDetails.getMem_bnk_id());
				mliExposureId = employeeService.getExposureId(mliDetails.getMem_bnk_id(),fyBasedOnStartAndEndDate);
              if(mliExposureId == null) {
            	  model.put("errormessage", "Notes:To upload file at first create exposure limit.");
}else {
	portfolioList = employeeService
			.getPortfolioNUmberForChecker(mliExposureId
					.getEXPOSURE_ID());

	for (PortfolioNumberMaster masterList : portfolioList) {
		// masterPortfolioList.add(Integer.toString(masterList.getPortfolioNUmber()));
		masterPortfolioList.add(masterList.getPortfolio_name());
		System.out.println(masterList.getPortfolio_name());

	}
	System.out.println("portfolioList" + portfolioList);
	List<PortfolioBatchBean> list = portfoliAppRefList(portfolioBatchService
			.appRefNoList(status));

	if (list != null) {
		for (PortfolioBatchBean bean : list) {
			listPort.add(bean.getArp_ref_no());
			System.out.println(listPort);
		}

	}
	System.out.println("listPort :" + listPort);
	System.out.println("masterPortfolioList :"
			+ masterPortfolioList);
	masterPortfolioList.retainAll(listPort);
	// listPort.retainAll(masterPortfolioList);
	
	
}
			
			
				PortfolioDetailsBean bean = new PortfolioDetailsBean();
				bean = npaService.getExposureDetails(userId);
//				employeeBean.setPAYOUT_CAP(bean.getPayCap());
//				employeeBean.setUTIL_EXP(bean.getUTIL_EXP());
//				employeeBean.setPENDING_EXP(bean.getPENDING_EXP());
				
				if(bean.getExpLmt()==null){
					  
					employeeBean.setPendingExp1(null);
					employeeBean.setUtilExp1(null);
					employeeBean.setTotExpSize1(null);
					employeeBean.setEXP_NO(null);
					employeeBean.setGURANTEE_COVERAGE(null);
					employeeBean.setGURANTEE_FEE(null);
					model.put("errormessage", "Notes:To upload file at first create exposure limit.");
			}else{
			    Long l = (new Double(bean.getUTIL_EXP())).longValue();
			    System.out.println("Utilized Exposure=="+l);
			    Long m = (new Double(bean.getPENDING_EXP())).longValue();
			    System.out.println("Unutilized Exposure Limit=="+l);
			    Long n = (new Double(bean.getExpLmt())).longValue();
			    System.out.println("Total Exposure Size=="+n);
			    
				employeeBean.setPendingExp1(m);
				employeeBean.setUtilExp1(l);
				employeeBean.setTotExpSize1(n);
				employeeBean.setEXP_NO(bean.getEXP_NO());
				employeeBean.setTOT_EXP_SIZE(bean.getExpLmt());
				employeeBean.setGURANTEE_COVERAGE(bean.getGURANTEE_COVERAGE());
				employeeBean.setGURANTEE_FEE(bean.getGURANTEE_FEE());
				employeeBean.setPAYOUT_CAP(bean.getPayCap());
			}
				System.out.println(masterPortfolioList.toString());
				model.put("appRefNoList", npaService.getPortfolioName(userId));
				// added by say 6 feb-----------------------
				model.put("adminlist", userActivityService.getActivity(
						"NBFCCHECKER", "System_Admin"));
				model.put("guaranteelist", userActivityService.getActivity(
						"NBFCCHECKER", "Registration"));
				model.put("applicationList", userActivityService.getActivity(
						"NBFCCHECKER", "Application_Processing"));
				model.put("RPaymentList", userActivityService.getActivity(
						"NBFCCHECKER", "Receipt_Payments"));
				model.put("actName", userActivityService.getActivityName(
						"NBFCCHECKER", "mliChecker"));// Added by say 25june19
				model.put("repList",
						userActivityService.getReport("NBFCCHECKER", "User_Report"));
				model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
				model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
				// end---------------------------------------------------------------------------------------
				// modelAct.put("actList",
				// userActivityService.getActivity("NBFCMAKER",
				// "User_Activity"));
				// model.put("actList", userActivityService.getActivity(
				// "NBFCCHECKER", "User_Activity"));
				model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCCHECKER", "Guarantee_Maintenance"));
				model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
				model.put("homePage", "nbfcCheckerHome");
				return new ModelAndView("mliCheckerScreen", model);
			} else {
				return new ModelAndView("redirect:/SVLogin.html");
			}
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}

	@RequestMapping(value = "/mliCheckerReject", method = RequestMethod.GET)
	public ModelAndView mliCheckerReject(
			@ModelAttribute("command") MLIMakerBean employeeBean,
			BindingResult result, HttpServletRequest request,
			HttpSession session, Model messageTag) throws Exception {
			String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		log.info("MLI Checker Reject*********************************************************************************************************************************************");

		listPort.clear();
		masterPortfolioList.clear();
		String status = "NMA";
		List<PortfolioBatchApp> appRefNoList = portfolioBatchService
				.appRefNoList(status);
		System.out.println("appRefNoList :" + appRefNoList);
		Map<String, Object> model = new HashMap<String, Object>();

	//	String userId = (String) session.getAttribute("userId");

		if (userId != null) {
			userPri = lofinService.getUserPrivlageDtl(userId, "A");
			userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
			if (userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")) {

				mliDetails = employeeService.getBNKId(userId);

				mliName = employeeService
						.getMLIName(mliDetails.getMem_bnk_id());

				portfolioList = employeeService
						.getPortfolioNUmberForChecker(mliName.getMliLName());
				for (PortfolioNumberMaster masterList : portfolioList) {
					// masterPortfolioList.add(Integer.toString(masterList.getPortfolioNUmber()));
					masterPortfolioList.add(masterList.getPortfolio_name());

				}
				System.out.println("portfolioList" + portfolioList);
				List<PortfolioBatchBean> list = portfoliAppRefList(portfolioBatchService
						.appRefNoList(status));

				if (list != null) {
					for (PortfolioBatchBean bean : list) {
						listPort.add(bean.getArp_ref_no());
						System.out.println(listPort);
					}

				}
				System.out.println("listPort :" + listPort);
				System.out.println("masterPortfolioList :"
						+ masterPortfolioList);
				masterPortfolioList.retainAll(listPort);

				System.out.println(masterPortfolioList.toString());
				messageTag.addAttribute("message",
						"Successfully forwarded the application to CGTMSE.");
				model.put("appRefNoList",
						portfoliAppRefNum(masterPortfolioList));
				// added by say 6 feb-----------------------
				model.put("adminlist", userActivityService.getActivity(
						"NBFCCHECKER", "System_Admin"));
				model.put("guaranteelist", userActivityService.getActivity(
						"NBFCCHECKER", "Registration"));
				model.put("applicationList", userActivityService.getActivity(
						"NBFCCHECKER", "Application_Processing"));
				model.put("RPaymentList", userActivityService.getActivity(
						"NBFCCHECKER", "Receipt_Payments"));
				model.put("actName", userActivityService.getActivityName(
						"NBFCCHECKER", "mliChecker"));// Added by say 25june19
				model.put("repList",
						userActivityService.getReport("NBFCCHECKER", "User_Report"));
				model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCCHECKER", "Guarantee_Maintenance"));
				model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
				model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
				// end---------------------------------------------------------------------------------------
				messageTag.addAttribute("message",
						"Batch file has been rejected.");
				model.put("homePage", "nbfcCheckerHome");
				return new ModelAndView("mliCheckerScreen", model);
			} else {
				return new ModelAndView("redirect:/SVLogin.html");
			}
		}
		return new ModelAndView("redirect:/SVLogin.html");

	}

	@RequestMapping(value = "/mliCheckerApproved", method = RequestMethod.GET)
	public ModelAndView mliCheckerApproved(
			@ModelAttribute("command") MLIMakerBean employeeBean,
			BindingResult result, HttpServletRequest request,
			HttpSession session, Model messageTag) throws Exception {
			String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		listPort.clear();
		masterPortfolioList.clear();
		String status = "NMA";
		List<PortfolioBatchApp> appRefNoList = portfolioBatchService
				.appRefNoList(status);
		System.out.println("appRefNoList :" + appRefNoList);
		Map<String, Object> model = new HashMap<String, Object>();

		//String userId = (String) session.getAttribute("userId");
		NBFCHelper nbfcHelper = new NBFCHelper();
		String fyBasedOnStartAndEndDate=nbfcHelper.getCurrentYear();
		if (userId != null) {
			userPri = lofinService.getUserPrivlageDtl(userId, "A");
			userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
			if (userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")) {
				mliDetails = employeeService.getBNKId(userId);
				// mliName
				// =employeeService.getMLIName(mliDetails.getMem_bnk_id());
				mliExposureId = employeeService.getExposureId(mliDetails
						.getMem_bnk_id(),fyBasedOnStartAndEndDate);

				portfolioList = employeeService
						.getPortfolioNUmberForChecker(mliExposureId
								.getEXPOSURE_ID());

				for (PortfolioNumberMaster masterList : portfolioList) {
					// masterPortfolioList.add(Integer.toString(masterList.getPortfolioNUmber()));
					masterPortfolioList.add(masterList.getPortfolio_name());
				}
				System.out.println("portfolioList" + portfolioList);
				List<PortfolioBatchBean> list = portfoliAppRefList(portfolioBatchService
						.appRefNoList(status));
				if (list != null) {
					for (PortfolioBatchBean bean : list) {
						listPort.add(bean.getArp_ref_no());
						System.out.println(listPort);
					}
				}
				System.out.println("listPort :" + listPort);
				System.out.println("masterPortfolioList :"
						+ masterPortfolioList);
				masterPortfolioList.retainAll(listPort);

				System.out.println(masterPortfolioList.toString());
				messageTag.addAttribute("message",
						"Successfully forwarded the application to CGTMSE.");
				model.put("appRefNoList",
						portfoliAppRefNum(masterPortfolioList));
				// added by say 6 feb-----------------------
				model.put("adminlist", userActivityService.getActivity(
						"NBFCCHECKER", "System_Admin"));
				model.put("guaranteelist", userActivityService.getActivity(
						"NBFCCHECKER", "Registration"));
				model.put("applicationList", userActivityService.getActivity(
						"NBFCCHECKER", "Application_Processing"));
				model.put("RPaymentList", userActivityService.getActivity(
						"NBFCCHECKER", "Receipt_Payments"));
				model.put("actName", userActivityService.getActivityName(
						"NBFCCHECKER", "mliChecker"));// Added by say 25june19
				model.put("repList",
						userActivityService.getReport("NBFCCHECKER", "User_Report"));
				model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCCHECKER", "Guarantee_Maintenance"));
				model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
				model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
				// end---------------------------------------------------------------------------------------
				model.put("homePage", "nbfcCheckerHome");
				return new ModelAndView("mliCheckerScreen", model);
			} else {
				return new ModelAndView("redirect:/SVLogin.html");
			}
		}
		return new ModelAndView("redirect:/SVLogin.html");

	}

	@RequestMapping(value = "/mliCheckerUpdateFirstShowSuccessRecord", method = RequestMethod.POST)
	public ModelAndView viewSuccessfullRecords(
			@ModelAttribute("command") MLIMakerFileUploadBean employeeBean,
			BindingResult result, HttpServletRequest request, Model modelMsg,HttpSession session)
			throws Exception {
			String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		Map<String, Object> model = new HashMap<String, Object>();
		String statusA = "NMA";
		System.out.println(statusA);
		validator.portfolioDetailsValidate(employeeBean, result);
		if (result.hasErrors()) {
			model.put("appRefNoList", portfoliAppRefList(portfolioBatchService
					.appRefNoList(statusA)));
			// added by say 6 feb-----------------------
			model.put("adminlist", userActivityService.getActivity(
					"NBFCCHECKER", "System_Admin"));
			model.put("guaranteelist", userActivityService.getActivity(
					"NBFCCHECKER", "Registration"));
			model.put("applicationList", userActivityService.getActivity(
					"NBFCCHECKER", "Application_Processing"));
			model.put("RPaymentList", userActivityService.getActivity(
					"NBFCCHECKER", "Receipt_Payments"));
			model.put("actName", userActivityService.getActivityName(
					"NBFCCHECKER", "mliChecker"));// Added by say 25june19
			model.put("repList",
					userActivityService.getReport("NBFCCHECKER", "User_Report"));
			model.put("GMaintainlist", userActivityService.getActivity(
					"NBFCCHECKER", "Guarantee_Maintenance"));
			model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
			model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
			// end---------------------------------------------------------------------------------------
			model.put("homePage", "nbfcCheckerHome");
			return new ModelAndView("mliCheckerScreen", model);
		}
		// String portfolioNo = request.getParameter("appNo");
		String portfolioNo = employeeBean.getPORTFOLIO_NO();
		System.out.println("portfolioNo---------------------- " + portfolioNo);

		if (portfolioBatchService.getPortfolioDetail(portfolioNo).isEmpty() != true) {
			model.put("appRefNoList",
					prepareListofBeanforPortfolio(portfolioBatchService
							.getPortfolioDetail(portfolioNo)));
		} else {
			modelMsg.addAttribute("message", "No Record Found.");

		}
		model.put("adminlist", userActivityService.getActivity(
				"NBFCCHECKER", "System_Admin"));
		model.put("guaranteelist", userActivityService.getActivity(
				"NBFCCHECKER", "Registration"));
		model.put("applicationList", userActivityService.getActivity(
				"NBFCCHECKER", "Application_Processing"));
		model.put("RPaymentList", userActivityService.getActivity(
				"NBFCCHECKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName(
				"NBFCCHECKER", "mliChecker"));// Added by say 25june19
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
		// end---------------------------------------------------------------------------------------
		model.put("homePage", "nbfcCheckerHome");
		return new ModelAndView("portfolioDetailsScreen", model);

	}

	@RequestMapping(value = "/mliCheckerUpdateFirstShowFailedRecord", method = RequestMethod.POST)
	public ModelAndView viewFailedRecords(
			@ModelAttribute("command") MLIMakerFileUploadBean employeeBean,
			BindingResult result, HttpServletRequest request, Model modelMsg,HttpSession session)
			throws Exception {
			String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		Map<String, Object> model = new HashMap<String, Object>();
		String statusA = "NMA";
		validator.portfolioDetailsValidate(employeeBean, result);
		if (result.hasErrors()) {
			model.put("appRefNoList", portfoliAppRefList(portfolioBatchService
					.appRefNoList(statusA)));
			// added by say 6 feb-----------------------
			model.put("adminlist", userActivityService.getActivity(
					"NBFCCHECKER", "System_Admin"));
			model.put("guaranteelist", userActivityService.getActivity(
					"NBFCCHECKER", "Registration"));
			model.put("applicationList", userActivityService.getActivity(
					"NBFCCHECKER", "Application_Processing"));
			model.put("RPaymentList", userActivityService.getActivity(
					"NBFCCHECKER", "Receipt_Payments"));
			model.put("actName", userActivityService.getActivityName(
					"NBFCCHECKER", "mliChecker"));// Added by say 25june19
			model.put("repList",
					userActivityService.getReport("NBFCCHECKER", "User_Report"));
			model.put("GMaintainlist", userActivityService.getActivity(
					"NBFCCHECKER", "Guarantee_Maintenance"));
			model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
			model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
			// end---------------------------------------------------------------------------------------
			model.put("homePage", "nbfcCheckerHome");
			return new ModelAndView("mliCheckerScreen", model);
		}
		// String portfolioNo = request.getParameter("appNo");
		String portfolioNo = employeeBean.getPORTFOLIO_NO();
		System.out.println("portfolioNo---------------------- " + portfolioNo);

		// List<MliMakerEntity> listDetails = portfolioBatchService
		// .getPortfolioDet(portfolioNo);
		// List<MliMakerEntity> listDetails = portfolioBatchService
		// .getPortfolioDetail(portfolioNo);
		if (portfolioBatchService.getPortfolioFailedRecords(portfolioNo)
				.isEmpty() != true) {
			model.put("appRefNoList",
					prepareListofBeanforPortfolio(portfolioBatchService
							.getPortfolioFailedRecords(portfolioNo)));
		} else {

			modelMsg.addAttribute("message", "No Record Found.");

		}
		model.put("adminlist", userActivityService.getActivity(
				"NBFCCHECKER", "System_Admin"));
		model.put("guaranteelist", userActivityService.getActivity(
				"NBFCCHECKER", "Registration"));
		model.put("applicationList", userActivityService.getActivity(
				"NBFCCHECKER", "Application_Processing"));
		model.put("RPaymentList", userActivityService.getActivity(
				"NBFCCHECKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName(
				"NBFCCHECKER", "mliChecker"));// Added by say 25june19
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
		// end---------------------------------------------------------------------------------------
		model.put("homePage", "nbfcCheckerHome");
		return new ModelAndView("portfolioRejectedDetailsScreen", model);

	}

	@RequestMapping(value = "/mliCheckerUpdateFirstForwordToCGTMSC", params = "action1", method = RequestMethod.POST)
	public ModelAndView mliMakerUpdateFirst(Model modelMessage,
			@ModelAttribute("command") MLIMakerFileUploadBean mliMakerBean,
			BindingResult result, HttpSession session) throws Exception {
			String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		System.out.println("accept...............");
		System.out.println("Saurav Tyagi");
		String statusA = "NMA";
		//String userId = (String) session.getAttribute("userId");
		Map<String, Object> model = new HashMap<String, Object>();
		validator.portfolioApprovalValidate(mliMakerBean, result);
		if (result.hasErrors()) {
			List<PortfolioBatchBean> list = portfoliAppRefList(portfolioBatchService
					.appRefNoList(statusA));

			if (list != null) {
				for (PortfolioBatchBean bean : list) {
					listPort.add(bean.getArp_ref_no());
					System.out.println(listPort);
				}
			}
			PortfolioDetailsBean bean = new PortfolioDetailsBean();
			bean=npaService.getExposureDetails(userId);
			mliMakerBean.setPAYOUT_CAP(bean.getPayCap());
			mliMakerBean.setUTIL_EXP(bean.getUTIL_EXP());
			mliMakerBean.setPENDING_EXP(bean.getPENDING_EXP());
			mliMakerBean.setEXP_NO(bean.getEXP_NO());
			mliMakerBean.setTOT_EXP_SIZE(bean.getExpLmt());
			mliMakerBean.setGURANTEE_COVERAGE(bean.getGURANTEE_COVERAGE());
			mliMakerBean.setGURANTEE_FEE(bean.getGURANTEE_FEE());
			
			
			System.out.println(masterPortfolioList.toString());
			model.put("appRefNoList",npaService.getPortfolioName(userId));
			
			// added by say 6 feb-----------------------
			model.put("adminlist", userActivityService.getActivity(
					"NBFCCHECKER", "System_Admin"));
			model.put("guaranteelist", userActivityService.getActivity(
					"NBFCCHECKER", "Registration"));
			model.put("applicationList", userActivityService.getActivity(
					"NBFCCHECKER", "Application_Processing"));
			model.put("RPaymentList", userActivityService.getActivity(
					"NBFCCHECKER", "Receipt_Payments"));
			model.put("actName", userActivityService.getActivityName(
					"NBFCCHECKER", "mliChecker"));// Added by say 25june19
			model.put("repList",
					userActivityService.getReport("NBFCCHECKER", "User_Report"));
			model.put("GMaintainlist", userActivityService.getActivity(
					"NBFCCHECKER", "Guarantee_Maintenance"));
			model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
			model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
			// end---------------------------------------------------------------------------------------
			model.put("homePage", "nbfcCheckerHome");
			return new ModelAndView("mliCheckerScreen", model);
		}

		String appNo = mliMakerBean.getPORTFOLIO_NO();

		System.out.println("appNo" + appNo);
		System.out.println("SStep 1");
		// MliMakerEntity mliMaker1 = prepareModel(mliMakerBean);
		PortfolioNumberDetails portNUm = portfolioBatchService
				.getPortfolioNUmber(appNo);

		List<MLIMakerFileUploadBean> mliMakerBenzz = new ArrayList<MLIMakerFileUploadBean>();

		List<MLIMakerApprovalRejection> mliList = portfolioBatchService
				.getPortfolioDetail(portNUm.getPortfolioName());

		/*
		 * for (MLIMakerApprovalRejection MliMakerEnt : mliList) {
		 * System.out.println(MliMakerEnt); mliMakerBenzz =
		 * prepareEmployeeBean(MliMakerEnt); //
		 * //---------------------------------------------
		 * MLIMakerApprovalRejection mliMaker = prepareModel(mliMakerBenzz);
		 * 
		 * portfolioApprovalService.addCheckerApproval(mliMaker);
		 * 
		 * }
		 */
		//String userId = (String) session.getAttribute("userId");
		mliMakerBenzz = prepareEmployeeBeanList(mliList);
		List<MLIMakerApprovalRejection> mliEntityList = prepareModelList(
				mliMakerBenzz, mliMakerBean.getAcceptStatus(), userId);
		// portfolioApprovalService.addCheckerApproval(mliEntityList);
		portfolioApprovalService.updateStatusMLIChkAppLodge(userId, "NCA",
				appNo);

		// mliCheckerUpdateService.updateStatusMLIChkAppLodge(userId, "NCA",
		// appNo);

		System.out.println("SuccessFully Approved.............");
		// portfolioBatchService.getPortfolioDetail(appNo);

		// PortfolioBatchApp employee = protfolioApproval(mliMakerBean);
		// portfolioApprovalService.approvePortfolioStatus(appNo);
		modelMessage.addAttribute("message", "Batch file has been Approved.");
		return new ModelAndView("redirect:/mliCheckerApproved.html");
		// return new ModelAndView("mliCheckerScreen", model);}
	}

	@RequestMapping(value = "/mliCheckerUpdateFirst", params = "action2", method = RequestMethod.POST)
	public ModelAndView mliMakerUpdateFist(Model modelMessage,
			@ModelAttribute("command") MLIMakerFileUploadBean mliMakerBean,
			BindingResult result, HttpSession session) throws Exception {
			String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		System.out.println("reject...............");
		String statusA = "NMA";
		Map<String, Object> model = new HashMap<String, Object>();
		validator.portfolioRejectionValidate(mliMakerBean, result);
		if (result.hasErrors()) {
			model.put("appRefNoList", portfoliAppRefList(portfolioBatchService
					.appRefNoList(statusA)));
			// added by say 6 feb-----------------------
			model.put("adminlist", userActivityService.getActivity(
					"NBFCCHECKER", "System_Admin"));
			model.put("guaranteelist", userActivityService.getActivity(
					"NBFCCHECKER", "Registration"));
			model.put("applicationList", userActivityService.getActivity(
					"NBFCCHECKER", "Application_Processing"));
			model.put("RPaymentList", userActivityService.getActivity(
					"NBFCCHECKER", "Receipt_Payments"));
			model.put("actName", userActivityService.getActivityName(
					"NBFCCHECKER", "mliChecker"));// Added by say 25june19
			model.put("repList",
					userActivityService.getReport("NBFCCHECKER", "User_Report"));
			model.put("GMaintainlist", userActivityService.getActivity(
					"NBFCCHECKER", "Guarantee_Maintenance"));
			model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
			model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
			// end---------------------------------------------------------------------------------------
			model.put("homePage", "nbfcCheckerHome");
			return new ModelAndView("mliCheckerScreen", model);
		}
		String remarks = mliMakerBean.getREMARKS();

		String appNo = mliMakerBean.getPORTFOLIO_NO();
		System.out.println("appNo" + appNo);
		System.out.println("SStep 1");

		List<MLIMakerFileUploadBean> mliMakerBenzz = new ArrayList<MLIMakerFileUploadBean>();

		List<MLIMakerApprovalRejection> mliList = portfolioBatchService
				.getPortfolioDetail(appNo);

		/*
		 * for (MLIMakerApprovalRejection MliMakerEnt : mliList) {
		 * System.out.println(MliMakerEnt); mliMakerBenzz =
		 * prepareEmployeeBeanForRejection(remarks, MliMakerEnt);
		 * 
		 * MLIMakerApprovalRejection mliMaker =
		 * prepareModelForReject(mliMakerBenzz);
		 * 
		 * portfolioApprovalService.addCheckerApproval(mliMaker);
		 * 
		 * }
		 */

		mliMakerBenzz = prepareEmployeeBeanListForRejection(mliList, remarks);
		//String userId = (String) session.getAttribute("userId");
		List<MLIMakerApprovalRejection> nliMackerRejection = prepareModelListForRejected(
				mliMakerBenzz, userId);
		portfolioApprovalService.addCheckerApproval(nliMackerRejection);
		System.out.println("SuccessFully Reject.............");
		// portfolioBatchService.getPortfolioDetail(appNo);

		// PortfolioBatchApp employee = protfolioApproval(mliMakerBean);
		// portfolioApprovalService.approvePortfolioStatus(appNo);

		// return new ModelAndView("redirect:/mliChecker.html");
		modelMessage.addAttribute("message", "Batch file has been rejected.");
		return new ModelAndView("redirect:/mliCheckerReject.html");

		// return new ModelAndView("mliCheckerScreen", model);

	}

	/*
	 * @RequestMapping(value = "/getDistrict", method = RequestMethod.POST)
	 * public @ResponseBody JsonResponse2 addUser(@ModelAttribute(value =
	 * "state") District district, BindingResult result, String state)throws
	 * Exception { JsonResponse2 res = new JsonResponse2(); Map<String, String>
	 * mapLongNameObj1 = districtService .listDistricts(state);
	 * userList.clear(); for (Map.Entry<String, String> entry :
	 * mapLongNameObj1.entrySet()) { District s1 = new District(); String value
	 * = entry.getValue(); s1.setDst_name(value); userList.add(s1); }
	 * mapLongNameObj1.clear(); res.setStatus("SUCCESS");
	 * res.setResult(userList);
	 * 
	 * return res; }
	 */

	@RequestMapping(value = "/getPortfolioNumber", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse2 getPortfolioNumber(
			@ModelAttribute(value = "portfoBaseYr") PortfolioNumInfo portfolioNumInfo,
			BindingResult result, String portfoBaseYr, HttpSession session)
			throws Exception {
		
		JsonResponse2 res = new JsonResponse2();
		countPortfolioNUmber = 0;
		String userId = (String) session.getAttribute("userId");
		mliDetails = employeeService.getBNKId(userId);
		NBFCHelper nbfcHelper = new NBFCHelper();
		String fyBasedOnStartAndEndDate=nbfcHelper.getCurrentYear();
		// mliName =employeeService.getMLIName(mliDetails.getMem_bnk_id());

		mliExposureId = employeeService.getExposureId(mliDetails
				.getMem_bnk_id(),fyBasedOnStartAndEndDate);

		/*
		 * Map<String, String> mapLongNameObj1 = portfolioInfoService
		 * .portfolioAppRefNumber(portfoBaseYr,mliName.getMliLName());
		 */

		Map<String, String> mapLongNameObj1 = portfolioInfoService
				.portfolioAppRefNumber(portfoBaseYr,
						mliExposureId.getEXPOSURE_ID());

		appRefNum.clear();
		for (Map.Entry<String, String> entry : mapLongNameObj1.entrySet()) {
			PortfolioNumInfo s1 = new PortfolioNumInfo();
			value = entry.getValue();
			keyVal = entry.getKey();

			// String value="-Select Portfolio Number-";
			if (countPortfolioNUmber == 0) {
				PortfolioNumInfo s2 = new PortfolioNumInfo();
				String select = "-Select Portfolio Number-";
				s2.setPortfolio_Number(select);
				countPortfolioNUmber++;
				appRefNum.add(s2);
			}

			s1.setPortfolio_Number(value);
			s1.setPortfolio_name(keyVal);
			System.out.println("portfolio number :" + s1);
			appRefNum.add(s1);

		}
		mapLongNameObj1.clear();
		res.setStatus("SUCCESS");
		res.setResult(appRefNum);

		return res;
	}

	@RequestMapping(value = "/getQuarterNumber", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse2 getQuarterNumber(
			@ModelAttribute(value = "portfoBaseYr") PortfolioNumber portfolioNumInfo,
			BindingResult result, String portfolioNumber) throws ParseException {
		JsonResponse2 res = new JsonResponse2();
		countPortfolioNUmber = 0;
		// qNumberlist.clear();
		Date date = new Date();
		qNumberlist = nbfcValidator.getQuarterIdNUmber(nbfcValidator
				.getQuarterNUmber(date));
		portfolioNumberInfo = portfolioInfoService
				.getPortfolioNum(portfolioNumber);
		Map<String, String> mapLongNameObj1 = portfolioInfoService
				.portfoloQuarterNumber(
						portfolioNumberInfo.getPortfolio_Number(), qNumberlist);
		quarterNumber.clear();
		for (Map.Entry<String, String> entry : mapLongNameObj1.entrySet()) {
			PortfolioNumber s1 = new PortfolioNumber();

			String value = entry.getValue();
			String key = entry.getKey();
			System.out.println(key + "  " + value);
			// String value="-Select Portfolio Number-";
			if (countPortfolioNUmber == 0) {
				PortfolioNumber s2 = new PortfolioNumber();
				String select = "-Select Quarter Number-";
				s2.setPortfolioQuarter(select);
				countPortfolioNUmber++;
				quarterNumber.add(s2);
			}

			s1.setPortfolioQuarter(value);
			s1.setSub_portfolio_dt_no(key);
			System.out.println("portfolio number :" + s1);
			quarterNumber.add(s1);

		}
		mapLongNameObj1.clear();
		res.setStatus("SUCCESS");
		res.setResult(quarterNumber);

		return res;
	}

	@RequestMapping(value = "/getPortfolioDetails", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse2 getPortfolioDetails(
			@ModelAttribute(value = "portfolioNum") PortfolioNumInfo portfolioNumInfo,
			BindingResult result, String portfolioNum,HttpSession session)
			throws NullPointerException, Exception {
		System.out.println("portfolioNum " + portfolioNum);
		JsonResponse2 res = new JsonResponse2();
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			res.setStatus("FAIL");
			return res;
		} 
		PortfolioNumberInfo pNumberInfo = portfolioInfoService
				.portfolioNumberInfo(portfolioNum);
		System.out.println("pNumberInfo.getPortfolioNUmber()"
				+ pNumberInfo.getPortfolioNUmber());
		int successValue = portfolioInfoService
				.portfolioSuccessRecordsCount(pNumberInfo.getPortfolioNUmber());
		int failedRecords = portfolioInfoService
				.portfolioFailedRecordsCount(pNumberInfo.getPortfolioNUmber());
		PortfolioNumInfoBean portfolioNumInfoBean = preparePortfolioModel(
				portfolioInfoService.PortfolioInfo(pNumberInfo
						.getPortfolioNUmber()), successValue, failedRecords);

		System.out.println("portfolioDetails: " + portfolioNumInfoBean
				+ "SuccessRecords :" + successValue + "failedRecords :"
				+ failedRecords);
		res.setStatus("SUCCESS");
		res.setResult(portfolioNumInfoBean);

		return res;
	}

	@RequestMapping(value = "/getMliId", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse2 getMliId(
			@ModelAttribute(value = "mliName") PortfolioNumInfo portfolioNumInfo,
			BindingResult result, String mliName,HttpSession session) throws NullPointerException,
			Exception {
			JsonResponse2 res = new JsonResponse2();
		MLIIdDetails mliIdDetails = new MLIIdDetails();
		UserBean usrBean = new UserBean();
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			usrBean.setMliId("000");
			res.setStatus("FAIL");
			res.setResult(usrBean);

			return res;
		} 
		/*
		 * String userId = (String) session.getAttribute("userId"); mliDetails =
		 * employeeService.getBNKId(userId);
		 */
		mliIdDetails = employeeService.getMLIID(mliName);
		String bankId = mliIdDetails.getMEM_BNK_ID() + ""
				+ mliIdDetails.getMEM_BRN_ID() + ""
				+ mliIdDetails.getMEM_ZNE_ID();
		usrBean.setMliId(bankId);
		res.setStatus("SUCCESS");
		res.setResult(usrBean);

		return res;
	}

	// --------------------say-added 22 nov
	// 2018--------------------------------------------------
	@RequestMapping(value = "/getDeleteUserDetails", method = RequestMethod.GET)
	public ModelAndView GetgetDeleteUserDetails(
			@ModelAttribute("command") UserBean employeeBean,
			BindingResult result, Model modelMsg, String userId,
			HttpSession session) throws Exception {
		System.out.println("ID......................" + userId);
		// List<State> stateList = stateService.listStates();
		log.info("Reset MLI Registration Page***********************************************************************************************************");

		Map<String, Object> model = new HashMap<String, Object>();

		// prepareBeanForEditDetails(
		// employeeService.getUserPrivlageDetailsForEdit(userId),
		// employeeService));

		model.put(
				"UserDetails",
				prepareModelForDelete(employeeService.getUserDetails(userId),
						employeeService));
		model.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList", userActivityService.getActivity(
				"CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList", userActivityService.getActivity(
				"CGTMSEMAKER", "Receipt_Payments"));

		model.put("actName",
				userActivityService.getActivityName("CGTMSEMAKER", "add"));// Added
																			// by
																			// Say
																			// 31
																			// Jan19

		model.put("homePage", "cgtmseMakerHome");
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		return new ModelAndView("userDeleteEditPage", model);

		//
		//
	}

	// Added by
	// say---------------------------------------------------------------
	// @RequestMapping(value = "/TaxInvoicesdata", method = RequestMethod.GET)
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
					// added by say 6 feb-----------------------
					model.put("adminlist", userActivityService.getActivity(
							"ADMIN", "System_Admin"));

					// end---------------------------------------------------------------------------------------
					// model.put("actList",
					// userActivityService.getActivity("ADMIN",
					// "User_Activity"));
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

	private PortfolioNumInfoBean preparePortfolioModel(
			PortfolioNumInfo employeeBean, int successRecords, int failedRecords)
			throws Exception {
		PortfolioNumInfoBean employees = new PortfolioNumInfoBean();
		employees.setGuarantee_commission(employeeBean
				.getGuarantee_commission());
	//	employees.setPayout_cap(employeeBean.getPayout_cap());
		employees.setPortfolio_base_yer(employeeBean.getPortfolio_base_yer());
		employees.setPortfolio_Number(employeeBean.getPortfolio_Number());
	//	employees.setPayout_cap(employeeBean.getPayout_cap());
		/*
		 * System.out.println("employeeBean.getPortfolio_start_date()  :" +
		 * employeeBean.getPortfolio_start_date()); String date =
		 * employeeBean.getPortfolio_start_date().substring(0, 10); String
		 * dateSplit[] = date.split("-"); String formatedSanctionDate =
		 * dateSplit[2] + "/" + dateSplit[1] + "/" + dateSplit[0];
		 */
		// employees.setPortfolio_start_date(formatedSanctionDate);
		employees.setPortfolio_start_date(employeeBean
				.getPortfolio_start_date());
		employees.setPortfolio_period(employeeBean.getPortfolio_period());
		employees.setSenctioned_portfolio(employeeBean
				.getSenctioned_portfolio());
		employees
				.setPortfolioSuccessRecords("Click to view the success Records "
						+ successRecords);
		employees.setPortfolioFailedRecords("Click to view the failed Records "
				+ failedRecords);

		return employees;
	}

	private List<MLIMakerApprovalRejection> prepareModelList(
			List<MLIMakerFileUploadBean> employees, String acceptStatus,
			String userId) {
		List<MLIMakerApprovalRejection> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<MLIMakerApprovalRejection>();
			MLIMakerApprovalRejection bean = null;
			for (MLIMakerFileUploadBean employee : employees) {
				bean = new MLIMakerApprovalRejection();

				bean.setLOAN_ACCOUNT_NO(employee.getLOAN_ACCOUNT_NO());//
				bean.setLONE_TYPE(employee.getLONE_TYPE());//
				bean.setSANCTIONED_AMOUNT(employee.getSANCTIONED_AMOUNT());//
				bean.setINTEREST_RATE(employee.getINSERT_RATE());//
				bean.setTENOR_IN_MONTHS(employee.getTENOR_IN_MONTHS());//
				bean.setPINCODE(employee.getPINCODE());//
				bean.setMSE_ITPAN(employee.getMSE_ITPAN());//
				// bean.setMSME_REGISTRATION_NO(employee.getMSME_REGISTRATION_NO());//
				bean.setNO_OF_EMPLOYEES(employee.getNO_OF_EMPLOYEES());//
				bean.setPROJECTED_SALES(employee.getPROJECTED_SALES());//
				bean.setPROJECTED_EXPORTS(employee.getPROJECTED_EXPORTS());//
				bean.setCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE(employee
						.getCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE());//
				// bean.setFIRST_TIME_CUSTOMER(employee.getFIRST_TIME_CUSTOMER());//
				bean.setCHIEF_PROMOTER_FIRST_NAME(employee
						.getCHIEF_PROMOTER_FIRST_NAME());//
				bean.setCHIEF_PROMOTER_MIDDLE_NAME(employee
						.getCHIEF_PROMOTER_MIDDLE_NAME());
				bean.setCHIEF_PROMOTER_LAST_NAME(employee
						.getCHIEF_PROMOTER_LAST_NAME());
				bean.setCHIEF_PROMOTER_IT_PAN(employee
						.getCHIEF_PROMOTER_IT_PAN());
				bean.setCHIEF_PROMOTER_MAIL_ID(employee
						.getCHIEF_PROMOTER_MAIL_ID());
				bean.setCHIEF_PROMOTER_CONTACT_NUMBER(employee
						.getCHIEF_PROMOTER_CONTACT_NUMBER());
				bean.setFILE_PATH(employee.getFILE_PATH());
				// bean.setBUSINESS_PRODUCT(employee.getBUSINESS_PRODUCT());//
				bean.setCATEGORY(employee.getCATEGORY());//
				bean.setCITY(employee.getCITY());//
				bean.setCONSTITUTION(employee.getCONSTITUTION());//
				bean.setDISTRICT(employee.getDISTRICT());//
				bean.setFIRST_DISBURSEMENT_DATE(employee
						.getFIRST_DISBURSEMENT_DATE());//
				bean.setFLAG(employee.getFLAG());//
				bean.setHANDICAPPED(employee.getHANDICAPPED());//
				bean.setINDUSTRY_NATURE(employee.getINDUSTRY_NATURE());//
				bean.setINDUSTRY_SECTOR(employee.getINDUSTRY_SECTOR());//
				bean.setLONE_TYPE(employee.getLONE_TYPE());
				bean.setMICRO_SMALL(employee.getMICRO_SMALL());//
				bean.setMINORITY_COMMUNITY(employee.getMINORITY_COMMUNITY());//
				bean.setMSE_ADDRESS(employee.getMSE_ADDRESS());// /
				bean.setMSE_NAME(employee.getMSE_NAME());//
				bean.setNEW_EXISTING_UNIT(employee.getNEW_EXISTING_UNIT());//
				bean.setPORTFOLIO_BASE_YER(employee.getPORTFOLIO_BASE_YER());//
				bean.setPORTFOLIO_NO(employee.getPORTFOLIO_NO());//
				bean.setREMARKS(employee.getREMARKS());//
				bean.setSNCTION_DATE(employee.getSNCTION_DATE());//
				bean.setSTATE(employee.getSTATE());//
				bean.setSTATUS("NCA");//
				bean.setUDYOG_AADHAR_NO(employee.getUDYOG_AADHAR_NO());//
				bean.setUSR_ID(employee.getUSR_ID());//
				bean.setWOMEN(employee.getWOMEN());//
				bean.setQUARTER_NO(employee.getQUARTER_NO());//
				// bean.setDISBURSEMENT_STATUS(employee.getDisbursement_status());//
				bean.setINSERT_DATE_TIME(employee.getINSERT_DATE_TIME());//
				bean.setVERIFIEDSTATUS(employee.getVERIFIEDSTATUS());//
				bean.setPortfolio_Name(employee.getPORTFOLIO_NAME());//
				bean.setNBFC_CHECKER_ID(userId);//
				bean.setRETAIL_TRADE(employee.getRetail_trade());
				bean.setAcceptStatus(acceptStatus);//
				bean.setOUTSTANDING_AMOUNT(employee.getOutstandingAmount());//
				bean.setAADHAR_NUMBER(employee.getAADHAR_NUMBER());

				beans.add(bean);
			}
		}
		return beans;
	}

	private MLIMakerApprovalRejection prepareModel(
			MLIMakerFileUploadBean employeeBean) {
		MLIMakerApprovalRejection employees = new MLIMakerApprovalRejection();

		employees.setLOAN_ACCOUNT_NO(employeeBean.getLOAN_ACCOUNT_NO());//
		employees.setLONE_TYPE(employeeBean.getLONE_TYPE());//
		employees.setSANCTIONED_AMOUNT(employeeBean.getSANCTIONED_AMOUNT());//
		employees.setINTEREST_RATE(employeeBean.getINSERT_RATE());//
		employees.setTENOR_IN_MONTHS(employeeBean.getTENOR_IN_MONTHS());//
		employees.setPINCODE(employeeBean.getPINCODE());//
		employees.setMSE_ITPAN(employeeBean.getMSE_ITPAN());//
		/*
		 * employees.setMSME_REGISTRATION_NO(employeeBean
		 * .getMSME_REGISTRATION_NO());//
		 */employees.setNO_OF_EMPLOYEES(employeeBean.getNO_OF_EMPLOYEES());//
		employees.setPROJECTED_SALES(employeeBean.getPROJECTED_SALES());//
		employees.setPROJECTED_EXPORTS(employeeBean.getPROJECTED_EXPORTS());//
		employees
				.setCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE(employeeBean
						.getCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE());//
		// employees.setFIRST_TIME_CUSTOMER(employeeBean.getFIRST_TIME_CUSTOMER());//
		employees.setCHIEF_PROMOTER_FIRST_NAME(employeeBean
				.getCHIEF_PROMOTER_FIRST_NAME());//
		employees.setCHIEF_PROMOTER_MIDDLE_NAME(employeeBean
				.getCHIEF_PROMOTER_MIDDLE_NAME());
		employees.setCHIEF_PROMOTER_LAST_NAME(employeeBean
				.getCHIEF_PROMOTER_LAST_NAME());
		employees.setCHIEF_PROMOTER_IT_PAN(employeeBean
				.getCHIEF_PROMOTER_IT_PAN());
		employees.setCHIEF_PROMOTER_MAIL_ID(employeeBean
				.getCHIEF_PROMOTER_MAIL_ID());
		employees.setCHIEF_PROMOTER_CONTACT_NUMBER(employeeBean
				.getCHIEF_PROMOTER_CONTACT_NUMBER());
		employees.setFILE_PATH(employeeBean.getFILE_PATH());
		// employees.setBUSINESS_PRODUCT(employeeBean.getBUSINESS_PRODUCT());//
		employees.setCATEGORY(employeeBean.getCATEGORY());//
		employees.setCITY(employeeBean.getCITY());//
		employees.setCONSTITUTION(employeeBean.getCONSTITUTION());//
		employees.setDISTRICT(employeeBean.getDISTRICT());//
		employees.setFIRST_DISBURSEMENT_DATE(employeeBean
				.getFIRST_DISBURSEMENT_DATE());//
		employees.setFLAG(employeeBean.getFLAG());//
		employees.setHANDICAPPED(employeeBean.getHANDICAPPED());//
		employees.setINDUSTRY_NATURE(employeeBean.getINDUSTRY_NATURE());//
		employees.setINDUSTRY_SECTOR(employeeBean.getINDUSTRY_SECTOR());//
		employees.setLONE_TYPE(employeeBean.getLONE_TYPE());
		employees.setMICRO_SMALL(employeeBean.getMICRO_SMALL());//
		employees.setMINORITY_COMMUNITY(employeeBean.getMINORITY_COMMUNITY());//
		employees.setMSE_ADDRESS(employeeBean.getMSE_ADDRESS());// /
		employees.setMSE_NAME(employeeBean.getMSE_NAME());//
		employees.setNEW_EXISTING_UNIT(employeeBean.getNEW_EXISTING_UNIT());//
		employees.setPORTFOLIO_BASE_YER(employeeBean.getPORTFOLIO_BASE_YER());//
		employees.setPORTFOLIO_NO(employeeBean.getPORTFOLIO_NO());//
		employees.setREMARKS(employeeBean.getREMARKS());//
		employees.setSNCTION_DATE(employeeBean.getSNCTION_DATE());//
		employees.setSTATE(employeeBean.getSTATE());//
		employees.setSTATUS("NCA");//
		employees.setUDYOG_AADHAR_NO(employeeBean.getUDYOG_AADHAR_NO());//
		employees.setUSR_ID(employeeBean.getUSR_ID());//
		employees.setWOMEN(employeeBean.getWOMEN());//
		employees.setQUARTER_NO(employeeBean.getQUARTER_NO());//
		// employees.setDISBURSEMENT_STATUS(employeeBean.getDisbursement_status());//
		employees.setINSERT_DATE_TIME(employeeBean.getINSERT_DATE_TIME());//
		employees.setVERIFIEDSTATUS(employeeBean.getVERIFIEDSTATUS());//
		employees.setRETAIL_TRADE(employeeBean.getRetail_trade());
		return employees;
	}

	private List<MLIMakerApprovalRejection> prepareModelListForRejected(
			List<MLIMakerFileUploadBean> employees, String userId) {
		List<MLIMakerApprovalRejection> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<MLIMakerApprovalRejection>();
			MLIMakerApprovalRejection bean = null;
			for (MLIMakerFileUploadBean employee : employees) {
				bean = new MLIMakerApprovalRejection();

				bean.setLOAN_ACCOUNT_NO(employee.getLOAN_ACCOUNT_NO());//
				bean.setLONE_TYPE(employee.getLONE_TYPE());//
				bean.setSANCTIONED_AMOUNT(employee.getSANCTIONED_AMOUNT());//
				bean.setINTEREST_RATE(employee.getINSERT_RATE());//
				bean.setTENOR_IN_MONTHS(employee.getTENOR_IN_MONTHS());//
				bean.setPINCODE(employee.getPINCODE());//
				bean.setMSE_ITPAN(employee.getMSE_ITPAN());//
				// bean.setMSME_REGISTRATION_NO(employee.getMSME_REGISTRATION_NO());//
				bean.setNO_OF_EMPLOYEES(employee.getNO_OF_EMPLOYEES());//
				bean.setPROJECTED_SALES(employee.getPROJECTED_SALES());//
				bean.setPROJECTED_EXPORTS(employee.getPROJECTED_EXPORTS());//
				bean.setCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE(employee
						.getCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE());//
				// bean.setFIRST_TIME_CUSTOMER(employee.getFIRST_TIME_CUSTOMER());//
				bean.setCHIEF_PROMOTER_FIRST_NAME(employee
						.getCHIEF_PROMOTER_FIRST_NAME());//
				bean.setCHIEF_PROMOTER_MIDDLE_NAME(employee
						.getCHIEF_PROMOTER_MIDDLE_NAME());
				bean.setCHIEF_PROMOTER_LAST_NAME(employee
						.getCHIEF_PROMOTER_LAST_NAME());
				bean.setCHIEF_PROMOTER_IT_PAN(employee
						.getCHIEF_PROMOTER_IT_PAN());
				bean.setCHIEF_PROMOTER_MAIL_ID(employee
						.getCHIEF_PROMOTER_MAIL_ID());
				bean.setCHIEF_PROMOTER_CONTACT_NUMBER(employee
						.getCHIEF_PROMOTER_CONTACT_NUMBER());
				bean.setFILE_PATH(employee.getFILE_PATH());
				// bean.setBUSINESS_PRODUCT(employee.getBUSINESS_PRODUCT());//
				bean.setCATEGORY(employee.getCATEGORY());//
				bean.setCITY(employee.getCITY());//
				bean.setCONSTITUTION(employee.getCONSTITUTION());//
				bean.setDISTRICT(employee.getDISTRICT());//
				bean.setFIRST_DISBURSEMENT_DATE(employee
						.getFIRST_DISBURSEMENT_DATE());//
				bean.setFLAG(employee.getFLAG());//
				bean.setHANDICAPPED(employee.getHANDICAPPED());//
				bean.setINDUSTRY_NATURE(employee.getINDUSTRY_NATURE());//
				bean.setINDUSTRY_SECTOR(employee.getINDUSTRY_SECTOR());//
				bean.setLONE_TYPE(employee.getLONE_TYPE());
				bean.setMICRO_SMALL(employee.getMICRO_SMALL());//
				bean.setMINORITY_COMMUNITY(employee.getMINORITY_COMMUNITY());//
				bean.setMSE_ADDRESS(employee.getMSE_ADDRESS());// /
				bean.setMSE_NAME(employee.getMSE_NAME());//
				bean.setNEW_EXISTING_UNIT(employee.getNEW_EXISTING_UNIT());//
				bean.setPORTFOLIO_BASE_YER(employee.getPORTFOLIO_BASE_YER());//
				bean.setPORTFOLIO_NO(employee.getPORTFOLIO_NO());//
				bean.setREMARKS(employee.getREMARKS());//
				bean.setSNCTION_DATE(employee.getSNCTION_DATE());//
				bean.setSTATE(employee.getSTATE());//
				bean.setSTATUS("NRE");//
				bean.setUDYOG_AADHAR_NO(employee.getUDYOG_AADHAR_NO());//
				bean.setUSR_ID(employee.getUSR_ID());//
				bean.setWOMEN(employee.getWOMEN());//
				bean.setQUARTER_NO(employee.getQUARTER_NO());//
				// bean.setDISBURSEMENT_STATUS(employee.getDisbursement_status());//
				bean.setINSERT_DATE_TIME(employee.getINSERT_DATE_TIME());//
				bean.setVERIFIEDSTATUS(employee.getVERIFIEDSTATUS());//
				bean.setRETAIL_TRADE(employee.getRetail_trade());//
				bean.setNBFC_CHECKER_ID(userId);//
				bean.setAADHAR_NUMBER(employee.getAADHAR_NUMBER());
				beans.add(bean);
			}
		}
		return beans;
	}

	private MLIMakerApprovalRejection prepareModelForReject(
			MLIMakerFileUploadBean employeeBean) {
		MLIMakerApprovalRejection employees = new MLIMakerApprovalRejection();

		employees.setLOAN_ACCOUNT_NO(employeeBean.getLOAN_ACCOUNT_NO());//
		employees.setLONE_TYPE(employeeBean.getLONE_TYPE());//
		employees.setSANCTIONED_AMOUNT(employeeBean.getSANCTIONED_AMOUNT());//
		employees.setINTEREST_RATE(employeeBean.getINSERT_RATE());//
		employees.setTENOR_IN_MONTHS(employeeBean.getTENOR_IN_MONTHS());//
		employees.setPINCODE(employeeBean.getPINCODE());//
		employees.setMSE_ITPAN(employeeBean.getMSE_ITPAN());//
		/*
		 * employees.setMSME_REGISTRATION_NO(employeeBean
		 * .getMSME_REGISTRATION_NO());//
		 */employees.setNO_OF_EMPLOYEES(employeeBean.getNO_OF_EMPLOYEES());//
		employees.setPROJECTED_SALES(employeeBean.getPROJECTED_SALES());//
		employees.setPROJECTED_EXPORTS(employeeBean.getPROJECTED_EXPORTS());//
		employees
				.setCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE(employeeBean
						.getCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE());//
		// employees.setFIRST_TIME_CUSTOMER(employeeBean.getFIRST_TIME_CUSTOMER());//
		employees.setCHIEF_PROMOTER_FIRST_NAME(employeeBean
				.getCHIEF_PROMOTER_FIRST_NAME());//
		employees.setCHIEF_PROMOTER_MIDDLE_NAME(employeeBean
				.getCHIEF_PROMOTER_MIDDLE_NAME());
		employees.setCHIEF_PROMOTER_LAST_NAME(employeeBean
				.getCHIEF_PROMOTER_LAST_NAME());
		employees.setCHIEF_PROMOTER_IT_PAN(employeeBean
				.getCHIEF_PROMOTER_IT_PAN());
		employees.setCHIEF_PROMOTER_MAIL_ID(employeeBean
				.getCHIEF_PROMOTER_MAIL_ID());
		employees.setCHIEF_PROMOTER_CONTACT_NUMBER(employeeBean
				.getCHIEF_PROMOTER_CONTACT_NUMBER());
		employees.setFILE_PATH(employeeBean.getFILE_PATH());
		// employees.setBUSINESS_PRODUCT(employeeBean.getBUSINESS_PRODUCT());//
		employees.setCATEGORY(employeeBean.getCATEGORY());//
		employees.setCITY(employeeBean.getCITY());//
		employees.setCONSTITUTION(employeeBean.getCONSTITUTION());//
		employees.setDISTRICT(employeeBean.getDISTRICT());//
		employees.setFIRST_DISBURSEMENT_DATE(employeeBean
				.getFIRST_DISBURSEMENT_DATE());//
		employees.setFLAG(employeeBean.getFLAG());//
		employees.setHANDICAPPED(employeeBean.getHANDICAPPED());//
		employees.setINDUSTRY_NATURE(employeeBean.getINDUSTRY_NATURE());//
		employees.setINDUSTRY_SECTOR(employeeBean.getINDUSTRY_SECTOR());//
		employees.setLONE_TYPE(employeeBean.getLONE_TYPE());
		employees.setMICRO_SMALL(employeeBean.getMICRO_SMALL());//
		employees.setMINORITY_COMMUNITY(employeeBean.getMINORITY_COMMUNITY());//
		employees.setMSE_ADDRESS(employeeBean.getMSE_ADDRESS());// /
		employees.setMSE_NAME(employeeBean.getMSE_NAME());//
		employees.setNEW_EXISTING_UNIT(employeeBean.getNEW_EXISTING_UNIT());//
		employees.setPORTFOLIO_BASE_YER(employeeBean.getPORTFOLIO_BASE_YER());//
		employees.setPORTFOLIO_NO(employeeBean.getPORTFOLIO_NO());//
		employees.setREMARKS(employeeBean.getREMARKS());//
		employees.setSNCTION_DATE(employeeBean.getSNCTION_DATE());//
		employees.setSTATE(employeeBean.getSTATE());//
		employees.setSTATUS("NRE");//
		employees.setUDYOG_AADHAR_NO(employeeBean.getUDYOG_AADHAR_NO());//
		employees.setUSR_ID(employeeBean.getUSR_ID());//
		employees.setWOMEN(employeeBean.getWOMEN());//
		employees.setQUARTER_NO(employeeBean.getQUARTER_NO());//
		// employees.setDISBURSEMENT_STATUS(employeeBean.getDisbursement_status());//
		employees.setINSERT_DATE_TIME(employeeBean.getINSERT_DATE_TIME());//
		employees.setVERIFIEDSTATUS(employeeBean.getVERIFIEDSTATUS());//

		return employees;
	}

	private List<MLIMakerFileUploadBean> prepareEmployeeBeanListForRejection(
			List<MLIMakerApprovalRejection> employees, String remarks) {
		List<MLIMakerFileUploadBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<MLIMakerFileUploadBean>();
			MLIMakerFileUploadBean bean = null;
			for (MLIMakerApprovalRejection employee : employees) {
				bean = new MLIMakerFileUploadBean();
				bean.setLOAN_ACCOUNT_NO(employee.getLOAN_ACCOUNT_NO());//
				bean.setLONE_TYPE(employee.getLONE_TYPE());//
				bean.setSANCTIONED_AMOUNT(employee.getSANCTIONED_AMOUNT());//
				bean.setINSERT_RATE(employee.getINTEREST_RATE());//
				bean.setTENOR_IN_MONTHS(employee.getTENOR_IN_MONTHS());//
				bean.setPINCODE(employee.getPINCODE());//
				bean.setMSE_ITPAN(employee.getMSE_ITPAN());//
				// bean.setMSME_REGISTRATION_NO(employee.getMSME_REGISTRATION_NO());//
				bean.setNO_OF_EMPLOYEES(employee.getNO_OF_EMPLOYEES());//
				bean.setPROJECTED_SALES(employee.getPROJECTED_SALES());//
				bean.setPROJECTED_EXPORTS(employee.getPROJECTED_EXPORTS());//
				bean.setCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE(employee
						.getCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE());//
				// bean.setFIRST_TIME_CUSTOMER(employee.getFIRST_TIME_CUSTOMER());//
				bean.setCHIEF_PROMOTER_FIRST_NAME(employee
						.getCHIEF_PROMOTER_FIRST_NAME());//
				bean.setCHIEF_PROMOTER_MIDDLE_NAME(employee
						.getCHIEF_PROMOTER_MIDDLE_NAME());
				bean.setCHIEF_PROMOTER_LAST_NAME(employee
						.getCHIEF_PROMOTER_LAST_NAME());
				bean.setCHIEF_PROMOTER_IT_PAN(employee
						.getCHIEF_PROMOTER_IT_PAN());
				bean.setCHIEF_PROMOTER_MAIL_ID(employee
						.getCHIEF_PROMOTER_MAIL_ID());
				bean.setCHIEF_PROMOTER_CONTACT_NUMBER(employee
						.getCHIEF_PROMOTER_CONTACT_NUMBER());
				bean.setFILE_PATH(employee.getFILE_PATH());
				// bean.setBUSINESS_PRODUCT(employee.getBUSINESS_PRODUCT());//
				bean.setCATEGORY(employee.getCATEGORY());//
				bean.setCITY(employee.getCITY());//
				bean.setCONSTITUTION(employee.getCONSTITUTION());//
				bean.setDISTRICT(employee.getDISTRICT());//
				bean.setFIRST_DISBURSEMENT_DATE(employee
						.getFIRST_DISBURSEMENT_DATE());//
				bean.setFLAG(employee.getFLAG());//
				bean.setHANDICAPPED(employee.getHANDICAPPED());//
				bean.setINDUSTRY_NATURE(employee.getINDUSTRY_NATURE());//
				bean.setINDUSTRY_SECTOR(employee.getINDUSTRY_SECTOR());//
				bean.setLONE_TYPE(employee.getLONE_TYPE());
				bean.setMICRO_SMALL(employee.getMICRO_SMALL());//
				bean.setMINORITY_COMMUNITY(employee.getMINORITY_COMMUNITY());//
				bean.setMSE_ADDRESS(employee.getMSE_ADDRESS());// /
				bean.setMSE_NAME(employee.getMSE_NAME());//
				bean.setNEW_EXISTING_UNIT(employee.getNEW_EXISTING_UNIT());//
				bean.setPORTFOLIO_BASE_YER(employee.getPORTFOLIO_BASE_YER());//
				bean.setPORTFOLIO_NO(employee.getPORTFOLIO_NO());//
				bean.setREMARKS(remarks);//
				bean.setSNCTION_DATE(employee.getSNCTION_DATE());//
				bean.setSTATE(employee.getSTATE());//
				bean.setSTATUS("NRE");//
				bean.setUDYOG_AADHAR_NO(employee.getUDYOG_AADHAR_NO());//
				bean.setUSR_ID(employee.getUSR_ID());//
				bean.setWOMEN(employee.getWOMEN());//
				bean.setQUARTER_NO(employee.getQUARTER_NO());//
				// bean.setDisbursement_status(employee.getDISBURSEMENT_STATUS());//
				bean.setINSERT_DATE_TIME(employee.getINSERT_DATE_TIME());//
				bean.setVERIFIEDSTATUS(employee.getVERIFIEDSTATUS());//
				bean.setRetail_trade(employee.getRETAIL_TRADE());//
				bean.setAADHAR_NUMBER(employee.getAADHAR_NUMBER());
				beans.add(bean);
			}
		}
		return beans;
	}

	private MLIMakerFileUploadBean prepareEmployeeBeanForRejection(
			String remarks, MLIMakerApprovalRejection employee) {
		MLIMakerFileUploadBean bean = new MLIMakerFileUploadBean();

		bean.setLOAN_ACCOUNT_NO(employee.getLOAN_ACCOUNT_NO());//
		bean.setLONE_TYPE(employee.getLONE_TYPE());//
		bean.setSANCTIONED_AMOUNT(employee.getSANCTIONED_AMOUNT());//
		bean.setINSERT_RATE(employee.getINTEREST_RATE());//
		bean.setTENOR_IN_MONTHS(employee.getTENOR_IN_MONTHS());//
		bean.setPINCODE(employee.getPINCODE());//
		bean.setMSE_ITPAN(employee.getMSE_ITPAN());//
		// bean.setMSME_REGISTRATION_NO(employee.getMSME_REGISTRATION_NO());//
		bean.setNO_OF_EMPLOYEES(employee.getNO_OF_EMPLOYEES());//
		bean.setPROJECTED_SALES(employee.getPROJECTED_SALES());//
		bean.setPROJECTED_EXPORTS(employee.getPROJECTED_EXPORTS());//
		bean.setCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE(employee
				.getCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE());//
		// bean.setFIRST_TIME_CUSTOMER(employee.getFIRST_TIME_CUSTOMER());//
		bean.setCHIEF_PROMOTER_FIRST_NAME(employee
				.getCHIEF_PROMOTER_FIRST_NAME());//
		bean.setCHIEF_PROMOTER_MIDDLE_NAME(employee
				.getCHIEF_PROMOTER_MIDDLE_NAME());
		bean.setCHIEF_PROMOTER_LAST_NAME(employee.getCHIEF_PROMOTER_LAST_NAME());
		bean.setCHIEF_PROMOTER_IT_PAN(employee.getCHIEF_PROMOTER_IT_PAN());
		bean.setCHIEF_PROMOTER_MAIL_ID(employee.getCHIEF_PROMOTER_MAIL_ID());
		bean.setCHIEF_PROMOTER_CONTACT_NUMBER(employee
				.getCHIEF_PROMOTER_CONTACT_NUMBER());
		bean.setFILE_PATH(employee.getFILE_PATH());
		// bean.setBUSINESS_PRODUCT(employee.getBUSINESS_PRODUCT());//
		bean.setCATEGORY(employee.getCATEGORY());//
		bean.setCITY(employee.getCITY());//
		bean.setCONSTITUTION(employee.getCONSTITUTION());//
		bean.setDISTRICT(employee.getDISTRICT());//
		bean.setFIRST_DISBURSEMENT_DATE(employee.getFIRST_DISBURSEMENT_DATE());//
		bean.setFLAG(employee.getFLAG());//
		bean.setHANDICAPPED(employee.getHANDICAPPED());//
		bean.setINDUSTRY_NATURE(employee.getINDUSTRY_NATURE());//
		bean.setINDUSTRY_SECTOR(employee.getINDUSTRY_SECTOR());//
		bean.setLONE_TYPE(employee.getLONE_TYPE());
		bean.setMICRO_SMALL(employee.getMICRO_SMALL());//
		bean.setMINORITY_COMMUNITY(employee.getMINORITY_COMMUNITY());//
		bean.setMSE_ADDRESS(employee.getMSE_ADDRESS());// /
		bean.setMSE_NAME(employee.getMSE_NAME());//
		bean.setNEW_EXISTING_UNIT(employee.getNEW_EXISTING_UNIT());//
		bean.setPORTFOLIO_BASE_YER(employee.getPORTFOLIO_BASE_YER());//
		bean.setPORTFOLIO_NO(employee.getPORTFOLIO_NO());//
		bean.setREMARKS(remarks);//
		bean.setSNCTION_DATE(employee.getSNCTION_DATE());//
		bean.setSTATE(employee.getSTATE());//
		bean.setSTATUS("NRE");//
		bean.setUDYOG_AADHAR_NO(employee.getUDYOG_AADHAR_NO());//
		bean.setUSR_ID(employee.getUSR_ID());//
		bean.setWOMEN(employee.getWOMEN());//
		bean.setQUARTER_NO(employee.getQUARTER_NO());//
		// bean.setDisbursement_status(employee.getDISBURSEMENT_STATUS());//
		bean.setINSERT_DATE_TIME(employee.getINSERT_DATE_TIME());//
		bean.setVERIFIEDSTATUS(employee.getVERIFIEDSTATUS());//
		bean.setRetail_trade(employee.getRETAIL_TRADE());//

		return bean;
	}

	private List<MLIMakerFileUploadBean> prepareEmployeeBeanList(
			List<MLIMakerApprovalRejection> employees) {// ////////////////////////////////
		List<MLIMakerFileUploadBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<MLIMakerFileUploadBean>();
			MLIMakerFileUploadBean bean = null;
			for (MLIMakerApprovalRejection employee : employees) {
				bean = new MLIMakerFileUploadBean();

				bean.setLOAN_ACCOUNT_NO(employee.getLOAN_ACCOUNT_NO());//
				bean.setLONE_TYPE(employee.getLONE_TYPE());//
				bean.setSANCTIONED_AMOUNT(employee.getSANCTIONED_AMOUNT());//
				bean.setINSERT_RATE(employee.getINTEREST_RATE());//
				bean.setTENOR_IN_MONTHS(employee.getTENOR_IN_MONTHS());//
				bean.setPINCODE(employee.getPINCODE());//
				bean.setMSE_ITPAN(employee.getMSE_ITPAN());//
				// bean.setMSME_REGISTRATION_NO(employee.getMSME_REGISTRATION_NO());//
				bean.setNO_OF_EMPLOYEES(employee.getNO_OF_EMPLOYEES());//
				System.out.println("employee.getPROJECTED_SALES()"+employee.getPROJECTED_SALES());
				System.out.println("employee.getPROJECTED_EXPORTS()"+employee.getPROJECTED_EXPORTS());
				bean.setPROJECTED_SALES(employee.getPROJECTED_SALES());//
				//System.out.println("employee.getPROJECTED_EXPORTS()"+employee.getPROJECTED_EXPORTS());
				bean.setPROJECTED_EXPORTS(employee.getPROJECTED_EXPORTS());//
				//System.out.println("employee.getPROJECTED_EXPORTS()"+employee.getPROJECTED_EXPORTS());
				bean.setCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE(employee
						.getCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE());//
				// bean.setFIRST_TIME_CUSTOMER(employee.getFIRST_TIME_CUSTOMER());//
				bean.setCHIEF_PROMOTER_FIRST_NAME(employee
						.getCHIEF_PROMOTER_FIRST_NAME());//
				bean.setCHIEF_PROMOTER_MIDDLE_NAME(employee
						.getCHIEF_PROMOTER_MIDDLE_NAME());
				bean.setCHIEF_PROMOTER_LAST_NAME(employee
						.getCHIEF_PROMOTER_LAST_NAME());
				bean.setCHIEF_PROMOTER_IT_PAN(employee
						.getCHIEF_PROMOTER_IT_PAN());
				bean.setCHIEF_PROMOTER_MAIL_ID(employee
						.getCHIEF_PROMOTER_MAIL_ID());
				bean.setCHIEF_PROMOTER_CONTACT_NUMBER(employee
						.getCHIEF_PROMOTER_CONTACT_NUMBER());
				bean.setFILE_PATH(employee.getFILE_PATH());
				// bean.setBUSINESS_PRODUCT(employee.getBUSINESS_PRODUCT());//
				bean.setCATEGORY(employee.getCATEGORY());//
				bean.setCITY(employee.getCITY());//
				bean.setCONSTITUTION(employee.getCONSTITUTION());//
				bean.setDISTRICT(employee.getDISTRICT());//
				bean.setFIRST_DISBURSEMENT_DATE(employee
						.getFIRST_DISBURSEMENT_DATE());//
				bean.setFLAG(employee.getFLAG());//
				bean.setHANDICAPPED(employee.getHANDICAPPED());//
				bean.setINDUSTRY_NATURE(employee.getINDUSTRY_NATURE());//
				bean.setINDUSTRY_SECTOR(employee.getINDUSTRY_SECTOR());//
				bean.setLONE_TYPE(employee.getLONE_TYPE());
				bean.setMICRO_SMALL(employee.getMICRO_SMALL());//
				bean.setMINORITY_COMMUNITY(employee.getMINORITY_COMMUNITY());//
				bean.setMSE_ADDRESS(employee.getMSE_ADDRESS());// /
				bean.setMSE_NAME(employee.getMSE_NAME());//
				bean.setNEW_EXISTING_UNIT(employee.getNEW_EXISTING_UNIT());//
				bean.setPORTFOLIO_BASE_YER(employee.getPORTFOLIO_BASE_YER());//
				bean.setPORTFOLIO_NO(employee.getPORTFOLIO_NO());//
				bean.setREMARKS(employee.getREMARKS());//
				bean.setSNCTION_DATE(employee.getSNCTION_DATE());//
				bean.setSTATE(employee.getSTATE());//
				bean.setSTATUS(employee.getSTATUS());//
				bean.setUDYOG_AADHAR_NO(employee.getUDYOG_AADHAR_NO());//
				bean.setUSR_ID(employee.getUSR_ID());//
				bean.setWOMEN(employee.getWOMEN());//
				bean.setQUARTER_NO(employee.getQUARTER_NO());//
				// bean.setDisbursement_status(employee.getDISBURSEMENT_STATUS());//
				bean.setINSERT_DATE_TIME(employee.getINSERT_DATE_TIME());//
				bean.setVERIFIEDSTATUS(employee.getVERIFIEDSTATUS());//
				bean.setPORTFOLIO_NAME(employee.getPortfolio_Name());//
				bean.setRetail_trade(employee.getRETAIL_TRADE());//
				bean.setOutstandingAmount(employee.getOUTSTANDING_AMOUNT());//
				bean.setAADHAR_NUMBER(employee.getAADHAR_NUMBER());
				beans.add(bean);
			}
		}
		return beans;
	}

	private MLIMakerFileUploadBean prepareEmployeeBean(
			MLIMakerApprovalRejection employee) {
		MLIMakerFileUploadBean bean = new MLIMakerFileUploadBean();

		bean.setLOAN_ACCOUNT_NO(employee.getLOAN_ACCOUNT_NO());//
		bean.setLONE_TYPE(employee.getLONE_TYPE());//
		bean.setSANCTIONED_AMOUNT(employee.getSANCTIONED_AMOUNT());//
		bean.setINSERT_RATE(employee.getINTEREST_RATE());//
		bean.setTENOR_IN_MONTHS(employee.getTENOR_IN_MONTHS());//
		bean.setPINCODE(employee.getPINCODE());//
		bean.setMSE_ITPAN(employee.getMSE_ITPAN());//
		// bean.setMSME_REGISTRATION_NO(employee.getMSME_REGISTRATION_NO());//
		bean.setNO_OF_EMPLOYEES(employee.getNO_OF_EMPLOYEES());//
		bean.setPROJECTED_SALES(employee.getPROJECTED_SALES());//
		bean.setPROJECTED_EXPORTS(employee.getPROJECTED_EXPORTS());//
		bean.setCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE(employee
				.getCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE());//
		// bean.setFIRST_TIME_CUSTOMER(employee.getFIRST_TIME_CUSTOMER());//
		bean.setCHIEF_PROMOTER_FIRST_NAME(employee
				.getCHIEF_PROMOTER_FIRST_NAME());//
		bean.setCHIEF_PROMOTER_MIDDLE_NAME(employee
				.getCHIEF_PROMOTER_MIDDLE_NAME());
		bean.setCHIEF_PROMOTER_LAST_NAME(employee.getCHIEF_PROMOTER_LAST_NAME());
		bean.setCHIEF_PROMOTER_IT_PAN(employee.getCHIEF_PROMOTER_IT_PAN());
		bean.setCHIEF_PROMOTER_MAIL_ID(employee.getCHIEF_PROMOTER_MAIL_ID());
		bean.setCHIEF_PROMOTER_CONTACT_NUMBER(employee
				.getCHIEF_PROMOTER_CONTACT_NUMBER());
		bean.setFILE_PATH(employee.getFILE_PATH());
		// bean.setBUSINESS_PRODUCT(employee.getBUSINESS_PRODUCT());//
		bean.setCATEGORY(employee.getCATEGORY());//
		bean.setCITY(employee.getCITY());//
		bean.setCONSTITUTION(employee.getCONSTITUTION());//
		bean.setDISTRICT(employee.getDISTRICT());//
		bean.setFIRST_DISBURSEMENT_DATE(employee.getFIRST_DISBURSEMENT_DATE());//
		bean.setFLAG(employee.getFLAG());//
		bean.setHANDICAPPED(employee.getHANDICAPPED());//
		bean.setINDUSTRY_NATURE(employee.getINDUSTRY_NATURE());//
		bean.setINDUSTRY_SECTOR(employee.getINDUSTRY_SECTOR());//
		bean.setLONE_TYPE(employee.getLONE_TYPE());
		bean.setMICRO_SMALL(employee.getMICRO_SMALL());//
		bean.setMINORITY_COMMUNITY(employee.getMINORITY_COMMUNITY());//
		bean.setMSE_ADDRESS(employee.getMSE_ADDRESS());// /
		bean.setMSE_NAME(employee.getMSE_NAME());//
		bean.setNEW_EXISTING_UNIT(employee.getNEW_EXISTING_UNIT());//
		bean.setPORTFOLIO_BASE_YER(employee.getPORTFOLIO_BASE_YER());//
		bean.setPORTFOLIO_NO(employee.getPORTFOLIO_NO());//
		bean.setREMARKS(employee.getREMARKS());//
		bean.setSNCTION_DATE(employee.getSNCTION_DATE());//
		bean.setSTATE(employee.getSTATE());//
		bean.setSTATUS(employee.getSTATUS());//
		bean.setUDYOG_AADHAR_NO(employee.getUDYOG_AADHAR_NO());//
		bean.setUSR_ID(employee.getUSR_ID());//
		bean.setWOMEN(employee.getWOMEN());//
		bean.setQUARTER_NO(employee.getQUARTER_NO());//
		// bean.setDisbursement_status(employee.getDISBURSEMENT_STATUS());//
		bean.setINSERT_DATE_TIME(employee.getINSERT_DATE_TIME());//
		bean.setVERIFIEDSTATUS(employee.getVERIFIEDSTATUS());//
		bean.setRetail_trade(employee.getRETAIL_TRADE());//

		return bean;
	}

	private UserBean prepareUserBean() {
		UserBean bean = new UserBean();

		bean.setEmail("Saurav");
		bean.setfName("   ");
		bean.setlName("Saurav");
		bean.setMiddalName("Saurav");
		bean.setMobileNumber("Saurav");
		bean.setPhoneNumber(null);

		return bean;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("redirect:/SVLogin.html");
	}

	private User prepareModel(UserBean employeeBean, String mem_bnk_id,
			String mem_brn_id, String mem_zne_id, String userId) {
		User employee = new User();
		employee.setUsr_id(excelValidator.userIDGenrator(
				employeeBean.getfName(), employeeBean.getlName()));
		employee.setfName(employeeBean.getfName());
		employee.setmName(employeeBean.getMiddalName());
		employee.setlName(employeeBean.getlName());
		employee.setIsActive("1");
		employee.setEmail(employeeBean.getEmail());
		//employee.setHint_question(employeeBean.getHint_question());
		//employee.setHint_ans(employeeBean.getHint_ans());
		employee.setMobileNumber(employeeBean.getMobileNumber());
		//employee.setPassword(excelValidator.passwordGenrator());
		employee.setPassword("welcome123");   // added by ohab 22/07/2020
		employee.setPhoneNumber(employeeBean.getPhoneNumber());
		employee.setPhone_code(employeeBean.getPhoneCode());
		employee.setUser_designation(employeeBean.getuDesignation());
		employee.setCreatedBy(userId);
		employee.setMem_bnk_id(mem_bnk_id);
		employee.setMem_brn_id(mem_brn_id);
		employee.setMem_zne_id(mem_zne_id);
		employee.setLOGIN_STATUS("N");
		employee.setForgetAttemptCounter(0);
		employee.setLoginAttemptCounter(0);
		
		return employee;
	}

	private PortfolioBatchApp protfolioApproval(MLICheckerBean employeeBean) {
		PortfolioBatchApp employee = new PortfolioBatchApp();
		employee.setArp_ref_no(employeeBean.getAppRefNo());
		return employee;
	}

	private List<UserBean> prepareListofBean(List<User> employees,
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
					
					String userType=employee.getUserType();
					if(userType.equals("Maker")) {
						bean.setUserType("Maker");
					}else {
						bean.setUserType("Checker");	
					}
					//bean.setUserType(employee.getUserType());
					bean.setuDesignation(employee.getUser_designation());
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

	private List<MLIMakerApplicationLodgementBean> prepareListofBeanforPortfolio(
			List<MLIMakerApprovalRejection> employees) throws ParseException {
		List<MLIMakerApplicationLodgementBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<MLIMakerApplicationLodgementBean>();
			MLIMakerApplicationLodgementBean bean = null;
			for (MLIMakerApprovalRejection employee : employees) {
				bean = new MLIMakerApplicationLodgementBean();
				// bean.setUserId(employee.getUserId());
				bean.setLOAN_ACCOUNT_NO(employee.getLOAN_ACCOUNT_NO());//
				bean.setLONE_TYPE(employee.getLONE_TYPE());//
				bean.setSANCTIONED_AMOUNT(employee.getSANCTIONED_AMOUNT());//
				bean.setINSERT_RATE(employee.getINTEREST_RATE());//
				bean.setTENOR_IN_MONTHS(employee.getTENOR_IN_MONTHS());//
				bean.setPINCODE(employee.getPINCODE());//
				bean.setMSE_ITPAN(employee.getMSE_ITPAN());//
				// bean.setMSME_REGISTRATION_NO(employee.getMSME_REGISTRATION_NO());//
				bean.setNO_OF_EMPLOYEES(employee.getNO_OF_EMPLOYEES());//
				bean.setPROJECTED_SALES(employee.getPROJECTED_SALES());//
				bean.setPROJECTED_EXPORTS(employee.getPROJECTED_EXPORTS());//
				bean.setCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE(employee
						.getCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE());//
				// bean.setFIRST_TIME_CUSTOMER(employee.getFIRST_TIME_CUSTOMER());//
				bean.setCHIEF_PROMOTER_FIRST_NAME(employee
						.getCHIEF_PROMOTER_FIRST_NAME());//
				bean.setCHIEF_PROMOTER_MIDDLE_NAME(employee
						.getCHIEF_PROMOTER_MIDDLE_NAME());
				bean.setCHIEF_PROMOTER_LAST_NAME(employee
						.getCHIEF_PROMOTER_LAST_NAME());
				bean.setCHIEF_PROMOTER_IT_PAN(employee
						.getCHIEF_PROMOTER_IT_PAN());
				bean.setCHIEF_PROMOTER_MAIL_ID(employee
						.getCHIEF_PROMOTER_MAIL_ID());
				bean.setCHIEF_PROMOTER_CONTACT_NUMBER(employee
						.getCHIEF_PROMOTER_CONTACT_NUMBER());
				bean.setFILE_PATH(employee.getFILE_PATH());
				// bean.setBUSINESS_PRODUCT(employee.getBUSINESS_PRODUCT());//
				bean.setCATEGORY(employee.getCATEGORY());//
				bean.setCITY(employee.getCITY());//
				bean.setCONSTITUTION(employee.getCONSTITUTION());//
				bean.setDISTRICT(employee.getDISTRICT());//

				DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
				if (employee.getFIRST_DISBURSEMENT_DATE() != null) {
					String strDate1 = dateFormat1.format(employee
							.getFIRST_DISBURSEMENT_DATE());

					bean.setFIRST_DISBURSEMENT_DATE(strDate1);
				} else {
					bean.setFIRST_DISBURSEMENT_DATE("");
				}

				//
				bean.setFLAG(employee.getFLAG());//
				bean.setHANDICAPPED(employee.getHANDICAPPED());//
				bean.setINDUSTRY_NATURE(employee.getINDUSTRY_NATURE());//
				bean.setINDUSTRY_SECTOR(employee.getINDUSTRY_SECTOR());//
				bean.setLONE_TYPE(employee.getLONE_TYPE());
				bean.setMICRO_SMALL(employee.getMICRO_SMALL());//
				bean.setMINORITY_COMMUNITY(employee.getMINORITY_COMMUNITY());//
				bean.setMSE_ADDRESS(employee.getMSE_ADDRESS());// /
				bean.setMSE_NAME(employee.getMSE_NAME());//
				bean.setNEW_EXISTING_UNIT(employee.getNEW_EXISTING_UNIT());//
				bean.setPORTFOLIO_BASE_YER(employee.getPORTFOLIO_BASE_YER());//
				bean.setPORTFOLIO_NO(employee.getPORTFOLIO_NO());//
				bean.setREMARKS(employee.getREMARKS());//

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String strDate = dateFormat.format(employee.getSNCTION_DATE());

				bean.setSNCTION_DATE(strDate);//

				bean.setSTATE(employee.getSTATE());//
				bean.setSTATUS(employee.getSTATUS());//
				bean.setUDYOG_AADHAR_NO(employee.getUDYOG_AADHAR_NO());//
				bean.setUSR_ID(employee.getUSR_ID());//
				bean.setWOMEN(employee.getWOMEN());//
				bean.setQUARTER_NO(employee.getQUARTER_NO());//

				bean.setPartialSecurityFlag(employee.getPARTIAL_SECURITY_FLAG());
				bean.setGuaranteeAmount(employee.getGUARANTEE_AMOUNT());
				
				String Amt= employee.getCOLLETRAL_SECURITY_AMOUNT();
				
				if (Amt== null) {
					bean.setColletralSecurityAmount("");
				} else {
					bean.setColletralSecurityAmount(employee
							.getCOLLETRAL_SECURITY_AMOUNT());
				}

				bean.setOutstandingAmount(employee.getOUTSTANDING_AMOUNT());
				bean.setRETAIL_TRADE(employee.getRETAIL_TRADE());
				bean.setAADHAR_NUMBER(employee.getAADHAR_NUMBER());
				beans.add(bean);
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

	private List<PortfolioBatchBean> portfoliAppRefList(
			List<PortfolioBatchApp> employees) {
		List<PortfolioBatchBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<PortfolioBatchBean>();
			PortfolioBatchBean bean = null;
			for (PortfolioBatchApp employee : employees) {
				bean = new PortfolioBatchBean();
				bean.setArp_ref_no(employee.getPortfolio_name());
				bean.setStatus(employee.getStatus());
				beans.add(bean);
			}
		}
		return beans;
	}

	private List<PortfolioBatchBean> portfoliAppRefNum(List<String> employees) {
		List<PortfolioBatchBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<PortfolioBatchBean>();
			PortfolioBatchBean bean = null;
			for (String employee : employees) {
				bean = new PortfolioBatchBean();
				bean.setArp_ref_no(employee);
				beans.add(bean);
			}
		}
		return beans;
	}

	private List<DistrictBean> prepareDistrictListofBean(
			List<District> employees) {
		List<DistrictBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<DistrictBean>();
			DistrictBean bean = null;
			for (District employee : employees) {
				bean = new DistrictBean();
				bean.setSte_code(employee.getSte_code());
				bean.setDst_name(employee.getDst_name());
				beans.add(bean);
			}
		}
		return beans;
	}

	private UserBean prepareEmployeeBean(User employee) {
		UserBean bean = new UserBean();
		bean.setEmail(employee.getEmail());
		// bean.setFax(employee.getFax());
		bean.setfName(employee.getfName());
		bean.setlName(employee.getlName());
		// bean.setPhone(employee.getPhone());
		bean.setState(employee.getState());
		// bean.setUserType(employee.getUserType());

		return bean;

	}

	private UserBean prepareModelForDelete(User userDetails,
			UserService employees) {
		UserBean bean = new UserBean();

		try {

			bean.setUserID(userDetails.getUsr_id());
			bean.setDeleteFName(userDetails.getfName());
			bean.setDeleteMiddalName(userDetails.getmName());
			bean.setDeleteLName(userDetails.getlName());
			bean.setDeleteemail(userDetails.getEmail());
			bean.setDeletephoneNumber(userDetails.getPhoneNumber());
			bean.setDeletemobileNumber(userDetails.getMobileNumber());
			bean.setDeleteuserType(userDetails.getUserType());
			// if (userDetails.getUserType().equals("A")) {
			// bean.setStatus("Active");
			// } else {
			//
			// bean.setStatus("Deactive");
			// }

			MLIName MLIName = employees.getMLIName(userDetails.getMem_bnk_id());
			bean.setDeleteMliName(MLIName.getMliLName());
			System.out.println("BNK ID" + userDetails.getMem_bnk_id());

		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return bean;
	}

	// Added by say 22 nov
	// 2018--------------------------------------------------------
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public ModelAndView GetDeleteUser(
			@ModelAttribute("command") UserBean employeeBean,
			BindingResult result, Model modelMsg, HttpSession session,
			HttpServletRequest request) throws Exception {
		// System.out.println("ID......................"+userId);
		// List<State> stateList = stateService.listStates();
		log.info("Reset MLI Registration Page***********************************************************************************************************");

		Map<String, Object> model = new HashMap<String, Object>();

		String userId = employeeBean.getUserID();
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		System.out.println("DATE...." + df.format(dateobj));
		String date = df.format(dateobj);

		employeeService.addEmployeeHistory1(userId, date);
		employeeService.UpdateUprflag(userId);
		// System.out.println("user deleted id------"+userId);
		employeeService.deleteUser(userId);
		model.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList", userActivityService.getActivity(
				"CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList", userActivityService.getActivity(
				"CGTMSEMAKER", "Receipt_Payments"));

		// model.put("actList",
		// userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));

		model.put("actName",
				userActivityService.getActivityName("CGTMSEMAKER", "add"));// Added
																			// by
																			// Say
																			// 31
																			// Jan19

		model.put("homePage", "cgtmseMakerHome");
		request.setAttribute("message", "MLI User " + userId
				+ "Successfully Deleted..");
		// model.put("UserDetails",ClearprepareModelForDelete(employeeService));

		// return new ModelAndView("redirect:/add.html", model);
		return new ModelAndView("redirect:/deleteUserSuccess.html");

	}

	@RequestMapping(value = "/deleteUserSuccess", method = RequestMethod.GET)
	public ModelAndView deleteUserSuccess(
			@ModelAttribute("command") UserBean employeeBean,
			BindingResult result, Model modelMsg, HttpSession session)
			throws Exception {
		// System.out.println("ID......................"+userId);
		// List<State> stateList = stateService.listStates();
		log.info("Reset MLI Registration Page***********************************************************************************************************");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList", userActivityService.getActivity(
				"CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList", userActivityService.getActivity(
				"CGTMSEMAKER", "Receipt_Payments"));

		// model.put("actList",
		// userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));

		model.put("actName",
				userActivityService.getActivityName("CGTMSEMAKER", "add"));// Added
																			// by
																			// Say
																			// 31
																			// Jan19

		model.put("homePage", "cgtmseMakerHome");

		modelMsg.addAttribute("message", "MLI User Successfully Deleted..");
		return new ModelAndView("userDeleteEditPage", model);

	}

	@RequestMapping(value = "BackUser", method = RequestMethod.POST)
	public ModelAndView GetBackUser(
			@ModelAttribute("command") UserBean employeeBean,
			BindingResult result, Model modelMsg, HttpSession session)
			throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList", userActivityService.getActivity(
				"CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList", userActivityService.getActivity(
				"CGTMSEMAKER", "Receipt_Payments"));

		// model.put("actList",
		// userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));

		model.put("actName",
				userActivityService.getActivityName("CGTMSEMAKER", "add"));// Added
																			// by
																			// Say
																			// 31
																			// Jan19

		model.put("homePage", "cgtmseMakerHome"); // say
													// 25june19
		model.put(
				"employees",
				prepareListofBean(employeeService.listEmployeess(),
						employeeService));
		model.put("stateList",
				prepareStateListofBean(stateService.listStates("CCA")));

		return new ModelAndView("userRegistration", model);

	}

	private UserRolePrivelage prepareMLIRoleModel(UserRoleBean userRoleBean,
			UserActivityService userActivityService) throws Exception {
		UserRolePrivelage userRolePrivelage = new UserRolePrivelage();

		userRolePrivelage.setUser_id(userRoleBean.getUserName());
		userRolePrivelage.setUpr_flag("A");
		if (userRoleBean.getUserType().equals("Checker")) {
			userRolePrivelage.setPrv_id(15);
		} else if (userRoleBean.getUserType().equals("Maker")) {
			userRolePrivelage.setPrv_id(14);
		}

		if ((userRoleBean.getUserType() != "" && userRoleBean.getUserName() != "")
				|| (userRoleBean.getUserType() != null && userRoleBean
						.getUserName() != null)) {
			userActivityService.setUserType(userRoleBean.getUserType(),
					userRoleBean.getUserName());
		}
		if (userActivityService.getMaxNumber() == 0) {

			userRolePrivelage.setS_no(1);

		} else {

			userRolePrivelage.setS_no(userActivityService.getMaxNumber() + 1);
		}

		return userRolePrivelage;
	}

	// ended--------------------------------------------------------------------------
	// DownExcelFle For User
	// Registration------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/userRegistrationDetailDownload", method = RequestMethod.GET)
	public ModelAndView userRegistrationDetailDownload(
			@ModelAttribute("command") User userDetails, BindingResult result,
			Model modelMsg, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		try {
			downloadFileDirPath=context.getRealPath("Download");
			
			String filePath = downloadFileDirPath + File.separator
					+ downloadFileName;
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
			XSSFSheet sheet = hwb
					.createSheet("UserRegistrationDetailDownLoadedFile");
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

			XSSFCell cell6 = rowhead.createCell(6);
			cell6.setCellStyle(style);
			cell6.setCellValue("phoneCode");// Done 9

			XSSFCell cell7 = rowhead.createCell(7);
			cell7.setCellStyle(style);
			cell7.setCellValue("phone No");// Done 10

			XSSFCell cell8 = rowhead.createCell(8);
			cell8.setCellStyle(style);
			cell8.setCellValue("Email");

			int index = 1;
			int sno = 0;
			Iterator<User> itr2 = list.iterator();
			while (itr2.hasNext()) {
				User obj1 = (User) itr2.next();
				sno++;
				XSSFRow row = sheet.createRow((short) index);
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
				row.createCell((short) 6).setCellValue(obj1.getPhone_code());// Done
																				// 7
				row.createCell((short) 7).setCellValue(obj1.getPhoneNumber());// Done
																				// 8
				row.createCell((short) 8).setCellValue(obj1.getEmail());// Done
																		// 9
				index++;
			}
			FileOutputStream fileOut = new FileOutputStream(filePath);
			hwb.write(fileOut);
			fileOut.close();
			ModelAndView model = new ModelAndView("userRegistration");
			System.out.println("File downloaded");
			modelMsg.addAttribute("message", "File DownLoaded Successfully ");
			// model.addObject("excelFileDownLoadSuccessfully","File DownLoaded Successfully in this location F:/ExcelReports/nbfcUserRoleExcelFile.xls.");
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

	// ended--------------------------------------------------------------------------

	@ExceptionHandler(CustomExceptionHandler.class)
	public ModelAndView handleCustomException(CustomExceptionHandler ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		// added by say 6 feb-----------------------
		map.put("adminlist",
				userActivityService.getActivity("ADMIN", "System_Admin"));

		// end---------------------------------------------------------------------------------------
		// model.put("actList",
		// userActivityService.getActivity("ADMIN", "User_Activity"));
		map.put("homePage", "adminHome");
		ModelAndView model = new ModelAndView("customException", map);
		model.addObject("customException", ex.getMessage());
		return model;

	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		// added by say 6 feb-----------------------
		map.put("adminlist",
				userActivityService.getActivity("ADMIN", "System_Admin"));

		// end---------------------------------------------------------------------------------------
		// model.put("actList",
		// userActivityService.getActivity("ADMIN", "User_Activity"));
		map.put("homePage", "adminHome");
		ModelAndView model = new ModelAndView("exception", map);
		model.addObject("exception", ex.getClass());
		return model;

	}

	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithException(ArithmeticException ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		// added by say 6 feb-----------------------
		map.put("adminlist",
				userActivityService.getActivity("ADMIN", "System_Admin"));

		// end---------------------------------------------------------------------------------------
		// model.put("actList",
		// userActivityService.getActivity("ADMIN", "User_Activity"));
		map.put("homePage", "adminHome");
		ModelAndView model = new ModelAndView("exception", map);
		model.addObject("exception", ex.getCause());
		return model;

	}

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullException(NullPointerException ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		// added by say 6 feb-----------------------
		map.put("adminlist",
				userActivityService.getActivity("ADMIN", "System_Admin"));
		// model.put("actList", userActivityService.getActivity("ADMIN",
		// "User_Activity"));
		map.put("homePage", "adminHome");
		ModelAndView model = new ModelAndView("exception", map);
		model.addObject("exception", ex.getCause());
		return model;

	}

}
