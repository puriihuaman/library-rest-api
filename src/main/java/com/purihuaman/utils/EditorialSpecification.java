package com.purihuaman.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.jpa.domain.Specification;

import com.purihuaman.entity.Editorial;

import jakarta.persistence.criteria.Predicate;

public class EditorialSpecification {
	private static final Set<String> ALLOWED_KEY = Set.of("name", "is_active");

	public static Specification<Editorial> filterEditorials(Map<String, String> valuesToFilter) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			valuesToFilter.forEach((key, value) -> {
				if (!ALLOWED_KEY.contains(key))
					return;
				if (value == null || value.trim().isEmpty())
					return;

				switch (key) {
				case "name":
					predicates.add(criteriaBuilder.like(root.get("name"), "%" + value + "%"));
					break;
				case "is_active":
					predicates.add(criteriaBuilder.equal(root.get("name"), Boolean.parseBoolean(value)));
					break;
				}
			});

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
