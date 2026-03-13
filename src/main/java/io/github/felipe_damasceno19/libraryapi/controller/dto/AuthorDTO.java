package io.github.felipe_damasceno19.libraryapi.controller.dto;

import io.github.felipe_damasceno19.libraryapi.model.Author;

import java.time.LocalDate;

public record AuthorDTO(String name,
                        LocalDate birthDate,
                        String nationality) {

    public Author mappinngAuthor(){
        Author author = new Author();
        author.setName(this.name);
        author.setBirthDate(this.birthDate);
        author.setNationality(this.nationality);
        return author;
    }

}
