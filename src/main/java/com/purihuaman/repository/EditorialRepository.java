package com.purihuaman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.purihuaman.entity.Editorial;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, String>, JpaSpecificationExecutor<Editorial> {
}
