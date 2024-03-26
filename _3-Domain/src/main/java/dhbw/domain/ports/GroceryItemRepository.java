package dhbw.domain.ports;
import dhbw.domain.GroceryItemEntity;

import java.util.List;
import java.util.Optional;

public interface GroceryItemRepository {
    Optional<GroceryItemEntity> findById(Long id);
    List<GroceryItemEntity> findAll();
    GroceryItemEntity save(GroceryItemEntity item);
    void deleteById(Long id);
    GroceryItemEntity update (Long id, String name, float preis, int anzahl, boolean checked);

}
