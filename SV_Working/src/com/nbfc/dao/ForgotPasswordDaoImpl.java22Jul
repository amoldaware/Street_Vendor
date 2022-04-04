package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.impl.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.icu.text.DateFormat;
import com.nbfc.bean.LoginBean;
import com.nbfc.model.Login;
import com.nbfc.model.OtpDetailsModel;
import com.raistudies.domain.CustomExceptionHandler;

@Repository("ForgotpasswordDao")
public class ForgotPasswordDaoImpl implements ForgotpasswordDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public String verifyUserID(LoginBean loginBean) {
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
			if(listCategories.getLOGIN_STATUS().equals("N")){
				
				return "firstTimeLogin";
				
			}else{
				
				return "success";
			}
		}else{
			return "invalidUser";
		}
		
	}


	
	public String getUsermailID(String loginUsrId) {
		String email=null;
		Login listCategories=null;
		if(sessionFactory!=null){
		String hql = "from Login where USR_ID = :USR_ID";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("USR_ID", loginUsrId);
		
         listCategories = (Login) query.uniqueResult();
		}else{
			throw new CustomExceptionHandler("connection is null");
		}
		if(listCategories.getUSR_EMAIL_ID()!=null){
		   email=listCategories.getUSR_EMAIL_ID();
		   return email;
				
			}else{
				
				return "Invalid";
			}
	}


	
	public void sendmail(String loginUsrId,String email, String otp, String subject,
			String mailBody) {
		CallableStatement callable=null;
		  Session session = sessionFactory.openSession();
		  Transaction tn = session.beginTransaction();
		   Connection connection = ((SessionImpl) session).connection();
	//	Connection conn =(Connection) sessionFactory.getCurrentSession();
		if(connection==null){
			System.out.println("connection is null.........");
		}
		
//        ### sending mail through oracle procedure
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
//       ###
		
	}


	
	public String getOTP(String loginUsrId) {
		int len=6;
		char[] ch = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
		        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
		        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
		        'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
		        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
		        'w', 'x', 'y', 'z' };
		    
		    char[] c=new char[len];
		    Random random=new Random();
		    for (int i = 0; i < len; i++) {
		      c[i]=ch[random.nextInt(ch.length)];
		    }
		    
		    return new String(c);
	}


	
	public void insertOTPData(OtpDetailsModel otpDetails) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(otpDetails);
	}


	
	public String verifyUserOtp(LoginBean loginBean) {
		// TODO Auto-generated method stub
		OtpDetailsModel listCategories=null;
		if(sessionFactory!=null){
		String hql = "from OtpDetailsModel where user_id = :USR_ID";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("USR_ID", loginBean.getUsr_id());
		
		listCategories = (OtpDetailsModel) query.uniqueResult();
		}else{
			throw new CustomExceptionHandler("connection is null");
		}
		if(listCategories!=null){
		    //return true;
			//return "success";
			if(listCategories.getOtp().equals(loginBean.getOtp())){
				
			   
				Date dateStart = listCategories.getCurrent_date();
				 
				System.out.println(dateStart);
//				SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
				SimpleDateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

				String formattedDate = targetFormat.format(dateStart); 
			    System.out.println(formattedDate);

			    Date date = new Date();  
				String currdate = (targetFormat.format(date));
	
				Date d1 = null;
				Date d2 = null;
				try { 
					
					d1 = targetFormat.parse(formattedDate);
					d2 = targetFormat.parse(currdate);
					long diff = d2.getTime() - d1.getTime();
					long diffMinutes = diff / (60 * 1000);
					
					System.out.print(diffMinutes + " minutes, ");
					if(diffMinutes < 30){
					
						return "validOtp";	
					}else{
						
						return "OTPExpire";	
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			
				
				
				
			}else{
				
				return "InvalidOTP";
			}
		}else{
			return "OTPNULL";
		}
		return null;
	}


	
	public boolean changePassword(Login login) {
try{
			
		}catch(Exception e){
			System.out.println("Exception in LoginDaoImpl on change Password :"+e);
		}
		sessionFactory.getCurrentSession().saveOrUpdate(login);
		return true;
	}


	
		


}
