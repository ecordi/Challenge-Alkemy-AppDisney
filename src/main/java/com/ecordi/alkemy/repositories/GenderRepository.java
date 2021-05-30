package com.ecordi.alkemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecordi.alkemy.entities.Gender;

public interface GenderRepository extends JpaRepository<Gender, Integer> {

}
