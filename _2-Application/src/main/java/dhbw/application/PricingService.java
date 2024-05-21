package dhbw.application;

import dhbw.domain.service.PricingCalculationService;
import dhbw.domain.designpattern_strategy.PriceCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PricingService {
    private final PricingCalculationService pricingCalculationService;

    @Autowired
    public PricingService(PricingCalculationService pricingCalculationService) {
        this.pricingCalculationService = pricingCalculationService;
    }

    public BigDecimal calculateTotalPriceForList(Long listId) {
        return pricingCalculationService.calculateTotalPrice(listId);
    }

    public void setPriceCalculationStrategy(PriceCalculationStrategy strategy) {
        pricingCalculationService.setPriceCalculationStrategy(strategy);
    }
}
