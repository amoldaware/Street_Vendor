package com.nbfc.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.nbfc.bean.ASFGenerationDetailBean;
import com.nbfc.bean.ClaimLodgementBean;
import com.nbfc.model.ClaimDetailsModel;
import com.nbfc.model.ClaimLodgeBlobModel;
import com.nbfc.model.MLIName;
import com.nbfc.model.documentModel;

public interface ClaimLodgementService {
	///public String getLoginUserMemId(String userId);
	

	public List<ClaimLodgementBean> getClaimLoadgmentDetails(
			String loginUserMemId);

	public ClaimLodgementBean getClaimRefNoDetails(String claimRefNo);

	

	public ClaimLodgementBean updateStatusClaimLodgeApprovedReturn(String userId,
			Map<String, Object> claimStatusMapObj,String userRole);

	public List<ClaimLodgementBean> getClaimLoadgmentDetailsForApproval(
			String MLIID, String cNT);

	public List<ClaimLodgementBean> claimLoadgementApprovalCGTMSEMLIWise(
			String userId);

	public ClaimLodgementBean getClaimChecklistDetails(String claimRefNo);

	public documentModel DownloadBlobDoc(String claimRefNo) throws FileNotFoundException, SQLException, IOException;
	public ClaimLodgementBean getMliBorrowerNpaDtlsBeforClaimLodgement(String cgPan);
	//public String saveClaimLodgmentData(ClaimLodgementBean claimLodgementBean,String userId);
	public String saveClaimLodgmentData(ClaimLodgementBean claimLodgementBean,String memberId,String loginUserId);

	public ClaimLodgementBean getSaveClaimLodgmentData(String cgPan);
	//public int saveClaimLodgementCheckListData(ClaimLodgementBean claimLodgementBean,String userId);
	public ClaimLodgementBean getSaveClaimLodgementCheckListData(String cgPan);

	//public void saveBlobDocument(documentModel document);

	public ClaimLodgementBean getCGPANForClaim(String cgpan, String userId);

	

	public ClaimLodgementBean checkDuplicateCGPANForClaim(String cgpan,
			String userId);

	public ClaimLodgementBean getClaimChecklistRecommndation(String claimRefNo);
	public List<ClaimLodgementBean> getClaimReturnedRecordsByNBFCChecker(String loginUserMemId);
	public String updateClaimLodgmentData(ClaimLodgementBean claimLodgementBean,String claimRefNo,String loginUserId);
	public int updateClaimLodgementCheckListData(ClaimLodgementBean claimLodgementBean,String claimRefNo);

	public ClaimLodgementBean getRecommandationCGS(String claimRefNo);

	public MLIName getMLIName(String mLIID);

	public ClaimLodgementBean getClaimCheckerDetails(String claimRefNo);

	public List<ClaimLodgementBean>  getClaimReturnReasons();

	public String submitForClaimReturnResons(List<ClaimLodgementBean> claimLodgList, String returnRemark);

	public List<ClaimLodgementBean> getClaimReturnedRecordsByNBFCCheckerCGS(
			String loginUserMemId);

	public List<ClaimLodgementBean> getClaimLoadgmentDetailsCGS(
			String loginUserMemId);

	public int saveClaimLodgementCheckListData(ClaimLodgementBean claimLodgementBean, String memberId,
			String loginUserId);

	public void saveBlobDocument(ClaimLodgeBlobModel claimLodgeBlobModelObj, String claimRefNO,
			int claimLodgementCheckListDataSave);

	public void saveBlobDocument1(ClaimLodgeBlobModel claimLodgeBlobModelObj, String claimRefNO1, int updatedCheckList);

	public List<ClaimLodgementBean> getSaveClaimLodgementReturnReasonData(
			String cgpan);

	
	//Diksha 
			public List<ClaimLodgementBean> getClaimSettledReport(String userId, Date toDate,
					Date fromDate, String memberId,String mliLongName, String role);
			
			public List<Map<String, Object>> getClaimSettledReportAll(String userId, Date toDate,
					Date fromDate, String role);
				
			
			public List<Map<String, Object>> getClaimDetailsReport(String userId, Date toDate,
					Date fromDate, String memberId, String role,String claimStatus);
			
			public List<Map<String, Object>> getClaimDetailsReportAll(String userId, Date toDate,
					Date fromDate, String role,String claimStatus);
			
    //End
			public int updateClaimReturnResonsRemark(
					String claimrefno, String returnRemark);
			
	
	
}
