package com.nbfc.common.utility.method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.nbfc.bean.GetMLINameBean;
import com.nbfc.bean.UserDashboardBean;
import com.nbfc.model.CGTMSECheckerForBatchApprovalGetStatus;
import com.nbfc.model.CGTMSEMakerForBatchApprovalGetStatus;
import com.nbfc.model.Login;
import com.nbfc.model.UserActivity;
import com.nbfc.model.UserDashboardVmodel;
import com.nbfc.service.ASFGenerationBulkUploadService;
import com.nbfc.service.CGTMSECheckerForBatchApprovalService;
import com.nbfc.service.CGTMSEMakerForBatchApprovalGetStatusService;
import com.nbfc.service.LoginService;
import com.nbfc.service.NBFCUserReportService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;
import com.vaannila.validator.UserValidator;

public class MenuUtils {
	static Login login = null;
	@Autowired
	ASFGenerationBulkUploadService ASFGenerationService;
	@Autowired
	private static LoginService loginService;
	@Autowired
	static
	UserService userService;
	@Autowired
	static
	UserActivityService userActivityService;
	@Autowired
	static
	NBFCUserReportService nbfcUserReportService;
	@Autowired
	private static CGTMSEMakerForBatchApprovalGetStatusService cgtmseMakerForBatchApprovalGetStatusService;
	@Autowired
	private static CGTMSECheckerForBatchApprovalService cgtmseCheckerForBatchApprovalService;
	static UserDashboardBean userDashboardBean;
	static GetMLINameBean getMLINameBean;
	UserValidator userValidator = new UserValidator();
	String userIdAndPasswordIsCorrect = null;
	static List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
	@SuppressWarnings("unused")
	public static Map<String, Object> prepareMenu(HttpSession session,
			String Role,String userId) {
		Map<String, Object> modelAct = new HashMap<String, Object>();
			if (Role.equals("CMAKER")) {
				session.setAttribute("uRole", "CMAKER");
				CGTMSEMakerForBatchApprovalGetStatus cgtmseMakerForBatchApprovalGetStatus = new CGTMSEMakerForBatchApprovalGetStatus();
				String statusNCA = null;
				int statusNCACount = 0;///////////////
				Integer subPortfolioDtlNo = 0;
				Object totalNoOfUploadedFile = 0;
				String status = null;


				List<Object> list1 = new ArrayList<Object>();
				String status1 = "NCA";
				cgtmseMakerForBatchApprovalGetStatus.setStatus(status1);
				totalNoOfUploadedFile = cgtmseMakerForBatchApprovalGetStatusService
						.getNCAStatusCountBasedOnNCAStatus(cgtmseMakerForBatchApprovalGetStatus);
				modelAct.put("countTotalNoOfUploadedFileKey", totalNoOfUploadedFile);
				modelAct.put("statusKey", status1);

				// end------------------------------------------------------------------------------------------------------------------------------------------------------------
				modelAct.put("guaranteelist", userActivityService.getActivity("CGTMSEMAKER", "Registration"));
				modelAct.put("applicationList",
						userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
				modelAct.put("RPaymentList", userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
				modelAct.put("GMaintainlist", userActivityService.getActivity("CGTMSEMAKER", "Guarantee_Maintenance"));
				List<UserActivity> userReportMainMenuList = userActivityService.getReport("CGTMSEMAKER", "User_Report");
				List<UserActivity> summaryReportList = userActivityService.getReport("summaryReportAllUser", "User_Report_Summary");
				//Added by sarita on 10/11/2021 --- START
				List<UserActivity> summaryReportStateActivityList = userActivityService.getReport("CGTMSEMAKER", "User_Report_Summary_State_Activity");
				userReportMainMenuList.addAll(summaryReportStateActivityList);
				//Added by sarita on 10/11/2021 --- END
				//Added by sarita on 03/01/2022 --- START
				List<UserActivity> disbursementDetailsData = userActivityService.getReport("CGTMSEMAKER", "disbursement_details_data");
				userReportMainMenuList.addAll(disbursementDetailsData);
				//Added by sarita on 03/01/2022 --- END
				userReportMainMenuList.addAll(summaryReportList);
				modelAct.put("repList", userReportMainMenuList);

				modelAct.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
				// modelAct.put("CBMFList", userActivityService.getActivity("CGTMSEMAKER",
				// "Claim_Bank_Mandate"));
				modelAct.put("actNameHome", "CGTMSE");// Added by Say 31 Jan19
				modelAct.put("homePage", "cgtmseMakerHome");
				//modelAct.put("urtFileUploadCgtmseMaker", userActivityService.getActivity("CGTMSEMAKER", "urt_upload_cgtmse"));//Vivek 01-03-2021
				modelAct.put("claimUploadCGTMSE", userActivityService.getActivity("CGTMSECHECKER", "claim_upload_cgtmse"));//added by Umar kr 21/01/021			
				System.out.println("forword successCGTMSEMAKERPage");
				modelAct.put("bankMandateFormCgtmscApproval", userActivityService.getActivity("CGTMSECHECKER", "bankMandateForm_Cgtmsc_Approval"));//added by vivek 24-01-2021
				List<UserActivity> urtMakerUploadList = userActivityService.getActivity("CGTMSEMAKER",
						"urt_upload_cgtmse");//vivek
				 
				List<UserActivity> claimPaymentList = userActivityService.getActivity("CGTMSEMAKER",
						"claim_Payment_Initition");
				List<UserActivity> claimRecoveryReport = userActivityService.getActivity("CGTMSECHECKER", "recovery_report");
				List<UserActivity> appropriationManual = userActivityService.getActivity("CGTMSEMAKER", "appropriation_manual");
				List<UserActivity> disbursementDetails = userActivityService.getActivity("CGTMSEMAKER", "disbursement_details");//Sarita
				List<UserActivity> claimAprvdList = userActivityService.getActivity("CGTMSEMAKER","claim-approved-data");//Added by Sarita 23032022
				urtMakerUploadList.addAll(claimRecoveryReport);
				urtMakerUploadList.addAll(claimPaymentList);
				urtMakerUploadList.addAll(appropriationManual);
				urtMakerUploadList.addAll(disbursementDetails);
				urtMakerUploadList.addAll(claimAprvdList);
				modelAct.put("urtMakerUpload", urtMakerUploadList);
			}
			if (Role.equals("CCHECKER")) {
				CGTMSECheckerForBatchApprovalGetStatus cgtmseCheckerForBatchApprovalGetStatus = new CGTMSECheckerForBatchApprovalGetStatus();
				session.setAttribute("uRole", "CCHECKER");// added by say
				// ---------------Added by sayali
				// 18march19---------------------------------------------
				// Get All the status
				List<Object> list1 = new ArrayList<Object>();
				String statusCMA1 = "CMA";
				Long noOfUploadedExcelFile = (long) 0;
				Integer counterNOfUploadedExcelFile = 0;
				Long noOfUploadedExcelFileCount = (long) 0;
				cgtmseCheckerForBatchApprovalGetStatus.setStatus(statusCMA1);
				int counter = 0;
				Long noOfFiles = (long) 0;
				List<Object> listObj = cgtmseCheckerForBatchApprovalService
						.getCMAStatusCount(cgtmseCheckerForBatchApprovalGetStatus);
				if (listObj.size() != 0) {
					Iterator<Object> itr1 = listObj.iterator();
					while (itr1.hasNext()) {
						Object[] obj1 = (Object[]) itr1.next();
						noOfFiles = (Long) obj1[1];
						noOfUploadedExcelFile = (Long) obj1[3];
						counterNOfUploadedExcelFile++;
					}
				}

				modelAct.put("statusCMACountKey", counterNOfUploadedExcelFile);
				modelAct.put("statusCMAKey", statusCMA1);
				modelAct.put("adminlist", userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
				modelAct.put("guaranteelist", userActivityService.getActivity("CGTMSECHECKER", "Registration"));
				modelAct.put("applicationList",
						userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
				modelAct.put("RPaymentList", userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
				modelAct.put("GMaintainlist",
						userActivityService.getActivity("CGTMSECHECKER", "Guarantee_Maintenance"));
				modelAct.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
				modelAct.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
				List<UserActivity> userReportMainMenuList = userActivityService.getReport("CGTMSECHECKER", "User_Report");
				List<UserActivity> summaryReportList = userActivityService.getReport("summaryReportAllUser", "User_Report_Summary");
				userReportMainMenuList.addAll(summaryReportList);
				//Added by sarita on 10/11/2021 --- START
				List<UserActivity> summaryReportStateActivityList = userActivityService.getReport("CGTMSEMAKER", "User_Report_Summary_State_Activity");
				userReportMainMenuList.addAll(summaryReportStateActivityList);
				//Added by sarita on 10/11/2021 --- END
				//Added by sarita on 03/01/2022 --- START
				List<UserActivity> disbursementDetailsData = userActivityService.getReport("CGTMSEMAKER", "disbursement_details_data");
				userReportMainMenuList.addAll(disbursementDetailsData);
				//Added by sarita on 03/01/2022 --- END
				modelAct.put("repList", userReportMainMenuList);
				
				modelAct.put("actNameHome", "CGTMSE");
				modelAct.put("claimUploadCGTMSE", userActivityService.getActivity("CGTMSECHECKER", "claim_upload_cgtmse")); //added by umar kr 21/01/021
				modelAct.put("bankMandateFormCgtmscApproval", userActivityService.getActivity("CGTMSECHECKER", "bankMandateForm_Cgtmsc_Approval"));//added by vivek 24-01-2021
				List<UserActivity> utrCheckerList = userActivityService.getActivity("CGTMSECHECKER", "urt_upload_cgtmse_Approval");
				List<UserActivity> claimPaymentList = userActivityService.getActivity("CGTMSECHECKER", "claim_Payment_Approval");
				List<UserActivity> claimRecoveryReport = userActivityService.getActivity("CGTMSECHECKER", "recovery_report");
				List<UserActivity> appropriationManual = userActivityService.getActivity("CGTMSEMAKER", "appropriation_manual");
				//List<UserActivity> disbursementDetails = userActivityService.getActivity("CGTMSEMAKER", "disbursement_details");//Sarita
				List<UserActivity> claimAprvdList = userActivityService.getActivity("CGTMSEMAKER","claim-approved-data");//Added by Sarita 23032022
				utrCheckerList.addAll(claimPaymentList);
				utrCheckerList.addAll(claimRecoveryReport);
			    utrCheckerList.addAll(appropriationManual);
			    utrCheckerList.addAll(claimAprvdList);
				//utrCheckerList.addAll(disbursementDetails);
				modelAct.put("urtMakerUpload", utrCheckerList);
			}
			if (Role.equals("NMAKER")) {
				session.setAttribute("uRole", "NMAKER");// added by say
				userDashboardBean = new UserDashboardBean();
				userDashboardBean = createUserDashboardBean(
						userActivityService.getUserDashboardDetails(userId));
				System.out.println("user Dashboard "+userDashboardBean);
				if (userDashboardBean != null) {
					modelAct.put("userDashboardBean", userDashboardBean);
				}
				// comment 1/7/20
			//	modelAct.put("rows", rows);
				// added by say 6 feb-----------------------
				modelAct.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				modelAct.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Registration"));
				modelAct.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
				modelAct.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
				modelAct.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
				modelAct.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
				modelAct.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
				modelAct.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
				modelAct.put("claimUpload",
						userActivityService.getActivity("NBFCMAKER", "Claim_Upload")); // added umar 29/12/020
				modelAct.put("bankMandateFormMli", userActivityService.getActivity("NBFCMAKER", "bank_Mandate_Form_Mli")); // added umar 29/12/020
				List<UserActivity> claimPaymentList = userActivityService.getActivity("CGTMSEMAKER",
						"claim_Payment_Initition");
				List<UserActivity> claimRecoveryReport = userActivityService.getActivity("CGTMSECHECKER", "recovery_report");
				List<UserActivity> recoveryReportUploadList = userActivityService.getActivity("NBFCMAKER", "recovery_upload");
				List<UserActivity> claimPaymentNBFCList = userActivityService.getActivity("NBFCMAKER", "claim_payment_report");
				//List<UserActivity> disbursementDetails = userActivityService.getActivity("CGTMSEMAKER", "disbursement_details");//Sarita 
				claimPaymentList.addAll(claimRecoveryReport);
				claimPaymentList.addAll(recoveryReportUploadList);
				claimPaymentList.addAll(claimPaymentNBFCList);
				//claimPaymentList.addAll(disbursementDetails);
				modelAct.put("urtMakerUpload", claimPaymentList);
			}
			if (Role.equals("NCHECKER")) {
				session.setAttribute("uRole", "NCHECKER");
				rows = nbfcUserReportService.getUserDashboardDetails(userId);
				getMLINameBean = nbfcUserReportService.getMliDetails(userId);
				session.setAttribute("mliName", getMLINameBean.getMliName());
				userDashboardBean = createUserDashboardBean(
						userActivityService.getUserDashboardDetails(userId));
				if (userDashboardBean != null) {
					modelAct.put("userDashboardBean", userDashboardBean);
				}
				modelAct.put("rows", rows);
				modelAct.put("adminlist", userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
				modelAct.put("guaranteelist", userActivityService.getActivity("NBFCCHECKER", "Registration"));
				modelAct.put("applicationList",
						userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
				modelAct.put("RPaymentList", userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
				modelAct.put("GMaintainlist", userActivityService.getActivity("NBFCCHECKER", "Guarantee_Maintenance"));

				modelAct.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
				modelAct.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
				modelAct.put("claimUpload",
						userActivityService.getActivity("NBFCCHECKER", "Claim_Upload")); // added umar 29/12/020
				modelAct.put("bankMandateForm", userActivityService.getActivity("NBFCCHECKER", "bank_Mandate_Form")); // added umar 29/12/020
				List<UserActivity> claimPaymentList = userActivityService.getActivity("CGTMSEMAKER",
						"claim_Payment_Initition");
				List<UserActivity> claimRecoveryReport = userActivityService.getActivity("CGTMSECHECKER", "recovery_report");
				List<UserActivity> recoveryReportUploadList = userActivityService.getActivity("NBFCCHECKER", "recovery_upload_approval");
				List<UserActivity> appropriationManual = userActivityService.getActivity("CGTMSEMAKER", "appropriation_manual");
				//List<UserActivity> disbursementDetails = userActivityService.getActivity("CGTMSEMAKER", "disbursement_details");//Sarita
				claimPaymentList.addAll(claimRecoveryReport);
				claimPaymentList.addAll(recoveryReportUploadList);
				//claimPaymentList.addAll(disbursementDetails);
				modelAct.put("urtMakerUpload", claimPaymentList);
				modelAct.put("repList", userActivityService.getReport("NBFCCHECKER", "User_Report"));
				modelAct.put("actNameHome", "CGTMSE");// Added by Say 31 Jan19
				modelAct.put("homePage", "nbfcCheckerHome");

	}
			return modelAct;
		}
	
	private static UserDashboardBean createUserDashboardBean(UserDashboardVmodel userDashboardVmodel) {
		// TODO Auto-generated method stub

		UserDashboardBean udb = new UserDashboardBean();
		if (userDashboardVmodel != null) {
			udb.setCck_t_dan(userDashboardVmodel.getCck_t_dan());
			udb.setCck_t_dan_all_approval(userDashboardVmodel.getCck_t_dan_all_approval());
			udb.setCmk_t_batch_approval(userDashboardVmodel.getCmk_t_batch_approval());
			udb.setCmk_t_batch_approved(userDashboardVmodel.getCmk_t_batch_approved());
			udb.setCmk_t_batch_pending(userDashboardVmodel.getCmk_t_batch_pending());
			udb.setCmk_t_batch_reject(userDashboardVmodel.getCmk_t_batch_reject());
			udb.setCmk_t_exposure(userDashboardVmodel.getCmk_t_exposure());
			udb.setCmk_t_exposure_approved(userDashboardVmodel.getCmk_t_exposure_approved());
			udb.setCmk_t_exposure_count(userDashboardVmodel.getCmk_t_exposure_count());
			udb.setCmk_t_exposure_pending(userDashboardVmodel.getCmk_t_exposure_pending());
			udb.setCmk_t_exposure_reject(userDashboardVmodel.getCmk_t_exposure_reject());
			udb.setCmk_t_fridge_portfolio(userDashboardVmodel.getCmk_t_fridge_portfolio());
			udb.setCmk_t_gurantee_fee(userDashboardVmodel.getCmk_t_gurantee_fee());
			udb.setCmk_t_mli_approve(userDashboardVmodel.getCmk_t_mli_approve());
			udb.setCmk_t_mli_pending(userDashboardVmodel.getCmk_t_mli_pending());
			udb.setCmk_t_mli_reg(userDashboardVmodel.getCmk_t_mli_reg());
			udb.setCmk_t_mli_reject(userDashboardVmodel.getCmk_t_mli_reject());
			udb.setCmk_t_portfolio(userDashboardVmodel.getCmk_t_portfolio());
			udb.setCmk_t_used_exposure(userDashboardVmodel.getCmk_t_used_exposure());
			udb.setMli_id(userDashboardVmodel.getMli_id());
			udb.setNck_dan_allocation_approval(userDashboardVmodel.getNck_dan_allocation_approval());
			udb.setNck_dan_payment_approval(userDashboardVmodel.getNck_dan_payment_approval());
			udb.setNck_file_approval(userDashboardVmodel.getNck_file_approval());
			udb.setNkm_total_upload_file(userDashboardVmodel.getNkm_total_upload_file());
			udb.setNmk_dan_pay_approval(userDashboardVmodel.getNmk_dan_pay_approval());
			udb.setNmk_exposure_lmt(userDashboardVmodel.getNmk_exposure_lmt());
			udb.setNmk_fridge_portfolio(userDashboardVmodel.getNmk_fridge_portfolio());
			udb.setNmk_running_portfolio(userDashboardVmodel.getNmk_running_portfolio());
			udb.setNmk_t_gurantee_fee(userDashboardVmodel.getNmk_t_gurantee_fee());
			udb.setNmk_total_approv(userDashboardVmodel.getNmk_total_approv());
			udb.setNmk_total_pending(userDashboardVmodel.getNmk_total_pending());
			udb.setNmk_total_portfolio(userDashboardVmodel.getNmk_total_portfolio());
			udb.setNmk_total_reject(userDashboardVmodel.getNmk_total_reject());
			udb.setNmk_unused_exposure(userDashboardVmodel.getNmk_unused_exposure());
			udb.setNmk_used_exposure(userDashboardVmodel.getNmk_used_exposure());

		}

		return udb;
	}
}
