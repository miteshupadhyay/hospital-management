package com.mitesh.cloud.techlearn.hospitalmanagement.util;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.mitesh.cloud.techlearn.hospitalmanagement.constant.AppConstant;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Patient;
import com.mitesh.cloud.techlearn.hospitalmanagement.exception.AWSException;

@Component
public class PatientUtility {
	
	@Value("${filepath}")
	private String path;
	
	public void uploadToS3(Patient savedPatient) throws IOException 
	{
		LocalDateTime current = LocalDateTime.now();
		Regions region=Regions.AP_SOUTH_1;
		String bucketName=AppConstant.BUCKET_NAME;
		String fileObjKeyName = AppConstant.HSPTL_MGMT+current;
		String fileName = path;
		
		try {
			AWSCredentials awsCredentials=new ProfileCredentialsProvider("default").getCredentials();
		}catch (AmazonClientException e) {
			throw new AWSException(AppConstant.AWS_EXCEPTION);
		}
		
		String fileContent= "Patient_Id | "+savedPatient.getPId()+" | "+"\n"+
	 						"Patient_First_Name | "+savedPatient.getFName()+" | "+"\n"+
							"Patient_Last_Name | "+savedPatient.getLName()+" | "+"\n"+
							"Patient_Phone_No | "+savedPatient.getPhNo()+" | "+"\n"+
							"Patient_Gender | "+savedPatient.getGender()+" | "+"\n"+
							"Patient_Dicease | "+savedPatient.getDisease()+" | "+"\n"+
							"Patient_Provider_Name | "+savedPatient.getProviderName()+" | "+"\n"+
							"Patient_Copay | "+savedPatient.getCopay()+" | "+"\n";
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
	    writer.write(fileContent);
	    writer.close();
	    AmazonS3 s3Client=AmazonS3ClientBuilder.standard().withRegion(region).build();
	    PutObjectRequest putObjectRequest=new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
		
		ObjectMetadata objectMetadata=new ObjectMetadata();
			objectMetadata.setContentType("plain/text");
			objectMetadata.addUserMetadata("x-amz-meta-title", "First Object of mine");
			
			putObjectRequest.setMetadata(objectMetadata);
			s3Client.putObject(putObjectRequest);
	}

}
