package com.purihuaman.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
	@JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
	private UUID id;

	@NotNull
	@JsonProperty(value = "firstName", required = true)
	private String firstName;

	@NotNull
	@Email
	@JsonProperty(value = "email", required = true)
	private String email;
}