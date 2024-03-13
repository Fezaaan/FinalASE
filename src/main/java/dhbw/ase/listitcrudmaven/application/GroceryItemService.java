package dhbw.ase.listitcrudmaven.application;

import dhbw.ase.listitcrudmaven.domain.GroceryItem;
import dhbw.ase.listitcrudmaven.infrastructure.persistence.GroceryItemEntity;
import dhbw.ase.listitcrudmaven.infrastructure.repository.GroceryItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroceryItemService {

    private final GroceryItemRepository groceryItemRepository;

    public GroceryItemService(GroceryItemRepository groceryItemRepository) {
        this.groceryItemRepository = groceryItemRepository;
    }

    @Transactional(readOnly = true)
    public List<GroceryItem> findAll() {
        // Konvertiere hier die Entity-Liste in eine Liste von Domain-Objekten
        return groceryItemRepository.findAll().stream()
                .map(this::convertEntityToDomain)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<GroceryItem> findById(Long id) {
        // Finde das Entity und konvertiere es in ein Domain-Objekt, falls vorhanden
        return groceryItemRepository.findById(id)
                .map(this::convertEntityToDomain);
    }

    @Transactional
    public GroceryItem create(GroceryItem groceryItem) {
        // Konvertiere das Domain-Objekt in ein Entity und speichere es
        GroceryItemEntity entity = convertDomainToEntity(groceryItem);
        GroceryItemEntity savedEntity = groceryItemRepository.save(entity);
        return convertEntityToDomain(savedEntity);
    }

    // Utility-Methode zum Konvertieren von Entity zu Domain-Objekt
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
