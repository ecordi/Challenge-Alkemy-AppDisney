package com.ecordi.alkemy.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "genders")
public class Gender implements Serializable {

	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "gender_sequence", sequenceName = "gender_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gender_sequence")
	private Long id;
	
	@NotEmpty
	@Column(name = "name")
	private String name;
	@JsonIgnoreProperties
	@Column(name = "image")
	private String image;
	@JsonIgnoreProperties
	@OneToMany
	private Set<Film> associated_films = new HashSet<>();
}