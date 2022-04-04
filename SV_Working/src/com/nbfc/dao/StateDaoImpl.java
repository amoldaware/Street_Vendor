package com.nbfc.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.model.MLIInfo;
import com.nbfc.model.MLIName;
import com.nbfc.model.MLIReating;
import com.nbfc.model.PortfolioNumInfo;
import com.nbfc.model.State;
import com.nbfc.model.StateMaster;

/**
 * @author Saurav Tyagi 2017
 * 
 */
@Repository("stateDao")
public class StateDaoImpl implements StateDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<State> listStates(String status) {
		List<State> list = (List<State>) sessionFactory.getCurrentSession()
				.createCriteria(State.class)
				.add(Restrictions.eq("status", status)).addOrder(Order.asc("ste_name")).list();
		return list;
	}

	public MLIInfo userInfo(String longName) {
		MLIInfo DliDetail = (MLIInfo) sessionFactory.getCurrentSession().get(
				MLIInfo.class, longName);
		return DliDetail;
	}

	public List<StateMaster> listStateMaster() {
		List<StateMaster> list = (List<StateMaster>) sessionFactory
				.getCurrentSession().createCriteria(StateMaster.class).list();
		return list;
	}

	public List<MLIReating> mliRatingList() throws NullPointerException {
		List<MLIReating> list = (List<MLIReating>) sessionFactory
				.getCurrentSession().createCriteria(MLIReating.class).list();
		return list;
	}

	public StateMaster stateName(String stateCode)throws NullPointerException  {
	System.out.println("1==="+stateCode);
		return (StateMaster) sessionFactory.getCurrentSession().createCriteria(StateMaster.class).add(Restrictions.eq("ste_code", stateCode)).uniqueResult();

	}
	
/*	public MLIName getBankID(String bankName) throws NullPointerException {
		
		return (MLIName) sessionFactory.getCurrentSession()
				.createCriteria(MLIName.class,bankName)
				.add(Restrictions.eq("mliLName", bankName)).uniqueResult();
	}*/

}
