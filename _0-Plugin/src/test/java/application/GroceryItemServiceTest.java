package application;

import dhbw.application.GroceryItemService;
import dhbw.domain.GroceryItemEntity;
import dhbw.domain.ports.GroceryItemRepository;
import dhbw.domain.vo.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GroceryItemServiceTest {

    @Mock
    private GroceryItemRepository groceryItemRepository;

    @InjectMocks
    private GroceryItemService groceryItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_returnsListOfItems() {
        // Vorbedingungen einrichten
        List<GroceryItemEntity> mockItemList = new ArrayList<>();
        mockItemList.add(new GroceryItemEntity());
        mockItemList.add(new GroceryItemEntity());

        when(groceryItemRepository.findAll()).thenReturn(mockItemList);

        // Aktion ausführen
        List<GroceryItemEntity> foundItems = groceryItemService.findAll();

        // Überprüfen, ob die Liste nicht leer ist
        assertNotNull(foundItems);
        // Überprüfen, ob die Liste die erwartete Anzahl von Elementen hat
        assertEquals(2, foundItems.size());
    }

    @Test
    void create_returnsCreatedItem() {
        // Vorbedingungen einrichten
        GroceryItemEntity newItem = new GroceryItemEntity();
        when(groceryItemRepository.save(any(GroceryItemEntity.class))).thenReturn(newItem);

        // Aktion ausführen
        GroceryItemEntity createdItem = groceryItemService.create(newItem);

        // Überprüfen, ob das erstellte Element nicht null ist
        assertNotNull(createdItem);
        // Überprüfen, ob das zurückgegebene Element mit dem erstellten Element übereinstimmt
        assertEquals(newItem, createdItem);
    }

    @Test
    void findById_itemFound() {
        // Vorbedingungen einrichten
        GroceryItemEntity mockItem = new GroceryItemEntity();
        mockItem.setItemID(1L);
        when(groceryItemRepository.findById(1L)).thenReturn(Optional.of(mockItem));

        // Aktion ausführen
        Optional<GroceryItemEntity> foundItem = groceryItemService.findById(1L);

        // Überprüfen, ob das gefundene Element nicht null ist
        assertNotNull(foundItem);
        // Überprüfen, ob das gefundene Element das erwartete Element ist
        assertTrue(foundItem.isPresent());
        assertEquals(mockItem, foundItem.get());
    }

    @Test
    void deleteById_itemDeleted() {
        // Vorbedingungen einrichten
        doNothing().when(groceryItemRepository).deleteById(anyLong());

        // Aktion ausführen
        groceryItemService.deleteById(1L);

        // Überprüfen, ob die Methode deleteById des Repositories aufgerufen wurde
        verify(groceryItemRepository).deleteById(1L);
    }

    @Test
    void update_returnsUpdatedItem() {
        // Vorbedingungen einrichten
        GroceryItemEntity updatedItem = new GroceryItemEntity();
        updatedItem.setItemID(1L);
        updatedItem.setItemName("UpdatedName");
        when(groceryItemRepository.update(anyLong(), anyString(), any(Money.class), anyInt(), anyBoolean())).thenReturn(updatedItem);

        // Aktion ausführen
        GroceryItemEntity result = groceryItemService.update(1L, "UpdatedName", new Money(), 2, false);

        // Überprüfen, ob das zurückgegebene Element nicht null ist
        assertNotNull(result);
        // Überprüfen, ob das zurückgegebene Element das erwartete Element ist
        assertEquals(updatedItem, result);
    }
}
