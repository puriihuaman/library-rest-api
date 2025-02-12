package com.purihuaman.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.purihuaman.entity.Author;

public interface AuthorService {
	List<Author> getAllAuthors(Pageable page);

	Author getAuthorById(String authorId);

	Author addAuthor(Author author);

	Author updateAuthor(Author author);

	Integer deleteAuthor(String authorId);

	List<Author> filterAuthors(Map<String, String> valuesToFilter, Pageable page);
}
