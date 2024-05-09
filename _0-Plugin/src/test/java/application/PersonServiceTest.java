package application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dhbw.application.PersonService;
import dhbw.ase.ListItCrudMavenApplication;
import dhbw.domain.PersonEntity;
import dhbw.domain.ShoppingListEntity;
import dhbw.domain.aggregate.ContactInfo;
import dhbw.domain.ports.ContactInfoRepository;
import dhbw.domain.ports.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(classes = ListItCrudMavenApplication.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private ContactInfoRepository contactInfoRepository;

    @InjectMocks
    private PersonService personService;

    private PersonEntity person;
    private ContactInfo contactInfo;

    @BeforeEach
    void setUp() {
        person = new PersonEntity();
        person.setPersonID(1L);
        person.setPersonName("John Doe");

        contactInfo = new ContactInfo();
        contactInfo.setPhoneNumber("1234567890");
    }

    @Test
    void addContactInfoToPersonShouldSaveContactInfo() {
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        when(contactInfoRepository.save(any(ContactInfo.class))).thenReturn(contactInfo);

        ContactInfo savedContactInfo = personService.addContactInfoToPerson(1L, contactInfo);

        assertNotNull(savedContactInfo);
        verify(contactInfoRepository).save(contactInfo);
        verify(personRepository).findById(1L);
    }

    @Test
    void updateContactInfoShouldUpdateDetails() {
        ContactInfo updatedContactInfo = new ContactInfo();
        updatedContactInfo.setPhoneNumber("0987654321");

        when(contactInfoRepository.findById(anyLong())).thenReturn(Optional.of(contactInfo));
        when(contactInfoRepository.save(any(ContactInfo.class))).thenReturn(updatedContactInfo);

        ContactInfo result = personService.updateContactInfo(1L, updatedContactInfo);

        assertNotNull(result);
        assertEquals("0987654321", result.getPhoneNumber());
        verify(contactInfoRepository).save(contactInfo);
    }

    @Test
    void deleteContactInfoShouldRemoveContactInfo() {
        when(contactInfoRepository.findById(anyLong())).thenReturn(Optional.of(contactInfo));
        doNothing().when(contactInfoRepository).deleteById(anyLong());

        personService.deleteContactInfo(1L);
        personService.save(person);

        verify(contactInfoRepository).deleteById(1L);
        verify(personRepository).save(person);
    }

    @Test
    void findByIdShouldReturnPerson() {
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));

        Optional<PersonEntity> foundPerson = personService.findById(1L);

        assertTrue(foundPerson.isPresent());
        assertEquals("John Doe", foundPerson.get().getPersonName());
        verify(personRepository).findById(1L);
    }
}
