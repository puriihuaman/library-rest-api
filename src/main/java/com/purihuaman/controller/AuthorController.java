package com.purihuaman.controller;

import com.purihuaman.dto.AuthorDTO;
import com.purihuaman.service.AuthorService;
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
@RequestMapping(value = "/authors", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {
	private final AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@GetMapping("all")
	public ResponseEntity<List<AuthorDTO>> findAllAuthors(
		@RequestParam Map<String, String> keywords
	)
	{
		short
			offset =
			keywords.containsKey("offset") ? Short.parseShort(keywords.get("offset")) : 0;
		short limit = keywords.containsKey("limit") ? Short.parseShort(keywords.get("limit")) : 10;

		Pageable page = PageRequest.of(offset, limit);

		List<AuthorDTO>
			result =
			keywords.isEmpty() ? authorService.findAllAuthors(page) : authorService.filterAuthors(keywords,
																								  page
			);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "id/{id}")
	public ResponseEntity<AuthorDTO> findAuthorById(@PathVariable("id") UUID authorId) {
		AuthorDTO result = authorService.findAuthorById(authorId);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping("create")
	public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
		AuthorDTO result = authorService.createAuthor(authorDTO);
		return ResponseEntity.ok().body(result);
	}

	@PutMapping("update/{id}")
	public ResponseEntity<AuthorDTO> updateAuthor(
		@PathVariable("id") UUID authorId,
		@RequestBody AuthorDTO authorDTO
	)
	{
		AuthorDTO result = authorService.updateAuthor(authorId, authorDTO);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable("id") UUID authorId) {
		authorService.deleteAuthor(authorId);
		return ResponseEntity.noContent().build();
	}
}
