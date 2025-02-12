package com.purihuaman.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "editorial")
@Table(name = "editorials")
public class Editorial {
	@Id
	@Column(name = "editorial_id", length = 36)
	private String editorialId;

	@Column(name = "name", nullable = false, length = 60)
	private String name;

	@Column(name = "is_active")
	private Boolean isActive;
}
