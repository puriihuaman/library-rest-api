package com.purihuaman.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {
	@JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
	private UUID id;

	@NotNull
	@JsonProperty(value = "loanDate", access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(
		shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC"
	)
	private LocalDateTime loanDate;

	@NotNull
	@JsonProperty(value = "returnDate")
	@JsonFormat(
		shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC"
	)
	private LocalDateTime returnDate;

	@NotNull
	@JsonProperty(value = "bookId")
	private UUID bookId;

	@NotNull
	@JsonProperty(value = "customerId")
	private UUID customerId;
}
