package com.nbfc.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.SanctionCheckerBean;
import com.nbfc.model.DanAllForCgtmseCheckerModel;
import com.nbfc.model.SanctionCheckerModel;
import com.nbfc.model.SanctionCheckerModelAud;
@Transactional
@Repository("danAllForCgtmseCheckerDao")
public class DanAllForCgtmseCheckerDaoImpl implements DanAllForCgtmseCheckerDao {
	@Autowired
	SessionFactory sessionFactory;

	
	public List<DanAllForCgtmseCheckerModel> getDataForAllDan() {
		// TODO Auto-generated method stub
		List<DanAllForCgtmseCheckerModel> danForAll = (List<DanAllForCgtmseCheckerModel>)sessionFactory
				.getCurrentSession()
				.createCriteria(DanAllForCgtmseCheckerModel.class).add(Restrictions.eq("danStatus", "DCCG")).list();

		
		
		
		
		
		/*Query queryObject = sessionFactory.getCurrentSession().createQuery("from DanAllForCgtmseCheckerModel");danStatus

		List<DanAllForCgtmseCheckerModel> list = queryObject.list();

*/

		
		return danForAll;
	}

	
	public int approveDanAllForCGTMSEChecker(List<String> list) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    int result = 0;
	    
	    try {
	       tx = session.beginTransaction();
		   if (list.size() > 0 && !list.isEmpty()) {
	       for(String danId:list){
	   				Query query = sessionFactory
	   						.getCurrentSession()
	   						.createQuery(
	   								"Update DanAllForCgtmseCheckerApprovalModel set status=:statusValue where dan_Id = :danId" );   								
	   				query.setParameter("statusValue", "DCCA");   				
	   				query.setParameter("danId", danId);
	   				result = query.executeUpdate();

	   			}
	   		} 
	       
	      
	       tx.commit();
	    } catch (HibernateException e) {
	       if (tx!=null) tx.rollback();
	       e.printStackTrace(); 
	    } finally {
	    	sessionFactory.close(); 
	    }
		return result;
		}
}
