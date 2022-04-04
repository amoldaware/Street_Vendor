package com.nbfc.service;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nbfc.bean.CGTMSEModifiedExposureLimitCheckerBean;
import com.nbfc.dao.CGTMSEModifiedExposureLimitCheckerDao;
import com.nbfc.model.CGTMSEAuditExposureLimit;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETExposureDetails;
import com.nbfc.model.CGTMSEExposureMasterDetails;
import com.nbfc.model.CGTMSEMemberInfoDetails;

@Service("cgtmseModifiedExposureLimitCheckerService")
@Transactional()
public class CGTMSEModifiedExposureLimitCheckerServiceImpl implements CGTMSEModifiedExposureLimitCheckerService{

	@Autowired
	private  CGTMSEModifiedExposureLimitCheckerDao cgtmseModifiedExposureLimitCheckerDao;
	
	public Map<String, String> getMliLongNameInDropDown() {
		return  (Map<String, String>) cgtmseModifiedExposureLimitCheckerDao.getMliLongNameInDropDown();
	}
	
	public ArrayList getMliShortNameOnChangeOfMliLongName(CGTMSEModifiedExposureLimitCheckerBean cgtmseModifiedExposureLimitCheckerBeanBeanObj) {
		return cgtmseModifiedExposureLimitCheckerDao.getMliShortNameOnChangeOfMliLongName(cgtmseModifiedExposureLimitCheckerBeanBeanObj);
	}
	
	public CGTMSEMemberInfoDetails getMliMemberInfo(CGTMSEMemberInfoDetails cgtmseMemberInfoDetailsObj){
		return cgtmseModifiedExposureLimitCheckerDao.getMliMemberInfo(cgtmseMemberInfoDetailsObj);
	}

	public CGTMSEExposureMasterDetails getMliExposureLimitInfo(CGTMSEExposureMasterDetails cgtmseExposureMasterDetailsObj) {
			return cgtmseModifiedExposureLimitCheckerDao.getMliExposureLimitInfo(cgtmseExposureMasterDetailsObj);
	}

	public CGTMSEExposureMasterCheckerGETExposureDetails getExposureLimitInfo(CGTMSEExposureMasterCheckerGETExposureDetails cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj) {
		return cgtmseModifiedExposureLimitCheckerDao.getExposureLimitInfo(cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj);
	}
	
	public CGTMSEAuditExposureLimit getAllExposureAuditLimitDetails(CGTMSEAuditExposureLimit cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj) {
		return cgtmseModifiedExposureLimitCheckerDao.getAllExposureAuditLimitDetails(cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj);
	}
}
