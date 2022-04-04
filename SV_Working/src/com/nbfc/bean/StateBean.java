package com.nbfc.bean;

/**
 * @author Saurav Tyagi 2017
 * 
 */

public class StateBean {

	private String ste_name;
	private String ste_code;

	public String getSte_name() {
		return ste_name;
	}

	@Override
	public String toString() {
		return "StateBean [ste_name=" + ste_name + ", ste_code=" + ste_code
				+ "]";
	}

	public void setSte_name(String ste_name) {
		this.ste_name = ste_name;
	}

	public String getSte_code() {
		return ste_code;
	}

	public void setSte_code(String ste_code) {
		this.ste_code = ste_code;
	}

}
