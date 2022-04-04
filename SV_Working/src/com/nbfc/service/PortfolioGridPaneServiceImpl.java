package com.nbfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.PortfolioGridPanBean;
import com.nbfc.dao.PortfolioGridPaneDao;
import com.nbfc.model.PortfolioGridPanModel;
import com.nbfc.model.SanctionDetailsSave;
@Service("portfolioGridPaneService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true) 
public class PortfolioGridPaneServiceImpl implements PortfolioGridPaneService{
@Autowired
PortfolioGridPaneDao portfolioGridPaneDao;
	public List<PortfolioGridPanModel> getPortfolioData() {
		// TODO Auto-generated method stub
		return portfolioGridPaneDao.getPortfolioData();
	}
	
	public List<PortfolioGridPanModel> getPortfolioDetailsbyIndex(String nameSearch,
			String searchValue) {
		// TODO Auto-generated method stub
		return portfolioGridPaneDao.getPortfolioDetailsbyIndex(nameSearch, searchValue);
	}
	
	public List<Object> getPortfolioDetailsbymliLongName(String nameSearch,
			String searchValue) {
		return portfolioGridPaneDao.getPortfolioDetailsbymliLongName(nameSearch, searchValue);

	}

}
