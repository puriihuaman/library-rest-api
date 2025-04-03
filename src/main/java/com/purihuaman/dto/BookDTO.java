package com.purihuaman.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class BookDTO {
	@JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
	private UUID id;

	@NotNull
	@JsonProperty(value = "isbn")
	private String isbn;

	@NotNull
	@JsonProperty(value = "title")
	private String title;

	@NotNull
	@JsonProperty(value = "publicationDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate publicationDate;

	// Total de copias
	@NotNull
	@JsonProperty(value = "totalCopies")
	private Integer totalCopies;

	// Cantidad de prestados
	@NotNull
	@JsonProperty(value = "borrowedCount")
	private Integer borrowedCount;

	// Copias disponibles/restantes por prestar
	@JsonProperty(value = "availableCopies", access = JsonProperty.Access.READ_ONLY)
	private Integer availableCopies;

	@NotNull(message = "The author is required")
	private UUID authorId;
	@NotNull(message = "The publisher is required")
	private UUID publisherId;
}
