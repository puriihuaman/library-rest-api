package com.purihuaman.service.use_case;

import com.purihuaman.dto.BookDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BookServiceUseCase {
	List<BookDTO> findAllBooks(Pageable page);

	BookDTO findBookById(UUID isbn);

	BookDTO createBook(BookDTO bookDTO);

	BookDTO updateBook(UUID isbn, BookDTO bookDTO);

	void deleteBook(UUID isbn);

	List<BookDTO> filterBooks(Map<String, String> valuesToFilter, Pageable page);

	void updateBorrowedBookCount(UUID id, Integer amount);
}
