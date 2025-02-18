package com.purihuaman.controller;

import java.util.List;
import java.util.Map;

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

import com.purihuaman.dto.AuthorDTO;
import com.purihuaman.service.AuthorService;

@RestController
@RequestMapping(name = "/authors", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {
	private final AuthorService authorService;

	public AuthorController(AuthorService service) {
		this.authorService = service;
	}

	@GetMapping
	public ResponseEntity<List<AuthorDTO>> findAllAuthors(@RequestParam Map<String, String> keywords) {
		short offset = keywords.containsKey("offset") ? Short.parseShort(keywords.get("offset")) : 0;
		short limit = keywords.containsKey("limit") ? Short.parseShort(keywords.get("limit")) : 10;

		Pageable page = PageRequest.of(offset, limit);

		List<AuthorDTO> result = authorService.findAllAuthors(page);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AuthorDTO> findAuthorById(@PathVariable("id") String authorId) {

		AuthorDTO result = authorService.findAuthorById(authorId);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping
	public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO author) {
		AuthorDTO result = authorService.createAuthor(author);
		return ResponseEntity.ok().body(result);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable("id") String authorId, @RequestBody AuthorDTO author) {
		AuthorDTO result = authorService.updateAuthor(author);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable("id") String authorId) {
		authorService.deleteAuthor(authorId);
		return ResponseEntity.noContent().build();
	}
}
