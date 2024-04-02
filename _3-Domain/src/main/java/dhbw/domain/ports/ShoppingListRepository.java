package dhbw.domain.ports;

import dhbw.domain.ShoppingListEntity;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository {
    Optional<ShoppingListEntity> findById(Long id);
    List<ShoppingListEntity> findAll();
    ShoppingListEntity save(ShoppingListEntity item);
    void deleteById(Long id);
    ShoppingListEntity update (Long id, String name);

}
