package com.ecordi.alkemy.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
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

/*Creation of metods Getters and setters with lombok*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
/****/

@Data
@Entity
@Table(name = "Film")

public class Film implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6348587719835971578L;

	@Id
	@SequenceGenerator(name = "film_sequence", sequenceName = "film_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_sequence")
	private Integer id;

	@Column(name = "image", nullable = true)
	@Lob()
	private String image;

	@Column(name = "title")
	@NotEmpty
	private String title;

	@NotNull
	@Min(1600)
	@Max(2021)
	@Column(name = "creation_date")
	private int creation_date;

	@Column(name = "qualification")

	@NotNull
	@Min(1)
	@Max(5)
	private int qualification;

	@ManyToMany(mappedBy = "associated_films")
	private Set<Character> associated_characters = new HashSet<>();
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="gender_id")
	private Gender gender;
}