package com.nbfc.dao;

import java.util.List;

import com.nbfc.bean.DANAllocationBean;
import com.nbfc.bean.DanCorrespondingDataListBeanCheckerBean;
import com.nbfc.model.DANAllocatioListEntity;
import com.nbfc.model.DANAllocation;
import com.nbfc.model.DANAllocationFee;
import com.nbfc.model.DanAllocationForASFNbfcMakerUsingVWModel;
import com.nbfc.model.DanAllocationForNbfcMakerUsingVWModel;
import com.nbfc.model.DanCorrespondigDataASFModel;
import com.nbfc.model.DanPaymentInitiateASFModel;
import com.nbfc.model.MLICGFeeDetails;
import com.nbfc.model.MliMakerEntity;


public interface DANAllocationDao {

	public DANAllocation danListForAllocation(String danID,String status);
	
	public DANAllocationFee getCGAmoutDetails(String DanId,String status);
	
	public List<DANAllocationFee> getCGAmoutDetailsList(List<String> DanId,
			String status);
	
	public boolean addDANAllocatioDetails(List<DANAllocationFee> danAllocatioFee) throws NullPointerException;
	
	public List<MLICGFeeDetails> getPortFiliDetails(List<String> portfolioNameList);
	
	public List<DANAllocation> danListDANAllocation(List<String> danID,
			String status);
	
	public List<DANAllocationFee> getApyIDAmoutDetailsList(List<String> DanId,
			String status);

	public List<DanAllocationForNbfcMakerUsingVWModel> getDataForDanAllocation(String mliId);

	public DANAllocationBean getRpNumberData(
			List<DanAllocationForNbfcMakerUsingVWModel> allocationList,String userId);
	
	public List<MLICGFeeDetails> getDisbusCaseDetails(String fileId);
	
	public List<MLICGFeeDetails> getNonDisbusCaseDetails(String fileId);

	public List<DanCorrespondingDataListBeanCheckerBean> getDisburseNonDisburseData(
			String fileName, String disbursedStatus);

	public List<DanAllocationForASFNbfcMakerUsingVWModel> getDataForDanAllocationASF(String mliId);

	public DANAllocationBean getRpNumberDataASF(List<DanAllocationForASFNbfcMakerUsingVWModel> allocationList,
			String userId);

	public List<DanPaymentInitiateASFModel> getApprovedDanDataForPaymentASF(String mliId);




}
