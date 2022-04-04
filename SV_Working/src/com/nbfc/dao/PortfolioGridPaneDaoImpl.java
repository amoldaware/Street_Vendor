package com.nbfc.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.bean.PortfolioGridPanBean;
import com.nbfc.controller.PortfolioGridController;
import com.nbfc.helper.PortfolioGridHelper;
import com.nbfc.model.NbfcInterfaceUploadForPortfolioGridPaneModel;
import com.nbfc.model.PortfolioGridPanModel;
@Repository("portfolioGridPaneDao")
public class PortfolioGridPaneDaoImpl implements PortfolioGridPaneDao{
@Autowired
private SessionFactory sessionFactory;
static Logger log = Logger.getLogger(PortfolioGridPaneDaoImpl.class.getName());

	
	public List<PortfolioGridPanModel> getPortfolioData() {
		log.info("Enter from preparePortfolioEditListofBean() method in PortfolioGridPaneDaoImpl class ");
		return (List<PortfolioGridPanModel>)sessionFactory.getCurrentSession().createCriteria(PortfolioGridPanModel.class).list();

	
		/*try {			

			Query queryObject = sessionFactory
					.getCurrentSession().createQuery(
					"select sum(iu.sanctionAmount),pm.long_name,pm.max_portfolio_size,pm.PortFolioName  from PortfolioGridPanModel as pm join pm.nbfcInterfaceUploadForPortfolioGridPaneModel as iu   where iu.VERIFIEDSTATUS=:VERIFIEDSTATUS  group by pm.portfolio_no,pm.long_name,pm.PortFolioName,pm.max_portfolio_size ,pm.PortFolioName");
			queryObject.setParameter("VERIFIEDSTATUS", "Y");			
			List<?> list = queryObject.list();	
			List<PortfolioGridHelper> helpdata=new ArrayList<PortfolioGridHelper>();
			PortfolioGridHelper pd=null;
			if(list.size()>0){
			for(int i=0; i<list.size(); i++) {
				//SanctionCheckerBean cb=new SanctionCheckerBean();
				Object[] row = (Object[]) list.get(i);
				pd = new PortfolioGridHelper();			
				pd.setSanction((String) row[0]);
				pd.setLong_name((String) row[1]);
				pd.setMax_portfolio_size((Double)row[2]);
				pd.setPortFolioName((String) row[3]);
				helpdata.add(pd);
			}
			}
					PortfolioGridPanBean pb=null;
					if(helpdata.size()>0){
					for(PortfolioGridHelper pd1:helpdata){
						pb=new PortfolioGridPanBean();
					pb.setLong_name(pd1.getLong_name());
					pb.setMax_portfolio_size(pd1.getMax_portfolio_size());
					pb.setPortfolioName(pd1.getPortFolioName());
					pb.setTotalSantionAmount(pd1.getSanction());
					resultList.add(pb);	
										
				}
					}

			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sessionFactory.close();
		}
		return resultList;
		
	*/
		
		
	}
	
	public List<PortfolioGridPanModel> getPortfolioDetailsbyIndex(
			String nameSearch, String searchValue) {
		log.info("Enter from getPortfolioDetailsbyIndex() method in PortfolioGridPaneDaoImpl class ");

		List<PortfolioGridPanModel> list=new ArrayList<PortfolioGridPanModel>();

		//List<PortfolioGridPanModel> list=new ArrayList<PortfolioGridPanModel>();
		//List<?> list = null;	

		// TODO Auto-generated method 0
		try {
			if (nameSearch.equals("Long_name")&& !searchValue.isEmpty()) {
				list =sessionFactory.getCurrentSession().createCriteria(PortfolioGridPanModel.class).add(Restrictions.like("long_name", searchValue,
						MatchMode.ANYWHERE)).list();
				
				/*Query queryObject = sessionFactory.getCurrentSession().createQuery("select sum(iu.sanctionAmount),pm.long_name,pm.max_portfolio_size,pm.PortFolioName  from PortfolioGridPanModel as pm join pm.nbfcInterfaceUploadForPortfolioGridPaneModel as iu   where iu.VERIFIEDSTATUS=:VERIFIEDSTATUS and pm.long_name like:long_name  group by pm.portfolio_no,pm.long_name,pm.PortFolioName,pm.max_portfolio_size ,pm.PortFolioName ");
				queryObject.setParameter("VERIFIEDSTATUS", "Y");
				queryObject.setParameter("long_name","%"+searchValue+"%");
				 list = queryObject.list();	*/			
				//added by say-----------------------------------------
				try {
					Query queryObject = sessionFactory.getCurrentSession().createQuery("SELECT PM.portfolio_no,PM.max_portfolio_size,PM.PortFolioName,PM.portfolioDate,PM.portfolioStatus,BD.longName FROM PortfolioGridPanModel PM,MLIExposureIDDetails MD, BankDetails BD WHERE PM.exposureID = MD.EXPOSURE_ID AND MD.MEM_BNK_ID = BD.MEM_BNK_ID AND BD.longName=:lONGNAME");
					//queryObject.setParameter("VERIFIEDSTATUS", "Y");
					System.out.println("val"+searchValue);
					queryObject.setParameter("lONGNAME",searchValue);
					List<Object[]> list1 = queryObject.list();	
					 
					
						if (list1.size() > 0) {
						
							for(Object[] arr : list1){
								System.out.println(Arrays.toString(arr));
							}
							
						}
				} catch (Exception e) {
					e.printStackTrace();// TODO: handle exception
				}
			//end---------------------------------------------------------------------------------			
			} else if (nameSearch.equals("portfolioName")&& !searchValue.isEmpty()) {
				list =sessionFactory.getCurrentSession().createCriteria(PortfolioGridPanModel.class).add(Restrictions.like("PortFolioName", searchValue,
						MatchMode.ANYWHERE)).list();
				/*Query queryObject = sessionFactory.getCurrentSession().createQuery("select sum(iu.sanctionAmount),pm.long_name,pm.max_portfolio_size,pm.PortFolioName  from PortfolioGridPanModel as pm join pm.nbfcInterfaceUploadForPortfolioGridPaneModel as iu   where iu.VERIFIEDSTATUS=:VERIFIEDSTATUS and pm.PortFolioName like:PortFolioName  group by pm.portfolio_no,pm.long_name,pm.PortFolioName,pm.max_portfolio_size ,pm.PortFolioName ");
				queryObject.setParameter("VERIFIEDSTATUS", "Y");
				queryObject.setParameter("PortFolioName",searchValue+"%");
				 list = queryObject.list();	*/
			} else {
				list = 	sessionFactory.getCurrentSession().createCriteria(PortfolioGridPanModel.class).list();
				/*Query queryObject = sessionFactory
				.getCurrentSession().createQuery("select sum(iu.sanctionAmount),pm.long_name,pm.max_portfolio_size,pm.PortFolioName  from PortfolioGridPanModel as pm join pm.nbfcInterfaceUploadForPortfolioGridPaneModel as iu   where iu.VERIFIEDSTATUS=:VERIFIEDSTATUS  group by pm.portfolio_no,pm.long_name,pm.PortFolioName,pm.max_portfolio_size ,pm.PortFolioName");
				queryObject.setParameter("VERIFIEDSTATUS", "Y");	
				list = queryObject.list();	*/
			}
			
			/*List<PortfolioGridHelper> helpdata = new ArrayList<PortfolioGridHelper>();
			PortfolioGridHelper pd = null;
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					// SanctionCheckerBean cb=new SanctionCheckerBean();
					Object[] row = (Object[]) list.get(i);
					pd = new PortfolioGridHelper();
					pd.setSanction((String) row[0]);
					pd.setLong_name((String) row[1]);
					pd.setMax_portfolio_size((Double) row[2]);
					pd.setPortFolioName((String) row[3]);
					helpdata.add(pd);
				}
			}
							PortfolioGridPanBean pb=null;
			if (helpdata.size() > 0) {
				for (PortfolioGridHelper pd1 : helpdata) {
					pb = new PortfolioGridPanBean();
					pb.setLong_name(pd1.getLong_name());
					pb.setMax_portfolio_size(pd1.getMax_portfolio_size());
					pb.setPortfolioName(pd1.getPortFolioName());
					pb.setTotalSantionAmount(pd1.getSanction());
					resultList.add(pb);

				}
			}*/
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			log.error("getting error in getPortfolioDetailsbyIndex() method in PortfolioGridPaneDaoImpl class "+e);

			e.printStackTrace();
		}finally{
			sessionFactory.close();
		}

		return list;		

		
		}


public List<Object> getPortfolioDetailsbymliLongName(String nameSearch,
		String searchValue) {
	// TODO Auto-generated method stub
	
	Query queryObject = sessionFactory.getCurrentSession().createQuery("SELECT PM.portfolio_no,PM.max_portfolio_size,PM.PortFolioName,PM.portfolioDate,PM.portfolioStatus,BD.longName" +
			" FROM PortfolioGridPanModel PM,MLIExposureIDDetails MD, BankDetails BD WHERE PM.exposureID = MD.EXPOSURE_ID " +
			"AND MD.MEM_BNK_ID = BD.MEM_BNK_ID AND UPPER(BD.longName) LIKE :lONGNAME");
	//queryObject.setParameter("VERIFIEDSTATUS", "Y");

	queryObject.setParameter("lONGNAME",searchValue.toUpperCase()+'%');
	System.out.println("val"+searchValue+'%');
	List<Object> list1 = queryObject.list();	

	return list1;
}
}


