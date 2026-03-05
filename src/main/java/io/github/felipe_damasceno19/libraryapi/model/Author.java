package io.github.felipe_damasceno19.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@ToString(exclude = "books")
@Table(name = "tb_author", schema = "public")
public class Author {

    @Id
    @Column(name = "author_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "author_name", length = 100, nullable = false)
    private String name;

    @Column(name = "author_birthdate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "author_nationality", length = 50, nullable = false)
    private String nationality;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;

// Método auxiliar para adicionar livros em lista de um mesmo autor
//    public void addBook(Book book){
//        books.add(book);
//        book.setAuthor(this);
//    }

}
