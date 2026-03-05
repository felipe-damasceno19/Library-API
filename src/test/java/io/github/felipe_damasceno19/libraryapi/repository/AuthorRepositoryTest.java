package io.github.felipe_damasceno19.libraryapi.repository;

import io.github.felipe_damasceno19.libraryapi.model.Author;
import io.github.felipe_damasceno19.libraryapi.model.Book;
import io.github.felipe_damasceno19.libraryapi.model.bookGenre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void saveTest(){

        Author author = new Author();
        author.setName("Machado de Assis");
        author.setNationality("Brasileiro");
        author.setBirthDate(LocalDate.of(1839, 6, 21));

        var autorSalvo = authorRepository.save(author);
        System.out.println(autorSalvo);
    }

    @Test
    public void updateTest(){
        var id = UUID.fromString("87a09f88-7c4d-457e-bb2d-5d7d13a153ba");

        Optional<Author> author = authorRepository.findById(id);

       if(author.isPresent()){

           Author foundAuthor = author.get();
           System.out.println("Author data: "+ foundAuthor);

           foundAuthor.setNationality("Colombiana");
           System.out.println("Updated Author: " + foundAuthor);

           authorRepository.save(foundAuthor);
       }
    }

    @Test
    public void ListTest(){
        List<Author> list = authorRepository.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Count: "+authorRepository.count());
    }

    @Test
    public void removeByIdTest(){
        var id = UUID.fromString("87a09f88-7c4d-457e-bb2d-5d7d13a153ba");
        authorRepository.deleteById(id);

    }

    @Test
    public void removeByObjectTest(){
        var id = UUID.fromString("b6b3138b-992d-4f3e-ac87-a9e6ce66cc00");

        var jose = authorRepository.findById(id).get();
        authorRepository.delete(jose);

    }

    @Test
    public void saveAuthorAndBookTest(){

        Author author = new Author();
        author.setName("George Orwell");
        author.setNationality("British");
        author.setBirthDate(LocalDate.of(1905, 03, 17));

        Book book = new Book();
        book.setIsbn("12484-2463");
        book.setName("1984");
        book.setGenre(bookGenre.NOVEL);
        book.setPublicationDate(LocalDate.of(1955, 6,19));
        book.setPrice(BigDecimal.valueOf(35));
        book.setAuthor(author);

        Book book2 = new Book();
        book2.setIsbn("12484-2434");
        book2.setName("Animal Farm");
        book2.setGenre(bookGenre.NOVEL);
        book2.setPublicationDate(LocalDate.of(1950, 2,28));
        book2.setPrice(BigDecimal.valueOf(25));
        book2.setAuthor(author);

        author.setBooks(new ArrayList<>());
        author.getBooks().add(book);
        author.getBooks().add(book2);

        authorRepository.save(author);

        bookRepository.saveAll(author.getBooks());

    }

    @Test
    public void listAuthorBooks(){

        UUID id = UUID.fromString("f3eaa690-5b93-4b53-8044-ddb2008e6d83");
        var author = authorRepository.findById(id).get();

        //buscar os livros do autor
        List<Book> booksList = bookRepository.findByAuthor(author);
        author.setBooks(booksList);

        author.getBooks().forEach(System.out::println);
    }
}
