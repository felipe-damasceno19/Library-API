package io.github.felipe_damasceno19.libraryapi.controller.mappers;

import io.github.felipe_damasceno19.libraryapi.controller.dto.AuthorDTO;
import io.github.felipe_damasceno19.libraryapi.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// Transforma essa interface em um component que pode ser injetado aonde precisarmos
@Mapper(componentModel = "spring")
public interface AuthorMapper {

    //Caso os nomes sejam diferentes, voce pode mapear usando essa annotation
    @Mapping(source = "name", target = "name")
    Author toEntity(AuthorDTO authorDTO);

    AuthorDTO toDTO(Author author);


}
