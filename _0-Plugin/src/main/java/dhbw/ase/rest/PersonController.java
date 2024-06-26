package dhbw.ase.rest;

import dhbw.domain.PersonEntity;
import dhbw.domain.ShoppingListEntity;
import dhbw.application.PersonService;
import dhbw.domain.aggregate.ContactInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owner/")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonEntity> createPerson(@RequestBody PersonEntity person) {
        PersonEntity createdPerson = personService.save(person);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }
    @PostMapping("/{personId}/lists")
    public ResponseEntity<ShoppingListEntity> addShoppingListToPerson(
            @PathVariable Long personId,
            @RequestBody ShoppingListEntity shoppingList
    ) {
        Optional<ShoppingListEntity> createdShoppingList = Optional.ofNullable(personService.addShoppingListToPerson(personId, shoppingList));
        return createdShoppingList.map(shoppingListEntity -> new ResponseEntity<>(shoppingListEntity, HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @GetMapping("/{id}")
    public ResponseEntity<PersonEntity> getPersonById(@PathVariable Long id) {
        return personService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<PersonEntity>> getAllPersons() {
        List<PersonEntity> persons = personService.findAll();
        return ResponseEntity.ok(persons);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonEntity> updatePerson(@PathVariable Long id, @RequestBody PersonEntity person) {
        return personService.update(id, person)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{personId}/lists")
    public ResponseEntity<List<ShoppingListEntity>> getPersonShoppingLists(@PathVariable Long personId) {
        List<ShoppingListEntity> shoppingLists = personService.findShoppingListsByPersonId(personId);
        if (shoppingLists != null) {
            return ResponseEntity.ok(shoppingLists);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/{personId}/contactinfo")
    public ResponseEntity<ContactInfo> addContactInfo(@PathVariable Long personId, @RequestBody ContactInfo contactInfo) {
        ContactInfo newContactInfo = personService.addContactInfoToPerson(personId, contactInfo);
        return new ResponseEntity<>(newContactInfo, HttpStatus.CREATED);
    }

    @PutMapping("/contactinfo/{contactInfoId}")
    public ResponseEntity<ContactInfo> updateContactInfo(@PathVariable Long contactInfoId, @RequestBody ContactInfo contactInfo) {
        ContactInfo updatedContactInfo = personService.updateContactInfo(contactInfoId, contactInfo);
        return ResponseEntity.ok(updatedContactInfo);
    }

    @DeleteMapping("/contactinfo/{contactInfoId}")
    public ResponseEntity<Void> deleteContactInfo(@PathVariable Long contactInfoId) {
        personService.deleteContactInfo(contactInfoId);
        return ResponseEntity.noContent().build();
    }
}
