package com.purihuaman.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
public class CustomerDTO {
	@JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
	private UUID id;

	@NotNull
	@NotEmpty
	@Size(min = 8, max = 8)
	@JsonProperty(value = "documentId", required = true)
	private String documentId;

	@NotNull
	@NotEmpty
	@Size(min = 2, max = 50)
	@JsonProperty(value = "firstName", required = true)
	private String firstName;

	@NotNull
	@NotEmpty
	@Size(min = 2, max = 50)
	@JsonProperty(value = "lastName", required = true)
	private String lastName;

	@Email
	@NotNull
	@NotEmpty
	@JsonProperty(value = "email", required = true)
	private String email;

	@NotNull
	@NotEmpty
	@Size(min = 9, max = 20)
	@JsonProperty(value = "phoneNumber", required = true)
	private String phoneNumber;
}
