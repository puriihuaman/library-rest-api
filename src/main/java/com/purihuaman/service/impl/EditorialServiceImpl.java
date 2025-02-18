package com.purihuaman.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.purihuaman.dao.EditorialDAO;
import com.purihuaman.dto.EditorialDTO;
import com.purihuaman.entity.Editorial;
import com.purihuaman.mapper.EditorialMapper;
import com.purihuaman.service.EditorialService;
import com.purihuaman.utils.EditorialSpecification;

@Service
public class EditorialServiceImpl implements EditorialService {
	private final EditorialDAO editorialDao;
	private final EditorialMapper mapper;

	public EditorialServiceImpl(EditorialDAO dao, EditorialMapper mapper) {
		this.editorialDao = dao;
		this.mapper = mapper;
	}

	@Override
	public List<EditorialDTO> findAllEditorials(Pageable page) {
		return mapper.toEditorialDTOList(editorialDao.findAllEditorials(page));
	}

	@Override
	public EditorialDTO findEditorialById(String id) {
		return mapper.toEditorialDTO(editorialDao.findEditorialById(id));
	}

	@Override
	public EditorialDTO createEditorial(EditorialDTO editorial) {
		editorial.setId(UUID.randomUUID().toString());
		Editorial editorialToSave = mapper.toEditorial(editorial);

		return mapper.toEditorialDTO(editorialDao.createEditorial(editorialToSave));
	}

	@Override
	public EditorialDTO updateEditorial(String id, EditorialDTO editorial) {
		EditorialDTO editorialFound = this.findEditorialById(id);

		editorialFound.setName(editorial.getName());
		editorialFound.setIsActive(editorial.getIsActive());

		EditorialDTO updated = mapper.toEditorialDTO(editorialDao.updateEditorial(mapper.toEditorial(editorialFound)));
		return updated;
	}

	@Override
	public void deleteEditorial(String id) {
		EditorialDTO editorialFound = this.findEditorialById(id);
		editorialDao.deleteAuthor(editorialFound.getId());
	}

	@Override
	public List<EditorialDTO> filterAuthors(Map<String, String> valuesToFilter, Pageable page) {
		Specification<Editorial> spec = EditorialSpecification.filterEditorials(valuesToFilter);
		return mapper.toEditorialDTOList(editorialDao.filterAuthors(spec, page).getContent());
	}

}
