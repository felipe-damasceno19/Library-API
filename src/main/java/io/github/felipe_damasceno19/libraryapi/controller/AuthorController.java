package io.github.felipe_damasceno19.libraryapi.controller;

import io.github.felipe_damasceno19.libraryapi.controller.dto.AuthorDTO;
import io.github.felipe_damasceno19.libraryapi.model.Author;
import io.github.felipe_damasceno19.libraryapi.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AuthorDTO authorDTO){
        Author authorEntity = authorDTO.mappinngAuthor();
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

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("id") String id){
        var authorId = UUID.fromString(id);
        Optional<Author> author = service.getById(authorId);

        if(author.isPresent()){
            Author entity = author.get();
            AuthorDTO dto = new AuthorDTO(entity.getId(),entity.getName(),
                        entity.getBirthDate(), entity.getNationality());
            return ResponseEntity.ok(dto);

        }
        return ResponseEntity.notFound().build();
    }
    
}
