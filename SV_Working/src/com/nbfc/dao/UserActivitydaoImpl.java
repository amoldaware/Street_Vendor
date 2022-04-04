package com.nbfc.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;




import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.bean.UserDashboardBean;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIMainEditDetails;
import com.nbfc.model.MLIName;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.UserActivity;
import com.nbfc.model.UserDashboardVmodel;
import com.nbfc.model.UserRolePrivelage;

@Repository("UserActivityDao")
public class UserActivitydaoImpl implements UserActivityDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	UserDashboardVmodel userDashboardVmodel;

	/*
	public List<UserActivity> getActivity(String roleName,String subActName) {
		
		return sessionFactory.getCurrentSession()
				.createCriteria(UserActivity.class)
				.add(Restrictions.eq("role_name", roleName)).add(Restrictions.eq("sub_act_name", subActName)).list();
	}*/
	
	public List<UserActivity> getActivity(String roleName,String subActName) {
		
		return sessionFactory.getCurrentSession()
				.createCriteria(UserActivity.class)
				.add(Restrictions.eq("role_name", roleName)).add(Restrictions.eq("sub_act_name", subActName)).add(Restrictions.eq("isactive", "1")).addOrder(Order.asc("index_postion")).list();
	}
	
	public MLIName getBankID(String bankName) throws NullPointerException {
		
		return (MLIName) sessionFactory.getCurrentSession()
				.createCriteria(MLIName.class,bankName)
				.add(Restrictions.eq("mliLName", bankName)).uniqueResult();
	}

	
	public List<MLIDetails> getUserName(String mem_ban_id) {
		return (List<MLIDetails>) sessionFactory.getCurrentSession()
				.createCriteria(MLIDetails.class)
				.add(Restrictions.eq("mem_bnk_id", mem_ban_id)).list();
	}

	
	public List<UserRolePrivelage> getUserPrvDetails() {
		// TODO Auto-generated method stub
		return  sessionFactory.getCurrentSession()
		.createCriteria(UserRolePrivelage.class).list();
	}

	
	public void saveUserRoles(UserRolePrivelage userRolePrivelage) {
	
		sessionFactory.getCurrentSession().save(userRolePrivelage);
		System.out.println("Data saved in nbfc_user_privilege table===");
		}

	
	public int getMaxNumber() {
		
		return (Integer)sessionFactory.getCurrentSession().createCriteria(UserRolePrivelage.class).setProjection(Projections.max("s_no")).uniqueResult();
	}
	public void setUserType(String roleName, String userID) {
	
				int result=0;
				
				Session session = sessionFactory.openSession();
				Transaction tn = session.beginTransaction();
				try{
						
				Query query = sessionFactory.getCurrentSession().createQuery("update UserRoleTypeModel set userType=:roleName  where usr_id = :usr_id");
				query.setParameter("usr_id", userID);
				query.setParameter("roleName", roleName);

				
				result = query.executeUpdate();
				if(result>0)
				tn.commit();
				
				} catch (Exception e) {
					tn.rollback();
					// TODO: handle exception
				}finally{
					session.close();
				}
	}
	
	public int verifychkCnt(String Mliname) {
		Integer count;
		

		try{
			String hql = "select count(usr_id)from User where mem_bnk_id = :MEM_BNK_ID and userType= :USR_TYPE";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("MEM_BNK_ID", Mliname);
			query.setParameter("USR_TYPE", "Checker");
			return ((Number) query.uniqueResult()).intValue();
			}catch(Exception e){
				System.out.println("Exception==="+e);
			}
			return 0;

}

	
	public int verifymkCnt(String bnkId) {
    Integer count;
	try{
			String hql = "select count(usr_id)from User where mem_bnk_id = :MEM_BNK_ID and userType= :USR_TYPE";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("MEM_BNK_ID", bnkId);
			query.setParameter("USR_TYPE", "Maker");
			return ((Number) query.uniqueResult()).intValue();
			}catch(Exception e){
				System.out.println("Exception==="+e);
			}
			return 0;
	}
	
	public int checkExistingUserRole(String mliId, String userrole,
			String userName) {
		Number count = 0;
		int value=0;
		try {
			String bankId = mliId.substring(0, 4);
			String zoneId = mliId.substring(4, 8);
			String branchId = mliId.substring(8, 12);
		
			System.out.println("bankId"+bankId);
			System.out.println("zoneId"+zoneId);
			System.out.println("branchId"+branchId);
			// String hql =
			// "from User where mem_bnk_id = :mem_bnk_id and mem_zne_id = :mem_zne_id and usr_id = :userName and mem_brn_id = :mem_brn_id and userType= :USR_TYPE";
			Number alreadyExists=verifyExists(mliId,userrole);
			if(alreadyExists.intValue()==1){
				value=1;
			}else{
				String hql = "select count(*) from User where MEM_BNK_ID = :MEM_BNK_ID and MEM_ZNE_ID = :MEM_ZNE_ID and MEM_BRN_ID = :MEM_BRN_ID  and userType in(:userTypeValue,:userTypeValue1)";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("MEM_BNK_ID", bankId);
				query.setParameter("MEM_ZNE_ID", zoneId);
				query.setParameter("MEM_BRN_ID", branchId);
				query.setParameter("userTypeValue","Maker");				
				query.setParameter("userTypeValue1","Checker");				
//				query.setParameter("userName", userName);
				 count = ((Number) query.uniqueResult()).intValue();
				 System.out.println("count===" +bankId);
				 System.out.println("count===" +zoneId);
				 System.out.println("count===" +branchId);
				 System.out.println("count===" +count);
				 if(count.intValue()==1){
					 value=2;
				 }
			}
		
		} catch (Exception e) {
			System.out.println("Exception===" + e);
		}
		return value;
	}
	private Number verifyExists(String mliId, String userrole) {
		String bankId = mliId.substring(0, 4);
		String zoneId = mliId.substring(4, 8);
		String branchId = mliId.substring(8, 12);
		String hql = "select count(*) from User where mem_bnk_id = :mem_bnk_id and mem_zne_id = :mem_zne_id and userType = :USR_TYPE and mem_brn_id = :mem_brn_id";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("mem_bnk_id", bankId);
		//query.setParameter("userName", userName);
		query.setParameter("mem_zne_id", zoneId);
		query.setParameter("mem_brn_id", branchId);
		query.setParameter("USR_TYPE", userrole);
    	return ((Number) query.uniqueResult()).intValue();
		}
	
	public UserDashboardVmodel getUserDashboardDetails(String mliId) {
		// TODO Auto-generated method stub
		System.out.println("*****************************************************");
		
		try{
	     userDashboardVmodel =(UserDashboardVmodel) sessionFactory.getCurrentSession().createCriteria(UserDashboardVmodel.class).add(Restrictions.eq("status", "AADA")).add(Restrictions.eq("mli_id", mliId)).uniqueResult();
		}catch(Exception e){
		e.printStackTrace();	
			}
		System.out.println("value"+userDashboardVmodel);
		return   userDashboardVmodel;
	}
	
	public List<UserActivity> getReport(String roleName, String subActName) {
		// TODO Auto-generated method stub
		System.out.println("Report");
		return sessionFactory.getCurrentSession()
				.createCriteria(UserActivity.class)
				.add(Restrictions.eq("role_name", roleName)).add(Restrictions.eq("sub_act_name", subActName)).add(Restrictions.eq("isactive", "1")).addOrder(Order.asc("index_postion")).list();

	}
	
	
	public List getActivityName(String role, String action) {
		return sessionFactory.getCurrentSession()
				.createCriteria(UserActivity.class)
				.add(Restrictions.eq("role_name", role)).add(Restrictions.eq("act_name", action)).add(Restrictions.eq("isactive", "1")).list();
	}
	
	public UserDashboardBean getUserDashboardReport(String user_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<MLIRegistration> getMliLongName(String val) {
		// TODO Auto-generated method stub
      return sessionFactory.getCurrentSession()
    		  .createCriteria(MLIRegistration.class)
    		  .add(Restrictions.eq("status", "CCA"))
    		  .add(Restrictions.ilike("longName",val, MatchMode.START)).list();
		
	}
	
	public void sendmail(String user_id, String email, String password,
			String subject, String mailBody) {
		// TODO Auto-generated method stub
		CallableStatement callable=null;
		  Session session = sessionFactory.openSession();
		  Transaction tn = session.beginTransaction();
		   Connection connection = ((SessionImpl) session).connection();
	//	Connection conn =(Connection) sessionFactory.getCurrentSession();
		if(connection==null){
			System.out.println("connection is null.........");
		}
		
//      ### sending mail through oracle procedure
        try{
       // callable = connection.prepareCall("{ call CGTSIINTRANETUSER.SENDTEXTMAIL_GEN(?,?,?,?) }");
        callable = connection.prepareCall("{ call SYS.SENDTEXTMAIL(?,?,?,?) }");
        callable.setString(1, email);
        callable.setString(2, "alert@cgtmse.in");
        callable.setString(3, subject);
        callable.setString(4, mailBody);		            
        callable.execute();
      System.out.println("mail send...");  
        }catch(Exception err){
        //	Log.log(5, "GMAction", "showApprRegistrationFormSubmit",err.toString());	
        	try {
				callable.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	callable = null;
        	session.close();
      
        	
        }                	
	}
	public int checkExistingUserRoleforModify(String mliId, String userrole,
			String userName) {
		Number count = 0;
		int value=0;
		try {
			String bankId = mliId.substring(0, 4);
			String zoneId = mliId.substring(4, 8);
			String branchId = mliId.substring(8, 12);
		
			String hql = "select count(*) from User where mem_bnk_id = :mem_bnk_id and mem_zne_id = :mem_zne_id and userType = :USR_TYPE and mem_brn_id = :mem_brn_id and usr_id not in(:UserID)";
	
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("mem_bnk_id", bankId);
			//query.setParameter("userName", userName);
			query.setParameter("mem_zne_id", zoneId);
			query.setParameter("mem_brn_id", branchId);
			query.setParameter("USR_TYPE", userrole);
			query.setParameter("UserID", userName);
			
	    	count=((Number) query.uniqueResult()).intValue();
	    	if(count.intValue()==1){
				 value=1;
			 }
		
		} catch (Exception e) {
			System.out.println("Exception===" + e);
		}
		return value;
	}
//	private Number verifyExists(String mliId, String userrole) {
//		String bankId = mliId.substring(0, 4);
//		String zoneId = mliId.substring(4, 8);
//		String branchId = mliId.substring(8, 12);
//		String hql = "select count(*) from User where mem_bnk_id = :mem_bnk_id and mem_zne_id = :mem_zne_id and userType = :USR_TYPE and mem_brn_id = :mem_brn_id";
//
//		Query query = sessionFactory.getCurrentSession().createQuery(hql);
//		query.setParameter("mem_bnk_id", bankId);
//		//query.setParameter("userName", userName);
//		query.setParameter("mem_zne_id", zoneId);
//		query.setParameter("mem_brn_id", branchId);
//		query.setParameter("USR_TYPE", userrole);
//    	return ((Number) query.uniqueResult()).intValue();
//		}
//	
}
