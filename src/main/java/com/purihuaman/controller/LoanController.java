package com.purihuaman.controller;

import com.purihuaman.dto.LoanDTO;
import com.purihuaman.service.LoanService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/loans", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanController {
	private final LoanService loanService;

	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}

	@GetMapping("all")
	public ResponseEntity<List<LoanDTO>> findAllLoans(@RequestParam Map<String, String> keywords) {
		Short offset = keywords.containsKey("offset") ? Short.valueOf(keywords.get("offset")) : 0;
		Short limit = keywords.containsKey("limit") ? Short.valueOf(keywords.get("limit")) : 10;

		Pageable page = PageRequest.of(offset, limit);

		List<LoanDTO>
			result =
			keywords.isEmpty() ? loanService.findAllLoans(page) : loanService.filterLoans(
				keywords,
				page
			);
		return ResponseEntity.ok(result);
	}

	@GetMapping("id/{id}")
	public ResponseEntity<LoanDTO> findLoanById(@PathVariable("id") UUID id) {
		LoanDTO loan = loanService.findLoanById(id);
		return ResponseEntity.ok().body(loan);
	}

	@PostMapping("create")
	public ResponseEntity<LoanDTO> createLoan(@RequestBody LoanDTO loan) {
		LoanDTO createdLoan = loanService.createLoan(loan);
		return ResponseEntity.ok().body(createdLoan);
	}

	@PutMapping("update/{isbn}")
	public ResponseEntity<LoanDTO> updateLoan(
		@PathVariable("isbn") UUID isbn,
		@RequestBody LoanDTO loan
	)
	{
		LoanDTO updatedLoan = loanService.updateLoan(isbn, loan);
		return ResponseEntity.ok().body(updatedLoan);
	}

	@DeleteMapping("delete/{isbn}")
	public ResponseEntity<Void> deleteLoan(@PathVariable("isbn") UUID isbn) {
		loanService.deleteLoan(isbn);
		return ResponseEntity.noContent().build();
	}
}
