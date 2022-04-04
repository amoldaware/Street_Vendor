package com.nbfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.CGPANDetailsReportBean;
import com.nbfc.dao.CGPANDetailDao;


@Service("CGPANDetailservice")
@Transactional

public class CGPANDetailServiceImpl implements CGPANDetailservice{
	@Autowired
	private CGPANDetailDao CgpanDetailDao;

	@Override
	public List<CGPANDetailsReportBean> getCgpanDetailsReport(String cgpan) {
		// TODO Auto-generated method stub
		return CgpanDetailDao.getCgpanDetailsReport(cgpan);
	}
	


}
