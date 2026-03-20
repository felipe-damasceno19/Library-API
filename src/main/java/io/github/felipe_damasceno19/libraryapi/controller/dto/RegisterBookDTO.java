package io.github.felipe_damasceno19.libraryapi.controller.dto;

import io.github.felipe_damasceno19.libraryapi.model.BookGenre;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record RegisterBookDTO(String isbn,
          String name,
          LocalDate publicationDate,gi
          BookGenre genre,
          BigDecimal price,
          UUID authorId) {
}
