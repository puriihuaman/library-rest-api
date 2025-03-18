package com.purihuaman.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Entity(name = "PUBLISHER")
@Table(name = "PUBLISHERS")
@Data
public class PublisherEntity {
	@Id
	@Column(name = "id", unique = true, length = 36)
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "name", nullable = false, length = 60)
	@Size(min = 2, max = 60)
	private String name;

	@Column(name = "available", nullable = false)
	private Boolean available;
}
