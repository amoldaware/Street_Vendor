package com.nbfc.service;

import java.util.List;

import com.nbfc.model.DanAllForCgtmseCheckerModel;

public interface DanAllForCgtmseCheckerService {

	public List<DanAllForCgtmseCheckerModel> getDataForAllDan();

	public int approveDanAllForCGTMSEChecker(List<String> list);

}
