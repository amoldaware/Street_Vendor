package com.nbfc.service;

import java.util.List;

import com.nbfc.bean.ASFGenerationDetailBean;

public interface ASFGenerationBulkUploadService {

	List<ASFGenerationDetailBean> getAllASFDetails(String userId, String fileid);

	List<ASFGenerationDetailBean> getASFDetailsByFileWise(String userId);

}
