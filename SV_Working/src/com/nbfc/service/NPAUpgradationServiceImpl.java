package com.nbfc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbfc.bean.NpaUpgradationBean;
import com.nbfc.dao.NPADetailsDao;
import com.nbfc.dao.NPAUpgradationDao;

@Service("NPAUpgradationService")
public class NPAUpgradationServiceImpl implements NPAUpgradationService{
	
	@Autowired
	NPAUpgradationDao npaUpgradationDao;
	public List<NpaUpgradationBean> getCgpanNpaDetails(String cgpan) {
		return npaUpgradationDao.getCgpanNpaDetails(cgpan);
	}
	
	public List<NpaUpgradationBean> getCgpanNpaUpgradationDetails(String cgpan) {
		return npaUpgradationDao.getCgpanNpaUpgradationDetails(cgpan);
	}
	public int saveNPAUpgradationDetails(NpaUpgradationBean bean,String usrId) {
		// TODO Auto-generated method stub
		return npaUpgradationDao.saveNPAUpgradationDetails(bean,usrId);
	}
	
	public int saveNPAUpgradationEditDetails(NpaUpgradationBean bean,String usrId) {
		// TODO Auto-generated method stub
		return npaUpgradationDao.saveNPAUpgradationEditDetails(bean,usrId);
	}
	
	public int SaveNPAUpgradationApprove(NpaUpgradationBean bean,String usrId) {
		// TODO Auto-generated method stub
		return npaUpgradationDao.SaveNPAUpgradationApprove(bean,usrId);
	}
	
	public int SaveNPAUpgradationReject(NpaUpgradationBean bean,String usrId) {
		// TODO Auto-generated method stub
		return npaUpgradationDao.SaveNPAUpgradationReject(bean,usrId);
	}
	public List<NpaUpgradationBean> getNPAUpgradationDetailsForApproval(String useId,String status) {
		return npaUpgradationDao.getNPAUpgradationDetailsForApproval(useId,status);
	}
	
	public NpaUpgradationBean getNPADetailsForUpgradation(String cgpan,String userId) {
		return npaUpgradationDao.getNPADetailsForUpgradation(cgpan,userId);
	}
	
	
	public NpaUpgradationBean getNPAUpgradationDetails(String cgpan,String userId) {
		return npaUpgradationDao.getNPAUpgradationDetails(cgpan,userId);
	}
	
	public List<NpaUpgradationBean> getCgpanStatus(String cgpan,String userId) {
		return npaUpgradationDao.getCgpanStatus(cgpan,userId);
	}
	
	public List<NpaUpgradationBean> getNPADetailsForUpgradationEdit(String userId,String status) {
		return npaUpgradationDao.getNPADetailsForUpgradationEdit(userId,status);
	}
	public List<NpaUpgradationBean> getNpaUpgradationReport(String userId, Date toDate,
			Date fromDate, String member,String mliLongName,String role) {
		// TODO Auto-generated method stub
		return npaUpgradationDao.getNpaUpgradationReport(userId, toDate, fromDate, member,mliLongName,role);
	}

}
