package com.example.pharmacy.controller;


	import org.springframework.http.HttpStatus;
		import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
		import org.springframework.web.bind.annotation.RequestBody;
		import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacy.requestdtos.AdminRequest;
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
			
			@GetMapping("/admins/{adminId}")
			public ResponseEntity<ResponseStructure<AdminResponse>> findUser(@PathVariable String adminId){
				AdminResponse response = adminService.findAdmin(adminId);
				return appResponseBuilder.success(HttpStatus.FOUND, "user is found",response);
			}
		}



