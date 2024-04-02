package dhbw.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Bi-directional relationship
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingListEntity> shoppingLists = new ArrayList<>();

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShoppingListEntity> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(List<ShoppingListEntity> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }
/*
    public void addShoppingList(ShoppingListEntity shoppingList) {
        shoppingLists.add(shoppingList);
        shoppingList.setPerson(this);
    }

    public void removeShoppingList(ShoppingListEntity shoppingList) {
        shoppingLists.remove(shoppingList);
        shoppingList.setPerson(null);
    }*/
}
