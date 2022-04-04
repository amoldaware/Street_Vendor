package com.nbfc.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.model.DistrictName;
import com.nbfc.model.NBFCHeaderDetails;
import com.nbfc.model.PromoterDetails;
import com.nbfc.model.SSIDetail;
import com.nbfc.model.StateName;

/**
 * @author Saurav Tyagi 2017
 * 
 */
@Repository("mliValidatorDao")
public class MLIValidatorDaoImpl implements MLIValidatorDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public List<PromoterDetails> getDetails() {
		
		System.out.println("Emp List");
		List<PromoterDetails> list = (List<PromoterDetails>) sessionFactory.getCurrentSession().createCriteria(PromoterDetails.class).list();
		System.out.println("List State"+list);
		
		return list;
	}
	
	public List<SSIDetail> getSSIDetails() {
		List<SSIDetail> list = (List<SSIDetail>) sessionFactory.getCurrentSession().createCriteria(SSIDetail.class).list();
		System.out.println("List State"+list);
		return list;
	}
	
	public List<DistrictName> getStateCode() {
		//return (DistrictName) sessionFactory.getCurrentSession().get(DistrictName.class, districtName); 
		List<DistrictName> list = (List<DistrictName>) sessionFactory.getCurrentSession().createCriteria(DistrictName.class).list();
		System.out.println("Value" +list);
		//List<DistrictName> sessionFactory.getCurrentSession().get(DistrictName.class);
		return list; 
		
	}

	
	public List<StateName> getStateName() {
		List<StateName> stateList = (List<StateName>) sessionFactory.getCurrentSession().createCriteria(StateName.class).list();
		System.out.println("Value" +stateList);
		//List<DistrictName> sessionFactory.getCurrentSession().get(DistrictName.class);
		return stateList; 
		
	}
	
	public HashMap<Integer, String> getHeaderDetails() {
		// TODO Auto-generated method stub
		HashMap<Integer, String> headerMap= new HashMap<Integer, String>();
		List<NBFCHeaderDetails> list = (List<NBFCHeaderDetails>) sessionFactory.getCurrentSession().createCriteria(NBFCHeaderDetails.class).list();
		System.out.println("Header List..."+list);
		for(NBFCHeaderDetails nbfcHeaderDetails:list)
		{
			headerMap.put(nbfcHeaderDetails.getHEADER_ID(), nbfcHeaderDetails.getHEADER_NAME());
			
		}
		return headerMap;
		
	}

}
