package dhbw.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personID;
    @Column
    private String personName;

    @JsonBackReference // verhindert zirkuläre Referenzen
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingListEntity> shoppingLists = new ArrayList<>();

    // Getter und Setter
    public Long getPersonID() {
        return personID;
    }

    public void setPersonID(Long id) {
        this.personID = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String name) {
        this.personName = name;
    }

    public List<ShoppingListEntity> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(List<ShoppingListEntity> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }
}
