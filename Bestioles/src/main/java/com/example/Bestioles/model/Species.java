package com.example.Bestioles.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "species")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "common_name", nullable = false)
    private String commonName;

    @Column(name = "latin_name", nullable = false)
    private String latinName;

    @OneToMany(mappedBy = "species", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Animal> animals = new HashSet<>();

    public Species() {}

    public Species(String commonName, String latinName) {
        this.commonName = commonName;
        this.latinName = latinName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        animal.setSpecies(this);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
        animal.setSpecies(null);
    }

    @Override
    public String toString() {
        return "Species{" +
                "id=" + id +
                ", commonName='" + commonName + '\'' +
                ", latinName='" + latinName + '\'' +
                ", animalsCount=" + animals.size() +
                '}';
    }

}
