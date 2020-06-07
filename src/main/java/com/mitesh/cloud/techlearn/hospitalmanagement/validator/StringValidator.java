package com.mitesh.cloud.techlearn.hospitalmanagement.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mitesh.cloud.techlearn.hospitalmanagement.validatorConstraint.StringValidatorConstraint;

public class StringValidator implements ConstraintValidator<StringValidatorConstraint, String>{

	@Override
    public void initialize(StringValidatorConstraint stringContstraint) {
    }
 
    @Override
    public boolean isValid(String disease,ConstraintValidatorContext cxt) {
    	if(disease.matches("[0-9]+"))
    		return false;
    	else 
    		return true;
    }
}
