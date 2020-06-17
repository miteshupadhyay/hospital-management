package com.mitesh.cloud.techlearn.hospitalmanagement.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitesh.cloud.techlearn.hospitalmanagement.dto.DoctorDto;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Doctor;
import com.mitesh.cloud.techlearn.hospitalmanagement.repository.DoctorRepository;
import com.mitesh.cloud.techlearn.hospitalmanagement.response.DoctorResponse;
import com.mitesh.cloud.techlearn.hospitalmanagement.service.DoctorService;
import com.mitesh.cloud.techlearn.hospitalmanagement.util.HospitalUtility;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired 
	ModelMapper modelMapper;
	
	@Autowired
	HospitalUtility hospitalUtility;

	@Override 
	public DoctorResponse addDoctorDetails(DoctorDto doctorDto) {
		Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
		 Doctor savedDoctor = doctorRepository.save(doctor);
		 try {
		 hospitalUtility.uploadDoctorToS3(savedDoctor);
		 }catch (Exception e) {
			System.out.println(e);
		}
		 DoctorResponse doctorResponse = modelMapper.map(savedDoctor, DoctorResponse.class);
		 return doctorResponse;
	  }

	@Override
	public DoctorResponse getDoctorDetails(int doctorId) {
		Optional<Doctor> findDoctor = doctorRepository.findById(Long.valueOf(doctorId));
		DoctorResponse doctorResponse = modelMapper.map(findDoctor.get(), DoctorResponse.class);
		return doctorResponse;
	}

}
