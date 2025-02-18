package com.purihuaman.dao.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.purihuaman.dao.EditorialDAO;
import com.purihuaman.entity.Editorial;
import com.purihuaman.repository.EditorialRepository;

@Repository
public class EditorialDAOImpl implements EditorialDAO {
	private final EditorialRepository editorialRepository;

	public EditorialDAOImpl(EditorialRepository repository) {
		this.editorialRepository = repository;
	}

	@Override
	public List<Editorial> findAllEditorials(Pageable page) {
		return editorialRepository.findAll(page).getContent();
	}

	@Override
	public Editorial findEditorialById(String id) {
		return editorialRepository.findById(id).orElseThrow();
	}

	@Override
	public Editorial createEditorial(Editorial editorial) {
		return editorialRepository.save(editorial);
	}

	@Override
	public Editorial updateEditorial(Editorial editorial) {
		return editorialRepository.save(editorial);
	}

	@Override
	public void deleteAuthor(String id) {
		editorialRepository.deleteById(id);
	}

	@Override
	public Page<Editorial> filterAuthors(Specification<Editorial> spec, Pageable page) {
		return editorialRepository.findAll(spec, page);
	}

}
