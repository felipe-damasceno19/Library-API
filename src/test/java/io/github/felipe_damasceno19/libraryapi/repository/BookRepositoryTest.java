package io.github.felipe_damasceno19.libraryapi.repository;

import io.github.felipe_damasceno19.libraryapi.model.Author;
import io.github.felipe_damasceno19.libraryapi.model.Book;
import io.github.felipe_damasceno19.libraryapi.model.bookGenre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static io.github.felipe_damasceno19.libraryapi.model.bookGenre.NOVEL;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void saveTest(){
        Book book = new Book();

        book.setIsbn("89274-6742");
        book.setName("The Posthumous Memoirs of Brás Cubas");
        book.setGenre(NOVEL);
        book.setPublicationDate(LocalDate.of(1880, 2,21));
        book.setPrice(BigDecimal.valueOf(45));

        Author author = authorRepository
                .findById(UUID.fromString("1ebf3f40-4518-4a9c-8d9c-27db95ac3404"))
                .orElse(null);

        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Test
    public void saveCascadeTest(){
        Book book = new Book();

        book.setIsbn("89274-6742");
        book.setName("The Posthumous Memoirs of Brás Cubas");
        book.setGenre(NOVEL);
        book.setPublicationDate(LocalDate.of(1880, 2,14));
        book.setPrice(BigDecimal.valueOf(45));

        Author author = new Author();
        author.setName("Fiódor Dostoievski");
        author.setNationality("Russian");
        author.setBirthDate(LocalDate.of(1840, 7, 21));

        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Test
    public void saveAuthorAndBookTest(){
        Book book = new Book();

        book.setIsbn("98842-4342");
        book.setName("Crime and Punishment");
        book.setGenre(NOVEL);
        book.setPublicationDate(LocalDate.of(1899, 9,1));
        book.setPrice(BigDecimal.valueOf(83));

        Author author = new Author();
        author.setName("Fiódor Dostoievski");
        author.setNationality("Russian");
        author.setBirthDate(LocalDate.of(1840, 7, 21));

        authorRepository.save(author);

        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Test
    public void updateAuthorOfTheBook(){

        UUID id = UUID.fromString("3e6fef00-45a1-440d-bf01-bca11e76ad6e");
        var bookToUpdate = bookRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("16436a82-cb86-4546-8501-1a81f03dee8d");
                Author newAuthor = authorRepository.findById(idAutor).orElse(null);

        bookToUpdate.setAuthor(newAuthor);

        bookRepository.save(bookToUpdate);
    }

    @Test
    public void deleteBook(){
        UUID id = UUID.fromString("62ca0c8a-b2b8-4348-8dcc-01b1876e1ecf");
        var bookToDelete = bookRepository.findById(id).orElse(null);

        if(bookToDelete != null){
            bookRepository.delete(bookToDelete);
        }
    }

    @Test
    @Transactional //Mesmo utilizando Lazy no fetch type, ele consegue pegar as informações do autor dentro do transactional
    public void getBookTest(){

        UUID id = UUID.fromString("3e6fef00-45a1-440d-bf01-bca11e76ad6e");
        Book book = bookRepository.findById(id).orElse(null);

        System.out.println("Book Name: "+book.getName());
        System.out.println("Author : "+book.getAuthor().getName());

    }

    @Test
    public void getBookByNameTest(){

        String name = "Animal Farm";
        List<Book> bookName = bookRepository.findByName(name);
        bookName.forEach(System.out::println);

    }

    @Test
    public void getBookByIsbnTest(){
        String isbn = "89274-6742";
        List<Book> list = bookRepository.findByIsbn(isbn);
        list.forEach(System.out::println);
    }

    @Test
    public void getBookByNameAndPriceTest(){

        String name = "Animal Farm";
        var price = BigDecimal.valueOf(25.00);

        List<Book> list = bookRepository.findByNameAndPrice(name, price);
        list.forEach(System.out::println);

    }

    @Test
    public void getBookByNameOrIsbn(){

        String name = "The Posthumous Memoirs of Brás Cubas";
        String isbn = "89274-6742";

        List<Book> list = bookRepository.findByNameOrIsbn(null, isbn);
        list.forEach(System.out::println);


    }

   @Test
   public void getBookByDateBetween(){

       LocalDate start = LocalDate.of(1920, 01, 19);
       LocalDate end = LocalDate.of(1980, 01, 19);

       List<Book> list = bookRepository.findByPublicationDateBetween(start, end);
       list.forEach(System.out::println);
    }

   @Test
   public void getBookByNameIsLike(){

        String name = "The";
        List<Book> list = bookRepository.findByNameLike("%"+name+"%");

        list.forEach(System.out::println);

   }

   @Test
    public void getAllBooks(){
        List<Book> list = bookRepository.listAllBooksWithJQPL();
        list.forEach(System.out::println);
   }

   @Test
    public void getAuthorsFromBooks(){
        List<Author> list = bookRepository.listAuthorsFromBooks();
        list.forEach(System.out::println);
   }

   @Test
    public void getBookAndAuthorName(){
        List<String> list = bookRepository.listBookAndAuthorNames();
        list.forEach(System.out::println);
   }


}
