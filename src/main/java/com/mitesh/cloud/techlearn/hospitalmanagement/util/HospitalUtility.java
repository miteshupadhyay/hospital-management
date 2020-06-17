
package com.mitesh.cloud.techlearn.hospitalmanagement.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitesh.cloud.techlearn.hospitalmanagement.constant.AppConstant;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Appointment;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Doctor;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Patient;

@Component
public class HospitalUtility {

	@Value("${filepath}")
	private String path;

	LocalDateTime current = LocalDateTime.now();
	Regions region = Regions.AP_SOUTH_1;
	String bucketName = AppConstant.BUCKET_NAME;
	
	

	ObjectMapper objectMapper = new ObjectMapper();

	public void uploadPatientToS3(Patient savedPatient) throws IOException {
		String fileName = path;
		String fileObjKeyName = AppConstant.PATIENT_DTL + current;
		String jsonString = objectMapper.writeValueAsString(savedPatient);

		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(jsonString);
		writer.close();

		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(region).build();
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType("plain/text");
		objectMetadata.addUserMetadata("x-amz-meta-title", "First Object of mine");

		putObjectRequest.setMetadata(objectMetadata);
		s3Client.putObject(putObjectRequest);
	}

	public void uploadDoctorToS3(Doctor savedDoctor) throws IOException {
		String fileName = path;
		String fileObjKeyName = AppConstant.DOCTOR_DTL + current;
		String jsonString = objectMapper.writeValueAsString(savedDoctor);

		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(jsonString);
		writer.close();

		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(region).build();
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType("plain/text");
		objectMetadata.addUserMetadata("x-amz-meta-title", "First Object of mine");

		putObjectRequest.setMetadata(objectMetadata);
		s3Client.putObject(putObjectRequest);
	}

	public void uploadAppointmentToS3(Appointment savedAppointment) throws IOException {
		String fileName = path;
		String fileObjKeyName = AppConstant.APPOINTMENT_DTL + current;
		String jsonString = objectMapper.writeValueAsString(savedAppointment);

		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(jsonString);
		writer.close();

		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(region).build();
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType("plain/text");
		objectMetadata.addUserMetadata("x-amz-meta-title", "First Object of mine");

		putObjectRequest.setMetadata(objectMetadata);
		s3Client.putObject(putObjectRequest);
	}

}
