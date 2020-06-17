package com.mitesh.cloud.techlearn.hospitalmanagement.response;

import java.util.Date;

import com.mitesh.cloud.techlearn.hospitalmanagement.dto.DoctorDto;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Doctor;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {

	private Date appointmentTime;
	private boolean started;
	private boolean ended;
	private String reason;
	
	private Patient patient;
	private Doctor doctor;
}
