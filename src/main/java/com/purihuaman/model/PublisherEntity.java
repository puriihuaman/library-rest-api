package com.purihuaman.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "PUBLISHER")
@Table(name = "PUBLISHERS")
@NoArgsConstructor
@Getter
@Setter
public class PublisherEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "PUBLISHER_ID", unique = true, length = 36)
	private UUID id;

	@Column(name = "NAME", nullable = false, length = 60)
	@Size(min = 2, max = 60)
	private String name;

	//@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
	//private List<BookEntity> books = new ArrayList<>();
}
