package dhbw.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listID;
    @Column
    private String listName;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "personID"
    )
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private PersonEntity owner;

    // Getter und Setter...
    public PersonEntity getOwner() {
        return owner;
    }

    public void setOwner(PersonEntity person) {
        this.owner = person;
    }

    // Ein ShoppingList kann viele Items haben.
    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<GroceryItemEntity> items = new ArrayList<>();

    public String getListName() {
        return listName;
    }

    public void setListName(String name) {
        this.listName = name;
    }

    public List<GroceryItemEntity> getItems() {
        return items;
    }

    public void setItems(List<GroceryItemEntity> items) {
        this.items = items;
    }

    public Object getListID() {
        return listID;
    }

    // Standardkonstruktoren, Getter und Setter
}
