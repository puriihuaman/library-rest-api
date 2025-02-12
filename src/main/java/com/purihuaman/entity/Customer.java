package com.purihuaman.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "customer")
@Table(name = "customers")
public class Customer {

	@Id
	@Column(name = "customer_id", length = 36)
	private String id;

	@Column(name = "identity_document", nullable = false, length = 8)
	private String identityDocument;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
}
