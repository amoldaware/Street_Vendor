package com.nbfc.dao;

import java.util.Date;
import java.util.List;

import com.nbfc.bean.NpaUpgradationBean;

public interface NPAUpgradationDao {
	
	//Diksha
		public List<NpaUpgradationBean> getCgpanNpaDetails(String cgpan);
		public List<NpaUpgradationBean> getCgpanNpaUpgradationDetails(String cgpan);
		public NpaUpgradationBean getNPADetailsForUpgradation(String cgpan,String userId);
		public List<NpaUpgradationBean> getCgpanStatus(String cgpan,String userId);
		public List<NpaUpgradationBean> getNPADetailsForUpgradationEdit(String userId,String status);
		public int saveNPAUpgradationDetails(NpaUpgradationBean bean,String usrId);
		public List<NpaUpgradationBean> getNPAUpgradationDetailsForApproval(String useId,String status);
		public NpaUpgradationBean getNPAUpgradationDetails(String cgpan,String userId);//
		public int SaveNPAUpgradationApprove(NpaUpgradationBean bean,String usrId);//
		public int SaveNPAUpgradationReject(NpaUpgradationBean bean,String usrId);
		public int saveNPAUpgradationEditDetails(NpaUpgradationBean bean,String usrId);
		
		//NPA Upgradation Report
		
		public List<NpaUpgradationBean> getNpaUpgradationReport(String userId, Date toDate,
				Date fromDate, String member,String mliLongName, String role);

}
