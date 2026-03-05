package io.github.felipe_damasceno19.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_book")
@Data
@ToString(exclude = "author")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "book_isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "book_name", length = 150, nullable = false)
    private String name;

    @Column(name = "book_publication_date", nullable = false)
    private LocalDate publicationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_genre", length = 30, nullable = false)
    private bookGenre genre;

    @Column(name = "book_price"
            , precision = 18, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

}
