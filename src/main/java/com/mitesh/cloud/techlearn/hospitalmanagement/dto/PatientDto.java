package com.mitesh.cloud.techlearn.hospitalmanagement.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.mitesh.cloud.techlearn.hospitalmanagement.constant.AppConstant;
import com.mitesh.cloud.techlearn.hospitalmanagement.model.common.Gender;
import com.mitesh.cloud.techlearn.hospitalmanagement.validatorConstraint.StringValidatorConstraint;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("PatientDto")
public class PatientDto {

	@NotBlank(message= "{patient.fName.validation}")
	@Size(max = AppConstant.MAX_FNAME,min =AppConstant.MIN_FNAME,message = "{length.name.validation}")
	private String fName;
	
	@ApiModelProperty(value = "This is the lastname")
	private String lName;
	
	@Pattern(regexp = "(\\+91|0)[0-9]{10}",message = "{patient.phNo.validation}")
	private String phNo;
	
	private Gender gender;
	
	@NotBlank(message= "{patient.disease.validation}")
	@StringValidatorConstraint
	private String disease;
	
	private String providerName;
	
	private Double copay;
	
}
