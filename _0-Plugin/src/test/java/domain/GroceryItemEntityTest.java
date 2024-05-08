package domain;

import dhbw.domain.GroceryItemEntity;
import dhbw.domain.ShoppingListEntity;
import dhbw.domain.vo.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class GroceryItemEntityTest {
    private GroceryItemEntity groceryItem;
    private ShoppingListEntity shoppingList;

    @BeforeEach
    void setUp() {
        Money price = new Money( new BigDecimal("2.99"), Currency.getInstance("EUR"));
        groceryItem = new GroceryItemEntity("Milch", price, 1, false);
        groceryItem.setItemID(1L);

        shoppingList = new ShoppingListEntity();
        shoppingList.setListID(1L);
        shoppingList.setListName("Wochenendeinkauf");

        groceryItem.setShoppingList(shoppingList);
    }

    @Test
    void testGetSetItemName() {
        assertEquals("Milch", groceryItem.getItemName(), "The item name should match.");
        groceryItem.setItemName("Brot");
        assertEquals("Brot", groceryItem.getItemName(), "The item name should be updated.");
    }

    @Test
    void testGetSetItemPreis() {
        assertNotNull(groceryItem.getItemPreis(), "Item price should not be null.");
        assertEquals(2.99, groceryItem.getItemPreis().getAmount().doubleValue(), "The item price should match.");
        Money newPrice = new Money( new BigDecimal("1.50"), Currency.getInstance("EUR"));
        groceryItem.setItemPreis(newPrice);
        assertEquals(1.50, groceryItem.getItemPreis().getAmount().doubleValue(), "The item price should be updated.");
    }

    @Test
    void testGetSetItemAnzahl() {
        assertEquals(1, groceryItem.getItemAnzahl(), "The item count should match.");
        groceryItem.setItemAnzahl(2);
        assertEquals(2, groceryItem.getItemAnzahl(), "The item count should be updated.");
    }

    @Test
    void testGetSetItemIsChecked() {
        assertFalse(groceryItem.isItemIsChecked(), "The item should not be checked.");
        groceryItem.setItemIsChecked(true);
        assertTrue(groceryItem.isItemIsChecked(), "The item should now be checked.");
    }

    @Test
    void testGetSetShoppingList() {
        assertNotNull(groceryItem.getShoppingList(), "The associated shopping list should not be null.");
        assertEquals(1L, groceryItem.getShoppingList().getListID(), "The shopping list ID should match.");

        ShoppingListEntity newShoppingList = new ShoppingListEntity();
        newShoppingList.setListID(2L);
        groceryItem.setShoppingList(newShoppingList);
        assertEquals(2L, groceryItem.getShoppingList().getListID(), "The shopping list should be updated.");
    }
}
