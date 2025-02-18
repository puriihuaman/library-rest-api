package com.purihuaman.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.purihuaman.dto.EditorialDTO;

public interface EditorialService {
	List<EditorialDTO> findAllEditorials(Pageable page);

	EditorialDTO findEditorialById(String id);

	EditorialDTO createEditorial(EditorialDTO editorial);

	EditorialDTO updateEditorial(String id, EditorialDTO editorial);

	void deleteEditorial(String id);

	List<EditorialDTO> filterAuthors(Map<String, String> valuesToFilter, Pageable page);
}
