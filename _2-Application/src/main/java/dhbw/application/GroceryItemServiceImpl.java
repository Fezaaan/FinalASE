package dhbw.application;
import dhbw.domain.GroceryItemEntity;
import dhbw.domain.ports.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryItemServiceImpl {
private final GroceryItemRepository groceryItemRepository;
    @Autowired
    public GroceryItemServiceImpl(GroceryItemRepository groceryItemRepository) {
        this.groceryItemRepository = groceryItemRepository;
    }

    public List<GroceryItemEntity> findAll() {
        return groceryItemRepository.findAll();
    }



    public GroceryItemEntity create(GroceryItemEntity groceryItemEntity) {
       return groceryItemRepository.save(groceryItemEntity);
    }

    public Optional<GroceryItemEntity> findById(Long id) {
        return groceryItemRepository.findById(id);
    }
}

/* package dhbw.ase.persistence;

import dhbw.application.GroceryItemService;
import dhbw.ase.repository.GroceryItemJpaRepository;
import dhbw.domain.ports.GroceryItemRepository;
import dhbw.domain.GroceryItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class GroceryItemServiceImpl {
    private final GroceryItemJpaRepository groceryItemJpaRepository;

    @Autowired
    public GroceryItemServiceImpl(GroceryItemJpaRepository groceryItemJpaRepository) {
        this.groceryItemJpaRepository = groceryItemJpaRepository;
    }

    @Transactional(readOnly = true)
    public List<GroceryItem> findAll() {
        // Konvertiere hier die Entity-Liste in eine Liste von Domain-Objekten
        return groceryItemJpaRepository.findAll().stream()
                .map(this::convertEntityToDomain)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<GroceryItem> findById(Long id) {
        // Finde das Entity und konvertiere es in ein Domain-Objekt, falls vorhanden
        return groceryItemJpaRepository.findById(id)
                .map(this::convertEntityToDomain);
    }

    @Transactional
    public GroceryItem create(GroceryItem groceryItem) {
        // Konvertiere das Domain-Objekt in ein Entity und speichere es
        GroceryItemEntity entity = convertDomainToEntity(groceryItem);
        GroceryItemEntity savedEntity = groceryItemJpaRepository.save(entity);
        return convertEntityToDomain(savedEntity);
    }

    private GroceryItem convertEntityToDomain(GroceryItemEntity entity) {
        return new GroceryItem(
                entity.getItemName(),
                entity.getPreis(),
                entity.getAnzahl(),
                entity.isChecked()
        );
    }

    // Utility-Methode zum Konvertieren von Domain-Objekt zu Entity
    private GroceryItemEntity convertDomainToEntity(GroceryItem domain) {
        return new GroceryItemEntity(
                domain.getItemName(),
                domain.getPreis(),
                domain.getAnzahl(),
                domain.isChecked()
        );
    }
}
*/