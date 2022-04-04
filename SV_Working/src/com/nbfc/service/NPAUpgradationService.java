package com.nbfc.service;

import java.util.Date;
import java.util.List;

import com.nbfc.bean.ITPANSearchHistoryBean;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.bean.NPAMarkBean;
import com.nbfc.bean.NPATCDetailsBean;
import com.nbfc.bean.NPAWCDetailsBean;
import com.nbfc.bean.NpaUpgradationBean;
import com.nbfc.bean.PortfolioBatchBean;
import com.nbfc.bean.PortfolioDetailsBean;
import com.nbfc.model.StateMaster;

public interface NPAUpgradationService {
	
	//Diksha
		public List<NpaUpgradationBean> getCgpanNpaDetails(String cgpan);
		public List<NpaUpgradationBean> getCgpanNpaUpgradationDetails(String cgpan);
		public NpaUpgradationBean getNPADetailsForUpgradation(String cgpan,String userId);
		public List<NpaUpgradationBean> getCgpanStatus(String cgpan,String userId);
		public List<NpaUpgradationBean> getNPADetailsForUpgradationEdit(String userId,String status);
		public int saveNPAUpgradationDetails(NpaUpgradationBean bean,String usrId);
		public List<NpaUpgradationBean> getNPAUpgradationDetailsForApproval(String useId,String status);
		public NpaUpgradationBean getNPAUpgradationDetails(String cgpan,String userId);
		public int SaveNPAUpgradationApprove(NpaUpgradationBean bean,String usrId);
		public int SaveNPAUpgradationReject(NpaUpgradationBean bean,String usrId);
		public int saveNPAUpgradationEditDetails(NpaUpgradationBean bean,String usrId);
		
		public List<NpaUpgradationBean> getNpaUpgradationReport(String userId, Date toDate,
				Date fromDate, String memberId,String mliLongName, String role);

}
