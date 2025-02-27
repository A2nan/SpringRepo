package com.example.Bestioles;

import com.example.Bestioles.model.Animal;
import com.example.Bestioles.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class BestiolesApplication implements CommandLineRunner {

	private final AnimalRepository animalRepository;

	@Autowired
	public BestiolesApplication(AnimalRepository animalRepository) {
		this.animalRepository = animalRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Liste des animaux:");
		animalRepository.findAll().forEach(System.out::println);

		Animal animal1 = new Animal();
		animal1.setName("Lion");
		animalRepository.save(animal1);

		Animal animal2 = new Animal();
		animal2.setName("Tigre");
		animalRepository.save(animal2);

		Optional<Animal> foundAnimal = animalRepository.findById(animal1.getId());
		foundAnimal.ifPresent(System.out::println);

		animalRepository.delete(animal1);
		System.out.println("Nombre total d'animaux après suppression: " + animalRepository.count());
	}

	public static void main(String[] args) {
		SpringApplication.run(BestiolesApplication.class, args);
	}
}
