package com.ecordi.alkemy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecordi.alkemy.entities.Film;
import com.ecordi.alkemy.entities.User;
import com.ecordi.alkemy.repositories.RoleRepository;
import com.ecordi.alkemy.serviceImplements.CharacterImplementation;
import com.ecordi.alkemy.serviceImplements.FilmImplementation;
import com.ecordi.alkemy.serviceImplements.GenderImplementation;
import com.ecordi.alkemy.services.UserService;
import com.ecordi.alkemy.services.userServiceImp;

@Controller
public class controllerbrowser {
	@Autowired
	private FilmImplementation _filmImplementation;
	@Autowired
	private CharacterImplementation _characterImplementation;
	@Autowired
	private GenderImplementation _genderImplementation;
	
	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;
	/**
	 * Entering takes you to the login form then redirects to the messy film list
	 */
	@GetMapping({ "/", ""})
	public String index() {
		return "redirect:/films";
	}

	/**
	 * Generates a list of movies with all their attributes and the status of their
	 * characters
	 */
	
	
	  @GetMapping("/films") public String gilm(Model model) {
	  
	  model.addAttribute("films", _filmImplementation.getFilms());
	  model.addAttribute("characters", _characterImplementation.getAll());
	  model.addAttribute("genders", _genderImplementation.getAll());

	  return "film"; }
	 
	@GetMapping("/gender{id}")
	public String gender(Model model, @RequestParam Long id) {

		model.addAttribute("gender",_genderImplementation.getAll());
		model.addAttribute("genders", _filmImplementation.getFilms());
		

		return "index2";
	}

}
