package io.github.felipe_damasceno19.libraryapi.service;

import io.github.felipe_damasceno19.libraryapi.model.Author;
import io.github.felipe_damasceno19.libraryapi.model.Book;
import io.github.felipe_damasceno19.libraryapi.model.BookGenre;
import io.github.felipe_damasceno19.libraryapi.repository.AuthorRepository;
import io.github.felipe_damasceno19.libraryapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static io.github.felipe_damasceno19.libraryapi.model.BookGenre.FANTASY;
import static io.github.felipe_damasceno19.libraryapi.model.BookGenre.NOVEL;

@Service
public class TransactionService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    // È possível fazer uma atualização em um objeto sem ter que dar o save,
    // pois so de estar dentro de uma transaction ele ja faz isso
    @Transactional
    public void updateWithoutUpdate(){

        var book = bookRepository.findById(UUID.fromString("32bc34de-7e80-4512-b4c2-263fc6745a78")).orElse(null);
        book.setPublicationDate(LocalDate.of(1948, 11, 02));

    }

    @Transactional
    public void execute(){
        //salvar o autor

        Author author = new Author();
        author.setName("Gaston Leroux");
        author.setNationality("French");
        author.setBirthDate(LocalDate.of(1930, 7, 21));

        authorRepository.save(author);

        //salvar o livro
        Book book = new Book();
        book.setIsbn("98842-4342");
        book.setName("The Phantom of The Opera");
        book.setGenre(NOVEL);
        book.setPublicationDate(LocalDate.of(1954, 4,14));
        book.setPrice(BigDecimal.valueOf(55));

        book.setAuthor(author);

        bookRepository.save(book);

        if(author.getName().equals("Gaston Leroux")){
            throw new RuntimeException("Rollback!");
        }

    }
}


