package com.nbfc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.nbfc.bean.CGTMSEExposureMasterBean;
import com.nbfc.model.CGTMSEExposureMasterGetDetailsOfMemberInfo;
import com.nbfc.model.CGTMSECreateExposureLimitByMaker;
import com.nbfc.model.PortFolioDetailsInChildTBL;
import com.nbfc.model.PortFolioDetailsInParentTBL;

public interface CGTMSECreateExposureLimitMakerDao {

public Map<String, String> getMliLongNameInDropDown();
	public ArrayList getMliShortNameOnChangeOfMliLongName(CGTMSEExposureMasterBean cgtmseExposureMasterBean);
	public Integer getMaxExposureIdCount();
	public List<CGTMSEExposureMasterGetDetailsOfMemberInfo> getDetailsOfMemberInfo(CGTMSEExposureMasterGetDetailsOfMemberInfo cgtmseExposureMasterGetDetailsOfMemberInfoObj2);
	public CGTMSECreateExposureLimitByMaker getDetailsOfExposer(CGTMSECreateExposureLimitByMaker cgtmseExposureMasterGetDetails, String fyYear);
	public List<Object[]> getExposureLimitDetailsPendingForApproval();
	public List<Object[]> getExposureLimitApprovedDetails();
	public List<Object[]> getExposureLimitRejectedDetails();
	public void createNewExposureLimit(CGTMSECreateExposureLimitByMaker cgtmseExposureMasterSaveDetailsObj);
	public List<Object[]>getAllExposureLimitDetails();
	public void savePortFolioDetailsInParentTBL(PortFolioDetailsInParentTBL objPortFolioDetailsInParentTBL);
	public void savePortFolioDetailsInChildTBL(PortFolioDetailsInChildTBL objPortFolioDetailsInChildTBL);
	public String getFyBasedOnStartAndEndDate(String startDate,String endDate);

}
