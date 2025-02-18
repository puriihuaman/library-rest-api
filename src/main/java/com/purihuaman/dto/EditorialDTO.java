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
public class EditorialDTO {
	@NotNull
	@Size(min = 36, max = 36)
	private String id;

	@NotNull
	@Size(min = 2, max = 60)
	private String name;

	@NotNull
	private Boolean isActive;
}
