package com.mitesh.cloud.techlearn.hospitalmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mitesh.cloud.techlearn.hospitalmanagement.dto.PatientDto;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	Patient save(PatientDto patientDto);
	
	@Query("from Patient where status='1'")
	List<Patient> findAllActivePatients();

}
