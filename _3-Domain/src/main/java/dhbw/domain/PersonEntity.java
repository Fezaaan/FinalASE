package dhbw.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dhbw.domain.aggregate.ContactInfo;
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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_info_id")
    private ContactInfo contactInfo;

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
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

}
