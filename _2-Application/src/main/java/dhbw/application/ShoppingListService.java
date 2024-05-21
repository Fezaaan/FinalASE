package dhbw.application;

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
                    shoppingList.setListName(updatedShoppingList.getListName());
                    // Hier weitere Felder aktualisieren, z.B. Beschreibung, falls vorhanden
                    return shoppingListRepository.save(shoppingList);
                })
                .orElseThrow(() -> new RuntimeException("ShoppingList not found with id " + id));
    }


    public void deleteShoppingList(Long id) {
        shoppingListRepository.deleteById(id);
    }

    public GroceryItemEntity addItemToShoppingList(Long listId, GroceryItemEntity groceryItem) {
        ShoppingListEntity shoppingList = getShoppingListByIdOrThrow(listId);
        setRelationshipBetweenItemAndList(groceryItem, shoppingList);
        return saveGroceryItem(groceryItem);
    }

    private ShoppingListEntity getShoppingListByIdOrThrow(Long listId) {
        return getShoppingListById(listId)
                .orElseThrow(() -> new RuntimeException("ShoppingList not found with id: " + listId));
    }

    private void setRelationshipBetweenItemAndList(GroceryItemEntity groceryItem, ShoppingListEntity shoppingList) {
        groceryItem.setShoppingList(shoppingList);
    }

    private GroceryItemEntity saveGroceryItem(GroceryItemEntity groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }
    public void deleteItemFromShoppingList(Long listId, Long itemId) {
        // Überprüfung, ob die Einkaufsliste existiert
        ShoppingListEntity shoppingList = shoppingListRepository.findById(listId)
                .orElseThrow(() -> new EntityNotFoundException("ShoppingList not found with id: " + listId));

        // Überprüfung, ob das zu löschende Item existiert und zur Liste gehört
        GroceryItemEntity itemToDelete = groceryItemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + itemId));

        if (!itemToDelete.getShoppingList().getListID().equals(listId)) {
            throw new IllegalArgumentException("Item does not belong to the specified shopping list");
        }

        // Löschen des Items
        groceryItemRepository.deleteById(itemId);
    }
    public ShoppingListEntity updateShoppingListName( Long id, String name) {
        return shoppingListRepository.update(id,name);
    }

}

