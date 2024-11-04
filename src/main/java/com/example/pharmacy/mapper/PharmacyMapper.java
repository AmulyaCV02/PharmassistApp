package com.example.pharmacy.mapper;

import org.springframework.stereotype.Component;


import com.example.pharmacy.entity.Pharmacy;
import com.example.pharmacy.requestdtos.PharmacyRequest;

import com.example.pharmacy.responsedtos.PharmacyResponse;

@Component
public class PharmacyMapper {

	public Pharmacy mapToPharmacy(PharmacyRequest pharmacyRequest ,Pharmacy pharmacy ) {
		pharmacy.setName(pharmacyRequest.getName());
		pharmacy.setGstNo(pharmacyRequest.getGstNo());
		pharmacy.setLicenseNo(pharmacyRequest.getLicenseNo());
		return pharmacy;

	}
	public PharmacyResponse mapToPharmacyResponse(Pharmacy pharmacy)
	{
		PharmacyResponse pharmacyResponse = new PharmacyResponse();
		pharmacyResponse.setPharmacyId(pharmacy.getPharmacyId());
		pharmacyResponse.setName(pharmacy.getName());
		pharmacyResponse.setGstNo(pharmacy.getGstNo());
		pharmacyResponse.setLicenseNo(pharmacy.getLicenseNo());

		return pharmacyResponse;

	}

}
