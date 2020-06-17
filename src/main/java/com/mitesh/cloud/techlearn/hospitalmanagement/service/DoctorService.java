package com.mitesh.cloud.techlearn.hospitalmanagement.service;

import com.mitesh.cloud.techlearn.hospitalmanagement.dto.DoctorDto;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Doctor;
import com.mitesh.cloud.techlearn.hospitalmanagement.response.DoctorResponse;

public interface DoctorService {
	  
	  public DoctorResponse addDoctorDetails(DoctorDto doctorDto);
	  
	  public DoctorResponse getDoctorDetails(int doctorId);
}
