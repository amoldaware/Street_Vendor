package com.nbfc.dao;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.bean.ApplicationStatusDetailsBean;
import com.raistudies.domain.CustomExceptionHandler;

@Repository("ApplicationStatusDao")
public class ApplicationStatusDaoImpl implements ApplicationStatusDao {
	@Autowired
	SessionFactory sessionFactory;
	ResultSet resultset = null;
	ResultSetMetaData resultSetMetaData = null;
	public List<Map<String, Object>> getApplicationStatus(String userId,Date toDate,Date fromDate,String status){

		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try {
			Session session4 = sessionFactory.openSession();
			/* Transaction tn = session4.beginTransaction(); */
			Connection conn = session4.connection();
			CallableStatement cs = conn
					.prepareCall("{? = call NBFC_PACKREPORT.Fun_get_MLIFileUploaded_Data(?,?,?,?,?,?)}");

			// register input parameters
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, userId);
			cs.setDate(3, new java.sql.Date(toDate.getTime()));
			cs.setDate(4, new java.sql.Date( fromDate.getTime()));
			cs.setString(5, status);
			cs.registerOutParameter(6, OracleTypes.CURSOR);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.execute();
			int result = cs.getInt(1);
			String pouterror = cs.getString(7);
			if (result != 0) {
				throw new CustomExceptionHandler("Exception occured  :"
						+ pouterror);
			} else {
				// Procedure execution
				resultset = (ResultSet) cs.getObject(6);
				resultSetMetaData = resultset.getMetaData();
				int coulmnCount = resultSetMetaData.getColumnCount();
				while (resultset.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= coulmnCount; i++) {
						columns.put(resultSetMetaData.getColumnLabel(i),
								resultset.getObject(i));
					}
					rows.add(columns);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;

	
		
	}
	@Override
	public List<Map<String, Object>> getFileData(String FileId) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try {
			Session session4 = sessionFactory.openSession();
			/* Transaction tn = session4.beginTransaction(); */
			Connection conn = session4.connection();
			CallableStatement cs = conn
					.prepareCall("{? = call NBFC_PACKREPORT.Fun_get_FileUploaded_Data(?,?,?)}");

			// register input parameters
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, FileId);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.execute();
			int result = cs.getInt(1);
			String pouterror = cs.getString(4);
			if (result != 0) {
				throw new CustomExceptionHandler("Exception occured  :"
						+ pouterror);
			} else {
				// Procedure execution

				resultset = (ResultSet) cs.getObject(3);
				resultSetMetaData = resultset.getMetaData();
				int coulmnCount = resultSetMetaData.getColumnCount();
				while (resultset.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= coulmnCount; i++) {
						columns.put(resultSetMetaData.getColumnLabel(i),
								resultset.getObject(i));
					}

					rows.add(columns);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;

	}
	@Override
	public ApplicationStatusDetailsBean getapplicationDetails(String FileId) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		ApplicationStatusDetailsBean bean = null;
		try {
			
			Session session4 = sessionFactory.openSession();
			/* Transaction tn = session4.beginTransaction(); */
			Connection conn = session4.connection();
			CallableStatement cs = conn
					.prepareCall("{? = call NBFC_PACKREPORT.Fun_get_LoanAccoutNo_Data(?,?,?)}");

			// register input parameters
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, FileId);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.execute();
			int result = cs.getInt(1);
			String pouterror = cs.getString(4);
			if (result != 0) {
				throw new CustomExceptionHandler("Exception occured  :"
						+ pouterror);
			} else {
				// Procedure execution

				resultset = (ResultSet) cs.getObject(3);
				resultSetMetaData = resultset.getMetaData();
				//int coulmnCount = resultSetMetaData.getColumnCount();
				while (resultset.next()) {
					bean = new ApplicationStatusDetailsBean();
					bean.setLONE_TYPE(resultset.getString(1));
					bean.setPORTFOLIO_NO(resultset.getString(2));
					
					bean.setLOAN_ACCOUNT_NO(resultset.getString(4));
					bean.setCONSTITUTION(resultset.getString(5));
					bean.setMSE_NAME(resultset.getString(6));
					bean.setSANCTION_DATE(resultset.getString(7));
					bean.setSANCTIONED_AMOUNT(resultset.getString(8));
					bean.setFIRST_DISBURSEMENT_DATE(resultset.getString(9));
					bean.setINTEREST_RATE(resultset.getString(10));
					bean.setMICRO_SMALL(resultset.getString(11));
					bean.setTENOR_IN_MONTHS(resultset.getString(12));
					bean.setMSE_ADDRESS(resultset.getString(13));
					bean.setCITY(resultset.getString(14));
					bean.setDISTRICT(resultset.getString(15));
					bean.setPINCODE(resultset.getString(16));
					bean.setSTATE(resultset.getString(17));
					bean.setMSE_ITPAN(resultset.getString(18));
					bean.setUDYOG_AADHAR_NO(resultset.getString(19));
					bean.setINDUSTRY_NATURE(resultset.getString(20));
					bean.setINDUSTRY_SECTOR(resultset.getString(21));
					bean.setNO_OF_EMPLOYEES(resultset.getString(22));
					bean.setNEW_EXISTING_UNIT(resultset.getString(23));
					bean.setPREVIOUS_BANKING_EXPERIENCE(resultset.getString(24));
					bean.setCHIEF_PROMOTER_FIRST_NAME(resultset.getString(25));
					bean.setCHIEF_PROMOTER_MIDDLE_NAME(resultset.getString(26));
					bean.setCHIEF_PROMOTER_LAST_NAME(resultset.getString(27));
					bean.setCHIEF_PROMOTER_IT_PAN(resultset.getString(28));
					bean.setCHIEF_PROMOTER_MAIL_ID(resultset.getString(29));
					bean.setCHIEF_PROMOTER_CONTACT_(resultset.getString(30));
					bean.setMINORITY_COMMUNITY(resultset.getString(31));
					bean.setHANDICAPPED(resultset.getString(32));
					bean.setWOMEN(resultset.getString(33));
					bean.setCATEGORY(resultset.getString(34));
					bean.setPORTFOLIO_NAME(resultset.getString(36));
					
					bean.setDAN_ID(resultset.getString(38));
					bean.setGUARANTEE_AMOUNT(resultset.getString(39));
					bean.setCOLLETRAL_SECURITY_AMOUNT(resultset.getString(40));
					bean.setRETAIL_TRADE(resultset.getString(41));
					bean.setAADHAR_(resultset.getString(42));
					bean.setCGPAN(resultset.getString(43));
					bean.setOUTSTANDING_AMOUNT(resultset.getString(44));
					bean.setMEMBER_ID(resultset.getString(45)+resultset.getString(46)+resultset.getString(47));
					//bean.setMEM_BNK_ID(resultset.getString(76));
					//bean.setMEM_ZNE_ID(resultset.getString(77));
					//bean.setMEM_BRN_ID(resultset.getString(78));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;

	}
	@Override
	public ArrayList<List> getGETuploadedFileData(
			String fileId) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		ArrayList<List> DataHV = new ArrayList<List>();
		ArrayList<String> Header = new ArrayList<String>();
		ArrayList<List> Data1 = new ArrayList<List>();
		ApplicationStatusDetailsBean bean = null;
		try {
		
			Session session4 = sessionFactory.openSession();
			/* Transaction tn = session4.beginTransaction(); */
			Connection conn = session4.connection();
			CallableStatement cs = conn
					.prepareCall("{? = call NBFC_PACKREPORT.Fun_FILE_DATA(?,?,?)}");

			// register input parameters
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, fileId);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.execute();
			int result = cs.getInt(1);
			String pouterror = cs.getString(4);
			if (result != 0) {
				throw new CustomExceptionHandler("Exception occured  :"+ pouterror);
			}else{
				 // Procedure execution
				    resultset = (ResultSet) cs.getObject(3);
					resultSetMetaData = resultset.getMetaData();
					int coulmnCount = resultSetMetaData.getColumnCount();
					System.out.println("coulmnCount=="+coulmnCount);
					
					
					
					
					//####
					
					
					
					
					int row_counter=1;
					while(resultset.next()){
						if(row_counter==1){
							
								for(int i=1 ; i<=coulmnCount; i++){
									
										Header.add(resultSetMetaData.getColumnLabel(i));
								}
						}
						ArrayList<String> Data = new ArrayList<String>();
						for(int n=1;n<=coulmnCount;n++){
							
							Data.add(resultset.getString(n));
							
					    }
						Data1.add(Data);
						
						
						
						
						row_counter++;
					}
					DataHV.add(Header);
					DataHV.add(Data1);
					
					
					//####
					
					
				/*	while (resultset.next()) {
						
						
						
					    Map<String, Object> columns = new LinkedHashMap<String, Object>();

					    for (int i = 1; i <= coulmnCount; i++) {
					        columns.put(resultSetMetaData.getColumnLabel(i), resultset.getObject(i));
					    }

					    rows.add(columns);
					}*/
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return DataHV;

	}
	@Override
	public List<Map<String, Object>> getstreetVendorReport(String userId, Date toDate, Date fromDate,
			String mliLongName, String role,String pmsNo) {
System.out.println("dao class"+new java.sql.Date(toDate.getTime()));
System.out.println("dao class"+new java.sql.Date(fromDate.getTime()));
System.out.println(""+role);
System.out.println(""+mliLongName);
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try {
			Session session4 = sessionFactory.openSession();
			/* Transaction tn = session4.beginTransaction(); */
			Connection conn = session4.connection();
			CallableStatement cs = conn
					.prepareCall("{ call STREET_VENDOR_REPORT(?,?,?,?,?,?)}");
			// register input parameters
			cs.setString(1, mliLongName);
			cs.setString(2, role);
			cs.setDate(3, (new java.sql.Date(toDate.getTime())));
			cs.setDate(4, (new java.sql.Date(fromDate.getTime())));
			cs.setString(5, pmsNo);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			//int result = cs.getInt(1);
			String pouterror = cs.getString(6);
			if (pouterror != null) {
				throw new CustomExceptionHandler("Exception occured  :"
						+ pouterror);
			} else {
				// Procedure execution

				resultset = (ResultSet) cs.getResultSet();
				resultSetMetaData = resultset.getMetaData();
				int coulmnCount = resultSetMetaData.getColumnCount();
				while (resultset.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= coulmnCount; i++) {
						columns.put(resultSetMetaData.getColumnLabel(i),
								resultset.getObject(i));
					}

					rows.add(columns);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;

	
		
		// TODO Auto-generated method stub
		//return null;
	}
	@Override
	public String getMliNameByMliId(String mliId) {
		String mliNmae="ALL";
		try {
			Session session = sessionFactory.openSession();
			String sqlQuery= "select innerqry.MEM_BANK_NAME from(select MEM_BANK_NAME,CONCAT(IFNULL(MEM_BNK_ID, '') ,IFNULL(MEM_ZNE_ID, '') ,IFNULL(MEM_BRN_ID, '')) mliId"
					+ " from NBFC_MEMBER_INFO )innerqry where innerqry.mliId='"+mliId+"'";
			Query query=session.createSQLQuery(sqlQuery);
			@SuppressWarnings("unchecked")
			List<String> list =(List<String>) query.list();
			if(!list.isEmpty())
				mliNmae=list.get(0);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mliNmae;
	}
	

	@Override
	public List<Map<String, Object>> findSummaryReport(String date,String date1) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try {
			Session session4 = sessionFactory.openSession();
			Connection conn = session4.connection();
			CallableStatement cs = conn.prepareCall("{ call STVN_MLI_Report(?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, date);
			cs.setString(3, date1);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.execute();
			String pouterror = cs.getString(4);

			resultset = (ResultSet) cs.getResultSet();
			resultSetMetaData = resultset.getMetaData();
			int coulmnCount = resultSetMetaData.getColumnCount();
			while (resultset.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= coulmnCount; i++) {
					columns.put(resultSetMetaData.getColumnLabel(i), resultset.getObject(i));
				}

				rows.add(columns);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	@Override
	public List<String> getLenderName() {
		List<String> lenderNames = new ArrayList<String>();
		try {
			Session session = sessionFactory.openSession();
			String sqlQuery= "select distinct LenderName from nbfc_street_vendor_master WHERE ISUPDATED=0 and LenderName is not null order by LenderName";
			Query query=session.createSQLQuery(sqlQuery);
			@SuppressWarnings("unchecked")
			List<String> list =(List<String>) query.list();
			lenderNames.addAll(list);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return lenderNames;
	}
	
	@Override
	public Map<String, String> getAllMliNames() {
		List<String> mlinames = new ArrayList<String>();
		Map<String,String> map = new HashMap<String,String>();
		try {
			Session session = sessionFactory.openSession();
			String sqlQuery= "select concat(MEM_BNK_ID,MEM_ZNE_ID,MEM_BRN_ID)MLI_id, concat(MEM_BANK_NAME,'(',concat(MEM_BNK_ID,MEM_ZNE_ID,MEM_BRN_ID),')')MEM_BANK_NAME from nbfc_member_info where MEM_ZNE_ID='0000' order by MEM_BANK_NAME";
			Query query=session.createSQLQuery(sqlQuery);
			@SuppressWarnings("unchecked")
			List<Object[]> list =(List<Object[]>) query.list();
			for(Object[] row: list){
		         String id = (String) row[0];
		         String name = (String)row[1];
		        // Map<String, String> map1 = new HashMap<String,String>();
		         map.put(id, name);
		     }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}
	@Override
	public long getCount(String lenderName) {
		String value = null;
		long count = 0L;
		try {
			Session session = sessionFactory.openSession();
			String sqlQuery= "select count(distinct VENDOR_ID) from nbfc_street_vendor_master where LenderName='"+ lenderName +"' AND ISUPDATED=0";
			Query query=session.createSQLQuery(sqlQuery);
			@SuppressWarnings("unchecked")
			List<BigInteger> obj = query.list();
			for (BigInteger l : obj) {
                if (l != null) {
                    count = l.longValueExact();
                }
            }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	@Override
	public Map<String, String> getHoUser(String mliId) {
		Map<String,String> map = new HashMap<String,String>();
		try {
			Session session = sessionFactory.openSession();
			String sqlQuery= "SELECT USR_ID,concat(USR_FIRST_NAME,' ',USR_MIDDLE_NAME,' ',USR_LAST_NAME)UserName FROM nbfc_user_info where concat(MEM_BNK_ID,MEM_ZNE_ID,MEM_BRN_ID) ='"+ mliId +"'";
			Query query=session.createSQLQuery(sqlQuery);
			@SuppressWarnings("unchecked")
			List<Object[]> list =(List<Object[]>) query.list();
			for(Object[] row: list){
		         String id = (String) row[0];
		         String name = (String)row[1];
		         map.put(id, name);
		     }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}
	@Override
	public void updateData(String lenderName,String user) {
		
		try {
			Session session4 = sessionFactory.openSession();
			/* Transaction tn = session4.beginTransaction(); */
			Connection conn = session4.connection();
			CallableStatement cs = conn
					.prepareCall("{call Update_LaderData_in_master(?,?,?)}");
			//cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(1, lenderName);
			cs.setString(2, user);			
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.execute();
			String pouterror = cs.getString(3);
	}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Map<Integer, String> getDistrictDetails(String state) 
	{
		Map<Integer,String> map = new HashMap<Integer,String>();
		int count = 0;
		try
		{
			Session session = sessionFactory.openSession();
			String sqlQuery= "select distinct(upper(DISTRICT)) from nbfc_street_vendor_master where UPPER(state)='"+ state +"'";
			Query query=session.createSQLQuery(sqlQuery);
			@SuppressWarnings("unchecked")
			List<String> list = query.list();
			for(String row: list)
			{
		         map.put(count, list.get(count));
		         count++;
		    }
			System.out.println("Distinct District in Master Table is ::::" + map.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	@Override
	public List<Map<String, Object>> getStateActivityDetails(Date fromDate, Date toDate, String state, String district,
			String activity) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try
		{
			System.out.println("Inside method getStateActivityDetails!!! ");
			Session session = sessionFactory.openSession();
			Connection conn = session.connection();
			CallableStatement cs = conn
					.prepareCall("{ call GetStateActivity_Data(?,?,?,?,?,?)}");
			cs.setDate(1, new java.sql.Date(fromDate.getTime()));
			cs.setDate(2, new java.sql.Date(toDate.getTime()));
			cs.setString(3, state);
			cs.setString(4, district);
			cs.setString(5, activity);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			String pouterror = cs.getString(6);
			if (pouterror != null) {
				throw new CustomExceptionHandler("Exception occured  :"
						+ pouterror);
			} else 
			{
				resultset = (ResultSet) cs.getResultSet();
				resultSetMetaData = resultset.getMetaData();
				int coulmnCount = resultSetMetaData.getColumnCount();
				while (resultset.next()) 
				{
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= coulmnCount; i++) {
						columns.put(resultSetMetaData.getColumnLabel(i),resultset.getObject(i));
					}
					rows.add(columns);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return rows;
	}	
}
