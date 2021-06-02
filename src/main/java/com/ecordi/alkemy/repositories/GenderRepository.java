package com.ecordi.alkemy.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecordi.alkemy.entities.Gender;
@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {
	 
    @Query(value = "SELECT id FROM genders WHERE id = :genderId ",nativeQuery = true)
    public ArrayList<Long> getFilmsIdByGender(@Param("genderId") Long genreId);    
    
	@Modifying
	@Transactional
	@Query(value = "SELECT * FROM genders", nativeQuery = true)
	public ArrayList<Object[]> getAll();

	@Modifying
	@Transactional
	@Query(value = "DELETE from  genders_associated_films  where gender_id=?", nativeQuery = true)
	void deleteRelation(Long film_id);
}
