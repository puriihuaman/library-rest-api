package com.purihuaman.service;

import com.purihuaman.dto.AuthorDTO;
import com.purihuaman.mapper.AuthorMapper;
import com.purihuaman.model.AuthorEntity;
import com.purihuaman.repository.AuthorRepository;
import com.purihuaman.service.use_case.AuthorServiceUseCase;
import com.purihuaman.utils.AuthorSpecification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService implements AuthorServiceUseCase {
	private final AuthorRepository authorRepository;
	private final AuthorMapper authorMapper;

	public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
		this.authorRepository = authorRepository;
		this.authorMapper = authorMapper;
	}

	@Override
	public List<AuthorDTO> findAllAuthors(Pageable page) {
		//return authorMapper.toDTOList(authorDao.findAllAuthors(page));
		return authorMapper.toDTOList(authorRepository.findAll(page).getContent());
	}

	@Override
	public AuthorDTO findAuthorById(UUID authorId) {
		//return authorMapper.toDTO(authorDao.findAuthorById(AUTHOR_UUID));
		Optional<AuthorEntity> authorEntityOptional = authorRepository.findById(authorId);
		if (authorEntityOptional.isEmpty()) {
			throw new RuntimeException("Not found author");
		}
		return authorMapper.toDTO(authorEntityOptional.get());
	}

	@Override
	public AuthorDTO createAuthor(AuthorDTO authorDTO) {
		AuthorEntity authorEntityToSave = authorMapper.toEntity(authorDTO);

		//return ResponseEntity.ok(authorMapper.toDTO(authorDao.createAuthor(authorEntityToSave)));
		return authorMapper.toDTO(authorRepository.save(authorEntityToSave));
	}

	@Override
	public AuthorDTO updateAuthor(UUID authorId, AuthorDTO authorDTO) {
		AuthorDTO authorDTOFound = this.findAuthorById(authorId);

		authorDTOFound.setFirstName(authorDTO.getFirstName());
		authorDTOFound.setEmail(authorDTO.getEmail());
		authorDTOFound.setAvailable(authorDTO.getAvailable());

		//Author
		//	updated =
		//	authorMapper.toDTO(authorDao.updateAuthor(authorMapper.toEntity(authorFound)));
		AuthorDTO
			updated =
			authorMapper.toDTO(authorRepository.save(authorMapper.toEntity(authorDTOFound)));
		return updated;
	}

	@Override
	public void deleteAuthor(UUID authorId) {
		AuthorDTO authorDTOFound = this.findAuthorById(authorId);
		//authorDao.deleteAuthor(AUTHOR_UUID);
		authorRepository.deleteById(authorDTOFound.getId());
	}

	@Override
	public List<AuthorDTO> filterAuthors(Map<String, String> valuesToFilter, Pageable page) {
		Specification<AuthorEntity> spec = AuthorSpecification.filterAuthors(valuesToFilter);

		//return authorMapper.toDTOList(authorDao.filterAuthors(spec, page).getContent());
		return authorMapper.toDTOList(authorRepository.findAll(spec, page).getContent());
	}
}
