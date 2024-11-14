package com.example.pharmacy.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacy.requestdtos.AdminRequest;
import com.example.pharmacy.responsedtos.AdminResponse;
import com.example.pharmacy.service.AdminService;
import com.example.pharmacy.util.AppResponseBuilder;
import com.example.pharmacy.util.ErrorStructure;
import com.example.pharmacy.util.ResponseStructure;

import jakarta.validation.Valid;


//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class AdminController 
{

	private final AdminService adminService;
	private final AppResponseBuilder appResponseBuilder;

	public AdminController(AdminService adminService,AppResponseBuilder appResponseBuilder)
	{
		this.adminService=adminService;
		this.appResponseBuilder=appResponseBuilder;
	}

	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<AdminResponse>> addAdmin(@RequestBody @Valid AdminRequest adminRequest)
	{
		AdminResponse response=adminService.addAdmin(adminRequest);
		return appResponseBuilder.success(HttpStatus.CREATED,"Admin Created", response);
	}

	@GetMapping("/admins/{adminId}")
	public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(@PathVariable String adminId)
	{
		AdminResponse adminResponse=adminService.findAdmin(adminId);
		return appResponseBuilder.success(HttpStatus.FOUND,"Admin is found",adminResponse);
	}

	@PutMapping("/admins/{adminId}")
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@RequestBody AdminRequest adminRequest,@PathVariable String adminId)
	{
		AdminResponse adminResponse= adminService.updateAdmin(adminRequest, adminId);
		return appResponseBuilder.success(HttpStatus.OK,"Admin Updated", adminResponse);
	}





}



