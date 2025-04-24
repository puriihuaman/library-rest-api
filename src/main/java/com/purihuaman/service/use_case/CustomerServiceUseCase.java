package com.purihuaman.service.use_case;

import com.purihuaman.dto.CustomerDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CustomerServiceUseCase {
    List<CustomerDTO> findAllCustomers(Pageable page);
    
    CustomerDTO findCustomerById(UUID customerId);
    
    CustomerDTO createCustomer(CustomerDTO customer);
    
    CustomerDTO updateCustomer(UUID customerId, CustomerDTO customer);
    
    void deleteCustomer(UUID customerId);
    
    List<CustomerDTO> filterCustomers(Map<String, String> valuesToFilter, Pageable page);
}
