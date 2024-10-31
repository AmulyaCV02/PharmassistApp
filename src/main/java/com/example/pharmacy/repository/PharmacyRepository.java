package com.example.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pharmacy.entity.Admin;
import com.example.pharmacy.entity.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, String>  {
 
}
