package com.purihuaman.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "editorial")
@Table(name = "editorials")
@Data
public class Editorial {
	@Id
	@Column(name = "editorial_id", length = 36)
	private String editorialId;

	@Column(name = "name", nullable = false, length = 60)
	private String name;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive;
}
