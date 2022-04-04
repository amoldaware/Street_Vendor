package com.nbfc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nbfc.bean.CGTMSEExposureMasterMakerModifyExposureLimitBean;
import com.nbfc.bean.CGTMSEModifiedExposureLimitCheckerBean;
import com.nbfc.model.CGTMSEAuditExposureLimit;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETExposureDetails;
import com.nbfc.model.CGTMSEExposureMasterDetails;
import com.nbfc.model.CGTMSEMemberInfoDetails;

public interface CGTMSEModifiedExposureLimitCheckerService 
{
	 public Map<String, String> getMliLongNameInDropDown();
	 public ArrayList getMliShortNameOnChangeOfMliLongName(CGTMSEModifiedExposureLimitCheckerBean cgtmseModifiedExposureLimitCheckerBeanBeanObj);
	 public CGTMSEMemberInfoDetails getMliMemberInfo(CGTMSEMemberInfoDetails cgtmseMemberInfoDetailsObj);
	 public CGTMSEExposureMasterDetails getMliExposureLimitInfo(CGTMSEExposureMasterDetails cgtmseExposureMasterDetailsObj);
	 public CGTMSEExposureMasterCheckerGETExposureDetails getExposureLimitInfo(CGTMSEExposureMasterCheckerGETExposureDetails cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj);
	 public CGTMSEAuditExposureLimit getAllExposureAuditLimitDetails(CGTMSEAuditExposureLimit cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj);
}
