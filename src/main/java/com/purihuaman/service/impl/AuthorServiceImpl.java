package com.purihuaman.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.purihuaman.dao.AuthorDAO;
import com.purihuaman.dto.AuthorDTO;
import com.purihuaman.entity.Author;
import com.purihuaman.mapper.AuthorMapper;
import com.purihuaman.service.AuthorService;
import com.purihuaman.utils.AuthorSpecification;

@Service
public class AuthorServiceImpl implements AuthorService {
	private final AuthorDAO authorDao;
	private final AuthorMapper mapper;

	public AuthorServiceImpl(AuthorDAO authorDao, AuthorMapper mapper) {
		this.authorDao = authorDao;
		this.mapper = mapper;
	}

	@Override
	public List<AuthorDTO> getAllAuthors(Pageable page) {
		return mapper.toAuthorDTOList(authorDao.getAllAuthors(page));
	}

	@Override
	public AuthorDTO getAuthorById(String authorId) {
		return mapper.toAuthorDTO(authorDao.getAuthorById(authorId));
	}

	@Override
	public AuthorDTO addAuthor(AuthorDTO author) {
		author.setAuthorId(UUID.randomUUID().toString());
		Author authorToSave = mapper.toAuthor(author);

		return mapper.toAuthorDTO(authorDao.addAuthor(authorToSave));
	}

	@Override
	public AuthorDTO updateAuthor(AuthorDTO author) {
		AuthorDTO authorFound = getAuthorById(author.getAuthorId());

		authorFound.setFirstName(author.getFirstName());
		authorFound.setEmail(author.getEmail());
		authorFound.setIsActive(author.getIsActive());

		AuthorDTO updated = mapper.toAuthorDTO(authorDao.updateAuthor(mapper.toAuthor(authorFound)));
		return updated;
	}

	@Override
	public void deleteAuthor(String authorId) {
		AuthorDTO authorFound = getAuthorById(authorId);
		authorDao.deleteAuthor(authorFound.getAuthorId());
	}

	@Override
	public List<AuthorDTO> filterAuthors(Map<String, String> valuesToFilter, Pageable page) {
		Specification<Author> spec = AuthorSpecification.filterAuthors(valuesToFilter);

		return mapper.toAuthorDTOList(authorDao.filterAuthors(spec, page).getContent());
	}
}
