package org.ferhat.librarymanagementrestapi.api;

import jakarta.validation.Valid;
import org.ferhat.librarymanagementrestapi.business.abstracts.IPublisherService;
import org.ferhat.librarymanagementrestapi.core.config.modelMapper.IModelMapperService;
import org.ferhat.librarymanagementrestapi.core.result.Result;
import org.ferhat.librarymanagementrestapi.core.result.ResultData;
import org.ferhat.librarymanagementrestapi.core.utils.ResultHelper;
import org.ferhat.librarymanagementrestapi.dto.request.publisher.PublisherSaveRequest;
import org.ferhat.librarymanagementrestapi.dto.request.publisher.PublisherUpdateRequest;
import org.ferhat.librarymanagementrestapi.dto.response.CursorResponse;
import org.ferhat.librarymanagementrestapi.dto.response.publisher.PublisherResponse;
import org.ferhat.librarymanagementrestapi.entity.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {

    private final IPublisherService publisherService;
    private final IModelMapperService modelMapperService;

    public PublisherController(IPublisherService publisherService, IModelMapperService modelMapperService) {
        this.publisherService = publisherService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<PublisherResponse> save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest) {

        Publisher savePublisher = this.modelMapperService.forRequest().map(publisherSaveRequest, Publisher.class);
        this.publisherService.save(savePublisher);
        return ResultHelper.created(this.modelMapperService.forResponse().map(savePublisher, PublisherResponse.class));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> get(@PathVariable("id") Long id) {
        Publisher publisher = this.publisherService.get(id);
        return ResultHelper.success(this.modelMapperService.forResponse().map(publisher, PublisherResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<PublisherResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {

        Page<Publisher> publisherPage = this.publisherService.cursor(page, pageSize);
        Page<PublisherResponse> publisherResponsePage = publisherPage
                .map(publisher -> this.modelMapperService.forResponse().map(publisher, PublisherResponse.class));

        return ResultHelper.cursor(publisherResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> update(@Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest) {
        Publisher updatePublisher = this.modelMapperService.forRequest().map(publisherUpdateRequest, Publisher.class);
        this.publisherService.update(updatePublisher);
        return ResultHelper.created(this.modelMapperService.forResponse().map(updatePublisher, PublisherResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.publisherService.delete(id);
        return ResultHelper.ok();
    }

}
