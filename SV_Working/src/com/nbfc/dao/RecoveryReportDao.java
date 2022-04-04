package com.nbfc.dao;

import java.util.List;

import com.nbfc.bean.RecoveryReportBO;
import com.nbfc.bean.RecoveryReportExcelBO;

public interface RecoveryReportDao {

	List<RecoveryReportBO> findAll(RecoveryReportBO bo);

	List<RecoveryReportBO> findMliList(String mliId);
	
	public List<RecoveryReportExcelBO> findExportToExcelData(RecoveryReportBO bo);

}
