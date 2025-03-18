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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
	@JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
	private UUID id;

	@JsonProperty(value = "documentId", required = true)
	@NotNull
	@NotEmpty
	@Size(min = 8, max = 8)
	private String documentId;

	@JsonProperty(value = "firstName", required = true)
	@NotNull
	@NotEmpty
	@Size(min = 2, max = 50)
	private String firstName;

	@JsonProperty(value = "lastName", required = true)
	@NotNull
	@NotEmpty
	@Size(min = 2, max = 50)
	private String lastName;

	@JsonProperty(value = "email", required = true)
	@Email
	@NotNull
	@NotEmpty
	private String email;

	@JsonProperty(value = "phoneNumber", required = true)
	@NotNull
	@NotEmpty
	@Size(min = 9, max = 20)
	private String phoneNumber;
}
