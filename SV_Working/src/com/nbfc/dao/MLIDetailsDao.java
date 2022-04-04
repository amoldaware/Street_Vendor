package com.nbfc.dao;

import java.util.List;

import com.nbfc.bean.MliUploadDetails;
import com.nbfc.model.AudMLiDetails;
import com.nbfc.model.MLIMainEditDetails;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.MLI_UNDERTAKEN_UPLOAD;
import com.nbfc.model.StateMaster;

public interface MLIDetailsDao {

	public List<MLIRegistration> getMLIDetails(String longName,String status);
	
	public List<MliUploadDetails> getMLIDetailsbyFileIndex(String indexFirst,String indexSecond);
	
	public MLIRegistration getMLIDetails(String mliName);
	
	public List<MLIRegistration> getMLIAllDetails();
	
    public MLIMainEditDetails getMLIEditDetails(String longName,String status);
	
	public AudMLiDetails getAUDMLIEditDetails(String longName,String status);
	
	public void updateAUDMLIApproveRejectStatus(
			AudMLiDetails mliEditApproveRejectUpdate);
	
	public List<MLIRegistration> getMLIDetailsForApproval(String status1,String status2,String status3);
	
	public List<MLIRegistration> getMLIDetailsbyIndex(String indexFirst,
			String indexSecond);

	public List<MLIRegistration> ApproverejectMliDetailsByIndex(String nameSearch,
			String searchValue);

	List<MLIMainEditDetails> getApprovedMLIDetails(String status);

	public List<StateMaster> getStatename(String state);

	public List<MliUploadDetails> getUploadFile(String longName, String status);
}
