package dhbw.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private PersonEntity person;

    // Getter und Setter...
    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    // Ein ShoppingList kann viele Items haben.
    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<GroceryItemEntity> items = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroceryItemEntity> getItems() {
        return items;
    }

    public void setItems(List<GroceryItemEntity> items) {
        this.items = items;
    }

    public Object getId() {
        return id;
    }

    // Standardkonstruktoren, Getter und Setter
}
