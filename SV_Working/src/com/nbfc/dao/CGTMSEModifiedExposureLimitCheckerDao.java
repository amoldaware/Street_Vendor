package com.nbfc.dao;

import java.util.ArrayList;
import java.util.Map;
import com.nbfc.bean.CGTMSEModifiedExposureLimitCheckerBean;
import com.nbfc.model.CGTMSEAuditExposureLimit;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETExposureDetails;
import com.nbfc.model.CGTMSEExposureMasterDetails;
import com.nbfc.model.CGTMSEMemberInfoDetails;

public interface CGTMSEModifiedExposureLimitCheckerDao{
	public Map<String, String> getMliLongNameInDropDown();
	public ArrayList getMliShortNameOnChangeOfMliLongName(CGTMSEModifiedExposureLimitCheckerBean cgtmseModifiedExposureLimitCheckerBeanBeanObj);
	public CGTMSEMemberInfoDetails getMliMemberInfo(CGTMSEMemberInfoDetails cgtmseMemberInfoDetailsObj);
	public CGTMSEExposureMasterDetails getMliExposureLimitInfo(CGTMSEExposureMasterDetails cgtmseExposureMasterDetailsObj);
	public CGTMSEExposureMasterCheckerGETExposureDetails getExposureLimitInfo(CGTMSEExposureMasterCheckerGETExposureDetails cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj);
	public CGTMSEAuditExposureLimit getAllExposureAuditLimitDetails(CGTMSEAuditExposureLimit cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj);
}
