package com.purihuaman.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.purihuaman.model.AuthorEntity;
import com.purihuaman.model.PublisherEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
	@JsonProperty(value = "isbn", required = true, access = JsonProperty.Access.WRITE_ONLY)
	private String isbn;

	@JsonProperty(value = "title", required = true)
	private String title;

	@JsonProperty(value = "title", required = true)
	private Integer year;

	@JsonProperty(value = "totalCopies", required = true)
	private Integer totalCopies;

	@JsonProperty(value = "borrowedCount", required = true)
	private Integer borrowedCount;

	@JsonProperty(value = "availableCopies", required = true)
	private Integer availableCopies;

	@JsonProperty(value = "available", required = true)
	private Boolean available;

	private AuthorEntity author;

	private PublisherEntity publisher;
}
