package io.github.felipe_damasceno19.libraryapi.controller.dto;

import io.github.felipe_damasceno19.libraryapi.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(UUID id,
        @NotBlank(message = "Required Field!")
        String name,
        @NotNull(message = "Required Field!")
        LocalDate birthDate,
        @NotBlank(message = "Required Field!")
        String nationality)
{

    public Author mappinngAuthor(){
        Author author = new Author();
        author.setName(this.name);
        author.setBirthDate(this.birthDate);
        author.setNationality(this.nationality);
        return author;
    }

}
