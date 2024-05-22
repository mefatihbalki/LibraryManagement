package org.ferhat.librarymanagementrestapi.dao;

import org.ferhat.librarymanagementrestapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
}
