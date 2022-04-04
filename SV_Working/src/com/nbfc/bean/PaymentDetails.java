package com.nbfc.bean;

import java.util.Date;

public class PaymentDetails {
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
	   private String paymentId;
	   private String collectingBank;
	   private String collectingBankBranch;
	   private String cgtsiAccountHoldingBranch;
	   private Date paymentDate;
	   private String drawnAtBranch;
	   private String drawnAtBank;
	   private String userId;
	   private double allocatedAmount;
	   private String cgtsiAccNumber;
	   
	   
	   
	   
	   
	   
	 
	 
	 
	 
	   
	   /**
	    * This is a constructor to PaymentDetails object. PaymentDetails object will be 
	    * instantiated with the parameters passed.
	    * @param PaymentId
	    * @param ModeOfPayment
	    * @param InstrumentType
	    * @param InstrumentNumber
	    * @param InstrumentDate
	    * @param InstrumentAmount
	    * @param PayableAt
	    * @roseuid 399EAA560271
	    */
	 
	   /* Added by Shyam */
	    /**
	    * Access method for the instrumentDate property.
	    * 
	    * @return   the current value of the realisationDate property
	    */
	   public Date getRealisationDate() 
	   {
	      return realisationDate;
	   }
	   
	   
	   public void setRealisationDate(Date TrealisationDate) 
	   {
	      realisationDate = TrealisationDate;
	   }
	   
	   /* ---- */
	   /**
	    * Access method for the instrumentAmount property.
	    * 
	    * @return   the current value of the instrumentAmount property
	    */
	   public double getInstrumentAmount() 
	   {
	      return instrumentAmount;
	   }
	   
	   /**
	    * Sets the value of the instrumentAmount property.
	    * 
	    * @param aInstrumentAmount the new value of the instrumentAmount property
	    */
	   public void setInstrumentAmount(double aInstrumentAmount) 
	   {
	      instrumentAmount = aInstrumentAmount;
	   }
	   
	   /**
	    * Access method for the modeOfPayment property.
	    * 
	    * @return   the current value of the modeOfPayment property
	    */
	   public String getModeOfPayment() 
	   {
	      return modeOfPayment;
	   }
	   
	   /**
	    * Sets the value of the modeOfPayment property.
	    * 
	    * @param aModeOfPayment the new value of the modeOfPayment property
	    */
	   public void setModeOfPayment(String aModeOfPayment) 
	   {
	      modeOfPayment = aModeOfPayment;
	   }
	   
	   /**
	    * Access method for the modeOfDelivery property.
	    * 
	    * @return   the current value of the modeOfDelivery property
	    */
	   public String getModeOfDelivery() 
	   {
	      return modeOfDelivery;
	   }
	   
	   /**
	    * Sets the value of the modeOfDelivery property.
	    * 
	    * @param aModeOfDelivery the new value of the modeOfDelivery property
	    */
	   public void setModeOfDelivery(String aModeOfDelivery) 
	   {
	      modeOfDelivery = aModeOfDelivery;
	   }
	 
	 
	   /**
	    * Access method for the instrumentType property.
	    * 
	    * @return   the current value of the instrumentType property
	    */
	   public String getInstrumentType() 
	   {
	      return instrumentType;
	   }
	   
	   /**
	    * Sets the value of the instrumentType property.
	    * 
	    * @param aInstrumentType the new value of the instrumentType property
	    */
	   public void setInstrumentType(String aInstrumentType) 
	   {
	      instrumentType = aInstrumentType;
	   }
	   
	   /**
	    * Access method for the instrumentDate property.
	    * 
	    * @return   the current value of the instrumentDate property
	    */
	   public Date getInstrumentDate() 
	   {
	      return instrumentDate;
	   }
	   
	   /**
	    * Sets the value of the instrumentDate property.
	    * 
	    * @param aInstrumentDate the new value of the instrumentDate property
	    */
	   public void setInstrumentDate(Date aInstrumentDate) 
	   {
	      instrumentDate = aInstrumentDate;
	   }
	   
	   /**
	    * Access method for the payableAt property.
	    * 
	    * @return   the current value of the payableAt property
	    */
	   public String getPayableAt() 
	   {
	      return payableAt;
	   }
	   
	   /**
	    * Sets the value of the payableAt property.
	    * 
	    * @param aPayableAt the new value of the payableAt property
	    */
	   public void setPayableAt(String aPayableAt) 
	   {
	      payableAt = aPayableAt;
	   }
	   
	   /**
	    * Access method for the receivedAmount property.
	    * 
	    * @return   the current value of the receivedAmount property
	    */
	   public double getReceivedAmount() 
	   {
	      return receivedAmount;
	   }
	   
	   /**
	    * Sets the value of the receivedAmount property.
	    * 
	    * @param aReceivedAmount the new value of the receivedAmount property
	    */
	   public void setReceivedAmount(double aReceivedAmount) 
	   {
	      receivedAmount = aReceivedAmount;
	   }
	   
	   /**
	    * Access method for the paymentId property.
	    * 
	    * @return   the current value of the paymentId property
	    */
	   public String getPaymentId() 
	   {
	      return paymentId;
	   }
	   
	   /**
	    * Sets the value of the paymentId property.
	    * 
	    * @param aPaymentId the new value of the paymentId property
	    */
	   public void setPaymentId(String aPaymentId) 
	   {
	      paymentId = aPaymentId;
	   }
	   
	   /**
	    * Access method for the collectingBankBranch property.
	    * 
	    * @return   the current value of the collectingBankBranch property
	    */
	   public String getCollectingBankBranch() 
	   {
	      return collectingBankBranch;
	   }
	   
	   /**
	    * Sets the value of the collectingBankBranch property.
	    * 
	    * @param aCollectingBankBranch the new value of the collectingBankBranch property
	    */
	   public void setCollectingBankBranch(String aCollectingBankBranch) 
	   {
	      collectingBankBranch = aCollectingBankBranch;
	   }
	   
	   /**
	    * Access method for the cgtsiAccountHoldingBranch property.
	    * 
	    * @return   the current value of the cgtsiAccountHoldingBranch property
	    */
	   public String getCgtsiAccountHoldingBranch() 
	   {
	      return cgtsiAccountHoldingBranch;
	   }
	   
	   /**
	    * Sets the value of the cgtsiAccountHoldingBranch property.
	    * 
	    * @param aCgtsiAccountHoldingBranch the new value of the cgtsiAccountHoldingBranch property
	    */
	   public void setCgtsiAccountHoldingBranch(String aCgtsiAccountHoldingBranch) 
	   {
	      cgtsiAccountHoldingBranch = aCgtsiAccountHoldingBranch;
	   }
	   
	   /**
	    * Access method for the paymentDate property.
	    * 
	    * @return   the current value of the paymentDate property
	    */
	   public Date getPaymentDate() 
	   {
	      return paymentDate;
	   }
	   
	   /**
	    * Sets the value of the paymentDate property.
	    * 
	    * @param aPaymentDate the new value of the paymentDate property
	    */
	   public void setPaymentDate(Date aPaymentDate) 
	   {
	      paymentDate = aPaymentDate;
	   }
	   
	   /**
	    * Access method for the drawnAtBranch property.
	    * 
	    * @return   the current value of the drawnAtBranch property
	    */
	   public String getDrawnAtBranch() 
	   {
	      return drawnAtBranch;
	   }
	   
	   /**
	    * Sets the value of the drawnAtBranch property.
	    * 
	    * @param aDrawnAtBranch the new value of the drawnAtBranch property
	    */
	   public void setDrawnAtBranch(String aDrawnAtBranch) 
	   {
	      drawnAtBranch = aDrawnAtBranch;
	   }
	   
	   /**
	    * Access method for the officerName property.
	    * 
	    * @return   the current value of the officerName property
	    */
	   
	   /**
	    * Access method for the drawnAtBank property.
	    * 
	    * @return   the current value of the drawnAtBank property
	    */
	   public String getDrawnAtBank() 
	   {
	      return drawnAtBank;
	   }
	   
	   /**
	    * Sets the value of the drawnAtBank property.
	    * 
	    * @param aDrawnAtBank the new value of the drawnAtBank property
	    */
	   public void setDrawnAtBank(String aDrawnAtBank) 
	   {
	      drawnAtBank = aDrawnAtBank;
	   }
	/**
	 * @return
	 */
	public String getInstrumentNo() {
		return instrumentNo;
	}

	/**
	 * @param string
	 */
	public void setInstrumentNo(String string) {
		instrumentNo = string;
	}

	/**
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param string
	 */
	public void setUserId(String string) {
		userId = string;
	}
	/**
	 * @return
	 */
	public String getCollectingBank() {
		return collectingBank;
	}

	/**
	 * @param string
	 */
	public void setCollectingBank(String string) {
		collectingBank = string;
	}

	/**
	 * @return
	 */
	public double getAllocatedAmount() {
		return allocatedAmount;
	}

	/**
	 * @param d
	 */
	public void setAllocatedAmount(double d) {
		allocatedAmount = d;
	}

	/**
	 * @return
	 */
	public String getCgtsiAccNumber() {
		return cgtsiAccNumber;
	}

	/**
	 * @param string
	 */
	public void setCgtsiAccNumber(String string) {
		cgtsiAccNumber = string;
	}


	  
	 
}
