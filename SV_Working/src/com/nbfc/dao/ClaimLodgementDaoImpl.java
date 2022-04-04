package com.nbfc.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nbfc.bean.ClaimLodgementBean;
import com.raistudies.domain.CustomExceptionHandler;

import java.text.DecimalFormat;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.nbfc.bean.ASFGenerationDetailBean;
import com.nbfc.bean.ClaimApprovalBean;
import com.nbfc.bean.ClaimLodgementBean;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.bean.NbfcAppropriationBean;
import com.nbfc.model.CGTMSECheckerForBatchApprovalAndRejectionSumbission;
import com.nbfc.model.ClaimDetailsModel;
import com.nbfc.model.ClaimLodgeBlobModel;
import com.nbfc.model.ClaimReturnReasonsModel;
import com.nbfc.model.MLIName;
import com.nbfc.model.documentModel;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.sql.rowset.serial.SerialBlob;

@Repository("ClaimLodgementDao")
public class ClaimLodgementDaoImpl implements ClaimLodgementDao {

	@Autowired
	
	SessionFactory sessionFactory;
	
	String zoneId=null;
	String branchId=null;
	
	@Override
	public List<ClaimLodgementBean> getClaimLoadgmentDetails(
			String loginUserMemId) {
		ClaimLodgementBean objClaimLodgementBean = null;

		System.out
				.println("getMliBorrowerNpaDtlsBeforClaimLodgement method called to procedure inside DAOImpl==");
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		@SuppressWarnings("unused")
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		ArrayList<ClaimLodgementBean> ClaimBeanList = new ArrayList<ClaimLodgementBean>();

		BigDecimal bg;
		try {
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			CallableStatement callableStatement = (CallableStatement) con
					.prepareCall("{ call PROC_GET_CLAIM_DETAIL_LIST(?,?,?) } ");
			callableStatement.setString(1, loginUserMemId);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			resultset = (ResultSet) callableStatement.getObject(3);
			while (resultset.next()) {
				objClaimLodgementBean = new ClaimLodgementBean();
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date claimdate = resultset.getDate(2);
				objClaimLodgementBean.setDateOfClaimLoadge(dateFormat
						.format(claimdate));

				objClaimLodgementBean.setClaimRefNo((resultset.getString(1)));
				objClaimLodgementBean.setUnitName((resultset.getString(3)));
				// objClaimLodgementBean.setRemarks((resultset.getString(4)));
				objClaimLodgementBean.setLatestOsAmt((resultset.getDouble(5)));
				objClaimLodgementBean.setRemarks("Enter Remarks");
				objClaimLodgementBean.setFirstInstallClaim((resultset
						.getDouble(6)));
				objClaimLodgementBean
						.setChkListStatus((resultset.getString(7)));
				objClaimLodgementBean
				.setClaimStatus((resultset.getString(8)));

				ClaimBeanList.add(objClaimLodgementBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ClaimBeanList;
	}

	@Override
	public ClaimLodgementBean getClaimRefNoDetails(String claimRefNo) {
		// TODO Auto-generated method stub
		ClaimLodgementBean objClaimLodgementBean = null;

		System.out
				.println("getClaimRefNoDetails method called to procedure inside DAOImpl==");
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		List<Object> dataList = null;
		DecimalFormat formatter;

		BigDecimal bg;
		try {
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			CallableStatement callableStatement = (CallableStatement) con
					.prepareCall("{ call PROC_GET_CLAIM_DETAILS(?,?,?) } ");
			callableStatement.setString(1, claimRefNo);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			resultset = (ResultSet) callableStatement.getObject(3);
			while (resultset.next()) {
				objClaimLodgementBean = new ClaimLodgementBean();
				objClaimLodgementBean.setCgpan((resultset.getString("CGPAN")));
				objClaimLodgementBean.setMemberId((resultset.getString(1)));
				objClaimLodgementBean.setLendingNbfcName((resultset
						.getString("LONG_NAME")));
				objClaimLodgementBean
						.setCity((resultset.getString("MEM_CITY")));
				objClaimLodgementBean.setAddress((resultset
						.getString("MEM_ADDRESS")));
				objClaimLodgementBean.setDistrict((resultset
						.getString("MEM_DISTRICT_NAME")));
				objClaimLodgementBean.setState((resultset
						.getString("MEM_STATE_NAME")));
				objClaimLodgementBean.setEmail((resultset
						.getString("MEM_EMAIL")));
				objClaimLodgementBean.setTelephoneNo((resultset
						.getString("MEM_PHONE_NUMBER")));
				objClaimLodgementBean.setGstinNo((resultset
						.getString("GSTIN_NO")));
				objClaimLodgementBean.setNameOfBorrower((resultset
						.getString("MSE_NAME")));
				objClaimLodgementBean.setAdressOfBorrower((resultset
						.getString("MSE_ADDRESS")));
				objClaimLodgementBean.setCityOfBorrower((resultset
						.getString("CITY")));
				objClaimLodgementBean.setDistrictOfBorrower((resultset
						.getString("DISTRICT")));
				objClaimLodgementBean.setStateOfBorrower((resultset
						.getString("STATE")));
				objClaimLodgementBean.setPincodeOfBorrower((resultset
						.getString("PINCODE")));
				objClaimLodgementBean.setLatestOsGuranteeAmtVStr(decimalFormat
						.format(resultset.getDouble("LATEST_OS_AMT")));
				objClaimLodgementBean.setGuarantee_Amt_str(decimalFormat
						.format(resultset.getDouble("GUARANTEE_AMOUNT")));
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date Npadate = resultset.getDate("NPA_EFFECTIVE_DT");
				objClaimLodgementBean.setDateOfNPA(dateFormat.format(Npadate));
				objClaimLodgementBean.setReasonOfNPA((resultset
						.getString("NPA_REASONS_TURNING_NPA")));
				objClaimLodgementBean.setLatestOsAmtVStr(decimalFormat
						.format(resultset.getDouble("LATEST_OS_AMT")));

				// objClaimLodgementBean.setTotalOsAmtStr(decimalFormat.format(resultset.getDouble("NPA_OUTSTANDING_AMT_ON_NPA_DT")));
				objClaimLodgementBean.setLandValueStr(decimalFormat
						.format(resultset.getDouble("SANCTION_LAND_VAL")));
				String buildingValStr = decimalFormat.format(resultset
						.getDouble("SANCTION_BUILDING_VAL"));
				objClaimLodgementBean.setBuildingValueStr(buildingValStr);
				objClaimLodgementBean.setPlanetValueStr(decimalFormat
						.format(resultset.getDouble("SANCTION_PLANET_VAL")));
				objClaimLodgementBean
						.setOtherAssetValueStr(decimalFormat.format(resultset
								.getDouble("SANCTION_OTHER_ASSET_VAL")));
				objClaimLodgementBean
						.setCurrentAssetValueStr(decimalFormat.format(resultset
								.getDouble("SANCTION_OTHER_ASSET_VAL")));
				objClaimLodgementBean.setOthersValueStr(decimalFormat
						.format(resultset.getDouble("SANCTION_OTHER_VAL")));
				objClaimLodgementBean
						.setTotalSecuritydetailsStr(decimalFormat
								.format(resultset
										.getDouble("SANCTION_TOTAL_SECURITY")));
				objClaimLodgementBean.setNetworthOfPromotorStr(decimalFormat
						.format(resultset.getDouble("SANCTION_NETWORTH")));
				objClaimLodgementBean.setReductionReason(decimalFormat
						.format(resultset.getDouble("SANCTION_NETWORTH")));
				objClaimLodgementBean.setLandValue1Str(decimalFormat
						.format(resultset.getDouble("NPA_LAND_VAL")));
				objClaimLodgementBean.setBuildingValue1Str(decimalFormat
						.format(resultset.getDouble("NPA_BUILDING_VAL")));
				objClaimLodgementBean.setPlanetValue1Str(decimalFormat
						.format(resultset.getDouble("NPA_PLANET_VAL")));
				objClaimLodgementBean.setOtherAssetValue1Str(decimalFormat
						.format(resultset.getDouble("NPA_OTHER_ASSET_VAL")));
				objClaimLodgementBean.setCurrentAssetValue1Str(decimalFormat
						.format(resultset.getDouble("NPA_OTHER_VAL")));
				objClaimLodgementBean.setOthersValue1Str(decimalFormat
						.format(resultset.getDouble("NPA_OTHER_VAL")));
				objClaimLodgementBean.setTotalSecuritydetails1Str(decimalFormat
						.format(resultset.getDouble("NPA_TOTAL_SECURITY")));
				objClaimLodgementBean.setNetworthOfPromotor1Str(decimalFormat
						.format(resultset.getDouble("NPA_NETWORTH")));
				objClaimLodgementBean.setReductionReason1(
						(resultset.getString("NPA_REDUCTION_REASON")));
				objClaimLodgementBean.setTotalOsAmtStr(decimalFormat
						.format(resultset.getDouble("CLAIM_ELIGABLE_AMT")));
				System.out.println("1==="
						+ resultset.getString("GURANTEE_COVERAGE"));
				objClaimLodgementBean.setGuaranteeCovStr((resultset
						.getString("GURANTEE_COVERAGE")));
				objClaimLodgementBean.setClaimRefNo(resultset
						.getString("CLAIM_REF_NO"));
				objClaimLodgementBean.setDealingOfficerName(resultset
						.getString("DEALING_OFFICER_NAME"));
				objClaimLodgementBean.setWilfulDefaulter(resultset
						.getString("WILFUL_DEFAULTER"));
				objClaimLodgementBean.setFraudAcc(resultset
						.getString("ACC_CLASSIFIED_FAULT"));
				objClaimLodgementBean.setEnquiryConcluded(resultset
						.getString("ENQUIRY_CONCLUDED"));
				objClaimLodgementBean.setMliReported(resultset
						.getString("INVOLVEMENT_MLI_STAFF_REPORTED"));

				if(resultset.getDate("DATE_OF_DEMAND_NOTICE_ISSUE")!=null){
				Date demandDate = resultset
						.getDate("DATE_OF_DEMAND_NOTICE_ISSUE");
				objClaimLodgementBean.setDateOfNotice(dateFormat
						.format(demandDate));
				
				}else{
					objClaimLodgementBean.setDateOfNotice(null);
				}

				objClaimLodgementBean.setForum(resultset.getString("FORUM"));
				objClaimLodgementBean.setLocationOfForum(resultset
						.getString("FORUM_LOCATION"));
				objClaimLodgementBean.setSuitNo(resultset
						.getString("SUIT_REG_NO"));
				objClaimLodgementBean.setSuitDate(resultset
						.getString("RESON_FILLING_SUIT"));

				Date sarfesidate = resultset.getDate("DATE_SARFAESI_ACT");
				if (sarfesidate != null) {
					objClaimLodgementBean.setDateOfSarfaesi(dateFormat
							.format(sarfesidate));
				} else {
					objClaimLodgementBean.setDateOfSarfaesi(null);
				}
				objClaimLodgementBean.setClaimAmt(resultset
						.getDouble("AMOUNT_CLAIMED_DEMAND"));
				objClaimLodgementBean.setAnySubsidyInvolved(resultset
						.getString("INVOLVED_SUBSIDY"));
				objClaimLodgementBean.setSubsidyReceived(resultset
						.getString("SUBSIDY_RECEIVED_AFTER_NPA"));
				objClaimLodgementBean.setSubsidyAdjust(resultset
						.getString("SUBSIDY_AGAINST_INTREST_DUES"));

				Date subsidyCreditdt = resultset.getDate("SUBSIDY_CREDIT_DATE");
				if (subsidyCreditdt != null) {
					objClaimLodgementBean.setDateOfSubsidy(dateFormat
							.format(subsidyCreditdt));
				} else {
					objClaimLodgementBean.setDateOfSubsidy(null);
				}
				objClaimLodgementBean.setSubsidyAmt((resultset
						.getDouble("SUBSIDY_AMOUNT")));

				objClaimLodgementBean.setRecovery(resultset
						.getDouble("RECOVERY_AMOUNT"));
				objClaimLodgementBean.setModeRecovery(resultset
						.getString("RECOVERY_MODE"));
				objClaimLodgementBean.setOsAmtClaim((resultset
						.getDouble("OUTSTANDING_AMOUNT_CLAIM")));
				objClaimLodgementBean.setOsAmtClaimGCPStr(decimalFormat.format(
						resultset.getDouble("OUTSTANDING_AMOUNT_CLAIM"))
						.toString());
				objClaimLodgementBean.setNpaRecoveryFlag(resultset
						.getString("ENSURED_INCLUSION_OF_RECEIPT"));
				objClaimLodgementBean.setConfirmRecoveryFlag(resultset
						.getString("CONFIRM_FEEDING_VALUE_RECOVERY"));
				objClaimLodgementBean
						.setEligableAmtClaimStr(decimalFormat.format(resultset
								.getDouble("CLAIM_ELIGIBILITY_AMOUNT")));
				objClaimLodgementBean
						.setFirstInstallClaimStr(decimalFormat.format(resultset
								.getDouble("FIRST_INSTALLMENT_CLAIM")));
				objClaimLodgementBean.setFinancialPositionComments(resultset
						.getString("MLIS_COMMENT"));
				objClaimLodgementBean.setCreditSupport(resultset
						.getString("MLI_PROVIDE_SUPPORT"));
				objClaimLodgementBean.setBankFacilityDtls(resultset
						.getString("PROVIED_DETAILS_OF_BANK"));
				objClaimLodgementBean
						.setRemarks(resultset.getString("REMARKS"));
				objClaimLodgementBean.setFinancialAssistanceDtls(resultset
						.getString("FINALCIAL_ASSISTANCE_MINI"));
				objClaimLodgementBean.setLandValue2Str((resultset
						.getString("LANDVALUE2")));
				objClaimLodgementBean.setBuildingValue2Str((resultset
						.getString("BUILDINGVALUE2")));
				objClaimLodgementBean.setPlanetValue2Str((resultset
						.getString("PLANETVALUE2")));
				objClaimLodgementBean.setLandValue2Str((resultset
						.getString("LANDVALUE2")));
				objClaimLodgementBean.setOtherAssetValue2Str((resultset
						.getString("OHTERASSETVALUE2")));
				objClaimLodgementBean.setOtherAssetValue2Str((resultset
						.getString("OTHERSVALUE2")));
				objClaimLodgementBean.setCurrentAssetValueStr((resultset
						.getString("CURRENTASSETVALUE2")));
				objClaimLodgementBean.setOthersValue2Str((resultset
						.getString("OTHERSVALUE2")));
				objClaimLodgementBean.setTotalSecuritydetails2Str((resultset
						.getString("TOTALSECURITYDETAILS2")));
				objClaimLodgementBean.setNetworthOfPromotor2Str((resultset
						.getString("NETWORTHOFPROMOTOR2")));
				objClaimLodgementBean.setReductionReason2((resultset.getString("REDUCTIONREASON2")));
				objClaimLodgementBean.setResonFillingSuit((resultset
						.getString("RESON_FILLING_SUIT")));
				objClaimLodgementBean.setDealingOfficerNO((resultset
						.getString("DEALING_OFFICER_NUMBER")));
				objClaimLodgementBean.setFirstInstallClaim((resultset
						.getDouble("FIRST_INSTALLMENT_CLAIM")));
				objClaimLodgementBean.setLoanAccNo((resultset
						.getString("LOAN_ACCOUNT_NO")));
				objClaimLodgementBean.setNbfcMakerClaimLodgRemarks((resultset
						.getString("NBFC_MAKER_LODG_REMARKS")));
				objClaimLodgementBean.setInternalRating1((resultset
						.getString("INTERNAL_RATING")));
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objClaimLodgementBean;
	}

	public ClaimLodgementBean updateStatusClaimLodgeApprovedReturn(
			String userId, Map<String, Object> claimStatusMapObj, String Urole) {
		Map<String,String> mapObj=null;
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		Connection conn = session.connection();
		CallableStatement callStmt = null;
		int dataUpdated = 0;
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		int count = 1;
		String success = "NoUpdated";
		ClaimLodgementBean bean = null;
		bean = new ClaimLodgementBean();
		System.out.println("claimStatusMapObj :" + claimStatusMapObj);
		Iterator Itr = claimStatusMapObj.keySet().iterator();
		String Status = "";
		String refmsg="";
		int x=0;
		while (Itr.hasNext()) {
			
			String key = (String) Itr.next();
			ClaimApprovalBean claim = (ClaimApprovalBean) claimStatusMapObj.get(key);

			System.out.println("Key : " + key + "   Value : "
					+ claim.getMLI_STATUS() + " Remarks:"
					+ claim.getMLI_REMARK() + "FINAL_SETTELE_AMT:"
					+ claim.getFINAL_SETTELEMENT_AMT()
					+ claim.getACTUAL_CLAIM_AMT() + "ACTUAL_CLAIM_AMT:");

			
			
		
			if (Urole.equals("CCHECKER")) {
				Double Final_claim_amt =Double.parseDouble(claim.getFINAL_SETTELEMENT_AMT()); 
				Double Actual_claim_amt =Double.parseDouble(claim.getACTUAL_CLAIM_AMT()); 
				System.out.println("Final_claim_amt++"+Final_claim_amt);
				if (claim.getMLI_STATUS().equals("Accept")) {
					Status = claim.getMLI_STATUS();
					//bean.setMsg("Claim Approved  : " + key);
					//refmsg  = refmsg+","+key;
					bean.setMsg("Claim Approved Successfully ");
				} else if (claim.getMLI_STATUS().equals("Return")) {

					Status = claim.getMLI_STATUS();
					refmsg  = refmsg+","+key;
					bean.setRMsg("Claim Returned  Successfully");
				} else if (claim.getMLI_STATUS().equals("Select")) {
					Status = claim.getMLI_STATUS();
				}
				if (!Status.equals("Select")) {

					try {
						callStmt = conn.prepareCall("{call PROC_APPROVE_RETURN_CGS_CLAIM(?,?,?,?,?,?,?)}");
					callStmt.setString(1, key);
					callStmt.setString(2, Status);
					callStmt.setString(3, userId);
					callStmt.setString(4, claim.getMLI_REMARK());
					callStmt.setString(5, Urole);
					callStmt.setDouble(6, Final_claim_amt);
					callStmt.setDouble(7, Actual_claim_amt);
					
					dataUpdated = callStmt.executeUpdate();
					System.out.println("dataUpdated ==" + dataUpdated);
//					conn.close();
//					callStmt.close();
					success = "Updated";
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/*Query query = sessionFactory
							.getCurrentSession()
							.createQuery(
									" UPDATE ClaimDetailsModel SET STATUS = :status,CGTMSE_CHECKER_ID=:userId,CGTMSE_CK_REMARK=:remark ,CGTMSE_CHECKER_DATE=SYSDATE WHERE CLAIM_REF_NO=:claimRefNo");
					query.setParameter("claimRefNo", key);
					query.setParameter("status", Status);
					query.setParameter("userId", userId);
					query.setParameter("remark", claim.getMLI_REMARK());*/
					// query.setParameter("sysdate", SYSDATE);
					/*int result = query.executeUpdate();*/
				}
			} else if (Urole.equals("NCHECKER")) {
				if (claim.getMLI_STATUS().equals("Accept")) {
					// Status="NCCA";
					//Status = "NE";
					Status = claim.getMLI_STATUS();
					/*if(refmsg.equals("")){
						refmsg=key;
					}
					refmsg=refmsg + "," + key;*/
					
					bean.setMsg("Claim Approved  Successfully");
					
				
				} else if (claim.getMLI_STATUS().equals("Return")) {
					// Status="NCCR";
					//Status = "CR";
					Status = claim.getMLI_STATUS();
					bean.setRMsg("Claim Returned Successfully ");
				} else if (claim.getMLI_STATUS().equals("Select")) {
					// Status="NMCL";
					//Status = "MR";
					Status = claim.getMLI_STATUS();
				}

				if (!Status.equals("Select")) {
					
					try {
					callStmt = conn.prepareCall("{call PROC_APPROVE_RETURN_NBFC_CLAIM(?,?,?,?,?)}");
					
					callStmt.setString(1, key);
					callStmt.setString(2, Status);
					callStmt.setString(3, userId);
					callStmt.setString(4, claim.getMLI_REMARK());
					callStmt.setString(5, Urole);
					dataUpdated = callStmt.executeUpdate();
					
					
					/*mapObj=new HashMap<String,String>();
					
					if(callStmt.executeUpdate()==0){
						mapObj.put(key, Status);
					}*/
					
					
					System.out.println("dataUpdated ==" + dataUpdated);
				
					success = "Updated";
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					
				/*	Query query = sessionFactory
							.getCurrentSession()
							.createQuery(
									" UPDATE ClaimDetailsModel SET STATUS = :status,NBFC_CHECKER_ID=:userId,REMARKS=:remark ,NBFC_CHECKER_DATE=SYSDATE WHERE CLAIM_REF_NO=:claimRefNo");
					query.setParameter("claimRefNo", key);
					query.setParameter("status", Status);
					query.setParameter("userId", userId);
					query.setParameter("remark", claim.getMLI_REMARK());
					// query.setParameter("sysdate", SYSDATE);
					int result = query.executeUpdate();*/
				}
			}
			x++;
			System.out.println("Count==="+x);
		
	}
//		refmsg = refmsg.replaceFirst(",", "");
//	System.out.println("ddddd" +refmsg);
		try {
			conn.close();
			callStmt.close();	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return bean;

	}

	@Override
	public List<ClaimLodgementBean> getClaimLoadgmentDetailsForApproval(
			String MLIID, String cNT) {

		// TODO Auto-generated method stub
		System.out
				.println("getMliBorrowerNpaDtlsBeforClaimLodgement method called to procedure inside DAOImpl==");
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		@SuppressWarnings("unused")
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		ArrayList<ClaimLodgementBean> ClaimBeanList = new ArrayList<ClaimLodgementBean>();
		ClaimLodgementBean objClaimLodgementBean = null;

		BigDecimal bg;
		try {
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			CallableStatement callableStatement = (CallableStatement) con
					.prepareCall("{ call PROC_GET_CLAIM_APPROVAL_LIST(?,?,?,?) } ");
			callableStatement.setString(1, MLIID);
			callableStatement.setString(2, cNT);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			callableStatement.execute();
			resultset = (ResultSet) callableStatement.getObject(4);
			while (resultset.next()) {
				objClaimLodgementBean = new ClaimLodgementBean();
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date claimdate = resultset.getDate(2);
				objClaimLodgementBean.setDateOfClaimLoadge(dateFormat
						.format(claimdate));

				objClaimLodgementBean.setClaimRefNo((resultset
						.getString("CLAIM_REF_NO")));
				objClaimLodgementBean.setUnitName((resultset
						.getString("MSE_NAME")));
				objClaimLodgementBean.setRemarks("Enter Remarks");
				objClaimLodgementBean.setLatestOsAmt((resultset
						.getDouble("OUTSTANDING_AMOUNT")));
				objClaimLodgementBean.setMemberId(MLIID);
				objClaimLodgementBean.setGuarantee_Amt(resultset
						.getDouble("GUARANTEE_AMOUNT"));
				objClaimLodgementBean.setOsAmtAsonNPA(resultset
						.getDouble("CLAIM_ELIGABLE_AMT"));
				objClaimLodgementBean.setRecovery(resultset
						.getDouble("RECOVERY_AMOUNT"));
				objClaimLodgementBean.setFirstInstallClaimStr(resultset.getString("FIRST_INSTALLMENT_CLAIM"));
		
				
				objClaimLodgementBean.setState(resultset.getString("STATE"));
				objClaimLodgementBean.setClaimAmountStr(resultset
						.getString("CLAIM_ELIGIBILITY_AMOUNT"));
				objClaimLodgementBean.setRemarks1(resultset
						.getString("CGTMSE_CK_REMARK"));
				objClaimLodgementBean.setOsAmtClaim(resultset
						.getDouble("OUTSTANDING_AMOUNT_CLAIM"));
				objClaimLodgementBean.setLatestOsAmtNPA(resultset
						.getDouble("LATEST_OS_AMT"));
				objClaimLodgementBean.setSubsidyAmt(resultset
						.getDouble("SUBSIDY_AMOUNT"));
				objClaimLodgementBean.setGstNo(resultset.getString("GSTIN_NO"));
				objClaimLodgementBean.setCgpan(resultset.getString("CGPAN"));
				objClaimLodgementBean.setClaimStatus(resultset.getString("STATUS"));
				objClaimLodgementBean.setFinal_status(resultset
						.getString("FINAL_STATUS"));
				objClaimLodgementBean.setReceiveASF(resultset
						.getString("ASF_PAID_FLAG"));
				objClaimLodgementBean.setAppRemarks(resultset
						.getString("APPLICATION_REMARK"));
				objClaimLodgementBean.setRemarks1(resultset
						.getString("RETURN_REMARK"));
				if(resultset.getDate("CGTMSE_CHECKER_DATE")!=null){
					Date return_date = resultset.getDate("CGTMSE_CHECKER_DATE");
					objClaimLodgementBean.setReturnDate(dateFormat.format(return_date));
				}else{
					objClaimLodgementBean.setReturnDate(null);
				}
				
				if(resultset.getDate("NBFC_CLAIM_LODG_FIRST_DATE")!=null){
					Date CLAIM_date = resultset.getDate("NBFC_CLAIM_LODG_FIRST_DATE");
					objClaimLodgementBean.setDateOfClaim(dateFormat.format(CLAIM_date));
				}else{
					objClaimLodgementBean.setDateOfClaim(null);
				}
				
				
				
			
				// objClaimLodgementBean.setGuaranteeCov((resultset.getDouble("GURANTEE_COVERAGE")));*/
				ClaimBeanList.add(objClaimLodgementBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ClaimBeanList;
	}

	@Override
	public List<ClaimLodgementBean> claimLoadgementApprovalCGTMSEMLIWise(
			String userId) {
		// TODO Auto-generated method stub
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		@SuppressWarnings("unused")
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		ArrayList<ClaimLodgementBean> ClaimBeanList = new ArrayList<ClaimLodgementBean>();
		ClaimLodgementBean objClaimLodgementBean = null;

		BigDecimal bg;
		try {
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			CallableStatement callableStatement = (CallableStatement) con
					.prepareCall("{ call PROC_CLAIM_CNT_MLIWISE(?,?,?) } ");
			callableStatement.setString(1, userId);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			resultset = (ResultSet) callableStatement.getObject(3);
			while (resultset.next()) {
				objClaimLodgementBean = new ClaimLodgementBean();
				objClaimLodgementBean.setMliName((resultset.getString(1)));
				objClaimLodgementBean.setUPTO_10L_CNT((resultset.getInt(2)));
				objClaimLodgementBean.setBETWEEN_10L_25L_CNT((resultset
						.getInt(3)));
				objClaimLodgementBean.setUPTO_25L_CNT((resultset.getInt(4)));
				///objClaimLodgementBean.setTotal_Amount((resultset.getDouble(5)));
				//Added By Parmanand
				double totalAmt=Math.round(resultset.getDouble(5));
				BigDecimal totalAmtBig=new BigDecimal(totalAmt);
				BigDecimal totalAmtBigRound=totalAmtBig.setScale(2, RoundingMode.HALF_UP);
				String totalAmtInStr=totalAmtBigRound.toPlainString();
				System.out.println("totalAmtInStr==="+totalAmtInStr);
				objClaimLodgementBean.setTotal_AmountStr(totalAmtInStr);
				//Added By Parmanand
				objClaimLodgementBean.setClaimCnt((resultset.getInt(6)));

				ClaimBeanList.add(objClaimLodgementBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ClaimBeanList;
	}

	@Override
	public ClaimLodgementBean getClaimChecklistDetails(String claimRefNo) {
		// TODO Auto-generated method stub
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		@SuppressWarnings("unused")
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		ArrayList<ClaimLodgementBean> ClaimBeanList = new ArrayList<ClaimLodgementBean>();
		ClaimLodgementBean objClaimLodgementBean = null;

		BigDecimal bg;
		try {
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			CallableStatement callableStatement = (CallableStatement) con
					.prepareCall("{ call PROC_CLAIM_CHECKLIST(?,?,?) } ");
			callableStatement.setString(1, claimRefNo);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			resultset = (ResultSet) callableStatement.getObject(3);
			while (resultset.next()) {
				objClaimLodgementBean = new ClaimLodgementBean();
				objClaimLodgementBean.setClaimRefNo((resultset
						.getString("CLAIM_REF_NO")));
				objClaimLodgementBean.setActivityEligible((resultset
						.getString("ACTIVITY_ELIGIGIBLE")));
				objClaimLodgementBean.setCibil((resultset.getString("CIBIL")));
				objClaimLodgementBean.setRateCharge((resultset
						.getString("RATE_CHARGE")));
				objClaimLodgementBean.setDateOfNPAAsRBI((resultset
						.getString("DATE_OF_NPA_AS_RBI")));
				// objClaimLodgementBean.setWhetherOutstanding((resultset.getString("WHETHER_OUTSTANDING")));
				objClaimLodgementBean.setApprisalDisbursement((resultset
						.getString("APPRISAL_DISBURSEMENT")));
				objClaimLodgementBean.setPostDisbursement((resultset
						.getString("POST_DISBURSEMENT")));
				objClaimLodgementBean.setExerciseCarried((resultset
						.getString("EXERCISE_CARRIED")));
				objClaimLodgementBean.setInternalRating((resultset
						.getString("INTERNAL_RATTING")));
				objClaimLodgementBean.setRecoverPertaining((resultset
						.getString("RECOVER_PERTAINING")));

				objClaimLodgementBean.setActivityEligibleRemarks((resultset
						.getString("ACTIVITY_ELIGIGIBLE_REMARKS")));
				objClaimLodgementBean.setCibilRemarks((resultset
						.getString("CIBIL_REMARKS")));
				objClaimLodgementBean.setRateChargeRemarks((resultset
						.getString("RATE_CHARGE_REMARKS")));
				objClaimLodgementBean.setDateOfNPAAsRBIRemarks((resultset
						.getString("DATE_OF_NPA_AS_RBI_REMARKS")));
				// objClaimLodgementBean.setWhetherOutstandingRemarks((resultset.getString("WHETHER_OUTSTANDING_REMARKS")));
				objClaimLodgementBean.setApprisalDisbursementRemarks((resultset
						.getString("APPRISAL_DISBURSEMENT_REMARKS")));
				objClaimLodgementBean.setPostDisbursementRemarks((resultset
						.getString("POST_DISBURSEMENT_REMARKS")));
				objClaimLodgementBean.setExerciseCarriedRemarks((resultset
						.getString("EXERCISE_CARRIED_REMARKS")));
				objClaimLodgementBean.setInternalRatingRemarks((resultset
						.getString("INTERNAL_RATTING_REMARKS")));
				objClaimLodgementBean.setRecoverPertainingRemarks((resultset
						.getString("RECOVER_PERTAINING_REMARKS")));

				// ClaimBeanList.add(objClaimLodgementBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objClaimLodgementBean;
	}

	@Override
	public documentModel DownloadBlobDoc(String claimRefNo)
			throws SQLException, IOException {

		Session session = sessionFactory.getCurrentSession();
		documentModel a=(documentModel) session.get(documentModel.class, claimRefNo);
		return a;

	}

	String memberIdValue = null;

	public ClaimLodgementBean getMliBorrowerNpaDtlsBeforClaimLodgement(
			String cgpan) {
		ClaimLodgementBean objClaimLodgementBean = null;

		System.out
				.println("getMliBorrowerNpaDtlsBeforClaimLodgement method called to procedure inside DAOImpl==");
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		List<Object> dataList = null;
		DecimalFormat formatter;

		/*
		 * objClaimLodgementBean.setLatestOsGuranteeAmt((resultset.getDouble(
		 * "LATEST_OS_AMT"))); Double d = new
		 * Double(resultset.getDouble("SANCTION_LAND_VAL"));
		 * System.out.println("d==="+d); bg = BigDecimal.valueOf(d);
		 * System.out.println("bg==="+bg);
		 * objClaimLodgementBean.setLandValueStr(bg.toString());
		 */

		BigDecimal bg;
		try {
			boolean empty = true;
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			// String dateOfSanctionOfExposure1 =
			// dateFormat.format(dateOfSanctionOfExposure);
			CallableStatement callableStatement = (CallableStatement) con
					.prepareCall("{ call proc_getMLI_BORROWER_NPA_DTLS1(?,?,?) } ");
			callableStatement.setString(1, cgpan);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			resultset = (ResultSet) callableStatement.getObject(3);
			while (resultset.next()) {
				empty = false;
				objClaimLodgementBean = new ClaimLodgementBean();
				objClaimLodgementBean.setCgpan((resultset.getString("CGPAN")));
				objClaimLodgementBean.setMemberId((resultset
						.getString("MEM_BNK_ID")));
				objClaimLodgementBean.setLendingNbfcName((resultset
						.getString("MEM_BANK_NAME")));
				objClaimLodgementBean
						.setCity((resultset.getString("MEM_CITY")));
				objClaimLodgementBean.setAddress((resultset
						.getString("MEM_ADDRESS")));
				objClaimLodgementBean.setDistrict((resultset
						.getString("MEM_DISTRICT_NAME")));
				objClaimLodgementBean.setState((resultset
						.getString("MEM_STATE_NAME")));
				objClaimLodgementBean.setEmail((resultset
						.getString("MEM_EMAIL")));
				objClaimLodgementBean.setTelephoneNo((resultset
						.getString("MEM_PHONE_NUMBER")));
				objClaimLodgementBean.setGstinNo((resultset
						.getString("GSTIN_NO")));
				objClaimLodgementBean.setNameOfBorrower((resultset
						.getString("MSE_NAME")));
				objClaimLodgementBean.setAdressOfBorrower((resultset
						.getString("MSE_ADDRESS")));
				objClaimLodgementBean.setCityOfBorrower((resultset
						.getString("CITY")));
				objClaimLodgementBean.setDistrictOfBorrower((resultset
						.getString("DISTRICT")));
				objClaimLodgementBean.setStateOfBorrower((resultset
						.getString("STATE")));
				objClaimLodgementBean.setPincodeOfBorrower((resultset
						.getString("PINCODE")));
				objClaimLodgementBean.setLatestOsGuranteeAmtVStr(decimalFormat
						.format(resultset.getDouble("LATEST_OS_AMT")));
				String dateOfNPAStr = dateFormat.format(resultset
						.getDate("NPA_EFFECTIVE_DT"));
				objClaimLodgementBean.setDateOfNPA(dateOfNPAStr);
				objClaimLodgementBean.setReasonOfNPA((resultset
						.getString("NPA_REASONS_TURNING_NPA")));
				objClaimLodgementBean.setLatestOsAmtVStr(decimalFormat
						.format(resultset.getDouble("LATEST_OS_AMT")));
				objClaimLodgementBean.setTotalOsAmtStr(decimalFormat
						.format(resultset.getDouble("CLAIM_ELIGABLE_AMT")));
				objClaimLodgementBean.setLandValueStr(decimalFormat
						.format(resultset.getDouble("SANCTION_LAND_VAL")));
				String buildingValStr = decimalFormat.format(resultset
						.getDouble("SANCTION_BUILDING_VAL"));
				objClaimLodgementBean.setBuildingValueStr(buildingValStr);
				objClaimLodgementBean.setPlanetValueStr(decimalFormat
						.format(resultset.getDouble("SANCTION_PLANET_VAL")));
				objClaimLodgementBean
						.setOtherAssetValueStr(decimalFormat.format(resultset
								.getDouble("SANCTION_OTHER_ASSET_VAL")));
				objClaimLodgementBean
						.setCurrentAssetValueStr(decimalFormat.format(resultset
								.getDouble("SANCTION_OTHER_ASSET_VAL")));
				objClaimLodgementBean.setOthersValueStr(decimalFormat
						.format(resultset.getDouble("SANCTION_OTHER_VAL")));
				objClaimLodgementBean
						.setTotalSecuritydetailsStr(decimalFormat
								.format(resultset
										.getDouble("SANCTION_TOTAL_SECURITY")));
				objClaimLodgementBean.setNetworthOfPromotorStr(decimalFormat
						.format(resultset.getDouble("SANCTION_NETWORTH")));
				objClaimLodgementBean.setReductionReason(decimalFormat.format(resultset.getDouble("SANCTION_NETWORTH")));
				objClaimLodgementBean.setLandValue1Str(decimalFormat
						.format(resultset.getDouble("NPA_LAND_VAL")));
				objClaimLodgementBean.setBuildingValue1Str(decimalFormat
						.format(resultset.getDouble("NPA_BUILDING_VAL")));
				objClaimLodgementBean.setPlanetValue1Str(decimalFormat
						.format(resultset.getDouble("NPA_PLANET_VAL")));
				objClaimLodgementBean.setOtherAssetValue1Str(decimalFormat
						.format(resultset.getDouble("NPA_OTHER_ASSET_VAL")));
				objClaimLodgementBean.setCurrentAssetValue1Str(decimalFormat
						.format(resultset.getDouble("NPA_OTHER_VAL")));
				objClaimLodgementBean.setOthersValue1Str(decimalFormat
						.format(resultset.getDouble("NPA_OTHER_VAL")));
				objClaimLodgementBean.setTotalSecuritydetails1Str(decimalFormat
						.format(resultset.getDouble("NPA_TOTAL_SECURITY")));
				objClaimLodgementBean.setNetworthOfPromotor1Str(decimalFormat
						.format(resultset.getDouble("NPA_NETWORTH")));
				objClaimLodgementBean.setReductionReason1((resultset.getString("NPA_REDUCTION_REASON")));
				System.out.println("1==="
						+ resultset.getString("GURANTEE_COVERAGE"));
				objClaimLodgementBean.setGuaranteeCovStr((resultset
						.getString("NPA_REDUCTION_REASON")));
				objClaimLodgementBean.setGuaranteeCovStr(resultset
						.getString("GURANTEE_COVERAGE"));
				objClaimLodgementBean.setSanctionAmt(resultset
						.getDouble("SANCTIONED_AMOUNT"));
			}
			if (empty) {
				objClaimLodgementBean.setCgpan("");
				objClaimLodgementBean.setMemberId("");
				objClaimLodgementBean.setLendingNbfcName("");
				objClaimLodgementBean.setCity("");
				objClaimLodgementBean.setAddress("");
				objClaimLodgementBean.setDistrict("");
				objClaimLodgementBean.setState("");
				objClaimLodgementBean.setEmail("");
				objClaimLodgementBean.setTelephoneNo("");
				objClaimLodgementBean.setGstinNo("");
				objClaimLodgementBean.setNameOfBorrower("");
				objClaimLodgementBean.setAdressOfBorrower("");
				objClaimLodgementBean.setCityOfBorrower("");
				objClaimLodgementBean.setDistrictOfBorrower("");
				objClaimLodgementBean.setStateOfBorrower("");
				objClaimLodgementBean.setPincodeOfBorrower("");
				objClaimLodgementBean.setLatestOsGuranteeAmtVStr("");
				objClaimLodgementBean.setDateOfNPA("");
				objClaimLodgementBean.setReasonOfNPA("");
				objClaimLodgementBean.setLatestOsAmtVStr("");
				objClaimLodgementBean.setTotalOsAmtStr("");
				objClaimLodgementBean.setLandValueStr("");
				objClaimLodgementBean.setBuildingValueStr("");
				objClaimLodgementBean.setPlanetValueStr("");
				objClaimLodgementBean.setOtherAssetValueStr("");
				objClaimLodgementBean.setCurrentAssetValueStr("");
				objClaimLodgementBean.setOthersValueStr("");
				objClaimLodgementBean.setTotalSecuritydetailsStr("");
				objClaimLodgementBean.setNetworthOfPromotorStr("");
				objClaimLodgementBean.setReductionReason("");
				objClaimLodgementBean.setLandValue1Str("");
				objClaimLodgementBean.setBuildingValue1Str("");
				objClaimLodgementBean.setPlanetValue1Str("");
				objClaimLodgementBean.setOtherAssetValue1Str("");
				objClaimLodgementBean.setCurrentAssetValue1Str("");
				objClaimLodgementBean.setOthersValue1Str("");
				objClaimLodgementBean.setTotalSecuritydetails1Str("");
				objClaimLodgementBean.setNetworthOfPromotor1Str("");
				objClaimLodgementBean.setReductionReason1("");
				objClaimLodgementBean.setGuaranteeCovStr("");
				objClaimLodgementBean.setGuaranteeCovStr("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objClaimLodgementBean;
	}

	public String saveClaimLodgmentData(ClaimLodgementBean claimLodgementBean,String memberId,String loginUserId) {
		SimpleDateFormat formatObj = new SimpleDateFormat("dd/MM/yyyy");
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		Connection conn = session.connection();
		CallableStatement callStmt = null;
		int dataSaved = 0;
		DecimalFormat decimalFormat=new DecimalFormat("0.00");
		int count=1;
		String Claim_REfno="";
		try{
			if(claimLodgementBean!=null){
				Date sysdate=new Date();
				ResultSet resultSet = null;
				String exception = "";
				System.out.println("memberId=="+memberIdValue);
				
				if (claimLodgementBean.getMemberId().length() > 4){
					memberIdValue = claimLodgementBean.getMemberId().substring(0, 4);
					zoneId=claimLodgementBean.getMemberId().substring(4, 8);
					branchId=claimLodgementBean.getMemberId().substring(8, 12);
					System.out.print("zoneId=="+zoneId);
					System.out.print("branchId=="+branchId);
				} 
				else {
					memberIdValue = claimLodgementBean.getMemberId();
				}

				String claimStatus="ML";
				callStmt = conn.prepareCall("{call pro_saveClaimLodgmentData1(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				callStmt.setString(1, claimLodgementBean.getCgpan());
				callStmt.setString(2, memberIdValue);
				if(claimLodgementBean.getDealingOfficerName()!="" || claimLodgementBean.getDealingOfficerName()!=null){
					callStmt.setString(3, claimLodgementBean.getDealingOfficerName());
				}
				
				callStmt.setString(4, claimLodgementBean.getWilfulDefaulter());
				callStmt.setString(5, claimLodgementBean.getFraudAcc());
				callStmt.setString(6, claimLodgementBean.getEnquiryConcluded());
				callStmt.setString(7, claimLodgementBean.getMliReported());
				if(claimLodgementBean.getDateOfNotice().length()!=0){
				if(claimLodgementBean.getDateOfNotice()!="" || claimLodgementBean.getDateOfNotice()!=null ){
					   Date parsed = formatObj.parse(claimLodgementBean.getDateOfNotice().toString());
				       java.sql.Date sql = new java.sql.Date(parsed.getTime());
				       callStmt.setDate(8,  sql);
				      
				}
				}else{
					  callStmt.setDate(8,  null);
				}
				if(claimLodgementBean.getForum()!="" || claimLodgementBean.getForum()!=null ){
					callStmt.setString(9, claimLodgementBean.getForum());
				}
				
				if(claimLodgementBean.getSuitNo()!="" || claimLodgementBean.getSuitNo()!=null ){
					callStmt.setString(10, claimLodgementBean.getSuitNo());
				}
				
				if(claimLodgementBean.getResonFillingSuit()!="" || claimLodgementBean.getResonFillingSuit()!=null ){
					callStmt.setString(11, claimLodgementBean.getResonFillingSuit());
				}else{
					callStmt.setString(11, ""); 	
				}
				String dateOfSerfia=claimLodgementBean.getDateOfSarfaesi();
				System.out.println("dateOfSerfia"+dateOfSerfia);
				if(claimLodgementBean.getDateOfSarfaesi()!=null ){
					 Date parsed1 = formatObj.parse(claimLodgementBean.getDateOfSarfaesi().toString());
				     java.sql.Date sql1 = new java.sql.Date(parsed1.getTime());
					 callStmt.setDate(12, sql1);
				}else{
					 callStmt.setDate(12, null);
				}
				
				if(claimLodgementBean.getLocationOfForum()!="" || claimLodgementBean.getLocationOfForum()!=null ){
					callStmt.setString(13, claimLodgementBean.getLocationOfForum());
				}
				
				callStmt.setDouble(14, claimLodgementBean.getClaimAmt());
				callStmt.setString(15, claimLodgementBean.getAnySubsidyInvolved());
				callStmt.setString(16, claimLodgementBean.getSubsidyReceived());
				callStmt.setString(17, claimLodgementBean.getSubsidyAdjust());
				String date_subsidy = claimLodgementBean.getDateOfSubsidy();
				
				System.out.println("date_subsidy"+date_subsidy);
				
				if(date_subsidy.length()!=0){
					if(date_subsidy!=null || date_subsidy!="" )
					{
					 Date parsed3 = formatObj.parse(claimLodgementBean.getDateOfSubsidy().toString());
				     java.sql.Date sql3 = new java.sql.Date(parsed3.getTime());
					 callStmt.setDate(18, sql3);
					}
				}else{
					callStmt.setDate(18,null);
				}
				
				if(claimLodgementBean.getSubsidyAmt()!=null){
					callStmt.setDouble(19, claimLodgementBean.getSubsidyAmt());
				}else{
					callStmt.setDouble(19, 0);    
				}
				
				callStmt.setDouble(20, claimLodgementBean.getRecovery());
				
				if(claimLodgementBean.getModeRecovery()!="" || claimLodgementBean.getModeRecovery()!=null ){
					callStmt.setString(21, claimLodgementBean.getModeRecovery());
				}
			
				if(claimLodgementBean.getLandValue2()!=null ){
					callStmt.setDouble(22,claimLodgementBean.getLandValue2());             
				}else{
					callStmt.setDouble(22,0.0);
				}
			
				if(claimLodgementBean.getBuildingValue2()!=null ){
					callStmt.setDouble(23,claimLodgementBean.getBuildingValue2());             
				}else{
					callStmt.setDouble(23,0.0);
				}
				
				if(claimLodgementBean.getPlanetValue2()!=null ){
					callStmt.setDouble(24,claimLodgementBean.getPlanetValue2());             
				}else{
					callStmt.setDouble(24,0.0);
				}
				
				if(claimLodgementBean.getOtherAssetValue2()!=null ){
					callStmt.setDouble(25,claimLodgementBean.getOtherAssetValue2());             
				}else{
					callStmt.setDouble(25,0.0);
				}
				
				if(claimLodgementBean.getCurrentAssetValue2()!=null ){
					callStmt.setDouble(26,claimLodgementBean.getCurrentAssetValue2());             
				}else{
					callStmt.setDouble(26,0.0);
				}
				
				if(claimLodgementBean.getOthersValue2()!=null ){
					callStmt.setDouble(27,claimLodgementBean.getOthersValue2());             
				}else{
					callStmt.setDouble(27,0.0);
				}
				
				if(claimLodgementBean.getNetworthOfPromotor2()!=null ){
					callStmt.setDouble(28,claimLodgementBean.getNetworthOfPromotor2());             
				}else{
					callStmt.setDouble(28,0.0);
				}
				
				callStmt.setString(29,claimLodgementBean.getReductionReason2());
				callStmt.setDouble(30,claimLodgementBean.getTotalSecuritydetails2());
				
				callStmt.setDouble(31, claimLodgementBean.getOsAmtClaim());
				callStmt.setString(32, claimLodgementBean.getNpaRecoveryFlag());
				callStmt.setString(33, claimLodgementBean.getConfirmRecoveryFlag());
				callStmt.setDouble(34, claimLodgementBean.getEligableAmtClaim());
				callStmt.setDouble(35, claimLodgementBean.getFirstInstallClaim());
				
				if(claimLodgementBean.getFinancialPositionComments()!="" || claimLodgementBean.getFinancialPositionComments()!=null ){
					callStmt.setString(36, claimLodgementBean.getFinancialPositionComments());
				}
				
				if(claimLodgementBean.getFinancialAssistanceDtls()!="" || claimLodgementBean.getFinancialAssistanceDtls()!=null ){
					callStmt.setString(37, claimLodgementBean.getFinancialAssistanceDtls());
				}
				if(claimLodgementBean.getCreditSupport()!="" || claimLodgementBean.getCreditSupport()!=null ){
					callStmt.setString(38, claimLodgementBean.getCreditSupport());
				}
				
				if(claimLodgementBean.getBankFacilityDtls()!="" || claimLodgementBean.getBankFacilityDtls()!=null ){
					callStmt.setString(39, claimLodgementBean.getBankFacilityDtls());
				}
			
				callStmt.setString(40, claimStatus);
				callStmt.setString(41,"19/JAN/2019");
				callStmt.setString(42,claimLodgementBean.getClaimLodgementCertificate());
				callStmt.setString(43,claimLodgementBean.getDealingOfficerNO());
 				if(claimLodgementBean.getInternalRating1()!="" || claimLodgementBean.getInternalRating1()!=null ){
					callStmt.setString(44, claimLodgementBean.getInternalRating1());
				}
				else{
					callStmt.setString(44, "");
				}
 				
				callStmt.registerOutParameter(45, Types.VARCHAR);
				if(claimLodgementBean.getNbfcMakerClaimLodgRemarks()!="" || claimLodgementBean.getNbfcMakerClaimLodgRemarks()!=null ){
					callStmt.setString(46, claimLodgementBean.getNbfcMakerClaimLodgRemarks());
				}else{
					callStmt.setString(46, "");
				}
				
				if(loginUserId!="" || loginUserId!=null ){
					callStmt.setString(47, loginUserId);
				}else{
					callStmt.setString(47, "");
				}
				callStmt.setString(48,zoneId);
				callStmt.setString(49,branchId);
				dataSaved=callStmt.executeUpdate();
				System.out.println("Record Save=="+dataSaved);
			    Claim_REfno=callStmt.getString(45);
				System.out.println("Claim_REfno Save=="+Claim_REfno);
				//conn.close();
				//callStmt.close();
				count++;
				return Claim_REfno;	
			}
		}catch(Exception e){
				e.printStackTrace();
		}finally{
			tn.commit();
			session.close();
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return Claim_REfno;
	}

	public int saveClaimLodgementCheckListData(ClaimLodgementBean claimLodgementBean,String memberId,String loginUserId) {
		System.out.println("1===saveClaimLodgementCheckListData method called==="+memberId+"CGPAN=="+claimLodgementBean.getCgpan());
		SimpleDateFormat formatObj = new SimpleDateFormat("dd/MM/yyyy");
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		Connection conn = session.connection();
		CallableStatement callStmt = null;
		int dataSaved = 0;
		DecimalFormat decimalFormat=new DecimalFormat("0.00");
	
		try{
			if(claimLodgementBean!=null){
				Date sysdate=new Date();
				ResultSet resultSet = null;
				String exception = "";
				callStmt = conn.prepareCall("{call pro_saveClaimLodgCheckList(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				callStmt.setString(1,	claimLodgementBean.getCgpan());
				callStmt.setString(2,	memberId);
				
				callStmt.setString(3,	claimLodgementBean.getActivityEligible());
				
				if(claimLodgementBean.getActivityEligibleRemarks()!="" || claimLodgementBean.getActivityEligibleRemarks()!=null){
					callStmt.setString(4,claimLodgementBean.getActivityEligibleRemarks());
				}else{
					callStmt.setString(4,"");
				}
				
				callStmt.setString(5,	claimLodgementBean.getCibil());
				
				if(claimLodgementBean.getCibilRemarks()!="" || claimLodgementBean.getCibilRemarks()!=null){
					callStmt.setString(6,claimLodgementBean.getCibilRemarks());
				}else{
					callStmt.setString(6,"");
				}
				
				callStmt.setString(7,	claimLodgementBean.getRateCharge());
				
				if(claimLodgementBean.getRateChargeRemarks()!="" || claimLodgementBean.getRateChargeRemarks()!=null){
					callStmt.setString(8,claimLodgementBean.getRateChargeRemarks());
				}else{
					callStmt.setString(8,"");
				}
				
				callStmt.setString(9, 	claimLodgementBean.getDateOfNPAAsRBI());
				
				if(claimLodgementBean.getDateOfNPAAsRBIRemarks()!="" || claimLodgementBean.getDateOfNPAAsRBIRemarks()!=null){
					callStmt.setString(10,claimLodgementBean.getDateOfNPAAsRBIRemarks());
				}else{
					callStmt.setString(10,"");
				}
				
				callStmt.setString(11, 	claimLodgementBean.getWhetherOutstanding());
				
				if(claimLodgementBean.getWhetherOutstandingRemarks()!="" || claimLodgementBean.getWhetherOutstandingRemarks()!=null){
					callStmt.setString(12,claimLodgementBean.getWhetherOutstandingRemarks());
				}else{
					callStmt.setString(12,"");
				}
				
				callStmt.setString(13, 	claimLodgementBean.getApprisalDisbursement());
				
				if(claimLodgementBean.getApprisalDisbursementRemarks()!="" || claimLodgementBean.getApprisalDisbursementRemarks()!=null){
					callStmt.setString(14,claimLodgementBean.getApprisalDisbursementRemarks());
				}else{
					callStmt.setString(14,"");
				}
				
				callStmt.setString(15, 	claimLodgementBean.getPostDisbursement());
				
				if(claimLodgementBean.getPostDisbursementRemarks()!="" || claimLodgementBean.getPostDisbursementRemarks()!=null){
					callStmt.setString(16,claimLodgementBean.getPostDisbursementRemarks());
				}else{
					callStmt.setString(16,"");
				}
				
				callStmt.setString(17, 	claimLodgementBean.getExerciseCarried());
				
				if(claimLodgementBean.getExerciseCarriedRemarks()!="" || claimLodgementBean.getExerciseCarriedRemarks()!=null){
					callStmt.setString(18,claimLodgementBean.getExerciseCarriedRemarks());
				}else{
					callStmt.setString(18,"");
				}
				
				callStmt.setString(19, 	claimLodgementBean.getInternalRating());
				
				if(claimLodgementBean.getInternalRatingRemarks()!="" || claimLodgementBean.getInternalRatingRemarks()!=null){
					callStmt.setString(20,claimLodgementBean.getInternalRatingRemarks());
				}else{
					callStmt.setString(20,"");
				}
				
				callStmt.setString(21, 	claimLodgementBean.getRecoverPertaining());
				
				if(claimLodgementBean.getRecoverPertainingRemarks()!="" || claimLodgementBean.getRecoverPertainingRemarks()!=null){
					callStmt.setString(22,claimLodgementBean.getRecoverPertainingRemarks());
				}else{
					callStmt.setString(22,"");
				}
				if(loginUserId!="" || loginUserId!=null ){
					callStmt.setString(23, loginUserId);
				}else{
					callStmt.setString(23, "");
				}
				
				dataSaved=callStmt.executeUpdate();
				System.out.println("Record Save=="+dataSaved);
				conn.close();
				callStmt.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			tn.commit();
			session.close();
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dataSaved;
	}

	public ClaimLodgementBean getSaveClaimLodgmentData(String cgpan) {
		ClaimLodgementBean objClaimLodgementBean = null;
		System.out
				.println("getSaveClaimLodgmentData method called to procedure inside DAOImpl==");
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		List<Object> dataList = null;
		DecimalFormat formatter;
		BigDecimal bg;
		try {
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			CallableStatement callableStatement = (CallableStatement) con
					.prepareCall("{ call proc_getSaveClaimLodgmentData(?,?,?) } ");
			callableStatement.setString(1, cgpan);
			callableStatement.registerOutParameter(2, Types.VARCHAR);

			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			resultset = (ResultSet) callableStatement.getObject(3);
			boolean empty = true;
			while (resultset.next()) {
				empty = false;
				objClaimLodgementBean = new ClaimLodgementBean();
				objClaimLodgementBean.setDealingOfficerName((resultset
						.getString("DEALING_OFFICER_NAME")));
				System.out.println("Dealing Officer Number==="
						+ resultset.getString("DEALING_OFFICER_NUMBER"));
				objClaimLodgementBean.setDealingOfficerNO((resultset
						.getString("DEALING_OFFICER_NUMBER")));
				if(resultset.getDate("DATE_OF_DEMAND_NOTICE_ISSUE")!=null){
					String dateOfNoticeStr = dateFormat.format(resultset.getDate("DATE_OF_DEMAND_NOTICE_ISSUE"));
					objClaimLodgementBean.setDateOfNotice(dateOfNoticeStr);
				}else{
					objClaimLodgementBean.setDateOfNotice(null);
				}
				objClaimLodgementBean.setWilfulDefaulter((resultset
						.getString("WILFUL_DEFAULTER")));
				objClaimLodgementBean.setFraudAcc((resultset
						.getString("ACC_CLASSIFIED_FAULT")));
				objClaimLodgementBean.setEnquiryConcluded((resultset
						.getString("ENQUIRY_CONCLUDED")));
				objClaimLodgementBean.setMliReported((resultset
						.getString("INVOLVEMENT_MLI_STAFF_REPORTED")));
				if (resultset.getString("INTERNAL_RATING") != null
						|| resultset.getString("INTERNAL_RATING") != "") {
					objClaimLodgementBean.setInternalRating1(resultset
							.getString("INTERNAL_RATING"));
				} else {
					objClaimLodgementBean.setInternalRating1("");
				}

				objClaimLodgementBean.setForum((resultset.getString("FORUM")));
				objClaimLodgementBean.setSuitNo((resultset
						.getString("SUIT_REG_NO")));

				if (resultset.getString("RESON_FILLING_SUIT") != null
						|| resultset.getString("RESON_FILLING_SUIT") != "") {
					objClaimLodgementBean.setResonFillingSuit(resultset
							.getString("RESON_FILLING_SUIT"));
				} else {
					objClaimLodgementBean.setResonFillingSuit("");
				}

				String dateOfSarfaesiStr = dateFormat.format(resultset
						.getDate("DATE_SARFAESI_ACT"));
				objClaimLodgementBean.setDateOfSarfaesi(dateOfSarfaesiStr);
				objClaimLodgementBean.setLocationOfForum((resultset
						.getString("FORUM_LOCATION")));
				objClaimLodgementBean.setClaimAmt((resultset
						.getDouble("AMOUNT_CLAIMED_DEMAND")));
				System.out
						.println("Does the project covered under CGTMSEguarantee, involve any  ==="
								+ resultset.getString("INVOLVED_SUBSIDY"));
				objClaimLodgementBean.setAnySubsidyInvolved((resultset
						.getString("INVOLVED_SUBSIDY")));
				objClaimLodgementBean.setSubsidyReceived((resultset
						.getString("SUBSIDY_RECEIVED_AFTER_NPA")));
				objClaimLodgementBean.setSubsidyAdjust((resultset
						.getString("SUBSIDY_AGAINST_INTREST_DUES")));
				if (resultset.getDate("SUBSIDY_CREDIT_DATE") != null) {
					String dateOfSubsidyStr = dateFormat.format(resultset
							.getDate("SUBSIDY_CREDIT_DATE"));
					objClaimLodgementBean.setDateOfSubsidy(dateOfSubsidyStr);
				} else {
					objClaimLodgementBean.setDateOfSubsidy("");
				}

				double subsidyAmtV = resultset.getDouble("SUBSIDY_AMOUNT");
				if (resultset.wasNull()) {
					objClaimLodgementBean.setSubsidyAmt(0.0);
				} else {
					objClaimLodgementBean.setSubsidyAmt((resultset
							.getDouble("SUBSIDY_AMOUNT")));
				}
				// objClaimLodgementBean.setSubsidyAmt((resultset.getDouble("SUBSIDY_AMOUNT")));
				double recoveryAmtV = resultset.getDouble("RECOVERY_AMOUNT");
				if (resultset.wasNull()) {
					objClaimLodgementBean.setRecovery(0.0);
				} else {
					objClaimLodgementBean.setRecovery((resultset
							.getDouble("RECOVERY_AMOUNT")));
				}
				// objClaimLodgementBean.setRecovery((resultset.getDouble("RECOVERY_AMOUNT")));
				System.out.println("RECOVERY_MODE  ==="
						+ resultset.getString("RECOVERY_MODE"));
				objClaimLodgementBean.setModeRecovery((resultset
						.getString("RECOVERY_MODE")));
				objClaimLodgementBean.setOsAmtClaim((resultset
						.getDouble("OUTSTANDING_AMOUNT_CLAIM")));
				objClaimLodgementBean.setNpaRecoveryFlag((resultset
						.getString("ENSURED_INCLUSION_OF_RECEIPT")));
				objClaimLodgementBean.setConfirmRecoveryFlag((resultset
						.getString("CONFIRM_FEEDING_VALUE_RECOVERY")));
				double landValue2V = resultset.getDouble("LANDVALUE2");
				if (resultset.wasNull()) {
					objClaimLodgementBean.setLandValue2Str("0.0");
				} else {
					objClaimLodgementBean.setLandValue2Str(decimalFormat
							.format(resultset.getDouble("LANDVALUE2")));
				}

				double buldingValue2V = resultset.getDouble("BUILDINGVALUE2");
				if (resultset.wasNull()) {
					objClaimLodgementBean.setBuildingValue2Str("0.0");
				} else {
					objClaimLodgementBean.setBuildingValue2Str(decimalFormat
							.format(resultset.getDouble("BUILDINGVALUE2")));
				}

				double plannetvalue2V = resultset.getDouble("PLANETVALUE2");
				if (resultset.wasNull()) {
					objClaimLodgementBean.setPlanetValue2Str("0.0");
				} else {
					objClaimLodgementBean.setPlanetValue2Str(decimalFormat
							.format(resultset.getDouble("PLANETVALUE2")));
				}
				double otherAssetValue2V = resultset.getDouble("PLANETVALUE2");
				if (resultset.wasNull()) {
					objClaimLodgementBean.setOtherAssetValue2Str("0.0");
				} else {
					objClaimLodgementBean.setOtherAssetValue2Str(decimalFormat
							.format(resultset.getDouble("OHTERASSETVALUE2")));
				}

				double currentAssetValue2V = resultset
						.getDouble("CURRENTASSETVALUE2");
				if (resultset.wasNull()) {
					objClaimLodgementBean.setCurrentAssetValue2Str("0.0");
				} else {
					objClaimLodgementBean
							.setCurrentAssetValue2Str(decimalFormat
									.format(resultset
											.getDouble("CURRENTASSETVALUE2")));
				}

				double otherValue2V = resultset.getDouble("OTHERSVALUE2");
				if (resultset.wasNull()) {
					objClaimLodgementBean.setOthersValue2Str("0.0");
				} else {
					objClaimLodgementBean.setOthersValue2Str(decimalFormat
							.format(resultset.getDouble("OTHERSVALUE2")));
				}

				double otherSecurityDetails2V = resultset
						.getDouble("TOTALSECURITYDETAILS2");
				if (resultset.wasNull()) {
					objClaimLodgementBean.setTotalSecuritydetails2Str("0.0");
				} else {
					objClaimLodgementBean
							.setTotalSecuritydetails2Str(decimalFormat.format(resultset
									.getDouble("TOTALSECURITYDETAILS2")));// 1
				}

				double networthofpromoter2V = resultset
						.getDouble("NETWORTHOFPROMOTOR2");
				if (resultset.wasNull()) {
					objClaimLodgementBean.setNetworthOfPromotor2Str("0.0");
				} else {
					objClaimLodgementBean
							.setNetworthOfPromotor2Str(decimalFormat
									.format(resultset
											.getDouble("NETWORTHOFPROMOTOR2")));
				}

				objClaimLodgementBean
						.setOsAmtClaimGCPStr(decimalFormat.format(resultset
								.getDouble("OUTSTANDING_AMOUNT_CLAIM")));
				objClaimLodgementBean
						.setEligableAmtClaimStr(decimalFormat.format(resultset
								.getDouble("CLAIM_ELIGIBILITY_AMOUNT")));
				objClaimLodgementBean.setFirstInstallClaim((resultset
						.getDouble("FIRST_INSTALLMENT_CLAIM")));
				objClaimLodgementBean.setFinancialPositionComments((resultset
						.getString("MLIS_COMMENT")));
				objClaimLodgementBean.setFinancialAssistanceDtls((resultset
						.getString("FINALCIAL_ASSISTANCE_MINI")));
				objClaimLodgementBean.setCreditSupport((resultset
						.getString("MLI_PROVIDE_SUPPORT")));
				objClaimLodgementBean.setBankFacilityDtls((resultset
						.getString("PROVIED_DETAILS_OF_BANK")));
				if (resultset.getString("RESON_FILLING_SUIT") != null
						|| resultset.getString("RESON_FILLING_SUIT") != "") {
					objClaimLodgementBean.setRemarks((resultset
							.getString("REMARKS")));
				} else {
					objClaimLodgementBean.setRemarks("");
				}
				if (resultset.getString("CLAIM_CERTIFICATE_FLAG") != null
						|| resultset.getString("CLAIM_CERTIFICATE_FLAG") != "") {
					objClaimLodgementBean
							.setClaimLodgementCertificate((resultset
									.getString("CLAIM_CERTIFICATE_FLAG")));
				} else {
					objClaimLodgementBean.setClaimLodgementCertificate("");
				}
				if (resultset.getString("CLAIM_REF_NO") != null
						|| resultset.getString("CLAIM_REF_NO") != "") {
					objClaimLodgementBean.setClaimRefNo((resultset
							.getString("CLAIM_REF_NO")));
				} else {
					objClaimLodgementBean.setClaimRefNo("");
				}

				if (resultset.getString("NBFC_MAKER_LODG_REMARKS") != null
						|| resultset.getString("NBFC_MAKER_LODG_REMARKS") != "") {
					objClaimLodgementBean
							.setNbfcMakerClaimLodgRemarks((resultset
									.getString("NBFC_MAKER_LODG_REMARKS")));
				} else {
					objClaimLodgementBean.setNbfcMakerClaimLodgRemarks("");
				}
				if (resultset.getString("CGS_RETURN_REMARK") != null
						|| resultset.getString("CGS_RETURN_REMARK") != "") {
					objClaimLodgementBean
							.setReturnReasonsRemarks((resultset
									.getString("CGS_RETURN_REMARK")));
				} else {
					objClaimLodgementBean.setReturnReasonsRemarks("");
				}
			}
			if (empty) {
				objClaimLodgementBean = new ClaimLodgementBean();
				objClaimLodgementBean.setDealingOfficerName("");
				objClaimLodgementBean.setWilfulDefaulter("");
				objClaimLodgementBean.setFraudAcc("");
				objClaimLodgementBean.setEnquiryConcluded("");
				objClaimLodgementBean.setMliReported("");
				objClaimLodgementBean.setDateOfNotice("");
				objClaimLodgementBean.setForum("");
				objClaimLodgementBean.setSuitNo("");
				objClaimLodgementBean.setResonFillingSuit("");
				objClaimLodgementBean.setDateOfSarfaesi("");
				objClaimLodgementBean.setLocationOfForum("");
				objClaimLodgementBean.setClaimAmt(0.00);
				objClaimLodgementBean.setAnySubsidyInvolved("");
				objClaimLodgementBean.setSubsidyReceived("");
				objClaimLodgementBean.setSubsidyAdjust("");
				objClaimLodgementBean.setCreditSupport("");
				objClaimLodgementBean.setSubsidyAmt(0.00);
				objClaimLodgementBean.setRecovery(0.00);
				objClaimLodgementBean.setModeRecovery("");
				objClaimLodgementBean.setOsAmtClaim(0.00);
				objClaimLodgementBean.setNpaRecoveryFlag("");
				objClaimLodgementBean.setConfirmRecoveryFlag("");
				objClaimLodgementBean.setLandValue2Str("0.0");
				objClaimLodgementBean.setBuildingValue2Str("0.0");
				objClaimLodgementBean.setPlanetValue2Str("0.0");
				objClaimLodgementBean.setOtherAssetValue2Str("0.0");
				objClaimLodgementBean.setCurrentAssetValue2Str("0.0");
				objClaimLodgementBean.setOthersValue2Str("0.0");
				objClaimLodgementBean.setTotalSecuritydetails2Str("0.0");
				objClaimLodgementBean.setNetworthOfPromotor(0.00);
				objClaimLodgementBean.setEligableAmtClaim(0.00);
				objClaimLodgementBean.setFirstInstallClaim(0.00);
				objClaimLodgementBean.setNetworthOfPromotor2Str("0.0");
				objClaimLodgementBean.setFinancialPositionComments("");
				objClaimLodgementBean.setFinancialAssistanceDtls("");
				objClaimLodgementBean.setCreditSupport("");
				objClaimLodgementBean.setBankFacilityDtls("");
				objClaimLodgementBean.setRemarks("");
				objClaimLodgementBean.setClaimLodgementCertificate("");
				objClaimLodgementBean.setDealingOfficerNO("");
				objClaimLodgementBean.setClaimRefNo("");
				objClaimLodgementBean.setNbfcMakerClaimLodgRemarks("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objClaimLodgementBean;
	}

	public ClaimLodgementBean getSaveClaimLodgementCheckListData(String cgpan) {
		ClaimLodgementBean objClaimLodgementBean = null;
		System.out
				.println("getSaveClaimLodgementCheckListData method called to procedure inside DAOImpl==");
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		List<Object> dataList = null;
		DecimalFormat formatter;
		BigDecimal bg;
		try {
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			CallableStatement callableStatement = (CallableStatement) con
					.prepareCall("{ call proc_getSaveClaimLodCListData(?,?,?) } ");
			callableStatement.setString(1, cgpan);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			resultset = (ResultSet) callableStatement.getObject(3);
			boolean empty = true;
			while (resultset.next()) {
				empty = false;
				objClaimLodgementBean = new ClaimLodgementBean();

				if (resultset.getString("ACTIVITY_ELIGIGIBLE") != null
						|| resultset.getString("ACTIVITY_ELIGIGIBLE") != "") {
					objClaimLodgementBean.setActivityEligible((resultset
							.getString("ACTIVITY_ELIGIGIBLE")));
				} else {
					objClaimLodgementBean.setActivityEligible("");
				}
				if (resultset.getString("ACTIVITY_ELIGIGIBLE_REMARKS") != null
						|| resultset.getString("ACTIVITY_ELIGIGIBLE_REMARKS") != "") {
					objClaimLodgementBean.setActivityEligibleRemarks((resultset
							.getString("ACTIVITY_ELIGIGIBLE_REMARKS")));
				} else {
					objClaimLodgementBean.setActivityEligibleRemarks("");
				}
				if (resultset.getString("CIBIL") != null
						|| resultset.getString("CIBIL") != "") {
					objClaimLodgementBean.setCibil((resultset
							.getString("CIBIL")));
				} else {
					objClaimLodgementBean.setCibil("");
				}
				if (resultset.getString("CIBIL_REMARKS") != null
						|| resultset.getString("CIBIL_REMARKS") != "") {
					objClaimLodgementBean.setCibilRemarks((resultset
							.getString("CIBIL_REMARKS")));
				} else {
					objClaimLodgementBean.setCibilRemarks("");
				}
				if (resultset.getString("RATE_CHARGE") != null
						|| resultset.getString("RATE_CHARGE") != "") {
					objClaimLodgementBean.setRateCharge((resultset
							.getString("RATE_CHARGE")));
				} else {
					objClaimLodgementBean.setRateCharge("");
				}

				if (resultset.getString("RATE_CHARGE_REMARKS") != null
						|| resultset.getString("RATE_CHARGE_REMARKS") != "") {
					objClaimLodgementBean.setRateChargeRemarks((resultset
							.getString("RATE_CHARGE_REMARKS")));
				} else {
					objClaimLodgementBean.setRateChargeRemarks("");
				}
				if (resultset.getString("DATE_OF_NPA_AS_RBI") != null
						|| resultset.getString("DATE_OF_NPA_AS_RBI") != "") {
					objClaimLodgementBean.setDateOfNPAAsRBI((resultset
							.getString("DATE_OF_NPA_AS_RBI")));
				} else {
					objClaimLodgementBean.setDateOfNPAAsRBI("");
				}
				if (resultset.getString("DATE_OF_NPA_AS_RBI_REMARKS") != null
						|| resultset.getString("DATE_OF_NPA_AS_RBI_REMARKS") != "") {
					objClaimLodgementBean.setDateOfNPAAsRBIRemarks((resultset
							.getString("DATE_OF_NPA_AS_RBI_REMARKS")));
				} else {
					objClaimLodgementBean.setDateOfNPAAsRBIRemarks("");
				}
				if (resultset.getString("WHETHER_OUTSTANDING") != null
						|| resultset.getString("WHETHER_OUTSTANDING") != "") {
					objClaimLodgementBean.setWhetherOutstanding((resultset
							.getString("WHETHER_OUTSTANDING")));
				} else {
					objClaimLodgementBean.setWhetherOutstanding("");
				}
				if (resultset.getString("WHETHER_OUTSTANDING_REMARKS") != null
						|| resultset.getString("WHETHER_OUTSTANDING_REMARKS") != "") {
					objClaimLodgementBean
							.setWhetherOutstandingRemarks((resultset
									.getString("WHETHER_OUTSTANDING_REMARKS")));
				} else {
					objClaimLodgementBean.setWhetherOutstandingRemarks("");
				}
				if (resultset.getString("APPRISAL_DISBURSEMENT") != null
						|| resultset.getString("APPRISAL_DISBURSEMENT") != "") {
					objClaimLodgementBean.setApprisalDisbursement((resultset
							.getString("APPRISAL_DISBURSEMENT")));
				} else {
					objClaimLodgementBean.setApprisalDisbursement("");
				}
				if (resultset.getString("APPRISAL_DISBURSEMENT_REMARKS") != null
						|| resultset.getString("APPRISAL_DISBURSEMENT_REMARKS") != "") {
					objClaimLodgementBean
							.setApprisalDisbursementRemarks((resultset
									.getString("APPRISAL_DISBURSEMENT_REMARKS")));
				} else {
					objClaimLodgementBean.setApprisalDisbursementRemarks("");
				}
				if (resultset.getString("POST_DISBURSEMENT") != null
						|| resultset.getString("POST_DISBURSEMENT") != "") {
					objClaimLodgementBean.setPostDisbursement((resultset
							.getString("POST_DISBURSEMENT")));
				} else {
					objClaimLodgementBean.setPostDisbursement("");
				}
				if (resultset.getString("POST_DISBURSEMENT_REMARKS") != null
						|| resultset.getString("POST_DISBURSEMENT_REMARKS") != "") {
					objClaimLodgementBean.setPostDisbursementRemarks((resultset
							.getString("POST_DISBURSEMENT_REMARKS")));
				} else {
					objClaimLodgementBean.setPostDisbursementRemarks("");
				}
				if (resultset.getString("EXERCISE_CARRIED") != null
						|| resultset.getString("EXERCISE_CARRIED") != "") {
					objClaimLodgementBean.setExerciseCarried((resultset
							.getString("EXERCISE_CARRIED")));
				} else {
					objClaimLodgementBean.setExerciseCarried("");
				}

				if (resultset.getString("EXERCISE_CARRIED_REMARKS") != null
						|| resultset.getString("EXERCISE_CARRIED_REMARKS") != "") {
					objClaimLodgementBean.setExerciseCarriedRemarks((resultset
							.getString("EXERCISE_CARRIED_REMARKS")));
				} else {
					objClaimLodgementBean.setExerciseCarriedRemarks("");
				}
				if (resultset.getString("INTERNAL_RATTING") != null
						|| resultset.getString("INTERNAL_RATTING") != "") {
					objClaimLodgementBean.setInternalRating((resultset
							.getString("INTERNAL_RATTING")));
				} else {
					objClaimLodgementBean.setInternalRating("");
				}
				if (resultset.getString("INTERNAL_RATTING_REMARKS") != null
						|| resultset.getString("INTERNAL_RATTING_REMARKS") != "") {
					objClaimLodgementBean.setInternalRatingRemarks((resultset
							.getString("INTERNAL_RATTING_REMARKS")));
				} else {
					objClaimLodgementBean.setInternalRatingRemarks("");
				}
				if (resultset.getString("RECOVER_PERTAINING") != null
						|| resultset.getString("RECOVER_PERTAINING") != "") {
					objClaimLodgementBean.setRecoverPertaining((resultset
							.getString("RECOVER_PERTAINING")));
				} else {
					objClaimLodgementBean.setRecoverPertaining("");
				}
				if (resultset.getString("RECOVER_PERTAINING_REMARKS") != null
						|| resultset.getString("RECOVER_PERTAINING_REMARKS") != "") {
					objClaimLodgementBean
							.setRecoverPertainingRemarks((resultset
									.getString("RECOVER_PERTAINING_REMARKS")));
				} else {
					objClaimLodgementBean.setRecoverPertainingRemarks("");
				}
				if (resultset.getString("CLAIM_CHECK_LIST_FLAG") != null
						|| resultset.getString("CLAIM_CHECK_LIST_FLAG") != "") {
					objClaimLodgementBean.setClaimCheckListFlag((resultset
							.getString("CLAIM_CHECK_LIST_FLAG")));
				} else {
					objClaimLodgementBean.setClaimCheckListFlag("");
				}

			}
			if (empty) {
				objClaimLodgementBean = new ClaimLodgementBean();
				objClaimLodgementBean.setActivityEligible("");
				objClaimLodgementBean.setActivityEligibleRemarks("");
				objClaimLodgementBean.setCibil("");
				objClaimLodgementBean.setCibilRemarks("");
				objClaimLodgementBean.setRateCharge("");
				objClaimLodgementBean.setRateChargeRemarks("");
				objClaimLodgementBean.setDateOfNPAAsRBI("");
				objClaimLodgementBean.setDateOfNPAAsRBIRemarks("");
				objClaimLodgementBean.setWhetherOutstanding("");
				objClaimLodgementBean.setWhetherOutstandingRemarks("");
				objClaimLodgementBean.setApprisalDisbursement("");
				objClaimLodgementBean.setApprisalDisbursementRemarks("");
				objClaimLodgementBean.setPostDisbursement("");
				objClaimLodgementBean.setPostDisbursementRemarks("");
				objClaimLodgementBean.setExerciseCarried("");
				objClaimLodgementBean.setExerciseCarriedRemarks("");
				objClaimLodgementBean.setInternalRating("");
				objClaimLodgementBean.setInternalRatingRemarks("");
				objClaimLodgementBean.setRecoverPertaining("");
				objClaimLodgementBean.setRecoverPertainingRemarks("");
				objClaimLodgementBean.setClaimCheckListFlag("");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objClaimLodgementBean;
	}

	public void saveBlobDocument(ClaimLodgeBlobModel claimLodgeBlobModelObj,String claimRefNO,int claimLodgementCheckListDataSave) {
		try{
			if(claimRefNO!="" && claimRefNO!=null){
				if(claimLodgementCheckListDataSave==1){
					sessionFactory.getCurrentSession().save(claimLodgeBlobModelObj);
					
				}
			}
				
		}catch(Exception e){
			System.out.println("Exception==="+e);
		}finally{
			sessionFactory.close();
		}
	}

	@Override
	public ClaimLodgementBean getCGPANForClaim(String cgpan, String userId) {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		ClaimLodgementBean claimBean;
		int flag = 0;
		int result1 = 0;
		Double total = 0.0;
		ArrayList<NbfcAppropriationBean> receivedPayments = null;

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{call Proc_NBFC_GetCgpan_ForClaim(?,?,?,?)}");
			// getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setString(1, cgpan);
			getReceivedPaymentsStmt.setString(2, userId);
			getReceivedPaymentsStmt.registerOutParameter(3, Types.INTEGER);
			// getReceivedPaymentsStmt.registerOutParameter(5,
			// OracleTypes.CURSOR);
			getReceivedPaymentsStmt.registerOutParameter(4, Types.VARCHAR);
			getReceivedPaymentsStmt.execute();

			String error = getReceivedPaymentsStmt.getString(4);
			if (null != error) {
				if (error.equals("NO_DATA_FOUND")) {
					int cnt = getReceivedPaymentsStmt.getInt(3);
					claimBean = new ClaimLodgementBean();
					if (cnt < 1) {
						claimBean.setCnt(0);// not appl

					} else {

						claimBean.setCnt(1);// yes
					}

					getReceivedPaymentsStmt.close();
					getReceivedPaymentsStmt = null;

					// throw new CustomExceptionHandler(error);

				} else {

					getReceivedPaymentsStmt.close();
					getReceivedPaymentsStmt = null;

					throw new CustomExceptionHandler(error);

				}

			} else {
				int cnt = getReceivedPaymentsStmt.getInt(3);
				claimBean = new ClaimLodgementBean();
				if (cnt < 1) {
					claimBean.setCnt(0);// not appl

				} else {

					claimBean.setCnt(1);// yes
				}

				getReceivedPaymentsStmt.close();
				getReceivedPaymentsStmt = null;

			}

		} catch (Exception exception) {

			try {
				conn.close();
			} catch (SQLException ignore) {
				throw new CustomExceptionHandler(ignore.getMessage());
			}

			throw new CustomExceptionHandler(exception.getMessage());
		} finally {
			session.close();

		}

		return claimBean;
	}

	@Override
	public ClaimLodgementBean checkDuplicateCGPANForClaim(String cgpan,
			String userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		ClaimLodgementBean claimBean;
		int flag = 0;
		int result1 = 0;
		Double total = 0.0;
		ArrayList<NbfcAppropriationBean> receivedPayments = null;

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call FuncValidateDupCgpanClaim(?,?,?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setString(2, cgpan);
			getReceivedPaymentsStmt.setString(3, userId);
			getReceivedPaymentsStmt.registerOutParameter(4, OracleTypes.CURSOR);
			getReceivedPaymentsStmt.registerOutParameter(5, Types.VARCHAR);
			getReceivedPaymentsStmt.execute();

			int functionReturnValue = getReceivedPaymentsStmt.getInt(1);

			if (functionReturnValue == 1) {

				String error = getReceivedPaymentsStmt.getString(5);
				getReceivedPaymentsStmt.close();
				getReceivedPaymentsStmt = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

				resultSet = (ResultSet) getReceivedPaymentsStmt.getObject(4);
				exception = getReceivedPaymentsStmt.getString(5);
				int COUNT = 0;
				claimBean = new ClaimLodgementBean();
				if (resultSet != null) {
					while (resultSet.next()) {

						COUNT = resultSet.getInt(1);
						if (COUNT == 0) {
							claimBean.setDupCnt(0);// not appl

						} else {

							claimBean.setDupCnt(1);// yes
						}

					}
					// na.setChcktbl(receivedPayments);
				} else {
					claimBean.setDupCnt(2);// yes
				}

			}
			resultSet.close();
			getReceivedPaymentsStmt.close();
			getReceivedPaymentsStmt = null;

			conn.commit();

		} catch (Exception exception) {

			try {
				conn.rollback();
			} catch (SQLException ignore) {
				throw new CustomExceptionHandler(ignore.getMessage());
			}

			throw new CustomExceptionHandler(exception.getMessage());
		} finally {
			session.close();

		}

		return claimBean;
	}

	@Override
	public ClaimLodgementBean getClaimChecklistRecommndation(String claimRefNo) {
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		@SuppressWarnings("unused")
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		ArrayList<ClaimLodgementBean> ClaimBeanList = new ArrayList<ClaimLodgementBean>();
		ClaimLodgementBean objClaimLodgementBean=null;
		BigDecimal bg;
		try {
			DecimalFormat decimalFormat=new DecimalFormat("0.00");
			CallableStatement callableStatement = (CallableStatement) con.prepareCall("{ call PROC_CLAIM_CHECKLIST_VALIDATE(?,?,?) } ");
			callableStatement.setString(1, claimRefNo);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute(); 
			resultset=(ResultSet) callableStatement.getObject(3);
			while (resultset.next()) {
				objClaimLodgementBean = new ClaimLodgementBean();			
				objClaimLodgementBean.setClaimRefNo((resultset.getString("CLAIM_REF_NO")));	
				objClaimLodgementBean.setActivityEligible((resultset.getString("ACTIVITY_ELIGIGIBLE")));	
				objClaimLodgementBean.setCibil((resultset.getString("CIBIL")));	
				objClaimLodgementBean.setRateCharge((resultset.getString("RATE_CHARGE")));	
				objClaimLodgementBean.setDateOfNPAAsRBI((resultset.getString("DATE_OF_NPA_AS_RBI")));	
				objClaimLodgementBean.setApprisalDisbursement((resultset.getString("APPRISAL_DISBURSEMENT")));	
				objClaimLodgementBean.setPostDisbursement((resultset.getString("POST_DISBURSEMENT")));	
				objClaimLodgementBean.setExerciseCarried((resultset.getString("EXERCISE_CARRIED")));	
				objClaimLodgementBean.setInternalRating((resultset.getString("INTERNAL_RATTING")));	
				objClaimLodgementBean.setRecoverPertaining((resultset.getString("RECOVER_PERTAINING")));
				objClaimLodgementBean.setActivityEligibleStatus((resultset.getString("STATUS1")));
				objClaimLodgementBean.setCibilStatus((resultset.getString("STATUS2")));
				objClaimLodgementBean.setRateChargeStatus((resultset.getString("STATUS3")));
				objClaimLodgementBean.setDateOfNPAAsRBIStatus((resultset.getString("STATUS4")));
				objClaimLodgementBean.setApprisalDisbursementStatus((resultset.getString("STATUS6")));
				objClaimLodgementBean.setPostDisbursementStatus((resultset.getString("STATUS7")));
				objClaimLodgementBean.setExerciseCarriedStatus((resultset.getString("STATUS8")));
				objClaimLodgementBean.setInternalRatingStatus((resultset.getString("STATUS9")));
				objClaimLodgementBean.setRecoverPertainingStatus((resultset.getString("STATUS10")));
				objClaimLodgementBean.setActivityEligibleRemarks((resultset.getString("ACTIVITY_ELIGIGIBLE_REMARKS")));	
				objClaimLodgementBean.setCibilRemarks((resultset.getString("CIBIL_REMARKS")));	
				objClaimLodgementBean.setRateChargeRemarks((resultset.getString("RATE_CHARGE_REMARKS")));	
				objClaimLodgementBean.setDateOfNPAAsRBIRemarks((resultset.getString("DATE_OF_NPA_AS_RBI_REMARKS")));	
				objClaimLodgementBean.setApprisalDisbursementRemarks((resultset.getString("APPRISAL_DISBURSEMENT_REMARKS")));	
				objClaimLodgementBean.setPostDisbursementRemarks((resultset.getString("POST_DISBURSEMENT_REMARKS")));	
				objClaimLodgementBean.setExerciseCarriedRemarks((resultset.getString("EXERCISE_CARRIED_REMARKS")));	
				objClaimLodgementBean.setInternalRatingRemarks((resultset.getString("INTERNAL_RATTING_REMARKS")));	
				objClaimLodgementBean.setRecoverPertainingRemarks((resultset.getString("RECOVER_PERTAINING_REMARKS")));	
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session3.close();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			return objClaimLodgementBean;	
	}	
	

	@Override
	public List<ClaimLodgementBean> getClaimReturnedRecordsByNBFCChecker(
			String loginUserMemId) {
		ClaimLodgementBean objClaimLodgementBean = null;
		System.out
				.println("getMliBorrowerNpaDtlsBeforClaimLodgement method called to procedure inside DAOImpl==");
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		@SuppressWarnings("unused")
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		ArrayList<ClaimLodgementBean> ClaimBeanList = new ArrayList<ClaimLodgementBean>();

		BigDecimal bg;
		try {
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			CallableStatement callableStatement = (CallableStatement) con
					.prepareCall("{ call PROC_GET_CLAIM_RETURN_DTLS(?,?,?) } ");
			callableStatement.setString(1, loginUserMemId);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			resultset = (ResultSet) callableStatement.getObject(3);
			while (resultset.next()) {
				objClaimLodgementBean = new ClaimLodgementBean();
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date claimdate = resultset.getDate(1);
				objClaimLodgementBean.setDateOfClaimLoadge(dateFormat
						.format(claimdate));
				objClaimLodgementBean.setClaimRefNo((resultset.getString(2)));
				objClaimLodgementBean.setUnitName((resultset.getString(3)));
				objClaimLodgementBean.setLatestOsAmt((resultset.getDouble(4)));
				objClaimLodgementBean.setClaimStatus((resultset.getString(5)));
				objClaimLodgementBean.setRemarks((resultset.getString(6)));
				objClaimLodgementBean.setCgpan((resultset.getString(7)));
				ClaimBeanList.add(objClaimLodgementBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ClaimBeanList;
	}

	public String updateClaimLodgmentData(
			ClaimLodgementBean claimLodgementBean, String claimRefNo,
			String loginUserId) {
		SimpleDateFormat formatObj = new SimpleDateFormat("dd/MM/yyyy");
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		Connection conn = session.connection();
		CallableStatement callStmt = null;
		int dataUpdated = 0;
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		int count = 1;
		String success = "NoUpdated";
		try {
			if (claimLodgementBean != null) {
				Date sysdate = new Date();
				ResultSet resultSet = null;
				/*
				 * String claimStatus="NMCL";//33
				 */String claimStatus = "MR";// 33
				String claimRefNumber = claimRefNo;
				System.out.println("claimRefNumber====" + claimRefNo);
				callStmt = conn
						.prepareCall("{call pro_Edit_CL_Data_By_MLIMaker2(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				callStmt.setString(1, claimRefNumber);

				if (claimLodgementBean.getDealingOfficerName() != ""
						|| claimLodgementBean.getDealingOfficerName() != null) {
					callStmt.setString(2,
							claimLodgementBean.getDealingOfficerName());
				}

				if (claimLodgementBean.getDealingOfficerNO() != ""
						|| claimLodgementBean.getDealingOfficerNO() != null) {
					callStmt.setString(3,
							claimLodgementBean.getDealingOfficerNO());
				}

				callStmt.setString(4, claimLodgementBean.getWilfulDefaulter());
				callStmt.setString(5, claimLodgementBean.getFraudAcc());
				callStmt.setString(6, claimLodgementBean.getEnquiryConcluded());
				callStmt.setString(7, claimLodgementBean.getMliReported());
				String dOfN=claimLodgementBean.getDateOfNotice();
				
				if (dOfN.length()!=0) {
					Date parsed22 = formatObj.parse(dOfN);
					java.sql.Date sql22 = new java.sql.Date(parsed22.getTime());
					callStmt.setDate(8, sql22);
				} else {
					callStmt.setDate(8, null);
				}

				if (claimLodgementBean.getForum() != ""
						|| claimLodgementBean.getForum() != null) {
					callStmt.setString(9, claimLodgementBean.getForum());
				}

				if (claimLodgementBean.getSuitNo() != ""
						|| claimLodgementBean.getSuitNo() != null) {
					callStmt.setString(10, claimLodgementBean.getSuitNo());
				}
				if (claimLodgementBean.getResonFillingSuit() != ""
						|| claimLodgementBean.getResonFillingSuit() != null) {
					callStmt.setString(11,
							claimLodgementBean.getResonFillingSuit());
				}
				System.out.println("DateOfSarfaesi=="
						+ claimLodgementBean.getDateOfSarfaesi());
				if (claimLodgementBean.getDateOfSarfaesi() != null) {
					Date parsed1 = formatObj.parse(claimLodgementBean
							.getDateOfSarfaesi().toString());
					java.sql.Date sql1 = new java.sql.Date(parsed1.getTime());
					callStmt.setDate(12, sql1);
				} else {
					callStmt.setDate(12, null);
				}

				if (claimLodgementBean.getLocationOfForum() != ""
						|| claimLodgementBean.getLocationOfForum() != null) {
					callStmt.setString(13,
							claimLodgementBean.getLocationOfForum());
				}
				callStmt.setDouble(14, claimLodgementBean.getClaimAmt());
				callStmt.setString(15,
						claimLodgementBean.getAnySubsidyInvolved());
				callStmt.setString(16, claimLodgementBean.getSubsidyReceived());
				callStmt.setString(17, claimLodgementBean.getSubsidyAdjust());
				System.out.println("DateOfSubsidy=="
						+ claimLodgementBean.getDateOfSubsidy());
				if (claimLodgementBean.getDateOfSubsidy().length() > 0) {
					Date parsed3 = formatObj.parse(claimLodgementBean
							.getDateOfSubsidy().toString());
					java.sql.Date sql3 = new java.sql.Date(parsed3.getTime());
					callStmt.setDate(18, sql3);
				} else {
					callStmt.setDate(18, null);
				}

				System.out.println("SubsidyAmt=="
						+ claimLodgementBean.getSubsidyAmt());
				if (claimLodgementBean.getSubsidyAmt() != null) {
					callStmt.setDouble(19, claimLodgementBean.getSubsidyAmt());
				} else {
					callStmt.setDouble(19, 0);
				}

				callStmt.setDouble(20, claimLodgementBean.getRecovery());

				if (claimLodgementBean.getModeRecovery() != ""
						|| claimLodgementBean.getModeRecovery() != null) {
					callStmt.setString(21, claimLodgementBean.getModeRecovery());
				}

				callStmt.setString(22, claimLodgementBean.getNpaRecoveryFlag());
				callStmt.setString(23,
						claimLodgementBean.getConfirmRecoveryFlag());

				System.out.println("getLandValue2=="
						+ claimLodgementBean.getLandValue2());
				if (claimLodgementBean.getLandValue2() != null) {
					callStmt.setDouble(24, claimLodgementBean.getLandValue2());
				} else {
					callStmt.setDouble(24, 0.0);
				}

				System.out.println("getBuildingValue2=="
						+ claimLodgementBean.getBuildingValue2());
				if (claimLodgementBean.getBuildingValue2() != null) {
					callStmt.setDouble(25,
							claimLodgementBean.getBuildingValue2());
				} else {
					callStmt.setDouble(25, 0.0);
				}

				System.out.println("getPlanetValue2=="
						+ claimLodgementBean.getPlanetValue2());
				if (claimLodgementBean.getPlanetValue2() != null) {
					callStmt.setDouble(26, claimLodgementBean.getPlanetValue2());
				} else {
					callStmt.setDouble(26, 0.0);
				}
				System.out.println("getOtherAssetValue2=="
						+ claimLodgementBean.getOtherAssetValue2());
				if (claimLodgementBean.getOtherAssetValue2() != null) {
					callStmt.setDouble(27,
							claimLodgementBean.getOtherAssetValue2());
				} else {
					callStmt.setDouble(27, 0.0);
				}
				System.out.println("getCurrentAssetValue2=="
						+ claimLodgementBean.getCurrentAssetValue2());
				if (claimLodgementBean.getCurrentAssetValue2() != null) {
					callStmt.setDouble(28,
							claimLodgementBean.getCurrentAssetValue2());
				} else {
					callStmt.setDouble(28, 0.0);
				}

				System.out.println("getOthersValue2=="
						+ claimLodgementBean.getOthersValue2());
				if (claimLodgementBean.getOthersValue2() != null) {
					callStmt.setDouble(29, claimLodgementBean.getOthersValue2());
				} else {
					callStmt.setDouble(29, 0.0);
				}

				System.out.println("getNetworthOfPromotor2=="
						+ claimLodgementBean.getNetworthOfPromotor2());
				if (claimLodgementBean.getOthersValue2() != null) {
					callStmt.setDouble(30,
							claimLodgementBean.getNetworthOfPromotor2());
				} else {
					callStmt.setDouble(30, 0.0);
				}

				callStmt.setDouble(31,
						claimLodgementBean.getTotalSecuritydetails2());
				callStmt.setDouble(32, claimLodgementBean.getOsAmtClaim());
				callStmt.setDouble(33, claimLodgementBean.getEligableAmtClaim());
				callStmt.setDouble(34,
						claimLodgementBean.getFirstInstallClaim());

				if (claimLodgementBean.getFinancialPositionComments() != ""
						|| claimLodgementBean.getFinancialPositionComments() != null) {
					callStmt.setString(35,
							claimLodgementBean.getFinancialPositionComments());
				}

				if (claimLodgementBean.getFinancialAssistanceDtls() != ""
						|| claimLodgementBean.getFinancialAssistanceDtls() != null) {
					callStmt.setString(36,
							claimLodgementBean.getFinancialAssistanceDtls());
				}

				if (claimLodgementBean.getCreditSupport() != ""
						|| claimLodgementBean.getCreditSupport() != null) {
					callStmt.setString(37,
							claimLodgementBean.getCreditSupport());
				}

				if (claimLodgementBean.getBankFacilityDtls() != ""
						|| claimLodgementBean.getBankFacilityDtls() != null) {
					callStmt.setString(38,
							claimLodgementBean.getBankFacilityDtls());
				}
				System.out.println("getRemarks=="
						+ claimLodgementBean.getRemarks());
				if (claimLodgementBean.getRemarks() != ""
						|| claimLodgementBean.getRemarks() != null) {
					callStmt.setString(39, claimLodgementBean.getRemarks());
				} else {
					callStmt.setString(39, null);
				}
				callStmt.setString(40, claimStatus);
				callStmt.setString(41,
						claimLodgementBean.getClaimLodgementCertificate());
				callStmt.setString(42,
						claimLodgementBean.getNbfcMakerClaimLodgRemarks());
				callStmt.setString(43, loginUserId);
				if(claimLodgementBean.getInternalRating1()!="" || claimLodgementBean.getInternalRating1()!=null ){
					callStmt.setString(44, claimLodgementBean.getInternalRating1());
				}
				else{
					callStmt.setString(44, "");
				}
				dataUpdated = callStmt.executeUpdate();
				System.out.println("dataUpdated ==" + dataUpdated);
				conn.close();
				callStmt.close();
				success = "Updated";
				return success;

			} else {
				System.out.println("HI==");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	public int updateClaimLodgementCheckListData(
			ClaimLodgementBean claimLodgementBean, String claimRefNo) {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		Connection conn = session.connection();
		CallableStatement callStmt = null;
		int dataSaved = 0;
		try {
			if (claimLodgementBean != null) {
				Date sysdate = new Date();
				ResultSet resultSet = null;
				String exception = "";
				String claimRefNumber = claimRefNo;
				callStmt = conn
						.prepareCall("{call pro_Edit_CL_CheckList(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				callStmt.setString(1, claimRefNumber);

				callStmt.setString(2, claimLodgementBean.getActivityEligible());

				if (claimLodgementBean.getActivityEligibleRemarks() != ""
						|| claimLodgementBean.getActivityEligibleRemarks() != null) {
					callStmt.setString(3,
							claimLodgementBean.getActivityEligibleRemarks());
				} else {
					callStmt.setString(3, "");
				}
				callStmt.setString(4, claimLodgementBean.getCibil());

				if (claimLodgementBean.getCibilRemarks() != ""
						|| claimLodgementBean.getCibilRemarks() != null) {
					callStmt.setString(5, claimLodgementBean.getCibilRemarks());
				} else {
					callStmt.setString(5, "");
				}
				callStmt.setString(6, claimLodgementBean.getRateCharge());

				if (claimLodgementBean.getRateChargeRemarks() != ""
						|| claimLodgementBean.getRateChargeRemarks() != null) {
					callStmt.setString(7,
							claimLodgementBean.getRateChargeRemarks());
				} else {
					callStmt.setString(7, "");
				}

				callStmt.setString(8, claimLodgementBean.getDateOfNPAAsRBI());

				if (claimLodgementBean.getDateOfNPAAsRBIRemarks() != ""
						|| claimLodgementBean.getDateOfNPAAsRBIRemarks() != null) {
					callStmt.setString(9,
							claimLodgementBean.getDateOfNPAAsRBIRemarks());
				} else {
					callStmt.setString(9, "");
				}

				callStmt.setString(10,
						claimLodgementBean.getWhetherOutstanding());

				if (claimLodgementBean.getWhetherOutstandingRemarks() != ""
						|| claimLodgementBean.getWhetherOutstandingRemarks() != null) {
					callStmt.setString(11,
							claimLodgementBean.getWhetherOutstandingRemarks());
				} else {
					callStmt.setString(11, "");
				}

				callStmt.setString(12,
						claimLodgementBean.getApprisalDisbursement());

				if (claimLodgementBean.getApprisalDisbursementRemarks() != ""
						|| claimLodgementBean.getApprisalDisbursementRemarks() != null) {
					callStmt.setString(13,
							claimLodgementBean.getApprisalDisbursementRemarks());
				} else {
					callStmt.setString(13, "");
				}

				callStmt.setString(14, claimLodgementBean.getPostDisbursement());

				if (claimLodgementBean.getPostDisbursementRemarks() != ""
						|| claimLodgementBean.getPostDisbursementRemarks() != null) {
					callStmt.setString(15,
							claimLodgementBean.getPostDisbursementRemarks());
				} else {
					callStmt.setString(15, "");
				}

				callStmt.setString(16, claimLodgementBean.getExerciseCarried());

				if (claimLodgementBean.getExerciseCarriedRemarks() != ""
						|| claimLodgementBean.getExerciseCarriedRemarks() != null) {
					callStmt.setString(17,
							claimLodgementBean.getExerciseCarriedRemarks());
				} else {
					callStmt.setString(17, "");
				}

				callStmt.setString(18, claimLodgementBean.getInternalRating());

				if (claimLodgementBean.getInternalRatingRemarks() != ""
						|| claimLodgementBean.getInternalRatingRemarks() != null) {
					callStmt.setString(19,
							claimLodgementBean.getInternalRatingRemarks());
				} else {
					callStmt.setString(19, "");
				}

				callStmt.setString(20,
						claimLodgementBean.getRecoverPertaining());

				if (claimLodgementBean.getRecoverPertainingRemarks() != ""
						|| claimLodgementBean.getRecoverPertainingRemarks() != null) {
					callStmt.setString(21,
							claimLodgementBean.getRecoverPertainingRemarks());
				} else {
					callStmt.setString(21, "");
				}

				dataSaved = callStmt.executeUpdate();
				System.out.println("Record Updated==" + dataSaved);
				conn.close();
				callStmt.close();
				return dataSaved;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataSaved;
	}

	@Override
	public ClaimLodgementBean getRecommandationCGS(String claimRefNo) {
		// TODO Auto-generated method stub
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		@SuppressWarnings("unused")
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		ArrayList<ClaimLodgementBean> ClaimBeanList = new ArrayList<ClaimLodgementBean>();
		ClaimLodgementBean objClaimLodgementBean = null;

		BigDecimal bg;
		try {
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			CallableStatement callableStatement = (CallableStatement) con
					.prepareCall("{ call PROC_RECOMMENDATION_CGS(?,?,?) } ");
			callableStatement.setString(1, claimRefNo);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			resultset = (ResultSet) callableStatement.getObject(3);
			while (resultset.next()) {
				objClaimLodgementBean = new ClaimLodgementBean();
				objClaimLodgementBean.setIntrestRate((resultset
						.getString("INTEREST_RATE")));
				objClaimLodgementBean.setActivityType((resultset
						.getString("INDUSTRY_NATURE")));
				objClaimLodgementBean.setWilfulDefaulter((resultset
						.getString("WILFUL_DEFAULTER")));
				objClaimLodgementBean.setFraudAcc((resultset
						.getString("ACC_CLASSIFIED_FAULT")));
				objClaimLodgementBean.setForum((resultset.getString("FORUM")));
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date claimdate = resultset.getDate("DATE_SARFAESI_ACT");
				objClaimLodgementBean.setLegalFileDate(dateFormat
						.format(claimdate));
				objClaimLodgementBean.setSuitNo((resultset
						.getString("SUIT_REG_NO")));
				String internal_rating = resultset.getString("INTERNAL_RATING");

				if (internal_rating == "") {
					internal_rating = "NA";
				} else {
					internal_rating = internal_rating;
				}
				System.out.println("rating--" + internal_rating);
				objClaimLodgementBean.setInternalRating(internal_rating);

				objClaimLodgementBean.setIntrestRateStatus((resultset
						.getString("STATUS1")));
				objClaimLodgementBean.setActivitytypeStatus((resultset
						.getString("STATUS2")));
				objClaimLodgementBean.setWilfuldefaulterStatus((resultset
						.getString("STATUS3")));
				objClaimLodgementBean.setAccFraudStatus((resultset
						.getString("STATUS4")));
				objClaimLodgementBean.setForumStatus((resultset
						.getString("STATUS5")));
				objClaimLodgementBean.setLegalFileDateStatus((resultset
						.getString("STATUS6")));
				objClaimLodgementBean.setSuitNoStatus((resultset
						.getString("STATUS7")));
				objClaimLodgementBean.setInternalRatingStatus((resultset
						.getString("STATUS8")));
				objClaimLodgementBean.setWithinNPA((resultset
						.getString("STATUS9")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objClaimLodgementBean;
	}

	@Override
	public MLIName getMLIName(String mLIID) {
		return (MLIName) sessionFactory.getCurrentSession().get(MLIName.class,
				mLIID);
	}

	@Override
	public ClaimLodgementBean getClaimCheckerDetails(String claimRefNo) {
		// TODO Auto-generated method stub
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		@SuppressWarnings("unused")
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		ArrayList<ClaimLodgementBean> ClaimBeanList = new ArrayList<ClaimLodgementBean>();
		ClaimLodgementBean objClaimLodgementBean = null;
		BigDecimal bg;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			CallableStatement callableStatement = (CallableStatement) con
					.prepareCall("{ call PROC_CLAIM_CHECKER_DETAILS(?,?,?) } ");
			callableStatement.setString(1, claimRefNo);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			resultset = (ResultSet) callableStatement.getObject(3);

			while (resultset.next()) {
				objClaimLodgementBean = new ClaimLodgementBean();
				objClaimLodgementBean.setMliID((resultset.getString("MLI_ID")));
				Date date1 = resultset.getDate("NBFC_CHECKER_DATE");
				String Fname = resultset.getString("USR_FIRST_NAME");
				String Lname = resultset.getString("USR_LAST_NAME");
				objClaimLodgementBean.setDateOfClaimLoadge((dateFormat
						.format(date1)));
				objClaimLodgementBean.setUserName((Fname) + " " + (Lname));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objClaimLodgementBean;
	}

	@Override
	public List<ClaimLodgementBean> getClaimReturnReasons() {
		// TODO Auto-generated method stub
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		@SuppressWarnings("unused")
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		ArrayList<ClaimLodgementBean> ClaimBeanList = new ArrayList<ClaimLodgementBean>();
		ClaimLodgementBean objClaimLodgementBean = null;
		BigDecimal bg;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			CallableStatement callableStatement = (CallableStatement) con
					.prepareCall("{ call PROC_CLAIM_RETURN_REMARKS_LIST(?,?,?) } ");
			callableStatement.setString(1, "");
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			resultset = (ResultSet) callableStatement.getObject(3);

			while (resultset.next()) {
				objClaimLodgementBean = new ClaimLodgementBean();
				objClaimLodgementBean.setRemarks((resultset
						.getString("RETURN_REMARKS")));
				objClaimLodgementBean.setRemarkID((resultset
						.getString("RETURN_ID")));
				ClaimBeanList.add(objClaimLodgementBean);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ClaimBeanList;
	}

	public String submitForClaimReturnResons(
			List<ClaimLodgementBean> claimLodgList,String returnRemark) {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		Connection conn = session.connection();
		CallableStatement callStmt = null;
		int dataSaved = 0;
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		int count = 1;
		String Claim_REfno = "";
		try {
String remarkID ="";
String usr_name ="";
			for (ClaimLodgementBean dt : claimLodgList) {
				
				
				remarkID=remarkID+","+dt.getRemarkID();
				Claim_REfno=dt.getClaimRefNo();
				usr_name=dt.getUserName();
			}
			remarkID = remarkID.replaceFirst(",", "");
			System.out.println("ddddd" +remarkID);
				callStmt = conn
						.prepareCall("{call pro_saveClaimReturnRemarks(?,?,?,?)}");
				// callStmt.setString(1, claimRefNo1);//CLAIM_REF_NO
				callStmt.setString(1, Claim_REfno);
				callStmt.setString(2, remarkID);
				callStmt.setString(3, usr_name);
				callStmt.setString(4, returnRemark);// WILFUL_DEFAULTER
				dataSaved = callStmt.executeUpdate();
			
			System.out.println("Record Save==" + dataSaved);
			
			// Claim_REfno=callStmt.getString(45);
			System.out.println("Claim_REfno Save==" + Claim_REfno);
			conn.close();
			callStmt.close();
			count++;
			return Claim_REfno;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Claim_REfno;
	}

	@Override
	public List<ClaimLodgementBean> getClaimReturnedRecordsByNBFCCheckerCGS(
			String loginUserMemId) {
		// TODO Auto-generated method stub
		ClaimLodgementBean objClaimLodgementBean = null;
		System.out
				.println("getMliBorrowerNpaDtlsBeforClaimLodgement method called to procedure inside DAOImpl==");
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		@SuppressWarnings("unused")
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		ArrayList<ClaimLodgementBean> ClaimBeanList = new ArrayList<ClaimLodgementBean>();

		BigDecimal bg;
		try {
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			CallableStatement callableStatement = (CallableStatement) con
					.prepareCall("{ call PROC_GET_CLAIM_RETURN_DTLS_CGS(?,?,?) } ");
			callableStatement.setString(1, loginUserMemId);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			resultset = (ResultSet) callableStatement.getObject(3);
			while (resultset.next()) {
				objClaimLodgementBean = new ClaimLodgementBean();
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date claimdate = resultset.getDate(1);
				objClaimLodgementBean.setDateOfClaimLoadge(dateFormat
						.format(claimdate));
				objClaimLodgementBean.setClaimRefNo((resultset.getString(2)));
				objClaimLodgementBean.setUnitName((resultset.getString(3)));
				objClaimLodgementBean.setLatestOsAmt((resultset.getDouble(4)));
				objClaimLodgementBean.setClaimStatus((resultset.getString(5)));
				objClaimLodgementBean.setRemarks((resultset.getString(6)));
				objClaimLodgementBean.setCgpan((resultset.getString(7)));
				ClaimBeanList.add(objClaimLodgementBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ClaimBeanList;
	}

	@Override
	public List<ClaimLodgementBean> getClaimLoadgmentDetailsCGS(
			String loginUserMemId) {
		// TODO Auto-generated method stub
		ClaimLodgementBean objClaimLodgementBean = null;

		System.out
				.println("getMliBorrowerNpaDtlsBeforClaimLodgement method called to procedure inside DAOImpl==");
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		@SuppressWarnings("unused")
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		ArrayList<ClaimLodgementBean> ClaimBeanList = new ArrayList<ClaimLodgementBean>();

		BigDecimal bg;
		try {
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			CallableStatement callableStatement = (CallableStatement) con
					.prepareCall("{ call PROC_GET_CLAIM_DETAIL_LIST_CGS(?,?,?) } ");
			callableStatement.setString(1, loginUserMemId);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			resultset = (ResultSet) callableStatement.getObject(3);
			while (resultset.next()) {
				objClaimLodgementBean = new ClaimLodgementBean();
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date claimdate = resultset.getDate(2);
				objClaimLodgementBean.setDateOfClaimLoadge(dateFormat
						.format(claimdate));

				objClaimLodgementBean.setClaimRefNo((resultset.getString(1)));
				objClaimLodgementBean.setUnitName((resultset.getString(3)));
				// objClaimLodgementBean.setRemarks((resultset.getString(4)));
				objClaimLodgementBean.setLatestOsAmt((resultset.getDouble(5)));
				objClaimLodgementBean.setRemarks("Enter Remarks");
				objClaimLodgementBean.setFirstInstallClaim((resultset
						.getDouble(6)));
				objClaimLodgementBean
						.setChkListStatus((resultset.getString(7)));
				objClaimLodgementBean.setClaimStatus((resultset.getString(8)));

				ClaimBeanList.add(objClaimLodgementBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ClaimBeanList;
	}

	@Override
	public int saveClaimLodgementCheckListData(ClaimLodgementBean claimLodgementBean, String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveBlobDocument1(ClaimLodgeBlobModel claimLodgeBlobModelObj, String claimRefNO1,
			int updatedCheckList) {
		try{
			Query query = sessionFactory.getCurrentSession().createQuery(" UPDATE ClaimLodgeBlobModel SET LEGAL_DOCUMENT = :DOC WHERE CLAIM_REF_NO=:claimRefNo");
    		query.setParameter("claimRefNo", claimLodgeBlobModelObj.getCLAIM_REF_NO());
    		query.setParameter("DOC", claimLodgeBlobModelObj.getLEGAL_DOCUMENT());
    		int result = query.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sessionFactory.close();
		}
	}

	@Override
	public List<ClaimLodgementBean> getSaveClaimLodgementReturnReasonData(String claimRefNo) {
		// TODO Auto-generated method stub
		ClaimLodgementBean objClaimLodgementBean = null;

		System.out
				.println("getMliBorrowerNpaDtlsBeforClaimLodgement method called to procedure inside DAOImpl==");
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		@SuppressWarnings("unused")
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		ArrayList<ClaimLodgementBean> ClaimBeanList = new ArrayList<ClaimLodgementBean>();

		BigDecimal bg;
		try {
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			CallableStatement callableStatement = (CallableStatement) con
					.prepareCall("{ call PROC_GET_CLAIM_RETURN_REASON(?,?,?) } ");
			callableStatement.setString(1, claimRefNo);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			resultset = (ResultSet) callableStatement.getObject(3);
			while (resultset.next()) {
				objClaimLodgementBean = new ClaimLodgementBean();
				objClaimLodgementBean.setReturnReasons(resultset.getString("RETURN_REMARKS"));
				//objClaimLodgementBean.setClaimRefNo(resultset.getString("CLAIM_REF_NO"));
				ClaimBeanList.add(objClaimLodgementBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ClaimBeanList;
	}
	//Diksha
	@Override
	public List<ClaimLodgementBean> getClaimSettledReport(String userId, Date toDate,
			Date fromDate, String memberId,String mliLongName,String role) {
		// TODO Auto-generated method stub
		ResultSet resultset = null;
		ResultSetMetaData resultSetMetaData = null;
		ClaimLodgementBean claimLodgementBean = null;
		List<ClaimLodgementBean> ClaimSettledReportData = new ArrayList<ClaimLodgementBean>();
		try {
			Session session4 = sessionFactory.openSession();
             System.out.println("memberId :"+memberId);
			/* Transaction tn = session4.beginTransaction(); */
			Connection conn = session4.connection();
			CallableStatement cs = conn
					.prepareCall("{? = call FunGetClaimSettledData(?,?,?,?,?,?,?)}");

			// register input parameters
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setDate(2, new java.sql.Date(toDate.getTime()));
			cs.setDate(3, new java.sql.Date(fromDate.getTime()));
			cs.setString(4, memberId);
			cs.setString(5, mliLongName);
			cs.setString(6, role);
			cs.registerOutParameter(7, OracleTypes.CURSOR);
			cs.registerOutParameter(8, Types.VARCHAR);
			
			cs.execute();
			int result = cs.getInt(1);

			String pouterror = cs.getString(8);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			resultset = (ResultSet) cs.getObject(7);
			while (resultset.next()) {
				claimLodgementBean = new ClaimLodgementBean();

				claimLodgementBean.setBankName(resultset.getString("Bank_Name"));
				claimLodgementBean.setMemberId(resultset.getString("Member_ID"));
				claimLodgementBean.setBorrowerName(resultset.getString("Unit_Name"));
				claimLodgementBean.setCgpan(resultset.getString("CGPAN"));
				claimLodgementBean.setOsAmtClaim(resultset.getDouble("OUTSTANDING_AMOUNT_CLAIM"));
				claimLodgementBean.setTotalOsAmt(resultset.getDouble("OUTSTANDING_AMOUNT"));
				claimLodgementBean.setRecovery(resultset
						.getDouble("RECOVERY_AMOUNT"));
				claimLodgementBean.setSubsidyAmt(resultset
						.getDouble("SUBSIDY_AMOUNT"));
				claimLodgementBean.setLatestOsAmt(resultset
						.getDouble("LATEST_OS_AMT"));
				claimLodgementBean.setGuarantee_Amt(resultset
						.getDouble("GUARANTEE_AMOUNT"));
				claimLodgementBean.setNpaEligibleAmt(resultset
						.getDouble("CLAIM_ELIGABLE_AMT"));
				claimLodgementBean.setFirstInstallClaim(resultset
						.getDouble("FIRST_INSTALLMENT_CLAIM"));
				claimLodgementBean.setEligableAmtClaim(resultset
						.getDouble("CLAIM_ELIGIBILITY_AMOUNT"));
			claimLodgementBean.setCgtCheckerDate(resultset
						.getDate("CGTMSE_CHECKER_DATE"));
				claimLodgementBean.setLoanAccountNo(resultset
						.getString("Loan_Account_No"));
				claimLodgementBean.setMscItPan(resultset.getString("MSE_ITPAN"));
				claimLodgementBean.setChipProItPan(resultset.getString("CHIEF_IT_PAN"));
				 
						

				ClaimSettledReportData.add(claimLodgementBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ClaimSettledReportData;

	}
	
	
			
			@Override
			public List<Map<String, Object>> getClaimSettledReportAll(String userId, Date toDate,
					Date fromDate, String role) {
				// TODO Auto-generated method stub
				ResultSet resultset = null;
				ResultSetMetaData resultSetMetaData = null;
				ClaimLodgementBean claimLodgementBean = null;
				//List<ClaimLodgementBean> ClaimSettledReportData = new ArrayList<ClaimLodgementBean>();
				List<Map<String, Object>> ClaimSettledReportData = new ArrayList<Map<String, Object>>();
				try {
					Session session4 = sessionFactory.openSession();
		            
					/* Transaction tn = session4.beginTransaction(); */
					Connection conn = session4.connection();
					CallableStatement cs = conn
							.prepareCall("{? = call FunGetClaimSettledAllData(?,?,?,?,?)}");

					// register input parameters
					cs.registerOutParameter(1, Types.INTEGER);
					cs.setDate(2, new java.sql.Date(toDate.getTime()));
					cs.setDate(3, new java.sql.Date(fromDate.getTime()));
					cs.setString(4, role);
					cs.registerOutParameter(5, OracleTypes.CURSOR);
					cs.registerOutParameter(6, Types.VARCHAR);
					
					cs.execute();
					int result = cs.getInt(1);

					String pouterror = cs.getString(6);
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					if (result != 0) {
						throw new CustomExceptionHandler("Exception occured  :"+ pouterror);
					}else{
						 // Procedure execution
						resultset = (ResultSet) cs.getObject(5);
							resultSetMetaData = resultset.getMetaData();
							int coulmnCount = resultSetMetaData.getColumnCount();
							System.out.println("coulmnCount=="+coulmnCount);
							while (resultset.next()) {
							    Map<String, Object> columns = new LinkedHashMap<String, Object>();

							    for (int i = 1; i <= coulmnCount; i++) {
							        columns.put(resultSetMetaData.getColumnLabel(i), resultset.getObject(i));
							    }

							    ClaimSettledReportData.add(columns);
							}
					}
					
					/*resultset = (ResultSet) cs.getObject(5);
					while (resultset.next()) {
						claimLodgementBean = new ClaimLodgementBean();

					claimLodgementBean.setBankName(resultset.getString("Bank_Name"));
					claimLodgementBean.setMemberId(resultset.getString("Member_ID"));
					claimLodgementBean.setBorrowerName(resultset.getString("Unit_Name"));
					claimLodgementBean.setCgpan(resultset.getString("CGPAN"));
					claimLodgementBean.setOsAmtClaim(resultset.getDouble("OUTSTANDING_AMOUNT_CLAIM"));
					claimLodgementBean.setTotalOsAmt(resultset.getDouble("OUTSTANDING_AMOUNT"));
					claimLodgementBean.setRecovery(resultset
							.getDouble("RECOVERY_AMOUNT"));
					claimLodgementBean.setSubsidyAmt(resultset
							.getDouble("SUBSIDY_AMOUNT"));
					claimLodgementBean.setLatestOsAmt(resultset
							.getDouble("LATEST_OS_AMT"));
					claimLodgementBean.setGuarantee_Amt(resultset
							.getDouble("GUARANTEE_AMOUNT"));
					claimLodgementBean.setNpaEligibleAmt(resultset
							.getDouble("CLAIM_ELIGABLE_AMT"));
					claimLodgementBean.setFirstInstallClaim(resultset
							.getDouble("FIRST_INSTALLMENT_CLAIM"));
					claimLodgementBean.setEligableAmtClaim(resultset
							.getDouble("CLAIM_ELIGIBILITY_AMOUNT"));
				claimLodgementBean.setCgtCheckerDate(resultset
							.getDate("CGTMSE_CHECKER_DATE"));
					claimLodgementBean.setLoanAccountNo(resultset
							.getString("Loan_Account_No"));
					
							

					ClaimSettledReportData.add(claimLodgementBean);
				}*/

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ClaimSettledReportData;
			
		}

			@Override
			public int updateClaimReturnResonsRemark(String claimrefno,
					String returnRemark) {
				Session session = sessionFactory.openSession();
				Transaction tn = session.beginTransaction();
				Connection conn = session.connection();
				CallableStatement callStmt = null;
				Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						" UPDATE ClaimDetailsModel SET CGS_RETURN_REMARK = :status WHERE CLAIM_REF_NO=:claimRefNo");
		  query.setParameter("claimRefNo", claimrefno);
		  query.setParameter("status", returnRemark);
		  int result = query.executeUpdate();
		  return result;
			}
		
			
			@Override
			public List<Map<String, Object>> getClaimDetailsReport(String userId, Date toDate,
					Date fromDate, String memberId,String role, String claimStatus) {
				// TODO Auto-generated method stub
				ResultSet resultset ;
				ResultSetMetaData resultSetMetaData;
			ClaimLodgementBean claimLodgementBean;
				List<Map<String, Object>> ClaimDetailsReportData = new ArrayList<Map<String, Object>>();
				try {
					Session session4 = sessionFactory.openSession();
		             System.out.println("memberId :"+memberId);
		             System.out.println("claimStatus=="+claimStatus);
					/* Transaction tn = session4.beginTransaction(); */
		             
		             System.out.println("Util==toDate=="+toDate);
		             System.out.println("Util==fromDate=="+fromDate);
					Connection conn = session4.connection();
					CallableStatement cs = conn
							.prepareCall("{? = call FunGetClaimDetailsData(?,?,?,?,?,?,?)}");

					// register input parameters
					cs.registerOutParameter(1, Types.INTEGER);
					cs.setDate(2, new java.sql.Date(toDate.getTime()));
					System.out.println("Sql==toDate=="+toDate.getTime());
					cs.setDate(3, new java.sql.Date(fromDate.getTime()));
					System.out.println("Sql==fromDate=="+fromDate.getTime());
					//cs.setDate(2,toDate);
					//cs.setDate(3,new java.sql.Date (fromDate));
					cs.setString(4, memberId);
					cs.setString(5, role);
					cs.setString(6, claimStatus);
					cs.registerOutParameter(7, OracleTypes.CURSOR);
					cs.registerOutParameter(8, Types.VARCHAR);
					
					cs.execute();
					int result = cs.getInt(1);

					String pouterror = cs.getString(8);
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					if (result != 0) {
						throw new CustomExceptionHandler("Exception occured  :"+ pouterror);
					}else{
						 // Procedure execution
						resultset = (ResultSet) cs.getObject(7);
							resultSetMetaData = resultset.getMetaData();
							int coulmnCount = resultSetMetaData.getColumnCount();
							System.out.println("coulmnCount=="+coulmnCount);
							while (resultset.next()) {
							    Map<String, Object> columns = new LinkedHashMap<String, Object>();

							    for (int i = 1; i <= coulmnCount; i++) {
							        columns.put(resultSetMetaData.getColumnLabel(i), resultset.getObject(i));
							    }

							    ClaimDetailsReportData.add(columns);
							}
					}

						
						/*claimLodgementBean = new ClaimLodgementBean();
						
						if(claimStatus.equals("NE"))
						{
							System.out.println("NE===");
						claimLodgementBean.setMemberId(resultset.getString("Member Id"));
						claimLodgementBean.setBankName(resultset.getString("Name of the NBFC"));
						claimLodgementBean.setBorrowerName(resultset.getString("Unit Name"));
						claimLodgementBean.setCgpan(resultset.getString("CGPAN"));
						claimLodgementBean.setClaimRefNo(resultset.getString("CLAIM_REF_NO"));
						claimLodgementBean.setGuarantee_Amt(resultset.getDouble("Guaranteed Amount"));
						claimLodgementBean.setClaimDt(resultset.getDate("Date of claim lodgement"));
					    claimLodgementBean.setNbfcCheckerDt(resultset.getDate("Date of Claim submission"));
					    claimLodgementBean.setFirstInstallClaim(resultset.getDouble("Claim Applied amount"));
						ClaimDetailsReportData.add(claimLodgementBean);
						}
						else if(claimStatus.equals("RT"))
						{
							System.out.println("RT===");
							claimLodgementBean.setMemberId(resultset.getString("Member Id"));
							claimLodgementBean.setBankName(resultset.getString("Name of the NBFC"));
							claimLodgementBean.setBorrowerName(resultset.getString("Unit Name"));
							claimLodgementBean.setCgpan(resultset.getString("CGPAN"));
							claimLodgementBean.setClaimRefNo(resultset.getString("CLAIM_REF_NO"));
							claimLodgementBean.setGuarantee_Amt(resultset.getDouble("Guaranteed Amount"));
							claimLodgementBean.setNbfcCheckerDt(resultset.getDate("Date of Claim submission"));
							claimLodgementBean.setCgtCheckerDate(resultset.getDate("Claim Return Date"));
							claimLodgementBean.setRemarks(resultset.getString("Reason for Return"));
							ClaimDetailsReportData.add(claimLodgementBean);
					 }
						else if(claimStatus.equals("AP"))
						{
							System.out.println("AP===");
							claimLodgementBean.setMemberId(resultset.getString("Member Id"));
							claimLodgementBean.setBankName(resultset.getString("Name of the NBFC"));
							claimLodgementBean.setBorrowerName(resultset.getString("Unit Name"));
							claimLodgementBean.setCgpan(resultset.getString("CGPAN"));
							claimLodgementBean.setClaimRefNo(resultset.getString("CLAIM_REF_NO"));
							claimLodgementBean.setGuarantee_Amt(resultset.getDouble("Guaranteed Amount"));
							claimLodgementBean.setClaimDt(resultset.getDate("Date of claim lodgement"));
						    claimLodgementBean.setNbfcCheckerDt(resultset.getDate("Date of Claim submission"));
						    claimLodgementBean.setCgtCheckerDate(resultset.getDate("Claim Approved date"));
						    claimLodgementBean.setFirstInstallClaim(resultset.getDouble("1st instalment Amount"));
							ClaimDetailsReportData.add(claimLodgementBean);
					 }
						else if(claimStatus.equals("AS"))
						{
							System.out.println("AS===");
							claimLodgementBean.setMemberId(resultset.getString("Member Id"));
							claimLodgementBean.setBankName(resultset.getString("Name of the NBFC"));
							claimLodgementBean.setBorrowerName(resultset.getString("Unit Name"));
							claimLodgementBean.setCgpan(resultset.getString("CGPAN"));
							claimLodgementBean.setClaimRefNo(resultset.getString("CLAIM_REF_NO"));
							claimLodgementBean.setGuarantee_Amt(resultset.getDouble("Guaranteed Amount"));
							claimLodgementBean.setClaimDt(resultset.getDate("Date of claim lodgement"));
						    claimLodgementBean.setNbfcCheckerDt(resultset.getDate("Date of Claim submission"));
						    claimLodgementBean.setCgtCheckerDate(resultset.getDate("Claim Approved date"));
							
							ClaimDetailsReportData.add(claimLodgementBean);
					 }*/
				

				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Error=="+e);
				}
				return ClaimDetailsReportData;

			}
			
			
			@Override
			public List<Map<String, Object>> getClaimDetailsReportAll(String userId, Date toDate,
					Date fromDate, String role,String claimStatus) {
				// TODO Auto-generated method stub
				ResultSet resultset = null;
				ResultSetMetaData resultSetMetaData = null;
				ClaimLodgementBean claimLodgementBean = null;
				//List<ClaimLodgementBean> ClaimSettledReportData = new ArrayList<ClaimLodgementBean>();
				List<Map<String, Object>> ClaimDetailsReportData = new ArrayList<Map<String, Object>>();
				try {
					Session session4 = sessionFactory.openSession();
		            
					/* Transaction tn = session4.beginTransaction(); */
					Connection conn = session4.connection();
					CallableStatement cs = conn
							.prepareCall("{? = call FunGetClaimDetailsDataAll(?,?,?,?,?,?)}");

					// register input parameters
					cs.registerOutParameter(1, Types.INTEGER);
					cs.setDate(2, new java.sql.Date(toDate.getTime()));
					cs.setDate(3, new java.sql.Date(fromDate.getTime()));
					cs.setString(4, role);
					cs.setString(5, claimStatus);
					cs.registerOutParameter(6, OracleTypes.CURSOR);
					cs.registerOutParameter(7, Types.VARCHAR);
					
					cs.execute();
					int result = cs.getInt(1);

					String pouterror = cs.getString(7);
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					if (result != 0) {
						throw new CustomExceptionHandler("Exception occured  :"+ pouterror);
					}else{
						 // Procedure execution
						resultset = (ResultSet) cs.getObject(6);
							resultSetMetaData = resultset.getMetaData();
							int coulmnCount = resultSetMetaData.getColumnCount();
							System.out.println("coulmnCount=="+coulmnCount);
							while (resultset.next()) {
							    Map<String, Object> columns = new LinkedHashMap<String, Object>();

							    for (int i = 1; i <= coulmnCount; i++) {
							        columns.put(resultSetMetaData.getColumnLabel(i), resultset.getObject(i));
							    }

							    ClaimDetailsReportData.add(columns);
							}
					}
					
					/*resultset = (ResultSet) cs.getObject(5);
					while (resultset.next()) {
						claimLodgementBean = new ClaimLodgementBean();

					claimLodgementBean.setBankName(resultset.getString("Bank_Name"));
					claimLodgementBean.setMemberId(resultset.getString("Member_ID"));
					claimLodgementBean.setBorrowerName(resultset.getString("Unit_Name"));
					claimLodgementBean.setCgpan(resultset.getString("CGPAN"));
					claimLodgementBean.setOsAmtClaim(resultset.getDouble("OUTSTANDING_AMOUNT_CLAIM"));
					claimLodgementBean.setTotalOsAmt(resultset.getDouble("OUTSTANDING_AMOUNT"));
					claimLodgementBean.setRecovery(resultset
							.getDouble("RECOVERY_AMOUNT"));
					claimLodgementBean.setSubsidyAmt(resultset
							.getDouble("SUBSIDY_AMOUNT"));
					claimLodgementBean.setLatestOsAmt(resultset
							.getDouble("LATEST_OS_AMT"));
					claimLodgementBean.setGuarantee_Amt(resultset
							.getDouble("GUARANTEE_AMOUNT"));
					claimLodgementBean.setNpaEligibleAmt(resultset
							.getDouble("CLAIM_ELIGABLE_AMT"));
					claimLodgementBean.setFirstInstallClaim(resultset
							.getDouble("FIRST_INSTALLMENT_CLAIM"));
					claimLodgementBean.setEligableAmtClaim(resultset
							.getDouble("CLAIM_ELIGIBILITY_AMOUNT"));
				claimLodgementBean.setCgtCheckerDate(resultset
							.getDate("CGTMSE_CHECKER_DATE"));
					claimLodgementBean.setLoanAccountNo(resultset
							.getString("Loan_Account_No"));
					
							

					ClaimSettledReportData.add(claimLodgementBean);
				}*/

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ClaimDetailsReportData;
			
		}
			//End
}
