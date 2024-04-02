package dhbw.application;
import dhbw.domain.GroceryItemEntity;
import dhbw.domain.ports.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryItemService {
    @Autowired
    private final GroceryItemRepository groceryItemRepository;
    @Autowired
    public GroceryItemService(GroceryItemRepository groceryItemRepository) {
        this.groceryItemRepository = groceryItemRepository;
    }

    public List<GroceryItemEntity> findAll() {
        return groceryItemRepository.findAll();
    }

    public GroceryItemEntity create(GroceryItemEntity groceryItemEntity) {
       return groceryItemRepository.save(groceryItemEntity);
    }

    public Optional<GroceryItemEntity> findById(Long id) {
        return groceryItemRepository.findById(id);
    }
    public void deleteById(Long id) {
        groceryItemRepository.deleteById(id);
    }
    public GroceryItemEntity update(Long id, String name, float preis, int anzahl, boolean checked){
     return groceryItemRepository.update(id,name,preis,anzahl,checked);
    }
}