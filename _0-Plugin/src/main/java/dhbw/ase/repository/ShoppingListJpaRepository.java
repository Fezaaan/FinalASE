package dhbw.ase.repository;

import dhbw.domain.ShoppingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListJpaRepository extends JpaRepository<ShoppingListEntity, Long> {

}