package dhbw.domain.designpattern_strategy;

import dhbw.domain.GroceryItemEntity;

import java.math.BigDecimal;
import java.util.List;

public interface PriceCalculationStrategy {
    BigDecimal calculateTotalPrice(List<GroceryItemEntity> items);
}
