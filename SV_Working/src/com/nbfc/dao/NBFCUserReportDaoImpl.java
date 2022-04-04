package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.GetMLINameBean;
import com.nbfc.model.NBFCUserPortfolioDetails;
import com.raistudies.domain.CustomExceptionHandler;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Repository("NBFCUserReportDao")
public class NBFCUserReportDaoImpl implements NBFCUserReportDao{

	
	@Autowired
	SessionFactory sessionFactory;
	
	ResultSet resultset = null;
	ResultSetMetaData resultSetMetaData = null;
	
	public List<NBFCUserPortfolioDetails> getPortfolioDetailForUserReport(String mliID) {
		
		return null;
	}


	public List<Map<String, Object>> getUserDashboardDetails(String userId) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try{
			Session session4 = sessionFactory.openSession();
			/*Transaction tn = session4.beginTransaction();*/
			Connection conn = session4.connection();
		    CallableStatement cs = conn.prepareCall("{? = call NBFC_PACKREPORT.Fun_getMLI_DashBoard_Data(?,?,?)}");

		    // register input parameters
		    cs.registerOutParameter(1, Types.INTEGER);
		    cs.setString(2, userId);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.registerOutParameter(4, Types.VARCHAR);
			 cs.execute();
			int result = cs.getInt(1);
			String pouterror = cs.getString(4);
			if (result != 0) {
				throw new CustomExceptionHandler("Exception occured  :"
						+ pouterror);
			}else{
				 // Procedure execution
				

				    resultset = (ResultSet) cs.getObject(3);
					resultSetMetaData = resultset.getMetaData();
					int coulmnCount = resultSetMetaData.getColumnCount();
					while (resultset.next()) {
					    Map<String, Object> columns = new LinkedHashMap<String, Object>();

					    for (int i = 1; i <= coulmnCount; i++) {
					        columns.put(resultSetMetaData.getColumnLabel(i), resultset.getObject(i));
					    }

					    rows.add(columns);
					}
			}
		   
		   
			
			
			
	
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rows;
	
		}

	
	public GetMLINameBean getMliDetails(String userId) {
		GetMLINameBean mliName=new GetMLINameBean();
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try{
			Session session4 = sessionFactory.openSession();
			/*mliName = (GetMLINameBean)session4.createCriteria(userId).uniqueResult();
			 
			
			System.out.println(mliName.getMliName()+"               mliName               ");*/
			/*Transaction tn = session4.beginTransaction();*/
			Connection conn = session4.connection();
		    CallableStatement cs = conn.prepareCall("{ call Fun_get_mli_name(?,?)}");

		    // register input parameters
		   // cs.registerOutParameter(1, Types.INTEGER);
		    cs.setString(1, userId);
			//cs.registerOutParameter(3,Types.CURSOR);
			cs.registerOutParameter(2, Types.VARCHAR);
			
			 cs.execute();
			//int result = cs.getInt(1);
			String pouterror = cs.getString(2);
			if (pouterror != null) {
				throw new CustomExceptionHandler("Exception occured  :"
						+ pouterror);
			}else{
					    resultset = (ResultSet) cs.getResultSet();
					resultSetMetaData = resultset.getMetaData();
					int coulmnCount = resultSetMetaData.getColumnCount();
					while (resultset.next()) {
						mliName.setMliName(resultset.getString(1));}
		}
			}catch(Exception e){
			e.printStackTrace();
		}
						
					
			
		  
		
		return mliName;
	
		
	}

}
