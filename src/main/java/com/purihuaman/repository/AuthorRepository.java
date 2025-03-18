package com.purihuaman.repository;

import com.purihuaman.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorRepository
	extends JpaRepository<AuthorEntity, UUID>, JpaSpecificationExecutor<AuthorEntity> {}
