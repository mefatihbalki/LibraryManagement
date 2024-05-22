package org.ferhat.librarymanagementrestapi.dao;

import org.ferhat.librarymanagementrestapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
}
