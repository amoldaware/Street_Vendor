package com.nbfc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbfc.bean.NbfcAppropriationBean;
import com.nbfc.dao.NbfcAppropriationDao;

@Service("nbfcAppropriationService")
public class NbfcAppropriationServiceImpl implements NbfcAppropriationService{
@Autowired
NbfcAppropriationDao nbfcAppropriationDao;
	
	public List<NbfcAppropriationBean> getDataForAppropriation(Date status) {
		// TODO Auto-generated method stub
		return nbfcAppropriationDao.getDataForAppropriation(status);
	}
	
	public int updateAppropriateStatus(List<String> checkedData,
			Date dateOfReconsilation, String userId) {
		// TODO Auto-generated method stub
		return nbfcAppropriationDao.updateAppropriateStatus(checkedData,dateOfReconsilation,userId);
	}
	
	public List getAppropriatePaymentDetails(String paymentId) {
		// TODO Auto-generated method stub
		return nbfcAppropriationDao.getAppropriatePaymentDetails(paymentId);
	}
	
	public List<NbfcAppropriationBean> getDataBetweenTwoDateForAppropriation(
			Date fromDateHidden1, Date toDateHidden1) {
		// TODO Auto-generated method stub
		return nbfcAppropriationDao.getDataBetweenTwoDateForAppropriation(fromDateHidden1,toDateHidden1);
	}
	
	public int updateApprocationDate(String pay_id, String user_id, Date app_date) {
		return nbfcAppropriationDao.updateApprocationDate(pay_id, user_id, app_date);
	}

	@Override
	public List<NbfcAppropriationBean> getDataForAppropriationASF(Date date1) {
		// TODO Auto-generated method stub
		return nbfcAppropriationDao.getDataForAppropriationASF(date1);
	}

	@Override
	public List<NbfcAppropriationBean> getDataBetweenTwoDateForAppropriationASF(Date fromDateHidden1,
			Date toDateHidden1) {
		// TODO Auto-generated method stub
		return nbfcAppropriationDao.getDataBetweenTwoDateForAppropriationASF(fromDateHidden1,toDateHidden1);
	}

	
	public List getAppropriatePaymentDetailsASF(String paymentId) {
		// TODO Auto-generated method stub
		System.out.println("hiiiiii");
		return nbfcAppropriationDao.getAppropriatePaymentDetailsASF(paymentId);
	}

	
	
}
