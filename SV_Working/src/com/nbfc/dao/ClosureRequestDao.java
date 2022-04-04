package com.nbfc.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.nbfc.bean.ApplicationStatusDetailsBean;

public interface ClosureRequestDao {
	
	public List<ApplicationStatusDetailsBean> getClosureDetailsForEdit(String userId,String status);
	
	public ApplicationStatusDetailsBean getAppClosureDetails(String cgpan);
	
	public List<ApplicationStatusDetailsBean> getCgpanStatus(String cgpan,String userId);

	public int saveAppClosureDetails(ApplicationStatusDetailsBean bean,String usrId);
	
	public List<ApplicationStatusDetailsBean> getAppClosureDetailsForApproval(String useId,String status);
	
	public ApplicationStatusDetailsBean getAppClosureDetails(String cgpan,String userId);
	
	public int SaveAppClosureReject(ApplicationStatusDetailsBean bean,String usrId);
	
	public List<ApplicationStatusDetailsBean> getAppClosureEditDetails(String userId,String status);
	
	public ApplicationStatusDetailsBean getEditAppClosureDetails(String cgpan,String userId);//
	
	public int SaveAppClosureEdit(ApplicationStatusDetailsBean bean,String usrId);
	
	public int SaveAppClosureApprove(ApplicationStatusDetailsBean bean,String usrId);//
	
	public List<Map<String, Object>> getAppClosureRequestReport(String userId, Date toDate,
			Date fromDate, String member, String role);
	
}
