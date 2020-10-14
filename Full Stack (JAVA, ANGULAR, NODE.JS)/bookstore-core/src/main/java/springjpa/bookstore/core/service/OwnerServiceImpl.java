package springjpa.bookstore.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springjpa.bookstore.core.domain.Book;
import springjpa.bookstore.core.domain.Owner;
import springjpa.bookstore.core.repository.OwnerRepository;

import java.util.List;
@Service
public class OwnerServiceImpl implements OwnerServiceInterface {
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner getOwnerWithAnimals(Long ownerId) {
        return null;
    }

    @Override
    public Owner addAnimal(Long ownerId, Long animalId) {
        return null;
    }

    @Override
    public void deleteOwner(Long ownerId) {

    }
}
