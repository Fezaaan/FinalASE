package ase.bridge;

import dhbw.ase.bridge.ShoppingListRepoBridge;
import dhbw.ase.repository.ShoppingListJpaRepository;
import dhbw.domain.ShoppingListEntity;
import dhbw.domain.ports.ShoppingListRepository;
import jakarta.persistence.EntityNotFoundException;
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

public class ShoppingListRepoBridgeTest {

    @Mock
    private ShoppingListJpaRepository shoppingListJpaRepository;

    @InjectMocks
    private ShoppingListRepoBridge shoppingListRepoBridge;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_returnsOptionalEmptyWhenNotFound() {
        // Vorbedingungen einrichten
        when(shoppingListJpaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Aktion ausführen
        Optional<ShoppingListEntity> result = shoppingListRepoBridge.findById(1L);

        // Überprüfen, ob das Optional leer ist
        assertTrue(result.isEmpty());
    }

    @Test
    void findAll_returnsListOfShoppingLists() {
        // Vorbedingungen einrichten
        List<ShoppingListEntity> mockList = new ArrayList<>();
        mockList.add(new ShoppingListEntity());
        mockList.add(new ShoppingListEntity());

        when(shoppingListJpaRepository.findAll()).thenReturn(mockList);

        // Aktion ausführen
        List<ShoppingListEntity> result = shoppingListRepoBridge.findAll();

        // Überprüfen, ob die Liste nicht leer ist
        assertNotNull(result);
        // Überprüfen, ob die Liste die erwartete Anzahl von Elementen hat
        assertEquals(2, result.size());
    }

    @Test
    void save_returnsSavedShoppingList() {
        // Vorbedingungen einrichten
        ShoppingListEntity newList = new ShoppingListEntity();
        when(shoppingListJpaRepository.save(any(ShoppingListEntity.class))).thenReturn(newList);

        // Aktion ausführen
        ShoppingListEntity result = shoppingListRepoBridge.save(newList);

        // Überprüfen, ob das zurückgegebene Objekt nicht null ist
        assertNotNull(result);
        // Überprüfen, ob das zurückgegebene Objekt dasselbe Objekt ist, das gespeichert wurde
        assertEquals(newList, result);
    }

    @Test
    void deleteById_deletesShoppingList() {
        // Aktion ausführen
        shoppingListRepoBridge.deleteById(1L);

        // Überprüfen, ob die Methode deleteById des Repositories aufgerufen wurde
        verify(shoppingListJpaRepository).deleteById(1L);
    }

    @Test
    void update_updatesShoppingListName() {
        // Vorbedingungen einrichten
        ShoppingListEntity existingList = new ShoppingListEntity();
        existingList.setListID(1L);
        existingList.setListName("OldName");

        when(shoppingListJpaRepository.findById(1L)).thenReturn(Optional.of(existingList));
        when(shoppingListJpaRepository.save(any(ShoppingListEntity.class))).thenReturn(existingList);

        // Aktion ausführen
        ShoppingListEntity result = shoppingListRepoBridge.update(1L, "NewName");

        // Überprüfen, ob das zurückgegebene Objekt nicht null ist
        assertNotNull(result);
        // Überprüfen, ob der Name aktualisiert wurde
        assertEquals("NewName", result.getListName());
    }

    @Test
    void update_throwsEntityNotFoundExceptionWhenNotFound() {
        // Vorbedingungen einrichten
        when(shoppingListJpaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Aktion ausführen und Überprüfen, ob eine EntityNotFoundException geworfen wird
        assertThrows(EntityNotFoundException.class, () -> shoppingListRepoBridge.update(1L, "NewName"));
    }
}
