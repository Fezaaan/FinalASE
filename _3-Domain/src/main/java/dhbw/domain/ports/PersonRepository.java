package dhbw.domain.ports;

import dhbw.domain.PersonEntity;
import dhbw.domain.ShoppingListEntity;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    Optional<PersonEntity> findById(Long id);
    List<PersonEntity> findAll();
    PersonEntity save(PersonEntity person);
    void deleteById(Long id);

    // Zusätzliche Methode, um alle Einkaufslisten einer Person zu finden
    // Hinweis: Diese Methode erfordert eine entsprechende Implementierung in der Service-Schicht,
    // die die JPA-Beziehung zwischen Person und ShoppingListEntity ausnutzt.
    List<ShoppingListEntity> findShoppingListsByPersonId(Long personId);
}
