package org.ferhat.librarymanagementrestapi.dao;

import org.ferhat.librarymanagementrestapi.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepo extends JpaRepository<Publisher, Long> {
}
