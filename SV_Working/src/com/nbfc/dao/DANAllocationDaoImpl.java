package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.math.BigDecimal;
import com.nbfc.bean.DANAllocationBean;
import com.nbfc.bean.DanCorrespondingDataListBeanCheckerBean;
import com.nbfc.model.DANAllocatioListEntity;
import com.nbfc.model.DANAllocation;
import com.nbfc.model.DANAllocationFee;
import com.nbfc.model.DanAllClass;
import com.nbfc.model.DanAllocationForASFNbfcMakerUsingVWModel;
import com.nbfc.model.DanAllocationForNbfcMakerUsingVWModel;
import com.nbfc.model.DanCorrespondigDataASFModel;
import com.nbfc.model.DanCorrespondigDataModel;
import com.nbfc.model.DanDisbursedDataModel;
import com.nbfc.model.DanPaymentInitiateASFModel;
import com.nbfc.model.DanPaymentInitiateModel;
import com.nbfc.model.DisburseNonDisburseModel;
import com.nbfc.model.MLICGFeeDetails;
import com.nbfc.model.MLIMakerApprovalRejection;
import com.nbfc.model.MliMakerEntity;
import com.raistudies.domain.CustomExceptionHandler;

@Repository("DANAllocationDao")
public class DANAllocationDaoImpl implements DANAllocationDao {
	@Autowired
	private SessionFactory sessionFactory;

	
	public DANAllocation danListForAllocation(String danID, String status) {
		return (DANAllocation) sessionFactory.getCurrentSession()
				.createCriteria(DANAllocation.class)
				.add(Restrictions.eq("DAN_FSTATUS", status))
				.add(Restrictions.eq("DAN_ID", danID)).uniqueResult();

	}

	
	public List<MLICGFeeDetails> getPortFiliDetails(
			List<String> portfolioNameList) {

		/*
		 * List<MLICGFeeDetails> DliDetail = (List<MLICGFeeDetails>)
		 * sessionFactory .getCurrentSession()
		 * .createCriteria(MLICGFeeDetails.class)
		 * .add(Restrictions.eq("portfolio_Name", "SBI/2017-2018/3")).list();
		 */
		List<MLICGFeeDetails> asdfas = (List<MLICGFeeDetails>) sessionFactory
				.getCurrentSession().createCriteria(MLICGFeeDetails.class)
				.add(Restrictions.eq("STATUS", "CCA"))
				.add(Restrictions.in("portfolio_Name", portfolioNameList))
				.list();
		/* @SuppressWarnings("unchecked") */
		/*
		 * List<MLICGFeeDetails> asdfas=sessionFactory .getCurrentSession()
		 * .createCriteria(MLICGFeeDetails.class)
		 * .add(Restrictions.in("portfolio_Name", portfolioNameList))
		 * .setProjection( Projections .projectionList() .add(Projections
		 * .groupProperty("portfolio_Name"))
		 * .add(Projections.property("FILE_ID"))
		 * .add(Projections.groupProperty("DAN_ID"))).list();
		 */

		return asdfas;

		/* return DliDetail; */
	}

	
	public DANAllocationFee getCGAmoutDetails(String DanId, String status) {
		// TODO Auto-generated method stub
		return (DANAllocationFee) sessionFactory.getCurrentSession()
				.createCriteria(DANAllocationFee.class)
				.add(Restrictions.eq("APPROVAL_STATUS", status))
				.add(Restrictions.eq("DAN_ID", DanId)).uniqueResult();
	}

	
	public List<DANAllocation> danListDANAllocation(List<String> danID,
			String status) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession()
				.createCriteria(DANAllocation.class)
				.add(Restrictions.eq("DAN_FSTATUS", status))
				.add(Restrictions.in("DAN_ID", danID)).list();

	}

	
	public List<DANAllocationFee> getCGAmoutDetailsList(List<String> danId,
			String status) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		for (String danValue : danId) {
			list.add(danValue);
		}
		List<String> list1 = new ArrayList<String>();
		list1.add("NB1201201800004");

		List<DANAllocationFee> danList = sessionFactory.getCurrentSession()
				.createCriteria(DANAllocationFee.class)
				.add(Restrictions.eq("APPROVAL_STATUS", status))
				.add(Restrictions.in("DAN_ID", danId)).list();
		List<DANAllocationFee> sdfgsd = new ArrayList<DANAllocationFee>();

		return danList;
	}

	
	public boolean addDANAllocatioDetails(List<DANAllocationFee> danAllocatioFee)
			throws NullPointerException {
		for (Object mliEntity : danAllocatioFee) {

			sessionFactory.getCurrentSession().saveOrUpdate(mliEntity);
			/*
			 * Session session = sessionFactory.openSession();
			 * session.save(mliEntity); session.flush();
			 */}
		// System.out.println(mliMakerEntity.toString());
		// sessionFactory.getCurrentSession().save(mliMakerEntity);
		System.out.println("*************************************************");
		return true;
	}

	
	public List<DANAllocationFee> getApyIDAmoutDetailsList(List<String> DanId,
			String status) {
		List<DANAllocationFee> dfgsdf = null;
		// TODO Auto-generated method stub
		if (DanId != null || !DanId.isEmpty()) {
			dfgsdf = sessionFactory.getCurrentSession()
					.createCriteria(DANAllocationFee.class)
					.add(Restrictions.eq("APPROVAL_STATUS", status))
					.add(Restrictions.in("DAN_ID", DanId)).list();
		}

		return dfgsdf;
	}

	
	public List<DanAllocationForNbfcMakerUsingVWModel> getDataForDanAllocation(
			String mliId) {
		// TODO Auto-generated method stub
		return (List<DanAllocationForNbfcMakerUsingVWModel>) sessionFactory
				.getCurrentSession()
				.createCriteria(DanAllocationForNbfcMakerUsingVWModel.class)
				.add(Restrictions.eq("status", "DCCA"))
				.add(Restrictions.eq("mliId", mliId)).list();

	}

	
	public DANAllocationBean getRpNumberData(
			List<DanAllocationForNbfcMakerUsingVWModel> allocationList,String userId) {

		//String userId = "salmank6852";

		// for (DanAllocationForNbfcMakerUsingVWModel mliEntity :
		// allocationList) {

		// sessionFactory.getCurrentSession().saveOrUpdate(mliEntity);
		/***/
		DANAllocationBean danAllocation = new DANAllocationBean();
		Session session4 = sessionFactory.openSession();
		Transaction tn = session4.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		Connection conn = session4.connection();
		for (DanAllocationForNbfcMakerUsingVWModel dd : allocationList) {
			total += Double.parseDouble(dd.getAmount());
		}
		// System.out.println(dd.getDanId()+" "+dd.getAmount()+" "+dd.getPortfolioRate());
		try { // Double amount=Double.parseDouble(dd.getAmount());
			CallableStatement call;
			CallableStatement call1;
			CallableStatement call2;

			call = (CallableStatement) conn
					.prepareCall("{ ? = call FUNCINSERTPAYMENTDETAIL_ONLINE(?,?,?,?) }");
			call.registerOutParameter(1, Types.INTEGER); // or whatever it is

			call.setDouble(2, total);
			call.setString(3, "Y");
			call.registerOutParameter(4, Types.VARCHAR);
			call.registerOutParameter(5, Types.VARCHAR);
			call.execute();
			int result = call.getInt(1); // propagate this back to enclosing
											// class
			String payid = call.getString(4);
			String pouterror = call.getString(5);

			if (result != 0) {
				throw new CustomExceptionHandler("Exception occured  :"
						+ pouterror);
			}

			if (result == 0) {
				// CallableStatement call;
				if (payid != null) {
					for (DanAllocationForNbfcMakerUsingVWModel dd : allocationList) {
						call1 = (CallableStatement) conn
								.prepareCall("{ ? = call FUNCINSERTALLOCATIONDET_ONLINE(?,?,?,?,?,?,?,trunc(sysdate),?,?) }");
						call1.registerOutParameter(1, Types.INTEGER); // or
																		// whatever
																		// it is
						call1.setString(2, dd.getDanId());
						call1.setString(3, "CG123456");
						call1.setString(4, payid);
						call1.setInt(5, 456123);
						call1.setInt(6, 8795);
						call1.setString(7, "Y");
						call1.setString(8, userId);
						// call1.setDate(9,(java.sql.Date) new Date());
						call1.setString(9, "hello");
						// call.registerOutParameter(4,Types.VARCHAR);
						call1.registerOutParameter(10, Types.VARCHAR);
						call1.execute();
						result1 = call1.getInt(1);
					
						String pouterror1 = call1.getString(10);
						if (result1 != 0) {
							throw new CustomExceptionHandler(
									"Exception occured  :" + pouterror1);
						}

					}
				}
				String bankId = null;
				String zoneId = null;
				String branchId = null;
				for (DanAllocationForNbfcMakerUsingVWModel dd : allocationList) {
					String mliId = dd.getMliId();
					bankId = mliId.substring(0, 4);
					zoneId = mliId.substring(4, 8);
					branchId = mliId.substring(8, 12);
				}
				if (result1 == 0) {
					call2 = (CallableStatement) conn
							.prepareCall("{ call PACK_ONLINE_PAYMENT_DETAIL.ALLOCATION_FOR_NEFT_RTGS(?,?,?,?,?,?,trunc(sysdate),?,?,?) }");
					call2.setString(1, bankId); // or whatever it is
					call2.setString(2, zoneId);
					call2.setString(3, branchId);
					call2.setString(4, payid);
					String virtACNumber = "18888"
							+ payid.replaceAll("[^a-zA-Z0-9 ]", "")
									.substring(2);
					call2.setString(5, virtACNumber);
					call2.setDouble(6, total);
					// call1.setString(7,"Y");
					call2.setString(7, "N");
					// call1.setDate(9,(java.sql.Date) new Date());
					call2.setString(8, "U");
					// call.registerOutParameter(4,Types.VARCHAR);
					call2.registerOutParameter(9, Types.VARCHAR);
					call2.execute();
					// int result1 = call1.getInt(1);
					String pouterror1 = call2.getString(9);
					if (pouterror1 != null) {
						throw new CustomExceptionHandler("Error Occured  :"
								+ pouterror1);
					}
					if (payid != null && total != null && virtACNumber != null) {
						danAllocation.setTotalAmount(BigDecimal.valueOf(Double.valueOf(total)).toBigDecimal().toPlainString());
						danAllocation.setRpNumber(payid);
						danAllocation.setVirtualAccountNumber(virtACNumber);
					}

				}
			}
			tn.commit();
		} catch (SQLException e) {
			//if (tn != null)
				tn.rollback();
			e.printStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				session4.close();
				sessionFactory.close();
			} catch (Exception e2) {

			}
		}

		return danAllocation;
	}

	
	public List<MLICGFeeDetails> getDisbusCaseDetails(String fileId) {
		return (List<MLICGFeeDetails>) sessionFactory.getCurrentSession()
				.createCriteria(MLICGFeeDetails.class)
				.add(Restrictions.eq("FILE_ID", fileId))
				.add(Restrictions.eq("DISBURSEMENT_STATUS", "Y")).list();

	}

	
	public List<MLICGFeeDetails> getNonDisbusCaseDetails(String fileId) {
		// TODO Auto-generated method stub
		return (List<MLICGFeeDetails>) sessionFactory.getCurrentSession()
				.createCriteria(MLICGFeeDetails.class)
				.add(Restrictions.eq("FILE_ID", fileId))
				.add(Restrictions.eq("DISBURSEMENT_STATUS", "N")).list();
	}

	public List<DanCorrespondingDataListBeanCheckerBean> getDisburseNonDisburseData(
			String fileName, String disStatus) {
		List<DisburseNonDisburseModel> disburseNonDisbList = null;
		List<DanDisbursedDataModel> disburseNonDisbList1 = null;

		List<DanCorrespondingDataListBeanCheckerBean> list1 = new ArrayList<DanCorrespondingDataListBeanCheckerBean>();

		// TODO Auto-generated method stub
		if (!fileName.isEmpty() && !disStatus.isEmpty()) {
			if (disStatus.equals("N")) {
				disburseNonDisbList = (List<DisburseNonDisburseModel>) sessionFactory
						.getCurrentSession()
						.createCriteria(DisburseNonDisburseModel.class)
						.add(Restrictions.eq("fileName", fileName))
						.add(Restrictions.eq("disStatus", "N")).list();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

				//List<DanCorrespondingDataListBeanCheckerBean> list = new ArrayList<DanCorrespondingDataListBeanCheckerBean>();
				if (disburseNonDisbList.size() > 0) {
					DanCorrespondingDataListBeanCheckerBean dgbean = null;
					for (DisburseNonDisburseModel dm : disburseNonDisbList) {
						dgbean = new DanCorrespondingDataListBeanCheckerBean();
						dgbean.setPortfolioName(dm.getPortfolioName());
						dgbean.setLoanAccNo(dm.getLoanAccNo());
						dgbean.setSanctionAmount(dm.getSanctionAmount());
//						dgbean.setCgpan(dm.getCgpan());
//						dgbean.setDanId(dm.getDanId());
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
				

			//	disburseNonDisbList1 = (List<DanDisbursedDataModel>) sessionFactory.getCurrentSession().createCriteria(DanDisbursedDataModel.class).add(Restrictions.eq("fileName", fileid)).list();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

				try {
					Query queryObject = sessionFactory.getCurrentSession().createQuery("from DanDisbursedDataModel  iu where iu.fileName=:fileid ");

					queryObject.setParameter("fileid", fileName);


					disburseNonDisbList1 = queryObject.list();
					
					
					//List<DanCorrespondingDataListBeanCheckerBean> list = new ArrayList<DanCorrespondingDataListBeanCheckerBean>();
					if (disburseNonDisbList1.size() > 0) {
						DanCorrespondingDataListBeanCheckerBean dgbean = null;
						for (DanDisbursedDataModel dm : disburseNonDisbList1) {
							dgbean = new DanCorrespondingDataListBeanCheckerBean();
							dgbean.setPortfolioName(dm.getPortfolioName());
							dgbean.setLoanAccNo(dm.getLoanAccountNo());
							dgbean.setSanctionAmount(dm.getSanction_Amount());
							dgbean.setOutstandingAmount(dm.getOutstandingAmount());
							dgbean.setBaseAmount(dm.getBaseAmount());
							dgbean.setPortfolioRate(dm.getPortfolioRate());
							dgbean.setDanAmount(dm.getDanAmount());
							dgbean.setCgpan(dm.getCgpan());
							dgbean.setDanId(dm.getDan_id());
							if (dm.getSanctionDate() != null) {
								dgbean.setSanctionDate(df.format(dm
										.getSanctionDate()));
							} else {
								dgbean.setSanctionDate("");
							}

							
							
							
							
							//if (sanctionAmount != null && igstRate != null) {
							//	double igstAmount = ((igstRate / 100) * sanctionAmount);
							dgbean.setIgstAmount(String.valueOf(dm.getIgstAmount()));
							//}

							dgbean.setIgstRate(String.valueOf(dm.getIgstRate()));

							//if (sanctionAmount != null && CgstRate != null) {
								//double cgstAmount = ((CgstRate / 100) * sanctionAmount);
							dgbean.setCgstAmount(String.valueOf(dm.getCgstAmount()));
							//}
							// pd.setCgstAmount((String)row[6]);
							dgbean.setCgstRate(String.valueOf(dm.getCgstRate()));
							//if (sanctionAmount != null && SgstRate != null) {
								//double sgstAmount = ((SgstRate / 100) * sanctionAmount);
							dgbean.setSgstAmount(String.valueOf(dm.getSgstAmount()));
							//}
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

				/*try {
					List<?> list = null;
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

					// Query queryObject =
					// sessionFactory.getCurrentSession().createQuery("select iu.portfolioNo,iu.loanAccountNo,iu.sanctionAmount,iu.sanctionDate,pm.igstAmount,pm.igstRate,pm.cgstAmount,pm.cgstRate,pm.sgstAmount,pm.sgstRate from DisburseNonDisburseModel as  iu  join iu.danGenerateRpNumberForPaymentCheckerModel as  pm  where iu.fileName=:fileid and iu.disStatus=:disStatus and pm.danId=:danId ");
					Query queryObject = sessionFactory
							.getCurrentSession()
							.createQuery(
									"select iu.portfolioName,iu.loanAccountNo,iu.sanctionAmount,iu.sanctionDate,pm.igstAmount,pm.igstRate,pm.cgstAmount,pm.cgstRate,pm.sgstAmount,pm.sgstRate from DisburseNonDisburseModel  iu ,DanGenerateRpNumberForPaymentCheckerModel   pm  where iu.fileName=:fileid and iu.disStatus=:disStatus and pm.danId=:danId ");

					queryObject.setParameter("fileid", fileid);
					queryObject.setParameter("disStatus", disStatus);
					queryObject.setParameter("danId", danId);

					list = queryObject.list();
					DanCorrespondingDataListBeanCheckerBean pd = null;
					if (list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							// SanctionCheckerBean cb=new SanctionCheckerBean();
							Object[] row = (Object[]) list.get(i);
							pd = new DanCorrespondingDataListBeanCheckerBean();
							pd.setPortfolioName((String) row[0]);
							pd.setBorrowerName((String) row[1]);
							pd.setSanctionAmount((String) row[2]);

							Double sanctionAmount = Double
									.parseDouble((String) row[2]);
							Double igstRate = Double
									.parseDouble((String) row[5]);
							Double CgstRate = Double
									.parseDouble((String) row[7]);
							Double SgstRate = Double
									.parseDouble((String) row[9]);
							pd.setSanctionDate(df.format((Date) row[3]));
							if (sanctionAmount != null && igstRate != null) {
								double igstAmount = ((igstRate / 100) * sanctionAmount);
								pd.setIgstAmount(String.valueOf(igstAmount));
							}

							pd.setIgstRate((String) row[5]);

							if (sanctionAmount != null && CgstRate != null) {
								double cgstAmount = ((CgstRate / 100) * sanctionAmount);
								pd.setCgstAmount(String.valueOf(cgstAmount));
							}
							// pd.setCgstAmount((String)row[6]);
							pd.setCgstRate((String) row[7]);
							if (sanctionAmount != null && SgstRate != null) {
								double sgstAmount = ((SgstRate / 100) * sanctionAmount);
								pd.setSgstAmount(String.valueOf(sgstAmount));
							}
							// pd.setSgstAmount((String)row[8]);
							pd.setSgstRate((String) row[9]);

							list1.add(pd);
						}
					}
				} catch (HibernateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
*/
			}

		}
		return list1;
	}


	@Override
	public List<DanAllocationForASFNbfcMakerUsingVWModel> getDataForDanAllocationASF(String mliId) {
		// TODO Auto-generated method stub
		return (List<DanAllocationForASFNbfcMakerUsingVWModel>) sessionFactory
				.getCurrentSession()
				.createCriteria(DanAllocationForASFNbfcMakerUsingVWModel.class)
				.add(Restrictions.eq("status", "DCCA"))
				.add(Restrictions.eq("mliId", mliId)).list();
	}
	
	public DANAllocationBean getRpNumberDataASF(
			List<DanAllocationForASFNbfcMakerUsingVWModel> allocationList,String userId) {

		//String userId = "salmank6852";

		// for (DanAllocationForNbfcMakerUsingVWModel mliEntity :
		// allocationList) {

		// sessionFactory.getCurrentSession().saveOrUpdate(mliEntity);
		/***/
		DANAllocationBean danAllocation = new DANAllocationBean();
		Session session4 = sessionFactory.openSession();
		Transaction tn = session4.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		Connection conn = session4.connection();
		for (DanAllocationForASFNbfcMakerUsingVWModel dd : allocationList) {
			total += Double.parseDouble(dd.getAmount());
		}
		// System.out.println(dd.getDanId()+" "+dd.getAmount()+" "+dd.getPortfolioRate());
		try { // Double amount=Double.parseDouble(dd.getAmount());
			CallableStatement call;
			CallableStatement call1;
			CallableStatement call2;

			call = (CallableStatement) conn
					.prepareCall("{ ? = call FUNCINSERTPAYMENTDETAIL_ONLINE(?,?,?,?) }");
			call.registerOutParameter(1, Types.INTEGER); // or whatever it is

			call.setDouble(2, total);
			call.setString(3, "Y");
			call.registerOutParameter(4, Types.VARCHAR);
			call.registerOutParameter(5, Types.VARCHAR);
			call.execute();
			int result = call.getInt(1); // propagate this back to enclosing
											// class
			String payid = call.getString(4);
			String pouterror = call.getString(5);

			if (result != 0) {
				throw new CustomExceptionHandler("Exception occured  :"
						+ pouterror);
			}

			if (result == 0) {
				// CallableStatement call;
				if (payid != null) {
					for (DanAllocationForASFNbfcMakerUsingVWModel dd : allocationList) {
						call1 = (CallableStatement) conn
								.prepareCall("{ ? = call FUNCINSERTALLOCATINASF_ONLINE(?,?,?,?,?,?,?,trunc(sysdate),?,?) }");
						call1.registerOutParameter(1, Types.INTEGER); // or
																		// whatever
																		// it is
						call1.setString(2, dd.getDanId());
						call1.setString(3, "CG123456");
						call1.setString(4, payid);
						call1.setInt(5, 456123);
						call1.setInt(6, 8795);
						call1.setString(7, "Y");
						call1.setString(8, userId);
						// call1.setDate(9,(java.sql.Date) new Date());
						call1.setString(9, "hello");
						// call.registerOutParameter(4,Types.VARCHAR);
						call1.registerOutParameter(10, Types.VARCHAR);
						call1.execute();
						result1 = call1.getInt(1);
					
						String pouterror1 = call1.getString(10);
						if (result1 != 0) {
							throw new CustomExceptionHandler(
									"Exception occured  :" + pouterror1);
						}

					}
				}
				String bankId = null;
				String zoneId = null;
				String branchId = null;
				for (DanAllocationForASFNbfcMakerUsingVWModel dd : allocationList) {
					String mliId = dd.getMliId();
					bankId = mliId.substring(0, 4);
					zoneId = mliId.substring(4, 8);
					branchId = mliId.substring(8, 12);
				}
				if (result1 == 0) {
					call2 = (CallableStatement) conn
							.prepareCall("{ call PACK_ONLINE_PAYMENT_DETAIL.ALLOCATION_FOR_NEFT_RTGS(?,?,?,?,?,?,trunc(sysdate),?,?,?) }");
					call2.setString(1, bankId); // or whatever it is
					call2.setString(2, zoneId);
					call2.setString(3, branchId);
					call2.setString(4, payid);
					String virtACNumber = "18888"
							+ payid.replaceAll("[^a-zA-Z0-9 ]", "")
									.substring(2);
					call2.setString(5, virtACNumber);
					call2.setDouble(6, total);
					// call1.setString(7,"Y");
					call2.setString(7, "N");
					// call1.setDate(9,(java.sql.Date) new Date());
					call2.setString(8, "U");
					// call.registerOutParameter(4,Types.VARCHAR);
					call2.registerOutParameter(9, Types.VARCHAR);
					call2.execute();
					// int result1 = call1.getInt(1);
					String pouterror1 = call2.getString(9);
					if (pouterror1 != null) {
						throw new CustomExceptionHandler("Error Occured  :"
								+ pouterror1);
					}
					if (payid != null && total != null && virtACNumber != null) {
						danAllocation.setTotalAmount(BigDecimal.valueOf(Double.valueOf(total)).toBigDecimal().toPlainString());
						danAllocation.setRpNumber(payid);
						danAllocation.setVirtualAccountNumber(virtACNumber);
					}

				}
			}
			tn.commit();
		} catch (SQLException e) {
			//if (tn != null)
				tn.rollback();
			e.printStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				session4.close();
				sessionFactory.close();
			} catch (Exception e2) {

			}
		}

		return danAllocation;
	}


	@Override
	public List<DanPaymentInitiateASFModel> getApprovedDanDataForPaymentASF(String mliId) {
		return (List<DanPaymentInitiateASFModel>) sessionFactory
				.getCurrentSession()
				.createCriteria(DanPaymentInitiateASFModel.class)
				.add(Restrictions.eq("mliId", mliId))
				.add(Restrictions.eq("danStatus", "DNCA"))
				.add(Restrictions.eq("status", "N")).list();
	}


	
}
