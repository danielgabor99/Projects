package springjpa.bookstore.core.service;

import org.springframework.stereotype.Service;
import springjpa.bookstore.core.domain.Animal;

import java.util.List;
@Service
public class AnimalServiceImpl implements AnimalServiceInterface {
    @Override
    public List<Animal> getAllAnimals() {
        return null;
    }

    @Override
    public List<Animal> getAnimalsByTypeAgeAndName(String type, int age, String name) {
        return null;
    }

    @Override
    public void addAnimal(Animal animal) {

    }

    @Override
    public List<Animal> topNAnimals(int n) {
        return null;
    }

    @Override
    public void deleteAnimal(Long animalId) {

    }
}
