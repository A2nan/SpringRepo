package com.example.Bestioles.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "species")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "species")
    private Set<Animal> animals;


}
