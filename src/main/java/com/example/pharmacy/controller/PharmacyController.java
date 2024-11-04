package com.example.pharmacy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.pharmacy.requestdtos.PharmacyRequest;
import com.example.pharmacy.responsedtos.PharmacyResponse;
import com.example.pharmacy.service.PharmacyService;
import com.example.pharmacy.util.AppResponseBuilder;
import com.example.pharmacy.util.ResponseStructure;

import jakarta.validation.Valid;
@Controller
public class PharmacyController {

	private final PharmacyService pharmacyService;
	private final AppResponseBuilder appResponseBuilder;


	public PharmacyController(PharmacyService pharmacyService, AppResponseBuilder appResponseBuilder) {
		this.pharmacyService = pharmacyService;
		this.appResponseBuilder = appResponseBuilder;
	}

     @PostMapping("/admins/{adminId}/pharmacies")
	public ResponseEntity<ResponseStructure<PharmacyResponse>> addPharmacy(@RequestBody @Valid PharmacyRequest pharmacyRequest,@PathVariable String adminId)
	{
		PharmacyResponse response=pharmacyService.addPharmacy(pharmacyRequest, adminId );
		return appResponseBuilder.success(HttpStatus.CREATED,"Admin Created", response);
	}
     
     @GetMapping("/admins/{adminId}/pharmacies")
 	public ResponseEntity<ResponseStructure<PharmacyResponse>> findPharmacyByAdminId(@PathVariable String adminId)
 	{
 		PharmacyResponse response=pharmacyService.findPharmacyByAdminId(adminId);
 		return appResponseBuilder.success(HttpStatus.FOUND,"Pharmacy associated with admin found",response );
 	}

     @PutMapping("/pharmacies/{pharmacyId}")
 	public ResponseEntity<ResponseStructure<PharmacyResponse>> updatePharmacy(@RequestBody PharmacyRequest pharmacyRequest,@PathVariable String pharmacyId)
 	{
 		PharmacyResponse response=pharmacyService.updatePharmacy(pharmacyRequest, pharmacyId);
 		return appResponseBuilder.success(HttpStatus.OK,"Pharmacy Updated", response);
 	}

}
