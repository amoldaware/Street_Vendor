package com.nbfc.bean;

import java.util.List;

public class NBFCASFUpdationBean {

	private String cgpan;
	private String dan_id;
	private String mse_itpan;
	private int outstanding_amount;
	private String mem_bnk_id;
	private String mem_brn_id;
	private String mem_zne_id;
	private String portfolio_name;
	private String upload_Date;
	private String fileid;

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	private boolean selectAll;

	
	
	
	
	
	public boolean isSelectAll() {
		return selectAll;
	}

	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}

	public String getUpload_Date() {
		return upload_Date;
	}

	public void setUpload_Date(String upload_Date) {
		this.upload_Date = upload_Date;
	}

	public String getPortfolio_name() {
		return portfolio_name;
	}

	public void setPortfolio_name(String portfolio_name) {
		this.portfolio_name = portfolio_name;
	}

	private String mse_name;
	private List<String> chcktbl;

	
	public List<String> getChcktbl() {
		return chcktbl;
	}

	public void setChcktbl(List<String> chcktbl) {
		this.chcktbl = chcktbl;
	}

	public NBFCASFUpdationBean() {
		super();
	}

	public NBFCASFUpdationBean(String cgpan, String dan_id, String mse_itpan,
			int outstanding_amount, String mem_bnk_id, String mem_brn_id,
			String mem_zne_id, String mse_name) {
		super();
		this.cgpan = cgpan;
		this.dan_id = dan_id;
		this.mse_itpan = mse_itpan;
		this.outstanding_amount = outstanding_amount;
		this.mem_bnk_id = mem_bnk_id;
		this.mem_brn_id = mem_brn_id;
		this.mem_zne_id = mem_zne_id;
		this.mse_name = mse_name;
	}

	@Override
	public String toString() {
		return "NBFCASFUpdationBean [cgpan=" + cgpan + ", dan_id=" + dan_id
				+ ", mse_itpan=" + mse_itpan + ", outstanding_amount="
				+ outstanding_amount + ", mem_bnk_id=" + mem_bnk_id
				+ ", mem_brn_id=" + mem_brn_id + ", mem_zne_id=" + mem_zne_id
				+ ", mse_name=" + mse_name + "]";
	}

	public String getCgpan() {
		return cgpan;
	}

	public void setCgpan(String cgpan) {
		this.cgpan = cgpan;
	}

	public String getDan_id() {
		return dan_id;
	}

	public void setDan_id(String dan_id) {
		this.dan_id = dan_id;
	}

	public String getMse_itpan() {
		return mse_itpan;
	}

	public void setMse_itpan(String mse_itpan) {
		this.mse_itpan = mse_itpan;
	}

	public int getOutstanding_amount() {
		return outstanding_amount;
	}

	public void setOutstanding_amount(int outstanding_amount) {
		this.outstanding_amount = outstanding_amount;
	}

	public String getMem_bnk_id() {
		return mem_bnk_id;
	}

	public void setMem_bnk_id(String mem_bnk_id) {
		this.mem_bnk_id = mem_bnk_id;
	}

	public String getMem_brn_id() {
		return mem_brn_id;
	}

	public void setMem_brn_id(String mem_brn_id) {
		this.mem_brn_id = mem_brn_id;
	}

	public String getMem_zne_id() {
		return mem_zne_id;
	}

	public void setMem_zne_id(String mem_zne_id) {
		this.mem_zne_id = mem_zne_id;
	}

	public String getMse_name() {
		return mse_name;
	}

	public void setMse_name(String mse_name) {
		this.mse_name = mse_name;
	}

}
