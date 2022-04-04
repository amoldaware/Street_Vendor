package com.nbfc.dao;

import java.util.List;

import com.nbfc.bean.CGPANDetailsReportBean;

public interface CGPANDetailDao {

	List<CGPANDetailsReportBean> getCgpanDetailsReport(String cgpan);

}
