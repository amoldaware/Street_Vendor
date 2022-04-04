package com.nbfc.dao;

import java.util.List;

import com.nbfc.bean.AppropriationManualBO;
import com.nbfc.exception.BusinessException;

public interface AppropriationManualDao {
	
	public List<AppropriationManualBO> findAll(AppropriationManualBO bo) throws BusinessException;

	public boolean save(List<AppropriationManualBO> listBO) throws BusinessException;
	

}
