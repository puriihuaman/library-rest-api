package com.purihuaman.dao.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.purihuaman.dao.AuthorDAO;
import com.purihuaman.entity.Author;
import com.purihuaman.repository.AuthorRepository;

@Repository
public class AuthorDAOImpl implements AuthorDAO {
	private final AuthorRepository authorRepository;

	public AuthorDAOImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public List<Author> getAllAuthors(Pageable page) {
		return authorRepository.findAll(page).getContent();
	}

	@Override
	public Author getAuthorById(String authorId) {
		return authorRepository.findById(authorId).orElse(null);
	}

	@Override
	public Author addAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public Author updateAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public void deleteAuthor(String authorId) {
		authorRepository.deleteById(authorId);
	}

	@Override
	public Page<Author> filterAuthors(Specification<Author> spec, Pageable page) {
		return authorRepository.findAll(spec, page);
	}
}
