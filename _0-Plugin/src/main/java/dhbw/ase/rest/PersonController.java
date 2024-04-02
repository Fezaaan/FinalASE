package dhbw.ase.rest;

import dhbw.domain.Person;
import dhbw.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{personId}/shopping-lists")
    public ResponseEntity<Person> getPersonShoppingLists(@PathVariable Long personId) {
        return personService.getPersonWithLists(personId)
                .map(person -> ResponseEntity.ok(person))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
