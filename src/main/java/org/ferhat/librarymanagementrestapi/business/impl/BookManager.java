package org.ferhat.librarymanagementrestapi.business.impl;

import org.ferhat.librarymanagementrestapi.business.abstracts.IBookService;
import org.ferhat.librarymanagementrestapi.core.exception.NotFoundException;
import org.ferhat.librarymanagementrestapi.core.utils.Message;
import org.ferhat.librarymanagementrestapi.dao.BookRepo;
import org.ferhat.librarymanagementrestapi.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookManager implements IBookService {
    private final BookRepo bookRepo;

    public BookManager(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Book save(Book book) {
        return this.bookRepo.save(book);
    }

    @Override
    public Book update(Book book) {
        this.get(book.getId());
        return this.bookRepo.save(book);
    }

    @Override
    public boolean delete(Long id) {
        Book book = this.get(id);
        this.bookRepo.delete(book);
        return true;
    }

    @Override
    public Book get(Long id) {
        return this.bookRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public Page<Book> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.bookRepo.findAll(pageable);
    }
}
