package com.purihuaman.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

	@Size(min = 36, max = 36)
	private String authorId;

	@NotNull
	private String firstName;

	@NotNull
	private String email;

	@NotNull
	private Boolean isActive;
}