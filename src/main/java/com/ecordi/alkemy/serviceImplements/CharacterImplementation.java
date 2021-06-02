package com.ecordi.alkemy.serviceImplements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ecordi.alkemy.entities.Character;
import com.ecordi.alkemy.entities.Film;
import com.ecordi.alkemy.entities.DTO.CharacterDTO;
import com.ecordi.alkemy.entities.DTO.FilmDTO;
import com.ecordi.alkemy.others.exceptions.CustomeFieldValidationException;
import com.ecordi.alkemy.others.exceptions.ObjectNameOrIdNotFound;
import com.ecordi.alkemy.repositories.CharacterRepository;
import com.ecordi.alkemy.services.CharacterService;

@Service
public class CharacterImplementation implements CharacterService {
	@Autowired
	private CharacterRepository _characterRepository;

	/**
	 * @return This metod returns all characters
	 */
	@Override
	public List<CharacterDTO> getAll() {
		List<Character> characters = _characterRepository.findAll();

		List<CharacterDTO> charactersDTO = new ArrayList<CharacterDTO>();
		for (Character c : characters) {
			charactersDTO.add(
					new CharacterDTO(c.getId(), c.getImage(), c.getName(), c.getAge(), c.getWeight(), c.getStory()));
		}
		return charactersDTO;
	}

	/**
	 * @param character
	 * @return True Returns true if the name is available or false if not
	 */
	private boolean checkCharacterNameAvailable(Character character) throws Exception {
		Optional<Character> characterFound = _characterRepository.findById(character.getId());
		if (characterFound.isPresent()) {
			throw new CustomeFieldValidationException("Character no disponible", "id");
		}
		return true;
	}

	/**
	 * @param character This method receives a character and saves it
	 */
	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public Character createCharacter(Character character) throws CustomeFieldValidationException {

		Character toCharacter = null;
		try {
			if (!character.equals(null) && checkCharacterNameAvailable(character)) {
				toCharacter = _characterRepository.getById(character.getId());
				mapCharacter(character, toCharacter);
			}
		} catch (Exception e) {
			Exception ex = new CustomeFieldValidationException("Character not available", "name" + character.getName());
			ex.printStackTrace();
		}
		return _characterRepository.save(toCharacter);
	}

	/**
	 * @param character
	 * @param characterUpdate
	 * @return characterUpdate This method receives a character and a character to
	 *         update, returns the updated one
	 */
	public Character mapCharacter(Character character, Character characterUpdate) {
		characterUpdate.setImage(character.getImage());
		characterUpdate.setName(character.getName());
		characterUpdate.setAge(character.getAge());
		characterUpdate.setWeight(character.getWeight());
		characterUpdate.setStory(character.getStory());
		characterUpdate.setAssociated_films(character.getAssociated_films());
		return _characterRepository.save(characterUpdate);
	}

	/**
	 * This method takes an character parameter and returns an updated character
	 * 
	 * @param character
	 * @return updateCharacter
	 */
	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public Character updateCharacter(Character character, Long id) throws ObjectNameOrIdNotFound {
		Character updateCharacter = _characterRepository.getById(id);
		try {
			if (!character.equals(null) && !character.equals(updateCharacter))
				mapCharacter(character, updateCharacter);
			return updateCharacter;
		} catch (Exception e) {
			new ObjectNameOrIdNotFound("Object not found");
			return null;
		}
	}

	/**
	 * @param id This method takes an id parameter and returns a character that
	 *           matches that
	 */
	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public boolean deleteCharacter(Long id) throws ObjectNameOrIdNotFound {
		try {
			if (!_characterRepository.findById(id).equals(null))
				_characterRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			new ObjectNameOrIdNotFound("Id not found");
			return false;
		}
	}

	/**
	 * @param Id
	 * @return Character This method takes an id parameter and returns a character
	 *         that matches that
	 */
	@Override
	public Character findById(Long id) {
		return _characterRepository.getChractaerById(id);
	}

	/**
	 * @param String name This method takes a String name as a parameter and returns
	 *               the characters that match that name
	 */
	@Override
	public List<Character> findByName(String name) {
		List<Character> toSearch = _characterRepository.findAll();
		ArrayList<Character> found = new ArrayList<Character>();

		for (Character character : toSearch) {
			if (character.getName().equals(name)) {
				found.add(character);
			}
		}
		return found;
	}

	/**
	 * @param Integer age This method takes a Integer age parameter and returns the
	 *                characters that match that age
	 */
	@Override
	public List<Character> findByAge(Integer age) {
		List<Character> toSearch = _characterRepository.findAll();
		ArrayList<Character> found = new ArrayList<Character>();

		for (Character character : toSearch) {
			if (character.getAge() == age) {
				found.add(character);
			}
		}
		return found;
	}

	/**
	 * @param Film film This method takes a Film film as a parameter and returns the
	 *             characters that participate in that film
	 */
	@Override
	public List<Character> findByFilm(Long idFilm) {
		List<Character> toSearch = _characterRepository.findAll();
		ArrayList<Character> found = new ArrayList<Character>();

		for (Character character : toSearch) {
			for (Film x : character.getAssociated_films()) {
				if (x.getId().equals(idFilm))
					found.add(character);
			}
		}
		return found;
	}

}
