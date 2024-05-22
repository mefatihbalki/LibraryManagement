package org.ferhat.librarymanagementrestapi.business.impl;

import org.ferhat.librarymanagementrestapi.business.abstracts.IBookBorrowingService;
import org.ferhat.librarymanagementrestapi.core.exception.NotFoundException;
import org.ferhat.librarymanagementrestapi.core.utils.Message;
import org.ferhat.librarymanagementrestapi.dao.BookBorrowingRepo;
import org.ferhat.librarymanagementrestapi.dao.BookRepo;
import org.ferhat.librarymanagementrestapi.entity.Book;
import org.ferhat.librarymanagementrestapi.entity.BookBorrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookBorrowingManager implements IBookBorrowingService {
    private final BookBorrowingRepo bookBorrowingRepo;
    private final BookRepo bookRepo;

    public BookBorrowingManager(BookBorrowingRepo bookBorrowingRepo, BookRepo bookRepo) {
        this.bookBorrowingRepo = bookBorrowingRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {

        Book book = bookRepo.findById(bookBorrowing.getBook().getId()).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
        if (book.getStock() > 0) {
            book.setStock(book.getStock() - 1);
            bookRepo.save(book);
            bookBorrowing.setBook(book);
            return this.bookBorrowingRepo.save(bookBorrowing);
        } else {
            throw new NotFoundException(Message.STOCK_NOT_ENOUGH);
        }
    }

    @Override
    public BookBorrowing update(BookBorrowing bookBorrowing) {
        bookBorrowing.setBorrowingDate(LocalDate.now());
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public boolean delete(Long id) {
        BookBorrowing bookBorrowing = this.get(id);
        this.bookBorrowingRepo.delete(bookBorrowing);
        return true;
    }

    @Override
    public BookBorrowing get(Long id) {
        return this.bookBorrowingRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public Page<BookBorrowing> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.bookBorrowingRepo.findAll(pageable);
    }
}
