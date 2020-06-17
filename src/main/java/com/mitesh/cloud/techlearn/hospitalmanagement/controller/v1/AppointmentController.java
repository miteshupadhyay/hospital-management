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

import com.mitesh.cloud.techlearn.hospitalmanagement.dto.AppointmentDto;
import com.mitesh.cloud.techlearn.hospitalmanagement.dto.DoctorDto;
import com.mitesh.cloud.techlearn.hospitalmanagement.response.AppointmentResponse;
import com.mitesh.cloud.techlearn.hospitalmanagement.service.AppointmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Validated
@Api(tags = "Appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@ApiOperation(value = "Add a new Appointment", nickname = "New Appointment Registration", response = AppointmentResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Appointment Added Successfully"),
			@ApiResponse(code = 404, message = "Appointment Not Found") 
			})

	@PostMapping(path = "/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppointmentResponse> addAppointmentDetails(@RequestBody AppointmentDto appointmentDto) {
		AppointmentResponse appointmentResponse = appointmentService.addAppointmentDetails(appointmentDto);
		return new ResponseEntity<>(appointmentResponse, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Get the Patient Details",nickname = "Get the Patient Details",response = DoctorDto.class)
	@ApiResponses(value= {			  
			  @ApiResponse(code = 200,message = "Doctor Details Fetched Successfully"),			  
			  @ApiResponse(code = 404,message = "Doctor Not Found") 
			  })
			  
	@GetMapping("/appointments/{appointmentId}") 
	public ResponseEntity<AppointmentResponse> getAppointmentDetails(@PathVariable int appointmentId)
	{ 
		AppointmentResponse appointmentResponse = appointmentService.getAppointmentDetails(appointmentId); 
		return new ResponseEntity<>(appointmentResponse,HttpStatus.OK); 
	}
	
	
}
