package com.nbfc.dao;


import com.nbfc.bean.ClaimBankMandateBean;
import com.nbfc.model.ClaimBankMandateBlobModel;
import com.nbfc.model.ClaimBankMandateBlobModelDownLoadPDF;
import com.nbfc.model.MLIInfo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import oracle.jdbc.OracleTypes;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;





@Repository("claimBankMandateDao")
public class ClaimBankMandateDaoImpl implements ClaimBankMandateDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public MLIInfo getMemInfo(String memberId){
		String bankId=null;
		String zoneId=null;
		String branchId=null;
		MLIInfo mLIInfo=null;
		System.out.println("memberId in DAO=="+memberId);
		if(memberId!=null || memberId!=""){
			if(memberId.length()>4){
				bankId = memberId.substring(0,4);
				zoneId=memberId.substring(4,8);
				branchId=memberId.substring(8,12);
				String hql = "from MLIInfo nmi where nmi.MEM_BNK_ID=:bankId and nmi.MEM_ZNE_ID=:zoneId and nmi.MEM_BRN_ID=:branchId";
			 	System.out.println("Query=="+hql);
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("bankId", bankId);
				query.setParameter("zoneId", zoneId);
				query.setParameter("branchId", branchId);
				mLIInfo= (MLIInfo) query.uniqueResult();
				return mLIInfo;
			}
			
		}
	    
		return mLIInfo;
	}
	
	public boolean saveBankMandateDetails(ClaimBankMandateBean claimBankMandateBean,String loginUserId) {
		Session sessionObj = sessionFactory.openSession();
		Transaction trans = sessionObj.beginTransaction();
		Connection conn = sessionObj.connection();
		CallableStatement callStmt = null;
		int dataSaved=0;
		String displayErrorMsg=null;
		int noOfRowSave=0;
		Map<String, String> mapObjError = new HashMap<String, String>();
		boolean recordSaveFlag=false;
		try{
			if(claimBankMandateBean!=null){
				callStmt = conn.prepareCall("{call pro_saveBankMandateDtls(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				if(claimBankMandateBean.getMliName()!=null || claimBankMandateBean.getMliName()!=""){
					callStmt.setString(1,claimBankMandateBean.getMliName());
				}else{
					callStmt.setString(1,null);
				}
				if(claimBankMandateBean.getMemberId()!=null || claimBankMandateBean.getMemberId()!=""){
					callStmt.setString(2,claimBankMandateBean.getMemberId());
				}else{
					callStmt.setString(2,null);
				}
				if(claimBankMandateBean.getContactNo()!=null ){
					callStmt.setString(3,claimBankMandateBean.getContactNo());
				}else{
					callStmt.setString(3,null);
				}
				
				if(claimBankMandateBean.getMobileNo()!=null ){
					callStmt.setString(4,claimBankMandateBean.getMobileNo());
				}else{
					callStmt.setString(4,null);
				}
				
				if(claimBankMandateBean.getEmailId()!=null || claimBankMandateBean.getEmailId()!=""){
					callStmt.setString(5,claimBankMandateBean.getEmailId());
				}else{
					callStmt.setString(5,null);
				}
				
				if(claimBankMandateBean.getMliBeneficiaryBankName()!=null || claimBankMandateBean.getMliBeneficiaryBankName()!=""){
					callStmt.setString(6,claimBankMandateBean.getMliBeneficiaryBankName());
				}else{
					callStmt.setString(6,null);
				}
				
				if(claimBankMandateBean.getMliBeneficiaryName()!=null || claimBankMandateBean.getMliBeneficiaryName()!=""){
					callStmt.setString(7,claimBankMandateBean.getMliBeneficiaryName());
				}else{
					callStmt.setString(7,null);
				}
				if(claimBankMandateBean.getBranchName()!=null || claimBankMandateBean.getBranchName()!=""){
					callStmt.setString(8,claimBankMandateBean.getBranchName());
				}else{
					callStmt.setString(8,null);
				}
				
				if(claimBankMandateBean.getAccountType()!=null || claimBankMandateBean.getAccountType()!=""){
					callStmt.setString(9,claimBankMandateBean.getAccountType());
				}else{
					callStmt.setString(9,null);
				}
				
				if(claimBankMandateBean.getBranchCode()!=null || claimBankMandateBean.getBranchCode()!=""){
					callStmt.setString(10,claimBankMandateBean.getBranchCode());
				}else{
					callStmt.setString(10,null);
				}
				
				if(claimBankMandateBean.getMicrCode()!=null || claimBankMandateBean.getMicrCode()!=""){
					callStmt.setString(11,claimBankMandateBean.getMicrCode());
				}else{
					callStmt.setString(11,null);
				}
				
				if(claimBankMandateBean.getAccountNo()!=null || claimBankMandateBean.getAccountNo()!=""){
					callStmt.setString(12,claimBankMandateBean.getAccountNo());
				}else{
					callStmt.setString(12,null);
				}
				
				if(claimBankMandateBean.getIfscCode()!=null || claimBankMandateBean.getIfscCode()!=""){
					callStmt.setString(13,claimBankMandateBean.getIfscCode());
				}else{
					callStmt.setString(13,null);
				}
				claimBankMandateBean.setRtgsNo("This field has been removed from frontend");
				if(claimBankMandateBean.getRtgsNo()!=null || claimBankMandateBean.getRtgsNo()!=""){
					callStmt.setString(14,claimBankMandateBean.getRtgsNo());
				}else{
					callStmt.setString(14,null);
				}
				
				if(loginUserId!=null || loginUserId!=""){
					callStmt.setString(15,loginUserId);
				}else{
					callStmt.setString(15,null);
				}
				callStmt.registerOutParameter(16, Types.VARCHAR);
				callStmt.registerOutParameter(17, Types.INTEGER);
				
				if(claimBankMandateBean.getNbfcMakerRemarks()!=null || claimBankMandateBean.getNbfcMakerRemarks()!=""){
					callStmt.setString(18,claimBankMandateBean.getNbfcMakerRemarks());
				}else{
					callStmt.setString(18,null);
				}
			
				callStmt.executeUpdate();
				String v_success_MSG=callStmt.getString(16);
				Integer returnSuccessVal=callStmt.getInt(17);
				System.out.println("v_success_MSG==="+v_success_MSG);
				System.out.println("returnSuccessVal==="+returnSuccessVal);
				
				if(returnSuccessVal==1){
					recordSaveFlag=true;
					System.out.println("Record Save SuccessFully IN DAO");
				}else{
					recordSaveFlag=false;
					System.out.println("Record Already Exist IN DAO");
				}
				
				
			}else{
				claimBankMandateBean.setMliName(null);
				claimBankMandateBean.setMemberId(null);
				claimBankMandateBean.setContactNo(null);
				claimBankMandateBean.setMobileNo(null);
				claimBankMandateBean.setEmailId(null);
				claimBankMandateBean.setMliBeneficiaryBankName(null);
				claimBankMandateBean.setMliBeneficiaryName(null);
				claimBankMandateBean.setAccountType(null);
				claimBankMandateBean.setBranchCode(null);
				claimBankMandateBean.setMicrCode(null);
				claimBankMandateBean.setAccountNo(null);
				claimBankMandateBean.setIfscCode(null);
				claimBankMandateBean.setRtgsNo(null);
				claimBankMandateBean.setNbfcMakerRemarks(null);
				claimBankMandateBean.setNbfcCheckerRemarks(null);
				claimBankMandateBean.setCgtmseCheckerRemarks(null);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			trans.commit();
			sessionObj.close();
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recordSaveFlag;
		
	}

	
	public void saveBankMandateBlob(ClaimBankMandateBlobModel claimBankMandateBlobModelObj) {
		try{
		sessionFactory.getCurrentSession().save(claimBankMandateBlobModelObj);
		}catch(Exception e){
			System.out.println("Exception==="+e);
		}finally{
			sessionFactory.close();
		}
	}
	
	
	
	@Override
	public ClaimBankMandateBean getSaveBankMandateDetails(String memId) {
		ClaimBankMandateBean objClaimBankMandateBean=null;
	 	Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		CallableStatement callStmt = null;
		ResultSet resultset = null;
		boolean flag=false;
		objClaimBankMandateBean = new ClaimBankMandateBean();
		try {
			callStmt = (CallableStatement)con.prepareCall("{ call PROC_getSaveBankMandateDetails(?,?) } ");
			callStmt.setString(1,memId);
			callStmt.registerOutParameter(2, OracleTypes.CURSOR);
			callStmt.execute(); 
			resultset=(ResultSet) callStmt.getObject(2);
			while (resultset.next()) {
				flag=true;
				
				if(resultset.getString("MEM_ID") != null){
					objClaimBankMandateBean.setMemberId(resultset.getString("MEM_ID"));
				}else{
					objClaimBankMandateBean.setMemberId(memId);
				}
				if(resultset.getString("MEM_CONT_NO") != null){
					objClaimBankMandateBean.setContactNo(resultset.getString("MEM_CONT_NO"));
				}else{
					objClaimBankMandateBean.setContactNo(null);
				}
				if(resultset.getString("MEM_MOB_NO") != null){
					objClaimBankMandateBean.setMobileNo(resultset.getString("MEM_MOB_NO"));
				}else{
					objClaimBankMandateBean.setMobileNo(null);
				}
				
				if(resultset.getString("MEM_EMAIL_ID") != null){
					objClaimBankMandateBean.setEmailId(resultset.getString("MEM_EMAIL_ID"));
				}else{
					objClaimBankMandateBean.setEmailId(null);
				}
				if(resultset.getString("MLI_BENEFICIARY_BANK_NAME") != null){
					objClaimBankMandateBean.setMliBeneficiaryBankName(resultset.getString("MLI_BENEFICIARY_BANK_NAME"));
				}else{
					objClaimBankMandateBean.setMliBeneficiaryBankName(null);
				}
				if(resultset.getString("MLI_BENEFICIARY_NAME") != null){
					objClaimBankMandateBean.setMliBeneficiaryName(resultset.getString("MLI_BENEFICIARY_NAME"));
				}else{
					objClaimBankMandateBean.setMliBeneficiaryName(null);
				}
				if(resultset.getString("MEM_BRN_NAME") != null){
					objClaimBankMandateBean.setBranchName(resultset.getString("MEM_BRN_NAME"));
				}else{
					objClaimBankMandateBean.setBranchName(null);
				}
				if(resultset.getString("MEM_ACC_TYPE") != null){
					objClaimBankMandateBean.setAccountType(resultset.getString("MEM_ACC_TYPE"));
				}else{
					objClaimBankMandateBean.setAccountType(null);
				}
				
				if(resultset.getString("MEM_BRN_CODE") != null){
					objClaimBankMandateBean.setBranchCode(resultset.getString("MEM_BRN_CODE"));
				}else{
					objClaimBankMandateBean.setBranchCode(null);
				}
				if(resultset.getString("MEM_MICR_CODE") != null){
					objClaimBankMandateBean.setMicrCode(resultset.getString("MEM_MICR_CODE"));
				}else{
					objClaimBankMandateBean.setMicrCode(null);
				}
				if(resultset.getString("MEM_ACC_NO") != null){
					objClaimBankMandateBean.setAccountNo(resultset.getString("MEM_ACC_NO"));
				}else{
					objClaimBankMandateBean.setAccountNo(null);
				}
				if(resultset.getString("MEM_IFSC_CODE") != null){
					objClaimBankMandateBean.setIfscCode(resultset.getString("MEM_IFSC_CODE"));
				}else{
					objClaimBankMandateBean.setIfscCode(null);
				}
				if(resultset.getString("MEM_RTGS_NO") != null){
					objClaimBankMandateBean.setRtgsNo(resultset.getString("MEM_RTGS_NO"));
				}else{
					objClaimBankMandateBean.setRtgsNo(null);
				}
				if(resultset.getString("MEM_STATUS") != null){
					objClaimBankMandateBean.setStatus(resultset.getString("MEM_STATUS"));
				}else{
					objClaimBankMandateBean.setStatus(null);
				}
				if(resultset.getString("NBFC_MAKER_REMARK") != null){
					objClaimBankMandateBean.setNbfcMakerRemarks(resultset.getString("NBFC_MAKER_REMARK"));
				}else{
					objClaimBankMandateBean.setNbfcMakerRemarks(null);
				}
				if(resultset.getString("NBFC_CHECKER_REMARK") != null){
					objClaimBankMandateBean.setNbfcCheckerRemarks(resultset.getString("NBFC_CHECKER_REMARK"));
				}else{
					objClaimBankMandateBean.setNbfcCheckerRemarks(null);
				}
				if(resultset.getString("CGTMSE_CHECKER_REMARK") != null){
					objClaimBankMandateBean.setCgtmseCheckerRemarks(resultset.getString("CGTMSE_CHECKER_REMARK"));
				}else{
					objClaimBankMandateBean.setCgtmseCheckerRemarks(null);
				}
				
			}
			if(flag==false){
				objClaimBankMandateBean.setMemberId(memId);
				objClaimBankMandateBean.setContactNo(null);
				objClaimBankMandateBean.setMobileNo(null);
				objClaimBankMandateBean.setEmailId(null);
				objClaimBankMandateBean.setMliBeneficiaryBankName(null);
				objClaimBankMandateBean.setMliBeneficiaryName(null);
				objClaimBankMandateBean.setBranchName(null);
				objClaimBankMandateBean.setAccountType(null);
				objClaimBankMandateBean.setBranchCode(null);
				objClaimBankMandateBean.setMicrCode(null);
				objClaimBankMandateBean.setAccountNo(null);
				objClaimBankMandateBean.setIfscCode(null);
				objClaimBankMandateBean.setRtgsNo(null);
				objClaimBankMandateBean.setStatus(null);
				objClaimBankMandateBean.setNbfcMakerRemarks(null);
				objClaimBankMandateBean.setNbfcCheckerRemarks(null);
				objClaimBankMandateBean.setNbfcCheckerRemarks(null);
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
			return objClaimBankMandateBean;		
	}
	

	@Override
	public ClaimBankMandateBean getBankMandateDetailsForApprovalorRejection(String memId) {
		ClaimBankMandateBean objClaimBankMandateBean=null;
		System.out.println("getBankMandateDetailsForApprovalorRejection method called to procedure inside DAOImpl==");
	 	Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		CallableStatement callStmt = null;
		ResultSet resultset = null;
		boolean flag=false;
		objClaimBankMandateBean = new ClaimBankMandateBean();
		try {
			callStmt = (CallableStatement)con.prepareCall("{ call PROC_GET_B_M_Dtls_App_Rej(?,?) } ");
			callStmt.setString(1,memId);
			callStmt.registerOutParameter(2, OracleTypes.CURSOR);
			callStmt.execute(); 
			resultset=(ResultSet) callStmt.getObject(2);
			while (resultset.next()) {
				flag=true;
				if(resultset.getString("MLI_NAME") != null){
					objClaimBankMandateBean.setMliName(resultset.getString("MLI_NAME"));
				}else{
					objClaimBankMandateBean.setMliName(memId);
				}
				if(resultset.getString("MEM_ID") != null){
					objClaimBankMandateBean.setMemberId(resultset.getString("MEM_ID"));
				}else{
					objClaimBankMandateBean.setMemberId(memId);
				}
				if(resultset.getString("MEM_CONT_NO") != null){
					objClaimBankMandateBean.setContactNo(resultset.getString("MEM_CONT_NO"));
				}else{
					objClaimBankMandateBean.setContactNo(null);
				}
				if(resultset.getString("MEM_MOB_NO") != null){
					objClaimBankMandateBean.setMobileNo(resultset.getString("MEM_MOB_NO"));
				}else{
					objClaimBankMandateBean.setMobileNo(null);
				}
				
				if(resultset.getString("MEM_EMAIL_ID") != null){
					objClaimBankMandateBean.setEmailId(resultset.getString("MEM_EMAIL_ID"));
				}else{
					objClaimBankMandateBean.setEmailId(null);
				}
				if(resultset.getString("MLI_BENEFICIARY_BANK_NAME") != null){
					objClaimBankMandateBean.setMliBeneficiaryBankName(resultset.getString("MLI_BENEFICIARY_BANK_NAME"));
				}else{
					objClaimBankMandateBean.setMliBeneficiaryBankName(null);
				}
				if(resultset.getString("MLI_BENEFICIARY_NAME") != null){
					objClaimBankMandateBean.setMliBeneficiaryName(resultset.getString("MLI_BENEFICIARY_NAME"));
				}else{
					objClaimBankMandateBean.setMliBeneficiaryName(null);
				}
				if(resultset.getString("MEM_BRN_NAME") != null){
					objClaimBankMandateBean.setBranchName(resultset.getString("MEM_BRN_NAME"));
				}else{
					objClaimBankMandateBean.setBranchName(null);
				}
				if(resultset.getString("MEM_ACC_TYPE") != null){
					objClaimBankMandateBean.setAccountType(resultset.getString("MEM_ACC_TYPE"));
				}else{
					objClaimBankMandateBean.setAccountType(null);
				}
				
				if(resultset.getString("MEM_BRN_CODE") != null){
					objClaimBankMandateBean.setBranchCode(resultset.getString("MEM_BRN_CODE"));
				}else{
					objClaimBankMandateBean.setBranchCode(null);
				}
				if(resultset.getString("MEM_MICR_CODE") != null){
					objClaimBankMandateBean.setMicrCode(resultset.getString("MEM_MICR_CODE"));
				}else{
					objClaimBankMandateBean.setMicrCode(null);
				}
				if(resultset.getString("MEM_ACC_NO") != null){
					objClaimBankMandateBean.setAccountNo(resultset.getString("MEM_ACC_NO"));
				}else{
					objClaimBankMandateBean.setAccountNo(null);
				}
				if(resultset.getString("MEM_IFSC_CODE") != null){
					objClaimBankMandateBean.setIfscCode(resultset.getString("MEM_IFSC_CODE"));
				}else{
					objClaimBankMandateBean.setIfscCode(null);
				}
				if(resultset.getString("MEM_RTGS_NO") != null){
					objClaimBankMandateBean.setRtgsNo(resultset.getString("MEM_RTGS_NO"));
				}else{
					objClaimBankMandateBean.setRtgsNo(null);
				}
				if(resultset.getString("MEM_STATUS") != null){
					objClaimBankMandateBean.setStatus(resultset.getString("MEM_STATUS"));
				}else{
					objClaimBankMandateBean.setStatus(null);
				}
				if(resultset.getString("NBFC_MAKER_REMARK") != null){
					objClaimBankMandateBean.setNbfcMakerRemarks(resultset.getString("NBFC_MAKER_REMARK"));
				}else{
					objClaimBankMandateBean.setNbfcMakerRemarks(null);
				}
				if(resultset.getString("NBFC_CHECKER_REMARK") != null){
					objClaimBankMandateBean.setNbfcCheckerRemarks(resultset.getString("NBFC_CHECKER_REMARK"));
				}else{
					objClaimBankMandateBean.setNbfcCheckerRemarks(null);
				}
				if(resultset.getString("CGTMSE_CHECKER_REMARK") != null){
					objClaimBankMandateBean.setCgtmseCheckerRemarks(resultset.getString("CGTMSE_CHECKER_REMARK"));
				}else{
					objClaimBankMandateBean.setCgtmseCheckerRemarks(null);
				}
				
			}
			if(flag==false){
				objClaimBankMandateBean.setMemberId(memId);
				objClaimBankMandateBean.setContactNo(null);
				objClaimBankMandateBean.setMobileNo(null);
				objClaimBankMandateBean.setEmailId(null);
				objClaimBankMandateBean.setMliBeneficiaryBankName(null);
				objClaimBankMandateBean.setMliBeneficiaryName(null);
				objClaimBankMandateBean.setBranchName(null);
				objClaimBankMandateBean.setAccountType(null);
				objClaimBankMandateBean.setBranchCode(null);
				objClaimBankMandateBean.setMicrCode(null);
				objClaimBankMandateBean.setAccountNo(null);
				objClaimBankMandateBean.setIfscCode(null);
				objClaimBankMandateBean.setRtgsNo(null);
				objClaimBankMandateBean.setStatus(null);
				objClaimBankMandateBean.setNbfcMakerRemarks(null);
				objClaimBankMandateBean.setNbfcCheckerRemarks(null);
				objClaimBankMandateBean.setCgtmseCheckerRemarks(null);
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
			return objClaimBankMandateBean;		
	}
	
	@Override
	public int approveBankMandateDtlsByNFCChecker (String memId,String loginUserId) {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		Connection conn = session.connection();
		CallableStatement callStmt = null;
		int dataUpdated = 0;
		try{
			if(loginUserId!=null && memId!=null){
				callStmt = conn.prepareCall("{call PRO_AppBankMan_Dtls_ByNBFC_CK(?,?)}");
				if(memId!="" || memId!=null){
					callStmt.setString(1,memId);
				}
				
				if(loginUserId!="" || loginUserId!=null){
					callStmt.setString(2,loginUserId);
				}
				
				dataUpdated=callStmt.executeUpdate();
				System.out.println("dataUpdated =="+dataUpdated);
				conn.close();
				callStmt.close();
				return dataUpdated;

			}else{
				System.out.println("HI==");
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
		return dataUpdated;
	}
	
	@Override
	public int returnBankMandateDtlsByNFCChecker (String memId,String loginUserId,ClaimBankMandateBean claimBankMandateBean) {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		Connection conn = session.connection();
		CallableStatement callStmt = null;
		int dataUpdated = 0;
		try{
			if(loginUserId!=null && memId!=null){
				callStmt = conn.prepareCall("{call PRO_RetBankMan_Dtls_ByNBFC_CK(?,?,?)}");
				if(memId!="" || memId!=null){
					callStmt.setString(1,memId);
				}
				
				if(loginUserId!="" || loginUserId!=null){
					callStmt.setString(2,loginUserId);
				}
				
				if(claimBankMandateBean.getNbfcCheckerRemarks()!="" || claimBankMandateBean.getNbfcCheckerRemarks()!=null){
					callStmt.setString(3,claimBankMandateBean.getNbfcCheckerRemarks());
				}
				
				dataUpdated=callStmt.executeUpdate();
				System.out.println("dataUpdated =="+dataUpdated);
				conn.close();
				callStmt.close();
				return dataUpdated;

			}else{
				System.out.println("HI==");
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
		return dataUpdated;
	}
	
	@Override
	public ClaimBankMandateBean getBankMandateDetailsForUpdate(String memId) {
		ClaimBankMandateBean objClaimBankMandateBean=null;
		objClaimBankMandateBean = new ClaimBankMandateBean();
	 	Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		CallableStatement callStmt = null;
		ResultSet resultset = null;
		boolean flag=false;
		try {
			callStmt = (CallableStatement)con.prepareCall("{ call PROC_GET_B_M_Dtls_For_Update(?,?) } ");
			callStmt.setString(1,memId);
			callStmt.registerOutParameter(2, OracleTypes.CURSOR);
			callStmt.execute(); 
			resultset=(ResultSet) callStmt.getObject(2);
			while (resultset.next()) {
				flag=true;
				if(resultset.getString("MLI_NAME") != null){
					objClaimBankMandateBean.setMliName(resultset.getString("MLI_NAME"));
				}else{
					objClaimBankMandateBean.setMliName(null);
				}
				if(resultset.getString("MEM_ID") != null){
					objClaimBankMandateBean.setMemberId(resultset.getString("MEM_ID"));
				}else{
					objClaimBankMandateBean.setMemberId(null);
				}
				
				if(resultset.getString("MEM_CONT_NO") != null){
					objClaimBankMandateBean.setContactNo(resultset.getString("MEM_CONT_NO"));
				}else{
					objClaimBankMandateBean.setContactNo(null);
				}
				if(resultset.getString("MEM_MOB_NO") != null){
					objClaimBankMandateBean.setMobileNo(resultset.getString("MEM_MOB_NO"));
				}else{
					objClaimBankMandateBean.setMobileNo(null);
				}
				
				if(resultset.getString("MEM_EMAIL_ID") != null){
					objClaimBankMandateBean.setEmailId(resultset.getString("MEM_EMAIL_ID"));
				}else{
					objClaimBankMandateBean.setEmailId(null);
				}
				if(resultset.getString("MLI_BENEFICIARY_BANK_NAME") != null){
					objClaimBankMandateBean.setMliBeneficiaryBankName(resultset.getString("MLI_BENEFICIARY_BANK_NAME"));
				}else{
					objClaimBankMandateBean.setMliBeneficiaryBankName(null);
				}
				if(resultset.getString("MLI_BENEFICIARY_NAME") != null){
					objClaimBankMandateBean.setMliBeneficiaryName(resultset.getString("MLI_BENEFICIARY_NAME"));
				}else{
					objClaimBankMandateBean.setMliBeneficiaryName(null);
				}
				if(resultset.getString("MEM_BRN_NAME") != null){
					objClaimBankMandateBean.setBranchName(resultset.getString("MEM_BRN_NAME"));
				}else{
					objClaimBankMandateBean.setBranchName(null);
				}
				if(resultset.getString("MEM_ACC_TYPE") != null){
					objClaimBankMandateBean.setAccountType(resultset.getString("MEM_ACC_TYPE"));
				}else{
					objClaimBankMandateBean.setAccountType(null);
				}
				
				if(resultset.getString("MEM_BRN_CODE") != null){
					objClaimBankMandateBean.setBranchCode(resultset.getString("MEM_BRN_CODE"));
				}else{
					objClaimBankMandateBean.setBranchCode(null);
				}
				if(resultset.getString("MEM_MICR_CODE") != null){
					objClaimBankMandateBean.setMicrCode(resultset.getString("MEM_MICR_CODE"));
				}else{
					objClaimBankMandateBean.setMicrCode(null);
				}
				if(resultset.getString("MEM_ACC_NO") != null){
					objClaimBankMandateBean.setAccountNo(resultset.getString("MEM_ACC_NO"));
				}else{
					objClaimBankMandateBean.setAccountNo(null);
				}
				if(resultset.getString("MEM_IFSC_CODE") != null){
					objClaimBankMandateBean.setIfscCode(resultset.getString("MEM_IFSC_CODE"));
				}else{
					objClaimBankMandateBean.setIfscCode(null);
				}
				if(resultset.getString("MEM_RTGS_NO") != null){
					objClaimBankMandateBean.setRtgsNo(resultset.getString("MEM_RTGS_NO"));
				}else{
					objClaimBankMandateBean.setRtgsNo(null);
				}
				if(resultset.getString("MEM_STATUS") != null){
					objClaimBankMandateBean.setStatus(resultset.getString("MEM_STATUS"));
				}else{
					objClaimBankMandateBean.setStatus(null);
				}
				if(resultset.getString("NBFC_MAKER_REMARK") != null){
					objClaimBankMandateBean.setNbfcMakerRemarks(resultset.getString("NBFC_MAKER_REMARK"));
				}else{
					objClaimBankMandateBean.setNbfcMakerRemarks(null);
				}
				if(resultset.getString("NBFC_CHECKER_REMARK") != null){
					objClaimBankMandateBean.setNbfcCheckerRemarks(resultset.getString("NBFC_CHECKER_REMARK"));
				}else{
					objClaimBankMandateBean.setNbfcCheckerRemarks(null);
				}
				if(resultset.getString("CGTMSE_CHECKER_REMARK") != null){
					objClaimBankMandateBean.setCgtmseCheckerRemarks(resultset.getString("CGTMSE_CHECKER_REMARK"));
				}else{
					objClaimBankMandateBean.setCgtmseCheckerRemarks(null);
				}
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
			return objClaimBankMandateBean;		
	}
	
	@Override
	public int updateBankMandateDtlsByNbfcMk (ClaimBankMandateBean claimBankMandateBean,String loginUserId) {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		Connection conn = session.connection();
		CallableStatement callStmt = null;
		int dataUpdated = 0;
		try{
			if(loginUserId!=null && claimBankMandateBean.getMemberId()!=null){
				callStmt = conn.prepareCall("{call PROC_Update_BM_DtlsByNBFC_MK(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				if(claimBankMandateBean.getMliName()!=null || claimBankMandateBean.getMliName()!=""){
					callStmt.setString(1,claimBankMandateBean.getMliName());
				}else{
					callStmt.setString(1,null);
				}
				if(claimBankMandateBean.getMemberId()!=null || claimBankMandateBean.getMemberId()!=""){
					callStmt.setString(2,claimBankMandateBean.getMemberId());
				}else{
					callStmt.setString(2,null);
				}
				if(claimBankMandateBean.getContactNo()!=null ){
					callStmt.setString(3,claimBankMandateBean.getContactNo());
				}else{
					callStmt.setString(3,null);
				}
				
				if(claimBankMandateBean.getMobileNo()!=null ){
					callStmt.setString(4,claimBankMandateBean.getMobileNo());
				}else{
					callStmt.setString(4,null);
				}
				
				if(claimBankMandateBean.getEmailId()!=null || claimBankMandateBean.getEmailId()!=""){
					callStmt.setString(5,claimBankMandateBean.getEmailId());
				}else{
					callStmt.setString(5,null);
				}
				
				if(claimBankMandateBean.getMliBeneficiaryBankName()!=null || claimBankMandateBean.getMliBeneficiaryBankName()!=""){
					callStmt.setString(6,claimBankMandateBean.getMliBeneficiaryBankName());
				}else{
					callStmt.setString(6,null);
				}
				
				if(claimBankMandateBean.getMliBeneficiaryName()!=null || claimBankMandateBean.getMliBeneficiaryName()!=""){
					callStmt.setString(7,claimBankMandateBean.getMliBeneficiaryName());
				}else{
					callStmt.setString(7,null);
				}
				if(claimBankMandateBean.getBranchName()!=null || claimBankMandateBean.getBranchName()!=""){
					callStmt.setString(8,claimBankMandateBean.getBranchName());
				}else{
					callStmt.setString(8,null);
				}
				
				if(claimBankMandateBean.getAccountType()!=null || claimBankMandateBean.getAccountType()!=""){
					callStmt.setString(9,claimBankMandateBean.getAccountType());
				}else{
					callStmt.setString(9,null);
				}
				
				if(claimBankMandateBean.getBranchCode()!=null || claimBankMandateBean.getBranchCode()!=""){
					callStmt.setString(10,claimBankMandateBean.getBranchCode());
				}else{
					callStmt.setString(10,null);
				}
				
				if(claimBankMandateBean.getMicrCode()!=null || claimBankMandateBean.getMicrCode()!=""){
					callStmt.setString(11,claimBankMandateBean.getMicrCode());
				}else{
					callStmt.setString(11,null);
				}
				
				if(claimBankMandateBean.getAccountNo()!=null || claimBankMandateBean.getAccountNo()!=""){
					callStmt.setString(12,claimBankMandateBean.getAccountNo());
				}else{
					callStmt.setString(12,null);
				}
				
				if(claimBankMandateBean.getIfscCode()!=null || claimBankMandateBean.getIfscCode()!=""){
					callStmt.setString(13,claimBankMandateBean.getIfscCode());
				}else{
					callStmt.setString(13,null);
				}
				claimBankMandateBean.setRtgsNo("This field has been removed from frontend");
				if(claimBankMandateBean.getRtgsNo()!=null || claimBankMandateBean.getRtgsNo()!=""){
					callStmt.setString(14,claimBankMandateBean.getRtgsNo());
				}else{
					callStmt.setString(14,null);
				}
				
				if(loginUserId!=null || loginUserId!=""){
					callStmt.setString(15,loginUserId);
				}else{
					callStmt.setString(15,null);
				}
				if(claimBankMandateBean.getNbfcMakerRemarks() != null || claimBankMandateBean.getNbfcMakerRemarks() !=""){
					callStmt.setString(16, claimBankMandateBean.getNbfcMakerRemarks());
				}else{
					claimBankMandateBean.setNbfcMakerRemarks(null);
				}
				
				if(claimBankMandateBean.getNbfcCheckerRemarks() != null || claimBankMandateBean.getNbfcCheckerRemarks() !=""){
					callStmt.setString(17, claimBankMandateBean.getNbfcCheckerRemarks());
				}else{
					claimBankMandateBean.setNbfcCheckerRemarks(null);
				}
				
				if(claimBankMandateBean.getCgtmseCheckerRemarks() != null || claimBankMandateBean.getCgtmseCheckerRemarks() !=""){
					callStmt.setString(18, claimBankMandateBean.getCgtmseCheckerRemarks());
				}else{
					claimBankMandateBean.setCgtmseCheckerRemarks(null);
				}
				
				
				dataUpdated=callStmt.executeUpdate();
				System.out.println("dataUpdated =="+dataUpdated);
				conn.close();
				callStmt.close();
				return dataUpdated;

			}else{
				System.out.println("HI==");
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
		return dataUpdated;
	}
	
	
	@Override
	public void UpdateBankMandateBlob(ClaimBankMandateBlobModel claimBankMandateBlobModelObj,String memId) {
		try{
			
			Query query = sessionFactory.getCurrentSession().createQuery(" UPDATE ClaimBankMandateBlobModel SET LEGAL_DOCUMENT = :DOC,MEM_STATUS=:status,NBFC_MK_USER_ID=:loginUserId,NBFC_MK_DATE=:nbfcMkDate,INSERTEDON=:insertedOn WHERE MEM_ID=:memId1");
    		query.setParameter("DOC", claimBankMandateBlobModelObj.getLEGAL_DOCUMENT());
    		query.setParameter("status", claimBankMandateBlobModelObj.getMEM_STATUS());
    		query.setParameter("loginUserId", claimBankMandateBlobModelObj.getNBFC_MK_USER_ID());
    		query.setParameter("nbfcMkDate", claimBankMandateBlobModelObj.getNBFC_MK_DATE());
    		query.setParameter("insertedOn", claimBankMandateBlobModelObj.getINSERTEDON());
    		query.setParameter("memId1", claimBankMandateBlobModelObj.getMEM_ID());
    		int result = query.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sessionFactory.close();
		}
	}
	

	@Override
	public ClaimBankMandateBlobModelDownLoadPDF downloadClaimBankMandate(String memberId)throws SQLException, IOException {

		Session session = sessionFactory.getCurrentSession();
		ClaimBankMandateBlobModelDownLoadPDF a=(ClaimBankMandateBlobModelDownLoadPDF) session.get(ClaimBankMandateBlobModelDownLoadPDF.class, memberId);
		return a;

	}

	@Override
	public ClaimBankMandateBean getBankMandateBlob(String memberId) {
		
		ClaimBankMandateBean objClaimBankMandateBean=null;
	 	Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		CallableStatement callStmt = null;
		ResultSet resultset = null;
		try {
			callStmt = (CallableStatement)con.prepareCall("{ call getBankMandateBlob(?,?) } ");
			callStmt.setString(1,memberId);
			callStmt.registerOutParameter(2, OracleTypes.CURSOR);
			callStmt.execute(); 
			resultset=(ResultSet) callStmt.getObject(2);
			while (resultset.next()) {
				objClaimBankMandateBean = new ClaimBankMandateBean();		
				objClaimBankMandateBean.setDataBlob((resultset.getBlob("LEGAL_DOCUMENT")));
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
			return objClaimBankMandateBean;		
	}
	

	@Override
	public List<ClaimBankMandateBean> getBankMandateDetailsForApprovalorRejectionFromCGTMSE() {
		ClaimBankMandateBean objClaimBankMandateBean=null;
		boolean flag=false;
		
		System.out.println("getBankMandateDetailsForApprovalorRejection method called to procedure inside DAOImpl==");
	 	Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		CallableStatement callStmt = null;
		ResultSet resultset = null;
		ArrayList<ClaimBankMandateBean> claimBankMandateBeanArrayListObj = new ArrayList<ClaimBankMandateBean>();
		try {
			callStmt = (CallableStatement)con.prepareCall("{ call PROC_GET_BMDtls_AppRejBYCGTMSE(?) } ");
			callStmt.registerOutParameter(1, OracleTypes.CURSOR);
			callStmt.execute(); 
			resultset=(ResultSet) callStmt.getObject(1);
			while (resultset.next()) {
				objClaimBankMandateBean = new ClaimBankMandateBean();
				flag=true;
				if(resultset.getString("MLI_NAME") != null){
					objClaimBankMandateBean.setMliName(resultset.getString("MLI_NAME"));
				}else{
					objClaimBankMandateBean.setMliName(null);
				}
				if(resultset.getString("MEM_ID") != null){
					objClaimBankMandateBean.setMemberId(resultset.getString("MEM_ID"));
				}else{
					objClaimBankMandateBean.setMemberId(null);
				}
				
				if(resultset.getString("MEM_CONT_NO") != null){
					objClaimBankMandateBean.setContactNo(resultset.getString("MEM_CONT_NO"));
				}else{
					objClaimBankMandateBean.setContactNo(null);
				}
				if(resultset.getString("MEM_MOB_NO") != null){
					objClaimBankMandateBean.setMobileNo(resultset.getString("MEM_MOB_NO"));
				}else{
					objClaimBankMandateBean.setMobileNo(null);
				}
				
				if(resultset.getString("MEM_EMAIL_ID") != null){
					objClaimBankMandateBean.setEmailId(resultset.getString("MEM_EMAIL_ID"));
				}else{
					objClaimBankMandateBean.setEmailId(null);
				}
				if(resultset.getString("MLI_BENEFICIARY_BANK_NAME") != null){
					objClaimBankMandateBean.setMliBeneficiaryBankName(resultset.getString("MLI_BENEFICIARY_BANK_NAME"));
				}else{
					objClaimBankMandateBean.setMliBeneficiaryBankName(null);
				}
				if(resultset.getString("MLI_BENEFICIARY_NAME") != null){
					objClaimBankMandateBean.setMliBeneficiaryName(resultset.getString("MLI_BENEFICIARY_NAME"));
				}else{
					objClaimBankMandateBean.setMliBeneficiaryName(null);
				}
				if(resultset.getString("MEM_BRN_NAME") != null){
					objClaimBankMandateBean.setBranchName(resultset.getString("MEM_BRN_NAME"));
				}else{
					objClaimBankMandateBean.setBranchName(null);
				}
				if(resultset.getString("MEM_ACC_TYPE") != null){
					objClaimBankMandateBean.setAccountType(resultset.getString("MEM_ACC_TYPE"));
				}else{
					objClaimBankMandateBean.setAccountType(null);
				}
				
				if(resultset.getString("MEM_BRN_CODE") != null){
					objClaimBankMandateBean.setBranchCode(resultset.getString("MEM_BRN_CODE"));
				}else{
					objClaimBankMandateBean.setBranchCode(null);
				}
				if(resultset.getString("MEM_MICR_CODE") != null){
					objClaimBankMandateBean.setMicrCode(resultset.getString("MEM_MICR_CODE"));
				}else{
					objClaimBankMandateBean.setMicrCode(null);
				}
				if(resultset.getString("MEM_ACC_NO") != null){
					objClaimBankMandateBean.setAccountNo(resultset.getString("MEM_ACC_NO"));
				}else{
					objClaimBankMandateBean.setAccountNo(null);
				}
				if(resultset.getString("MEM_IFSC_CODE") != null){
					objClaimBankMandateBean.setIfscCode(resultset.getString("MEM_IFSC_CODE"));
				}else{
					objClaimBankMandateBean.setIfscCode(null);
				}
				if(resultset.getString("MEM_RTGS_NO") != null){
					objClaimBankMandateBean.setRtgsNo(resultset.getString("MEM_RTGS_NO"));
				}else{
					objClaimBankMandateBean.setRtgsNo(null);
				}
				if(resultset.getString("MEM_STATUS") != null){
					objClaimBankMandateBean.setStatus(resultset.getString("MEM_STATUS"));
				}else{
					objClaimBankMandateBean.setStatus(null);
				}
				if(resultset.getString("NBFC_MAKER_REMARK") != null){
					objClaimBankMandateBean.setNbfcMakerRemarks(resultset.getString("NBFC_MAKER_REMARK"));
				}else{
					objClaimBankMandateBean.setNbfcMakerRemarks(null);
				}
				if(resultset.getString("NBFC_CHECKER_REMARK") != null){
					objClaimBankMandateBean.setNbfcCheckerRemarks(resultset.getString("NBFC_CHECKER_REMARK"));
				}else{
					objClaimBankMandateBean.setNbfcCheckerRemarks(null);
				}
				if(resultset.getString("CGTMSE_CHECKER_REMARK") != null){
					objClaimBankMandateBean.setNbfcCheckerRemarks(resultset.getString("CGTMSE_CHECKER_REMARK"));
				}else{
					objClaimBankMandateBean.setNbfcCheckerRemarks(null);
				}
				claimBankMandateBeanArrayListObj.add(objClaimBankMandateBean);
			}
			if(flag==false){
					objClaimBankMandateBean.setMliName(null);
					objClaimBankMandateBean.setMemberId(null);
					objClaimBankMandateBean.setContactNo(null);
					objClaimBankMandateBean.setMobileNo(null);
					objClaimBankMandateBean.setEmailId(null);
					objClaimBankMandateBean.setMliBeneficiaryBankName(null);
					objClaimBankMandateBean.setMliBeneficiaryName(null);
					objClaimBankMandateBean.setBranchName(null);
					objClaimBankMandateBean.setAccountType(null);
					objClaimBankMandateBean.setBranchCode(null);
					objClaimBankMandateBean.setMicrCode(null);
					objClaimBankMandateBean.setAccountNo(null);
					objClaimBankMandateBean.setIfscCode(null);
					objClaimBankMandateBean.setRtgsNo(null);
					objClaimBankMandateBean.setStatus(null);
					objClaimBankMandateBean.setNbfcMakerRemarks(null);
					objClaimBankMandateBean.setNbfcCheckerRemarks(null);
					objClaimBankMandateBean.setNbfcCheckerRemarks(null);
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
			return  claimBankMandateBeanArrayListObj;		
	}
	
	@Override
	public int approveBankMandateDtlsByCGTMSEChecker (String memId,String loginUserId) {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		Connection conn = session.connection();
		CallableStatement callStmt = null;
		int dataUpdated = 0;
		try{
			if(loginUserId!=null && memId!=null){
				callStmt = conn.prepareCall("{call PRO_AppBankManDtlsByCGTMSE_CK(?,?)}");
				if(memId!="" || memId!=null){
					callStmt.setString(1,memId);
				}
				
				if(loginUserId!="" || loginUserId!=null){
					callStmt.setString(2,loginUserId);
				}
				
				dataUpdated=callStmt.executeUpdate();
				System.out.println("dataUpdated =="+dataUpdated);
				conn.close();
				callStmt.close();
				return dataUpdated;

			}else{
				System.out.println("HI==");
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
		return dataUpdated;
	}
	
	@Override
	public int returnBankMandateDtlsByCGTMSEChecker (String memId,String loginUserId,ClaimBankMandateBean claimBankMandateBean) {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		Connection conn = session.connection();
		CallableStatement callStmt = null;
		int dataUpdated = 0;
		try{
			if(loginUserId!=null && memId!=null){
				callStmt = conn.prepareCall("{call PRO_RetBankManDtlsByCGTMSE_CK(?,?,?)}");
				if(memId!="" || memId!=null){
					callStmt.setString(1,memId);
				}
				
				if(loginUserId!="" || loginUserId!=null){
					callStmt.setString(2,loginUserId);
				}
				
				if(claimBankMandateBean.getCgtmseCheckerRemarks()!="" || claimBankMandateBean.getCgtmseCheckerRemarks()!=null){
					callStmt.setString(3,claimBankMandateBean.getCgtmseCheckerRemarks());
				}
				dataUpdated=callStmt.executeUpdate();
				System.out.println("dataUpdated =="+dataUpdated);
				conn.close();
				callStmt.close();
				return dataUpdated;

			}else{
				System.out.println("HI==");
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
		return dataUpdated;
	}
	
	@Override
	public List<ClaimBankMandateBean> getReportsOfNBFCAccountDetailsByCGTMSEChecker() {
		ClaimBankMandateBean objClaimBankMandateBean=null;
		boolean flag=false;
		
		System.out.println("getReportsOfNBFCAccountDetailsByCGTMSEChecker method called to procedure inside DAOImpl==");
	 	Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		Connection con = session3.connection();
		CallableStatement callStmt = null;
		ResultSet resultset = null;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		
		ArrayList<ClaimBankMandateBean> claimBankMandateBeanArrayListObj = new ArrayList<ClaimBankMandateBean>();
		try {
			callStmt = (CallableStatement)con.prepareCall("{ call PROC_ReportsOfAccountDetails(?) } ");
			callStmt.registerOutParameter(1, OracleTypes.CURSOR);
			callStmt.execute(); 
			resultset=(ResultSet) callStmt.getObject(1);
			while (resultset.next()) {
				flag=true;
				objClaimBankMandateBean = new ClaimBankMandateBean();
				if(resultset.getString("MLI_NAME") != null){
					objClaimBankMandateBean.setMliName(resultset.getString("MLI_NAME"));
				}else{
					objClaimBankMandateBean.setMliName(null);
				}
				if(resultset.getString("MEM_ID") != null){
					objClaimBankMandateBean.setMemberId(resultset.getString("MEM_ID"));
				}else{
					objClaimBankMandateBean.setMemberId(null);
				}
				
				if(resultset.getString("MEM_CONT_NO") != null){
					objClaimBankMandateBean.setContactNo(resultset.getString("MEM_CONT_NO"));
				}else{
					objClaimBankMandateBean.setContactNo(null);
				}
				if(resultset.getString("MEM_MOB_NO") != null){
					objClaimBankMandateBean.setMobileNo(resultset.getString("MEM_MOB_NO"));
				}else{
					objClaimBankMandateBean.setMobileNo(null);
				}
				
				if(resultset.getString("MEM_EMAIL_ID") != null){
					objClaimBankMandateBean.setEmailId(resultset.getString("MEM_EMAIL_ID"));
				}else{
					objClaimBankMandateBean.setEmailId(null);
				}
				if(resultset.getString("MLI_BENEFICIARY_BANK_NAME") != null){
					objClaimBankMandateBean.setMliBeneficiaryBankName(resultset.getString("MLI_BENEFICIARY_BANK_NAME"));
				}else{
					objClaimBankMandateBean.setMliBeneficiaryBankName(null);
				}
				if(resultset.getString("MLI_BENEFICIARY_NAME") != null){
					objClaimBankMandateBean.setMliBeneficiaryName(resultset.getString("MLI_BENEFICIARY_NAME"));
				}else{
					objClaimBankMandateBean.setMliBeneficiaryName(null);
				}
				if(resultset.getString("MEM_BRN_NAME") != null){
					objClaimBankMandateBean.setBranchName(resultset.getString("MEM_BRN_NAME"));
				}else{
					objClaimBankMandateBean.setBranchName(null);
				}
				if(resultset.getString("MEM_ACC_TYPE") != null){
					objClaimBankMandateBean.setAccountType(resultset.getString("MEM_ACC_TYPE"));
				}else{
					objClaimBankMandateBean.setAccountType(null);
				}
				
				if(resultset.getString("MEM_BRN_CODE") != null){
					objClaimBankMandateBean.setBranchCode(resultset.getString("MEM_BRN_CODE"));
				}else{
					objClaimBankMandateBean.setBranchCode(null);
				}
				if(resultset.getString("MEM_MICR_CODE") != null){
					objClaimBankMandateBean.setMicrCode(resultset.getString("MEM_MICR_CODE"));
				}else{
					objClaimBankMandateBean.setMicrCode(null);
				}
				if(resultset.getString("MEM_ACC_NO") != null){
					objClaimBankMandateBean.setAccountNo(resultset.getString("MEM_ACC_NO"));
				}else{
					objClaimBankMandateBean.setAccountNo(null);
				}
				if(resultset.getString("MEM_IFSC_CODE") != null){
					objClaimBankMandateBean.setIfscCode(resultset.getString("MEM_IFSC_CODE"));
				}else{
					objClaimBankMandateBean.setIfscCode(null);
				}
				if(resultset.getString("MEM_RTGS_NO") != null){
					objClaimBankMandateBean.setRtgsNo(resultset.getString("MEM_RTGS_NO"));
				}else{
					objClaimBankMandateBean.setRtgsNo(null);
				}
				if(resultset.getString("MEM_STATUS") != null){
					objClaimBankMandateBean.setStatus(resultset.getString("MEM_STATUS"));
				}else{
					objClaimBankMandateBean.setStatus(null);
				}
				if(resultset.getString("NBFC_MAKER_REMARK") != null){
					objClaimBankMandateBean.setNbfcMakerRemarks(resultset.getString("NBFC_MAKER_REMARK"));
				}else{
					objClaimBankMandateBean.setNbfcMakerRemarks(null);
				}
				if(resultset.getString("NBFC_CHECKER_REMARK") != null){
					objClaimBankMandateBean.setNbfcCheckerRemarks(resultset.getString("NBFC_CHECKER_REMARK"));
				}else{
					objClaimBankMandateBean.setNbfcCheckerRemarks(null);
				}
				if(resultset.getString("CGTMSE_CHECKER_REMARK") != null){
					objClaimBankMandateBean.setNbfcCheckerRemarks(resultset.getString("CGTMSE_CHECKER_REMARK"));
				}else{
					objClaimBankMandateBean.setNbfcCheckerRemarks(null);
				}
				if(resultset.getString("NBFC_MK_USER_ID") != null){
					objClaimBankMandateBean.setNbfcMakerId(resultset.getString("NBFC_MK_USER_ID"));
				}else{
					objClaimBankMandateBean.setNbfcMakerId(null);
				}
				if(resultset.getString("NBFC_MK_DATE") != null){
					objClaimBankMandateBean.setNbfcMakerDate(dateFormat.format(resultset.getDate("NBFC_MK_DATE")));
				}else{
					objClaimBankMandateBean.setNbfcMakerDate(null);
				}
				if(resultset.getString("NBFC_CK_USER_ID") != null){
					objClaimBankMandateBean.setNbfcCheckerId(resultset.getString("NBFC_CK_USER_ID"));
				}else{
					objClaimBankMandateBean.setNbfcCheckerId(null);
				}
				if(resultset.getString("NBFC_CK__DATE") != null){
					objClaimBankMandateBean.setNbfcCheckerDate(dateFormat.format(resultset.getDate("NBFC_CK__DATE")));
				}else{
					objClaimBankMandateBean.setNbfcCheckerDate(null);
				}
				if(resultset.getString("CGTMSE_CK_USER_ID") != null){
					objClaimBankMandateBean.setCgtmseCheckerId(resultset.getString("CGTMSE_CK_USER_ID"));
				}else{
					objClaimBankMandateBean.setCgtmseCheckerId(null);
				}
				if(resultset.getString("CGTMSE_CK_DATE") != null){
					objClaimBankMandateBean.setCgtmeCheckerDate(dateFormat.format(resultset.getDate("CGTMSE_CK_DATE")));
				}else{
					objClaimBankMandateBean.setCgtmeCheckerDate(null);
				}
				claimBankMandateBeanArrayListObj.add(objClaimBankMandateBean);
			}
			
			if(flag==false){
					objClaimBankMandateBean.setMliName(null);
					objClaimBankMandateBean.setMemberId(null);
					objClaimBankMandateBean.setContactNo(null);
					objClaimBankMandateBean.setMobileNo(null);
					objClaimBankMandateBean.setEmailId(null);
					objClaimBankMandateBean.setMliBeneficiaryBankName(null);
					objClaimBankMandateBean.setMliBeneficiaryName(null);
					objClaimBankMandateBean.setBranchName(null);
					objClaimBankMandateBean.setAccountType(null);
					objClaimBankMandateBean.setBranchCode(null);
					objClaimBankMandateBean.setMicrCode(null);
					objClaimBankMandateBean.setAccountNo(null);
					objClaimBankMandateBean.setIfscCode(null);
					objClaimBankMandateBean.setRtgsNo(null);
					objClaimBankMandateBean.setStatus(null);
					objClaimBankMandateBean.setNbfcMakerRemarks(null);
					objClaimBankMandateBean.setNbfcCheckerRemarks(null);
					objClaimBankMandateBean.setNbfcCheckerRemarks(null);
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
			return  claimBankMandateBeanArrayListObj;		
	}
	
}
