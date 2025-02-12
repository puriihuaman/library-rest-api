package com.purihuaman.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.jpa.domain.Specification;

import com.purihuaman.entity.Author;

import jakarta.persistence.criteria.Predicate;

public class AuthorSpecification {
	private static final Set<String> ALLOWED_KEY = Set.of("first_name", "email", "is_active");

	public static Specification<Author> filterAuthors(Map<String, String> valuesToFilter) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			valuesToFilter.forEach((key, value) -> {
				if (!ALLOWED_KEY.contains(key))
					return;

				if (value == null || value.trim().isEmpty())
					return;

				switch (key) {
				case "first_name":
					predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + value + "%"));
					break;

				case "email":
					predicates.add(criteriaBuilder.like(root.get("email"), "%" + value + "%"));
					break;
				case "is_active":
					predicates.add(criteriaBuilder.equal(root.get("isActive"), Boolean.parseBoolean(value)));
					break;
				}
			});

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
