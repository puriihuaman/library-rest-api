package com.purihuaman.service;

import com.purihuaman.dto.CustomerDTO;
import com.purihuaman.enums.APIError;
import com.purihuaman.exception.APIRequestException;
import com.purihuaman.mapper.CustomerMapper;
import com.purihuaman.model.CustomerEntity;
import com.purihuaman.repository.CustomerRepository;
import com.purihuaman.service.use_case.CustomerServiceUseCase;
import com.purihuaman.utils.CustomerSpecification;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            List<CustomerEntity> customers = customerRepository.findAll(page).getContent();

            return customerMapper.toDTOList(customers);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public CustomerDTO findCustomerById(UUID customerId) {
        try {
            Optional<CustomerEntity> response = customerRepository.findById(customerId);
            
            if (response.isEmpty()) {
                APIError.RECORD_NOT_FOUND.setTitle("Customer Not Found");
                APIError.RECORD_NOT_FOUND.setMessage("Customer Not Found");
                throw new APIRequestException(APIError.RECORD_NOT_FOUND);
            }
            CustomerEntity customerFound = response.get();
            
            return customerMapper.toDTO(customerFound);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public CustomerDTO createCustomer(CustomerDTO customer) {
        try {
            CustomerEntity customerToSave = customerMapper.toEntity(customer);
            CustomerEntity savedCustomer = customerRepository.save(customerToSave);
            
            return customerMapper.toDTO(savedCustomer);
        } catch (APIRequestException ex) {
            throw ex;
        } catch (DataIntegrityViolationException ex) {
            Throwable cause = ex.getCause();
            Throwable rootCause = cause.getCause();
            
            if (cause instanceof ConstraintViolationException ||
                rootCause instanceof DataIntegrityViolationException) throw new APIRequestException(
                APIError.UNIQUE_CONSTRAINT_VIOLATION);
            else throw new APIRequestException(APIError.RESOURCE_ASSOCIATED_EXCEPTION);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public CustomerDTO updateCustomer(UUID customerId, CustomerDTO customer) {
        try {
            CustomerDTO customerFound = this.findCustomerById(customerId);
            
            customerFound.setFirstName(customer.getFirstName());
            customerFound.setLastName(customer.getLastName());
            customerFound.setEmail(customer.getEmail());
            customerFound.setPhoneNumber(customer.getPhoneNumber());
            customerFound.setDocumentId(customer.getDocumentId());
            
            CustomerEntity customerToUpdate = customerMapper.toEntity(customerFound);
            CustomerEntity updatedCustomer = customerRepository.save(customerToUpdate);
            
            return customerMapper.toDTO(updatedCustomer);
        } catch (APIRequestException ex) {
            throw ex;
        } catch (DataIntegrityViolationException ex) {
            Throwable cause = ex.getCause();
            Throwable rootCause = cause.getCause();
            
            if (cause instanceof ConstraintViolationException ||
                rootCause instanceof DataIntegrityViolationException) throw new APIRequestException(
                APIError.UNIQUE_CONSTRAINT_VIOLATION);
            else throw new APIRequestException(APIError.RESOURCE_ASSOCIATED_EXCEPTION);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public void deleteCustomer(UUID customerId) {
        try {
            CustomerDTO customerDTOFound = this.findCustomerById(customerId);
            customerRepository.deleteById(customerDTOFound.getId());
        } catch (APIRequestException ex) {
            throw ex;
        } catch (DataIntegrityViolationException ex) {
            Throwable cause = ex.getCause();
            Throwable rootCause = cause.getCause();
            
            if (cause instanceof ConstraintViolationException ||
                rootCause instanceof DataIntegrityViolationException) throw new APIRequestException(
                APIError.UNIQUE_CONSTRAINT_VIOLATION);
            else throw new APIRequestException(APIError.RESOURCE_ASSOCIATED_EXCEPTION);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public List<CustomerDTO> filterCustomers(Map<String, String> valuesToFilter, Pageable page) {
        try {
            Specification<CustomerEntity> spec = CustomerSpecification.filterCustomers(
                valuesToFilter);
            List<CustomerEntity> customers = customerRepository.findAll(spec, page).getContent();
            
            return customerMapper.toDTOList(customers);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
}
