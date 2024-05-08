package domain;

import dhbw.domain.PersonEntity;
import dhbw.domain.ShoppingListEntity;
import dhbw.domain.aggregate.ContactInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class PersonEntityTest {
    private PersonEntity person;
    private ContactInfo contactInfo;
    private ShoppingListEntity shoppingList1, shoppingList2;

    @BeforeEach
    void setUp() {
        person = new PersonEntity();
        person.setPersonID(1L);
        person.setPersonName("Max Mustermann");

        contactInfo = new ContactInfo();
        contactInfo.setId(1L);
        contactInfo.setPhoneNumber("1234567890");
        contactInfo.setAddress("Musterstraße 1");
        contactInfo.setEmail("max@example.com");

        shoppingList1 = new ShoppingListEntity();
        shoppingList1.setListID(1L);
        shoppingList1.setListName("Wochenendeinkauf");

        shoppingList2 = new ShoppingListEntity();
        shoppingList2.setListID(2L);
        shoppingList2.setListName("Partyeinkauf");

        person.setContactInfo(contactInfo);
        person.setShoppingLists(Arrays.asList(shoppingList1, shoppingList2));
    }

    @Test
    void testGetPersonID() {
        assertEquals(1L, person.getPersonID(), "The person ID should match the one set.");
    }

    @Test
    void testSetPersonID() {
        person.setPersonID(2L);
        assertEquals(2L, person.getPersonID(), "The person ID should be updated.");
    }

    @Test
    void testGetPersonName() {
        assertEquals("Max Mustermann", person.getPersonName(), "The person name should match the one set.");
    }

    @Test
    void testSetPersonName() {
        person.setPersonName("Anna Beispiel");
        assertEquals("Anna Beispiel", person.getPersonName(), "The person name should be updated.");
    }

    @Test
    void testGetShoppingLists() {
        assertNotNull(person.getShoppingLists(), "The shopping lists should not be null.");
        assertEquals(2, person.getShoppingLists().size(), "There should be two shopping lists.");
    }

    @Test
    void testSetShoppingLists() {
        ShoppingListEntity newShoppingList = new ShoppingListEntity();
        newShoppingList.setListID(3L);
        newShoppingList.setListName("Neujahrseinkauf");
        person.setShoppingLists(List.of(newShoppingList));
        assertEquals(1, person.getShoppingLists().size(), "There should be one shopping list now.");
        assertEquals("Neujahrseinkauf", person.getShoppingLists().get(0).getListName(), "The new shopping list should be set.");
    }

    @Test
    void testGetContactInfo() {
        assertNotNull(person.getContactInfo(), "The contact info should not be null.");
        assertEquals("1234567890", person.getContactInfo().getPhoneNumber(), "The phone number should match the one set.");
    }

    @Test
    void testSetContactInfo() {
        ContactInfo newContactInfo = new ContactInfo();
        newContactInfo.setId(2L);
        newContactInfo.setPhoneNumber("0987654321");
        person.setContactInfo(newContactInfo);
        assertEquals("0987654321", person.getContactInfo().getPhoneNumber(), "The contact info should be updated.");
    }
}