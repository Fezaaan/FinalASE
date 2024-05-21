package dhbw.domain.designpattern_strategy;


import dhbw.domain.GroceryItemEntity;

import java.math.BigDecimal;
import java.util.List;

public class SimplePriceCalculationStrategy implements PriceCalculationStrategy {
    @Override
    public BigDecimal calculateTotalPrice(List<GroceryItemEntity> items) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (GroceryItemEntity item : items) {
            totalPrice = totalPrice.add(calculateItemPrice(item));
        }
        return totalPrice;
    }

    private BigDecimal calculateItemPrice(GroceryItemEntity item) {
        BigDecimal basePrice = item.getItemPreis().getAmount();
        return basePrice.multiply(BigDecimal.valueOf(item.getItemAnzahl()));
    }
}
