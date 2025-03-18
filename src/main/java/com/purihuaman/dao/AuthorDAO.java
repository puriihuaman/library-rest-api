package com.purihuaman.dao;

import com.purihuaman.model.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface AuthorDAO {
	List<AuthorEntity> findAllAuthors(Pageable page);

	AuthorEntity findAuthorById(UUID authorId);

	AuthorEntity createAuthor(AuthorEntity authorEntity);

	AuthorEntity updateAuthor(AuthorEntity authorEntity);

	void deleteAuthor(UUID authorId);

	Page<AuthorEntity> filterAuthors(Specification<AuthorEntity> spec, Pageable page);
}
