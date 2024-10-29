package com.example.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pharmacy.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {

}
