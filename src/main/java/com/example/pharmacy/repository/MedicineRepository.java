package com.example.pharmacy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pharmacy.entity.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, String> {

	List<Medicine> findByNameLikeIgnoreCaseOrIngredientsLikeIgnoreCase(String text, String text2);

}
