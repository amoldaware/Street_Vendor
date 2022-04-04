package com.nbfc.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.ASFGenerationDetailBean;
import com.nbfc.bean.ClaimLodgementBean;
import com.nbfc.dao.ClaimLodgementDao;
import com.nbfc.model.ClaimDetailsModel;
import com.nbfc.model.ClaimLodgeBlobModel;
import com.nbfc.model.ClaimReturnReasonsModel;
import com.nbfc.model.MLIName;
import com.nbfc.model.documentModel;

@Service("ClaimLodgementService")
@Transactional()
public class ClaimLodgementServiceImpl implements ClaimLodgementService {
	@Autowired
	ClaimLodgementDao claimLodgementDao;
	
	/*public String getLoginUserMemId(String userId) {
		return claimLodgementDao.getLoginUserMemId(userId);
	}*/

	
	

	@Override
	public List<ClaimLodgementBean> getClaimLoadgmentDetails(
			String loginUserMemId) {
		// TODO Auto-generated method stub
		return claimLodgementDao.getClaimLoadgmentDetails(loginUserMemId);
	}

	@Override
	public ClaimLodgementBean getClaimRefNoDetails(String claimRefNo) {
		// TODO Auto-generated method stub
		return claimLodgementDao.getClaimRefNoDetails(claimRefNo);
	}

	

	@Override
	public ClaimLodgementBean updateStatusClaimLodgeApprovedReturn(String userId,
			Map<String, Object> claimStatusMapObj,String Urole) {
		// TODO Auto-generated method stub
		return claimLodgementDao.updateStatusClaimLodgeApprovedReturn(userId,claimStatusMapObj,Urole);
	}

	@Override
	public List<ClaimLodgementBean> getClaimLoadgmentDetailsForApproval(
			String MLIID,String cNT) {
		// TODO Auto-generated method stub
		return claimLodgementDao.getClaimLoadgmentDetailsForApproval(MLIID,cNT);

	}

	@Override
	public List<ClaimLodgementBean> claimLoadgementApprovalCGTMSEMLIWise(
			String userId) {
		// TODO Auto-generated method stub
		return claimLodgementDao.claimLoadgementApprovalCGTMSEMLIWise(userId);
	}

	@Override
	public ClaimLodgementBean getClaimChecklistDetails(String claimRefNo) {
		// TODO Auto-generated method stub
		return claimLodgementDao.getClaimChecklistDetails(claimRefNo);
	}

	@Override
	public documentModel DownloadBlobDoc(String claimRefNo) throws FileNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
	
		return claimLodgementDao.DownloadBlobDoc(claimRefNo);
	
	}
	public ClaimLodgementBean getMliBorrowerNpaDtlsBeforClaimLodgement(String cgPan) {
		return claimLodgementDao.getMliBorrowerNpaDtlsBeforClaimLodgement(cgPan);
	}
	
	/*public String saveClaimLodgmentData(ClaimLodgementBean claimLodgementBean,String userId) {
		return  claimLodgementDao.saveClaimLodgmentData(claimLodgementBean,userId);
	}*/
	public String saveClaimLodgmentData(ClaimLodgementBean claimLodgementBean,String memberId,String loginUserId) {
		return  claimLodgementDao.saveClaimLodgmentData(claimLodgementBean,memberId,loginUserId);
}
	
	public ClaimLodgementBean getSaveClaimLodgmentData(String cgPan) {
		return claimLodgementDao.getSaveClaimLodgmentData(cgPan);
	}
	
//	public int saveClaimLodgementCheckListData(ClaimLodgementBean claimLodgementBean,String userId) {
//		return  claimLodgementDao.saveClaimLodgementCheckListData(claimLodgementBean,userId);
//	}
	
	public ClaimLodgementBean getSaveClaimLodgementCheckListData(String cgPan) {
		return claimLodgementDao.getSaveClaimLodgementCheckListData(cgPan);
	}

//	@Override
//	public void saveBlobDocument(documentModel document) {
//		// TODO Auto-generated method stub
//	claimLodgementDao.saveBlobDocument(document);
//	}

	@Override
	public ClaimLodgementBean getCGPANForClaim(String cgpan, String userId) {
		// TODO Auto-generated method stub
		return claimLodgementDao.getCGPANForClaim(cgpan,userId);
	}

	@Override
	public ClaimLodgementBean checkDuplicateCGPANForClaim(String cgpan,
			String userId) {
		// TODO Auto-generated method stub
		return claimLodgementDao.checkDuplicateCGPANForClaim(cgpan,userId);
	}

	@Override
	public ClaimLodgementBean getClaimChecklistRecommndation(String claimRefNo) {
		// TODO Auto-generated method stub
		return claimLodgementDao.getClaimChecklistRecommndation(claimRefNo);
	}
	@Override
	public List<ClaimLodgementBean> getClaimReturnedRecordsByNBFCChecker(String loginUserMemId){
			return claimLodgementDao.getClaimReturnedRecordsByNBFCChecker(loginUserMemId);
	}
	
	public String updateClaimLodgmentData(ClaimLodgementBean claimLodgementBean,String claimRefNod,String loginUserId) {
		return  claimLodgementDao.updateClaimLodgmentData(claimLodgementBean,claimRefNod,loginUserId);
	}
	
	public int updateClaimLodgementCheckListData(ClaimLodgementBean claimLodgementBean,String claimRefNo) {
		return  claimLodgementDao.updateClaimLodgementCheckListData(claimLodgementBean,claimRefNo);
	}

	@Override
	public ClaimLodgementBean getRecommandationCGS(String claimRefNo) {
		// TODO Auto-generated method stub
		return  claimLodgementDao.getRecommandationCGS(claimRefNo);
	}

	@Override
	public MLIName getMLIName(String mLIID) {
		// TODO Auto-generated method stub
		return  claimLodgementDao.getMLIName(mLIID);
	}

	@Override
	public ClaimLodgementBean getClaimCheckerDetails(String claimRefNo) {
		// TODO Auto-generated method stub
		return  claimLodgementDao.getClaimCheckerDetails(claimRefNo);
	}

	@Override
	public List<ClaimLodgementBean>  getClaimReturnReasons() {
		// TODO Auto-generated method stub
		return  claimLodgementDao.getClaimReturnReasons();
	}

	@Override
	public String submitForClaimReturnResons(List<ClaimLodgementBean> claimLodgList,String returnRemark) {
		// TODO Auto-generated method stub
		return  claimLodgementDao.submitForClaimReturnResons(claimLodgList,returnRemark);
	}

	@Override
	public List<ClaimLodgementBean> getClaimReturnedRecordsByNBFCCheckerCGS(
			String loginUserMemId) {
		// TODO Auto-generated method stub
		return  claimLodgementDao.getClaimReturnedRecordsByNBFCCheckerCGS(loginUserMemId);
	}

	@Override
	public List<ClaimLodgementBean> getClaimLoadgmentDetailsCGS(
			String loginUserMemId) {
		// TODO Auto-generated method stub
		return  claimLodgementDao.getClaimLoadgmentDetailsCGS(loginUserMemId);
	}

	@Override
	public int saveClaimLodgementCheckListData(ClaimLodgementBean claimLodgementBean, String memberId,
			String loginUserId) {
		// TODO Auto-generated method stub
		return  claimLodgementDao.saveClaimLodgementCheckListData(claimLodgementBean,memberId,loginUserId);
	}

	@Override
	public void saveBlobDocument(ClaimLodgeBlobModel claimLodgeBlobModelObj, String claimRefNO,
			int claimLodgementCheckListDataSave) {
		// TODO Auto-generated method stub
		 claimLodgementDao.saveBlobDocument(claimLodgeBlobModelObj,claimRefNO,claimLodgementCheckListDataSave);
	}

	@Override
	public void saveBlobDocument1(ClaimLodgeBlobModel claimLodgeBlobModelObj, String claimRefNO1,
			int updatedCheckList) {
		// TODO Auto-generated method stub
		 claimLodgementDao.saveBlobDocument1(claimLodgeBlobModelObj,claimRefNO1,updatedCheckList);
	}

	@Override
	public List<ClaimLodgementBean> getSaveClaimLodgementReturnReasonData(
			String cgpan) {
		// TODO Auto-generated method stub
		 return claimLodgementDao.getSaveClaimLodgementReturnReasonData(cgpan);
	}
	
	//Diksha
	
			public List<ClaimLodgementBean> getClaimSettledReport(String userId, Date toDate,
					Date fromDate, String member,String mliLongName,String role) {
				// TODO Auto-generated method stub
				return claimLodgementDao.getClaimSettledReport(userId, toDate, fromDate, member,mliLongName,role);
			}

			public List<Map<String, Object>> getClaimSettledReportAll(String userId, Date toDate,
					Date fromDate, String role) {
				// TODO Auto-generated method stub
				return claimLodgementDao.getClaimSettledReportAll(userId, toDate, fromDate,role);
			}
			
			public List<Map<String, Object>> getClaimDetailsReport(String userId, Date toDate,
					Date fromDate, String member,String role,String claimStatus) {
				// TODO Auto-generated method stub
				return claimLodgementDao.getClaimDetailsReport(userId, toDate, fromDate, member,role,claimStatus);
			}
			
			public List<Map<String, Object>> getClaimDetailsReportAll(String userId, Date toDate,
					Date fromDate, String role,String claimStatus) {
				// TODO Auto-generated method stub
				return claimLodgementDao.getClaimDetailsReportAll(userId, toDate, fromDate,role,claimStatus);
			}
			
			//end
			
			
			@Override
			public int updateClaimReturnResonsRemark(String claimrefno,
					String returnRemark) {
				// TODO Auto-generated method stub
				return claimLodgementDao.updateClaimReturnResonsRemark(claimrefno,returnRemark);
			}
			
			
			
			
			

}
