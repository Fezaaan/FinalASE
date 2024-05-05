package dhbw.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dhbw.domain.vo.Money;
import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Items")
public class GroceryItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemID;

    @Column
    private String itemName;
    @Embedded
    private Money itemPreis;
    @Column
    private Integer itemAnzahl;
    @Column
    private boolean itemIsChecked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_list_id")
    @JsonBackReference
    private ShoppingListEntity shoppingList;
    public void setShoppingList(ShoppingListEntity shoppingList) {
        this.shoppingList = shoppingList;
    }
    public ShoppingListEntity getShoppingList(){
        return shoppingList;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Money getItemPreis() {
        return itemPreis;
    }

    public void setItemPreis(Money itemPreis) {
        this.itemPreis = itemPreis;
    }

    public Integer getItemAnzahl() {
        return itemAnzahl;
    }

    public void setItemAnzahl(Integer anzahl) {
        this.itemAnzahl = anzahl;
    }

    public boolean isItemIsChecked() {
        return itemIsChecked;
    }

    public void setItemIsChecked(boolean itemIsChecked) {
        this.itemIsChecked = itemIsChecked;
    }

    public Long getItemID() {
        return itemID;
    }

    public GroceryItemEntity(String itemName, Money preis, Integer anzahl, boolean itemIsChecked) {
        this.itemName = itemName;
        this.itemPreis = preis;
        this.itemAnzahl = anzahl;
        this.itemIsChecked = false;
    }
    public GroceryItemEntity() {

    }

}