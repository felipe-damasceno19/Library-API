package io.github.felipe_damasceno19.libraryapi.controller.dto;

import io.github.felipe_damasceno19.libraryapi.model.BookGenre;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SearchResultDTO(String isbn,
         String name,
         LocalDate publicationDate,
         BookGenre genre,
         BigDecimal price,
         AuthorDTO author)
{
}
