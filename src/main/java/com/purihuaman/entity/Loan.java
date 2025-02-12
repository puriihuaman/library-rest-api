package com.purihuaman.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "loan")
@Table(name = "loans")
public class Loan {

	@Id
	@Column(name = "loan_id", nullable = false, length = 36)
	private String id;

	@Temporal(TemporalType.DATE)
	@Column(name = "loan_date", nullable = false)
	private LocalDate loanDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "return_date", nullable = false)
	private LocalDate returnDate;

	@ManyToOne
	@JoinColumn(name = "book_isbn", nullable = false)
	private Book book;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
}
