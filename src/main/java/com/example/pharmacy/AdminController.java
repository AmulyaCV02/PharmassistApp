package com.example.pharmacy;
import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacy.adminrequest.AdminRequest;
import com.example.pharmacy.responsedtos.AdminResponse;
import com.example.pharmacy.service.AdminService;
import com.example.pharmacy.util.AppResponseBuilder;
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
		
		@PostMapping("/admins")
		public ResponseEntity<ResponseStructure<AdminResponse>> addAdmin(@RequestBody @Valid AdminRequest adminRequest)
		{
			AdminResponse response=adminService.addAdmin(adminRequest);
			return appResponseBuilder.success(HttpStatus.CREATED,"Admin Created", response);
		}
	}


