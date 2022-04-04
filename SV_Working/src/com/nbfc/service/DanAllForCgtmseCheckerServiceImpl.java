package com.nbfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.dao.DanAllForCgtmseCheckerDao;
import com.nbfc.model.DanAllForCgtmseCheckerModel;
@Service("danAllForCgtmseCheckerService")
@Transactional
public class DanAllForCgtmseCheckerServiceImpl implements DanAllForCgtmseCheckerService{
@Autowired
DanAllForCgtmseCheckerDao danAllForCgtmseCheckerDao;


public List<DanAllForCgtmseCheckerModel> getDataForAllDan() {
	// TODO Auto-generated method stub
	return danAllForCgtmseCheckerDao.getDataForAllDan();
}


public int approveDanAllForCGTMSEChecker(List<String> list) {
	// TODO Auto-generated method stub
	return danAllForCgtmseCheckerDao.approveDanAllForCGTMSEChecker(list);
}
	
	
}
