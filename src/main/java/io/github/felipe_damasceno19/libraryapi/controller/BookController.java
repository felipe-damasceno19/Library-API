package io.github.felipe_damasceno19.libraryapi.controller;

import io.github.felipe_damasceno19.libraryapi.controller.dto.RegisterBookDTO;
import io.github.felipe_damasceno19.libraryapi.controller.dto.ResponseError;
import io.github.felipe_damasceno19.libraryapi.exceptions.DuplicateRegistrationException;
import io.github.felipe_damasceno19.libraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid RegisterBookDTO dto){
        try{
            return ResponseEntity.ok(dto);

        }
        catch (DuplicateRegistrationException e){
            var dtoError = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(dtoError.status()).body(dtoError);
        }

    }
}
