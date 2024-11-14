package com.example.pharmacy.responsedtos;

import java.time.LocalDate;

import enums.Form;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


public class MedicineResponse {
	private String medicineID;
	private String name;
	private String category;
	private String ingredients;
	private int dosageInMg;
	@Enumerated(EnumType.STRING)
	private Form form;
	private String manufacturer;
	private int stockQuantity;
    private LocalDate expiryDate;
	private double price;
	

	public String getMedicineID() {
		return medicineID;
	}
	public void setMedicineID(String string) {
		this.medicineID = string;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public int getDosageInMg() {
		return dosageInMg;
	}
	public void setDosageInMg(int dosageInMg) {
		this.dosageInMg = dosageInMg;
	}
	public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	
	
	
}
