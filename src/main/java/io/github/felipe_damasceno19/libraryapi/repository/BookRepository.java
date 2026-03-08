package io.github.felipe_damasceno19.libraryapi.repository;

import io.github.felipe_damasceno19.libraryapi.model.Author;
import io.github.felipe_damasceno19.libraryapi.model.Book;
import io.github.felipe_damasceno19.libraryapi.model.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    // Query method
    // select * from tb_book where author_id = ?
    List<Book> findByAuthor(Author author);

    // select * from tb_book where book_name = ?
    List<Book> findByName(String name);

    // select * from tb_book where book_isbn = ?
    List<Book> findByIsbn(String isbn);

    // select * from tb_book where book_name = ?, book_price = ?
    List<Book> findByNameAndPrice(String name, BigDecimal price);

    // select * from tb_book where book_name = ? or book_isbn = ?
    List<Book> findByNameOrIsbn(String name, String isbn);

    // select * from tb_book where book_publication_date between ? and ?
    List<Book> findByPublicationDateBetween(LocalDate start, LocalDate end);


    // select * from tb_book where book_name like ?
    // Pode usar tambem o findBy...Containing , ou StartingWith, ou EndingWith. Tudo depende da situação
    List<Book> findByNameLike(String name);

    // JPQL -> referencia as entidades e as propriedades da entidade, e não do banco
    @Query("select b from Book as b order by b.name")
    List<Book> listAllBooksWithJQPL();

    @Query("select a from Book b join b.author a")
    List<Author> listAuthorsFromBooks();

    @Query("select b.name, a.name from Book b join b.author a")
    List<String> listBookAndAuthorNames();

    // named parameters -> parametros nomeados
    @Query("select b from Book b where b.genre = :genre")
    List<Book> findByGenre(@Param("genre") BookGenre bookGenre);

    // positional parameters -> parametros posicionais
    @Query("select b from Book b where b.genre = ?1")
    List<Book> findByGenrePositionalParameters(BookGenre bookGenre);




}

