package org.ferhat.librarymanagementrestapi.dto.response.book_borrowing;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingResponse {
    private Long id;
    private String borrowerName;
    private String borrowerEmail;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
}
