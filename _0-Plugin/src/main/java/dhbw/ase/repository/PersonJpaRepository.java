package dhbw.ase.repository;

import dhbw.domain.GroceryItemEntity;
import dhbw.domain.PersonEntity;
import dhbw.domain.ShoppingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonJpaRepository extends JpaRepository<PersonEntity, Long> {
    @Query("SELECT p.shoppingLists FROM PersonEntity p WHERE p.personID = :personId")
    List<ShoppingListEntity> findShoppingListsByPersonId(Long personId);
}