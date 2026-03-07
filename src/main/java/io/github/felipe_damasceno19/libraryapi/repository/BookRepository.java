package io.github.felipe_damasceno19.libraryapi.repository;

import io.github.felipe_damasceno19.libraryapi.model.Author;
import io.github.felipe_damasceno19.libraryapi.model.Book;
import jakarta.persistence.EnumType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    // Query method
    // select * from book where author_id = ?
    List<Book> findByAuthor(Author author);

    // select * from book where book_name = ?
    List<Book> findByName(String name);

    // select * from book where book_isbn = ?
    List<Book> findByIsbn(String isbn);

    // select * from book where book_name = ?, book_price = ?
    List<Book> findByNameAndPrice(String name, BigDecimal price);

    // select * from book where book_name = ? or book_isbn = ?
    List<Book> findByNameOrIsbn(String name, String isbn);
}

