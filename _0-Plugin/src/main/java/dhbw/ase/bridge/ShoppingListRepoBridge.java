package dhbw.ase.bridge;

import dhbw.ase.repository.ShoppingListJpaRepository;
import dhbw.domain.ShoppingListEntity;
import dhbw.domain.ports.ShoppingListRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ShoppingListRepoBridge implements ShoppingListRepository {

    private final ShoppingListJpaRepository shoppingListJpaRepository;

    @Autowired
    public ShoppingListRepoBridge(ShoppingListJpaRepository shoppingListJpaRepository) {
        this.shoppingListJpaRepository = shoppingListJpaRepository;
    }

    @Override
    public Optional<ShoppingListEntity> findById(Long id) {
        return shoppingListJpaRepository.findById(id);
    }

    @Override
    public List<ShoppingListEntity> findAll() {
        return shoppingListJpaRepository.findAll();
    }

    @Override
    public ShoppingListEntity save(ShoppingListEntity list) {
        return shoppingListJpaRepository.save(list);
    }

    @Override
    public void deleteById(Long id) {
        shoppingListJpaRepository.deleteById(id);
    }

    @Override
    public ShoppingListEntity update(Long id, String name) {
        Optional<ShoppingListEntity> listOpt = shoppingListJpaRepository.findById(id);
        if (listOpt.isPresent()) {
            ShoppingListEntity existingList = listOpt.get();
            existingList.setListName(name);
            return shoppingListJpaRepository.save(existingList);
        } else {
            throw new EntityNotFoundException("ShoppingList with id " + id + " not found.");
        }
    }
}
