package com.ecordi.alkemy.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.ecordi.alkemy.entities.Film;
import com.ecordi.alkemy.entities.DTO.CharacterDTO;
import com.ecordi.alkemy.others.exceptions.CustomeFieldValidationException;
import com.ecordi.alkemy.others.exceptions.ObjectNameOrIdNotFound;
import com.ecordi.alkemy.entities.Character;

@Service
public interface CharacterService {

	public List<CharacterDTO> getAll();

	public Character createCharacter(Character character) throws CustomeFieldValidationException;

	public Character updateCharacter(Character character, Long id) throws ObjectNameOrIdNotFound;

	public boolean deleteCharacter(Long id) throws ObjectNameOrIdNotFound;

	public Character findById(Long id);

	public List<Character> findByName(String name) throws ObjectNameOrIdNotFound;;

	public List<Character> findByAge(Integer age);

	public List<Character> findByFilm(Long idFilm) throws ObjectNameOrIdNotFound;;
}
