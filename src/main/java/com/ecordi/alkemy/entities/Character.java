package com.ecordi.alkemy.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*Creacion de Metodos Getters and setters with lombok*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
/****/

@Data
@Entity
@Table(name = "characters")
public class Character implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2970418123549394228L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "image")
	@Lob()
	private String image;

	@NotEmpty
	@Column(name = "name")	
	private String name;

	@NotNull
	@Min(1)
	@Column(name="age")
	private int age;

	@NotNull
	@Min(1)
	@Column(name="weight")
	private int weight;

	@NotEmpty
	@Column(name="story")
	private String story;

	@ManyToMany
	@JoinTable(name = "actuaciones", 
	joinColumns = @JoinColumn(name = "idCharacter"), 
	inverseJoinColumns = @JoinColumn(name = "idFilm"))
	private Set<Film> associated_films = new HashSet<>();

}