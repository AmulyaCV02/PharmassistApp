package com.example.pharmacy.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.pharmacy.entity.Medicine;
import com.example.pharmacy.entity.Pharmacy;
import com.example.pharmacy.exception.InvalidDataException;
import com.example.pharmacy.exception.InvalidDateFormatException;
import com.example.pharmacy.exception.NoMedicinesFoundException;
import com.example.pharmacy.exception.NoPharmacyFoundException;
import com.example.pharmacy.exception.PharmacyNotFoundByIdException;
import com.example.pharmacy.mapper.MedicineMapper;
import com.example.pharmacy.repository.MedicineRepository;
import com.example.pharmacy.repository.PharmacyRepository;
import com.example.pharmacy.responsedtos.MedicineResponse;

import enums.Form;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class MedicineService {
	private final MedicineRepository medicineRepository;
	private final PharmacyRepository pharmacyRepository ;
	private final MedicineMapper medicineMapper;

       public MedicineService(MedicineRepository medicineRepository, PharmacyRepository pharmacyRepository,
			MedicineMapper medicineMapper) {
		super();
		this.medicineRepository = medicineRepository;
		this.pharmacyRepository = pharmacyRepository;
		this.medicineMapper = medicineMapper;
	}
	@Transactional
	public String uploadMedicines(MultipartFile file,String pharmacyId) {

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

			Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
					.orElseThrow(()-> new NoPharmacyFoundException  ("Failed to find pharmacy with Id"+pharmacyId));

		
			for(Sheet sheet:workbook) {
				for(Row row :sheet) {
					if(row.getRowNum() !=0) {

						Medicine medicine = new Medicine();

						medicine.setName(row.getCell(0).getStringCellValue());
						medicine.setCategory(row.getCell(1).getStringCellValue());
						medicine.setDosageInMg((int) row.getCell(2).getNumericCellValue());
						medicine.setForm(Form.valueOf(row.getCell(3).getStringCellValue().toUpperCase()));
						medicine.setIngredients(row.getCell(4).getStringCellValue());
						medicine.setManufacturer(row.getCell(5).getStringCellValue());
						medicine.setPrice(row.getCell(6).getNumericCellValue());

						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						try {
							LocalDate expiryDate = LocalDate.parse(row.getCell(7).getStringCellValue(), formatter);
							medicine.setExpiryDate(expiryDate);
						} catch (DateTimeParseException e) {
							throw new InvalidDateFormatException("Invalid date format in row " + row.getRowNum() +
									": expected format is yyyy-MM-dd");
						}
						

						medicine.setStockQuantity((int) row.getCell(8).getNumericCellValue());

						medicine.setPharmacy(pharmacy); // set the Pharmacy inn medicine

						medicineRepository.save(medicine);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Medicines Added";
	}


	public Medicine getMedicine(Row row) 
	{
		Medicine medicine = new Medicine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try
		{
			medicine.setName(row.getCell(0).getStringCellValue());
			medicine.setCategory(row.getCell(1).getStringCellValue());
			medicine.setDosageInMg((int) row.getCell(2).getNumericCellValue());
			medicine.setForm(Form.valueOf(row.getCell(3).getStringCellValue().toUpperCase()));
			medicine.setIngredients(row.getCell(4).getStringCellValue());
			medicine.setManufacturer(row.getCell(5).getStringCellValue());
			medicine.setPrice((double)row.getCell(6).getNumericCellValue());
			medicine.setExpiryDate(LocalDate.parse(row.getCell(7).getStringCellValue(), formatter));
			medicine.setStockQuantity((int) row.getCell(8).getNumericCellValue());
		}
		catch(NumberFormatException e) 
		{
			throw new InvalidDataException("Data is in invalid format in row "+row.getRowNum()+"in cell");
		}
		catch(IllegalStateException e) 
		{
			throw new InvalidDataException("Data is in invalid format in row "+row.getRowNum());
		}
		catch(DateTimeParseException ex) 
		{
			throw new InvalidDateFormatException("Invalid date format in row "+row.getRowNum());
		}	
		return medicine;
	}

	public void saveMedicine(@Valid Medicine medicine) 
	{
		medicineRepository.save(medicine);
	}
	
	
		public List<MedicineResponse> findMedicine(String text)
		{
			text="%"+text+"%";
			List<Medicine> medicines=medicineRepository.findByNameLikeIgnoreCaseOrIngredientsLikeIgnoreCase(text,text);

			if(medicines.isEmpty())
			{
				throw new NoMedicinesFoundException("Failed to find medicines based on name or ingredients");
			}
			return medicines.stream()
					.map(medicineMapper::mapToMedicineResponse)
					.collect(Collectors.toList());
		}
	
	
}
	
// find medicine eithor mathces with name or dosage. if one is matches then return

