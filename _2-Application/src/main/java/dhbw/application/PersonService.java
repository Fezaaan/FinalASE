package dhbw.application;

import dhbw.domain.PersonEntity;
import dhbw.domain.ShoppingListEntity;
import dhbw.domain.aggregate.ContactInfo;
import dhbw.domain.ports.ContactInfoRepository;
import dhbw.domain.ports.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final ContactInfoRepository contactInfoRepository;
    @Autowired
    public PersonService(PersonRepository personRepository, ContactInfoRepository contactInfoRepository) {
        this.personRepository = personRepository;
        this.contactInfoRepository=contactInfoRepository;
    }

    @Transactional
    public ContactInfo addContactInfoToPerson(Long personId, ContactInfo contactInfo) {
        PersonEntity person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found with id: " + personId));

        contactInfo.setPerson(person);
        person.setContactInfo(contactInfo);
        return contactInfoRepository.save(contactInfo);
    }

    @Transactional
    public ContactInfo updateContactInfo(Long contactInfoId, ContactInfo contactinfo) {
        ContactInfo contactInfo = contactInfoRepository.findById(contactInfoId)
                .orElseThrow(() -> new RuntimeException("ContactInfo not found with id: " + contactInfoId));

        contactInfo.setPhoneNumber(contactinfo.getPhoneNumber());
        contactInfo.setAddress(contactinfo.getAddress());
        contactInfo.setEmail(contactinfo.getEmail());
        return contactInfoRepository.save(contactInfo);
    }
//
    @Transactional
    public void deleteContactInfo(Long contactInfoId) {
        ContactInfo contactInfo = contactInfoRepository.findById(contactInfoId)
                .orElseThrow(() -> new RuntimeException("ContactInfo not found with id: " + contactInfoId));

        PersonEntity person = contactInfo.getPerson();
        if (person != null) {
            person.setContactInfo(null);
            personRepository.save(person);
        }

        contactInfoRepository.deleteById(contactInfoId);
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
