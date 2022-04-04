package com.nbfc.dao;

import java.util.List;

import com.nbfc.bean.ASFGenerationDetailBean;

public interface ASFGenerationBulkUploadDao {

	List<ASFGenerationDetailBean> getAllASFDetails(String userid, String fileid);

	List<ASFGenerationDetailBean> getASFDetailsByFileWise(String userId);

}
