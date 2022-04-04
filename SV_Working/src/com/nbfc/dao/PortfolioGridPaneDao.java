package com.nbfc.dao;

import java.util.List;

import com.nbfc.bean.PortfolioGridPanBean;
import com.nbfc.model.PortfolioGridPanModel;

public interface PortfolioGridPaneDao {
	public List<PortfolioGridPanModel> getPortfolioData();

	public List<PortfolioGridPanModel> getPortfolioDetailsbyIndex(
			String nameSearch, String searchValue);

	public List<Object> getPortfolioDetailsbymliLongName(String nameSearch,
			String searchValue);

}
