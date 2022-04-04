package com.nbfc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.model.District;
import com.nbfc.model.MLIMaker;
import com.nbfc.model.MLIMakerApprovalRejection;
import com.nbfc.model.MliMakerEntity;
import com.nbfc.model.PortfolioBatchApp;
import com.nbfc.model.PortfolioNumberDetails;
import com.nbfc.model.PortfolioValidate;

/**
 * @author Saurav Tyagi 2017
 * 
 */
@Repository("portfolioBatchAppRefNoDao")
public class PortfolioBatchAppRefNoImpl implements PortfolioBatchAppRefNoDao {

	@Autowired
	private SessionFactory sessionFactory;
	Map<String, String> mapObj = new HashMap<String, String>();

	public List<PortfolioBatchApp> appRefNoList(String status)
			throws NullPointerException {
		String verifiedStatus = "Y";
		List<PortfolioBatchApp> appRefNoList = sessionFactory
				.getCurrentSession()
				.createCriteria(PortfolioBatchApp.class, status)
				.add(Restrictions.eq("status", "NMA"))
				.add(Restrictions.eq("verifiedStatus", "Y")).list();
		System.out.println("appRefNoList  " + appRefNoList);
		List<PortfolioBatchApp> list = new ArrayList<PortfolioBatchApp>(
				new HashSet<PortfolioBatchApp>(appRefNoList));
		System.out.println("list :" + list);
		return list;
	}

	public List<MLIMakerApprovalRejection> getPortfolioDetail(
			String PORTFOLIO_NAME) throws NullPointerException {
		System.out.println("SStep 2");
		System.out.println("Get List ..............................."
				+ PORTFOLIO_NAME);
		String status = "NMA";
		String verifaiedStatus = "Y";
		List<MLIMakerApprovalRejection> DliDetail = new ArrayList<MLIMakerApprovalRejection>();
		/*
		 * List<MliMakerEntity> DliDetail=(List<MliMakerEntity>)
		 * sessionFactory.getCurrentSession().get(MliMakerEntity.class,
		 * PORTFOLIO_NO);
		 */
		// List<MliMakerEntity> DliDetail=(List<MliMakerEntity>)
		// sessionFactory.getCurrentSession().createCriteria(MliMakerEntity.class,PORTFOLIO_NO).add(Restrictions.eq("PORTFOLIO_NO",
		// PORTFOLIO_NO)).list();
		// List<MLIMakerApprovalRejection>
		// DliDetail=(List<MLIMakerApprovalRejection>)
		// sessionFactory.getCurrentSession().createCriteria(MLIMakerApprovalRejection.class,PORTFOLIO_NO).add(Restrictions.eq("PORTFOLIO_NO",
		// PORTFOLIO_NO)).add(Restrictions.eq("STATUS", status)).list();
		try {
			DliDetail = (List<MLIMakerApprovalRejection>) sessionFactory
					.getCurrentSession()
					.createCriteria(MLIMakerApprovalRejection.class,
							PORTFOLIO_NAME)
					.add(Restrictions.eq("portfolio_Name", PORTFOLIO_NAME))
					.add(Restrictions.eq("STATUS", status))
					.add(Restrictions.eq("VERIFIEDSTATUS", verifaiedStatus))
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("...."+DliDetail.getLOAN_ACCOUNT_NO()+" "+DliDetail.getBUSINESS_PRODUCT());
		return DliDetail;
	}

	public List<MliMakerEntity> getPortfolioDet(String portFolioNo)
			throws NullPointerException {
		System.out.println("portFolioNo " + portFolioNo);

		List<MliMakerEntity> DliDetail = (List<MliMakerEntity>) sessionFactory
				.getCurrentSession()
				.createCriteria(MliMakerEntity.class, portFolioNo)
				.add(Restrictions.eq("PORTFOLIO_NO", portFolioNo)).list();
		System.out.println(DliDetail);
		return DliDetail;
	}

	public List<PortfolioValidate> getLoanaccountNumber()
			throws NullPointerException {

		List<PortfolioValidate> DliDetail = (List<PortfolioValidate>) sessionFactory
				.getCurrentSession().createCriteria(PortfolioValidate.class)
				.list();

		System.out.println(DliDetail.toString());

		return DliDetail;
	}

	public List<MLIMakerApprovalRejection> getPortfolioFailedRecords(
			String PORTFOLIO_NAME) throws NullPointerException {
		String status = "NMR";
		String verifaiedStatus = "Y";

		List<MLIMakerApprovalRejection> DliDetail = (List<MLIMakerApprovalRejection>) sessionFactory
				.getCurrentSession()
				.createCriteria(MLIMakerApprovalRejection.class, PORTFOLIO_NAME)
				.add(Restrictions.eq("portfolio_Name", PORTFOLIO_NAME))
				.add(Restrictions.eq("STATUS", status))
				.add(Restrictions.eq("VERIFIEDSTATUS", verifaiedStatus)).list();
		return DliDetail;
	}
	public PortfolioNumberDetails getPortfolioNUmber(String portfolioName)
			throws NullPointerException {

		return (PortfolioNumberDetails) sessionFactory.getCurrentSession()
				.createCriteria(PortfolioNumberDetails.class)
				.add(Restrictions.eq("portfolioName", portfolioName))
				.uniqueResult();
	}

}
