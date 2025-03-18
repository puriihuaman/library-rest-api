package com.purihuaman.repository;

import com.purihuaman.model.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublisherRepository
	extends JpaRepository<PublisherEntity, UUID>, JpaSpecificationExecutor<PublisherEntity> {}
