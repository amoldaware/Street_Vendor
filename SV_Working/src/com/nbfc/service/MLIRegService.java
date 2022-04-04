package com.nbfc.service;

import java.util.List;

import com.nbfc.model.AudMLiDetails;
import com.nbfc.model.BankDetails;
import com.nbfc.model.MLIEditApproveRejectUpdate;
import com.nbfc.model.MLIEditDetails;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.MLIRegistrationAppraval;
import com.nbfc.model.MLI_UNDERTAKEN_UPLOAD;
import com.nbfc.model.NewMLIRegistration;

public interface MLIRegService {
	
	public List<BankDetails> getBankDetails();
	
	public void addMLIDetails(MLIRegistration mliRegistration);
	
	public void addNEWMLIDetails(NewMLIRegistration mliRegistration);
	
	public List<MLIRegistration> getMLIRegList(String mliName,String status);
	
	public MLIRegistration getMLIDetails(String mliName);
	
	public MLIRegistrationAppraval getMLIDetailsForApproveReject(String mliName);
	
	public void updateMLIDetails(MLIRegistrationAppraval mliRegistration);
	
	public void editMLIDetails(MLIEditDetails mliRegistration);
	
	public MLIEditDetails getMLIDtl(String mliName);
	
	public void audAddMLIDetails(AudMLiDetails mliRegistration);
	
	public AudMLiDetails getMLIAudDetails(String mliName);
	
	public void updateMLIApproveRejectStatus(MLIEditApproveRejectUpdate mliEditApproveRejectUpdate);
	
	public List<MLIRegistration> getMLIListForChecker(String status);

	public void mliUploadFile(MLI_UNDERTAKEN_UPLOAD mli_upload);

}
