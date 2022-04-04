package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.model.MLIMaker;
import com.nbfc.model.MLIMakerApprovalRejection;
import com.nbfc.model.MliMakerEntity;
import com.nbfc.model.PortfolioBatchApp;
import com.raistudies.domain.CustomExceptionHandler;

/**
 * @author Saurav Tyagi 2017
 * 
 */
@Repository("PortfolioApprovalDao")
public class PortfolioApprovalImpl implements PortfolioApprovalDao {

	@Autowired
	private SessionFactory sessionFactory;
	TransactionManager tx;
	@SuppressWarnings("deprecation")
	
	public boolean approvePortfolioStatus(String appRefNo) {
		System.out.println("appRefNo" + appRefNo);
		String status = "Approved";
		Query query = sessionFactory.getCurrentSession().createQuery(
				"Update Mli_macker_checker_info set status=:statueValue"
						+ " where app_ref_no = :appRefNo");

		query.setParameter("statusValue", "Approved");
		query.setParameter("appRefNo", appRefNo);
		int result = query.executeUpdate();
		sessionFactory.getCurrentSession().getTransaction().commit();
		return false;
	}

	
	public void addCheckerApproval(List<MLIMakerApprovalRejection> employee) {

		for (Object mliEntityObj : employee) {
			sessionFactory.getCurrentSession().saveOrUpdate(mliEntityObj);

		}
		/*
		 * Session session3 = sessionFactory.openSession(); Transaction tx3 =
		 * session3.beginTransaction(); session3.saveOrUpdate(employee);
		 * tx3.commit();
		 */// sessionFactory.getCurrentSession().saveOrUpdate(employee);
			// System.out.println("Successfully Update..");
			// sessionFactory.close();
	}

	
	public boolean updateStatusMLIChkAppLodge(String usrId, String status,
			String prtfoliNum) {
		
		System.out.println("usrId :"+usrId);
		System.out.println("usrId :"+usrId);
		
		
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();
		
		Connection con=session3.connection();
		try {
			CallableStatement callable= (CallableStatement)con.prepareCall("{ call UPDATE_MLI_MAKER_STATUS(?,?) } ");
			callable.setString(1, usrId);
			callable.registerOutParameter(2, Types.VARCHAR);
			callable.execute();
			String pouterror1 = callable.getString(2);
			if (pouterror1 != null) {
				throw new CustomExceptionHandler("Error Occured  :"+ pouterror1);
			}

			tn.commit();
		} catch (SQLException e) {
			if (tn != null)
				tn.rollback();
			e.printStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
				// session.close();
				session3.close();
				sessionFactory.close();
			} catch (Exception e2) {

			}
		}
		return false;
	}

}

