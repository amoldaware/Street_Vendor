package com.nbfc.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.bean.LoginBean;
import com.nbfc.model.Login;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.SessionTrackHistory;
import com.nbfc.model.UserPerivilegeDetails;
import com.raistudies.domain.CustomExceptionHandler;

@Repository("loginDao")
public class LoginDaoImpl implements LoginDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public String verifyUserLogin(LoginBean loginBean) {
		System.out.println("varify login dao.......");
		Login listCategories=null;
		if(sessionFactory!=null){
		String hql = "from Login where USR_ID = :USR_ID and USR_PASSWORD= :USR_PASSWORD";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("USR_ID", loginBean.getUsr_id());
		query.setParameter("USR_PASSWORD", loginBean.getUsr_password());
		listCategories = (Login) query.uniqueResult();
		}else{
			throw new CustomExceptionHandler("connection is null");
		}
		if(listCategories!=null){
		    //return true;
			//return "success";
			if(listCategories.getLOGIN_STATUS().equals("N")){
				
				return "firstTimeLogin";
				
			}
			if("0".equals(listCategories.getIsActive()))
			{
				return "deactive";
			}
			else{
				 String hql = "update Login set loginAttemptTime = :attemptTime,loginAttemptCounter=:attemptCount,forgetAttemptCounter=:fattemptCount where USR_ID = :USR_ID";
					Query query = sessionFactory.getCurrentSession().createQuery(hql);
					query.setParameter("USR_ID", loginBean.getUsr_id());
					query.setParameter("attemptTime", new Date());
					query.setParameter("attemptCount", 0);
					query.setParameter("fattemptCount", 0);
					query.executeUpdate();
					return "success";
			}
		}else{
			return "invalidUser";
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> gerLoginUserPrivilege(String loginUsrId,String usrFlag) {
		//String hql = "select subPortfolioNo,count(distinct subPortfolioNo),status from CGTMSEMakerForBatchApprovalGetStatus where STATUS =:NCAStatus group by subPortfolioNo,status";
		String hql = "select nup.prvId,nup.uprFlag,nup.usrId,npm.prvId,npm.prvName,npm.prvDescription,npm.prvCreatedModifiedBy from NBFCUserPerivilege nup ,NBFCPrivilegeMaster npm  where nup.prvId=npm.prvId AND nup.usrId=:loginUsrId AND nup.uprFlag=:usrFlag";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("loginUsrId", loginUsrId);
		query.setParameter("usrFlag", usrFlag);
		System.out.println("QUERY1==="+query);
		
		List<Object> listCategories4 = query.list();
		java.util.Iterator<Object> itr1= listCategories4.iterator();
	
		while(itr1.hasNext()){
			Object[] obj1 = (Object[]) itr1.next();
		}
		return listCategories4;
	}

	
	public Login userDetails(String usr_id) {
		return (Login) sessionFactory.getCurrentSession().get(Login.class, usr_id);
	}

	
	public UserPerivilegeDetails getUserPrivlageDtl(String uID, String uStatus) {
		return (UserPerivilegeDetails) sessionFactory.getCurrentSession().createCriteria(UserPerivilegeDetails.class, uID).add(Restrictions.eq("user_id", uID)).add(Restrictions.eq("upr_flag", uStatus)).uniqueResult();
	}


	
	public NBFCPrivilegeMaster getPrivlageMstDtl(int prv_id) {
		
		return (NBFCPrivilegeMaster) sessionFactory.getCurrentSession().get(NBFCPrivilegeMaster.class, prv_id);

	}
	
	
	public boolean changePassword(Login login) {
		try{
			
		}catch(Exception e){
			System.out.println("Exception in LoginDaoImpl on change Password :"+e);
		}
		sessionFactory.getCurrentSession().saveOrUpdate(login);
		return true;
	}


	@Override
	public int getLoginAttempt(LoginBean loginBean) {
		System.out.println("varify login dao.......");
		int attemptCount=0;
		Login listCategories=null;
		if(sessionFactory!=null){
		String hql = "from Login where USR_ID = :USR_ID";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("USR_ID", loginBean.getUsr_id());
		listCategories = (Login) query.uniqueResult();
		
		}else{
			throw new CustomExceptionHandler("connection is null");
		}
		if(listCategories!=null){
		    //return true;
			//return "success";
			
			Date attemptTime;
			
			attemptCount=listCategories.getLoginAttemptCounter();
			attemptTime=listCategories.getLoginAttemptTime();
			if(attemptTime==null)
			{
				attemptTime=new Date();
			}
			   SimpleDateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

				String formattedDate = targetFormat.format(attemptTime); 
			    System.out.println(formattedDate);

			    Date date = new Date();  
				String currdate = (targetFormat.format(date));

				Date d1 = null;
				Date d2 = null;
				long diff=0;
				long diffMinutes=0;
				
				try { 
					
					d1 = targetFormat.parse(formattedDate);
					d2 = targetFormat.parse(currdate);
					diff = d2.getTime() - d1.getTime();
					diffMinutes= diff / (60 * 1000);
					System.out.println("now time "+d2+" table time "+d1+" Diff "+diffMinutes);
					
					  if(diffMinutes<15 && attemptCount>=2)
					  {
						  //result="attemptException";
						  String hql = "update Login set isActive =:isActive where USR_ID = :USR_ID";
							Query query = sessionFactory.getCurrentSession().createQuery(hql);
							query.setParameter("isActive","0");
							query.setParameter("USR_ID", loginBean.getUsr_id());
							query.executeUpdate();
							
						  
					  }
					  attemptCount=attemptCount+1;
					  if(diffMinutes>15)
					  {
						attemptCount=1;
					  }
					  String hql = "update Login set loginAttemptTime = :attemptTime,loginAttemptCounter=:attemptCount where USR_ID = :USR_ID";
						Query query = sessionFactory.getCurrentSession().createQuery(hql);
						query.setParameter("USR_ID", loginBean.getUsr_id());
						query.setParameter("attemptTime", new Date());
						query.setParameter("attemptCount", attemptCount);
						query.executeUpdate();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					
				}

		}
		return attemptCount;
		
	}


	@Override
	public void updateLoginHistory(LoginBean loginBean,HttpSession session, HttpServletRequest request) throws Exception {
		
		try
		{
			if(sessionFactory.getCurrentSession()!=null){
				if("logout".equals(loginBean.getfName()))
				{
					String hql = "from SessionTrackHistory where USR_ID=:USR_ID order by loginTime desc";
					Query query = sessionFactory.getCurrentSession().createQuery(hql);
					query.setParameter("USR_ID", loginBean.getUsr_id());
					SessionTrackHistory loginData = (SessionTrackHistory) query.list().get(0);
					 String hql1 = "update SessionTrackHistory set logoutTime = :logoutTime where id = :trackId";
						Query query1 = sessionFactory.getCurrentSession().createQuery(hql1);
						query1.setParameter("logoutTime", new Date());
						query1.setParameter("trackId", loginData.getId());
						query1.executeUpdate();
					
				}
				else
				{
				String hql = "from Login where USR_ID = :USR_ID";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("USR_ID", loginBean.getUsr_id());
				Login loginData = (Login) query.uniqueResult();
				
				System.out.println("login Histrory save process");
				SessionTrackHistory history=new SessionTrackHistory();
				history.setIpAddress(request.getRemoteAddr());
				history.setMachineName(request.getRemoteHost());
				history.setBrowserInfo(request.getHeader("user-agent"));
				history.setLoginTime(new Date());
				history.setUsrId(loginBean.getUsr_id());
				history.setWebSessionId(request.getSession().getId());
				history.setMemBnkId(loginData.getMEM_BNK_ID());
				sessionFactory.getCurrentSession().save(history);
				}
			}
			else
			{
				System.out.println("Database Connection Fail");
			}
			
				
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
}
