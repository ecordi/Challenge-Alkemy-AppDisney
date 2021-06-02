package com.ecordi.alkemy.entities.DTO;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FilmDTO {
	private Long id;
	private String title;

	private String image;

	private Date creation_Date;
	private int qualification;

}
