package org.ferhat.librarymanagementrestapi.dto.response.book;

import lombok.*;
import org.ferhat.librarymanagementrestapi.entity.Category;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private Long id;
    private String name;
    private LocalDate publicationYear;
    private int stock;
    private Long authorId;
    private Long publisherId;
    private List<Category> categoryList;
}
