package io.github.felipe_damasceno19.libraryapi.service;

import io.github.felipe_damasceno19.libraryapi.exceptions.OperationNotAllowed;
import io.github.felipe_damasceno19.libraryapi.model.Author;
import io.github.felipe_damasceno19.libraryapi.model.SystemUser;
import io.github.felipe_damasceno19.libraryapi.repository.AuthorRepository;
import io.github.felipe_damasceno19.libraryapi.repository.BookRepository;
import io.github.felipe_damasceno19.libraryapi.security.SecurityService;
import io.github.felipe_damasceno19.libraryapi.validator.AuthorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
//Gera um construtor para todas as propriedades que tem final no nome
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;
    private final AuthorValidator validator;
    private final BookRepository bookRepository;
    private final SecurityService securityService;

    public Author save(Author author){
        validator.validate(author);
        SystemUser user = securityService.getUserLogged();
        author.setUser(user);
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

    public List<Author> searchByExample(String name, String nationality){
        var author = new Author();
        author.setName(name);
        author.setNationality(nationality);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Author> authorExample = Example.of(author, matcher);

        return repository.findAll(authorExample);

    }

    public boolean haveBook(Author author){
        return bookRepository.existsByAuthor(author);
    }


}
