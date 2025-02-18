package com.purihuaman.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.purihuaman.dto.AuthorDTO;

public interface AuthorService {
	List<AuthorDTO> findAllAuthors(Pageable page);

	AuthorDTO findAuthorById(String authorId);

	AuthorDTO createAuthor(AuthorDTO author);

	AuthorDTO updateAuthor(AuthorDTO author);

	void deleteAuthor(String authorId);

	List<AuthorDTO> filterAuthors(Map<String, String> valuesToFilter, Pageable page);
}
