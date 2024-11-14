package com.example.pharmacy.mapper;

import org.springframework.stereotype.Component;

import com.example.pharmacy.entity.Medicine;
import com.example.pharmacy.entity.Patient;
import com.example.pharmacy.requestdtos.MedicineRequest;
import com.example.pharmacy.responsedtos.MedicineResponse;
import com.example.pharmacy.responsedtos.PatientResponse;

@Component
public class MedicineMapper {
	public Medicine mapTomedicine(MedicineRequest medicineRequest,Medicine medicine)
	{
		medicine.setName(medicineRequest.getName());
		medicine.setCategory(medicineRequest.getCategory());
	    medicine.setDosageInMg(medicineRequest.getDosageInMg());
		medicine.setForm(medicineRequest.getForm());
		medicine.setExpiryDate(medicineRequest.getExpiryDate());
		medicine.setIngredients(medicineRequest.getIngredients());
		medicine.setManufacturer(medicineRequest.getManufacturar());
		medicine.setStockQuantity(medicineRequest.getStockQuantity());
		medicine.setPrice(medicineRequest.getPrice());
		return medicine;
	}

	public MedicineResponse mapToMedicineResponse(Medicine medicine)
	{   MedicineResponse response = new MedicineResponse();
	    response.setMedicineID(medicine.getMedicineId());    
		response.setName(medicine.getName());
		response.setCategory(medicine.getCategory());
		response.setIngredients(medicine.getIngredients());
		response.setDosageInMg(medicine.getDosageInMg());
		response.setForm(medicine.getForm());
		response.setManufacturer(medicine.getManufacturer());
		response.setStockQuantity(medicine.getStockQuantity());
		response.setExpiryDate(medicine.getExpiryDate());
		response.setPrice(medicine.getPrice());

		return response;
	}

}
