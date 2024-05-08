package domain;

import dhbw.domain.GroceryItemEntity;
import dhbw.domain.PersonEntity;
import dhbw.domain.ShoppingListEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class ShoppingListEntityTest {
    private ShoppingListEntity shoppingList;
    private PersonEntity owner;
    private GroceryItemEntity item1, item2;

    @BeforeEach
    void setUp() {
        shoppingList = new ShoppingListEntity();
        owner = new PersonEntity();
        owner.setPersonID(1L);
        owner.setPersonName("John Doe");

        item1 = new GroceryItemEntity();
        item1.setItemID(1L);
        item1.setItemName("Milk");
        item1.setItemAnzahl(2);

        item2 = new GroceryItemEntity();
        item2.setItemID(2L);
        item2.setItemName("Bread");
        item2.setItemAnzahl(3);

        List<GroceryItemEntity> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        shoppingList.setOwner(owner);
        shoppingList.setListName("Weekly Shopping");
        shoppingList.setItems(items);
    }

    @Test
    void testGetOwner() {
        assertEquals(owner, shoppingList.getOwner(), "The owner should be correctly retrieved.");
    }

    @Test
    void testSetOwner() {
        PersonEntity newOwner = new PersonEntity();
        newOwner.setPersonID(2L);
        shoppingList.setOwner(newOwner);
        assertEquals(newOwner, shoppingList.getOwner(), "The owner should be correctly set.");
    }

    @Test
    void testGetItems() {
        assertEquals(2, shoppingList.getItems().size(), "Should retrieve two items.");
        assertTrue(shoppingList.getItems().contains(item1), "Items should include 'Milk'.");
        assertTrue(shoppingList.getItems().contains(item2), "Items should include 'Bread'.");
    }

    @Test
    void testSetItems() {
        GroceryItemEntity item3 = new GroceryItemEntity();
        item3.setItemID(3L);
        item3.setItemName("Eggs");
        item3.setItemAnzahl(12);

        List<GroceryItemEntity> newItems = new ArrayList<>();
        newItems.add(item3);
        shoppingList.setItems(newItems);

        assertEquals(1, shoppingList.getItems().size(), "Should only have one item now.");
        assertTrue(shoppingList.getItems().contains(item3), "Items should include 'Eggs'.");
    }

    @Test
    void testGetAndSetListName() {
        assertEquals("Weekly Shopping", shoppingList.getListName(), "The list name should be correctly retrieved.");
        shoppingList.setListName("Monthly Shopping");
        assertEquals("Monthly Shopping", shoppingList.getListName(), "The list name should be correctly updated.");
    }
}
