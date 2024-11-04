package com.example.pharmacy.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.pharmacy.entity.Admin;
import com.example.pharmacy.entity.Pharmacy;
import com.example.pharmacy.exception.AdminNotFoundByIdException;
import com.example.pharmacy.exception.NoPharmacyFoundException;
import com.example.pharmacy.mapper.PharmacyMapper;
import com.example.pharmacy.repository.AdminRepository;
import com.example.pharmacy.repository.PharmacyRepository;
import com.example.pharmacy.requestdtos.AdminRequest;
import com.example.pharmacy.requestdtos.PharmacyRequest;
import com.example.pharmacy.responsedtos.AdminResponse;
import com.example.pharmacy.responsedtos.PharmacyResponse;
import com.example.pharmacy.util.ResponseStructure;
@Service
public class PharmacyService {
	private final PharmacyRepository pharmacyRepository;
	private final PharmacyMapper pharmacyMapper;
	private final AdminRepository adminRepository; 
	
	

	public PharmacyService(PharmacyRepository pharmacyRepository, PharmacyMapper pharmacyMapper,
			AdminRepository adminRepository) {
		super();
		this.pharmacyRepository = pharmacyRepository;
		this.pharmacyMapper = pharmacyMapper;
		this.adminRepository = adminRepository;
	}


public PharmacyResponse addPharmacy(PharmacyRequest pharmacyRequest,String adminId) {
	
	 return  adminRepository.findById(adminId)
			  .map(admin -> { Pharmacy pharmacy = pharmacyMapper.mapToPharmacy(pharmacyRequest, new Pharmacy());
			   pharmacy = pharmacyRepository.save(pharmacy);
			   admin.setPharmacy(pharmacy);
			   adminRepository.save(admin);
			   

		        return pharmacyMapper.mapToPharmacyResponse(pharmacy);
				  
			  })
			  
			  .orElseThrow(() -> new AdminNotFoundByIdException("Admin with ID \" + pharmacyRequest.getAdminId() + \" not found."));
}



public PharmacyResponse findPharmacyByAdminId(String adminId){
	Admin admin=adminRepository.findById(adminId)
			.orElseThrow(() -> new AdminNotFoundByIdException("Failed to Find Admin"));

	Pharmacy pharmacy=adminRepository.findPharmacyByAdminId(adminId);
	if(pharmacy==null)
	{
		throw new NoPharmacyFoundException("No Pharmacy associated with admin ID:"+adminId);
	}
	return pharmacyMapper.mapToPharmacyResponse(pharmacy);

}
	
	
	
}
