package org.ferhat.librarymanagementrestapi.business.impl;

import org.ferhat.librarymanagementrestapi.business.abstracts.ICategoryService;
import org.ferhat.librarymanagementrestapi.dao.BookRepo;
import org.ferhat.librarymanagementrestapi.dao.CategoryRepo;
import org.ferhat.librarymanagementrestapi.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryManager implements ICategoryService {
    private final CategoryRepo categoryRepo;
    private final BookRepo bookRepo;

    public CategoryManager(CategoryRepo categoryRepo, BookRepo bookRepo) {
        this.categoryRepo = categoryRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Category update(Category category) {
        this.get(category.getId());
        return this.categoryRepo.save(category);
    }

    @Override
    public String delete(Long id) {
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            if (category.getBookList().isEmpty()) {
                categoryRepo.delete(category);
                return "Category deleted successfully";
            } else {
                return "Category has books";
            }
        }
        return "Category not found";
    }

    @Override
    public Category get(Long id) {
        return this.categoryRepo.findById(id).orElseThrow();
    }

    @Override
    public Page<Category> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.categoryRepo.findAll(pageable);
    }
}
