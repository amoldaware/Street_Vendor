package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.model.MliMakerEntity;
import com.nbfc.model.PortfolioAmountCount;
import com.nbfc.model.PortfolioNumInfo;
import com.nbfc.model.PortfolioNumber;
import com.nbfc.model.PortfolioNumberInfo;
import com.raistudies.domain.CustomExceptionHandler;

/**
 * @author Saurav Tyagi 2017
 * 
 */

@Repository("PortfolioInfoDao")
public class PortfolioInfoDaoImpl implements PortfolioInfoDao {

	@Autowired
	private SessionFactory sessionFactory;

	Map<String, String> mapObj = new HashMap<String, String>();

	
	public Map<String, String> portfolioAppRefNumber(String baseYr,
			String mliName) {

		List<PortfolioNumInfo> PortfolioNumList = sessionFactory
				.getCurrentSession()
				.createCriteria(PortfolioNumInfo.class, baseYr)
				.add(Restrictions.eq("portfolio_base_yer", baseYr))
				.add(Restrictions.eq("EXPOSURE_ID", mliName)).list();
		for (PortfolioNumInfo portfolioNumInfo : PortfolioNumList) {
			mapObj.put(portfolioNumInfo.getPortfolio_Number(),
					portfolioNumInfo.getPortfolio_name());
		}
		return mapObj;
	}

	
	public PortfolioNumInfo PortfolioInfo(String portfolioNum) {
		PortfolioNumInfo DliDetail = (PortfolioNumInfo) sessionFactory
				.getCurrentSession().get(PortfolioNumInfo.class, portfolioNum);
		// System.out.println("DliDetail : "+DliDetail.getPortfolio_start_date());
		return DliDetail;
	}

	
	public boolean addMILDetails(List<MliMakerEntity> mliMakerEntity) {

		// Connection conn = session4.connection();
		int counter = 1;
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		
		Integer i = null;
		try {
			for (Object mliEntity : mliMakerEntity) {
				System.out.println("Counter :" + counter++);
				i = (Integer) session.save(mliEntity);
				/*
				 * Session session = sessionFactory.openSession();
				 * session.save(mliEntity); session.flush();
				 */}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			tn.commit();
			session.close();
			
		}
	
		
		// ########################## Updating verify
		// status##################################################
		/*
		 * if (i.intValue() > 0) { Session session2 =
		 * sessionFactory.openSession(); Transaction tn1 =
		 * session2.beginTransaction();
		 * 
		 * Connection conn = session2.connection();
		 * 
		 * try {
		 * 
		 * CallableStatement call2;
		 * 
		 * call2 = (CallableStatement) conn
		 * .prepareCall("{ call UPDATE_VERIFY_STATUS(?) }");
		 * call2.registerOutParameter(1, Types.VARCHAR); call2.execute(); String
		 * pouterror1 = call2.getString(1); if (pouterror1 != null) { throw new
		 * CustomExceptionHandler("Error Occured  :" + pouterror1); }
		 * 
		 * tn1.commit(); } catch (SQLException e) { if (tn1 != null)
		 * tn1.rollback(); e.printStackTrace(); // TODO Auto-generated catch
		 * block e.printStackTrace(); } finally { try { conn.close(); //
		 * session.close(); session2.close(); sessionFactory.close(); } catch
		 * (Exception e2) {
		 * 
		 * } } }
		 */
		// ############################################################################
		System.out
				.println("******************Update Verify proc successfully executed******************************");
		// System.out.println(mliMakerEntity.toString());
		// sessionFactory.getCurrentSession().save(mliMakerEntity);
		System.out.println("*************************************************");
		return true;
	}

	/*
	 *  public Map<String, String> portfoloQuarterNumber(String
	 * protfolioNumber) {
	 * 
	 * List<PortfolioNumber> PortfolioNumList =
	 * sessionFactory.getCurrentSession() .createCriteria(PortfolioNumber.class,
	 * protfolioNumber) .add(Restrictions.eq("portfolioNo",
	 * protfolioNumber)).add(Restrictions.le("subPortfolioSerialNO", 4
	 * )).list(); for (PortfolioNumber portfolioNumInfo : PortfolioNumList) {
	 * mapObj.put(portfolioNumInfo.getSub_portfolio_dt_no(),
	 * portfolioNumInfo.getPortfolioQuarter());
	 * System.out.println("********************************"
	 * +portfolioNumInfo.getSub_portfolio_dt_no
	 * ()+" "+portfolioNumInfo.getPortfolioNo()); } return mapObj; }
	 */

	
	public Map<String, String> portfoloQuarterNumber(String protfolioNumber,
			List<Integer> qNumberlist) {
		int firstValue = 0;
		int secondValue = 0;

		if (qNumberlist.size() == 1) {
			firstValue = qNumberlist.get(0);

			List<PortfolioNumber> PortfolioNumList = sessionFactory
					.getCurrentSession()
					.createCriteria(PortfolioNumber.class, protfolioNumber)
					.add(Restrictions.eq("portfolioNo", protfolioNumber))
					.add(Restrictions.eq("subPortfolioSerialNO", firstValue))
					.list();
			for (PortfolioNumber portfolioNumInfo : PortfolioNumList) {
				mapObj.put(portfolioNumInfo.getSub_portfolio_dt_no(),
						portfolioNumInfo.getPortfolioQuarter());
				/*
				 * System.out.println("********************************" +
				 * portfolioNumInfo.getSub_portfolio_dt_no() + " " +
				 * portfolioNumInfo.getPortfolioNo());
				 */
			}

		} else {
			firstValue = qNumberlist.get(0);
			secondValue = qNumberlist.get(1);

			List<PortfolioNumber> PortfolioNumList = sessionFactory
					.getCurrentSession()
					.createCriteria(PortfolioNumber.class, protfolioNumber)
					.add(Restrictions.eq("portfolioNo", protfolioNumber))
					.add(Restrictions.or(Restrictions.like(
							"subPortfolioSerialNO", firstValue), Restrictions
							.like("subPortfolioSerialNO", secondValue))).list();
			for (PortfolioNumber portfolioNumInfo : PortfolioNumList) {
				mapObj.put(portfolioNumInfo.getSub_portfolio_dt_no(),
						portfolioNumInfo.getPortfolioQuarter());
				/*
				 * System.out.println("********************************" +
				 * portfolioNumInfo.getSub_portfolio_dt_no() + " " +
				 * portfolioNumInfo.getPortfolioNo());
				 */
			}
		}

		return mapObj;
	}

	/*
	 *  public String portfolioAmountCount(String portfolioNumber)
	 * throws NullPointerException { String value = (String)
	 * sessionFactory.getCurrentSession()
	 * .createCriteria(PortfolioAmountCount.class)
	 * .setProjection(Projections.sum("sanctionAmount"))
	 * .add(Restrictions.eq("portfolioNUmber", portfolioNumber))
	 * .uniqueResult(); if (value == null) { String nullValue = "0"; return
	 * nullValue; } else { return value; } }
	 */
	
	public String portfolioAmountCount(String portfolioNumber)
			throws NullPointerException {
		
		
			String value = (String) sessionFactory.getCurrentSession()
					.createCriteria(PortfolioAmountCount.class)
					.setProjection(Projections.sum("sanctionAmount"))
					.add(Restrictions.eq("portfolioNUmber", portfolioNumber))
					.uniqueResult();
			if (value == null) {
				String nullValue = "0";
				return nullValue;
			} else {
				return value;
			}
		
		
////changes mase by say 21jan		
//		Integer value = (Integer) sessionFactory.getCurrentSession()
//				.createCriteria(PortfolioAmountCount.class)
//				.setProjection(Projections.sum("outStandingAmount"))
//				.add(Restrictions.eq("portfolioNUmber", portfolioNumber))
//				.uniqueResult();
//		
//		System.out.println("amt" +value);		
//		
//		if(value!=null){
//			
//			
//			return value;
//		} else {
//			return 0;
//		}
//	//end
	}

	
	public int portfolioSuccessRecordsCount(String portfolioNumber)
			throws NullPointerException {
		String status = "NMA";
		String verifactionStatus = "Y";
		return (Integer) sessionFactory.getCurrentSession()
				.createCriteria(PortfolioAmountCount.class)
				.setProjection(Projections.count("portfolioNUmber"))
				.add(Restrictions.eq("portfolioNUmber", portfolioNumber))
				.add(Restrictions.eq("status", status))
				.add(Restrictions.eq("verifactionStatus", verifactionStatus))
				.uniqueResult();
	}

	
	public int portfolioFailedRecordsCount(String portfolioNumber)
			throws NullPointerException {
		String status = "NMR";
		String verifactionStatus = "Y";
		return (Integer) sessionFactory.getCurrentSession()
				.createCriteria(PortfolioAmountCount.class)
				.setProjection(Projections.count("portfolioNUmber"))
				.add(Restrictions.eq("portfolioNUmber", portfolioNumber))
				.add(Restrictions.eq("status", status))
				.add(Restrictions.eq("verifactionStatus", verifactionStatus))
				.uniqueResult();
	}

	
	public PortfolioNumberInfo portfolioNumberInfo(String portfolioName)
			throws NullPointerException {

		return (PortfolioNumberInfo) sessionFactory.getCurrentSession().get(
				PortfolioNumberInfo.class, portfolioName);
	}

	
	public Map<String, String> getDetailsForMLI(String mliName)
			throws NullPointerException {
		List<PortfolioNumInfo> PortfolioNumList = sessionFactory
				.getCurrentSession().createCriteria(PortfolioNumInfo.class)
				.add(Restrictions.eq("mliLongName", mliName)).list();
		for (PortfolioNumInfo portfolioNumInfo : PortfolioNumList) {
			mapObj.put(portfolioNumInfo.getPortfolio_name(),
					portfolioNumInfo.getPortfolio_name());
		}
		return mapObj;
	}

	
	public PortfolioNumInfo getPortfolioNum(String pprtfolioName)
			throws NullPointerException {
		return (PortfolioNumInfo) sessionFactory.getCurrentSession()
				.createCriteria(PortfolioNumInfo.class, pprtfolioName)
				.add(Restrictions.eq("portfolio_name", pprtfolioName))
				.uniqueResult();
	}

}
