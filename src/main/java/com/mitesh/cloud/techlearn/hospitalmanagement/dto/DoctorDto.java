package com.mitesh.cloud.techlearn.hospitalmanagement.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("DoctorDto")
public class DoctorDto {

	private String firstName;
	
	private String lastName;
	
	private String speciality;
	
}
