package org.ferhat.librarymanagementrestapi.business.abstracts;

import org.ferhat.librarymanagementrestapi.entity.Publisher;
import org.springframework.data.domain.Page;

public interface IPublisherService {

    Publisher save(Publisher publisher);

    Publisher update(Publisher publisher);

    boolean delete(Long id);

    Publisher get(Long id);

    Page<Publisher> cursor(int page, int pageSize);
}
