package com.ecordi.alkemy.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

import com.ecordi.alkemy.entities.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
	@Query(value = "SELECT * FROM characters", nativeQuery = true)
	public ArrayList<Object[]> getAll();
	
	@Transactional
	@Query(value = "Select * from characteres where id=?", nativeQuery = true)
	public Character getChractaerById(Long id);

}
