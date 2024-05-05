package dhbw.ase.repository;
import dhbw.domain.GroceryItemEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryItemJpaRepository extends JpaRepository<GroceryItemEntity, Long> {
    @Query("SELECT g FROM GroceryItemEntity g WHERE g.shoppingList.listID = :shoppingListId")
    List<GroceryItemEntity> findByShoppingList_Id(Long shoppingListId);
}