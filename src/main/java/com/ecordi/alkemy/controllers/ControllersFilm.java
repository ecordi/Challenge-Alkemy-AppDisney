package com.ecordi.alkemy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecordi.alkemy.entities.Film;
import com.ecordi.alkemy.entities.Gender;
import com.ecordi.alkemy.entities.DTO.FilmDTO;
import com.ecordi.alkemy.others.exceptions.ObjectNameOrIdNotFound;
import com.ecordi.alkemy.repositories.FilmRepository;
import com.ecordi.alkemy.serviceImplements.FilmImplementation;

import jdk.jfr.Timespan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping("/api/movies")

public class ControllersFilm {

	@Autowired
	private FilmImplementation _filmImplementation;

	/**
	 * @return all films movies is used in reference to films (movies or series)
	 */

	@GetMapping
	public ResponseEntity<?> findbyAll() {
		try {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(_filmImplementation.getFilms());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error");
		}
	}

	/**
	 * @param String name
	 * @return returns the films that match the id passed by parameter
	 */
	@GetMapping("{id}")
	public ResponseEntity<?> getMovieDetail(@RequestParam("id") Long id) {
		try {
			return new ResponseEntity<>(_filmImplementation.getFilmDetail(id), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
		}
	}

	/**
	 * This method creates an object of type Film
	 * 
	 * @param film
	 */
	@PutMapping("/create")
	public void createFilm(@Valid @RequestBody Film film) {
		_filmImplementation.createFilm(film);
	}
	/*
	 * @PutMapping("/create") public ResponseEntity<Film>
	 * create(@RequestParam("imageFile") MultipartFile image, @ModelAttribute Film
	 * film) {
	 * 
	 * try { if (!image.isEmpty()) { Path imagesPath =
	 * Paths.get("src//main//resources//static//images"); String absolutPath =
	 * imagesPath.toFile().getAbsolutePath(); try { byte[] bytes = image.getBytes();
	 * Path route = Paths.get(absolutPath + image.getOriginalFilename());
	 * Files.write(route, bytes); film.setImage(image.getOriginalFilename()); }
	 * catch (IOException e) { e.printStackTrace(); } } return
	 * ResponseEntity.status(HttpStatus.CREATED).body(_filmImplementation.createFilm
	 * (film), HttpStatus.OK); } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error"); } }
	 */

	/**
	 * @param film
	 * @param id
	 * @return film updated It takes as a parameter a film and its id to modify it.
	 *         Saves it in the database and returns it to be displayed
	 */
	@PutMapping(value = "/update{id}")
	public void updateMovie(@PathVariable Long id, @RequestBody Film film) {
		_filmImplementation.updateFilm(film,id);
	}

	/**
	 * Take as a parameter a film and its id to remove it.
	 * 
	 * @param id In case of not finding it, throw the following exception
	 * @throws ObjectNameOrIdNotFound
	 */
	@DeleteMapping(value = "/delete{id}")
	public void deleteMovie(@PathVariable Long id) throws ObjectNameOrIdNotFound {
		try {
			_filmImplementation.deleteFilm(id);

		} catch (Exception e) {
			new ObjectNameOrIdNotFound("File not found");
		}
	}

	/**
	 * @param gender
	 * @return returns the films that match the genre passed by parameter
	 */
	@GetMapping(value = "{idGenero}")
	public ResponseEntity<?> findByGender(@RequestParam Long idGenero) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(_filmImplementation.findByGender(idGenero));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
		}
	}

	/**
	 * @param date
	 * @return returns the films that coincide with the creation date passed by
	 *         parameter
	 */
	@GetMapping(value="{date}")
	public ResponseEntity<?> findByCreationDate(@RequestParam Date date) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(_filmImplementation.findByDate(date));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
		}
	}

	/**
	 * @param title
	 * @return returns the films that match the title passed by parameter
	 */
	/* En los requerimientos se pide buscar por nombre, se asume titulo del film */
	@GetMapping(value="{title}")
	public ResponseEntity<?> findByTitle(@RequestParam String title) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(_filmImplementation.findByTitle(title));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
		}
	}

}
