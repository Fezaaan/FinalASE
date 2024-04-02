package dhbw.ase.service;

import dhbw.domain.GroceryItemEntity;
import dhbw.domain.ShoppingListEntity;
import dhbw.domain.ports.GroceryItemRepository;
import dhbw.domain.ports.ShoppingListRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final GroceryItemRepository groceryItemRepository;


    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository, GroceryItemRepository groceryItemRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.groceryItemRepository= groceryItemRepository;
    }

    public ShoppingListEntity createShoppingList(ShoppingListEntity shoppingList) {
        return shoppingListRepository.save(shoppingList);
    }


    public List<ShoppingListEntity> getAllShoppingLists() {
        return shoppingListRepository.findAll();
    }


    public Optional<ShoppingListEntity> getShoppingListById(Long id) {
        return shoppingListRepository.findById(id);
    }


    public ShoppingListEntity updateShoppingList(Long id, ShoppingListEntity updatedShoppingList) {
        return shoppingListRepository.findById(id)
                .map(shoppingList -> {
                    shoppingList.setName(updatedShoppingList.getName());
                    // Hier weitere Felder aktualisieren, z.B. Beschreibung, falls vorhanden
                    return shoppingListRepository.save(shoppingList);
                })
                .orElseThrow(() -> new RuntimeException("ShoppingList not found with id " + id));
    }


    public void deleteShoppingList(Long id) {
        shoppingListRepository.deleteById(id);
    }

    public GroceryItemEntity addItemToShoppingList(Long listId, GroceryItemEntity groceryItem) {
        Optional<ShoppingListEntity> shoppingListOpt = shoppingListRepository.findById(listId);
        if (!shoppingListOpt.isPresent()) {
            throw new RuntimeException("ShoppingList not found with id: " + listId);
        }

        ShoppingListEntity shoppingList = shoppingListOpt.get();
        groceryItem.setShoppingList(shoppingList); // Setzt die Beziehung zwischen dem Item und der Liste
        return groceryItemRepository.save(groceryItem); // Speichert das Item, das nun zur Liste gehört
    }
    public void deleteItemFromShoppingList(Long listId, Long itemId) {
        // Überprüfung, ob die Einkaufsliste existiert
        ShoppingListEntity shoppingList = shoppingListRepository.findById(listId)
                .orElseThrow(() -> new EntityNotFoundException("ShoppingList not found with id: " + listId));

        // Überprüfung, ob das zu löschende Item existiert und zur Liste gehört
        GroceryItemEntity itemToDelete = groceryItemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + itemId));

        if (!itemToDelete.getShoppingList().getId().equals(listId)) {
            throw new IllegalArgumentException("Item does not belong to the specified shopping list");
        }

        // Löschen des Items
        groceryItemRepository.deleteById(itemId);
    }
    public ShoppingListEntity updateShoppingListName( Long id, String name) {
        return shoppingListRepository.update(id,name);
    }

}

