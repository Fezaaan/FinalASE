package domain.vo;

import static org.junit.jupiter.api.Assertions.*;

import dhbw.domain.vo.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

class MoneyTest {

    @Test
    void testMoneyConstructorAndGetters() {
        Currency currency = Currency.getInstance("EUR");
        BigDecimal amount = new BigDecimal("10.50");
        Money money = new Money(amount, currency);

        assertSame(currency, money.getCurrency(), "Currency should match the one set in constructor.");
        assertEquals(amount, money.getAmount(), "Amount should match the one set in constructor.");
    }

    @Test
    void testEquals() {
        Currency currency = Currency.getInstance("USD");
        Money money1 = new Money(new BigDecimal("20.00"), currency);
        Money money2 = new Money(new BigDecimal("20.00"), currency);
        Money money3 = new Money(new BigDecimal("20.01"), currency);

        assertEquals(money1, money2, "Money objects with same amount and currency should be equal.");
        assertNotEquals(money1, money3, "Money objects with different amounts should not be equal.");
    }

    @Test
    void testHashCode() {
        Currency currency = Currency.getInstance("GBP");
        Money money1 = new Money(new BigDecimal("100.00"), currency);
        Money money2 = new Money(new BigDecimal("100.00"), currency);

        assertEquals(money1.hashCode(), money2.hashCode(), "Hash code should be the same for equal Money objects.");
    }

    @Test
    void testToString() {
        Currency currency = Currency.getInstance("JPY");
        Money money = new Money(new BigDecimal("500"), currency);

        String expectedString = "¥ 500";
        assertEquals(expectedString, money.toString(), "ToString should generate the correct string representation.");
    }
}
