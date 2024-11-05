package com.example.pharmacy.service;



	import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.pharmacy.entity.Patient;
import com.example.pharmacy.exception.NoPatientsFoundException;
import com.example.pharmacy.exception.NoPharmacyFoundException;
import com.example.pharmacy.mapper.PatientMapper;
import com.example.pharmacy.repository.PatientRepository;
import com.example.pharmacy.repository.PharmacyRepository;
import com.example.pharmacy.requestdtos.PatientRequest;
import com.example.pharmacy.responsedtos.PatientResponse;
import com.example.pharmacy.util.ResponseStructure;

import jakarta.validation.Valid;

	@Service
	public class PatientService 
	{
		private final PatientRepository patientRepository;
		private final PharmacyRepository pharmacyRepository;
		private final PatientMapper patientMapper;

		public PatientService(PatientRepository patientRepository,PharmacyRepository pharmacyRepository,PatientMapper patientMapper)
		{
			this.patientMapper=patientMapper;
			this.patientRepository=patientRepository;
			this.pharmacyRepository=pharmacyRepository;
		}

		public PatientResponse addPatient(PatientRequest patientRequest,String pharmacyId)
		{
			return pharmacyRepository.findById(pharmacyId)
					.map(pharmacy ->{
						Patient patient=patientMapper.mapToPatient(patientRequest, new Patient());
			 			patient.setPharmacy(pharmacy);
						pharmacy.getPatients().add(patient);
						patientRepository.save(patient);
						return patientMapper.mapToPatientResponse(patient);
					})
					.orElseThrow(() ->new NoPharmacyFoundException("Failed to find Pharmacy "));
		}
		public List<PatientResponse> findAllPatientByPharmacyId(String pharmacyId) {
		    return pharmacyRepository.findById(pharmacyId)
		        .map(pharmacy -> patientRepository.findPatientByPharmacyId(pharmacyId))
		        .filter(patients -> !patients.isEmpty())
		        .orElseThrow(() -> new NoPatientsFoundException("No patients associated with the pharmacyID: " + pharmacyId))
		        .stream()
		        .map(patientMapper::mapToPatientResponse)
		        .collect(Collectors.toList());
		}
	
		
	
					
		}





	

	
