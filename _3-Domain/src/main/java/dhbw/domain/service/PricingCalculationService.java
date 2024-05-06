package dhbw.domain.service;

import dhbw.domain.GroceryItemEntity;
import dhbw.domain.ShoppingListEntity;
import dhbw.domain.ports.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PricingCalculationService {

    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public PricingCalculationService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public BigDecimal calculateTotalPrice(Long shoppingListId) {
        ShoppingListEntity shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new IllegalArgumentException("Shopping list not found with ID: " + shoppingListId));

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (GroceryItemEntity item : shoppingList.getItems()) {
            totalPrice = totalPrice.add(calculateItemPrice(item));
        }
        return totalPrice;
    }

    private BigDecimal calculateItemPrice(GroceryItemEntity item) {
        BigDecimal basePrice = item.getItemPreis().getAmount();
        return basePrice.multiply(BigDecimal.valueOf(item.getItemAnzahl()));
    }
}
