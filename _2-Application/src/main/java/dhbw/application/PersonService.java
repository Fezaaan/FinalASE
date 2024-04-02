package dhbw.application;

import dhbw.ase.domain.Person;
import dhbw.ase.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> getPersonWithLists(Long personId) {
        // Hier wird angenommen, dass das Person-Objekt bereits die Listen und Items über JPA-Relationen lädt.
        return personRepository.findById(personId);
    }
}
