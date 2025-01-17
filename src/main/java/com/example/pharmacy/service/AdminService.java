package com.example.pharmacy.service;

import org.springframework.stereotype.Service;

import com.example.pharmacy.adminrequest.AdminRequest;
import com.example.pharmacy.entity.Admin;
import com.example.pharmacy.mapper.AdminMapper;
import com.example.pharmacy.repository.AdminRepository;
import com.example.pharmacy.responsedtos.AdminResponse;
import com.example.pharmacy.util.AppResponseBuilder;




@Service
public class AdminService {
	private final AdminRepository adminRepository;
	private final AdminMapper adminMapper;

	public AdminService(AdminRepository adminRepository,AppResponseBuilder appResponseBuilder,AdminMapper adminMapper)
	{
		this.adminRepository=adminRepository;
		this.adminMapper=adminMapper;
	}

	public AdminResponse addAdmin(AdminRequest adminRequest)
	{
		Admin admin=adminRepository.save(adminMapper.mapToAdmin(adminRequest, new Admin()));
		return adminMapper.mapToAdminResponse(admin);
	}
	
	


}
