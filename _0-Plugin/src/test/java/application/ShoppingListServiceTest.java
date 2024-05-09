package application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dhbw.application.ShoppingListService;
import dhbw.ase.ListItCrudMavenApplication;
import dhbw.domain.GroceryItemEntity;
import dhbw.domain.ShoppingListEntity;
import dhbw.domain.ports.GroceryItemRepository;
import dhbw.domain.ports.ShoppingListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes= ListItCrudMavenApplication.class)
public class ShoppingListServiceTest {

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Mock
    private GroceryItemRepository groceryItemRepository;

    @InjectMocks
    private ShoppingListService shoppingListService;

    private ShoppingListEntity shoppingList;
    private GroceryItemEntity groceryItem;

    @BeforeEach
    void setUp() {
        shoppingList = new ShoppingListEntity();
        shoppingList.setListID(1L);
        shoppingList.setListName("Weekly Groceries");

        groceryItem = new GroceryItemEntity();
        groceryItem.setItemID(1L);
        groceryItem.setItemName("Apples");
        groceryItem.setShoppingList(shoppingList);
    }

    @Test
    void createShoppingList_ShouldReturnSavedShoppingList() {
        when(shoppingListRepository.save(any(ShoppingListEntity.class))).thenReturn(shoppingList);

        ShoppingListEntity savedShoppingList = shoppingListService.createShoppingList(shoppingList);

        assertNotNull(savedShoppingList);
        assertEquals("Weekly Groceries", savedShoppingList.getListName());
        verify(shoppingListRepository).save(shoppingList);
    }

    @Test
    void getAllShoppingLists_ShouldReturnAllShoppingLists() {
        when(shoppingListRepository.findAll()).thenReturn(Arrays.asList(shoppingList));

        List<ShoppingListEntity> shoppingLists = shoppingListService.getAllShoppingLists();

        assertFalse(shoppingLists.isEmpty());
        verify(shoppingListRepository).findAll();
    }

    @Test
    void getShoppingListById_ShouldReturnShoppingList() {
        when(shoppingListRepository.findById(anyLong())).thenReturn(Optional.of(shoppingList));

        Optional<ShoppingListEntity> foundShoppingList = shoppingListService.getShoppingListById(1L);

        assertTrue(foundShoppingList.isPresent());
        assertEquals("Weekly Groceries", foundShoppingList.get().getListName());
        verify(shoppingListRepository).findById(1L);
    }

    @Test
    void updateShoppingList_ShouldUpdateShoppingListDetails() {
        when(shoppingListRepository.findById(anyLong())).thenReturn(Optional.of(shoppingList));
        when(shoppingListRepository.save(any(ShoppingListEntity.class))).thenReturn(shoppingList);

        ShoppingListEntity updatedList = shoppingListService.updateShoppingList(1L, shoppingList);

        assertNotNull(updatedList);
        verify(shoppingListRepository).save(shoppingList);
    }

    @Test
    void deleteShoppingList_ShouldRemoveShoppingList() {
        doNothing().when(shoppingListRepository).deleteById(anyLong());
        shoppingListService.deleteShoppingList(1L);
        verify(shoppingListRepository).deleteById(1L);
    }
    @Test
    void addItemToShoppingList_itemAddedToList() {
        // Vorbedingungen einrichten
        ShoppingListEntity shoppingList = new ShoppingListEntity();
        GroceryItemEntity groceryItem = new GroceryItemEntity();
        groceryItem.setItemID(1L);
        groceryItem.setItemName("TestItem");

        when(shoppingListRepository.findById(anyLong())).thenReturn(Optional.of(shoppingList));
        when(groceryItemRepository.save(any(GroceryItemEntity.class))).thenReturn(groceryItem);

        // Aktion ausführen
        GroceryItemEntity addedItem = shoppingListService.addItemToShoppingList(1L, groceryItem);

        // Überprüfen, ob das Item korrekt hinzugefügt wurde
        assertNotNull(addedItem);
        assertEquals("TestItem", addedItem.getItemName());
        assertEquals(shoppingList, addedItem.getShoppingList());
        verify(groceryItemRepository).save(any(GroceryItemEntity.class));
    }

    @Test
    void deleteItemFromShoppingList_itemDeletedFromList() {
        // Vorbedingungen einrichten
        ShoppingListEntity shoppingList = new ShoppingListEntity();
        shoppingList.setListID(1L);
        GroceryItemEntity groceryItem = new GroceryItemEntity();
        groceryItem.setItemID(1L);
        groceryItem.setItemName("TestItem");
        groceryItem.setShoppingList(shoppingList);

        when(shoppingListRepository.findById(anyLong())).thenReturn(Optional.of(shoppingList));
        when(groceryItemRepository.findById(anyLong())).thenReturn(Optional.of(groceryItem));
        doNothing().when(groceryItemRepository).deleteById(anyLong());

        // Aktion ausführen
        shoppingListService.deleteItemFromShoppingList(1L, 1L);

        // Überprüfen, ob das Item korrekt gelöscht wurde
        verify(groceryItemRepository).deleteById(1L);
    }

    @Test
    void updateShoppingListName_updatesName() {
        // Vorbedingungen einrichten
        ShoppingListEntity shoppingList = new ShoppingListEntity();
        shoppingList.setListID(1L);
        shoppingList.setListName("NewName"); // Setze den neuen Namen

        when(shoppingListRepository.update(anyLong(), anyString())).thenReturn(shoppingList);

        // Aktion ausführen
        ShoppingListEntity updatedList = shoppingListService.updateShoppingListName(1L, "NewName");

        // Überprüfen, ob der Name korrekt aktualisiert wurde
        assertNotNull(updatedList);
        assertEquals("NewName", updatedList.getListName());
        verify(shoppingListRepository).update(1L, "NewName");
    }


}