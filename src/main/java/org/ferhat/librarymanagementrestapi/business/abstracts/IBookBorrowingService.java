package org.ferhat.librarymanagementrestapi.business.abstracts;

import org.ferhat.librarymanagementrestapi.entity.BookBorrowing;
import org.springframework.data.domain.Page;

public interface IBookBorrowingService {

    BookBorrowing save(BookBorrowing bookBorrowing);

    BookBorrowing update(BookBorrowing bookBorrowing);

    boolean delete(Long id);

    BookBorrowing get(Long id);

    Page<BookBorrowing> cursor(int page, int pageSize);
}
