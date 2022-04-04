package com.nbfc.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import java.util.Map.Entry;
import oracle.jdbc.OracleTypes;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import com.nbfc.exception.NBFCException;
import com.nbfc.model.UserInfo;
import com.nbfc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
//import com.sun.xml.internal.stream.Entity;
import org.springframework.web.multipart.MultipartFile;

public class BulkUpload0 {
	@Autowired
	private UserService employeeService;

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection dbConn = null;
		// ##
		System.out.println("getConnection()");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(
				// "jdbc:oracle:thin:@192.168.10.120:1523:CGINTRA","CGTSIINTRANETUSER","CGTSIINTRANETUSER$321");
				"jdbc:oracle:thin:@158.100.60.116:1521:CGFSIDB", "CGTSITEMPUSER", "CGTSITEMPUSER");
		// "jdbc:oracle:thin:@192.168.10.120:1524:CGFSIDB","CGTSITEMPUSER","CGTSITEMPUSER$321");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select sysdate from dual");
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
		// ##
		return con;

	}/**/

	public String prepareDataForInsertQuery(HSSFCell cell, TableDetailBean tableBeanObj) {
		String preparedValue = "''";
		if (tableBeanObj.getColumnDataType().equals("VARCHAR")) {

			if (cell != null) {
				if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

					BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
					preparedValue = bd.toPlainString();

				} else {
					if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						preparedValue = "''";

					} else {
						preparedValue = "'" + cell.toString() + "'";

					}
				}
			}

		} else if (tableBeanObj.getColumnDataType().equals("DATE")) {

			if (cell != null) {
				// System.out.println("Date::" + cell.toString().trim());

				DateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy"); // for parsing input
				DateFormat df3 = new SimpleDateFormat("dd/MM/yyyy"); // for parsing input
				DateFormat df4 = new SimpleDateFormat("YYYY/MM"); // for parsing input
				SimpleDateFormat simpleDateFormate = new SimpleDateFormat("dd/MM/yyyy");
				df1.setLenient(false);
				df3.setLenient(false);
				df4.setLenient(false);
				if (cell.toString().trim().length() > 7) {
					try {

						Date data_dt = df1.parse(cell.toString().trim());
						preparedValue = "'" + simpleDateFormate.format(data_dt) + "'";
						System.out.println("1: " + preparedValue);
					} catch (ParseException e) {

						try {
							Date data_dt = df3.parse(cell.toString().trim());
							preparedValue = "'" + simpleDateFormate.format(data_dt) + "'";
							System.out.println("2: " + preparedValue);

						} catch (ParseException inner_e) {
							try {

								Date data_dt = df4.parse(cell.toString().trim());
								preparedValue = "'" + simpleDateFormate.format(data_dt) + "'";
								System.out.println("3: " + preparedValue);

							} catch (ParseException inner_e3) {

								preparedValue = "'" + cell.toString().trim() + "'";
								System.out.println("4: " + preparedValue);
							}
						}

					}
				} else {/* Vending Duration */

					preparedValue = "'" + cell.toString().trim() + "'";
					System.out.println("5: " + preparedValue);
				}

			}

		} else if (tableBeanObj.getColumnDataType().equals("NUMBER")) {

			if (cell != null) {
				if (cell.toString().trim().contains(".") && !cell.toString().trim().contains("E")) {
					// System.out.println("With Decimal Number:" + cell.toString().trim());
					String firstNumber = cell.toString().trim().replace(".00", "");
					String finalNumber = "";
					if (firstNumber.endsWith(".0")) {
						finalNumber = firstNumber.replace(".0", "");
					} else {
						finalNumber = firstNumber;
					}
					// System.out.println("Final Number is ::" + finalNumber);
					if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						preparedValue = "''";
					} else {
						try {
							if (finalNumber.contains(".")) {
								BigDecimal bd = new BigDecimal(finalNumber);
								bd = bd.setScale(2, BigDecimal.ROUND_HALF_EVEN);
								preparedValue = "'" + bd.toPlainString() + "'";
								System.out.println("Big Decimal Number:" + bd.toPlainString());
							} else {
								BigDecimal bd = new BigDecimal(finalNumber);
								preparedValue = "'" + bd.toPlainString() + "'";
								System.out.println("Big Decimal Number:" + bd.toPlainString());
							}
						} catch (Exception e) {
							preparedValue = "'" + cell.toString() + "'";
						}
					}
				} else {
					// System.out.println("Without Decimal Number:" + cell.toString().trim());
					if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						preparedValue = "''";
					} else {
						try {
							BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
							preparedValue = "'" + bd.toPlainString() + "'";
						} catch (Exception e) {
							preparedValue = "'" + cell.toString() + "'";
						}
					}
				}
			}
		}

		return preparedValue.trim();
	}

	public String validateFieldType(HSSFCell cell, TableDetailBean tableBeanObj) {
		String isValidMsg = "INVALID";

		try {
			if (tableBeanObj.getColumnDataType().equals("VARCHAR")) {
				System.out.print("###### VARCHAR ######");
				if (cell != null) {

					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						System.out.println(
								tableBeanObj.getColumnName() + "########## CELL_TYPE_STRING ##################");
						if (cell.getStringCellValue().trim().length() > 0
								&& cell.getStringCellValue().trim().length() <= tableBeanObj.getColumnLength()) {

							if (cell.getStringCellValue().length() > 0
									&& cell.getStringCellValue().length() <= tableBeanObj.getColumnLength()) {

								isValidMsg = "VALID";
							} else {
								isValidMsg = tableBeanObj.getDisplayColumnName()
										+ " field length is more. The expected length is "
										+ tableBeanObj.getColumnLength();
							}
							
						} else {
							isValidMsg = tableBeanObj.getDisplayColumnName()
									+ " field length is more. The expected length is " + tableBeanObj.getColumnLength();
						}
					}

					if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						System.out.println(
								tableBeanObj.getColumnName() + "##########CELL_TYPE_NUMERIC##################");
						String val = "" + cell.getNumericCellValue();
						if (val.contains(".0")) {
							val = val.substring(0, val.length() - 2);
						} else {
							BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
							val = bd.toPlainString();
						}
						if (val.length() > 0 && val.length() <= tableBeanObj.getColumnLength()) {
							isValidMsg = "VALID";
						} else {
							isValidMsg = tableBeanObj.getDisplayColumnName()
									+ " field length is more. The expected length is " + tableBeanObj.getColumnLength();
						}
					}

					if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						if (tableBeanObj.getColumnAllowNullFlag().equals("Y")) {
							isValidMsg = "VALID";
						} else {
							isValidMsg = tableBeanObj.getDisplayColumnName() + " cannot be blank/empty.";
						}
					}
				} else {
					if (tableBeanObj.getColumnAllowNullFlag().equals("Y")) {
						isValidMsg = "VALID";
					} else {
						isValidMsg = tableBeanObj.getDisplayColumnName() + " cannot be blank/empty.";
					}
				}
			}

			if (tableBeanObj.getColumnDataType().equals("NUMBER")) {
				//System.out.print("###### NUMBER ######");
				if (cell != null) {
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        int precision = tableBeanObj.getColumnPrecision() - tableBeanObj.getColumnScale();
                        if (precision > 0) {
							Double cval = cell.getNumericCellValue();
							BigDecimal bd = new BigDecimal(cval.toString());
							long lonVal = bd.longValue();
							// System.out.println(lonVal);
							System.out.println("The expo numeric val is" + lonVal);
							// String cellval = ""+cell.getNumericCellValue();
							String cellval = "" + lonVal;
							System.out.println("The cell value  is " + cellval);
							int charcnt = 0;
							if (cellval.contains(".")) {
								System.out.println("Inside contains");
								for (char c : cellval.toCharArray())
									if (!("" + c).equals("."))
										charcnt++;
									else
										break;
								System.out.println("The charcnt is" + charcnt);
								if (charcnt > precision) {
									System.out.println("inside if of charcnt>2");
									isValidMsg = tableBeanObj.getDisplayColumnName() + " should be of " + precision
											+ " digits and upto " + tableBeanObj.getColumnScale() + " decimal places";
								} else {
									System.out.println("inside else of charcnt>2");
									isValidMsg = "VALID";
								}

							} else {
								System.out.println("inside else of contains");
								if (cellval.length() > precision) {
									isValidMsg = tableBeanObj.getDisplayColumnName() + " should be of " + precision
											+ " digits and upto " + tableBeanObj.getColumnScale() + " decimal places";
								} else
									isValidMsg = "VALID";
							}
						} else {
							System.out.println("inside else of precision>0");

							isValidMsg = "VALID";
						}
					}

					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						isValidMsg = tableBeanObj.getDisplayColumnName() + " should be number and not text";
					}

					if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						if (tableBeanObj.getColumnAllowNullFlag().equals("Y")) {
							isValidMsg = "VALID";
						} else {
							isValidMsg = tableBeanObj.getDisplayColumnName() + " cannot be blank/empty.";
						}
					}
				} else {
					if (tableBeanObj.getColumnAllowNullFlag().equals("Y")) {
						isValidMsg = "VALID";
					} else {
						isValidMsg = tableBeanObj.getDisplayColumnName() + " cannot be blank/empty.";
					}
				}
			}

			if (tableBeanObj.getColumnDataType().equals("DATE")) {
				//System.out.print("###### DATE ######");
				if (cell != null) {

					DateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
					DateFormat df3 = new SimpleDateFormat("dd/MM/yyyy");
					DateFormat df4 = new SimpleDateFormat("YYYY/MM");

					df1.setLenient(false);
					df3.setLenient(false);
					df4.setLenient(false);

					try {
						df1.parse(cell.toString());
						isValidMsg = "VALID";
					} catch (ParseException e) {

						try {

							df3.parse(cell.toString());
							isValidMsg = "VALID";
						} catch (ParseException inner_e) {
							try {

								df4.parse(cell.toString());
								isValidMsg = "VALID";
							} catch (ParseException inner_e1) {

								System.out.println("inner e1 :" + e);
								isValidMsg = tableBeanObj.getDisplayColumnName()
										+ " should of format dd/mm/yyyy or dd-MMM-yyyy";
							}
						}
					}
					if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						if (tableBeanObj.getColumnAllowNullFlag().equals("Y")) {
							isValidMsg = "VALID";
						} else {
							isValidMsg = tableBeanObj.getDisplayColumnName() + " cannot be blank/empty.";
						}
					}
				} else {

					if (tableBeanObj.getColumnAllowNullFlag().equals("Y")) {
						isValidMsg = "VALID";
					} else {
						isValidMsg = tableBeanObj.getDisplayColumnName() + " cannot be blank/empty.";
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Error:" + e);
			isValidMsg=e.getMessage();
		}
		System.out.println("Column Name:" + tableBeanObj.getColumnName() + " isValid :" + isValidMsg);
		return isValidMsg;
	}

	public LinkedHashMap<String, TableDetailBean> getTableHeaderData(Connection conn, String TableName, String BulkName)
			throws ParseException, NBFCException {
		
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		String errorCode = null;
		LinkedHashMap<String, TableDetailBean> TableHeaderDataDetail = new LinkedHashMap<String, TableDetailBean>();
		try {
			
			callableStmt = conn.prepareCall("{call Func_table_header_data_" + BulkName + "(?,?,?,?)}");
			callableStmt.setString(1, "Admin");
			callableStmt.setString(2, TableName);
			callableStmt.registerOutParameter(3, java.sql.Types.NUMERIC);
			callableStmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			callableStmt.execute();
			
			errorCode = callableStmt.getString(4);

			if (errorCode != null) {
				callableStmt.close();
				System.out.println("errorCode:" + errorCode);
				throw new NBFCException(errorCode);
			} else {
				resultset = (ResultSet) callableStmt.getResultSet();
				int max_tab_id = callableStmt.getInt(3);
				while (resultset.next()) {
					TableDetailBean tableBeanObj = new TableDetailBean();
					tableBeanObj.setColumnName(resultset.getString("column_name"));
					tableBeanObj.setColumnDataType(resultset.getString("data_type"));
					tableBeanObj.setColumnLength(resultset.getInt("data_length"));
					tableBeanObj.setColumnAllowNullFlag(resultset.getString("NULLABLE"));
					tableBeanObj.setMax_table_id(max_tab_id);
					tableBeanObj.setDisplayColumnName(resultset.getString("DISPLAY_NAME"));
					TableHeaderDataDetail.put(resultset.getString("column_name"), tableBeanObj);
				}
			}
			resultset.close();
			resultset = null;
			callableStmt.close();
			callableStmt = null;
		} catch (SQLException sqlexception) {
			System.out.println("sqlexception :" + sqlexception.toString());
			throw new NBFCException(sqlexception.getMessage());

		}
		return TableHeaderDataDetail;
	}

	public void validateData(Connection con, ArrayList successRecord, ArrayList unsuccessRecord, String UploadId,
			String BulkName, LinkedHashMap<String, TableDetailBean> headers) throws SQLException {
		CallableStatement callableStmt1 = null;
		String errorCode1 = null;
		try {
			callableStmt1 = con.prepareCall("{call Func_validate_data_" + BulkName + "(?,?,?)}");
			callableStmt1.setString(1, "Admin");
			callableStmt1.setString(2, UploadId);
			callableStmt1.registerOutParameter(3, java.sql.Types.VARCHAR);
			callableStmt1.execute();
			errorCode1 = callableStmt1.getString(3);

			if (errorCode1 != null) {
				callableStmt1.close();
				// throw new NBFCException("Error@Bulkupload-validate Data:" + errorCode1);
			} else {
				System.out.println("Data inserted into staging table successfully");
			}
			callableStmt1.close();
			callableStmt1 = null;
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			// throw new NBFCException("Error@Bulkupload-validate Data:" +
			// sqlexception.toString());
		} finally {
			con.commit();
		}
		// ####Validate Function####end####

		// ####Moving to main table Function####start####
		CallableStatement callableStmt2 = null;
		// int status2 = -1;
		String errorCode2 = null;
		try {
			// ##
			callableStmt2 = con.prepareCall("{call Func_move_stag_to_main_" + BulkName + "(?,?,?)}");
			// callableStmt2.registerOutParameter(1, java.sql.Types.INTEGER);
			callableStmt2.setString(1, "Admin");
			callableStmt2.setString(2, UploadId);
			callableStmt2.registerOutParameter(3, java.sql.Types.VARCHAR);
			callableStmt2.execute();
			// status2 = callableStmt2.getInt(1);
			errorCode2 = callableStmt2.getString(3);

			if (errorCode2 != null) {

				callableStmt2.close();
				// throw new NBFCException("Error@Bulkupload- data Moving to main table:" +
				// errorCode2);

			} else {
				System.out.println("Data moved into main table successfully");
			}
			callableStmt2.close();
			callableStmt2 = null;
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw new NBFCException("Error@Bulkupload-data Moving to main table:" + sqlexception.toString());
		} finally {

			con.commit();
		}
		// ####Moving to main table Function####end####

		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@3@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

		// ####Getting Successful and Unsuccessful data table Function####start####
		CallableStatement callableStmt3 = null;
		CallableStatement callableStmt4 = null;
		String errorCode3 = null;
		String errorCode4 = null;
		// ArrayList nestData = new ArrayList();

		// ##
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSetMetaData resultSetMetaData1 = null;
		ResultSetMetaData resultSetMetaData2 = null;
		ArrayList coulmName1 = new ArrayList();
		ArrayList coulmName2 = new ArrayList();

		ArrayList DataList1 = new ArrayList();
		ArrayList DataList2 = new ArrayList();

		try {
			// ##
			callableStmt3 = con.prepareCall("{call Func_get_uploaded_data_APPS_Success_STVN(?,?,?)}");
			callableStmt3.setString(1, "Admin");
			callableStmt3.setString(2, UploadId);
			callableStmt3.registerOutParameter(3, java.sql.Types.VARCHAR);
			callableStmt3.execute();
			errorCode3 = callableStmt3.getString(3);

			if (errorCode3 != null) {
				callableStmt3.close();
			} else {
				// Successful data start
				rs1 = (ResultSet) callableStmt3.getResultSet();
				resultSetMetaData1 = rs1.getMetaData();
				int coulmnCount1 = resultSetMetaData1.getColumnCount();
				for (int i = 1; i <= coulmnCount1; i++) {
					coulmName1.add(resultSetMetaData1.getColumnName(i));

				}
				ArrayList<String> DisplayHeaderNameLst1 = getDisplayNameOfHeaderName(coulmName1, headers);
				System.out.println("%%%%%%%%%%%%coulmName1:" + coulmName1);
				System.out.println("%%%%%%%%%%%%DisplayHeaderNameLst:" + DisplayHeaderNameLst1);

				while (rs1.next()) {

					ArrayList columnValue1 = new ArrayList();
					for (int i = 1; i <= coulmnCount1; i++) {
						columnValue1.add(rs1.getString(i));
					}
					DataList1.add(columnValue1);
				}

				successRecord.add(0, DisplayHeaderNameLst1);
				successRecord.add(1, DataList1);
				// Successful data end
			}

		} catch (SQLException sqlexception) {

		} finally {
			// DBConnection.freeConnection(conn);
			rs1.close();
			callableStmt3.close();
			callableStmt3 = null;
			resultSetMetaData1 = null;
		}

		try {
			callableStmt4 = con.prepareCall("{call Func_get_uploaded_data_APPS_UnSucess_STVN(?,?,?)}");
			callableStmt4.setString(1, "Admin");
			callableStmt4.setString(2, UploadId);
			callableStmt4.registerOutParameter(3, java.sql.Types.VARCHAR);
			callableStmt4.execute();
			errorCode4 = callableStmt4.getString(3);

			if (errorCode4 != null) {
				callableStmt4.close();
			} else {
				// UnSuccessful data start
				rs2 = (ResultSet) callableStmt4.getResultSet();
				resultSetMetaData2 = rs2.getMetaData();
				int coulmnCount2 = resultSetMetaData2.getColumnCount();
				for (int i = 1; i <= coulmnCount2; i++) {
					coulmName2.add(resultSetMetaData2.getColumnName(i));
				}

				ArrayList<String> DisplayHeaderNameLst2 = getDisplayNameOfHeaderName(coulmName2, headers);

				while (rs2.next()) {

					ArrayList columnValue2 = new ArrayList();
					for (int i = 1; i <= coulmnCount2; i++) {
						columnValue2.add(rs2.getString(i));
					}
					DataList2.add(columnValue2);
				}

				unsuccessRecord.add(0, DisplayHeaderNameLst2);
				unsuccessRecord.add(1, DataList2);
				// UnSuccessful data end
			}

		} catch (SQLException sqlexception) {

		} finally {
			// DBConnection.freeConnection(conn);
			rs2.close();
			callableStmt4.close();
			callableStmt4 = null;
			resultSetMetaData2 = null;
		}
		// ####Getting Successful and Unsuccessful data table Function####end####

	}

	@SuppressWarnings("finally")
	public HashMap<String, ArrayList<String>> CheckExcelData(MultipartFile uploadFormFile,
			LinkedHashMap<String, TableDetailBean> headers, String TableName, Connection con, UserInfo UserObj,
			String BulkName) throws IOException, SQLException {

		InputStream is = null;
		Statement Stat = con.createStatement();
		String ColumnNames = "";
		boolean setFirstColumFlag = false;
		int max_table_id = 0;
		HashMap<String, ArrayList<String>> UploadedStatus = new HashMap<String, ArrayList<String>>();
		LinkedHashMap<String, String> TemplateHeaderDisplayName = new LinkedHashMap<String, String>();
		HashMap<String, String> Excelheaders = new HashMap<String, String>();
		HashMap<String, String> UnMatchheaders = new HashMap<String, String>();
		ArrayList<String> TemplateHeaderColumnName = new ArrayList<String>();
		// ##BATCH_ID##
		String BatchId = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
		System.out.println("##BatchId## :" + BatchId);

		for (Entry<String, TableDetailBean> entry : headers.entrySet()) {
			// ColumnNames = ColumnNames + "," + entry.getKey();
			TemplateHeaderColumnName.add(entry.getKey());
			TableDetailBean tabledetailbean = entry.getValue();
			TemplateHeaderDisplayName.put(tabledetailbean.getDisplayColumnName(), tabledetailbean.getColumnName());

		}
		ColumnNames = TemplateHeaderColumnName.toString().substring(1,TemplateHeaderColumnName.toString().length() - 1);

		int columnCnt = 0;
		columnCnt = headers.size();
		Boolean QueryAddedFlag = false;

		try {
			is = uploadFormFile.getInputStream();
			HSSFWorkbook book = new HSSFWorkbook(is);
			HSSFSheet sheet = book.getSheetAt(0);
			int ExcelNoOfColumns = sheet.getRow(0).getLastCellNum();
			int excelRowCnt = sheet.getLastRowNum();
			Iterator rowItr = sheet.iterator();
			Iterator rowheaderItr = sheet.iterator();
			int row_cnt = 0;
			ArrayList<String> errors = new ArrayList<String>();
			ArrayList recordlevelerrors = new ArrayList();
			ArrayList DataHeadererrors = new ArrayList();
			ArrayList Dataerrors = new ArrayList();
			ArrayList Allerrors = new ArrayList();
			ArrayList<String> columnNameLst = new ArrayList<String>();
			//String tblKey = "USER_LIMIT";
			// int excelDataLimit=employeeService.userLimit(tblKey);
			// if(excelRowCnt<=excelDataLimit) {
			while (rowheaderItr.hasNext()) {
				HSSFRow row = (HSSFRow) rowheaderItr.next();
				HSSFCell celVal[] = new HSSFCell[columnCnt];
				if (row.getRowNum() == 0) {
					for (int k = 0; k < columnCnt; k++) {
						HSSFCell cellV = row.getCell(k) != null ? row.getCell(k) : null;
						celVal[k] = cellV != null ? cellV : null;
						if (null != celVal[k]) {
							Excelheaders.put(celVal[k].toString(), celVal[k].toString());
							columnNameLst.add(celVal[k].toString());
							if (!TemplateHeaderDisplayName.containsKey(Excelheaders.get(celVal[k].toString()))) {
								UnMatchheaders.put(celVal[k].toString(), celVal[k].toString());
							}
						}
					}

				} else {
					break;
				}
			}

			System.out.println("###Excelheaders :" + Excelheaders);
			System.out.println("###UnMatchheaders :" + UnMatchheaders);

			if (ExcelNoOfColumns != columnCnt) {
				// Excel Count not matching with Template count
				errors.add("Uploaded Excel Column [Count:" + ExcelNoOfColumns+ "] not matched with Template Column Count [Count:" + columnCnt + "]");
				UploadedStatus.put("error", errors);
				System.out.println("unmatched count ExcelNoOfColumns:" + ExcelNoOfColumns + "  columnCnt:" + columnCnt);
				
			} else if (UnMatchheaders.size() > 0) {
				// Excel Header name not matching with Template Header Name
				String unmatchedHeaderName = "";
				for (Entry<String, String> entry : UnMatchheaders.entrySet()) {
					unmatchedHeaderName = unmatchedHeaderName + "," + entry.getKey();
				}
				errors.add("Excel Header are not matching Unmatched Header Name: " + unmatchedHeaderName);
				System.out.println("Excel Header are not matching Unmatched Header Name: " + unmatchedHeaderName);
				UploadedStatus.put("error", errors);

			} else {
				// System.out.println("###########else####################");
				while (rowItr.hasNext()) {
					recordlevelerrors = new ArrayList();
					int rowErorExistFlag = 0;
					ArrayList<String> ValidrowDataLst = new ArrayList<String>();
					ArrayList rowDataLst = new ArrayList();
					String insertQuery = "";
					HSSFRow row = (HSSFRow) rowItr.next();
					for (int k = 0; k < columnCnt; k++) {

						String ExcelColumnName = columnNameLst.get(k).toString();
						String ColumnName = TemplateHeaderDisplayName.get(ExcelColumnName);
						TableDetailBean tableBeanObj = headers.get(ColumnName);
						String ColumnAllowNullFlag = tableBeanObj.getColumnAllowNullFlag();
						if (!setFirstColumFlag) {
							max_table_id = tableBeanObj.getMax_table_id();
						}

						if (row_cnt != 0) {
							if (null == row.getCell(k)) {
								rowDataLst.add(row.getCell(k));

							} else {
								rowDataLst.add(row.getCell(k).toString());

							}
							String isValidMsg = validateFieldType(row.getCell(k), tableBeanObj);
							if (!isValidMsg.equals("VALID")) {
								String MandatoryFlag = "";
								if (ColumnAllowNullFlag.equals("Y")) {
									MandatoryFlag = "No";
								} else if (ColumnAllowNullFlag.equals("N")) {
									MandatoryFlag = "Yes";
								}
								recordlevelerrors.add(isValidMsg);
								rowErorExistFlag++;
							} else {
								String prepValues = prepareDataForInsertQuery(row.getCell(k), tableBeanObj);
								ValidrowDataLst.add(prepValues);
							}
						}

					}
					if (row_cnt == 1) {
						for (Entry<String, TableDetailBean> entry : headers.entrySet()) {
							TableDetailBean tabledetailbean = new TableDetailBean();
							tabledetailbean = entry.getValue();
							DataHeadererrors.add(tabledetailbean.getDisplayColumnName());
							
						}
						DataHeadererrors.add("Error Details");
					}
					if (recordlevelerrors.size() > 0) {
						rowDataLst.add(recordlevelerrors.toString().substring(1, recordlevelerrors.toString().length() - 1));
						Dataerrors.add(rowDataLst);
					}
					if ((ValidrowDataLst.size() == columnCnt) && (rowErorExistFlag == 0)) {

						if (!setFirstColumFlag) {
							ColumnNames = "UPLOAD_ID," + ColumnNames+ ",PROCESS_FLAG,PROCESS_DESC,Create_By,Modify_Date,Modify_By,RECORD_ID";
							setFirstColumFlag = true;
						}

						String ColumnValues = ValidrowDataLst.toString().substring(1,ValidrowDataLst.toString().length() - 1);
						ColumnValues = BatchId + "," + ColumnValues + ",'P','Pending'," + "'" + UserObj.getUSR_ID()+ "'," + "NOW()," + "'" + UserObj.getUSR_ID() + "'," + max_table_id;
						insertQuery = "INSERT INTO " + TableName + "(" + ColumnNames + ") VALUES(" + ColumnValues + ")";
						max_table_id = max_table_id + 1;
						Stat.addBatch(insertQuery);
						QueryAddedFlag = true;
						System.out.println("insertQuery : " + insertQuery);

					}
					row_cnt++;

				}
				String bulkTraxkerValues = UserObj.getMEM_BNK_ID() + "," + BatchId + ",'Street Vendor Bulk Upload','"+ UserObj.getUSR_ID() + "'," + "now(),'P',0,0,now()," + excelRowCnt;
				String bulkTraxkerQuery = "insert into bulk_upload_tracker (member_id,upload_id,process_name,uploaded_by,uploaded_date,STATUS,SUCCESS_CNT,UNSUCCESS_CNT,MODIFY_DATE,excel_cnt)"+ "values(" + bulkTraxkerValues + ")";
				int n[] = {};
				try {

					if (QueryAddedFlag) {
						Stat.addBatch(bulkTraxkerQuery);
						n = Stat.executeBatch();
						con.commit();
						System.out.println("n[]" + n.length);
					}

				} catch (Exception err) {
					err.printStackTrace();
					errors.add("Error while Batch executing:" + err.toString());
					UploadedStatus.put("error", errors);
				}
				if (n.length > 0) {
					//UploadedStatus.put("error", errors);
					ArrayList<String> upldId = new ArrayList<String>();
					upldId.add(BatchId);
					UploadedStatus.put("uploadId", upldId);
				}
				UploadedStatus.put("error", errors);
			}
			Allerrors.add(DataHeadererrors);
			Allerrors.add(Dataerrors);
			UploadedStatus.put("allerror", Allerrors);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
			}
			if (Stat != null) {
				Stat.close();
			}
			return UploadedStatus;
		}
	}

	ArrayList<String> getDisplayNameOfHeaderName(ArrayList<String> HeaderNameLst,
			LinkedHashMap<String, TableDetailBean> headers) {
		ArrayList<String> displayHeaderNameLst = new ArrayList<String>();
		for (Entry<String, TableDetailBean> entry : headers.entrySet()) {
			TableDetailBean t = entry.getValue();
			// System.out.println("[ "+t.getDisplayColumnName()+"] display #t# Name [
			// "+t.getColumnName()+" ]");
		}

		for (String header : HeaderNameLst) {
			if (null != headers.get(header)) {
				TableDetailBean tabledetailbean = headers.get(header);
				// System.out.println("##############getDisplayNameOfHeaderName###############:"+header);
				String display_header_name = tabledetailbean.getDisplayColumnName();
				// System.out.println("##############getDisplayNameOfHeaderName#######display_header_name########:"+display_header_name);
				displayHeaderNameLst.add(display_header_name);
			} else {
				displayHeaderNameLst.add(header);
			}
		} /**/
		System.out.println("displayHeaderNameLst :" + displayHeaderNameLst);
		System.out.println("DisplayHeaderNameLst Size:" + displayHeaderNameLst.size());
		System.out.println("HeaderNameLst Size:" + HeaderNameLst.size());

		return displayHeaderNameLst;
	}

	public LinkedHashMap<String, TableDetailBean> getTableHeaderDataOSAmt(Connection conn, String tableName,
			String bulkName) {
		// TODO Auto-generated method stub
		System.out.println("##getTableHeaderData##");
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		int status = -1;
		String errorCode = null;
		LinkedHashMap<String, TableDetailBean> TableHeaderDataDetail = new LinkedHashMap<String, TableDetailBean>();
		try {
			// ##
			callableStmt = conn.prepareCall("{?=call BULKUPLOAD.Func_table_header_data_" + bulkName + "(?,?,?,?,?)}");
			callableStmt.registerOutParameter(1, java.sql.Types.INTEGER);
			callableStmt.setString(2, "Admin");
			callableStmt.setString(3, tableName);
			callableStmt.registerOutParameter(4, OracleTypes.CURSOR);
			callableStmt.registerOutParameter(5, java.sql.Types.NUMERIC);
			// callableStmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			callableStmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			callableStmt.execute();
			status = callableStmt.getInt(1);
			errorCode = callableStmt.getString(6);

			if (status == 1) {
				// Log.log(Log.ERROR, "ClaimAction","getClaimSettlePaymentReportData()","SP
				// returns a 1. Error code is :" + errorCode);
				callableStmt.close();
				System.out.println("errorCode:" + errorCode);
				throw new NBFCException(errorCode);

			} else if (status == 0) {

				resultset = (ResultSet) callableStmt.getObject(4);
				int max_tab_id = callableStmt.getInt(5);
				// String DisplayColName = callableStmt.getString(6);

				// System.out.println("##getTableHeaderData## max_tab_id:"+max_tab_id);
				// System.out.println("##getTableHeaderData## firstColName:"+firstColName);

				while (resultset.next()) {
					TableDetailBean tableBeanObj = new TableDetailBean();
					tableBeanObj.setColumnName(resultset.getString("column_name"));
					tableBeanObj.setColumnDataType(resultset.getString("data_type"));
					tableBeanObj.setColumnLength(resultset.getInt("data_length"));
					tableBeanObj.setColumnAllowNullFlag(resultset.getString("NULLABLE"));
					tableBeanObj.setMax_table_id(max_tab_id);
					tableBeanObj.setDisplayColumnName(resultset.getString("DISPLAY_NAME"));
					TableHeaderDataDetail.put(resultset.getString("column_name"), tableBeanObj);
				}
			}
			resultset.close();
			resultset = null;
			callableStmt.close();
			callableStmt = null;
		} catch (SQLException sqlexception) {
			// Log.log(Log.ERROR,
			// "ClaimAction","getClaimSettlePaymentSavedMLIWiseCK2Data()","Error retrieving
			// all Claim settled Payment Process Data!");
			System.out.println("sqlexception :" + sqlexception.toString());
			throw new NBFCException(sqlexception.getMessage());

		} finally {
			// DBConnection.freeConnection(conn);
		}
		return TableHeaderDataDetail;
	}

	public static ArrayList getDownloadList(String uploadId, String flag, Connection conn) {
		ArrayList dolanloadDataList = new ArrayList<Object>();
		ArrayList DataList1 = new ArrayList<Object>();
		ArrayList coulmName1 = new ArrayList<Object>();
		ResultSetMetaData resultSetMetaData1 = null;
		try {
			// String sqlQuery= "SELECT * FROM stvn.bulk_uplaod_failur where
			// process_desc='S' and UPLOAD_ID=9072020105555" ;
		//	String sqlQuery = "SELECT CIG_MEMBER AS 'Member of CIG', CIG_NAME AS 'Name of CIG', CIG_Code AS 'CIG Code', JLG_MEMBER AS 'Member of JLG', JLG_Name AS 'Name of JLG', JLG_Code AS 'JLG Code', STREET_VENDOR_NAME AS 'Name of Street Vendor', FATHER_SPOUSE_NAME AS 'Father\\'s/Spouse\\'s Name', DOB AS 'Date of Birth', Gender AS 'Gendar', Marital_Status AS 'Marital Status', Mobile_no AS 'Mobile no', Social_Category AS 'Social Category', Nativity AS 'Nativity', PWD AS 'PWD', Minority_community AS 'Minority community', AadharNo AS 'Aadhaar No', KYC_documents AS 'KYC Documents', Others_KYC_Document AS 'Others Govt Approved Document', KYC_documents_No AS 'KYC documents No', Vending_proof_doc AS 'Proof of Vending', Vending_proof_doc_No AS 'Proof of Vending No', Permanent_Address AS 'Permanent Address', District AS 'P_District', State AS 'P_State', Pin AS 'P_Pin', Present_Address AS 'Current Address', Present_District AS 'C_District', Present_State AS 'C_State', Present_Pin AS 'C_Pin', Activity_Name AS 'Name of Activity', Others_Activity AS 'If Activity is Others (Pls specify)', Vending_Place_Name AS 'Place of Vending', Vending_Duration AS 'Duration of Vending  (YYYY/MM)', DistrictOfVending AS 'District of Vending', Vending_Area AS 'Area of Vending', Monthly_Sales AS 'Monthly Sales (In Rs)', Disbursement_Amount AS 'Disbursement Amount', Loan_Sanction_Amount AS 'Loan Sanction Amount', SanctionDate AS 'Sanction Date(DD/MM/YYYY)', Guarantee_Amount AS 'Guarantee Amount', Loan_Tenure AS 'Tenure of Loan (in Months)', Loan_Account_no AS 'Loan Account number', Moratorium AS 'Moratorium (in Months)', total_Instalment AS 'No of Instalment', ROI AS 'ROI', process_desc AS 'Process Result' FROM bulk_uplaod_failur "
				//	+ "WHERE PROCESS_FLAG='" + flag + "' AND UPLOAD_ID = '" + uploadId + "'";
					
					String sqlQuery = "SELECT CIG_MEMBER AS 'Member of CIG / JLG/None', CIG_NAME AS 'Name of CIG / JLG',  STREET_VENDOR_NAME AS 'Name of Street Vendor', Gender AS 'Gendar',Mobile_no AS 'Mobile no', Social_Category AS 'Social Category',  PWD AS 'PWD', Minority_community AS 'Minority community', AadharNo AS 'Aadhaar No',  Activity_Name AS 'Name of Activity', Others_Activity AS 'If Activity is Others (Pls specify)', DistrictOfVending AS 'District of Vending', State AS 'State of Vending', Loan_Sanction_Amount AS 'Loan Sanction Amount', SanctionDate AS 'Sanction Date(DD/MM/YYYY)', Guarantee_Amount AS 'Guarantee Amount', Loan_Tenure AS 'Tenure of Loan (in Months)', Loan_Account_no AS 'Loan Account number', Moratorium AS 'Moratorium (in Months)',  ROI AS 'ROI', process_desc AS 'Process Result' FROM bulk_uplaod_failur "
							+ "WHERE PROCESS_FLAG='" + flag + "' AND UPLOAD_ID = '" + uploadId + "'";
			Statement stmt = conn.createStatement();
			ResultSet rs1 = stmt.executeQuery(sqlQuery);
			// rs1 = (ResultSet) callableStmt3.getResultSet();
			resultSetMetaData1 = rs1.getMetaData();
			int coulmnCount1 = resultSetMetaData1.getColumnCount();
			for (int i = 1; i <= coulmnCount1; i++) {
				// System.out.println("Column "+i+"
				// Name:"+resultSetMetaData1.getColumnLabel(i));
				coulmName1.add(resultSetMetaData1.getColumnLabel(i));

			}
			// ArrayList<String> DisplayHeaderNameLst1 =
			// getDisplayNameOfHeaderName(coulmName1, headers);
			// System.out.println("%%%%%%%%%%%%coulmName1:" + coulmName1);
			// System.out.println("%%%%%%%%%%%%DisplayHeaderNameLst:" +
			// DisplayHeaderNameLst1);

			while (rs1.next()) {

				ArrayList columnValue1 = new ArrayList();
				for (int i = 1; i <= coulmnCount1; i++) {
					columnValue1.add(rs1.getString(i));
				}
				DataList1.add(columnValue1);
			}

			dolanloadDataList.add(0, coulmName1);
			dolanloadDataList.add(1, DataList1);

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			try {
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dolanloadDataList;
	}
}
