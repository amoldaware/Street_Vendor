package com.nbfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.DANAllocationBean;
import com.nbfc.bean.DanCorrespondingDataListBeanCheckerBean;
import com.nbfc.dao.DANAllocationDao;
import com.nbfc.model.DANAllocatioListEntity;
import com.nbfc.model.DANAllocation;
import com.nbfc.model.DANAllocationFee;
import com.nbfc.model.DanAllocationForASFNbfcMakerUsingVWModel;
import com.nbfc.model.DanAllocationForNbfcMakerUsingVWModel;
import com.nbfc.model.DanCorrespondigDataASFModel;
import com.nbfc.model.DanCorrespondigDataModel;
import com.nbfc.model.DanPaymentInitiateASFModel;
import com.nbfc.model.MLICGFeeDetails;
import com.nbfc.model.MliMakerEntity;
@Service("danAllocationService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DANAllocationServiceImpl implements DANAllocationService{

	@Autowired
	DANAllocationDao danAllocationDao;
	
	
	public DANAllocation danListForAllocation(String danID, String status) {
		
		return danAllocationDao.danListForAllocation(danID,status);
	}

	
	public List<MLICGFeeDetails> getPortFiliDetails(List<String> portfolioNameList) {
		// TODO Auto-generated method stub
		return danAllocationDao.getPortFiliDetails(portfolioNameList);
	}

	
	public DANAllocationFee getCGAmoutDetails(String DanId,String status) {
		// TODO Auto-generated method stub
		return danAllocationDao.getCGAmoutDetails(DanId,status);
	}

	
	public List<DANAllocation> danListDANAllocation(List<String> danID,
			String status) {
		
		return danAllocationDao.danListDANAllocation(danID, status);
	}

	
	public List<DANAllocationFee> getCGAmoutDetailsList(List<String> DanId,
			String status) {
		// TODO Auto-generated method stub
		return danAllocationDao.getCGAmoutDetailsList(DanId, status);
	}

	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean addDANAllocatioDetails(List<DANAllocationFee> danAllocatioFee)
			throws NullPointerException {
		
		return danAllocationDao.addDANAllocatioDetails(danAllocatioFee);
	}

	
	public List<DANAllocationFee> getApyIDAmoutDetailsList(List<String> DanId,
			String status) {
		// TODO Auto-generated method stub
		return danAllocationDao.getApyIDAmoutDetailsList(DanId, status);
	}

	
	public List<DanAllocationForNbfcMakerUsingVWModel> getDataForDanAllocation(String mliId) {
		// TODO Auto-generated method stub
		return danAllocationDao.getDataForDanAllocation(mliId);
	}

	
	public DANAllocationBean getRpNumberData(
			List<DanAllocationForNbfcMakerUsingVWModel> allocationList,String userId) {
		return danAllocationDao.getRpNumberData(allocationList,userId);

	}
	public DANAllocationBean getRpNumberDataASF(
			List<DanAllocationForASFNbfcMakerUsingVWModel> allocationList,String userId) {
		return danAllocationDao.getRpNumberDataASF(allocationList,userId);

	}
	
	public List<MLICGFeeDetails> getDisbusCaseDetails(String fileId) {
		
		return danAllocationDao.getDisbusCaseDetails(fileId);
	}

	
	public List<MLICGFeeDetails> getNonDisbusCaseDetails(String fileId) {
		// TODO Auto-generated method stub
		return danAllocationDao.getNonDisbusCaseDetails(fileId);
	}

	
	public List<DanCorrespondingDataListBeanCheckerBean> getDisburseNonDisburseData(
			String fileName, String disbursedStatus) {
		// TODO Auto-generated method stub
		return danAllocationDao.getDisburseNonDisburseData(fileName,disbursedStatus);	
		}

	public List<DanAllocationForASFNbfcMakerUsingVWModel> getDataForDanAllocationASF(String mliId) {
		// TODO Auto-generated method stub
		return danAllocationDao.getDataForDanAllocationASF(mliId);
	}


	@Override
	public List<DanPaymentInitiateASFModel> getApprovedDanDataForPaymentASF(String mliId) {
		// TODO Auto-generated method stub
		return danAllocationDao.getApprovedDanDataForPaymentASF(mliId);
	}
	
}
