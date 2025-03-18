package com.purihuaman.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.UUID;

@Entity(name = "AUTHOR")
@Table(name = "AUTHORS")
@Data
public class AuthorEntity {
	@Id
	@Column(name = "id", unique = true, length = 36)
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "firstName", nullable = false, length = 45)
	private String firstName;

	@Email
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "available", nullable = false)
	private Boolean available;
}
