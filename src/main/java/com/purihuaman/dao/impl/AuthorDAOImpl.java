package com.purihuaman.dao.impl;

import com.purihuaman.dao.AuthorDAO;
import com.purihuaman.model.AuthorEntity;
import com.purihuaman.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AuthorDAOImpl implements AuthorDAO {
	private final AuthorRepository authorRepository;

	public AuthorDAOImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public List<AuthorEntity> findAllAuthors(Pageable page) {
		return authorRepository.findAll(page).getContent();
	}

	@Override
	public AuthorEntity findAuthorById(UUID authorId) {
		return authorRepository.findById(authorId).orElse(null);
	}

	@Override
	public AuthorEntity createAuthor(AuthorEntity authorEntity) {
		return authorRepository.save(authorEntity);
	}

	@Override
	public AuthorEntity updateAuthor(AuthorEntity authorEntity) {
		return authorRepository.save(authorEntity);
	}

	@Override
	public void deleteAuthor(UUID authorId) {
		authorRepository.deleteById(authorId);
	}

	@Override
	public Page<AuthorEntity> filterAuthors(Specification<AuthorEntity> spec, Pageable page) {
		return authorRepository.findAll(spec, page);
	}
}
