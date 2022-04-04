
package com.nbfc.service;

import java.util.List;

import com.nbfc.bean.PortfolioGridPanBean;
import com.nbfc.model.Login;
import com.nbfc.model.PortfolioGridPanModel;
import com.nbfc.model.SanctionDetailsSave;

public interface PortfolioGridPaneService {
	public List<PortfolioGridPanModel> getPortfolioData();

	public List<PortfolioGridPanModel> getPortfolioDetailsbyIndex(String nameSearch, String searchValue);


	public List<Object> getPortfolioDetailsbymliLongName(String nameSearch,
			String searchValue);

	//public PortfolioGridPanModel getPortfolioDetails(String longName);

}
