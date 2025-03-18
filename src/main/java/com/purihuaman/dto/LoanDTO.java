package com.purihuaman.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.purihuaman.model.BookEntity;
import com.purihuaman.model.CustomerEntity;
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
	@JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
	private UUID id;

	@JsonProperty(value = "loanDate", required = true)
	private LocalDateTime loanDate;

	@JsonProperty(value = "returnDate", required = true)
	private LocalDateTime returnDate;

	private BookEntity book;

	private CustomerEntity customer;
}
