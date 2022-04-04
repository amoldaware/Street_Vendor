package com.nbfc.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.model.District;
import com.nbfc.model.DistrictName;
import com.nbfc.model.StateName;


/**
 * @author Saurav Tyagi 2017
 * 
 */

@Repository("distrectDao")
public class DistrictDaoImpl implements DistrictDao {

	@Autowired
	private SessionFactory sessionFactory;
	Map<String, String> mapObj = new HashMap<String, String>();

	
	public Map<String, String> listDistricts(String state) {
		List<District> districtList = sessionFactory.getCurrentSession()
				.createCriteria(District.class, state)
				.add(Restrictions.eq("ste_code", state)).list();
		for (District district : districtList) {
			System.out.println("district" +district.getDst_name());
			mapObj.put(district.getDst_name(), district.getDst_name());
			
		}
		return mapObj;
	}

	
}
