package org.ferhat.librarymanagementrestapi.api;

import jakarta.validation.Valid;
import org.ferhat.librarymanagementrestapi.business.abstracts.*;
import org.ferhat.librarymanagementrestapi.core.config.modelMapper.IModelMapperService;
import org.ferhat.librarymanagementrestapi.core.result.Result;
import org.ferhat.librarymanagementrestapi.core.result.ResultData;
import org.ferhat.librarymanagementrestapi.core.utils.ResultHelper;
import org.ferhat.librarymanagementrestapi.dto.request.book.BookSaveRequest;
import org.ferhat.librarymanagementrestapi.dto.request.book.BookUpdateRequest;
import org.ferhat.librarymanagementrestapi.dto.response.CursorResponse;
import org.ferhat.librarymanagementrestapi.dto.response.author.AuthorResponse;
import org.ferhat.librarymanagementrestapi.dto.response.book.BookResponse;
import org.ferhat.librarymanagementrestapi.entity.Author;
import org.ferhat.librarymanagementrestapi.entity.Book;
import org.ferhat.librarymanagementrestapi.entity.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final IBookService bookService;
    private final IModelMapperService modelMapperService;
    private final IAuthorService authorService;
    private final ICategoryService categoryService;
    private final IBookBorrowingService bookBorrowingService;
    private final IPublisherService publisherService;

    public BookController(IBookService bookService, IModelMapperService modelMapperService,
                          IAuthorService authorService, ICategoryService categoryService,
                          IBookBorrowingService bookBorrowingService, IPublisherService publisherService) {
        this.bookService = bookService;
        this.modelMapperService = modelMapperService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookBorrowingService = bookBorrowingService;
        this.publisherService = publisherService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest) {

        Book saveBook = this.modelMapperService.forRequest().map(bookSaveRequest, Book.class);
        Author author = this.authorService.get(bookSaveRequest.getAuthorId());
        saveBook.setAuthor(author);
        Publisher publisher = this.publisherService.get(bookSaveRequest.getPublisherId());
        saveBook.setPublisher(publisher);
        this.bookService.save(saveBook);

        return ResultHelper.created(this.modelMapperService.forResponse().map(saveBook, BookResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> get(@PathVariable("id") Long id) {
        Book book = this.bookService.get(id);
        return ResultHelper.success(this.modelMapperService.forResponse().map(book, BookResponse.class));
    }

    @GetMapping("/{id}/author")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> getAuthor(@PathVariable("id") Long id) {
        Book book = this.bookService.get(id);
        return ResultHelper.success(this.modelMapperService.forResponse().map(book.getAuthor(), AuthorResponse.class));
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {

        Page<Book> bookPage = this.bookService.cursor(page, pageSize);
        Page<BookResponse> bookResponsePage = bookPage
                .map(book -> this.modelMapperService.forResponse().map(book, BookResponse.class));

        return ResultHelper.cursor(bookResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
        Book updateBook = this.modelMapperService.forRequest().map(bookUpdateRequest, Book.class);
        this.bookService.update(updateBook);
        return ResultHelper.success(this.modelMapperService.forResponse().map(updateBook, BookResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.bookService.delete(id);
        return ResultHelper.ok();
    }

}
