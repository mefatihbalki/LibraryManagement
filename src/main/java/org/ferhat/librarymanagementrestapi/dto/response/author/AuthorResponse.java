package org.ferhat.librarymanagementrestapi.dto.response.author;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private String country;
}
