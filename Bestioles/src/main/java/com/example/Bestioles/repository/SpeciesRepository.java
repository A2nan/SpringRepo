package com.example.Bestioles.repository;

import com.example.Bestioles.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {

    // 1️⃣ Trouver la première espèce dont le nom commun correspond au paramètre fourni
    Optional<Species> findFirstByCommonNameIgnoreCase(String commonName);

    // 2️⃣ Trouver une liste d'espèces dont le nom latin contient le paramètre fourni, en ignorant la casse
    List<Species> findByLatinNameContainingIgnoreCase(String latinName);
}
