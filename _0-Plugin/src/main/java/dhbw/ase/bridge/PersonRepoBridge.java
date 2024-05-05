package dhbw.ase.bridge;

import dhbw.ase.repository.PersonJpaRepository;
import dhbw.domain.PersonEntity;
import dhbw.domain.ShoppingListEntity;
import dhbw.domain.ports.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class PersonRepoBridge implements PersonRepository {
    private final PersonJpaRepository personJpaRepository;

    public PersonRepoBridge(PersonJpaRepository personJpaRepository) {
        this.personJpaRepository = personJpaRepository;
    }

    @Override
    public Optional<PersonEntity> findById(Long id) {
        return personJpaRepository.findById(id);
    }

    @Override
    public List<PersonEntity> findAll() {
        return personJpaRepository.findAll();
    }

    @Override
    public PersonEntity save(PersonEntity person) {
        return personJpaRepository.save(person);
    }

    @Override
    public void deleteById(Long id) {
        personJpaRepository.deleteById(id);
    }

    @Override
    public List<ShoppingListEntity> findShoppingListsByPersonId(Long personId) {
        return personJpaRepository.findShoppingListsByPersonId(personId);
    }
}
