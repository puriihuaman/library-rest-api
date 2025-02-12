package com.purihuaman.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "author")
@Table(name = "authors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
	@Id
	@Column(name = "author_id", length = 36)
	private String authorId;

	@Column(name = "first_name", nullable = false, length = 45)
	private String firstName;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive;
}
