package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import oracle.jdbc.OracleTypes;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.bean.DanCorrespondingDataListBeanCheckerBean;
import com.nbfc.model.DanCorrespondigDataModel;
import com.nbfc.model.DanCorressDataToRPNumberCheckerModel;
import com.nbfc.model.DanDisbursedDataModel;
import com.nbfc.model.DanGenerateRpNumberForPaymentCheckerModel;
import com.nbfc.model.DanPaymentInitiateASFModel;
import com.nbfc.model.DanPaymentInitiateModel;
import com.nbfc.model.DisburseNonDisburseModel;
import com.nbfc.model.MLIRegistration;
import com.raistudies.domain.CustomExceptionHandler;

@Repository("danGenerateRpNumberForPaymentCheckerDao")
public class DanGenerateRpNumberForPaymentCheckerDaoImpl implements
		DanGenerateRpNumberForPaymentCheckerDao {
	@Autowired
	SessionFactory sessionFactory;

	ResultSet resultset = null;
	ResultSetMetaData resultSetMetaData = null;
	ArrayList<String> coulmNameList = new ArrayList<String>();
	ArrayList<String> nestData = new ArrayList<String>();
	ArrayList<List<String>> claimSettlePaymentReportData = new ArrayList<List<String>>();
	Map<List, List> valueMap;

	
	public List<DanGenerateRpNumberForPaymentCheckerModel> getDanGenetateDataForAppoval(
			List<String> mliLName) {
		// TODO Auto-generated method stub
		return (List<DanGenerateRpNumberForPaymentCheckerModel>) sessionFactory
				.getCurrentSession()
				.createCriteria(DanGenerateRpNumberForPaymentCheckerModel.class)
				.add(Restrictions.eq("status", "NMDA")).list();

	}

	
	public int getDanRpApproval(List<String> list1) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int result = 0;

		try {
			tx = session.beginTransaction();
			if (list1.size() > 0 && list1 != null) {
				for (String danId : list1) {
					Query query = sessionFactory
							.getCurrentSession()
							.createQuery(
									"Update DanAllocationCheckerApproval set statusValue=:statusValue where payId = :danId");
					query.setParameter("statusValue", "DNCA");
					query.setParameter("danId", danId);
					result = query.executeUpdate();

				}
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}
		return result;
	}

	
	public List<DanPaymentInitiateModel> getApprovedDanDataForPayment(
			String mliId) {
		// TODO Auto-generated method stub
		return (List<DanPaymentInitiateModel>) sessionFactory
				.getCurrentSession()
				.createCriteria(DanPaymentInitiateModel.class)
				.add(Restrictions.eq("mliId", mliId))
				.add(Restrictions.eq("danStatus", "DNCA"))
				.add(Restrictions.eq("status", "N")).list();

	}

	
	public int getDanRpRejection(List<String> list1, String remark) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int result = 0;

		try {
			tx = session.beginTransaction();
			if (list1.size() > 0 && (remark != null || !remark.equals(""))) {
				for (String payId : list1) {
					Query query = sessionFactory
							.getCurrentSession()
							.createQuery(
									"Update DanAllocationCheckerApproval set statusValue=:statusValue,remark=:remark where payId = :payId");

					query.setParameter("statusValue", "DCCA");
					query.setParameter("payId", payId);
					query.setParameter("remark", remark);

					result = query.executeUpdate();

				}
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	
	public List<DanCorressDataToRPNumberCheckerModel> getDanCorrespondingData(
			String rpNumber, String danId) {
		return (List<DanCorressDataToRPNumberCheckerModel>) sessionFactory
				.getCurrentSession()
				.createCriteria(DanCorressDataToRPNumberCheckerModel.class)
				.add(Restrictions.eq("rpNumber", rpNumber)).list();
	}

	
	public List<DanCorrespondingDataListBeanCheckerBean> getDisburseNonDisburseData(
			String fileid, String danId, String disStatus) {
		List<DisburseNonDisburseModel> disburseNonDisbList = null;
		List<DanDisbursedDataModel> disburseNonDisbList1 = null;

		List<DanCorrespondingDataListBeanCheckerBean> list1 = new ArrayList<DanCorrespondingDataListBeanCheckerBean>();

		// TODO Auto-generated method stub
		if (!fileid.isEmpty() && !disStatus.isEmpty()) {
			if (disStatus.equals("N")) {
				disburseNonDisbList = (List<DisburseNonDisburseModel>) sessionFactory
						.getCurrentSession()
						.createCriteria(DisburseNonDisburseModel.class)
						.add(Restrictions.eq("fileName", fileid))
						.add(Restrictions.eq("disStatus", "N")).list();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

				// List<DanCorrespondingDataListBeanCheckerBean> list = new
				// ArrayList<DanCorrespondingDataListBeanCheckerBean>();
				if (disburseNonDisbList.size() > 0) {
					DanCorrespondingDataListBeanCheckerBean dgbean = null;
					for (DisburseNonDisburseModel dm : disburseNonDisbList) {
						dgbean = new DanCorrespondingDataListBeanCheckerBean();
						dgbean.setPortfolioName(dm.getPortfolioName());
						dgbean.setBorrowerName(dm.getLoanAccNo());
						dgbean.setSanctionAmount(dm.getSanctionAmount());
						if (dm.getSanctionDate() != null) {
							dgbean.setSanctionDate(df.format(dm
									.getSanctionDate()));
						} else {
							dgbean.setSanctionDate("");
						}

						list1.add(dgbean);
					}
				}
			} else {

				// disburseNonDisbList1 = (List<DanDisbursedDataModel>)
				// sessionFactory.getCurrentSession().createCriteria(DanDisbursedDataModel.class).add(Restrictions.eq("fileName",
				// fileid)).list();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

				try {
					Query queryObject = sessionFactory
							.getCurrentSession()
							.createQuery(
									"from DanDisbursedDataModel  iu where iu.fileName=:fileid ");

					queryObject.setParameter("fileid", fileid);

					disburseNonDisbList1 = queryObject.list();

					// List<DanCorrespondingDataListBeanCheckerBean> list = new
					// ArrayList<DanCorrespondingDataListBeanCheckerBean>();
					if (disburseNonDisbList1.size() > 0) {
						DanCorrespondingDataListBeanCheckerBean dgbean = null;
						for (DanDisbursedDataModel dm : disburseNonDisbList1) {
							dgbean = new DanCorrespondingDataListBeanCheckerBean();
							dgbean.setPortfolioName(dm.getPortfolioName());
							dgbean.setBorrowerName(dm.getLoanAccountNo());
							dgbean.setSanctionAmount(dm.getSanction_Amount());
							dgbean.setOutstandingAmount(dm
									.getOutstandingAmount());

							dgbean.setBaseAmount(dm.getBaseAmount());
							dgbean.setPortfolioRate(dm.getPortfolioRate());
							dgbean.setDanAmount(dm.getDanAmount());
							dgbean.setCgpan(dm.getCgpan());

							if (dm.getSanctionDate() != null) {
								dgbean.setSanctionDate(df.format(dm
										.getSanctionDate()));
							} else {
								dgbean.setSanctionDate("");
							}

							// if (sanctionAmount != null && igstRate != null) {
							// double igstAmount = ((igstRate / 100) *
							// sanctionAmount);
							dgbean.setIgstAmount(String.valueOf(dm
									.getIgstAmount()));
							// }

							dgbean.setIgstRate(String.valueOf(dm.getIgstRate()));

							// if (sanctionAmount != null && CgstRate != null) {
							// double cgstAmount = ((CgstRate / 100) *
							// sanctionAmount);
							dgbean.setCgstAmount(String.valueOf(dm
									.getCgstAmount()));
							// }
							// pd.setCgstAmount((String)row[6]);
							dgbean.setCgstRate(String.valueOf(dm.getCgstRate()));
							// if (sanctionAmount != null && SgstRate != null) {
							// double sgstAmount = ((SgstRate / 100) *
							// sanctionAmount);
							dgbean.setSgstAmount(String.valueOf(dm
									.getSgstAmount()));
							// }
							// pd.setSgstAmount((String)row[8]);
							dgbean.setSgstRate(String.valueOf(dm.getSgstRate()));

							list1.add(dgbean);

						}
					}
				} catch (HibernateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// list =(List<DisburseNonDisburseModel>)
				// sessionFactory.getCurrentSession().createCriteria(DisburseNonDisburseModel.class).add(Restrictions.eq("fileName",
				// fileid)).add(Restrictions.eq("disStatus", "Y")).list();

				/*
				 * try { List<?> list = null; DateFormat df = new
				 * SimpleDateFormat("dd/MM/yyyy");
				 * 
				 * // Query queryObject = //
				 * sessionFactory.getCurrentSession().createQuery(
				 * "select iu.portfolioNo,iu.loanAccountNo,iu.sanctionAmount,iu.sanctionDate,pm.igstAmount,pm.igstRate,pm.cgstAmount,pm.cgstRate,pm.sgstAmount,pm.sgstRate from DisburseNonDisburseModel as  iu  join iu.danGenerateRpNumberForPaymentCheckerModel as  pm  where iu.fileName=:fileid and iu.disStatus=:disStatus and pm.danId=:danId "
				 * ); Query queryObject = sessionFactory .getCurrentSession()
				 * .createQuery(
				 * "select iu.portfolioName,iu.loanAccountNo,iu.sanctionAmount,iu.sanctionDate,pm.igstAmount,pm.igstRate,pm.cgstAmount,pm.cgstRate,pm.sgstAmount,pm.sgstRate from DisburseNonDisburseModel  iu ,DanGenerateRpNumberForPaymentCheckerModel   pm  where iu.fileName=:fileid and iu.disStatus=:disStatus and pm.danId=:danId "
				 * );
				 * 
				 * queryObject.setParameter("fileid", fileid);
				 * queryObject.setParameter("disStatus", disStatus);
				 * queryObject.setParameter("danId", danId);
				 * 
				 * list = queryObject.list();
				 * DanCorrespondingDataListBeanCheckerBean pd = null; if
				 * (list.size() > 0) { for (int i = 0; i < list.size(); i++) {
				 * // SanctionCheckerBean cb=new SanctionCheckerBean(); Object[]
				 * row = (Object[]) list.get(i); pd = new
				 * DanCorrespondingDataListBeanCheckerBean();
				 * pd.setPortfolioName((String) row[0]);
				 * pd.setBorrowerName((String) row[1]);
				 * pd.setSanctionAmount((String) row[2]);
				 * 
				 * Double sanctionAmount = Double .parseDouble((String) row[2]);
				 * Double igstRate = Double .parseDouble((String) row[5]);
				 * Double CgstRate = Double .parseDouble((String) row[7]);
				 * Double SgstRate = Double .parseDouble((String) row[9]);
				 * pd.setSanctionDate(df.format((Date) row[3])); if
				 * (sanctionAmount != null && igstRate != null) { double
				 * igstAmount = ((igstRate / 100) * sanctionAmount);
				 * pd.setIgstAmount(String.valueOf(igstAmount)); }
				 * 
				 * pd.setIgstRate((String) row[5]);
				 * 
				 * if (sanctionAmount != null && CgstRate != null) { double
				 * cgstAmount = ((CgstRate / 100) * sanctionAmount);
				 * pd.setCgstAmount(String.valueOf(cgstAmount)); } //
				 * pd.setCgstAmount((String)row[6]); pd.setCgstRate((String)
				 * row[7]); if (sanctionAmount != null && SgstRate != null) {
				 * double sgstAmount = ((SgstRate / 100) * sanctionAmount);
				 * pd.setSgstAmount(String.valueOf(sgstAmount)); } //
				 * pd.setSgstAmount((String)row[8]); pd.setSgstRate((String)
				 * row[9]);
				 * 
				 * list1.add(pd); } } } catch (HibernateException e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); }
				 */
			}

		}
		return list1;
	}

	
	public int saveDataForPaymentInitiation(
			List<DanGenerateRpNumberForPaymentCheckerModel> initiatedData) {

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();

		Connection conn = session.connection();
		int status = 0;

		// System.out.println(dd.getDanId()+" "+dd.getAmount()+" "+dd.getPortfolioRate());
		try { // Double amount=Double.parseDouble(dd.getAmount());
			CallableStatement call;

			for (DanGenerateRpNumberForPaymentCheckerModel dd : initiatedData) {
				call = (CallableStatement) conn
						.prepareCall("{  call PACK_ONLINE_PAYMENT_DETAIL.PROC_XML_GENRATE(?,?) }");
				// call.registerOutParameter( 1, Types.INTEGER ); // or whatever
				// it is

				call.setString(1, dd.getRpNumber());

				call.registerOutParameter(2, Types.VARCHAR);
				call.execute();
				// int result = call.getInt(1); // propagate this back to
				// enclosing class
				// String payid = call.getString(4);
				String pouterror = call.getString(2);

				if (pouterror != null) {
					throw new CustomExceptionHandler("Exception Occurred :"
							+ pouterror);
				} else {
					status = 1;
				}

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
				conn.close();
				session.close();
				sessionFactory.close();
			} catch (Exception e2) {

			}

		}
		return status;
	}

	
	public List<DanCorrespondigDataModel> getApprovedDanData(String mliId) {
		String status = "DNMA";
		List<DanCorrespondigDataModel> danList = sessionFactory
				.getCurrentSession()
				.createCriteria(DanCorrespondigDataModel.class)
				.add(Restrictions.eq("status", status))
				.add(Restrictions.eq("mliId", mliId)).list();
		return danList;
	}

	/*
	 *  public List<DanCorrespondigDataModel> getDanId(String mliId) {
	 * // TODO Auto-generated method stub List<DanCorrespondigDataModel>
	 * danList=sessionFactory.getCurrentSession()
	 * .createCriteria(DanCorrespondigDataModel.class)
	 * .add(Restrictions.eq("mliId", mliId)).list(); return danList; }
	 */
	
	public int getDanCheckerApprovalRejection(
			List<DanGenerateRpNumberForPaymentCheckerModel> initiatedData,
			String remark) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int result = 0;

		try {
			tx = session.beginTransaction();
			if (initiatedData.size() > 0
					&& (remark != null || !remark.equals(""))) {
				for (DanGenerateRpNumberForPaymentCheckerModel dn : initiatedData) {
					/*
					 * Query query = sessionFactory .getCurrentSession()
					 * .createQuery(
					 * "Update DanGenerateRpNumberForPaymentCheckerModel set status=:statusValue,remark=:remark where danId = :danId"
					 * );
					 * 
					 * query.setParameter("statusValue", "DCCA");
					 * query.setParameter("danId", dn.getDanId());
					 * query.setParameter("remark", remark);
					 * 
					 * result = query.executeUpdate();
					 */

					Query query = sessionFactory
							.getCurrentSession()
							.createQuery(
									"Update DanAllocationCheckerApproval set statusValue=:statusValue,remark=:remark where payId = :payId");

					query.setParameter("statusValue", "DNMA");
					query.setParameter("payId", dn.getRpNumber());
					query.setParameter("remark", remark);

					result = query.executeUpdate();

				}
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	
	public List<DanCorrespondigDataModel> getDanId(String mliId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<DanPaymentInitiateModel> getPaymentInitiatedData(
			List<DanGenerateRpNumberForPaymentCheckerModel> list, String mliId) {
		// TODO Auto-generated method stub
		List<String> listData = new ArrayList<String>();
		for (DanGenerateRpNumberForPaymentCheckerModel dn : list) {
			listData.add(dn.getRpNumber());
		}
		return (List<DanPaymentInitiateModel>) sessionFactory
				.getCurrentSession()
				.createCriteria(DanPaymentInitiateModel.class)
				.add(Restrictions.eq("mliId", mliId))
				.add(Restrictions.eq("danStatus", "DNCA"))
				.add(Restrictions.eq("status", "I"))
				.add(Restrictions.in("rpNumber", listData)).list();

	}
	public List<DanPaymentInitiateASFModel> getPaymentInitiatedDataASF(
			List<DanGenerateRpNumberForPaymentCheckerModel> list, String mliId) {
		// TODO Auto-generated method stub
		List<String> listData = new ArrayList<String>();
		for (DanGenerateRpNumberForPaymentCheckerModel dn : list) {
			listData.add(dn.getRpNumber());
		}
		return (List<DanPaymentInitiateASFModel>) sessionFactory
				.getCurrentSession()
				.createCriteria(DanPaymentInitiateASFModel.class)
				.add(Restrictions.eq("mliId", mliId))
				.add(Restrictions.eq("danStatus", "DNCA"))
				.add(Restrictions.eq("status", "I"))
				.add(Restrictions.in("rpNumber", listData)).list();

	}


	
	public List<DanCorrespondigDataModel> getUserReportData(String mliId) {
		String status = "DNMA";
		ClassMetadata classMetadata = sessionFactory
				.getClassMetadata(DanCorrespondigDataModel.class);

		List<DanCorrespondigDataModel> danList = sessionFactory
				.getCurrentSession()
				.createCriteria(DanCorrespondigDataModel.class)
				.add(Restrictions.eq("status", status))
				.add(Restrictions.eq("mliId", mliId)).list();

		String[] propertyNames = classMetadata.getPropertyNames();
		System.out.println("value..." + propertyNames);

		return danList;
	}

	
	/*public List<Map<String, Object>> createReport(String userId) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		*/
	public List<Map<String, Object>> createReport(String userId) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try {
			Session session4 = sessionFactory.openSession();
			/* Transaction tn = session4.beginTransaction(); */
			Connection conn = session4.connection();
			CallableStatement cs = conn
					.prepareCall("{? = call NBFC_PACKREPORT.Fun_get_MLIFileUploaded_Data(?,?,?)}");

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
			} else {
				// Procedure execution

				resultset = (ResultSet) cs.getObject(3);
				resultSetMetaData = resultset.getMetaData();
				int coulmnCount = resultSetMetaData.getColumnCount();
				while (resultset.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= coulmnCount; i++) {
						//columns.put(resultSetMetaData.getColumnLabel(i),resultset.getObject(i));
						columns.put(resultSetMetaData.getColumnLabel(i),resultset.getString(i));
					}

					rows.add(columns);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;

	}

	
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

	
	public List<Map<String, Object>> createReportMK(String userId) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try {
			Session session4 = sessionFactory.openSession();
			/* Transaction tn = session4.beginTransaction(); */
			Connection conn = session4.connection();
			CallableStatement cs = conn
					.prepareCall("{? = call NBFC_PACKREPORT.FUN_CHECKER_FILE_DATA(?,?,?)}");

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
			} else {
				// Procedure execution

				resultset = (ResultSet) cs.getObject(3);
				resultSetMetaData = resultset.getMetaData();
				int coulmnCount = resultSetMetaData.getColumnCount();
				while (resultset.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= coulmnCount; i++) {
						//columns.put(resultSetMetaData.getColumnLabel(i),resultset.getObject(i));
						columns.put(resultSetMetaData.getColumnLabel(i),resultset.getString(i));
					}

					rows.add(columns);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;

	}
}
