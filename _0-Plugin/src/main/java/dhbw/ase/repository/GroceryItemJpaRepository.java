package dhbw.ase.repository;

import dhbw.domain.GroceryItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryItemJpaRepository extends JpaRepository<GroceryItemEntity, Long> {

}