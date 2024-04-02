package dhbw.ase.repository;

import dhbw.domain.GroceryItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryItemJpaRepository extends JpaRepository<GroceryItemEntity, Long> {
    List<GroceryItemEntity> findByShoppingListId(Long shoppingListId);
}