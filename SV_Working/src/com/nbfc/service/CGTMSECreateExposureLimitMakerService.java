package com.nbfc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nbfc.bean.CGTMSEExposureMasterBean;
import com.nbfc.model.CGTMSEExposureMasterDetails;
import com.nbfc.model.CGTMSEExposureMasterGetDetailsOfMemberInfo;
import com.nbfc.model.CGTMSECreateExposureLimitByMaker;
import com.nbfc.model.PortFolioDetailsInChildTBL;
import com.nbfc.model.PortFolioDetailsInParentTBL;

public interface CGTMSECreateExposureLimitMakerService {
	public Map<String, String> getMliLongNameInDropDown();
	public ArrayList getMliShortNameOnChangeOfMliLongName(CGTMSEExposureMasterBean cgtmseExposureMasterBean);
	public Integer getMaxExposureIdCount();
	public List<CGTMSEExposureMasterGetDetailsOfMemberInfo> getDetailsOfMemberInfo(CGTMSEExposureMasterGetDetailsOfMemberInfo cgtmseExposureMasterGetDetailsOfMemberInfoObj2);
	public CGTMSECreateExposureLimitByMaker getDetailsOfExposer(CGTMSECreateExposureLimitByMaker cgtmseExposureMasterGetDetails, String fyBasedOnStartAndEndDate);
	public void createNewExposureLimit(CGTMSECreateExposureLimitByMaker cgtmseExposureMasterSaveDetailsObj);
	public List<Object[]> getExposureLimitDetailsPendingForApproval();
	public List<Object[]> getExposureLimitApprovedDetails();
	public List<Object[]> getExposureLimitRejectedDetails();
	public  List<Object[]>getAllExposureLimitDetails();
	public void savePortFolioDetailsInParentTBL(PortFolioDetailsInParentTBL objPortFolioDetailsInParentTBL);
	public void savePortFolioDetailsInChildTBL(PortFolioDetailsInChildTBL objPortFolioDetailsInChildTBL);
	public String getFyBasedOnStartAndEndDate(String startDate,String endDate);
	/*public ArrayList<CGTMSECreateExposureLimitByMaker> validateStartEndDateForOneYear(CGTMSEExposureMasterBean cgtmseExposureMasterBean);*/
	//public List<CGTMSEExposureMasterDetails> getExposureLimitDetail();

}
