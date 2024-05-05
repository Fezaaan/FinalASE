package dhbw.domain.vo;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public final class Money {

    @Column(name = "price")
    private final BigDecimal amount;
    private final Currency currency;
    // JPA benötigt einen no-arg Konstruktor
    public Money(){
        amount = null;
        currency = null;
    }
    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount) &&
                Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        assert currency != null;
        return String.format("%s %s", currency.getSymbol(), amount);
    }
}
