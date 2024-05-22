package org.ferhat.librarymanagementrestapi.dto.response.publisher;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponse {
    private Long id;
    private String name;
    private LocalDate establishmentYear;
}
