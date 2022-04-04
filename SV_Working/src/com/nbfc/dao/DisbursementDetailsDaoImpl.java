package com.nbfc.dao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.nbfc.bean.FileExcelDataBO;
import com.nbfc.bean.LenderDetailsBO;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.*;
//SARITA - 11022022
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository("DisbursementDetailsDao")
@Transactional
public class DisbursementDetailsDaoImpl implements DisbursementDetailsDao
{
	@Autowired
	private SessionFactory sessionFactory;
	
	static Logger log = Logger.getLogger(DisbursementDetailsDaoImpl.class.getName());

	@Override
	public List<LenderDetailsBO> getLenderDetails() throws BusinessException 
	{
		Session session = null;
		List<LenderDetailsBO> lbData = new ArrayList<>();
		String query = "";
		try {
			session = sessionFactory.openSession();
			query = "select BankName,BankCode from  lendercodes";
			SQLQuery sql = session.createSQLQuery(query);
			List<String> list = sql.list();
			if (list != null && list.size() > 0) {
				Iterator itr = list.iterator();
				while (itr.hasNext()) {
					Object obj[] = (Object[]) itr.next();
					LenderDetailsBO lbData1 = new LenderDetailsBO();
					lbData1.setBankName(String.valueOf(obj[0]));
					lbData1.setLenderCode(Integer.valueOf(String.valueOf(obj[1])));
					lbData.add(lbData1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return lbData;
	}

	@Override
	public String saveLenderDetails(Map<String, List> lenderDetails) throws BusinessException 
	{
		
		Session session = null;
		List<ApplicationDetailsBean> applicationDetailsBeanList1 = new ArrayList<>();
		List<ApplicationDetailsBean> ApplicationDetailsList = null;
		List<PersonalDetailsBean> PersonalDetailsList = null;
		List<BankAccountDetailsBean> BankAccountDetailsList = null;
		List<AreaOfVendingBean> BusinessDetailsList = null;
		List<PermanentAddressBean> PermanentAddressList = null;
		List<CurrentAddressBean> CurrentAddressList = null;
		List<LenderBean> LenderDetailsList = null;
		String returStr = "";
		try
		{
			//session = sessionFactory.openSession();
			for (String data : lenderDetails.keySet()) 
		    {
				ApplicationDetailsList = lenderDetails.get("ApplicationDetails");
				PersonalDetailsList = lenderDetails.get("PersonalDetails");
				BankAccountDetailsList = lenderDetails.get("BankAccountDetails");
				BusinessDetailsList = lenderDetails.get("BusinessDetails");
				PermanentAddressList = lenderDetails.get("PermanentAddress");
				CurrentAddressList = lenderDetails.get("CurrentAddress");
				LenderDetailsList = lenderDetails.get("LenderDetails");
			 }
			int count = 0;  
			for(ApplicationDetailsBean applicationDetailsBeanList : ApplicationDetailsList)
			{
				applicationDetailsBeanList.setPersonalDetails(PersonalDetailsList.get(count));
				applicationDetailsBeanList.setBankAccountDetails(BankAccountDetailsList.get(count));
				applicationDetailsBeanList.setAreaOfVending(BusinessDetailsList.get(count));
				applicationDetailsBeanList.setLenderPermanentAddress(PermanentAddressList.get(count));
				applicationDetailsBeanList.setLenderCurrentAddress(CurrentAddressList.get(count));
				applicationDetailsBeanList.setLenderBean(LenderDetailsList.get(count));
				applicationDetailsBeanList1.add(applicationDetailsBeanList);
				count++;
			}
			
			//applicationDetailsBeanList1
			//
			
			for (Object mliEntity : applicationDetailsBeanList1) 
			{
				sessionFactory.getCurrentSession().saveOrUpdate(mliEntity);
				sessionFactory.getCurrentSession().flush();
				sessionFactory.getCurrentSession().clear();
				//sessionFactory.getCurrentSession().saveOrUpdate(mliEntity);
			}
			returStr = "Data Saved Successfully";
		}
		catch(Exception e){
			returStr = "Error Occurred!!";
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
		return returStr;
	}

	@Override
	public List<ApplicationDetailsBean> getDisburmentDetailsData(int lenderCode,String fromDate,String toDate,String applicationNo,String status,String guaranteeIssue) throws BusinessException 
	{
		Session session = null;
		List<ApplicationDetailsBean> lbData = new ArrayList<>();
		List<String> disbDate = new ArrayList<>();
		List<String> guaranteeData = new ArrayList<>();
		String query = "";
		LenderBean lenderBean = new LenderBean();
		List<String> guaIssData = new ArrayList<>();
		try {
			Query q = sessionFactory.getCurrentSession().createQuery(
					"from ApplicationDetailsBean where lender_lenderCode =:lenderCode order by disbursedDt asc");

			q.setParameter("lenderCode", lenderCode);
			lbData = q.list();
			for (ApplicationDetailsBean data : lbData) {
				disbDate.add(data.getDisbursedDt());
			}
			String newDisbStrDate = disbDate.get(0);
			String newDisbLstDate = disbDate.get(disbDate.size() - 1);

			if (lenderCode == 1) 
			{
				lbData.clear();
				Query queryObject1 = sessionFactory.getCurrentSession().createQuery(
						"from ApplicationDetailsBean where disbursedDt >=:fromDt and disbursedDt <=:toDt");
				queryObject1.setParameter("fromDt",fromDate);
				queryObject1.setParameter("toDt",toDate);
				lbData = queryObject1.list();
			} 
			else {
				if ((fromDate == null || fromDate.trim().length() == 0)
						&& (toDate == null || toDate.trim().length() == 0)
						&& (applicationNo == null || applicationNo.trim().length() == 0)
						&& ("0".equalsIgnoreCase(status)) && ("0".equalsIgnoreCase(guaranteeIssue))) {
					lbData.clear();
					Query queryObject = sessionFactory.getCurrentSession()
							.createQuery("from ApplicationDetailsBean where lender_lenderCode =:lenderCode");

					queryObject.setParameter("lenderCode", lenderCode);
					lbData = queryObject.list();
				} else 
				{
					lbData.clear();
					if ((fromDate != null && fromDate.trim().length() > 0)
							&& (toDate != null && toDate.trim().length() > 0)
							&& (applicationNo == null || applicationNo.trim().length() == 0)
							&& ("0".equalsIgnoreCase(status))) {
						Query queryObject1 = sessionFactory.getCurrentSession().createQuery(
								"from ApplicationDetailsBean where lender_lenderCode =:lenderCode and disbursedDt >=:fromDt and disbursedDt <=:toDt");
						queryObject1.setParameter("lenderCode", lenderCode);
						queryObject1.setParameter("fromDt", (fromDate != "" ? fromDate : newDisbStrDate));
						queryObject1.setParameter("toDt", (toDate != "" ? toDate : newDisbLstDate));
						lbData = queryObject1.list();
					} else if (applicationNo != null && applicationNo.trim().length() > 0
							&& ("0".equalsIgnoreCase(status))) {
						lbData.clear();
						Query queryObject1 = sessionFactory.getCurrentSession().createQuery(
								"from ApplicationDetailsBean where lender_lenderCode =:lenderCode and disbursedDt >=:fromDt and disbursedDt <=:toDt and applicationNo =:applicationNo");
						queryObject1.setParameter("lenderCode", lenderCode);
						queryObject1.setParameter("fromDt", (fromDate != "" ? fromDate : newDisbStrDate));
						queryObject1.setParameter("toDt", (toDate != "" ? toDate : newDisbLstDate));
						queryObject1.setParameter("applicationNo", applicationNo);
						lbData = queryObject1.list();
					} else if (!"0".equalsIgnoreCase(status)) {
						lbData.clear();
						if(applicationNo != null && applicationNo.trim().length() > 0){
							Query queryObject1 = sessionFactory.getCurrentSession().createQuery(
									"from ApplicationDetailsBean where lender_lenderCode =:lenderCode and disbursedDt >=:fromDt and disbursedDt <=:toDt and applicationNo =:applicationNo  and status =:status");
							queryObject1.setParameter("lenderCode", lenderCode);
							queryObject1.setParameter("fromDt", (fromDate != "" ? fromDate : newDisbStrDate));
							queryObject1.setParameter("toDt", (toDate != "" ? toDate : newDisbLstDate));
							queryObject1.setParameter("applicationNo", applicationNo);
							queryObject1.setParameter("status", status);
							lbData = queryObject1.list();
						}
						else{
							Query queryObject1 = sessionFactory.getCurrentSession().createQuery(
									"from ApplicationDetailsBean where lender_lenderCode =:lenderCode and disbursedDt >=:fromDt and disbursedDt <=:toDt and status =:status");
							queryObject1.setParameter("lenderCode", lenderCode);
							queryObject1.setParameter("fromDt", (fromDate != "" ? fromDate : newDisbStrDate));
							queryObject1.setParameter("toDt", (toDate != "" ? toDate : newDisbLstDate));
							queryObject1.setParameter("status", status);
							lbData = queryObject1.list();
						}
					} else if (guaranteeIssue != null && guaranteeIssue.trim().length() > 0) {
						lbData.clear();
						if ("Yes".equalsIgnoreCase(guaranteeIssue)) {
							Query queryObject1 = sessionFactory.getCurrentSession().createSQLQuery(
									"select aadhaarNo,accountNo,applicant_DOB,applicant_Gender,applicant_Name,applicationDate,applicationNo,bankName,branchName,category,curr_houseNo,curr_pin,curr_state,curr_town_Dist,curr_village,disbursedAmount,disbursedDt,durationOfVending,ifsc,isDisbursed,lender_bankName,lender_lenderCode,lenderBranch,lenderName,loanAccountNumber,loanTenure,loanTerm,maritalStatusName,memberOfCIGGLG,minorityCommunity,mli_id,mobileNo,mobileVendor,moratoriumMonths,nameOfCIGGLG,nearestMobileLM,per_houseNo,per_pin,per_state,per_town_Dist,per_village,pwd,rateOfInterest,sanctionedAmount,sanctionedDt,stationaryVendor,status,svaVendingActivityName,ulbName,vendorType,voterIDCardNo,guaranteeReady from disbursement_details WHERE lender_lenderCode=:lenderCode AND EXISTS (SELECT AadharNo FROM nbfc_street_vendor_master WHERE disbursement_details.applicationNo = nbfc_street_vendor_master.AadharNo)");
							queryObject1.setParameter("lenderCode", lenderCode);
							guaIssData = queryObject1.list();
						} else {
							Query queryObject1 = sessionFactory.getCurrentSession().createSQLQuery(
									"select aadhaarNo,accountNo,applicant_DOB,applicant_Gender,applicant_Name,applicationDate,applicationNo,bankName,branchName,category,curr_houseNo,curr_pin,curr_state,curr_town_Dist,curr_village,disbursedAmount,disbursedDt,durationOfVending,ifsc,isDisbursed,lender_bankName,lender_lenderCode,lenderBranch,lenderName,loanAccountNumber,loanTenure,loanTerm,maritalStatusName,memberOfCIGGLG,minorityCommunity,mli_id,mobileNo,mobileVendor,moratoriumMonths,nameOfCIGGLG,nearestMobileLM,per_houseNo,per_pin,per_state,per_town_Dist,per_village,pwd,rateOfInterest,sanctionedAmount,sanctionedDt,stationaryVendor,status,svaVendingActivityName,ulbName,vendorType,voterIDCardNo,guaranteeReady from disbursement_details WHERE lender_lenderCode=:lenderCode AND NOT EXISTS (SELECT AadharNo FROM nbfc_street_vendor_master WHERE disbursement_details.applicationNo = nbfc_street_vendor_master.AadharNo)");
							queryObject1.setParameter("lenderCode", lenderCode);
							guaIssData = queryObject1.list();
						}

						if (guaIssData != null && guaIssData.size() > 0) {
							Iterator itr = guaIssData.iterator();

							while (itr.hasNext()) {
								Object[] o = (Object[]) itr.next();
								ApplicationDetailsBean applicationDetailsBean = new ApplicationDetailsBean();
								PersonalDetailsBean personalDetailsBean = new PersonalDetailsBean();
								PermanentAddressBean permanentAddressBean = new PermanentAddressBean();
								CurrentAddressBean currentAddressBean = new CurrentAddressBean();
								BankAccountDetailsBean bankAccountDetailsBean = new BankAccountDetailsBean();
								AreaOfVendingBean areaOfVendingBean = new AreaOfVendingBean();

								String aadhaarNo = String.valueOf(o[0]);
								String accountNo = String.valueOf(o[1]);
								String applicant_DOB = String.valueOf(o[2]);
								String applicant_Gender = String.valueOf(o[3]);
								String applicant_Name = String.valueOf(o[4]);
								String applicationDate = String.valueOf(o[5]);
								String applicationNo1 = String.valueOf(o[6]);
								String bankName = String.valueOf(o[7]);
								String branchName = String.valueOf(o[8]);
								String category = String.valueOf(o[9]);
								String curr_houseNo = String.valueOf(o[10]);
								String curr_pin = String.valueOf(o[11]);
								String curr_state = String.valueOf(o[12]);
								String curr_town_Dist = String.valueOf(o[13]);
								String curr_village = String.valueOf(o[14]);
								String disbursedAmount = String.valueOf(o[15]);
								String disbursedDt = String.valueOf(o[16]);
								String durationOfVending = String.valueOf(o[17]);
								String ifsc = String.valueOf(o[18]);
								String isDisbursed = String.valueOf(o[19]);
								String lender_bankName = String.valueOf(o[20]);
								String lender_lenderCode = String.valueOf(o[21]);
								String lenderBranch = String.valueOf(o[22]);
								String lenderName = String.valueOf(o[23]);
								String loanAccountNumber = String.valueOf(o[24]);
								String loanTenure = String.valueOf(o[25]);
								String loanTerm = String.valueOf(o[26]);
								String maritalStatusName = String.valueOf(o[27]);
								String memberOfCIGGLG = String.valueOf(o[28]);
								String minorityCommunity = String.valueOf(o[29]);
								String mli_id = String.valueOf(o[30]);
								String mobileNo = String.valueOf(o[31]);
								String mobileVendor = String.valueOf(o[32]);
								String moratoriumMonths = String.valueOf(o[33]);
								String nameOfCIGGLG = String.valueOf(o[34]);
								String nearestMobileLM = String.valueOf(o[35]);
								String per_houseNo = String.valueOf(o[36]);
								String per_pin = String.valueOf(o[37]);
								String per_state = String.valueOf(o[38]);
								String per_town_Dist = String.valueOf(o[39]);
								String per_village = String.valueOf(o[40]);
								String pwd = String.valueOf(o[41]);
								String rateOfInterest = String.valueOf(o[42]);
								String sanctionedAmount = String.valueOf(o[43]);
								String sanctionedDt = String.valueOf(o[44]);
								String stationaryVendor = String.valueOf(o[45]);
								String status1 = String.valueOf(o[46]);
								String svaVendingActivityName = String.valueOf(o[47]);
								String ulbName = String.valueOf(o[48]);
								String vendorType = String.valueOf(o[49]);
								String voterIDCardNo = String.valueOf(o[50]);
								String guaranteeReady = String.valueOf(o[51]);
								

								personalDetailsBean.setAadhaarNo(aadhaarNo);
								personalDetailsBean.setApplicant_DOB(applicant_DOB);
								personalDetailsBean.setApplicant_Gender(applicant_Gender);
								personalDetailsBean.setApplicant_Name(applicant_Name);
								personalDetailsBean.setMaritalStatusName(maritalStatusName);
								personalDetailsBean.setMinorityCommunity(minorityCommunity);
								personalDetailsBean.setMobileNo(mobileNo);
								personalDetailsBean.setPwd(pwd);

								permanentAddressBean.setHouseNo(per_houseNo);
								permanentAddressBean.setPin(per_pin);
								permanentAddressBean.setState(per_state);
								permanentAddressBean.setTown_Dist(per_town_Dist);
								permanentAddressBean.setVillage(per_village);

								currentAddressBean.setHouseNo(curr_houseNo);
								currentAddressBean.setPin(curr_pin);
								currentAddressBean.setState(curr_state);
								currentAddressBean.setTown_Dist(curr_town_Dist);
								currentAddressBean.setVillage(curr_village);

								bankAccountDetailsBean.setAccountNo(accountNo);
								bankAccountDetailsBean.setBankName(bankName);
								bankAccountDetailsBean.setBranchName(branchName);
								bankAccountDetailsBean.setIfsc(ifsc);

								areaOfVendingBean.setMobileVendor(mobileVendor);
								areaOfVendingBean.setNearestMobileLM(nearestMobileLM);
								areaOfVendingBean.setStationaryVendor(stationaryVendor);
								areaOfVendingBean.setUlbName(ulbName);

								lenderBean.setLender_bankName(lender_bankName);
								lenderBean.setLenderCode(Integer.valueOf(lender_lenderCode));
								lenderBean.setMli_id(mli_id);

								applicationDetailsBean.setApplicationNo(applicationNo1);
								applicationDetailsBean.setApplicationDate(applicationDate);
								applicationDetailsBean.setCategory(category);
								applicationDetailsBean.setDisbursedAmount(disbursedAmount);
								applicationDetailsBean.setDisbursedDt(disbursedDt);
								applicationDetailsBean.setIsDisbursed(isDisbursed);
								applicationDetailsBean.setDurationOfVending(durationOfVending);
								applicationDetailsBean.setLenderBranch(lenderBranch);
								applicationDetailsBean.setLenderName(lenderName);
								applicationDetailsBean.setLoanAccountNumber(loanAccountNumber);
								applicationDetailsBean.setLoanTenure(loanTenure);
								applicationDetailsBean.setLoanTerm(loanTerm);
								applicationDetailsBean.setMemberOfCIGGLG(memberOfCIGGLG);
								applicationDetailsBean.setMoratoriumMonths(moratoriumMonths);
								applicationDetailsBean.setNameOfCIGGLG(nameOfCIGGLG);
								applicationDetailsBean.setRateOfInterest(rateOfInterest);
								applicationDetailsBean.setSanctionedAmount(sanctionedAmount);
								applicationDetailsBean.setSanctionedDt(sanctionedDt);
								applicationDetailsBean.setStatus(status1);
								applicationDetailsBean.setSvaVendingActivityName(svaVendingActivityName);
								applicationDetailsBean.setVendorType(vendorType);
								applicationDetailsBean.setVoterIDCardNo(voterIDCardNo);
								applicationDetailsBean.setGuaranteeReady(guaranteeReady);

								applicationDetailsBean.setPersonalDetails(personalDetailsBean);
								applicationDetailsBean.setBankAccountDetails(bankAccountDetailsBean);
								applicationDetailsBean.setAreaOfVending(areaOfVendingBean);
								applicationDetailsBean.setLenderBean(lenderBean);
								applicationDetailsBean.setLenderPermanentAddress(permanentAddressBean);
								applicationDetailsBean.setLenderCurrentAddress(currentAddressBean);

								lbData.add(applicationDetailsBean);
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return lbData;
	}

	@Override
	public String getLenderData(int lenderCode) throws BusinessException 
	{
		Session session = null;
        String mliId = "";
		String query = "";
		try
		{
			session = sessionFactory.openSession();
			query = "select mli_id from lendercodes where BankCode =:lenderCode";
			SQLQuery sql = session.createSQLQuery(query);
			sql.setParameter("lenderCode", lenderCode);
			List<String> list = sql.list();
			mliId = list.get(0);
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
		}
		return mliId;
	}

	@Override
	public List getApllicationNoList(int lenderCode) throws BusinessException {
		Session session = null;
		String query = "";
		List<String> applicationNoList = null;
		try
		{
			session = sessionFactory.openSession();
			query = "select applicationNo from  disbursement_details where lender_lenderCode='"+lenderCode+"'";
			SQLQuery sql = session.createSQLQuery(query);
			applicationNoList = sql.list();
		}
		catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return applicationNoList;
	}

	@Override
	public int updateLenderDetails() throws BusinessException {
		int result = 0;
		try
		{
			Query queryObject1 = sessionFactory
					.getCurrentSession()
					.createSQLQuery("update disbursement_details dd ,  lendercodes lc set dd.lender_bankName = lc.BankName , dd.mli_id = lc.mli_id , dd.lender_lenderCode = lc.BankCode where dd.lenderName = lc.BankName and dd.isCGPANGenerated is null");
		    result = queryObject1.executeUpdate();
		}
		catch(Exception e){
			result = 0;
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return result;
	}

	@Override
	public List<ApplicationDetailsBean> getDisburseData(List<FileExcelDataBO> list2) 
	{
		String pmsNo = "" , query = "";
		Session session = null;
		List<String> disburseDataList = null; 
		List<ApplicationDetailsBean> lbData = new ArrayList<>();
		try
		{
			if(list2 != null && list2.size()>0)
			{
			  for(FileExcelDataBO list1 : list2)
			  {
				  pmsNo = pmsNo+"'"+list1.getPmsNumber()+"',";
			  }
			}
			pmsNo = pmsNo.substring(0,pmsNo.length() - 1);
			session = sessionFactory.openSession();
			query = "select lenderName,applicationNo,loanAccountNumber,applicant_Name,disbursedAmount,DATE_FORMAT(disbursedDt,'%d/%m/%Y'),sanctionedAmount,DATE_FORMAT(sanctionedDt,'%d/%m/%Y'),status,loanTerm from  disbursement_details where ApplicationNo in("+pmsNo+")";
			SQLQuery sql = session.createSQLQuery(query);
			disburseDataList = sql.list();
			
			if (disburseDataList != null && disburseDataList.size() > 0) {
				Iterator itr = disburseDataList.iterator();
				while (itr.hasNext()) {
					Object[] o = (Object[]) itr.next();
					ApplicationDetailsBean applicationDetailsBean = new ApplicationDetailsBean();
					PersonalDetailsBean personalDetailsBean = new PersonalDetailsBean();
					
					String bankName = String.valueOf(o[0]);
					String applicationNo = String.valueOf(o[1]);
					String loanAccountNumber = String.valueOf(o[2]);
					String applicantName = String.valueOf(o[3]);
					String disbursedAmount = String.valueOf(o[4]);
					String disbursedDt = String.valueOf(o[5]);
					String sanctionedAmount = String.valueOf(o[6]);
					String sanctionedDt = String.valueOf(o[7]);
					String status = String.valueOf(o[8]);
					String loanTerm = String.valueOf(o[9]);
					
					personalDetailsBean.setApplicant_Name(applicantName);
					applicationDetailsBean.setApplicationNo(applicationNo);
					applicationDetailsBean.setLoanAccountNumber(loanAccountNumber);
					applicationDetailsBean.setDisbursedAmount(disbursedAmount);
					applicationDetailsBean.setDisbursedDt(disbursedDt);
					applicationDetailsBean.setSanctionedAmount(sanctionedAmount);
					applicationDetailsBean.setSanctionedDt(sanctionedDt);
					applicationDetailsBean.setStatus(status);
					applicationDetailsBean.setLoanTerm(loanTerm);
					applicationDetailsBean.setLenderName(bankName);
					
					applicationDetailsBean.setPersonalDetails(personalDetailsBean);
					lbData.add(applicationDetailsBean);	
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return lbData;
	}
	
	
	
	

	@Override
	public void updateMasterTableData() 
	{
		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String nowString = df.format(now);
		Session session = null;
		List<ApplicationDetailsBean> disburseDetailsData = new ArrayList<>();
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
		try
		{
			System.out.println("Now is: "+ nowString);
			session = sessionFactory.openSession();
            
			Query query = sessionFactory.getCurrentSession().createQuery("from ApplicationDetailsBean where upload_date >=:upload_date");
			query.setParameter("upload_date", nowString);
			
			disburseDetailsData = query.list();
			
			for (ApplicationDetailsBean disbData : disburseDetailsData) 
			{
				//System.out.println("ApplicationNumber is :::" + disbData.getApplicationNo() + "\t Loan Term :::" + disbData.getLoanTerm());
				Query queryObject1 = sessionFactory
						.getCurrentSession()
						.createSQLQuery("update "
								+ "nbfc_street_vendor_master set "
								+ "street_vendor_name=:street_vendor_name, "
								+ "DOB=:dob,"
								+ "Gender=:gender,"
								+ "Marital_Status=:marital_status,"
								+ "Mobile_No=:mobile_no,"
								+ "Social_Category=:social_category,"
								+ "Minority_Community=:monirity_Community,"
								+ "DistrictOfVending=:districtOfVending,"
								+ "Activity_Name=:activityName,"
								+ "AadharNoStatus=:AadharNoStatus,"
								+ "loanTerm=:loanterm,"
								+ "Loan_Account_No=:loanAccountNo,"
								+ "Present_Address=:per_houseNo,"
								+ "Present_District=:per_town_Dist,"
								+ "Present_State=:per_state,"
								+ "Present_Pin=:per_pin,"
								+ "Disbursement_Amount=:disbursedAmount,"
								+ "Loan_Sanction_Amount=:sanctionedAmount,"
								+ "Loan_Tenure=:loanTenure,"
								+ "DisbursementDate=:disbursedDt,"
								+ "SanctionDate=:sanctionedDt,"
								+ "api_update_date=:api_update_date,"
								+ "isupdated=:isupdated "
								+ "where AadharNo=:AadharNo and "
								+ "loanTerm=:loanTerm and "
								+ "ClaimSettled is null");
				
				queryObject1.setParameter("street_vendor_name",disbData.getPersonalDetails().getApplicant_Name());
				queryObject1.setParameter("dob",disbData.getPersonalDetails().getApplicant_DOB());
				queryObject1.setParameter("gender", disbData.getPersonalDetails().getApplicant_Gender());
				queryObject1.setParameter("marital_status", disbData.getPersonalDetails().getMaritalStatusName());
				queryObject1.setParameter("mobile_no", disbData.getPersonalDetails().getMobileNo());
				queryObject1.setParameter("social_category", disbData.getCategory());
				queryObject1.setParameter("monirity_Community", disbData.getPersonalDetails().getMinorityCommunity());
				queryObject1.setParameter("districtOfVending", disbData.getAreaOfVending().getvDistrictName());
				queryObject1.setParameter("activityName", disbData.getSvaVendingActivityName());
				queryObject1.setParameter("AadharNoStatus", chekcNull(disbData.getStatus()));
				queryObject1.setParameter("loanterm", chekcNull(disbData.getLoanTerm()));
				queryObject1.setParameter("loanAccountNo", disbData.getLoanAccountNumber());
				queryObject1.setParameter("per_houseNo", disbData.getLenderPermanentAddress().getHouseNo());
				queryObject1.setParameter("per_town_Dist", disbData.getLenderPermanentAddress().getTown_Dist());
				queryObject1.setParameter("per_state", disbData.getLenderPermanentAddress().getState());
				queryObject1.setParameter("per_pin", disbData.getLenderPermanentAddress().getPin());
				queryObject1.setParameter("disbursedAmount", disbData.getDisbursedAmount());
				queryObject1.setParameter("sanctionedAmount", disbData.getSanctionedAmount());
				queryObject1.setParameter("loanTenure", disbData.getLoanTenure());
				if(disbData.getDisbursedDt() != null && disbData.getDisbursedDt().trim().length()>0){
					String disDate = disbData.getDisbursedDt().replace("T00:00:00", "");
					queryObject1.setParameter("disbursedDt",formatter2.format(formatter1.parse(disDate)));
				}
				if(disbData.getSanctionedDt() != null && disbData.getSanctionedDt().trim().length()>0){
					String sanDate = disbData.getSanctionedDt().replace("T00:00:00", "");
					queryObject1.setParameter("sanctionedDt",formatter2.format(formatter1.parse(sanDate)));
				}
				queryObject1.setParameter("api_update_date", now);
				queryObject1.setParameter("isupdated", "1");
				queryObject1.setParameter("AadharNo", disbData.getApplicationNo());
				queryObject1.setParameter("loanTerm", chekcNull(disbData.getLoanTerm()));
				int result = queryObject1.executeUpdate();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	
	public String chekcNull(String str){
		if(str != null && str.trim().length()>0){
			return str.trim().toUpperCase();
		}
		else{
			return "";
		}
	}

	@Override
	public void saveUnmatcherMasterTableData() 
	{
		Session session = null;
		String query = "";
		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String nowString = df.format(now);
		try
		{
			session = sessionFactory.openSession();
			query = "select dd.applicationNo,dd.loanAccountNumber,dd.disbursedAmount,nb.Disbursement_Amount,dd.disbursedDt,"
				  + "nb.DisbursementDate,dd.sanctionedAmount,nb.Loan_Sanction_Amount,dd.sanctionedDt,nb.SanctionDate,dd.upload_date from "
				  + "nbfc_street_vendor_master nb inner join disbursement_details dd on nb.AadharNo = dd.applicationNo and nb.loanTerm = dd.loanTerm and "
				  + "((dd.disbursedAmount <> nb.Disbursement_Amount) OR (dd.sanctionedAmount <> nb.Loan_Sanction_Amount) OR "
				  + "(DATE_FORMAT(dd.disbursedDt, '%d-%m-%Y') <> nb.DisbursementDate) OR "
				  + "(DATE_FORMAT(dd.sanctionedDt, '%d-%m-%Y') <> nb.SanctionDate)) and dd.upload_date >=:upload_date";
			Query queryObject1 = sessionFactory.getCurrentSession().createSQLQuery(query);
			queryObject1.setParameter("upload_date", nowString);
			List<String> msthst = queryObject1.list();
				
			if(msthst != null && msthst.size() > 0)
			{
				Iterator itr = msthst.iterator();
				while(itr.hasNext())
				{
					Object[] o = (Object[]) itr.next();
					StreetVendorMasterHistory masterHistory = new StreetVendorMasterHistory();
					masterHistory.setApplicationNo(String.valueOf(o[0]));
					masterHistory.setLoanAccountNumber(String.valueOf(o[1]));
					masterHistory.setDisbursedAmount(String.valueOf(o[2]));
					masterHistory.setDisbursement_Amount(String.valueOf(o[3]));
					masterHistory.setDisbursedDt(String.valueOf(o[4]));
					masterHistory.setDisbursementDate(String.valueOf(o[5]));
					masterHistory.setSanctionedAmount(String.valueOf(o[6]));
					masterHistory.setLoan_Sanction_Amount(String.valueOf(o[7]));
					masterHistory.setSanctionedDt(String.valueOf(o[8]));
					masterHistory.setSanctionDate(String.valueOf(o[9]));
					masterHistory.setUpload_date(String.valueOf(o[10]));
					sessionFactory.getCurrentSession().saveOrUpdate(masterHistory);
					sessionFactory.getCurrentSession().flush();
					sessionFactory.getCurrentSession().clear();
				}
			}
			updateMasterTableData(); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
}
