package com.nbfc.bean;

/**
 * @author Saurav Tyagi 2017
 * 
 */

public class DistrictBean {

	private String ste_code;
	private String dst_name;
	
	
	@Override
	public String toString() {
		return "DistrictBean [dst_name=" + dst_name + "]";
	}

	public String getDst_name() {
		return dst_name;
	}

	public void setDst_name(String dst_name) {
		this.dst_name = dst_name;
	}


	public String getSte_code() {
		return ste_code;
	}

	public void setSte_code(String ste_code) {
		this.ste_code = ste_code;
	}

		
}
