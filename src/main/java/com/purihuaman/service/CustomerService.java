package com.purihuaman.service;

import com.purihuaman.dto.CustomerDTO;
import com.purihuaman.mapper.CustomerMapper;
import com.purihuaman.model.CustomerEntity;
import com.purihuaman.repository.CustomerRepository;
import com.purihuaman.service.use_case.CustomerServiceUseCase;
import com.purihuaman.utils.CustomerSpecification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService implements CustomerServiceUseCase {
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;

	public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	@Override
	public List<CustomerDTO> findAllCustomers(Pageable page) {
		List<CustomerEntity> customers = customerRepository.findAll(page).getContent();
		return customerMapper.toDTOList(customers);
	}

	@Override
	public CustomerDTO findCustomerById(UUID customerId) {
		Optional<CustomerEntity> customer = customerRepository.findById(customerId);
		if (customer.isEmpty()) {
			throw new RuntimeException("Customer not found");
		}
		return customerMapper.toDTO(customer.get());
	}

	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDTO) {
		CustomerEntity customerToSave = customerMapper.toEntity(customerDTO);
		return customerMapper.toDTO(customerRepository.save(customerToSave));
	}

	@Override
	public CustomerDTO updateCustomer(UUID customerId, CustomerDTO customerDTO) {
		CustomerDTO customerDTOFound = this.findCustomerById(customerId);
		if (customerDTOFound == null) {
			throw new RuntimeException("Customer not found");
		}
		CustomerEntity customerToUpdate = customerMapper.toEntity(customerDTO);
		return customerMapper.toDTO(customerRepository.save(customerToUpdate));
	}

	@Override
	public void deleteCustomer(UUID customerId) {
		CustomerDTO customerDTOFound = this.findCustomerById(customerId);
		customerRepository.deleteById(customerDTOFound.getId());
	}

	@Override
	public List<CustomerDTO> filterCustomers(Map<String, String> valuesToFilter, Pageable page) {
		Specification<CustomerEntity> spec = CustomerSpecification.filterCustomers(valuesToFilter);
		List<CustomerEntity> customers = customerRepository.findAll(spec, page).getContent();
		return customerMapper.toDTOList(customers);
	}
}
