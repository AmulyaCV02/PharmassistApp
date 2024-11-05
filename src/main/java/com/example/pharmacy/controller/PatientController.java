package com.example.pharmacy.controller;


	import java.util.List;

	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacy.requestdtos.PatientRequest;
import com.example.pharmacy.responsedtos.PatientResponse;
import com.example.pharmacy.service.PatientService;
import com.example.pharmacy.util.AppResponseBuilder;
import com.example.pharmacy.util.ResponseStructure;

import jakarta.validation.Valid;

	@RestController
	public class PatientController 
	{
		private final PatientService patientService;
		private final AppResponseBuilder appResponseBuilder;

		public PatientController(PatientService patientService,AppResponseBuilder appResponseBuilder)
		{
			this.appResponseBuilder=appResponseBuilder;
			this.patientService=patientService;
		}

		@PostMapping("/pharmacies/{pharmacyId}/patients")
		public ResponseEntity<ResponseStructure<PatientResponse>> addPatient(@RequestBody @Valid PatientRequest patientRequest,@PathVariable String pharmacyId)
		{
			PatientResponse response=patientService.addPatient(patientRequest, pharmacyId);
			return appResponseBuilder.success(HttpStatus.CREATED,"Patient Registered", response);
		}
		

		@GetMapping("/pharmacies/{pharmacyId}/patients")
	
		public ResponseEntity<ResponseStructure<List<PatientResponse>>> findAllPatientsByPharmacyId(@PathVariable String pharmacyId) {
		    List<PatientResponse> response = patientService.findAllPatientByPharmacyId(pharmacyId);
		    return appResponseBuilder.success(HttpStatus.FOUND, "Patients associated with the pharmacy found", response);
		}
	

	}

