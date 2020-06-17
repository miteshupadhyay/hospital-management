package com.mitesh.cloud.techlearn.hospitalmanagement.response;

import java.util.List;

import javax.persistence.Embedded;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Doctor;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Insurance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {

	private Long id;
	private String firstName;
	private String lastName;
	private String phone;
		
	@Embedded
	private Insurance insurance;
	private List<Doctor> doctors;
	
}
