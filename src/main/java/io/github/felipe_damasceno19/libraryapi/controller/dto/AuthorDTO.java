package io.github.felipe_damasceno19.libraryapi.controller.dto;

import java.time.LocalDate;

public record AuthorDTO(String name,
                        LocalDate birthDate,
                        String nationality)
{}
