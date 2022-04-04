package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.BankMandateFormBo;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.BankMandateForm;
import com.nbfc.model.ClaimUploadHeader;

@Repository
@Transactional
public class BankMandateFormDaoImpl implements BankMandateFormDao {
	final static Logger logger = Logger.getLogger(BankMandateFormDaoImpl.class.getName());
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<BankMandateFormBo> findBankMandateForm(String mliId, String usrId) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		BankMandateFormBo bo = null;
		List<BankMandateFormBo> bankMandateFormList = null;
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Get_Bank_MandateForm_Data(?,?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, mliId);
			callableStmt.setString(3, usrId);
			callableStmt.registerOutParameter(4, Types.VARCHAR);

			callableStmt.execute();
			String error = callableStmt.getString(4);
			rs = callableStmt.getResultSet();

			while (rs.next()) {
				bankMandateFormList = new ArrayList<>();
				bo = new BankMandateFormBo();

				bo.setMlIName(rs.getString("MLI_Name"));
				bo.setMilId(rs.getString("MLI_Id"));
				bo.setMliContactNo(rs.getString("MLI_ContNo"));
				bo.setMliMobileNo(rs.getString("MLI_MobileNo"));
				bo.setMliEmailId(rs.getString("MLI_EmailId"));
				bo.setBankName(rs.getString("Bank_Name"));
				bo.setBranch(rs.getString("Branch_Name"));
				bo.setAccName(rs.getString("Account_Name"));
				bo.setAccNum(rs.getString("Account_No"));
				bo.setTypeOfAcc(rs.getString("Type_Of_Account"));
				bo.setIfscCode(rs.getString("IFSC_Code"));
				bo.setUploadDoc(rs.getString("Upload_Document"));
				bo.setApproveStatus(rs.getString("Approval_Status"));
				bo.setmRemarks(rs.getString("Maker_Remarks"));
				bo.setcRemarks(rs.getString("Checker_Remarks"));
				bo.setCgtmsRemarks(rs.getString("CGTMSE_Remarks"));
				bo.setFileData(rs.getBytes("File_Data"));
				bo.setDeclaration(rs.getString("Declartion_Flag"));
				bankMandateFormList.add(bo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bankMandateFormList;
	}

	@Override
	public boolean saveBankMandateFormDataMli(BankMandateForm bankMandate) throws BusinessException {
		Session session = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.getCurrentSession();
			if (bankMandate.getId() != null) {
				session.update(bankMandate);
			} else {
				session.save(bankMandate);
			}
			isSuccess = true;

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;
	}

	@Override
	public BankMandateFormBo findBankMandateFormData(String mliId, String usrId) throws BusinessException {
		// TODO Auto-generated method stub
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		BankMandateFormBo bo = null;
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call GET_Checker_File_Data(?,?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, mliId);
			callableStmt.setString(3, usrId);
			callableStmt.registerOutParameter(4, Types.VARCHAR);

			callableStmt.execute();
			String error = callableStmt.getString(4);
			rs = callableStmt.getResultSet();

			while (rs.next()) {

				bo = new BankMandateFormBo();
				bo.setUploadDoc(rs.getString("Upload_Document"));
				bo.setFileData(rs.getBytes("File_Data"));

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bo;

	}

	@Override
	public boolean saveBankMandateFormDataMliChecker(BankMandateFormBo bankMandatechecker) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		boolean isSuccess = false;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call FUNC_Update_bank_Mand_Form(?,?,?,?,?,?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, bankMandatechecker.getApproveStatus());
			callableStmt.setString(3, bankMandatechecker.getcRemarks());
			callableStmt.setString(4, bankMandatechecker.getAccNum());
			callableStmt.setString(5, bankMandatechecker.getIfscCode());
			callableStmt.setString(6, bankMandatechecker.getMilId());
			callableStmt.setString(7, bankMandatechecker.getLoginId());
			callableStmt.registerOutParameter(8, Types.VARCHAR);

			callableStmt.execute();
			String error = callableStmt.getString(8);
			rs = callableStmt.getResultSet();
			tx.commit();
			isSuccess = true;

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;
	}

	@Override
	public BankMandateForm findBankMandateDataStutusWise(String mliId, String status) throws BusinessException {
		Session session = null;
		BankMandateForm bnkMandate = null;
		try {
			session = sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(BankMandateForm.class);
			cr.add(Restrictions.eq("mliId", mliId));
			cr.add(Restrictions.eq("approvalStatus", status)).setMaxResults(1);
			bnkMandate = (BankMandateForm) cr.uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bnkMandate;
	}

	@Override
	public BankMandateForm getBankMandateStatusData(String mliId, String usrId) throws BusinessException {
		// TODO Auto-generated method stub
		Session session = null;
		BankMandateForm bnkMandate = null;
		try {
			session = sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(BankMandateForm.class);
			cr.add(Restrictions.eq("mliId", mliId));
			cr.add(Restrictions.eq("createdBy", usrId)).setMaxResults(1);
			bnkMandate = (BankMandateForm) cr.uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bnkMandate;
	}
	
	@Override
	public List<BankMandateFormBo> findAllCgtmseApprovalByUserId(String userId,String apprvStatus) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		List<BankMandateFormBo> bankMandateList = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Get_Manadete_Details(?,?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, userId);
			callableStmt.setString(3, apprvStatus);
			callableStmt.registerOutParameter(4, Types.VARCHAR);

			callableStmt.execute();
			String error = callableStmt.getString(4);
			rs = callableStmt.getResultSet();

			while (rs.next()) {
				BankMandateFormBo bo = new BankMandateFormBo();

				bo.setMilId(rs.getString("MLI_Id"));
				bo.setMlIName(rs.getString("MEM_BANK_NAME"));
				bo.setBankName(rs.getString("Bank_Name"));
				bo.setBranch(rs.getString("Branch_Name"));
				bo.setAccNum(rs.getString("Account_No"));
				bo.setTypeOfAcc(rs.getString("Type_Of_Account"));
				bo.setIfscCode(rs.getString("IFSC_Code"));
				bo.setUploadDoc(rs.getString("Upload_Document"));
				bo.setApproveStatus(rs.getString("Approval_Status"));
				bo.setcRemarks(rs.getString("CGTMSE_Remarks"));

				bankMandateList.add(bo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bankMandateList;
	}

	@Override
	public boolean updateCgtmseApproal(BankMandateFormBo bo) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		boolean isSuccess = false;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call FUNC_Update_bank_Mand_Form(?,?,?,?,?,?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, bo.getApproveStatus());
			callableStmt.setString(3, bo.getcRemarks());
			callableStmt.setString(4, bo.getAccNum());
			callableStmt.setString(5, bo.getIfscCode());
			callableStmt.setString(6, bo.getMilId());
			callableStmt.setString(7, bo.getLoginId());
			callableStmt.registerOutParameter(8, Types.VARCHAR);

			callableStmt.execute();
			String error = callableStmt.getString(8);
			rs = callableStmt.getResultSet();
			tx.commit();
			isSuccess = true;

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;
	}
	
	@Override
	public BankMandateForm downloadCgtmseApproal(String mliId) throws BusinessException {
		Session session = null;
		BankMandateForm bnkMandate = null;
		try {
			session = sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(BankMandateForm.class);
			cr.add(Restrictions.eq("mliId", mliId)).setMaxResults(1);;
			bnkMandate = (BankMandateForm) cr.uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bnkMandate;
	}

	@Override
	public boolean checkBankMandateExist(String mliId,String userId) throws BusinessException {
		Session session = null;
		boolean isBnkNotExist = false;
		try
		{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from BankMandateForm where mliId=:mliID");
			query.setParameter("mliID", mliId);
			List<BankMandateForm> mliList = query.list();
			if(mliList.isEmpty() == false && mliList.size() > 0)
			{
				isBnkNotExist = checkBankMandateExistWithDifferentUser(mliId,userId,session);
				System.out.println("Data :: 1. isBnkNotExist ::: " + isBnkNotExist);
			}
		}
		catch(Exception e){
			logger.error(e.getMessage());
		}
		return isBnkNotExist;
	}
	
	public boolean checkBankMandateExistWithDifferentUser(String mliId,String userId,Session session) throws BusinessException 
	{
		boolean isBnkNotExistWithSameUser = false;
		try
		{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from BankMandateForm where mliId=:mliID and createdBy!=:createdBy");
			query.setParameter("mliID", mliId);
			query.setParameter("createdBy", userId);
			List<BankMandateForm> mliList = query.list();
			if(mliList.isEmpty() == false && mliList.size() > 0)
			{
				System.out.println("checkBankMandateExistWithSameUser ::: " + mliList.size());
				isBnkNotExistWithSameUser = true;
			}
		}
		catch(Exception e){
			logger.error(e.getMessage());
		}
		return isBnkNotExistWithSameUser;
	}
}
