package com.mitesh.cloud.techlearn.hospitalmanagement.controller.v1;

import javax.validation.Valid;

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

import com.mitesh.cloud.techlearn.hospitalmanagement.dto.PatientDto;
import com.mitesh.cloud.techlearn.hospitalmanagement.entity.Patient;
import com.mitesh.cloud.techlearn.hospitalmanagement.response.PatientResponse;
import com.mitesh.cloud.techlearn.hospitalmanagement.service.PatientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Validated
@Api(tags = "Patient")
public class PatientController {

	@Autowired
	private PatientService patientService;

	/*
	 * @ApiOperation(value = "Get All the Patient Details",nickname =
	 * "Get All the Patient Details",response = PatientDto.class)
	 * 
	 * @ApiResponses(value= {
	 * 
	 * @ApiResponse(code = 200,message = "Patient Details Fetched Successfully"),
	 * 
	 * @ApiResponse(code = 404,message = "Patient Not Found") })
	 * 
	 * @GetMapping("/patients") public List<PatientDto> getAllActivePatientDetails()
	 * { return patientService.findAllActivePatients(); }
	 */ 
	  @ApiOperation(value = "Get the Patient Details",nickname = "Get the Patient Details",response = PatientResponse.class)
	  @ApiResponses(value= {	  
			  @ApiResponse(code = 200,message = "Patient Details Fetched Successfully"),
			  @ApiResponse(code = 404,message = "Patient Not Found") 
			  })
	  
	  @GetMapping("/patients/{pId}") 
	  public ResponseEntity<PatientResponse>  getPatientDetails(@PathVariable int pId)
	  {
		  PatientResponse patientResponse =  patientService.getPatientDetails(pId); 
		  return new ResponseEntity<>(patientResponse,HttpStatus.OK); 
	  }

	@ApiOperation(value = "Add a new Patient", nickname = "New Patient Registration", response = PatientResponse.class)

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Patient Added Successfully"),
			@ApiResponse(code = 404, message = "Patient Not Found") 
			})
	
	@PostMapping(path = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PatientResponse> addPatientDetails(@RequestBody @Valid PatientDto patientDto) {
		PatientResponse patientResponse = patientService.addPatientDetails(patientDto);
		return new ResponseEntity<>(patientResponse, HttpStatus.CREATED);
	}

	/*
	 * @DeleteMapping(path = "/patients/{pId}") public void
	 * deletePatientDetails(@PathVariable int pId) {
	 * patientService.deletePatientDetails(pId); }
	 * 
	 * @ApiOperation(value = "Update the Patient Details",nickname =
	 * "Update the Patient Details",response = PatientDto.class)
	 * 
	 * @ApiResponses(value= {
	 * 
	 * @ApiResponse(code = 200,message = "Patient Details Updated Successfully"),
	 * 
	 * @ApiResponse(code = 404,message = "Patient Not Found") })
	 * 
	 * @PutMapping("/patients/{pId}") public ResponseEntity<Patient>
	 * updatePatientDetails(@RequestBody @Valid PatientDto patientDto,@PathVariable
	 * int pId) { Patient updatePatientDetails =
	 * patientService.updatePatientDetails(patientDto,pId); return new
	 * ResponseEntity<>(updatePatientDetails,HttpStatus.OK); }
	 */
}
