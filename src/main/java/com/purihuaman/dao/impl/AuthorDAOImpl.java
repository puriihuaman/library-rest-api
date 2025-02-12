package com.purihuaman.dao.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.purihuaman.dao.AuthorDAO;
import com.purihuaman.entity.Author;
import com.purihuaman.repository.AuthorRepository;

public class AuthorDAOImpl implements AuthorDAO {

	final private AuthorRepository authorRepository;

	public AuthorDAOImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public List<Author> getAllAuthors(Pageable page) {
		return authorRepository.findAll(page).getContent();
	}

	@Override
	public Author getAuthorById(String authorId) {
		return authorRepository.findById(authorId).get();
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
	public Integer deleteAuthor(String authorId) {
		authorRepository.deleteById(authorId);
		return 1;
	}

	@Override
	public Page<Author> filterAuthors(Specification<Author> spec, Pageable page) {
		return authorRepository.findAll(spec, page);
	}

}
