package com.example.fammz.Actor;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.fammz.Cast.Cast;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate birthDate;
    private String biography;
    private String profileImageUrl;

    @JsonManagedReference
    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL)
    private List<Cast> castList;  // Relación con Cast
}
