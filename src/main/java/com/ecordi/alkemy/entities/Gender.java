package com.ecordi.alkemy.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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
@Table(name = "gender")
public class Gender implements Serializable {

	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "gender_sequence", sequenceName = "gender_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gender_sequence")
	private Long id;

	@Column(name = "name")
	@NotEmpty
	private String name;
	
	@Column(name = "image")
	@Lob()
	private String image;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gender", cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
	private Set<Film> associated_films = new HashSet<>();
}
