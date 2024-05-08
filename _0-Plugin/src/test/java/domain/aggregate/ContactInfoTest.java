package domain.aggregate;

import static org.junit.jupiter.api.Assertions.*;

import dhbw.domain.PersonEntity;
import dhbw.domain.aggregate.ContactInfo;
import org.junit.jupiter.api.Test;

class ContactInfoTest {

    @Test
    void testContactInfoSettersAndGetters() {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setId(1L);
        contactInfo.setPhoneNumber("1234567890");
        contactInfo.setAddress("Test Street 123");
        contactInfo.setEmail("test@example.com");

        PersonEntity person = new PersonEntity();
        person.setPersonID(1L);
        person.setPersonName("John Doe");

        contactInfo.setPerson(person);

        assertEquals(1L, contactInfo.getId(), "ID should match the set value.");
        assertEquals("1234567890", contactInfo.getPhoneNumber(), "Phone number should match the set value.");
        assertEquals("Test Street 123", contactInfo.getAddress(), "Address should match the set value.");
        assertEquals("test@example.com", contactInfo.getEmail(), "Email should match the set value.");
        assertEquals(person, contactInfo.getPerson(), "Associated person should match the set person.");
    }

    @Test
    void testContactInfoCreation() {
        PersonEntity person = new PersonEntity();
        person.setPersonID(2L);
        person.setPersonName("Jane Doe");

        ContactInfo contactInfo = new ContactInfo("9876543210", "Example Road 456", "jane@example.com", person);

        assertEquals("9876543210", contactInfo.getPhoneNumber(), "Constructor should set phone number correctly.");
        assertEquals("Example Road 456", contactInfo.getAddress(), "Constructor should set address correctly.");
        assertEquals("jane@example.com", contactInfo.getEmail(), "Constructor should set email correctly.");
        assertSame(person, contactInfo.getPerson(), "Constructor should associate the correct person entity.");
    }
}
