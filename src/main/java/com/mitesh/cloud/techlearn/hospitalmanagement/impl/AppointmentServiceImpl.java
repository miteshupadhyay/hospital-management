package com.mitesh.cloud.techlearn.hospitalmanagement.impl;

import java.security.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mitesh.cloud.techlearn.hospitalmanagement.constant.AppConstant;
import com.mitesh.cloud.techlearn.hospitalmanagement.dto.AppointmentDto;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Appointment;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Doctor;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Patient;
import com.mitesh.cloud.techlearn.hospitalmanagement.repository.AppointmentRepository;
import com.mitesh.cloud.techlearn.hospitalmanagement.response.AppointmentResponse;
import com.mitesh.cloud.techlearn.hospitalmanagement.service.AppointmentService;
import com.mitesh.cloud.techlearn.hospitalmanagement.util.HospitalUtility;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired 
	ModelMapper modelMapper;
	
	@Autowired
	HospitalUtility hospitalUtility;
	
	RestTemplate restTemplate = new RestTemplate();

	@Override 
	public AppointmentResponse addAppointmentDetails(AppointmentDto appointmentDto) {
		
		Doctor doctor= restTemplate.getForObject(AppConstant.REST_URI + AppConstant.DOCTOR + appointmentDto.getDoctorId(),Doctor.class);
		Patient patient=restTemplate.getForObject(AppConstant.REST_URI + AppConstant.PATIENT + appointmentDto.getPatientId(),Patient.class);
		Appointment appointment=new Appointment();
		appointment.setAppointmentTime(new Date());
		appointment.setEnded(appointmentDto.isEnded());
		appointment.setStarted(appointmentDto.isStarted());
		appointment.setReason(appointmentDto.getReason());
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);	
		
		Appointment savedAppointment = appointmentRepository.save(appointment);
		 try {
		hospitalUtility.uploadAppointmentToS3(savedAppointment);
		 }catch (Exception e) {
			// TODO: handle exception
		}
		AppointmentResponse appointmentResponse = modelMapper.map(savedAppointment, AppointmentResponse.class);
		 return appointmentResponse;
	  }

	@Override
	public AppointmentResponse getAppointmentDetails(int appointmentId) {
		Optional<Appointment> findAppointment = appointmentRepository.findById(Long.valueOf(appointmentId));
		AppointmentResponse appointmentResponse = modelMapper.map(findAppointment.get(), AppointmentResponse.class);
		return appointmentResponse;
	}

}
