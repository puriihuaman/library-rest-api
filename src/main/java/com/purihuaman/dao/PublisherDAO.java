package com.purihuaman.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.purihuaman.model.PublisherEntity;

public interface PublisherDAO {
	List<PublisherEntity> findAllPublishers(Pageable page);

	Optional<PublisherEntity> findPublisherById(UUID publisherId);

	PublisherEntity createPublisher(PublisherEntity publisherEntity);

	PublisherEntity updatePublisher(PublisherEntity publisherEntity);

	void deletePublisher(UUID publisherId);

	Page<PublisherEntity> filterPublishers(Specification<PublisherEntity> spec, Pageable page);
}
