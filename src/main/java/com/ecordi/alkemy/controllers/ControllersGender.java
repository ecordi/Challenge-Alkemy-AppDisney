package com.ecordi.alkemy.controllers;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecordi.alkemy.entities.Film;
import com.ecordi.alkemy.entities.Gender;
import com.ecordi.alkemy.entities.DTO.GenderDTO;
import com.ecordi.alkemy.others.exceptions.ObjectNameOrIdNotFound;
import com.ecordi.alkemy.services.GenderService;

@RestController
@RequestMapping(path = "/api")
public class ControllersGender {
	@Autowired
	private GenderService _genderService;

	/**
	 * @param Character character Takes a character as a parameter to create it and
	 *                  save it in the database *Create characters
	 */
	@GetMapping(path = "/create")
	public Gender createGender(@RequestBody Gender gender) {
		return _genderService.createGender(gender);
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

	@ResponseBody
	@GetMapping(value = "/hello2{id}")
	public Gender index(@RequestParam(value = "id", required = true) Long id, HttpServletRequest request) {

		return _genderService.findById(id);
	}

	/**
	 * Update gender
	 * 
	 * @param gender gender
	 * @param Long   id
	 * @throws ObjectNameOrIdNotFound
	 */
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Gender updateGender(@RequestBody Gender gender, @PathVariable Long id) throws ObjectNameOrIdNotFound {
		try {
			return _genderService.updateGender(gender, id);
		} catch (Exception e) {
			new ObjectNameOrIdNotFound("Object or Id not found");
			return null;
		}

	}

	/**
	 * @param id
	 * @throws ObjectNameOrIdNotFound Delete Genders by id
	 */

	@DeleteMapping("/delete{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteGender(@PathVariable Long id) throws ObjectNameOrIdNotFound {
		_genderService.deleteGender(id);
	}

	/**
	 * Search all Genders
	 */
	@GetMapping("/genders")
	public List<GenderDTO> getAll() {
		return _genderService.getAll();
	}

	/**
	 * Search Genders by id
	 */
	@GetMapping("{id}")
	public ResponseEntity<Gender> findById(@RequestParam Long id) {
		if (id > 0) {
			Gender result = _genderService.findById(id);
			;
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	/**
	 * Search Genders by name
	 */
	@GetMapping("/gender/j")
	public ResponseEntity<?> findByName(@RequestParam String name) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(_genderService.findByName(name));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
		}
	}

	/**
	 * Search Gender by film
	 */
	@GetMapping("/films/gender")
	public ResponseEntity<?> findByFilm(@RequestParam Long idFilm) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(_genderService.findByFilm(idFilm));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
		}
	}

}