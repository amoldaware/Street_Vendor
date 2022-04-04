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

import com.nbfc.bean.MliUploadDetails;
import com.nbfc.model.AudMLiDetails;
import com.nbfc.model.MLIMainEditDetails;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.MLI_UNDERTAKEN_UPLOAD;
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

	public List<MliUploadDetails> getMLIDetailsbyFileIndex(String indexFirst,
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
		List <MliUploadDetails> listbean=new ArrayList<MliUploadDetails>();
		for(MLIRegistration obj:mliDetails)
		{
			MLI_UNDERTAKEN_UPLOAD uploadFile = (MLI_UNDERTAKEN_UPLOAD) sessionFactory.getCurrentSession().createCriteria(MLI_UNDERTAKEN_UPLOAD.class).add(Restrictions.eq("mliId", obj.getMem_bnk_id())).uniqueResult();
			MliUploadDetails bean=new MliUploadDetails();
			bean.setGstinNumber(obj.getGstinNumber());
			bean.setMem_bnk_id(obj.getMem_bnk_id());
			bean.setMliLongName(obj.getLongName());
			bean.setShortName(obj.getShortName());
			bean.setCompanyAddress(obj.getCompanyAddress());
			bean.setCity(obj.getCity());
			bean.setCompanyPAN(obj.getCompanyPAN());
			bean.setContactPerson(obj.getContactPerson());
			bean.setMobileNUmber(obj.getMobileNUmber());
			bean.setEmailId(obj.getEmailId());
			bean.setStatus(obj.getStatus());
			if(uploadFile!=null)
			{
			bean.setFileName(uploadFile.getFileName());
			bean.setFileExtension(uploadFile.getFileExtension());
			bean.setFilePath(uploadFile.getFilePath());
			}
			listbean.add(bean);
			
		}
		return listbean;

		
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


	@Override
	
		public List<MliUploadDetails> getUploadFile(String longName, String status) {
			
		Query queryObject = sessionFactory
				.getCurrentSession()
				.createSQLQuery("select GSTIN_NO,mem_bnk_id,LONG_NAME,SHORT_NAME,MEM_ADDRESS,MEM_CITY,"
	+ "COMPANY_PAN,CONTACT_PERSON,MOBILE_NO,MEM_EMAIL,STATUS,u.FILE_NAME,u.FILE_EXTENSION,u.FILE_PATH "
	+ "from NBFC_MEMBER_INFO left join mli_undertaken_upload as u on MEM_BNK_ID=u.mli_id order by mem_bnk_id desc");
						
					/*	"select m.gstinNumber,m.mem_bnk_id,m.longName,m.shortName,m.companyAddress,m.city,"
						+ "m.companyPAN,m.contactPerson,m.mobileNUmber,m.emailId,m.status,u.fileName,u.fileExtension,u.filePath "
						+ "from MLIRegistration as m left join MLI_UNDERTAKEN_UPLOAD as u on "
						+ "m.mem_bnk_id=u.mliId");
						*/
		ArrayList <Object[]>uploadList=(ArrayList<Object[]>) queryObject.list();
		System.out.println("Mli Upload List "+uploadList);
		List <MliUploadDetails> listbean=new ArrayList<MliUploadDetails>();
		for(Object []obj:uploadList)
		{
			MliUploadDetails bean=new MliUploadDetails();
			bean.setGstinNumber(String.valueOf(obj[0]));
			bean.setMem_bnk_id(String.valueOf(obj[1]));
			bean.setMliLongName(String.valueOf(obj[2]));
			bean.setShortName(String.valueOf(obj[3]));
			bean.setCompanyAddress(String.valueOf(obj[4]));
			bean.setCity(String.valueOf(obj[5]));
			bean.setCompanyPAN(String.valueOf(obj[6]));
			bean.setContactPerson(String.valueOf(obj[7]));
			bean.setMobileNUmber(String.valueOf(obj[8]));
			bean.setEmailId(String.valueOf(obj[9]));
			bean.setStatus(String.valueOf(obj[10]));
			bean.setFileName(String.valueOf(obj[11]));
			bean.setFileExtension(String.valueOf(obj[12]));
			bean.setFilePath(String.valueOf(obj[13]));
			listbean.add(bean);
			
		}
		return listbean;
		/*
		 * 
		Criteria c = sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class);
	        c.addOrder(Order.desc("mem_bnk_id"));
			return c.list();*/
			
			//return (List<MLIRegistration>) sessionFactory.getCurrentSession().createCriteria(MLIRegistration.class, longName).list();
		}
	}


