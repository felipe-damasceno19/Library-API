package io.github.felipe_damasceno19.libraryapi.service;

import io.github.felipe_damasceno19.libraryapi.exceptions.OperationNotAllowed;
import io.github.felipe_damasceno19.libraryapi.model.Author;
import io.github.felipe_damasceno19.libraryapi.repository.AuthorRepository;
import io.github.felipe_damasceno19.libraryapi.repository.BookRepository;
import io.github.felipe_damasceno19.libraryapi.validator.AuthorValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository repository;
    private final AuthorValidator validator;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository repository, AuthorValidator validator, BookRepository bookRepository){
        this.repository = repository;
        this.validator = validator;
        this.bookRepository = bookRepository;
    }

    public Author save(Author author){
        validator.validate(author);
        return repository.save(author);
    }

    public void update(Author author){
        if(author.getId() == null){
            throw new IllegalArgumentException("Author doesnt exist!");
        }
        validator.validate(author);
        repository.save(author);
    }

    public Optional<Author> getById(UUID id){
        return repository.findById(id);
    }

    public void delete(Author author){
       if(haveBook(author)){
           throw new OperationNotAllowed("Cannot delete author: registered books found");
       }
       repository.delete(author);
    }

    public List<Author> searchAuthor(String name, String nationality){

        if(name != null && nationality != null){
           return repository.findByNameAndNationality(name, nationality);
        }
        if(name != null){
            return repository.findByName(name);
        }
        if(nationality != null){
            return repository.findByNationality(nationality);
        }
        return repository.findAll();
    }

    public boolean haveBook(Author author){
        return bookRepository.existsByAuthor(author);
    }

}
