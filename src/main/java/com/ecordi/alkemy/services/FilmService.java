package com.ecordi.alkemy.services;

import com.ecordi.alkemy.entities.Gender;
import com.ecordi.alkemy.entities.DTO.FilmDTO;
import com.ecordi.alkemy.entities.Character;
import com.ecordi.alkemy.others.exceptions.ObjectNameOrIdNotFound;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecordi.alkemy.entities.Film;


@Service
public interface FilmService {

	public List<FilmDTO> getFilms();

	public Film getFilmDetail(Long id);

	public void createFilm(Film film);

	public void updateFilm(Film film,Long id);

	public void deleteFilm(Long id);

	public List<Film> findByTitle(String title);

	public List<Film> findByGender(Long id);

	public List<Film> findByDate(Date date);

	public List<Film> findByCharacters(Character character);

	public List<Film> getByOrder(String tipe);

}