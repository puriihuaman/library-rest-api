package com.purihuaman.dao.impl;

import com.purihuaman.dao.PublisherDAO;
import com.purihuaman.model.PublisherEntity;
import com.purihuaman.repository.PublisherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PublisherDAOImpl implements PublisherDAO {
	private final PublisherRepository publisherRepository;

	public PublisherDAOImpl(PublisherRepository publisherRepository) {
		this.publisherRepository = publisherRepository;
	}

	@Override
	public List<PublisherEntity> findAllPublishers(Pageable page) {
		return publisherRepository.findAll(page).getContent();
	}

	@Override
	public Optional<PublisherEntity> findPublisherById(UUID editorialId) {
		return publisherRepository.findById(editorialId);
	}

	@Override
	public PublisherEntity createPublisher(PublisherEntity publisherEntity) {
		return publisherRepository.save(publisherEntity);
	}

	@Override
	public PublisherEntity updatePublisher(PublisherEntity publisherEntity) {
		return publisherRepository.save(publisherEntity);
	}

	@Override
	public void deletePublisher(UUID editorialId) {
		publisherRepository.deleteById(editorialId);
	}

	@Override
	public Page<PublisherEntity> filterPublishers(Specification<PublisherEntity> spec, Pageable page) {
		return publisherRepository.findAll(spec, page);
	}

}
