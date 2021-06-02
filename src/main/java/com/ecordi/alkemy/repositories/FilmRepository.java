package com.ecordi.alkemy.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;

import com.ecordi.alkemy.entities.Film;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Character> {

	public abstract Film getByTitle(String title);

	public abstract Film getById(Integer filmId);

	@Modifying
	@Transactional
	@Query(value = "SELECT * FROM films ORDER BY creation_date ASC", nativeQuery = true)
	public ArrayList<Film> getAllByOrderASC();

	@Modifying
	@Transactional
	@Query(value = "SELECT * FROM films ORDER BY creation_date DESC", nativeQuery = true)
	public ArrayList<Film> getAllByOrderDESC();

	@Modifying
	@Transactional
	@Query(value = "SELECT title,image,creation_date FROM movies", nativeQuery = true)
	public ArrayList<Object[]> getAll();

	@Modifying
	@Transactional
	@Query(value = "DELETE from actuations where id_film=?", nativeQuery = true)
	void deleteRelation(Long film_id);

}
