package com.nbfc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.nbfc.bean.UserBean;

/**
 * @author Saurav Tyagi 2017
 * 
 */
public class UserValidator implements Validator {
	

	
	public boolean supports(Class<?> clazz) {
		return UserBean.class.isAssignableFrom(clazz);
	}

	
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		ValidationUtils.rejectIfEmpty(errors, "gender", "gender.required");
		ValidationUtils.rejectIfEmpty(errors, "country", "country.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "aboutYou", "aboutYou.required");
		UserBean user = (UserBean) target;
		
	}

}
