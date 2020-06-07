package com.mitesh.cloud.techlearn.hospitalmanagement.impl;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mitesh.cloud.techlearn.hospitalmanagement.config.ObjectMapperUtils;
import com.mitesh.cloud.techlearn.hospitalmanagement.dto.PatientDto;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Patient;
import com.mitesh.cloud.techlearn.hospitalmanagement.exception.ResourceAlreadyExistsException;
import com.mitesh.cloud.techlearn.hospitalmanagement.exception.ResourceNotFoundException;
import com.mitesh.cloud.techlearn.hospitalmanagement.repository.PatientRepository;
import com.mitesh.cloud.techlearn.hospitalmanagement.service.PatientService;
import com.mitesh.cloud.techlearn.hospitalmanagement.util.PatientUtility;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	PatientUtility patientUtility;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public Patient getPatientDetails(int pId) {

		Optional<Patient> patient = patientRepository.findById(pId);
		if (!patient.isPresent())
			throw new ResourceNotFoundException("Entered Patient Id " + pId + " doesn't exist");
		return patient.get();
	}

	@Override
	public Patient addPatientDetails(PatientDto patientDto) {

		Patient patient = modelMapper.map(patientDto, Patient.class);
		patient.setStatus(true);
		Patient savedPatient = patientRepository.save(patient);
		try {
			patientUtility.uploadToS3(savedPatient);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return savedPatient;
	}

	@Override
	public void deletePatientDetails(int pId) {

		Optional<Patient> patient = patientRepository.findById(pId);
		if (!patient.isPresent())
			throw new ResourceNotFoundException("Entered Patient Id " + pId + " doesn't exist");
		else {
			patient.get().setStatus(false);
			patientRepository.save(patient.get());
		}
	}

	@Override
	public List<PatientDto> findAllActivePatients() {
		List<Patient> findAllActivePatients = patientRepository.findAllActivePatients();
		List<PatientDto> listOfPatientDto = ObjectMapperUtils.mapAll(findAllActivePatients, PatientDto.class);
		return listOfPatientDto;
	}

	@Override
	public Patient updatePatientDetails(PatientDto patientDto, int pId) {
		Optional<Patient> patient = patientRepository.findById(pId);
		if (!patient.isPresent())
			throw new ResourceNotFoundException("Entered Patient Id " + pId + " doesn't exist");

		patient.get().setCopay(patientDto.getCopay());
		patient.get().setDisease(patientDto.getDisease());
		patient.get().setFName(patientDto.getFName());
		patient.get().setGender(patientDto.getGender());
		patient.get().setLName(patientDto.getLName());
		patient.get().setPhNo(patientDto.getPhNo());
		patient.get().setProviderName(patientDto.getProviderName());
		try 
		{		
			patientRepository.save(patient.get());
		}catch(DataIntegrityViolationException e) {
			throw new ResourceAlreadyExistsException(e.getRootCause().getMessage());
		}
		return patient.get();
	}

}
