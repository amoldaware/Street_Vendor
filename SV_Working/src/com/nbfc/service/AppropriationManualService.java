package com.nbfc.service;

import java.util.List;

import com.nbfc.bean.AppropriationManualBO;
import com.nbfc.exception.BusinessException;

public interface AppropriationManualService {
	public List<AppropriationManualBO> getAll(AppropriationManualBO bo) throws BusinessException;

	public boolean save(List<AppropriationManualBO> listBO) throws BusinessException;

}
