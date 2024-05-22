package org.ferhat.librarymanagementrestapi.business.abstracts;

import org.ferhat.librarymanagementrestapi.entity.Author;
import org.springframework.data.domain.Page;

public interface IAuthorService {

    Author save(Author author);

    Author update(Author author);

    boolean delete(Long id);

    Author get(Long id);

    Page<Author> cursor(int page, int pageSize);
}
