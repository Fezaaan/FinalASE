package dhbw.ase.rest;

import dhbw.application.GroceryItemService;
import dhbw.domain.GroceryItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items/")
public class GroceryItemController {

    @Autowired
    private GroceryItemService groceryItemService;

    @GetMapping
    public ResponseEntity<List<GroceryItemEntity>> getAllGroceryItems() {
        List<GroceryItemEntity> items = groceryItemService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroceryItemEntity> getGroceryItemById(@PathVariable Long id) {
        return groceryItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<GroceryItemEntity> createGroceryItem(@RequestBody GroceryItemEntity groceryItemEntity) {
        GroceryItemEntity createdItem = groceryItemService.create(groceryItemEntity);
        System.out.println("Create");
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<GroceryItemEntity> updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItemEntity groceryItemEntity) {

        GroceryItemEntity updatedItem = groceryItemService.update(
                id,
                groceryItemEntity.getItemName(),
                groceryItemEntity.getItemPreis(),
                groceryItemEntity.getItemAnzahl(),
                groceryItemEntity.isItemIsChecked()
        );

        System.out.println("Update");
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroceryItem(@PathVariable Long id) {
        groceryItemService.deleteById(id);
        System.out.println("Deleted");
        return ResponseEntity.noContent().build();
    }
}
