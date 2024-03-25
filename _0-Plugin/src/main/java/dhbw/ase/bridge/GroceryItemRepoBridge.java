package dhbw.ase.bridge;

import dhbw.ase.repository.GroceryItemJpaRepository;
import dhbw.domain.GroceryItemEntity;
import dhbw.domain.ports.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GroceryItemRepoBridge implements GroceryItemRepository {

    private final GroceryItemJpaRepository groceryItemJpaRepository;
    @Autowired
    public GroceryItemRepoBridge(GroceryItemJpaRepository groceryItemJpaRepository) {
        this.groceryItemJpaRepository = groceryItemJpaRepository;
    }


    @Override
    public Optional<GroceryItemEntity> findById(Long id) {
        return groceryItemJpaRepository.findById(id);
    }

    @Override
    public List<GroceryItemEntity> findAll() {
        return groceryItemJpaRepository.findAll();
    }

    @Override
    public GroceryItemEntity save(GroceryItemEntity item) {
        return groceryItemJpaRepository.save(item);
    }

    @Override
    public void deleteById(Long id) {
        groceryItemJpaRepository.deleteById(id);
    }
}