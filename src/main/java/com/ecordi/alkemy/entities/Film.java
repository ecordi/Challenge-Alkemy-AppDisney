package com.ecordi.alkemy.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "films")

public class Film implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6348587719835971578L;
    
	@Id
	@SequenceGenerator(name = "film_sequence", sequenceName = "film_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_sequence")
	@JsonIgnoreProperties
	private Long id;
   
    
	@Column(name = "image", nullable = true)
	@Lob()
	@JsonIgnoreProperties
	private String image;
    
	@Column(name = "title")
	@NotEmpty
	@JsonIgnoreProperties
	private String title;
  
	@NotNull
	@Column(name = "creation_date")
	@JsonIgnoreProperties
    @JsonFormat(pattern="dd-MM-yyyy")
	private Date creation_date;
    
	@Column(name = "qualification")
	@NotNull
	@Min(1)
	@Max(5)
    @JsonIgnoreProperties
	private int qualification;
   
	@ManyToMany
	@JoinTable(name = "actuations", 
	joinColumns = @JoinColumn(name = "id_Character"), 
	inverseJoinColumns = @JoinColumn(name = "id_Film"))
    @JsonIgnore
    private Set<Character> associated_characters;
    
    @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_Gender")
    @JsonIgnoreProperties
    private Gender gender;
}