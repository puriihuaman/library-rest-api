package com.purihuaman.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "LOAN_ID", unique = true, length = 36)
	private UUID id;

	@Column(name = "LOAN_DATE", nullable = false)
	private LocalDateTime loanDate;

	@Column(name = "RETURN_DATE", nullable = false)
	private LocalDateTime returnDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID", nullable = false)
	private BookEntity book;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID", nullable = false)
	private CustomerEntity customer;

	public void markAsReturned() {
		this.returnDate = LocalDateTime.now();
		this.book.setBorrowedCount(this.book.getBorrowedCount() - 1);
		this.book.setAvailableCopies(this.book.getAvailableCopies() + 1);
	}
}
