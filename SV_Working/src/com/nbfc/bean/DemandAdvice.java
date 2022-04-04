package com.nbfc.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class DemandAdvice {


    private String danNo;
    private String danType;
    private String bankId;
    private String zoneId;
    private String rpNumber;
    public String getRpNumber() {
		return rpNumber;
	}

	public void setRpNumber(String rpNumber) {
		this.rpNumber = rpNumber;
	}

	private String branchId;
   
    private String cgpan;
    private String borrowerId;
    private double amountRaised;
    private String allocated;
    private String appropriated;
    private String reason;
    private String paymentId;
    private double penalty;
    private String userId;
   // private boolean chcktbl;
    private double claimRevoverAmount;
    private List<String> chcktbl;
    
    // For payment details 
    private double instrumentAmount;
	   private String modeOfPayment;
	   private String modeOfDelivery;
	   private String instrumentNo;
	   private String instrumentType;
	   private Date instrumentDate;
	   private String payableAt;
	   private double receivedAmount;
	   //added by shyam
	   private Date realisationDate;
	   public double getInstrumentAmount() {
		return instrumentAmount;
	}

	public void setInstrumentAmount(double instrumentAmount) {
		this.instrumentAmount = instrumentAmount;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getModeOfDelivery() {
		return modeOfDelivery;
	}

	public void setModeOfDelivery(String modeOfDelivery) {
		this.modeOfDelivery = modeOfDelivery;
	}

	public String getInstrumentNo() {
		return instrumentNo;
	}

	public void setInstrumentNo(String instrumentNo) {
		this.instrumentNo = instrumentNo;
	}

	public String getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}

	public Date getInstrumentDate() {
		return instrumentDate;
	}

	public void setInstrumentDate(Date instrumentDate) {
		this.instrumentDate = instrumentDate;
	}

	public String getPayableAt() {
		return payableAt;
	}

	public void setPayableAt(String payableAt) {
		this.payableAt = payableAt;
	}

	public double getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(double receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public Date getRealisationDate() {
		return realisationDate;
	}

	public void setRealisationDate(Date realisationDate) {
		this.realisationDate = realisationDate;
	}

	public String getCollectingBank() {
		return collectingBank;
	}

	public void setCollectingBank(String collectingBank) {
		this.collectingBank = collectingBank;
	}

	public String getCollectingBankBranch() {
		return collectingBankBranch;
	}

	public void setCollectingBankBranch(String collectingBankBranch) {
		this.collectingBankBranch = collectingBankBranch;
	}

	public String getCgtsiAccountHoldingBranch() {
		return cgtsiAccountHoldingBranch;
	}

	public void setCgtsiAccountHoldingBranch(String cgtsiAccountHoldingBranch) {
		this.cgtsiAccountHoldingBranch = cgtsiAccountHoldingBranch;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getDrawnAtBranch() {
		return drawnAtBranch;
	}

	public void setDrawnAtBranch(String drawnAtBranch) {
		this.drawnAtBranch = drawnAtBranch;
	}

	public String getDrawnAtBank() {
		return drawnAtBank;
	}

	public void setDrawnAtBank(String drawnAtBank) {
		this.drawnAtBank = drawnAtBank;
	}

	public double getAllocatedAmount() {
		return allocatedAmount;
	}

	public void setAllocatedAmount(double allocatedAmount) {
		this.allocatedAmount = allocatedAmount;
	}

	public String getCgtsiAccNumber() {
		return cgtsiAccNumber;
	}

	public void setCgtsiAccNumber(String cgtsiAccNumber) {
		this.cgtsiAccNumber = cgtsiAccNumber;
	}

	private String collectingBank;
	   private String collectingBankBranch;
	   private String cgtsiAccountHoldingBranch;
	   private Date paymentDate;
	   private String drawnAtBranch;
	   private String drawnAtBank;
	   private double allocatedAmount;
	   private String cgtsiAccNumber;
	   
    
    
    public double getClaimRevoverAmount() {
		return claimRevoverAmount;
	}

	public void setClaimRevoverAmount(double claimRevoverAmount) {
		this.claimRevoverAmount = claimRevoverAmount;
	}

	private Date appropriatedDate;
  
    private String status;
    
       
   

	

    public DemandAdvice()
    {
        appropriatedDate = null;
    }

    public DemandAdvice(double cldanAmount, String cldan)
    {
        appropriatedDate = null;
        amountRaised = cldanAmount;
        danNo = cldan;
    }

    

    public String getDanNo()
    {
        return danNo;
    }

    public void setDanNo(String aDanNo)
    {
        danNo = aDanNo;
    }

    public String getDanType()
    {
        return danType;
    }

    public void setDanType(String aDanType)
    {
        danType = aDanType;
    }

    public String getBankId()
    {
        return bankId;
    }

    public void setBankId(String aBankId)
    {
        bankId = aBankId;
    }

    public String getZoneId()
    {
        return zoneId;
    }

    public void setZoneId(String aZoneId)
    {
        zoneId = aZoneId;
    }

   /* public boolean isChcktbl() {
		return chcktbl;
	}

	public void setChcktbl(boolean chcktbl) {
		this.chcktbl = chcktbl;
	}*/

	public String getBranchId()
    {
        return branchId;
    }

   /* public ArrayList<String> getChcktbl() {
		return chcktbl;
	}

	public void setChcktbl(ArrayList<String> chcktbl) {
		this.chcktbl = chcktbl;
	}*/

	public void setBranchId(String aBranchId)
    {
        branchId = aBranchId;
    }

   

    

   
	public List<String> getChcktbl() {
		return chcktbl;
	}

	public void setChcktbl(List<String> chcktbl) {
		this.chcktbl = chcktbl;
	}

	public String getCgpan()
    {
        return cgpan;
    }

    public void setCgpan(String aCgpan)
    {
        cgpan = aCgpan;
    }

    public double getAmountRaised()
    {
        return amountRaised;
    }

    public void setAmountRaised(double aAmountRaised)
    {
        amountRaised = aAmountRaised;
    }

    public String getAllocated()
    {
        return allocated;
    }

    public void setAllocated(String aAllocated)
    {
        allocated = aAllocated;
    }

    public String getAppropriated()
    {
        return appropriated;
    }

    public void setAppropriated(String aAppropriated)
    {
        appropriated = aAppropriated;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String aReason)
    {
        reason = aReason;
    }

    public String getPaymentId()
    {
        return paymentId;
    }

    public void setPaymentId(String aPaymentId)
    {
        paymentId = aPaymentId;
    }

    public double getPenalty()
    {
        return penalty;
    }

    public void setPenalty(double aPenalty)
    {
        penalty = aPenalty;
    }

    public Boolean getAllocatedValue()
    {
        return null;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String string)
    {
        userId = string;
    }

    public String getBorrowerId()
    {
        return borrowerId;
    }

    public void setBorrowerId(String string)
    {
        borrowerId = string;
    }

    
    public Date getAppropriatedDate()
    {
        return appropriatedDate;
    }

    public void setAppropriatedDate(Date date)
    {
        appropriatedDate = date;
    }

    

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

}
