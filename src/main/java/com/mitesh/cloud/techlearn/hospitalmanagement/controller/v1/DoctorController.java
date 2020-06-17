package com.mitesh.cloud.techlearn.hospitalmanagement.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mitesh.cloud.techlearn.hospitalmanagement.dto.DoctorDto;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Patient;
import com.mitesh.cloud.techlearn.hospitalmanagement.response.DoctorResponse;
import com.mitesh.cloud.techlearn.hospitalmanagement.service.DoctorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Validated
@Api(tags = "Doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@ApiOperation(value = "Add a new Doctor", nickname = "New Doctor Registration", response = DoctorDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Doctor Added Successfully"),
			@ApiResponse(code = 404, message = "Doctor Not Found") 
			})

	@PostMapping(path = "/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DoctorResponse> addDoctorsDetails(@RequestBody DoctorDto doctorDto) {
		DoctorResponse doctorResponse = doctorService.addDoctorDetails(doctorDto);
		return new ResponseEntity<>(doctorResponse, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Get the Patient Details",nickname = "Get the Patient Details",response = DoctorDto.class)
	@ApiResponses(value= {			  
			  @ApiResponse(code = 200,message = "Doctor Details Fetched Successfully"),			  
			  @ApiResponse(code = 404,message = "Doctor Not Found") 
			  })
			  
	@GetMapping("/doctors/{doctorId}") 
	public ResponseEntity<DoctorResponse> getDoctorsDetails(@PathVariable int doctorId)
	{ 
		DoctorResponse doctorsResponse = doctorService.getDoctorDetails(doctorId); 
		return new ResponseEntity<>(doctorsResponse,HttpStatus.OK); 
	}
	
	
}
