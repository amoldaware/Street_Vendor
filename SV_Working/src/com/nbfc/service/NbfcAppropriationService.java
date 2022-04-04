package com.nbfc.service;

import java.util.Date;
import java.util.List;

import com.nbfc.bean.NbfcAppropriationBean;

public interface NbfcAppropriationService {

	List<NbfcAppropriationBean> getDataForAppropriation(Date status);

	int updateAppropriateStatus(List<String> checkedData,
			Date dateOfReconsilation, String userId);

	List getAppropriatePaymentDetails(String paymentId);

	List<NbfcAppropriationBean> getDataBetweenTwoDateForAppropriation(
			Date fromDateHidden1, Date toDateHidden1);

	int updateApprocationDate(String pay_id, String user_id, Date app_date);

	List<NbfcAppropriationBean> getDataForAppropriationASF(Date date1);

	List<NbfcAppropriationBean> getDataBetweenTwoDateForAppropriationASF(Date fromDateHidden1, Date toDateHidden1);

	List getAppropriatePaymentDetailsASF(String paymentId);

}
