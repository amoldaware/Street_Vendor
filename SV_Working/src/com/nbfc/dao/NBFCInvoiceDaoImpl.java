package com.nbfc.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.impl.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.icu.text.DecimalFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.CGPANDetailsReportBean;
import com.nbfc.common.utility.method.NumberToWordsFormatMethod;
import com.nbfc.helper.NBFCHelper;
import com.nbfc.model.ProformaInvoiceDetails;
import com.nbfc.model.ProformaInvoiceDetailsASF;
import com.nbfc.model.TaxDetailMaster;
import com.nbfc.model.TaxInvoiceDetails;
import com.nbfc.model.TaxInvoiceDetailsASF;

@Repository("NBFCInvoiceDao")
public class NBFCInvoiceDaoImpl implements NBFCInvoiceDao {

	@Autowired
	SessionFactory sessionFactory;

	public List<TaxDetailMaster> getTaxInvoiceDeails() {
		System.out.println("Hello");
		return (List<TaxDetailMaster>) sessionFactory.getCurrentSession()
				.createCriteria(TaxDetailMaster.class).list();
	}

	// Modified By Parmanand 09-Jan-2019
	public void callTaxInvoiceReport(String portfolioName,
			HttpServletResponse response, HttpServletRequest request,
			CGPANDetailsReportBean bean) throws JRException {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		NBFCHelper nbfcHelper = new NBFCHelper();
		Connection connection = ((SessionImpl) session).connection();
		JasperPrint jasperPrint1 = null;
		if (connection == null) {
			System.out.println("connection is null.........");
		}
		String State = bean.getState();
		String DAN = bean.getDanId();
		String userId = bean.getUserId();
		// TaxInvoice No
		String date = bean.getDciAppropriationDate();
		String TaxNo = bean.getTaxid();
		// String id = date.replace("/", "");

		//-------------------------------------------------------------------------------------
		double dci_base_amt = bean.getDci_base_amt() ;
		BigDecimal dci_base_amt1 = new BigDecimal(dci_base_amt);
		BigDecimal dci_base_amt2 = dci_base_amt1.setScale(2, RoundingMode.HALF_UP);
		//String taxBaseAmountInRupes = dci_base_amt2.toPlainString();
		
		double dci_total_amt = Math.round(bean.getDci_total_amt());
		BigDecimal dci_total_amt1 = new BigDecimal(dci_total_amt);
		BigDecimal dci_total_amt2 = dci_total_amt1.setScale(2, RoundingMode.HALF_UP);
//-----------------------------------------------------------------------------------------	
		
		double IGSTAMT = Math.round(bean.getIgstAmt());
		double CGSTAMT =Math.round(bean.getCgstAmt());
		double SGSTAMT = Math.round(bean.getSgstAmt());
		
	/*	String fyBasedOnStartAndEndDate = nbfcHelper.getCurrentYear();
		StringBuilder sb = new StringBuilder(fyBasedOnStartAndEndDate);
		sb.delete(5, 7);
		String fy = sb.toString();

		String TaxInvoiceNo = fy + "N0000000" + TaxNo;*/

		double outSandingAndGuran = ((bean.getOutstandingAmount1()
				* bean.getGuaranteeFee() / 100));
		double outSandingAndGuranRound = Math.round(outSandingAndGuran);
		BigDecimal taxAmt = new BigDecimal(outSandingAndGuranRound);
		BigDecimal taxAmountInRupes2 = taxAmt.setScale(2, RoundingMode.HALF_UP);
		String taxAmountInRupes = taxAmountInRupes2.toPlainString();
		double objTotalAmt2 = bean.getIgstAmt() + bean.getCgstAmt()
				+ bean.getSgstAmt() + outSandingAndGuran;
		BigDecimal objTotalAmt = new BigDecimal(Math.round(objTotalAmt2));
		BigDecimal objFinalToatlAmt1 = objTotalAmt.setScale(2,
				RoundingMode.HALF_UP);
		//-----------------------------------------------------------
		String fianlTotalAmountInRupes = dci_total_amt2.toPlainString();
		long fianlTotalAmountInRupes1 = dci_total_amt2.longValue();
		String sim2 = NumberToWordsFormatMethod
				.inWordFormat(fianlTotalAmountInRupes1);
		//--------------------------------------------------------------
//		String fianlTotalAmountInRupes = objFinalToatlAmt1.toPlainString();
//		
//		long fianlTotalAmountInRupes1 = dci_total_amt2.longValue();
//		String sim2 = NumberToWordsFormatMethod
//				.inWordFormat(fianlTotalAmountInRupes1);
//		//-----------------------------------------------------
		
		BigDecimal IGST_AMT= new BigDecimal(IGSTAMT);
		BigDecimal IGST_AMT1 = IGST_AMT.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal CGST_AMT = new BigDecimal(CGSTAMT);
		BigDecimal CGST_AMT1 = CGST_AMT.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal SGST_AMT = new BigDecimal(SGSTAMT);
		BigDecimal SGST_AMT1 = SGST_AMT.setScale(2, RoundingMode.HALF_UP);
		
		HashMap map = new HashMap();
		map.put("TotalIgstAmt", IGST_AMT1);
		map.put("TotalCgstAmt", CGST_AMT1);
		map.put("TotalSgstAmt", SGST_AMT1);
		map.put("TaxInvoiceNo", TaxNo);
		map.put("TaxAmount", taxAmountInRupes2);
		map.put("TotalAmount", dci_total_amt2);
		map.put("TotalAmountInWords", sim2);
		map.put("StateName", State);
		map.put("portfolioName", portfolioName);
		map.put("userId", userId);
		try {
			String filename = "TaxInvoiceReport1";
			String reportfileName = "Taxreport" + portfolioName + ".pdf";
			String tempFolderPath = "/WEB-INF/JReport/" + filename;
			System.out
					.println("tempFolderPath.............................................."
							+ tempFolderPath);
			File tempFolder = new File(tempFolderPath);
			if (!tempFolder.exists()) {
				tempFolder.mkdirs();
			}
			String jasperFilePath = tempFolderPath + ".jasper";
			System.out.println("jasperFilePath......................"
					+ jasperFilePath);
			File reportFile = new File(jasperFilePath);
			if (!reportFile.exists()) {
				InputStream jRXmlStream = request
						.getSession()
						.getServletContext()
						.getResourceAsStream(
								"/WEB-INF/JReport/" + filename + ".jrxml");
				JasperDesign jasperDesign = JRXmlLoader.load(jRXmlStream);
				JasperCompileManager.compileReportToFile(jasperDesign,
						jasperFilePath);
				System.out.println(jasperFilePath);
			}
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObjectFromFile(reportFile.getPath());
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, map, connection);
			JRAbstractExporter exporterPDF = new JRPdfExporter();
			exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			try {
				exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,
						response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			response.setHeader("Content-Disposition", "attachment;filename="
					+ reportfileName);
			response.setContentType("application/pdf");
			exporterPDF.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tn.commit();

		}

	}

	@Override
	public List<Object> getTaxReportDetails(String portfolioName, String uid) {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		Connection connection = ((SessionImpl) session).connection();
		String sql = "SELECT MAX(MEM_BANK_NAME) MEM_BANK_NAME,MAX(MEM_ADDRESS) MEM_ADDRESS,MAX(TAX_DATE) TAX_DATE,MAX(GSTIN_NO) GSTIN_NO,"
				+ "MAX(MEM_STATE_NAME) AS STE_CODE,MAX(STE_NAME) STE_NAME,MAX(MLI_ID) MLI_ID,MAX(PORTFOLIO_NAME) PORTFOLIO_NAME,"
				+ "SUM(SANCTIONED_AMOUNT) SANCTIONED_AMOUNT,SUM(GUARANTEE_AMOUNT) GUARANTEE_AMOUNT,"
				+ "MAX(TENOR_IN_MONTHS) TENOR_IN_MONTHS,MAX((NBFC_UPLOADED_DATE)) NBFC_UPLOADED_DATE,MAX(DCI_AMOUNT_RAISED) DCI_AMOUNT_RAISED,MAX(DCI_BASE_AMT) DCI_BASE_AMT,"
				+ "MAX(IGST_RATE) IGST_RATE,MAX(IGST_AMT) IGST_AMT,MAX(CGST_RATE) CGST_RATE,MAX(CGST_AMT) CGST_AMT,MAX(SGST_RATE) SGST_RATE,MAX(SGST_AMT) SGST_AMT, "
				+ "MAX (DCI_STANDARD_RATE) DCI_STANDARD_RATE "
				+ "FROM(SELECT NM.MEM_BANK_NAME,NM.MEM_ADDRESS,(SELECT TO_CHAR(DI.DCI_GUARANTEE_START_DT,'DD/MM/YYYY') FROM NBFC_DAN_CGPAN_INFO DI WHERE DI.DAN_ID = NI.DAN_ID  ) TAX_DATE,"
				+ "NM.GSTIN_NO,NM.MEM_STATE_NAME,S.STE_NAME,NM.MEM_BNK_ID||NM.MEM_ZNE_ID||NM.MEM_BRN_ID MLI_ID,"
				+ "NI.PORTFOLIO_NAME,NI.SANCTIONED_AMOUNT,NI.GUARANTEE_AMOUNT,NI.TENOR_IN_MONTHS,"
				+ "TO_CHAR(NI.NBFC_UPLOADED_DATE,'DD/MM/YYYY') NBFC_UPLOADED_DATE,"
				+ "(SELECT  SUM(DCI_AMOUNT_RAISED) FROM NBFC_DAN_CGPAN_INFO DI WHERE DI.DAN_ID IN(SELECT DISTINCT (C.DAN_ID) FROM nbfc_interface_upload c WHERE c.PORTFOLIO_NAME='"
				+ portfolioName
				+ "'  ))  AS DCI_AMOUNT_RAISED,"
				+ "(SELECT  SUM(DI.DCI_BASE_AMT) FROM NBFC_DAN_CGPAN_INFO DI WHERE DI.DAN_ID IN(SELECT DISTINCT (C.DAN_ID) FROM nbfc_interface_upload c WHERE c.PORTFOLIO_NAME='"
				+ portfolioName
				+ "') )  AS DCI_BASE_AMT,"
				+ "(SELECT  MAX(DI.IGST_RATE) FROM NBFC_DAN_CGPAN_INFO DI WHERE DI.DAN_ID IN(SELECT DISTINCT (C.DAN_ID) FROM nbfc_interface_upload c WHERE c.PORTFOLIO_NAME='"
				+ portfolioName
				+ "') )  AS IGST_RATE,"
				+ "(SELECT  SUM(DI.IGST_AMT) FROM NBFC_DAN_CGPAN_INFO DI WHERE DI.DAN_ID IN(SELECT DISTINCT (C.DAN_ID) FROM nbfc_interface_upload c WHERE c.PORTFOLIO_NAME='"
				+ portfolioName
				+ "'  ) )  AS IGST_AMT,(SELECT  MAX(DI.CGST_RATE) FROM NBFC_DAN_CGPAN_INFO DI WHERE DI.DAN_ID IN(SELECT DISTINCT (C.DAN_ID) FROM nbfc_interface_upload c WHERE c.PORTFOLIO_NAME='"
				+ portfolioName
				+ "')) "
				+ " AS CGST_RATE,(SELECT  SUM(DI.CGST_AMT) FROM NBFC_DAN_CGPAN_INFO DI WHERE DI.DAN_ID IN(SELECT DISTINCT (C.DAN_ID) FROM nbfc_interface_upload c WHERE c.PORTFOLIO_NAME='"
				+ portfolioName
				+ "' ) )  AS CGST_AMT,(SELECT  MAX(DI.SGST_RATE) FROM NBFC_DAN_CGPAN_INFO DI WHERE DI.DAN_ID IN(SELECT DISTINCT (C.DAN_ID) FROM nbfc_interface_upload c WHERE c.PORTFOLIO_NAME='"
				+ portfolioName
				+ "') )  AS SGST_RATE,"
				+ "(SELECT  SUM(DI.SGST_AMT) FROM NBFC_DAN_CGPAN_INFO DI WHERE DI.DAN_ID IN(SELECT DISTINCT (C.DAN_ID) FROM nbfc_interface_upload c WHERE c.PORTFOLIO_NAME='"
				+ portfolioName
				+ "' ) )  AS SGST_AMT, (SELECT SUM (DI.DCI_STANDARD_RATE) FROM NBFC_DAN_CGPAN_INFO DI WHERE DI.DAN_ID = ni.dan_id ) AS DCI_STANDARD_RATE "
				+ "FROM nbfc_interface_upload NI,NBFC_MEMBER_INFO NM,CGTSITEMPUSER.STATE_MASTER S WHERE NI.MEM_BNK_ID||NI.MEM_ZNE_ID||NI.MEM_BRN_ID=NM.MEM_BNK_ID||NM.MEM_ZNE_ID||NM.MEM_BRN_ID AND S.STE_CODE =MEM_STATE_NAME AND PORTFOLIO_NAME='"
				+ portfolioName + "')";
		System.out.println("Query-------" + sql);
		SQLQuery query = session.createSQLQuery(sql);
		List<Object> list = query.list();
		return list;
	}

	public void insertTaxInvoiceDetails(TaxInvoiceDetails taxDetailsObj) {
		sessionFactory.getCurrentSession().save(taxDetailsObj);
	}

	@Override
	public Integer getMaxTaxInvoiceIdCount() {
		try {
			String hql = "SELECT MAX(TAX_INV_ID)+1 FROM TaxInvoiceDetails ";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			System.out.println("Exception===" + e);
		}
		return 0;
	}

	@Override
	public Integer getTaxInvoiceIdCount(String portfolioName) {
		try {
			String hql = "SELECT count(*) FROM TaxInvoiceDetails where PORTFOLIO_NAME=:PORTFOLIO ";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("PORTFOLIO", portfolioName);
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			System.out.println("Exception===" + e);
		}
		return 0;
	}

	@Override
	public String getTaxInvoiceNo(String portfolioName) {
		String Taxid = "";
		try {
			Session session = sessionFactory.openSession();
			Transaction tn = session.beginTransaction();
			Connection connection = ((SessionImpl) session).connection();
			SQLQuery slqQuery = session.createSQLQuery("SELECT TAX_INV_ID FROM NBFC_GST_TAX_INVOICE WHERE PORTFOLIO_NAME = '"+portfolioName+"'");
			//SQLQuery q = session.reateSQLQuery("select  To_char(FYSTARTDATE('01-Apr-2018'),'RRRR') ||--' ||(To_char(FYSTARTDATE('01-Mar-2019'),'RRRR')+1) as fyyear from dual");
			ArrayList	fyList = (ArrayList) slqQuery.list();
			/*Query query = session
					.createQuery("SELECT TAX_INV_ID FROM TaxInvoiceDetails WHERE PORTFOLIO_NAME = :PORTFOLIO");
			query.setParameter("PORTFOLIO", portfolioName);
			List<String> rows = query.list();
			System.out.println("current row>>>>>>  " + rows.toString());*/
			Iterator itr=fyList.iterator();
			while(itr.hasNext()){
				Taxid=(String) itr.next();
			}
			
		return Taxid;
		
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getTaxInvoiceData(String mliId, HttpSession session) {
		String Role = (String) session.getAttribute("uRole");
		List<Object> list;
		if (Role.equals("CMAKER") || Role.equals("CCHECKER")) {
			String hql = "SELECT PM.PortFolioName,MAX(MR.mem_bnk_name),MAX(UI.USR_ID) FROM FileUploadModel IU,PortFolioDetailsInParentTBL PM,DanCgpanDetailsModel DC,MLIRegistration MR,UserInfo UI WHERE PM.portfolio_no = IU.portfolioNo AND DC.DCI_APPROPRIATION_FLAG='Y' AND IU.danId = DC.danId AND concat(concat(IU.mem_bank_id,IU.mem_zne_id),IU.mem_brn_id) = concat(concat(MR.mem_bnk_id,MR.mem_zne_id),MR.mem_brn_id) AND UI.MEM_BNK_ID = MR.mem_bnk_id GROUP BY PM.PortFolioName,MR.mem_bnk_name";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			list = query.list();
		} else {
			String hql = "SELECT (PM.PortFolioName),MAX(MR.mem_bnk_name),MAX(UI.USR_ID) FROM FileUploadModel IU,PortFolioDetailsInParentTBL PM,DanCgpanDetailsModel DC,MLIRegistration MR,UserInfo UI  WHERE PM.portfolio_no = IU.portfolioNo AND DC.DCI_APPROPRIATION_FLAG='Y' AND IU.danId = DC.danId AND concat(concat(IU.mem_bank_id,IU.mem_zne_id),IU.mem_brn_id) = concat(concat(MR.mem_bnk_id,MR.mem_zne_id),MR.mem_brn_id) AND concat(concat(MR.mem_bnk_id,MR.mem_zne_id),MR.mem_brn_id) = :MLIID AND UI.MEM_BNK_ID = MR.mem_bnk_id GROUP BY PM.PortFolioName,MR.mem_bnk_name";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("MLIID", mliId);
			list = query.list();
		}

		return list;
	}

	@Override
	public List<Object> proformaInvoiceData(String mliId, HttpSession session) {
		String Role = (String) session.getAttribute("uRole");
		List<Object> list;
		if (Role.equals("CMAKER") || Role.equals("CCHECKER")) {
			String hql = "SELECT (PM.PortFolioName),max(MR.mem_bnk_name),MAX(UI.USR_ID) FROM FileUploadModel IU,PortFolioDetailsInParentTBL PM,DanCgpanDetailsModel DC,MLIRegistration MR,UserInfo UI WHERE PM.portfolio_no = IU.portfolioNo AND DC.DCI_APPROPRIATION_FLAG='N' AND IU.danId = DC.danId AND concat(concat(IU.mem_bank_id,IU.mem_zne_id),IU.mem_brn_id) = concat(concat(MR.mem_bnk_id,MR.mem_zne_id),MR.mem_brn_id) AND UI.MEM_BNK_ID = MR.mem_bnk_id GROUP BY PM.PortFolioName,MR.mem_bnk_name";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			list = query.list();
		} else {
			String hql = "SELECT (PM.PortFolioName),max(MR.mem_bnk_name),MAX(UI.USR_ID) FROM FileUploadModel IU,PortFolioDetailsInParentTBL PM,DanCgpanDetailsModel DC,MLIRegistration MR,UserInfo UI WHERE PM.portfolio_no = IU.portfolioNo AND DC.DCI_APPROPRIATION_FLAG='N' AND IU.danId = DC.danId AND concat(concat(IU.mem_bank_id,IU.mem_zne_id),IU.mem_brn_id) = concat(concat(MR.mem_bnk_id,MR.mem_zne_id),MR.mem_brn_id) AND concat(concat(MR.mem_bnk_id,MR.mem_zne_id),MR.mem_brn_id) = :MLIID AND UI.MEM_BNK_ID = MR.mem_bnk_id GROUP BY PM.PortFolioName,MR.mem_bnk_name";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("MLIID", mliId);
			list = query.list();
		}

		return list;
	}

	@Override
	public Integer getProformaInvoiceIdCount(String portfolioName) {
		try {
			String hql = "SELECT count(*) FROM ProformaInvoiceDetails where PORTFOLIO_NAME=:PORTFOLIO ";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("PORTFOLIO", portfolioName);
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			System.out.println("Exception===" + e);
		}
		return 0;
	}

	@Override
	public Integer getMaxProformaInvoiceIdCount() {
		try {
			String hql = "SELECT MAX(PROFORMA_INV_ID)+1 FROM ProformaInvoiceDetails ";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			System.out.println("Exception===" + e);
		}
		return 0;
	}

	@Override
	public String getProformaInvoiceNo(String portfolioName) {
		String Proformaid = "";
		try {
			Session session = sessionFactory.openSession();
			Transaction tn = session.beginTransaction();
			Connection connection = ((SessionImpl) session).connection();

			Query query = session
					.createQuery("SELECT PROFORMA_INV_ID FROM ProformaInvoiceDetails WHERE PORTFOLIO_NAME = :PORTFOLIO");
			query.setParameter("PORTFOLIO", portfolioName);

			List<Long> rows = query.list();
			System.out.println("current row>>>>>>  " + rows.toString());

			for (Long val : rows) {
				Proformaid = val.toString();
			}

			return Proformaid;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Modified By Parmanand 09-Jan-2019
	@Override
	public void callProformaInvoiceReport(String portfolioName,
			HttpServletResponse response, HttpServletRequest request,
			CGPANDetailsReportBean bean) {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		NBFCHelper nbfcHelper = new NBFCHelper();
		Connection connection = ((SessionImpl) session).connection();
		JasperPrint jasperPrint1 = null;
		if (connection == null) {
			System.out.println("connection is null.........");
		}

		String date = bean.getDciGuaranteeeSDate();
		String appDate = bean.getAppsubmittedDate();
		String ProformaNo = bean.getTaxInvoiceId().toString();
		System.out.println("ProformaNo===" + ProformaNo);
		Date myDate = new Date();
		System.out.println(myDate);
		SimpleDateFormat mdyFormat1 = new SimpleDateFormat("MM/dd/yy");

		// String mdy1 = mdyFormat1.format(appDate);
		// System.out.println("sysDate==="+mdy1);
		String[] formatedDate = appDate.split("/");
		String finalDate = formatedDate[0] + formatedDate[1] + formatedDate[2];
		/*
		 * try { System.out.println(mdyFormat1.parse(mdy1)); } catch
		 * (ParseException e1) { e1.printStackTrace(); }
		 */

		String ProformaInvoiceNo = finalDate + "0000" + ProformaNo;
		String State = bean.getState();
		String userId = bean.getUserId();
//-------------------------------------------------------------------------------------
		double dci_base_amt = bean.getDci_base_amt() ;
		BigDecimal dci_base_amt1 = new BigDecimal(dci_base_amt);
		BigDecimal dci_base_amt2 = dci_base_amt1.setScale(2, RoundingMode.HALF_UP);
	//	String taxBaseAmountInRupes = dci_base_amt2.toPlainString();
		
		double dci_total_amt = Math.round(bean.getDci_total_amt());
		BigDecimal dci_total_amt1 = new BigDecimal(dci_total_amt);
		BigDecimal dci_total_amt2 = dci_total_amt1.setScale(2, RoundingMode.HALF_UP);
//-----------------------------------------------------------------------------------------		
		double outSandingAndGuran = ((bean.getOutstandingAmount1()) * (bean
				.getGuaranteeFee() / 100));
		double outSandingAndGuranRound = Math.round(outSandingAndGuran);
		
		BigDecimal taxAmt = new BigDecimal(outSandingAndGuranRound);
		BigDecimal taxAmountInRupes2 = taxAmt.setScale(2, RoundingMode.HALF_UP);
		
	//	String taxAmountInRupes = taxAmountInRupes2.toPlainString();

		double objTotalAmt2 = Math.round(bean.getIgstAmt()) +Math.round( bean.getCgstAmt())
				+Math.round( bean.getSgstAmt()) + outSandingAndGuran;
		BigDecimal objTotalAmt = new BigDecimal(Math.round(objTotalAmt2));
		BigDecimal objFinalToatlAmt1 = objTotalAmt.setScale(2,
				RoundingMode.HALF_UP);
		//--------------------------------------------------------------------
		String fianlTotalAmountInRupes = dci_total_amt2.toPlainString();
		long fianlTotalAmountInRupes1 = dci_total_amt2.longValue();
		String sim2 = NumberToWordsFormatMethod
				.inWordFormat(fianlTotalAmountInRupes1);
//-----------------------------------------------------------------------------------
	/*	long fianlTotalAmountInRupes1 = objFinalToatlAmt1.longValue();
		String sim2 = NumberToWordsFormatMethod
				.inWordFormat(fianlTotalAmountInRupes1);*/
//----------------------------------------------------------------------
		double IGSTAMT = Math.round(bean.getIgstAmt());
		double CGSTAMT =Math.round(bean.getCgstAmt());
		double SGSTAMT = Math.round(bean.getSgstAmt());
		
		BigDecimal IGST_AMT= new BigDecimal(IGSTAMT);
		BigDecimal IGST_AMT1 = IGST_AMT.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal CGST_AMT = new BigDecimal(CGSTAMT);
		BigDecimal CGST_AMT1 = CGST_AMT.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal SGST_AMT = new BigDecimal(SGSTAMT);
		BigDecimal SGST_AMT1 = SGST_AMT.setScale(2, RoundingMode.HALF_UP);
		
		HashMap map1 = new HashMap();
		map1.put("TotalIgstAmt", IGST_AMT1);
		map1.put("TotalCgstAmt", CGST_AMT1);
		map1.put("TotalSgstAmt", SGST_AMT1);
		map1.put("TaxInvoiceNo", ProformaInvoiceNo);
		map1.put("TaxAmount", taxAmountInRupes2);// taxAmt tTax
		map1.put("finalAmt", fianlTotalAmountInRupes1);// totalAmt tAmt
		map1.put("TotalAmount", dci_total_amt2);// totalAmt tAmt
		map1.put("TotalAmountInWords", sim2);
		map1.put("StateName", State);
		map1.put("portfolioName", portfolioName);
		map1.put("userId", userId);
		try {
			String filename = "ParaformaInvoiceReport1";
			String reportfileName = "ProformaReport" + portfolioName + ".pdf";
			String tempFolderPath = "/WEB-INF/JReport/" + filename;
			System.out.println("path:" + tempFolderPath);
			File tempFolder = new File(tempFolderPath);
			if (!tempFolder.exists()) {
				tempFolder.mkdirs();
			}
			String jasperFilePath = tempFolderPath + ".jasper";
			File reportFile = new File(jasperFilePath);
			if (!reportFile.exists()) {
				InputStream jRXmlStream = request
						.getSession()
						.getServletContext()
						.getResourceAsStream(
								"/WEB-INF/JReport/" + filename + ".jrxml");
				JasperDesign jasperDesign = JRXmlLoader.load(jRXmlStream);
				JasperCompileManager.compileReportToFile(jasperDesign,
						jasperFilePath);
				System.out.println(jasperFilePath);
			}
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObjectFromFile(reportFile.getPath());
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, map1, connection);
			JRAbstractExporter exporterPDF = new JRPdfExporter();
			exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			try {
				exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,
						response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			response.setHeader("Content-Disposition", "attachment;filename="
					+ reportfileName);
			response.setContentType("application/pdf");
			exporterPDF.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tn.commit();

		}

	}

	@Override
	public void insertProformaInvoiceDetails(ProformaInvoiceDetails proformaObj1) {
		sessionFactory.getCurrentSession().save(proformaObj1);
	}

	@Override
	public List<Object> getTaxInvoiceDataASF(String mliId, HttpSession session) {
		String Role = (String) session.getAttribute("uRole");
		List<Object> list;
		if (Role.equals("CMAKER") || Role.equals("CCHECKER")) {
			String hql = "SELECT DISTINCT(DC.danId),(MR.mem_bnk_name) FROM FileUploadModel IU,DanCgpanDetailsModel DC,MLIRegistration MR,DemandDeviceModel DM WHERE  DC.DCI_APPROPRIATION_FLAG='Y' AND concat(concat(DM.mem_bank_id,DM.mem_zne_id),DM.mem_brn_id) = concat(concat(MR.mem_bnk_id,MR.mem_zne_id),MR.mem_brn_id) AND DM.danId=DC.danId AND DM.Dan_type='AF'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			list = query.list();
		} else {
			String hql = "SELECT DISTINCT(DC.danId),(MR.mem_bnk_name) FROM FileUploadModel IU,DanCgpanDetailsModel DC,MLIRegistration MR, DemandDeviceModel DM  WHERE  DC.DCI_APPROPRIATION_FLAG='Y' AND  concat(concat(DM.mem_bank_id,DM.mem_zne_id),DM.mem_brn_id) = concat(concat(MR.mem_bnk_id,MR.mem_zne_id),MR.mem_brn_id) AND concat(concat(MR.mem_bnk_id,MR.mem_zne_id),MR.mem_brn_id) = :MLIID AND DM.danId=DC.danId AND DM.Dan_type='AF'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("MLIID", mliId);
			list = query.list();
		}

		return list;

	}

	@Override
	public Integer getTaxInvoiceIdCountASF(String danId) {
		try {
			String hql = "SELECT count(*) FROM TaxInvoiceDetailsASF where DAN_ID=:DANID ";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("DANID", danId);
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			System.out.println("Exception===" + e);
		}
		return 0;
	}

	@Override
	public List<Object> getTaxReportDetailsASF(String danId) {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		Connection connection = ((SessionImpl) session).connection();
		String sql = " SELECT "
				+ " MAX (DI.DAN_id) DAN_ID,"
				+ " MAX (ROUND (di.IGST_AMT)) IGST_AMT,"
				+ " MAX (ROUND (di.CGST_AMT)) CGST_AMT,"
				+ " MAX (ROUND (di.SGST_AMT)) SGST_AMT,"
				+ " ROUND(SUM (IU.OUTSTANDING_AMOUNT)) OUTSTANDING_AMT,"
				+ " MAX (di.DCI_APPROPRIATION_DT) AS APPR_DT,"
				+ " MAX (di.DCI_STANDARD_RATE) GUARANTEE_FEE,"
				+ " MAX (di.DCI_GUARANTEE_START_DT) AS GSD,"
				+ " MAX((SELECT NM.MEM_STATE_NAME FROM NBFC_MEMBER_INFO NM WHERE NM.MEM_BNK_ID=IU.MEM_BNK_ID)) STATE_CODE,"
				+ " MAX((SELECT SM.STE_NAME FROM  NBFC_STATE_MASTER SM ,NBFC_MEMBER_INFO NM WHERE NM.MEM_BNK_ID=IU.MEM_BNK_ID AND NM.MEM_STATE_NAME=SM.STE_CODE)) STATE_NAME, " 
				+ " MAX(iu.NBFC_UPLOADED_DATE) AS Application_SubmittedDate "
				+ "  FROM nbfc_interface_upload iu, nbfc_dan_cgpan_info di, nbfc_asf_detail ad"
				+ "   WHERE  ad.ASF_DAN_ID = DI.DAN_ID "
				+ "   AND IU.FILE_ID = AD.FILE_ID " 
				+ "   AND ad.cgpan = iu.cgpan "
				+ "   AND DI.DAN_ID = '" + danId + "'";
		System.out.println("Query-------" + sql);
		SQLQuery query = session.createSQLQuery(sql);
		List<Object> list = query.list();
		return list;
	}

	// Modified By Parmanand 09-Jan-2019
	@Override
	public void callTaxInvoiceReportASF(String danId,
			HttpServletResponse response, HttpServletRequest request,
			CGPANDetailsReportBean bean) {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		Connection connection = ((SessionImpl) session).connection();
		JasperPrint jasperPrint1 = null;
		if (connection == null) {
			System.out.println("connection is null.........");
		}
		NBFCHelper nbfcHelper = new NBFCHelper();
		String State = bean.getState();
		String DAN = bean.getDanId();
		String userId = bean.getUserId();
		String date = bean.getDciAppropriationDate();
		String TaxNo = bean.getTaxInvoiceId().toString();
		String fyBasedOnStartAndEndDate = nbfcHelper.getCurrentYear();
		StringBuilder sb = new StringBuilder(fyBasedOnStartAndEndDate);
		sb.delete(5, 7);
		String fy = sb.toString();

		String TaxInvoiceNo = fy + "N0000000" + TaxNo;

		double outSandingAndGuran = ((bean.getOutstandingAmount1()
				* bean.getGuaranteeFee() / 100));
		double outSandingAndGuranRound = Math.round(outSandingAndGuran);
		BigDecimal taxAmt = new BigDecimal(outSandingAndGuranRound);
		BigDecimal taxAmountInRupes2 = taxAmt.setScale(2, RoundingMode.HALF_UP);
		
		String taxAmountInRupes = taxAmountInRupes2.toPlainString();
		double objTotalAmt2 = bean.getIgstAmt() + bean.getCgstAmt()
				+ bean.getSgstAmt() + outSandingAndGuran;
		BigDecimal objTotalAmt = new BigDecimal(Math.round(objTotalAmt2));
		BigDecimal objFinalToatlAmt1 = objTotalAmt.setScale(2,
				RoundingMode.HALF_UP);
		String fianlTotalAmountInRupes = objFinalToatlAmt1.toPlainString();
		long fianlTotalAmountInRupes1 = objFinalToatlAmt1.longValue();
		String sim2 = NumberToWordsFormatMethod
				.inWordFormat(fianlTotalAmountInRupes1);
		double IGSTAMT = Math.round(bean.getIgstAmt());
		double CGSTAMT =Math.round(bean.getCgstAmt());
		double SGSTAMT = Math.round(bean.getSgstAmt());
		
		BigDecimal IGST_AMT= new BigDecimal(IGSTAMT);
		BigDecimal IGST_AMT1 = IGST_AMT.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal CGST_AMT = new BigDecimal(CGSTAMT);
		BigDecimal CGST_AMT1 = CGST_AMT.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal SGST_AMT = new BigDecimal(SGSTAMT);
		BigDecimal SGST_AMT1 = SGST_AMT.setScale(2, RoundingMode.HALF_UP);
		
		HashMap map = new HashMap();
		map.put("TotalIgstAmt", IGST_AMT1);
		map.put("TotalCgstAmt", CGST_AMT1);
		map.put("TotalSgstAmt", SGST_AMT1);
		map.put("TaxInvoiceNo", TaxInvoiceNo);
		map.put("TaxAmount", taxAmountInRupes2);
		map.put("TotalAmount", objFinalToatlAmt1);
		map.put("TotalAmountInWords", sim2);
		map.put("StateName", State);
		map.put("danId", danId);
		map.put("userId", userId);
		try {
			String filename = "TaxInvoiceReportASF1";
			String reportfileName = "TaxreportASF" + danId + ".pdf";
			String tempFolderPath = "/WEB-INF/JReport/" + filename;
			System.out.println("path:" + tempFolderPath);
			File tempFolder = new File(tempFolderPath);
			if (!tempFolder.exists()) {
				tempFolder.mkdirs();
			}
			String jasperFilePath = tempFolderPath + ".jasper";
			File reportFile = new File(jasperFilePath);
			if (!reportFile.exists()) {
				InputStream jRXmlStream = request
						.getSession()
						.getServletContext()
						.getResourceAsStream(
								"/WEB-INF/JReport/" + filename + ".jrxml");
				JasperDesign jasperDesign = JRXmlLoader.load(jRXmlStream);
				JasperCompileManager.compileReportToFile(jasperDesign,
						jasperFilePath);
				System.out.println(jasperFilePath);
			}
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObjectFromFile(reportFile.getPath());
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, map, connection);
			JRAbstractExporter exporterPDF = new JRPdfExporter();
			exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			try {
				exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,
						response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			response.setHeader("Content-Disposition", "attachment;filename="
					+ reportfileName);
			response.setContentType("application/pdf");
			exporterPDF.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tn.commit();

		}

	}

	@Override
	public Integer getMaxTaxInvoiceIdCountASF() {
		try {
			String hql = "SELECT MAX(TAX_INV_ID)+1 FROM TaxInvoiceDetailsASF ";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			System.out.println("Exception===" + e);
		}
		return 0;
	}

	@Override
	public void insertTaxInvoiceDetailsASF(TaxInvoiceDetailsASF taxDetailsObj) {
		sessionFactory.getCurrentSession().save(taxDetailsObj);
	}

	@Override
	public String getTaxInvoiceNoASF(String danId) {
		String Taxid = "";
		try {
			Session session = sessionFactory.openSession();
			Transaction tn = session.beginTransaction();
			Connection connection = ((SessionImpl) session).connection();
			Query query = session
					.createQuery("SELECT TAX_INV_ID FROM TaxInvoiceDetailsASF WHERE DAN_ID = :DANID");
			query.setParameter("DANID", danId);
			List<Long> rows = query.list();
			System.out.println("current row>>>>>>  " + rows.toString());
			for (Long val : rows) {
				Taxid = val.toString();
			}

			return Taxid;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> proformaInvoiceDataASF(String mliId, HttpSession session) {
		String Role = (String) session.getAttribute("uRole");
		List<Object> list;
		if (Role.equals("CMAKER") || Role.equals("CCHECKER")) {
			String hql = "SELECT DISTINCT(DC.danId),(MR.mem_bnk_name) FROM FileUploadModel IU,DanCgpanDetailsModel DC,MLIRegistration MR,DemandDeviceModel DM WHERE DC.DCI_APPROPRIATION_FLAG='N' AND concat(concat(DM.mem_bank_id,DM.mem_zne_id),DM.mem_brn_id) = concat(concat(MR.mem_bnk_id,MR.mem_zne_id),MR.mem_brn_id) AND DM.danId=DC.danId AND DM.Dan_type='AF'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			list = query.list();
		} else {
			String hql = "SELECT DISTINCT(DC.danId),(MR.mem_bnk_name) FROM FileUploadModel IU,DanCgpanDetailsModel DC,MLIRegistration MR, DemandDeviceModel DM  WHERE DC.DCI_APPROPRIATION_FLAG='N' AND  concat(concat(DM.mem_bank_id,DM.mem_zne_id),DM.mem_brn_id) = concat(concat(MR.mem_bnk_id,MR.mem_zne_id),MR.mem_brn_id) AND concat(concat(MR.mem_bnk_id,MR.mem_zne_id),MR.mem_brn_id) = :MLIID AND DM.danId=DC.danId AND DM.Dan_type='AF'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("MLIID", mliId);
			list = query.list();
		}

		return list;
	}

	@Override
	public Integer getProformaInvoiceIdCountASF(String danId) {
		try {
			String hql = "SELECT count(*) FROM ProformaInvoiceDetailsASF where PORTFOLIO_NAME=:DAN ";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("DAN", danId);
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			System.out.println("Exception===" + e);
		}
		return 0;
	}

	@Override
	public Integer getMaxProformaInvoiceIdCountASF() {
		try {
			String hql = "SELECT MAX(PROFORMA_INV_ID)+1 FROM ProformaInvoiceDetailsASF ";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			System.out.println("Exception===" + e);
		}
		return 0;
	}

	// Modified By Parmanand 09-Jan-2019
	@Override
	public void callProformaInvoiceReportASF(String danId,
			HttpServletResponse response, HttpServletRequest request,
			CGPANDetailsReportBean bean) {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		Connection connection = ((SessionImpl) session).connection();
		JasperPrint jasperPrint1 = null;
		if (connection == null) {
			System.out.println("connection is null.........");
		}
		
		
		
		
		
		
		String State = bean.getState();
		String DAN = bean.getDanId();
		String userId = bean.getUserId();
		String date = bean.getDciGuaranteeeSDate();
		String TaxNo = bean.getTaxInvoiceId().toString();
		String id = date.replace("/", "");
		String TaxInvoiceNo = id + TaxNo;
		System.out.println("taxno--------------" + TaxInvoiceNo);

		double outSandingAndGuran = ((bean.getOutstandingAmount1()
				* bean.getGuaranteeFee() / 100));
		double outSandingAndGuranRound = Math.round(outSandingAndGuran);
		BigDecimal taxAmt = new BigDecimal(outSandingAndGuranRound);
		BigDecimal taxAmountInRupes2 = taxAmt.setScale(2, RoundingMode.HALF_UP);
		String taxAmountInRupes = taxAmountInRupes2.toPlainString();
		double objTotalAmt2 = bean.getIgstAmt() + bean.getCgstAmt()
				+ bean.getSgstAmt() + outSandingAndGuran;
		BigDecimal objTotalAmt = new BigDecimal(Math.round(objTotalAmt2));
		BigDecimal objFinalToatlAmt1 = objTotalAmt.setScale(2,
				RoundingMode.HALF_UP);
		String fianlTotalAmountInRupes = objFinalToatlAmt1.toPlainString();
		long fianlTotalAmountInRupes1 = objFinalToatlAmt1.longValue();
		String sim2 = NumberToWordsFormatMethod
				.inWordFormat(fianlTotalAmountInRupes1);

		double IGSTAMT = Math.round(bean.getIgstAmt());
		double CGSTAMT =Math.round(bean.getCgstAmt());
		double SGSTAMT = Math.round(bean.getSgstAmt());
		
		
		BigDecimal IGST_AMT= new BigDecimal(IGSTAMT);
		BigDecimal IGST_AMT1 = IGST_AMT.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal CGST_AMT = new BigDecimal(CGSTAMT);
		BigDecimal CGST_AMT1 = CGST_AMT.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal SGST_AMT = new BigDecimal(SGSTAMT);
		BigDecimal SGST_AMT1 = SGST_AMT.setScale(2, RoundingMode.HALF_UP);
		
		HashMap map = new HashMap();
		map.put("TotalIgstAmt", IGST_AMT1);
		map.put("TotalCgstAmt", CGST_AMT1);
		map.put("TotalSgstAmt", SGST_AMT1);
		map.put("TaxInvoiceNo", TaxInvoiceNo);
		map.put("TaxAmount", taxAmountInRupes2);
		map.put("TotalAmount", objFinalToatlAmt1);
		map.put("TotalAmountInWords", sim2);
		map.put("StateName", State);
		map.put("danId", danId);
		map.put("userId", userId);
		try {
			String filename = "ParaformaInvoiceReportASF1";
			String reportfileName = "ProformaReportASF" + danId + ".pdf";
			String tempFolderPath = "/WEB-INF/JReport/" + filename;
			System.out.println("path:" + tempFolderPath);
			File tempFolder = new File(tempFolderPath);
			if (!tempFolder.exists()) {
				tempFolder.mkdirs();
			}
			String jasperFilePath = tempFolderPath + ".jasper";
			File reportFile = new File(jasperFilePath);
			if (!reportFile.exists()) {
				InputStream jRXmlStream = request
						.getSession()
						.getServletContext()
						.getResourceAsStream(
								"/WEB-INF/JReport/" + filename + ".jrxml");
				JasperDesign jasperDesign = JRXmlLoader.load(jRXmlStream);
				JasperCompileManager.compileReportToFile(jasperDesign,
						jasperFilePath);
				System.out.println(jasperFilePath);
			}
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObjectFromFile(reportFile.getPath());
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, map, connection);
			JRAbstractExporter exporterPDF = new JRPdfExporter();
			exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			try {
				exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,
						response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			response.setHeader("Content-Disposition", "attachment;filename="
					+ reportfileName);
			response.setContentType("application/pdf");
			exporterPDF.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tn.commit();

		}

	}

	@Override
	public void insertProformaInvoiceDetailsASF(
			ProformaInvoiceDetailsASF proformaObj1) {
		sessionFactory.getCurrentSession().save(proformaObj1);
	}

	@Override
	public String getProformaInvoiceNoASF(String danId) {
		String Proformaid = "";
		try {
			Session session = sessionFactory.openSession();
			Transaction tn = session.beginTransaction();
			Connection connection = ((SessionImpl) session).connection();

			Query query = session
					.createQuery("SELECT PROFORMA_INV_ID FROM ProformaInvoiceDetailsASF WHERE DAN_ID = :DAN");
			query.setParameter("DAN", danId);

			List<Long> rows = query.list();
			System.out.println("current row>>>>>>  " + rows.toString());

			for (Long val : rows) {
				Proformaid = val.toString();
			}

			return Proformaid;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
