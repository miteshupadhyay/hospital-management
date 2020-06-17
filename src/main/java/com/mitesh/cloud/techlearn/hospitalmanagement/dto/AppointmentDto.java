package com.mitesh.cloud.techlearn.hospitalmanagement.dto;

import java.util.Date;

import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Doctor;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Patient;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("AppointmentDto")
public class AppointmentDto {

	private Date appointmentTime;
	private boolean started;
	private boolean ended;
	private String reason;
	
	private Integer patientId;
	private Integer doctorId;
	
}
