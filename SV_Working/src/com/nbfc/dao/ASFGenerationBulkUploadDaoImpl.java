package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.bean.ASFGenerationDetailBean;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.bean.NbfcAppropriationBean;
import com.raistudies.domain.CustomExceptionHandler;

import oracle.jdbc.OracleTypes;
@Repository("ASFGenerationBulkUploadDao")
public class ASFGenerationBulkUploadDaoImpl implements ASFGenerationBulkUploadDao{
@Autowired
SessionFactory sessionFactory;
	
	@Override
	public ArrayList<ASFGenerationDetailBean> getAllASFDetails(String userid,String fileId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
	
		ArrayList<ASFGenerationDetailBean> ASFBeanList = new ArrayList<ASFGenerationDetailBean>();

		Connection conn = session.connection(); 

		try {
			Connection connection = null;
			CallableStatement getASfdataStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getASfdataStmt = conn
					.prepareCall(" { call NBFC.PROC_ASF_DATA(?,?,?,?) }");
		
			getASfdataStmt.setString(1, userid);
			getASfdataStmt.setString(2, fileId);
			getASfdataStmt.registerOutParameter(3, Types.VARCHAR);
			getASfdataStmt.registerOutParameter(4, OracleTypes.CURSOR);
			
			getASfdataStmt.execute();

			String pouterror1 = getASfdataStmt.getString(3);
			if (pouterror1 != null) {
				throw new CustomExceptionHandler("Error Occured  :"
						+ pouterror1);
		
			} else {

				resultSet = (ResultSet) getASfdataStmt.getObject(4);
				exception = getASfdataStmt.getString(3);
				if(resultSet!=null){
					ASFGenerationDetailBean asfBean=null;
					while (resultSet.next()) {
						asfBean = new ASFGenerationDetailBean();					
						asfBean.setCGPAN(resultSet.getString(1));				
						asfBean.setItpan(resultSet.getString(3));					
						asfBean.setLoadAccountNo(resultSet.getString(2));	
						asfBean.setPrevoutstandingAmt(resultSet.getDouble(4));	
						asfBean.setMse_name(resultSet.getString(5));	
						ASFBeanList.add(asfBean);
					}
					
					
				
				}
			
				
			}
			resultSet.close();
			getASfdataStmt.close();
			getASfdataStmt = null;

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

		return ASFBeanList;
	}

	@Override
	public List<ASFGenerationDetailBean> getASFDetailsByFileWise(String userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
	
		ArrayList<ASFGenerationDetailBean> ASFBeanList = new ArrayList<ASFGenerationDetailBean>();

		Connection conn = session.connection(); 

		try {
			Connection connection = null;
			CallableStatement getASfdataStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getASfdataStmt = conn
					.prepareCall(" { call PROC_ASF_DATA_FILEWISE(?,?) }");
		
			getASfdataStmt.setString(1, userId);
			getASfdataStmt.registerOutParameter(2, Types.VARCHAR);
			//getASfdataStmt.registerOutParameter(3, OracleTypes.CURSOR);
			
			getASfdataStmt.execute();
			//resultSet = getASfdataStmt.executeQuery();
			String pouterror1 = getASfdataStmt.getString(2);
			if (pouterror1 != null) {
				throw new CustomExceptionHandler("Error Occured  :"
						+ pouterror1);
		
			} else {

				resultSet = (ResultSet)getASfdataStmt.getResultSet(); 
						//getASfdataStmt.getObject(3);
				exception = getASfdataStmt.getString(2);
				if(resultSet!=null){
					ASFGenerationDetailBean asfBean=null;
					while (resultSet.next()) {
						asfBean = new ASFGenerationDetailBean();					
						asfBean.setFileId(resultSet.getString(2));				
						asfBean.setPortfolioName(resultSet.getString(1));	
						asfBean.setMli_Name(resultSet.getString(3));	
						asfBean.setFrom_date(resultSet.getString(4));	
						asfBean.setTo_date(resultSet.getString(5));	
						ASFBeanList.add(asfBean);
					}
				}
			}
			resultSet.close();
			getASfdataStmt.close();
			getASfdataStmt = null;

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

		return ASFBeanList;
}
}