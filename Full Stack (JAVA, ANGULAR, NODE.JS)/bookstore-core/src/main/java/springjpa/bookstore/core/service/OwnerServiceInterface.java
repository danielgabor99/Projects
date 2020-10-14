package springjpa.bookstore.core.service;

import springjpa.bookstore.core.domain.Owner;

import java.util.List;

public interface OwnerServiceInterface {
    List<Owner> getAllOwners();
    Owner getOwnerWithAnimals(Long ownerId);
    Owner addAnimal(Long ownerId, Long animalId);
    void deleteOwner(Long ownerId);

}
