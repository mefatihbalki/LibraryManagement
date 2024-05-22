package org.ferhat.librarymanagementrestapi.dto.request.book;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.ferhat.librarymanagementrestapi.entity.BookBorrowing;
import org.ferhat.librarymanagementrestapi.entity.Category;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {

    @NotNull(message = "Book name musn't be null!")
    private String name;
    private LocalDate publicationYear;
    private int stock;
    private Long authorId;
    private Long publisherId;
    private List<Category> categoryList;


}
