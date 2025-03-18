package com.purihuaman.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "BOOK")
@Table(name = "BOOKS")
@Data
public class BookEntity {
	@Id
	@Column(name = "isbn", unique = true, length = 36)
	private String isbn;

	@Column(name = "title", nullable = false, length = 60)
	private String title;

	@Column(name = "year", nullable = false)
	private Integer year;

	@Column(name = "totalCopies", nullable = false)
	private Integer totalCopies;

	@Column(name = "borrowedCount", nullable = false)
	private Integer borrowedCount;

	@Column(name = "availableCopies", nullable = false)
	private Integer availableCopies;

	@Column(name = "available", nullable = false)
	private Boolean available;

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private AuthorEntity author;

	@ManyToOne
	@JoinColumn(name = "publisher_id", nullable = false)
	private PublisherEntity publisher;
}
