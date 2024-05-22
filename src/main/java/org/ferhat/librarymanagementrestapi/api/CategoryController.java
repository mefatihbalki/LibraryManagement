package org.ferhat.librarymanagementrestapi.api;

import jakarta.validation.Valid;
import org.ferhat.librarymanagementrestapi.business.abstracts.ICategoryService;
import org.ferhat.librarymanagementrestapi.core.config.modelMapper.IModelMapperService;
import org.ferhat.librarymanagementrestapi.core.result.Result;
import org.ferhat.librarymanagementrestapi.core.result.ResultData;
import org.ferhat.librarymanagementrestapi.core.utils.ResultHelper;
import org.ferhat.librarymanagementrestapi.dto.request.category.CategorySaveRequest;
import org.ferhat.librarymanagementrestapi.dto.request.category.CategoryUpdateRequest;
import org.ferhat.librarymanagementrestapi.dto.response.CursorResponse;
import org.ferhat.librarymanagementrestapi.dto.response.category.CategoryResponse;
import org.ferhat.librarymanagementrestapi.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    private final ICategoryService categoryService;
    private final IModelMapperService modelMapperService;

    public CategoryController(ICategoryService categoryService, IModelMapperService modelMapperService) {
        this.categoryService = categoryService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest categorySaveRequest) {

        Category saveCategory = this.modelMapperService.forRequest().map(categorySaveRequest, Category.class);
        this.categoryService.save(saveCategory);
        return ResultHelper.created(this.modelMapperService.forResponse().map(saveCategory, CategoryResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> get(@PathVariable("id") Long id) {
        Category category = this.categoryService.get(id);
        return ResultHelper.success(this.modelMapperService.forResponse().map(category, CategoryResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {

        Page<Category> categoryPage = this.categoryService.cursor(page, pageSize);
        Page<CategoryResponse> categoryResponsePage = categoryPage
                .map(category -> this.modelMapperService.forResponse().map(category, CategoryResponse.class));

        return ResultHelper.cursor(categoryResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        Category updateCategory = this.modelMapperService.forRequest().map(categoryUpdateRequest, Category.class);
        this.categoryService.update(updateCategory);
        return ResultHelper.success(this.modelMapperService.forResponse().map(updateCategory, CategoryResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.categoryService.delete(id);
        return ResultHelper.ok();
    }
}
