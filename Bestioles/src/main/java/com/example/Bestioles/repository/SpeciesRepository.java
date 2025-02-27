package com.example.Bestioles.repository;

import com.example.Bestioles.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {

    // 1️⃣ Trouver la première espèce dont le nom commun correspond au paramètre fourni
    Optional<Species> findFirstByCommonNameIgnoreCase(String commonName);

    // 2️⃣ Trouver une liste d'espèces dont le nom latin contient le paramètre fourni, en ignorant la casse
    List<Species> findByLatinNameContainingIgnoreCase(String latinName);

    // 1️⃣ Récupérer toutes les espèces, triées par nom commun en ordre ascendant
    @Query("SELECT s FROM Species s ORDER BY s.commonName ASC")
    List<Species> findAllOrderByCommonNameAsc();

    // 2️⃣ Rechercher les espèces dont le nom commun contient le paramètre fourni
    @Query("SELECT s FROM Species s WHERE s.commonName LIKE %:commonName%")
    List<Species> findByCommonNameLike(@Param("commonName") String commonName);
}
