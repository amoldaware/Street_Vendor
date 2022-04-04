package com.nbfc.helper;

public class TableDetailBean {
	String ColumnName="";
	String ColumnDataType="";
	int ColumnLength=0;
	int max_table_id=0;
	String DisplayColumnName="";
	//Jayanthi
		int columnPrecision =0;
		int columnScale =0;
		//Jayanthi
	 
	public String getDisplayColumnName() {
		return DisplayColumnName;
	}
	public int getColumnPrecision() {
		return columnPrecision;
	}
	public void setColumnPrecision(int columnPrecision) {
		this.columnPrecision = columnPrecision;
	}
	public int getColumnScale() {
		return columnScale;
	}
	public void setColumnScale(int columnScale) {
		this.columnScale = columnScale;
	}
	public void setDisplayColumnName(String displayColumnName) {
		DisplayColumnName = displayColumnName;
	}
	public int getMax_table_id() {
		return max_table_id;
	}
	public void setMax_table_id(int max_table_id) {
		this.max_table_id = max_table_id;
	}
	String ColumnAllowNullFlag="";
	
	public String getColumnName() {
		return ColumnName;
	}
	public void setColumnName(String columnName) {
		ColumnName = columnName;
	}
	public String getColumnDataType() {
		return ColumnDataType;
	}
	public void setColumnDataType(String columnDataType) {
		ColumnDataType = columnDataType;
	}
	 
	public String getColumnAllowNullFlag() {
		return ColumnAllowNullFlag;
	}
	public void setColumnAllowNullFlag(String columnAllowNullFlag) {
		ColumnAllowNullFlag = columnAllowNullFlag;
	}
	public int getColumnLength() {
		return ColumnLength;
	}
	public void setColumnLength(int columnLength) {
		ColumnLength = columnLength;
	}
	
}
