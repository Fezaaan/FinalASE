package dhbw.ase.listitcrudmaven.infrastructure.repository;

import dhbw.ase.listitcrudmaven.infrastructure.persistence.GroceryItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryItemRepository extends JpaRepository<GroceryItemEntity, Long> {

}
