package com.example.pharmacy.mapper;



import org.springframework.stereotype.Component;


import com.example.pharmacy.entity.Admin;
import com.example.pharmacy.requestdtos.AdminRequest;
import com.example.pharmacy.responsedtos.AdminResponse;

@Component
public class AdminMapper
{
	public Admin mapToAdmin(AdminRequest adminRequest,Admin admin)
	{
		admin.setEmail(adminRequest.getEmail());
		admin.setPhoneNumber(adminRequest.getPhoneNumber());
		admin.setPassword(adminRequest.getPassword());
		
		return admin;
	}
	
	public AdminResponse mapToAdminResponse(Admin admin)
	{
		AdminResponse adminResponse=new AdminResponse();
		adminResponse.setAdminId(admin.getAdminId());
		adminResponse.setEmail(admin.getEmail());
		adminResponse.setPhoneNumber(admin.getPhoneNumber());
		
		return adminResponse;
	}

}
