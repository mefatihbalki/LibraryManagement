package org.ferhat.librarymanagementrestapi.business.impl;

import org.ferhat.librarymanagementrestapi.business.abstracts.IAuthorService;
import org.ferhat.librarymanagementrestapi.core.exception.NotFoundException;
import org.ferhat.librarymanagementrestapi.core.utils.Message;
import org.ferhat.librarymanagementrestapi.dao.AuthorRepo;
import org.ferhat.librarymanagementrestapi.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorManager implements IAuthorService {

    private final AuthorRepo authorRepo;

    public AuthorManager(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public Author update(Author author) {
        this.get(author.getId());
        return this.authorRepo.save(author);
    }

    @Override
    public boolean delete(Long id) {
        Author author = this.get(id);
        this.authorRepo.delete(author);
        return true;
    }

    @Override
    public Author get(Long id) {
        return this.authorRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public Page<Author> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.authorRepo.findAll(pageable);
    }
}
