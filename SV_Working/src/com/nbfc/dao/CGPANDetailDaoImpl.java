package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.bean.ApplicationStatusDetailsBean;
import com.nbfc.bean.CGPANDetailsReportBean;
import com.nbfc.bean.ClaimLodgementBean;
import com.raistudies.domain.CustomExceptionHandler;

@Repository("CGPANDetailDao")
public class CGPANDetailDaoImpl implements CGPANDetailDao {
	@Autowired
	
	SessionFactory sessionFactory;
	ResultSet resultset = null;
	ResultSetMetaData resultSetMetaData = null;
	@Override
	public ArrayList<CGPANDetailsReportBean> getCgpanDetailsReport(String cgpan) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		CGPANDetailsReportBean bean = null;
		ArrayList<CGPANDetailsReportBean> CGPANBeanList = new ArrayList<CGPANDetailsReportBean>();
		try {
			
			Session session4 = sessionFactory.openSession();
			/* Transaction tn = session4.beginTransaction(); */
			Connection conn = session4.connection();
			
			
			
	CallableStatement callableStatement = (CallableStatement) conn.prepareCall("{ call NBFC.PROC_CGPAN_HISTORY(?,?,?) } ");
	callableStatement.setString(1, cgpan);
	callableStatement.registerOutParameter(2, Types.VARCHAR);
	callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
	callableStatement.execute();
	
	resultset = (ResultSet) callableStatement.getObject(3);
	while (resultset.next()) {
		
					bean = new CGPANDetailsReportBean();
					bean.setDisplayCgpen(resultset.getString(1));
					bean.setMliId(resultset.getString(2));
					bean.setSsiName(resultset.getString(3));
					bean.setDanId(resultset.getString(4));
					bean.setGuaranteeAmt(resultset.getDouble(5));
					bean.setOutstandingAmt(resultset.getDouble(6));
					bean.setPayId(resultset.getString(7));
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String Apprdate = resultset.getString(8);
					bean.setDciAppropriationDate((Apprdate));
					
					bean.setAppropriation_by(resultset.getString(9));
					
					String GSdate = resultset.getString(10);
					bean.setDciGuaranteeeSDate((GSdate));
					
					String crystdate = resultset.getString(11);
					bean.setCrystalizationDate((crystdate));
					
					String expdate = resultset.getString(12);
					bean.setExpiryDate((expdate));
					
					bean.setDan_Amt(resultset.getDouble(13));
					
					bean.setStatus(resultset.getString(14));
					CGPANBeanList.add(bean);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CGPANBeanList;
		/*String query = "select n.cgpan,N.MEM_BNK_ID||N.MEM_ZNE_ID||N.MEM_BRN_ID,N.MSE_NAME,d.dan_id,N.GUARANTEE_AMOUNT,N.OUTSTANDING_AMOUNT,d.DCI_AMOUNT_RAISED,D.PAY_ID,d.DCI_APPROPRIATION_DT,d.DCI_APPROPRIATION_BY,D.DCI_GUARANTEE_START_DT,N.TENOR_IN_MONTHS from" 
		+ " nbfc_interface_upload n,"
		+ " NBFC_DAN_CGPAN_INFO d"
		+ " where"
		+ " N.DAN_ID = D.DAN_ID and "
		+ " n.cgpan=:Cgpan"
		+ " union  all "
		+ " select n.cgpan,N.MEM_BNK_ID||N.MEM_ZNE_ID||N.MEM_BRN_ID,N.MSE_NAME,d.dan_id,N.GUARANTEE_AMOUNT,N.OUTSTANDING_AMOUNT ,DCI_AMOUNT_RAISED ,D.PAY_ID,d.DCI_APPROPRIATION_DT,d.DCI_APPROPRIATION_BY,D.DCI_GUARANTEE_START_DT,N.TENOR_IN_MONTHS from"
		+" nbfc_interface_upload n,"
		+" NBFC_DAN_CGPAN_INFO d,"
		+" NBFC_ASF_DETAIL a"
		+" where"
		+" A.CGPAN = n.cgpan"
		+" and A.ASF_DAN_ID = d.dan_id "
		+" and n.cgpan=:Cgpan";

		

		
 		String hql =  "select IU.CGPAN,concat(concat(IU.mem_bank_id,IU.mem_zne_id),IU.mem_brn_id) AS mliID,IU.danId,IU.OUTSTANDING_AMOUNT,"
				+ " IU.mseName,IU.GUARANTEE_AMOUNT,DC.payId,DC.Dci_guarantesDate,IU.tenorInMonth,DC.Dci_appropriationDate,DC.Dci_APPROPRIATE_BY,DC.Dci_guarantesDate,IU.tenorInMonth FROM"
				+ " FileUploadModel IU,"
				+ " DanCgpanDetailsModel DC" 
				+ " WHERE"
				+ " IU.danId = DC.danId AND"
				+ " IU.CGPAN=:Cgpan"
				+ " union all"
			    + " select IU.CGPAN,concat(concat(IU.mem_bank_id,IU.mem_zne_id),IU.mem_brn_id) AS mliID,IU.danId,IU.OUTSTANDING_AMOUNT,"
				+ " IU.mseName,IU.GUARANTEE_AMOUNT,DC.payId,DC.Dci_guarantesDate,IU.tenorInMonth,DC.Dci_appropriationDate,DC.Dci_APPROPRIATE_BY,DC.Dci_guarantesDate,IU.tenorInMonth FROM"
				+ " FileUploadModel IU,"
				+ " DanCgpanDetailsModel DC,"
				+ " ASFDetailModel AD"
				+ " WHERE"
				+ " AD.CGPAN = IU.CGPAN AND"
				+ " AD.ASF_DAN_ID = DC.danId"
				+ " AND IU.CGPAN=:Cgpan";
		Query query1 = sessionFactory.getCurrentSession().createSQLQuery(query);
		query1.setParameter("Cgpan",cgpan);
 		List<Object> list = query1.list();
		return list;*/

	}

}
