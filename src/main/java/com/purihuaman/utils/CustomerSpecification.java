package com.purihuaman.utils;

import com.purihuaman.model.CustomerEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomerSpecification {
	private static final String DOCUMENT_ID = "documentId";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String EMAIL = "email";
	private static final String PHONE_NUMBER = "phoneNumber";
	private static final Set<String> ALLOWED_KEY = Set.of(
		DOCUMENT_ID,
		FIRST_NAME,
		LAST_NAME,
		EMAIL,
		PHONE_NUMBER
	);

	public static Specification<CustomerEntity> filterCustomers(Map<String, String> valuesToFilter) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			valuesToFilter.forEach((key, value) -> {
				if (!ALLOWED_KEY.contains(key)) return;

				if (value == null || value.trim().isEmpty()) return;

				switch (key) {
					case DOCUMENT_ID:
						String documentId = "%" + value.trim().toLowerCase() + "%";
						predicates.add(cb.like(cb.lower(root.get(DOCUMENT_ID)), documentId));
						break;
					case FIRST_NAME:
						String firstName = "%" + value.trim().toLowerCase() + "%";
						predicates.add(cb.like(cb.lower(root.get(FIRST_NAME)), firstName));
						break;
					case LAST_NAME:
						String lastName = "%" + value.trim().toLowerCase() + "%";
						predicates.add(cb.like(cb.lower(root.get(LAST_NAME)), lastName));
						break;
					case EMAIL:
						String email = "%" + value.trim().toLowerCase() + "%";
						predicates.add(cb.like(cb.lower(root.get(EMAIL)), email));
						break;
					case PHONE_NUMBER:
						String phoneNumber = "%" + value.trim().toLowerCase() + "%";
						predicates.add(cb.like(cb.lower(root.get(PHONE_NUMBER)), phoneNumber));
						break;
				}
			});

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
