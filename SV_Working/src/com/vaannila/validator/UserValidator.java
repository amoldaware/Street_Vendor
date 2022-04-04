package com.vaannila.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.nbfc.bean.LoginBean;


/**
 * @author Saurav Tyagi 2017
 * 
 */
public class UserValidator implements Validator {
	static Logger log = Logger.getLogger(UserValidator.class.getName());	
	public boolean supports(Class<?> clazz) {
		return LoginBean.class.isAssignableFrom(clazz);
	}

	
	public void validate(Object target, Errors errors) {
		log.info("validate called"+errors.getFieldError());
		
	    //To check Validation
		LoginBean loginBeanObj=(LoginBean)target;
		
		if(loginBeanObj.getUsr_id()=="" || loginBeanObj.getUsr_id()==null){
			errors.rejectValue("usr_id","userid.required");
			
		}
		
		if(loginBeanObj.getUsr_password()=="" || loginBeanObj.getUsr_password()==null){
			errors.rejectValue("usr_password","password.required");
			
		}
		if(loginBeanObj.getJ_captcha_response()=="" || loginBeanObj.getJ_captcha_response()==null){
			errors.rejectValue("j_captcha_response","j_captcha_response.required");
			
		}
		if(loginBeanObj.getCheckBoxId()=="" || loginBeanObj.getCheckBoxId()==null){
			errors.rejectValue("checkBoxId","checkbox.required");
			
		}
		
	}
	
	public boolean validateForUserid(Object target, Errors errors) {
		log.info("validate called"+errors.getFieldError());
		
	    //To check Validation
		LoginBean loginBeanObj=(LoginBean)target;
		
		if(loginBeanObj.getUsr_id()=="" || loginBeanObj.getUsr_id()==null){
			errors.rejectValue("usr_id","userid.required");
			return false;
		}else{
		return true;
		}
	}
}
