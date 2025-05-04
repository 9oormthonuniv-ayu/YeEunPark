package com.pye.book.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;

    private String genreName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "genre")
    private List<BookGenre> bookGenres;
}
