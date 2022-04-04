
package com.nbfc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.bcel.generic.NEW;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.bean.DisbursementForApprovalBean;
import com.nbfc.model.DisbursementForApprovalModel;
import com.nbfc.model.PortfolioNumber;
import com.nbfc.model.SanctionMakerModel;
import com.nbfc.model.SanctionMakerModelForAud;
import com.raistudies.domain.CustomExceptionHandler;

@Repository("sanctionMakerDao")
public class SanctionMakerDaoImpl implements SanctionMakerDao{
@Autowired
  private SessionFactory sessionFactory;
	List<SanctionMakerModel> resultList=new ArrayList<SanctionMakerModel>();
	static Logger log = Logger.getLogger(SanctionMakerDaoImpl.class.getName());

	
	public List<SanctionMakerModel> getPortfolioNumber() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createCriteria(DisbursementForApprovalModel.class).list();
	}

	
	public List<SanctionMakerModel> getDisburseDataForApproval(
			String portfolioNo, String loanAccountNumber) {			
		log.info("Enter in getDisburseDataForApproval() method in DisbursementForApprovalDaoImpl class ");
		log.info("portfolioNo :"+portfolioNo+"loanAccountNumber: "+loanAccountNumber);
		//int portfolioNumber=Integer.parseInt(portfolioNo);
		/*try {
			//if((!portfolioNo.equals("")||portfolioNo!=null)&&(loanAccountNumber.equals("")||loanAccountNumber!=null)){
			if(!portfolioNo.equals("")&&!loanAccountNumber.equals("")){

				List<SanctionMakerModel> resultList = sessionFactory.getCurrentSession()
				.createCriteria(SanctionMakerModel.class,portfolioNo)
				.add(Restrictions.eq("portfolioNo", portfolioNo)).add(Restrictions.eq("loanAccountNo", loanAccountNumber )).add(Restrictions.eq("status", "CCA" )).list();

			}else if((portfolioNo!=""||portfolioNo!=null)&&(loanAccountNumber==""||loanAccountNumber==null)){
				List<SanctionMakerModel> resultList = sessionFactory.getCurrentSession()
				.createCriteria(SanctionMakerModel.class,portfolioNo)
				.add(Restrictions.eq("portfolioNo", portfolioNo)).add(Restrictions.eq("status", "CCA" )).list();

			}else if((loanAccountNumber!=""||loanAccountNumber!=null)&&(portfolioNo==""||portfolioNo==null)){
				List<SanctionMakerModel> resultList = sessionFactory.getCurrentSession()
				.createCriteria(SanctionMakerModel.class).add(Restrictions.eq("loanAccountNo", loanAccountNumber )).add(Restrictions.eq("status", "CCA" )).list();

			}*/
		
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SanctionMakerModel.class); 
		Criterion rest1=Restrictions.eq("portfolioNo", portfolioNo);
		Criterion rest2=Restrictions.eq("loanAccountNo", loanAccountNumber);	
		Criterion rest3=Restrictions.or(rest1, rest2);		
		Criterion rest4=Restrictions.eq("status", "CCA");
		
		Criterion rest5=Restrictions.and(rest3, rest4);	
		List<SanctionMakerModel> resultList=criteria.add(rest5).list();
*/
		
		try {
			Query queryObject = sessionFactory
					.getCurrentSession()
					.createQuery(
							"FROM SanctionMakerModel di " +
							"WHERE  di.status=:status " +
							"and di.portfolioNo LIKE :PORTFOLIO_NO " +
							"and di.loanAccountNo LIKE :LOAN_ACCOUNT_NO");
			queryObject.setParameter("PORTFOLIO_NO", "%" + portfolioNo + "%");
			queryObject.setParameter("LOAN_ACCOUNT_NO", "%" + loanAccountNumber
					+ "%");
			queryObject.setParameter("status", "CCA");
			System.out.println("hql " + queryObject + " data list "
					+ queryObject.list());
			//queryObject.setParameter("DISBURSEMENT_STATUS", "N");
			resultList = queryObject.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Exit from getDisburseDataForApproval() method in DisbursementForApprovalDaoImpl class ");
			
		return resultList;
	}

	
	public int updateDisbursedData(List<SanctionMakerModel> disbursedData,
			String userId) {
		// TODO Auto-generated method stub
		int result = 0;
		List<SanctionMakerModel> sma = new ArrayList<SanctionMakerModel>();
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		try {
			log.info("Enter in updateDisbusedData() method in DisbursementForApprovalDaoImpl class ");
			if (disbursedData.size() > 0) {
				for (SanctionMakerModel dm1 : disbursedData) {
					Query queryObject1 = sessionFactory.getCurrentSession().createQuery("FROM SanctionMakerModel di WHERE   di.loanAccountNo = :LOAN_ACCOUNT_NO");
					queryObject1.setParameter("LOAN_ACCOUNT_NO",dm1.getLoanAccountNo());
					sma.addAll(queryObject1.list());
				}
			}
			List<SanctionMakerModelForAud> auditData = setAuditData(sma);
			for (SanctionMakerModelForAud auditDataiter : auditData) {
				session.save(auditDataiter);
			}
			
		
		if(disbursedData.size()>0 && userId!=null){
			for(SanctionMakerModel dm:disbursedData){

		Query query = sessionFactory.getCurrentSession().createQuery("Update SanctionMakerModel set status=:statusValue,sanctionedAmount=:sanctionedAmount,sanMakerDate=:sanMakerDate,sanMakerId=:sanMakerId where loanAccountNo = :LOAN_ACCOUNT_NO");
		query.setParameter("statusValue", "SDE");
		query.setParameter("sanctionedAmount", dm.getSanctionedAmount());
		query.setParameter("sanMakerDate", new Date());	
		query.setParameter("sanMakerId", userId);
		query.setParameter("LOAN_ACCOUNT_NO", dm.getLoanAccountNo());
		result = query.executeUpdate();	
				
			}
		}
		tn.commit();
		sessionFactory.close();
		log.info("Exit from updateDisbusedData() method in DisbursementForApprovalDaoImpl Java class ");
		}catch(Exception e){
	        if (tn!=null) tn.rollback();
			e.printStackTrace();
		}finally{
			sessionFactory.close();
			session.close();
			
		}
		return result;
	}

	private List<SanctionMakerModelForAud> setAuditData(List<SanctionMakerModel> sma) {
		List<SanctionMakerModelForAud> auditTable=new ArrayList<SanctionMakerModelForAud>();
		// TODO Auto-generated method stub
		int audInterfaceId=0;
		 audInterfaceId=getAudInterfaceUploadId();

		//int i=audInterfaceId;
		for(SanctionMakerModel sma1:sma){
		SanctionMakerModelForAud smas=new SanctionMakerModelForAud();

		//if(audInterfaceId>0){
		audInterfaceId=audInterfaceId+1;
		smas.setAudinterfaceUplaodId(audInterfaceId);
		//}
		smas.setInterfaceUplaodId(sma1.getInterfaceUplaodId());
		smas.setLoneType(sma1.getLoneType());
		smas.setBusinessProduct(sma1.getBusinessProduct());
		smas.setPortfolioNo(Integer.parseInt(sma1.getPortfolioNo()));
		smas.setSubPortfolioNo(sma1.getSubPortfolioNo());
		smas.setLoanAccountNo(sma1.getLoanAccountNo());
		smas.setConstitution(sma1.getConstitution());
		smas.setMseName(sma1.getMseName());
		smas.setDisbursmentStatus(sma1.getDisbursmentStatus());
		smas.setInterestRate(sma1.getInterestRate());
		smas.setMicroSmall(sma1.getMicroSmall());
		smas.setTenorInMonth(sma1.getTenorInMonth());
		smas.setMseAddress(sma1.getMseAddress());
		smas.setCity(sma1.getCity());
		smas.setDistrict(sma1.getDistrict());
		smas.setPincode(sma1.getPincode());
		smas.setState(sma1.getState());
		smas.setMseITPAN(sma1.getMseITPAN());
		smas.setUdyogAadharNo(sma1.getUdyogAadharNo());
		smas.setMseregistrationNo(sma1.getMseregistrationNo());
		smas.setIndustryNature(sma1.getIndustryNature());
		smas.setIndustrySector(sma1.getIndustrySector());
		smas.setNoOfEmployees(sma1.getNoOfEmployees());
		smas.setProjectedSales(sma1.getProjectedSales());
		smas.setProjectedExports(sma1.getProjectedExports());
		smas.setNewExistingUnit(sma1.getNewExistingUnit());
		smas.setPreviousbankingExperience(sma1.getPreviousbankingExperience());
		smas.setFirstTimeCustomer(sma1.getFirstTimeCustomer());
		smas.setChiefPromoterFirstName(sma1.getChiefPromoterFirstName());
		smas.setChiefPromoterMiddleName(sma1.getChiefPromoterMiddleName());
		smas.setChiefPromoterLastName(sma1.getChiefPromoterLastName());
		smas.setChiefPromoterITPAN(sma1.getChiefPromoterITPAN());
		smas.setChiefPromoterMailId(sma1.getChiefPromoterMailId());
		smas.setChiefPromoterContactNo(sma1.getChiefPromoterContactNo());
		smas.setMinorityCommunity(sma1.getMinorityCommunity());
		smas.setHandicapped(sma1.getHandicapped());
		smas.setWomen(sma1.getWomen());
		smas.setCategory(sma1.getCategory());
		smas.setCgtmseMakerDate(sma1.getCgtmseMakerDate());
		smas.setStatus(sma1.getStatus());
		smas.setFilePath(sma1.getFilePath());
		smas.setRemarks(sma1.getRemarks());
		smas.setFlag(sma1.getFlag());
		smas.setFileLink(sma1.getFileLink());
		smas.setCgdan(sma1.getCgdan());
		smas.setMPendency(sma1.getMPendency());
		smas.setLoneType(sma1.getLoneType());
		smas.setRejection_reason(sma1.getRejection_reason());
		smas.setDisbMakerId(sma1.getDisbMakerId());
		smas.setDisbMakerDate(sma1.getDisbMakerDate());
		smas.setDisbCheckerId(sma1.getDisbCheckerId());
		smas.setDisbCheckerDate(sma1.getDisbCheckerDate());
		smas.setSanMakerId(sma1.getSanMakerId());
		smas.setSanMakerDate(sma1.getSanMakerDate());
		smas.setSanCheckerId(sma1.getSanCheckerId());
		smas.setSanCheckerDate(sma1.getSanCheckerDate());
		smas.setSanctionedAmount(sma1.getSanctionedAmount());

		auditTable.add(smas);
		}
		return auditTable;
	}

	private int getAudInterfaceUploadId() {
		int max=0;
		Session session = sessionFactory.openSession();
		try {		
		 max = (Integer)session.createQuery("SELECT COALESCE(MAX(sn.audinterfaceUplaodId), 0) FROM SanctionMakerModelForAud sn").uniqueResult();			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			//session.flush();
		}
	    return max;
	}

	

	
	/*	Query qry = session.createQuery("insert into SanctionMakerModelForAudit(interfaceUplaodId," +
	"fileLink," +
	"cgdan," +
	"MPendency," +
	"usrId," +
	"rejection_reason," +
	"loneType," +
	"businessProduct," +
	"loanAccountNo," +
	"constitution," +
	"mseName," +
	"snctionDate," +
	"sanctionedAmount," +
	"firstDisbursementDate," +
	"interestRate," +
	"tenorInMonth," +
	"microSmall," +
	"mseAddress," +
	"city," +
	"district," +
	"pincode," +
	"state," +
	"mseITPAN," +
	"udyogAadharNo," +
	"mseregistrationNo," +
	"industryNature," +
	"industrySector," +
	"noOfEmployees," +
	"projectedSales," +
	"projectedExports," +
	"newExistingUnit," +
	"previousbankingExperience," +
	"firstTimeCustomer," +
	"chiefPromoterFirstName," +
	"chiefPromoterMiddleName," +
	"chiefPromoterLastName," +
	"chiefPromoterITPAN," +
	"chiefPromoterMailId," +
	"chiefPromoterContactNo," +
	"minorityCommunity," +
	"handicapped," +
	"women," +
	"category," +
	"protfolioBaseYer," +
	"portfolioNo," +
	"insertDateTime," +
	"status," +
	"remarks," +
	"flag," +
	"filePath," +
	"cgtmseMakerDate," +
	"sanMakerDate," +
	"sanMakerId," +
	"disbMakerDate," +
	"disbMakerId," +
	"disbCheckerId," +
	"disbCheckerDate," +
	"sanCheckerId, " +
	"sanCheckerDate)  select sn.interfaceUplaodId," +
	"sn.fileLink," +
	"sn.cgdan," +
	"sn.MPendency," +
	"sn.usrId," +
	"sn.rejection_reason," +
	"sn.loneType," +
	"sn.businessProduct," +
	"sn.loanAccountNo," +
	"sn.constitution," +
	"sn.mseName," +
	"sn.snctionDate," +
	"sn.sanctionedAmount," +
	"sn.firstDisbursementDate," +
	"sn.interestRate," +
	"sn.tenorInMonth," +
	"sn.microSmall," +
	"sn.mseAddress," +
	"sn.city," +
	"sn.district," +
	"sn.pincode," +
	"sn.state," +
	"sn.mseITPAN," +
	"sn.udyogAadharNo," +
	"sn.mseregistrationNo," +
	"sn.industryNature," +
	"sn.industrySector," +
	"sn.noOfEmployees," +
	"sn.projectedSales," +
	"sn.projectedExports," +
	"sn.newExistingUnit," +
	"sn.previousbankingExperience," +
	"sn.firstTimeCustomer," +
	"sn.chiefPromoterFirstName," +
	"sn.chiefPromoterMiddleName," +
	"sn.chiefPromoterLastName,sn." +
	"chiefPromoterITPAN," +
	"sn.chiefPromoterMailId,sn." +
	"chiefPromoterContactNo," +
	"sn.minorityCommunity," +
	"sn.handicapped," +
	"sn.women," +
	"sn.category," +
	"sn.protfolioBaseYer," +
	"sn.portfolioNo," +
	"sn.insertDateTime," +
	"sn.status," +
	"sn.remarks," +
	"sn.flag," +
	"sn.filePath," +
	"sn.cgtmseMakerDate," +
	"sn.sanMakerDate," +
	"sn.sanMakerId," +
	"sn.disbMakerDate," +
	"sn.disbMakerId," +
	"sn.disbCheckerId," +
	"sn.disbCheckerDate," +
	"sn.sanCheckerId," +
	"sn.sanCheckerDate from SanctionMakerModel sn where sn.loanAccountNo= :LOAN_ACCOUNT_NO");
qry.setParameter("LOAN_ACCOUNT_NO", dm1.getLoanAccountNo());
int res = qry.executeUpdate();
*/
	

}
