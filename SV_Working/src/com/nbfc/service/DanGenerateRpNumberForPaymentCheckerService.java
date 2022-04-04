package com.nbfc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nbfc.bean.DanCorrespondingDataListBeanCheckerBean;
import com.nbfc.model.DanCorrespondigDataModel;
import com.nbfc.model.DanCorressDataToRPNumberCheckerModel;
import com.nbfc.model.DanGenerateRpNumberForPaymentCheckerModel;
import com.nbfc.model.DanPaymentInitiateASFModel;
import com.nbfc.model.DanPaymentInitiateModel;
import com.nbfc.model.DisburseNonDisburseModel;

public interface DanGenerateRpNumberForPaymentCheckerService {

	//public List<DanGenerateRpNumberForPaymentCheckerModel> getDanGenetateDataForAppoval();

	public int getDanRpApproval(List<String> list1);



	public int getDanRpRejection(List<String> list1, String remark);


	public List<DanCorressDataToRPNumberCheckerModel> getDanCorrespondingData(
			String rpNumber,String danId);

	public  List<DanCorrespondingDataListBeanCheckerBean> getDisburseNonDisburseData(String fileId,String danId,String disStatus);

	public List<DanGenerateRpNumberForPaymentCheckerModel> getDanGenetateDataForAppoval(
			List<String> mliLName);

	public int saveDataForPaymentInitiation(
			List<DanGenerateRpNumberForPaymentCheckerModel> initiatedData);

	

	public List<DanCorrespondigDataModel> getApprovedDanData(
			String danId1);
	public List<DanCorrespondigDataModel> getUserReportData(
			String danId1);
	public List<Map<String, Object>> createReport(String userId);
	
	public List<Map<String, Object>> createReportMK(String userId);
	

	public List<DanCorrespondigDataModel> getDanId(String mliId);

	public List<DanPaymentInitiateModel> getApprovedDanDataForPayment(
			String mliId);



	public int getDanCheckerApprovalRejection(
			List<DanGenerateRpNumberForPaymentCheckerModel> initiatedData,
			String remark);



	public List<DanPaymentInitiateModel> getPaymentInitiatedData(List<DanGenerateRpNumberForPaymentCheckerModel> listPaymentInitiation,String mliId);

	public List<Map<String, Object>> getFileData(String FileId);



	public List<DanPaymentInitiateASFModel> getPaymentInitiatedDataASF(
			List<DanGenerateRpNumberForPaymentCheckerModel> listPaymentInitiation, String mliId);
}
