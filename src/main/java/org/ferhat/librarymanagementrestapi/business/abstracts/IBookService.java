package org.ferhat.librarymanagementrestapi.business.abstracts;

import org.ferhat.librarymanagementrestapi.entity.Book;
import org.springframework.data.domain.Page;

public interface IBookService {

    Book save(Book book);

    Book update(Book book);

    boolean delete(Long id);

    Book get(Long id);

    Page<Book> cursor(int page, int pageSize);
}
