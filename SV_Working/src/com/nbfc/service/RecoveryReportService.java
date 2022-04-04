package com.nbfc.service;

import java.util.List;

import com.nbfc.bean.RecoveryReportBO;
import com.nbfc.bean.RecoveryReportExcelBO;

public interface RecoveryReportService {

	List<RecoveryReportBO> getAll(RecoveryReportBO bo);

	List<RecoveryReportBO> getMliList(String mliId);

	List<RecoveryReportExcelBO> getExportToExcelData(RecoveryReportBO bo);

}
