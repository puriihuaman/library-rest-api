package com.purihuaman.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.purihuaman.entity.Editorial;

public interface EditorialDAO {
	List<Editorial> findAllEditorials(Pageable page);

	Editorial findEditorialById(String id);

	Editorial createEditorial(Editorial editorial);

	Editorial updateEditorial(Editorial editorial);

	void deleteAuthor(String id);

	Page<Editorial> filterAuthors(Specification<Editorial> spec, Pageable page);
}
