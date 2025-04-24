package com.purihuaman.controller;

import com.purihuaman.dto.BookDTO;
import com.purihuaman.service.BookService;
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
@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("all")
	public ResponseEntity<List<BookDTO>> findAllBooks(@RequestParam Map<String, String> keywords) {
		Short offset = keywords.containsKey("offset") ? Short.valueOf(keywords.get("offset")) : 0;
		Short limit = keywords.containsKey("limit") ? Short.valueOf(keywords.get("limit")) : 10;

		Pageable page = PageRequest.of(offset, limit);

		List<BookDTO>
			result =
			keywords.isEmpty() ? bookService.findAllBooks(page) : bookService.filterBooks(
				keywords,
				page
			);
		return ResponseEntity.ok(result);
	}

	@GetMapping("id/{id}")
	public ResponseEntity<BookDTO> findBookById(@PathVariable("id") UUID id) {
		BookDTO book = bookService.findBookById(id);
		return ResponseEntity.ok().body(book);
	}

	@PostMapping("create")
	public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO book) {
		BookDTO createdBook = bookService.createBook(book);
		return ResponseEntity.ok().body(createdBook);
	}

	@PutMapping("update/{isbn}")
	public ResponseEntity<BookDTO> updateBook(
		@PathVariable("isbn") UUID isbn,
		@RequestBody BookDTO book
	)
	{
		BookDTO updatedBook = bookService.updateBook(isbn, book);
		return ResponseEntity.ok().body(updatedBook);
	}

	@DeleteMapping("delete/{isbn}")
	public ResponseEntity<Void> deleteBook(@PathVariable("isbn") UUID isbn) {
		bookService.deleteBook(isbn);
		return ResponseEntity.noContent().build();
	}
}
