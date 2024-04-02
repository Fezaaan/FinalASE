package dhbw.ase.rest;

import dhbw.domain.GroceryItemEntity;
import dhbw.domain.ShoppingListEntity;
import dhbw.ase.service.ShoppingListService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list/")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping
    public ResponseEntity<ShoppingListEntity> createShoppingList(@RequestBody ShoppingListEntity shoppingList) {
        ShoppingListEntity createdList = shoppingListService.createShoppingList(shoppingList);
        return new ResponseEntity<>(createdList, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ShoppingListEntity>> getAllShoppingLists() {
        List<ShoppingListEntity> lists = shoppingListService.getAllShoppingLists();
        return ResponseEntity.ok(lists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingListEntity> getShoppingListById(@PathVariable Long id) {
        return shoppingListService.getShoppingListById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingListEntity> updateShoppingList(@PathVariable Long id, @RequestBody ShoppingListEntity shoppingList) {
        try {
            ShoppingListEntity updatedList = shoppingListService.updateShoppingList(id, shoppingList);
            return ResponseEntity.ok(updatedList);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoppingList(@PathVariable Long id) {
        shoppingListService.deleteShoppingList(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{listId}/items")
    public ResponseEntity<GroceryItemEntity> addItemToShoppingList(@PathVariable Long listId, @RequestBody GroceryItemEntity groceryItem) {
        GroceryItemEntity newItem = shoppingListService.addItemToShoppingList(listId, groceryItem);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }
    @DeleteMapping("/{listId}/items/{itemId}")
    public ResponseEntity<Void> deleteItemFromShoppingList(@PathVariable Long listId, @PathVariable Long itemId) {
        try {
            shoppingListService.deleteItemFromShoppingList(listId, itemId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping
    public ResponseEntity<ShoppingListEntity> updateShoppingListName(@PathVariable Long id, @RequestBody String name) {
        try {
            ShoppingListEntity updatedList = shoppingListService.updateShoppingListName(id, name);
            return ResponseEntity.ok(updatedList);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
