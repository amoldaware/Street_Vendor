package com.nbfc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbfc.bean.RecoveryReportBO;
import com.nbfc.bean.RecoveryReportExcelBO;
import com.nbfc.dao.RecoveryReportDao;

@Service
public class ClaimReportServiceImpl implements RecoveryReportService {
	final static Logger logger = Logger.getLogger(ClaimReportServiceImpl.class.getName());

	@Autowired
	private RecoveryReportDao claimReportDao;

	@Override
	public List<RecoveryReportBO> getAll(RecoveryReportBO bo) {
		return claimReportDao.findAll(bo);
	}
	@Override
	public List<RecoveryReportBO> getMliList(String mliId) {
		return claimReportDao.findMliList(mliId);
	}
	@Override
	public List<RecoveryReportExcelBO> getExportToExcelData(RecoveryReportBO bo) {
		return claimReportDao.findExportToExcelData(bo);
	}

}
