package com.example.pharmacy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.pharmacy.requestdtos.MedicineRequest;
import com.example.pharmacy.requestdtos.PatientRequest;
import com.example.pharmacy.responsedtos.MedicineResponse;
import com.example.pharmacy.responsedtos.PatientResponse;
import com.example.pharmacy.service.MedicineService;
import com.example.pharmacy.util.AppResponseBuilder;
import com.example.pharmacy.util.ResponseStructure;
import com.example.pharmacy.util.SimpleResponseStructure;

import jakarta.validation.Valid;
@RestController
public class MedicineController {
	private final MedicineService medicineService;
    private final AppResponseBuilder appResponseBuilder;

	public MedicineController(MedicineService medicineService, AppResponseBuilder appResponseBuilder) {
		super();
		this.medicineService = medicineService;
		this.appResponseBuilder = appResponseBuilder;
	}
	@PostMapping("/pharmacies/{pharmacyId}/medicines")
	public ResponseEntity<SimpleResponseStructure> uploadMedicine(@RequestParam("medicine_info") MultipartFile file,@PathVariable String pharmacyId)
	{
		String message=medicineService.uploadMedicines(file,pharmacyId);
		return appResponseBuilder.success(HttpStatus.CREATED,message); 
	}
 
	@GetMapping("/medicines")
	public ResponseEntity<ResponseStructure<List<MedicineResponse>>> findMedicine(@RequestParam String text)
	{
		List<MedicineResponse> responses=medicineService.findMedicine(text);
		return appResponseBuilder.success(HttpStatus.FOUND,"Medicine Found",responses);	
				
	}

	//uploadMedicine (@RequestParam MultiPart file)
	//return type simple response structure
}
