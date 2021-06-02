package com.ecordi.alkemy.serviceImplements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecordi.alkemy.entities.Character;
import com.ecordi.alkemy.entities.Film;
import com.ecordi.alkemy.entities.Gender;
import com.ecordi.alkemy.entities.DTO.FilmDTO;
import com.ecordi.alkemy.entities.DTO.GenderDTO;
import com.ecordi.alkemy.others.exceptions.ObjectNameOrIdNotFound;
import com.ecordi.alkemy.repositories.FilmRepository;
import com.ecordi.alkemy.repositories.GenderRepository;
import com.ecordi.alkemy.services.GenderService;

@Service
public class GenderImplementation implements GenderService {
	@Autowired
	private GenderRepository _genderRepository;
	@Autowired
	private FilmRepository _filmRepository;

	/**
	 * 
	 */
	@Override
	public List<GenderDTO> getAll() {
		List<Gender> gender = _genderRepository.findAll();
		List<GenderDTO> gendersDTO = new ArrayList<GenderDTO>();
		for (Gender g : gender) {
			gendersDTO.add(new GenderDTO(g.getId(), g.getName(), g.getImage()));
		}
		return gendersDTO;
	}

	/**
	 * 
	 */
	@Override
	public Gender createGender(Gender gender) {
		return _genderRepository.save(gender);
	}

	/**
	 * 
	 */

	public Gender mapGender(Gender from, Gender to) {
		to.setId(from.getId());
		to.setImage(from.getImage());
		to.setName(from.getName());
		to.setAssociated_films(from.getAssociated_films());
		return to;
	}

	/**
	 * 
	 */
	@Override
	public Gender updateGender(Gender from, Long id) {
		Gender toUpdate = new Gender();
		mapGender(from, toUpdate);

		return _genderRepository.saveAndFlush(toUpdate);
	}

	@Override
	public boolean deleteGender(Long id) throws ObjectNameOrIdNotFound {
		try {
			_genderRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	/**
	 * 
	 */

	@Override
	public Gender findById(Long id) {
		return _genderRepository.getById(id);
	}

	/**
	 * 
	 */
	@Override
	public List<Gender> findByName(String name) {
		List<Gender> toSearch = _genderRepository.findAll();
		ArrayList<Gender> found = new ArrayList<Gender>();
		for (Gender gender : toSearch) {
			if (gender.getName().equals(name)) {
				found.add(gender);
			}
		}
		return found;
	}

	/**
	 * 
	 */
	@Override
	public Gender findByCharacter(Long idCharacter) {
		List<Film> toSearch = _filmRepository.findAll();
		Gender found = null;

		for (Film film : toSearch) {
			for (Character x : film.getAssociated_characters()) {
				if (x.getId().equals(idCharacter))
					found = film.getGender();
			}
		}
		return found;
	}

	/**
	 * 
	 */
	@Override
	public Gender findByFilm(Long idFilm) {
		List<Film> search = _filmRepository.findAll();
		Gender found = null;

		for (Film film : search) {
			if (film.getId().equals(idFilm)) {
				found = film.getGender();
			}
		}

		return found;
	}

}