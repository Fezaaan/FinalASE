package dhbw.application;

import dhbw.domain.PersonEntity;
import dhbw.domain.ShoppingListEntity;
import dhbw.domain.ports.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;


    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonEntity save(PersonEntity person) {
        return personRepository.save(person);
    }

    public Optional<PersonEntity> findById(Long id) {
        return personRepository.findById(id);
    }

    public List<PersonEntity> findAll() {
        return personRepository.findAll();
    }

    public Optional<PersonEntity> update(Long id, PersonEntity personDetails) {
        return personRepository.findById(id).map(person -> {
            person.setPersonName(personDetails.getPersonName());
            // Weitere Eigenschaften hier aktualisieren
            return personRepository.save(person);
        });
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    public ShoppingListEntity addShoppingListToPerson(Long personId, ShoppingListEntity shoppingList) {
        PersonEntity person = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Person with id " + personId + " not found"));

        shoppingList.setOwner(person);
        person.getShoppingLists().add(shoppingList);
        personRepository.save(person);
        return shoppingList;
    }

    public List<ShoppingListEntity> findShoppingListsByPersonId(Long personId) {
        return personRepository.findById(personId).map(PersonEntity::getShoppingLists)
                .orElseThrow(() -> new EntityNotFoundException("Person with id " + personId + " not found"));
    }
}
