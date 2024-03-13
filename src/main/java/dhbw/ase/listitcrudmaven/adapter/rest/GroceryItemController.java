package dhbw.ase.listitcrudmaven.adapter.rest;

import dhbw.ase.listitcrudmaven.application.GroceryItemService;
import dhbw.ase.listitcrudmaven.domain.GroceryItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grocery-items")
public class GroceryItemController {
//test
    private final GroceryItemService groceryItemService;

    public GroceryItemController(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    @GetMapping
    public ResponseEntity<List<GroceryItem>> getAllGroceryItems() {
        return new ResponseEntity<>(groceryItemService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroceryItem> getGroceryItemById(@PathVariable Long id) {
        return groceryItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<GroceryItem> createGroceryItem(@RequestBody GroceryItem groceryItem) {
        GroceryItem createdGroceryItem = groceryItemService.create(groceryItem);
        return new ResponseEntity<>(createdGroceryItem, HttpStatus.CREATED);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<GroceryItem> updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItem groceryItem) {
        return groceryItemService.update(id, groceryItem)
                .map(updatedGroceryItem -> new ResponseEntity<>(updatedGroceryItem, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroceryItem(@PathVariable Long id) {
        if (groceryItemService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
*/
    // Hier könntest du weitere Methoden hinzufügen, wie z.B. das Suchen von Einträgen oder das Abhaken eines Items als gekauft (isChecked).
}
