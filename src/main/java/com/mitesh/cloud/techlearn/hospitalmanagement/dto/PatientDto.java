package com.mitesh.cloud.techlearn.hospitalmanagement.dto;

import java.util.List;

import javax.persistence.Embedded;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Doctor;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Insurance;

import io.swagger.annotations.ApiModel;
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

	private Long id;
	private String firstName;
	private String lastName;
	private String phone;
		
	@Embedded
	private Insurance insurance;
	
	private List<Integer> doctors;
}
