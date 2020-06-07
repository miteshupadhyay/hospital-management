package com.mitesh.cloud.techlearn.hospitalmanagement.constant;

public final class AppConstant {

	private AppConstant() {
		
	}
	
	public static final int STATUS_LENGHT=1;
	public static final int MAX_FNAME = 30;
	public static final int MIN_FNAME = 3;
	public static final int PH_NO_LENGTH=10;
	public static final String BUCKET_NAME="hsptlmgmt";
	public static final String HSPTL_MGMT="HospitalManagement";
	public static final String AWS_EXCEPTION=
	"Cannot load the credentials from the credential profiles file. " +
    "Please make sure that your credentials file is at the correct " +
    "location (/home/username/.aws/credentials), and is in valid format.";
	
}
