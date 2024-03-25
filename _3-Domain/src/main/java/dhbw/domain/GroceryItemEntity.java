package dhbw.domain;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Items")
public class GroceryItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private int preis;
    private Integer anzahl; // Kann null sein
    private boolean isChecked;
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public Integer getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public GroceryItemEntity(String itemName, int preis, Integer anzahl, boolean isChecked) {
        this.itemName = itemName;
        this.preis = preis;
        this.anzahl = anzahl;
        this.isChecked = false;
    }
    public GroceryItemEntity() {

    }

}
/*

package dhbw.domain;


public class GroceryItem {
    private String itemName;
    private int preis;
    private Integer anzahl; // Kann null sein, um Optionalität auszudrücken
    private boolean isChecked; // Kann nachträglich geändert werden

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public Integer getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public GroceryItem(String itemName, int preis, Integer anzahl, boolean isChecked) {
        this.itemName = itemName;
        this.preis = preis;
        this.anzahl = anzahl;
        this.isChecked = false;
    }

}

 */