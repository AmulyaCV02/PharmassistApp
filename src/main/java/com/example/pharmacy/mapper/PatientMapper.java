package com.example.pharmacy.mapper;

	

	import org.springframework.stereotype.Component;

import com.example.pharmacy.entity.Patient;
import com.example.pharmacy.requestdtos.PatientRequest;
import com.example.pharmacy.responsedtos.PatientResponse;


	@Component
	public class PatientMapper 
	{
		public Patient mapToPatient(PatientRequest patientRequest,Patient patient)
		{
			patient.setName(patientRequest.getName());
			patient.setPhoneNumber(patientRequest.getPhoneNumber());
			patient.setEmail(patientRequest.getEmail());
			patient.setGender(patientRequest.getGender());
			patient.setDateOfBirth(patientRequest.getDateOfBirth());

			return patient;
		}

		public PatientResponse mapToPatientResponse(Patient patient)
		{
			PatientResponse patientResponse=new PatientResponse();
			patientResponse.setPatientId(patient.getPatientID());
			patientResponse.setName(patient.getName());
			patientResponse.setPhoneNumber(patient.getPhoneNumber());
			patientResponse.setEmail(patient.getEmail());
			patientResponse.setGender(patient.getGender());
			patientResponse.setDateOfBirth(patient.getDateOfBirth());

			return patientResponse;
		}
	}


