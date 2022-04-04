package com.nbfc.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nbfc.bean.CGTMSEExposureMasterMakerModifyExposureLimitBean;
import com.nbfc.dao.CGTMSEModifyExposureLimitMakerDao;
import com.nbfc.model.CGTMSEAuditExposureLimit;
import com.nbfc.model.CGTMSEExposureMasterDetails;
import com.nbfc.model.CGTMSEMemberInfoDetails;

@Service("CGTMSEExposureMasterMakerModifyExposureLimitService")
@Transactional()
public class CGTMSEModifyExposureLimitMakerServiceImpl implements CGTMSEModifyExposureLimitMakerService{
	@Autowired
	private  CGTMSEModifyExposureLimitMakerDao cgtmseExposureMasterMakerModifyExposureLimitDao;
	
	public List<Object[]>  getMliLongNameInDropDown() {
		return (List<Object[]>) cgtmseExposureMasterMakerModifyExposureLimitDao.getMliLongNameInDropDown();
	}
	
	public ArrayList getMliShortNameOnChangeOfMliLongName(CGTMSEExposureMasterMakerModifyExposureLimitBean cgtmseExposureMasterMakerModifyExposureLimitBeanObj) {
		return cgtmseExposureMasterMakerModifyExposureLimitDao.getMliShortNameOnChangeOfMliLongName(cgtmseExposureMasterMakerModifyExposureLimitBeanObj);
	}
	
	public List<CGTMSEMemberInfoDetails> getMliMemberInfo(CGTMSEMemberInfoDetails cgtmseMemberInfoDetailsObj){
		return cgtmseExposureMasterMakerModifyExposureLimitDao.getMliMemberInfo(cgtmseMemberInfoDetailsObj);
	}

	public CGTMSEExposureMasterDetails getMliExposureLimitInfo(CGTMSEExposureMasterDetails cgtmseExposureMasterDetailsObj) {
		return cgtmseExposureMasterMakerModifyExposureLimitDao.getMliExposureLimitInfo(cgtmseExposureMasterDetailsObj);
	}
	
	public int saveModifyExposureLimitDataAuditTable(CGTMSEAuditExposureLimit  cgtmseAuditExposureLimitObj2) {
		return cgtmseExposureMasterMakerModifyExposureLimitDao.saveModifyExposureLimitDataAuditTable(cgtmseAuditExposureLimitObj2);
	}
	
	public int saveModifyExposureLimitByMakerAndSendForApprovalToChecker(CGTMSEExposureMasterDetails cgtmseExposureMasterDetailsObj1) {
		return cgtmseExposureMasterMakerModifyExposureLimitDao.saveModifyExposureLimitByMakerAndSendForApprovalToChecker(cgtmseExposureMasterDetailsObj1);
	}
	
	public List<Object[]> getNoOfExposureMasterPendingForApproval() {
		return cgtmseExposureMasterMakerModifyExposureLimitDao.getNoOfExposureMasterPendingForApproval();
	}
	
	public List<Object[]> getExposureMasterDetailsPendingForApproval() {
		return cgtmseExposureMasterMakerModifyExposureLimitDao.getExposureMasterDetailsPendingForApproval();
	}
	
	public List<Object[]> getExposureMasterCheckerApprovalDetails() {
		return cgtmseExposureMasterMakerModifyExposureLimitDao.getExposureMasterCheckerApprovalDetails();
	}
	
	public List<Object[]> getExposureMasterCheckerRejectionDetails() {
		return cgtmseExposureMasterMakerModifyExposureLimitDao.getExposureMasterCheckerRejectionDetails();
	}
	
	public void updateStatusModifyExposerLimit(CGTMSEMemberInfoDetails cgtmseExposureMasterObj){
		cgtmseExposureMasterMakerModifyExposureLimitDao.updateStatusModifyExposerLimit(cgtmseExposureMasterObj);
	}
	
}
