package com.purihuaman.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "book")
@Table(name = "books")
public class Book {
	@Id
	@Column(name = "isbn", nullable = false, length = 36)
	private String isbn;

	@Column(name = "title", nullable = false, length = 60)
	private String title;

	@Column(name = "year", nullable = false)
	private Integer year;

	@Column(name = "copies", nullable = false)
	private Integer specimens;

	@Column(name = "borrowed_copies", nullable = false)
	private Integer borrowedCopies;

	@Column(name = "remaining_copies", nullable = false)
	private Integer remainingCopies;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive;

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Author author;

	@ManyToOne
	@JoinColumn(name = "editorial_id", nullable = false)
	private Editorial editorial;
}
