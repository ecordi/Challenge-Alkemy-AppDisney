package com.ecordi.alkemy.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

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

@Entity 
public class User implements Serializable{

	/** 
	 * 
	 */
	private static final long serialVersionUID = -5353369175886471201L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	private Long id;
	
	@Column
	@NotBlank
	@Size(min=5,max=8,message="No se cumple las reglas del tamano")
	private String firstName;
	@Column
	@NotBlank
	private String lastName;
	@Column
	@NotBlank(message="Debe cumplir con el formato user@user.com")
	private String email;
	@Column
	@NotBlank
	private String username;
	@Column
	@NotBlank(message="No puede estar vacio")
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@Size(min=1)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles;

}