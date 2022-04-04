package com.nbfc.service;

import java.util.List;

import com.nbfc.model.AudMLiDetails;
import com.nbfc.model.MLIEditApproveRejectUpdate;
import com.nbfc.model.MLIMainEditDetails;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.StateMaster;

public interface MLIDetailsService {

	
	public List<MLIRegistration> getMLIDetails(String longName,String status);
	
	public List<MLIRegistration> getMLIAllDetails();
	
	public MLIRegistration getMLIDetails(String mliName);
	
	public MLIMainEditDetails getMLIEditDetails(String longName,String status);
	
	public AudMLiDetails getAUDMLIEditDetails(String longName,String status);
	
	public void updateAUDMLIApproveRejectStatus(AudMLiDetails mliEditApproveRejectUpdate);
	
	public List<MLIRegistration> getMLIDetailsForApproval(String status1,String status2,String status3);
	
	public List<MLIRegistration> getMLIDetailsbyIndex(String indexFirst,String indexSecond);
	
	public List<MLIMainEditDetails> getApprovedMLIDetails(String status);


	public List<MLIRegistration> ApproverejectMliDetailsByIndex(
			String nameSearch, String searchValue);

	public List<StateMaster> getStatename(String state);
		
}
