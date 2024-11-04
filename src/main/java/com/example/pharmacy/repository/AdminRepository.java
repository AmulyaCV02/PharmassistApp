package com.example.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.pharmacy.entity.Admin;
import com.example.pharmacy.entity.Pharmacy;

public interface AdminRepository extends JpaRepository<Admin, String> {
	@Query("SELECT a.pharmacy FROM Admin a WHERE a.id= :adminId")
	Pharmacy findPharmacyByAdminId(@Param("adminId")String adminId);

}
