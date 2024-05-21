package dhbw.domain.service;

import dhbw.domain.GroceryItemEntity;
import dhbw.domain.ShoppingListEntity;
import dhbw.domain.designpattern_strategy.PriceCalculationStrategy;
import dhbw.domain.designpattern_strategy.SimplePriceCalculationStrategy;
import dhbw.domain.ports.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PricingCalculationService {

    private final ShoppingListRepository shoppingListRepository;
    private PriceCalculationStrategy priceCalculationStrategy;

    @Autowired
    public PricingCalculationService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.priceCalculationStrategy = new SimplePriceCalculationStrategy(); // Standardstrategie
    }

    public void setPriceCalculationStrategy(PriceCalculationStrategy priceCalculationStrategy) {
        this.priceCalculationStrategy = priceCalculationStrategy;
    }

    public BigDecimal calculateTotalPrice(Long shoppingListId) {
        ShoppingListEntity shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new IllegalArgumentException("Shopping list not found with ID: " + shoppingListId));

        return priceCalculationStrategy.calculateTotalPrice(shoppingList.getItems());
    }
}
