package com.nbfc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.CGPANDetailsReportBean;
import com.nbfc.dao.NBFCInvoiceDao;
import com.nbfc.model.ProformaInvoiceDetails;
import com.nbfc.model.ProformaInvoiceDetailsASF;
import com.nbfc.model.TaxDetailMaster;
import com.nbfc.model.TaxInvoiceDetails;
import com.nbfc.model.TaxInvoiceDetailsASF;

@Service("NBFCInvoiceService")
@Transactional
public class NBFCInvoiceServiceImpl implements NBFCInvoiceService{

	@Autowired
	NBFCInvoiceDao nbfcInvoiceDao;
	
	
	public List<TaxDetailMaster> getTaxInvoiceDeails() throws NullPointerException {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.getTaxInvoiceDeails();
	}

	
	public void callTaxInvoiceReport(String dan_id,HttpServletResponse response,HttpServletRequest request,CGPANDetailsReportBean bean) throws JRException {
		
		nbfcInvoiceDao.callTaxInvoiceReport(dan_id,response,request,bean);
	}



	public List<Object> getTaxReportDetails(String dan_id,String uid) {
		return nbfcInvoiceDao.getTaxReportDetails(dan_id,uid);
	}


	public void insertTaxInvoiceDetails(TaxInvoiceDetails taxDetailsObj) {
		try {
			nbfcInvoiceDao.insertTaxInvoiceDetails(taxDetailsObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public Integer getMaxTaxInvoiceIdCount() {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.getMaxTaxInvoiceIdCount();
	}


	@Override
	public Integer getTaxInvoiceIdCount(String dan_id) {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.getTaxInvoiceIdCount(dan_id);
	}


	@Override
	public String getTaxInvoiceNo(String dan_id) {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.getTaxInvoiceNo(dan_id);
	}


	@Override
	public List<Object> getTaxInvoiceData(String mliId,HttpSession session) {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.getTaxInvoiceData(mliId,session);
	}


	@Override
	public List<Object> proformaInvoiceData(String mliId, HttpSession session) {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.proformaInvoiceData(mliId,session);
	}


	@Override
	public Integer getProformaInvoiceIdCount(String dan_id) {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.getProformaInvoiceIdCount(dan_id);
	}


	@Override
	public Integer getMaxProformaInvoiceIdCount() {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.getMaxProformaInvoiceIdCount();
	}


	@Override
	public String getProformaInvoiceNo(String dan_id) {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.getProformaInvoiceNo(dan_id);
	}


	@Override
	public void callProformaInvoiceReport(String dan_id,
			HttpServletResponse response, HttpServletRequest request,
			CGPANDetailsReportBean bean) {
		nbfcInvoiceDao.callProformaInvoiceReport(dan_id,response,request,bean);
		
	}


	@Override
	public void insertProformaInvoiceDetails(ProformaInvoiceDetails proformaObj1) {
		nbfcInvoiceDao.insertProformaInvoiceDetails(proformaObj1);
		
	}


	@Override
	public List<Object> getTaxInvoiceDataASF(String mliId, HttpSession session) {
		return nbfcInvoiceDao.getTaxInvoiceDataASF(mliId,session);
	}


	@Override
	public Integer getTaxInvoiceIdCountASF(String danId) {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.getTaxInvoiceIdCountASF(danId);
	}


	@Override
	public List<Object> getTaxReportDetailsASF(String danId, String userId) {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.getTaxReportDetailsASF(danId);
	}


	@Override
	public void callTaxInvoiceReportASF(String danId, HttpServletResponse response, HttpServletRequest request,
			CGPANDetailsReportBean bean) {
		// TODO Auto-generated method stub
		nbfcInvoiceDao.callTaxInvoiceReportASF(danId,response,request,bean);
	}


	@Override
	public void insertTaxInvoiceDetailsASF(TaxInvoiceDetailsASF taxDetailsObj) {
		// TODO Auto-generated method stub
		nbfcInvoiceDao.insertTaxInvoiceDetailsASF(taxDetailsObj);
	}


	@Override
	public Integer getMaxTaxInvoiceIdCountASF() {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.getMaxTaxInvoiceIdCountASF();
	
	}


	@Override
	public String getTaxInvoiceNoASF(String danId) {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.getTaxInvoiceNoASF(danId);
	}


	@Override
	public List<Object> proformaInvoiceDataASF(String mliId, HttpSession session) {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.proformaInvoiceDataASF(mliId,session);
	}


	@Override
	public Integer getProformaInvoiceIdCountASF(String danId) {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.getProformaInvoiceIdCountASF(danId);
	}


	@Override
	public Integer getMaxProformaInvoiceIdCountASF() {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.getMaxProformaInvoiceIdCountASF();
	}


	@Override
	public void callProformaInvoiceReportASF(String danId, HttpServletResponse response, HttpServletRequest request,
			CGPANDetailsReportBean bean) {
		// TODO Auto-generated method stub
		nbfcInvoiceDao.callProformaInvoiceReportASF(danId,response,request,bean);
	}


	@Override
	public void insertProformaInvoiceDetailsASF(ProformaInvoiceDetailsASF proformaObj1) {
		// TODO Auto-generated method stub
		nbfcInvoiceDao.insertProformaInvoiceDetailsASF(proformaObj1);
	}


	@Override
	public String getProformaInvoiceNoASF(String danId) {
		// TODO Auto-generated method stub
		return nbfcInvoiceDao.getProformaInvoiceNoASF(danId);
	}

}
