package com.purihuaman.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "BOOK")
@Table(name = "BOOKS")
@Data
public class BookEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "BOOK_ID", unique = true, length = 20)
	private UUID id;

	@Column(name = "ISBN", unique = true, length = 20)
	private String isbn;

	@Column(name = "TITLE", nullable = false, length = 100)
	private String title;

	@Column(name = "PUBLICATION_DATE", nullable = false)
	private LocalDate publicationDate;

	@Column(name = "TOTAL_COPIES", nullable = false)
	private Integer totalCopies = 0;

	@Column(name = "BORROWED_COUNT", nullable = false)
	private Integer borrowedCount = 0;

	@Column(name = "AVAILABLE_COPIES", nullable = false)
	private Integer availableCopies = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHOR_ID", referencedColumnName = "AUTHOR_ID")
	private AuthorEntity author;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PUBLISHER_ID", referencedColumnName = "PUBLISHER_ID")
	private PublisherEntity publisher;

	@PrePersist
	@PreUpdate
	private void calculateAvailableCopies() {
		this.availableCopies = totalCopies - borrowedCount;
	}
}
