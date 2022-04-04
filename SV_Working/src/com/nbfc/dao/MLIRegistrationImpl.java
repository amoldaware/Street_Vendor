package com.nbfc.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.model.AudMLiDetails;
import com.nbfc.model.BankDetails;
import com.nbfc.model.MLIEditApproveRejectUpdate;
import com.nbfc.model.MLIEditDetails;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.MLIRegistrationAppraval;
import com.nbfc.model.MLI_UNDERTAKEN_UPLOAD;
import com.nbfc.model.NewMLIRegistration;
import com.nbfc.model.PortfolioBatchApp;

@Repository("mliRegistrationDao")
public class MLIRegistrationImpl implements MLIRegistrationDao {

	private static final int List = 0;
	private static final int AudMLiDetails = 0;
	@Autowired
	SessionFactory sessionFactory;

	
	public List<BankDetails> getBankDetails() {
		return (List<BankDetails>) sessionFactory.getCurrentSession()
				.createCriteria(BankDetails.class).list();
	}

	
	public void addMLIDetails(MLIRegistration mliRegistration) {
		sessionFactory.getCurrentSession().save(mliRegistration);

	}

	
	public List<MLIRegistration> getMLIRegList(String mliLongName,String status) {
		//String status = "CMR";
		return sessionFactory.getCurrentSession()
				.createCriteria(MLIRegistration.class, mliLongName)
				.add(Restrictions.eq("status", status)).list();
	}

	
	public MLIRegistration getMLIDetails(String mliName) {
		return (MLIRegistration) sessionFactory.getCurrentSession().get(
				MLIRegistration.class, mliName);
	}

	
	public void updateMLIDetails(MLIRegistrationAppraval mliRegistration) {
		sessionFactory.getCurrentSession().saveOrUpdate(mliRegistration);
	}

	
	public void editMLIDetails(MLIEditDetails mliRegistration) {
		sessionFactory.getCurrentSession().saveOrUpdate(mliRegistration);
	}

	
	public MLIEditDetails getMLIDtl(String mliName) {
		MLIEditDetails sdfgsd =(MLIEditDetails) sessionFactory.getCurrentSession().get(
				MLIEditDetails.class, mliName);
		System.out.println("Details "+sdfgsd);
		return sdfgsd;
	}

	
	public void audAddMLIDetails(AudMLiDetails mliRegistration) {
		sessionFactory.getCurrentSession().saveOrUpdate(mliRegistration);
	}

	
	public AudMLiDetails getMLIAudDetails(String mliName) {
		AudMLiDetails asdfasd= (AudMLiDetails) sessionFactory.getCurrentSession().get(
				AudMLiDetails.class, mliName);
	/*String status="CCA";
		List<AudMLiDetails> asdfasd= (List<AudMLiDetails>) sessionFactory.getCurrentSession().createCriteria(AudMLiDetails.class,mliName).add(Restrictions.eq("status", status)).list();
		System.out.println("Details 2"+asdfasd);
		for(AudMLiDetails list:asdfasd){
			
		}*/
		return asdfasd;
	}

	
	public void updateMLIApproveRejectStatus(
			MLIEditApproveRejectUpdate mliEditApproveRejectUpdate) {
		sessionFactory.getCurrentSession().saveOrUpdate(mliEditApproveRejectUpdate);
			}

	
	public List<MLIRegistration> getMLIListForChecker(String status) {
		// TODO Auto-generated method stub
		String state = "CME";
		return sessionFactory.getCurrentSession()
				.createCriteria(MLIRegistration.class, status)
				.add(Restrictions.eq("status", state)).list();
	}

	
	public MLIRegistrationAppraval getMLIDetailsForApproveReject(String mliName) {
		// TODO Auto-generated method stub
		return (MLIRegistrationAppraval) sessionFactory.getCurrentSession().get(
				MLIRegistrationAppraval.class, mliName);
	}

	
	public void addNEWMLIDetails(NewMLIRegistration mliRegistration) {
		System.out.println("Added MLI Detail----");
		sessionFactory.getCurrentSession().save(mliRegistration);
		
	}


	@Override
	public void mliUploadFile(MLI_UNDERTAKEN_UPLOAD mli_upload) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(mli_upload);
	}
}
