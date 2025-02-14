package com.purihuaman.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.purihuaman.dto.AuthorDTO;

public interface AuthorService {
	List<AuthorDTO> getAllAuthors(Pageable page);

	AuthorDTO getAuthorById(String authorId);

	AuthorDTO addAuthor(AuthorDTO author);

	AuthorDTO updateAuthor(AuthorDTO author);

	void deleteAuthor(String authorId);

	List<AuthorDTO> filterAuthors(Map<String, String> valuesToFilter, Pageable page);
}
