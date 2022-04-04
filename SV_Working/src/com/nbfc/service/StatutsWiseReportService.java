package com.nbfc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.nbfc.bean.ApplicationStatusDetailsBean;
import com.nbfc.bean.MliWiseReportDetailBean;

public interface StatutsWiseReportService {
	public ArrayList<Object[]> StatutsWiseReport(String userId,String toDate,String fromDate,String status);
	public List<Map<String, Object>> getStatutsDetails(String userId,String role,Date toDate,Date fromDate,String status);
    public List<Map<String, Object>> getMliWeise(String role,String userid, Date fromDate,Date toDate, String MliId);
    public List<Object[]> getMliWiseDate(String role, String userid, String fromDate, String toDate, String MliId);
    public List<Object[]> getStatutsWiseData(String userId,String role, String toDate, String fromDate, String statuts);
    public List<MliWiseReportDetailBean> getMliWeiseReport(String role,String userid, Date fromDate,Date toDate, String MliId);
    public List<Map<String, Object>> getAsfReportDetail(String role,String userid, Date fromDate,Date toDate, String MliId);
    public List<MliWiseReportDetailBean> getASFfReportDetail(String role,String userid, Date fromDate,Date toDate, String MliId);
    public List<Map<String, Object>> getDanASFDetail(String role,String userid, Date fromDate,Date toDate, String MliId,String SsName);
    public List<MliWiseReportDetailBean> getDanGfReportDowanload(String role, String userid, Date fromDate, Date toDate, String MliId,String ssName);
    public List<Object[]> getDANGFDetails(String role, String userid, String fromDate, String toDate, String MliId,String ssName);
    public List<Object[]> getDanId(String DanId);
    public ApplicationStatusDetailsBean getapplicationDetails(String FileId);
    public List<Object[]> getDANASFDetails(String role, String userid, String fromDate, String toDate, String MliId, String ssName);
    public List<Object[]> getDanIdForASF(String DanId);
    public String getMEMBNKID(String UserId);
    public Map<String ,String> getBankName(String BankId);
    public Map<String,String> getAllMliLongName();
}
