package com.nbfc.dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.nbfc.model.CGTMSEMakerAllBatchFileDetailsForwarededByMLI;


@Repository("cgmseMakerAllBatchFileDetailsForwarededByMLIDao")
public class CGTMSEMakerAllBatchFileDetailsForwarededByMLIDaoImpl implements CGTMSEMakerAllBatchFileDetailsForwarededByMLIDao{
	 
	@Autowired
	private SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	
	public List<CGTMSEMakerAllBatchFileDetailsForwarededByMLI> getIndividualBatchDetails(CGTMSEMakerAllBatchFileDetailsForwarededByMLI cgtmseMakerAllBatchFileDetailsForwarededByMLI) {
		System.out.println(" Inside CGTMSEMakerAllBatchFileDetailsForwarededByMLIDaoImpl#############");
		Integer batchId=cgtmseMakerAllBatchFileDetailsForwarededByMLI.getSubPortfolioNo();
		System.out.println("batchId==="+batchId);
		String Status=cgtmseMakerAllBatchFileDetailsForwarededByMLI.getSTATUS();
		
		String hql="from CGTMSEMakerAllBatchFileDetailsForwarededByMLI where subPortfolioNo=:batchId AND STATUS = :Status";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("batchId", batchId);
		query.setParameter("Status", Status);
		System.out.println("Query=="+query);
		List<CGTMSEMakerAllBatchFileDetailsForwarededByMLI> listCategories4 = query.list();
		/*int count=0;
		Iterator<CGTMSEMakerAllBatchFileDetailsForwarededByMLI> itr1= listCategories4.iterator();
		
		while(itr1.hasNext()){
			CGTMSEMakerAllBatchFileDetailsForwarededByMLI cgtmseMakerAllBatchFileDetailsForwarededByMLIobj=(CGTMSEMakerAllBatchFileDetailsForwarededByMLI)itr1.next();
			System.out.println(" Inside While Loop#############");
			System.out.println("DateOFUPLOAD==="+cgtmseMakerAllBatchFileDetailsForwarededByMLIobj.getDATEOFUPLOAD()+ "PORTFOLIONO=="+cgtmseMakerAllBatchFileDetailsForwarededByMLIobj.getPORTFOLIONO()+ "TOTALSANCTIONEDAMOUNT==="+cgtmseMakerAllBatchFileDetailsForwarededByMLIobj.getTOTALSANCTIONEDAMOUNT()+ "STATUS==="+cgtmseMakerAllBatchFileDetailsForwarededByMLIobj.getSTATUS() );
			count++;
		}
		System.out.println("Count=="+count);*/
		
		return listCategories4;
	}

	public List<CGTMSEMakerAllBatchFileDetailsForwarededByMLI> getAllPortFolioNo() {
		System.out.println(" Inside CGTMSEMakerAllBatchFileDetailsForwarededByMLIDaoImpl#############");
		String hql = "FROM CGTMSEMakerAllBatchFileDetailsForwarededByMLI";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<CGTMSEMakerAllBatchFileDetailsForwarededByMLI> listCategories3 = query.list();
		Iterator<CGTMSEMakerAllBatchFileDetailsForwarededByMLI> itr= listCategories3.iterator();
		while(itr.hasNext()){
			Object o= itr.next();
			CGTMSEMakerAllBatchFileDetailsForwarededByMLI s1=(CGTMSEMakerAllBatchFileDetailsForwarededByMLI)o;
			System.out.println("Inside CGTMSEMakerAllBatchFileDetailsForwarededByMLIDaoImpl class PORTFOLIONO=="+s1.getPORTFOLIONO());
		}
		return listCategories3;
	}
	
}
