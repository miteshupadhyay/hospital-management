package com.mitesh.cloud.techlearn.hospitalmanagement.service;

import java.util.List;

import com.mitesh.cloud.techlearn.hospitalmanagement.dto.PatientDto;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Patient;

public interface PatientService {
	
	public Patient getPatientDetails(int pId);

	public Patient addPatientDetails(PatientDto patientDto);
	
	public void deletePatientDetails(int pId);
	
	public List<PatientDto> findAllActivePatients();
	
	public Patient updatePatientDetails(PatientDto patientDto,int pId);

}
