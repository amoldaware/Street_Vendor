package com.nbfc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.DanCorrespondingDataListBeanCheckerBean;
import com.nbfc.dao.DanGenerateRpNumberForPaymentCheckerDao;
import com.nbfc.model.DanCorrespondigDataModel;
import com.nbfc.model.DanCorressDataToRPNumberCheckerModel;
import com.nbfc.model.DanGenerateRpNumberForPaymentCheckerModel;
import com.nbfc.model.DanPaymentInitiateASFModel;
import com.nbfc.model.DanPaymentInitiateModel;
import com.nbfc.model.DisburseNonDisburseModel;

@Service("DanGenerateRpNumberForPaymentCheckerService")
@Transactional()
public class DanGenerateRpNumberForPaymentCheckerServiceImpl implements DanGenerateRpNumberForPaymentCheckerService{
@Autowired
DanGenerateRpNumberForPaymentCheckerDao  danGenerateRpNumberForPaymentCheckerDao;
	
	public List<DanGenerateRpNumberForPaymentCheckerModel> getDanGenetateDataForAppoval(List<String> portfolioName) {
		// TODO Auto-generated method stub
		return danGenerateRpNumberForPaymentCheckerDao.getDanGenetateDataForAppoval(portfolioName);
	}
	
	public int getDanRpApproval(List<String> list1) {
		// TODO Auto-generated method stub
		return danGenerateRpNumberForPaymentCheckerDao.getDanRpApproval(list1);
	}
	
	
	public int getDanRpRejection(List<String> list1, String remark) {
		// TODO Auto-generated method stub
		return danGenerateRpNumberForPaymentCheckerDao.getDanRpRejection(list1,remark);
	}
	
	public List<DanCorressDataToRPNumberCheckerModel> getDanCorrespondingData(
			String rpNumber,String danId) {
		// TODO Auto-generated method stub
		return danGenerateRpNumberForPaymentCheckerDao.getDanCorrespondingData(rpNumber,danId);
	}
	
	public List<DanCorrespondingDataListBeanCheckerBean> getDisburseNonDisburseData(String fileid,String danId,String disStatus) {
		return danGenerateRpNumberForPaymentCheckerDao.getDisburseNonDisburseData(fileid,danId,disStatus);
		// TODO Auto-generated method stub
	}
	
	public int saveDataForPaymentInitiation(
			List<DanGenerateRpNumberForPaymentCheckerModel> initiatedData) {
		// TODO Auto-generated method stub
		return danGenerateRpNumberForPaymentCheckerDao.saveDataForPaymentInitiation(initiatedData);
	}
	
	public List<DanCorrespondigDataModel> getApprovedDanData(String danId1) {
		// TODO Auto-generated method stub
		return danGenerateRpNumberForPaymentCheckerDao.getApprovedDanData(danId1);
	}
	
	public List<DanCorrespondigDataModel> getDanId(String mliId) {
		// TODO Auto-generated method stub
		return danGenerateRpNumberForPaymentCheckerDao.getDanId(mliId);
	}
	
	public List<DanPaymentInitiateModel> getApprovedDanDataForPayment(
			String mliId) {
		// TODO Auto-generated method stub
		return danGenerateRpNumberForPaymentCheckerDao.getApprovedDanDataForPayment(mliId);
	}
	
	public int getDanCheckerApprovalRejection(
			List<DanGenerateRpNumberForPaymentCheckerModel> initiatedData,
			String remark) {
		// TODO Auto-generated method stub
		return danGenerateRpNumberForPaymentCheckerDao.getDanCheckerApprovalRejection(initiatedData,remark);
	}
	
	public List<DanPaymentInitiateModel> getPaymentInitiatedData(List<DanGenerateRpNumberForPaymentCheckerModel> list,String mliId) {
		// TODO Auto-generated method stub
		return danGenerateRpNumberForPaymentCheckerDao.getPaymentInitiatedData(list,mliId);
	}
	
	public List<DanCorrespondigDataModel> getUserReportData(String danId1) {
		// TODO Auto-generated method stub
		return danGenerateRpNumberForPaymentCheckerDao.getUserReportData(danId1);
	}
	
	public List<Map<String, Object>> createReport( String userId) {
		
		return danGenerateRpNumberForPaymentCheckerDao.createReport(userId);
	}
	
	
	
	public List<Map<String, Object>> getFileData(String FileId) {
		// TODO Auto-generated method stub
		return danGenerateRpNumberForPaymentCheckerDao.getFileData(FileId);
	}
	
	public List<Map<String, Object>> createReportMK(String userId) {
		// TODO Auto-generated method stub
		return danGenerateRpNumberForPaymentCheckerDao.createReportMK(userId);
	}

	@Override
	public List<DanPaymentInitiateASFModel> getPaymentInitiatedDataASF(
			List<DanGenerateRpNumberForPaymentCheckerModel> list, String mliId) {
		// TODO Auto-generated method stub
		return danGenerateRpNumberForPaymentCheckerDao.getPaymentInitiatedDataASF(list,mliId);
	}
	
	

}
