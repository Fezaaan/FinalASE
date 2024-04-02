package dhbw.ase.bridge;

import dhbw.ase.repository.GroceryItemJpaRepository;
import dhbw.domain.GroceryItemEntity;
import dhbw.domain.ports.GroceryItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GroceryItemRepoBridge implements GroceryItemRepository {

    private final GroceryItemJpaRepository groceryItemJpaRepository;
    @Autowired
    public GroceryItemRepoBridge(GroceryItemJpaRepository groceryItemJpaRepository) {
        this.groceryItemJpaRepository = groceryItemJpaRepository;
    }
    @Override
    public Optional<GroceryItemEntity> findById(Long id) {
        return groceryItemJpaRepository.findById(id);
    }

    @Override
    public List<GroceryItemEntity> findAll() {
        return groceryItemJpaRepository.findAll();
    }

    @Override
    public GroceryItemEntity save(GroceryItemEntity item) {
        return groceryItemJpaRepository.save(item);
    }

    @Override
    public void deleteById(Long id) {
        groceryItemJpaRepository.deleteById(id);
    }

    @Override
    public GroceryItemEntity update(Long id, String name, float preis, int anzahl, boolean checked) {
        Optional<GroceryItemEntity> oldOpt = groceryItemJpaRepository.findById(id);
        if (oldOpt.isPresent()) {
            GroceryItemEntity existingItem = oldOpt.get();
            existingItem.setItemName(name);
            existingItem.setPreis(preis);
            existingItem.setAnzahl(anzahl);
            existingItem.setChecked(checked);
            return groceryItemJpaRepository.save(existingItem);
        } else {
            throw new EntityNotFoundException("GroceryItem with id " + id + " not found.");
        }
    }

    @Override
    public List<GroceryItemEntity> findByShoppingListId(Long shoppingListId) {
        return groceryItemJpaRepository.findByShoppingListId(shoppingListId);
    }


}