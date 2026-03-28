package io.github.felipe_damasceno19.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@ToString(exclude = "books")
@Table(name = "tb_author", schema = "public")
@EntityListeners(AuditingEntityListener.class)
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

    //È padrão utilizar fetch type lazy em relações one to many
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;

    //Assim que um autor for registrado, essa anotação faz com que a data do momento do registro seja salva
    @CreatedDate
    @Column(name = "author_register_date")
    private LocalDateTime registerDate;

    //Assim que um autor for atualizado, essa annotation salva o mommento da atualização
    @LastModifiedDate
    @Column(name = "author_update_date")
    private LocalDateTime updateDate;

    @Column(name = "user_id")
    private UUID userId;

    /*
         Método auxiliar para adicionar livros em lista de um mesmo autor
         public void addBook(Book book){
             books.add(book);
             book.setAuthor(this);
          }
      */


}
