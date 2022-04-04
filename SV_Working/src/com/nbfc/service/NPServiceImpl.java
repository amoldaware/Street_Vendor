package com.nbfc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbfc.bean.ITPANSearchHistoryBean;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.bean.NPAMarkBean;
import com.nbfc.bean.NPATCDetailsBean;
import com.nbfc.bean.NPAWCDetailsBean;
import com.nbfc.bean.PortfolioBatchBean;
import com.nbfc.bean.PortfolioDetailsBean;
import com.nbfc.dao.NPADetailsDao;

@Service("NPAService")
public class NPServiceImpl implements NPAService {
	@Autowired
	NPADetailsDao npaDetailsDao;

	
	public String getMemberId(String userId) {
		return npaDetailsDao.getMemberId(userId);
	}

	
	public NPADetailsBean getCGPANDetails(String cgpan,String usrId) {
		return npaDetailsDao.getCGPANDetails(cgpan,usrId);
	}

	
	public int saveNPADetails(NPAMarkBean npaMarkBean,NPATCDetailsBean npaTCDetailsBean,NPAWCDetailsBean npaWCDetailsBean,String usrId) {
		// TODO Auto-generated method stub
		return npaDetailsDao.saveNPADetails(npaMarkBean,npaTCDetailsBean,npaWCDetailsBean,usrId);
	}

	
	public List<NPADetailsBean> getNPADetails(String cgpan) {
		// TODO Auto-generated method stub
		return npaDetailsDao.getNPADetails(cgpan);
	}

	
	public List<NPADetailsBean> getNPADetailsForApproval(String useId,String status) {
		return npaDetailsDao.getNPADetailsForApproval(useId,status);
	}

	
	public int updateNPAApproveReject(String cgpanList, String useId,String status,String userType,String ReturnRemark) {
		// TODO Auto-generated method stub
		return npaDetailsDao.updateNPAApproveReject(cgpanList, useId,status,userType,ReturnRemark);
	}

	
	public List<NPADetailsBean> getNPADetailsForCGTMSE(String status,
			String usrId, String cgpan, String sDate, String eDate) {
		// TODO Auto-generated method stub
		return npaDetailsDao.getNPADetailsForCGTMSE(status, usrId, cgpan, sDate, eDate);
	}


	public NPADetailsBean getModifyNPADetails(String cgpan,String userId) {
		// TODO Auto-generated method stub
		return npaDetailsDao.getModifyNPADetails(cgpan,userId);
	}


	public int updateNPADetails(NPADetailsBean bean, String cgpan,String userId) {
		// TODO Auto-generated method stub
		return npaDetailsDao.updateNPADetails(bean,cgpan,userId);
	}


	public String getCGPAN() {
		// TODO Auto-generated method stub
		return npaDetailsDao.getCGPAN();
	}


	public PortfolioDetailsBean getExposureDetails(String userId) {
		return npaDetailsDao.getExposureDetails(userId);
	}


	@Override
	public List<PortfolioBatchBean> getPortfolioName(String userId) {
		// TODO Auto-generated method stub
		return npaDetailsDao.getPortfolioName(userId);
	}
	@Override
	public List<ITPANSearchHistoryBean> getITpanSearchDetails(String itPan) {
		// TODO Auto-generated method stub
		return npaDetailsDao.getITpanSearchDetails(itPan);
	}


	@Override
	public int NPASaveInAuditTable(NPADetailsBean bean, String cgpan, String userId) {
		// TODO Auto-generated method stub
		return npaDetailsDao.NPASaveInAuditTable(bean,cgpan, userId);
	}
	public List<NPADetailsBean> getNPAReportDetail(String userId, Date toDate,
			Date fromDate, String member,String role) {
		// TODO Auto-generated method stub
		return npaDetailsDao.getNPAReportDetail(userId, toDate, fromDate, member,role);
	}
	
}
