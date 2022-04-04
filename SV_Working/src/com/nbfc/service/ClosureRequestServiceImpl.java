package com.nbfc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbfc.bean.ApplicationStatusDetailsBean;
import com.nbfc.dao.ClosureRequestDao;

@Service("ClosureRequestService")

public class ClosureRequestServiceImpl implements ClosureRequestService{
	
	@Autowired
	ClosureRequestDao closureRequestDao;
	
	public List<ApplicationStatusDetailsBean> getClosureDetailsForEdit(String userId,String status) {
		return closureRequestDao.getClosureDetailsForEdit(userId,status);
	}	
	
	public ApplicationStatusDetailsBean getAppClosureDetails(String cgpan) {
		return closureRequestDao.getAppClosureDetails(cgpan);
	}
		
	public List<ApplicationStatusDetailsBean> getCgpanStatus(String cgpan,String userId) {
			return closureRequestDao.getCgpanStatus(cgpan,userId);
		}
	
	public int saveAppClosureDetails(ApplicationStatusDetailsBean bean,String usrId) {
		// TODO Auto-generated method stub
		return closureRequestDao.saveAppClosureDetails(bean,usrId);
	}
	
	public List<ApplicationStatusDetailsBean> getAppClosureDetailsForApproval(String useId,String status) {
		return closureRequestDao.getAppClosureDetailsForApproval(useId,status);
	}
	
	public ApplicationStatusDetailsBean getAppClosureDetails(String cgpan,String userId) {
		return closureRequestDao.getAppClosureDetails(cgpan,userId);
	}
	
	public int SaveAppClosureReject(ApplicationStatusDetailsBean bean,String usrId) {
		// TODO Auto-generated method stub
		return closureRequestDao.SaveAppClosureReject(bean,usrId);
	}
	
	public List<ApplicationStatusDetailsBean> getAppClosureEditDetails(String userId,String status) {
		return closureRequestDao.getAppClosureEditDetails(userId,status);
	}

	public ApplicationStatusDetailsBean getEditAppClosureDetails(String cgpan,String userId) {
		return closureRequestDao.getEditAppClosureDetails(cgpan,userId);
	}
	
	public int SaveAppClosureEdit(ApplicationStatusDetailsBean bean,String usrId) {
		// TODO Auto-generated method stub
		return closureRequestDao.SaveAppClosureEdit(bean,usrId);
	}
	
	public int SaveAppClosureApprove(ApplicationStatusDetailsBean bean,String usrId) {
		// TODO Auto-generated method stub
		return closureRequestDao.SaveAppClosureApprove(bean,usrId);
	}
	
	public List<Map<String, Object>> getAppClosureRequestReport(String userId, Date toDate,
			Date fromDate, String member,String role) {
		// TODO Auto-generated method stub
		return closureRequestDao.getAppClosureRequestReport(userId, toDate, fromDate, member,role);
	}
}
