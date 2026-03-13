package io.github.felipe_damasceno19.libraryapi.service;

import io.github.felipe_damasceno19.libraryapi.model.Author;
import io.github.felipe_damasceno19.libraryapi.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository){
        this.repository = repository;
    }

    public Author save(Author author){
        return repository.save(author);
    }

}
