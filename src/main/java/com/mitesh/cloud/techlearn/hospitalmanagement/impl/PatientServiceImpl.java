package com.mitesh.cloud.techlearn.hospitalmanagement.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mitesh.cloud.techlearn.hospitalmanagement.constant.AppConstant;
import com.mitesh.cloud.techlearn.hospitalmanagement.dto.PatientDto;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Doctor;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Patient;
import com.mitesh.cloud.techlearn.hospitalmanagement.exception.ResourceNotFoundException;
import com.mitesh.cloud.techlearn.hospitalmanagement.repository.PatientRepository;
import com.mitesh.cloud.techlearn.hospitalmanagement.response.PatientResponse;
import com.mitesh.cloud.techlearn.hospitalmanagement.service.PatientService;
import com.mitesh.cloud.techlearn.hospitalmanagement.util.HospitalUtility;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	HospitalUtility patientUtility;

	@Override
	public PatientResponse addPatientDetails(PatientDto patientDto) {

		List<Doctor> listOfDoctors = new ArrayList<Doctor>();

		for (Integer doctorId : patientDto.getDoctors()) 
		{
			RestTemplate restTemplate = new RestTemplate();
			Doctor forObject = restTemplate.getForObject(AppConstant.REST_URI + AppConstant.DOCTOR + doctorId,Doctor.class);
			listOfDoctors.add(forObject);
		}
		Patient patient = new Patient();
		patient.setFirstName(patientDto.getFirstName());
		patient.setLastName(patientDto.getLastName());
		patient.setPhone(patientDto.getPhone());
		patient.setInsurance(patientDto.getInsurance());
		patient.setDoctors(listOfDoctors);
		
		Patient	savedPatient= patientRepository.save(patient);
		try {
			patientUtility.uploadPatientToS3(savedPatient);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PatientResponse response = modelMapper.map(savedPatient, PatientResponse.class);
		return response;
	}

	@Override
	public PatientResponse getPatientDetails(int pId) {
		Optional<Patient> patient = patientRepository.findById(Long.valueOf(pId));
		int index=0;
		System.out.println("No of Doctors "+patient.get().getDoctors().size());
		List<Doctor> doctors=new ArrayList<Doctor>();
			
		for(Doctor doctor:patient.get().getDoctors())
		{
			Doctor doctor1=new Doctor();
			doctor1.setFirstName(doctor.getFirstName());
			doctor1.setLastName(doctor.getLastName());
			doctor1.setSpeciality(doctor.getSpeciality());
			doctor1.setId(doctor.getId());
			doctors.add(doctor1);
			//index++;
		}
		
				
		
		/*
		 * for(Doctor doctor:patient.get().getDoctors()) { Doctor doctor1=new Doctor();
		 * doctor1.setFirstName(patient.get().getDoctors().get(index).getFirstName());
		 * doctor1.setLastName(patient.get().getDoctors().get(index).getLastName());
		 * doctor1.setSpeciality(patient.get().getDoctors().get(index).getSpeciality());
		 * doctor1.setId(patient.get().getDoctors().get(index).getId());
		 * doctors.add(doctor1); }
		 */
		
		
		  Doctor doctor=new Doctor(); 
		  doctor.setFirstName("test");		  
		  doctor.setLastName("test"); 
		  doctor.setId(5L); 
		  List<Doctor> list=Arrays.asList(doctor);		 
		
		if (!patient.isPresent())
			throw new ResourceNotFoundException("Entered Patient Id " + pId + " doesn't exist");
		//PatientResponse patientResponse = modelMapper.map(patient.get(), PatientResponse.class);
		PatientResponse patientResponse=new PatientResponse();
		patientResponse.setFirstName(patient.get().getFirstName());
		patientResponse.setLastName(patient.get().getLastName());
		patientResponse.setPhone(patient.get().getPhone());
		patientResponse.setInsurance(patient.get().getInsurance());
		patientResponse.setId(patient.get().getId());
		patientResponse.setDoctors(doctors);
		return patientResponse;
	}

	/*
	 * 
	 * @Autowired private PatientRepository patientRepository;
	 * 
	 * @Autowired PatientUtility patientUtility;
	 * 
	 * @Autowired ModelMapper modelMapper;
	 * 
	 * @Override public void deletePatientDetails(int pId) {
	 * 
	 * Optional<Patient> patient = patientRepository.findById(pId); if
	 * (!patient.isPresent()) throw new
	 * ResourceNotFoundException("Entered Patient Id " + pId + " doesn't exist");
	 * else { patient.get().setStatus(false); patientRepository.save(patient.get());
	 * } }
	 * 
	 * @Override public List<PatientDto> findAllActivePatients() { List<Patient>
	 * findAllActivePatients = patientRepository.findAllActivePatients();
	 * List<PatientDto> listOfPatientDto =
	 * ObjectMapperUtils.mapAll(findAllActivePatients, PatientDto.class); return
	 * listOfPatientDto; }
	 * 
	 * @Override public Patient updatePatientDetails(PatientDto patientDto, int pId)
	 * { Optional<Patient> patient = patientRepository.findById(pId); if
	 * (!patient.isPresent()) throw new
	 * ResourceNotFoundException("Entered Patient Id " + pId + " doesn't exist");
	 * 
	 * patient.get().setCopay(patientDto.getCopay());
	 * patient.get().setDisease(patientDto.getDisease());
	 * patient.get().setFName(patientDto.getFName());
	 * patient.get().setGender(patientDto.getGender());
	 * patient.get().setLName(patientDto.getLName());
	 * patient.get().setPhNo(patientDto.getPhNo());
	 * patient.get().setProviderName(patientDto.getProviderName()); try {
	 * patientRepository.save(patient.get()); }catch(DataIntegrityViolationException
	 * e) { throw new ResourceAlreadyExistsException(e.getRootCause().getMessage());
	 * } return patient.get(); }
	 * 
	 */}
