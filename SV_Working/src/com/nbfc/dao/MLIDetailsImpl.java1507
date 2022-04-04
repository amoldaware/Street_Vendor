package com.nbfc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.jstl.sql.Result;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.model.AudMLiDetails;
import com.nbfc.model.MLIMainEditDetails;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.StateMaster;

@Repository("MLIDetailsDao")
public class MLIDetailsImpl implements MLIDetailsDao {

	private static final int MLIRegistration = 0;
	@Autowired
	private SessionFactory sessionFactory;
	List<MLIRegistration> mliDetails;
	List<String> statusList = new ArrayList<String>();
	String myStatus = null;
	
	public List<MLIRegistration> getMLIAllDetails() {
		return (List<MLIRegistration>) sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class).list();
	}

	
	public List<MLIRegistration> getMLIDetails(String longName, String status) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class);
        c.addOrder(Order.desc("mem_bnk_id"));
		return c.list();
		
		//return (List<MLIRegistration>) sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class, longName).list();
	}

	
	public MLIRegistration getMLIDetails(String mliName) {
		return (MLIRegistration) sessionFactory.getCurrentSession().get(MLIRegistration.class, mliName);
	}

	
	public MLIMainEditDetails getMLIEditDetails(String longName, String status) {
		return (MLIMainEditDetails) sessionFactory.getCurrentSession().createCriteria(MLIMainEditDetails.class).add(Restrictions.eq("longName", longName)).add(Restrictions.eq("status", status)).uniqueResult();
	}

	
	public AudMLiDetails getAUDMLIEditDetails(String longName, String status) {
		return (AudMLiDetails) sessionFactory.getCurrentSession().createCriteria(AudMLiDetails.class).add(Restrictions.eq("longName", longName)).add(Restrictions.eq("status", status)).uniqueResult();
	}

	
	public void updateAUDMLIApproveRejectStatus(AudMLiDetails mliEditApproveRejectUpdate) {
		sessionFactory.getCurrentSession().saveOrUpdate(mliEditApproveRejectUpdate);

	}

	
	public List<MLIRegistration> getMLIDetailsForApproval(String status1,
		String status2,String status3) {
//		Criteria c = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class);
//        c.addOrder(Order.desc("mem_bnk_id"));
	//	return c.list();
		
		
		List<String> statusList = new ArrayList<String>();
		statusList.add(status1);
		statusList.add(status2);
		statusList.add(status3);
		Criteria c1= sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class);
		c1.add(Restrictions.in("status", statusList));
	    c1.addOrder(Order.desc("mem_bnk_id"));
		return c1.list();
	}

	
	public List<MLIRegistration> getMLIDetailsbyIndex(String indexFirst,
			String indexSecond) {
		if (indexFirst.equals("longName")) {
			mliDetails = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class).add(Restrictions.ilike("longName",indexSecond, MatchMode.ANYWHERE)).list();
		} else if (indexFirst.equals("emailId")) {
			mliDetails = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class).add(Restrictions.ilike("emailId", indexSecond, MatchMode.ANYWHERE)).list();
		} else if (indexFirst.equals("companyPAN")) {
			mliDetails = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class).add(Restrictions.ilike("companyPAN", indexSecond, MatchMode.ANYWHERE)).list();
		} else if (indexFirst.equals("status")) {
			if (indexSecond.equals("Approved")) {
				myStatus = "CCA";
				mliDetails = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class).add(Restrictions.eq("status", myStatus)).list();
			} else if (indexSecond.equals("Rejected")) {
				myStatus = "CCR";
				mliDetails = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class).add(Restrictions.eq("status", myStatus)).list();
			} else if (indexSecond.equals("Pending for Approval")) {
				statusList.add("CME");
				statusList.add("CMR");
				mliDetails = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class).add(Restrictions.in("status", statusList)).list();
			} else if (indexSecond.equals("CEMMA")) {
				myStatus = "CEMMA";
				mliDetails = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class).add(Restrictions.eq("status", myStatus)).list();
			}
		} else {
			mliDetails = null;
		}
		return mliDetails;
	}

	
	public List<MLIMainEditDetails> getApprovedMLIDetails(String status) {
		return (List<MLIMainEditDetails>) sessionFactory.getCurrentSession().createCriteria(MLIMainEditDetails.class).add(Restrictions.eq("status", status)).list();
		
	}

	
	public List<MLIRegistration> ApproverejectMliDetailsByIndex(
			String indexFirst, String indexSecond) {
		if (indexFirst.equals("longName")) {
			mliDetails = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class).add(Restrictions.ilike("longName",indexSecond, MatchMode.ANYWHERE)).list();
		} else if (indexFirst.equals("emailId")) {
			mliDetails = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class).add(Restrictions.ilike("emailId", indexSecond, MatchMode.ANYWHERE)).list();
		} else if (indexFirst.equals("companyPAN")) {
			mliDetails = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class).add(Restrictions.ilike("companyPAN", indexSecond, MatchMode.ANYWHERE)).list();
		} else if (indexFirst.equals("status")) {
			if (indexSecond.equals("Approved")) {
				myStatus = "CCA";
				mliDetails = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class).add(Restrictions.eq("status", myStatus)).list();
			} else if (indexSecond.equals("Rejected")) {
				myStatus = "CCR";
				mliDetails = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class).add(Restrictions.eq("status", myStatus)).list();
			} else if (indexSecond.equals("Pending for Approval")) {
				statusList.add("CME");
				statusList.add("CMR");
				mliDetails = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class).add(Restrictions.in("status", statusList)).list();
			} else if (indexSecond.equals("CEMMA")) {
				myStatus = "CEMMA";
				mliDetails = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class).add(Restrictions.eq("status", myStatus)).list();
			}
		} else {
			mliDetails = null;
		}
		return mliDetails;
	}

	
	public List getStatename(String state) {
		
		return sessionFactory.getCurrentSession().createCriteria(StateMaster.class).add(Restrictions.eq("ste_code", state)).list();	
	}

}
