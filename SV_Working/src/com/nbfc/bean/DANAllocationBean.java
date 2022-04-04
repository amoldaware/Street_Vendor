package com.nbfc.bean;

import java.util.Date;
import java.util.List;

public class DANAllocationBean {

	private String portfoliName;
	private String fileName;
	
	private String totalFee;
	private String portfolioRate;
	private boolean selectionStatus;
	private String dateOfRPGenration;
    private List<String> danIDList;
    private List<String> cgPanList;
    private String rpNUmber;
    private String totalAmount;
    private String currentDate;
    private List<String> checkbtn;
    private String borwerName;
    private String sanctionAmount;
    private String loneAccountNo;
    private String sanctionDate;
    private String disbursermentDate;
    private String calculatedFee;
    private String rateOfInterest;


	
	public String getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(String rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public String getSanctionDate() {
		return sanctionDate;
	}

	public void setSanctionDate(String sanctionDate) {
		this.sanctionDate = sanctionDate;
	}

	public String getDisbursermentDate() {
		return disbursermentDate;
	}

	public void setDisbursermentDate(String disbursermentDate) {
		this.disbursermentDate = disbursermentDate;
	}

	public String getCalculatedFee() {
		return calculatedFee;
	}

	public void setCalculatedFee(String calculatedFee) {
		this.calculatedFee = calculatedFee;
	}

	public String getLoneAccountNo() {
		return loneAccountNo;
	}

	public void setLoneAccountNo(String loneAccountNo) {
		this.loneAccountNo = loneAccountNo;
	}

	public String getSanctionAmount() {
		return sanctionAmount;
	}

	public void setSanctionAmount(String sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}

	public String getBorwerName() {
		return borwerName;
	}

	public void setBorwerName(String borwerName) {
		this.borwerName = borwerName;
	}

	public List<String> getCheckbtn() {
		return checkbtn;
	}

	public void setCheckbtn(List<String> checkbtn) {
		this.checkbtn = checkbtn;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getRpNUmber() {
		return rpNUmber;
	}

	public void setRpNUmber(String rpNUmber) {
		this.rpNUmber = rpNUmber;
	}

	public List<String> getDanIDList() {
		return danIDList;
	}

	public void setDanIDList(List<String> danIDList) {
		this.danIDList = danIDList;
	}

	public List<String> getCgPanList() {
		return cgPanList;
	}

	public void setCgPanList(List<String> cgPanList) {
		this.cgPanList = cgPanList;
	}

	public String getDateOfRPGenration() {
		return dateOfRPGenration;
	}

	public void setDateOfRPGenration(String dateOfRPGenration) {
		this.dateOfRPGenration = dateOfRPGenration;
	}



	private String DAN_ID;
	private String DAN_TYPE;
	private String MEM_BNK_ID;
	private String MEM_ZNE_ID;
	private String MEM_BRN_ID;
	private Date DAN_GENERATED_DT;
	private Date DAN_DUE_DT;
	private Date DAN_EXPIRY_DT;
	private String DAN_WAIVED_FLAG;
	private String DAN_STATUS;
	private String DAN_CREATED_MODIFIED_BY;
	private Date DAN_CREATED_MODIFIED_DT;
	private String IS_CGTMSE_PROCESSED;
	private String IS_CGTMSE_PROCESSED_DATE;
	private String DAN_FSTATUS;
	private String NBFC_MAKER_ID;
	private String NBFC_MAKER_DATE;
	private String NBFC_CHECKER_ID;
	private Date NBFC_CHECKER_DATE;

	private String mliId;

	
	public String getMliId() {
		return mliId;
	}

	public void setMliId(String mliId) {
		this.mliId = mliId;
	}

	public String getDAN_ID() {
		return DAN_ID;
	}

	public void setDAN_ID(String dAN_ID) {
		DAN_ID = dAN_ID;
	}

	public String getDAN_TYPE() {
		return DAN_TYPE;
	}

	public void setDAN_TYPE(String dAN_TYPE) {
		DAN_TYPE = dAN_TYPE;
	}

	public String getMEM_BNK_ID() {
		return MEM_BNK_ID;
	}

	public void setMEM_BNK_ID(String mEM_BNK_ID) {
		MEM_BNK_ID = mEM_BNK_ID;
	}

	public String getMEM_ZNE_ID() {
		return MEM_ZNE_ID;
	}

	public void setMEM_ZNE_ID(String mEM_ZNE_ID) {
		MEM_ZNE_ID = mEM_ZNE_ID;
	}

	public String getMEM_BRN_ID() {
		return MEM_BRN_ID;
	}

	public void setMEM_BRN_ID(String mEM_BRN_ID) {
		MEM_BRN_ID = mEM_BRN_ID;
	}

	public Date getDAN_GENERATED_DT() {
		return DAN_GENERATED_DT;
	}

	public void setDAN_GENERATED_DT(Date dAN_GENERATED_DT) {
		DAN_GENERATED_DT = dAN_GENERATED_DT;
	}

	public Date getDAN_DUE_DT() {
		return DAN_DUE_DT;
	}

	public void setDAN_DUE_DT(Date dAN_DUE_DT) {
		DAN_DUE_DT = dAN_DUE_DT;
	}

	public Date getDAN_EXPIRY_DT() {
		return DAN_EXPIRY_DT;
	}

	public void setDAN_EXPIRY_DT(Date dAN_EXPIRY_DT) {
		DAN_EXPIRY_DT = dAN_EXPIRY_DT;
	}

	public String getDAN_WAIVED_FLAG() {
		return DAN_WAIVED_FLAG;
	}

	public void setDAN_WAIVED_FLAG(String dAN_WAIVED_FLAG) {
		DAN_WAIVED_FLAG = dAN_WAIVED_FLAG;
	}

	public String getDAN_STATUS() {
		return DAN_STATUS;
	}

	public void setDAN_STATUS(String dAN_STATUS) {
		DAN_STATUS = dAN_STATUS;
	}

	public String getDAN_CREATED_MODIFIED_BY() {
		return DAN_CREATED_MODIFIED_BY;
	}

	public void setDAN_CREATED_MODIFIED_BY(String dAN_CREATED_MODIFIED_BY) {
		DAN_CREATED_MODIFIED_BY = dAN_CREATED_MODIFIED_BY;
	}

	public Date getDAN_CREATED_MODIFIED_DT() {
		return DAN_CREATED_MODIFIED_DT;
	}

	public void setDAN_CREATED_MODIFIED_DT(Date dAN_CREATED_MODIFIED_DT) {
		DAN_CREATED_MODIFIED_DT = dAN_CREATED_MODIFIED_DT;
	}

	public String getIS_CGTMSE_PROCESSED() {
		return IS_CGTMSE_PROCESSED;
	}

	public void setIS_CGTMSE_PROCESSED(String iS_CGTMSE_PROCESSED) {
		IS_CGTMSE_PROCESSED = iS_CGTMSE_PROCESSED;
	}

	public String getIS_CGTMSE_PROCESSED_DATE() {
		return IS_CGTMSE_PROCESSED_DATE;
	}

	public void setIS_CGTMSE_PROCESSED_DATE(String iS_CGTMSE_PROCESSED_DATE) {
		IS_CGTMSE_PROCESSED_DATE = iS_CGTMSE_PROCESSED_DATE;
	}

	public String getDAN_FSTATUS() {
		return DAN_FSTATUS;
	}

	public void setDAN_FSTATUS(String dAN_FSTATUS) {
		DAN_FSTATUS = dAN_FSTATUS;
	}

	public String getNBFC_MAKER_ID() {
		return NBFC_MAKER_ID;
	}

	public void setNBFC_MAKER_ID(String nBFC_MAKER_ID) {
		NBFC_MAKER_ID = nBFC_MAKER_ID;
	}

	public String getNBFC_MAKER_DATE() {
		return NBFC_MAKER_DATE;
	}

	public void setNBFC_MAKER_DATE(String nBFC_MAKER_DATE) {
		NBFC_MAKER_DATE = nBFC_MAKER_DATE;
	}

	public String getNBFC_CHECKER_ID() {
		return NBFC_CHECKER_ID;
	}

	public void setNBFC_CHECKER_ID(String nBFC_CHECKER_ID) {
		NBFC_CHECKER_ID = nBFC_CHECKER_ID;
	}

	public Date getNBFC_CHECKER_DATE() {
		return NBFC_CHECKER_DATE;
	}

	public void setNBFC_CHECKER_DATE(Date nBFC_CHECKER_DATE) {
		NBFC_CHECKER_DATE = nBFC_CHECKER_DATE;
	}

	public DANAllocationBean() {
		super();
	}

	public DANAllocationBean(String portfoliName, String fileName,
			String totalFee, String portfolioRate) {
		super();
		this.portfoliName = portfoliName;
		this.fileName = fileName;
		this.totalFee = totalFee;
		this.portfolioRate = portfolioRate;

	}

	public String getPortfoliName() {
		return portfoliName;
	}

	public void setPortfoliName(String portfoliName) {
		this.portfoliName = portfoliName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getPortfolioRate() {
		return portfolioRate;
	}

	public void setPortfolioRate(String portfolioRate) {
		this.portfolioRate = portfolioRate;
	}

	public boolean isSelectionStatus() {
		return selectionStatus;
	}

	public void setSelectionStatus(boolean selectionStatus) {
		this.selectionStatus = selectionStatus;
	}


	
	private String rpNumber;
	private String danId;
	private String remark;

	
	private String virtualAccountNumber;
	private String amount;
	private String date;
	private String status;
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDanId() {
		return danId;
	}
	public void setDanId(String danId) {
		this.danId = danId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String message;

	private List<String> chcktbl;
	private List<String> danNumber;
	public List<String> getDanNumber() {
		return danNumber;
	}

	public void setDanNumber(List<String> danNumber) {
		this.danNumber = danNumber;
	}



	private boolean chcktbl1;

	public boolean isChcktbl1() {
		return chcktbl1;
	}
	public void setChcktbl1(boolean chcktbl1) {
		this.chcktbl1 = chcktbl1;
	}
	public boolean isSelectAll1() {
		return selectAll1;
	}
	public void setSelectAll1(boolean selectAll1) {
		this.selectAll1 = selectAll1;
	}
	
	public boolean isSelectAll() {
		return selectAll;
	}
	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}
	public List<String> getChcktbl() {
		return chcktbl;
	}
	public void setChcktbl(List<String> chcktbl) {
		this.chcktbl = chcktbl;
	}
	private boolean selectAll;
	private boolean selectAll1;


	public String getRpNumber() {
		return rpNumber;
	}
	public void setRpNumber(String rpNumber) {
		this.rpNumber = rpNumber;
	}
	public String getVirtualAccountNumber() {
		return virtualAccountNumber;
	}
	public void setVirtualAccountNumber(String virtualAccountNumber) {
		this.virtualAccountNumber = virtualAccountNumber;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}

