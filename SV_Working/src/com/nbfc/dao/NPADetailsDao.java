package com.nbfc.dao;

import java.util.Date;
import java.util.List;

import com.nbfc.bean.ITPANSearchHistoryBean;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.bean.NPAMarkBean;
import com.nbfc.bean.NPATCDetailsBean;
import com.nbfc.bean.NPAWCDetailsBean;
import com.nbfc.bean.PortfolioBatchBean;
import com.nbfc.bean.PortfolioDetailsBean;

public interface NPADetailsDao {

	public String getMemberId(String userId);
	public NPADetailsBean getCGPANDetails(String cgpan,String usrId);
	public int saveNPADetails(NPAMarkBean npaMarkBean,NPATCDetailsBean npaTCDetailsBean,NPAWCDetailsBean npaWCDetailsBean,String usrId);
	public List<NPADetailsBean> getNPADetails(String cgpan);
	public List<NPADetailsBean> getNPADetailsForCGTMSE(String status,String usrId, String cgpan,String sDate,String eDate);
	public List<NPADetailsBean> getNPADetailsForApproval(String useId,String status);
	public int updateNPAApproveReject(String cgpanList, String useId,String status,String userType, String returnRemark);
	public NPADetailsBean getModifyNPADetails(String cgpan,String userId);
	public int updateNPADetails(NPADetailsBean bean, String cgpan, String userId);
	public String getCGPAN();
	public PortfolioDetailsBean getExposureDetails(String userId);
	public List<PortfolioBatchBean> getPortfolioName(String userId);
	public List<ITPANSearchHistoryBean> getITpanSearchDetails(String itPan);
	public int NPASaveInAuditTable(NPADetailsBean bean, String cgpan, String userId);
	public List<NPADetailsBean> getNPAReportDetail(String userId, Date toDate,
			Date fromDate, String member, String role);
	
}
