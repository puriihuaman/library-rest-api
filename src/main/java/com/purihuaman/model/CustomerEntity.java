package com.purihuaman.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Entity(name = "CUSTOMER")
@Table(name = "CUSTOMERS")
@Data
public class CustomerEntity {
	@Id
	@Column(name = "id", unique = true, length = 36)
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "documentId", nullable = false, length = 8)
	@NotNull
	@NotEmpty
	@Size(min = 8, max = 8)
	private String documentId;

	@Column(name = "firstName", nullable = false)
	@NotNull
	@NotEmpty
	@Size(min = 2, max = 50)
	private String firstName;

	@Column(name = "lastName", nullable = false)
	@NotNull
	@NotEmpty
	@Size(min = 2, max = 50)
	private String lastName;

	@Column(name = "email", nullable = false)
	@Email
	@NotNull
	@NotEmpty
	private String email;

	@Column(name = "phoneNumber", nullable = false)
	@NotNull
	@NotEmpty
	@Size(min = 9, max = 20)
	private String phoneNumber;
}
