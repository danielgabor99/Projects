package springjpa.bookstore.core.service;

import springjpa.bookstore.core.domain.Animal;

import java.util.List;

public interface AnimalServiceInterface {
    List<Animal> getAllAnimals();
    List<Animal> getAnimalsByTypeAgeAndName(String type, int age, String name);
    void addAnimal(Animal animal);
    List<Animal> topNAnimals(int n);
    void deleteAnimal(Long animalId);

}
