package com.purihuaman.repository;

import com.purihuaman.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository
	extends JpaRepository<BookEntity, UUID>, JpaSpecificationExecutor<BookEntity> {
	@Modifying
	@Query(
		"UPDATE BOOK b SET b.borrowedCount = b.borrowedCount + :amount, b.availableCopies = b.totalCopies - b.borrowedCount WHERE b.id = :bookId"
	)
	void updateBorrowedBook(@Param("bookId") UUID bookId, @Param("amount") Integer amount);
}
