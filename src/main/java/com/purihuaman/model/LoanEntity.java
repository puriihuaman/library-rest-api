package com.purihuaman.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "LOAN")
@Table(name = "LOANS")
@Data
public class LoanEntity {
	@Id
	@Column(name = "id", unique = true, length = 36)
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "loanDate", nullable = false)
	private LocalDateTime loanDate;

	@Column(name = "returnDate", nullable = false)
	private LocalDateTime returnDate;

	@ManyToOne
	@JoinColumn(name = "book_isbn", nullable = false)
	private BookEntity book;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private CustomerEntity customer;
}
