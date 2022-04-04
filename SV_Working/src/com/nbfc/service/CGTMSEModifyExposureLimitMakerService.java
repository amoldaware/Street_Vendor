package com.nbfc.service;

import java.util.ArrayList;
import java.util.List;
import com.nbfc.bean.CGTMSEExposureMasterMakerModifyExposureLimitBean;
import com.nbfc.model.CGTMSEAuditExposureLimit;
import com.nbfc.model.CGTMSEExposureMasterDetails;
import com.nbfc.model.CGTMSEMemberInfoDetails;

public interface CGTMSEModifyExposureLimitMakerService {
	 public List<Object[]> getMliLongNameInDropDown();
	 public ArrayList getMliShortNameOnChangeOfMliLongName(CGTMSEExposureMasterMakerModifyExposureLimitBean cgtmseExposureMasterMakerModifyExposureLimitBeanObj);
	 public List<CGTMSEMemberInfoDetails> getMliMemberInfo(CGTMSEMemberInfoDetails cgtmseMemberInfoDetailsObj);
	 public CGTMSEExposureMasterDetails getMliExposureLimitInfo(CGTMSEExposureMasterDetails cgtmseExposureMasterDetailsObj);
	 public int saveModifyExposureLimitDataAuditTable(CGTMSEAuditExposureLimit  cgtmseAuditExposureLimitObj2);
	 public int saveModifyExposureLimitByMakerAndSendForApprovalToChecker(CGTMSEExposureMasterDetails cgtmseExposureMasterDetailsObj1);
	 public void updateStatusModifyExposerLimit(CGTMSEMemberInfoDetails cgtmseExposureMasterObj);
	 public List<Object[]> getNoOfExposureMasterPendingForApproval();
	 public List<Object[]> getExposureMasterDetailsPendingForApproval();
	 public List<Object[]> getExposureMasterCheckerApprovalDetails();
	 public List<Object[]> getExposureMasterCheckerRejectionDetails();
		
}
