package com.example.Bestioles;

import com.example.Bestioles.model.Animal;
import com.example.Bestioles.model.Species;
import com.example.Bestioles.repository.AnimalRepository;
import com.example.Bestioles.repository.SpeciesRepository;
import com.example.Bestioles.repository.PersonRepository;
import com.example.Bestioles.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BestiolesApplication implements CommandLineRunner {

	private final AnimalRepository animalRepository;
	private final SpeciesRepository speciesRepository;
	private final PersonRepository personRepository;

	@Autowired
	public BestiolesApplication(AnimalRepository animalRepository, SpeciesRepository speciesRepository, PersonRepository personRepository) {
		this.animalRepository = animalRepository;
		this.speciesRepository = speciesRepository;
		this.personRepository = personRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Liste des animaux avant ajout :");
		animalRepository.findAll().forEach(System.out::println);

		// Recherche des animaux appartenant à une espèce spécifique
		Optional<Species> speciesOptional = speciesRepository.findById(1);
		if (speciesOptional.isPresent()) {
			List<Animal> animauxMammiferes = animalRepository.findBySpecies(speciesOptional.get());
			System.out.println("Animaux appartenant à l'espèce avec ID 1 : " + animauxMammiferes);
		}

		// Vérifie si l'espèce "Mammifère" existe, sinon la créer
		Species mammifere = speciesRepository.findFirstByCommonNameIgnoreCase("Mammifère")
				.orElseGet(() -> {
					Species newSpecies = new Species();
					newSpecies.setCommonName("Mammifère");
					newSpecies.setLatinName("Mammalia");
					return speciesRepository.save(newSpecies);
				});

		// Vérifie si l'espèce "Reptile" existe, sinon la créer
		Species reptile = speciesRepository.findFirstByCommonNameIgnoreCase("Reptile")
				.orElseGet(() -> {
					Species newSpecies = new Species();
					newSpecies.setCommonName("Reptile");
					newSpecies.setLatinName("Reptilia");
					return speciesRepository.save(newSpecies);
				});

		// Création et ajout d'animaux
		Animal lion = new Animal();
		lion.setName("Lion");
		lion.setColor("Jaune");
		lion.setSex("Mâle");
		lion.setSpecies(mammifere);
		animalRepository.save(lion);

		Animal tigre = new Animal();
		tigre.setName("Tigre");
		tigre.setColor("Orange et noir");
		tigre.setSex("Mâle");
		tigre.setSpecies(mammifere);
		animalRepository.save(tigre);

		Animal crocodile = new Animal();
		crocodile.setName("Crocodile");
		crocodile.setColor("Vert");
		crocodile.setSex("Femelle");
		crocodile.setSpecies(reptile);
		animalRepository.save(crocodile);

		System.out.println("\nListe des animaux après ajout :");
		animalRepository.findAll().forEach(System.out::println);

		// Recherche d'un animal par son ID
		Optional<Animal> foundAnimal = animalRepository.findById(lion.getId());
		foundAnimal.ifPresent(animal -> System.out.println("\nAnimal trouvé : " + animal));

		// Suppression d'un animal et vérification du nombre restant
		animalRepository.delete(lion);
		System.out.println("\nNombre total d'animaux après suppression : " + animalRepository.count());

		// Recherche des animaux ayant une couleur spécifique
		List<String> couleurs = List.of("Jaune", "Vert");
		List<Animal> animauxFiltres = animalRepository.findByColorIn(couleurs);
		System.out.println("\nAnimaux dont la couleur est 'Jaune' ou 'Vert' : " + animauxFiltres);

		// Recherche des personnes par nom ou prénom
		List<Person> personnes = personRepository.findByLastnameOrFirstname("Dupont", "Jean");
		System.out.println("\nPersonnes avec nom 'Dupont' ou prénom 'Jean' : " + personnes);

		// Recherche des personnes d'un âge supérieur ou égal à 30
		List<Person> adultes = personRepository.findByAgeGreaterThanEqual(30);
		System.out.println("\nPersonnes âgées de 30 ans ou plus : " + adultes);
	}

	public static void main(String[] args) {
		SpringApplication.run(BestiolesApplication.class, args);
	}
}
