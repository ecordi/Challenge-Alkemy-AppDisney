package com.ecordi.alkemy.serviceImplements;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.apache.catalina.valves.rewrite.Substitution.MapElement;
import org.modelmapper.ModelMapper;
import com.ecordi.alkemy.entities.Character;
import com.ecordi.alkemy.entities.Film;
import com.ecordi.alkemy.entities.Gender;
import com.ecordi.alkemy.entities.DTO.FilmDTO;
import com.ecordi.alkemy.others.enums.ShortedOrNot;
import com.ecordi.alkemy.others.exceptions.CustomeFieldValidationException;
import com.ecordi.alkemy.others.exceptions.ObjectNameOrIdNotFound;
import com.ecordi.alkemy.repositories.FilmRepository;
import com.ecordi.alkemy.repositories.GenderRepository;
import com.ecordi.alkemy.services.FilmService;

@Service
public class FilmImplementation implements FilmService {

	@Autowired
	private final FilmRepository _filmRepository;
	@Autowired(required = true)
	private final GenderRepository _genderRepository;


	@Autowired
	public FilmImplementation(FilmRepository filmRepository, GenderRepository genderRepository) {
		this._filmRepository = filmRepository;
		this._genderRepository = genderRepository;
	}

	/**
	 * @return all films movies is used in reference to films (movies or series)
	 */

	public List<FilmDTO> getFilms() {
		List<Film> films = _filmRepository.findAll();
		List<FilmDTO> filmDTO = new ArrayList<FilmDTO>();
		for (Film f : films) {
			filmDTO.add(new FilmDTO(f.getId(), f.getTitle(), f.getImage(), f.getCreation_date(),f.getQualification()));
		}
		return filmDTO;
	}

	/**
	 * @param String name
	 * @return returns the films that match the id passed by parameter
	 */
	public Film getFilmDetail(Long id) {
		Film film = _filmRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Film with id: " + id + ", dont exist in system."));
		return film;
	}

	/**
	 * @param character
	 * @return True if the name is available or false if not
	 */
	private boolean checkFilmNameAvailable(Film film) throws Exception {
		Optional<Film> filmFound = _filmRepository.findById(film.getId());
		if (filmFound.isPresent()&& filmFound.equals(film.getTitle())) {
			throw new CustomeFieldValidationException("The Film already exists","name"+film.getTitle() );
		}
		return true;
	}

	/**
	 * This method creates an object of type Film
	 * 
	 * @param film
	 */
	@Override
	public void createFilm(Film film) {
		Film toFilm= null;
		try {
			if (!film.equals(null) && checkFilmNameAvailable(film)) {
				toFilm = _filmRepository.getById(film.getId());
				mapCharacter(toFilm);
				_filmRepository.save(film);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param character
	 * @param characterUpdate
	 * @return characterUpdate This method receives a character and a character to
	 *         update, returns the updated one
	 */
	public Film mapCharacter(Film film) {
		Film updatefilm = null;
		updatefilm.setImage(film.getImage());
		updatefilm.setTitle(film.getTitle());
		updatefilm.setCreation_date(film.getCreation_date());
		updatefilm.setQualification(film.getQualification());
		updatefilm.setGender(film.getGender());
		updatefilm.setAssociated_characters(film.getAssociated_characters());
		return _filmRepository.saveAndFlush(updatefilm);
	}

	/**
	 * @param Long id
	 * @param Film film This method takes a long id and movie and updates a movie
	 *             that matches the id.
	 */
	@Transactional
	public void updateFilm(Film film, Long id) {
		Film filmToUpdate = _filmRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Film with id: " + id + ", dont exists in system."));
		if (film.getQualification() > 5 || film.getQualification() < 0) {
			throw new IllegalStateException("Film qualification must be between 0 and 5.");
		}
		filmToUpdate.setTitle(film.getTitle());
		filmToUpdate.setImage(film.getImage());
		filmToUpdate.setCreation_date(film.getCreation_date());
		filmToUpdate.setGender(film.getGender());
		filmToUpdate.setQualification(film.getQualification());
		filmToUpdate.setAssociated_characters(film.getAssociated_characters());
	}

	/**
	 * @param Long id This method takes an id and removes the movies that match that
	 *             id
	 */
	public void deleteFilm(Long id) {
		Optional<Film> film = _filmRepository.findById(id);
		if (film.isEmpty()) {
			throw new IllegalStateException("Film with id: " + id + ", dont exists in system.");
		}
		_filmRepository.deleteRelation(id);
		_filmRepository.delete(film.get());
	}

	/**
	 * 
	 * @param id
	 * @param genderId
	 */
	@Transactional
	public void setGender(Long id, Long genderId) {
		Film film = _filmRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("film with id: " + id + ", dont exists in system."));
		Gender gender = _genderRepository.findById(genderId)
				.orElseThrow(() -> new IllegalStateException("Genre with id: " + id + ", dont exists in system."));
		film.setGender(gender);
	}

	/**
	 * @param String Title This method takes a String title as a parameter and looks
	 *               for matches in the list of movies
	 */
	@Override
	public List<Film> findByTitle(String title) {
		List<Film> toSearch = _filmRepository.findAll();
		ArrayList<Film> found = new ArrayList<Film>();
		for (Film film : toSearch) {
			if (film.getTitle().equals(title)) {
				found.add(film);
			}
		}
		return found;
	}

	/**
	 * @param Gender gender This method takes a Gender gender as a parameter and
	 *               looks for matches in the list of movies
	 */
	@Override
	public List<Film> findByGender(Long id) {

		List<Film> toSearch = _filmRepository.findAll();
		List<Film> found = new ArrayList<Film>();
		for (Film f : toSearch) {
			f.getGender().getId().equals(id);
			found.add(f);
		}
		return (List<Film>) found;
	}

	/**
	 * @param Date date This method takes a Date date as a parameter and looks for
	 *             matches in the list of movies
	 */
	@Override
	public List<Film> findByDate(Date date) {
		List<Film> toSearch = _filmRepository.findAll();
		ArrayList<Film> found = new ArrayList<Film>();
		for (Film film : toSearch) {
			if (film.getCreation_date().equals(date))
				found.add(film);
		}
		return found;
	}

	/**
	 * @param Date date This method takes a Date date as a parameter and looks for
	 *             matches in the list of movies
	 */
	@Override
	public List<Film> findByCharacters(Character character) {

		List<Film> toSearch = _filmRepository.findAll();
		ArrayList<Film> found = new ArrayList<Film>();

		for (Film film : toSearch) {
			for (Character x : film.getAssociated_characters()) {
				if (x.getId().equals(character.getId()))
					found.add(film);
			}
		}
		return found;
	}

	/**
	 * @param Date date This method takes a Date date as a parameter and looks for
	 *             matches in the list of movies
	 */
	@Override
	public List<Film> getByOrder(String tipe) {
		if (tipe.equals(ShortedOrNot.ASC)) {
			return (ArrayList<Film>) _filmRepository.getAllByOrderASC();
		} else if (tipe.equals(ShortedOrNot.DESC)) {
			return (ArrayList<Film>) _filmRepository.getAllByOrderDESC();
		} else {
			return (ArrayList<Film>) _filmRepository.findAll();
		}

	}

}
