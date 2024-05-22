package org.ferhat.librarymanagementrestapi.dao;

import org.ferhat.librarymanagementrestapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
