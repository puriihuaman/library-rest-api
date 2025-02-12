package com.purihuaman.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.purihuaman.entity.Author;
import com.purihuaman.service.AuthorService;

@RestController
@RequestMapping(name = "/authors", produces = "application/json")
public class AuthorController {
	private final AuthorService service;

	public AuthorController(AuthorService service) {
		this.service = service;
	}

	@GetMapping
	public List<Author> getAllAuthors(@RequestParam Map<String, String> keywords) {
		short offset = keywords.containsKey("offset") ? Short.parseShort(keywords.get("offset")) : 0;
		short limit = keywords.containsKey("limit") ? Short.parseShort(keywords.get("limit")) : 10;

		Pageable page = PageRequest.of(offset, limit);

		return service.getAllAuthors(page);
	}

	@GetMapping("/{id}")
	public Author getAuthorById(@PathVariable("id") String authorId) {
		return service.getAuthorById(authorId);
	}

	@PostMapping
	public Author createAuthor(@RequestBody Author author) {
		return service.addAuthor(author);
	}

	@PutMapping("/{id}")
	public Author updateAuthor(@PathVariable("id") String authorId, @RequestBody Author author) {
		return service.updateAuthor(author);
	}

	@DeleteMapping("/{id}")
	public void deleteAuthor(@PathVariable("id") String authorId) {
		service.deleteAuthor(authorId);
	}
}
