package com.nbfc.dao;

import java.util.List;

import com.nbfc.model.DanAllForCgtmseCheckerModel;

public interface DanAllForCgtmseCheckerDao {

	List<DanAllForCgtmseCheckerModel> getDataForAllDan();

	int approveDanAllForCGTMSEChecker(List<String> list);

}
