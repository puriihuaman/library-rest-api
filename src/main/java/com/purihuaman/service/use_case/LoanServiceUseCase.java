package com.purihuaman.service.use_case;

import com.purihuaman.dto.LoanDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface LoanServiceUseCase {
    List<LoanDTO> findAllLoans(Pageable page);
    
    LoanDTO findLoanById(UUID loanId);
    
    LoanDTO createLoan(LoanDTO loan);
    
    LoanDTO updateLoan(UUID loanId, LoanDTO loan);
    
    void deleteLoan(UUID loanId);
    
    List<LoanDTO> filterLoans(Map<String, String> valuesToFilter, Pageable page);
}
