package com.nbfc.service;
import java.util.List;
import java.util.Map;

import com.nbfc.bean.FileExcelDataBO;
import com.nbfc.bean.LenderDetailsBO;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.ApplicationDetailsBean;
import com.nbfc.model.LenderBean;

public interface DisbursementDetailsService 
{
	public List<LenderDetailsBO> getLenderDetails() throws BusinessException;
	public String getLenderData(int lenderCode)throws BusinessException;
	public String saveLenderDetails(Map<String,List> lenderDetails)throws BusinessException;
	public int updateLenderDetails()throws BusinessException;
	public List<ApplicationDetailsBean> getDisburmentDetailsData(int lenderCode,String fromDate,String toDate,String applicationNo,String status,String guaranteeIssue)throws BusinessException;
	public List getApllicationNoList(int lenderCode)throws BusinessException;
	public List<ApplicationDetailsBean> getDisburseData(List<FileExcelDataBO> list);
	public void updateMasterTableData();
	public void saveUnmatcherMasterTableData();
}
