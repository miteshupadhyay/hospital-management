package com.mitesh.cloud.techlearn.hospitalmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.mitesh.cloud.techlearn.hospitalmanagement.model.common.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "patientId_generator")
	@SequenceGenerator(name = "patientId_generator",sequenceName = "patient_sequence",allocationSize = 50)
	private int pId;
	
	@Column(name = "first_name")
	private String fName;
	
	@Column(name = "last_name")
	private String lName;
	
	@Column(name = "phone")
	private String phNo;
	
	@Column(name = "Gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private String disease;
	
	@Column(name = "provider_name")
	private String providerName;
	
	private Double copay;
	
	private boolean status;
}
