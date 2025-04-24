package com.purihuaman.service;

import com.purihuaman.dto.BookDTO;
import com.purihuaman.dto.CustomerDTO;
import com.purihuaman.dto.LoanDTO;
import com.purihuaman.enums.APIError;
import com.purihuaman.exception.APIRequestException;
import com.purihuaman.mapper.LoanMapper;
import com.purihuaman.model.LoanEntity;
import com.purihuaman.repository.LoanRepository;
import com.purihuaman.service.use_case.LoanServiceUseCase;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService implements LoanServiceUseCase {
    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    private final BookService bookService;
    private final CustomerService customerService;
    
    public LoanService(
        LoanRepository loanRepository,
        LoanMapper loanMapper,
        BookService bookService,
        CustomerService customerService
    )
    {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
        this.bookService = bookService;
        this.customerService = customerService;
    }
    
    @Override
    public List<LoanDTO> findAllLoans(Pageable page) {
        try {
            List<LoanEntity> loans = loanRepository.findAll(page).getContent();
            
            return loanMapper.toDTOList(loans);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public LoanDTO findLoanById(UUID loanId) {
        try {
            Optional<LoanEntity> response = loanRepository.findById(loanId);
            
            if (response.isEmpty()) {
                APIError.RECORD_NOT_FOUND.setTitle("Loan not found");
                APIError.RECORD_NOT_FOUND.setMessage(
                    "The loan you are trying to access does not exist.");
                
                throw new APIRequestException(APIError.RECORD_NOT_FOUND);
            }
            LoanEntity loan = response.get();
            
            return loanMapper.toDTO(loan);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public LoanDTO createLoan(LoanDTO loan) {
        try {
            BookDTO book = this.bookService.findBookById(loan.getBookId());
            CustomerDTO customer = this.customerService.findCustomerById(loan.getCustomerId());
            
            LoanDTO
                loanToSave =
                LoanDTO
                    .builder()
                    .loanDate(LocalDateTime.now())
                    .returnDate(loan.getReturnDate())
                    .bookId(book.getId())
                    .customerId(customer.getId())
                    .build();
            
            LoanEntity loanEntity = loanMapper.toEntity(loanToSave);
            
            bookService.updateBorrowedBookCount(book.getId(), 1);
            LoanEntity savedLoan = loanRepository.save(loanEntity);
            
            return loanMapper.toDTO(savedLoan);
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
    public LoanDTO updateLoan(UUID loanId, LoanDTO loan) {
        try {
            return null;
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
    public void deleteLoan(UUID loanId) {
        try {
            LoanDTO loanFound = this.findLoanById(loanId);
            
            loanRepository.deleteById(loanFound.getId());
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
    public List<LoanDTO> filterLoans(Map<String, String> valuesToFilter, Pageable page) {
        try {
            return List.of();
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
}
