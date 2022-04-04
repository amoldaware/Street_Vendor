package com.vaannila.validator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.nbfc.bean.CGTMSECreatedExposureLimitCheckerBean;
public class CGTMSEExposureMasterCheckerValidator implements Validator{
	
	public boolean supports(Class<?> clazz) {
		System.out.println("supports method called==");
		return CGTMSECreatedExposureLimitCheckerBean.class.isAssignableFrom(clazz);
	}

	public void validate(Object object, Errors errors) {
		CGTMSECreatedExposureLimitCheckerBean cgtmseExposureMasterCheckerBeanObj=(CGTMSECreatedExposureLimitCheckerBean)object;
		if(cgtmseExposureMasterCheckerBeanObj.getMliLongName()=="" || cgtmseExposureMasterCheckerBeanObj.getMliLongName()==null || cgtmseExposureMasterCheckerBeanObj.getMliLongName()=="NONE"){
			errors.rejectValue("mliLongName","mlilongname.required.select");
		}
		if(cgtmseExposureMasterCheckerBeanObj.getMliLongName().equals("select")){
			errors.rejectValue("mliLongName","mlilongname.required.select");
		}
	}
}
