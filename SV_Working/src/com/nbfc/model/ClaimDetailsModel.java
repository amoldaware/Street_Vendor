package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NBFC_CLAIM_DETAIL")
public class ClaimDetailsModel {
	
		@Id
		@Column(name = "CLAIM_REF_NO")
		private String CLAIM_REF_NO;
		
		
		@Column(name="STATUS")
		private String STATUS;
		
		@Column(name="REMARKS")
		private String REMARKS="";
		
		@Column(name="CGTMSE_CK_REMARK")
		private String CGTMSE_CK_REMARK;
		
		@Column(name="NBFC_CHECKER_ID")
		private String NBFC_CHECKER_ID;
		
		@Column(name="NBFC_CHECKER_DATE")
		private String NBFC_CHECKER_DATE;

		@Column(name="CGTMSE_CHECKER_DATE")
		private String CGTMSE_CHECKER_DATE;
		
		@Column(name="CGTMSE_CHECKER_ID")
		private String CGTMSE_CHECKER_ID;
		
		@Column(name="CGS_RETURN_REMARK")
		private String CGS_RETURN_REMARK;

		
		public String getCGS_RETURN_REMARK() {
			return CGS_RETURN_REMARK;
		}


		public void setCGS_RETURN_REMARK(String cGS_RETURN_REMARK) {
			CGS_RETURN_REMARK = cGS_RETURN_REMARK;
		}


		public String getCGTMSE_CK_REMARK() {
			return CGTMSE_CK_REMARK;
		}


		public void setCGTMSE_CK_REMARK(String cGTMSE_CK_REMARK) {
			CGTMSE_CK_REMARK = cGTMSE_CK_REMARK;
		}


		public String getREMARKS() {
			return REMARKS;
		}


		public void setREMARKS(String rEMARKS) {
			REMARKS = rEMARKS;
		}



		
		public String getCGTMSE_CHECKER_DATE() {
			return CGTMSE_CHECKER_DATE;
		}


		public void setCGTMSE_CHECKER_DATE(String cGTMSE_CHECKER_DATE) {
			CGTMSE_CHECKER_DATE = cGTMSE_CHECKER_DATE;
		}


		public String getCGTMSE_CHECKER_ID() {
			return CGTMSE_CHECKER_ID;
		}


		public void setCGTMSE_CHECKER_ID(String cGTMSE_CHECKER_ID) {
			CGTMSE_CHECKER_ID = cGTMSE_CHECKER_ID;
		}


		

		public String getNBFC_CHECKER_ID() {
			return NBFC_CHECKER_ID;
		}


		public void setNBFC_CHECKER_ID(String nBFC_CHECKER_ID) {
			NBFC_CHECKER_ID = nBFC_CHECKER_ID;
		}


		public String getNBFC_CHECKER_DATE() {
			return NBFC_CHECKER_DATE;
		}


		public void setNBFC_CHECKER_DATE(String nBFC_CHECKER_DATE) {
			NBFC_CHECKER_DATE = nBFC_CHECKER_DATE;
		}


		public String getCLAIM_REF_NO() {
			return CLAIM_REF_NO;
		}


		public void setCLAIM_REF_NO(String cLAIM_REF_NO) {
			CLAIM_REF_NO = cLAIM_REF_NO;
		}


		public String getSTATUS() {
			return STATUS;
		}


		public void setSTATUS(String sTATUS) {
			STATUS = sTATUS;
		}
		
}
