package com.purihuaman.controller;

import com.purihuaman.dto.CustomerDTO;
import com.purihuaman.service.CustomerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CustomerController {
	private final CustomerService customerService;

	private CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/all")
	public ResponseEntity<Object> findAll(@RequestParam Map<String, String> keywords) {
		short
			offset =
			keywords.containsKey("offset") ? Short.parseShort(keywords.get("offset")) : 0;
		short limit = keywords.containsKey("limit") ? Short.parseShort(keywords.get("limit")) : 0;
		Pageable page = PageRequest.of(offset, limit);

		List<CustomerDTO>
			customers =
			keywords.isEmpty()
				? customerService.findAllCustomers(page)
				: customerService.filterCustomers(keywords, page);
		return ResponseEntity.ok(customers);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Object> findCustomerById(@PathVariable UUID id) {
		CustomerDTO foundCustomerDTO = customerService.findCustomerById(id);
		return ResponseEntity.ok(foundCustomerDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<Object> createCustomer(@Validated @RequestBody CustomerDTO customerDTO) {
		CustomerDTO savedCustomerDTO = customerService.createCustomer(customerDTO);
		return ResponseEntity.ok(savedCustomerDTO);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateCustomer(
		@PathVariable UUID id,
		@Validated @RequestBody CustomerDTO customerDTO
	)
	{
		CustomerDTO updatedCustomerDTO = customerService.updateCustomer(id, customerDTO);
		return ResponseEntity.ok(updatedCustomerDTO);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable UUID id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.ok().build();
	}
}
