package org.ferhat.librarymanagementrestapi.api;

import jakarta.validation.Valid;
import org.ferhat.librarymanagementrestapi.business.abstracts.IAuthorService;
import org.ferhat.librarymanagementrestapi.core.config.modelMapper.IModelMapperService;
import org.ferhat.librarymanagementrestapi.core.result.Result;
import org.ferhat.librarymanagementrestapi.core.result.ResultData;
import org.ferhat.librarymanagementrestapi.core.utils.ResultHelper;
import org.ferhat.librarymanagementrestapi.dto.request.author.AuthorSaveRequest;
import org.ferhat.librarymanagementrestapi.dto.request.author.AuthorUpdateRequest;
import org.ferhat.librarymanagementrestapi.dto.response.CursorResponse;
import org.ferhat.librarymanagementrestapi.dto.response.author.AuthorResponse;
import org.ferhat.librarymanagementrestapi.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {
    private final IAuthorService authorService;
    private final IModelMapperService modelMapperService;

    public AuthorController(IAuthorService authorService, IModelMapperService modelMapperService) {
        this.authorService = authorService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest) {
        Author saveAuthor = this.modelMapperService.forRequest().map(authorSaveRequest, Author.class);
        this.authorService.save(saveAuthor);
        return ResultHelper.created(this.modelMapperService.forResponse().map(saveAuthor, AuthorResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> get(@PathVariable("id") Long id) {
        Author author = this.authorService.get(id);
        AuthorResponse authorResponse = this.modelMapperService.forResponse().map(author, AuthorResponse.class);
        return ResultHelper.success(authorResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AuthorResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {

        Page<Author> authorPage = this.authorService.cursor(page, pageSize);
        Page<AuthorResponse> authorResponsePage = authorPage
                .map(author -> this.modelMapperService.forResponse().map(author, AuthorResponse.class));

        return ResultHelper.cursor(authorResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest) {
        Author updateAuthor = this.modelMapperService.forRequest().map(authorUpdateRequest, Author.class);
        this.authorService.update(updateAuthor);
        return ResultHelper.success(this.modelMapperService.forResponse().map(updateAuthor, AuthorResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.authorService.delete(id);
        return ResultHelper.ok();
    }


}
