package com.purihuaman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.purihuaman.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String>, JpaSpecificationExecutor<Author> {
}
