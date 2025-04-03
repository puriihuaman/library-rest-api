package com.purihuaman.service;

import com.purihuaman.dto.PublisherDTO;
import com.purihuaman.mapper.PublisherMapper;
import com.purihuaman.model.PublisherEntity;
import com.purihuaman.repository.PublisherRepository;
import com.purihuaman.service.use_case.PublisherServiceUseCase;
import com.purihuaman.utils.PublisherSpecification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class PublisherService implements PublisherServiceUseCase {
	private final PublisherRepository publisherRepository;
	private final PublisherMapper publisherMapper;

	public PublisherService(
		PublisherRepository publisherRepository,
		PublisherMapper publisherMapper
	)
	{
		this.publisherRepository = publisherRepository;
		this.publisherMapper = publisherMapper;
	}

	@Override
	public List<PublisherDTO> findAllPublishers(Pageable page) {
		List<PublisherEntity> publisherEntities = publisherRepository.findAll(page).getContent();
		return publisherMapper.toDTOList(publisherEntities);
	}

	@Override
	public PublisherDTO findPublisherById(UUID publisherId) {
		try {
			Optional<PublisherEntity> result = publisherRepository.findById(publisherId);
			if (result.isEmpty()) {
				throw new Exception("Error");
			}

			return publisherMapper.toDTO(result.get());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PublisherDTO createPublisher(PublisherDTO publisherDTO) {
		PublisherEntity publisherEntityToSave = publisherMapper.toEntity(publisherDTO);


		return publisherMapper.toDTO(publisherRepository.save(publisherEntityToSave));
	}

	@Override
	public PublisherDTO updatePublisher(UUID publisherId, PublisherDTO publisherDTO) {
		PublisherDTO existingPublisherDTO = this.findPublisherById(publisherId);

		existingPublisherDTO.setName(publisherDTO.getName());

		PublisherEntity publisherEntityToSave = publisherMapper.toEntity(existingPublisherDTO);
		PublisherDTO
			updated =
			publisherMapper.toDTO(publisherRepository.save(publisherEntityToSave));
		return updated;
	}

	@Override
	public void deletePublisher(UUID publisherId) {
		PublisherDTO editorialFound = this.findPublisherById(publisherId);
		publisherRepository.deleteById(editorialFound.getId());
	}

	@Override
	public List<PublisherDTO> filterPublishers(Map<String, String> valuesToFilter, Pageable page) {
		Specification<PublisherEntity>
			spec =
			PublisherSpecification.filterPublishers(valuesToFilter);

		return publisherMapper.toDTOList(publisherRepository.findAll(spec, page).getContent());
	}
}
