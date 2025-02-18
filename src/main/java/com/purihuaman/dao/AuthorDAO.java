package com.purihuaman.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.purihuaman.entity.Author;

public interface AuthorDAO {
	List<Author> findAllAuthors(Pageable page);

	Author findAuthorById(String authorId);

	Author createAuthor(Author author);

	Author updateAuthor(Author author);

	void deleteAuthor(String authorId);

	Page<Author> filterAuthors(Specification<Author> spec, Pageable page);
}
