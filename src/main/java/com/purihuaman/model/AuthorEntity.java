package com.purihuaman.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "AUTHOR")
@Table(name = "AUTHORS")
@Data
public class AuthorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "AUTHOR_ID", unique = true, length = 36)
	private UUID id;

	@Column(name = "FIRST_NAME", nullable = false, length = 60)
	private String firstName;

	@Email
	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	//@OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
	//private List<BookEntity> books = new ArrayList<>();
}
