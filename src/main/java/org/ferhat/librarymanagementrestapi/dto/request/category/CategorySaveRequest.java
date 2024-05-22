package org.ferhat.librarymanagementrestapi.dto.request.category;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategorySaveRequest {

    @NotNull(message = "Category name can't be null!")
    private String name;
    private String description;
}
