package com.purihuaman.utils;

import com.purihuaman.model.PublisherEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PublisherSpecification {
	private static final String PUBLISHER_NAME = "name";
	private static final String IS_ACTIVE = "isActive";

	private static final Set<String> ALLOWED_KEY = Set.of(PUBLISHER_NAME, IS_ACTIVE);

	public static Specification<PublisherEntity> filterPublishers(Map<String, String> valuesToFilter) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			valuesToFilter.forEach((key, value) -> {
				if (!ALLOWED_KEY.contains(key)) return;
				if (value == null || value.trim().isEmpty()) return;

				switch (key) {
					case PUBLISHER_NAME:
						String nameValue = "%" + value.trim().toLowerCase() + "%";
						predicates.add(cb.like(cb.lower(root.get(PUBLISHER_NAME)), nameValue));
						break;
					case IS_ACTIVE:
						Boolean isActiveValue = Boolean.valueOf(value.trim().toLowerCase());
						predicates.add(cb.equal(root.get(IS_ACTIVE), isActiveValue));
						break;
				}
			});

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
