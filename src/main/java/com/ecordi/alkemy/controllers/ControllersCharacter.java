package com.ecordi.alkemy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecordi.alkemy.entities.Film;
import com.ecordi.alkemy.entities.DTO.CharacterDTO;
import com.ecordi.alkemy.others.exceptions.CustomeFieldValidationException;
import com.ecordi.alkemy.others.exceptions.ObjectNameOrIdNotFound;
import com.ecordi.alkemy.entities.Character;
import com.ecordi.alkemy.services.CharacterService;

@RestController
@RequestMapping(path = "/api/characters")
public class ControllersCharacter {

	@Autowired
	private CharacterService _characterService;

	/**
	 * Search all characters
	 */
	@GetMapping("")
	public List<CharacterDTO> getAll() {
		return _characterService.getAll();
	}

	/**
	 * @param Character character 
	 * Takes a character as a parameter to create it and
	 *                  save it in the database *Create characters*
	 */
	@PostMapping("/create")
	public Character createCharacter(@RequestBody Character character) throws CustomeFieldValidationException{
		return _characterService.createCharacter(character);
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
	 * Update character
	 * 
	 * @param Character character
	 * @param Long      id
	 * @throws ObjectNameOrIdNotFound
	 */
	@PutMapping("/update{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Character updateCharacter(@RequestBody Character character, @PathVariable Long id)
			throws ObjectNameOrIdNotFound {
		try {
			return _characterService.updateCharacter(character, id);
		} catch (Exception e) {
			new ObjectNameOrIdNotFound("Object or Id not found");
			return null;
		}
	}

	/**
	 * @param id
	 * @throws ObjectNameOrIdNotFound
	 * 
	 *                                Delete characters by id
	 */
	@DeleteMapping("/delete{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCharacter(@PathVariable Long id) throws ObjectNameOrIdNotFound {
		_characterService.deleteCharacter(id);
	}

	/**
	 * Search characters by id
	 */
	@GetMapping("{id}")
	public Character findById(@PathVariable Long id) {
		return _characterService.findById(id);
	}

	/**
	 * Search characters by name
	 */
	@GetMapping("{name}")
	public ResponseEntity<?> findByName(@RequestParam String name) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(_characterService.findByName(name));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
		}
	}

	/**
	 * Search by age
	 */
	@GetMapping("{edad}")
	public ResponseEntity<?> findByAge(@RequestParam int age) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(_characterService.findByAge(age));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
		}
	}

	/**
	 * Search characters by film
	 */
	@GetMapping("/film{id}")
	public ResponseEntity<?> findByFilm(@RequestParam Long idFilm) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(_characterService.findByFilm(idFilm));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
		}
	}

}