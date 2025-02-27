package com.example.Bestioles.model;

import jakarta.persistence.*;

@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true) // Peut être NULL
    private String color;

    @Column(nullable = false) // S'assurer que `sex` a toujours une valeur
    private String sex = "Unknown";

    @ManyToOne
    @JoinColumn(name = "species_id", nullable = false) // Relation avec Species
    private Species species;

    // ✅ Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    // ✅ Méthode toString pour affichage
    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", sex='" + sex + '\'' +
                ", species=" + (species != null ? species.getId() : "null") +
                '}';
    }
}
