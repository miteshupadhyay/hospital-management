package com.mitesh.cloud.techlearn.hospitalmanagement.service;

import com.mitesh.cloud.techlearn.hospitalmanagement.dto.AppointmentDto;
import com.mitesh.cloud.techlearn.hospitalmanagement.dto.DoctorDto;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Doctor;
import com.mitesh.cloud.techlearn.hospitalmanagement.response.AppointmentResponse;
import com.mitesh.cloud.techlearn.hospitalmanagement.response.DoctorResponse;

public interface AppointmentService {
	  
	  public AppointmentResponse addAppointmentDetails(AppointmentDto appointmentDto);
	  
	  public AppointmentResponse getAppointmentDetails(int appointmentId);
}
