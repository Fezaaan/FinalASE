package dhbw.domain.ports;
import dhbw.domain.GroceryItemEntity;
import dhbw.domain.vo.Money;

import java.util.List;
import java.util.Optional;

public interface GroceryItemRepository {
    Optional<GroceryItemEntity> findById(Long id);
    List<GroceryItemEntity> findAll();
    GroceryItemEntity save(GroceryItemEntity item);
    void deleteById(Long id);

    GroceryItemEntity update(Long id, String name, Money preis, int anzahl, boolean checked);

    List<GroceryItemEntity> findByShoppingListId(Long shoppingListId);

}
