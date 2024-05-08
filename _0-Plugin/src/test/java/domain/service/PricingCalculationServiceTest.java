package domain.service;

import dhbw.domain.GroceryItemEntity;
import dhbw.domain.ShoppingListEntity;
import dhbw.domain.ports.ShoppingListRepository;
import dhbw.domain.service.PricingCalculationService;
import dhbw.domain.vo.Money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.util.Optional;

class PricingCalculationServiceTest {

    private ShoppingListRepository repositoryMock;
    private PricingCalculationService service;

    @BeforeEach
    void setUp() {
        repositoryMock = Mockito.mock(ShoppingListRepository.class);
        service = new PricingCalculationService(repositoryMock);
    }

    @Test
    void calculateTotalPrice_ShouldCalculateCorrectTotal() {
        // Arrange
        Long shoppingListId = 1L;
        ShoppingListEntity shoppingList = new ShoppingListEntity();
        GroceryItemEntity item1 = new GroceryItemEntity();
        item1.setItemPreis(new Money(new BigDecimal("5.00"), Currency.getInstance("EUR")));
        item1.setItemAnzahl(2);

        GroceryItemEntity item2 = new GroceryItemEntity();
        item2.setItemPreis(new Money(new BigDecimal("3.00"), Currency.getInstance("EUR")));
        item2.setItemAnzahl(3);

        shoppingList.setItems(Arrays.asList(item1, item2));

        when(repositoryMock.findById(shoppingListId)).thenReturn(Optional.of(shoppingList));

        // Expected total price: 5.00*2 + 3.00*3 = 10.00 + 9.00 = 19.00
        BigDecimal expected = new BigDecimal("19.00");

        // Act
        BigDecimal result = service.calculateTotalPrice(shoppingListId);

        // Assert
        assertEquals(expected, result, "Total price calculated incorrectly.");
    }

    @Test
    void calculateTotalPrice_ShouldThrowExceptionWhenListNotFound() {
        // Arrange
        Long shoppingListId = 99L;  // assuming no such list
        when(repositoryMock.findById(shoppingListId)).thenReturn(Optional.empty());

        // Assert
        assertThrows(IllegalArgumentException.class, () -> service.calculateTotalPrice(shoppingListId),
                "Expected an IllegalArgumentException to be thrown if the shopping list is not found.");
    }
}
