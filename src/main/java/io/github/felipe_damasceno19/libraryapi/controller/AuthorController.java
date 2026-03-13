package io.github.felipe_damasceno19.libraryapi.controller;

import io.github.felipe_damasceno19.libraryapi.controller.dto.AuthorDTO;
import io.github.felipe_damasceno19.libraryapi.model.Author;
import io.github.felipe_damasceno19.libraryapi.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AuthorDTO author){
        Author authorEntity = author.mappinngAuthor();
        service.save(authorEntity);

        // Pega os dados da requisição atual e constroi uma nova url, pegando o Id e colocando no url
        // localhost:8080/authors/id
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(authorEntity.getId())
                        .toUri();

        //Retorna o status de created e o URI de location que criamos
        return ResponseEntity.created(location).build();
    }



}
