package com.nbfc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nbfc.bean.CGTMSEExposureMasterBean;
import com.nbfc.dao.CGTMSECreateExposureLimitMakerDao;
import com.nbfc.model.CGTMSEExposureMasterGetDetailsOfMemberInfo;
import com.nbfc.model.CGTMSECreateExposureLimitByMaker;
import com.nbfc.model.PortFolioDetailsInChildTBL;
import com.nbfc.model.PortFolioDetailsInParentTBL;

@Service("CGTMSEExposureMasterMakerService")
@Transactional()
public class CGTMSECreateExposureLimitMakerServiceImpl implements CGTMSECreateExposureLimitMakerService {
	@Autowired
	private CGTMSECreateExposureLimitMakerDao cgtmseCreateExposureLimitByMakerDao;
	
	public Map<String, String> getMliLongNameInDropDown() {
		return (Map<String, String>) cgtmseCreateExposureLimitByMakerDao.getMliLongNameInDropDown();
	}
	
	public ArrayList getMliShortNameOnChangeOfMliLongName(CGTMSEExposureMasterBean cgtmseExposureMasterBean) {
		return cgtmseCreateExposureLimitByMakerDao.getMliShortNameOnChangeOfMliLongName(cgtmseExposureMasterBean);
	}

	public Integer getMaxExposureIdCount() {
		return cgtmseCreateExposureLimitByMakerDao.getMaxExposureIdCount();
	}

	public List<CGTMSEExposureMasterGetDetailsOfMemberInfo> getDetailsOfMemberInfo(CGTMSEExposureMasterGetDetailsOfMemberInfo cgtmseExposureMasterGetDetailsOfMemberInfoObj2) {
		return cgtmseCreateExposureLimitByMakerDao.getDetailsOfMemberInfo(cgtmseExposureMasterGetDetailsOfMemberInfoObj2);
	}
	
	public CGTMSECreateExposureLimitByMaker getDetailsOfExposer(CGTMSECreateExposureLimitByMaker cgtmseExposureMasterGetDetails,String fyYear) {
		return cgtmseCreateExposureLimitByMakerDao.getDetailsOfExposer(cgtmseExposureMasterGetDetails,fyYear);
	}

	public void createNewExposureLimit(CGTMSECreateExposureLimitByMaker cgtmseExposureMasterSaveDetailsObj) {
		cgtmseCreateExposureLimitByMakerDao.createNewExposureLimit(cgtmseExposureMasterSaveDetailsObj);
	}
	
	public List<Object[]> getExposureLimitDetailsPendingForApproval() {
		return cgtmseCreateExposureLimitByMakerDao.getExposureLimitDetailsPendingForApproval();
	}

	public List<Object[]> getExposureLimitApprovedDetails() {
		return cgtmseCreateExposureLimitByMakerDao.getExposureLimitApprovedDetails();
	}

	public List<Object[]> getExposureLimitRejectedDetails() {
		return cgtmseCreateExposureLimitByMakerDao.getExposureLimitRejectedDetails();
	}
	
	public List<Object[]>getAllExposureLimitDetails() {
		return cgtmseCreateExposureLimitByMakerDao.getAllExposureLimitDetails();
	}
	
	public void savePortFolioDetailsInParentTBL(PortFolioDetailsInParentTBL objPortFolioDetailsInParentTBL) {
		cgtmseCreateExposureLimitByMakerDao.savePortFolioDetailsInParentTBL(objPortFolioDetailsInParentTBL);
	}

	public void savePortFolioDetailsInChildTBL(PortFolioDetailsInChildTBL objPortFolioDetailsInChildTBL) {
		cgtmseCreateExposureLimitByMakerDao.savePortFolioDetailsInChildTBL(objPortFolioDetailsInChildTBL);
	}
	
	public String getFyBasedOnStartAndEndDate(String startDate,String endDate) {
		return cgtmseCreateExposureLimitByMakerDao.getFyBasedOnStartAndEndDate(startDate,endDate);
	}
	
	/*public ArrayList<CGTMSECreateExposureLimitByMaker> validateStartEndDateForOneYear(CGTMSEExposureMasterBean cgtmseExposureMasterBean) {
		return cgtmseCreateExposureLimitByMakerDao.getValidateStartEndDateForOneYear(cgtmseExposureMasterBean);
	}*/

}
