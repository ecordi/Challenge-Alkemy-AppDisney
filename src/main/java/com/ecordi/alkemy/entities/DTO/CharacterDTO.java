package com.ecordi.alkemy.entities.DTO;



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
public class CharacterDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2970418123549394228L;
	private Long id;
	private String image;
	private String name;
	private int age;
	private float weight;
	private String story;

}
