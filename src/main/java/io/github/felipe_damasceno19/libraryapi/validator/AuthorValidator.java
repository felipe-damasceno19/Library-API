package io.github.felipe_damasceno19.libraryapi.validator;

import io.github.felipe_damasceno19.libraryapi.exceptions.DuplicateRegistrationException;
import io.github.felipe_damasceno19.libraryapi.model.Author;
import io.github.felipe_damasceno19.libraryapi.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorValidator {

    private final AuthorRepository repository;


    public void validate(Author author){
        if (existAuthoRegistered(author)){
            throw new DuplicateRegistrationException("Author already registered!");
        }
    }

    private boolean existAuthoRegistered(Author author){
        Optional<Author> foundAuthor = repository.findByNameAndBirthDateAndNationality(author.getName()
                , author.getBirthDate(), author.getNationality());

        if(author.getId() == null){
            return foundAuthor.isPresent();
        }

        return !author.getId().equals(foundAuthor.get().getId())
                && foundAuthor.isPresent();
    }
}
