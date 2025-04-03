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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "CUSTOMER")
@Table(name = "CUSTOMERS")
@Data
public class CustomerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "CUSTOMER_ID", unique = true, length = 36)
	private UUID id;

	@NotNull
	@NotEmpty
	@Size(min = 8, max = 8)
	@Column(name = "DOCUMENT_ID", nullable = false, length = 8)
	private String documentId;

	@NotNull
	@NotEmpty
	@Size(min = 2, max = 50)
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@NotNull
	@NotEmpty
	@Size(min = 2, max = 50)
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Email
	@NotNull
	@NotEmpty
	@Column(name = "EMAIL", nullable = false)
	private String email;

	@NotNull
	@NotEmpty
	@Size(min = 9, max = 20)
	@Column(name = "PHONE_NUMBER", nullable = false)
	private String phoneNumber;

	//@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	//private List<LoanEntity> loans = new ArrayList<>();
}
