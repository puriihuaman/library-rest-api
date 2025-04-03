package com.purihuaman.service;

import com.purihuaman.dto.BookDTO;
import com.purihuaman.dto.CustomerDTO;
import com.purihuaman.dto.LoanDTO;
import com.purihuaman.mapper.LoanMapper;
import com.purihuaman.model.LoanEntity;
import com.purihuaman.repository.LoanRepository;
import com.purihuaman.service.use_case.LoanServiceUseCase;
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
		return loanMapper.toDTOList(loanRepository.findAll(page).getContent());
	}

	@Override
	public LoanDTO findLoanById(UUID id) {
		Optional<LoanEntity> loanEntity = loanRepository.findById(id);

		if (loanEntity.isEmpty()) {
			throw new RuntimeException("Loan not found");
		}

		return loanMapper.toDTO(loanEntity.get());
	}

	@Override
	public LoanDTO createLoan(LoanDTO loanDTO) {
		// Search book by id
		BookDTO book = this.bookService.findBookById(loanDTO.getBookId());
		// Search customer by id
		CustomerDTO customer = this.customerService.findCustomerById(loanDTO.getCustomerId());

		LoanDTO
			loanToSave =
			LoanDTO
				.builder()
				.loanDate(LocalDateTime.now())
				.returnDate(loanDTO.getReturnDate())
				.bookId(book.getId())
				.customerId(customer.getId())
				.build();

		LoanEntity loanEntity = loanMapper.toEntity(loanToSave);

		bookService.updateBorrowedBookCount(book.getId(), 1);

		return loanMapper.toDTO(loanRepository.save(loanEntity));
	}

	@Override
	public LoanDTO updateLoan(UUID id, LoanDTO loanDTO) {
		return null;
	}

	@Override
	public void deleteLoan(UUID id) {
		LoanDTO loanFound = this.findLoanById(id);

		loanRepository.deleteById(loanFound.getId());
	}

	@Override
	public List<LoanDTO> filterLoans(Map<String, String> valuesToFilter, Pageable page) {
		return List.of();
	}
}
