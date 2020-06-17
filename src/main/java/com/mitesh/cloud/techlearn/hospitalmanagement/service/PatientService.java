package com.mitesh.cloud.techlearn.hospitalmanagement.service;

import com.mitesh.cloud.techlearn.hospitalmanagement.dto.PatientDto;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Patient;
import com.mitesh.cloud.techlearn.hospitalmanagement.response.PatientResponse;

public interface PatientService {
	
	public PatientResponse addPatientDetails(PatientDto patientDto);
	
	
	  public PatientResponse getPatientDetails(int pId);
	  /* 
	 * public void deletePatientDetails(int pId);
	 * 
	 * public List<PatientDto> findAllActivePatients();
	 * 
	 * public Patient updatePatientDetails(PatientDto patientDto,int pId);
	 */

}
