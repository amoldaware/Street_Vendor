package com.nbfc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbfc.bean.AppropriationManualBO;
import com.nbfc.dao.AppropriationManualDao;
import com.nbfc.exception.BusinessException;

@Service
public class AppropriationManualServiceImpl implements AppropriationManualService {
	final static Logger logger = Logger.getLogger(AppropriationManualServiceImpl.class.getName());

	@Autowired
	AppropriationManualDao appropriationManualDao;

	@Override
	public List<AppropriationManualBO> getAll(AppropriationManualBO bo) throws BusinessException {
		return appropriationManualDao.findAll(bo);
	}

	@Override
	public boolean save(List<AppropriationManualBO> listBO) throws BusinessException {
		return appropriationManualDao.save(listBO);
	}

}
