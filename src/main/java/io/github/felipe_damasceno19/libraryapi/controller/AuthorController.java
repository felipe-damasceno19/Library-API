package io.github.felipe_damasceno19.libraryapi.controller;

import io.github.felipe_damasceno19.libraryapi.controller.dto.AuthorDTO;
import io.github.felipe_damasceno19.libraryapi.controller.dto.ResponseError;
import io.github.felipe_damasceno19.libraryapi.controller.mappers.AuthorMapper;
import io.github.felipe_damasceno19.libraryapi.exceptions.DuplicateRegistrationException;
import io.github.felipe_damasceno19.libraryapi.exceptions.OperationNotAllowed;
import io.github.felipe_damasceno19.libraryapi.model.Author;
import io.github.felipe_damasceno19.libraryapi.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService service;
    private final AuthorMapper mapper;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid AuthorDTO authorDTO){
        try{
            Author author = mapper.toEntity(authorDTO);
            service.save(author);

            // Pega os dados da requisição atual e constroi uma nova url, pegando o Id e colocando no url
            // localhost:8080/authors/id
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(author.getId())
                    .toUri();

            //Retorna o status de created e o URI de location que criamos
            return ResponseEntity.created(location).build();
        }
        catch (DuplicateRegistrationException e){
            var dtoError = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(dtoError.status()).body(dtoError);
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> get(@PathVariable("id") String id){
        var authorId = UUID.fromString(id);
        Optional<Author> optionalAuthor = service.getById(authorId);

        return service.getById(authorId)
            .map(author -> {
                AuthorDTO dto = mapper.toDTO(author);
                return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
        try{
            var authorId = UUID.fromString(id);
            Optional<Author> author = service.getById(authorId);

            if(author.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            service.delete(author.get());
            return ResponseEntity.noContent().build();
        }
        catch (OperationNotAllowed e){
            var dtoError = ResponseError.defaultResponse(e.getMessage());
            return ResponseEntity.status(dtoError.status()).body(dtoError);
        }

    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> search(@RequestParam(value = "name", required = false) String name
                ,@RequestParam(value = "nationality", required = false) String nationality){

        List<Author> result = service.searchByExample(name, nationality);
        List<AuthorDTO> list = result.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody @Valid AuthorDTO dto){
        try{
            var uuid = UUID.fromString(id);
            Optional<Author> authorOptional = service.getById(uuid);

            if(authorOptional.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            var author = authorOptional.get();
            author.setName(dto.name());
            author.setNationality(dto.nationality());
            author.setBirthDate(dto.birthDate());

            service.update(author);

            return ResponseEntity.noContent().build();
        }
        catch (DuplicateRegistrationException e){
            var dtoError = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(dtoError.status()).body(dtoError);
        }

    }



}
