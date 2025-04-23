package com.purihuaman.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublisherDTO {
	@JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
	private UUID id;

	@NotNull
	@Size(min = 2, max = 60)
	@JsonProperty(value = "name", required = true)
	private String name;
}
