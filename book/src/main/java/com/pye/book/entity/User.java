package com.pye.book.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Entity;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, length = 100)
    private String email;

    private String password;

    private String phone;

    private LocalDate createdAt;

    @OneToMany(mappedBy = "user")
    private List<Rental> rentals;
}
