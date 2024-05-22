package org.ferhat.librarymanagementrestapi.dao;

import org.ferhat.librarymanagementrestapi.entity.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookBorrowingRepo extends JpaRepository<BookBorrowing, Long> {
}
