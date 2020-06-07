package com.mitesh.cloud.techlearn.hospitalmanagement.validatorConstraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.mitesh.cloud.techlearn.hospitalmanagement.validator.StringValidator;

@Documented
@Constraint(validatedBy = StringValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StringValidatorConstraint {

		String message() default "{invalid.string}";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
	
}
