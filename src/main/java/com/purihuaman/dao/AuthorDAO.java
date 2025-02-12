package com.purihuaman.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.purihuaman.entity.Author;

public interface AuthorDAO {
	List<Author> getAllAuthors(Pageable page);

	Author getAuthorById(String authorId);

	Author addAuthor(Author author);

	Author updateAuthor(Author author);

	Integer deleteAuthor(String authorId);

	Page<Author> filterAuthors(Specification<Author> spec, Pageable page);
}
