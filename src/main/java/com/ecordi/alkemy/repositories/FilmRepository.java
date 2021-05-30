package com.ecordi.alkemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecordi.alkemy.entities.Film;

public interface FilmRepository extends JpaRepository<Film, Integer>{

}
