package io.github.felipe_damasceno19.libraryapi.controller.dto;

import io.github.felipe_damasceno19.libraryapi.model.Book;
import io.github.felipe_damasceno19.libraryapi.model.BookGenre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record RegisterBookDTO(
        @NotBlank(message = "Required field")
        @Size(min = 10, max = 14, message = "Field out of bounds!")
        String isbn,
        @NotBlank(message = "Required field")
        @Size(min = 2, max = 100, message = "Field out of bounds!")
        String name,
        @Past(message = "Cannot be a future date")
        LocalDate publicationDate,
        BookGenre genre,
        BigDecimal price,
        @NotNull(message = "Required field")
        UUID authorId) {

    void mappingBook(){
        Book book = new Book();
        book.setIsbn(this.isbn);
        book.setName(this.name);
        book.setGenre(this.genre);
        book.setPrice(this.price);
    }
}
