package com.example.pharmacy.requestdtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PharmacyRequest {
	
	@NotNull(message="Name should not be null")
	@NotBlank(message="Name should not to be blank")
	@Pattern(regexp = "^[A-Z][a-zA-Z-' ]+$",message="Inavlid Name")
	private String name;
	
	@NotNull(message="GST is not to be null")
	@NotBlank(message="GSTis not to be blank")
	@Pattern(regexp = "^([0-9]{2})([A-Z]{5})([0-9]{4})([A-Z])([0-9]{1})([Z][0-9]{1})$",
	message="Inavlid GSTNumber eg :07ABCDE5678Z9")	
	private String gstNo;
	
	
	@NotNull(message="Licensenumber is not to be null")
	@NotBlank(message="License numberis not to be blank")
	@Pattern(regexp = "^\\d{5}/[A-Z]{3}/\\d{4}$",
	message="Inavlid LicenseNumber,  eg:12345/XYZ/2024 ")
	private String licenseNo;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGstNo() {
		return gstNo;
	}
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	
	
}
