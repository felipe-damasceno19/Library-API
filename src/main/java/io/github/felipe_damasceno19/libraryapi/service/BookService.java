package io.github.felipe_damasceno19.libraryapi.service;

import io.github.felipe_damasceno19.libraryapi.repository.AuthorRepository;
import io.github.felipe_damasceno19.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;
}
