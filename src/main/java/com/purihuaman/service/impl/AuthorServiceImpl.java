package com.purihuaman.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.purihuaman.dao.AuthorDAO;
import com.purihuaman.entity.Author;
import com.purihuaman.service.AuthorService;
import com.purihuaman.utils.AuthorSpecification;

@Service
public class AuthorServiceImpl implements AuthorService {
	private final AuthorDAO authorDao;

	public AuthorServiceImpl(AuthorDAO authorDao) {
		this.authorDao = authorDao;
	}

	@Override
	public List<Author> getAllAuthors(Pageable page) {
		return authorDao.getAllAuthors(page);
	}

	@Override
	public Author getAuthorById(String authorId) {
		return authorDao.getAuthorById(authorId);
	}

	@Override
	public Author addAuthor(Author author) {
		// TODO: Add the ID
		return authorDao.addAuthor(author);
	}

	@Override
	public Author updateAuthor(Author author) {
		return authorDao.updateAuthor(author);
	}

	@Override
	public Integer deleteAuthor(String authorId) {
		// TODO: Check existence
		// Author author = getAuthorById(authorId);

		return authorDao.deleteAuthor(authorId);
	}

	@Override
	public List<Author> filterAuthors(Map<String, String> valuesToFilter, Pageable page) {
		Specification<Author> spec = AuthorSpecification.filterAuthors(valuesToFilter);
		return authorDao.filterAuthors(spec, page).getContent();
	}
}
