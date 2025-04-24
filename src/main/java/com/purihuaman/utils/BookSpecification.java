package com.purihuaman.utils;

import com.purihuaman.model.BookEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BookSpecification {
	private static final String TITLE = "title";
	private static final String YEAR = "year";
	private static final String FIRST_NAME = "firstName";
	private static final String NAME = "Name";

	private static final Set<String> ALLOWED_KEY = Set.of(TITLE, YEAR, FIRST_NAME, NAME);

	public static Specification<BookEntity> filterBooks(Map<String, String> valuesToFilter) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			valuesToFilter.forEach((key, value) -> {
				if (!ALLOWED_KEY.contains(key)) return;
				if (value == null || value.trim().isEmpty()) return;

				switch (key) {
					case TITLE:
						String title = "%" + value.trim().toLowerCase() + "%";
						predicates.add(cb.like(cb.lower(root.get(TITLE)), title));
						break;
					case YEAR:
						LocalDate year = LocalDate.parse(value.trim());
						LocalDate now = LocalDate.now();
						predicates.add(cb.between(root.get(YEAR), now, year));
						break;
					case FIRST_NAME:
						String firstName = "%" + value.trim().toLowerCase() + "%";
						predicates.add(cb.like(cb.lower(root.get(FIRST_NAME)), firstName));
						break;
					case NAME:
						String name = "%" + value.trim().toLowerCase() + "%";
						predicates.add(cb.like(cb.lower(root.get(NAME)), name));
						break;
				}
			});

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
