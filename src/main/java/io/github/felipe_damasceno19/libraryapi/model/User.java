package io.github.felipe_damasceno19.libraryapi.model;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "user_login", nullable = false)
    private String login;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Type(ListArrayType.class)
    @Column(name = "user_roles", columnDefinition = "varchar[]")
    private List<String> roles;

}
