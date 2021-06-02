package com.ecordi.alkemy.services;

import com.ecordi.alkemy.entities.Gender;
import com.ecordi.alkemy.entities.DTO.GenderDTO;
import com.ecordi.alkemy.entities.Character;
import com.ecordi.alkemy.entities.Film;
import com.ecordi.alkemy.others.exceptions.ObjectNameOrIdNotFound;


import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface GenderService {

	public List<GenderDTO> getAll();

	public Gender createGender(Gender gender);

	public Gender updateGender(Gender from, Long id);

	public boolean deleteGender(Long id) throws ObjectNameOrIdNotFound;

	public Gender findById(Long id);

	public List<Gender> findByName(String name);

	public Gender findByCharacter(Long idCharacter);

	public Gender findByFilm(Long idFilm);

}

